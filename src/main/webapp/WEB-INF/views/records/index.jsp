<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actRec" value="${ForwardConst.ACT_REC.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>レコード　一覧</h2>
        <table id="record_list">
            <tbody>
                <tr>
                    <th class="record_name">氏名</th>
                    <th class="record_date">日付</th>
                    <th class="record_price">金額</th>
                    <th class="record_action">操作</th>
                </tr>
                <c:forEach var="record" items="${records}" varStatus="status">
                    <fmt:parseDate value="${record.recordDate}" pattern="yyyy-MM-dd" var="recordDay" type="date" />

                    <tr class="row${status.count % 2}">
                        <td class="record_name"><c:out value="${record.user.name}" /></td>
                        <td class="record_date"><fmt:formatDate value='${recordDay}' pattern='yyyy-MM-dd' /></td>
                        <td class="record_price">${record.price}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <p><a href="<c:url value='?action=${actRec}&command=${commNew}' />">新規レコードの登録</a></p>

    </c:param>
</c:import>
