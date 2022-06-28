package services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import actions.views.DemandRecordConverter;
import actions.views.DemandRecordView;
import actions.views.UserConverter;
import actions.views.UserView;
import constants.JpaConst;
import models.DemandRecord;
import models.Store;
import models.validators.DemandRecordValidator;

public class DemandRecordService extends ServiceBase {

    public List<DemandRecord> getMinePerPage(UserView user, int page) {

        List<DemandRecord> demandrecords = em.createNamedQuery(JpaConst.Q_DEMANDREC_GET_ALL_MINE, DemandRecord.class)
                .setParameter(JpaConst.JPQL_PARM_USER, UserConverter.toModel(user))
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return demandrecords;
    }

    public List<DemandRecord> getAllPerPage(int page) {

        List<DemandRecord> demandrecords = em.createNamedQuery(JpaConst.Q_DEMANDREC_GET_ALL, DemandRecord.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return demandrecords;
    }

    /**
     * idを条件に取得したデータをReportViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public DemandRecordView findOne(int id) {
        return DemandRecordConverter.toView(findOneInternal(id));
    }

    /**
     * 画面から入力された日報の登録内容を元にデータを1件作成し、日報テーブルに登録する
     * @param rv 日報の登録内容
     * @return バリデーションで発生したエラーのリスト
     */
    public List<String> create(DemandRecordView derv) {
        List<String> errors = DemandRecordValidator.validate(derv);
        if (errors.size() == 0) {
            LocalDateTime ldt = LocalDateTime.now();
            derv.setCreatedAt(ldt);
            derv.setUpdatedAt(ldt);
            createInternal(derv);
        }

        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    /**
     * 画面から入力された日報の登録内容を元に、日報データを更新する
     * @param rv 日報の更新内容
     * @return バリデーションで発生したエラーのリスト
     */
    public List<String> update(DemandRecordView derv) {

        //バリデーションを行う
        List<String> errors = DemandRecordValidator.validate(derv);

        if (errors.size() == 0) {

            //更新日時を現在時刻に設定
            LocalDateTime ldt = LocalDateTime.now();
            derv.setUpdatedAt(ldt);

            updateInternal(derv);
        }

        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    /**
     * idを条件にデータを1件取得する
     * @param id
     * @return 取得データのインスタンス
     */
    private DemandRecord findOneInternal(int id) {
        return em.find(DemandRecord.class, id);
    }

    /**
     * 日報データを1件登録する ReportView→Report→DB
     * @param rv 日報データ
     */
    private void createInternal(DemandRecordView derv) {

        em.getTransaction().begin();
        em.persist(DemandRecordConverter.toModel(derv));
        em.getTransaction().commit();

    }

    /**
     * 日報データを更新する
     * @param rv 日報データ
     */
    private void updateInternal(DemandRecordView derv) {

        em.getTransaction().begin();
        DemandRecord der = findOneInternal(derv.getId());
        DemandRecordConverter.copyViewToModel(der, derv);
        em.getTransaction().commit();

    }

    public List<Store> getAllPage(int page) {

        List<Store> stores = em.createNamedQuery(JpaConst.Q_STORE_GET_ALL, Store.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return stores;
    }

    public void destroy(int id) {

        em.getTransaction().begin();
        DemandRecord der = findOneInternal(id);
        em.remove(der);
        em.getTransaction().commit();
        em.close();
    }

    public List<DemandRecord> getDailyMonth(UserView user,LocalDate start,LocalDate end) {

        LocalDate ldt = LocalDate.now();

        List<DemandRecord> monthDemandRecords = em.createNamedQuery(JpaConst.Q_DEMANDREC_GET_MONTH, DemandRecord.class)
                .setParameter(JpaConst.JPQL_PARM_USER, UserConverter.toModel(user))
                .setParameter("start" ,start)
                .setParameter("end", end)
                .getResultList();
        return monthDemandRecords;
    }

    public long sumDemandMonth(UserView user,LocalDate start,LocalDate end) {

        LocalDate ldt = LocalDate.now();

        long sumDemandRecords = em.createNamedQuery(JpaConst.Q_DEMANDREC_SUM_MONTH, Long.class)
                .setParameter(JpaConst.JPQL_PARM_USER, UserConverter.toModel(user))
                .setParameter("start" ,start)
                .setParameter("end", end)
                .getSingleResult();
        return sumDemandRecords;
    }







}
