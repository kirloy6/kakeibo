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
<fmt:parseDate value="${record.recordDate}" pattern="yyyy-MM-dd" var="recordDay" type="date" />
<label for="${AttributeConst.REC_DATE.getValue()}">日付</label><br />
<input type="date" name="${AttributeConst.REC_DATE.getValue()}" value="<fmt:formatDate value='${recordDay}' pattern='yyyy-MM-dd' />" />
<br /><br />

<label for="name">氏名</label><br />
<c:out value="${sessionScope.login_user.name}" />
<br /><br />
<p>固定費</p>
<input list="titleList" id="Id" name="title" value="${record.title}" />
<datalist id="titleList">
<select name="title">
   <c:forEach var="fixedTitle" items="${fixedTitles}">
         <option value="${fixedTitle.title}">${fixedTitle.title}</option>
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
    <c:if test="${record.price == -2147483648}">
         value = ""
    </c:if>
    value="${record.price}"
/>

<br/><br/>

<input type="hidden" name="${AttributeConst.REC_ID.getValue()}" value="${record.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
<button type="submit">投稿</button>

