function selectstockgroups (dom) {
		     //放入一筆空白的清單
			  $('select[name="selectstockcompany"]').append($("<option></option>").attr("value", "").text("選擇產業類別"));
		     for (var i = 0; i < dom.length; i++) {
//	 		 console.log(msg[0].msg[i].industryCategory);   
		     //將取得的Json一筆一筆放入清單
		     $('select[name="selectstockcompany"]').append($("<option></option>").attr("value",dom[i].industryCategory).text(dom[i].industryCategory));
		     }
		};
function selectstockid (msg) {
    //放入一筆空白的清單
//	console.log(value2);
	var s = $('select[name="selectstockcompany"]').val();
		
   $.each(msg,function(key,value)
   {
   	$.each(value,function(key2,value2)
   	{	
   		if(key2==s){
   		$('select[name="selectstockid"]').append($("<option></option>").attr("value",value2).text(value2));
   		}
   	});
//將取得的Json一筆一筆放入清單 		    		
   });
};
