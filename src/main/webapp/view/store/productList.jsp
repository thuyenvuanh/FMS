<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<!-- Mirrored from webapplayers.com/inspinia_admin-v2.9.4/ecommerce_product_list.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 06 Jun 2022 04:37:12 GMT -->
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <title>Product List</title>

    <link href="../css/bootstrap.min.css" rel="stylesheet"/>
    <link href="../font-awesome/css/font-awesome.css" rel="stylesheet"/>

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
    <jsp:include page="layoutStore.jsp"></jsp:include>
    <div class="row wrapper border-bottom white-bg page-heading">
        <div class="col-lg-10">
            <h2>E-commerce product list</h2>
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <a href="index.html">Home</a>
                </li>
                <li class="breadcrumb-item">
                    <a>E-commerce</a>
                </li>
                <li class="breadcrumb-item active">
                    <strong>Product list</strong>
                </li>
            </ol>
        </div>
        <div class="col-lg-2"></div>
    </div>
    <%--    Title--%>
    <%--    Search--%>
    <div class="ibox-content m-b-sm border-bottom">
        <div class="row">
            <div class="col-md-2">
                <div class="form-group">
                    <label class="col-form-label" for="product_name">Category</label>
                    <select name="status" class="form-control">
                        <c:forEach var="category" items="${requestScope.categories}">
                            <option value="${category.id}" ${category.id == 1 ? "selected" : ""}>${category.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-md-2">
                <div class="form-group">
                    <label class="col-form-label" for="product_name">Product Name</label>
                    <input type="text" id="product_name" name="product_name" value=""
                           placeholder="Product Name" class="form-control">
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-group" id="date_range_transaction">
                    <label class="col-form-label">Price</label>
                    <div class="input-daterange input-group" id="datepicker">
                        <input type="text" class="form-control-sm form-control" name="start"
                               value="" placeholder="Min">
                        <span class="input-group-addon">to</span>
                        <input type="text" class="form-control-sm form-control" name="end"
                               value="" placeholder="Max">
                    </div>
                </div>
            </div>

            <div class="col-md-2">
                <div class="form-group">
                    <label class="col-form-label" for="quantity">Quantity</label>
                    <input type="text" id="quantity" name="quantity" value="" placeholder="Quantity"
                           class="form-control">
                </div>
            </div>
            <div class="col-md-2">
                <div class="form-group">
                    <label class="col-form-label" for="status">Status</label>
                    <select name="status" id="status" class="form-control">
                        <option value="1" selected>Enabled</option>
                        <option value="0">Disabled</option>
                    </select>
                </div>
            </div>
            <div class="container-fluid">
                <button class="btn btn-outline-success  float-right" type="submit">Search</button>
            </div>
        </div>

    </div>

    <%--    Search--%>
    <%--    Create--%>
    <nav class="navbar navbar-light bg-light">
        <div class="container-fluid">
            <a href="${pageContext.servletContext.contextPath}/product/createPage" class="">
                <button class="btn btn-outline-primary"
                        type="submit">Create
                </button>
            </a>
        </div>
    </nav>
    <%--    Create--%>

    <div class="wrapper wrapper-content animated fadeInRight ecommerce">

        <div class="row">
            <div class="col-lg-12">
                <div class="ibox">
                    <div class="ibox-content">
                        <table class="footable table table-stripped toggle-arrow-tiny"
                               data-page-size="15">
                            <thead>
                            <c:url var="sort" value="${requestScope.contextPath}/product/list">
                                <c:param name="page" value="${requestScope.currentPage}"></c:param>
                                <c:param name="isAscending" value="${requestScope.isAsc}"></c:param>
                            </c:url>
                            <tr>
                                <th data-toggle="true" data-sort-ignore="true">
                                    <a href="${sort}&sortField=ID">Product ID</a>
                                </th>
                                <th data-hide="phone" data-sort-ignore="true">
                                    <a href="${sort}&sortField=Name">Product Name</a>
                                </th>
                                <%--                                <th data-hide="all" data-sort-ignore="true">Description</th>--%>
                                <th data-hide="all" data-sort-ignore="true">Image</th>
                                <th data-hide="phone" data-sort-ignore="true">Price</th>
                                <th data-hide="phone,tablet" data-sort-ignore="true">Quantity</th>
                                <th data-hide="phone" data-sort-ignore="true">Status</th>
                                <th class="text-right" data-sort-ignore="true">
                                    Action
                                </th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach var="product" items="${requestScope.productList}">
                                <tr>
                                    <td>${product.id}</td>
                                    <td>${product.name}</td>
                                    <td>
                                        <img src="${product.imagePath}" alt="${product.name}" style="width: 35%"/>
                                    </td>
                                    <td>${product.price}</td>
                                    <td>${product.qtyAvailable}</td>
                                    <td>
                                        <span class="label label-primary">Enable</span>
                                    </td>
                                    <td class="text-right">
                                        <div class="btn-group">
                                            <c:url var="viewLink" value="/product/view">
                                                <c:param name="productID" value="${product.id}"></c:param>
                                            </c:url>
                                            <a href="${viewLink}">
                                                <button class="btn-white btn btn-xs">
                                                    View
                                                </button>
                                            </a>
                                            <a href="product-update.html">
                                                <button class="btn-white btn btn-xs">
                                                    Edit
                                                </button>
                                            </a>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                            <tfoot>
                            <tr>
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
                                                    <c:param name="isAscending" value="true"></c:param>
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
                                                       value="${requestScope.contextPath}/product/list">
                                                    <c:param name="page" value="${page}"></c:param>
                                                    <c:param name="sortField"
                                                             value="${requestScope.sortField}"></c:param>
                                                    <c:param name="isAscending" value="true"></c:param>
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
                                                    <c:param name="isAscending" value="true"></c:param>
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
    <jsp:include page="footer.jsp"></jsp:include>
</div>


<!-- Mainly scripts -->
<script src="../../js/jquery-3.1.1.min.js"></script>
<script src="../../js/popper.min.js"></script>
<script src="../../js/bootstrap.js"></script>
<script src="../../js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="../../js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<!-- Custom and plugin javascript -->
<script src="../../js/inspinia.js"></script>
<script src="../../js/plugins/pace/pace.min.js"></script>

<!-- FooTable -->
<script src="../../js/plugins/footable/footable.all.min.js"></script>
<script src="../../js/plugins/sweetalert/sweetalert.min.js"></script>

<!-- Mainly scripts -->
<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/popper.min.js"></script>
<script src="../js/bootstrap.js"></script>
<script src="../js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="../js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<!-- Custom and plugin javascript -->
<script src="../js/inspinia.js"></script>
<script src="../js/plugins/pace/pace.min.js"></script>

<!-- FooTable -->
<script src="../js/plugins/footable/footable.all.min.js"></script>

<!-- Page-Level Scripts -->
<!-- Sweet alert -->
<script src="../js/plugins/sweetalert/sweetalert.min.js"></script>

<%--if controller return update successful status--%>
<c:if test="${sessionScope.createStatus != null}">
    <script>
        $(document).ready(function () {
            swal({
                title: "Create Success!",
                text: "You clicked the button!",
                type: "success"
            });
        });
    </script>
    <%
        session.removeAttribute("createStatus");
    %>
</c:if>
<script>
    $(document).ready(function () {
        $(".footable").footable();
    });
</script>

</body>

<!-- Mirrored from webapplayers.com/inspinia_admin-v2.9.4/ecommerce_product_list.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 06 Jun 2022 04:37:12 GMT -->
</html>


