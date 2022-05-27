package constants;

public enum ForwardConst {

    //action
    ACT("action"),
    ACT_TOP("Top"),
    ACT_USER("User"),
    ACT_AUTH("Auth"),
    ACT_REC("Record"),

    //command
    CMD("command"),
    CMD_NONE(""),
    CMD_SHOW("show"),
    CMD_NEW("entryNew"),
    CMD_INDEX("index"),
    CMD_CREATE("create"),
    CMD_LOGIN("login"),
    CMD_LOGOUT("logout"),
    CMD_SHOW_LOGIN("showLogin"),
    ///jsp
    FW_ERR_UNKNOWN("error/unknown"),
    FW_TOP_INDEX("topPage/index"),
    FW_LOGIN("login/login"),
    FW_USER_INDEX("users/index"),
    FW_USER_NEW("users/new"),
    FW_REC_INDEX("records/index"),
    FW_REC_NEW("records/new");


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
