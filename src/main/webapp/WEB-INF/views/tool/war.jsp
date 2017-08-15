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
	            <div class="row">
	              <div class="col-md-2 text-center">
	                <div class="form-group">
	                  <label class="col-sm-4 text-center"><code>服务器 <i class="fa fa-chevron-right text-info"></i></code></label>
					  <div class="col-sm-8">
	                    <select id="tool-war-ip" name="tool-war-ip" class="form-select" style="width: 90%;">
			              <option value="192.168.101.181" selected="selected">192.168.101.181</option>
			              <option value="192.168.101.182">192.168.101.182</option>
					    </select>
					  </div>
	                </div>
	              </div>
	              <div class="col-md-2 text-center">
	                <div class="form-group">
	                  <label class="col-sm-4 text-center"><code>类型 <i class="fa fa-chevron-right text-info"></i></code></label>
					  <div class="col-sm-8">
	                    <select id="tool-war-type" name="tool-war-type" class="form-select" style="width: 90%;">
			              <option value="admin" selected="selected">admin</option>
			              <option value="app">app</option>
			              <option value="inventory">inventory</option>
						  <option value="mall">mall</option>
						  <option value="platform">platform</option>
						  <option value="platms">platms</option>
						  <option value="quoits">quoits</option>
						  <option value="report">report</option>
						  <option value="uic">uic</option>
						  <option value="wars">wars</option>
						  <option value="ring">ring</option>
					    </select>
					  </div>
	                </div>
	              </div>
	              <div class="col-md-6">
	              	<div class="fileinput fileinput-new input-group" data-provides="fileinput">
		              <span class="input-group-addon btn btn-warning btn-file"> <span class="fileinput-new">选择war包</span> <span class="fileinput-exists">重选</span>
		              <input type="file" name="file">
		              </span> <a href="#" class="input-group-addon btn btn-default fileinput-exists" data-dismiss="fileinput">移除</a>
		              <div class="form-control" data-trigger="fileinput"> <i class="glyphicon glyphicon-file fileinput-exists"></i> <span class="fileinput-filename"></span></div>
		            </div>
	              </div>
	              <div class="col-md-2 text-center">
	                <button type="button" class="btn btn-success waves-effect waves-light m-r-20" id="tool-war-run" onclick="toolWarRun();">部署</button>
	              </div>
	            </div>
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
		
	});
	
	function toolWarRun(){
		showMsgDiv("XXXX");
	}

</script>
<!--Style Switcher -->
<script src="${pageContext.request.contextPath}/plugins/bower_components/styleswitcher/jQuery.style.switcher.js"></script>
</body>
</html>
