<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/plugins/images/favicon.png">
<title>登录</title>
<!-- Bootstrap Core CSS -->
<link href="${pageContext.request.contextPath}/eliteadmin/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- animation CSS -->
<link href="${pageContext.request.contextPath}/eliteadmin/css/animate.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="${pageContext.request.contextPath}/eliteadmin/css/style.css" rel="stylesheet">
<!-- color CSS -->
<link href="${pageContext.request.contextPath}/eliteadmin/css/colors/blue.css" id="theme"  rel="stylesheet">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>
<body>
<!-- Preloader -->
<div class="preloader">
  <div class="cssload-speeding-wheel"></div>
</div>
<section id="wrapper" class="login-register">
  <div class="login-box login-sidebar">
    <div class="white-box">
      <form class="form-horizontal form-material" id="loginform" action="${pageContext.request.contextPath}/user/login" method="post">
        <a href="javascript:void(0)" class="text-center db"><img src="${pageContext.request.contextPath}/plugins/images/eliteadmin-logo-dark.png" alt="Home" /><br/><img src="${pageContext.request.contextPath}/plugins/images/eliteadmin-text-dark.png" alt="Home" /></a>  
        <input type="hidden" id="hiddenjump" name="hiddenjump" value="/home/index">
        <div class="form-group m-t-40">
          <div class="col-xs-12">
            <input id="username" name="username" class="form-control" type="text" placeholder="Username">
          </div>
        </div>
        <div class="form-group">
          <div class="col-xs-12">
            <input id="password" name="password" class="form-control" type="password" placeholder="Password">
          </div>
        </div>
        <div class="form-group">
          <div class="col-md-12">
            <div class="checkbox checkbox-primary pull-left p-t-0">
              <input id="rememberme" name="rememberme" type="hidden" value="0">
              <input id="remember" name="remember" type="checkbox">
              <label for="remember"> Remember me (ten days)</label>
            </div>
          </div>
        </div>
        <div class="form-group text-center m-t-20">
          <div class="col-xs-12">
            <button class="btn btn-info btn-lg btn-block text-uppercase waves-effect waves-light" onclick="login();">Log In</button>
          </div>
        </div>
        <div class="row">
          <div class="col-xs-12 col-sm-12 col-md-12 m-t-10 text-center">
            <div class="social"><a href="javascript:void(0)" class="btn  btn-facebook" data-toggle="tooltip"  title="Login with Facebook"> <i aria-hidden="true" class="fa fa-facebook"></i> </a> <a href="javascript:void(0)" class="btn btn-googleplus" data-toggle="tooltip"  title="Login with Google"> <i aria-hidden="true" class="fa fa-google-plus"></i> </a> </div>
          </div>
        </div>
        <div class="form-group m-b-0">
          <div class="col-sm-12 text-center">
            <p>Don't have an account? <a href="${pageContext.request.contextPath}/home/register" class="text-primary m-l-5"><b>Sign Up</b></a></p>
          </div>
        </div>
        <div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 m-t-10 text-center">
				<div id="msgDiv" class="alert alert-danger alert-dismissable" style="display: none">
					<button type="button" class="close" data-dismiss="alert" aria-hidden="true">
						&times;
					</button>
					<span id="msg"></span>
				</div>
			</div>
		</div>
      </form>
    </div>
  </div>
</section>
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
<!-- Custom Theme JavaScript -->
<script src="${pageContext.request.contextPath}/eliteadmin/js/custom.min.js"></script>
<!--Style Switcher -->
<script src="${pageContext.request.contextPath}/plugins/bower_components/styleswitcher/jQuery.style.switcher.js"></script>
<!-- Base -->
<script src="${pageContext.request.contextPath}/js/base.js"></script>
<script>
	$(document).ready(function(){
		var hjump = GetQueryString("jump");
		if(hjump != null && hjump != ""){
			$("#hiddenjump").val(hjump);
		}
		var msg = "${msg}";
		if(msg == null || msg == ""){
			var username = "${username}";
			if(username != ""){
				window.location.href="${pageContext.request.contextPath}/home/index";
			}else{
				var uname = getCookie("username");
				var pwd = getCookie("password");
				if(uname != "" && pwd != ""){
					window.location.href="${pageContext.request.contextPath}/user/login/cookie?jump=" + $("#hiddenjump").val();
				}
			}
		}else{
			showMsgDiv(msg);
			$('#username').val("${name}");
		}
	});
	
	function login(){
		if($('#remember').is(":checked")){
			$('#rememberme').val("1");
		}
		$("#loginform").submit();
	}
	
</script>
</body>
</html>
