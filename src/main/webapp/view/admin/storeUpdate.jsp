<%--
  Created by IntelliJ IDEA.
  User: LEGION
  Date: 6/18/2022
  Time: 10:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Store Update</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
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

    <!-- Select2 -->
    <link href="../../css/plugins/select2/select2.min.css" rel="stylesheet">
    <link href="../../css/plugins/select2/select2-bootstrap4.min.css" rel="stylesheet">
    <link href="../../css/plugins/dualListbox/bootstrap-duallistbox.min.css" rel="stylesheet">
    <link href="../css/plugins/select2/select2.min.css" rel="stylesheet">
    <link href="../css/plugins/select2/select2-bootstrap4.min.css" rel="stylesheet">
    <link href="../css/plugins/dualListbox/bootstrap-duallistbox.min.css" rel="stylesheet">
</head>
<body>

<div id="wrapper">
    <jsp:include page="layoutAdmin.jsp"/>
    <div class="row wrapper border-bottom white-bg page-heading">
        <div class="col-lg-10">
            <h2>Store update</h2>
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <a href="#">Home</a>
                </li>
                <li class="breadcrumb-item">
                    <a>E-commerce</a>
                </li>
                <li class="breadcrumb-item">
                    <a>Store list</a>
                </li>
                <li class="breadcrumb-item active">
                    <strong>Store update</strong>
                </li>
            </ol>
        </div>
    </div>

    <div class="wrapper wrapper-content animated fadeInRight ecommerce">

        <div class="row">
            <div class="col-lg-12">
                <div class="tabs-container">
                    <ul class="nav nav-tabs">
                        <li><a class="nav-link active" data-toggle="tab" href="#tab-1">Store update</a></li>
                    </ul>
                    <div class="tab-content">
                        <div id="tab-1" class="tab-pane active">
                            <div class="panel-body">
                                <form id="form_store_update" class="updateForm"
                                      action="<c:url value="/store/update"/>"
                                      autocomplete="off">
                                    <fieldset>
                                        <c:set var="store" value="${requestScope.store}"/>
                                        <%--                                                <div class="form-group row"><label class="col-sm-2 col-form-label">ID:</label>--%>
                                        <%--                                                    <div class="col-sm-10">--%>
                                        <%--                                                        <input type="hidden" class="form-control" name="storeId" value="${store.id}" readonly/>--%>
                                        <%--                                                    </div>--%>
                                        <%--                                                </div>
                                        --%>
                                        <input type="hidden" class="form-control" name="storeId" value="${store.id}" readonly/>
                                        <div class="form-group row">
                                            <label class="col-sm-2 col-form-label">Store Name:</label>
                                            <div class="col-sm-10">
                                                <input type="hidden" name="old_name" readonly value="${store.name}">
                                                <input type="text" class="form-control" name="name" value="${store.name}" placeholder="Store Name">
                                            </div>
                                        </div>
                                        <!-- Store manager input -->
                                        <div class="form-group row">
                                            <label class="col-sm-2 col-form-label">Store Manager:</label>
                                            <div class="col-sm-10">
                                                <c:if test="${manager == null}">
                                                    <select id="select_manager" name="manager_id" class="form-control">
                                                        <c:forEach var="acc" items="${requestScope.avManager}">
                                                                <option value="${acc.id}" >${acc.fullName}</option>
                                                        </c:forEach>
                                                    </select>
                                                </c:if>
                                                <c:if test="${manager != null}">
                                                    <input type="text" name="manager_id" class="form-control" value="${requestScope.manager.fullName}" disabled>
                                                </c:if>
                                            </div>
                                        </div>
                                        <!-- Cashier input-->
                                        <div class="form-group row">
                                            <label class="col-sm-2 col-form-label">Cashier: </label>
                                            <div class="col-sm-10">
                                                <c:if test="${cashier == null}">
                                                    <select id="select_cashier" name="cashier_id" class="form-control">
                                                        <c:forEach var="acc" items="${requestScope.avCashier}">
                                                                <option value="${acc.id}" >${acc.fullName}</option>
                                                        </c:forEach>
                                                    </select>
                                                </c:if>
                                                <c:if test="${cashier != null}">
                                                    <input type="text" name="cashier_id" class="form-control" value="${requestScope.cashier.fullName}" disabled>
                                                </c:if>
                                            </div>
                                        </div>
                                    </fieldset>
                                    <div class="form-layout-footer text-center">
                                        <button type="button" id="update_store_form"
                                                class="btn btn-primary bd-0 update_store_form">Submit
                                        </button>
                                        <a href="<c:url value="/store/list"/>" type="button" class="btn btn-dark">Cancel</a>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <jsp:include page="footer.jsp"></jsp:include>
</div>
</div>

<%--Script--%>
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

<!-- Jquery Validate -->
<script src="../../js/plugins/jquery-ui/jquery-ui.min.js"></script>
<script src="../js/plugins/jquery-ui/jquery-ui.min.js"></script>
<script src="../../js/plugins/validate/jquery.validate.min.js"></script>
<script src="../js/plugins/validate/jquery.validate.min.js"></script>

<!-- Page-Level Scripts -->
<script>
    $(document).ready(function () {
        $('.footable').footable();
    });

</script>

<!-- Sweet alert -->
<script src="js/plugins/sweetalert/sweetalert.min.js"></script>
<%-- Select2 --%>
<script src="../js/plugins/select2/select2.full.min.js"></script>
<script>

    $(document).ready(function () {
        $('.update_store_form').click(function () {
            swal({
                title: "Are you sure Update?",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "Yes, update it!",
                closeOnConfirm: false
            });
        });
        $('.confirm').click(function () {
            $(".updateForm").submit();
        });

        $("#select_manager").select2({
            theme: 'bootstrap4',
        });
        $("#select_cashier").select2({
            theme: 'bootstrap4',
        });

        $("#form_store_update").validate({
            rules: {
                name: {
                    required: true
                },
                manager_id: {
                    required: true
                },
            },
            messages: {
                name: {
                    required: "Please enter Store name"
                },
                manager_id: {
                    required: "Please select an account"
                }
            }
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
    <h2>Update success!</h2>
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
                style="display: inline-block; background-color: rgb(174, 222, 244); box-shadow: rgba(174, 222, 244, 0.8) 0px 0px 2px, rgba(0, 0, 0, 0.05) 0px 0px 0px 1px inset;">
            OK
        </button>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>
