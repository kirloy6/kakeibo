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
    String USER_COL_PASS = "password"; //パスワード



    //Entity名
    String ENTITY_USER = "user"; //ユーザー

    //JPQL内パラメータ
    String JPQL_PARM_LOGIN_ID = "login_id"; //ログインid
    String JPQL_PARM_PASSWORD = "password"; //パスワード
    String JPQL_PARM_USER = "user"; //ユーザー

    //NamedQueryの nameとquery
    //全ての従業員をidの降順に取得する
    String Q_USER_GET_ALL = ENTITY_USER + ".getAll"; //name
    String Q_USER_GET_ALL_DEF = "SELECT u FROM User AS u ORDER BY u.id DESC"; //query
    //全ての従業員の件数を取得する
    String Q_USER_COUNT = ENTITY_USER + ".count";
    String Q_USER_COUNT_DEF = "SELECT COUNT(u) FROM User AS u";
}
