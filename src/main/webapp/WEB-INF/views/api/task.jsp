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
<title>接口-定时任务</title>
<!-- Bootstrap Core CSS -->
<link href="${pageContext.request.contextPath}/eliteadmin/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/plugins/bower_components/datatables/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/cdn/buttons.dataTables.min.css" rel="stylesheet" type="text/css" />
<!-- Menu CSS -->
<link href="${pageContext.request.contextPath}/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css" rel="stylesheet">
<!-- animation CSS -->
<link href="${pageContext.request.contextPath}/eliteadmin/css/animate.css" rel="stylesheet">
<!-- Page plugins css -->
<link href="${pageContext.request.contextPath}/plugins/bower_components/clockpicker/dist/jquery-clockpicker.min.css" rel="stylesheet">
<!--alerts CSS -->
<link href="${pageContext.request.contextPath}/plugins/bower_components/sweetalert/sweetalert.css" rel="stylesheet" type="text/css">
<!-- Custom CSS -->
<link href="${pageContext.request.contextPath}/eliteadmin/css/style.css" rel="stylesheet">
<!-- color CSS -->
<link href="${pageContext.request.contextPath}/eliteadmin/css/colors/blue.css" id="theme"  rel="stylesheet">
<style>
.popover {
	z-index: 99999;
}
</style>
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
          <h4 class="page-title"><i class="fa fa-pagelines m-r-10" style='color:green'></i><span><b style='color:black'>定时任务</b></span></h4>
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
	              	<!-- /.Create Task -->
		            <div class="button-box text-right">
		              <button type="button" class="btn btn-info btn-outline" onclick="initApiTaskModal()" data-toggle="modal" data-target="#exampleModalTask" data-whatever="@fat">添加定时任务</button>
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
            <div class="modal fade" id="exampleModalTask" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabelTask">
              <div class="modal-dialog" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="exampleModalLabelTask"><label class="text-inverse" id="task-modal-lable"></label></h4>
                  </div>
                  <div class="modal-body">
                    <form id="api-task-form" class="form-horizontal form-material">
	                    <input type="hidden" id="api-task-id" name="api-task-id" value="">
	                    <input type="hidden" id="api-task-time-hide" name="api-task-time-hide" value="">
	                    <div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <label class="col-sm-3 text-info text-center"><i class="ti-star text-danger m-r-10"></i><code>是否运行 <i class="fa fa-chevron-right text-danger"></i></code></label>
                          	<div class="radio-list">
                          		<label class="radio-inline"><input type="radio" id="api-task-run1" name="api-task-run" value="1" checked>运行 </label>
                          		<label class="radio-inline"><input type="radio" id="api-task-run0" name="api-task-run" value="0">不运行 </label>
                        	</div>
	                      </div>
	                    </div>
	                    <div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <label class="col-sm-3 text-info text-center"><i class="ti-star text-danger m-r-10"></i><code>异常报告 <i class="fa fa-chevron-right text-danger"></i></code></label>
                          	<div class="radio-list">
                          		<label class="radio-inline"><input type="radio" id="api-task-mail1" name="api-task-mail" value="1">发送 </label>
                          		<label class="radio-inline"><input type="radio" id="api-task-mail0" name="api-task-mail" value="0" checked>不发送 </label>
                          		<label class="text-info">(发送报告邮箱请联系管理员配置)</label>
                        	</div>
	                      </div>
	                    </div>
	                    <div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <label class="col-sm-3 text-info text-center"><i class="ti-star text-danger m-r-10"></i><code>循环监控 <i class="fa fa-chevron-right text-danger"></i></code></label>
                          	<div class="radio-list">
                          		<label class="radio-inline"><input type="radio" id="api-task-monitor1" name="api-task-monitor" value="1">是 </label>
                          		<label class="radio-inline"><input type="radio" id="api-task-monitor0" name="api-task-monitor" value="0" checked>否 </label>
                        	</div>
	                      </div>
	                    </div>
	                    <div class="form-group task-time-div">
	                      <div class="col-md-12 m-b-20">
	                        <label class="col-sm-3 text-info text-center"><i class="ti-star text-danger m-r-10"></i><code>触发时间 <i class="fa fa-chevron-right text-danger"></i></code></label>
	                        <div class="col-sm-8 input-group clockpicker" data-placement="bottom" data-align="top" data-autoclose="true">
		                        <input type="text" id="api-task-time" name="api-task-time" class="form-control" value="00:00" readOnly>
								<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
	                        </div>
	                      </div>
	                    </div>
	                    <div class="form-group task-cycle-div" style="display: none">
	                      <div class="col-md-12 m-b-20">
	                        <label class="col-sm-3 text-info text-center"><i class="ti-star text-danger m-r-10"></i><code>触发周期 <i class="fa fa-chevron-right text-danger"></i></code></label>
	                        <div class="col-sm-9">
		                        <select id="api-task-cycle" name="api-task-cycle" class="form-select" style="width: 80%;">
		                        	<option value="1" selected="selected">一分钟</option>
		                        	<option value="3">三分钟</option>
		                        	<option value="5">五分钟</option>
		                        	<option value="10">十分钟</option>
		                        	<option value="30">半小时</option>
		                        	<option value="60">一小时</option>
		                        </select>
	                        </div>
	                      </div>
	                    </div>
	                    <div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <label class="col-sm-3 text-info text-center"><i class="ti-star text-danger m-r-10"></i><code>项目 <i class="fa fa-chevron-right text-danger"></i></code></label>
	                        <div class="col-sm-9">
		                        <select id="api-task-project" name="api-task-project" class="form-select" style="width: 80%;"></select>
	                        </div>
	                      </div>
	                    </div>
	                    <div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <label class="col-sm-3 text-info text-center"><i class="ti-star text-danger m-r-10"></i><code>版本 <i class="fa fa-chevron-right text-danger"></i></code></label>
	                        <div class="col-sm-9">
		                        <select id="api-task-version" name="api-task-version" class="form-select" style="width: 80%;"></select>
	                        </div>
	                      </div>
	                    </div>
	                    <div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <label class="col-sm-3 text-info text-center"><code>测试账号 <i class="fa fa-chevron-right text-danger"></i></code></label>
	                        <div class="col-sm-9">
		                        <select id="api-task-account" name="api-task-account" class="form-select" style="width: 80%;"></select>
	                        </div>
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
                    <button type="button" class="btn btn-primary waves-effect" onclick="apiTaskSave();">保存</button>
                    <button type="button" class="btn btn-default waves-effect" data-dismiss="modal">取消</button>
                  </div>
                </div>
              </div>
            </div>
			<!-- /.table -->
            <div class="table-responsive">
            <table id="api-task-table" class="table table-striped">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>项目</th>
                  <th>版本</th>
                  <th><b class='label label-inverse'>测试账号</b></th>
                  <th><b class='label label-success'>是否运行</b></th>
                  <th><b class='label label-info'>循环监控</b></th>
                  <th><b class='label label-red'>触发时间</b></th>
                  <th><b class='label label-primary'>发送报告</b></th>
                  <th>创建人</th>
                  <th><b class='label label-danger'>更新人</b></th>
                  <th>创建日期</th>
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
<!-- Clock Plugin JavaScript -->
<script src="${pageContext.request.contextPath}/plugins/bower_components/clockpicker/dist/jquery-clockpicker.min.js"></script>
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

	$('.clockpicker').clockpicker();

    $(document).ready(function(){
    	createTable();
    	initEvent();
    	initApiTaskProject(null);
    	initApiTaskVersion(null);
    	initApiTaskAccount(null);
    });
    
    function initEvent(){
    	$("#api-task-monitor0").change(function(){
			$(".task-time-div").show();
			$(".task-cycle-div").hide();
		});
    	$("#api-task-monitor1").change(function(){
    		$(".task-time-div").hide();
			$(".task-cycle-div").show();
		});
    }

    function createTable() {
    	$('#api-task-table').dataTable().fnDestroy();
    	$('#api-task-table').DataTable({
    		responsive : false,
    		sAjaxSource : "<%=request.getContextPath()%>/api/task/list/data",
    		bProcessing : false,
    		"aaSorting": [
    			[0,'desc']
    		],
    		aoColumnDefs : [
    			{
					"sWidth" : "8%",
					"aTargets" : [ 0 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.id;
					}
				},
				{
					"sWidth" : "8%",
					"aTargets" : [ 1 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.projecto == null ? "-" : data.projecto.name;
					}
				},
				{
					"sWidth" : "6%",
					"aTargets" : [ 2 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.versiono == null ? "-" : tooltipJsonByVersion(data.versiono.version, data.versiono.channel);
					}
				},
				{
					"sWidth" : "12%",
					"aTargets" : [ 3 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.accounto == null ? "-" : data.accounto.loginname + "/" + data.accounto.password;
					}
				},
				{
					"sWidth" : "8%",
					"aTargets" : [ 4 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						if(data.runFlag == 1){
							return "<b style='color:green'>运行</b>";
						}
						return "<b style='color:black'>不运行</b>";
					}
				},
				{
					"sWidth" : "8%",
					"aTargets" : [ 5 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						if(data.monitor == 1){
							return "<b style='color:blue'>是</b>";
						}
						return "<b style='color:black'>否</b>";
					}
				},
				{
					"sWidth" : "8%",
					"aTargets" : [ 6 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						if(data.monitor == 1){
							return data.runTime == null ? "-" : "<b style='color:red'>" + data.runTime + "分钟</b>";
						}
						return data.runTime == null ? "-" : "<b style='color:red'>" + data.runTime + "</b>";
					}
				},
				{
					"sWidth" : "8%",
					"aTargets" : [ 7 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						if(data.mail == 1){
							return "<b style='color:purple'>发送</b>";
						}
						return "<b style='color:black'>不发送</b>";
					}
				},
				{
					"sWidth" : "8%",
					"aTargets" : [ 8 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.createby == null ? "-" : data.createby;
					}
				},
				{
					"sWidth" : "8%",
					"aTargets" : [ 9 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.runby == null ? "-" : data.runby;
					}
				},
				{
					"sWidth" : "10%",
					"aTargets" : [ 10 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return new Date(data.createTime).Format("yyyy-MM-dd hh:mm:ss");
					}
				},
				{
					"sWidth" : "8%",
					"aTargets" : [ 11 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						var html = "<a href=\"#\" data-id='{0}' data-data='{1}' class='apiTaskEdit'><i class=\"fa fa-pencil text-inverse m-r-15\" data-toggle=\"modal\" data-target=\"#exampleModalTask\"></i></a>"
							 + "<a href=\"#\" data-id='{0}' class='apiTaskDel'><i class=\"fa fa-close text-danger\"></i></a>";
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
		$(".apiTaskEdit").on("click", function(){
			$('#task-modal-lable').html("定时任务-编辑");
			hideMsgDiv();
			var t = $(this).data('data');
			$('#api-task-id').val(t.id);
			$('#api-task-run' + t.runFlag).prop("checked", true);
			$('#api-task-mail' + t.mail).prop("checked", true);
			$('#api-task-monitor' + t.monitor).prop("checked", true);
			if(t.monitor == 1){
				$('#api-task-cycle').val(t.runTime);
				$(".task-cycle-div").show();
				$(".task-time-div").hide();
			}else{
				$('#api-task-time').val(t.runTime);
				$(".task-time-div").show();
				$(".task-cycle-div").hide();
			}
			initApiTaskProject(t.projecto.id);
	    	initApiTaskVersion(t.versiono.id);
	    	if(t.accounto == null){
	    		initApiTaskAccount(null);
	    	}else{
	    		initApiTaskAccount(t.accounto.id);
	    	}
		});
		
		$(".apiTaskDel").on("click", function(){
			var tid = $(this).data('id');
			apiTaskDel(tid);
		});
	}
    
    function initApiTaskModal(){
    	$('#task-modal-lable').html("定时任务-添加");
    	$('#api-task-id').val("");
    	$('#api-task-time').val("00:00");
    	$('#api-task-cycle').val("1");
    	$('#api-task-run1').prop("checked", true);
    	$('#api-task-mail0').prop("checked", true);
    	$('#api-task-monitor0').prop("checked", true);
    	$(".task-time-div").show();
		$(".task-cycle-div").hide();
    	initApiTaskProject(null);
    	initApiTaskVersion(null);
    	initApiTaskAccount(null);
    	hideMsgDiv();
    }
    
    function apiTaskSave(){
    	var tmonitor = $("input[name='api-task-monitor']:checked").val();
    	var ttime = $('#api-task-time').val();
    	var tcycle = $('#api-task-cycle').val();
    	var tproject = $('#api-task-project').val();
    	var tversion = $('#api-task-version').val();
    	var taccount = $('#api-task-account').val();
    	if(tmonitor == 0 && (ttime == null || ttime.trim() == "")){
	    	showMsgDiv("请输入触发时间！");
    	}else if(tmonitor == 1 && (tcycle == null || tcycle.trim() == "")){
    		showMsgDiv("请输入触发周期！");
    	}else if(tproject == null || tproject.trim() == ""){
	    	showMsgDiv("请选择项目！");
    	}else if(tversion == null || tversion.trim() == ""){
	    	showMsgDiv("请选择版本！");
    	}else if(taccount == null || taccount.trim() == ""){
	    	showMsgDiv("请选择测试账号！");
    	}else{
    		if(tmonitor == 1){
    			$('#api-task-time-hide').val(tcycle);
    		}else{
    			$('#api-task-time-hide').val(ttime);
    		}
    		$.ajax({
    			type:"post",
          		url:"<%=request.getContextPath()%>/api/task/repeat",
          		data:$('#api-task-form').serialize(),
          		success:function(data){
          	    	if(data.responseCode == "0000"){
          	    		hideMsgDiv();
          	    		$('#exampleModalTask').modal('hide');
          	    		$.ajax({
          	    			type:"post",
          	          		url:"<%=request.getContextPath()%>/api/task/create/update",
          	          		data:$('#api-task-form').serialize(),
          	          		success:function(data){
          	          			if(data.responseCode == "0000"){
          	          				$('#api-task-table').dataTable()._fnAjaxUpdate();
          	          			}else{
          	          				swal("错误", data.responseMsg, "error");
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
    
    function initApiTaskProject(projectid){
    	$.ajax({
    		type:"get",
    		url:"<%=request.getContextPath()%>/api/project/list/data",
    		success:function(data){
    			if(data.responseCode == "0000"){
    				var optionstring = "";
    				var list = data.data;
    				if(list != null){
    					for(var i = list.length - 1; i >= 0; i--){
        					if(projectid == list[i].id || i == (list.length - 1)){
        						optionstring += "<option value='" + list[i].id + "' selected>" + list[i].name + "</option>";
        					}else{
    	    					optionstring += "<option value='" + list[i].id + "'>" + list[i].name + "</option>";
        					}
        				}
    				}
    				$('#api-task-project').empty();
    				$('#api-task-project').append(optionstring);
    			}
    		}
    	});
    }
    
    function initApiTaskVersion(versionid){
    	$.ajax({
    		type:"get",
    		url:"<%=request.getContextPath()%>/api/version/list/data",
    		success:function(data){
    			if(data.responseCode == "0000"){
    				var optionstring = "";
    				var list = data.data;
    				if(list != null){
    					for(var i = list.length - 1; i >= 0; i--){
        					if(versionid == list[i].id || i == (list.length - 1)){
        						optionstring += "<option value='" + list[i].id + "' selected>" + list[i].version + "</option>";
        					}else{
    	    					optionstring += "<option value='" + list[i].id + "'>" + list[i].version + "</option>";
        					}
        				}
    				}
    				$('#api-task-version').empty();
    				$('#api-task-version').append(optionstring);
    			}
    		}
    	});
    }
    
    function initApiTaskAccount(accountid){
		$.ajax({
			type:"get",
      		url:"<%=request.getContextPath()%>/api/account/list/data",
      		success:function(data){
      			if(data.responseCode == "0000"){
      				var optionstring = "";
    				var list = data.data;
    				if(list != null){
    					for(var i = list.length - 1; i >= 0; i--){
        					if(accountid == list[i].id){
        						optionstring += "<option value='" + list[i].id + "' selected>" + list[i].loginname + "/" + list[i].password + "</option>";
        					}else{
    	    					optionstring += "<option value='" + list[i].id + "'>" + list[i].loginname + "/" + list[i].password + "</option>";
        					}
        				}
    				}
    				$('#api-task-account').empty();
    				$('#api-task-account').append(optionstring + "<option value='0'>无</option>");
      			}
      	    }
		});
	}
    
    function apiTaskDel(tid){
    	swal({
			title: "你确定吗？",
			text: "删除之后无法恢复，谨慎操作！",
			type: "warning",
			showCancelButton: true,
			confirmButtonColor: "#DD6B55",
			confirmButtonText: "确定，删除！",
			cancelButtonText: "取消",
			closeOnConfirm: false
		}, function(){
			$.ajax({
				type:"get",
          		url:"<%=request.getContextPath()%>/api/task/delete/id=" + tid,
          		success:function(data){
          			if(data.responseCode == "0000"){
          	    		swal("成功", "删除成功！", "success");
          	    	}else{
          	    		swal("错误", data.responseMsg, "error");
          	    	}
          	    	$('#api-task-table').dataTable()._fnAjaxUpdate();
          	    }
			});
		});
    }
    
</script>
<!--Style Switcher -->
<script src="${pageContext.request.contextPath}/plugins/bower_components/styleswitcher/jQuery.style.switcher.js"></script>
</body>
</html>
