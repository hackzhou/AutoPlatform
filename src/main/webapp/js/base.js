/**
 * 
 */

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