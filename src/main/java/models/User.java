package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = JpaConst.TABLE_USER)
@NamedQueries({
    @NamedQuery(
            name=JpaConst.Q_USER_GET_ALL,
            query = JpaConst.Q_USER_GET_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_USER_COUNT,
            query = JpaConst.Q_USER_COUNT_DEF)
})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    /**
     * id
     */
    @Id
    @Column(name = JpaConst.USER_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * ログインid
     */
    @Column(name = JpaConst.USER_COL_LOGIN_ID, nullable = false, unique = true)
    private String login_id;

    /**
     * 氏名
     */
    @Column(name = JpaConst.USER_COL_NAME, nullable = false)
    private String name;

    /**
     * パスワード
     */
    @Column(name = JpaConst.USER_COL_PASS, length = 64, nullable = false)
    private String password;

}
