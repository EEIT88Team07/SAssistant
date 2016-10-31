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

<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
	$(document).ready(

	function() {
		var table1 = $('#datatable').DataTable({
			"language" : {
				"emptyTable" : "沒有數據",
				"info" : "第 _PAGE_ of _PAGES_頁",
				"sSearch" : "檢索:",
				"lengthMenu" : "每頁_MENU_ 條紀錄",
				"sInfoEmpty" : "每頁   0 of 0 條紀錄"
			},
			"ajax" : {
				"url" : contextPath + "/sellingajax.view",
				"contentType" : "application/x-www-form-urlencoded; charset=UTF-8",
				"data" : function(d) {
					d.action = "getSellingHistory";
				}
			},
			"columns" : [ {
				"data" : "stockId"
			},{
				"data" : "stockName"
			},{
				"data" : "dateOfPurchase"
			},{
				"data" : "sellingPrice"
			},{
				"data" : "sellingQuantity"
			},{
				"data" : "dateOfSelling"
			},{
				"data" : "total"
			},{
				"data" : "cost"
			},{
				"data" : "income"
			},{
				"data" : "netIncome"
			},{
				"data" : "netProfitMargin"
			} ]

		});

	});
</script>

</head>
<body class="homepage">

	<!-- Header -->
	<div id="header">
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
		<div id="searchform" style="border: black 5px solid;">

			<div id="datatablediv" style="margin: 50px;">
				<div>
					<span class="firm">${param.selectstockid}</span>
				</div>
				<div id="errorbox">
					<span class="error">${error.id}</span> <span class="error">${error.dateOfPurchase}</span>
					<span class="error">${error.purchasePrice}</span> <span
						class="error">${error.purchaseQuantity}</span> <span class="error">${error.stopLossLimit}</span>
					<span class="error">${error.takeProfitLimit}</span> <span
						class="error">${error.action}</span>
				</div>

				<table id="datatable" class="display" cellspacing="0" width="100%"
					style="border-style: hidden;">
					<thead>
						<tr class="backgrou">
							<th>股票代碼</th>
							<th>股票名稱</th>
							<th>購買日期</th>
							<th>賣出價格</th>
							<th>賣出數量</th>
							<th>賣出日期</th>
							<th>賣出總額</th>
							<th>成本</th>
							<th>收入</th>
							<th>淨利</th>
							<th>淨利率</th>
							<th></th>
						</tr>
					</thead>
				</table>
				<button id="create-user">新增一筆購買記錄</button>
			</div>
		</div>



		<div id="dialog-form" title="新增一筆交易記錄">
			<p class="validateTips">*號為必填欄位</p>

			<form>
				<fieldset>
					<label for="stockId">股票代碼*</label> <input type="text"
						name="stockId" id="stockId"
						class="text ui-widget-content ui-corner-all"> <label
						for="dateOfPurchase">購買時間*</label> <input type="text"
						name="dateOfPurchase" id="dateOfPurchase"
						class="text ui-widget-content ui-corner-all"> <label
						for="purchasePrice">購買價格*</label> <input type="text"
						name="purchasePrice" id="purchasePrice"
						class="text ui-widget-content ui-corner-all"> <label
						for="purchaseQuantity">購買數量*</label> <input type="text"
						name="purchaseQuantity" id="purchaseQuantity"
						class="text ui-widget-content ui-corner-all"> <label
						for="stopLossLimit">停損點</label> <input type="text"
						name="stopLossLimit" id="stopLossLimit"
						class="text ui-widget-content ui-corner-all"> <label
						for="takeProfitLimit">停利點</label> <input type="text"
						name="takeProfitLimit" id="takeProfitLimit"
						class="text ui-widget-content ui-corner-all">

					<!-- Allow form submission with keyboard without duplicating the dialog button -->
					<input type="submit" tabindex="-1"
						style="position: absolute; top: -1000px">
				</fieldset>
			</form>
		</div>

		<form style="display: none;"
			action="<c:url value="/transhistory.controller" />" name="fileinfo"
			method="POST">
			<input type="text" name="action" value="刪除"> <input
				type="submit">
		</form>





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



	<script type="text/javascript">
		$('#datatable').removeClass('display').addClass('table table-striped table-bordered');
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