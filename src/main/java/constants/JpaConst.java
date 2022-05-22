package constants;

public interface JpaConst {

  //persistence-unit名
    String PERSISTENCE_UNIT_NAME = "kakeibo";

  //データ取得件数の最大値
    int ROW_PER_PAGE = 15; //1ページに表示するレコードの数

    //ユーザーテーブル
    String TABLE_USER = "users"; //テーブル名
    //ユーザーテーブルカラム
    String USER_COL_ID = "id"; //id
    String USER_COL_LOGIN_ID="login_id";//ログインid
    String USER_COL_NAME = "name"; //氏名
    String USER_COL_PASS = "password"; //パスワード

}
