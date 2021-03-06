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
<title>接口-报告详情</title>
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
<!-- diff -->
<link href="${pageContext.request.contextPath}/css/diffview.css" rel="stylesheet" type="text/css" />
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
          <h4 class="page-title"><i class="fa fa-pagelines m-r-10" style='color:green'></i><span><b style='color:black'>报告详情</b></span></h4>
        </div>
        <div class="col-lg-2 col-md-4 col-sm-4 col-xs-12">
          <label class="col-md-4">异常类型：</label>
          <select id="api-report-detail-err-s" name="api-report-detail-err-s" class="col-md-8">
          	<option value="0" selected="selected">无</option>
          	<option value="1">全部</option>
          	<option value="2">不通</option>
          	<option value="3">500</option>
          </select>
        </div>
        <div class="col-lg-2 col-md-4 col-sm-4 col-xs-12">
          <button type="button" id="api-rerun" class="btn btn-default" onclick="rerun()">失败重跑</button>
        </div>
        <div class="col-lg-2 col-md-4 col-sm-4 col-xs-12">
          <label class="col-md-6">是否包含非验证点：</label>
          <select id="api-report-detail-filter-s" name="api-report-detail-filter-s" class="col-md-6">
            <option value="0" selected="selected">全部</option>
            <option value="1">包含</option>
          </select>
        </div>
      </div>
      <!-- /.modal -->
      <div class="modal fade" id="exampleModalDetail" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabelDetail">
        <div class="modal-dialog" role="document" style="width: 100%;">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
              <h4 class="modal-title" id="exampleModalLabelDetail"><label class="text-info" id="showid"></label>结果对比<label class="text-danger" id="ignores"></label></h4>
            </div>
            <div class="modal-body">
              <form id="api-result-detail-diff-form" class="form-horizontal form-material">
              	<div class="form-group" style="display:none">
                  <div class="col-md-12 m-b-20">
					<div class="row">
					  <div class="top">
						<input type="text" id="contextSize" value="" />
					  </div>
				      <div class="col-sm-6 textInput">
						<textarea id="baseText"></textarea>
		    		  </div>
			    	  <div class="col-sm-6 textInput spacer">
						<textarea id="newText"></textarea>
                   	  </div>
               		</div>
                  </div>
                </div>
                <div class="viewType" style="display:none">
					<input type="radio" name="_viewtype" id="sidebyside" onclick="diffUsingJS(0);" /> <label for="sidebyside">Side by Side Diff</label>
					&nbsp; &nbsp;
					<input type="radio" name="_viewtype" id="inline" onclick="diffUsingJS(1);" /> <label for="inline">Inline Diff</label>
				</div>
				<div id="diffoutput"> </div>
           	  </form>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-default waves-effect" data-dismiss="modal">取消</button>
            </div>
          </div>
        </div>
      </div>
      <!-- /row -->
      <div class="row">
        <div class="col-sm-12">
          <div class="white-box">
			<!-- /.table -->
            <div class="table-responsive">
            <table id="api-report-detail-table" class="table display">
              <thead>
                <tr>
                  <th>ID</th>
                  <th><b class='label label-inverse'>名称</b></th>
                  <th><b class='label label-warning'>类型</b></th>
                  <th><b class='label label-danger'>地址</b></th>
                  <th>描述</th>
                  <th><b class='label label-inverse'>版本号</b></th>
                  <th><b class='label label-info'>渠道号</b></th>
                  <th><b class='label label-success'>测试账号</b></th>
                  <th>请求体</th>
                  <th><b class='label label-warning'>状态</b></th>
                  <th><b class='label label-primary'>响应(毫秒)</b></th>
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
<!-- diff -->
<script src="${pageContext.request.contextPath}/js/diffview.js"></script>
<script src="${pageContext.request.contextPath}/js/difflib.js"></script>
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
<script type="text/javascript">
  
	$(document).ready(function() {
		$('.top-menu-all').hide();
		$('.top-logout-user').hide();
		initEvent();
		createTable(0,0);
	});
	
	function initEvent() {
		$("#api-rerun").attr("disabled","true");
		$("#api-report-detail-err-s").change(function(){
			$("#api-report-detail-filter-s").val("0");
			$("#api-rerun").attr("disabled","true");
			createTable($(this).val(),0);
		});
		
		$("#api-report-detail-filter-s").change(function(){
			$("#api-report-detail-err-s").val("0");
			createTable(0,$(this).val());
		});
	}
	
	function createTable(index,filter) {
		$('#api-report-detail-table').dataTable().fnDestroy();
    	$('#api-report-detail-table').DataTable({
    		responsive : false,
    		sAjaxSource : "<%=request.getContextPath()%>/api/report/detail/list/data/i=" + index + "/j=" + filter + "/id=${data}",
    		bProcessing : false,
    		"aaSorting": [
    			[9,'asc']
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
					"sWidth" : "10%",
					"aTargets" : [ 1 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.name;
					}
				},
				{
					"sWidth" : "5%",
					"aTargets" : [ 2 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.type;
					}
				},
				{
					"sWidth" : "12%",
					"aTargets" : [ 3 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						if(data.msg == null || data.msg == ""){
							var html = "<div data-toggle='tooltip' title='{0}' data-placement='left'><a href=\"#\" data-id='{0}' data-data='{1}' class='initResultDetailData'><b data-toggle=\"modal\" data-target=\"#exampleModalDetail\">{2}</b></a></div>";
							return String.format(html, data.id, escape(JSON.stringify(data)), data.url);
						}else{
							var html = "<div data-toggle='tooltip' title='{0}' data-placement='left'><a href=\"#\" data-data='{1}' class='alertError'>{2}</a></div>";
							if(data.body == null || data.body == ""){
								return String.format(html, data.id, data.msg, data.url);
							}else{
								return String.format(html, data.id, data.msg + "-->[Data:" + data.body + "]", data.url);
							}
						}
						return data.url;
					}
				},
				{
					"sWidth" : "8%",
					"aTargets" : [ 4 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return (data.description == null || data.description == "") ? "-" : data.description;
					}
				},
				{
					"sWidth" : "6%",
					"aTargets" : [ 5 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.version;
					}
				},
				{
					"sWidth" : "6%",
					"aTargets" : [ 6 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.channel;
					}
				},
				{
					"sWidth" : "8%",
					"aTargets" : [ 7 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return (data.account == null || data.account == "") ? "-" : tooltipText(data.account);
					}
				},
				{
					"sWidth" : "10%",
					"aTargets" : [ 8 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						if(data.body == null || data.body == ""){
							return "-";
						}
						return tooltipJson(data.body);
					}
				},
				{
					"sWidth" : "5%",
					"aTargets" : [ 9 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						if("SUCCESS" == data.status){
							return "<b style='color:green' onclick='alertAllRequest2(" + data.id + ");'>成功</b><input type='hidden' id='api-all-request" + data.id + "' value='" + data.memo + "'>";
						}else if("FAILURE" == data.status){
							if($("#api-report-detail-err-s").val() != "0"){
								$("#api-rerun").removeAttr("disabled");
							}
							return "<b style='color:red' onclick='alertAllRequest(" + data.id + ");'>失败</b><input type='hidden' id='api-all-request" + data.id + "' value='" + data.memo + "'>";
						}else{
							return "-";
						}
					}
				},
				{
					"sWidth" : "7%",
					"aTargets" : [ 10 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						if(data.time > 300){
							return "<b style='color:red'>" + data.time + "</b>";
						}else{
							return "<b style='color:green'>" + data.time + "</b>";
						}
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
						if(data.msg == null || data.msg == ""){
							var html = "<div data-toggle='tooltip' title='{0}' data-placement='left'><a href=\"#\" data-id='{0}' data-data='{1}' class='initResultDetailData'><i class=\"fa fa-database text-primary m-r-15\" data-toggle=\"modal\" data-target=\"#exampleModalDetail\"></i></a></div>";
							return String.format(html, data.id, escape(JSON.stringify(data)));
						}else{
							var html = "<div data-toggle='tooltip' title='{0}' data-placement='left'><a href=\"#\" data-data='{1}' class='alertError'><i class=\"fa fa-times-circle text-danger m-r-15\"></i></a></div>";
							if(data.body == null || data.body == ""){
								return String.format(html, data.id, data.msg);
							}else{
								return String.format(html, data.id, data.msg + "-->[Data:" + data.body + "]");
							}
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
		$(".initResultDetailData").on("click", function(){
			var data = JSON.parse(unescape($(this).data('data')));
			$("#showid").html("[" + data.id + "->" + data.name + "]");
			$("#baseText").val(jsonFormat(data.resulta));
			$("#newText").val(jsonFormat(data.resultb));
			diffUsingJS(0);
			if(data.strategy != null && data.strategy != ""){
				$("#ignores").html("（忽略字段：" + data.strategy + "）");
			}else{
				$("#ignores").html("");
			}
		});
		
		$(".alertError").on("click", function(){
			swal("错误", $(this).data('data'), "error");
		});
	}
	
	function alertAllRequest(id) {
		var ar = $("#api-all-request" + id).val();
		swal("请求信息", (ar == null ? "" : ar.replace(/;/g, "\r\n")), "error");
	}
	
	function alertAllRequest2(id) {
		var ar = $("#api-all-request" + id).val();
		swal("请求信息", (ar == null ? "" : ar.replace(/;/g, "\r\n")), "success");
	}

	function diffUsingJS(viewType) {
		"use strict";
		var byId = function (id) { return document.getElementById(id); },
		base = difflib.stringAsLines(byId("baseText").value),
		newtxt = difflib.stringAsLines(byId("newText").value),
		sm = new difflib.SequenceMatcher(base, newtxt),
		opcodes = sm.get_opcodes(),
		diffoutputdiv = byId("diffoutput"),
		contextSize = byId("contextSize").value;
	
		diffoutputdiv.innerHTML = "";
		contextSize = contextSize || null;
	
		diffoutputdiv.appendChild(diffview.buildView({
			baseTextLines: base,
			newTextLines: newtxt,
			opcodes: opcodes,
			baseTextName: "结果(线上)数据",/* 线上数据 */
			newTextName: "测试(预发)数据",/* 预发数据 */
			contextSize: contextSize,
			viewType: viewType
		}));
	}
	
	function rerun() {
		var index = $("#api-report-detail-err-s").val();
		$.ajax({
			type:"get",
			url:"<%=request.getContextPath()%>/api/report/rerun/i=" + index + "/id=${data}",
      		success:function(data){
      			if(data.responseCode == "0000"){
      				swal({
      					title: "成功!",
      					html: true,
						text: "<a href=\"${pageContext.request.contextPath}/api/report/list\">查看报告</a>",
      					imageUrl: "${pageContext.request.contextPath}/plugins/images/thumbs-up.jpg"
      				});
      	    	}else{
      	    		swal("错误", data.responseMsg, "error");
      	    	}
      	    }
		});
	}
	
</script>
<!--Style Switcher -->
<script src="${pageContext.request.contextPath}/plugins/bower_components/styleswitcher/jQuery.style.switcher.js"></script>
</body>
</html>
