<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/r/bs-3.3.5/jq-2.1.4,dt-1.10.8/datatables.min.css"/>

<script src="${pageContext.request.contextPath}/js/skel.min.js"></script>
<script src="${pageContext.request.contextPath}/js/skel-panels.min.js"></script>
<script src="${pageContext.request.contextPath}/js/init.js"></script>
<script src="${pageContext.request.contextPath}/js/functions.js"></script>
<script src="${pageContext.request.contextPath}/js/selectstock.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/r/bs-3.3.5/jqc-1.11.3,dt-1.10.8/datatables.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script
		src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
<!-- jQuery Datepicker -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/jquery-ui.min.css" />
<script
	src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
<script type="text/javascript" charset="utf-8">

$(document).ready(function() {
	
 	//table樣式
	$('#datatable').DataTable({
		"language":{
		    "emptyTable": "沒有數據",
	   		"info": "第 _PAGE_ of _PAGES_頁",
	   		"search": "檢索:",
	   		"lengthMenu": "每頁_MENU_ 條紀錄",
	   		"infoEmpty": "每頁   0 of 0 條紀錄",
	   		"paginate": {
	   			"previous": "上一頁",
	   			"next": "下一頁"
	   		}	
		},
    });
	
// 	$(".row").css({"margin":"0px auto"};

 
//頁面跳轉指定位置 jquery
$(function(){    
window.location.hash ="#auto_content";     
});

// jQuery.support.cors = true; jsoup使用

//指定ajax 讀取json網頁${pageContext.request.contextPath}
var contextPath = "${pageContext.request.contextPath}";
$.ajax({
	"method": "GET",
	"contentType": "application/x-www-form-urlencoded; charset=UTF-8",
	"dataType": "json",
	"data": "action=groupname",
	"url": contextPath+"/groupInfo.view",
	"cache": false,
    "success": selectstockgroups,//呼叫外面的JS function 
  	 error: function (xhr, ajaxOptions, thrownError) {
     alert(xhr.status);
   }
});

$('select[name="selectstockcompany"]').change(function() {
	clearForm();//一開始先清理表單
	$.ajax({
		"method": "GET",
		"contentType": "application/x-www-form-urlencoded; charset=UTF-8",
		"dataType": "json",
		"url": contextPath+"/groupInfo.view",
		"data": "action=stockids",
		"cache": false,
	    "success": selectstockid ,
	   error: function (xhr, ajaxOptions, thrownError) {//錯誤彈出式窗
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
								href="${pageContext.request.contextPath}/pages/investment/company.jsp">個股查詢</a></li>
						</ul>
						</li>
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
			<!-- 輸入表格 -->
	<!-- 					指定位置用的div-->

		<div id="searchform" style="border: black 5px solid; margin: 0px; padding: 0px;" >
			<div class="search1" style="display: inline; margin: 0px; padding: 0px;" >	
					<form action="<c:url value="/instantlyInfo.controller" />" method="post" class="form-inline">
						<div  class="submit1" style="display: inline;">
							<input id="select" class="btn btn-primary superbtn" type="submit" name="datanysis" value="Select" >
						</div>
					</form>
			</div>
			
<!-- 			測試用區塊 -->		
<!-- 			測試用區塊 -->
			
			
<!-- 			顯示表格 -->
<%-- 			<c:set var="string1" value="${param.selectstockid}"/> --%>
<%-- 			<c:set var="string2" value="${fn:substring(string1,5,10)}" /> --%>
			
			<div style="padding: 30px; " >
			<c:if test="${not empty select}">
			<div><span class="firm">${param.selectstockid}</span></div>
					<table id="datatable" class="display" cellspacing="0" width="80%" style="margin-left: 9%;">
						<thead>
							<tr class="backgrou">
								<th width="8%" style="font-size: 20px;">股票代碼</th>
								<th width="8%" style="font-size: 20px;">股票名稱</th>
								<th width="8%" style="font-size: 20px;">最近成交價</th>
								<th width="8%" style="font-size: 20px;">當盤成交量</th>
								<th width="12%" style="font-size: 20px;">當日累計成交量</th>
								<th width="8%" style="font-size: 20px;">資料時間</th>
								<th width="8%" style="font-size: 20px;">資料日期</th>
								<th width="8%" style="font-size: 20px;">今日最高點</th>
								<th width="8%" style="font-size: 20px;">今日最低點</th>
								<th width="8%" style="font-size: 20px;">開盤價</th>								
							</tr>
						</thead>

						<!-- 						直接用foreach做循環表單 -->
						<!-- var bean物件 EL取得servlet的request 裡key value 的select -->
						<tbody>
							<c:forEach var="bean" items="${select}">
							<c:url value="/instantlyInfo.controller" var="path">
								<c:param name="selectstockid" value="${bean.stockId}" />
								<c:param name="datanysis" value="Select"/>
							</c:url>
								<tr>
									<td style="font-weight: bold; font-size: 18px;"><a href="${path}">${bean.stockId}</a></td>
									<td style="font-weight: bold; font-size: 18px;">${bean.stockName}</td>
									<td style="font-weight: bold; font-size: 18px;">${bean.finalPrice}</td>
									<td style="font-weight: bold; font-size: 18px;">${bean.temporalVolume}</td>
									<td style="font-weight: bold; font-size: 18px;">${bean.volume}</td>
									<td style="font-weight: bold; font-size: 18px;">${bean.infomationTime}</td>
									<td style="font-weight: bold; font-size: 18px;">${bean.infomationDate}</td>
									<td style="font-weight: bold; font-size: 18px;">${bean.high}</td>
									<td style="font-weight: bold; font-size: 18px;">${bean.low}</td>
									<td style="font-weight: bold; font-size: 18px;">${bean.openPrice}</td>
								</tr>
							</c:forEach>
						</c:if>
						</tbody>
					</table>
				 </div>
			</div>
		<!-- /Main -->
	</div>
	<div id="disqus_thread"></div>
	
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