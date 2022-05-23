package constants;

public enum ForwardConst {

    //action
    ACT("action"),
    ACT_TOP("Top"),
    ACT_USER("User"),

    //command
    CMD("command"),
    CMD_NONE(""),
    CMD_SHOW("show"),
    CMD_NEW("entryNew"),
    CMD_INDEX("index"),

    ///jsp
    FW_ERR_UNKNOWN("error/unknown"),
    FW_TOP_INDEX("topPage/index"),
    FW_LOGIN("login/login"),
    FW_USER_INDEX("users/index");

    private final String text;

    /**
     * コンストラクタ
     */
    private ForwardConst(final String text) {
        this.text = text;
    }

    /**
     * 値(文字列)取得
     */
    public String getValue() {
        return this.text;
    }

}
