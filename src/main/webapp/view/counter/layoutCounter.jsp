<%--
  Created by IntelliJ IDEA.
  User: LEGION
  Date: 6/20/2022
  Time: 4:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar-default navbar-static-side" role="navigation">
    <div class="sidebar-collapse">
        <ul class="nav metismenu" id="side-menu">
            <li class="nav-header">
                <div class="dropdown profile-element">
                    <img alt="image" class="rounded-circle" height="50px;" src="<c:url value="/images/Background.png"/> " />
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <span class="block m-t-xs font-bold">Counter<b class="caret"></b></span>
                    </a>
                    <ul class="dropdown-menu animated fadeInRight m-t-xs">
                        <li>
                            <a class="dropdown-item" href="profile.html">Store profile</a>
                        </li>
                        <li class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="<c:url value="/account/logout"/>">Logout</a></li>
                    </ul>
                </div>
                <div class="logo-element">FMS</div>
            </li>

            <li class="active">
                <a href="<c:url value="/counter/index"/>">
                    <i class="fa fa-id-card"></i>
                    <span class="nav-label">Counter</span></a>
            </li>
            <li class="active">

                <a href="<c:url value="/customer/list"/>">
                    <i class="fa fa-user-o"></i>
                    <span class="nav-label">Customer</span></a>
            </li>
        </ul>
    </div>
</nav>


<div id="page-wrapper" class="gray-bg">
    <div class="row border-bottom">
        <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <a class="navbar-minimalize minimalize-styl-2 btn btn-primary" href="#"><i class="fa fa-bars"></i></a>
            </div>
            <ul class="nav navbar-top-links navbar-right">
                <li>
                    <a href="<c:url value="/account/logout"/>"> <i class="fa fa-sign-out"></i> Log out </a>
                </li>
            </ul>
        </nav>
    </div>
<%--</div>--%>