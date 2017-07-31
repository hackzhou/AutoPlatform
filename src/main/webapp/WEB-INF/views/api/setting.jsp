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
<title>接口-设置</title>
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
          <h4 class="page-title"><i class="fa fa-pagelines m-r-10" style='color:green'></i><span><b style='color:black'>设置</b></span></h4>
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
	            <form id="api-upload-form" class="form-horizontal" action="${pageContext.request.contextPath}/api/upload/fileUpload" method="post" enctype="multipart/form-data">
	              <div class="form-group">
	                <label class="col-sm-2 text-info text-center">接口数据批量上传（Excel）：</label>
	                <div class="col-sm-8">
	                  <div class="fileinput fileinput-new input-group" data-provides="fileinput" onclick="hideMsgDiv();">
	                    <div class="form-control" data-trigger="fileinput"> <i class="glyphicon glyphicon-file fileinput-exists"></i> <span class="fileinput-filename"></span></div>
	                    <span class="input-group-addon btn btn-default btn-file"> <span class="fileinput-new">选择文件</span> <span class="fileinput-exists">重选</span>
	                    <input type="file" name="file">
	                    </span> <a href="#" class="input-group-addon btn btn-default fileinput-exists" data-dismiss="fileinput">移除</a>
	                  </div>
	                </div>
	                <div class="col-sm-2">
		              <button type="button" class="btn btn-primary waves-effect waves-light m-r-20" onclick="apiFileUpload();">提交</button>
		              <a href="${pageContext.request.contextPath}/api/upload/fileDownload">
		              	<button type="button" class="btn btn-warning waves-effect waves-light">模板下载</button>
		              </a>
	                </div>
	              </div>
	            </form>
	          </div>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-12">
          <div class="panel panel-info">
            <div class="panel-body">
	          <div class="form-body">
	            <form id="api-copy-case" class="form-horizontal" action="${pageContext.request.contextPath}/api/setting/copy" method="post">
	              <div class="form-group">
	                <label class="col-sm-2 text-info text-center">案例复制：</label>
	                <div class="col-sm-2">
	                  <label class="col-sm-4 text-center"><code>项目 <i class="fa fa-chevron-right text-info"></i></code></label>
                      <div class="col-sm-8">
                       <select id="api-copy-project" name="api-copy-project" class="form-select" style="width: 90%;"></select>
                      </div>
	                </div>
	                <div class="col-sm-1">
	                	<label class="col-sm-12 text-center"><i class="fa fa-th-large text-success"></i></label>
	                </div>
	                <div class="col-sm-2">
	                  <label class="col-sm-4 text-center"><code>版本 <i class="fa fa-chevron-right text-info"></i></code></label>
                      <div class="col-sm-8">
                       <select id="api-copy-version-a" name="api-copy-version-a" class="form-select" style="width: 90%;"></select>
                      </div>
	                </div>
	                <div class="col-sm-1">
	                	<label class="col-sm-12 text-center"><i class="fa fa-arrow-right text-success"></i></label>
	                </div>
	                <div class="col-sm-2">
	                  <label class="col-sm-4 text-center"><code>版本 <i class="fa fa-chevron-right text-info"></i></code></label>
                      <div class="col-sm-8">
                       <select id="api-copy-version-b" name="api-copy-version-b" class="form-select" style="width: 90%;"></select>
                      </div>
	                </div>
	                <div class="col-sm-2">
		              <button type="button" class="btn btn-primary waves-effect waves-light" onclick="apiCopyCase();">复制</button>
	                </div>
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
		initEvent();
		initCopyApiCaseProject();
		initCopyApiCaseVersion();
		var msg = $('#msg').html();
		if(msg != null && msg != ""){
			showMsgDiv(msg);
		}else{
			hideMsgDiv();
		}
		var hdata = GetQueryStringChinese("data");
		if(hdata != null && hdata != ""){
			if(hdata == "success"){
				swal("成功!", "批量更新成功！", "success");
			}else if(hdata.indexOf("Error") == 0){
				showMsgDiv(hdata);
			}
		}
	});
	
	function initEvent(){
		$("#api-copy-version-a").change(function(){
			hideMsgDiv();
		});
		$("#api-copy-version-b").change(function(){
			hideMsgDiv();
		});
	}
	
	function apiCopyCase(){
		var cproject = $('#api-copy-project').val();
		var cversiona = $('#api-copy-version-a').val();
		var cversionb = $('#api-copy-version-b').val();
		if(cproject == null || cproject.trim() == ""){
			showMsgDiv("项目不能为空！");
		}else if(cversiona == null || cversiona.trim() == ""){
			showMsgDiv("版本号不能为空！");
		}else if(cversiona == cversionb){
			showMsgDiv("案例复制版本号不能相同！");
		}else{
			swal({
	    		title: "你确定吗？",
	    		text: "案例复制无法撤销（谨慎操作！）",
				type: "warning",
				showCancelButton: true,
				confirmButtonColor: "#DD6B55",
				confirmButtonText: "确定，复制！",
				cancelButtonText: "取消",
				closeOnConfirm: false
			}, function(){
				$('#api-copy-case').submit();
			});
		}
	}
	
	function initCopyApiCaseProject(){
    	$.ajax({
    		type:"get",
    		url:"<%=request.getContextPath()%>/api/project/list/data",
    		success:function(data){
    			if(data.responseCode == "0000"){
    				var optionstring = "";
    				var list = data.data;
    				for(var i = list.length - 1; i >= 0; i--){
    					if(i == (list.length - 1)){
    						optionstring += "<option value='" + list[i].id + "' selected>" + list[i].name + "</option>";
    					}else{
	    					optionstring += "<option value='" + list[i].id + "'>" + list[i].name + "</option>";
    					}
    				}
    				$('#api-copy-project').empty();
    				$('#api-copy-project').append(optionstring);
    			}
    		}
    	});
    }
	
	function initCopyApiCaseVersion(){
    	$.ajax({
    		type:"get",
    		url:"<%=request.getContextPath()%>/api/version/list/data",
    		success:function(data){
    			if(data.responseCode == "0000"){
    				var optionstring = "";
    				var list = data.data;
    				for(var i = list.length - 1; i >= 0; i--){
    					if(i == (list.length - 1)){
    						optionstring += "<option value='" + list[i].id + "' selected>" + list[i].version + "</option>";
    					}else{
	    					optionstring += "<option value='" + list[i].id + "'>" + list[i].version + "</option>";
    					}
    				}
    				$('#api-copy-version-a').empty();
    				$('#api-copy-version-a').append(optionstring);
    				$('#api-copy-version-b').empty();
    				$('#api-copy-version-b').append(optionstring);
    			}
    		}
    	});
    }
	
	function apiFileUpload(){
		var filename = $('.fileinput-filename').html();
		if(filename == ""){
			showMsgDiv("请选择文件！");
		}else{
			if(checkFiles(filename)){
				swal({
		    		title: "你确定吗？",
		    		text: "接口数据批量上传，谨慎操作！<br/>已存在的<b class=\"text-danger\">接口地址</b>则更新，否则创建！<br/>已存在的<b class=\"text-danger\">默认案例</b>则更新，否则创建！",
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
				showMsgDiv("文件格式不合法，文件的扩展名必须为.xlsx或.XLSX格式！");
			}
		}
	}
	
	function checkFiles(str){
		var strRegex = "(.xlsx|.XLSX)$";
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
