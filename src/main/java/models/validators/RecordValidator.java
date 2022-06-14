package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.RecordView;
import constants.MessageConst;

//日報インスタンスに設定されている値のバリデーションを行うクラス

public class RecordValidator {

        public static List<String> validate(RecordView rv) {
            List<String> errors = new ArrayList<String>();

            String titleError = validateStore(rv.getTitle());
            if (!titleError.equals("")) {
                errors.add(titleError);
            }

            //内容のチェック
            String priceError = validatePrice(rv.getPrice());
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
        private static String validateStore(String title) {
            if (title == null || title.equals("")) {
                return MessageConst.E_NOTITLE.getMessage();
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
            if ( price <0) {
                return MessageConst.E_NOPRICE.getMessage();
            }

            //入力値がある場合は空文字を返却
            return "";
        }
    }
