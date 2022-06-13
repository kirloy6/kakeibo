<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.AttributeConst" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="action" value="${ForwardConst.ACT_AUTH.getValue()}" />
<c:set var="command" value="${ForwardConst.CMD_LOGIN.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${loginError}">
            <div id="flush_error">
                ログインIDかパスワードが間違っています。
            </div>
        </c:if>
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <div class="frame">
        <div class="box0">
        <h2>家計簿にログイン</h2>
        </div>
        <form method="POST" action="<c:url value='/?action=${action}&command=${command}' />">
        <div class="box1">
            <label for="${AttributeConst.USER_LOGIN_ID.getValue()}" id="1_text1">ログインID</label><br />
            <input type="text" id="text1" name="${AttributeConst.USER_LOGIN_ID.getValue()}" value="${login_id}" />
            <br /><br />
        </div>
         <div class="box2">
            <label for="${AttributeConst.USER_PASS.getValue()}" id="1_text2">パスワード</label><br />
            <input type="password" id="text2" name="${AttributeConst.USER_PASS.getValue()}" />
            <br /><br />
         </div>

            <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
            <button type="submit">ログイン</button>
        </form>
        </div>
    </c:param>
</c:import>
