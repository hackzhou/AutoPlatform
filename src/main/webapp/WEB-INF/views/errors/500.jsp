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
<title>500</title>
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
<section id="wrapper" class="error-page">
  <div class="error-box">
    <div class="error-body text-center">
      <h1>500</h1>
      <h3 class="text-uppercase">Internal Server Error.</h3>
      <p class="text-muted m-t-30 m-b-30">Please try after some time</p>
      <a href="${pageContext.request.contextPath}/home/index" class="btn btn-info btn-rounded waves-effect waves-light m-b-40">Back to home</a> </div>
    <jsp:include page="/WEB-INF/views/foot.jsp"></jsp:include>
  </div>
</section>
<!-- jQuery -->
<script src="${pageContext.request.contextPath}/plugins/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="${pageContext.request.contextPath}/eliteadmin/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- Custom Theme JavaScript -->
<script src="${pageContext.request.contextPath}/eliteadmin/js/custom.min.js"></script>

</body>
</html>
