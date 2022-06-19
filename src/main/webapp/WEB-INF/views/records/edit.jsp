<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst" %>

<c:set var="actRec" value="${ForwardConst.ACT_REC.getValue()}" />
<c:set var="commUpd" value="${ForwardConst.CMD_UPDATE.getValue()}" />
<c:set var="commDest" value="${ForwardConst.CMD_DESTROY.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h2>レコード 編集ページ</h2>
        <form style="display:inline;"  method="POST" action="<c:url value='?action=${actRec}&command=${commUpd}' />">
            <c:import url="_form.jsp" />
        </form>
         &nbsp;&nbsp;&nbsp;


        <form style="display:inline;"  method="POST" onclick="confirmDestroy()"
            action="<c:url value='?action=${actRec}&command=${commDest}' />">
            <input type="hidden" name="${AttributeConst.REC_ID.getValue()}" value="${record.id}" />
            <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
            <button  type="submit" >削除</button>
        </form>
        <script>
                    function confirmDestroy() {
                        if(confirm("本当に削除してよろしいですか？")) {
                            document.forms[1].submit();
                        }
                    }
                </script>


        <p>
            <a href="<c:url value='?action=Record&command=index' />">一覧に戻る</a>
        </p>
    </c:param>
</c:import>