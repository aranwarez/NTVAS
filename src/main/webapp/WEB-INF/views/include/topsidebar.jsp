<%@page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- Logo -->
<a href="../dashboard/list" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
	<span class="logo-mini"><b>N</b>TC</span> <!-- logo for regular state and mobile devices -->
	<span class="logo-lg"> ${menu_name} </span>
</a>
<!-- Header Navbar: style can be found in header.less -->
<nav class="navbar navbar-static-top">
	<!-- Sidebar toggle button-->
	<a href="#" class="sidebar-toggle" data-toggle="push-menu"
		role="button"> <span class="sr-only">Toggle navigation</span> <span
		class="icon-bar"></span> <span class="icon-bar"></span> <span
		class="icon-bar"></span>
	</a>

	<div class="navbar-custom-menu">
		<ul class="nav navbar-nav">

			<!-- User Account: style can be found in dropdown.less -->
			<li class="dropdown user user-menu"><a href="#"
				class="dropdown-toggle" data-toggle="dropdown"> <img
					src="<c:url value="/resources/adminltd/dist/img/avatar5.png" />"
					class="user-image" alt="User Image"> <span class="hidden-xs">${UserList.getFULL_NAME()}</span>
			</a>
				<ul class="dropdown-menu">
					<!-- User image -->
					<li class="user-header"><img
						src="<c:url value="/resources/adminltd/dist/img/avatar5.png" />"
						class="img-circle" alt="User Image">

						<p>${UserList.getUSER_ID()}</p></li>
					<!-- Menu Body -->
					<li class="user-body">
						<div class="row">
							<div class="col-xs-4 text-center">
								<a href="#">Total Transaction</a>
							</div>
							<div class="col-xs-4 text-center">
								<a href="#">Sales</a>
							</div>
							<div class="col-xs-4 text-center">
								<a href="#">Today Transaction</a>
							</div>
						</div> <!-- /.row -->
					</li>
					<!-- Menu Footer-->
					<li class="user-footer">
						<div class="pull-left">
							<a href="#" class="btn btn-default btn-flat">Profile</a>
						</div>
						<div class="pull-right">
							<a href="../Logout" class="btn btn-default btn-flat">Sign out</a>
						</div>
					</li>
				</ul></li>
			<!-- Control Sidebar Toggle Button -->
			<li><a href="#" data-toggle="control-sidebar"><i
					class="fa fa-gears"></i></a></li>
		</ul>
	</div>
</nav>