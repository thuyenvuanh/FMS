<%--
  Created by IntelliJ IDEA.
  User: ttcha
  Date: 6/18/2022
  Time: 3:30 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Customer List</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet"/>
    <link href="../font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <%--    Jquery--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

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
            <h2>E-commerce orders</h2>
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <a href="index.html">Home</a>
                </li>
                <li class="breadcrumb-item">
                    <a href="">FMS</a>
                </li>
                <li class="breadcrumb-item active">
                    <strong>Orders</strong>
                </li>
            </ol>
        </div>
    </div>

    <div class="wrapper wrapper-content animated fadeInRight ecommerce">
        <div class="ibox-content m-b-sm border-bottom">
            <c:url var="searchfield"
                   value="${requestScope.contextPath}/customer/search"></c:url>
            <form action="${searchfield}">
                <div class="row">
                    <%--                        <div class="col-lg-2">--%>
                    <%--                            <div class="form-group">--%>
                    <%--                                <label class="col-form-label" for="status"--%>
                    <%--                                >Order status</label--%>
                    <%--                                >--%>
                    <%--                                <select name="status" id="status" class="form-control">--%>
                    <%--                                    <option value="" selected="">None</option>--%>
                    <%--                                    <option value="1">Active</option>--%>
                    <%--                                    <option value="0">Inactive</option>--%>
                    <%--                                </select>--%>
                    <%--                            </div>--%>
                    <%--                        </div>--%>
                    <div class="col-xl-5 col-lg-9 col-md-12 text-left">
                        <div class="form-group">
                            <%--                                for="status"--%>
                            <label class="col-form-label">Search by</label>
                            <div class="input-group m-b">
                                <div class="input-group-prepend">
                                    <select name="" id="" class="form-control">
                                        <option value="">Phone</option>
                                    </select>
                                </div>
                                <input name="searchItem"
                                       type="text" class="form-control"/>
                                <button name="action"
                                        class="btn btn-outline-success float-right"
                                        type="submit"
                                >
                                    Search
                                </button>
                            </div>
                            <c:if test="${requestScope.totalPages == 0}">
                                <span class="text-warning">
                                    NO CUSTOMER FOUND
                                </span>
                            </c:if>
                        </div>
                    </div>
                    <%--                        <div class="col-lg-2 container-fluid pt-5">--%>
                    <%--                            <button name="action"--%>
                    <%--                                    class="btn btn-outline-success float-right"--%>
                    <%--                                    type="submit"--%>
                    <%--                            >--%>
                    <%--                                Search--%>
                    <%--                            </button>--%>
                    <%--                        </div>--%>
                </div>
            </form>

        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="ibox">
                    <div class="ibox-content">
                        <table
                                class="footable table table-stripped toggle-arrow-tiny"
                                data-page-size="15"
                        >
                            <thead>
                            <tr>
                                <th data-hide="phone">Customer</th>

                                <th data-hide="phone">Phone number</th>

                                <%--                                    <th>Status</th>--%>

                                <th class="text-right">Amounts</th>

                                <th class="text-right">Actions</th>


                            </tr>
                            </thead>
                            <tbody>

                            <%--For listing--%>
                            <c:set var="amount" value="${requestScope.amountlist}"></c:set>
                            <c:if test="${requestScope.customerList != null}">
                                <c:forEach var="list" items="${requestScope.customerList}">

                                    <tr>
                                        <td>${list.name}</td>
                                        <td class="text-left">${list.phone}</td>

                                        <td class="text-right"><fmt:formatNumber
                                                value="${amount.get(list)}" pattern="###,###,### ₫"/></td>

                                        <td class="text-right">
                                            <c:url var="delete" value="${requestScope.contextPath}/customer/remove"></c:url>
                                            <form class="form_${list.phone}"
                                                  action="${delete}">
                                                <input type="hidden" name="phonenum" value="${list.phone}">
                                                <button type="button" class="btn btn-primary btn-sm delete_${list.phone}">
                                                    Delete
                                                </button>

                                                <a
                                                        class="btn btn-primary btn-sm ${list.phone}">Detail</a>
                                                <a href="<%=request.getContextPath()%>/customer/Movetoupdate?phonenum=${list.phone}"
                                                   class="btn btn-primary btn-sm">Update</a>
                                            </form>
                                        </td>
                                    </tr>

                                    <%-- Show detail here--%>
                                    <input name="var" value="${list.phone}" type="hidden">
                                    <tr id="${list.phone}" style="display: none">
                                        <td colspan="4">
                                            <div>Customer Id: ${list.id}</div>
                                            <c:choose>
                                                <c:when test="${list.doB != null}">
                                                    <div>DoB: ${list.doB}</div>
                                                </c:when>
                                                <c:when test="${list.doB == null}">
                                                    <div>DoB: None</div>
                                                </c:when>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${list.address != null}">
                                                    <div>Address: ${list.address}</div>
                                                </c:when>
                                                <c:when test="${list.address == null}">
                                                    <div>DoB: None</div>
                                                </c:when>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${list.gender == 0}">
                                                    <div>Gender: Male</div>
                                                </c:when>
                                                <c:when test="${list.gender == 1}">
                                                    <div>Gender: Female</div>
                                                </c:when>
                                                <c:when test="${list.gender == 2}">
                                                    <div>Gender: None</div>
                                                </c:when>
                                            </c:choose>
                                        </td>
                                    </tr>

                                </c:forEach>
                            </c:if>
                            </tbody>

                            <tfoot>
                            <tr>

                                <c:if test="${requestScope.totalPages != 0}">
                                    <td colspan="6">
                                        <nav aria-label="Page navigation example">
                                            <ul class="paginations">
                                                <li class="page-item ${requestScope.currentPage == 1?"disabled":""}">
                                                    <c:url var="previousPage"
                                                           value="${requestScope.contextPath}/customer/list">
                                                        <c:param name="page"
                                                                 value="${requestScope.currentPage - 1}"></c:param>
                                                        <c:param name="sortField"
                                                                 value="${requestScope.sortField}"></c:param>
                                                        <c:param name="isAscending"
                                                                 value="${!requestScope.isAsc}"></c:param>
                                                    </c:url>
                                                    <a
                                                            class="page-link"
                                                            href="${previousPage}"
                                                            aria-label="Previous"
                                                    >
                                                        <span aria-hidden="true">&laquo;</span>
                                                        <span class="sr-only">Previous</span>
                                                    </a>
                                                </li>
                                                <c:forEach begin="1" end="${requestScope.totalPages}" var="page">
                                                    <c:url var="paging"
                                                           value="${requestScope.contextPath}/customer/list">
                                                        <c:param name="page" value="${page}"></c:param>
                                                        <c:param name="sortField"
                                                                 value="${requestScope.sortField}"></c:param>
                                                        <c:param name="isAscending"
                                                                 value="${!requestScope.isAsc}"></c:param>
                                                    </c:url>
                                                    <li class="page-item ${requestScope.currentPage == page ?"active":""}">
                                                        <a class="page-link "
                                                           href="${paging}">${page}</a>
                                                    </li>
                                                </c:forEach>
                                                <li class="page-item ${requestScope.currentPage == requestScope.totalPages?"disabled":""}">
                                                    <c:url var="nextPage"
                                                           value="${requestScope.contextPath}/customer/list">
                                                        <c:param name="page"
                                                                 value="${requestScope.currentPage + 1}"></c:param>
                                                        <c:param name="sortField"
                                                                 value="${requestScope.sortField}"></c:param>
                                                        <c:param name="isAscending"
                                                                 value="${!requestScope.isAsc}"></c:param>
                                                    </c:url>
                                                    <a class="page-link" href="${nextPage}" aria-label="Next">
                                                        <span aria-hidden="true">&raquo;</span>
                                                        <span class="sr-only">Next</span>
                                                    </a>
                                                </li>
                                            </ul>
                                        </nav>
                                    </td>
                                </c:if>


                            </tr>
                            </tfoot>


                        </table>
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
<!-- Body -->

</div>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="../js/plugins/validate/jquery.validate.min.js"></script>

<!-- Custom and plugin javascript -->
<script src="js/inspinia.js"></script>
<script src="js/plugins/pace/pace.min.js"></script>

<!-- FooTable -->
<script src="js/plugins/footable/footable.all.min.js"></script>

<!-- Sweet alert -->
<script src="../js/plugins/sweetalert/sweetalert.min.js"></script>

<!-- Page-Level Scripts -->
<script>
    $(document).ready(function () {
        $(".footable").footable();

        $('#btnDeleteCus').click(function () {
            swal({
                title: "Are you sure?",
                text: "You will not be able to recover this imaginary file!",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "Yes, delete it!",
                closeOnConfirm: false
            }, function () {
                swal("Deleted!", "Your imaginary file has been deleted.", "success");
            });
        });

    });
</script>

<script>
    <c:forEach var="list" items="${requestScope.customerList}">
    $(".${list.phone}").click(function () {
        //var searchString = $('#showMore').val();
        if ($("#${list.phone}").css('display') == 'none') {
            $("#${list.phone}").css("display", "block").filter(function () {
                //return $(this).text().trim() === searchString;
            })
        } else if ($("#${list.phone}").css('display') == 'block') {
            $("#${list.phone}").css("display", "none").filter(function () {
                //return $(this).text().trim() === searchString;
            })
        }
    });

    $(".delete_${list.phone}").click(function () {
        swal({
                title: "Are you sure?",
                text: "Your will not be able to recover customer!",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "Yes, delete it!",
                cancelButtonText: "No, cancel!",
                closeOnConfirm: false,
                closeOnCancel: false
            },
            function (isConfirm) {
                if (isConfirm) {
                    $(".form_${list.phone}").submit();
                } else {
                    swal("Cancelled", "", "error");
                }
            });
    });

    </c:forEach>
    <c:if test="${sessionScope.deletestatus != null}">
    swal({
        title: "Deleted!",
        text: "Customer has been deleted.",
        type: "success"
    });
    <%
    session.removeAttribute("deletestatus");
    %>
    </c:if>

    <c:if test="${sessionScope.updateStatus != null}">
    swal({
        title: "Update!",
        text: "Customer has been updated.",
        type: "success"
    });
    <%
    session.removeAttribute("updateStatus");
    %>
    </c:if>
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