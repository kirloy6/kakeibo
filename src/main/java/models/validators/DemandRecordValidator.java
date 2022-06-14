package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.DemandRecordView;
import constants.MessageConst;


public class DemandRecordValidator {

        public static List<String> validate(DemandRecordView derv) {
            List<String> errors = new ArrayList<String>();

            String storeError = validateStore(derv.getStore());
            if (!storeError.equals("")) {
                errors.add(storeError);
            }

            String priceError = validatePrice(derv.getPrice());
            if (!priceError.equals("")) {
                errors.add(priceError);
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
            if ( price < 0) {
                return MessageConst.E_NOPRICE.getMessage();
            }

            //入力値がある場合は空文字を返却
            return "";
        }
    }
