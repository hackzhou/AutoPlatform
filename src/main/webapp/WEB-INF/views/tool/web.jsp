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
<title>工具-更新包(前端部署)</title>
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
          <h4 class="page-title"><i class="fa fa-pagelines m-r-10" style='color:green'></i><span><b style='color:black'>更新包(前端部署)</b></span></h4>
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
	            <form id="tool-web-form" action="${pageContext.request.contextPath}/tool/web/run" method="post" enctype="multipart/form-data">
	              <div class="row">
	                <div class="col-md-2 text-center">
	                  <div class="form-group">
	                    <label class="col-sm-4 text-center"><code>部署项目 <i class="fa fa-chevron-right text-info"></i></code></label>
					    <div class="col-sm-8">
	                      <select id="tool-web-project" name="tool-web-project" class="form-select" style="width: 100%;">
	                        <option value="ball" selected>ball</option>
	                        <option value="channel">channel</option>
	                        <option value="dart">dart</option>
	                        <option value="fishing">fishing</option>
	                        <option value="football">football</option>
	                        <option value="kingdom">kingdom</option>
	                        <option value="legion">legion</option>
	                        <option value="moto">moto</option>
	                        <option value="platform">platform</option>
	                        <option value="ring">ring</option>
	                        <option value="samguk">samguk</option>
	                      </select>
					    </div>
	                  </div>
	                </div>
	                <div class="col-md-2 text-center">
	                  <div class="form-group">
	                    <label class="col-sm-4 text-center"><code>版本 <i class="fa fa-chevron-right text-info"></i></code></label>
					    <div class="col-sm-8">
	                      <select id="tool-web-version" name="tool-web-version" class="form-select" style="width: 100%;"></select>
					    </div>
	                  </div>
	                </div>
	                <div class="col-md-4 text-center">
	                  <div class="form-group">
	                    <label class="col-sm-2 text-center"><code>路径 <i class="fa fa-chevron-right text-info"></i></code></label>
					    <div class="col-sm-10">
	                      <input id="tool-web-path" name="tool-web-path" class="form-control" style="width: 100%;" readonly="readonly">
					    </div>
	                  </div>
	                </div>
	                <div class="col-md-2 text-center">
	                  <div class="form-group">
	                    <label class="col-sm-6 text-center"><code>服务器项目 <i class="fa fa-chevron-right text-info"></i></code></label>
					    <div class="col-sm-6">
	                      <select id="tool-server-project" name="tool-server-project" class="form-select" style="width: 100%;"></select>
					    </div>
	                  </div>
	                </div>
	                <div class="col-md-2 text-center">
	                  <button type="button" class="btn btn-success waves-effect waves-light m-r-20" id="tool-web-run" onclick="toolWebRun();">部署</button>
	                  <button type="button" class="btn btn-primary waves-effect waves-light m-r-20" id="tool-web-up" onclick="toolWebUp();">上一层</button>
	                  <button type="button" class="btn btn-primary waves-effect waves-light m-r-20" id="tool-web-down" onclick="toolWebDown();">下一层</button>
	                </div>
	              </div>
	            </form>
	          </div>
            </div>
          </div>
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
		initServerProject();
	});
	
	function initEvent(){
		/* hideMsgDiv(); */
		initToolWebVersion(null, null);
		$("#tool-web-project").change(function(){
			initToolWebVersion($(this).val(), null);
		});
		$("#tool-web-version").change(function(){
			var path = $('#tool-web-path').val();
			if(path == null || path.trim() == ""){
				showMsgDiv("获取当前路径为空！");
			}else{
				$('#tool-web-path').val(delLastElement(path) + "/" + $(this).val());
			}
		});
	}
	
	function initToolWebVersion(project,version){
		$("#tool-web-up").prop("disabled", true);
		project = (project == null || project.trim() == "") ? "ball" : project;
		$.ajax({
    		type:"get",
    		url:"<%=request.getContextPath()%>/tool/web/project=" + project + "/versions",
    		success:function(data){
    			if(data.responseCode == "0000"){
    				var optionstring = "";
    				var valstr = "";
    				var list = data.data;
    				if(list != null){
    					for(var i = 0; i < list.length; i++){
        					if(version == list[i] || i == 0){
        						valstr = list[i];
        						optionstring += "<option value='" + list[i] + "' selected>" + list[i] + "</option>";
        					}else{
    	    					optionstring += "<option value='" + list[i] + "'>" + list[i] + "</option>";
        					}
        				}
    				}
    				$('#tool-web-version').empty();
    				$('#tool-web-version').append(optionstring);
    				$('#tool-web-path').val(project.replace(/\*/g, "/") + "/" + valstr);
    				if(project.indexOf("\*") > 0){
    					$("#tool-web-up").prop("disabled", false);
    				}else{
    					$("#tool-web-up").prop("disabled", true);
    				}
    				if(valstr == ""){
    					$("#tool-web-down").prop("disabled", true);
    				}else{
    					$("#tool-web-down").prop("disabled", false);
    				}
    			}else{
    				$('#tool-web-version').empty();
    				$('#tool-web-path').val("");
    				showMsgDiv(data.responseMsg);
    			}
    		}
    	});
	}
	
	function toolWebUp(){
		var path = $('#tool-web-path').val();
		if(path == null || path.trim() == ""){
			showMsgDiv("获取当前路径为空！");
		}else{
			var newPath1 = delLastElement(path);
			var newPath2 = delLastElement(newPath1);
			if(newPath2 != null && newPath2.indexOf("\/") > 0){
				initToolWebVersion(newPath2.replace(/\//g, "*"), null);
			}else{
				initToolWebVersion(newPath2.replace(/\//g, "*"), newPath1.split("\/")[1]);
			}
		}
	}
	
	function toolWebDown(){
		var path = $('#tool-web-path').val();
		if(path == null || path.trim() == ""){
			showMsgDiv("获取当前路径为空！");
		}else{
			initToolWebVersion(path.replace(/\//g, "*"), null);
		}
	}
	
	function delLastElement(text){
		if(text != null && text.indexOf("\/") > 0){
			var arr = text.split("\/");
			arr.pop();
			text = arr.join("\/");
		}
		return text;
	}
	
	function initServerProject(){
		$.ajax({
    		type:"get",
    		url:"<%=request.getContextPath()%>/tool/web/server/projects",
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
    				$('#tool-server-project').empty();
    				$('#tool-server-project').append(optionstring);
    			}else{
    				$('#tool-server-project').empty();
    				showMsgDiv(data.responseMsg);
    			}
    		}
    	});
	}
	
</script>
<!--Style Switcher -->
<script src="${pageContext.request.contextPath}/plugins/bower_components/styleswitcher/jQuery.style.switcher.js"></script>
</body>
</html>
