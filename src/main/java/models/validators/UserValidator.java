package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.UserView;
import constants.MessageConst;
import services.UserService;

/**
 * 従業員インスタンスに設定されている値のバリデーションを行うクラス
 *
 */
public class UserValidator {

    /**
     * 従業員インスタンスの各項目についてバリデーションを行う
     * @param service 呼び出し元Serviceクラスのインスタンス
     * @param ev EmployeeViewのインスタンス
     * @param codeDuplicateCheckFlag 社員番号の重複チェックを実施するかどうか(実施する:true 実施しない:false)
     * @param passwordCheckFlag パスワードの入力チェックを実施するかどうか(実施する:true 実施しない:false)
     * @return エラーのリスト
     */
    public static List<String> validate(
            UserService service, UserView uv, Boolean login_idDuplicateCheckFlag, Boolean passwordCheckFlag) {
        List<String> errors = new ArrayList<String>();
      //社員番号のチェック
        String login_idError = validateLogin_id(service, uv.getLogin_id(), login_idDuplicateCheckFlag);
        if (!login_idError.equals("")) {
            errors.add(login_idError);
        }



        //氏名のチェック
        String nameError = validateName(uv.getName());
        if (!nameError.equals("")) {
            errors.add(nameError);
        }

        //パスワードのチェック
        String passError = validatePassword(uv.getPassword(), passwordCheckFlag);
        if (!passError.equals("")) {
            errors.add(passError);
        }

        return errors;
    }

    private static String validateLogin_id(UserService service, String login_id, Boolean login_idDuplicateCheckFlag) {

        //入力値がなければエラーメッセージを返却
        if (login_id == null || login_id.equals("")) {
            return MessageConst.E_NOUSER_LOGIN_ID.getMessage();
        }

        if (login_idDuplicateCheckFlag) {
            //社員番号の重複チェックを実施

            long usersCount = isDuplicateUser(service, login_id);

            //同一社員番号が既に登録されている場合はエラーメッセージを返却
            if (usersCount > 0) {
                return MessageConst.E_USER_LOGIN_ID_EXIST.getMessage();
            }
        }

        //エラーがない場合は空文字を返却
        return "";
    }

    private static long isDuplicateUser(UserService service, String login_id) {

        long usersCount = service.countByLogin_id(login_id);
        return usersCount;
    }

    /**
     * 氏名に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param name 氏名
     * @return エラーメッセージ
     */
    private static String validateName(String name) {

        if (name == null || name.equals("")) {
            return MessageConst.E_NONAME.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }

    private static String validatePassword(String password, Boolean passwordCheckFlag) {

        //入力チェックを実施 かつ 入力値がなければエラーメッセージを返却
        if (passwordCheckFlag && (password == null || password.equals(""))) {
            return MessageConst.E_NOPASSWORD.getMessage();
        }

        //エラーがない場合は空文字を返却
        return "";
    }





}
