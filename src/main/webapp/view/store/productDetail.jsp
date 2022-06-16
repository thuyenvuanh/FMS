<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 15/06/2022
  Time: 11:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <title>Product Detail</title>

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

                                <fieldset>
                                    <div class="form-group row"><label class="col-sm-2 col-form-label">Name:</label>
                                        <div class="col-sm-10"><input readonly type="text" class="form-control"
                                                                      placeholder="Product name"></div>
                                    </div>
                                    <div class="form-group row"><label class="col-sm-2 col-form-label">Price:</label>
                                        <div class="col-sm-10"><input readonly type="text" class="form-control"
                                                                      placeholder="$160.00"></div>
                                    </div>
                                    <div class="form-group row"><label
                                            class="col-sm-2 col-form-label">Description:</label>
                                        <div class="col-sm-10">
                                            <input readonly type="text" class="form-control" placeholder="Description">

                                        </div>
                                    </div>
                                    <div class="form-group row"><label class="col-sm-2 col-form-label">Meta Tag
                                        Title:</label>
                                        <div class="col-sm-10"><input readonly type="text" class="form-control"
                                                                      placeholder="..."></div>
                                    </div>
                                    <div class="form-group row"><label class="col-sm-2 col-form-label">Meta Tag
                                        Description:</label>
                                        <div class="col-sm-10"><input readonly type="text" class="form-control"
                                                                      placeholder="Sheets containing Lorem"></div>
                                    </div>
                                    <div class="form-group row"><label class="col-sm-2 col-form-label">Meta Tag
                                        Keywords:</label>
                                        <div class="col-sm-10"><input readonly type="text" class="form-control"
                                                                      placeholder="Lorem, Ipsum, has, been"></div>
                                    </div>
                                </fieldset>
                                <div class="form-layout-footer text-center">
                                    <a href="product-update.html">
                                        <button class="btn btn-primary bd-0">Update Form</button>
                                    </a>

                                    <a href="#">
                                        <button class="btn btn-danger bd-0">Delete</button>
                                    </a>
                                    <a href="ecommerce_product_list.html">
                                        <button class="btn btn-secondary bd-0">Back to list</button>
                                    </a>

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

<script>
    $(document).ready(function () {
        $(".footable").footable();
    });
</script>

</body>
</html>
