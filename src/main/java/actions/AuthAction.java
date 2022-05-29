package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.UserView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.MessageConst;
import constants.PropertyConst;
import models.FixedTitle;
import models.Store;
import services.DailyRecordService;
import services.RecordService;
import services.UserService;

/**
 * 認証に関する処理を行うActionクラス
 *
 */
public class AuthAction extends ActionBase {

    private UserService service;
    private RecordService rservice;
    private DailyRecordService drservice;


    /**
     * メソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        service = new UserService();
        rservice = new RecordService();
        drservice = new DailyRecordService();

        //メソッドを実行
        invoke();

        service.close();
        rservice.close();
        drservice.close();

    }

    /**
     * ログイン画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void showLogin() throws ServletException, IOException {

        //CSRF対策用トークンを設定
        putRequestScope(AttributeConst.TOKEN, getTokenId());

        //セッションにフラッシュメッセージが登録されている場合はリクエストスコープに設定する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH,flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        //ログイン画面を表示
        forward(ForwardConst.FW_LOGIN);
    }


    public void login() throws ServletException, IOException {

        String login_id = getRequestParam(AttributeConst.USER_LOGIN_ID);
        String plainPass = getRequestParam(AttributeConst.USER_PASS);
        String pepper = getContextScope(PropertyConst.PEPPER);

        //有効な従業員か認証する
        Boolean isValidUser = service.validateLogin(login_id, plainPass,pepper);

        if (isValidUser) {
            //認証成功の場合

            //CSRF対策 tokenのチェック
            if (checkToken()) {

                //ログインした従業員のDBデータを取得
                UserView uv = service.findOne(login_id, plainPass,pepper);
                List<FixedTitle> fixedTitles = rservice.getAllPage(1);

                putSessionScope(AttributeConst.FIXEDTITLES, fixedTitles);
                List<Store> stores = drservice.getAllPage(1);

                putSessionScope(AttributeConst.STORES, stores);

                //セッションにログインした従業員を設定
                putSessionScope(AttributeConst.LOGIN_USER, uv);
                //セッションにログイン完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_LOGINED.getMessage());
                //トップページへリダイレクト
                redirect(ForwardConst.ACT_TOP, ForwardConst.CMD_INDEX);
            }
        } else {
            //認証失敗の場合

            //CSRF対策用トークンを設定
            putRequestScope(AttributeConst.TOKEN, getTokenId());
            //認証失敗エラーメッセージ表示フラグをたてる
            putRequestScope(AttributeConst.LOGIN_ERR, true);
            //入力された従業員コードを設定
            putRequestScope(AttributeConst.USER_LOGIN_ID, login_id);

            //ログイン画面を表示
            forward(ForwardConst.FW_LOGIN);
        }
    }

    public void logout() throws ServletException, IOException {

        //セッションからログイン従業員のパラメータを削除
        removeSessionScope(AttributeConst.LOGIN_USER);

        //セッションにログアウト時のフラッシュメッセージを追加
        putSessionScope(AttributeConst.FLUSH, MessageConst.I_LOGOUT.getMessage());

        //ログイン画面にリダイレクト
        redirect(ForwardConst.ACT_AUTH, ForwardConst.CMD_SHOW_LOGIN);

    }


}
