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
