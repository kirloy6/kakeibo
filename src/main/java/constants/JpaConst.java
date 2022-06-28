package constants;

//* ※インターフェイスに定義した変数は public static final 修飾子がついているとみなされる
public interface JpaConst {
  //persistence-unit名
    String PERSISTENCE_UNIT_NAME = "kakeibo";

    //データ取得件数の最大値
    int ROW_PER_PAGE = 15; //1ページに表示するレコードの数

    //従業員テーブル
    String TABLE_USER = "users"; //テーブル名
    //従業員テーブルカラム
    String USER_COL_ID = "id"; //id
    String USER_COL_LOGIN_ID = "login_id"; //社員番号
    String USER_COL_NAME = "name"; //氏名
    String USER_COL_PASS = "password";//パスワード

    //レコードテーブル
    String TABLE_REC ="records";

    String REC_COL_ID = "id"; //id
    String REC_COL_USER = "user_id"; //日報を作成した従業員のid
    String REC_COL_REC_DATE = "record_date"; //いつの日報かを示す日付
    String REC_COL_TITLE = "title"; //日報のタイトル
    String REC_COL_PRICE = "price"; //日報の内容
    String REC_COL_CREATED_AT = "created_at"; //登録日時
    String REC_COL_UPDATED_AT = "updated_at"; //更新日時

    String TABLE_FIX ="fixedtitles";

    String FIX_COL_ID = "id"; //id
    String FIX_COL_TITLE = "fixedtitle";

  //デイリーレコードテーブル
    String TABLE_DAILYREC ="dailyrecords";

    String DREC_COL_ID = "id"; //id
    String DREC_COL_USER = "user_id"; //日報を作成した従業員のid
    String DREC_COL_REC_DATE = "record_date"; //いつの日報かを示す日付
    String DREC_COL_STORE = "store"; //日報のタイトル
    String DREC_COL_PRICE = "price"; //日報の内容
    String DREC_COL_CREATED_AT = "created_at"; //登録日時
    String DREC_COL_UPDATED_AT = "updated_at"; //更新日時

    String TABLE_STORE ="stores";

    String STORE_COL_ID = "id"; //id
    String STORE_COL_STORE = "store";

    String TABLE_DEMANDREC ="demandrecords";

    String DEREC_COL_ID = "id"; //id
    String DEREC_COL_USER = "user_id"; //日報を作成した従業員のid
    String DEREC_COL_REC_DATE = "record_date"; //いつの日報かを示す日付
    String DEREC_COL_STORE = "store"; //日報のタイトル
    String DEREC_COL_PRICE = "price"; //日報の内容
    String DEREC_COL_CREATED_AT = "created_at"; //登録日時
    String DEREC_COL_UPDATED_AT = "updated_at"; //更新日時





    //Entity名
    String ENTITY_USER = "user"; //ユーザー
    String ENTITY_REC ="record";
    String ENTITY_FIX ="fixedTitle";
    String ENTITY_DAILYREC ="dailyrecord";
    String ENTITY_STORE="store";
    String ENTITY_DEMANDREC="demandrecord";

    //JPQL内パラメータ
    String JPQL_PARM_LOGIN_ID = "login_id"; //ログインid
    String JPQL_PARM_PASSWORD = "password"; //パスワード
    String JPQL_PARM_USER = "user"; //ユーザー
    String JPQL_PARM_TIME="time";

    //NamedQueryの nameとquery
    //全ての従業員をidの降順に取得する
    String Q_USER_GET_ALL = ENTITY_USER + ".getAll"; //name
    String Q_USER_GET_ALL_DEF = "SELECT u FROM User AS u ORDER BY u.id DESC"; //query
    //全ての従業員の件数を取得する
    String Q_USER_COUNT = ENTITY_USER + ".count";
    String Q_USER_COUNT_DEF = "SELECT COUNT(u) FROM User AS u";

    String Q_USER_GET_BY_LOGIN_ID_AND_PASS = ENTITY_USER + ".getByLogin_idAndPass";
    String Q_USER_GET_BY_LOGIN_ID_AND_PASS_DEF = "SELECT u FROM User AS u WHERE u.login_id = :" + JPQL_PARM_LOGIN_ID + " AND u.password = :" + JPQL_PARM_PASSWORD;

    String Q_USER_COUNT_RESISTERED_BY_LOGIN_ID= ENTITY_USER + ".countRegisteredByLogin_id";
    String Q_USER_COUNT_RESISTERED_BY_LOGIN_ID_DEF = "SELECT COUNT (u) FROM User AS u WHERE u.login_id = :" + JPQL_PARM_LOGIN_ID;

  //全ての日報をidの降順に取得する
    String Q_REC_GET_ALL = ENTITY_REC + ".getAll";
    String Q_REC_GET_ALL_DEF = "SELECT r FROM Record AS r ORDER BY r.id DESC";

    //指定した従業員が作成した日報を全件idの降順で取得する
    String Q_REC_GET_ALL_MINE = ENTITY_REC + ".getAllMine";
    String Q_REC_GET_ALL_MINE_DEF = "SELECT r FROM Record AS r WHERE r.user = :" + JPQL_PARM_USER + " ORDER BY r.id DESC";

    String Q_FIX_GET_ALL = ENTITY_FIX + ".getAll";
    String Q_FIX_GET_ALL_DEF = "SELECT f FROM FixedTitle AS f ORDER BY f.id ASC";

  //全ての日報をidの降順に取得する
    String Q_DAILYREC_GET_ALL = ENTITY_DAILYREC + ".getAll";
    String Q_DAILYREC_GET_ALL_DEF = "SELECT d FROM DailyRecord AS d ORDER BY d.id DESC";

    //指定した従業員が作成した日報を全件idの降順で取得する
    String Q_DAILYREC_GET_ALL_MINE = ENTITY_DAILYREC + ".getAllMine";
    String Q_DAILYREC_GET_ALL_MINE_DEF = "SELECT d FROM DailyRecord AS d WHERE d.user = :" + JPQL_PARM_USER + " ORDER BY d.id DESC";

    String Q_STORE_GET_ALL = ENTITY_STORE + ".getAll";
    String Q_STORE_GET_ALL_DEF = "SELECT s FROM Store AS s ORDER BY s.id ASC";

    String Q_REC_GET_MONTH = ENTITY_REC + ".getMonth";
    String Q_REC_GET_MONTH_DEF = "SELECT r FROM Record AS r WHERE r.user = :" + JPQL_PARM_USER + " AND r.recordDate >= :start AND r.recordDate <= :end ORDER BY r.recordDate ASC";

    String Q_REC_SUM_MONTH = ENTITY_REC + ".sumMonth";
    String Q_REC_SUM_MONTH_DEF = "SELECT COALESCE(SUM(r.price), 0) FROM Record AS r WHERE r.user = :" + JPQL_PARM_USER + " AND r.recordDate >= :start AND r.recordDate <= :end ORDER BY r.recordDate ASC";


    String Q_DAILYREC_GET_MONTH = ENTITY_DAILYREC + ".getDailyMonth";
    String Q_DAILYREC_GET_MONTH_DEF = "SELECT d FROM DailyRecord AS d WHERE d.user = :" + JPQL_PARM_USER + " AND d.recordDate >= :start AND d.recordDate <= :end  ORDER BY d.recordDate ASC";

    String Q_DAILYREC_SUM_MONTH = ENTITY_DAILYREC + ".sumDailyMonth";
    String Q_DAILYREC_SUM_MONTH_DEF = "SELECT COALESCE(SUM(d.price), 0) FROM DailyRecord AS d WHERE d.user = :" + JPQL_PARM_USER + " AND d.recordDate >= :start AND d.recordDate <= :end ORDER BY d.recordDate ASC";

    String Q_DAILYREC_GET_YEAR = ENTITY_DAILYREC + ".getDailyYear";
    String Q_DAILYREC_GET_YEAR_DEF = "SELECT d FROM DailyRecord AS d  WHERE d.recordDate >= :start AND d.recordDate <= :end ORDER BY d.recordDate ASC";

    String Q_DAILYREC_SUM_YEAR = ENTITY_DAILYREC + ".sumDailyYear";
    String Q_DAILYREC_SUM_YEAR_DEF = "SELECT COALESCE(SUM(d.price), 0) FROM DailyRecord AS d WHERE d.recordDate >= :start AND d.recordDate <= :end ORDER BY d.recordDate ASC";


  //全ての日報をidの降順に取得する
    String Q_DEMANDREC_GET_ALL = ENTITY_DEMANDREC + ".getAll";
    String Q_DEMANDREC_GET_ALL_DEF = "SELECT de FROM DemandRecord AS de ORDER BY de.id DESC";

    //指定した従業員が作成した日報を全件idの降順で取得する
    String Q_DEMANDREC_GET_ALL_MINE = ENTITY_DEMANDREC + ".getAllMine";
    String Q_DEMANDREC_GET_ALL_MINE_DEF = "SELECT de FROM DemandRecord AS de WHERE de.user = :" + JPQL_PARM_USER + " ORDER BY de.id DESC";

    String Q_DEMANDREC_GET_MONTH = ENTITY_DEMANDREC + ".getDemandMonth";
    String Q_DEMANDREC_GET_MONTH_DEF = "SELECT de FROM DemandRecord AS de WHERE de.user = :" + JPQL_PARM_USER + " AND de.recordDate >= :start AND de.recordDate <= :end ORDER BY de.recordDate ASC";

    String Q_DEMANDREC_SUM_MONTH = ENTITY_DEMANDREC + ".sumDemandMonth";
    String Q_DEMANDREC_SUM_MONTH_DEF = "SELECT COALESCE(SUM(de.price), 0) FROM DemandRecord AS de WHERE de.user = :" + JPQL_PARM_USER + " AND de.recordDate >= :start AND de.recordDate <= :end ORDER BY de.recordDate ASC";




}