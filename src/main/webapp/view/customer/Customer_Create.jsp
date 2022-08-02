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
    <title>Customer Create</title>
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
    <jsp:include page="../counter/layoutCounter.jsp"></jsp:include>
        <!-- TMP -->
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
                            <li><a class="nav-link active" data-toggle="tab" href="#tab-1"> Customer info</a></li>

                        </ul>


                        <div class="tab-content">
                            <div id="tab-1" class="tab-pane active">
                                <c:url var="createCus" value="${requestScope.contextPath}/customer/addcustomer" >
                                </c:url>
                                <form id="form_create_customer" action="${createCus}">
                                    <div class="panel-body">

                                        <fieldset>
                                            <div class="form-group row"><label class="col-sm-2 col-form-label">Name:</label>
                                                <div class="col-sm-10"><input type="text" class="form-control"
                                                                              placeholder="Customer name"
                                                                              name="Cusname"></div>
                                            </div>
                                            <c:set var="cusCreate" value="${sessionScope.phoneNumber}"></c:set>
                                            <div class="form-group row"><label class="col-sm-2 col-form-label">Phone
                                                number:</label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="form-control" data-mask="000000000000"
                                                           placeholder="" autocomplete="off" maxlength="14"
                                                           name="Cusphone" value="${cusCreate}">

                                                </div>
                                            </div>
                                        </fieldset>
                                        <div class="form-layout-footer text-center">
                                            <button class="btn btn-primary bd-0"
                                                    id="btnCreateCus"
                                                    type="button"
                                            name="action" value="add" >Submit</button>
                                        </div>
                                    </div>
                                </form>
                            </div>

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

</div>
<!-- Body -->
</div>
<script src="../../plugins/jqueryMask/jquery.mask.min.js"></script>
<script src="../../js/jquery-3.1.1.min.js"></script>
<script src="../../js/popper.min.js"></script>
<script src="../../js/bootstrap.js"></script>
<script src="../../js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="../../js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="../../js/plugins/validate/jquery.validate.min.js"></script>
<script src="../js/plugins/validate/jquery.validate.min.js"></script>
<script src="../js/plugins/jquery-ui/jquery-ui.min.js"></script>

<!-- Custom and plugin javascript -->
<script src="../../js/inspinia.js"></script>
<script src="../../js/plugins/pace/pace.min.js"></script>

<!-- FooTable -->
<script src="../../js/plugins/footable/footable.all.min.js"></script>

<%--Main--%>
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

<!-- Sweet alert -->
<script src="../js/plugins/sweetalert/sweetalert.min.js"></script>

<!-- Jquery Validate -->
<script src="../../js/plugins/jquery-ui/jquery-ui.min.js"></script>
<script src="../js/plugins/jquery-ui/jquery-ui.min.js"></script>
<script src="../../js/plugins/validate/jquery.validate.min.js"></script>
<script src="../js/plugins/validate/jquery.validate.min.js"></script>

<%-- Jquery Mask --%>
<script src="../js/plugins/jqueryMask/jquery.mask.min.js"></script>

<!-- Page-Level Scripts -->
<script>
    $(document).ready(function () {
        $(".footable").footable();

        $("#btnCreateCus").click(function () {
            swal({
                    title: "Are you sure?",
                    text: " ",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "Yes, update it!",
                    cancelButtonText: "No, cancel!",
                    closeOnConfirm: false,
                    closeOnCancel: false
                },
                function (isConfirm) {
                    if (isConfirm) {
                        $("#form_create_customer").submit();
                    } else {
                        swal("Cancelled", "", "error");
                    }
                });
        });

        $("#form_create_customer").validate({
            rules: {
                Cusname: {
                    required: true,
                },
                Cusphone: {
                    required: true,
                    minlength: 9,
                    maxlength: 12
                }
            },
            messages: {
                Cusname: {
                    required: 'Please enter customer name',
                },
                Cusphone: {
                    required: 'Please enter customer phone',
                    minlength: 'Phone number must be greater than 9',
                    maxlength: 'Phone number must be lower than 12'
                }
            }
        })
    });
</script>

<!-- Alert -->
<div
        class="sweet-overlay"
        tabindex="-1"
        style="opacity: -0.03; display: none"
></div>
<div
        class="sweet-alert hideSweetAlert"
        data-custom-class=""
        data-has-cancel-button="false"
        data-has-confirm-button="true"
        data-allow-outside-click="false"
        data-has-done-function="false"
        data-animation="pop"
        data-timer="null"
        style="display: none; margin-top: -171px; opacity: 0"
>
    <div class="sa-icon sa-error" style="display: none">
        <span class="sa-x-mark">
          <span class="sa-line sa-left"></span>
          <span class="sa-line sa-right"></span>
        </span>
    </div>
    <div class="sa-icon sa-warning" style="display: none">
        <span class="sa-body"></span>
        <span class="sa-dot"></span>
    </div>
    <div class="sa-icon sa-info" style="display: none"></div>
    <div class="sa-icon sa-success" style="display: block">
        <span class="sa-line sa-tip"></span>
        <span class="sa-line sa-long"></span>

        <div class="sa-placeholder"></div>
        <div class="sa-fix"></div>
    </div>
    <div class="sa-icon sa-custom" style="display: none"></div>
    <h2>Create success!</h2>
    <p style="display: block">Your imaginary file has been deleted.</p>
    <fieldset>
        <input type="text" tabindex="3" placeholder=""/>
        <div class="sa-input-error"></div>
    </fieldset>
    <div class="sa-error-container">
        <div class="icon">!</div>
        <p>Not valid!</p>
    </div>
    <div class="sa-button-container">
        <button
                class="cancel"
                tabindex="2"
                style="display: none; box-shadow: none"
        >
            Cancel
        </button>

        <button
                class="confirm"
                tabindex="1"
                style="
            display: inline-block;
            background-color: rgb(174, 222, 244);
            box-shadow: rgba(174, 222, 244, 0.8) 0px 0px 2px,
              rgba(0, 0, 0, 0.05) 0px 0px 0px 1px inset;
          "
        >
            a
        </button>
    </div>
</div>

</body>
</html>