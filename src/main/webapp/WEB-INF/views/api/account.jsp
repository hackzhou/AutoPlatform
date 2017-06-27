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
<title>接口-账号</title>
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
          <h4 class="page-title"><i class="fa fa-pagelines m-r-10" style='color:green'></i><span><b style='color:black'>测试账号</b></span></h4>
        </div>
      </div>
      <!-- /row -->
      <div class="row">
        <div class="col-md-12">
          <div class="panel panel-info">
            <div class="panel-body">
	          <div class="form-body">
	            <div class="row">
	              <!-- <div class="col-md-3">
	                <div class="form-group">
					  <label class="control-label col-md-2">Name:</label>
					  <div class="col-md-9">
	                    <input type="text" class="form-control" placeholder="name">
					  </div>
	                </div>
	              </div> -->
	              <div class="col-md-12">
	              	<!-- /.Create Account -->
		            <div class="button-box text-right">
		              <button type="button" class="btn btn-info btn-outline" onclick="initApiAccountModal()" data-toggle="modal" data-target="#exampleModal4" data-whatever="@fat">添加账号</button>
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
            <div class="modal fade" id="exampleModal4" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel4">
              <div class="modal-dialog" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="exampleModalLabel4"><label class="text-inverse" id="account-modal-lable"></label></h4>
                  </div>
                  <div class="modal-body">
                    <form id="api-account-form" class="form-horizontal form-material">
	                    <div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <input type="hidden" id="api-account-id" name="api-account-id" value="">
	                        <input type="text" id="api-account-loginname" name="api-account-loginname" class="form-control" placeholder="账号名称">
	                        <i class="ti-star text-danger m-r-10"></i><label class="text-info">(通常会使用同一个库，所以使用同一个账号测试！)</label>
	                      </div>
	                    </div>
	                    <div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <input type="text" id="api-account-password" name="api-account-password" class="form-control" placeholder="账号密码">
	                        <i class="ti-star text-danger"></i>
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
                    <button type="button" class="btn btn-primary waves-effect" onclick="apiAccountSave();">保存</button>
                    <button type="button" class="btn btn-default waves-effect" data-dismiss="modal">取消</button>
                  </div>
                </div>
              </div>
            </div>
			<!-- /.table -->
            <div class="table-responsive">
            <table id="api-account-table" class="table table-striped">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>测试账号</th>
                  <th>账号密码</th>
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
    	$('#api-account-table').dataTable().fnDestroy();
    	$('#api-account-table').DataTable({
    		responsive : false,
    		sAjaxSource : "<%=request.getContextPath()%>/api/account/list/data",
    		bProcessing : false,
    		"aaSorting": [
    			[0,'desc']
    		],
    		aoColumnDefs : [
    			{
					"sWidth" : "15%",
					"aTargets" : [ 0 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.id;
					}
				},
				{
					"sWidth" : "25%",
					"aTargets" : [ 1 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.loginname;
					}
				},
				{
					"sWidth" : "25%",
					"aTargets" : [ 2 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.password;
					}
				},
				{
					"sWidth" : "20%",
					"aTargets" : [ 3 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return new Date(data.createTime).Format("yyyy-MM-dd hh:mm:ss");
					}
				},
				{
					"sWidth" : "15%",
					"aTargets" : [ 4 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						var html = "<a href=\"#\" data-id='{0}' data-data='{1}' class='apiAccountEdit'><i class=\"fa fa-pencil text-inverse m-r-15\" data-toggle=\"modal\" data-target=\"#exampleModal4\"></i></a>"
							 + "<a href=\"#\" data-id='{0}' class='apiAccountDel'><i class=\"fa fa-close text-danger\"></i></a>";
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
		$(".apiAccountEdit").on("click", function(){
			$('#account-modal-lable').html("账号-编辑");
			hideMsgDiv();
			var a = $(this).data('data');
			$('#api-account-id').val(a.id);
	      	$('#api-account-loginname').val(a.loginname);
	      	$('#api-account-password').val(a.password);
		});
		
		$(".apiAccountDel").on("click", function(){
			var aid = $(this).data('id');
			apiAccountDel(aid);
		});
	}
    
    function initApiAccountModal(){
    	$('#account-modal-lable').html("账号-添加");
    	$('#api-account-id').val("");
    	$('#api-account-loginname').val("");
    	$('#api-account-password').val("");
    	hideMsgDiv();
    }
    
    function apiAccountSave(){
    	var aloginname = $('#api-account-loginname').val();
    	var apassword = $('#api-account-password').val();
    	if(aloginname == null || aloginname.trim() == ""){
	    	showMsgDiv("请输入账号名称！");
    	}else if(apassword == null || apassword.trim() == ""){
	    	showMsgDiv("请输入账号密码！");
    	}else{
    		$.ajax({
    			type:"post",
          		url:"<%=request.getContextPath()%>/api/account/repeat",
          		data:$('#api-account-form').serialize(),
          		success:function(data){
          	    	if(data.responseCode == "0000"){
          	    		hideMsgDiv();
          	    		$('#exampleModal4').modal('hide');
          	    		$.ajax({
          	    			type:"post",
          	          		url:"<%=request.getContextPath()%>/api/account/create/update",
          	          		data:$('#api-account-form').serialize(),
          	          		success:function(data){
          	          			if(data.responseCode == "0000"){
          	          				$('#api-account-table').dataTable()._fnAjaxUpdate();
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
    
    function apiAccountDel(aid){
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
          		url:"<%=request.getContextPath()%>/api/account/delete/id=" + aid,
          		success:function(data){
          			if(data.responseCode == "0000"){
          	    		swal("成功", "删除成功！", "success");
          	    	}else{
          	    		swal("错误", data.responseMsg, "error");
          	    	}
          	    	$('#api-account-table').dataTable()._fnAjaxUpdate();
          	    }
			});
		});
    }
    
</script>
<!--Style Switcher -->
<script src="${pageContext.request.contextPath}/plugins/bower_components/styleswitcher/jQuery.style.switcher.js"></script>
</body>
</html>
