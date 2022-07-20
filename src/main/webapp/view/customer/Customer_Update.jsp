<%--
  Created by IntelliJ IDEA.
  User: ttcha
  Date: 7/4/2022
  Time: 9:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<!-- Mirrored from webapplayers.com/inspinia_admin-v2.9.4/ecommerce_product_list.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 06 Jun 2022 04:37:12 GMT -->

<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Customer Update</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet" />
    <link href="../font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <!-- FooTable -->
    <link href="../css/plugins/footable/footable.core.css" rel="stylesheet" />

    <link href="../css/animate.css" rel="stylesheet" />
    <link href="../css/style.css" rel="stylesheet" />

    <!-- Sweet Alert -->
    <link href="../css/plugins/sweetalert/sweetalert.css" rel="stylesheet"/>

    <%--    --%>
    <link href="../css/bootstrap.min.css" rel="stylesheet" />
    <link href="../font-awesome/css/font-awesome.css" rel="stylesheet" />
    <%--    <link rel="stylesheet"--%>
    <%--          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">--%>
    <!-- FooTable -->
    <link href="../css/plugins/footable/footable.core.css" rel="stylesheet" />

    <link href="../css/animate.css" rel="stylesheet" />
    <link href="../css/style.css" rel="stylesheet" />
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
<%--                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">--%>
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

                <div class="content clearfix">
                    <h1 id="form-h-0" tabindex="-1" class="title current">Account</h1>
                    <fieldset id="form-p-0" role="tabpanel" aria-labelledby="form-h-0" class="body current" aria-hidden="false">
                        <h2>Update Information</h2>
                        <div class="row">

                                <c:forEach var="update" items="${requestScope.info}">
<%--                                    class="btn btn-primary"--%>
                                    <c:url var="CustomerUpdate" value="${requestScope.contextPath}/customer/update" >
                                    </c:url>
                                    <form action="${CustomerUpdate}" id="formUpdateCus">
                                    <div class="col-lg-8">
                                        <div class="form-group">
                                            <label>Username</label>
                                            <input name="name" value="${update.name}" class="form-control required" aria-required="true">
                                        </div>
                                        <div class="form-group">
                                            <label>Phone</label>
                                            <input name="phone" value="${update.phone}" readonly class="form-control required" aria-required="true">
                                        </div>
<%--                                        <div class="form-group">--%>
<%--                                            <label>Date of Birth</label>--%>
<%--                                            <input placeholder="${update.doB}" name="DoB" type="text" class="form-control required" aria-required="true">--%>
<%--                                        </div>--%>
                                        <div class="form-group" id="update-Dob">
                                            <label>Date of Birth</label>
                                            <input
<%--                                                    placeholder="${update.doB}"--%>
                                                   name="Dob" type="text"
                                                   value="${update.doB}"
                                                   class="form-control input-Dob" data-mask="00/00/0000" autocomplete="off" maxlength="10">
                                            <span class="form-text">dd/mm/yyyy</span>
                                        </div>

                                        <div class="form-group">
                                            <label>Address</label>
                                            <input placeholder="${update.address}"
                                                   name="address" type="text"
                                                   class="form-control required" aria-required="true">
                                        </div>

                                        <c:choose>
                                            <c:when test="${update.gender == 0}">
                                                <div class="form-group">
                                                    <label>Gender</label>
                                                    <input placeholder="Male" name="gender" type="text" class="form-control required" aria-required="true">
                                                </div>
                                            </c:when>
                                            <c:when test="${update.gender == 1}">
                                                <div class="form-group">
                                                    <label>Gender</label>
                                                    <input placeholder="Female" name="gender" type="text" class="form-control required" aria-required="true">
                                                </div>
                                            </c:when>
                                            <c:when test="${update.gender == 2}">
                                                <div class="form-group">
                                                    <label>Gender</label>
                                                    <input placeholder="None" name="gender" type="text" class="form-control required" aria-required="true">
                                                </div>
                                            </c:when>
                                        </c:choose>

                                    </div>
                                <div class="col-lg-4">
                                    <div class="text-center">
                                        <div style="margin-top: 20px">
                                            <i class="fa fa-sign-in" style="font-size: 180px;color: #e5e5e5 "></i>
                                        </div>
                                    </div>
                                </div>
                                <div class="w-100 p-3">
<%--                                    <a href="#next" class="btn btn-primary" role="menuitem">Apply</a>--%>
                                    <input type="submit" value="submit" class="btn btn-primary">
                                </div>
                            </form>
                            </c:forEach>
                        </div>
                    </fieldset>
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

<%----%>
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

<!-- Jquery Validate -->
<script src="../../js/plugins/jquery-ui/jquery-ui.min.js"></script>
<script src="../js/plugins/jquery-ui/jquery-ui.min.js"></script>
<script src="../../js/plugins/validate/jquery.validate.min.js"></script>
<script src="../js/plugins/validate/jquery.validate.min.js"></script>

<!-- Sweet alert -->
<script src="../js/plugins/sweetalert/sweetalert.min.js"></script>

    <!-- Page-Level Scripts -->
    <script>
        $(document).ready(function () {
            $(".footable").footable();

            $("#formUpdateCus").validate({
                rules: {
                    Dob: {
                        required: true,
                    },
                    address:{
                      required: true,
                    },
                    name:{
                        request: true,
                    }
                },
                messages: {
                    Dob: {
                        required: 'Please enter Date of birth'
                    },
                    address: {
                        required: 'Please enter Address'
                    },
                    name: {
                        required: 'Please enter Name'
                    }
                }
            });
        });

        $(document).ready(function () {
            $('#update-Dob .input-Dob').datepicker({
                keyboardNavigation: false,
                forceParse: false,
                autoclose: true,
                format: "yyyy-MM-dd"
            });
        });

    </script>

<script src="js/plugins/jqueryMask/jquery.mask.min.js"></script>

</body>
</html>
