package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.DemandRecord;

/**
 * 従業員データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class DemandRecordConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param ev EmployeeViewのインスタンス
     * @return Employeeのインスタンス
     */
    public static DemandRecord toModel(DemandRecordView derv) {

        return new DemandRecord(
                derv.getId(),
                UserConverter.toModel(derv.getUser()),
                derv.getRecordDate(),
                derv.getStore(),
                derv.getPrice(),
                derv.getCreatedAt(),
                derv.getUpdatedAt());
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param e Employeeのインスタンス
     * @return EmployeeViewのインスタンス
     */
    public static DemandRecordView toView(DemandRecord der) {

        if(der == null) {
            return null;
        }

        return new DemandRecordView(
                der.getId(),
                UserConverter.toView(der.getUser()),
                der.getRecordDate(),
                der.getStore(),
                der.getPrice(),
                der.getCreatedAt(),
                der.getUpdatedAt());
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<DemandRecordView> toViewList(List<DemandRecord> list) {
        List<DemandRecordView> dervs = new ArrayList<>();

        for (DemandRecord der : list) {
            dervs.add(toView(der));
        }

        return dervs;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param e DTOモデル(コピー先)
     * @param ev Viewモデル(コピー元)
     */
    public static void copyViewToModel(DemandRecord der, DemandRecordView derv) {
        der.setId(derv.getId());
        der.setUser(UserConverter.toModel(derv.getUser()));
        der.setRecordDate(derv.getRecordDate());
        der.setStore(derv.getStore());
        der.setPrice(derv.getPrice());
        der.setCreatedAt(derv.getCreatedAt());
        der.setUpdatedAt(derv.getUpdatedAt());



    }
}
