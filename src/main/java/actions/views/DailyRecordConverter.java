package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.DailyRecord;

/**
 * 従業員データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class DailyRecordConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param ev EmployeeViewのインスタンス
     * @return Employeeのインスタンス
     */
    public static DailyRecord toModel(DailyRecordView drv) {

        return new DailyRecord(
                drv.getId(),
                UserConverter.toModel(drv.getUser()),
                drv.getRecordDate(),
                drv.getStore(),
                drv.getPrice(),
                drv.getCreatedAt(),
                drv.getUpdatedAt());
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param e Employeeのインスタンス
     * @return EmployeeViewのインスタンス
     */
    public static DailyRecordView toView(DailyRecord dr) {

        if(dr == null) {
            return null;
        }

        return new DailyRecordView(
                dr.getId(),
                UserConverter.toView(dr.getUser()),
                dr.getRecordDate(),
                dr.getStore(),
                dr.getPrice(),
                dr.getCreatedAt(),
                dr.getUpdatedAt());
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<DailyRecordView> toViewList(List<DailyRecord> list) {
        List<DailyRecordView> drvs = new ArrayList<>();

        for (DailyRecord dr : list) {
            drvs.add(toView(dr));
        }

        return drvs;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param e DTOモデル(コピー先)
     * @param ev Viewモデル(コピー元)
     */
    public static void copyViewToModel(DailyRecord dr, DailyRecordView drv) {
        dr.setId(drv.getId());
        dr.setUser(UserConverter.toModel(drv.getUser()));
        dr.setRecordDate(drv.getRecordDate());
        dr.setStore(drv.getStore());
        dr.setPrice(drv.getPrice());
        dr.setCreatedAt(drv.getCreatedAt());
        dr.setUpdatedAt(drv.getUpdatedAt());



    }
}
