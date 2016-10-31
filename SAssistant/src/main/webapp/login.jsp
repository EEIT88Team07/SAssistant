<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> 
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登入</title>
<style type="text/css">
#main {
	position:relative;
	top: 50px;
    width:100%;
    text-align:center;
}
#content {
  width: 500px ;
  margin-left: auto ;
  margin-right: auto ;
}
</style>
<script type="text/javascript">
//由<body>的onLoad事件處理函數觸發此函數
function setFocusToUserId(){   
	 document.forms[0].userId.focus();   // 將游標放在userId欄位內
}
</script>

<!-- jQuery ui -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/jquery-ui.min.css" />
<script
	src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
<script>
	$(function() {
		$("#dialog").dialog();
	});
</script>
</head>
<body onLoad="setFocusToUserId()" style="background:#EBFFEB;">
<!-- 下列敘述設定變數funcName的值為LOG，top.jsp 會用到此變數 -->
<c:set var="funcName" value="LOG" scope="session"/>
<c:set var="msg" value="登入" />
<c:if test="${ ! empty sessionScope.timeOut }" > <!-- 表示使用逾時，重新登入 -->
   <c:set var="msg" value="<font color='red'>${sessionScope.timeOut}</font>" />
</c:if>
<Form action="<c:url value='/login.controller' />" method="POST" name="loginForm">
    <Table width='600px' style="border-width:2; background:#E0E0E0; 
                        border-style:inset; border-color:#EF02A4;" align="center">
         <TR>
             <TH width="50%">&nbsp;</TH>
             <TH width="50%">&nbsp;</TH>
         </TR>
         <TR>
             <TD height='50' colspan='2' align="CENTER" style="font-size:0.5cm;font-weight: 350;"> 
                <Font color="#006600"  face="標楷體" size="48px">
                    ${msg}
                </Font>
             </TD>
         </TR>
         <TR height='10'>
             <TD align="CENTER" colspan='2'>&nbsp;</TD>
         </TR>
         <TR>
             <TD width="200" align="right">帳號：　</TD>
             <TD width="200" colspan='2' align="LEFT">
             <input  type="text" name="account" size="10" value="${sessionScope.user}">          
             &nbsp;<small><Font color='red' size="-3">${ErrorMsgKey.AccountEmptyError}</Font></small></TD>
         </TR>
         <TR>
             <TD width="200" align="right">密碼：　</TD>
             <TD width="200" colspan='2' align="LEFT" >
             <input  type="password" name="password"  size="10" 
             value="${sessionScope.password}">
              
             &nbsp;<small><Font color='red'  size="-3">${ErrorMsgKey.PasswordEmptyError}</Font></small></TD>
             
         </TR>  
         <tr>
         <TD width="200" align="right" >
             <input type="checkbox" name="rememberMe" 
               <c:if test='${sessionScope.rememberMe==true}'>
                             
                  checked='checked'
               </c:if> 
             value="true">
         </TD>
         <TD width="200"  colspan='2' align="left"><small>記住密碼</small></TD>
             
         </tr>
         <TR height='10'>
             <TD align="CENTER" colspan='2'>&nbsp;<Font color='red' size="-1">${ErrorMsgKey.LoginError}&nbsp;</Font></TD>
         </TR>
        <TR>
            <TD colspan="2" align="center"><input type="submit" value="提交"> </TD>
         </TR>
         <TR height='10'>
             <TD align="CENTER" colspan='2'>&nbsp;</TD>
         </TR>
    </Table>
</Form>
</body>
</html>