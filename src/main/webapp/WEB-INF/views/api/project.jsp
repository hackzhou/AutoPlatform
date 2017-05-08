<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" type="image/png" sizes="16x16" href="../../../plugins/images/favicon.png">
<title>Project</title>
<!-- Bootstrap Core CSS -->
<link href="../../../eliteadmin/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="../../../plugins/bower_components/datatables/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />
<link href="https://cdn.datatables.net/buttons/1.2.2/css/buttons.dataTables.min.css" rel="stylesheet" type="text/css" />
<!-- Menu CSS -->
<link href="../../../plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css" rel="stylesheet">
<!-- animation CSS -->
<link href="../../../eliteadmin/css/animate.css" rel="stylesheet">
<!--alerts CSS -->
<link href="../../../plugins/bower_components/sweetalert/sweetalert.css" rel="stylesheet" type="text/css">
<!-- Custom CSS -->
<link href="../../../eliteadmin/css/style.css" rel="stylesheet">
<!-- color CSS -->
<link href="../../../eliteadmin/css/colors/blue.css" id="theme"  rel="stylesheet">
<!-- <script src="http://www.w3schools.com/lib/w3data.js"></script> -->
</head>
<body>
<!-- Preloader -->
<div class="preloader">
  <div class="cssload-speeding-wheel"></div>
</div>
<div id="wrapper">
  <!-- Top Navigation -->
  <nav class="navbar navbar-default navbar-static-top m-b-0">
    <div class="navbar-header"> <a class="navbar-toggle hidden-sm hidden-md hidden-lg " href="javascript:void(0)" data-toggle="collapse" data-target=".navbar-collapse"><i class="ti-menu"></i></a>
      <div class="top-left-part"><a class="logo" href="../index.html"><b><img src="../../../plugins/images/eliteadmin-logo.png" alt="home" /></b><span class="hidden-xs">多多游戏测试平台</span></a></div>
      <ul class="nav navbar-top-links navbar-left hidden-xs">
        <li><a href="javascript:void(0)" class="open-close hidden-xs waves-effect waves-light"><i class="icon-arrow-left-circle ti-menu"></i></a></li>
        <li class="dropdown"> <a class="dropdown-toggle profile-pic" data-toggle="dropdown" href="#"><b class="hidden-xs">接口自动化</b> </a>
          <ul class="dropdown-menu dropdown-user animated flipInY">
            <li><a href="project.html"><i class="ti-briefcase"></i> Project</a></li>
            <li><a href="case.html"><i class="ti-write"></i> Case</a></li>
            <li><a href="report.html"><i class="ti-book"></i> Report</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="setting.html"><i class="ti-settings"></i> Setting</a></li>
          </ul>
        </li>
        <li class="dropdown"> <a class="dropdown-toggle profile-pic" data-toggle="dropdown" href="#"><b class="hidden-xs">UI自动化</b> </a>
        </li>
        <li class="dropdown"> <a class="dropdown-toggle profile-pic" data-toggle="dropdown" href="#"><b class="hidden-xs">测试工具</b> </a>
        </li>
      </ul>
      <ul class="nav navbar-top-links navbar-right pull-right">
        <li class="dropdown"> <a class="dropdown-toggle profile-pic" data-toggle="dropdown" href="#"> <img src="../../../plugins/images/users/varun.jpg" alt="user-img" width="36" class="img-circle"><b class="hidden-xs">Steave</b> </a>
          <ul class="dropdown-menu dropdown-user animated flipInY">
            <li><a href="#"><i class="fa fa-power-off"></i> Logout</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </nav>
  <!-- Page Content -->
  <div id="page-wrapper">
    <div class="container-fluid">
      <div class="row bg-title">
        <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
          <!-- <h4 class="page-title">Data Table</h4> -->
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
					  <label class="control-label col-md-2">Name:</label>
					  <div class="col-md-9">
	                    <input type="text" class="form-control" placeholder="name">
					  </div>
	                </div>
	              </div>
	              <div class="col-md-9">
	              	<!-- /.Create Project -->
		            <div class="button-box text-right">
		              <button type="button" class="btn btn-info btn-outline" data-toggle="modal" data-target="#exampleModal" data-whatever="@fat">Add Project</button>
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
            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel1">
              <div class="modal-dialog" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="exampleModalLabel1">Add Project</h4>
                  </div>
                  <div class="modal-body">
                    <from class="form-horizontal form-material">
	                    <div class="form-group">
	                      <div class="col-md-12 m-b-20">
	                        <input type="text" class="form-control" placeholder="Type name">
	                      </div>
	                      <div class="col-md-12 m-b-20">
	                        <input type="text" class="form-control" placeholder="Email">
	                      </div>
	                      <div class="col-md-12 m-b-20">
	                        <input type="text" class="form-control" placeholder="Phone">
	                      </div>
	                      <div class="col-md-12 m-b-20">
	                        <input type="text" class="form-control" placeholder="Designation">
	                      </div>
	                      <div class="col-md-12 m-b-20">
	                        <input type="text" class="form-control" placeholder="Age">
	                      </div>
	                      <div class="col-md-12 m-b-20">
	                        <input type="text" class="form-control" placeholder="Date of joining">
	                      </div>
	                      <div class="col-md-12 m-b-20">
	                        <input type="text" class="form-control" placeholder="Salary">
	                      </div>
	                      <div class="col-md-12 m-b-20">
	                        <div class="fileupload btn btn-danger btn-rounded waves-effect waves-light"><span><i class="ion-upload m-r-5"></i>Upload Contact Image</span>
	                          <input type="file" class="upload">
	                        </div>
	                      </div>
	                    </div>
	                </from>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-info waves-effect" data-dismiss="modal">Save</button>
                    <button type="button" class="btn btn-default waves-effect" data-dismiss="modal">Cancel</button>
                  </div>
                </div>
              </div>
            </div>
			<!-- /.table -->
            <div class="table-responsive">
            <table id="myTable" class="table table-striped">
              <thead>
                <tr>
                  <th>No</th>
                  <th>Name</th>
                  <th>Position</th>
                  <th>Office</th>
                  <th>Age</th>
                  <th>Start date</th>
                  <th>Salary</th>
                  <th>Action</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>1</td>
                  <td>Tiger Nixon</td>
                  <td>System Architect</td>
                  <td>Edinburgh</td>
                  <td>61</td>
                  <td>2011/04/25</td>
                  <td>$320,800</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>2</td>
                  <td>Garrett Winters</td>
                  <td>Accountant</td>
                  <td>Tokyo</td>
                  <td>63</td>
                  <td>2011/07/25</td>
                  <td>$170,750</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>3</td>
                  <td>Ashton Cox</td>
                  <td>Junior Technical Author</td>
                  <td>San Francisco</td>
                  <td>66</td>
                  <td>2009/01/12</td>
                  <td>$86,000</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>4</td>
                  <td>Cedric Kelly</td>
                  <td>Senior Javascript Developer</td>
                  <td>Edinburgh</td>
                  <td>22</td>
                  <td>2012/03/29</td>
                  <td>$433,060</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>5</td>
                  <td>Airi Satou</td>
                  <td>Accountant</td>
                  <td>Tokyo</td>
                  <td>33</td>
                  <td>2008/11/28</td>
                  <td>$162,700</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>6</td>
                  <td>Brielle Williamson</td>
                  <td>Integration Specialist</td>
                  <td>New York</td>
                  <td>61</td>
                  <td>2012/12/02</td>
                  <td>$372,000</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>7</td>
                  <td>Herrod Chandler</td>
                  <td>Sales Assistant</td>
                  <td>San Francisco</td>
                  <td>59</td>
                  <td>2012/08/06</td>
                  <td>$137,500</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>8</td>
                  <td>Rhona Davidson</td>
                  <td>Integration Specialist</td>
                  <td>Tokyo</td>
                  <td>55</td>
                  <td>2010/10/14</td>
                  <td>$327,900</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>9</td>
                  <td>Colleen Hurst</td>
                  <td>Javascript Developer</td>
                  <td>San Francisco</td>
                  <td>39</td>
                  <td>2009/09/15</td>
                  <td>$205,500</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>10</td>
                  <td>Sonya Frost</td>
                  <td>Software Engineer</td>
                  <td>Edinburgh</td>
                  <td>23</td>
                  <td>2008/12/13</td>
                  <td>$103,600</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>11</td>
                  <td>Jena Gaines</td>
                  <td>Office Manager</td>
                  <td>London</td>
                  <td>30</td>
                  <td>2008/12/19</td>
                  <td>$90,560</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>12</td>
                  <td>Quinn Flynn</td>
                  <td>Support Lead</td>
                  <td>Edinburgh</td>
                  <td>22</td>
                  <td>2013/03/03</td>
                  <td>$342,000</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>13</td>
                  <td>Charde Marshall</td>
                  <td>Regional Director</td>
                  <td>San Francisco</td>
                  <td>36</td>
                  <td>2008/10/16</td>
                  <td>$470,600</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>14</td>
                  <td>Haley Kennedy</td>
                  <td>Senior Marketing Designer</td>
                  <td>London</td>
                  <td>43</td>
                  <td>2012/12/18</td>
                  <td>$313,500</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>15</td>
                  <td>Tatyana Fitzpatrick</td>
                  <td>Regional Director</td>
                  <td>London</td>
                  <td>19</td>
                  <td>2010/03/17</td>
                  <td>$385,750</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>16</td>
                  <td>Michael Silva</td>
                  <td>Marketing Designer</td>
                  <td>London</td>
                  <td>66</td>
                  <td>2012/11/27</td>
                  <td>$198,500</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>17</td>
                  <td>Paul Byrd</td>
                  <td>Chief Financial Officer (CFO)</td>
                  <td>New York</td>
                  <td>64</td>
                  <td>2010/06/09</td>
                  <td>$725,000</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>18</td>
                  <td>Gloria Little</td>
                  <td>Systems Administrator</td>
                  <td>New York</td>
                  <td>59</td>
                  <td>2009/04/10</td>
                  <td>$237,500</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>19</td>
                  <td>Bradley Greer</td>
                  <td>Software Engineer</td>
                  <td>London</td>
                  <td>41</td>
                  <td>2012/10/13</td>
                  <td>$132,000</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>20</td>
                  <td>Dai Rios</td>
                  <td>Personnel Lead</td>
                  <td>Edinburgh</td>
                  <td>35</td>
                  <td>2012/09/26</td>
                  <td>$217,500</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>21</td>
                  <td>Jenette Caldwell</td>
                  <td>Development Lead</td>
                  <td>New York</td>
                  <td>30</td>
                  <td>2011/09/03</td>
                  <td>$345,000</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>22</td>
                  <td>Yuri Berry</td>
                  <td>Chief Marketing Officer (CMO)</td>
                  <td>New York</td>
                  <td>40</td>
                  <td>2009/06/25</td>
                  <td>$675,000</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>23</td>
                  <td>Caesar Vance</td>
                  <td>Pre-Sales Support</td>
                  <td>New York</td>
                  <td>21</td>
                  <td>2011/12/12</td>
                  <td>$106,450</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>24</td>
                  <td>Doris Wilder</td>
                  <td>Sales Assistant</td>
                  <td>Sidney</td>
                  <td>23</td>
                  <td>2010/09/20</td>
                  <td>$85,600</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>25</td>
                  <td>Angelica Ramos</td>
                  <td>Chief Executive Officer (CEO)</td>
                  <td>London</td>
                  <td>47</td>
                  <td>2009/10/09</td>
                  <td>$1,200,000</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>26</td>
                  <td>Gavin Joyce</td>
                  <td>Developer</td>
                  <td>Edinburgh</td>
                  <td>42</td>
                  <td>2010/12/22</td>
                  <td>$92,575</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>27</td>
                  <td>Jennifer Chang</td>
                  <td>Regional Director</td>
                  <td>Singapore</td>
                  <td>28</td>
                  <td>2010/11/14</td>
                  <td>$357,650</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>28</td>
                  <td>Brenden Wagner</td>
                  <td>Software Engineer</td>
                  <td>San Francisco</td>
                  <td>28</td>
                  <td>2011/06/07</td>
                  <td>$206,850</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>29</td>
                  <td>Fiona Green</td>
                  <td>Chief Operating Officer (COO)</td>
                  <td>San Francisco</td>
                  <td>48</td>
                  <td>2010/03/11</td>
                  <td>$850,000</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>30</td>
                  <td>Shou Itou</td>
                  <td>Regional Marketing</td>
                  <td>Tokyo</td>
                  <td>20</td>
                  <td>2011/08/14</td>
                  <td>$163,000</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>31</td>
                  <td>Michelle House</td>
                  <td>Integration Specialist</td>
                  <td>Sidney</td>
                  <td>37</td>
                  <td>2011/06/02</td>
                  <td>$95,400</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>32</td>
                  <td>Suki Burks</td>
                  <td>Developer</td>
                  <td>London</td>
                  <td>53</td>
                  <td>2009/10/22</td>
                  <td>$114,500</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>33</td>
                  <td>Prescott Bartlett</td>
                  <td>Technical Author</td>
                  <td>London</td>
                  <td>27</td>
                  <td>2011/05/07</td>
                  <td>$145,000</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>34</td>
                  <td>Gavin Cortez</td>
                  <td>Team Leader</td>
                  <td>San Francisco</td>
                  <td>22</td>
                  <td>2008/10/26</td>
                  <td>$235,500</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>35</td>
                  <td>Martena Mccray</td>
                  <td>Post-Sales support</td>
                  <td>Edinburgh</td>
                  <td>46</td>
                  <td>2011/03/09</td>
                  <td>$324,050</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>36</td>
                  <td>Unity Butler</td>
                  <td>Marketing Designer</td>
                  <td>San Francisco</td>
                  <td>47</td>
                  <td>2009/12/09</td>
                  <td>$85,675</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>37</td>
                  <td>Howard Hatfield</td>
                  <td>Office Manager</td>
                  <td>San Francisco</td>
                  <td>51</td>
                  <td>2008/12/16</td>
                  <td>$164,500</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>38</td>
                  <td>Hope Fuentes</td>
                  <td>Secretary</td>
                  <td>San Francisco</td>
                  <td>41</td>
                  <td>2010/02/12</td>
                  <td>$109,850</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>39</td>
                  <td>Vivian Harrell</td>
                  <td>Financial Controller</td>
                  <td>San Francisco</td>
                  <td>62</td>
                  <td>2009/02/14</td>
                  <td>$452,500</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>40</td>
                  <td>Timothy Mooney</td>
                  <td>Office Manager</td>
                  <td>London</td>
                  <td>37</td>
                  <td>2008/12/11</td>
                  <td>$136,200</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>41</td>
                  <td>Jackson Bradshaw</td>
                  <td>Director</td>
                  <td>New York</td>
                  <td>65</td>
                  <td>2008/09/26</td>
                  <td>$645,750</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>42</td>
                  <td>Olivia Liang</td>
                  <td>Support Engineer</td>
                  <td>Singapore</td>
                  <td>64</td>
                  <td>2011/02/03</td>
                  <td>$234,500</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>43</td>
                  <td>Bruno Nash</td>
                  <td>Software Engineer</td>
                  <td>London</td>
                  <td>38</td>
                  <td>2011/05/03</td>
                  <td>$163,500</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>44</td>
                  <td>Sakura Yamamoto</td>
                  <td>Support Engineer</td>
                  <td>Tokyo</td>
                  <td>37</td>
                  <td>2009/08/19</td>
                  <td>$139,575</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                  <tr><td>45</td>
                  <td>Thor Walton</td>
                  <td>Developer</td>
                  <td>New York</td>
                  <td>61</td>
                  <td>2013/08/11</td>
                  <td>$98,540</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>46</td>
                  <td>Finn Camacho</td>
                  <td>Support Engineer</td>
                  <td>San Francisco</td>
                  <td>47</td>
                  <td>2009/07/07</td>
                  <td>$87,500</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>47</td>
                  <td>Serge Baldwin</td>
                  <td>Data Coordinator</td>
                  <td>Singapore</td>
                  <td>64</td>
                  <td>2012/04/09</td>
                  <td>$138,575</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>48</td>
                  <td>Zenaida Frank</td>
                  <td>Software Engineer</td>
                  <td>New York</td>
                  <td>63</td>
                  <td>2010/01/04</td>
                  <td>$125,250</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>49</td>
                  <td>Zorita Serrano</td>
                  <td>Software Engineer</td>
                  <td>San Francisco</td>
                  <td>56</td>
                  <td>2012/06/01</td>
                  <td>$115,000</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>50</td>
                  <td>Jennifer Acosta</td>
                  <td>Junior Javascript Developer</td>
                  <td>Edinburgh</td>
                  <td>43</td>
                  <td>2013/02/01</td>
                  <td>$75,650</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>51</td>
                  <td>Cara Stevens</td>
                  <td>Sales Assistant</td>
                  <td>New York</td>
                  <td>46</td>
                  <td>2011/12/06</td>
                  <td>$145,600</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>52</td>
                  <td>Hermione Butler</td>
                  <td>Regional Director</td>
                  <td>London</td>
                  <td>47</td>
                  <td>2011/03/21</td>
                  <td>$356,250</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>53</td>
                  <td>Lael Greer</td>
                  <td>Systems Administrator</td>
                  <td>London</td>
                  <td>21</td>
                  <td>2009/02/27</td>
                  <td>$103,500</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>54</td>
                  <td>Jonas Alexander</td>
                  <td>Developer</td>
                  <td>San Francisco</td>
                  <td>30</td>
                  <td>2010/07/14</td>
                  <td>$86,500</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>55</td>
                  <td>Shad Decker</td>
                  <td>Regional Director</td>
                  <td>Edinburgh</td>
                  <td>51</td>
                  <td>2008/11/13</td>
                  <td>$183,000</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>56</td>
                  <td>Michael Bruce</td>
                  <td>Javascript Developer</td>
                  <td>Singapore</td>
                  <td>29</td>
                  <td>2011/06/27</td>
                  <td>$183,000</td>
                  <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                <tr>
                  <td>57</td>
                  <td>Donna Snider</td>
                  <td>Customer Support</td>
                  <td>New York</td>
                  <td>27</td>
                  <td>2011/01/25</td>
                  <td>$112,000</td>
                <td class="text-nowrap">
                  	<a href="#" data-toggle="tooltip" data-original-title="Edit"> <i class="fa fa-pencil text-inverse m-r-10"></i> </a> 
                  	<a href="#" data-toggle="tooltip" data-original-title="Close"> <i class="fa fa-close text-danger" id="sa-warning"></i> </a> 
                  </td>
                </tr>
                </tbody>
            </table>
            </div>
          </div>
        </div>
    </div>
    <!-- /.container-fluid -->
    <footer class="footer text-center"> 2016 &copy; Elite Admin brought to you by themedesigner.in </footer>
  </div>
  <!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->
<!-- jQuery -->
<script src="../../../plugins/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="../../../eliteadmin/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- Menu Plugin JavaScript -->
<script src="../../../plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></script>
<!--slimscroll JavaScript -->
<script src="../../../eliteadmin/js/jquery.slimscroll.js"></script>
<!--Wave Effects -->
<script src="../../../eliteadmin/js/waves.js"></script>
<!-- Sweet-Alert  -->
<script src="../../../plugins/bower_components/sweetalert/sweetalert.min.js"></script>
<script src="../../../plugins/bower_components/sweetalert/jquery.sweet-alert.custom.js"></script>
<!-- Custom Theme JavaScript -->
<script src="../../../eliteadmin/js/custom.min.js"></script>
<script src="../../../plugins/bower_components/datatables/jquery.dataTables.min.js"></script>
<!-- start - This is for export functionality only -->
<script src="https://cdn.datatables.net/buttons/1.2.2/js/dataTables.buttons.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.2.2/js/buttons.flash.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js"></script>
<script src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/pdfmake.min.js"></script>
<script src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/vfs_fonts.js"></script>
<script src="https://cdn.datatables.net/buttons/1.2.2/js/buttons.html5.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.2.2/js/buttons.print.min.js"></script>
<!-- end - This is for export functionality only -->
<script>

    $(document).ready(function(){
      $('#myTable').DataTable();
    });

</script>
<!--Style Switcher -->
<script src="../../../plugins/bower_components/styleswitcher/jQuery.style.switcher.js"></script>
</body>
</html>
