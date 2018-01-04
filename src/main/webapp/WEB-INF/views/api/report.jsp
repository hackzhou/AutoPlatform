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
<title>接口-报告</title>
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
<!-- Daterange picker plugins css -->
<link href="${pageContext.request.contextPath}/plugins/bower_components/timepicker/bootstrap-timepicker.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/plugins/bower_components/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">
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
        <div class="col-lg-1 col-md-4 col-sm-4 col-xs-12">
          <h4 class="page-title"><i class="fa fa-pagelines m-r-10" style='color:green'></i><span><b style='color:black'>报告</b></span></h4>
        </div>
        <div class="col-lg-2 col-md-4 col-sm-4 col-xs-12">
          <label class="col-md-3">项目：</label>
          <select id="api-report-project-s" name="api-report-project-s" class="col-md-9"></select>
        </div>
        <div class="col-lg-2 col-md-4 col-sm-4 col-xs-12">
          <label class="col-md-3">版本：</label>
          <select id="api-report-version-s" name="api-report-version-s" class="col-md-9"></select>
        </div>
        <div class="col-lg-2 col-md-4 col-sm-4 col-xs-12">
          <label class="col-md-3">时间：</label>
          <input type="text" id="api-report-time-s" class="col-md-9 input-daterange-timepicker" name="daterange" readonly="readonly"/>
        </div>
      </div>
      <!-- /row -->
      <div class="row">
        <div class="col-sm-12">
          <div class="white-box">
			<!-- /.table -->
            <div class="table-responsive">
            <table id="api-report-table" class="table display">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>项目</th>
                  <th>版本</th>
                  <th><b class='label label-info'>对比</b></th>
                  <th><b class='label label-success'>运行</b></th>
                  <th><b class='label label-inverse'>名称</b></th>
                  <th><b class='label label-warning'>状态</b></th>
                  <th>运行时长</th>
                  <th><b class='label label-success'>成功</b>/<b class='label label-red'>失败</b>/<b class='label label-info'>总数</b></th>
                  <th><b class='label label-danger'>成功率</b></th>
                  <th>运行人</th>
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
<!-- Plugin JavaScript -->
<script src="${pageContext.request.contextPath}/plugins/bower_components/moment/moment.js"></script>
<!-- Date range Plugin JavaScript -->
<script src="${pageContext.request.contextPath}/plugins/bower_components/timepicker/bootstrap-timepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
<!-- start - This is for export functionality only -->
<script src="${pageContext.request.contextPath}/js/cdn/dataTables.buttons.min.js"></script>
<script src="${pageContext.request.contextPath}/js/cdn/buttons.flash.min.js"></script>
<script src="${pageContext.request.contextPath}/js/cdn/jszip.min.js"></script>
<script src="${pageContext.request.contextPath}/js/cdn/pdfmake.min.js"></script>
<script src="${pageContext.request.contextPath}/js/cdn/vfs_fonts.js"></script>
<script src="${pageContext.request.contextPath}/js/cdn/buttons.html5.min.js"></script>
<script src="${pageContext.request.contextPath}/js/cdn/buttons.print.min.js"></script>
<!-- end - This is for export functionality only -->
<script type="text/javascript">

	$('.input-daterange-timepicker').daterangepicker({
		buttonClasses: ['btn', 'btn-sm'],
        applyClass: 'btn-danger',
        cancelClass: 'btn-inverse'
	});
  
	$(document).ready(function() {
		var d1 = new Date();
		var d2 = new Date(d1 - 7 * 24 * 3600 * 1000);
		var d = (d2.getMonth() + 1) + "/" + d2.getDate() + "/" + d2.getFullYear() + " - " + (d1.getMonth() + 1) + "/" + d1.getDate() + "/" + d1.getFullYear();
		$('#api-report-time-s').val(d);
		createTable(0,0,getApiReportTime(d));
		initEvent();
		initApiReportProjectSearch();
		initApiReportVersionSearch(null);
	});
	
	function initEvent() {
		$("#api-report-project-s").change(function(){
			if($(this).val() == "0"){
				initApiReportVersionSearch(null);
			}else{
				initApiReportVersionSearch($(this).val());
			}
			createTable($(this).val(),0,getApiReportTime(null));
		});
		$("#api-report-version-s").change(function(){
			createTable($("#api-report-project-s").val(),$(this).val(),getApiReportTime(null));
		});
		$("#api-report-time-s").change(function(){
			createTable($("#api-report-project-s").val(),$("#api-report-version-s").val(),getApiReportTime(null));
		});
	}
	
	function getApiReportTime(date) {
		if(date == null){
			return $("#api-report-time-s").val().replace(/ - /g, ",").replace(/\//g, "-");
		}else{
			return date.replace(/ - /g, ",").replace(/\//g, "-");
		}
	}
	
	function createTable(pid,vid,time) {
		$('#api-report-table').dataTable().fnDestroy();
    	$('#api-report-table').DataTable({
    		responsive : false,
    		sAjaxSource : "<%=request.getContextPath()%>/api/report/list/data/pid=" + pid + "/vid=" + vid + "/time=" + time,
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
					"sWidth" : "7%",
					"aTargets" : [ 1 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.projecto == null ? "-" : data.projecto.name;
					}
				},
				{
					"sWidth" : "5%",
					"aTargets" : [ 2 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.versiono == null ? "-" : tooltipJsonByVersion(data.versiono.version, data.versiono.channel);
					}
				},
				{
					"sWidth" : "5%",
					"aTargets" : [ 3 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						if(data.compare == "0"){
							return "结果";
						}else if(data.compare == "1"){
							return "线上";
						}else{
							return "-";
						}
					}
				},
				{
					"sWidth" : "5%",
					"aTargets" : [ 4 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						if(data.platform == "1"){
							return "测试";
						}else if(data.platform == "2"){
							return "预发";
						}else if(data.platform == "3"){
							return "线上";
						}else{
							return "-";
						}
					}
				},
				{
					"sWidth" : "15%",
					"aTargets" : [ 5 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						if("COMPLETE" == data.status){
							if(data.msg == null || data.msg == ""){
								var html = "<div data-toggle='tooltip' title='{0}' data-placement='left'><a href=\"${pageContext.request.contextPath}/api/report/detail/list/id={1}\" target='_blank' data-toggle=\"tooltip\" data-original-title=\"Detail\">{2}</a></div>";
								return String.format(html, data.id, data.id, data.name);
							}else{
								var html = "<div data-toggle='tooltip' title='{0}' data-placement='left'><a href=\"#\" data-data='{1}' class='alertError'>{2}</a></div>";
								return String.format(html, data.id, data.msg, data.name);
							}
						}
						return data.name;
					}
				},
				{
					"sWidth" : "7%",
					"aTargets" : [ 6 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						if("RUNNING" == data.status){
							return "正在运行...";
						}else if("WAITING" == data.status){
							return "等待运行...";
						}else if("COMPLETE" == data.status){
							return "运行完成";
						}else {
							return "-";
						}
					}
				},
				{
					"sWidth" : "7%",
					"aTargets" : [ 7 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						if(data.endTime == null || data.endTime == ""){
							return "-";
						}
						if(data.startTime == data.endTime){
							return "<1秒";
						}
						return String.duration(new Date(data.startTime), new Date(data.endTime));
					}
				},
				{
					"sWidth" : "12%",
					"aTargets" : [ 8 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						var format = "<b class='text-success'>{0}</b>" + " / "
						+ "<b style='color:red'>{1}</b>" + " / "
						+ "<b class='text-info'>{2}</b>";
						if("COMPLETE" == data.status){
							return String.format(format, data.success, data.fail, data.total);
						}else{
							return String.format(format, "-", "-", data.total);
						}
					}
				},
				{
					"sWidth" : "7%",
					"aTargets" : [ 9 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						if("COMPLETE" == data.status){
							var per = Math.round(data.success / data.total * 10000) / 100.00;
							var html = "<b style='color:{0}'>" + per + "%</b>";
							if(data.success == data.total){
								return String.format(html, "green");
							}else{
								return String.format(html, "red");
							}
						}
						return "-";
					}
				},
				{
					"sWidth" : "7%",
					"aTargets" : [ 10 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return (data.runby == null || data.runby == "") ? "-" : (data.runby.indexOf("Auto") == 0 ? "<b style='color:aqua'>Auto</b>" + data.runby.substr(4) : data.runby);
					}
				},
				{
					"sWidth" : "10%",
					"aTargets" : [ 11 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return new Date(data.createTime).Format("yyyy-MM-dd hh:mm:ss");
					}
				},
				{
					"sWidth" : "5%",
					"aTargets" : [ 12 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						if("COMPLETE" == data.status){
							if(data.msg == null || data.msg == ""){
								var html = "<div data-toggle='tooltip' title='{0}' data-placement='left'><a href=\"${pageContext.request.contextPath}/api/report/detail/list/id={0}\" target='_blank' data-toggle=\"tooltip\" data-original-title=\"Detail\"><i class=\"fa fa-list text-inverse m-r-15\"></i></a></div>";
								return String.format(html, data.id);
							}else{
								var html = "<div data-toggle='tooltip' title='{0}' data-placement='left'><a href=\"#\" data-data='{1}' class='alertError'><i class=\"fa fa-times-circle text-danger m-r-15\"></i></a></div>";
								return String.format(html, data.id, data.msg);
							}
						}else {
							return "<a href=\"#\" data-toggle=\"tooltip\" data-original-title=\"Detail\"><i class=\"fa fa-spin fa-spinner text-success m-r-15\"></i></a>";
						}
					}
				}
    		],
    		fnDrawCallback : function() {
    			initTableEvent();
    		}
    	});
	}
	
	function initTableEvent() {
		$(".alertError").on("click", function(){
			swal("错误", $(this).data('data'), "error");
		});
	}
	
	function initApiReportProjectSearch(){
    	$.ajax({
    		type:"get",
    		url:"<%=request.getContextPath()%>/api/project/list/data",
    		success:function(data){
    			if(data.responseCode == "0000"){
    				var optionstring = "<option value='0' selected>全部</option>";
    				var list = data.data;
    				if(list != null){
    					for(var i = list.length - 1; i >= 0; i--){
    						optionstring += "<option value='" + list[i].id + "'>" + list[i].name + "</option>";
        				}
    				}
    				$('#api-report-project-s').empty();
    				$('#api-report-project-s').append(optionstring);
    			}
    		}
    	});
    }
	
	function initApiReportVersionSearch(pid){
		if(pid == null || pid == ""){
			$('#api-report-version-s').empty();
			$('#api-report-version-s').append("<option value='0' selected>全部</option>");
		}else{
			$.ajax({
	    		type:"get",
	    		url:"<%=request.getContextPath()%>/api/version/list/data/pid=" + pid,
	    		success:function(data){
	    			if(data.responseCode == "0000"){
	    				var optionstring = "<option value='0' selected>全部</option>";
	    				var list = data.data;
	    				if(list != null){
	    					for(var i = 0; i < list.length; i++){
	    						optionstring += "<option value='" + list[i].id + "'>" + list[i].version + "</option>";
	        				}
	    				}
	    				$('#api-report-version-s').empty();
	    				$('#api-report-version-s').append(optionstring);
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
