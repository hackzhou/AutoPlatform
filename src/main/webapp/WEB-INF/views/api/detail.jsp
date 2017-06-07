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
        <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
        </div>
      </div>
      <!-- /.modal -->
      <div class="modal fade" id="exampleModal6" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel6">
        <div class="modal-dialog" role="document" style="width: 100%;">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
              <h4 class="modal-title" id="exampleModalLabel6">结果对比<label class="text-danger" id="ignores"></label></h4>
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
            <table id="api-report-detail-table" class="table table-striped">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>名称</th>
                  <th>类型</th>
                  <th>地址</th>
                  <th>描述</th>
                  <th>版本号</th>
                  <th>渠道号</th>
                  <th>测试账号</th>
                  <th>请求体</th>
                  <th>状态</th>
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
		createTable();
	});
	
	function createTable() {
		$('#api-report-detail-table').dataTable().fnDestroy();
    	$('#api-report-detail-table').DataTable({
    		responsive : false,
    		sAjaxSource : "<%=request.getContextPath()%>/api/report/detail/list/data/id=${data}",
    		bProcessing : false,
    		"aaSorting": [
    			[0,'desc']
    		],
    		aoColumnDefs : [
    			{
					"sWidth" : "5%",
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
					"sWidth" : "10%",
					"aTargets" : [ 3 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.url;
					}
				},
				{
					"sWidth" : "10%",
					"aTargets" : [ 4 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.description;
					}
				},
				{
					"sWidth" : "10%",
					"aTargets" : [ 5 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.version;
					}
				},
				{
					"sWidth" : "10%",
					"aTargets" : [ 6 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.channel;
					}
				},
				{
					"sWidth" : "10%",
					"aTargets" : [ 7 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.account;
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
							return "<b style='color:green'>成功</b>";
						}else if("FAILURE" == data.status){
							return "<b style='color:red'>失败</b>";
						}else{
							return "-";
						}
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
					"sWidth" : "5%",
					"aTargets" : [ 11 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						var html = "<a href=\"#\" data-id='{0}' data-data='{1}' class='initResultDetailData'><i class=\"fa fa-database text-inverse m-r-10\" data-toggle=\"modal\" data-target=\"#exampleModal6\"></i></a>";
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
		$(".initResultDetailData").on("click", function(){
			var data = $(this).data('data');
			$("#baseText").val(jsonFormat(data.resulta));
			$("#newText").val(jsonFormat(data.resultb));
			diffUsingJS(0);
			if(data.strategy != null && data.strategy != ""){
				$("#ignores").html("（忽略字段：" + data.strategy + "）");
			}
		});
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
			baseTextName: "线上数据",
			newTextName: "预发数据",
			contextSize: contextSize,
			viewType: viewType
		}));
	}
	
</script>
<!--Style Switcher -->
<script src="${pageContext.request.contextPath}/plugins/bower_components/styleswitcher/jQuery.style.switcher.js"></script>
</body>
</html>
