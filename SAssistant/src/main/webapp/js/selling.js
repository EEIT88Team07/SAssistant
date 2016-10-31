function selling(stockId, dateOfPurchase, purchasePrice, purchaseQuantity, purchaseNumber) {
	
	var dialog, form;

	var sellingPrice = $("#sellingPrice");
	var sellingQuantity = $("#sellingQuantity");
	var dateOfSelling = $("#dateOfSelling");
	var allFields = $([]).add(sellingPrice).add(sellingQuantity).add(dateOfSelling);
	var tips = $(".validateTips");
	function updateTips(t) {
		tips.text(t).addClass("ui-state-highlight");
		setTimeout(function() {
			tips.removeClass("ui-state-highlight", 1500);
		}, 500);
	}

	function checkRegexp(o, regexp, n) {
		if (!(regexp.test(o.val()))) {
			o.addClass("ui-state-error");
			updateTips(n);
			return false;
		} else {
			return true;
		}
	}

	function addselling() {
		var valid = true;
		allFields.removeClass("ui-state-error");

		valid = valid && checkRegexp(sellingPrice, /^[0-9]+\.?[0-9]*$/, "購買價格必須為正的整數或浮點數");
		valid = valid && checkRegexp(sellingQuantity, /^[0-9]+\.?[0-9]*$/, "購買數量必須為正的整數或浮點數");
		if (valid) {			
			
			var url = contextPath + "/sellinghistory.controller?purchaseNumber=" + purchaseNumber +"&sellingPrice=" + sellingPrice + "&SellingQuantity=" + SellingQuantity + "&dateOfSelling=" + dateOfSelling+ "&action=新增";

			document.location.href = url;

			dialog.dialog("close");
		}
	}

	dialog = $("#dialog-form-selling").dialog({
		autoOpen : false,
		height : 400,
		width : 350,
		modal : true,
		buttons : {
			"新增" : addselling,
			取消 : function() {
				dialog.dialog("close");
			}
		},
		取消 : function() {
			form[0].reset();
			allFields.removeClass("ui-state-error");
		}
	});

	form = dialog.find("form").on("submit", function(event) {
		event.preventDefault();
		addtrans();
	});

	$("#create-user").button().on("click", function() {
		dialog.dialog("open");
	});
}