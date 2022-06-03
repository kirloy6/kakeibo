package services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import actions.views.DailyRecordConverter;
import actions.views.DailyRecordView;
import constants.JpaConst;
import models.DailyRecord;
import models.Store;
import models.User;
import models.validators.DailyRecordValidator;

public class DailyRecordService extends ServiceBase {

    public List<DailyRecord> getMinePerPage(User user, int page) {

        List<DailyRecord> dailyrecords = em.createNamedQuery(JpaConst.Q_DAILYREC_GET_ALL_MINE, DailyRecord.class)
                .setParameter(JpaConst.JPQL_PARM_USER, user)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return dailyrecords;
    }

    public List<DailyRecord> getAllPerPage(int page) {

        List<DailyRecord> dailyrecords = em.createNamedQuery(JpaConst.Q_DAILYREC_GET_ALL, DailyRecord.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return dailyrecords;
    }

    /**
     * idを条件に取得したデータをReportViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public DailyRecordView findOne(int id) {
        return DailyRecordConverter.toView(findOneInternal(id));
    }

    /**
     * 画面から入力された日報の登録内容を元にデータを1件作成し、日報テーブルに登録する
     * @param rv 日報の登録内容
     * @return バリデーションで発生したエラーのリスト
     */
    public List<String> create(DailyRecordView drv) {
        List<String> errors = DailyRecordValidator.validate(drv);
        if (errors.size() == 0) {
            LocalDateTime ldt = LocalDateTime.now();
            drv.setCreatedAt(ldt);
            drv.setUpdatedAt(ldt);
            createInternal(drv);
        }

        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    /**
     * 画面から入力された日報の登録内容を元に、日報データを更新する
     * @param rv 日報の更新内容
     * @return バリデーションで発生したエラーのリスト
     */
    public List<String> update(DailyRecordView drv) {

        //バリデーションを行う
        List<String> errors = DailyRecordValidator.validate(drv);

        if (errors.size() == 0) {

            //更新日時を現在時刻に設定
            LocalDateTime ldt = LocalDateTime.now();
            drv.setUpdatedAt(ldt);

            updateInternal(drv);
        }

        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    /**
     * idを条件にデータを1件取得する
     * @param id
     * @return 取得データのインスタンス
     */
    private DailyRecord findOneInternal(int id) {
        return em.find(DailyRecord.class, id);
    }

    /**
     * 日報データを1件登録する ReportView→Report→DB
     * @param rv 日報データ
     */
    private void createInternal(DailyRecordView drv) {

        em.getTransaction().begin();
        em.persist(DailyRecordConverter.toModel(drv));
        em.getTransaction().commit();

    }

    /**
     * 日報データを更新する
     * @param rv 日報データ
     */
    private void updateInternal(DailyRecordView drv) {

        em.getTransaction().begin();
        DailyRecord dr = findOneInternal(drv.getId());
        DailyRecordConverter.copyViewToModel(dr, drv);
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
        DailyRecord dr = findOneInternal(id);
        em.remove(dr);
        em.getTransaction().commit();
        em.close();
    }

    public List<DailyRecord> getMonth(LocalDate start,LocalDate end) {

        LocalDate ldt = LocalDate.now();

        List<DailyRecord> monthDailyRecords = em.createNamedQuery(JpaConst.Q_DAILYREC_GET_MONTH, DailyRecord.class)
                .setParameter("start" ,start)
                .setParameter("end", end)
                .getResultList();
        return monthDailyRecords;
    }

    public long sumMonth(LocalDate start,LocalDate end) {

        LocalDate ldt = LocalDate.now();

        long sumDailyRecords = em.createNamedQuery(JpaConst.Q_DAILYREC_SUM_MONTH, Long.class)
                .setParameter("start" ,start)
                .setParameter("end", end)
                .getSingleResult();
        return sumDailyRecords;
    }







}
