<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.AttributeConst" %>

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h2>レコード 分割ページ</h2>
        <form method="POST" action="<c:url value='?action=${actDailyRec}&command=${commUpd}' />">



<fmt:parseDate value="${dailyRecord.recordDate}" pattern="yyyy-MM-dd" var="dailyRecordDay" type="date" />
<label for="${AttributeConst.DAILYREC_DATE.getValue()}">日付</label><br />
<input type="date" name="${AttributeConst.DAILYREC_DATE.getValue()}" value="<fmt:formatDate value='${dailyRecordDay}' pattern='yyyy-MM-dd' />" />
<br /><br />

<label for="name">氏名</label><br />
<c:out value="${sessionScope.login_user.name}" />
<br /><br />
<p>id:${dailyRecord.id}の分割登録</p>
<input type="text" name="store" value="${dailyRecord.store}" ><br/>
<c:out value="${dailyRecord.price}" /><br/>

<input type="number" name="price"  /><br/>


<input type="number" name="price"  /><br/>
<input type="number" name="price"  /><br/>



<br/><br/>

<input type="hidden" name="${AttributeConst.DAILYREC_ID.getValue()}" value="${dailyRecord.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
<button type="submit">投稿</button>


</form>
</c:param>
</c:import>

