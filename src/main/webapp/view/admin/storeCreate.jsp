<%--
  Created by IntelliJ IDEA.
  User: LEGION
  Date: 6/18/2022
  Time: 10:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

        <title>Store Create</title>

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

            <jsp:include page="layoutAdmin.jsp"></jsp:include>

                <div class="row wrapper border-bottom white-bg page-heading">
                    <div class="col-lg-10">
                        <h2>Store Create</h2>
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item">
                                <a href="index.html">Home</a>
                            </li>
                            <li class="breadcrumb-item">
                                <a>E-commerce</a>
                            </li>
                            <li class="breadcrumb-item">
                                <a>Store list</a>
                            </li>
                            <li class="breadcrumb-item active">
                                <strong>Store create</strong>
                            </li>
                        </ol>
                    </div>
                </div>

                <div class="wrapper wrapper-content animated fadeInRight ecommerce">

                    <div class="row">
                        <div class="col-lg-12">
                            <div class="tabs-container">
                                <ul class="nav nav-tabs">
                                    <li><a class="nav-link active" data-toggle="tab" href="#tab-1">Store info</a></li>
                                </ul>
                                <div class="tab-content">
                                    <div id="tab-1" class="tab-pane active">
                                        <div class="panel-body">
                                            <form id="form_store_create" class="createForm" action="${pageContext.servletContext.contextPath}/store/create" autocomplete="off">
                                            <fieldset>
                                                <div class="form-group row"><label class="col-sm-2 col-form-label">Name:</label>
                                                    <div class="col-sm-10"><input name="storeName" type="text" class="form-control" placeholder="Store name"></div>
                                                </div>
                                                <div class="form-group row"><label  class="col-sm-2 col-form-label">Store Manager:</label>
                                                    <div class="col-sm-10">
                                                        <div>
                                                            <select id="select_manager" name="select_storeManager"
                                                                    class="form-control">
                                                                <option value="0">None</option>
                                                                <c:forEach var="acc" items="${requestScope.avaiAccounts}">
                                                                    <c:if test="${acc.roleID.name eq 'Store Manager'}">
                                                                        <option value="${acc.id}" >${acc.fullName}</option>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
<%--                                                        <div id="TextAreaBtn"></div>--%>
<%--                                                        <button class="btn btn-light" type="button" id="URLbtn" onclick="createInput()">More</button>--%>
                                                    </div>
                                                </div>
                                                <div class="form-group row"><label  class="col-sm-2 col-form-label">Store Cashier:</label>
                                                    <div class="col-sm-10">
                                                        <div>
                                                            <select id="select_cashier" name="select_cashier"
                                                                    class="form-control">
                                                                <option value="0">None</option>
                                                                <c:forEach var="acc" items="${requestScope.avaiAccounts}">
                                                                    <c:if test="${acc.roleID.name eq 'Cashier'}">
                                                                        <option value="${acc.id}" >${acc.fullName}</option>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <%--                                                        <div id="TextAreaBtn"></div>--%>
                                                        <%--                                                        <button class="btn btn-light" type="button" id="URLbtn" onclick="createInput()">More</button>--%>
                                                    </div>
                                                </div>
                                            </fieldset>
                                            <div class="form-layout-footer text-center">
                                                <button type="button" class="btn btn-primary bd-0 create_store_form" id="create_store_form">Submit</button>
                                                <button onclick="history.back()" type="button" class="btn btn-dark">Cancel</button>
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

        <!-- CodeMirror -->
        <script src="../../js/plugins/codemirror/codemirror.js"></script>
        <script src="../../js/plugins/codemirror/mode/xml/xml.js"></script>
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
        <script src="../js/plugins/sweetalert/sweetalert.min.js"></script>
        <script src="../../js/plugins/sweetalert/sweetalert.min.js"></script>

        <!-- Jquery Validate -->
        <script src="../../js/plugins/jquery-ui/jquery-ui.min.js"></script>
        <script src="../js/plugins/jquery-ui/jquery-ui.min.js"></script>
        <script src="../../js/plugins/validate/jquery.validate.min.js"></script>
        <script src="../js/plugins/validate/jquery.validate.min.js"></script>

        <script>
                                                    $(document).ready(function () {

                                                        $('.footable').footable();

                                                        $("#select_storemanager").select2({
                                                            theme: 'bootstrap4',
                                                        });

                                                    });
        </script>

        <script>
            function createInput() {
                var newSelect = document.createElement("select");
                newSelect.id = 'select_storemanager';
                newSelect.name = 'storeManager';
                newSelect.className = 'form-control m-b';

                var newOption = document.createElement("option");
                newOption.value = '0';
                newOption.textContent = 'None';
                newSelect.appendChild(newOption);
                <c:forEach var="acc" items="${requestScope.listStoreManager}">
                    newOption = document.createElement("option");
                    newOption.value = '${acc.id} ${acc.id==storeManager?"selected":""}';
                    newOption.textContent = '${acc.username}';
                    newSelect.appendChild(newOption);
                </c:forEach>

                var btnElem = document.createElement("button");
                btnElem.type = "button";
                btnElem.textContent = "-";
                btnElem.className = 'btn btn-light';
                btnElem.addEventListener("click", removeUrlBox);
                
                var child = document.createElement("div");
                child.className = 'group';
                child.appendChild(newSelect);
                child.appendChild(btnElem);

                var element = document.getElementById("TextAreaBtn");
                element.appendChild(child);
            }

            function removeUrlBox() {
                this.closest('.group').remove();
            }
        </script>
        <!-- select 2 -->
        <script src="../js/plugins/select2/select2.full.min.js"></script>

        <!-- Alert -->
        <script src="js/plugins/sweetalert/sweetalert.min.js"></script>
        <script>

            $(document).ready(function () {
                $('.create_store_form').click(function () {
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

                $('#select_manager').select2({
                    theme: 'bootstrap4',
                });

                $('#select_cashier').select2({
                    theme: 'bootstrap4',
                });

                $.validator.addMethod("valueNotEquals", function (value, element, arg) {
                    return arg !== value;
                }, "Value must not equal arg.");

                $("#form_store_create").validate({
                    rules: {
                        storeName: {
                            required: true
                        },
                        storeManager: {
                            valueNotEquals: "0"
                        }
                    },
                    messages: {
                        storeName: {
                            required: "Please enter Store Name"
                        },
                        storeManager: {
                            valueNotEquals: "Please choose Store Manager"
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
