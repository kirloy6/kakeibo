package actions;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;

import constants.AttributeConst;
import constants.ForwardConst;
import models.Record;
import services.RecordService;

/**
 * トップページに関する処理を行うActionクラス
 *
 */
public class TopAction extends ActionBase {

    private RecordService service;

    /**
     * indexメソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        service = new RecordService();

        //メソッドを実行
        invoke();

        service.close();

    }

    /**
     * 一覧画面を表示する
     */
    public void index() throws ServletException, IOException {

        String time =request.getParameter("month");
        if(time == null) {
            LocalDate date =LocalDate.now();
            DateTimeFormatter   formatter = DateTimeFormatter.ofPattern("yyyy-MM");
            time= formatter.format(date);
        }



        String start =time + "-01";
        String end = time +"-30";

        LocalDate endDay = LocalDate.parse(end).withDayOfMonth(1).plusMonths(1).minusDays(1);


        List<Record> monthRecords = service.getMonth(toLocalDate(start),endDay);
        long sumRecord= service.sumMonth(toLocalDate(start),endDay);



        putRequestScope(AttributeConst.SUMRECORD, sumRecord);
        putRequestScope(AttributeConst.MONTHRECORDS, monthRecords);



        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        //一覧画面を表示
        forward(ForwardConst.FW_TOP_INDEX);
    }
}