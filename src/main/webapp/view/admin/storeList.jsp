<%--
  Created by IntelliJ IDEA.
  User: LEGION
  Date: 6/18/2022
  Time: 2:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Store List</title>

        <link href="../css/bootstrap.min.css" rel="stylesheet"/>
        <link href="../font-awesome/css/font-awesome.css" rel="stylesheet"/>

        <!-- FooTable -->
        <link href="../css/plugins/footable/footable.core.css" rel="stylesheet"/>

        <link href="../css/animate.css" rel="stylesheet"/>
        <link href="../css/style.css" rel="stylesheet"/>
        <!-- Sweet Alert -->
        <link href="../css/plugins/sweetalert/sweetalert.css" rel="stylesheet"/>
    </head>
    <body>


        <div id="wrapper">
            <jsp:include page="layoutAdmin.jsp"/>

                <div class="row wrapper border-bottom white-bg page-heading">
                    <div class="col-lg-10">
                        <h2>Store list</h2>
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item">
                                <a href="<c:url value="/adminDashboard/index"/>">Home</a>
                            </li>
                            <li class="breadcrumb-item active">
                                <strong>Store list</strong>
                            </li>
                        </ol>
                    </div>
                </div>



                <div class="ibox-content m-b-sm border-bottom">
                    <form>
                        <div class="row">
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label class="col-form-label" for="store_name">Store Name</label>
                                    <input type="text" id="store_name" name="name" value="${name}"
                                           placeholder="Store Name" class="form-control">
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label class="col-form-label" for="store_manager">Store Manager</label>
                                    <input type="text" id="store_manager" name="storeManager" value="${storeManager}"
                                           placeholder="Store Manager" class="form-control">
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label class="col-form-label" for="status">Status</label>
                                    <select name="status" id="status" class="form-control">
                                        <option value="0" ${status == 0 ? "selected" : ""}>Enabled</option>
                                        <option value="1" ${status == 1 ? "selected" : ""}>Disabled</option>
                                    </select>
                                </div>
                            </div>
                            <div class="container-fluid">
                                <button class="btn btn-outline-success  float-right" type="submit" formaction="<c:url value="/store/search"/>">Search</button>
                            </div>
                        </div>
                    </form>
                </div>

                <nav class="navbar navbar-light bg-light">
                    <div class="container-fluid">
                        <a href="${pageContext.servletContext.contextPath}/store/createPage" class="">
                            <button class="btn btn-outline-primary" type="submit">Create</button>
                        </a>
                    </div>
                </nav>
            <div class="wrapper wrapper-content animated fadeInRight ecommerce">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="ibox">
                            <div class="ibox-content">

                                <table class="footable table table-stripped toggle-arrow-tiny" data-page-size="15">
                                    <thead>
                                        <c:url var="sort" value="${requestScope.contextPath}/store/list">
                                            <c:param name="page" value="${requestScope.currentPage}"/>
                                            <c:param name="isAscending" value="${requestScope.isAsc}"/>
                                        </c:url>
                                        <tr>
                                            <th data-toggle="true" data-sort-ignore="true">
                                                <a href="${sort}&sortField=Name">Store</a>
                                            </th>
                                            <th class="text-left" data-sort-ignore="true">Store Manager</th>
                                            <th data-hide="phone" data-sort-ignore="true">Status</th>
                                            <th class="text-right" data-sort-ignore="true">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="store" items="${map}" >
                                            <c:set var="key" value="${store.key}" target="com.fptuni.fms.model.Store"/>
                                            <c:set var="value" value="${store.value}" target="java.util.List"/>
                                            <tr>
                                                <td>${key.name}</td>
                                                <td>
                                                    <c:forEach var="acc" items="${value}">
                                                        ${acc.roleID.name == "Store Manager" ? acc.fullName : ""}
                                                    </c:forEach>
                                                </td>
                                                <c:if test="${key.isDeleted == false}">
                                                    <td>
                                                        <span class="label label-primary">Enable</span>
                                                    </td>
                                                </c:if>
                                                <c:if test="${key.isDeleted == true}">
                                                    <td>
                                                        <span class="label label-danger">Disable</span>
                                                    </td>
                                                </c:if>
                                                <td class="text-right">
                                                    <form>
                                                        <input  type="hidden" name="storeId" value="${key.id}"/>
                                                            <c:url var="viewLink" value="/store/view">
                                                                <c:param name="storeId" value="${key.id}"/>
                                                            </c:url>
                                                            <c:url var="updateLink" value="/store/updatePage">
                                                                <c:param name="storeId" value="${key.id}"/>
                                                            </c:url>
                                                            <button class="btn-white btn btn-xs" formmethod="post" formaction="${viewLink}">View</button>
                                                            <button class="btn-white btn btn-xs" formmethod="post" formaction="${updateLink}">Edit</button>
                                                    </form>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <td colspan="6">
                                                <nav aria-label="Page navigation example">
                                                    <ul class="paginations">
                                                        <li class="page-item ${requestScope.currentPage == 1?"disabled":""}">
                                    <c:choose>
                                        <c:when test="${
                                            (requestScope.name != null && requestScope.name ne '')
                                        ||  (requestScope.storeManager != null && requestScope.storeManager ne '')
                                        }">
                                            <c:url var="previousPage" value="${requestScope.contextPath}/store/search">
                                                <c:param name="page" value="${requestScope.currentPage - 1}"/>
                                                <c:param name="sortField" value="${requestScope.sortField}"/>
                                                <c:param name="isAscending" value="${!requestScope.isAsc}"/>
                                                <c:param name="name" value="${requestScope.name}"/>
                                                <c:param name="storeManager" value="${requestScope.storeManager}"/>
                                                <c:param name="status" value="${requestScope.status}"/>
                                            </c:url>
                                        </c:when>
                                        <c:otherwise>
                                            <c:url var="previousPage" value="${requestScope.contextPath}/store/list">
                                                <c:param name="page" value="${requestScope.currentPage - 1}"/>
                                                <c:param name="sortField" value="${requestScope.sortField}"/>
                                                <c:param name="isAscending" value="${!requestScope.isAsc}"/>
                                            </c:url>
                                        </c:otherwise>
                                    </c:choose>

                                                            <a class="page-link " href="${previousPage}" aria-label="Previous" >
                                                                <span aria-hidden="true">&laquo;</span>
                                                                <span class="sr-only">Previous</span>
                                                            </a>
                                                        </li>
                                                        <c:forEach begin="1" end="${requestScope.totalPages}" var="page">
                                    <c:choose>
                                        <c:when test="${
                                            (requestScope.name != null && requestScope.name ne '')
                                        ||  (requestScope.storeManager != null && requestScope.storeManager ne '')
                                        }">
                                            <c:url var="paging" value="${requestScope.contextPath}/store/search">
                                                <c:param name="page" value="${page}"/>
                                                <c:param name="sortField" value="${requestScope.sortField}"/>
                                                <c:param name="isAscending" value="${!requestScope.isAsc}"/>
                                                <c:param name="name" value="${requestScope.name}"/>
                                                <c:param name="storeManager" value="${requestScope.storeManager}"/>
                                                <c:param name="status" value="${requestScope.status}"/>
                                            </c:url>
                                        </c:when>
                                        <c:otherwise>
                                            <c:url var="paging" value="${requestScope.contextPath}/store/list">
                                                <c:param name="page" value="${page}"/>
                                                <c:param name="sortField" value="${requestScope.sortField}"/>
                                                <c:param name="isAscending" value="${!requestScope.isAsc}"/>
                                            </c:url>
                                        </c:otherwise>
                                    </c:choose>
                                                            <li class="page-item ${requestScope.currentPage == page ?"active":""}">
                                                                <a class="page-link " href="${paging}">${page}</a>
                                                            </li>
                                                        </c:forEach>
                                                        <li class="page-item ${requestScope.currentPage == requestScope.totalPages?"disabled":""}">
                                    <c:choose>
                                        <c:when test="${
                                            (requestScope.name != null && requestScope.name ne '')
                                        ||  (requestScope.storeManager != null && requestScope.storeManager ne '')
                                        }">
                                            <c:url var="nextPage" value="${requestScope.contextPath}/store/search">
                                                <c:param name="page" value="${requestScope.currentPage + 1}"/>
                                                <c:param name="sortField" value="${requestScope.sortField}"/>
                                                <c:param name="isAscending" value="${!requestScope.isAsc}"/>
                                                <c:param name="name" value="${requestScope.name}"/>
                                                <c:param name="storeManager" value="${requestScope.storeManager}"/>
                                                <c:param name="status" value="${requestScope.status}"/>
                                            </c:url>
                                        </c:when>
                                        <c:otherwise>
                                            <c:url var="nextPage" value="${requestScope.contextPath}/store/list">
                                                <c:param name="page" value="${requestScope.currentPage + 1}"/>
                                                <c:param name="sortField" value="${requestScope.sortField}"/>
                                                <c:param name="isAscending" value="${!requestScope.isAsc}"/>
                                            </c:url>
                                        </c:otherwise>
                                    </c:choose>
                                                            <a class="page-link" href="${nextPage}" aria-label="Next">
                                                                <span aria-hidden="true">&raquo;</span>
                                                                <span class="sr-only">Next</span>
                                                            </a>
                                                        </li>
                                                    </ul>
                                                </nav>
                                            </td>
                                        </tr>
                                    </tfoot>
                                </table>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="footer.jsp"/>
        </div>
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
        <c:if test="${sessionScope.createStatus != null}">
            <script>
                $(document).ready(function () {
                    swal({
                        title: "Create Success!",
                        text: "Store created",
                        type: "success"
                    });
                });
            </script>
            <%
                session.removeAttribute("createStatus");
            %>
        </c:if>

        <c:if test="${sessionScope.updateStatus != null}">
            <script>
                $(document).ready(function () {
                    swal({
                        title: "Update Success!",
                        text: "You clicked the button!",
                        type: "success"
                    });
                });
            </script>
            <%
                session.removeAttribute("updateStatus");
            %>
        </c:if>
        <c:if test="${sessionScope.deleteStatus != null}">
            <script>
                $(document).ready(function () {
                    swal({
                        title: "Delete Success!",
                        text: "You clicked the button!",
                        type: "success"
                    });
                });
            </script>
            <%
                session.removeAttribute("deleteStatus");
            %>
        </c:if>
        <script>
            $(document).ready(function () {

                $('.footable').footable();

            });

        </script>




        <!-- ------- -->
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
                        style="display: inline-block; background-color: rgb(174, 222, 244); box-shadow: rgba(174, 222, 244, 0.8) 0px 0px 2px, rgba(0, 0, 0, 0.05) 0px 0px 0px 1px inset;">OK</button>
            </div>
        </div>
    </body>
</html>
