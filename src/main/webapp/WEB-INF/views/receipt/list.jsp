<%@page session="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:url var="home" value="/" scope="request" />

<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Nepal Telecom | ${fx}</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta
            content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
            name="viewport">
        <jsp:include page="${request.contextPath}/headCss"></jsp:include>
        </head>
        <body class="hold-transition skin-blue sidebar-mini">
            <div class="wrapper">

                <header class="main-header">
                <jsp:include page="${request.contextPath}/topmenu"></jsp:include>
                </header>
                <!-- Left side column. contains the logo and sidebar -->
                <aside class="main-sidebar">
                    <!-- sidebar: style can be found in sidebar.less -->
                    <section class="sidebar">
                        <!-- Sidebar user panel -->
                        <div class="user-panel">
                            <div class="pull-left image">
                                <img
                                    src="<c:url value="/resources/adminltd/dist/img/user2-160x160.jpg" />"
                                class="img-circle" alt="User Image">
                        </div>
                        <div class="pull-left info">
                            <p>NABIN</p>
                            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                        </div>
                    </div>
                    <!-- search form -->
                    <form action="#" method="get" class="sidebar-form">
                        <div class="input-group">
                            <input type="text" name="q" class="form-control"
                                   placeholder="Search..."> <span class="input-group-btn">
                                <button type="submit" name="search" id="search-btn"
                                        class="btn btn-flat">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                        </div>
                    </form>
                    <!-- /.search form -->
                    <!-- sidebar menu: : style can be found in sidebar.less -->
                    <ul class="sidebar-menu" data-widget="tree">
                        <li class="header">MAIN NAVIGATION</li>
                            <jsp:include page="${request.contextPath}/leftmenu"></jsp:include>
                        </ul>
                    </section>
                    <!-- /.sidebar -->
                </aside>

                <!-- Content Wrapper. Contains page content -->
                <div class="content-wrapper">
                    <!-- Content Header (Page header) -->
                    <section class="content-header">
                        <h1>Receipt</h1>
                        <ol class="breadcrumb">
                            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                            <li><a href="#">Tables</a></li>
                            <li class="active">Data tables</li>
                        </ol>
                        <br/>
                        <br/>
                        <table>
                            <tr>
                                <td>Service Provider </td>      
                                <td>  
                                    <select style="width: 400px;" name="QSP_CODE" id="QSP_CODE"> 
                                        <option value=''>Select :</option>
                                    <c:forEach var="SP" items="${Sp_list}">
                                        <option value="${SP.SP_CODE}">${SP.SP_NAME} ${SP.SP_CODE}</option>
                                    </c:forEach>
                                </select>
                            </td> 

                            <td>Cancel Flag</td>      
                            <td>  
                                <select  style="width: 100px;" name="QPOST_FLAG" id="QPOST_FLAG" > 
                                    <option value='N'>N</option>
                                    <option value='Y'>Y</option>
                                    <option value=''>All</option>
                                </select>
                            </td>
                        </tr>
                        <tr>  <c:forEach var="DAT" items="${Date_list}">
                                <td>FromDt</td>      
                                <td>  
                                    <input type="text" style="width: 150px;" value="${DAT.NEP_FROM_DATE}" class="nepali-calendar form-control"  name="QFROM_DT"
                                           id="QFROM_DT" placeholder="Enter Transaction from dt."> 
                                </td>
                                <td>To Dt</td>      
                                <td>  
                                    <input type="text" style="width: 150px;" value="${DAT.NEP_TODAY_DATE}" class="nepali-calendar form-control" name="QTO_DT"
                                           id="QTO_DT" placeholder="Enter Transaction to dt."> 
                                </td>

                                <td>
                                    <input type="button" style="width: 150px;" value="QUERY" onclick="getReceiptFilterList();">    
                                </td>
                            </c:forEach>
                        </tr>
                    </table> 

                    <a href="#" class="btn btn-primary pull-right" data-toggle="modal"
                       data-target="#myModal"> <i class="fa fa-plus"></i> Add
                    </a>
                </section>

                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-xs-12">

                            <!-- /.box -->

                            <div class="box">
                                <div class="box-header">
                                    <h3 class="box-title">${fx}</h3>
                                </div>

                                <%
                                    if (request.getParameter("sucess") != null) {
                                %>>
                                <div class="alert alert-success">
                                    <strong> <%=request.getParameter("sucess")%>
                                    </strong>
                                </div>
                                <%
                                    }
                                %>
                                <%
                                    if (request.getParameter("error") != null) {
                                %>>
                                <div class="alert alert-danger">
                                    <strong> <%=request.getParameter("error")%>
                                    </strong>
                                </div>
                                <%
                                    }
                                %>
                                <div class="overlay">
                                    <i class="fa fa-refresh fa-spin"></i>
                                </div>
                                <!-- /.box-header -->
                                <div class="box-body table-responsive">
                                    <table id="example1" class="table table-bordered table-striped">
                                        <thead>
                                            <tr>
                                                <th>ReceiptNo.</th>
                                                <th>Date</th>
                                                <th>SP Code</th>
                                                <th>Bank</th>
                                                <th>BankName</th>
                                                <th>ChequeNo.</th>
                                                <th>Receipt Amt</th>
                                                <th>Remarks</th>
                                                <th>Create By</th>
                                                <th>Create Dt</th>
                                                <th>Cancel By</th>
                                                <th>Cancel Dt</th>
                                                <th>View</th>
                                                <th>Cancel</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="user" items="${data_list}">
                                                <tr>
                                                    <td>${user.RECEIPT_NO}</td>
                                                    <td>${user.NEP_DT}</td>
                                                    <td>${user.S_NO}</td>
                                                    <td>${user.BANK_CD}</td>
                                                    <td>${user.BANK_NAME}</td>
                                                    <td>${user.CHEQUE_NO}</td>
                                                    <td>${user.PAID_AMT}</td>
                                                    <td>${user.REMARKS}</td>
                                                    <td>${user.CREATE_BY}</td>
                                                    <td>${user.CREATE_DT}</td>
                                                    <td>${user.CANCEL_BY}</td>
                                                    <td>${user.CANCEL_DT}</td>
                                                    <td>
                                                        <div class="btn-group">
                                                            <a href="#" class="btn btn-info" data-toggle="modal"
                                                               data-target="#editModal"
                                                               onclick="return editReceipt('${user.RECEIPT_NO}')"> <i
                                                                    class="fa fa-edit"></i> View
                                                            </a>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div>
                                                            <a href="" class="btn btn-default" data-toggle="modal"
                                                               data-target="#deleteModal"
                                                               onclick="return deleteReceipt('${user.RECEIPT_NO}')"> <i
                                                                    class="fa fa-trash"></i> Cancel
                                                            </a>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- /.box-body -->
                            </div>
                            <!-- /.box -->
                        </div>
                        <!-- /.col -->
                    </div>
                    <!-- /.row -->
                </section>
                <!-- /.content -->
            </div>
            <!-- /.content-wrapper -->
            <footer class="main-footer">
                <jsp:include page="${request.contextPath}/footer"></jsp:include>
                </footer>

                <!-- Control Sidebar -->
                <aside class="control-sidebar control-sidebar-dark">
                    <!-- Create the tabs -->
                    <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
                        <li><a href="#control-sidebar-home-tab" data-toggle="tab"><i
                                    class="fa fa-home"></i></a></li>
                        <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i
                                    class="fa fa-gears"></i></a></li>
                    </ul>

                </aside>
                <!-- /.control-sidebar -->
                <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
                <div class="control-sidebar-bg"></div>
            </div>
            <!-- ./wrapper -->

        <jsp:include page="${request.contextPath}/dialogreceipt"></jsp:include>

        <jsp:include page="${request.contextPath}/footJS"></jsp:include>

            <script>
                $(function () {

                    $('#example1').DataTable();

                })
            </script>
            <script src="<c:url value="/resources/function/receipt.js" />"></script>
        <script src="<c:url value="/resources/adminltd/js/commonajax.js" />"></script>
    </body>
</html>
