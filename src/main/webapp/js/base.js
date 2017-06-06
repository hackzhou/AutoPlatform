/**
 * 
 */

(function($) {
	$(".close").on("click", function() {
		$("#msgDiv").hide();
	});
})(jQuery);

String.prototype.trim=function() {
    return this.replace(/(^\s*)|(\s*$)/g,'');
}

String.format = function() {
	if (arguments.length == 0)
		return null;
	var str = arguments[0];
	for (var i = 1; i < arguments.length; i++) {
		var re = new RegExp('\\{' + (i - 1) + '\\}', 'gm');
		str = str.replace(re, arguments[i]);
	}
	return str;
};

function jsonFormat(data){
	return JSON.stringify(JSON.parse(data), null, '\t');
}

function GetQueryString(name){
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r != null)
		return  unescape(r[2]);
	return null;
}

function tooltipJson(text){
	if(text == null || text.length == 0){
		return text;
	}
	var glance = "";
	if(text.length > 30) {
		glance = text.substring(0, 30) + "...";
	}else{
		glance = text;
	}
	var jsonObj = JSON.parse(text);
	var json = JSON.stringify(jsonObj, null, '\t');
	var html = "<div data-toggle='tooltip' title='{0}' data-placement='left'>{1}</div>";
	return String.format(html, json, glance);
}

function getCookie(name){
	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	if(arr=document.cookie.match(reg)){
		return unescape(arr[2]);
	}else{
		return "";
	}
}

function showMsgDiv(msgStr){
	if(msgStr != ""){
		$("#msgDiv").show();
		$("#msg").html(msgStr);
	}
}

function hideMsgDiv(){
	$("#msgDiv").hide();
}
