<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actRec" value="${ForwardConst.ACT_REC.getValue()}" />
<c:set var="actDailyRec" value="${ForwardConst.ACT_DAILYREC.getValue()}" />
<c:set var="actDeRec" value="${ForwardConst.ACT_DAILYREC.getValue()}" />
<c:set var="actTop" value="${ForwardConst.ACT_TOP.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />
<c:set var="commEdt" value="${ForwardConst.CMD_EDIT.getValue()}" />


<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>家計簿へようこそ</h2>

        <form method="POST" action="<c:url value='?action=${actTop}&command=${commIdx}' />">
        <input type="month" name="month" value="<fmt:formatDate value='${month}' pattern='yyyy-MM' />" />
        <button type="submit">表示</button>
        </form>

        <h3>【今月の固定費レコード　一覧】</h3>
        <table id="record_list">
            <tbody>
                <tr>
                    <th class="record_title">タイトル</th>
                    <th class="record_price">金額</th>
                    <th class="repcord_action">操作</th>
                </tr>
                <c:forEach var="monthRecord" items="${monthRecords}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="record_title"><c:out value="${monthRecord.title}" /></td>
                        <td class="record_price">${monthRecord.price}</td>
                        <td class="record_action"><a href="<c:url value='?action=${actRec}&command=${commEdt}&id=${monthRecord.id}' />">編集する</a></td>

                    </tr>

                </c:forEach>

            </tbody>
            <tfoot>
                <tr>
                    <td>合計</td>
                    <td class="total_price">${sumRecord}</td>
                </tr>
            </tfoot>
        </table>


 <h3>【今月のデイリーレコード　一覧】</h3>
        <table id="record_list">
            <tbody>
                <tr>
                    <th class="record_date">日付</th>
                    <th class="record_title">タイトル</th>
                    <th class="record_price">金額</th>
                    <th class="repcord_action">操作</th>
                </tr>
                <c:forEach var="monthDailyRecord" items="${monthDailyRecords}" varStatus="status">
                <fmt:parseDate value="${monthDailyRecord.recordDate}" pattern="yyyy-MM-dd" var="dailyRecordDay" type="date" />
                    <tr class="row${status.count % 2}">
                        <td class="record_date"><fmt:formatDate value='${dailyRecordDay}' pattern='yyyy-MM-dd' /></td>
                        <td class="record_store"><c:out value="${monthDailyRecord.store}" /></td>
                        <td class="record_price">${monthDailyRecord.price}</td>
                        <td class="record_action"><a href="<c:url value='?action=${actDailyRec}&command=${commEdt}&id=${monthDailyRecord.id}' />">編集する</a></td>

                    </tr>

                </c:forEach>

            </tbody>
            <tfoot>
                <tr>
                    <td></td>
                    <td>合計</td>
                    <td class="total_price">${sumDailyRecord}</td>
                </tr>
            </tfoot>
        </table>
         <table>
            <tr>
            <td>年間トータル</td>
            <td>${sumDailyYearRecord}</td>
             <td>${monthRatio}%</td>
            </tr>
        </table>
         <table>
            <tr>
            <td>一人分 (固定費＋デイリーレコード合計)/2)</td>
            <td>${totalOne}</td>
            </tr>
        </table>
        <h3>【今月の立替レコード　一覧】</h3>
        <table id="record_list">
            <tbody>
                <tr>
                    <th class="record_date">日付</th>
                    <th class="record_title">タイトル</th>
                    <th class="record_price">金額</th>
                    <th class="repcord_action">操作</th>
                </tr>
                <c:forEach var="monthDemandRecord" items="${monthDemandRecords}" varStatus="status">
                <fmt:parseDate value="${monthDemandRecord.recordDate}" pattern="yyyy-MM-dd" var="demandRecordDay" type="date" />
                    <tr class="row${status.count % 2}">
                        <td class="record_date"><fmt:formatDate value='${demandRecordDay}' pattern='yyyy-MM-dd' /></td>
                        <td class="record_store"><c:out value="${monthDemandRecord.store}" /></td>
                        <td class="record_price">${monthDemandRecord.price}</td>
                        <td class="record_action"><a href="<c:url value='?action=${actDeRec}&command=${commEdt}&id=${monthDemandRecord.id}' />">編集する</a></td>

                    </tr>

                </c:forEach>

            </tbody>
            <tfoot>
                <tr>
                    <td></td>
                    <td>合計</td>
                    <td class="total_price">${sumDemandRecord}</td>
                </tr>
            </tfoot>
        </table>

        <table>
            <tr>
            <td>一人分 ((固定費＋デイリーレコード合計)/2)+立替レコード</td>
            <td>${total}</td>
            </tr>
        </table>





         <p><a href="<c:url value='?action=${actRec}&command=${commNew}' />">新規固定費レコードの登録</a></p>
        <p><a href="<c:url value='?action=${actDailyRec}&command=${commNew}' />">デイリーレコードの登録</a></p>
        <p><a href="<c:url value='?action=${actDeRec}&command=${commNew}' />">立替レコードの登録</a></p>

    </c:param>
</c:import>