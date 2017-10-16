<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="navbar navbar-default navbar-static-top m-b-0">
	<div class="navbar-header"> <a class="navbar-toggle hidden-sm hidden-md hidden-lg " href="javascript:void(0)" data-toggle="collapse" data-target=".navbar-collapse"><i class="ti-menu"></i></a>
		<div class="top-left-part"><a class="logo" href="${pageContext.request.contextPath}/home/index"><b><img src="${pageContext.request.contextPath}/plugins/images/eliteadmin-logo.png" alt="home" /></b><span class="hidden-xs">多多游戏测试平台</span></a></div>
		<ul class="nav navbar-top-links navbar-left hidden-xs top-menu-all">
			<li><a href="javascript:void(0)" class="open-close hidden-xs waves-effect waves-light"><i class="icon-arrow-left-circle ti-menu"></i></a></li>
			<li class="dropdown"> <a class="dropdown-toggle profile-pic" data-toggle="dropdown" href="#"><b class="hidden-xs m-r-10">接口自动化</b> </a>
			<ul class="dropdown-menu dropdown-user animated flipInY">
				<li><a href="${pageContext.request.contextPath}/api/project/list"><i class="ti-briefcase m-r-10"></i> 项目</a></li>
				<li><a href="${pageContext.request.contextPath}/api/version/list"><i class="ti-layers m-r-10"></i> 版本</a></li>
				<li><a href="${pageContext.request.contextPath}/api/interface/list"><i class="ti-panel m-r-10"></i> 接口</a></li>
				<li><a href="${pageContext.request.contextPath}/api/account/list"><i class="ti-user m-r-10"></i> 账号</a></li>
				<li role="separator" class="divider"></li>
				<li><a href="${pageContext.request.contextPath}/api/case/list"><i class="ti-write m-r-10"></i> 案例</a></li>
				<li><a href="${pageContext.request.contextPath}/api/report/list"><i class="ti-book m-r-10"></i> 报告</a></li>
				<li role="separator" class="divider"></li>
				<li><a href="${pageContext.request.contextPath}/api/task/list"><i class="ti-timer m-r-10"></i> 任务</a></li>
				<li><a href="${pageContext.request.contextPath}/api/setting/list"><i class="ti-settings m-r-10"></i> 设置</a></li>
			</ul>
			</li>
			<li class="dropdown"> <a class="dropdown-toggle profile-pic" data-toggle="dropdown" href="#"><b class="hidden-xs m-r-10">UI自动化</b> </a>
			<ul class="dropdown-menu dropdown-user animated flipInY">
				<li><a href="${pageContext.request.contextPath}/ui/code/page"><i class="fa fa-file-code-o m-r-10"></i> 编码</a></li>
				<li><a href="${pageContext.request.contextPath}/ui/list/list"><i class="fa fa-list m-r-10"></i> 列表</a></li>
				<li role="separator" class="divider"></li>
				<li><a href="${pageContext.request.contextPath}/ui/device/list"><i class="ti-mobile m-r-10"></i> 设备</a></li>
				<li><a href="${pageContext.request.contextPath}/ui/report/list"><i class="ti-book m-r-10"></i> 报告</a></li>
			</ul>
			</li>
			<li class="dropdown"> <a class="dropdown-toggle profile-pic" data-toggle="dropdown" href="#"><b class="hidden-xs m-r-10">测试工具</b> </a>
			<ul class="dropdown-menu dropdown-user animated flipInY">
				<li><a href="${pageContext.request.contextPath}/tool/war/page"><i class="fa fa-rocket m-r-10"></i> 更新包(后端部署)</a></li>
				<%-- <li><a href="${pageContext.request.contextPath}/tool/web/page"><i class="fa fa-tablet m-r-10"></i> 更新包(前端部署)</a></li> --%>
				<li><a href="${pageContext.request.contextPath}/tool/time/page"><i class="ti-timer m-r-10"></i> 工具(调整时间)</a></li>
			</ul>
			</li>
		</ul>
		<ul class="nav navbar-top-links navbar-right pull-right">
			<li class="dropdown"> <a class="dropdown-toggle profile-pic" data-toggle="dropdown" href="#"> <img src="${pageContext.request.contextPath}/plugins/images/img2.jpg" alt="user-img" width="36" class="img-rounded"><b class="hidden-xs m-r-10">${username}</b> </a>
			<ul class="dropdown-menu dropdown-user animated flipInY">
				<li><a href="${pageContext.request.contextPath}/user/logout"><i class="fa fa-power-off m-r-10"></i> 登出</a></li>
			</ul>
			</li>
		</ul>
	</div>
</nav>
