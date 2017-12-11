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
<title>项目测试状态</title>
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
          <h4 class="page-title"><i class="fa fa-pagelines m-r-10" style='color:green'></i><span><b style='color:black'>工具(项目测试状态)</b></span></h4>
        </div>
      </div>
      <!-- /row -->
      <div class="row">
	  	<div class="col-sm-12">
	  	  <div class="white-box">
	  	  	<!-- /.table -->
            <div class="table-responsive">
              <table id="tool-status-table" class="table">
                <thead>
                  <tr>
                    <th>ID</th>
                    <th><b class='label label-inverse'>项目</b></th>
                    <th><b class='label label-inverse'>子项目</b></th>
                    <th><b class='label label-inverse'>项目状态</b></th>
                    <th><b class='label label-inverse'>更新人</b></th>
                    <th><b class='label label-inverse'>操作</b></th>
                  </tr>
                </thead>
                <tbody>
                  <!-- <tr>
                  	<td rowspan="3"><b>wf_uic</b></td>
                  	<td><b>api</b></td>
                  	<td><label id="wf_uic_api"></label></td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_uic_api')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>rpc_api</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_uic_rpc_api')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>task</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_uic_task')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td rowspan="3"><b>wf_dart</b></td>
                  	<td><b>api</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_dart_api')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>task</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_dart_task')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>mqc</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_dart_mqc')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td rowspan="2"><b>wf_wars</b></td>
                  	<td><b>api</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_wars_api')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>task</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_wars_task')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td rowspan="1"><b>wf_tcard</b></td>
                  	<td><b>api</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_tcard_api')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td rowspan="3"><b>wf_kindom</b></td>
                  	<td><b>api</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_kindom_api')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>task</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_kindom_task')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>mqc</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_kindom_mqc')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td rowspan="2"><b>wf_fish</b></td>
                  	<td><b>api</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_fish_api')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>ws</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_fish_ws')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td rowspan="3"><b>wf_billiards</b></td>
                  	<td><b>api</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_billiards_api')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>task</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_billiards_task')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>rpc_api</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_billiards_rpc_api')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td rowspan="1"><b>wf_arrows</b></td>
                  	<td><b>api</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_arrows_api')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td rowspan="3"><b>wf_bike</b></td>
                  	<td><b>api</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_bike_api')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>task</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_bike_task')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>mqc</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_bike_mqc')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td rowspan="4"><b>wf_trans</b></td>
                  	<td><b>api</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_trans_api')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>mqc</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_trans_mqc')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>task</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_trans_task')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>rpc_api</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_trans_rpc_api')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td rowspan="5"><b>wf_platform</b></td>
                  	<td><b>app_api</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_platform_app_api')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>mqc</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_platform_mqc')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>task</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_platform_task')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>rpc_api</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_platform_rpc_api')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>wap_api</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_platform_wap_api')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td rowspan="4"><b>wf_shop</b></td>
                  	<td><b>api</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_shop_api')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>task</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_shop_task')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>rpc_api</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_shop_rpc_api')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>mqc</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_shop_rpc_mqc')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td rowspan="4"><b>wf_ops</b></td>
                  	<td><b>api</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_ops_api')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>task</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_ops_task')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>rpc_api</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_ops_rpc_api')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>mqc</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_ops_rpc_mqc')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td rowspan="1"><b>wf_open</b></td>
                  	<td><b>api</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_open_api')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td rowspan="1"><b>wf_base</b></td>
                  	<td><b>rpc_api</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_base_rpc_api')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td rowspan="3"><b>wf_quoits</b></td>
                  	<td><b>api</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_quoits_api')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>rpc_api</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_quoits_rpc_api')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>mqc</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_quoits_mqc')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td rowspan="4"><b>wf_data</b></td>
                  	<td><b>api</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_data_api')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>rpc_api</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_data_rpc_api')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>task</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_data_task')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>mqc</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('wf_data_mqc')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td rowspan="10"><b>ch002.open.doyo.cn</b></td>
                  	<td><b>\channel\wxpayment</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('ch002_open_doyo_cn_channel_wxpayment')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\wechat\billiards</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('ch002_open_doyo_cn_channel_wechat_billiards')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\wechat\dart</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('ch002_open_doyo_cn_channel_wechat_dart')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\wechat\fish</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('ch002_open_doyo_cn_channel_wechat_fish')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\wechat\home</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('ch002_open_doyo_cn_channel_wechat_home')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\wechat\kingdom</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('ch002_open_doyo_cn_channel_wechat_kingdom')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\wechat\legion</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('ch002_open_doyo_cn_channel_wechat_legion')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\wechat\moto</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('ch002_open_doyo_cn_channel_wechat_moto')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\wechat\ring</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('ch002_open_doyo_cn_channel_wechat_ring')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\wechat\samguk</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('ch002_open_doyo_cn_channel_wechat_samguk')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td rowspan="10"><b>com-code.pt.3gfly.net</b></td>
                  	<td><b>\channel\payment</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('com_code_pt_3gfly_net_channel_payment')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\weituo\billiards</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('com_code_pt_3gfly_net_channel_weituo_billiards')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\weituo\dart</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('com_code_pt_3gfly_net_channel_weituo_dart')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\weituo\fish</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('com_code_pt_3gfly_net_channel_weituo_fish')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\weituo\home</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('com_code_pt_3gfly_net_channel_weituo_home')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\weituo\kingdom</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('com_code_pt_3gfly_net_channel_weituo_kingdom')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\weituo\legion</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('com_code_pt_3gfly_net_channel_weituo_legion')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\weituo\moto</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('com_code_pt_3gfly_net_channel_weituo_moto')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\weituo\ring</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('com_code_pt_3gfly_net_channel_weituo_ring')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\weituo\samguk</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('com_code_pt_3gfly_net_channel_weituo_samguk')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td rowspan="39"><b>www.beeplay123.com</b></td>
                  	<td><b>\agreement</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_agreement')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\appnew</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_appnew')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\billiards</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_billiards')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\dart</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_dart')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\fish</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_fish')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\home</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_home')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\kingdom</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_kingdom')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\legion</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_legion')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\m</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_m')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\moto</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_moto')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\payment</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_payment')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\ring</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_ring')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\samguk</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_samguk')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\wap\home</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_wap_home')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\club\billiards</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_channel_club_billiards')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\club\dart</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_channel_club_dart')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\club\fish</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_channel_club_fish')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\club\kingdom</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_channel_club_kingdom')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\club\legion</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_channel_club_legion')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\club\moto</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_channel_club_moto')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\club\samguk</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_channel_club_samguk')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\newokooo\billiards</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_channel_newokooo_billiards')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\newokooo\dart</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_channel_newokooo_dart')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\newokooo\fish</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_channel_newokooo_fish')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\newokooo\home</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_channel_newokooo_home')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\newokooo\kingdom</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_channel_newokooo_kingdom')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\newokooo\legion</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_channel_newokooo_legion')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\newokooo\moto</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_channel_newokooo_moto')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\newokooo\ring</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_channel_newokooo_ring')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\newokooo\samguk</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_channel_newokooo_samguk')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\flyme\billiards</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_channel_flyme_billiards')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\flyme\dart</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_channel_flyme_dart')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\flyme\fish</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_channel_flyme_fish')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\flyme\home\meizu</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_channel_flyme_home/meizu')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\flyme\kingdom</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_channel_flyme_kingdom')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\flyme\legion</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_channel_flyme_legion')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\flyme\moto</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_channel_flyme_moto')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\flyme\ring</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_channel_flyme_ring')">更新状态</button></td>
                  </tr>
                  <tr>
                  	<td><b>\channel\flyme\samguk</b></td>
                  	<td>测试完成</td>
                  	<td>system</td>
                  	<td><button class="btn btn-info btn-outline" onclick="updateStatus('www_beeplay123_com_channel_flyme_samguk')">更新状态</button></td>
                  </tr> -->
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
<script src="${pageContext.request.contextPath}/eliteadmin/js/jasny-bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bower_components/datatables/jquery.dataTables.min.js"></script>
<script>

	$(document).ready(function(){
		createTable();
	});
	
	function createTable() {
    	$('#tool-status-table').dataTable().fnDestroy();
    	$('#tool-status-table').DataTable({
    		responsive : false,
    		sAjaxSource : "<%=request.getContextPath()%>/tool/status/list",
    		bProcessing : false,
    		"aaSorting": [
    			[0,'desc']
    		],
    		aoColumnDefs : [
    			{
					"sWidth" : "10%",
					"aTargets" : [ 0 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.id;
					}
				},
				{
					"sWidth" : "15%",
					"aTargets" : [ 1 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.root;
					}
				},
				{
					"sWidth" : "15%",
					"aTargets" : [ 2 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.name;
					}
				},
				{
					"sWidth" : "30%",
					"aTargets" : [ 3 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						if(data.status == "测试中"){
							return "<b class='text-success'>" + data.status + "...</b>";
						}else{
							return "<b class='text-info'>" + data.status + "</b>";
						}
					}
				},
				{
					"sWidth" : "20%",
					"aTargets" : [ 4 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return data.operator;
					}
				},
				{
					"sWidth" : "10%",
					"aTargets" : [ 5 ],
					"mData" : null,
					"sClass" : "text-center",
					"mRender" : function(data, type, full) {
						return "<button class=\"btn btn-info btn-outline\" onclick=\"updateStatus('" + data.id + "')\">更新状态</button>";
					}
				}
    		],
    	});
    }
	
	function updateStatus(id){
		$.ajax({
			type:"get",
      		url:"<%=request.getContextPath()%>/tool/status/update/id=" + id,
      		success:function(data){
      			if(data.responseCode == "0000"){
      				swal("成功", "更新成功！", "success");
      	    	}else{
      	    		swal("错误", data.responseMsg, "error");
      	    	}
      			$('#tool-status-table').dataTable()._fnAjaxUpdate();
      	    }
		});
	}
	
</script>
<!--Style Switcher -->
<script src="${pageContext.request.contextPath}/plugins/bower_components/styleswitcher/jQuery.style.switcher.js"></script>
</body>
</html>
