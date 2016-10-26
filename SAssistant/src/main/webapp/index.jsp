<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>SAssistant</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/skel-noscript.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style-desktop.css" />

<link
	href='http://fonts.googleapis.com/css?family=Roboto+Condensed:700italic,400,300,700'
	rel='stylesheet' type='text/css'>
<!--[if lte IE 8]><script src="js/html5shiv.js"></script><![endif]-->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>

<script id="dsq-count-scr" src="//markchen-2.disqus.com/count.js" async></script>
<!-- bookstapcss包	 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.css" />


<script src="${pageContext.request.contextPath}/js/skel.min.js"></script>
<script src="${pageContext.request.contextPath}/js/skel-panels.min.js"></script>
<script src="${pageContext.request.contextPath}/js/init.js"></script>
<script src="${pageContext.request.contextPath}/js/functions.js"></script>
<script src="${pageContext.request.contextPath}/js/selectstock.js"></script>

</head>
<body class="homepage">

	<!-- Header -->
	<div id="header">
<!-- 	登入系統 -->
		<div style="float: right; margin: 30px">
			<a href="#" style="font-size: 24px">註冊</a>
			<c:if test="${ ! empty LoginOK }">
				<a href="<c:url value='/logout.jsp' />" style="font-size: 24px">
  					登出 
	        	</a>
			</c:if>
			<c:if test="${empty LoginOK }">
				<a href="<c:url value='/login.jsp' />" style="font-size: 24px">
				   登入 
				</a>
            </c:if>
		</div>
	
		<!-- 標題 -->
		<div class="container">

			<!-- Logo -->
			<div id="logo">
				<a href="${pageContext.request.contextPath}/index.jsp"><img alt="" src="${pageContext.request.contextPath}/images/logo.png"/></a>
			</div>
			<div id="fdw">
				<nav>
				<ul>
					<li><a
						href="${pageContext.request.contextPath}/pages/basic/basic.jsp">基礎概念</a></li>
					<li><a href="#">投資管理<span class="arrow"></span></a>
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
								href="${pageContext.request.contextPath}/pages/member/existrans.jsp">現有股票</a></li>
							<li><a
								href="${pageContext.request.contextPath}/pages/member/transhistory.jsp">交易記錄</a></li>
							<li><a
								href="${pageContext.request.contextPath}/pages/member/focus.jsp">我的關注股</a></li>
							<li><a
								href="${pageContext.request.contextPath}/pages/member/accountmanage.jsp">帳號管理</a></li>
						</ul></li>
					<li><a href="#">contact</a></li>
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
		<!-- /圖片 -->
		
		<!-- Main -->
		<div id="page">
			<h1 style="font-size: 72px" align="center">
			<c:if test="${empty LoginOK }">
				${logoutMessage}
            </c:if>
            </h1>
			<!-- Extra -->
			<div id="marketing" class="container">
				
			</div>
			<!-- /Extra -->

			<!-- Main -->
			<div id="main" class="container">
				
			</div>
			<!-- Main -->

		</div>
		<!-- /Main -->

		


		<!-- Copyright -->
		<div id="copyright" class="container">
			Design: <a href="http://templated.co">TEMPLATED</a> Images: <a
				href="http://unsplash.com">Unsplash</a> (<a
				href="http://unsplash.com/cc0">CC0</a>)
		</div>
	</div>
	<div id="disqus_thread"></div>

	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script
		src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
	<script>
		(function() { // DON'T EDIT BELOW THIS LINE
			var d = document, s = d.createElement('script');
			s.src = '//markchen-2.disqus.com/embed.js';
			s.setAttribute('data-timestamp', +new Date());
			(d.head || d.body).appendChild(s);
		})();
	</script>
</body>
</html>