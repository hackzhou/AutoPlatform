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
<title>Project</title>
<!-- Bootstrap Core CSS -->
<link href="${pageContext.request.contextPath}/eliteadmin/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/plugins/bower_components/datatables/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />
<link href="https://cdn.datatables.net/buttons/1.2.2/css/buttons.dataTables.min.css" rel="stylesheet" type="text/css" />
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
<!-- <script src="http://www.w3schools.com/lib/w3data.js"></script> -->
</head>
<body>
<!-- Preloader -->
<div class="preloader">
  <div class="cssload-speeding-wheel"></div>
</div>
<div id="wrapper">
  <!-- Top Navigation -->
  <nav class="navbar navbar-default navbar-static-top m-b-0">
    <div class="navbar-header"> <a class="navbar-toggle hidden-sm hidden-md hidden-lg " href="javascript:void(0)" data-toggle="collapse" data-target=".navbar-collapse"><i class="ti-menu"></i></a>
      <div class="top-left-part"><a class="logo" href="${pageContext.request.contextPath}/home/page"><b><img src="${pageContext.request.contextPath}/plugins/images/eliteadmin-logo.png" alt="home" /></b><span class="hidden-xs">多多游戏测试平台</span></a></div>
      <ul class="nav navbar-top-links navbar-left hidden-xs">
        <li><a href="javascript:void(0)" class="open-close hidden-xs waves-effect waves-light"><i class="icon-arrow-left-circle ti-menu"></i></a></li>
        <li class="dropdown"> <a class="dropdown-toggle profile-pic" data-toggle="dropdown" href="#"><b class="hidden-xs">接口自动化</b> </a>
          <ul class="dropdown-menu dropdown-user animated flipInY">
            <li><a href="${pageContext.request.contextPath}/api/project/list"><i class="ti-briefcase"></i> Project</a></li>
            <li><a href="${pageContext.request.contextPath}/api/case/list"><i class="ti-write"></i> Case</a></li>
            <li><a href="${pageContext.request.contextPath}/api/report/list"><i class="ti-book"></i> Report</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="${pageContext.request.contextPath}/api/setting/list"><i class="ti-settings"></i> Setting</a></li>
          </ul>
        </li>
        <li class="dropdown"> <a class="dropdown-toggle profile-pic" data-toggle="dropdown" href="#"><b class="hidden-xs">UI自动化</b> </a>
        </li>
        <li class="dropdown"> <a class="dropdown-toggle profile-pic" data-toggle="dropdown" href="#"><b class="hidden-xs">测试工具</b> </a>
        </li>
      </ul>
      <ul class="nav navbar-top-links navbar-right pull-right">
        <li class="dropdown"> <a class="dropdown-toggle profile-pic" data-toggle="dropdown" href="#"> <img src="${pageContext.request.contextPath}/plugins/images/users/varun.jpg" alt="user-img" width="36" class="img-circle"><b class="hidden-xs">Steave</b> </a>
          <ul class="dropdown-menu dropdown-user animated flipInY">
            <li><a href="#"><i class="fa fa-power-off"></i> Logout</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </nav>
  <!-- Page Content -->
  <div id="page-wrapper">
    <div class="container-fluid">
      <div class="row bg-title">
        <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
          <!-- <h4 class="page-title">Data Table</h4> -->
        </div>
      </div>
      <!-- /row -->
      <div class="row">
        <div class="col-md-12">
          <div class="panel panel-info">
            <div class="panel-body">
	          <div class="form-body">
	            <div class="row">
	              <div class="col-md-3">
	                <div class="form-group">
					  <label class="control-label col-md-2">Name:</label>
					  <div class="col-md-9">
	                    <input type="text" class="form-control" placeholder="name">
					  </div>
	                </div>
	              </div>
	              <div class="col-md-9">
	              	<!-- /.Create Project -->
		            <div class="button-box text-right">
		              <button type="button" class="btn btn-info btn-outline" data-toggle="modal" data-target="#exampleModal" data-whatever="@fat">Add Project</button>
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
            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel1">
              <div class="modal-dialog" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="exampleModalLabel1">Add Project</h4>
                  </div>
                  <div class="modal-body">
                    <from class="form-horizontal form-material">
	                    <div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <input type="text" class="form-control" placeholder="Type name">
	                      </div>
	                      <div class="col-md-12 m-b-20">
	                        <input type="text" class="form-control" placeholder="Email">
	                      </div>
	                      <div class="col-md-12 m-b-20">
	                        <input type="text" class="form-control" placeholder="Phone">
	                      </div>
	                      <div class="col-md-12 m-b-20">
	                        <input type="text" class="form-control" placeholder="Designation">
	                      </div>
	                      <div class="col-md-12 m-b-20">
	                        <input type="text" class="form-control" placeholder="Age">
	                      </div>
	                      <div class="col-md-12 m-b-20">
	                        <input type="text" class="form-control" placeholder="Date of joining">
	                      </div>
	                      <div class="col-md-12 m-b-20">
	                        <input type="text" class="form-control" placeholder="Salary">
	                      </div>
	                      <div class="col-md-12 m-b-20">
	                        <div class="fileupload btn btn-danger btn-rounded waves-effect waves-light"><span><i class="ion-upload m-r-5"></i>Upload Contact Image</span>
	                          <input type="file" class="upload">
	                        </div>
	                      </div>
	                    </div>
	                </from>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-info waves-effect" data-dismiss="modal">Save</button>
                    <button type="button" class="btn btn-default waves-effect" data-dismiss="modal">Cancel</button>
                  </div>
                </div>
              </div>
            </div>
			<!-- /.table -->
            <div class="table-responsive">
            <table id="projectTable" class="table table-striped">
              <thead>
                <tr>
                  <th>No</th>
                  <th>Name</th>
                  <th>Create Date</th>
                  <th>Action</th>
                </tr>
              </thead>
              <tbody>
              	<c:forEach items="${data}" var="project" varStatus="indexs">
              		<tr>
	                  <td>${indexs.index + 1}</td>
	                  <td>${project.name}</td>
	                  <td><fmt:formatDate value="${project.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	                  <td class="text-nowrap">
	                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
	                  	<a href="#" data-toggle="tooltip" data-original-title="Close" id="${project.id}"> <i class="fa fa-close text-danger" id="sa-warning_${project.id}"></i> </a> 
	                  </td>
	                </tr>
              	</c:forEach>
                </tbody>
            </table>
            </div>
          </div>
        </div>
    </div>
    <!-- /.container-fluid -->
    <footer class="footer text-center"> 2016 &copy; Elite Admin brought to you by themedesigner.in </footer>
  </div>
  <!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->
<!-- jQuery -->
<script src="${pageContext.request.contextPath}/plugins/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="${pageContext.request.contextPath}/eliteadmin/bootstrap/dist/js/bootstrap.min.js"></script>
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
<script src="https://cdn.datatables.net/buttons/1.2.2/js/dataTables.buttons.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.2.2/js/buttons.flash.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js"></script>
<script src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/pdfmake.min.js"></script>
<script src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/vfs_fonts.js"></script>
<script src="https://cdn.datatables.net/buttons/1.2.2/js/buttons.html5.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.2.2/js/buttons.print.min.js"></script>
<!-- end - This is for export functionality only -->
<script>

    $(document).ready(function(){
      	$('#projectTable').DataTable();
      	
		$('[id^="sa-warning"]').click(function(){
			var did = $(this).attr("id").split("_")[1];
			swal({
				title: "Are you sure?",
				text: "You will not be able to recover this imaginary file!",
				type: "warning",
				showCancelButton: true,
				confirmButtonColor: "#DD6B55",
				confirmButtonText: "Yes, delete it!",
				closeOnConfirm: false
			}, function(){
				$.ajax({
					type:"get",
	          		url:"<%=request.getContextPath()%>/api/project/delete/id=" + did,
	          		success:function(data){
	          	    	if(data.success){
	          	    		swal("Deleted!", "Your imaginary file has been deleted.", "success");
	          	    	}
	          	    	$(location).prop('href', '<%=request.getContextPath()%>/api/project/list');
	          	    }
				});
			});
		});
    });
    
</script>
<!--Style Switcher -->
<script src="${pageContext.request.contextPath}/plugins/bower_components/styleswitcher/jQuery.style.switcher.js"></script>
</body>
</html>
