<%--
  Created by IntelliJ IDEA.
  User: ttcha
  Date: 6/18/2022
  Time: 3:30 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Customer List</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet"/>
    <link href="../font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <%--    Jquery--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- FooTable -->
    <link href="../css/plugins/footable/footable.core.css" rel="stylesheet"/>

    <link href="../css/animate.css" rel="stylesheet"/>
    <link href="../css/style.css" rel="stylesheet"/>
    <!-- Sweet Alert -->
    <link href="../css/plugins/sweetalert/sweetalert.css" rel="stylesheet"/>
    <link href="../../css/plugins/sweetalert/sweetalert.css" rel="stylesheet"/>

    <link href="../../css/bootstrap.min.css" rel="stylesheet"/>
    <link href="../../font-awesome/css/font-awesome.css" rel="stylesheet"/>

    <!-- FooTable -->
    <link href="../../css/plugins/footable/footable.core.css" rel="stylesheet"/>

    <link href="../../css/animate.css" rel="stylesheet"/>
    <link href="../../css/style.css" rel="stylesheet"/>
</head>
<body>
<div id="wrapper">
    <jsp:include page="../counter/layoutCounter.jsp"></jsp:include>
<%--    <nav class="navbar-default navbar-static-side" role="navigation">--%>
<%--        <div class="sidebar-collapse">--%>
<%--            <ul class="nav metismenu" id="side-menu">--%>
<%--                <li class="nav-header">--%>
<%--                    <div class="dropdown profile-element">--%>
<%--                        <img--%>
<%--                                alt="image"--%>
<%--                                class="rounded-circle"--%>
<%--                                src="img/profile_small.html"--%>
<%--                        />--%>
<%--                        <a data-toggle="dropdown" class="dropdown-toggle"--%>
<%--                           href="/view/counter/index.jsp">--%>
<%--                  <span class="block m-t-xs font-bold"--%>
<%--                  >Counter<b class="caret"></b--%>
<%--                  ></span>--%>
<%--                        </a>--%>
<%--                        <ul class="dropdown-menu animated fadeInRight m-t-xs">--%>
<%--                            <li>--%>
<%--                                <a class="dropdown-item" href="profile.html"--%>
<%--                                >Store profile</a--%>
<%--                                >--%>
<%--                            </li>--%>
<%--                            <li class="dropdown-divider"></li>--%>
<%--                            <li><a class="dropdown-item" href="login.html">Logout</a></li>--%>
<%--                        </ul>--%>
<%--                    </div>--%>
<%--                    <div class="logo-element">FMS</div>--%>
<%--                </li>--%>

<%--                <li class="active">--%>
<%--                    <a href="/FMS/counter/index">--%>
<%--                        <i class="fa fa-id-card"></i>--%>
<%--                        <span class="nav-label">Counter</span></a--%>
<%--                    >--%>
<%--                </li>--%>
<%--                <li class="active">--%>
<%--                    <a href="/FMS/customer/list">--%>
<%--                        <i class="fa fa-user-o"></i>--%>
<%--                        <span class="nav-label">Customer</span></a--%>
<%--                    >--%>
<%--                </li>--%>
<%--            </ul>--%>
<%--        </div>--%>
<%--    </nav>--%>
<%--    <!-- Body -->--%>
<%--    <div id="page-wrapper" class="gray-bg">--%>
<%--        <div class="row border-bottom">--%>
<%--            <nav--%>
<%--                    class="navbar navbar-static-top"--%>
<%--                    role="navigation"--%>
<%--                    style="margin-bottom: 0"--%>
<%--            >--%>
<%--                <div class="navbar-header">--%>
<%--                    <a--%>
<%--                            class="navbar-minimalize minimalize-styl-2 btn btn-primary"--%>
<%--                            href="#"--%>
<%--                    ><i class="fa fa-bars"></i--%>
<%--                    ></a>--%>
<%--                </div>--%>
<%--                <ul class="nav navbar-top-links navbar-right">--%>
<%--                    <li>--%>
<%--                        <a href="login.html">--%>
<%--                            <i class="fa fa-sign-out"></i> Log out--%>
<%--                        </a>--%>
<%--                    </li>--%>
<%--                </ul>--%>
<%--            </nav>--%>
<%--        </div>--%>

        <!-- TMP -->
        <div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-lg-10">
                <h2>E-commerce orders</h2>
                <ol class="breadcrumb">
                    <li class="breadcrumb-item">
                        <a href="index.html">Home</a>
                    </li>
                    <li class="breadcrumb-item">
                        <a>E-commerce</a>
                    </li>
                    <li class="breadcrumb-item active">
                        <strong>Orders</strong>
                    </li>
                </ol>
            </div>
        </div>

        <div class="wrapper wrapper-content animated fadeInRight ecommerce">
            <div class="ibox-content m-b-sm border-bottom">
                <c:url var="searchfield"
                       value="${requestScope.contextPath}/customer/search"></c:url>
                <form action="${searchfield}">
                    <div class="row">
                        <%--                        <div class="col-lg-2">--%>
                        <%--                            <div class="form-group">--%>
                        <%--                                <label class="col-form-label" for="status"--%>
                        <%--                                >Order status</label--%>
                        <%--                                >--%>
                        <%--                                <select name="status" id="status" class="form-control">--%>
                        <%--                                    <option value="" selected="">None</option>--%>
                        <%--                                    <option value="1">Active</option>--%>
                        <%--                                    <option value="0">Inactive</option>--%>
                        <%--                                </select>--%>
                        <%--                            </div>--%>
                        <%--                        </div>--%>
                        <div class="col-xl-5 col-lg-9 col-md-12 text-left">
                            <div class="form-group">
                                <%--                                for="status"--%>
                                <label class="col-form-label">Search by</label>
                                <div class="input-group m-b">
                                    <div class="input-group-prepend">
                                        <select name="" id="" class="form-control">
                                            <option value="">Phone</option>
                                        </select>
                                    </div>
                                    <input name="searchItem"
                                           type="text" class="form-control"/>
                                    <button name="action"
                                            class="btn btn-outline-success float-right"
                                            type="submit"
                                    >
                                        Search
                                    </button>
                                </div>
                            </div>
                        </div>
<%--                        <div class="col-lg-2 container-fluid pt-5">--%>
<%--                            <button name="action"--%>
<%--                                    class="btn btn-outline-success float-right"--%>
<%--                                    type="submit"--%>
<%--                            >--%>
<%--                                Search--%>
<%--                            </button>--%>
<%--                        </div>--%>
                    </div>
                </form>

            </div>

            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox">
                        <div class="ibox-content">
                            <table
                                    class="footable table table-stripped toggle-arrow-tiny"
                                    data-page-size="15"
                            >
                                <thead>
                                <tr>
                                    <th data-hide="phone">Customer</th>

                                    <th data-hide="phone">Phone number</th>

<%--                                    <th>Status</th>--%>

                                    <th class="text-right">Amounts</th>

                                    <th class="text-right">Actions</th>


                                </tr>
                                </thead>
                                <tbody>

                                <%--For listing--%>
                                <c:set var="amount" value="${requestScope.amountlist}"></c:set>
                                <c:forEach var="list" items="${requestScope.customerList}">
                                    <tr>
                                        <td>${list.name}</td>
                                        <td class="text-left">${list.phone}</td>
                                            <%--                                        <c:choose>--%>
                                            <%--                                            <c:when test="${list.isDeleted == false}">--%>
                                            <%--                                                <td style="padding-top: 17px" class="">--%>
                                            <%--                                                    <span class="label label-primary">Active</span>--%>
                                            <%--                                                </td>--%>
                                            <%--                                            </c:when>--%>
                                            <%--                                            <c:when test="${list.isDeleted == true}">--%>
                                            <%--                                                <td style="padding-top: 17px">--%>
                                            <%--                                                    <span class="label label-danger">Inactive</span>--%>
                                            <%--                                                </td>--%>
                                            <%--                                            </c:when>--%>
                                            <%--                                        </c:choose>--%>

                                            <%-- For taking out the balance--%>

                                            <%--                                        <fmt:setLocale value="vi_VN"/>--%>
                                        <td class="text-right"><fmt:formatNumber
                                                value="${amount.get(list)}" pattern="###,###,### ₫"/></td>

                                        <td class="text-right">
                                            <a href="<%=request.getContextPath()%>/customer/remove?phonenum=${list.phone}"
                                               class="btn btn-primary btn-sm">Delete</a>
                                            <a
                                                    class="btn btn-primary btn-sm ${list.phone}">Detail</a>
                                            <a href="<%=request.getContextPath()%>/customer/Movetoupdate?phonenum=${list.phone}"
                                               class="btn btn-primary btn-sm">Update</a>
                                        </td>


                                    </tr>
                                    <%-- Show detail here--%>
                                    <input name="var" value="${list.phone}" type="hidden">
                                    <tr id="${list.phone}" style="display: none">
                                        <td class="col-sm-3">
                                            <p>DoB: ${list.doB}</p>
                                        </td>
                                        <td class="col-sm-3">
                                            <p>Address: ${list.address}</p>
                                        </td>
                                        <c:choose>
                                            <c:when test="${list.gender == 0}">
                                                <td class="col-sm-3">
                                                    <p>Gender: Male</p>
                                                </td>
                                            </c:when>
                                            <c:when test="${list.gender == 1}">
                                                <td class="col-sm-3">
                                                    <p>Gender: Female</p>
                                                </td>
                                            </c:when>
                                            <c:when test="${list.gender == 2}">
                                                <td class="col-sm-3">
                                                    <p>Gender: None</p>
                                                </td>
                                            </c:when>
                                        </c:choose>
                                    </tr>

                                </c:forEach>
                                </tbody>

                                <tfoot>
                                <tr>

                                    <td colspan="6">
                                        <nav aria-label="Page navigation example">
                                            <ul class="paginations">
                                                <li class="page-item ${requestScope.currentPage == 1?"disabled":""}">
                                                    <c:url var="previousPage"
                                                           value="${requestScope.contextPath}/product/list">
                                                        <c:param name="page"
                                                                 value="${requestScope.currentPage - 1}"></c:param>
                                                        <c:param name="sortField"
                                                                 value="${requestScope.sortField}"></c:param>
                                                        <c:param name="isAscending"
                                                                 value="${!requestScope.isAsc}"></c:param>
                                                    </c:url>
                                                    <a
                                                            class="page-link"
                                                            href="${previousPage}"
                                                            aria-label="Previous"
                                                    >
                                                        <span aria-hidden="true">&laquo;</span>
                                                        <span class="sr-only">Previous</span>
                                                    </a>
                                                </li>
                                                <c:forEach begin="1" end="${requestScope.totalPages}" var="page">
                                                    <c:url var="paging"
                                                           value="${requestScope.contextPath}/customer/list">
                                                        <c:param name="page" value="${page}"></c:param>
                                                        <c:param name="sortField"
                                                                 value="${requestScope.sortField}"></c:param>
                                                        <c:param name="isAscending"
                                                                 value="${!requestScope.isAsc}"></c:param>
                                                    </c:url>
                                                    <li class="page-item ${requestScope.currentPage == page ?"active":""}">
                                                        <a class="page-link "
                                                           href="${paging}">${page}</a>
                                                    </li>
                                                </c:forEach>
                                                <li class="page-item ${requestScope.currentPage == requestScope.totalPages?"disabled":""}">
                                                    <c:url var="nextPage"
                                                           value="${requestScope.contextPath}/product/list">
                                                        <c:param name="page"
                                                                 value="${requestScope.currentPage + 1}"></c:param>
                                                        <c:param name="sortField"
                                                                 value="${requestScope.sortField}"></c:param>
                                                        <c:param name="isAscending"
                                                                 value="${!requestScope.isAsc}"></c:param>
                                                    </c:url>
                                                    <a class="page-link" href="${nextPage}" aria-label="Next">
                                                        <span aria-hidden="true">&raquo;</span>
                                                        <span class="sr-only">Next</span>
                                                    </a>
                                                </li>
                                            </ul>
                                        </nav>
                                    </td>


                                </tr>
                                </tfoot>


                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- TMP -->

        <div class="footer">
            <div class="float-right">10GB of <strong>250GB</strong> Free.</div>
            <div><strong>Copyright</strong> Example Company &copy; 2014-2018</div>
        </div>
    </div>

    <div class="wrapper wrapper-content animated fadeInRight ecommerce">
        <div class="ibox-content m-b-sm border-bottom">
            <c:url var="searchfield"
                   value="${requestScope.contextPath}/customer/search"></c:url>
            <form action="${searchfield}">
                <div class="row">
                    <%--                        <div class="col-lg-2">--%>
                    <%--                            <div class="form-group">--%>
                    <%--                                <label class="col-form-label" for="status"--%>
                    <%--                                >Order status</label--%>
                    <%--                                >--%>
                    <%--                                <select name="status" id="status" class="form-control">--%>
                    <%--                                    <option value="" selected="">None</option>--%>
                    <%--                                    <option value="1">Active</option>--%>
                    <%--                                    <option value="0">Inactive</option>--%>
                    <%--                                </select>--%>
                    <%--                            </div>--%>
                    <%--                        </div>--%>
                    <div class="col-lg-10 text-center">
                        <div class="form-group">
                            <%--                                for="status"--%>
                            <label class="col-form-label">Search by</label>
                            <div class="input-group m-b">
                                <div class="input-group-prepend">
                                    <select name="" id="" class="form-control">
                                        <option value="">Phone</option>
                                    </select>
                                </div>
                                <input name="searchItem"
                                       type="text" class="form-control"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-2 container-fluid pt-5">
                        <button name="action"
                                class="btn btn-outline-success float-right"
                                type="submit"
                        >
                            Search
                        </button>
                    </div>
                </div>
            </form>

        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="ibox">
                    <div class="ibox-content">
                        <table
                                class="footable table table-stripped toggle-arrow-tiny"
                                data-page-size="15"
                        >
                            <thead>
                            <tr>
                                <th data-hide="phone">Customer</th>

                                <th data-hide="phone">Phone number</th>

                                <th>Status</th>

                                <th class="text-center">Actions</th>

                                <th class="text-right">Amounts</th>
                            </tr>
                            </thead>
                            <tbody>

                            <%--For listing--%>
                            <c:set var="amount" value="${requestScope.amountlist}"></c:set>
                            <c:forEach var="list" items="${requestScope.customerList}">
                                <tr>
                                    <td>${list.name}</td>
                                    <td class="text-left">${list.phone}</td>
                                    <c:choose>
                                        <c:when test="${list.isDeleted == false}">
                                            <td style="padding-top: 17px" class="">
                                                <span class="label label-primary">Active</span>
                                            </td>
                                        </c:when>
                                        <c:when test="${list.isDeleted == true}">
                                            <td style="padding-top: 17px">
                                                <span class="label label-danger">Inactive</span>
                                            </td>
                                        </c:when>
                                    </c:choose>

                                    <td class="text-center">
                                        <a href="<%=request.getContextPath()%>/customer/remove?phonenum=${list.phone}"
                                           class="btn btn-primary btn-sm">Delete</a>
                                        <a
                                                class="btn btn-primary btn-sm ${list.phone}">Detail</a>
                                        <a href="<%=request.getContextPath()%>/customer/Movetoupdate?phonenum=${list.phone}"
                                           class="btn btn-primary btn-sm">Update</a>
                                    </td>

                                        <%-- For taking out the balance--%>

                                    <fmt:setLocale value="vi_VN"/>
                                    <td class="text-right"><fmt:formatNumber
                                            value="${amount.get(list)}" type="currency"/></td>


                                </tr>
                                <%-- Show detail here--%>
                                <input name="var" value="${list.phone}" type="hidden">
                                <tr id="${list.phone}" style="display: none">
                                    <td class="col-sm-3">
                                        <p>DoB: ${list.doB}</p>
                                    </td>
                                    <td class="col-sm-3">
                                        <p>Address: ${list.address}</p>
                                    </td>
                                    <c:choose>
                                        <c:when test="${list.gender == 0}">
                                            <td class="col-sm-3">
                                                <p>Gender: Male</p>
                                            </td>
                                        </c:when>
                                        <c:when test="${list.gender == 1}">
                                            <td class="col-sm-3">
                                                <p>Gender: Female</p>
                                            </td>
                                        </c:when>
                                        <c:when test="${list.gender == 2}">
                                            <td class="col-sm-3">
                                                <p>Gender: None</p>
                                            </td>
                                        </c:when>
                                    </c:choose>
                                </tr>

                            </c:forEach>
                            </tbody>

                            <tfoot>
                            <tr>
                                <!--<td colspan="7">
                                    <ul class="pagination float-right"></ul>
                                </td>-->

                                <td colspan="6">
                                    <!-- <ul class="pagination float-right"></ul> -->
                                    <nav aria-label="Page navigation example">
                                        <ul class="paginations">
                                            <li class="page-item ${requestScope.currentPage == 1?"disabled":""}">
                                                <c:url var="previousPage"
                                                       value="${requestScope.contextPath}/product/list">
                                                    <c:param name="page"
                                                             value="${requestScope.currentPage - 1}"></c:param>
                                                    <c:param name="sortField"
                                                             value="${requestScope.sortField}"></c:param>
                                                    <c:param name="isAscending"
                                                             value="${!requestScope.isAsc}"></c:param>
                                                </c:url>
                                                <a
                                                        class="page-link"
                                                        href="${previousPage}"
                                                        aria-label="Previous"
                                                >
                                                    <span aria-hidden="true">&laquo;</span>
                                                    <span class="sr-only">Previous</span>
                                                </a>
                                            </li>
                                            <c:forEach begin="1" end="${requestScope.totalPages}" var="page">
                                                <c:url var="paging"
                                                       value="${requestScope.contextPath}/customer/list">
                                                    <c:param name="page" value="${page}"></c:param>
                                                    <c:param name="sortField"
                                                             value="${requestScope.sortField}"></c:param>
                                                    <c:param name="isAscending"
                                                             value="${!requestScope.isAsc}"></c:param>
                                                </c:url>
                                                <li class="page-item ${requestScope.currentPage == page ?"active":""}">
                                                    <a class="page-link "
                                                       href="${paging}">${page}</a>
                                                </li>
                                            </c:forEach>
                                            <li class="page-item ${requestScope.currentPage == requestScope.totalPages?"disabled":""}">
                                                <c:url var="nextPage"
                                                       value="${requestScope.contextPath}/product/list">
                                                    <c:param name="page"
                                                             value="${requestScope.currentPage + 1}"></c:param>
                                                    <c:param name="sortField"
                                                             value="${requestScope.sortField}"></c:param>
                                                    <c:param name="isAscending"
                                                             value="${!requestScope.isAsc}"></c:param>
                                                </c:url>
                                                <a class="page-link" href="${nextPage}" aria-label="Next">
                                                    <span aria-hidden="true">&raquo;</span>
                                                    <span class="sr-only">Next</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </nav>
                                </td>


                            </tr>
                            </tfoot>


                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- TMP -->

    <div class="footer">
        <div class="float-right">10GB of <strong>250GB</strong> Free.</div>
        <div><strong>Copyright</strong> Example Company &copy; 2014-2018</div>
    </div>
</div>
<!-- Body -->

</div>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="../js/plugins/validate/jquery.validate.min.js"></script>

<!-- Custom and plugin javascript -->
<script src="js/inspinia.js"></script>
<script src="js/plugins/pace/pace.min.js"></script>

<!-- FooTable -->
<script src="js/plugins/footable/footable.all.min.js"></script>

<!-- Page-Level Scripts -->
<script>
    $(document).ready(function () {
        $(".footable").footable();
    });
</script>

<script>
    <c:forEach var="list" items="${requestScope.customerList}">
    $(".${list.phone}").click(function () {
        //var searchString = $('#showMore').val();
        if ($("#${list.phone}").css('display') == 'none') {
            $("#${list.phone}").css("display", "block").filter(function () {
                //return $(this).text().trim() === searchString;
            })
        } else if ($("#${list.phone}").css('display') == 'block') {
            $("#${list.phone}").css("display", "none").filter(function () {
                //return $(this).text().trim() === searchString;
            })
        }
    });
    </c:forEach>
</script>

</body>
</html>