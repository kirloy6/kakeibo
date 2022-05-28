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
import models.Record;
import services.RecordService;

public class RecordAction extends ActionBase {

    private RecordService service;
    @Override
    public void process() throws ServletException, IOException {

        service=new RecordService();

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
        List<Record> records = service.getAllPerPage(page);


        putRequestScope(AttributeConst.RECORDS, records); //取得した日報データ
        putRequestScope(AttributeConst.PAGE, page); //ページ数
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
                redirect(ForwardConst.ACT_REC, ForwardConst.CMD_INDEX);
            }
        }
    }

        public void edit() throws ServletException, IOException {

            //idを条件に日報データを取得する
            RecordView rv = service.findOne(toNumber(getRequestParam(AttributeConst.REC_ID)));

            //セッションからログイン中の従業員情報を取得
            UserView uv = (UserView) getSessionScope(AttributeConst.LOGIN_USER);

            if (rv == null || uv.getId() != rv.getUser().getId()) {
                //該当の日報データが存在しない、または
                //ログインしている従業員が日報の作成者でない場合はエラー画面を表示
                forward(ForwardConst.FW_ERR_UNKNOWN);

            } else {

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.RECORD, rv); //取得した日報データ

                //編集画面を表示
                forward(ForwardConst.FW_REC_EDIT);
            }


    }


        }








