<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst" %>


<c:set var="actTop" value="${ForwardConst.ACT_TOP.getValue()}" />
<c:set var="actUser" value="${ForwardConst.ACT_USER.getValue()}" />
<c:set var="actAuth" value="${ForwardConst.ACT_AUTH.getValue()}" />
<c:set var="actRec" value="${ForwardConst.ACT_REC.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commOut" value="${ForwardConst.CMD_LOGOUT.getValue()}" />


<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
    <title><c:out value="家計簿" /></title>
    <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
    <link rel="stylesheet" href="<c:url value='/css/style.css' />">
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <div id="header_menu">
                <h1><a href="<c:url value='/?action=${actTop}&command=${commIdx}' />">家計簿</a></h1>&nbsp;&nbsp;&nbsp;
            </div>
            <div>
                <a href="<c:url value='?action=${actUser}&command=${commIdx}' />">ユーザー管理</a>&nbsp;
                <a href="<c:url value='?action=${actRec}&command=${commIdx}' />">レコード管理</a>&nbsp;
                <a href="<c:url value='?action=${actAuth}&command=${commOut}' />">ログアウト</a>
            </div>
        </div>
        <div id="content">${param.content}</div>
        <div id="footer">by Shuhei.</div>
    </div>
</body>
</html>
