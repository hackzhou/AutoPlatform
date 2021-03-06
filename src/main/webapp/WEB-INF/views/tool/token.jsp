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
<title>工具-Token</title>
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
  <!-- Navigation -->
  <jsp:include page="/WEB-INF/views/top.jsp"></jsp:include>
  <!-- Page Content -->
  <div id="page-wrapper">
    <div class="container-fluid">
      <div class="row bg-title">
        <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
          <h4 class="page-title"><i class="fa fa-pagelines m-r-10" style='color:green'></i><span><b style='color:black'>批量生成Token</b></span></h4>
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
      <div class="row">
        <div class="col-md-12">
          <div class="panel panel-info">
            <div class="panel-body">
	          <div class="form-body">
	            <form id="api-upload-form" class="form-horizontal" action="${pageContext.request.contextPath}/tool/token/fileUpload" method="post" enctype="multipart/form-data">
	              <div class="form-group">
	                <label class="col-sm-2 text-center"><code>环境 <i class="fa fa-chevron-right text-info"></i></code></label>
					    <div class="col-sm-2">
	                      <select id="tool-token-platform" name="tool-token-platform" class="form-select" style="width: 80%;">
                        	<option value='1' selected="selected">测试环境</option>
                        	<!-- <option value='3'>其他环境</option> -->
                          </select>
					    </div>
	                <label class="col-sm-2 text-info text-center">文件上传（TXT）：</label>
	                <div class="col-sm-4">
	                  <div class="fileinput fileinput-new input-group" data-provides="fileinput" onclick="hideMsgDiv();">
	                    <div class="form-control" data-trigger="fileinput"> <i class="glyphicon glyphicon-file fileinput-exists"></i> <span class="fileinput-filename"></span></div>
	                    <span class="input-group-addon btn btn-default btn-file"> <span class="fileinput-new">选择文件</span> <span class="fileinput-exists">重选</span>
	                    <input type="file" name="file">
	                    </span> <a href="#" class="input-group-addon btn btn-default fileinput-exists" data-dismiss="fileinput">移除</a>
	                  </div>
	                </div>
	                <div class="col-sm-2">
		              <button type="button" class="btn btn-primary waves-effect waves-light m-r-20" onclick="apiFileUpload();">提交</button>
		              <a href="${pageContext.request.contextPath}/tool/token/fileDownload">
		              	<button type="button" class="btn btn-warning waves-effect waves-light">结果下载</button>
		              </a>
	                </div>
	              </div>
	              <div style='text-align:center'>
	              	使用注意事项：1、TXT文件格式：<b style='color:red'>[第一行]版本号,渠道号[第二行]账号,密码[第三行]账号,密码[第四行]...</b> 
	              	2、运行完成后请点击"结果下载"进行下载(会保留最后一次运行结果)
	              </div>
	            </form>
	          </div>
            </div>
          </div>
        </div>
      </div>
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
<!-- start - This is for export functionality only -->
<script src="${pageContext.request.contextPath}/js/cdn/dataTables.buttons.min.js"></script>
<script src="${pageContext.request.contextPath}/js/cdn/buttons.flash.min.js"></script>
<script src="${pageContext.request.contextPath}/js/cdn/jszip.min.js"></script>
<script src="${pageContext.request.contextPath}/js/cdn/pdfmake.min.js"></script>
<script src="${pageContext.request.contextPath}/js/cdn/vfs_fonts.js"></script>
<script src="${pageContext.request.contextPath}/js/cdn/buttons.html5.min.js"></script>
<script src="${pageContext.request.contextPath}/js/cdn/buttons.print.min.js"></script>
<!-- end - This is for export functionality only -->
<script>

	$(document).ready(function() {
		var msg = $('#msg').html();
		if(msg != null && msg != ""){
			showMsgDiv(msg);
		}else{
			hideMsgDiv();
		}
		var hdata = GetQueryStringChinese("data");
		if(hdata != null && hdata != ""){
			if(hdata == "success"){
				swal("成功!", "成功生成！ 请点击[结果下载]", "success");
			}else if(hdata.indexOf("Error") == 0){
				showMsgDiv(hdata);
			}
		}
	});
	
	function apiFileUpload(){
		var filename = $('.fileinput-filename').html();
		if(filename == ""){
			showMsgDiv("请选择文件！");
		}else{
			if(checkFiles(filename)){
				swal({
		    		title: "你确定吗？",
		    		text: "请检查上传文件[TXT]格式！<br/>[第一行] <b class=\"text-danger\">版本号,渠道号</b><br/>[第二行] <b class=\"text-danger\">账号,密码</b><br/>[第三行] <b class=\"text-danger\">账号,密码</b><br/> ...",
		    		html: true,
					type: "warning",
					showCancelButton: true,
					confirmButtonColor: "#DD6B55",
					confirmButtonText: "确定，上传！",
					cancelButtonText: "取消",
					closeOnConfirm: false
				}, function(){
					$('#api-upload-form').submit();
				});
			}else{
				showMsgDiv("文件格式不合法，文件的扩展名必须为.txt或.TXT格式！");
			}
		}
	}
	
	function checkFiles(str){
		var strRegex = "(.txt|.TXT)$";
		var re = new RegExp(strRegex);
		if (re.test(str.toLowerCase())){
			return true;
		}else{
			return false;
		}
	}
	
</script>
<!--Style Switcher -->
<script src="${pageContext.request.contextPath}/plugins/bower_components/styleswitcher/jQuery.style.switcher.js"></script>
</body>
</html>
