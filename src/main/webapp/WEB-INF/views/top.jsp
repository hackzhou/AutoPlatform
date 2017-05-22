<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="navbar navbar-default navbar-static-top m-b-0">
	<div class="navbar-header"> <a class="navbar-toggle hidden-sm hidden-md hidden-lg " href="javascript:void(0)" data-toggle="collapse" data-target=".navbar-collapse"><i class="ti-menu"></i></a>
		<div class="top-left-part"><a class="logo" href="${pageContext.request.contextPath}/home/index"><b><img src="${pageContext.request.contextPath}/plugins/images/eliteadmin-logo.png" alt="home" /></b><span class="hidden-xs">多多游戏测试平台</span></a></div>
		<ul class="nav navbar-top-links navbar-left hidden-xs">
			<li><a href="javascript:void(0)" class="open-close hidden-xs waves-effect waves-light"><i class="icon-arrow-left-circle ti-menu"></i></a></li>
			<li class="dropdown"> <a class="dropdown-toggle profile-pic" data-toggle="dropdown" href="#"><b class="hidden-xs">接口自动化</b> </a>
			<ul class="dropdown-menu dropdown-user animated flipInY">
				<li><a href="${pageContext.request.contextPath}/api/project/list"><i class="ti-briefcase"></i> Project</a></li>
				<li><a href="${pageContext.request.contextPath}/api/version/list"><i class="ti-layers"></i> Version</a></li>
				<li><a href="${pageContext.request.contextPath}/api/interface/list"><i class="ti-panel"></i> Interface</a></li>
				<li><a href="${pageContext.request.contextPath}/api/account/list"><i class="ti-user"></i> Account</a></li>
				<li role="separator" class="divider"></li>
				<li><a href="${pageContext.request.contextPath}/api/case/list"><i class="ti-write"></i> Case</a></li>
				<li><a href="${pageContext.request.contextPath}/api/report/list"><i class="ti-book"></i> Report</a></li>
				<li role="separator" class="divider"></li>
				<li><a href="${pageContext.request.contextPath}/api/setting/list"><i class="ti-settings"></i> Setting</a></li>
			</ul>
			<!-- /.dropdown-user -->
			</li>
			<li class="dropdown"> <a class="dropdown-toggle profile-pic" data-toggle="dropdown" href="#"><b class="hidden-xs">UI自动化</b> </a>
			</li>
			<li class="dropdown"> <a class="dropdown-toggle profile-pic" data-toggle="dropdown" href="#"><b class="hidden-xs">测试工具</b> </a>
			</li>
		</ul>
		<ul class="nav navbar-top-links navbar-right pull-right">
			<li class="dropdown"> <a class="dropdown-toggle profile-pic" data-toggle="dropdown" href="#"> <img src="${pageContext.request.contextPath}/plugins/images/img2.jpg" alt="user-img" width="36" class="img-circle"><b class="hidden-xs">${username}</b> </a>
			<ul class="dropdown-menu dropdown-user animated flipInY">
				<li><a href="${pageContext.request.contextPath}/user/logout"><i class="fa fa-power-off"></i> Logout</a></li>
			</ul>
			</li>
		</ul>
	</div>
</nav>
