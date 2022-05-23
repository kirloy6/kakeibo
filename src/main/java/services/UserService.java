package services;

import java.util.List;

import actions.views.UserConverter;
import actions.views.UserView;
import constants.JpaConst;
import models.User;
import models.validators.UserValidator;

/**
 * 従業員テーブルの操作に関わる処理を行うクラス
 */
public class UserService extends ServiceBase {

    /**
     * 指定されたページ数の一覧画面に表示するデータを取得し、EmployeeViewのリストで返却する
     * @param page ページ数
     * @return 表示するデータのリスト
     */
    public List<UserView> getPerPage(int page) {
        List<User>users = em.createNamedQuery(JpaConst.Q_USER_GET_ALL, User.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();

        return UserConverter.toViewList(users);
    }

    /**
     * 従業員テーブルのデータの件数を取得し、返却する
     * @return 従業員テーブルのデータの件数
     */
    public long countAll() {
        long empCount = (long) em.createNamedQuery(JpaConst.Q_USER_COUNT, Long.class)
                .getSingleResult();

        return empCount;
    }



    /**
     * idを条件に取得したデータをEmployeeViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public UserView findOne(int id) {
        User u = findOneInternal(id);
        return UserConverter.toView(u);
    }


    /**
     * 画面から入力された従業員の登録内容を元にデータを1件作成し、従業員テーブルに登録する
     * @param ev 画面から入力された従業員の登録内容
     * @param pepper pepper文字列
     * @return バリデーションや登録処理中に発生したエラーのリスト
     */
    public List<String> createUser(UserView uv) {

        //パスワードをハッシュ化して設定
        String pass = uv.getPassword();
        uv.setPassword(pass);


        //登録内容のバリデーションを行う
        List<String> errors = UserValidator.validate(this, uv, true, true);

        //バリデーションエラーがなければデータを登録する
        if (errors.size() == 0) {
            create(uv);
        }

        //エラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    /**
     * 画面から入力された従業員の更新内容を元にデータを1件作成し、従業員テーブルを更新する
     * @param ev 画面から入力された従業員の登録内容
     * @param pepper pepper文字列
     * @return バリデーションや更新処理中に発生したエラーのリスト
     */
    public List<String> update(UserView uv, String pepper) {

        //idを条件に登録済みの従業員情報を取得する
        UserView savedEmp = findOne(uv.getId());

        boolean validateLogin_id = false;
        if (!savedEmp.getLogin_id().equals(uv.getLogin_id())) {
            //社員番号を更新する場合

            //社員番号についてのバリデーションを行う
            validateLogin_id = true;
            //変更後の社員番号を設定する
            savedEmp.setLogin_id(uv.getLogin_id());
        }

        boolean validatePass = false;
        if (uv.getPassword() != null && !uv.getPassword().equals("")) {
            //パスワードに入力がある場合

            //パスワードについてのバリデーションを行う
            validatePass = true;

        }

        savedEmp.setName(uv.getName()); //変更後の氏名を設定する


        //更新内容についてバリデーションを行う
        List<String> errors = UserValidator.validate(this, savedEmp, validateLogin_id, validatePass);

        //バリデーションエラーがなければデータを更新する
        if (errors.size() == 0) {
            update(savedEmp);
        }

        //エラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }


    /**
     * idを条件にデータを1件取得し、Employeeのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    private User findOneInternal(int id) {
        User u = em.find(User.class, id);

        return u;
    }

    /**
     * 従業員データを1件登録する
     * @param ev 従業員データ
     * @return 登録結果(成功:true 失敗:false)
     */
    private void create(UserView uv) {

        em.getTransaction().begin();
        em.persist(UserConverter.toModel(uv));
        em.getTransaction().commit();

    }

    /**
     * 従業員データを更新する
     * @param ev 画面から入力された従業員の登録内容
     */
    private void update(UserView uv) {

        em.getTransaction().begin();
        User u = findOneInternal(uv.getId());
        UserConverter.copyViewToModel(u, uv);
        em.getTransaction().commit();

    }

}