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

    LOGIN_USER("login_user"),
    LOGIN_ERR("loginError"),

  //従業員管理
    USER("user"),
    USERS("users"),
    USER_COUNT("users_count"),
    USER_ID("id"),
    USER_LOGIN_ID("login_id"),
    USER_PASS("password"),
    USER_NAME("name"),


    RECORD("record"),
    RECORDS("records"),
    REC_ID("id"),
    REC_DATE("record_date"),
    REC_TITLE("title"),
    REC_PRICE("price"),

    FIXEDTITLE("fixedTitle"),
    FIXEDTITLES("fixedTitles"),

    DAILYRECORD("dailyRecord"),
    DAILYRECORDS("dailyRecords"),
    DAILYREC_ID("id"),
    DAILYREC_DATE("record_date"),
    DAILYREC_STORE("store"),
    DAILYREC_PRICE("price"),

    STORE("store"),
    STORES("stores"),

    MONTHRECORDS("monthRecords"),
    SUMRECORD("sumRecord"),
    MONTHDAILYRECORDS("monthDailyRecords"),
    SUMDAILYRECORD("sumDailyRecord");


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


