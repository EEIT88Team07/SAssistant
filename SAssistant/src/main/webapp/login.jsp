<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>SAssistant</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<style type="text/css">
</style>


<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>

<!-- basic -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/skel-noscript.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style-desktop.css" />
<link
	href='http://fonts.googleapis.com/css?family=Roboto+Condensed:700italic,400,300,700'
	rel='stylesheet' type='text/css'>

<script src="${pageContext.request.contextPath}/js/skel.min.js"></script>
<script src="${pageContext.request.contextPath}/js/skel-panels.min.js"></script>
<script src="${pageContext.request.contextPath}/js/init.js"></script>
<script src="${pageContext.request.contextPath}/js/functions.js"></script>
<script src="${pageContext.request.contextPath}/js/selectstock.js"></script>

<!-- basic -->


<!-- jQueryUI -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!-- jQueryUI -->


<!-- 留言板 -->
<script id="dsq-count-scr" src="//markchen-2.disqus.com/count.js" async></script>
<!-- 留言板 -->
<!-- bookstapcss包	 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/jquery-ui.min.css" />
<script
	src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
<!-- bookstapcss包	 -->


<!-- Datatable -->
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/datatables.css" />


<!-- Datatable -->


<link
	href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300'
	rel='stylesheet' type='text/css'>

</head>
<body style="background: #EBFFEB;">


	<div style="float: right; margin: 30px;">
		<a href="#" style="font-size: 24px">註冊</a>
		<c:if test="${ ! empty LoginOK }">
			<a href="<c:url value='/logout.jsp' />" style="font-size: 24px">
				登出 </a>
		</c:if>
		<c:if test="${empty LoginOK }">
			<a href="<c:url value='/login.jsp' />" style="font-size: 24px">
				登入 </a>
		</c:if>
	</div>
	<div id="header">
		<!-- 標題 -->
		<div style="margin: 0">
			<div
				style="display: inline-block; float: left; margin-left: 20px; margin-top: 20px">
				<a href="${pageContext.request.contextPath}/index.jsp"><img
					alt="" src="${pageContext.request.contextPath}/images/logo.png" /></a>
			</div>
			<div id="fdw" style="display: inline-block;">

				<nav>
				<ul>
					<li><a
						href="${pageContext.request.contextPath}/pages/basic/basic.jsp">基礎概念</a></li>
					<li><a href="#">股市資訊<span class="arrow"></span></a>
						<ul style="display: none;" class="sub_menu">
							<li><a
								href="${pageContext.request.contextPath}/pages/investment/stockinquiries.jsp">每日收盤</a></li>
							<li><a
								href="${pageContext.request.contextPath}/pages/investment/realtime.jsp">即時行情</a></li>
							<li><a
								href="${pageContext.request.contextPath}/pages/investment/company.jsp">個股查詢</a></li>
						</ul></li>
					<li><a
						href="${pageContext.request.contextPath}/pages/news/news.jsp">股市新聞</a>
					<li><a href="#">會員專區<span class="arrow"></span></a>
						<ul style="display: none;" class="sub_menu">
							<li><a
								href="${pageContext.request.contextPath}/pages/member/transhistory.jsp">購買記錄管理</a></li>
							<li><a
								href="${pageContext.request.contextPath}/pages/member/sellinghistory.jsp">賣出記錄管理</a></li>
							<li><a
								href="${pageContext.request.contextPath}/pages/member/focus.jsp">我的關注股</a></li>
						</ul></li>

				</ul>
				</nav>
			</div>
			<!-- Nav -->
		</div>

		<!-- 標題 -->

		<!-- 圖片 -->
		<div id="banner">
			<div class="container"></div>
		</div>


	</div>



	<!-- 下列敘述設定變數funcName的值為LOG，top.jsp 會用到此變數 -->
	<c:set var="funcName" value="LOG" scope="session" />
	<c:set var="msg" value="登入" />
	<c:if test="${ ! empty sessionScope.timeOut }">
		<!-- 表示使用逾時，重新登入 -->
		<c:set var="msg"
			value="<font color='red'>${sessionScope.timeOut}</font>" />
	</c:if>

	<div class="form-style-8">

		<h2>${msg}</h2>
		<Form action="<c:url value='/login.controller' />" method="POST"
			name="loginForm">

			<div style="margin: 20px;">
				<label>帳號:</label> <input type="text" name="account" size="10"
					value="${sessionScope.user}" /> <small><Font color='red'
					size="-3">${ErrorMsgKey.AccountEmptyError}</Font></small> <label>密碼:</label>
				<input type="password" name="password" size="10"
					value="${sessionScope.password}"> <small><Font
					color='red' size="-3">${ErrorMsgKey.PasswordEmptyError}</Font></small>
				<div>
					<label style="display: inline-block;">記住密碼:</label> <input
						style="display: inline-block;" type="checkbox" name="rememberMe"
						<c:if test='${sessionScope.rememberMe==true}'>
						checked='checked'
					</c:if>
						value="true">
				</div>
				<div style="margin: 20px">
					<input type="submit" value="提交"> <input type="button"
						value="取消" style="margin-left: 20px">

				</div>
			</div>
		</form>

	</div>








</body>
</html>