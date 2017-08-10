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
<title>注册</title>
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
      <form class="form-horizontal form-material" id="registerform" action="${pageContext.request.contextPath}/user/register" method="post">
        <a href="javascript:void(0)" class="text-center db"><img src="${pageContext.request.contextPath}/plugins/images/eliteadmin-logo-dark.png" alt="Home" /><br/><img src="${pageContext.request.contextPath}/plugins/images/eliteadmin-text-dark.png" alt="Home" /></a> 
        <h3 class="box-title m-t-40 m-b-0">立即注册</h3><small>创建您的帐户并欢迎您的加入</small> 
        <div class="form-group m-t-20">
          <div class="col-xs-12">
            <input id="username" name="username" class="form-control" type="text" placeholder="用户名">
          </div>
        </div>
        <div class="form-group ">
          <div class="col-xs-12">
            <input id="email" name="email" class="form-control" type="text" placeholder="邮箱">
          </div>
        </div>
        <div class="form-group ">
          <div class="col-xs-12">
            <input id="password" name="password" class="form-control" type="password" placeholder="密码">
          </div>
        </div>
        <div class="form-group">
          <div class="col-xs-12">
            <input id="password2" name="password2" class="form-control" type="password" placeholder="确认 密码">
          </div>
        </div>
        <div class="form-group text-center m-t-20">
          <div class="col-xs-12">
            <button class="btn btn-info btn-lg btn-block text-uppercase waves-effect waves-light" type="submit">注册</button>
          </div>
        </div>
        <div class="form-group m-b-0">
          <div class="col-sm-12 text-center">
            <p>已经有账户了？ <a href="${pageContext.request.contextPath}/home/login" class="text-primary m-l-5"><b>登录</b></a></p>
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
<script>

	$(document).ready(function(){
		showMsgDiv("${msg}");
		$('#username').val("${name}");
		$('#email').val("${email}");
	});
	
	function showMsgDiv(msgStr){
		if(msgStr != ""){
			$("#msgDiv").show();
			$("#msg").html(msgStr);
		}
	}
	
</script>
</body>
</html>
