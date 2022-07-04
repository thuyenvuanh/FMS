<%--
  Created by IntelliJ IDEA.
  User: LEGION
  Date: 6/18/2022
  Time: 2:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar-default navbar-static-side" role="navigation">
    <div class="sidebar-collapse">
        <ul class="nav metismenu" id="side-menu">
            <li class="nav-header">
                <div class="dropdown profile-element">
                    <img alt="image" class="rounded-circle" src="img/profile_small.html" />
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <span class="block m-t-xs font-bold">Admin <b class="caret"></b></span>
                    </a>
                    <ul class="dropdown-menu animated fadeInRight m-t-xs">
                        <li><a class="dropdown-item" href="profile.html">Profile</a></li>
                        <li class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="login.html">Logout</a></li>
                    </ul>
                </div>
                <div class="logo-element">
                    FMS
                </div>
            </li>


            <li class="active">
                <a href="layout.html"><i class="fa fa-home"></i> <span class="nav-label">Home</span></a>
            </li>

            <li class="active"><a href="transaction_list.html"><i class="fa fa-money"></i> <span
                    class="nav-label">Transaction</span></a></li>
            <c:url var="storeListLink" value="${requestScope.contextPath}/store/list"></c:url>
            <li class="active"><a href="${storeListLink}"><i class="fa fa-table"></i> <span
                    class="nav-label">Store</span></a></li>
            <c:url var="accountListLink" value="${requestScope.contextPath}/account/list"></c:url>
            <li class="active"><a href="${accountListLink}"><i class="fa fa-user"></i> <span
                    class="nav-label">Account</span></a></li>


        </ul>

    </div>
</nav>

<div id="page-wrapper" class="gray-bg">
    <div class="row border-bottom">
        <nav
                class="navbar navbar-static-top"
                role="navigation"
                style="margin-bottom: 0"
        >
            <div class="navbar-header">
                <a class="navbar-minimalize minimalize-styl-2 btn btn-primary" href="#"
                ><i class="fa fa-bars"></i
                ></a>
            </div>
            <ul class="nav navbar-top-links navbar-right">
                <li>
                    <a href="<c:url value="/account/logout"/>"> <i class="fa fa-sign-out"></i> Log out </a>
                </li>
            </ul>
        </nav>
    </div>

<%--</div>--%>