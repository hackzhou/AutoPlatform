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
<title>项目测试状态</title>
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
          <h4 class="page-title"><i class="fa fa-pagelines m-r-10" style='color:green'></i><span><b style='color:black'>工具(项目测试状态)</b></span></h4>
        </div>
      </div>
      <!-- /row -->
      <div class="row">
	  	<div class="col-sm-12">
	  	  <div class="white-box">
	  	  	<!-- /.table -->
            <div class="table-responsive">
              <table id="tool-status-table" class="table">
                <thead>
                  <tr>
                    <th>ID</th>
                    <th><b class='label label-inverse'>项目</b></th>
                    <th><b class='label label-inverse'>子项目</b></th>
                    <th><b class='label label-inverse'>项目状态</b></th>
                    <th><b class='label label-inverse'>更新人</b></th>
                    <th><b class='label label-inverse'>操作</b></th>
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
<script src="${pageContext.request.contextPath}/eliteadmin/js/jasny-bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bower_components/datatables/jquery.dataTables.min.js"></script>
<script>

	$(document).ready(function(){
		createTable();
	});
	
	function createTable() {
    	$('#tool-status-table').dataTable().fnDestroy();
    	$('#tool-status-table').DataTable({
    		responsive : false,
    		sAjaxSource : "<%=request.getContextPath()%>/tool/status/list",
    		bProcessing : false,
    		"aaSorting": [
    			[0,'asc']
    		],
    		"paging": false,
    		bLengthChange: false,
    		aoColumnDefs : [
    			{
					"sWidth" : "10%",
					"aTargets" : [ 0 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.id;
					}
				},
				{
					"sWidth" : "15%",
					"aTargets" : [ 1 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						if(data.memo % 2 == 0){
							return "<b style='color:purple'>" + data.root + "</b>";
						}else{
							return "<b style='color:coral'>" + data.root + "</b>";
						}
					}
				},
				{
					"sWidth" : "15%",
					"aTargets" : [ 2 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return "<b>" + data.name + "</b>";
					}
				},
				{
					"sWidth" : "30%",
					"aTargets" : [ 3 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						if(data.status == "测试中"){
							return "<b class='text-success'>" + data.status + "...</b>";
						}else{
							return "<b class='text-info'>" + data.status + "</b>";
						}
					}
				},
				{
					"sWidth" : "20%",
					"aTargets" : [ 4 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.operator;
					}
				},
				{
					"sWidth" : "10%",
					"aTargets" : [ 5 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return "<button class=\"btn btn-info btn-outline\" onclick=\"updateStatus('" + data.id + "')\">更新状态</button>";
					}
				}
    		],
    	});
    }
	
	function updateStatus(id){
		$.ajax({
			type:"get",
      		url:"<%=request.getContextPath()%>/tool/status/update/id=" + id,
      		success:function(data){
      			if(data.responseCode == "0000"){
      				swal("成功", "更新成功！", "success");
      	    	}else{
      	    		swal("错误", data.responseMsg, "error");
      	    	}
      			$('#tool-status-table').dataTable()._fnAjaxUpdate();
      	    }
		});
	}
	
</script>
<!--Style Switcher -->
<script src="${pageContext.request.contextPath}/plugins/bower_components/styleswitcher/jQuery.style.switcher.js"></script>
</body>
</html>
