package models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name= JpaConst.TABLE_DAILYREC)
@NamedQueries({
    @NamedQuery(
            name=JpaConst.Q_DAILYREC_GET_ALL,
            query = JpaConst.Q_DAILYREC_GET_ALL_DEF),
    @NamedQuery(
            name=JpaConst.Q_DAILYREC_GET_ALL_MINE,
            query = JpaConst.Q_DAILYREC_GET_ALL_MINE_DEF)
})
@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
@Entity
public class DailyRecord {
    @Id
    @Column(name = JpaConst.DREC_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = JpaConst.DREC_COL_USER, nullable = false)
    private User user;

    /**
     * いつの日報かを示す日付
     */
    @Column(name = JpaConst.DREC_COL_REC_DATE, nullable = false)
    private LocalDate recordDate;

    @Column(name = JpaConst.DREC_COL_STORE, nullable = false)
    private String store;


    /**
     * 金額
     */
    @Column(name = JpaConst.DREC_COL_PRICE, nullable = false)
    private Integer price;

    /**
     * 登録日時
     */
    @Column(name = JpaConst.DREC_COL_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    @Column(name = JpaConst.DREC_COL_UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;

}


