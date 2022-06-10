package actions;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.DemandRecordView;
import actions.views.UserView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import constants.MessageConst;
import models.DemandRecord;
import services.DemandRecordService;

public class DemandRecordAction extends ActionBase {

    private DemandRecordService service;
    @Override
    public void process() throws ServletException, IOException {

        service=new DemandRecordService();

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
        List<DemandRecord> records = service.getAllPerPage(page);

        putRequestScope(AttributeConst.DEMANDRECORDS, records);
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
        DemandRecord der = new DemandRecord();
        der.setRecordDate(LocalDate.now());
        putRequestScope(AttributeConst.DEMANDRECORD, der); //日付のみ設定済みの日報インスタンス



        //新規登録画面を表示
        forward(ForwardConst.FW_DEMANDREC_NEW);

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
            DemandRecordView derv = new DemandRecordView(
                    null,
                    uv, //ログインしている従業員を、日報作成者として登録する
                    day,
                    getRequestParam(AttributeConst.DEMANDREC_STORE),
                    toNumber(getRequestParam(AttributeConst.DEMANDREC_PRICE)),
                    null,
                    null);

            List<String> errors = service.create(derv);
            if (errors.size() > 0) {
                //登録中にエラーがあった場合

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.DEMANDRECORD, derv);//入力された日報情報
                putRequestScope(AttributeConst.ERR, errors);//エラーのリスト

                //新規登録画面を再表示
                forward(ForwardConst.FW_DEMANDREC_NEW);

            } else {
                //登録中にエラーがなかった場合




                //セッションに登録完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_TOP, ForwardConst.CMD_INDEX);
            }
        }
    }

    public void edit() throws ServletException, IOException {

        //idを条件に日報データを取得する
        DemandRecordView derv = service.findOne(toNumber(getRequestParam(AttributeConst.DEMANDREC_ID)));

        //セッションからログイン中の従業員情報を取得
        UserView uv = (UserView) getSessionScope(AttributeConst.LOGIN_USER);

        if (derv == null || uv.getId() != derv.getUser().getId()) {
            forward(ForwardConst.FW_ERR_UNKNOWN);

        } else {

            putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
            putRequestScope(AttributeConst.DEMANDRECORD, derv); //取得した日報データ

            //編集画面を表示
            forward(ForwardConst.FW_DEMANDREC_EDIT);
        }


}





    public void update() throws ServletException, IOException {





        //CSRF対策 tokenのチェック
        if (checkToken()) {

            //idを条件に日報データを取得する
            DemandRecordView derv = service.findOne(toNumber(getRequestParam(AttributeConst.DEMANDREC_ID)));

            //入力された日報内容を設定する
            derv.setRecordDate(toLocalDate(getRequestParam(AttributeConst.REC_DATE)));
            derv.setStore(getRequestParam(AttributeConst.DEMANDREC_STORE));
            derv.setPrice(toNumber(getRequestParam(AttributeConst.DEMANDREC_PRICE)));

            //日報データを更新する
            List<String> errors = service.update(derv);


            if (errors.size() > 0) {
                //更新中にエラーが発生した場合

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.DEMANDRECORD, derv); //入力された日報情報
                putRequestScope(AttributeConst.ERR, errors); //エラーのリスト

                //編集画面を再表示
                forward(ForwardConst.FW_DEMANDREC_EDIT);
            } else {
                //更新中にエラーがなかった場合

                //セッションに更新完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_TOP, ForwardConst.CMD_INDEX);

            }
        }

    }

    public void destroy() throws ServletException, IOException {

        int id =toNumber(getRequestParam(AttributeConst.DEMANDREC_ID));
        service.destroy(id);
/*
        RecordView rv = service.findOne(toNumber(getRequestParam(AttributeConst.REC_ID)));
        Record r = RecordConverter.toModel(rv);
*/




        //セッションに登録完了のフラッシュメッセージを設定
        putSessionScope(AttributeConst.FLUSH, MessageConst.I_DELETED.getMessage());

        //一覧画面にリダイレクト
        redirect(ForwardConst.ACT_TOP, ForwardConst.CMD_INDEX);
    }




    }


