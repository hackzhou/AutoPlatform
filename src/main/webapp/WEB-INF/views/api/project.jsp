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
<title>接口-项目</title>
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
          <h4 class="page-title"><i class="fa fa-pagelines m-r-10" style='color:green'></i><span><b style='color:black'>项目</b></span></h4>
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
	              	<!-- /.Create Project -->
		            <div class="button-box text-right">
		              <button type="button" class="btn btn-info btn-outline" onclick="initApiProjectModal()" data-toggle="modal" data-target="#exampleModalProject" data-whatever="@fat">添加项目</button>
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
            <div class="modal fade" id="exampleModalProject" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabelProject">
              <div class="modal-dialog" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="exampleModalLabelProject"><label class="text-inverse" id="project-modal-lable"></label></h4>
                  </div>
                  <div class="modal-body">
                    <form id="api-project-form" class="form-horizontal form-material">
                        <input type="hidden" id="api-project-id" name="api-project-id" value="">
	                    <div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <label class="col-sm-3 text-info text-center"><i class="ti-star text-danger m-r-10"></i><code>服务地址  <i class="fa fa-chevron-right text-danger"></i></code></label>
	                        <div class="col-sm-9">
		                        <select id="api-project-server" name="api-project-server" class="form-select" style="width: 80%;"></select>
	                        </div>
	                      </div>
	                    </div>
	                    <div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <input type="text" id="api-project-name" name="api-project-name" class="form-control" placeholder="项目名称">
	                        <i class="ti-star text-danger"></i>
	                      </div>
	                    </div>
	                    <div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <input type="text" id="api-project-path" name="api-project-path" class="form-control" placeholder="项目地址">
	                        <i class="ti-star text-danger m-r-10"></i>
	                        <label class="text-info">(请求地址=服务地址[IP+端口]+<b class="text-danger">项目地址</b>+接口地址)</label>
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
                    <button type="button" class="btn btn-primary waves-effect" onclick="apiProjectSave();">保存</button>
                    <button type="button" class="btn btn-default waves-effect" data-dismiss="modal">取消</button>
                  </div>
                </div>
              </div>
            </div>
            <!-- /.modal run -->
            <div class="modal fade" id="exampleModalRun2" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabelRun2">
              <div class="modal-dialog" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="exampleModalLabelRun2">项目-运行</h4>
                  </div>
                  <div class="modal-body">
                    <form id="api-project-run-form" class="form-horizontal form-material">
                    	<input type="hidden" id="api-project-run-id" name="api-project-run-id" value="">
                    	<div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <label class="col-sm-3 text-info text-center"><i class="ti-star text-danger m-r-10"></i><code>对比方式<i class="fa fa-chevron-right text-danger"></i></code></label>
                          	<div class="radio-list">
                          		<label class="radio-inline"><input type="radio" id="api-project-run-compare0" name="api-project-run-compare" value="0" checked>自定义对比</label>
                          		<label class="radio-inline"><input type="radio" id="api-project-run-compare1" name="api-project-run-compare" value="1">线上对比</label>
                        	</div>
	                      </div>
	                    </div>
                    	<div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <label class="col-sm-3 text-info text-center"><i class="ti-star text-danger m-r-10"></i><code>运行环境<i class="fa fa-chevron-right text-danger"></i></code></label>
	                        <div class="col-sm-9">
		                        <select id="api-project-run-platform" name="api-project-run-platform" class="form-select" style="width: 80%;">
		                        	<option value='1' selected="selected">测试环境</option>
		                        	<option value='2'>预发环境</option>
		                        	<option value='3'>线上(其它)环境</option>
		                        </select>
	                        </div>
	                      </div>
	                    </div>
                    	<div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <label class="col-sm-3 text-info text-center"><i class="ti-star text-danger m-r-10"></i><code>版本 <i class="fa fa-chevron-right text-danger"></i></code></label>
	                        <div class="col-sm-9">
		                        <select id="api-project-run-version" name="api-project-run-version" class="form-select" style="width: 80%;"></select>
	                        </div>
	                      </div>
	                    </div>
                    	<div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <label class="col-sm-3 text-info text-center"><i class="ti-star text-danger m-r-10"></i><code>测试账号 <i class="fa fa-chevron-right text-danger"></i></code></label>
	                        <div class="col-sm-9">
		                        <select id="api-project-run-account" name="api-project-run-account" class="form-select" style="width: 80%;"></select>
	                        </div>
	                      </div>
	                    </div>
	                </form>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-primary waves-effect" onclick="apiProjectRun();" data-dismiss="modal">运行</button>
                    <button type="button" class="btn btn-default waves-effect" data-dismiss="modal">取消</button>
                  </div>
                </div>
              </div>
            </div>
			<!-- /.table -->
            <div class="table-responsive">
            <table id="api-project-table" class="table display">
              <thead>
                <tr>
                  <th>ID</th>
                  <th><b class='label label-primary'>服务地址</b></th>
                  <th><b class='label label-danger'>项目地址</b></th>
                  <th><b class='label label-inverse'>名称</b></th>
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
    	initToolWarIP(null);
    	initEvent();
    });
    
    function initEvent() {
    	$("#api-project-run-compare0").change(function(){
    		$("#api-project-run-platform").removeAttr("disabled");
    		$("#api-project-run-platform").val("1");
		});
    	$("#api-project-run-compare1").change(function(){
    		$("#api-project-run-platform").val("2");
    		$("#api-project-run-platform").attr({"disabled":"disabled"});
		});
    }

    function createTable() {
    	$('#api-project-table').dataTable().fnDestroy();
    	$('#api-project-table').DataTable({
    		responsive : false,
    		sAjaxSource : "<%=request.getContextPath()%>/api/project/list/data",
    		bProcessing : false,
    		"aaSorting": [
    			[0,'desc']
    		],
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
					"sWidth" : "20%",
					"aTargets" : [ 1 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.server;
					}
				},
				{
					"sWidth" : "20%",
					"aTargets" : [ 2 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						var html = "<a href=\"#\" data-id='{0}' data-data='{1}' class='apiProjectEdit'><label data-toggle=\"modal\" data-target=\"#exampleModalProject\">{2}</label></a>";
						return String.format(html, data.id, JSON.stringify(data), data.path);
					}
				},
				{
					"sWidth" : "20%",
					"aTargets" : [ 3 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						var html = "<a href=\"#\" data-id='{0}' class='initApiProjectRun'><b data-toggle=\"modal\" data-target=\"#exampleModalRun2\" onclick=\"initApiRunModal()\">{1}</b></a>";
						return String.format(html, data.id, data.name);
					}
				},
				{
					"sWidth" : "20%",
					"aTargets" : [ 4 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return new Date(data.createTime).Format("yyyy-MM-dd hh:mm:ss");
					}
				},
				{
					"sWidth" : "10%",
					"aTargets" : [ 5 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						var html = "<a href=\"#\" data-id='{0}' class='initApiProjectRun'><i class=\"fa fa-toggle-right text-success m-r-15\" data-toggle=\"modal\" data-target=\"#exampleModalRun2\" onclick=\"initApiRunModal()\"></i></a>"
							+ "<a href=\"#\" data-id='{0}' data-data='{1}' class='apiProjectEdit'><i class=\"fa fa-pencil text-inverse m-r-15\" data-toggle=\"modal\" data-target=\"#exampleModalProject\"></i></a>"
							+ "<a href=\"#\" data-id='{0}' class='apiProjectDel'><i class=\"fa fa-close text-danger\"></i></a>";
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
		$(".apiProjectEdit").on("click",function(){
			$('#project-modal-lable').html("项目-编辑");
			hideMsgDiv();
			var p = $(this).data('data');
			$('#api-project-id').val(p.id);
	      	$('#api-project-name').val(p.name);
	      	$('#api-project-path').val(p.path);
	      	initToolWarIP(p.server);
		});
		
		$(".apiProjectDel").on("click",function(){
			var pid = $(this).data('id');
			apiProjectDel(pid);
		});
		
		$(".initApiProjectRun").on("click",function(){
			var pid = $(this).data('id');
			initApiProjectRun(pid);
		});
	}
    
    function initApiProjectModal(){
    	$('#project-modal-lable').html("项目-添加");
    	$('#api-project-id').val("");
    	$('#api-project-name').val("");
    	$('#api-project-path').val("");
    	initToolWarIP(null);
    	hideMsgDiv();
    }
    
    function initApiRunModal(){
    	$("#api-project-run-compare0").prop("checked", true);
    	$("#api-project-run-platform").removeAttr("disabled");
		$("#api-project-run-platform").val("1");
    }
    
    function apiProjectRun(){
    	var prunversion = $('#api-project-run-version').val();
    	if(prunversion == null || prunversion.trim() == ""){
	    	swal("错误", "请选择运行版本！", "error");
    	}else {
    		$("#api-project-run-platform").removeAttr("disabled");
    		$.ajax({
    			type:"post",
          		url:"<%=request.getContextPath()%>/api/project/run",
          		data:$('#api-project-run-form').serialize(),
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
	}
    
    function initApiProjectRun(pid){
		$('#api-project-run-id').val(pid);
		initApiCaseVersion(pid,null);
		$.ajax({
			type:"get",
      		url:"<%=request.getContextPath()%>/api/account/list/data",
      		success:function(data){
      			if(data.responseCode == "0000"){
      				var optionstring = "";
    				var list = data.data;
    				if(list != null){
    					for(var i = list.length - 1; i >= 0; i--){
        					optionstring += "<option value='" + list[i].id + "'>" + list[i].loginname + "/" + list[i].password + "</option>";
        				}
    				}
    				$('#api-project-run-account').empty();
    				$('#api-project-run-account').append(optionstring + "<option value='0'>无</option>");
      			}
      	    }
		});
	}
    
    function initApiCaseVersion(pid,versionid){
    	$.ajax({
    		type:"get",
    		url:"<%=request.getContextPath()%>/api/version/list/data/pid=" + pid,
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
    				$('#api-project-run-version').empty();
    				$('#api-project-run-version').append(optionstring);
    			}
    		}
    	});
    }
    
    function initToolWarIP(ip){
		$.ajax({
    		type:"get",
    		url:"<%=request.getContextPath()%>/api/project/ips",
    		success:function(data){
    			if(data.responseCode == "0000"){
    				var optionstring = "";
    				var list = data.data;
    				if(list != null){
    					for(var i = 0; i < list.length; i++){
        					if(ip == list[i] || i == 0){
        						optionstring += "<option value='" + list[i] + "' selected>" + list[i] + "</option>";
        					}else{
    	    					optionstring += "<option value='" + list[i] + "'>" + list[i] + "</option>";
        					}
        				}
    				}
    				$('#api-project-server').empty();
    				$('#api-project-server').append(optionstring);
    			}
    		}
    	});
	}
    
    function apiProjectSave(){
    	var pname = $('#api-project-name').val();
    	var ppath = $('#api-project-path').val();
    	var pserver = $('#api-project-server').val();
    	if(pname == null || pname.trim() == ""){
	    	showMsgDiv("请输入项目名称！");
    	}else if(pserver == null || pserver.trim() == ""){
    		showMsgDiv("请选择服务器IP！");
    	}else if(ppath == null || ppath.trim() == ""){
    		showMsgDiv("请输入项目地址！");
    	}else if(ppath[0] != "/" || ppath[ppath.length-1] == "/"){
    		showMsgDiv("请输入合法的项目地址！");
    	}else{
    		$.ajax({
    			type:"post",
    			url:"<%=request.getContextPath()%>/api/project/repeat",
    			data:$('#api-project-form').serialize(),
          		success:function(data){
          			if(data.responseCode == "0000"){
          				hideMsgDiv();
          	    		$('#exampleModalProject').modal('hide');
          	    		$.ajax({
          	    			type:"post",
          	          		url:"<%=request.getContextPath()%>/api/project/create/update",
          	          		data:$('#api-project-form').serialize(),
          	          		success:function(data){
          	          			if(data.responseCode == "0000"){
          	          				$('#api-project-table').dataTable()._fnAjaxUpdate();
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
    
    function apiProjectDel(pid){
    	swal({
    		title: "你确定吗？",
			text: "删除之后无法恢复，谨慎操作！<br/>该项目下[<b class=\"text-danger\">接口/案例/定时任务</b>]也将被删除！",
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
          		url:"<%=request.getContextPath()%>/api/project/delete/id=" + pid,
          		success:function(data){
          			if(data.responseCode == "0000"){
          	    		swal("成功", "删除成功！", "success");
          	    	}else{
          	    		swal("错误", data.responseMsg, "error");
          	    	}
          	    	$('#api-project-table').dataTable()._fnAjaxUpdate();
          	    }
			});
		});
    }
</script>
<!--Style Switcher -->
<script src="${pageContext.request.contextPath}/plugins/bower_components/styleswitcher/jQuery.style.switcher.js"></script>
</body>
</html>
