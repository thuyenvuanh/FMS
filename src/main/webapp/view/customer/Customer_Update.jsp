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
    <link href="../css/bootstrap.min.css" rel="stylesheet"/>
    <link href="../font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <!-- FooTable -->
    <link href="../css/plugins/footable/footable.core.css" rel="stylesheet"/>
    <!-- Sweet Alert -->
    <link href="../css/plugins/sweetalert/sweetalert.css" rel="stylesheet"/>

    <link href="../css/plugins/datapicker/datepicker3.css" rel="stylesheet">

    <link href="../css/animate.css" rel="stylesheet"/>
    <link href="../css/style.css" rel="stylesheet"/>

</head>

<body>
<div id="wrapper">
    <jsp:include page="../counter/layoutCounter.jsp"></jsp:include>

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
                <fieldset id="form-p-0" role="tabpanel" aria-labelledby="form-h-0" class="body current"
                          aria-hidden="false">
                    <h2>Update Information</h2>
                    <div class="row">

                        <c:forEach var="update" items="${requestScope.info}">
                            <%--                                    class="btn btn-primary"--%>
                            <c:url var="CustomerUpdate" value="${requestScope.contextPath}/customer/update">
                            </c:url>
                            <form action="${CustomerUpdate}" id="formUpdateCus">
                                <div class="col-lg-8">
                                    <div class="form-group">
                                        <label>Username</label>
                                        <input name="name" type="text"
                                               value="${update.name}" class="form-control required"
                                               aria-required="true">
                                    </div>
                                    <div class="form-group">
                                        <label>Phone</label>
                                        <input name="phone" value="${update.phone}" readonly
                                               class="form-control required" aria-required="true">
                                    </div>
                                    <div class="form-group" id="update-Dob">
                                        <label>Date of Birth</label>
                                        <input
                                                name="Dob" type="text"
                                                readonly
                                                id="input-Dob"
                                                value="2003-01-01"
                                                class="form-control input-Dob" data-mask="00/00/0000" autocomplete="off"
                                                maxlength="10">
                                        <c:if test="${requestScope.InvalidDate != null}">
                                            <span class="text-warning" id="alertDate">
                                                    ${requestScope.InvalidDate}
                                            </span>
                                        </c:if>
                                        <span class="form-text">yyyy-MM-dd</span>
                                    </div>

                                    <div class="form-group">
                                        <label>Address</label>
                                        <input placeholder="${update.address}"
                                               name="address" type="text"
                                               class="form-control required" aria-required="true">
                                    </div>

                                    <div class="form-group">
                                        <label>Gender</label>
                                        <select name="gender" class="form-control required"
                                                aria-required="true">
                                            <option value="female">Female</option>
                                            <option value="male">Male</option>
                                            <option value="none">None</option>
                                        </select>
                                    </div>


                                </div>

                                <div class="w-100 p-3">
                                        <%--                                    <a href="#next" class="btn btn-primary" role="menuitem">Apply</a>--%>
                                    <a class="btn btn-warning"
                                       href="${requestScope.contextPath}/FMS/customer/list">Back</a>
                                    <input type="button" value="submit" id="btnUpdateCus" class="btn btn-primary">
                                </div>
                            </form>
                        </c:forEach>
                    </div>
                </fieldset>
            </div>

        </div>

        <!-- TMP -->


    </div>
    <jsp:include page="../counter/footer.jsp"></jsp:include>
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

<!-- Data picker -->
<script src="../js/plugins/datapicker/bootstrap-datepicker.js"></script>

<!-- Sweet alert -->
<script src="../js/plugins/sweetalert/sweetalert.min.js"></script>

<!-- Jquery Validate -->

<script src="../../js/plugins/validate/jquery.validate.min.js"></script>
<script src="../js/plugins/validate/jquery.validate.min.js"></script>

<!-- Page-Level Scripts -->
<script>
    $(document).ready(function () {

        $('#input-Dob').change(function(){
            $('#alertDate').hide();
        });

        $('#update-Dob .input-Dob').datepicker({
            keyboardNavigation: false,
            forceParse: false,
            autoclose: true,
            format: "yyyy-mm-dd"
        });
        $("#btnUpdateCus").click(function () {
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
                        $("#formUpdateCus").submit();
                    } else {
                        swal("Cancelled", "", "error");
                    }
                });
        });

        $("#formUpdateCus").validate({
            rules: {
                name: {
                    required: true
                },
                Dob: {
                    required: true
                },
                address: {
                    required: true
                },
                gender: {
                    required: true
                }
            },
            messages: {
                name: {
                    required: 'Please enter name'
                },
                Dob: {
                    required: 'Please enter date'
                },
                address: {
                    required: 'Please enter address'
                },
                gender: {
                    required: 'Please enter gender'
                }
            }
        })
    })
    ;
</script>

<script src="js/plugins/jqueryMask/jquery.mask.min.js"></script>
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