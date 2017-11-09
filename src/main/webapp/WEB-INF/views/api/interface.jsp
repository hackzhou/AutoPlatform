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
<title>接口-接口</title>
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
          <h4 class="page-title"><i class="fa fa-pagelines m-r-10" style='color:green'></i><span><b style='color:black'>接口</b></span></h4>
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
					  <label class="control-label text-center col-md-2">项目：</label>
					  <div class="col-md-10">
	                    <select id="api-interface-project-s" name="api-interface-project-s" class="form-select" style="width: 80%;"></select>
					  </div>
	                </div>
	              </div>
	              <div class="col-md-9">
	              	<!-- /.Create Interface -->
		            <div class="button-box text-right">
		              <button type="button" class="btn btn-info btn-outline" onclick="initApiInterfaceModal()" data-toggle="modal" data-target="#exampleModalInterface" data-whatever="@fat">添加接口</button>
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
            <div class="modal fade" id="exampleModalInterface" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabelInterface">
              <div class="modal-dialog" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="exampleModalLabelInterface"><label class="text-inverse" id="interface-modal-lable"></label></h4>
                  </div>
                  <div class="modal-body">
                    <form id="api-interface-form" class="form-horizontal form-material">
                    	<div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <label class="col-sm-3 text-info text-center"><i class="ti-star text-danger m-r-10"></i><code>项目 <i class="fa fa-chevron-right text-danger"></i></code></label>
	                        <div class="col-sm-9">
		                        <select id="api-interface-project" name="api-interface-project" class="form-select" style="width: 80%;"></select>
	                        </div>
	                      </div>
	                    </div>
	                    <div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <label class="col-sm-3 text-info text-center"><i class="ti-star text-danger m-r-10"></i><code>类型 <i class="fa fa-chevron-right text-danger"></i></code></label>
	                        <div class="col-sm-9">
		                        <select id="api-interface-type" name="api-interface-type" class="form-select" style="width: 80%;">
		                        	<option value="GET" selected="selected">GET</option>
		                        	<option value="POST">POST</option>
		                        	<option value="PUT">PUT</option>
		                        	<option value="DELETE">DELETE</option>
		                        </select>
	                        </div>
	                      </div>
	                    </div>
	                    <div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <input type="hidden" id="api-interface-id" name="api-interface-id" value="">
	                        <input type="text" id="api-interface-name" name="api-interface-name" class="form-control" placeholder="接口名称">
	                        <i class="ti-star text-danger"></i>
	                      </div>
	                    </div>
	                    <div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <input type="text" id="api-interface-url" name="api-interface-url" class="form-control" placeholder="接口地址">
	                        <i class="ti-star text-danger m-r-10"></i><label class="text-info">(格式：/xxx/yyy)</label>
	                      </div>
	                    </div>
	                    <div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <input type="text" id="api-interface-description" name="api-interface-description" class="form-control" placeholder="接口描述">
	                        <label class="text-info">(如果填写api_platform，则运行时项目地址为/api_platform)</label>
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
                    <button type="button" class="btn btn-primary waves-effect" onclick="apiInterfaceSave();">保存</button>
                    <button type="button" class="btn btn-default waves-effect" data-dismiss="modal">取消</button>
                  </div>
                </div>
              </div>
            </div>
			<!-- /.table -->
            <div class="table-responsive">
            <table id="api-interface-table" class="table display">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>项目</th>
                  <th><b class='label label-inverse'>名称</b></th>
                  <th><b class='label label-warning'>类型</b></th>
                  <th><b class='label label-danger'>地址</b></th>
                  <th>描述</th>
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
    	createTable(null);
    	initEvent();
    	initApiInterfaceProject(null);
    	initApiInterfaceProjectSearch();
    });
    
    function initEvent(){
    	$("#api-interface-project-s").change(function(){
    		createTable($(this).val());
		});
    }

    function createTable(pid) {
    	$('#api-interface-table').dataTable().fnDestroy();
    	$('#api-interface-table').DataTable({
    		responsive : false,
    		sAjaxSource : "<%=request.getContextPath()%>/api/interface/list/data/pid=" + pid,
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
					"sWidth" : "10%",
					"aTargets" : [ 1 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.projecto == null ? "-" : data.projecto.name;
					}
				},
				{
					"sWidth" : "15%",
					"aTargets" : [ 2 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.name;
					}
				},
				{
					"sWidth" : "5%",
					"aTargets" : [ 3 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.type;
					}
				},
				{
					"sWidth" : "30%",
					"aTargets" : [ 4 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						var html = "<a href=\"#\" data-id='{0}' data-data='{1}' class='apiInterfaceEdit'><b data-toggle=\"modal\" data-target=\"#exampleModalInterface\">{2}</b></a>";
						return String.format(html, data.id, JSON.stringify(data), data.url);
					}
				},
				{
					"sWidth" : "10%",
					"aTargets" : [ 5 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return (data.description == null || data.description == "") ? "-" : data.description;
					}
				},
				{
					"sWidth" : "10%",
					"aTargets" : [ 6 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return new Date(data.createTime).Format("yyyy-MM-dd hh:mm:ss");
					}
				},
				{
					"sWidth" : "10%",
					"aTargets" : [ 7 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						var html = "<a href=\"#\" data-id='{0}' data-data='{1}' class='apiInterfaceEdit'> <i class=\"fa fa-pencil text-inverse m-r-15\" data-toggle=\"modal\" data-target=\"#exampleModalInterface\"></i></a>"
							 + "<a href=\"#\" data-id='{0}' class='apiInterfaceDel'><i class=\"fa fa-close text-danger\"></i></a>";
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
		$(".apiInterfaceEdit").on("click", function(){
			$('#interface-modal-lable').html("接口-编辑");
			hideMsgDiv();
			var i = $(this).data('data');
			$('#api-interface-id').val(i.id);
      		$('#api-interface-name').val(i.name);
      		$('#api-interface-url').val(i.url);
      		$('#api-interface-description').val(i.description);
      		$('#api-interface-type').val(i.type);
      		initApiInterfaceProject(i.projecto.id);
		});
		
		$(".apiInterfaceDel").on("click", function(){
			var iid = $(this).data('id');
			apiInterfaceDel(iid);
		});
	}
    
    function initApiInterfaceModal(){
    	$('#interface-modal-lable').html("接口-添加");
    	$('#api-interface-id').val("");
    	$('#api-interface-name').val("");
    	$('#api-interface-url').val("");
    	$('#api-interface-description').val("");
    	initApiInterfaceProject(null);
    	$('#api-interface-type').val("GET");
    	hideMsgDiv();
    }
    
    function initApiInterfaceProjectSearch(){
    	$.ajax({
    		type:"get",
    		url:"<%=request.getContextPath()%>/api/project/list/data",
    		success:function(data){
    			if(data.responseCode == "0000"){
    				var optionstring = "";
    				var list = data.data;
    				if(list != null){
    					for(var i = list.length - 1; i >= 0; i--){
        					if(i == (list.length - 1)){
        						optionstring += "<option value='" + list[i].id + "' selected>" + list[i].name + "</option>";
        					}else{
    	    					optionstring += "<option value='" + list[i].id + "'>" + list[i].name + "</option>";
        					}
        				}
    				}
    				$('#api-interface-project-s').empty();
    				$('#api-interface-project-s').append(optionstring);
    			}
    		}
    	});
    }
    
    function initApiInterfaceProject(projectid){
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
    				$('#api-interface-project').empty();
    				$('#api-interface-project').append(optionstring);
    			}
    		}
    	});
    }
    
    function apiInterfaceSave(){
    	var iproject = $('#api-interface-project').val();
    	var itype = $('#api-interface-type').val();
    	var iname = $('#api-interface-name').val();
    	var iurl = $('#api-interface-url').val();
    	if(iproject == null || iproject.trim() == ""){
	    	showMsgDiv("请选择接口项目！");
    	}else if(itype == null || itype.trim() == ""){
	    	showMsgDiv("请选择接口类型！");
    	}else if(iname == null || iname.trim() == ""){
	    	showMsgDiv("请输入接口名称！");
    	}else if(iurl == null || iurl.trim() == ""){
	    	showMsgDiv("请输入接口地址！");
    	}else if(iurl[0] != "/" || iurl[iurl.length-1] == "/"){
    		showMsgDiv("请输入合法的接口地址！");
    	}else{
    		$.ajax({
    			type:"post",
          		url:"<%=request.getContextPath()%>/api/interface/repeat",
          		data:$('#api-interface-form').serialize(),
          		success:function(data){
          	    	if(data.responseCode == "0000"){
          	    		hideMsgDiv();
          	    		$('#exampleModalInterface').modal('hide');
          	    		$.ajax({
          	    			type:"post",
          	          		url:"<%=request.getContextPath()%>/api/interface/create/update",
          	          		data:$('#api-interface-form').serialize(),
          	          		success:function(data){
          	          			if(data.responseCode == "0000"){
          	          				$('#api-interface-table').dataTable()._fnAjaxUpdate();
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
    
    function apiInterfaceDel(iid){
    	swal({
    		title: "你确定吗？",
			text: "删除之后无法恢复，谨慎操作！<br/>该接口下[<b class=\"text-danger\">案例</b>]也将被删除！",
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
          		url:"<%=request.getContextPath()%>/api/interface/delete/id=" + iid,
          		success:function(data){
          			if(data.responseCode == "0000"){
          	    		swal("成功", "删除成功！", "success");
          	    	}else{
          	    		swal("错误", data.responseMsg, "error");
          	    	}
          	    	$('#api-interface-table').dataTable()._fnAjaxUpdate();
          	    }
			});
		});
    }
    
</script>
<!--Style Switcher -->
<script src="${pageContext.request.contextPath}/plugins/bower_components/styleswitcher/jQuery.style.switcher.js"></script>
</body>
</html>
