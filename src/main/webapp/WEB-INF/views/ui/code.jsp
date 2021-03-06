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
<title>UI-编码</title>
<!-- Bootstrap Core CSS -->
<link href="${pageContext.request.contextPath}/eliteadmin/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Menu CSS -->
<link href="${pageContext.request.contextPath}/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css" rel="stylesheet">
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
<!-- codemirror CSS -->
<link href="${pageContext.request.contextPath}/css/codemirror.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/show-hint.css" rel="stylesheet">
<style>
.CodeMirror {border: 2px inset #dee;width: 100%;height: 100%;}
</style>
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
          <h4 class="page-title"><i class="fa fa-pagelines m-r-10" style='color:green'></i><span><b style='color:black'>编码</b><label id="ui-code-title-lable"></label></span></h4>
        </div>
        <div class="button-box text-right">
          <button type="button" class="btn btn-primary btn-circle" onclick="uiCodeList();"><i class="fa fa-list"></i> </button>
          <input id="ui-code-description" name="ui-code-description" class="btn btn-danger btn-outline" type="text" style="text-align: left;width: 25%;" placeholder="请填写描述信息...">
          <button type="button" class="btn btn-info btn-outline" id="ui-code-action-save" onclick="uiCodeSave();"></button>
          <button type="button" class="btn btn-info btn-outline" id="ui-code-action-run" onclick="uiCodeRun();"></button>
          <select id="ui-code-devices-sel" name="ui-code-devices-sel" class="select2 btn-danger m-b-10 select2-multiple" multiple="multiple" data-placeholder="请选择设备..."></select>
        </div>
      </div>
      <div class="row">
		<div class="col-sm-12">
			<form id="ui-code-form" class="form-horizontal form-material">
				<input type="hidden" id="ui-code-desc" name="ui-code-desc" value="">
				<input type="hidden" id="ui-code-devices" name="ui-code-devices" value="">
				<input type="hidden" id="ui-code-java" name="ui-code-java" value="">
				<div class="form-group">
					<div><textarea id="ui-code"></textarea></div>
				</div>
			</form>
		</div>
	  </div>
	  <div class="row">
	  	<div class="col-sm-12">
	  	  <form class="form-horizontal">
	  	  	<div class="form-group">
              <label class="col-md-12">最新运行结果显示</label>
              <div class="col-md-12">
                <textarea id="ui-code-result" class="form-control" rows="15">运行完刷新显示...</textarea>
              </div>
            </div>
	  	  </form>
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
<script src="${pageContext.request.contextPath}/plugins/bower_components/multiselect/js/jquery.multi-select.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/plugins/bower_components/datatables/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bower_components/bootstrap-tagsinput/dist/bootstrap-tagsinput.min.js"></script>
<!--Code Mirror -->
<script src="${pageContext.request.contextPath}/js/codemirror.js"></script>
<script src="${pageContext.request.contextPath}/js/matchbrackets.js"></script> 
<script src="${pageContext.request.contextPath}/js/show-hint.js"></script>
<script src="${pageContext.request.contextPath}/js/clike.js"></script>
<script>

    $(document).ready(function(){
    	initCodeMirror();
    	var code = '${data}';
    	if(code == null || code == ""){
    		initDefaultCode(null);
    	}else{
    		initDefaultCode(JSON.parse(code));
    	}
    });
    
    function initUiDevice(devices){
    	$.ajax({
    		type:"get",
    		async: false,
    		url:"<%=request.getContextPath()%>/ui/device/list/data",
    		success:function(data){
    			if(data.responseCode == "0000"){
    				var optionstring = "<optgroup label=\"请选择设备...\">";
    				var selected = "";
    				var list = data.data;
    				for (var i = 0; i < list.length; i++) {
    					if(isContain(devices, list[i].id)){
    						selected += "<option value='" + list[i].id + "' selected>" + list[i].deviceName + "_" + list[i].udid + "</option>";
    					}
    					optionstring += "<option value='" + list[i].id + "'>" + list[i].deviceName + "_" + list[i].udid + "</option>";
    				}
    				optionstring += "</optgroup>";
    				$('#ui-code-devices-sel').empty();
    				$('#ui-code-devices-sel').append(selected + optionstring);
    				$('#ui-code-devices-sel').select2();
    			}
    		}
    	});
    }
    
    function isContain(text, str){
    	if(text != null){
    		var arr = text.split(",");
        	for (var i = 0; i < arr.length; i++) {
        		if(arr[i] == str){
        			return true;
        		}
        	}
    	}
    	return false;
    }

    var javaEditor;
    function initCodeMirror(){
    	javaEditor = CodeMirror.fromTextArea(document.getElementById("ui-code"), {
			lineNumbers: true,
            matchBrackets: true,
            indentUnit: 4,
            mode: "text/x-java"
		});
    	var mac = CodeMirror.keyMap.default == CodeMirror.keyMap.macDefault;
        CodeMirror.keyMap.default[(mac ? "Cmd" : "Ctrl") + "-Space"] = "autocomplete";
    }
    
    function initDefaultCode(code){
    	var clss = "";
    	if(code == null){
    		$('#ui-code-action-save').html("保存");
    		$('#ui-code-action-run').html("保存并运行");
    		$('#ui-code-title-lable').html("（新文件）");
    		$('#ui-code-description').val("");
    		initUiDevice(null);
    	}else{
    		clss = code.cls;
    		$('#ui-code-action-save').html("更新");
    		$('#ui-code-action-run').html("更新并运行");
    		$('#ui-code-title-lable').html("（文件：" + clss + ".java）");
    		$('#ui-code-description').val(HTMLDecode(code.description));
    		initUiDevice(code.devices);
    	}
    	$.ajax({
  			type:"get",
       		url:"<%=request.getContextPath()%>/ui/code/default/code/cls=" + clss,
       		success:function(data){
       			if(data.responseCode == "0000"){
       				javaEditor.setValue(data.data);
       				if(data.data2 != null){
	       				$('#ui-code-result').html(data.data2);
       				}
       			}else{
       				swal("错误", data.responseMsg, "error");
       			}
       	    }
  		});
    }
    
    function uiCodeVerify(){
    	var dev = $('#ui-code-devices-sel').val();
    	var desc = $('#ui-code-description').val();
    	if(desc == null || desc.trim() == ""){
    		swal("错误", "请填写描述信息！", "error");
    	}else if(dev == null || dev == ""){
    		swal("错误", "请选择设备！", "error");
    	}else {
    		$('#ui-code-desc').val(desc);
    		$('#ui-code-devices').val(removeRepeat(dev));
    		$('#ui-code-java').val(javaEditor.getValue());
    		return true;
    	}
    	return false;
    }
    
	function removeRepeat(text){
		if(typeof(text) != "undefined" && text != null){
			var ret = [];
    		var ar = text.toString().split(",");
    		for (var i = 0, j = ar.length; i < j; i++) {
    			if (ret.indexOf(ar[i]) === -1) {
    				ret.push(ar[i]);
    			}
    		}
    		return ret;
    	}
    	return null;
    }
    
    function uiCodeSave(){
		if(uiCodeVerify()){
        	$.ajax({
      			type:"post",
           		url:"<%=request.getContextPath()%>/ui/code/create/update",
           		data:$('#ui-code-form').serialize(),
           		success:function(data){
           			var code = data.data;
           			if(data.responseCode == "0000"){
           				hrefCodeCls("成功", $('#ui-code-action-save').html() + "成功！", "success", code.id);
           			}else{
           				swal("错误", data.responseMsg, "error");
           			}
           	    }
      		});
    	}
	}
    
    function uiCodeRun(){
    	if(uiCodeVerify()){
        	$.ajax({
      			type:"post",
           		url:"<%=request.getContextPath()%>/ui/code/create/update",
           		data:$('#ui-code-form').serialize(),
           		success:function(data){
           			if(data.responseCode == "0000"){
           				var code = data.data;
           				$.ajax({
           		  			type:"post",
       		        		url:"<%=request.getContextPath()%>/ui/code/run",
       		        		data:$('#ui-code-form').serialize(),
       		        		success:function(data){
       		        			if(data.responseCode == "0000"){
       		        				hrefCodeCls("成功", "已运行！", "success", code.id);
       		        			}else{
       		        				hrefCodeCls("错误", data.responseMsg, "error", code.id);
       		        			}
       		        	    }
           		  		});
           			}else{
           				swal("错误", data.responseMsg, "error");
           			}
           	    }
      		});
    	}
    }
    
    function hrefCodeCls(title, text, type, codeId){
    	var btntext = null;
    	if(isClsData()){
			btntext = "确定并跳转！";
		}else{
			btntext = "确定并刷新！";
		}
		swal({
			title: title,
			text: text,
			type: type,
			showCancelButton: false,
			confirmButtonText: btntext,
			closeOnConfirm: false
  		},function(){
  	    	if(isClsData()){
  				$(location).attr('href', '${pageContext.request.contextPath}/ui/code/page/id=' + codeId);
  			}else{
  				window.location.reload();
  			}
  		});
    }
    
    function isClsData(){
    	var code = '${data}';
    	if(code == null || code == ""){
			return true;
		}
    	return false;
    }
    
	function uiCodeList(){
		$(location).attr('href', '${pageContext.request.contextPath}/ui/list/list');
    }
    
</script>
<!--Style Switcher -->
<script src="${pageContext.request.contextPath}/plugins/bower_components/styleswitcher/jQuery.style.switcher.js"></script>
</body>
</html>
