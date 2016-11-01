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
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

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
<script src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
<script src="${pageContext.request.contextPath}/js/candlestick.js"></script>

<!-- JQuery -->
<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
	$(document).ready(function() {

		var strUrl = location.search;
		var stockid = "";
		if (strUrl.indexOf("?") != -1) {
			var getSearch = strUrl.split("?");
			var getPara = getSearch[1].split("&");
			for (i = 0; i < getPara.length; i++) {
				var ParaVal = getPara[0].split("=");
				stockid = ParaVal[1];
			}
		}

		if (stockid.length == 0) {
			stockid = $('td[name="stockIdTable"]').text()
		}
		if (stockid.length != 0) {

			$.ajax({
				"method" : "GET",
				"contentType" : "application/x-www-form-urlencoded; charset=UTF-8",
				"dataType" : "json",
				"data" : "action=getCandPara&id=" + stockid,
				"url" : contextPath + "/dataAnalysis.view",
				"cache" : false,
				"success" : getCandlestick
			})
		}
	});
</script>

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
					登出 </a>
			</c:if>
			<c:if test="${empty LoginOK }">
				<a href="<c:url value='/login.jsp' />" style="font-size: 24px">
					登入 </a>
			</c:if>
		</div>

		<!-- 標題 -->
	<div style="margin: 0">

			<!-- Logo -->

			<div style="display: inline-block; float: left; margin-left: 20px; margin-top: 20px">
				<a href="${pageContext.request.contextPath}/index.jsp"><img
					alt="" src="${pageContext.request.contextPath}/images/logo.png" /></a>
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
		<!-- /圖片 -->
		<!-- Main -->
		<div style="display: block;">
			<div style="margin: 20px;">

				<div class="search1" align="center">
					<!-- 輸入表格 -->

					<form action="<c:url value="/stockCompany.controller" />"
						method="post" class="form-inline">
						<div class="form-group">
							<label class="control-label">股票產業：</label> <select
								name="selectstockcompany" style="width: 150px;"></select> <label
								class="control-label">股票代碼：</label> <select name="selectstockid"
								style="width: 150px;"></select>
						</div>
						<div class="submit1" style="display: inline;">
							<input class="btn btn-primary superbtn" type="submit"
								name="datanysis" value="Select">
						</div>
					</form>
				</div>
				<!-- 			顯示表格 -->
				<table id="output" style="margin: 30px;">
					<!-- 直接用foreach做循環表單 -->
					<!-- var bean物件 EL取得servlet的request 裡key value 的select -->
					<tbody>
						<c:if test="${not empty select}">
							<c:forEach var="bean" items="${select}">
								<table width="600" border="0" cellspacing="0" cellpadding="0"
									align="left" style="margin: 100px;">
									<tr align="center" bgcolor="#ACD6FF">
										<td height="26" width="600" align="center"
											style="font-weight: bold">公 司 基 本 資 料</td>
									</tr>
									<tr align="center" bgcolor="#00CACA" />
									<td width="600" />
									<table width="100%" border="0" cellspacing="0" cellpadding="0"
										align="left">
										<tr bgcolor="#FFFFFF">
											<td width="12%" bgcolor="#DDDDFF" height="25px"
												style="font-weight: bold">股票代碼</td>
											<td name="stockIdTable" width="30%" align="left"
												height="25px">${bean.stockId}</td>
											<td width="12%" bgcolor="#DDDDFF" height="25px"
												style="font-weight: bold">股本</td>
											<td width="20%" align="center" height="25px">${bean.capitalStock}</td>
										</tr>
										<tr bgcolor="#FFFFFF">
											<td width="100" height="25" bgcolor="#DDDDFF"
												style="font-weight: bold">成立時間</td>
											<td align="left">民國 ${bean.establishDate}</td>
											<td width="100" bgcolor="#DDDDFF" style="font-weight: bold">現金股利</td>
											<td align="center">${bean.cashDividend}</td>
										</tr>
										<tr bgcolor="#FFFFFF">
											<td width="100" height="25" bgcolor="#DDDDFF"
												style="font-weight: bold">上市(櫃)時間</td>
											<td align="left">民國 ${bean.publicCompanyDate}</td>
											<td width="100" bgcolor="#DDDDFF" style="font-weight: bold">股票股利</td>
											<td align="center">${bean.stockDividend}</td>
										</tr>
										<tr bgcolor="#FFFFFF">
											<td width="100" height="25" bgcolor="#DDDDFF"
												style="font-weight: bold">董 事 長</td>
											<td align="left">${bean.chairman}</td>
											<td width="100" bgcolor="#DDDDFF" style="font-weight: bold">盈餘配股</td>
											<td align="center">${bean.sdre}</td>
										</tr>
										<tr bgcolor="#FFFFFF">
											<td width="100" bgcolor="#DDDDFF" height="25px"
												style="font-weight: bold">總 經 理</td>
											<td align="left" height="25px">${bean.generalManager}</td>
											<td width="100" bgcolor="#DDDDFF" style="font-weight: bold">公積配股</td>
											<td align="center">${bean.sdcr}</td>
										</tr>
										<tr bgcolor="#FFFFFF">
											<td width="100" bgcolor="#DDDDFF" class="ttt"
												style="font-weight: bold">發 言 人</td>
											<td align="left">${bean.spokesman}</td>
											<td width="100" bgcolor="#DDDDFF" height="25px"
												style="font-weight: bold">股東會日期</td>
											<td align="center" height="25px">民國 ${bean.smDate}</td>
										</tr>
										<tr bgcolor="#FFFFFF">
											<td width="100" height="25" bgcolor="#DDDDFF"
												style="font-weight: bold">股務代理</td>
											<td align="left">${bean.serviceAgent}</td>
											<td width="100" bgcolor="#DDDDFF" style="font-weight: bold">公司電話</td>
											<td align="center">${bean.companyPhone}</td>
										</tr>
										<tr bgcolor="#FFFFFF">
											<td width="100" height="25" bgcolor="#DDDDFF"
												style="font-weight: bold">營收比重</td>
											<td colspan="3" class="yui-td-left">${bean.ofRevenue}</td>
										</tr>
										<tr bgcolor="#FFFFFF">
											<td width="100" height="25" bgcolor="#DDDDFF"
												style="font-weight: bold">網 址</td>
											<td colspan="3" class="yui-td-left"><a
												href='${bean.url}'>${bean.url}</a></td>
										</tr>
										<tr bgcolor="#FFFFFF">
											<td width="100" height="25" bgcolor="#DDDDFF"
												style="font-weight: bold">工 廠</td>
											<td colspan="3" class="yui-td-left">${bean.factory}</td>
										</tr>
									</table>
								</table>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
				<div id="candlestick"
					style="width: 600px; height: 400px; display: inline-block; margin: 100px"
					align="right"></div>
			</div>
			<div style="background-color: #FFFFFF; margin: 350px"></div>
		</div>
		<!-- /Featured -->
		<!-- Copyright -->
		<!-- 		<div style="margin: 100px;background-color: red;"></div> -->
	</div>


	<!-- Include all compiled plugins (below), or include individual files as needed -->

	<div id="disqus_thread"></div>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script
		src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			//指定ajax 讀取json網頁
			var contextPath = "${pageContext.request.contextPath}";
			$.ajax({
				"method" : "GET",
				"contentType" : "application/x-www-form-urlencoded; charset=UTF-8",
				"dataType" : "json",
				"data" : "action=groupname",
				"url" : contextPath + "/groupInfo.view",
				"cache" : false,
				"success" : selectstockgroups,//呼叫外面的JS function 
				error : function(xhr, ajaxOptions, thrownError) {
					alert(xhr.status);
				}
			});

			$('select[name="selectstockcompany"]').change(function() {
				clearForm();
				$.ajax({
					"method" : "GET",
					"contentType" : "application/x-www-form-urlencoded; charset=UTF-8",
					"dataType" : "json",
					"url" : contextPath + "/groupInfo.view",
					"data" : "action=stockids",
					"cache" : false,
					"success" : selectstockid,
					error : function(xhr, ajaxOptions, thrownError) {
						alert(xhr.status);
					}
				});
			});
		});

		function clearForm() {
			$('select[name="selectstockid"]').val("");
			$('select[name="selectstockid"]').first().empty();
		}
	</script>

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