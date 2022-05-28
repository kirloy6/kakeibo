package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name= JpaConst.TABLE_FIX)
@NamedQuery(
        name=JpaConst.Q_FIX_GET_ALL,
        query = JpaConst.Q_FIX_GET_ALL_DEF)



@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
@Entity

public class FixedTitle {

        @Id
        @Column(name = JpaConst.FIX_COL_ID)
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;


        @Column(name = JpaConst.FIX_COL_TITLE, nullable = false)
        private String title ;

}
