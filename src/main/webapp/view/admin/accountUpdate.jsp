<%--
  Created by IntelliJ IDEA.
  User: LEGION
  Date: 6/18/2022
  Time: 3:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Account Update</title>

    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <link href="../../font-awesome/css/font-awesome.css" rel="stylesheet">

    <!-- FooTable -->
    <link href="../../css/plugins/footable/footable.core.css" rel="stylesheet">
    <!-- Sweet Alert -->
    <link href="../../css/plugins/sweetalert/sweetalert.css" rel="stylesheet">

    <link href="../../css/animate.css" rel="stylesheet">
    <link href="../../css/style.css" rel="stylesheet">
</head>
<body>

<div id="wrapper">

    <jsp:include page="layoutAdmin.jsp"></jsp:include>

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
                    <strong>Product edit</strong>
                </li>
            </ol>
        </div>
    </div>

    <div class="wrapper wrapper-content animated fadeInRight ecommerce">

        <div class="row">
            <div class="col-lg-12">
                <div class="tabs-container">
                    <ul class="nav nav-tabs">
                        <li><a class="nav-link active" data-toggle="tab" href="#tab-1"> Account info</a></li>
                    </ul>
                    <div class="tab-content">
                        <div id="tab-1" class="tab-pane active">
                            <div class="panel-body">

                                <fieldset>
                                    <div class="form-group row"><label
                                            class="col-sm-2 col-form-label">Username:</label>
                                        <div class="col-sm-10"><input type="text" class="form-control"
                                                                      placeholder="Username" readonly></div>
                                    </div>
                                    <div class="form-group row"><label
                                            class="col-sm-2 col-form-label">Fullname:</label>
                                        <div class="col-sm-10"><input type="text" class="form-control"
                                                                      placeholder="Fullname"></div>
                                    </div>
                                    <div class="form-group row"><label
                                            class="col-sm-2 col-form-label">Password:</label>
                                        <div class="col-sm-10"><input type="password" class="form-control"
                                                                      placeholder="password"></div>
                                    </div>
                                    <div class="form-group row"><label
                                            class="col-sm-2 col-form-label">Confirm password:</label>
                                        <div class="col-sm-10"><input type="password" class="form-control"
                                                                      placeholder=" confirm password"></div>
                                    </div>

                                    <div class="form-group row"><label
                                            class="col-sm-2 col-form-label">Role:</label>
                                        <div class="col-sm-10">
                                            <select class="form-control m-b" name="role">
                                                <option selected disabled></option>
                                                <option>role 1</option>
                                                <option>role 2</option>
                                                <option>role 3</option>
                                                <option>role 4</option>
                                            </select>
                                        </div>

                                </fieldset>
                                <div class="form-layout-footer text-center">
                                    <button class="btn btn-primary bd-0 update_product_form">Submit</button>

                                    <button class="btn btn-danger bd-0 delete_product">Delete</button>
                                    <a href="ecommerce_product_list.html"> <button
                                            class="btn btn-secondary bd-0">Back to list</button></a>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
</div>

<%--Script--%>
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

        $('.footable').footable();

    });

</script>

<!-- Alert -->
<script src="js/plugins/sweetalert/sweetalert.min.js"></script>
<script>

    $(document).ready(function () {
        $('.create_product_form').click(function () {
            swal({
                title: "Are you sure create?",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "Yes, create it!",
                closeOnConfirm: false
            });
        });
        $('.confirm').click(function () {
            window.location = "account_list.html";
        })
    });
</script>

<!-- Alert -->
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
    <h2>Create success!</h2>
    <p style="display: block;">Your imaginary file has been deleted.</p>
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
                style="display: inline-block; background-color: rgb(174, 222, 244); box-shadow: rgba(174, 222, 244, 0.8) 0px 0px 2px, rgba(0, 0, 0, 0.05) 0px 0px 0px 1px inset;">a</button>
    </div>
</div>
</body>
</html>
