package constants;

public enum AttributeConst {

    //フラッシュメッセージ
    FLUSH("flush"),

    //一覧画面共通
    MAX_ROW("maxRow"),
    PAGE("page"),

  //入力フォーム共通
    TOKEN("_token"),
    ERR("errors"),

  //従業員管理
    USER("user"),
    USERS("users"),
    USER_COUNT("users_count"),
    USER_ID("id"),
    USER_LOGIN_ID("login_id"),
    USER_PASS("password"),
    USER_NAME("name");


    private final String text;
    private final Integer i;

    private AttributeConst(final String text) {
        this.text = text;
        this.i = null;
    }

    private AttributeConst(final Integer i) {
        this.text = null;
        this.i = i;
    }

    public String getValue() {
        return this.text;
    }

    public Integer getIntegerValue() {
        return this.i;
    }

}


