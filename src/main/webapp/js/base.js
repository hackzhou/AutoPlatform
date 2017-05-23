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
