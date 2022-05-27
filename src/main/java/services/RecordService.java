package services;

import java.util.List;

import constants.JpaConst;
import models.Record;
import models.User;

public class RecordService extends ServiceBase {

    public List<Record> getMinePerPage(User user, int page) {

        List<Record> records = em.createNamedQuery(JpaConst.Q_REC_GET_ALL_MINE, Record.class)
                .setParameter(JpaConst.JPQL_PARM_USER, user)
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


}
