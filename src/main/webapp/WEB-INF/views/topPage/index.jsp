<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actRec" value="${ForwardConst.ACT_REC.getValue()}" />
<c:set var="actDailyRec" value="${ForwardConst.ACT_DAILYREC.getValue()}" />
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
                <c:forEach var="nowRecord" items="${nowRecords}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="record_title"><c:out value="${nowRecord.title}" /></td>
                        <td class="record_price">${nowRecord.price}</td>
                        <td class="record_action"><a href="<c:url value='?action=${actRec}&command=${commEdt}&id=${nowRecord.id}' />">編集する</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

         <table id="record_list">
            <tbody>
                <tr>
                    <th class="record_title">タイトル</th>
                    <th class="record_price">金額</th>
                    <th class="record_action">操作</th>
                </tr>



                <c:forEach var="record" items="${records}" varStatus="status">
                    <fmt:parseDate value="${record.recordDate}" pattern="yyyy-MM-dd" var="recordDay" type="date" />
                    <tr class="row${status.count % 2}">


                        <td class="record_title">${record.title}</td>
                        <td class="record_price">${record.price}</td>
                         <td class="record_action"><a href="<c:url value='?action=${actRec}&command=${commEdt}&id=${record.id}' />">編集する</a></td>
                        </tr>
                        </c:forEach>



            </tbody>
        </table>



         <p><a href="<c:url value='?action=${actRec}&command=${commNew}' />">新規固定費レコードの登録</a></p>
        <p><a href="<c:url value='?action=${actDailyRec}&command=${commNew}' />">デイリーレコードの登録</a></p>

    </c:param>
</c:import>