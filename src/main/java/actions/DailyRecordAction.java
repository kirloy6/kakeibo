package actions;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.DailyRecordView;
import actions.views.UserView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import constants.MessageConst;
import models.DailyRecord;
import services.DailyRecordService;

public class DailyRecordAction extends ActionBase {

    private DailyRecordService service;
    @Override
    public void process() throws ServletException, IOException {

        service=new DailyRecordService();

        invoke();
        service.close();

    }

    /**
     * 一覧画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void index() throws ServletException, IOException {

        //指定されたページ数の一覧画面に表示する日報データを取得
        int page = getPage();
        List<DailyRecord> records = service.getAllPerPage(page);

        putRequestScope(AttributeConst.DAILYRECORDS, records);
        putRequestScope(AttributeConst.PAGE, page);
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        //一覧画面を表示
        forward(ForwardConst.FW_REC_INDEX);
    }

    public void entryNew() throws ServletException, IOException {

        putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン

        //日報情報の空インスタンスに、日報の日付＝今日の日付を設定する
        DailyRecord dr = new DailyRecord();
        dr.setRecordDate(LocalDate.now());
        putRequestScope(AttributeConst.DAILYRECORD, dr); //日付のみ設定済みの日報インスタンス



        //新規登録画面を表示
        forward(ForwardConst.FW_DAILYREC_NEW);

    }


    public void create() throws ServletException, IOException {



        //CSRF対策 tokenのチェック
        if (checkToken()) {

            //日報の日付が入力されていなければ、今日の日付を設定
            LocalDate day = null;
            if (getRequestParam(AttributeConst.REC_DATE) == null
                    || getRequestParam(AttributeConst.REC_DATE).equals("")) {
                day = LocalDate.now();
            } else {
                day = LocalDate.parse(getRequestParam(AttributeConst.REC_DATE));
            }

            //セッションからログイン中の従業員情報を取得
            UserView uv = (UserView) getSessionScope(AttributeConst.LOGIN_USER);
            //パラメータの値をもとに情報のインスタンスを作成する
            DailyRecordView drv = new DailyRecordView(
                    null,
                    uv, //ログインしている従業員を、日報作成者として登録する
                    day,
                    getRequestParam(AttributeConst.DAILYREC_STORE),
                    toNumber(getRequestParam(AttributeConst.DAILYREC_PRICE)),
                    null,
                    null);

            List<String> errors = service.create(drv);
            if (errors.size() > 0) {
                //登録中にエラーがあった場合

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.DAILYRECORD, drv);//入力された日報情報
                putRequestScope(AttributeConst.ERR, errors);//エラーのリスト

                //新規登録画面を再表示
                forward(ForwardConst.FW_DAILYREC_NEW);

            } else {
                //登録中にエラーがなかった場合




                //セッションに登録完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_REC, ForwardConst.CMD_INDEX);
            }
        }
    }

    public void edit() throws ServletException, IOException {

        //idを条件に日報データを取得する
        DailyRecordView drv = service.findOne(toNumber(getRequestParam(AttributeConst.DAILYREC_ID)));

        //セッションからログイン中の従業員情報を取得
        UserView uv = (UserView) getSessionScope(AttributeConst.LOGIN_USER);

        if (drv == null || uv.getId() != drv.getUser().getId()) {
            forward(ForwardConst.FW_ERR_UNKNOWN);

        } else {

            putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
            putRequestScope(AttributeConst.DAILYRECORD, drv); //取得した日報データ

            //編集画面を表示
            forward(ForwardConst.FW_DAILYREC_EDIT);
        }


}

    public void split() throws ServletException, IOException {

        //idを条件に日報データを取得する
        DailyRecordView drv = service.findOne(toNumber(getRequestParam(AttributeConst.DAILYREC_ID)));

        //セッションからログイン中の従業員情報を取得
        UserView uv = (UserView) getSessionScope(AttributeConst.LOGIN_USER);

        if (drv == null || uv.getId() != drv.getUser().getId()) {
            forward(ForwardConst.FW_ERR_UNKNOWN);

        } else {

            putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
            putRequestScope(AttributeConst.DAILYRECORD, drv); //取得した日報データ

            //編集画面を表示
            forward(ForwardConst.FW_DAILYREC_SPLIT);
        }


}
    public void update() throws ServletException, IOException {


        //CSRF対策 tokenのチェック
        if (checkToken()) {

            //idを条件に日報データを取得する
            DailyRecordView drv = service.findOne(toNumber(getRequestParam(AttributeConst.DAILYREC_ID)));

            //入力された日報内容を設定する
            drv.setRecordDate(toLocalDate(getRequestParam(AttributeConst.REC_DATE)));
            drv.setStore(getRequestParam(AttributeConst.DAILYREC_STORE));
            drv.setPrice(toNumber(getRequestParam(AttributeConst.DAILYREC_PRICE)));

            //日報データを更新する
            List<String> errors = service.update(drv);


            if (errors.size() > 0) {
                //更新中にエラーが発生した場合

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.DAILYRECORD, drv); //入力された日報情報
                putRequestScope(AttributeConst.ERR, errors); //エラーのリスト

                //編集画面を再表示
                forward(ForwardConst.FW_DAILYREC_EDIT);
            } else {
                //更新中にエラーがなかった場合

                //セッションに更新完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_TOP, ForwardConst.CMD_INDEX);

            }
        }

    }




    public void updateSplit() throws ServletException, IOException {




        String[] prices =request.getParameterValues("price2");
        List<Integer> priceList = new ArrayList<>();
                for(int j =0;j<prices.length;j++) {
                       System.out.println(prices[j]);
                       if(prices[j]==null || prices[j].equals("")) {
                           prices[j] ="1";
                       } else {
                       priceList.add(Integer.parseInt(prices[j]));
                }
                }
                String[] months =  request.getParameterValues("month");
                List<LocalDate> monthList = new ArrayList<>();
                for(int j =0;j<months.length;j++) {
                       System.out.println(months[j]);
                       if(months[j] == null || months[j].equals("")) {
                           months[j] = "0000-01";
                           monthList.add(toLocalDate(months[j]+"-01"));
                       } else {
                       monthList.add(toLocalDate(months[j]+"-01"));
                }
                }






        //CSRF対策 tokenのチェック
        if (checkToken()) {

            //idを条件に日報データを取得する
            DailyRecordView drv = service.findOne(toNumber(getRequestParam(AttributeConst.DAILYREC_ID)));

            //入力された日報内容を設定する
            drv.setRecordDate(toLocalDate(getRequestParam(AttributeConst.REC_DATE)));
            drv.setStore(getRequestParam(AttributeConst.DAILYREC_STORE));
            drv.setPrice(toNumber(getRequestParam(AttributeConst.DAILYREC_PRICE)));

            UserView uv = (UserView) getSessionScope(AttributeConst.LOGIN_USER);
            //日報データを更新する
            List<String> errors = service.update(drv);
            for(int i=0;i<priceList.size();i++) {
                DailyRecordView drv1 = new DailyRecordView(
                        null,
                        uv, //ログインしている従業員を、日報作成者として登録する
                        monthList.get(i),
                        getRequestParam(AttributeConst.DAILYREC_STORE),
                        priceList.get(i),
                        null,
                        null);

                List<String> errors1 = service.createUp(drv1);
                errors.addAll(errors1);
            }


            if (errors.size() > 0) {
                //更新中にエラーが発生した場合

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.DAILYRECORD, drv); //入力された日報情報
                putRequestScope(AttributeConst.ERR, errors); //エラーのリスト

                //編集画面を再表示
                forward(ForwardConst.FW_DAILYREC_EDIT);
            } else {
                //更新中にエラーがなかった場合

                //セッションに更新完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_REC, ForwardConst.CMD_INDEX);

            }
        }

    }

    public void destroy() throws ServletException, IOException {

        int id =toNumber(getRequestParam(AttributeConst.DAILYREC_ID));
        service.destroy(id);
/*
        RecordView rv = service.findOne(toNumber(getRequestParam(AttributeConst.REC_ID)));
        Record r = RecordConverter.toModel(rv);
*/




        //セッションに登録完了のフラッシュメッセージを設定
        putSessionScope(AttributeConst.FLUSH, MessageConst.I_DELETED.getMessage());

        //一覧画面にリダイレクト
        redirect(ForwardConst.ACT_REC, ForwardConst.CMD_INDEX);
    }




    }


