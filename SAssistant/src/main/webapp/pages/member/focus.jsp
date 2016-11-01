<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>SAssistant</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />

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
	$(document).ready(function() {
		$('span[title="favorite"]').click(function() {
			$(this).toggleClass("glyphicon glyphicon-minus");
		});

		$('input[id="getMyFavourite"]').click(function() {
			table1.destroy();
			$('#datatable').DataTable({
				"language" : {
					"emptyTable" : "沒有數據",
					"info" : "第 _PAGE_ of _PAGES_頁",
					"sSearch" : "檢索:",
					"lengthMenu" : "每頁_MENU_ 條紀錄",
					"sInfoEmpty" : "每頁   0 of 0 條紀錄"
				},
				"ajax" : {
					"url" : contextPath + "/myfavouriteajax.view",
					"contentType" : "application/x-www-form-urlencoded; charset=UTF-8",
					"data" : function(d) {
						d.action = "getmyfav";
						d.startDate = $('input[name="startDate"]').val();
						d.endDate = $('input[name="endDate"]').val();
						d.datanysis = $('input[name="datanysis"]').val();
					}
				},
				"columns" : [ {
					"data" : "stockId"
				}, {
					"data" : "buildDate"
				}, {
					"data" : "openPrice"
				}, {
					"data" : "closingPrice"
				}, {
					"data" : "turnOverInValue"
				}, {
					"data" : "changeInPrice"
				}, {
					"data" : "tradingVolume"
				}, {
					"data" : "numberOfTransactions"
				}, {
					"data" : "highestPrice"
				}, {
					"data" : "lowestPrice"
				} ]

			});

		});

		var table1 = $('#datatable').DataTable({
			"language" : {
				"emptyTable" : "沒有數據",
				"info" : "第 _PAGE_ of _PAGES_頁",
				"sSearch" : "檢索:",
				"lengthMenu" : "每頁_MENU_ 條紀錄",
				"sInfoEmpty" : "每頁   0 of 0 條紀錄"
			},
			"ajax" : {
				"url" : contextPath + "/myfavouriteajax.view",
				"contentType" : "application/x-www-form-urlencoded; charset=UTF-8",
				"data" : function(d) {
					d.action = "onload";
				}
			},
			"initComplete" : function() {
				var api = this.api();
				api.$('tr').click(function() {
					var stockid = this.childNodes[0].innerHTML;
					var url = contextPath + "/stockCompany.controller?selectstockid=" + stockid + "&datanysis=Select";
					document.location.href = url;
				});
			},
			"columns" : [ {
				"data" : "stockId"
			}, {
				"data" : "buildDate"
			}, {
				"data" : "openPrice"
			}, {
				"data" : "closingPrice"
			}, {
				"data" : "turnOverInValue"
			}, {
				"data" : "changeInPrice"
			}, {
				"data" : "tradingVolume"
			}, {
				"data" : "numberOfTransactions"
			}, {
				"data" : "highestPrice"
			}, {
				"data" : "lowestPrice"
			} ]

		});

		//ajax自動載入前一日資料

		$.ajax({
			"method" : "GET",
			"contentType" : "application/x-www-form-urlencoded; charset=UTF-8",
			"dataType" : "json",
			"data" : "action=groupname",
			"url" : contextPath + "/groupInfo.view",
			"cache" : false,
			"success" : selectstockgroups,//呼叫外面的JS function 
			"error" : function(xhr, ajaxOptions, thrownError) {
				alert(xhr.status);
			}
		});
		$('select[name="selectstockcompany"]').change(function() {
			clearForm();//一開始先清理表單
			$.ajax({
				"method" : "GET",
				"contentType" : "application/x-www-form-urlencoded; charset=UTF-8",
				"dataType" : "json",
				"url" : contextPath + "/groupInfo.view",
				"data" : "action=stockids",
				"cache" : false,
				"success" : selectstockid,
				error : function(xhr, ajaxOptions, thrownError) {//錯誤彈出式窗
					alert(xhr.status);
				}
			});
		});
		//Datepicker
		$('input[name="startDate"],[name="endDate"]').attr("readonly", true).datepicker({
			"defaultDate" : new Date(),
			"changeMonth" : true,
			"changeYear" : true,
			"dateFormat" : "yy-mm-dd",
			"altFormat" : "yy/mm/dd",
			"numberOfMonths" : 1,
			"maxDate" : new Date(),
			"minDate" : new Date(2005, 1 - 1, 1)
		});

	});

	function clearForm() {
		$('select[name="selectstockid"]').val("");
		$('select[name="selectstockid"]').empty();
	}
</script>
</head>
<body class="homepage">

	<!-- Header -->
	<div id="header">
		<div style="float: right; margin: 30px">
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

			<!-- Logo -->
			<div
				style="display: inline-block; float: left; margin-left: 20px; margin-top: 20px">
				<a href="${pageContext.request.contextPath}/index.jsp"><img
					alt="" src="${pageContext.request.contextPath}/images/logo.png" /></a>
			</div>

			<div id="fdw">
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
		<!-- /圖片 -->







		<!-- Main -->
		<div id="searchform">
			<div class="search1">


				<div class="form-group" style="margin: 10px auto; display: block;">
					<label class="control-label">日期區間：</label><input type="text"
						name="startDate" value="${param.startDate}" placeholder="起始日期"
						style="width: 120px; display: inline;"> <span>${error.stratDate}</span>
					<label class="control-label" style="display: inline;">到
						&nbsp;</label><input type="text" name="endDate" value="${param.endDate}"
						placeholder="終止日期" style="width: 120px; display: inline;">

					<input class="btn btn-primary superbtn" type="button"
						id="getMyFavourite" name="datanysis" value="讀取資料"
						style="margin-left: 30px;"> <span>${error.endDate}</span>
				</div>


			</div>

			<div id="datatablediv" style="margin: 50px">
				<div>
					<span class="firm">${param.selectstockid}</span>
				</div>

				<table id="datatable" class="display" cellspacing="0" width="100%">
					<thead>
						<tr class="backgrou">
							<th>股票代碼</th>
							<th>資料日期</th>
							<th>開盤價</th>
							<th>收盤價</th>
							<th>最高價</th>
							<th>最低價</th>
							<th>成交股數</th>
							<th>漲跌價差</th>
							<th>成交金額</th>
							<th>成交筆數</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
		<!-- /Extra -->

		<!-- Main -->
		<div id="main" class="container"></div>
		<!-- Main -->

	</div>
	<!-- /Main -->




	<!-- Copyright -->
	<div id="copyright" class="container">
		Design: <a href="http://templated.co">TEMPLATED</a> Images: <a
			href="http://unsplash.com">Unsplash</a> (<a
			href="http://unsplash.com/cc0">CC0</a>)
	</div>


	<div id="disqus_thread"></div>




	<script type="text/javascript">
		$('#datatable').removeClass('display').addClass('table table-striped table-bordered');
	</script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script type="text/javascript">
		(function() { // DON'T EDIT BELOW THIS LINE var d = document, s =
			d.createElement('script');
			s.src = '//markchen-2.disqus.com/embed.js';
			s.setAttribute('data-timestamp', +new Date());
			(d.head || d.body).appendChild(s);
		})();
	</script>
</body>
</html>