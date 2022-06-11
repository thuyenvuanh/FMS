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
    <link href="../../css/bootstrap.min.css" rel="stylesheet"/>
    <link href="../../font-awesome/css/font-awesome.css" rel="stylesheet"/>

    <!-- FooTable -->
    <link href="../../css/plugins/footable/footable.core.css" rel="stylesheet"/>

    <link href="../../css/animate.css" rel="stylesheet"/>
    <link href="../../css/style.css" rel="stylesheet"/>
</head>

<body>
<div id="wrapper">
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav metismenu" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element">
                        <img
                                alt="image"
                                class="rounded-circle"
                                src="img/profile_small.html"
                        />
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="block m-t-xs font-bold">David Williams</span>
                            <span class="text-muted text-xs block"
                            >Art Director <b class="caret"></b></span>

                        </a>
                        <ul class="dropdown-menu animated fadeInRight m-t-xs">
                            <li>
                                <a class="dropdown-item" href="profile.html">Profile</a>
                            </li>
                            <li>
                                <a class="dropdown-item" href="contacts.html">Contacts</a>
                            </li>
                            <li>
                                <a class="dropdown-item" href="mailbox.html">Mailbox</a>
                            </li>
                            <li class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="login.html">Logout</a></li>
                        </ul>
                    </div>
                    <div class="logo-element">IN+</div>
                </li>
                <li class="active">
                    <a href="#"
                    ><i class="fa fa-shopping-cart"></i>
                        <span class="nav-label">E-commerce</span
                        ><span class="fa arrow"></span
                        ></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="ecommerce_products_grid.html">Products grid</a>
                        </li>
                        <li class="active">
                            <a href="ecommerce_product_list.html">Products list</a>
                        </li>
                        <li><a href="ecommerce_product.html">Product edit</a></li>
                        <li>
                            <a href="ecommerce_product_detail.html">Product detail</a>
                        </li>
                        <li><a href="ecommerce-cart.html">Cart</a></li>
                        <li><a href="ecommerce-orders.html">Orders</a></li>
                        <li><a href="ecommerce_payments.html">Credit Card form</a></li>
                        <li><a href="customer-list.html">Customer list</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>

    <div id="page-wrapper" class="gray-bg">
        <div class="row border-bottom">
            <nav
                    class="navbar navbar-static-top"
                    role="navigation"
                    style="margin-bottom: 0"
            ></nav>
        </div>
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

        <div class="wrapper wrapper-content animated fadeInRight ecommerce">
            <nav class="navbar navbar-light bg-light">
                <div class="container-fluid">
                    <a href="ecommerce_product.html" class=""
                    >
                        <button class="btn btn-outline-primary" type="submit">
                            Create
                        </button>
                    </a
                    >
                    <form class="d-flex">
                        <input
                                class="form-control me-2"
                                type="search"
                                placeholder="Search"
                                aria-label="Search"
                        />
                        <button class="btn btn-outline-success" type="submit">
                            Search
                        </button>
                    </form>
                </div>
            </nav>

            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox">
                        <div class="ibox-content">
                            <table class="footable table table-stripped toggle-arrow-tiny"
                                   data-page-size="15">
                                <thead>
                                <tr>
                                    <th data-toggle="true">Product Name</th>
                                    <th data-hide="phone">Model</th>
                                    <th data-hide="all">Description</th>
                                    <th data-hide="phone">Price</th>
                                    <th data-hide="phone,tablet">Quantity</th>
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
                                            It is a long established fact that a reader will be
                                            distracted by the readable content of a page when
                                            looking at its layout. The point of using Lorem Ipsum
                                            is that it has a more-or-less normal distribution of
                                            letters, as opposed to using 'Content here, content
                                            here', making it look like readable English.
                                        </td>
                                        <td>${product.price}</td>
                                        <td>${product.qtyAvailable}</td>
                                        <td>
                                            <span class="label label-primary">Enable</span>
                                        </td>
                                        <td class="text-right">
                                            <div class="btn-group">
                                                <a href="product-view.html">
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
                                                <li class="page-item">
                                                    <a
                                                            class="page-link"
                                                            href="#"
                                                            aria-label="Previous"
                                                    >
                                                        <span aria-hidden="true">&laquo;</span>
                                                        <span class="sr-only">Previous</span>
                                                    </a>
                                                </li>
                                                <c:forEach var="pageIndex" items="${requestScope.totalPages}">
                                                    <li class="page-item">
                                                        <a class="page-link" href="${pageIndex}">${pageIndex}</a>
                                                    </li>
                                                </c:forEach>
                                                <li class="page-item">
                                                    <a class="page-link" href="#" aria-label="Next">
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
        <div class="footer">
            <div class="float-right">10GB of <strong>250GB</strong> Free.</div>
            <div><strong>Copyright</strong> Example Company &copy; 2014-2018</div>
        </div>
    </div>
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
<script>
    $(document).ready(function () {
        $(".footable").footable();
    });
</script>
</body>

<!-- Mirrored from webapplayers.com/inspinia_admin-v2.9.4/ecommerce_product_list.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 06 Jun 2022 04:37:12 GMT -->
</html>


