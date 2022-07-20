<%--
  Created by IntelliJ IDEA.
  User: LEGION
  Date: 6/18/2022
  Time: 10:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Store View</title>

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
            <jsp:include page="layoutAdmin.jsp"></jsp:include>

                <div class="row wrapper border-bottom white-bg page-heading">
                    <div class="col-lg-10">
                        <h2>Store view</h2>
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
                                <strong>Store view</strong>
                            </li>
                        </ol>
                    </div>
                </div>

                <div class="wrapper wrapper-content animated fadeInRight ecommerce">

                    <div class="row">
                        <div class="col-lg-12">
                            <div class="tabs-container">
                                <ul class="nav nav-tabs">
                                    <li><a class="nav-link active" data-toggle="tab" href="#tab-1">Store detail</a></li>
                                </ul>
                                <div class="tab-content">
                                    <div id="tab-1" class="tab-pane active">
                                        <div class="panel-body">
                                            <c:set var="store" value="${requestScope.store}"></c:set>
                                            <fieldset>
                                                <div class="form-group row"><label class="col-sm-2 col-form-label">ID:</label>
                                                    <div class="col-sm-10 text-align">${store.id}</div>
                                                </div>
                                                <div class="form-group row"><label class="col-sm-2 col-form-label">Store Name:</label>
                                                    <div class="col-sm-10 text-align" style="padding: 7px 15px;">${store.name}</div>
                                                </div>
                                                <div class="form-group row"><label class="col-sm-2 col-form-label">Store Manager:</label>
                                                    <div class="col-sm-10" style="padding: 7px 15px;">
                                                    <c:forEach var="acc" items="${requestScope.accountList}">
                                                        <c:if test="${acc.roleID.name eq 'Store Manager'}">
                                                            <div class="text-align">${acc.fullName}</div>
                                                        </c:if>
                                                    </c:forEach>
                                                    </div>
                                                </div>
                                                <div class="form-group row"><label class="col-sm-2 col-form-label">Cashier: </label>
                                                    <div class="col-sm-10" style="padding: 7px 15px;">
                                                        <c:forEach var="acc" items="${requestScope.accountList}">
                                                            <c:if test="${acc.roleID.name eq 'Cashier'}">
                                                                <div class="text-align">${acc.fullName}</div>
                                                            </c:if>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                                <div class="form-group row"><label class="col-sm-2 col-form-label">Status:</label>
                                                    <div class="col-sm-10 text-align">
                                                        <c:if test="${store.isDeleted == false}">
                                                            <span class="label label-primary">Enable</span>
                                                        </c:if>
                                                        <c:if test="${store.isDeleted == true}">
                                                            <span class="label label-danger">Disable</span>
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </fieldset>
                                            <div class="form-layout-footer text-center">
                                                <c:url var="updateLink" value="${requestScope.contextPath}/store/updatePage">
                                                    <c:param name="storeId" value="${store.id}"></c:param>
                                                </c:url>
                                                <c:url var="deleteLink" value="${requestScope.contextPath}/store/delete">
                                                    <c:param name="storeId" value="${store.id}"></c:param>
                                                </c:url>
                                                <a href="${deleteLink}">
                                                    <button class="btn btn-danger bd-0">Delete</button>
                                                </a>
                                                <a href="${updateLink}">
                                                    <button class="btn btn-primary bd-0">Update</button>
                                                </a>
                                                <button onclick="history.back()" type="button" class="btn btn-secondary bd-0">Back to list</button>

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

    <!-- Page-Level Scripts -->
    <script>
        $(document).ready(function () {

            $('.footable').footable();

        });

    </script>
</body>
</html>
