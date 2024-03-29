<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="contextPath" value="${requestScope.contextPath}"></c:url>
<nav class="navbar-default navbar-static-side" role="navigation" >
    <div class="sidebar-collapse">
        <ul class="nav metismenu" id="side-menu" >
            <li class="nav-header">
                <div class="dropdown profile-element">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
            <span class="block m-t-xs font-bold"
            >${sessionScope.storeSession.name} <b class="caret"></b
            ></span>
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
                <c:url var="storeDashBoardLink" value="${requestScope.contextPath}/dashboard/store"></c:url>
                <a href="${storeDashBoardLink}"
                ><i class="fa fa-home"></i> <span class="nav-label">Home</span></a
                >
            </li>
            <li class="active">

                <c:url var="productListLink" value="${requestScope.contextPath}/product/list"></c:url>
                <a href="${productListLink}"
                ><i class="fa fa-dropbox"></i>
                    <span class="nav-label">Products</span></a
                >
            </li>
            <li class="active">
                <c:url var="orderListLink" value="${requestScope.contextPath}/order/list"></c:url>
                <a href="${orderListLink}"
                ><i class="fa fa-table"></i> <span class="nav-label">Orders</span></a
                >
            </li>
            <li class="active">
                <c:url var="transactionListLink" value="${requestScope.contextPath}/transactionShared/list"></c:url>
                <a href="${transactionListLink}"
                ><i class="fa fa-money"></i>
                    <span class="nav-label">Transaction</span></a
                >
            </li>
        </ul>
    </div>
</nav>
<%--Left nav--%>

<!-- Body -->
<%--Top nav--%>
<div id="page-wrapper" class="gray-bg">
    <div class="row border-bottom" >
        <nav
                class="navbar navbar-static-top"
                role="navigation"
                style="margin-bottom: 0px"
        >
            <div class="navbar-header">
                <a class="navbar-minimalize minimalize-styl-2 btn btn-primary" href="#"
                ><i class="fa fa-bars"></i
                ></a>
            </div>
            <ul class="nav navbar-top-links navbar-right" >
                <li>
                    <a href="<c:url value="/account/logout"/>"> <i class="fa fa-sign-out"></i> Log out </a>
                </li>
            </ul>
        </nav>
    </div>
    <%--Top nav--%>

    <!-- Content -->

    <!-- Content -->

    <%--
  </div>
  --%>
