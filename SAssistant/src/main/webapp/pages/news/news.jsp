<%@page import="org.jsoup.nodes.Element"%>
<%@page import="org.jsoup.nodes.Document"%>
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

<script>
	document.getElement
</script>
<style type="text/css">
		table {
			border:3px solid #01B468;
			border-collapse:collapse;
			margin-left: 120px;
		}
		td {
			border:1px solid #9F5000;
			padding: 10px;
		}
		thead {
			background-color:#019858;
			color:#ffffff;
			border-bottom:3px double #9F5000;
		}
		tbody tr:nth-child(2n) {background-color:#FFF8D7}
		tbody tr:nth-child(2n+1) {background-color:#FFE4CA}
		tbody tr:hover {background-color:#ffffff;}
</style>
</head>
<body class="homepage">

	<!-- Header -->
	<div id="header">
		<div style="float: right; margin: 30px">
			<c:if test="${empty LoginOK }">
				<a href="#" style="font-size: 24px">註冊</a>
			</c:if>
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
								href="${pageContext.request.contextPath}/pages/investment/stockinquiries.jsp">個股查詢</a></li>
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
		<div id="show" style="width: 95%; margin: 20px;" >
			<table style="border:3px solid #01B468; border-collapse: collapse;">
				<tr align="center"><td style="font-size: 20px;">yahoo台股盤勢</td><td style="font-size: 20px;">yahoo盤勢分析</td><td style="font-size: 20px;">yahoo個股動態</td></tr>
				<tr>
					<td>
						<%@ page import="org.jsoup.*" %>
						<%	org.jsoup.select.Elements N2 = Jsoup.connect("https://tw.stock.yahoo.com/rss/url/d/e/N2.html").userAgent("Mozilla").get().select("item");
							for(int i = 0; i < N2.size(); i++){
							String t = N2.select("title").get(i).text();
							String l = N2.select("link").get(i).text();
							out.print("<a href="+l+">"+t+"</a>"+"<br>"+"<br>");
						}
						%>
					</td>
					<td>
						<%@ page import="org.jsoup.*" %>
						<% org.jsoup.select.Elements R1 = Jsoup.connect("https://tw.stock.yahoo.com/rss/url/d/e/R1.html").userAgent("Mozilla").get().select("item"); 
							for(int i = 0; i < R1.size(); i++){
							String t = R1.select("title").get(i).text();
							String l = R1.select("link").get(i).text();
							out.print("<a href="+l+">"+t+"</a>"+"<br>"+"<br>");
						}
						%>
					</td>
					<td>
						<%@ page import="org.jsoup.*" %>
						<% org.jsoup.select.Elements N3 = Jsoup.connect("https://tw.stock.yahoo.com/rss/url/d/e/N3.html").userAgent("Mozilla").get().select("item"); 
							for(int i = 0; i < N3.size(); i++){
							String t = N3.select("title").get(i).text();
							String l = N3.select("link").get(i).text();
							out.print("<a href="+l+">"+t+"</a>"+"<br>"+"<br>");
						}
						%>
					</td>
				</tr>
			</table>
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