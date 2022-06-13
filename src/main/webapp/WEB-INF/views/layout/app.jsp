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
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script>

        let total = $('#total').text();
        $('#price1').on('input', function(){
            let price1 = $('#price1').val();
            console.log(price1);
            $('#price2').val((total - price1));
        });

        $('#price2').on('input', function(){
            let price1 = $('#price1').val();
            let price2 = $('#price2').val();

            if(total - price1 - price2 >= 0){
                $('#price3').val((total - price1 - price2));
            }
        });

    </script>
       <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js"></script>

    <script>
  var ctx = document.getElementById("myDoughnutChart");
  var myDoughnutChart= new Chart(ctx, {
    type: 'doughnut',
    data: {
      labels: ["賛成", "反対", "わからない", "未回答"], //データ項目のラベル
      datasets: [{
          backgroundColor: [
              "#c97586",
              "#bbbcde",
              "#93b881",
              "#e6b422"
          ],
          data: [45, 32, 18, 5] //グラフのデータ
      }]
    },
    options: {
      title: {
        display: true,
        //グラフタイトル
        text: 'デイリーレコード'
      }
    }
  });
</script>




</body>
</html>
