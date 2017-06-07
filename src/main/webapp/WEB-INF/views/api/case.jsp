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
<title>接口-案例</title>
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
<!-- page CSS -->
<link href="${pageContext.request.contextPath}/plugins/bower_components/custom-select/custom-select.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/plugins/bower_components/bootstrap-tagsinput/dist/bootstrap-tagsinput.css" rel="stylesheet" />
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
        </div>
      </div>
      <!-- /row -->
      <div class="row">
        <div class="col-md-12">
          <div class="panel panel-info">
            <div class="panel-body">
	          <div class="form-body">
	            <div class="row">
	              <div class="col-md-12">
	              	<!-- /.Create Case -->
		            <div class="button-box text-right">
		              <button type="button" class="btn btn-info btn-outline" onclick="initApiCaseModal()" data-toggle="modal" data-target="#exampleModal5" data-whatever="@fat">添加案例</button>
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
            <div class="modal fade" id="exampleModal5" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel5">
              <div class="modal-dialog" role="document" style="width: 1000px;">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="exampleModalLabel5">添加/更新 案例</h4>
                  </div>
                  <div class="modal-body">
                    <form id="api-case-form" class="form-horizontal form-material">
                        <input type="hidden" id="api-case-id" name="api-case-id" value="">
                        <div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <label class="col-sm-3 text-info text-center"><i class="ti-star text-danger m-r-10"></i><code>是否运行 <i class="fa fa-chevron-right text-danger"></i></code></label>
                          	<div class="radio-list">
                          		<label class="radio-inline"><input type="radio" id="api-case-run1" name="api-case-run" value="1" checked>运行 </label>
                          		<label class="radio-inline"><input type="radio" id="api-case-run0" name="api-case-run" value="0">不运行 </label>
                        	</div>
	                      </div>
	                    </div>
	                    <div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <label class="col-sm-3 text-info text-center"><i class="ti-star text-danger m-r-10"></i><code>版本 <i class="fa fa-chevron-right text-danger"></i></code></label>
	                        <div class="col-sm-9">
		                        <select id="api-case-version" name="api-case-version" class="form-select" style="width: 80%;"></select>
	                        </div>
	                      </div>
	                    </div>
                        <div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <label class="col-sm-3 text-info text-center"><i class="ti-star text-danger m-r-10"></i><code>接口 <i class="fa fa-chevron-right text-danger"></i></code></label>
	                        <div class="col-sm-9">
		                        <select id="api-case-interface" name="api-case-interface" class="form-control select2" style="width: 80%;"></select>
	                        </div>
	                      </div>
	                    </div>
	                    <div class="form-group">
	                      <div class="col-md-12 m-b-20">
				            <div class="input-group m-b-20"> <span class="input-group-addon">非验证点</span>
				              <input type="text" id="api-case-strategy" name="api-case-strategy" value="" data-role="tagsinput" placeholder="添加字段">
				            </div>
	                      </div>
	                    </div>
	                    <div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <input type="text" id="api-case-name" name="api-case-name" class="form-control" placeholder="案例名称">
	                        <i class="ti-star text-danger"></i>
	                      </div>
	                    </div>
	                    <div class="form-group">
	                      <div class="col-md-12 m-b-20" style="width:100%">
							<textarea autoHeight="true" name="api-case-body" id="api-case-body" style="min-height:300px;overflow:hidden;" class="form-control" placeholder="请求体"></textarea>
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
                    <button type="button" class="btn btn-primary waves-effect" onclick="apiCaseSave();">保存</button>
                    <button type="button" class="btn btn-default waves-effect" data-dismiss="modal">取消</button>
                  </div>
                </div>
              </div>
            </div>
            <!-- /.modal run -->
            <div class="modal fade" id="exampleModalRun1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabelRun1">
              <div class="modal-dialog" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="exampleModalLabelRun1">运行案例</h4>
                  </div>
                  <div class="modal-body">
                    <form id="api-case-run-form" class="form-horizontal form-material">
                    	<input type="hidden" id="api-case-run-id" name="api-case-run-id" value="">
                    	<div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <label class="col-sm-3 text-info text-center"><i class="ti-star text-danger m-r-10"></i><code>测试账号 <i class="fa fa-chevron-right text-danger"></i></code></label>
	                        <div class="col-sm-9">
		                        <select id="api-case-run-account" name="api-case-run-account" class="form-select" style="width: 80%;"></select>
	                        </div>
	                      </div>
	                    </div>
	                </form>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-primary waves-effect" onclick="apiCaseRun();" data-dismiss="modal">运行</button>
                    <button type="button" class="btn btn-default waves-effect" data-dismiss="modal">取消</button>
                  </div>
                </div>
              </div>
            </div>
			<!-- /.table -->
            <div class="table-responsive">
            <table id="api-case-table" class="table table-striped">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>项目</th>
                  <th>版本</th>
                  <th>地址</th>
                  <th>名称</th>
                  <th>请求体</th>
                  <th>非验证点</th>
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
<script src="${pageContext.request.contextPath}/plugins/bower_components/custom-select/custom-select.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/plugins/bower_components/datatables/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bower_components/bootstrap-tagsinput/dist/bootstrap-tagsinput.min.js"></script>
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

	function autoHeight(elem){
		elem.style.height = 'auto';
		elem.scrollTop = 0;
		elem.style.height = elem.scrollHeight + 'px';
	}
	$.fn.autoHeight = function(){
		this.each(function(){
			autoHeight(this);
			$(this).on('keyup', function(){
				autoHeight(this);
			});
		});
	}
	$('textarea[autoHeight]').autoHeight();
  
	$(document).ready(function() {
		createTable();
		initEvent();
		initApiCaseVersion(null);
		initApiCaseInterface(null);
	});
	
	function initEvent(){
		$("#api-case-interface").change(function(){
			var iid = $(this).val();
			$.ajax({
				type:"get",
	      		url:"<%=request.getContextPath()%>/api/interface/id=" + iid,
	      		success:function(data){
	      	    	if(data.responseCode == "0000"){
	      	    		var i = data.data;
	      	    		$('#api-case-name').val(i.name);
	      	    	}
	      	    }
			});
		});
	}
	
	function createTable() {
		$('#api-case-table').dataTable().fnDestroy();
   		$('#api-case-table').DataTable({
	   		responsive : false,
	   		sAjaxSource : "<%=request.getContextPath()%>/api/case/list/data",
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
						return data.interfaceo.projecto.name;
					}
				},
				{
					"sWidth" : "5%",
					"aTargets" : [ 2 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.versiono.version;
					}
				},
				{
					"sWidth" : "20%",
					"aTargets" : [ 3 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.interfaceo.url;
					}
				},
				{
					"sWidth" : "10%",
					"aTargets" : [ 4 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.name;
					}
				},
				{
					"sWidth" : "20%",
					"aTargets" : [ 5 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return tooltipJson(data.body);
					}
				},
				{
					"sWidth" : "10%",
					"aTargets" : [ 6 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.strategy;
					}
				},
				{
					"sWidth" : "10%",
					"aTargets" : [ 7 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return new Date(data.createTime).Format("yyyy-MM-dd hh:mm:ss");
					}
				},
				{
					"sWidth" : "10%",
					"aTargets" : [ 8 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						var html = "<a href=\"#\" data-id='{0}' data-data='{1}' class='apiCaseEdit'><i class=\"fa fa-pencil text-inverse m-r-10\" data-toggle=\"modal\" data-target=\"#exampleModal5\"></i></a>"
							 + "<a href=\"#\" data-id='{0}' class='apiCaseDel'><i class=\"fa fa-close text-danger m-r-10\"></i></a>";
						if(data.run == 1){
							html += "<a href=\"#\" data-id='{0}' class='initApiCaseRun'><i class=\"fa fa-toggle-right text-success\" data-toggle=\"modal\" data-target=\"#exampleModalRun1\"></i></a>";
						}
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
		$(".apiCaseEdit").on("click", function(){
			hideMsgDiv();
			var c = $(this).data('data');
			$('#api-case-id').val(c.id);
  	      	$('#api-case-name').val(c.name);
  	  		$('#api-case-body').val(jsonFormat(c.body));
  	  		$('#api-case-strategy').tagsinput('removeAll');
  	      	$('#api-case-strategy').tagsinput('add', c.strategy);
			$('#api-case-run' + c.run).prop("checked", true);
			initApiCaseVersion(c.versiono.id);
  	      	initApiCaseInterface(c.interfaceo.id);
		});
		
		$(".apiCaseDel").on("click", function(){
			var cid = $(this).data('id');
			apiCaseDel(cid);
		});
		
		$(".initApiCaseRun").on("click", function(){
			var cid = $(this).data('id');
			initApiCaseRun(cid);
		});
	}
	
	function apiCaseRun(){
		$.ajax({
			type:"post",
      		url:"<%=request.getContextPath()%>/api/case/run",
      		data:$('#api-case-run-form').serialize(),
      		success:function(data){
      			if(data.responseCode == "0000"){
      				swal({
      					title: "成功!",
      					text: "运行案例成功.",
      					imageUrl: "${pageContext.request.contextPath}/plugins/images/thumbs-up.jpg"
      				});
      			}else{
      				swal("错误", data.responseMsg, "error");
      			}
      	    }
		});
	}
	
	function initApiCaseRun(cid){
		$('#api-case-run-id').val(cid);
		$.ajax({
			type:"get",
      		url:"<%=request.getContextPath()%>/api/account/list/data",
      		success:function(data){
      			if(data.responseCode == "0000"){
      				var optionstring = "<option value='0'>无</option>";
    				var list = data.data;
    				for(var i = 0; i < list.length; i++){
    					optionstring += "<option value='" + list[i].id + "'>" + list[i].loginname + "/" + list[i].password + "</option>";
    				}
    				$('#api-case-run-account').empty();
    				$('#api-case-run-account').append(optionstring);
      			}
      	    }
		});
	}
	
	function initApiCaseVersion(versionid){
    	$.ajax({
    		type:"get",
    		url:"<%=request.getContextPath()%>/api/version/list/data",
    		success:function(data){
    			if(data.responseCode == "0000"){
    				var optionstring = "";
    				var list = data.data;
    				for(var i = 0; i < list.length; i++){
    					if(versionid == list[i].id || i == 0){
    						optionstring += "<option value='" + list[i].id + "' selected>" + list[i].version + "</option>";
    					}else{
	    					optionstring += "<option value='" + list[i].id + "'>" + list[i].version + "</option>";
    					}
    				}
    				$('#api-case-version').empty();
    				$('#api-case-version').append(optionstring);
    			}
    		}
    	});
    }
	
	function initApiCaseInterface(interfaceid){
    	$.ajax({
    		type:"get",
    		url:"<%=request.getContextPath()%>/api/interface/list/data",
    		success:function(data){
    			if(data.responseCode == "0000"){
    				var optionstring = "<optgroup label=\"请选择...\">";
    				var selected = "";
    				var list = data.data;
    				for(var i = 0; i < list.length; i++){
    					if(interfaceid == list[i].id || i == 0){
    						selected = "<option value='" + list[i].id + "'>[" + list[i].type +  "] " + list[i].url + "</option>";
    						if(interfaceid == null){
	    						$('#api-case-name').val(list[i].name);
    						}
    					}
    					optionstring += "<option value='" + list[i].id + "'>[" + list[i].type +  "] " + list[i].url + "</option>";
    				}
    				optionstring += "</optgroup>";
    				$('#api-case-interface').empty();
    				$('#api-case-interface').append(selected + optionstring);
    				$('#api-case-interface').select2();
    			}
    		}
    	});
    }
	
	function initApiCaseModal(){
		$('#api-case-id').val("");
		$('#api-case-name').val("");
		$('#api-case-body').val("");
		$('#api-case-run1').prop("checked",true);
    	$('#api-case-strategy').tagsinput('removeAll');
    	initApiCaseVersion(null);
    	initApiCaseInterface(null);
    	autoHeight($("#api-case-body")[0]);
    	hideMsgDiv();
    }
	
	function apiCaseSave(){
    	if($('#api-case-name').val().trim() == ""){
	    	showMsgDiv("请输入案例名称！");
    	}else{
    		if($('#api-case-body').val().trim() == ""){
    			apiCaseCreateUpdate();
    		}else{
    			$.ajax({
        			type:"post",
              		url:"<%=request.getContextPath()%>/api/case/is/json",
              		data:$('#api-case-form').serialize(),
              		success:function(data){
              			if(data.responseCode == "0000"){
              				apiCaseCreateUpdate();
              			}else{
              				showMsgDiv("您输入的JSON格式不正确！");
              			}
              	    }
        		});
    		}
    	}
    }
	
	function apiCaseCreateUpdate(){
		hideMsgDiv();
		$('#exampleModal5').modal('hide');
		$.ajax({
			type:"post",
      		url:"<%=request.getContextPath()%>/api/case/create/update",
      		data:$('#api-case-form').serialize(),
      		success:function(data){
      			if(data.responseCode == "0000"){
      				$('#api-case-table').dataTable()._fnAjaxUpdate();
      			}else{
      	    		swal("错误!", data.responseMsg, "error");
      	    	}
      	    }
		});
	}
	
	function apiCaseDel(cid){
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
          		url:"<%=request.getContextPath()%>/api/case/delete/id=" + cid,
          		success:function(data){
          	    	if(data.responseCode == "0000"){
          	    		swal("成功", "删除成功！", "success");
          	    	}else{
          	    		swal("错误", data.responseMsg, "error");
          	    	}
          	    	$('#api-case-table').dataTable()._fnAjaxUpdate();
          	    }
			});
		});
    }
	
</script>
<!--Style Switcher -->
<script src="${pageContext.request.contextPath}/plugins/bower_components/styleswitcher/jQuery.style.switcher.js"></script>
</body>
</html>
