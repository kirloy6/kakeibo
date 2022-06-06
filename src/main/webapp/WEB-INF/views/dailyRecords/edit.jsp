<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst" %>

<c:set var="actDailyRec" value="${ForwardConst.ACT_DAILYREC.getValue()}" />
<c:set var="commUpd" value="${ForwardConst.CMD_UPDATE.getValue()}" />
<c:set var="commDest" value="${ForwardConst.CMD_DESTROY.getValue()}" />
<c:set var="commSplit" value="${ForwardConst.CMD_SPLIT.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h2>レコード 編集ページ</h2>
        <form method="POST" action="<c:url value='?action=${actDailyRec}&command=${commUpd}' />">
            <c:import url="_form.jsp" />
        </form>



        <form method="POST"
            action="<c:url value='?action=${actDailyRec}&command=${commDest}' />">
            <input type="hidden" name="${AttributeConst.DAILYREC_ID.getValue()}" value="${dailyRecord.id}" />
            <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
            <button type="submit" >削除</button>
        </form>

        <p>
            <a href="<c:url value='?action=${actDailyRec}&command=${commSplit}&id=${dailyRecord.id}' />">分割</a>
        </p>

        <p>
            <a href="<c:url value='?action=Record&command=index' />">一覧に戻る</a>
        </p>
    </c:param>
</c:import>