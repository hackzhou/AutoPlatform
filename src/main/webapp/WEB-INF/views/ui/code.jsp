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
<!-- Custom CSS -->
<link href="${pageContext.request.contextPath}/eliteadmin/css/style.css" rel="stylesheet">
<!-- color CSS -->
<link href="${pageContext.request.contextPath}/eliteadmin/css/colors/blue.css" id="theme"  rel="stylesheet">
<!-- codemirror CSS -->
<link href="${pageContext.request.contextPath}/css/codemirror.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/show-hint.css" rel="stylesheet">
<style>.CodeMirror {border: 2px inset #dee;width: 100%;height: 100%;}</style>
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
          <button type="button" class="btn btn-info btn-outline" id="ui-code-action-save" onclick="uiCodeSave();"></button>
          <button type="button" class="btn btn-info btn-outline" id="ui-code-action-run" onclick="uiCodeRun();"></button>
        </div>
      </div>
      <div class="row">
		<div class="col-sm-12">
			<form id="ui-code-form" class="form-horizontal form-material">
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
              <label class="col-md-12">运行结果</label>
              <div class="col-md-12">
                <textarea id="ui-code-result" class="form-control" rows="15"></textarea>
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
<!--Code Mirror -->
<script src="${pageContext.request.contextPath}/js/codemirror.js"></script>
<script src="${pageContext.request.contextPath}/js/matchbrackets.js"></script>
<script src="${pageContext.request.contextPath}/js/show-hint.js"></script>
<script src="${pageContext.request.contextPath}/js/clike.js"></script>
<script>

    $(document).ready(function(){
    	initCodeMirror();
    	var cls = "${data}";
    	initDefaultCode(cls);
    });

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
    
    function initDefaultCode(cls){
    	if(cls == null || cls == ""){
    		$('#ui-code-action-save').html("保存");
    		$('#ui-code-action-run').html("保存并运行");
    		$('#ui-code-title-lable').html("（新文件）");
    	}else{
    		$('#ui-code-action-save').html("更新");
    		$('#ui-code-action-run').html("更新并运行");
    		$('#ui-code-title-lable').html("（文件：" + cls + ".java）");
    	}
    	$.ajax({
  			type:"get",
       		url:"<%=request.getContextPath()%>/ui/code/default/code/cls=" + cls,
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
    
    function uiCodeSave(){
    	$('#ui-code-java').val(javaEditor.getValue());
    	$.ajax({
  			type:"post",
       		url:"<%=request.getContextPath()%>/ui/code/create/update",
       		data:$('#ui-code-form').serialize(),
       		success:function(data){
       			if(data.responseCode == "0000"){
       				swal("成功", "保存成功！", "success");
       				hrefCodeCls();
       			}else{
       				swal("错误", data.responseMsg, "error");
       			}
       	    }
  		});
	}
    
    function uiCodeRun(){
    	$('#ui-code-java').val(javaEditor.getValue());
    	$.ajax({
  			type:"post",
       		url:"<%=request.getContextPath()%>/ui/code/create/update",
       		data:$('#ui-code-form').serialize(),
       		success:function(data){
       			if(data.responseCode == "0000"){
       				$.ajax({
       		  			type:"post",
   		        		url:"<%=request.getContextPath()%>/ui/code/run",
   		        		data:$('#ui-code-form').serialize(),
   		        		success:function(data){
   		        			if(data.responseCode == "0000"){
   		        				swal("成功", "已运行！", "success");
   		        				hrefCodeCls();
   		        			}else{
   		        				swal("错误", data.responseMsg, "error");
   		        			}
   		        	    }
       		  		});
       			}else{
       				swal("错误", data.responseMsg, "error");
       			}
       	    }
  		});
    }
    
    function hrefCodeCls(){
    	var cls = "${data}";
    	if(cls == null || cls == ""){
			$(location).attr('href', '${pageContext.request.contextPath}/ui/code/page/cls=' + data.data);
		}
    }
    
</script>
<!--Style Switcher -->
<script src="${pageContext.request.contextPath}/plugins/bower_components/styleswitcher/jQuery.style.switcher.js"></script>
</body>
</html>
