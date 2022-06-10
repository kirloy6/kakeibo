package actions;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;

import constants.AttributeConst;
import constants.ForwardConst;
import models.DailyRecord;
import models.DemandRecord;
import models.Record;
import services.DailyRecordService;
import services.DemandRecordService;
import services.RecordService;

/**
 * トップページに関する処理を行うActionクラス
 *
 */
public class TopAction extends ActionBase {

    private RecordService service;
    private DailyRecordService dservice;
    private DemandRecordService deservice;

    /**
     * indexメソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        service = new RecordService();
        dservice = new DailyRecordService();
        deservice = new DemandRecordService();

        //メソッドを実行
        invoke();

        service.close();
        dservice.close();
        deservice.close();

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
        String timeYear;
        LocalDate dateYear =LocalDate.now();
            DateTimeFormatter   formatterYear = DateTimeFormatter.ofPattern("yyyy");
            timeYear= formatterYear.format(dateYear);


        String start =time + "-01";
        String end = time +"-30";

        String startYear =timeYear + "-01-01";
        String endYear = timeYear +"-12-31";

        LocalDate endDay = LocalDate.parse(end).withDayOfMonth(1).plusMonths(1).minusDays(1);


        List<Record> monthRecords = service.getMonthData(toLocalDate(start),endDay);
        long sumRecord= service.sumMonth(toLocalDate(start),endDay);


        List<DailyRecord> monthDailyRecords = dservice.getDailyMonth(toLocalDate(start),endDay);
        long sumDailyRecord= dservice.sumDailyMonth(toLocalDate(start),endDay);

        long sumDailyYearRecord= dservice.sumDailyYear(toLocalDate(startYear),toLocalDate(endYear));

        List<DemandRecord> monthDemandRecords = deservice.getDailyMonth(toLocalDate(start),endDay);
        long sumDemandRecord= deservice.sumDemandMonth(toLocalDate(start),endDay);

        long totalOne =(sumRecord+sumDailyRecord)/2;
        long total =((sumRecord+sumDailyRecord)/2)+sumDemandRecord;

        double monthRatio =(double)sumDailyRecord/sumDailyYearRecord*100;
        monthRatio = Math.floor(monthRatio);
        System.out.println(monthRatio);






        putRequestScope(AttributeConst.SUMRECORD, sumRecord);
        putRequestScope(AttributeConst.MONTHRECORDS, monthRecords);
        putRequestScope(AttributeConst.SUMDAILYRECORD, sumDailyRecord);
        putRequestScope(AttributeConst.MONTHDAILYRECORDS, monthDailyRecords);
        putRequestScope(AttributeConst.SUMDEMANDRECORD, sumDemandRecord);
        putRequestScope(AttributeConst.MONTHDEMANDRECORDS, monthDemandRecords);
        putRequestScope(AttributeConst.SUMDAILYYEARRECORD, sumDailyYearRecord);
        request.setAttribute("totalOne", totalOne);
        request.setAttribute("total", total);
        request.setAttribute("monthRatio", monthRatio);



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