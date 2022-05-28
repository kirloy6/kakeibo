package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Record;

/**
 * 従業員データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class RecordConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param ev EmployeeViewのインスタンス
     * @return Employeeのインスタンス
     */
    public static Record toModel(RecordView rv) {

        return new Record(
                rv.getId(),
                UserConverter.toModel(rv.getUser()),
                rv.getRecordDate(),
                rv.getTitle(),
                rv.getPrice(),
                rv.getCreatedAt(),
                rv.getUpdatedAt());
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param e Employeeのインスタンス
     * @return EmployeeViewのインスタンス
     */
    public static RecordView toView(Record r) {

        if(r == null) {
            return null;
        }

        return new RecordView(
                r.getId(),
                UserConverter.toView(r.getUser()),
                r.getRecordDate(),
                r.getTitle(),
                r.getPrice(),
                r.getCreatedAt(),
                r.getUpdatedAt());
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<RecordView> toViewList(List<Record> list) {
        List<RecordView> rvs = new ArrayList<>();

        for (Record r : list) {
            rvs.add(toView(r));
        }

        return rvs;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param e DTOモデル(コピー先)
     * @param ev Viewモデル(コピー元)
     */
    public static void copyViewToModel(Record r, RecordView rv) {
        r.setId(rv.getId());
        r.setUser(UserConverter.toModel(rv.getUser()));
        r.setRecordDate(rv.getRecordDate());
        r.setTitle(rv.getTitle());
        r.setPrice(rv.getPrice());
        r.setCreatedAt(rv.getCreatedAt());
        r.setUpdatedAt(rv.getUpdatedAt());



    }
}
