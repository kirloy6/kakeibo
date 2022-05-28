<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actRec" value="${ForwardConst.ACT_REC.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />
<c:set var="commEdt" value="${ForwardConst.CMD_EDIT.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>固定費レコード　一覧</h2>
        <table id="record_list">
            <tbody>
                <tr>
                    <th class="report_title">タイトル</th>
                    <th class="record_price">金額</th>
                    <th class="record_action">操作</th>
                </tr>



                <c:forEach var="record" items="${records}" varStatus="status">
                    <fmt:parseDate value="${record.recordDate}" pattern="yyyy-MM-dd" var="recordDay" type="date" />
                    <tr class="row${status.count % 2}">


                        <td class="record_title">${record.title}</td>
                        <td class="record_price">${record.price}</td>
                         <td class="report_action"><a href="<c:url value='?action=${actRec}&command=${commEdt}&id=${record.id}' />">編集する</a></td>
                        </tr>
                        </c:forEach>


            </tbody>
        </table>

        <p><a href="<c:url value='?action=${actRec}&command=${commNew}' />">新規レコードの登録</a></p>

    </c:param>
</c:import>
