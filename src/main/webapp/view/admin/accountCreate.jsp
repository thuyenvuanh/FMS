<%--
  Created by IntelliJ IDEA.
  User: LEGION
  Date: 6/18/2022
  Time: 2:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.fptuni.fms.model.Role"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

        <title>Account Create - FMS</title>
        <link href="../css/bootstrap.min.css" rel="stylesheet"/>
        <link href="../font-awesome/css/font-awesome.css" rel="stylesheet"/>

        <!-- FooTable -->
        <link href="../css/plugins/footable/footable.core.css" rel="stylesheet"/>

        <link href="../css/animate.css" rel="stylesheet"/>
        <link href="../css/style.css" rel="stylesheet"/>
        <!-- Sweet Alert -->
        <link href="../css/plugins/sweetalert/sweetalert.css" rel="stylesheet"/>

        <link href="../css/plugins/select2/select2.min.css" rel="stylesheet">
        <link href="../css/plugins/select2/select2-bootstrap4.min.css" rel="stylesheet">
        <link href="../css/plugins/dualListbox/bootstrap-duallistbox.min.css" rel="stylesheet"
    </head>
    <body>

        <div id="wrapper">

            <jsp:include page="layoutAdmin.jsp"/>

                <div class="row wrapper border-bottom white-bg page-heading">
                    <div class="col-lg-10">
                        <h2>Account Create</h2>
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item">
                                <a href="<c:url value="/adminDashboard/index" />">Home</a>
                            </li>
                            <li class="breadcrumb-item">
                                <a href="<c:url value="/account/list"/> ">Account list</a>
                            </li>
                            <li class="breadcrumb-item active">
                                <strong>Account create</strong>
                            </li>
                        </ol>
                    </div>
                </div>

                <div class="wrapper wrapper-content animated fadeInRight ecommerce">

                    <div class="row">
                        <div class="col-lg-12">
                            <div class="tabs-container">
                                <ul class="nav nav-tabs">
                                    <li><a class="nav-link active" data-toggle="tab" href="#tab-1">Account info</a></li>
                                </ul>
                                <div class="tab-content">
                                    <div id="tab-1" class="tab-pane active">
                                        <div class="panel-body">

                                            <form id="form_account_create" method="POST" class="createForm" action="${pageContext.servletContext.contextPath}/account/create" autocomplete="off">
                                            <fieldset>
                                                <div class="form-group row"><label class="col-sm-2 col-form-label">Username:</label>
                                                    <div class="col-sm-10"><input name="username" type="text" class="form-control" placeholder="Username"></div>
                                                </div>
                                                <div class="form-group row"><label class="col-sm-2 col-form-label">Full Name:</label>
                                                    <div class="col-sm-10"><input name="fullName" type="text" class="form-control" placeholder="Full Name"></div>
                                                </div>
                                                <div class="form-group row"><label class="col-sm-2 col-form-label">Password:</label>
                                                    <div class="col-sm-10"><input name="password" type="password" class="form-control password" placeholder="Password"></div>
                                                </div>
                                                <div class="form-group row"><label class="col-sm-2 col-form-label">Confirm password:</label>
                                                    <div class="col-sm-10"><input name="cfPassword" type="password" class="form-control" placeholder="Confirm password"></div>
                                                </div>
                                                <div class="form-group row"><label  class="col-sm-2 col-form-label">Role:</label>
                                                    <div class="col-sm-10">
                                                        <select class="form-control m-b" id="select_role" name="roleId">
                                                            <option value="0">None</option>
                                                            <c:forEach var="role" items="${requestScope.listRole}">
                                                                <option value="${role.id}" ${role.id==roleId?"selected":""} >${role.name}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </fieldset>
                                            <div class="form-layout-footer text-center">
                                                <button type="button" class="btn btn-primary bd-0 create_account_form" id="create_account_form">Submit</button>
                                                <a href="<c:url value="/account/list"/>" class="btn btn-dark">Cancel</a>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <jsp:include page="footer.jsp"/>
        </div>

    <!-- CodeMirror -->
    <script src="../js/plugins/codemirror/codemirror.js"></script>
    <script src="../js/plugins/codemirror/mode/xml/xml.js"></script>
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
        <script src="../js/plugins/jquery-ui/jquery-ui.min.js"></script>
        <script src="../js/plugins/validate/jquery.validate.min.js"></script>

        <%-- Select2 --%>
        <script src="../js/plugins/select2/select2.full.min.js"></script>
    <!-- Page-Level Scripts -->

        <c:if test="${sessionScope.createStatus != null}">
            <script>
                $(document).ready(function () {
                    swal({
                        title: "Create Fail! Username exist!",
                        text: "You clicked the button!"
                    });
                });
            </script>
            <%
                session.removeAttribute("createStatus");
            %>
        </c:if>

        <script>

            $(document).ready(function () {
                $('.create_account_form').click(function () {
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
                    $(".createForm").submit();
                });

                $.validator.addMethod("valueNotEquals", function(value, element, arg){
                    return arg !== value;
                }, "Value must not equal arg.");

                $("#form_account_create").validate({
                    rules: {
                        username: {
                            required: true,
                        },
                        fullName: {
                            required: true,
                        },
                        password: {
                            required: true,
                            minlength: 5
                        },
                        cfPassword:{
                            required: true,
                            equalTo: "input[name=password]"
                        },
                        roleId: {
                            valueNotEquals: "0"
                        }
                    },
                    messages: {
                        username: {
                            required: "Please enter username",
                        },
                        fullName: {
                            required: "Please enter full name",
                        },
                        password: {
                            required: "Please enter password",
                            minlength: "Please enter at least 5 characters"
                        },
                        cfPassword:{
                            required: "Please enter confirm password",
                            equalTo: "Confirm password not same as password above"
                        },
                        roleId: {
                            valueNotEquals: "Please choose role"
                        }
                    }
                });
            });
        </script>

        <script>
            $(document).ready(function () {

                $('.footable').footable();

                $("#select_role").select2({
                    theme: 'bootstrap4',
                });




            })


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
