<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
<h2>项目【<%=request.getContextPath()%>】路径入口：</h2><br/>
<table style="width: 60%;" border="1">
	<tr>
		<td align="center">平台：</td>
		<td><a href="${pageContext.request.contextPath}/home/index">首页</a><br/></td>
		<td><a href="${pageContext.request.contextPath}/home/login">登录</a><br/></td>
		<td><a href="${pageContext.request.contextPath}/home/register">注册</a><br/></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td align="center">接口自动化：</td>
		<td><a href="${pageContext.request.contextPath}/api/project/list">接口项目</a><br/></td>
		<td><a href="${pageContext.request.contextPath}/api/version/list">接口版本</a><br/></td>
		<td><a href="${pageContext.request.contextPath}/api/interface/list">接口信息</a><br/></td>
		<td><a href="${pageContext.request.contextPath}/api/account/list">测试账号</a><br/></td>
		<td><a href="${pageContext.request.contextPath}/api/case/list">接口案例</a><br/></td>
		<td><a href="${pageContext.request.contextPath}/api/report/list">接口报告</a><br/></td>
		<td><a href="${pageContext.request.contextPath}/api/task/list">定时任务</a><br/></td>
		<td><a href="${pageContext.request.contextPath}/api/setting/list">接口设置</a><br/></td>
	</tr>
	<tr>
		<td align="center">UI自动化：</td>
		<td><a href="${pageContext.request.contextPath}/ui/device/list">UI设备</a><br/></td>
		<td><a href="${pageContext.request.contextPath}/ui/code/page">UI编码</a><br/></td>
		<td><a href="${pageContext.request.contextPath}/ui/list/list">编码列表</a><br/></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td align="center">测试工具：</td>
		<td><a href="${pageContext.request.contextPath}/tool/war/page">部署工具</a><br/></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
	</tr>
</table>
</body>
</html>
