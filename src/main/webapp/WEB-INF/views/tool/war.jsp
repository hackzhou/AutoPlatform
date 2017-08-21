<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/plugins/images/favicon.png">
<title>工具-更新包(部署工具)</title>
<!-- Bootstrap Core CSS -->
<link href="${pageContext.request.contextPath}/eliteadmin/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/plugins/bower_components/datatables/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/cdn/buttons.dataTables.min.css" rel="stylesheet" type="text/css" />
<!-- Menu CSS -->
<link href="${pageContext.request.contextPath}/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css" rel="stylesheet">
<!-- animation CSS -->
<link href="${pageContext.request.contextPath}/eliteadmin/css/animate.css" rel="stylesheet">
<!--alerts CSS -->
<link href="${pageContext.request.contextPath}/plugins/bower_components/sweetalert/sweetalert.css" rel="stylesheet" type="text/css">
<!-- Custom CSS -->
<link href="${pageContext.request.contextPath}/eliteadmin/css/style.css" rel="stylesheet">
<!-- color CSS -->
<link href="${pageContext.request.contextPath}/eliteadmin/css/colors/blue.css" id="theme"  rel="stylesheet">
</head>
<body>
<!-- Preloader -->
<div class="preloader">
  <div class="cssload-speeding-wheel"></div>
</div>
<div id="wrapper">
  <!-- Top Navigation -->
  <jsp:include page="/WEB-INF/views/top.jsp"></jsp:include>
  <!-- Page Content -->
  <div id="page-wrapper">
    <div class="container-fluid">
      <div class="row bg-title">
        <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
          <h4 class="page-title"><i class="fa fa-pagelines m-r-10" style='color:green'></i><span><b style='color:black'>更新包(部署工具)</b></span></h4>
        </div>
      </div>
      <div class="row">
		<div class="col-xs-12 col-sm-12 col-md-12 m-t-10 text-center">
			<div id="msgDiv" class="alert alert-danger alert-dismissable" style="display: none">
				<button type="button" class="close" aria-hidden="true">
					&times;
				</button>
				<span id="msg">${msg}</span>
			</div>
		</div>
	  </div>
      <!-- /row -->
      <div class="row">
        <div class="col-md-12">
          <div class="panel panel-info">
            <div class="panel-body">
	          <div class="form-body">
	            <form id="tool-war-form" action="${pageContext.request.contextPath}/tool/war/run" method="post" enctype="multipart/form-data">
	              <div class="row">
	                <div class="col-md-2 text-center">
	                  <div class="form-group">
	                    <label class="col-sm-4 text-center"><code>服务器IP <i class="fa fa-chevron-right text-info"></i></code></label>
					    <div class="col-sm-8">
	                      <select id="tool-war-ip" name="tool-war-ip" class="form-select" style="width: 100%;"></select>
					    </div>
	                  </div>
	                </div>
	                <div class="col-md-2 text-center">
	                  <div class="form-group">
	                    <label class="col-sm-4 text-center"><code>部署服务 <i class="fa fa-chevron-right text-info"></i></code></label>
					    <div class="col-sm-8">
	                      <select id="tool-war-server" name="tool-war-server" class="form-select" style="width: 100%;"></select>
					    </div>
	                  </div>
	                </div>
	                <div class="col-md-3 text-center">
	                  <div class="form-group">
	                    <label class="col-sm-3 text-center"><code>对比文件 <i class="fa fa-chevron-right text-info"></i></code></label>
					    <div class="col-sm-9">
	                      <select id="tool-war-name" name="tool-war-name" class="form-select" style="width: 100%;"></select>
					    </div>
	                  </div>
	                </div>
	                <div class="col-md-3">
	              	  <div class="fileinput fileinput-new input-group" data-provides="fileinput">
		                <span class="input-group-addon btn btn-warning btn-file"> <span class="fileinput-new">选择war包</span> <span class="fileinput-exists">重选</span>
		                <input type="file" name="file">
		                </span> <a href="#" class="input-group-addon btn btn-default fileinput-exists" data-dismiss="fileinput">移除</a>
		                <div class="form-control" data-trigger="fileinput"> <i class="glyphicon glyphicon-file fileinput-exists"></i> <span class="fileinput-filename"></span></div>
		              </div>
	                </div>
	                <div class="col-md-2 text-center">
	                  <input type="hidden" id="tool-war-start-log-ip" name="tool-war-start-log-ip" value="">
	                  <input type="hidden" id="tool-war-start-log-server" name="tool-war-start-log-server" value="">
	                  <button type="button" class="btn btn-primary waves-effect waves-light m-r-20" id="tool-war-showlog"><span id="tool-war-showlog-span"></span></button>
	                  <button type="button" class="btn btn-success waves-effect waves-light m-r-20" id="tool-war-run" onclick="toolWarRun();">部署</button>
	                </div>
	              </div>
	            </form>
	          </div>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
	  	<div class="col-sm-12">
	  	  <form class="form-horizontal">
	  	  	<div class="form-group">
              <label class="col-md-12">查看日志结果显示</label>
              <div class="col-md-12">
                <textarea id="tool-war-resultlog" class="form-control" rows="25"></textarea>
              </div>
            </div>
	  	  </form>
	  	</div>
	  </div>
      <!-- /.container-fluid -->
      <jsp:include page="/WEB-INF/views/foot.jsp"></jsp:include>
    </div>
    <!-- /#page-wrapper -->
  </div>
</div>
<!-- /#wrapper -->
<!-- jQuery -->
<script src="${pageContext.request.contextPath}/plugins/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="${pageContext.request.contextPath}/eliteadmin/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- Base -->
<script src="${pageContext.request.contextPath}/js/base.js"></script>
<script src="${pageContext.request.contextPath}/js/dateFormat.js"></script>
<!-- Menu Plugin JavaScript -->
<script src="${pageContext.request.contextPath}/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></script>
<!--slimscroll JavaScript -->
<script src="${pageContext.request.contextPath}/eliteadmin/js/jquery.slimscroll.js"></script>
<!--Wave Effects -->
<script src="${pageContext.request.contextPath}/eliteadmin/js/waves.js"></script>
<!-- Sweet-Alert  -->
<script src="${pageContext.request.contextPath}/plugins/bower_components/sweetalert/sweetalert.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bower_components/sweetalert/jquery.sweet-alert.custom.js"></script>
<!-- Custom Theme JavaScript -->
<script src="${pageContext.request.contextPath}/eliteadmin/js/custom.min.js"></script>
<script src="${pageContext.request.contextPath}/eliteadmin/js/jasny-bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bower_components/datatables/jquery.dataTables.min.js"></script>
<script>

	$(document).ready(function(){
		initEvent();
		initShowWarLog();
		initToolWarType();
		var msg = $('#msg').html();
		if(msg != null && msg != ""){
			showMsgDiv(msg);
		}else{
			hideMsgDiv();
		}
		setInterval("readWarLog()", 5000);
	});
	
	function initEvent(){
		$("#tool-war-ip").change(function(){
			initToolWarServer($(this).val(), null);
		});
		$("#tool-war-showlog-span").html("启动查看日志");
		$("#tool-war-showlog").click(function(){
			if($("#tool-war-showlog-span").html() == "启动查看日志"){
				var ip = $('#tool-war-ip').val();
				var server = $('#tool-war-server').val();
				if(ip == null || ip.trim() == ""){
					showMsgDiv("请选择服务器地址！");
				}else if(server == null || server.trim() == ""){
					showMsgDiv("请选择部署服务！");
				}else{
					$("#tool-war-showlog-span").html("停止查看日志");
					$("#tool-war-showlog").prop("class", "btn btn-danger waves-effect waves-light m-r-20");
					$("#tool-war-ip").prop("disabled", true);
					$("#tool-war-server").prop("disabled", true);
					startWarLog(ip, server);
				}
			}else{
				$("#tool-war-showlog-span").html("启动查看日志");
				$("#tool-war-showlog").prop("class", "btn btn-primary waves-effect waves-light m-r-20");
				$("#tool-war-ip").prop("disabled", false);
				$("#tool-war-server").prop("disabled", false);
				stopWarLog();
			}
		});
	}
	
	function initToolWarIP(ip){
		$.ajax({
    		type:"get",
    		url:"<%=request.getContextPath()%>/tool/war/ips",
    		success:function(data){
    			if(data.responseCode == "0000"){
    				hideMsgDiv();
    				var optionstring = "";
    				var list = data.data;
    				if(list != null){
    					for(var i = 0; i < list.length; i++){
        					if(ip == list[i] || i == 0){
        						optionstring += "<option value='" + list[i] + "' selected>" + list[i] + "</option>";
        					}else{
    	    					optionstring += "<option value='" + list[i] + "'>" + list[i] + "</option>";
        					}
        				}
    				}
    				$('#tool-war-ip').empty();
    				$('#tool-war-ip').append(optionstring);
    			}else{
    				$('#tool-war-ip').empty();
    				showMsgDiv(data.responseMsg);
    			}
    		}
    	});
	}
	
	function initToolWarServer(ip, server){
		$.ajax({
    		type:"get",
    		url:"<%=request.getContextPath()%>/tool/war/ip=" + ip + "/servers",
    		success:function(data){
    			if(data.responseCode == "0000"){
    				hideMsgDiv();
    				var optionstring = "";
    				var list = data.data;
    				if(list != null){
    					for(var i = 0; i < list.length; i++){
        					if(server == list[i] || i == 0){
        						optionstring += "<option value='" + list[i] + "' selected>" + list[i] + "</option>";
        					}else{
    	    					optionstring += "<option value='" + list[i] + "'>" + list[i] + "</option>";
        					}
        				}
    				}
    				$('#tool-war-server').empty();
    				$('#tool-war-server').append(optionstring);
    			}else{
    				$('#tool-war-server').empty();
    				showMsgDiv(data.responseMsg);
    			}
    		}
    	});
	}
	
	function initToolWarType(){
    	$.ajax({
    		type:"get",
    		url:"<%=request.getContextPath()%>/tool/war/types",
    		success:function(data){
    			if(data.responseCode == "0000"){
    				var optionstring = "";
    				var list = data.data;
    				if(list != null){
    					for(var i = 0; i < list.length; i++){
        					if(i == 0){
        						optionstring += "<option value='" + list[i] + "' selected>" + list[i] + "</option>";
        					}else{
    	    					optionstring += "<option value='" + list[i] + "'>" + list[i] + "</option>";
        					}
        				}
    				}
    				$('#tool-war-name').empty();
    				$('#tool-war-name').append(optionstring);
    			}else{
    				$('#tool-war-name').empty();
    				showMsgDiv(data.responseMsg);
    			}
    		}
    	});
    }
	
	function toolWarRun(){
		swal({
			title: "你确定部署吗？",
			text: "请检查相关请求参数，谨慎操作！",
			type: "warning",
			showCancelButton: true,
			confirmButtonColor: "#DD6B55",
			confirmButtonText: "确定，部署！",
			cancelButtonText: "取消",
			closeOnConfirm: true
		}, function(){
			var ip = $('#tool-war-ip').val();
			var server = $('#tool-war-server').val();
			var name = $('#tool-war-name').val();
			var filename = $('.fileinput-filename').html();
			if(ip == null || ip.trim() == ""){
				showMsgDiv("请选择服务器地址！");
			}else if(server == null || server.trim() == ""){
				showMsgDiv("请选择部署服务！");
			}else if(name == null || name.trim() == ""){
				showMsgDiv("请选择对比文件！");
			}else if(filename == ""){
				showMsgDiv("请选择War包！");
			}else if(!checkFiles(filename)){
				showMsgDiv("选择文件不合法，文件的扩展名必须为.war！");
			}else{
				hideMsgDiv();
				if($("#tool-war-showlog-span").html() == "启动查看日志"){
					$("#tool-war-showlog-span").html("停止查看日志");
					$("#tool-war-showlog").prop("class", "btn btn-danger waves-effect waves-light m-r-20");
					$("#tool-war-ip").prop("disabled", true);
					$("#tool-war-server").prop("disabled", true);
					startWarLog($("#tool-war-ip").val(), $("#tool-war-server").val());
				}
				$('#tool-war-form').submit();
			}
		});
	}
	
	function checkFiles(str){
		var strRegex = "(.war)$";
		var re = new RegExp(strRegex);
		if (re.test(str.toLowerCase())){
			return true;
		}else{
			return false;
		}
	}
	
	function initShowWarLog(){
		$.ajax({
    		type:"get",
    		url:"<%=request.getContextPath()%>/tool/war/log/isrun",
    		success:function(data){
    			var bool = true;
    			if(data.responseCode == "0000"){
    				var result = data.data;
    				if(result.success){
    					$("#tool-war-start-log-ip").val(result.data);
    					$("#tool-war-start-log-server").val(result.name);
    					initToolWarIP(result.data);
    					initToolWarServer(result.data, result.name);
    					$("#tool-war-showlog-span").html("停止查看日志");
    					$("#tool-war-showlog").prop("class", "btn btn-danger waves-effect waves-light m-r-20");
    					$("#tool-war-ip").prop("disabled", true);
    					$("#tool-war-server").prop("disabled", true);
    					bool = false;
    				}
    			}
    			if(bool){
    				$("#tool-war-start-log-ip").val("");
					$("#tool-war-start-log-server").val("");
					initToolWarIP(null);
					initToolWarServer(null, null);
					$("#tool-war-showlog-span").html("启动查看日志");
					$("#tool-war-showlog").prop("class", "btn btn-primary waves-effect waves-light m-r-20");
					$("#tool-war-ip").prop("disabled", false);
					$("#tool-war-server").prop("disabled", false);
					stopWarLog();
    			}
    		}
    	});
	}
	
	function startWarLog(ip, server){
		$.ajax({
    		type:"get",
    		url:"<%=request.getContextPath()%>/tool/war/ip=" + ip + "/server=" + server + "/log/start",
    		success:function(data){
    			if(data.responseCode == "0000"){
    				var result = data.data;
    				if(result.success){
    					$("#tool-war-start-log-ip").val(ip);
    					$("#tool-war-start-log-server").val(server);
    				}else{
    					showMsgDiv(result.msg);
    				}
    			}else{
    				showMsgDiv(data.responseMsg);
    			}
    		}
    	});
	}
	
	function stopWarLog(){
		var ip = $("#tool-war-start-log-ip").val();
		if(ip == null || ip == ""){
			ip = $("#tool-war-ip").val();
		}
		if(ip != null && ip != ""){
			$.ajax({
	    		type:"get",
	    		url:"<%=request.getContextPath()%>/tool/war/ip=" + ip + "/log/stop",
	    		success:function(data){
	    			if(data.responseCode == "0000"){
	    				var result = data.data;
	    				if(result.success){
	    					$("#tool-war-start-log-ip").val("");
	    					$("#tool-war-start-log-server").val("");
	    					$("#tool-war-resultlog").val("");
	    				}else{
	    					showMsgDiv(result.msg);
	    				}
	    			}else{
	    				showMsgDiv(data.responseMsg);
	    			}
	    		}
	    	});
		}else{
			showMsgDiv("停止查看日志失败！");
		}
	}
	
	function readWarLog(){
		var ip = $("#tool-war-start-log-ip").val();
		if(ip != null && ip != ""){
			$.ajax({
	    		type:"get",
	    		url:"<%=request.getContextPath()%>/tool/war/ip=" + ip + "/log/read",
	    		success:function(data){
	    			if(data.responseCode == "0000"){
	    				var result = data.data;
	    				if(result.success){
	    					$("#tool-war-resultlog").val(result.data);
	    				}else{
	    					showMsgDiv(result.msg);
	    				}
	    			}else{
	    				showMsgDiv(data.responseMsg);
	    			}
	    		}
	    	});
		}
	}

</script>
<!--Style Switcher -->
<script src="${pageContext.request.contextPath}/plugins/bower_components/styleswitcher/jQuery.style.switcher.js"></script>
</body>
</html>
