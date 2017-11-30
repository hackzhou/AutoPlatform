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
<link href="${pageContext.request.contextPath}/plugins/bower_components/dropify/dist/css/dropify.min.css" rel="stylesheet" >
<!-- animation CSS -->
<link href="${pageContext.request.contextPath}/eliteadmin/css/animate.css" rel="stylesheet">
<!--alerts CSS -->
<link href="${pageContext.request.contextPath}/plugins/bower_components/sweetalert/sweetalert.css" rel="stylesheet" type="text/css">
<!-- page CSS -->
<link href="${pageContext.request.contextPath}/plugins/bower_components/custom-select/custom-select.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/plugins/bower_components/multiselect/css/multi-select.css" rel="stylesheet" type="text/css" />
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
          <h4 class="page-title"><i class="fa fa-pagelines m-r-10" style='color:green'></i><span><b style='color:black'>案例</b></span></h4>
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
	                    <select id="api-case-project-s" name="api-case-project-s" class="form-select" style="width: 80%;"></select>
					  </div>
	                </div>
	              </div>
	              <div class="col-md-3">
	                <div class="form-group">
					  <label class="control-label text-center col-md-2">版本：</label>
					  <div class="col-md-10">
	                    <select id="api-case-version-s" name="api-case-version-s" class="form-select" style="width: 80%;"></select>
					  </div>
	                </div>
	              </div>
	              <div class="col-md-6">
	              	<!-- /.Create Case -->
		            <div class="button-box text-right">
		              <button type="button" class="btn btn-info btn-outline" onclick="initApiCaseModal()" data-toggle="modal" data-target="#exampleModalCase" data-whatever="@fat">添加案例</button>
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
            <div class="modal fade" id="exampleModalCase" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabelCase">
              <div class="modal-dialog" role="document" style="width: 1000px;">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="exampleModalLabelCase"><label class="text-inverse" id="case-modal-lable"></label></h4>
                  </div>
                  <div class="modal-body">
                    <form id="api-case-form" class="form-horizontal form-material">
                        <input type="hidden" id="api-case-id" name="api-case-id" value="">
                        <input type="hidden" id="api-case-img-path" name="api-case-img-path" value="">
                        <input type="hidden" id="api-case-update-img" name="api-case-update-img" value="0">
                        <div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <label class="col-sm-3 text-info text-center"><i class="ti-star text-danger m-r-10"></i><code>是否默认 <i class="fa fa-chevron-right text-danger"></i></code></label>
                          	<div class="radio-list">
                          		<label class="radio-inline"><input type="radio" id="api-case-flag0" name="api-case-flag" value="0" checked>否</label>
                          		<label class="radio-inline"><input type="radio" id="api-case-flag1" name="api-case-flag" value="1">是  <b class="text-info">(批量导入时创建或者覆盖)</b></label>
                        	</div>
	                      </div>
	                    </div>
                        <div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <label class="col-sm-3 text-info text-center"><i class="ti-star text-danger m-r-10"></i><code>是否运行 <i class="fa fa-chevron-right text-danger"></i></code></label>
                          	<div class="radio-list">
                          		<label class="radio-inline"><input type="radio" id="api-case-run1" name="api-case-run" value="1" checked>运行 </label>
                          		<label class="radio-inline"><input type="radio" id="api-case-run0" name="api-case-run" value="0">不运行  <b class="text-info">(关联后运行一次)</b></label>
                        	</div>
	                      </div>
	                    </div>
	                    <div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <label class="col-sm-3 text-info text-center"><i class="ti-star text-danger m-r-10"></i><code>登录验证 <i class="fa fa-chevron-right text-danger"></i></code></label>
                          	<div class="radio-list">
                          		<label class="radio-inline"><input type="radio" id="api-case-login1" name="api-case-login" value="1" checked>是 </label>
                          		<label class="radio-inline"><input type="radio" id="api-case-login0" name="api-case-login" value="0">否  <b class="text-info"></b></label>
                        	</div>
	                      </div>
	                    </div>
	                    <div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <label class="col-sm-3 text-info text-center"><i class="ti-star text-danger m-r-10"></i><code>项目 <i class="fa fa-chevron-right text-danger"></i></code></label>
	                        <div class="col-sm-9">
		                        <select id="api-case-project" name="api-case-project" class="form-select" style="width: 80%;"></select>
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
	                        <input type="text" id="api-case-name" name="api-case-name" class="form-control" placeholder="案例名称">
	                        <i class="ti-star text-danger"></i>
	                      </div>
	                    </div>
	                    <hr style="height:3px;border:none;border-top:3px dotted red;">
	                    <div class="form-group">
	                      <div class="col-md-12 m-b-20">
				            <div class="input-group m-b-20"> <span class="input-group-addon">非验证点</span>
				              <input type="text" id="api-case-strategy" name="api-case-strategy" value="" data-role="tagsinput" placeholder="添加字段">
				            </div>
	                      </div>
	                    </div>
                        <div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <label class="col-sm-3 text-info text-center"><i class="ti-star text-danger m-r-10"></i><code>接口 <i class="fa fa-chevron-right text-danger"></i></code></label>
	                        <div class="col-sm-7">
		                        <select id="api-case-interface" name="api-case-interface" class="form-control select2" style="width: 100%;"></select>
	                        </div>
	                        <div class="col-sm-2">
	                        	<label class="text-info">"{X}"会替换请求体中值</label>
	                        </div>
	                      </div>
	                    </div>
	                    <div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <label class="col-sm-3 text-info text-center"><code>一次请求 <i class="fa fa-chevron-right text-danger"></i></code></label>
	                        <div class="radio-list">
                          		<label class="radio-inline"><input type="radio" id="api-case-once0" name="api-case-once" value="0" checked>否</label>
                          		<label class="radio-inline"><input type="radio" id="api-case-once1" name="api-case-once" value="1">是  <b class="text-info">(签到类型接口)</b></label>
                        	</div>
	                      </div>
	                    </div>
	                    <div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <label class="col-sm-3 text-info text-center"><code>前期准备 <i class="fa fa-chevron-right text-danger"></i></code></label>
	                        <div class="col-sm-7">
		                        <select id="api-case-ready" name="api-case-ready" class="form-select" style="width: 100%;"></select>
	                        </div>
	                        <div class="col-sm-2">
	                        	<label class="text-info">(新增请联系管理员)</label>
	                        </div>
	                      </div>
	                    </div>
	                    <div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <label class="col-sm-3 text-info text-center"><code>是否有请求体 <i class="fa fa-chevron-right text-danger"></i></code></label>
                          	<div class="radio-list">
                          		<label class="radio-inline"><input type="radio" id="api-case-is-body0" name="api-case-is-body" value="0" checked>没有</label>
                          		<label class="radio-inline"><input type="radio" id="api-case-is-body1" name="api-case-is-body" value="1">有</label>
                        	</div>
	                      </div>
	                    </div>
	                    <div class="form-group">
	                      <div id="bodyDiv" class="col-md-12 m-b-20" style="width:100%;display: none">
							<textarea autoHeight="true" name="api-case-body" id="api-case-body" style="min-height:300px;overflow:hidden;" class="form-control" placeholder="请求体"></textarea>
                           </div>
	                    </div>
	                    <div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <label class="col-sm-3 text-info text-center"><code>请填写验证结果 <i class="fa fa-chevron-right text-danger"></i></code></label>
	                        <%-- <label class="col-sm-3 text-info text-center"><code>是否是新接口 <i class="fa fa-chevron-right text-danger"></i></code></label>
                          	<div class="col-sm-2 radio-list">
                          		<label class="radio-inline"><input type="radio" id="api-case-is-result0" name="api-case-is-result" value="0" checked>不是</label>
                          		<label class="radio-inline"><input type="radio" id="api-case-is-result1" name="api-case-is-result" value="1">是 </label>
                        	</div>
                        	<label class="col-sm-7 text-info">(单次运行的所有案例全为"是"，则不请求线上！)</label> --%>
                        	<label class="col-sm-7 text-info">(实际请求返回值与此结果对比)</label>
	                      </div>
	                    </div>
	                    <div class="form-group">
	                      <!-- <div id="resultDiv" class="col-md-12 m-b-20" style="width:100%;display: none"> -->
	                      <div id="resultDiv" class="col-md-12 m-b-20" style="width:100%;">
							<textarea autoHeight="true" name="api-case-result" id="api-case-result" style="min-height:300px;overflow:hidden;" class="form-control" placeholder="验证结果"></textarea>
                           </div>
	                    </div>
	                    <hr style="height:3px;border:none;border-top:3px dotted red;">
	                    <div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <label class="col-sm-2 text-info text-center"><code>关联案例（1） <i class="fa fa-chevron-right text-danger"></i></code></label>
	                        <div class="col-sm-8">
		                        <select id="api-case-case1" name="api-case-case1" class="form-control select2" style="width: 100%;"></select>
	                        </div>
	                        <div class="col-sm-2">
	                        	<input type="hidden" id="api-case-count" name="api-case-count" value="1"/>
	                        	<input type="hidden" id="api-case-link" name="api-case-link" value=""/>
	                        	<button type="button" class="btn btn-success add-link-case" onclick="addApiCaseLink()">添加</button>
	                        	<button type="button" class="btn btn-success del-link-case" onclick="delApiCaseLink()">删除</button>
	                        </div>
	                      </div>
	                    </div>
	                </form>
	                <form id="api-case-img-form" class="form-horizontal form-material" action="${pageContext.request.contextPath}/api/case/fileUpload" method="POST" enctype="multipart/form-data">
	                	<hr style="height:3px;border:none;border-top:3px dotted red;">
	                	<div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <label class="col-sm-3 text-info text-center"><code>是否上传图片 <i class="fa fa-chevron-right text-danger"></i></code></label>
                          	<div class="col-sm-2 radio-list">
                          		<label class="radio-inline"><input type="radio" id="api-case-is-img0" name="api-case-is-img" value="0" checked>否</label>
                          		<label class="radio-inline"><input type="radio" id="api-case-is-img1" name="api-case-is-img" value="1">是 </label>
                        	</div>
                        	<label class="col-sm-7 text-info">(涉及图片上传接口)</label>
	                      </div>
	                    </div>
	                    <div class="form-group">
	                      <div id="imgDiv" class="col-md-12 m-b-20" style="width:100%;display: none"></div>
	                    </div>
	                </form>
                  </div>
                  <div class="modal-footer">
                  	<div id="msgDiv" class="alert alert-danger alert-dismissable text-center" style="display: none">
						<button type="button" class="close" aria-hidden="true">
							&times;
						</button>
						<span id="msg"></span>
					</div>
                    <button type="button" class="btn btn-primary waves-effect" onclick="apiImgUploadSave();">保存</button>
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
                    <h4 class="modal-title" id="exampleModalLabelRun1">案例-运行</h4>
                  </div>
                  <div class="modal-body">
                    <form id="api-case-run-form" class="form-horizontal form-material">
                    	<input type="hidden" id="api-case-run-id" name="api-case-run-id" value="">
                    	<div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <label class="col-sm-3 text-info text-center"><i class="ti-star text-danger m-r-10"></i><code>对比方式<i class="fa fa-chevron-right text-danger"></i></code></label>
                          	<div class="radio-list">
                          		<label class="radio-inline"><input type="radio" id="api-case-run-compare0" name="api-case-run-compare" value="0" checked>自定义对比</label>
                          		<label class="radio-inline"><input type="radio" id="api-case-run-compare1" name="api-case-run-compare" value="1">线上对比</label>
                        	</div>
	                      </div>
	                    </div>
                    	<div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <label class="col-sm-3 text-info text-center"><i class="ti-star text-danger m-r-10"></i><code>测试环境<i class="fa fa-chevron-right text-danger"></i></code></label>
	                        <div class="col-sm-9">
		                        <select id="api-case-run-platform" name="api-case-run-platform" class="form-select" style="width: 80%;">
		                        	<option value='1' selected="selected">测试环境</option>
		                        	<option value='2'>预发环境</option>
		                        	<option value='3'>线上环境</option>
		                        </select>
	                        </div>
	                      </div>
	                    </div>
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
            <table id="api-case-table" class="table display">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>项目</th>
                  <th>版本</th>
                  <th><b class='label label-danger'>地址</b></th>
                  <th><b class='label label-inverse'>名称</b><b class='label label-info'>默认</b></th>
                  <th>请求体</th>
                  <th><b class='label label-success'>非验证点</b></th>
                  <th><b class='label label-warning'>关联案例</b></th>
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
<script src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>
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
<script src="${pageContext.request.contextPath}/plugins/bower_components/custom-select/custom-select.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/plugins/bower_components/multiselect/js/jquery.multi-select.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/plugins/bower_components/datatables/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bower_components/bootstrap-tagsinput/dist/bootstrap-tagsinput.min.js"></script>
<!-- jQuery file upload -->
<script src="${pageContext.request.contextPath}/plugins/bower_components/dropify/dist/js/dropify.min.js"></script>
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
		createTable(null, null);
		initEvent();
		initApiCaseReady(null);
		initApiCaseProject(null);
		initApiCaseProjectSearch();
		initApiCaseVersion(null,null);
		initApiCaseVersionSearch(null);
		initApiCaseInterface(null,null);
		initApiCaseLink(null,null);
	});
	
	var dropifyImg;
	function initDestroy(file){
		var f = "<input type='file' id='api-case-img' name='api-case-img' class='dropify' data-max-file-size='1M' data-default-file='" + file + "'/>";
		$("#imgDiv").append(f);
		$("#imgDiv").show();
		dropifyImg = $('#api-case-img').dropify({
			messages: {
		        'default': '点击或拖拽文件到这里',
		        'replace': '点击或拖拽文件到这里来替换文件',
		        'remove':  '移除文件',
		        'error':   '对不起，你上传的文件太大了',
		    }
	    });
		dropifyImg = dropifyImg.data('dropify');
	}
	
	function removeDestroy(){
		$("#imgDiv").empty();
		$("#imgDiv").hide();
		dropifyImg = null;
	}
	
	function initEvent(){
		$("#api-case-run-compare0").change(function(){
    		$("#api-case-run-platform").removeAttr("disabled");
    		$("#api-case-run-platform").val("1");
		});
    	$("#api-case-run-compare1").change(function(){
    		$("#api-case-run-platform").val("2");
    		$("#api-case-run-platform").attr({"disabled":"disabled"});
		});
		$("#api-case-project-s").change(function(){
			initApiCaseVersionSearch($(this).val());
    		createTable($(this).val(), null);
		});
		$("#api-case-version-s").change(function(){
    		createTable($("#api-case-project-s").val(), $(this).val());
		});
		$("#api-case-project").change(function(){
			initApiCaseVersion($(this).val(),null);
			initApiCaseInterface($(this).val(),null);
			initApiCaseLink($(this).val(),null);
		});
		$("#api-case-version").change(function(){
			initApiCaseLink($("#api-case-project").val(),$(this).val());
		});
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
		/* $("#api-case-is-result0").change(function(){
			$("#resultDiv").hide();
		});
		$("#api-case-is-result1").change(function(){
			$("#resultDiv").show();
		}); */
		$("#api-case-is-body0").change(function(){
			$("#bodyDiv").hide();
		});
		$("#api-case-is-body1").change(function(){
			$("#bodyDiv").show();
		});
		$("#api-case-is-img0").change(function(){
			$('#api-case-update-img').val("1");
			removeDestroy();
		});
		$("#api-case-is-img1").change(function(){
			$('#api-case-update-img').val("1");
			initDestroy("");
		});
		if(parseInt($('#api-case-count').val()) > 1){
			$('.del-link-case').removeAttr("disabled");
		}else{
			$('.del-link-case').attr({"disabled":"disabled"});
		}
	}
	
	function createTable(pid, vid) {
		$('#api-case-table').dataTable().fnDestroy();
   		$('#api-case-table').DataTable({
	   		responsive : false,
	   		sAjaxSource : "<%=request.getContextPath()%>/api/case/list/data/pid=" + pid + "/vid=" + vid,
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
					"sWidth" : "5%",
					"aTargets" : [ 1 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return (data.interfaceo == null || data.interfaceo.projecto == null) ? "-" : data.interfaceo.projecto.name;
					}
				},
				{
					"sWidth" : "5%",
					"aTargets" : [ 2 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.versiono == null ? "-" : tooltipJsonByVersion(data.versiono.version, data.versiono.channel);
					}
				},
				{
					"sWidth" : "20%",
					"aTargets" : [ 3 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						var html = "<a href=\"#\" data-id='{0}' data-data='{1}' class='apiCaseEdit'><b data-toggle=\"modal\" data-target=\"#exampleModalCase\">{2}</b></a>";
						return String.format(html, data.id, JSON.stringify(data), data.interfaceo == null ? "-" : "[" + data.interfaceo.type + "]" + data.interfaceo.url);
					}
				},
				{
					"sWidth" : "15%",
					"aTargets" : [ 4 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						var html = "";
						if(data.run == 1){
							html = "<a href=\"#\" data-id='{0}' class='initApiCaseRun'><lable class='{1}' data-toggle=\"modal\" data-target=\"#exampleModalRun1\">{2}</label></a>";
						}else{
							html = "<lable class='{1}'>{2}</label>";
						}
						return String.format(html, data.id, (data.flag == 0) ? "text-inverse" : "text-info", data.name);
					}
				},
				{
					"sWidth" : "12%",
					"aTargets" : [ 5 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return (data.body == null || data.body == "") ? "-" : tooltipJson(data.body);
					}
				},
				{
					"sWidth" : "10%",
					"aTargets" : [ 6 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return (data.strategy == null || data.strategy == "") ? "-" : tooltipText(data.strategy);
					}
				},
				{
					"sWidth" : "8%",
					"aTargets" : [ 7 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return (data.link == null || data.link == "") ? "-" : tooltipText(data.link);
					}
				},
				{
					"sWidth" : "12%",
					"aTargets" : [ 8 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return new Date(data.createTime).Format("yyyy-MM-dd hh:mm:ss");
					}
				},
				{
					"sWidth" : "8%",
					"aTargets" : [ 9 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						var html = "";
						if(data.run == 1){
							html += "<a href=\"#\" data-id='{0}' class='initApiCaseRun'><i class=\"fa fa-toggle-right text-success m-r-15\" data-toggle=\"modal\" data-target=\"#exampleModalRun1\"></i></a>";
						}
						html += "<a href=\"#\" data-id='{0}' data-data='{1}' class='apiCaseEdit'><i class=\"fa fa-pencil text-inverse m-r-15\" data-toggle=\"modal\" data-target=\"#exampleModalCase\"></i></a>"
							 + "<a href=\"#\" data-id='{0}' class='apiCaseDel'><i class=\"fa fa-close text-danger\"></i></a>";
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
			$('#case-modal-lable').html("案例-编辑");
			$('#api-case-update-img').val("0");
			hideMsgDiv();
			var c = $(this).data('data');
			$('#api-case-id').val(c.id);
  	      	$('#api-case-name').val(c.name);
  	      	$('#api-case-run' + c.run).prop("checked", true);
  	      	$('#api-case-login' + c.login).prop("checked", true);
  	      	$('#api-case-flag' + c.flag).prop("checked", true);
  	      	$('#api-case-strategy').tagsinput('removeAll');
	      	$('#api-case-strategy').tagsinput('add', c.strategy);
  	      	if(c.body != null && c.body != ""){
  	      		$('#api-case-is-body1').prop("checked",true);
  	  			$('#api-case-body').val(jsonFormat(c.body));
  	  			$("#bodyDiv").show();
  	      	}else{
  	      		$('#api-case-is-body0').prop("checked",true);
  	      		$("#bodyDiv").hide();
  	      	}
			if(c.result != null && c.result != ""){
				/* $('#api-case-is-result1').prop("checked",true); */
				$('#api-case-result').val(jsonFormat(c.result));
				/* $("#resultDiv").show(); */
			}else{
  	      		/* $('#api-case-is-result0').prop("checked",true); */
  	      		/* $("#resultDiv").hide(); */
  	      	}
			if(c.img != null && c.img != ""){
				$('#api-case-is-img1').prop("checked",true);
				removeDestroy();
				initDestroy(c.img);
			}else{
				$('#api-case-is-img0').prop("checked",true);
				removeDestroy();
  	      	}
			if(c.once != null && c.once != ""){
				$('#api-case-once1').prop("checked",true);
			}else{
				$('#api-case-once0').prop("checked",true);
			}
			initApiCaseReady(c.ready);
			initApiCaseProject(c.interfaceo.projecto.id);
			initApiCaseVersion(c.interfaceo.projecto.id, c.versiono.id);
  	      	initApiCaseInterface(c.interfaceo.projecto.id, c.interfaceo.id);
  	      	editApiCaseLink(c.interfaceo.projecto.id,c.versiono.id,c.link,c.id);
			$('#api-case-project').prop("disabled",true);
			autoHeight($("#api-case-body")[0]);
			autoHeight($("#api-case-result")[0]);
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
	
	function initApiCaseModal(){
		$('#case-modal-lable').html("案例-添加");
		$('#api-case-update-img').val("0");
		$('#api-case-id').val("");
		$('#api-case-name').val("");
		$('#api-case-run1').prop("checked",true);
		$('#api-case-login1').prop("checked",true);
		$('#api-case-flag0').prop("checked",true);
		$('#api-case-strategy').tagsinput('removeAll');
		$('#api-case-is-body0').prop("checked",true);
		$('#api-case-body').val("");
		$("#bodyDiv").hide();
		$('#api-case-is-result0').prop("checked",true);
		$('#api-case-result').val("");
		/* $("#resultDiv").hide(); */
		$('#api-case-is-img0').prop("checked",true);
		$('#api-case-once0').prop("checked",true);
		removeDestroy();
		initApiCaseReady(null);
		initApiCaseProject(null);
    	initApiCaseVersion(null,null);
    	initApiCaseInterface(null,null);
    	initApiCaseLink(null,null);
    	$('#api-case-project').prop("disabled",false);
    	autoHeight($("#api-case-body")[0]);
    	autoHeight($("#api-case-result")[0]);
    	hideMsgDiv();
    }
	
	function initApiCaseLink(pid, vid){
		var count = parseInt($('#api-case-count').val());
		if(count > 1){
			for (var i = 2; i <= count; i++) {
				$("#link-case-" + i).remove();
			}
		}
		$('#api-case-count').val(1);
		$('.del-link-case').attr({"disabled":"disabled"});
		initApiCaseCase(pid,vid,1,null,null);
	}
	
	function editApiCaseLink(pid, vid, link, myid){
		var count = parseInt($('#api-case-count').val());
		if(count > 1){
			for (var i = 2; i <= count; i++) {
				$("#link-case-" + i).remove();
			}
		}
		$('#api-case-count').val(1);
		if(link == null || link == ""){
			initApiCaseCase(pid,vid,1,null,myid);
		}else{
			var arrlink = link.split(",");
			for (var i = 1; i <= arrlink.length; i++) {
				if(i != 1){
					addApiCaseLink();
				}
				initApiCaseCase(pid,vid,i,arrlink[i-1],myid);
			}
		}
		if($('#api-case-count').val() == "1"){
			$('.del-link-case').attr({"disabled":"disabled"});
		}else{
			$('.del-link-case').removeAttr("disabled");
		}
	}
	
	function addApiCaseLink(){
		var html = "<div id=\"link-case-{0}\" class=\"form-group\">" + 
					"<div class=\"col-md-12 m-b-20\">" + 
						"<label class=\"col-sm-2 text-info text-center\"><code>关联案例（{0}）<i class=\"fa fa-chevron-right text-danger\"></i></code></label>" + 
						"<div class=\"col-sm-8\">" + 
							"<select id=\"api-case-case{0}\" name=\"api-case-case{0}\" class=\"form-control select2\" style=\"width: 100%;\"></select>" + 
						"</div>" + 
					"</div>" + 
				"</div>";
		var count = parseInt($('#api-case-count').val()) + 1;
		$('#api-case-form').append(String.format(html, count));
		$('#api-case-count').val(count);
		$('.del-link-case').removeAttr("disabled");
		var caseid = $('#api-case-id').val();
		if(caseid == null || caseid == ""){
			initApiCaseCase($('#api-case-project').val(),$('#api-case-version').val(),count,null,null);
		}else{
			initApiCaseCase($('#api-case-project').val(),$('#api-case-version').val(),count,null,caseid);
		}
	}
	
	function delApiCaseLink(){
		var count = parseInt($('#api-case-count').val());
		if(count > 1){
			$("#link-case-" + count).remove();
			$('#api-case-count').val(count - 1);
			if(count == 2){
				$('.del-link-case').attr({"disabled":"disabled"});
			}
		}
	}
	
	function countApiCaseLink(){
		var result = "";
		var count = parseInt($('#api-case-count').val());
		for (var i = 1; i <= count; i++) {
			result += "," + $('#api-case-case' + i).val();
		}
		if(result[0] == ','){
			result = result.substring(1);
		}
		$('#api-case-link').val(result);
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
	
	function initApiCaseRun(cid){
		$('#api-case-run-id').val(cid);
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
    				$('#api-case-run-account').empty();
    				$('#api-case-run-account').append(optionstring + "<option value='0'>无</option>");
      			}
      	    }
		});
	}
	
	function initApiCaseReady(ready){
		$.ajax({
    		type:"get",
    		url:"<%=request.getContextPath()%>/api/case/ready",
    		success:function(data){
    			if(data.responseCode == "0000"){
    				var optionstring = "";
    				var bool = false;
    				var list = data.data;
    				if(list != null){
    					for(var i = 0; i < list.length; i++){
        					if(ready == list[i].id){
        						bool = true;
        						optionstring += "<option value='" + list[i].id + "' selected>[" + list[i].id + "]" + list[i].desc + "</option>";
        					}else{
    	    					optionstring += "<option value='" + list[i].id + "'>[" + list[i].id + "]" + list[i].desc + "</option>";
        					}
        				}
    				}
    				if(bool){
    					optionstring = "<option value='0'>[0]无</option>" + optionstring;
    				}else{
    					optionstring = "<option value='0' selected>[0]无</option>" + optionstring;
    				}
    				$('#api-case-ready').empty();
    				$('#api-case-ready').append(optionstring);
    			}
    		}
    	});
    }
	
	function initApiCaseProjectSearch(){
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
    				$('#api-case-project-s').empty();
    				if(optionstring != ""){
    					optionstring += "<option value='-1'>--工具【所有带图片接口】--</option><option value='-2'>--工具【所有不运行接口】--</option>";
    					optionstring += "<option value='-3'>--工具【所有有关联接口】--</option><option value='-4'>--工具【所有一次请求接口】--</option>";
    					optionstring += "<option value='-5'>--工具【所有数据准备接口】--</option>";
	    				$('#api-case-project-s').append(optionstring);
    				}
    			}
    		}
    	});
    }
	
	function initApiCaseProject(projectid){
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
    				$('#api-case-project').empty();
    				$('#api-case-project').append(optionstring);
    			}
    		}
    	});
    }
	
	function initApiCaseVersionSearch(projectid){
		if(projectid == -1){
			$('#api-case-version-s').empty();
		}else{
			$.ajax({
	    		type:"get",
	    		url:"<%=request.getContextPath()%>/api/version/list/data/pid=" + projectid,
	    		success:function(data){
	    			if(data.responseCode == "0000"){
	    				var optionstring = "";
	    				var list = data.data;
	    				if(list != null){
	    					for(var i = 0; i < list.length; i++){
	        					if(i == 0){
	        						optionstring += "<option value='" + list[i].id + "' selected>" + list[i].version + "</option>";
	        					}else{
	    	    					optionstring += "<option value='" + list[i].id + "'>" + list[i].version + "</option>";
	        					}
	        				}
	    				}
	    				$('#api-case-version-s').empty();
	    				$('#api-case-version-s').append(optionstring);
	    			}
	    		}
	    	});
		}
    }
	
	function initApiCaseVersion(projectid, versionid){
    	$.ajax({
    		type:"get",
    		url:"<%=request.getContextPath()%>/api/version/list/data/pid=" + projectid,
    		success:function(data){
    			if(data.responseCode == "0000"){
    				var optionstring = "";
    				var list = data.data;
    				if(list != null){
	    				for(var i = 0; i < list.length; i++){
	    					if(versionid == list[i].id || i == 0){
	    						optionstring += "<option value='" + list[i].id + "' selected>" + list[i].version + "</option>";
	    					}else{
		    					optionstring += "<option value='" + list[i].id + "'>" + list[i].version + "</option>";
	    					}
	    				}
    				}
    				$('#api-case-version').empty();
    				$('#api-case-version').append(optionstring);
    			}
    		}
    	});
    }
	
	function initApiCaseInterface(projectid, interfaceid){
    	$.ajax({
    		type:"get",
    		url:"<%=request.getContextPath()%>/api/interface/list/data/projectid=" + projectid,
    		success:function(data){
    			if(data.responseCode == "0000"){
    				var optionstring = "<optgroup label=\"请选择...\">";
    				var selected = "";
    				var list = data.data;
    				if(list != null){
	    				for (var i = 0; i < list.length; i++) {
	    					if(interfaceid == list[i].id || i == 0){
	    						selected = "<option value='" + list[i].id + "'>[" + list[i].type +  "] " + list[i].url + "</option>";
	    						if(interfaceid == null){
		    						$('#api-case-name').val(list[i].name);
	    						}
	    					}
	    					optionstring += "<option value='" + list[i].id + "'>[" + list[i].type +  "] " + list[i].url + "</option>";
	    				}
    				}
    				optionstring += "</optgroup>";
    				$('#api-case-interface').empty();
    				$('#api-case-interface').append(selected + optionstring);
    				$('#api-case-interface').select2();
    			}
    		}
    	});
    }
	
	function initApiCaseCase(projectid, versionid, index, cid, myid){
		$.ajax({
    		type:"get",
    		url:"<%=request.getContextPath()%>/api/case/list/data/pid=" + projectid + "/vid=" + versionid,
    		success:function(data){
    			if(data.responseCode == "0000"){
    				var optionstring = "<optgroup label=\"请选择...\"><option value='0'>无</option>";
    				var selected = "";
    				var list = data.data;
    				if(list != null){
    					for (var i = 0; i < list.length; i++) {
        					if(list[i].id != myid){
        						if(cid == null){
            						if(i == 0){
                						selected = "<option value='0'>无</option>";
                					}
            					}else{
            						if(cid == list[i].id || i == 0){
            							selected = "<option value='" + list[i].id + "'>[" + list[i].id +  "] " + list[i].name + " [" + list[i].interfaceo.type + "] " + list[i].interfaceo.url + "</option>";
                					}
            					}
            					optionstring += "<option value='" + list[i].id + "'>[" + list[i].id +  "] " + list[i].name + " [" + list[i].interfaceo.type + "] " + list[i].interfaceo.url + "</option>";
        					}
        				}
    				}
    				optionstring += "</optgroup>";
    				$('#api-case-case' + index).empty();
    				$('#api-case-case' + index).append(selected + optionstring);
    				$('#api-case-case' + index).select2();
    			}
    		}
    	});
	}
	
	function apiImgUploadSave(){
		if(dropifyImg != null && dropifyImg.file != null && dropifyImg.file.object != null && dropifyImg.file.name != null){
			if(!dropifyImg.isImage()){
				showMsgDiv("文件请选择图片！");
			}else{
				$.ajaxFileUpload({
					type:"post",
		      		url:"<%=request.getContextPath()%>/api/case/fileUpload",
		      		secureuri:false,
		      		fileElementId:'api-case-img',
		      		dataType:'json',
		      		success:function(data){
		      			if(data.responseCode == "0000"){
		      				$('#api-case-img-path').val(data.data);
		      				apiCaseSave();
		      			}else{
		      				showMsgDiv(data.responseMsg);
		      			}
		      	    }
				});
			}
		}else{
			$('#api-case-img-path').val("");
			apiCaseSave();
		}
	}
	
	function apiCaseSave(){
		var cproject = $('#api-case-project').val();
		var cversion = $('#api-case-version').val();
		var cname = $('#api-case-name').val();
		var cinterface = $('#api-case-interface').val();
		var cresult = $('#api-case-result').val();
		if(cproject == null || cproject.trim() == ""){
	    	showMsgDiv("请选择项目！");
    	}else if(cversion == null || cversion.trim() == ""){
	    	showMsgDiv("请选择版本！");
    	}else if(cname == null || cname.trim() == ""){
	    	showMsgDiv("请输入案例名称！");
    	}else if(cinterface == null || cinterface.trim() == ""){
	    	showMsgDiv("请选择接口！");
    	}else if(cresult == null || cresult.trim() == ""){
	    	showMsgDiv("请填写验证结果！");
    	}else {
    		countApiCaseLink();
    		$.ajax({
    			type:"post",
          		url:"<%=request.getContextPath()%>/api/case/is/json",
          		data:$('#api-case-form').serialize(),
          		success:function(data){
          			if(data.responseCode == "0000"){
          				apiCaseCreateUpdate();
          			}else{
          				showMsgDiv("您输入的JSON格式不正确 => " + data.responseMsg);
          			}
          	    }
    		});
    	}
    }
	
	function apiCaseCreateUpdate(){
		hideMsgDiv();
		$('#exampleModalCase').modal('hide');
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
