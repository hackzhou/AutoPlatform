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
<title>工具-调整时间</title>
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
          <h4 class="page-title"><i class="fa fa-pagelines m-r-10" style='color:green'></i><span><b style='color:black'>工具(调整时间)</b></span></h4>
        </div>
      </div>
      <!-- /row -->
      <div class="row">
        <div class="col-sm-12">
          <div class="white-box">
            <div class="table-responsive">
              <table id="tool-time-table" class="table table-striped">
                <thead>
                  <tr>
                    <th><b class='label label-inverse'>IP</b></th>
                    <th><b class='label label-info'>当前时间</b></th>
                    <th><b class='label label-danger'>校准(网络标准)时间</b></th>
                    <th><b class='label label-primary'>选择时间</b></th>
                    <th><b class='label label-success'>设置时间</b></th>
                  </tr>
                </thead>
              <tbody>
                <tr>
                  <td id="time-ip-181"><b>10.33.85.181</b></td>
                  <td id="time-current-192-168-101-181"></td>
                  <td><button id="time-net-192-168-101-181" type="button" class="btn btn-danger" onclick="setNetTime('10.33.85.181');" disabled="disabled">校准时间</button></td>
                  <td><input id="time-val-192-168-101-181" type="datetime-local" style="width:80%;" disabled="disabled"></td>
                  <td><button id="time-set-192-168-101-181" type="button" class="btn btn-success" onclick="setTime('10.33.85.181');" disabled="disabled">设置时间</button></td>
                </tr>
                <tr>
                  <td id="time-ip-182"><b>10.33.85.182</b></td>
                  <td id="time-current-192-168-101-182"></td>
                  <td><button id="time-net-192-168-101-182" type="button" class="btn btn-danger" onclick="setNetTime('10.33.85.182');" disabled="disabled">校准时间</button></td>
                  <td><input id="time-val-192-168-101-182" type="datetime-local" style="width:80%;" disabled="disabled"></td>
                  <td><button id="time-set-192-168-101-182" type="button" class="btn btn-success" onclick="setTime('10.33.85.182');" disabled="disabled">设置时间</button></td>
                </tr>
                <tr>
                  <td id="time-ip-184"><b>10.33.85.184</b></td>
                  <td id="time-current-192-168-101-184"></td>
                  <td><button id="time-net-192-168-101-184" type="button" class="btn btn-danger" onclick="setNetTime('10.33.85.184');" disabled="disabled">校准时间</button></td>
                  <td><input id="time-val-192-168-101-184" type="datetime-local" style="width:80%;" disabled="disabled"></td>
                  <td><button id="time-set-192-168-101-184" type="button" class="btn btn-success" onclick="setTime('10.33.85.184');" disabled="disabled">设置时间</button></td>
                </tr>
                <tr>
                  <td id="time-ip-192"><b>10.33.85.192</b></td>
                  <td id="time-current-192-168-101-192"></td>
                  <td><button id="time-net-192-168-101-192" type="button" class="btn btn-danger" onclick="setNetTime('10.33.85.192');" disabled="disabled">校准时间</button></td>
                  <td><input id="time-val-192-168-101-192" type="datetime-local" style="width:80%;" disabled="disabled"></td>
                  <td><button id="time-set-192-168-101-192" type="button" class="btn btn-success" onclick="setTime('10.33.85.192');" disabled="disabled">设置时间</button></td>
                </tr>
                <tr>
                  <td id="time-ip-194"><b>10.33.85.194</b></td>
                  <td id="time-current-192-168-101-194"></td>
                  <td><button id="time-net-192-168-101-194" type="button" class="btn btn-danger" onclick="setNetTime('10.33.85.194');" disabled="disabled">校准时间</button></td>
                  <td><input id="time-val-192-168-101-194" type="datetime-local" style="width:80%;" disabled="disabled"></td>
                  <td><button id="time-set-192-168-101-194" type="button" class="btn btn-success" onclick="setTime('10.33.85.194');" disabled="disabled">设置时间</button></td>
                </tr>
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
		getCurrentTimes();
		/* setInterval(function(){
			getCurrentTimes();
		}, 10 * 1000); */
	});
	
	function getCurrentTimes(){
    	$.ajax({
			type:"get",
      		url:"<%=request.getContextPath()%>/tool/time/get",
      		success:function(data){
      	    	if(data.responseCode == "0000"){
					var arr = data.data.split(";");
					for (var i = 0; i < arr.length - 1; i++) {
						var ip = arr[i].split(",")[0].replace(/\./g, "-");
						var time = arr[i].split(",")[1];
						$('#time-current-' + ip).html(time);
						$('#time-net-' + ip).removeAttr("disabled");
						$('#time-val-' + ip).removeAttr("disabled");
						$('#time-set-' + ip).removeAttr("disabled");
					}
      	    	}else{
      	    		swal("错误", data.responseMsg, "error");
      	    	}
      	    }
		});
    }
	
	function setNetTime(ip){
		$.ajax({
			type:"get",
      		url:"<%=request.getContextPath()%>/tool/time/set/ip=" + ip + "/time=0",
      		success:function(data){
      	    	if(data.responseCode == "0000"){
      	    		swal("成功", "【" + ip + "】设置成功！", "success");
      	    		window.location.reload();
      	    	}else{
      	    		swal("错误", "【" + ip + "】" + data.responseMsg, "error");
      	    	}
      	    }
		});
	}
	
	function setTime(ip){
		var time = $('#time-val-' + ip.replace(/\./g, "-")).val();
		if(time == null || time.trim() == ""){
			swal("错误", "【" + ip + "】请完整填写要设置的时间！", "error");
		}else{
			$.ajax({
				type:"get",
	      		url:"<%=request.getContextPath()%>/tool/time/set/ip=" + ip + "/time=" + time,
	      		success:function(data){
	      	    	if(data.responseCode == "0000"){
	      	    		swal("成功", "【" + ip + "】设置成功！", "success");
	      	    		window.location.reload();
	      	    	}else{
	      	    		swal("错误", "【" + ip + "】" + data.responseMsg, "error");
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
