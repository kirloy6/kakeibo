<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.AttributeConst" %>

<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<fmt:parseDate value="${dailyRecord.recordDate}" pattern="yyyy-MM-dd" var="dailyRecordDay" type="date" />
<label for="${AttributeConst.DAILYREC_DATE.getValue()}">日付</label><br />
<input type="date" name="${AttributeConst.DAILYREC_DATE.getValue()}" value="<fmt:formatDate value='${dailyRecordDay}' pattern='yyyy-MM-dd' />" />
<br /><br />

<label for="name">氏名</label><br />
<c:out value="${sessionScope.login_user.name}" />
<br /><br />
<p>デイリーレコード</p>
<input list="storeList" id="Id" name="store" />
<datalist id="storeList">
<select name="store">
   <c:forEach var="store" items="${stores}">
         <option value="${store.store}">${store.store}</option>
    </c:forEach>
 </select>
 </datalist>
 <script>
'use strcit';
document.getElementById('Id').addEventListener('input', function handleInput (event) {
  console.log(event.target.value);
}, false);
</script>



<input type="number" name="price"  min="0"
    <c:if test="${dailyRecord.price == -2147483648}" >
         <c:out value="" />
    </c:if>
/>
<br/><br/>

<input type="hidden" name="${AttributeConst.DAILYREC_ID.getValue()}" value="${dailyRecord.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
<button type="submit">投稿</button>

