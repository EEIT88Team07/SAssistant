<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- 留言板 -->
<script id="dsq-count-scr" src="//markchen-2.disqus.com/count.js" async></script>
<!-- bookstapcss包	 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.css" />
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/r/bs-3.3.5/jq-2.1.4,dt-1.10.8/datatables.min.css" />


<script src="${pageContext.request.contextPath}/js/skel.min.js"></script>
<script src="${pageContext.request.contextPath}/js/skel-panels.min.js"></script>
<script src="${pageContext.request.contextPath}/js/init.js"></script>
<script src="${pageContext.request.contextPath}/js/functions.js"></script>
<script src="${pageContext.request.contextPath}/js/selectstock.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/r/bs-3.3.5/jqc-1.11.3,dt-1.10.8/datatables.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script
	src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>



<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
	$(document).ready(function() {

		$('span[title="favorite"]').click(function() {
			$(this).toggleClass("glyphicon glyphicon-minus");
		});
		//table樣式
		$('#datatable').DataTable({
			"language" : {
				"emptyTable" : "沒有數據",
				"info" : "第 _PAGE_ of _PAGES_頁",
				"sSearch" : "檢索:",
				"lengthMenu" : "每頁_MENU_ 條紀錄",
				"sInfoEmpty" : "每頁   0 of 0 條紀錄",
				"paginate": {
		   			"previous": "上一頁",
		   			"next": "下一頁"
		   		}
			},
			"searching": false
		});

		$('input[name="prodaction"]').click(function() {
			//這邊取得ID
			var account = $('input[name="account"]').val();

			$.ajax({
				"method" : "GET",
				"contentType" : "application/json",
				"dataType" : "json",
				"data" : "action=getMyFav&account=" + account,
				"url" : contextPath + "/MyFavouriteAjax.view",
				"cache" : false,
				"success" : function(json) {

					if (json != null) {
						console.log(json)

					}
				}

			})
		})
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
								href="${pageContext.request.contextPath}/pages/investment/stockinquiries.jsp">個股查詢</a></li>
							<li><a
								href="${pageContext.request.contextPath}/pages/investment/realtime.jsp">即時行情</a></li>
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



		<input type="text" name="account" value="${param.account}"> <input
			type="submit" name="prodaction" value="Select">






		<!-- Main -->
		<div id="searchform" style="border: black 5px solid;">
			<div class="search1">

				<div id="auto_content" class="pic"></div>

				<form action="<c:url value='/myfavourite.controller' />"
					method="post" class="form-inline">
					<div class="form-group">
						<label class="control-label">股票產業：</label> <select
							name="selectstockcompany" style="width: 120px">
							<c:if test="${not empty param}">
								<option>${param.selectstockcompany}</option>
							</c:if>
						</select> <label class="control-label">股票代碼： </label> <select
							name="selectstockid">
							<c:if test="${not empty param}">
								<option>${param.selectstockid}</option>
							</c:if>
						</select> <input type="text" name="account" value="test123">
					</div>
					<div class="form-group" style="margin: 10px auto">
						<label class="control-label">日期區間：&nbsp;</label><input
							type="text" name="startDate" value="${param.startDate}"
							placeholder="起始日期" style="width: 120px"> <span>${error.stratDate}</span>
						<label class="control-label">到&nbsp;</label><input type="text"
							name="endDate" value="${param.endDate}" placeholder="終止日期"
							style="width: 120px"> <span>${error.endDate}</span>
					</div>
					<div class="submit1">
						<input class="btn btn-primary superbtn" type="submit"
							name="datanysis" value="讀取資料"> <input id="formButton"
							type="submit" name="datanysis" value="onload">

					</div>
				</form>
			</div>

			<div style="margin: 200px;">
				<div>
					<span class="firm">${param.selectstockid}</span>
				</div>
				<table id="datatable" class="display" cellspacing="0" width="100%">
					<thead>
						<tr class="backgrou">
							<th>我的最愛</th>
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
					<!-- 						直接用foreach做循環表單 -->
					<!-- var bean物件 EL取得servlet的request 裡key value 的select -->
					<tbody>

						<c:if test="${not empty select}">
							<c:forEach var="bean" items="${select}">
								<c:url value="/stockCompany.controller" var="path">
									<c:param name="selectstockid" value="${bean.stockId}" />
									<c:param name="datanysis" value="Select" />
								</c:url>
								<tr>
									<td>
										<div>
											<button type="button" class="btn btn-default">
												<span title="favorite" class="glyphicon glyphicon-plus"
													aria-hidden="true"></span>
											</button>
										</div>
									</td>
									<td><a href="${path}">${bean.stockId}</a></td>
									<td>${bean.buildDate}</td>
									<td>${bean.openPrice}</td>
									<td>${bean.closingPrice}</td>
									<td>${bean.highestPrice}</td>
									<td>${bean.lowestPrice}</td>
									<td>${bean.tradingVolume}</td>
									<td>${bean.changeInPrice}</td>
									<td>${bean.turnOverInValue}</td>
									<td>${bean.numberOfTransactions}</td>

								</tr>
							</c:forEach>
						</c:if>
					</tbody>
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
		$('#datatable').removeClass('display').addClass(
				'table table-striped table-bordered');
	</script>
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