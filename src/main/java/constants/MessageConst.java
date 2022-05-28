package constants;

public enum MessageConst {

  //認証
    I_LOGINED("ログインしました"),
    E_LOGINED("ログインに失敗しました。"),
    I_LOGOUT("ログアウトしました。"),


    //DB更新
    I_REGISTERED("登録が完了しました。"),
    I_UPDATED("更新が完了しました。"),

  //バリデーション
    E_NONAME("氏名を入力してください。"),
    E_NOPASSWORD("パスワードを入力してください。"),
    E_NOUSER_LOGIN_ID("ログインidを入力してください。"),
    E_USER_LOGIN_ID_EXIST("入力されたログインIDの情報は既に存在しています。");


    private final String text;

    /**
     * コンストラクタ
     */
    private MessageConst(final String text) {
        this.text = text;
    }

    /**
     * 値(文字列)取得
     */
    public String getMessage() {
        return this.text;
    }


}
