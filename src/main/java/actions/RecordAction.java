package actions;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.RecordView;
import actions.views.UserView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import constants.MessageConst;
import models.DailyRecord;
import models.DemandRecord;
import models.FixedTitle;
import models.Record;
import services.DailyRecordService;
import services.DemandRecordService;
import services.RecordService;

public class RecordAction extends ActionBase {

    private RecordService service;
    private DailyRecordService drservice;
    private DemandRecordService derservice;

    @Override
    public void process() throws ServletException, IOException {

        service=new RecordService();
        drservice=new DailyRecordService();
        derservice=new DemandRecordService();

        invoke();
        service.close();
        drservice.close();
        derservice.close();

    }

    /**
     * 一覧画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void index() throws ServletException, IOException {





        //指定されたページ数の一覧画面に表示する日報データを取得
        int page = getPage();
        List<Record> records = service.getAllPerPage(page);
        List<DailyRecord> dailyRecords = drservice.getAllPerPage(page);
        List<DemandRecord> demandRecords = derservice.getAllPerPage(page);

        putRequestScope(AttributeConst.DEMANDRECORDS, demandRecords);
        putRequestScope(AttributeConst.DAILYRECORDS, dailyRecords);
        putRequestScope(AttributeConst.RECORDS, records);
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
        Record r = new Record();
        r.setRecordDate(LocalDate.now());
        putRequestScope(AttributeConst.RECORD, r); //日付のみ設定済みの日報インスタンス

        List<FixedTitle> fixedTitles = service.getAllPage(1);

        putSessionScope(AttributeConst.FIXEDTITLES, fixedTitles);

        //新規登録画面を表示
        forward(ForwardConst.FW_REC_NEW);

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
            //パラメータの値をもとに日報情報のインスタンスを作成する
            RecordView rv = new RecordView(
                    null,
                    uv, //ログインしている従業員を、日報作成者として登録する
                    day,
                    getRequestParam(AttributeConst.REC_TITLE),
                    toNumber(getRequestParam(AttributeConst.REC_PRICE)),
                    null,
                    null);

            List<String> errors = service.create(rv);
            if (errors.size() > 0) {
                //登録中にエラーがあった場合

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.RECORD, rv);//入力された日報情報
                putRequestScope(AttributeConst.ERR, errors);//エラーのリスト

                //新規登録画面を再表示
                forward(ForwardConst.FW_REC_NEW);

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
            RecordView rv = service.findOne(toNumber(getRequestParam(AttributeConst.REC_ID)));

            //セッションからログイン中の従業員情報を取得
            UserView uv = (UserView) getSessionScope(AttributeConst.LOGIN_USER);

            if (rv == null || uv.getId() != rv.getUser().getId()) {
                forward(ForwardConst.FW_ERR_UNKNOWN);

            } else {

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.RECORD, rv); //取得した日報データ

                //編集画面を表示
                forward(ForwardConst.FW_REC_EDIT);
            }


    }
        public void update() throws ServletException, IOException {

            //CSRF対策 tokenのチェック
            if (checkToken()) {

                //idを条件に日報データを取得する
                RecordView rv = service.findOne(toNumber(getRequestParam(AttributeConst.REC_ID)));

                //入力された日報内容を設定する
                rv.setRecordDate(toLocalDate(getRequestParam(AttributeConst.REC_DATE)));
                rv.setTitle(getRequestParam(AttributeConst.REC_TITLE));
                rv.setPrice(toNumber(getRequestParam(AttributeConst.REC_PRICE)));

                //日報データを更新する
                List<String> errors = service.update(rv);

                if (errors.size() > 0) {
                    //更新中にエラーが発生した場合

                    putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                    putRequestScope(AttributeConst.RECORD, rv); //入力された日報情報
                    putRequestScope(AttributeConst.ERR, errors); //エラーのリスト

                    //編集画面を再表示
                    forward(ForwardConst.FW_REC_EDIT);
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
            int id =toNumber(getRequestParam(AttributeConst.REC_ID));
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








