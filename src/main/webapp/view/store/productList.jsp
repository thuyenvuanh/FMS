<%@ page import="java.util.ArrayList" %>
<%@ page import="com.fptuni.fms.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

    <!-- Select2 -->
    <link href="../../css/plugins/select2/select2.min.css" rel="stylesheet">
    <link href="../../css/plugins/select2/select2-bootstrap4.min.css" rel="stylesheet">
    <link href="../../css/plugins/dualListbox/bootstrap-duallistbox.min.css" rel="stylesheet">
    <link href="../css/plugins/select2/select2.min.css" rel="stylesheet">
    <link href="../css/plugins/select2/select2-bootstrap4.min.css" rel="stylesheet">
    <link href="../css/plugins/dualListbox/bootstrap-duallistbox.min.css" rel="stylesheet">

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
        <c:url var="searchLink" value="${requestScope.contextPath}/product/list"></c:url>
        <form id="form_product_search" action="${searchLink}" autocomplete="off" method="post">
            <div class="row">
                <div class="col-md-2">
                    <div class="form-group">
                        <label class="col-form-label" for="product_name">Category</label>
<%--                        <select name="categoryID" class="select_category form-control">--%>
<%--                            <option value="0">All</option>--%>
<%--                            <c:forEach var="category" items="${requestScope.categories}">--%>
<%--                                <option value="${category.id}" ${requestScope.categoryID == category.id ? "selected":"" }>${category.name}</option>--%>
<%--                            </c:forEach>--%>
<%--                        </select>--%>
                        <select name="categoryID" class="select_category form-control" data-select2-id="6" tabindex="-1" aria-hidden="true">
                            <option value="0">All</option>
                            <c:forEach var="category" items="${requestScope.categories}">
                                <option value="${category.id}" ${requestScope.categoryID == category.id ? "selected":"" }>${category.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <label class="col-form-label" for="product_name">Product Name</label>
                        <input type="text" id="product_name" name="productName" value="${requestScope.productName}"
                               placeholder="Product Name" class="form-control">
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group" id="date_range_transaction">
                        <label class="col-form-label">Price</label>
                        <div class="input-daterange input-group">
                            <input type="text" class="form-control" data-mask="0000000000000" placeholder="Min"
                                   name="minPrice" id="minPrice" maxlength="17" value="${requestScope.minPrice}">
                            <span class="input-group-addon">to</span>
                            <input type="text" class="form-control" data-mask="0000000000000" placeholder="Max"
                                   name="maxPrice" id="maxPrice" maxlength="17" value="${requestScope.maxPrice}">
                        </div>
                    </div>

                </div>

                <div class=" col-md-2">
                    <div class="form-group">
                        <label class="col-form-label" for="quantity">Quantity</label>
                        <input type="text" class="form-control" data-mask="0000000000000" placeholder="Quantity"
                               autocomplete="off" maxlength="17" id="quantity" name="quantity"
                               value="${requestScope.quantity}">

                    </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <label class="col-form-label" for="status">Status</label>
                        <select name="status" id="status" class="form-control">
                            <option value="1" ${requestScope.status == 1 ? "selected":"" }>Enabled</option>
                            <option value="0" ${requestScope.status == 0 ? "selected":"" }>Disabled</option>
                        </select>
                    </div>
                </div>
                <div class="container-fluid">
                    <button class="btn btn-outline-success  float-right" type="submit">Search</button>
                    <button type="reset" value="Reset" class="btn btn-outline-danger float-right" style="margin-right: 2%">Reset</button>
                </div>
            </div>

        </form>

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
                                <c:param name="currentPage" value="${requestScope.currentPage}"></c:param>
                                <c:param name="isAscending" value="${requestScope.isAscending}"></c:param>
                                <c:param name="minPrice" value="${requestScope.minPrice}"></c:param>
                                <c:param name="maxPrice" value="${requestScope.maxPrice}"></c:param>
                                <c:param name="quantity" value="${requestScope.quantity}"></c:param>
                                <c:param name="categoryID" value="${requestScope.categoryID}"></c:param>
                                <c:param name="productName" value="${requestScope.productName}"></c:param>
                            </c:url>
                            <c:url var="sortFormLink" value="${requestScope.contextPath}/product/list"></c:url>

                            <form action="${sortFormLink}" method="post" id="sortForm" style="display: none">
                                <input type="hidden" name="currentPage" value="${requestScope.currentPage}"/>
                                <input type="hidden" name="isAscending" value="${requestScope.isAscending}">
                                <input type="hidden" name="minPrice" value="${requestScope.minPrice}">
                                <input type="hidden" name="maxPrice" value="${requestScope.maxPrice}">
                                <input type="hidden" name="quantity" value="${requestScope.quantity}">
                                <input type="hidden" name="categoryID" value="${requestScope.categoryID}">
                                <input type="hidden" name="productName" value="${requestScope.productName}">
                                <input type="hidden" id="sortFieldInput" name="sortField">

                                <tr style="color: dodgerblue">
                                    <th data-toggle="true" data-sort-ignore="true">
                                        <%--                                    <a href="${sort}&sortField=ID">Product ID</a>--%>
                                        <a onclick="document.getElementById('sortFieldInput').value = 'ID'; document.getElementById('sortForm').submit();">Product
                                            ID</a>
                                    </th>
                                    <th data-hide="phone" data-sort-ignore="true">
                                        <%--                                    <a href="${sort}&sortField=Name">Product Name</a>--%>
                                        <a onclick="document.getElementById('sortFieldInput').value = 'Name';document.getElementById('sortForm').submit();">Product
                                            Name</a>
                                    </th>
                                    <th data-hide="all" data-sort-ignore="true">Image</th>
                                    <th data-hide="phone" data-sort-ignore="true">
                                        <%--                                        <a href="${sort}&sortField=Price">Price</a>--%>
                                        <a onclick="document.getElementById('sortFieldInput').value = 'Price';document.getElementById('sortForm').submit();">Price</a>
                                    </th>
                                    <th data-hide="phone,tablet" data-sort-ignore="true">
                                        <%--                                        <a href="${sort}&sortField=QtyAvailable">Quantity</a>--%>
                                        <a onclick="document.getElementById('sortFieldInput').value = 'QtyAvailable';document.getElementById('sortForm').submit();">Quantity</a>
                                    </th>
                                    <th data-hide="phone" data-sort-ignore="true">Status</th>
                                    <th class="text-right" data-sort-ignore="true">
                                        Action
                                    </th>
                                </tr>
                            </form>
                            </thead>

                            <tbody>

                            <c:forEach var="product" items="${requestScope.productList}">
                                <tr>
                                    <td>
                                            ${product.id}
                                        <input type="hidden" name="productID" value="${product.id}"/>
                                    </td>
                                    <td>${product.name}</td>
                                    <td>
                                        <img src="../${product.imagePath}" alt="${product.name}" style="width: 25%"/>
                                    </td>
                                    <fmt:setLocale value="vi_VN"/>
                                    <td><fmt:formatNumber value="${product.price}" type="currency"/></td>
                                    <td>${product.qtyAvailable}</td>
                                    <c:if test="${product.qtyAvailable != 0}">
                                        <td>
                                            <span class="label label-primary">Enable</span>
                                        </td>
                                    </c:if>
                                    <c:if test="${product.qtyAvailable == 0}">
                                        <td>
                                            <span class="label label-danger">Disable</span>
                                        </td>
                                    </c:if>
                                    <td class="text-right">
                                        <c:url var="deleteLink" value="${requestScope.contextPath}/product/delete">
                                        </c:url>
                                        <form class="deleteForm-${product.id}" action="${deleteLink}">
                                            <input type="hidden" name="productID" value="${product.id}"/>
                                            <div class="btn-group">
                                                <c:url var="viewLink" value="/product/view">
                                                    <c:param name="productID" value="${product.id}"></c:param>
                                                </c:url>
                                                <c:url var="updateLink" value="/product/updatePage">
                                                    <c:param name="productID" value="${product.id}"></c:param>
                                                </c:url>
                                                <button class="btn-white btn btn-xs" formmethod="post"
                                                        formaction="${viewLink}">
                                                    View
                                                </button>
                                                <button class="btn-white btn btn-xs" formmethod="post"
                                                        formaction="${updateLink}">
                                                    Edit
                                                </button>
                                                <button type="button"
                                                        class="btn-white btn btn-xs btn_delete_product_${product.id}" }>
                                                    Delete
                                                </button>
                                            </div>
                                        </form>
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
                                                    <c:param name="sortField"
                                                             value="${requestScope.sortField}"></c:param>
                                                    <c:param name="currentPage"
                                                             value="${requestScope.currentPage - 1}"></c:param>
                                                    <c:param name="isAscending"
                                                             value="${!requestScope.isAscending}"></c:param>
                                                    <c:param name="minPrice" value="${requestScope.minPrice}"></c:param>
                                                    <c:param name="maxPrice" value="${requestScope.maxPrice}"></c:param>
                                                    <c:param name="quantity" value="${requestScope.quantity}"></c:param>
                                                    <c:param name="categoryID"
                                                             value="${requestScope.categoryID}"></c:param>
                                                    <c:param name="productName"
                                                             value="${requestScope.productName}"></c:param>
                                                </c:url>
                                                <c:url var="previousPageLink"
                                                       value="${requestScope.contextPath}/product/list"></c:url>

                                                <form action="${previousPageLink}" method="post"
                                                      id="previousPagingForm">
                                                    <input type="hidden" name="currentPage" id="previousPage"/>
                                                    <input type="hidden" name="isAscending"
                                                           value="${!requestScope.isAscending}">
                                                    <input type="hidden" name="minPrice"
                                                           value="${requestScope.minPrice}">
                                                    <input type="hidden" name="maxPrice"
                                                           value="${requestScope.maxPrice}">
                                                    <input type="hidden" name="quantity"
                                                           value="${requestScope.quantity}">
                                                    <input type="hidden" name="categoryID"
                                                           value="${requestScope.categoryID}">
                                                    <input type="hidden" name="productName"
                                                           value="${requestScope.productName}">
                                                    <input type="hidden" name="sortField"
                                                           value="${requestScope.sortField}">

                                                </form>

                                                <a class="page-link" aria-label="Previous"
                                                   onclick="document.getElementById('previousPage').value=${requestScope.currentPage - 1}; document.getElementById('previousPagingForm').submit();">
                                                    <span aria-hidden="true">&laquo;</span>
                                                    <span class="sr-only">Previous</span>
                                                </a>
                                            </li>
                                            <c:forEach begin="1" end="${requestScope.totalPages}" var="page">
                                                <c:url var="paging"
                                                       value="${requestScope.contextPath}/product/list">
                                                    <c:param name="sortField"
                                                             value="${requestScope.sortField}"></c:param>
                                                    <c:param name="currentPage" value="${page}"></c:param>
                                                    <c:param name="isAscending"
                                                             value="${!requestScope.isAscending}"></c:param>
                                                    <c:param name="minPrice" value="${requestScope.minPrice}"></c:param>
                                                    <c:param name="maxPrice" value="${requestScope.maxPrice}"></c:param>
                                                    <c:param name="quantity" value="${requestScope.quantity}"></c:param>
                                                    <c:param name="categoryID"
                                                             value="${requestScope.categoryID}"></c:param>
                                                    <c:param name="productName"
                                                             value="${requestScope.productName}"></c:param>
                                                </c:url>
                                                <c:url var="pagingLink"
                                                       value="${requestScope.contextPath}/product/list"></c:url>

                                                <form action="${pagingLink}" method="post" id="pagingForm">
                                                    <input type="hidden" name="currentPage" id="currentPage"/>
                                                    <input type="hidden" name="isAscending"
                                                           value="${!requestScope.isAscending}">
                                                    <input type="hidden" name="minPrice"
                                                           value="${requestScope.minPrice}">
                                                    <input type="hidden" name="maxPrice"
                                                           value="${requestScope.maxPrice}">
                                                    <input type="hidden" name="quantity"
                                                           value="${requestScope.quantity}">
                                                    <input type="hidden" name="categoryID"
                                                           value="${requestScope.categoryID}">
                                                    <input type="hidden" name="productName"
                                                           value="${requestScope.productName}">
                                                    <input type="hidden" name="sortField"
                                                           value="${requestScope.sortField}">

                                                </form>
                                                <li class="page-item ${requestScope.currentPage == page ?"active":""}">
                                                    <a class="page-link"
                                                       onclick="document.getElementById('currentPage').value=${page}; document.getElementById('pagingForm').submit();">${page}</a>
                                                </li>


                                            </c:forEach>
                                            <c:url var="nextPage"
                                                   value="${requestScope.contextPath}/product/list">
                                                <c:param name="sortField" value="${requestScope.sortField}"></c:param>
                                                <c:param name="currentPage"
                                                         value="${requestScope.currentPage + 1}"></c:param>
                                                <c:param name="isAscending"
                                                         value="${!requestScope.isAscending}"></c:param>
                                                <c:param name="minPrice" value="${requestScope.minPrice}"></c:param>
                                                <c:param name="maxPrice" value="${requestScope.maxPrice}"></c:param>
                                                <c:param name="quantity" value="${requestScope.quantity}"></c:param>
                                                <c:param name="categoryID" value="${requestScope.categoryID}"></c:param>
                                                <c:param name="productName"
                                                         value="${requestScope.productName}"></c:param>
                                            </c:url>

                                            <li class="page-item ${requestScope.currentPage == requestScope.totalPages?"disabled":""}">
                                                <c:url var="nextPageLink"
                                                       value="${requestScope.contextPath}/product/list"></c:url>
                                                <form action="${nextPageLink}" method="post"
                                                      id="nextPagingForm" style="display: none">
                                                    <input type="hidden" name="currentPage" id="nextPage"/>
                                                    <input type="hidden" name="isAscending"
                                                           value="${!requestScope.isAscending}">
                                                    <input type="hidden" name="minPrice"
                                                           value="${requestScope.minPrice}">
                                                    <input type="hidden" name="maxPrice"
                                                           value="${requestScope.maxPrice}">
                                                    <input type="hidden" name="quantity"
                                                           value="${requestScope.quantity}">
                                                    <input type="hidden" name="categoryID"
                                                           value="${requestScope.categoryID}">
                                                    <input type="hidden" name="productName"
                                                           value="${requestScope.productName}">
                                                    <input type="hidden" name="sortField"
                                                           value="${requestScope.sortField}">

                                                </form>
                                                <%--                                                <a class="page-link" href="${nextPage}" aria-label="Next">--%>
                                                <%--                                                    <span aria-hidden="true">&raquo;</span>--%>
                                                <%--                                                    <span class="sr-only">Next</span>--%>
                                                <%--                                                </a>--%>
                                                <a class="page-link" aria-label="Next"
                                                   onclick="document.getElementById('nextPage').value=${requestScope.currentPage + 1}; document.getElementById('nextPagingForm').submit();">
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
<script src="../js/plugins/footable/footable.all.min.js"></script>
<script src="../js/plugins/sweetalert/sweetalert.min.js"></script>

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
<script src="../../js/plugins/footable/footable.all.min.js"></script>
<script src="../js/plugins/footable/footable.all.min.js"></script>

<!-- Input Mask-->
<script src="../js/plugins/jqueryMask/jquery.mask.min.js"></script>

<!-- Page-Level Scripts -->
<!-- Sweet alert -->
<script src="../js/plugins/sweetalert/sweetalert.min.js"></script>

<%-- Select2 --%>
<script src="../js/plugins/select2/select2.full.min.js"></script>

<!-- Jquery Validate -->
<script src="../../js/plugins/jquery-ui/jquery-ui.min.js"></script>
<script src="../js/plugins/jquery-ui/jquery-ui.min.js"></script>
<script src="../../js/plugins/validate/jquery.validate.min.js"></script>
<script src="../js/plugins/validate/jquery.validate.min.js"></script>

<%--if controller return create successful status--%>
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

<c:if test="${sessionScope.updateStatus != null}">
    <script>
        $(document).ready(function () {
            swal({
                title: "Update Success!",
                text: "You clicked the button!",
                type: "success"
            });
        });
    </script>
    <%
        session.removeAttribute("updateStatus");
    %>
</c:if>
<script>
    <c:forEach var="product" items="${requestScope.productList}" varStatus="loop">
    $(document).ready(function () {
        $(".btn_delete_product_${product.id}").click(function () {
            swal({
                    title: "Are you sure?",
                    text: "Your will not be able to recover this product!",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "Yes, delete it!",
                    cancelButtonText: "No, cancel!",
                    closeOnConfirm: false,
                    closeOnCancel: false
                },
                function (isConfirm) {
                    if (isConfirm) {
                        $(".deleteForm-${product.id}").submit();
                        swal("Deleted!", "Your product has been deleted.", "success");
                    } else {
                        swal("Cancelled", "", "error");
                    }
                });
        });
    });
    </c:forEach>
    <%
        session.removeAttribute("deleteStatus");
    %>
</script>
<script>
    $(document).ready(function () {
        $(".footable").footable();

        $(".select_category").select2({
            theme: 'bootstrap4',
        });
        // $.validator.addMethod('greaterThan', function (value, element, param) {
        //     return this.optional(element) || parseInt(value) >= parseInt($(param).val());
        // }, 'Invalid value');

<%--        let selectInput = document.querySelector("#minPrice");--%>

<%--        selectInput.addEventListener("keydown", function(e){--%>
<%--            const key = e.key;--%>
<%--            if(key === "Backspace"){--%>
<%--                <%--%>
<%--//                    request.removeAttribute("minPrice");--%>
<%--                    request.setAttribute("minPrice", 0);--%>
<%--                %>--%>
<%--                selectInput.value = 0;--%>

<%--                console.log(selectInput.value);--%>
<%--            }--%>
<%--        })--%>


<%--        // if($("#minPrice").val() != "" && $("#maxPrice").val() != ""){--%>
<%--            $("#form_product_search").validate({--%>

<%--                rules: {--%>
<%--                    maxPrice: {--%>
<%--                        greaterThan: '#minPrice',--%>
<%--                        number: true,--%>
<%--                        maxlength: 9--%>
<%--                    },--%>
<%--                },--%>
<%--                messages: {--%>
<%--                    maxPrice: {--%>
<%--                        greaterThan: 'Max price must be greater than min price',--%>
<%--                        maxlength: 'Invalid input cause over amount format'--%>
<%--                    }--%>
<%--                }--%>

<%--            })--%>
        // }

    });

</script>

<%--<!-- Alert -->--%>
<div class="sweet-overlay" tabindex="-1" style="opacity: -0.03; display: none;"></div>
<div class="sweet-alert hideSweetAlert" data-custom-class="" data-has-cancel-button="false"
     data-has-confirm-button="true" data-allow-outside-click="false" data-has-done-function="false"
     data-animation="pop" data-timer="null" style="display: none; margin-top: -171px; opacity: 0;">
    <div class="sa-icon sa-error" style="display: none;">
    <span class="sa-x-mark">
    <span class="sa-line sa-left"></span>
    <span class="sa-line sa-right"></span>
    </span>
    </div>
    <div class="sa-icon sa-warning" style="display: none;">
        <span class="sa-body"></span>
        <span class="sa-dot"></span>
    </div>
    <div class="sa-icon sa-info" style="display: none;"></div>
    <div class="sa-icon sa-success" style="display: block;">
        <span class="sa-line sa-tip"></span>
        <span class="sa-line sa-long"></span>

        <div class="sa-placeholder"></div>
        <div class="sa-fix"></div>
    </div>
    <div class="sa-icon sa-custom" style="display: none;"></div>
    <h2>Update success!</h2>
    <p style="display: block;">Your Product has been deleted.</p>
    <fieldset>
        <input type="text" tabindex="3" placeholder="">
        <div class="sa-input-error"></div>
    </fieldset>
    <div class="sa-error-container">
        <div class="icon">!</div>
        <p>Not valid!</p>
    </div>
    <div class="sa-button-container">
        <button class="cancel" tabindex="2" style="display: none; box-shadow: none;">Cancel</button>

        <button class="confirm" tabindex="1"
                style="display: inline-block; background-color: rgb(174, 222, 244); box-shadow: rgba(174, 222, 244, 0.8) 0px 0px 2px, rgba(0, 0, 0, 0.05) 0px 0px 0px 1px inset;">
            OK
        </button>
    </div>
</div>
</body>

</html>
