<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <title>Product Detail</title>

    <link href="../css/bootstrap.min.css" rel="stylesheet"/>
    <link href="../font-awesome/css/font-awesome.css" rel="stylesheet"/>

    <link href="../css/animate.css" rel="stylesheet"/>
    <link href="../css/style.css" rel="stylesheet"/>
    <!-- Sweet Alert -->
    <link href="../css/plugins/sweetalert/sweetalert.css" rel="stylesheet"/>
    <link href="../../css/plugins/sweetalert/sweetalert.css" rel="stylesheet"/>
    <link href="../../css/bootstrap.min.css" rel="stylesheet"/>
    <link href="../../font-awesome/css/font-awesome.css" rel="stylesheet"/>

    <!-- FooTable -->
    <link href="../../css/plugins/footable/footable.core.css" rel="stylesheet"/>
    <link href="../css/plugins/footable/footable.core.css" rel="stylesheet"/>

    <link href="../../css/animate.css" rel="stylesheet"/>
    <link href="../../css/style.css" rel="stylesheet"/>
    <style>
        .text-align {
            display: flex;
            align-items: center;
        }
    </style>
</head>
<body>
<div id="wrapper">
    <jsp:include page="layoutStore.jsp"></jsp:include>
    <div class="row wrapper border-bottom white-bg page-heading">
        <div class="col-lg-10">
            <h2>Product edit</h2>
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <a href="index.html">Home</a>
                </li>
                <li class="breadcrumb-item">
                    <a>E-commerce</a>
                </li>
                <li class="breadcrumb-item">
                    <a>Products list</a>
                </li>
                <li class="breadcrumb-item active">
                    <strong>Product view</strong>
                </li>
            </ol>
        </div>
    </div>
    <div class="wrapper wrapper-content animated fadeInRight ecommerce">

        <div class="row">
            <div class="col-lg-12">
                <div class="tabs-container">
                    <ul class="nav nav-tabs">
                        <li><a class="nav-link active" data-toggle="tab" href="#tab-1"> Product info</a></li>
                    </ul>
                    <div class="tab-content">
                        <div id="tab-1" class="tab-pane active">
                            <div class="panel-body">

                                <div class="ibox">
                                    <div class="ibox-content">
                                        <table class="footable table table-stripped toggle-arrow-tiny" data-page-size="15">

                                            <thead>
                                            <tr>
                                                <th data-toggle="true" data-sort-ignore="true">ID</th>

                                                <th data-sort-ignore="true">Name</th>

                                                <th data-hide="all" data-sort-ignore="true">Image</th>

                                                <th data-hide="phone" data-sort-ignore="true">Price</th>

                                                <th data-hide="phone" data-sort-ignore="true">Category</th>

                                                <th class="text-right" data-sort-ignore="true">Available quantity</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:set var="productDetail" value="${requestScope.product}"></c:set>
                                            <tr>
                                                <td>${productDetail.id}</td>
                                                <td>${productDetail.name}</td>
                                                <td>
                                                    <img src="${productDetail.imagePath}" alt="${productDetail.name}"
                                                         style="width: 35%"/>
                                                </td>
                                                <fmt:setLocale value="vi_VN"/>
                                                <td ><fmt:formatNumber value="${productDetail.price}" type="currency"/></td>

                                                <td>${category.name}</td>

                                                <td class="text-right">${productDetail.qtyAvailable}</td>

                                            </tr>
                                            </tbody>

                                        </table>
                                    </div>

                                </fieldset>
                                <div class="form-layout-footer text-center">
                                    <c:url var="updateProductLink" value="${requestScope.contextPath}/product/updatePage">
                                        <c:param name="productID" value="${productDetail.id}"></c:param>
                                    </c:url>
                                    <c:url var="deleteProductLink" value="${requestScope.contextPath}/product/delete">
                                        <c:param name="productID" value="${productDetail.id}"></c:param>
                                    </c:url>
                                    <a href="${updateProductLink}">
                                        <button class="btn btn-primary bd-0">Update</button>
                                    </a>
                                    <a href="${deleteProductLink}">
                                        <button class="btn btn-danger bd-0">Delete</button>
                                    </a>
<%--                                    <a href="ecommerce_product_list.html">--%>
<%--                                        <button class="btn btn-secondary bd-0">Back to list</button>--%>
<%--                                    </a>--%>
                                    <button onclick="history.back()" type="button" class="btn btn-dark">Back to list
                                    </button>
                                </div>
                            </div>
                        </div>

                    </div>
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

<!-- Page-Level Scripts -->
<!-- Sweet alert -->
<script src="../../js/plugins/sweetalert/sweetalert.min.js"></script>
<script src="../js/plugins/sweetalert/sweetalert.min.js"></script>

<script>
    $(document).ready(function () {
        $(".footable").footable();
    });
</script>

</body>
</html>
