<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.AttributeConst" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actUser" value="${ForwardConst.ACT_USER.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />

<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>ユーザー　一覧</h2>
        <table id="user_list">
            <tbody>
                <tr>
                    <th>ログインID</th>
                    <th>氏名</th>
                </tr>
                <c:forEach var="user" items="${users}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td><c:out value="${user.login_id}" /></td>
                        <td><c:out value="${user.name}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>


        <p><a href="<c:url value='?action=${actUser}&command=${commNew}' />">新規ユーザーの登録</a></p>

    </c:param>
</c:import>
