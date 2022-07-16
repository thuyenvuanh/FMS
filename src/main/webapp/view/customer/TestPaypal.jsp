<%--
  Created by IntelliJ IDEA.
  User: ttcha
  Date: 6/18/2022
  Time: 7:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Paypal</title>
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
                  <span class="block m-t-xs font-bold"
                  >Counter<b class="caret"></b
                  ></span>
                        </a>
                        <ul class="dropdown-menu animated fadeInRight m-t-xs">
                            <li>
                                <a class="dropdown-item" href="profile.html"
                                >Store profile</a
                                >
                            </li>
                            <li class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="login.html">Logout</a></li>
                        </ul>
                    </div>
                    <div class="logo-element">FMS</div>
                </li>

                <li class="active">
                    <a href="ecommerce_product_list.html">
                        <i class="fa fa-id-card"></i>
                        <span class="nav-label">Counter</span></a>
                </li>
                <li class="active">
                    <a href="customer-list.html">
                        <i class="fa fa-user-o"></i>
                        <span class="nav-label">Customer</span></a
                    >
                </li>
            </ul>
        </div>
    </nav>
    <!-- Body -->
    <div id="page-wrapper" class="gray-bg">
        <div class="row border-bottom">
            <nav
                    class="navbar navbar-static-top"
                    role="navigation"
                    style="margin-bottom: 0"
            >
                <div class="navbar-header">
                    <a
                            class="navbar-minimalize minimalize-styl-2 btn btn-primary"
                            href="#"
                    ><i class="fa fa-bars"></i
                    ></a>
                </div>
                <ul class="nav navbar-top-links navbar-right">
                    <li>
                        <a href="login.html">
                            <i class="fa fa-sign-out"></i> Log out
                        </a>
                    </li>
                </ul>
            </nav>
        </div>

        <!-- TMP -->
        <!-- PayPal Logo --><table border='0' cellpadding='10' cellspacing='0' align='center'><tr><td align='center'></td></tr><tr><td align='center'>
        <a href='https://www.sandbox.paypal.com/vn/webapps/mpp/paypal-popup' title='How PayPal Works' onclick="javascript:window.open('https://www.sandbox.paypal.com/vn/webapps/mpp/paypal-popup','WIPaypal','toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=yes, width=1060, height=700'); return false;"><img src='https://www.paypalobjects.com/webstatic/en_AU/i/buttons/btn_paywith_primary_l.png' alt='Thanh toán bằng PayPal | Lớn' /></a></td></tr></table><!-- PayPal Logo -->
        <!-- TMP -->

        <a href="https://paypal.me/FMSse16?country.x=VN&locale.x=en_US">Pay</a>

        <div class="footer">
            <div class="float-right">10GB of <strong>250GB</strong> Free.</div>
            <div><strong>Copyright</strong> Example Company &copy; 2014-2018</div>
        </div>
    </div>
    <!-- Body -->
</div>
<script src="../../plugins/jqueryMask/jquery.mask.min.js"></script>
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

<!-- Page-Level Scripts -->
<script>
    $(document).ready(function () {
        $(".footable").footable();
    });
</script>

</body>
</html>
