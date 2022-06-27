package services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import actions.views.RecordConverter;
import actions.views.RecordView;
import actions.views.UserConverter;
import actions.views.UserView;
import constants.JpaConst;
import models.FixedTitle;
import models.Record;
import models.validators.RecordValidator;

public class RecordService extends ServiceBase {

    public List<Record> getMinePerPage(UserView user, int page) {

        List<Record> records = em.createNamedQuery(JpaConst.Q_REC_GET_ALL_MINE, Record.class)
                .setParameter(JpaConst.JPQL_PARM_USER, UserConverter.toModel(user))
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return records;
    }

    public List<Record> getAllPerPage(int page) {

        List<Record> records = em.createNamedQuery(JpaConst.Q_REC_GET_ALL, Record.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return records;
    }

    /**
     * idを条件に取得したデータをReportViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public RecordView findOne(int id) {
        return RecordConverter.toView(findOneInternal(id));
    }

    /**
     * 画面から入力された日報の登録内容を元にデータを1件作成し、日報テーブルに登録する
     * @param rv 日報の登録内容
     * @return バリデーションで発生したエラーのリスト
     */
    public List<String> create(RecordView rv) {
        List<String> errors = RecordValidator.validate(rv);
        if (errors.size() == 0) {
            LocalDateTime ldt = LocalDateTime.now();
            rv.setCreatedAt(ldt);
            rv.setUpdatedAt(ldt);
            createInternal(rv);
        }

        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    /**
     * 画面から入力された日報の登録内容を元に、日報データを更新する
     * @param rv 日報の更新内容
     * @return バリデーションで発生したエラーのリスト
     */
    public List<String> update(RecordView rv) {

        //バリデーションを行う
        List<String> errors = RecordValidator.validate(rv);

        if (errors.size() == 0) {

            //更新日時を現在時刻に設定
            LocalDateTime ldt = LocalDateTime.now();
            rv.setUpdatedAt(ldt);

            updateInternal(rv);
        }

        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    /**
     * idを条件にデータを1件取得する
     * @param id
     * @return 取得データのインスタンス
     */
    private Record findOneInternal(int id) {
        return em.find(Record.class, id);
    }

    /**
     * 日報データを1件登録する ReportView→Report→DB
     * @param rv 日報データ
     */
    private void createInternal(RecordView rv) {

        em.getTransaction().begin();
        em.persist(RecordConverter.toModel(rv));
        em.getTransaction().commit();

    }

    /**
     * 日報データを更新する
     * @param rv 日報データ
     */
    private void updateInternal(RecordView rv) {

        em.getTransaction().begin();
        Record r = findOneInternal(rv.getId());
        RecordConverter.copyViewToModel(r, rv);
        em.getTransaction().commit();

    }

    public List<FixedTitle> getAllPage(int page) {

        List<FixedTitle> fixedTitles = em.createNamedQuery(JpaConst.Q_FIX_GET_ALL, FixedTitle.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return fixedTitles;
    }

    public void destroy(int id) {

        em.getTransaction().begin();
        Record r = findOneInternal(id);
        em.remove(r);
        em.getTransaction().commit();
        em.close();
    }


    public List<Record> getMonthData(LocalDate start,LocalDate end) {

        LocalDate ldt = LocalDate.now();

        List<Record> monthRecords = em.createNamedQuery(JpaConst.Q_REC_GET_MONTH, Record.class)
                .setParameter("start" ,start)
                .setParameter("end", end)
                .getResultList();
        return monthRecords;
    }

    public long sumMonth(LocalDate start,LocalDate end) {

        LocalDate ldt = LocalDate.now();

        long sumRecords = em.createNamedQuery(JpaConst.Q_REC_SUM_MONTH, Long.class)
                .setParameter("start" ,start)
                .setParameter("end", end)
                .getSingleResult();
        return sumRecords;
    }





}
