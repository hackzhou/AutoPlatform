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
<title>UI-设备</title>
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
          <h4 class="page-title"><i class="fa fa-pagelines m-r-10" style='color:green'></i><span><b style='color:black'>设备</b></span></h4>
        </div>
      </div>
      <!-- /row -->
      <div class="row">
        <div class="col-md-12">
          <div class="panel panel-info">
            <div class="panel-body">
	          <div class="form-body">
	            <div class="row">
	              <div class="col-md-12">
	              	<!-- /.Create Device -->
		            <div class="button-box text-right">
		              <button type="button" class="btn btn-info btn-outline" onclick="initUiDeviceModal()" data-toggle="modal" data-target="#exampleModalDevice" data-whatever="@fat">添加设备</button>
		            </div>
	              </div>
	            </div>
	          </div>
            </div>
          </div>
        </div>
      </div>
      <!-- /row -->
      <div class="row">
        <div class="col-sm-12">
          <div class="white-box">
            <!-- /.modal -->
            <div class="modal fade" id="exampleModalDevice" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabelDevice">
              <div class="modal-dialog" role="document" style="width: 800px;">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="exampleModalLabelDevice"><label class="text-inverse" id="device-modal-lable"></label></h4>
                  </div>
                  <div class="modal-body">
                    <form id="ui-device-form" class="form-horizontal form-material">
                        <input type="hidden" id="ui-device-id" name="ui-device-id" value="">
                    	<div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <label class="col-sm-3 text-info text-center"><i class="ti-star text-danger m-r-10"></i><code>平台 <i class="fa fa-chevron-right text-danger"></i></code></label>
	                        <div class="col-sm-9">
		                        <select id="ui-platform-name" name="ui-platform-name" class="form-select" style="width: 80%;">
		                        	<option value="Android" selected="selected">Android</option>
		                        	<option value="iOS">iOS</option>
		                        </select>
	                        </div>
	                      </div>
	                    </div>
	                    <div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <input type="text" id="ui-platform-version" name="ui-platform-version" class="form-control" placeholder="平台版本">
	                        <i class="ti-star text-danger m-r-10"></i>
	                      </div>
	                    </div>
	                    <div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <input type="text" id="ui-nickname" name="ui-nickname" class="form-control" placeholder="别名">
	                        <i class="ti-star text-danger m-r-10"></i>
	                      </div>
	                    </div>
	                    <div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <input type="text" id="ui-device-name" name="ui-device-name" class="form-control" placeholder="设备名称">
	                        <i class="ti-star text-danger m-r-10"></i>
	                      </div>
	                    </div>
	                    <div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <input type="text" id="ui-device-udid" name="ui-device-udid" class="form-control" placeholder="UDID">
	                        <i class="ti-star text-danger m-r-10"></i>
	                      </div>
	                    </div>
	                    <div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <input type="text" id="ui-server-url" name="ui-server-url" class="form-control" placeholder="驱动服务">
	                        <i class="ti-star text-danger m-r-10"></i>
	                      </div>
	                    </div>
	                    <div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <input type="text" id="ui-device-app" name="ui-device-app" class="form-control" placeholder="App">
	                      </div>
	                    </div>
	                    <div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <div id="msgDiv" class="alert alert-danger alert-dismissable" style="display: none">
								<button type="button" class="close" aria-hidden="true">
									&times;
								</button>
								<span id="msg"></span>
							</div>
	                      </div>
	                    </div>
	                </form>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-primary waves-effect" onclick="uiDeviceSave();">保存</button>
                    <button type="button" class="btn btn-default waves-effect" data-dismiss="modal">取消</button>
                  </div>
                </div>
              </div>
            </div>
			<!-- /.table -->
            <div class="table-responsive">
            <table id="ui-device-table" class="table display">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>平台</th>
                  <th>平台版本</th>
                  <th>别名</th>
                  <th>设备名称</th>
                  <th><b style='color:red'>UDID</b></th>
                  <th>驱动服务</th>
                  <th>App</th>
                  <th>创建时间</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
              </tbody>
            </table>
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

    $(document).ready(function(){
    	createTable();
    });
    
    function createTable() {
    	$('#ui-device-table').dataTable().fnDestroy();
    	$('#ui-device-table').DataTable({
    		responsive : false,
    		sAjaxSource : "<%=request.getContextPath()%>/ui/device/list/data",
    		bProcessing : false,
    		"aaSorting": [
    			[0,'desc']
    		],
    		aoColumnDefs : [
    			{
					"sWidth" : "6%",
					"aTargets" : [ 0 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.id;
					}
				},
				{
					"sWidth" : "6%",
					"aTargets" : [ 1 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.platformName;
					}
				},
				{
					"sWidth" : "6%",
					"aTargets" : [ 2 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.platformVersion;
					}
				},
				{
					"sWidth" : "10%",
					"aTargets" : [ 3 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.nickname;
					}
				},
				{
					"sWidth" : "12%",
					"aTargets" : [ 4 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.deviceName;
					}
				},
				{
					"sWidth" : "12%",
					"aTargets" : [ 5 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.udid;
					}
				},
				{
					"sWidth" : "18%",
					"aTargets" : [ 6 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.serverUrl;
					}
				},
				{
					"sWidth" : "14%",
					"aTargets" : [ 7 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return (data.app == null || data.app == "") ? "-" : data.app;
					}
				},
				{
					"sWidth" : "10%",
					"aTargets" : [ 8 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return new Date(data.createTime).Format("yyyy-MM-dd hh:mm:ss");
					}
				},
				{
					"sWidth" : "6%",
					"aTargets" : [ 9 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						var html = "<a href=\"#\" data-id='{0}' data-data='{1}' class='uiDeviceEdit'><i class=\"fa fa-pencil text-inverse m-r-15\" data-toggle=\"modal\" data-target=\"#exampleModalDevice\"></i></a>"
							 + "<a href=\"#\" data-id='{0}' class='uiDeviceDel'><i class=\"fa fa-close text-danger\"></i></a>";
						return String.format(html, data.id, JSON.stringify(data));
					}
				}
    		],
    		fnDrawCallback : function() {
    			initTableEvent();
    		}
    	});
    }
    
    function initTableEvent() {
		$(".uiDeviceEdit").on("click", function(){
			$('#device-modal-lable').html("设备-编辑");
			hideMsgDiv();
			var d = $(this).data('data');
			$('#ui-device-id').val(d.id);
			$('#ui-platform-name').val(d.platformName);
			$('#ui-platform-version').val(d.platformVersion);
			$('#ui-nickname').val(d.nickname);
			$('#ui-device-name').val(d.deviceName);
			$('#ui-device-udid').val(d.udid);
			$('#ui-server-url').val(d.serverUrl);
			$('#ui-device-app').val(d.app);
		});
		
		$(".uiDeviceDel").on("click", function(){
			var aid = $(this).data('id');
			uiDeviceDel(aid);
		});
	}
    
    function initUiDeviceModal(){
    	$('#device-modal-lable').html("设备-添加");
    	$('#ui-device-id').val("");
		$('#ui-platform-name').val("Android");
		$('#ui-platform-version').val("");
		$('#ui-nickname').val("");
		$('#ui-device-name').val("");
		$('#ui-device-udid').val("");
		$('#ui-server-url').val("");
		$('#ui-device-app').val("");
    	hideMsgDiv();
    }
    
    function uiDeviceSave(){
    	var platformName = $('#ui-platform-name').val();
    	var platformVersion = $('#ui-platform-version').val();
    	var nickname = $('#ui-nickname').val();
    	var deviceName = $('#ui-device-name').val();
    	var deviceUdid = $('#ui-device-udid').val();
    	var serverUrl = $('#ui-server-url').val();
       	if(platformName == null || platformName.trim() == ""){
   	    	showMsgDiv("请选择平台！");
       	}else if(platformName != "Android"){
       		showMsgDiv("目前平台只支持Android！");
       	}else if(platformVersion == null || platformVersion.trim() == ""){
       		showMsgDiv("请输入平台版本！");
       	}else if(nickname == null || nickname.trim() == ""){
       		showMsgDiv("请输入别名！");
       	}else if(deviceName == null || deviceName.trim() == ""){
       		showMsgDiv("请输入设备名称！");
       	}else if(deviceUdid == null || deviceUdid.trim() == ""){
       		showMsgDiv("请输入设备Udid！");
       	}else if(serverUrl == null || serverUrl.trim() == ""){
       		showMsgDiv("请输入驱动服务！");
       	}else{
       		$.ajax({
       			type:"post",
       			url:"<%=request.getContextPath()%>/ui/device/repeat",
       			data:$('#ui-device-form').serialize(),
             		success:function(data){
             			if(data.responseCode == "0000"){
             				hideMsgDiv();
             	    		$('#exampleModalDevice').modal('hide');
             	    		$.ajax({
             	    			type:"post",
             	          		url:"<%=request.getContextPath()%>/ui/device/create/update",
             	          		data:$('#ui-device-form').serialize(),
             	          		success:function(data){
             	          			if(data.responseCode == "0000"){
             	          				$('#ui-device-table').dataTable()._fnAjaxUpdate();
             	          			}else{
             	          	    		swal("错误!", data.responseMsg, "error");
             	          	    	}
             	          	    }
             	    		});
             			}else{
             	    		showMsgDiv(data.responseMsg);
             	    	}
             	    }
       		});
       	}
	}
    
    function uiDeviceDel(did){
    	swal({
			title: "你确定吗？",
			text: "删除之后无法恢复，谨慎操作！",
			html: true,
			type: "warning",
			showCancelButton: true,
			confirmButtonColor: "#DD6B55",
			confirmButtonText: "确定，删除！",
			cancelButtonText: "取消",
			closeOnConfirm: false
		}, function(){
			$.ajax({
				type:"get",
          		url:"<%=request.getContextPath()%>/ui/device/delete/id=" + did,
          		success:function(data){
          			if(data.responseCode == "0000"){
          	    		swal("成功", "删除成功！", "success");
          	    	}else{
          	    		swal("错误", data.responseMsg, "error");
          	    	}
          	    	$('#ui-device-table').dataTable()._fnAjaxUpdate();
          	    }
			});
		});
    }
    
</script>
<!--Style Switcher -->
<script src="${pageContext.request.contextPath}/plugins/bower_components/styleswitcher/jQuery.style.switcher.js"></script>
</body>
</html>
