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
	  <!-- .row -->
      <div class="row">
        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
          <div class="panel panel-default">
            <div class="panel-heading">【前端】</div>
            <div class="panel-wrapper collapse in">
              <div class="panel-body">
                <!-- /.table -->
                <table id="tool-status-table" border="1" style="width: 100%;height: 100%;" >
                <thead>
                  <tr>
                    <th style="text-align:center;"><b>项目</b></th>
                    <th style="text-align:center;"><b>子项目</b></th>
                    <th style="text-align:center;"><b>更新人</b></th>
                    <th style="text-align:center;"><b>项目状态</b></th>
                  </tr>
                </thead>
                <tbody>
                <c:forEach items="${data2}" var="item">
                  <tr id="tr-${item.id}" style="background: ${item.status=='测试中'?'#FFD700':'#90EE90'}">
                  	<c:if test="${not empty item.memo}">
                  	  <td rowspan="${item.memo}" style="vertical-align:middle;background:#87CEEB"><b>${item.root}</b></td>
                  	</c:if>
                  	<td><b>${item.name}</b></td>
                  	<td><b>${item.operator}</b></td>
                  	<td><button id="btn-${item.id}" onclick="updateStatus('${item.id}')">${item.status}</button></td>
                  </tr>
                </c:forEach>
                </tbody>
                </table>
              </div>
              <div class="panel-footer"> 【前端】 </div>
            </div>
          </div>
        </div>
        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
          <div class="panel panel-default">
            <div class="panel-heading">【后端】</div>
            <div class="panel-wrapper collapse in">
              <div class="panel-body">
                <!-- /.table -->
                <table id="tool-status-table" border="1" style="width: 100%;height: 100%;" >
                <thead>
                  <tr>
                    <th style="text-align:center;"><b>项目</b></th>
                    <th style="text-align:center;"><b>子项目</b></th>
                    <th style="text-align:center;"><b>更新人</b></th>
                    <th style="text-align:center;"><b>项目状态</b></th>
                  </tr>
                </thead>
                <tbody>
                <c:forEach items="${data}" var="item">
                  <tr id="tr-${item.id}" style="background: ${item.status=='测试中'?'#FFD700':'#90EE90'}">
                  	<c:if test="${not empty item.memo}">
                  	  <td rowspan="${item.memo}" style="vertical-align:middle;background:#87CEEB"><b>${item.root}</b></td>
                  	</c:if>
                  	<td><b>${item.name}</b></td>
                  	<td><b id="operator-${item.id}" >${item.operator}</b></td>
                  	<td><button id="btn-${item.id}" onclick="updateStatus('${item.id}')">${item.status}</button></td>
                  </tr>
                </c:forEach>
                </tbody>
                </table>
              </div>
              <div class="panel-footer"> 【后端】 </div>
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
	
	function updateStatus(id){
		$.ajax({
			type:"get",
      		url:"<%=request.getContextPath()%>/tool/status/update/id=" + id,
      		success:function(data){
      			if(data.responseCode == "0000"){
      				if("测试中" == data.data.status){
	      				$('#tr-' + id).css("background-color", "#FFD700");
      					$('#btn-' + id).text("测试中");
      					$('#btn-' + id).text("测试完成");
      					$('#operator-' + id).html(data.data.operator);
      				}else{
      					$('#tr-' + id).css("background-color", "#90EE90");
	      				$('#btn-' + id).text("测试完成");
	      				$('#operator-' + id).html(data.data.operator);
      				}
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
