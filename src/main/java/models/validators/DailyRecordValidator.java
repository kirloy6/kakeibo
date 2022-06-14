package models.validators;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import actions.views.DailyRecordView;
import constants.MessageConst;

//日報インスタンスに設定されている値のバリデーションを行うクラス

public class DailyRecordValidator {

    public static List<String> validate(DailyRecordView drv) {
        List<String> errors = new ArrayList<String>();

        String storeError = validateStore(drv.getStore());
        if (!storeError.equals("")) {
            errors.add(storeError);
        }

        //内容のチェック
        String priceError = validatePrice(drv.getPrice());
        if (!priceError.equals("")) {
            errors.add(priceError);
        }
        String timeError = validateTime(drv.getRecordDate());
        if(!timeError.equals("")) {
            errors.add(timeError);
        }

        return errors;
    }

    /**
     * タイトルに入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param title タイトル
     * @return エラーメッセージ
     */
    private static String validateStore(String store) {
        if (store == null || store.equals("")) {
            return MessageConst.E_NOSTORE.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }

    /**
     * 内容に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param content 内容
     * @return エラーメッセージ
     */
    private static String validatePrice(Integer price) {
        if (price < 0) {
            return MessageConst.E_NOPRICE.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }
    private static String validateTime(LocalDate recordDate) {
        if(recordDate == null || recordDate.equals("")) {
            return MessageConst.E_NOTIME.getMessage();
        }

          return "";
      }

}
