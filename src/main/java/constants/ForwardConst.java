package constants;

public enum ForwardConst {

  //action
    ACT("action"),
    ACT_TOP("Top"),

  //command
    CMD("command"),
    CMD_NONE(""),

    //jsp
    FW_ERR_UNKNOWN("error/unknown");

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



