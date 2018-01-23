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
<title>压力测试-持久连接</title>
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
          <h4 class="page-title"><i class="fa fa-pagelines m-r-10" style='color:green'></i><span><b style='color:black'>工具(压力测试-持久连接)</b></span></h4>
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
	            <form id="tool-pressure-form" action="${pageContext.request.contextPath}/tool/pressure/run" method="post" enctype="multipart/form-data">
	              <div class="row">
	                <div class="col-md-2 text-center">
	                  <div class="form-group">
	                    <label class="col-sm-4 text-center"><code>压测环境 <i class="fa fa-chevron-right text-info"></i></code></label>
					    <div class="col-sm-8">
	                      <select id="tool-pressure-platform" name="tool-pressure-platform" class="form-select" style="width: 80%;">
                        	<option value='1' selected="selected">测试环境</option>
                        	<!-- <option value='3'>其他环境</option> -->
                          </select>
					    </div>
	                  </div>
	                </div>
	                <div class="col-md-2 text-center">
	                  <div class="form-group">
	                    <label class="col-sm-4 text-center"><code>压测项目 <i class="fa fa-chevron-right text-info"></i></code></label>
					    <div class="col-sm-8">
	                      <select id="tool-pressure-project" name="tool-pressure-project" class="form-select" style="width:90%;"></select>
					    </div>
	                  </div>
	                </div>
	                <div class="col-md-5">
	              	  <div class="fileinput fileinput-new input-group" data-provides="fileinput">
		                <span class="input-group-addon btn btn-pressurening btn-file"> <span class="fileinput-new">选择TOKEN文件</span> <span class="fileinput-exists">重选</span>
		                <input type="file" name="file">
		                </span> <a href="#" class="input-group-addon btn btn-default fileinput-exists" data-dismiss="fileinput">移除</a>
		                <div class="form-control" data-trigger="fileinput"> <i class="glyphicon glyphicon-file fileinput-exists"></i> <span class="fileinput-filename"></span></div>
		              </div>
	                </div>
	                <div class="col-md-3 text-center">
	                  <button type="button" class="btn btn-success waves-effect waves-light m-r-20" id="tool-pressure-run" onclick="toolPressureRun();">运行</button>
	                  <button type="button" class="btn btn-danger waves-effect waves-light m-r-20" id="tool-pressure-stop" onclick="toolPressureStop();">停止</button>
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
                <textarea id="tool-pressure-resultlog" class="form-control" rows="25" readonly="readonly" autofocus="autofocus"></textarea>
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
		var msg = "${msg}";
		var hdata = GetQueryStringChinese("data");
		if(hdata == "success"){
			checkPressureProjects();
			hideMsgDiv();
		}else{
			if(msg != null && msg != ""){
				checkStopPressureProjects();
				showMsgDiv(msg);
			}else{
				hideMsgDiv();
			}
		}
		initPressureProjects();
	});
	
	var myInterval;
	function checkPressureProjects(){
		myInterval = setInterval(function(){
			$.ajax({
	    		type:"get",
	    		url:"<%=request.getContextPath()%>/tool/pressure/check",
	    		success:function(data){
	    			if(data.responseCode == "0000"){
	    				if(data.data.check > 0){
	    					$('#tool-pressure-resultlog').val("");
		    				$('#tool-pressure-resultlog').val("在线人数：" + data.data.check);
	    				}else{
	    					checkStopPressureProjects();
	    				}
	    			}else{
	    				showMsgDiv(data.responseMsg);
	    			}
	    		}
	    	});
		}, 2 * 1000);
	}
	
	function checkStopPressureProjects(){
		clearInterval(myInterval);
		$('#tool-pressure-resultlog').val("");
	}
	
	function initPressureProjects(){
		$.ajax({
    		type:"get",
    		url:"<%=request.getContextPath()%>/tool/pressure/projects",
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
    				$('#tool-pressure-project').empty();
    				$('#tool-pressure-project').append(optionstring);
    			}else{
    				$('#tool-pressure-project').empty();
    				showMsgDiv(data.responseMsg);
    			}
    		}
    	});
	}
	
	function toolPressureRun(){
		var project = $('#tool-pressure-project').val();
		var filename = $('.fileinput-filename').html();
		if(project == null || project.trim() == ""){
			showMsgDiv("请选择压测项目！");
		}else if(filename == null || filename == ""){
			showMsgDiv("请选择TOKEN文件！");
		}else if(!checkFiles(filename)){
			showMsgDiv("选择文件不合法，文件的扩展名必须为.txt！");
		}else{
			checkPressureProjects();
			$('#tool-pressure-form').submit();
		}
	}
	
	function checkFiles(str){
		var strRegex = "(.txt)$";
		var re = new RegExp(strRegex);
		if (re.test(str.toLowerCase())){
			return true;
		}else{
			return false;
		}
	}
	
	function toolPressureStop(){
		$.ajax({
    		type:"get",
    		url:"<%=request.getContextPath()%>/tool/pressure/stop",
    		success:function(data){
    			if(data.responseCode == "0000"){
    				checkStopPressureProjects();
    				swal("成功", "全部停止！", "success");
    			}else{
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
