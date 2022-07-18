<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: LEGION
  Date: 6/20/2022
  Time: 5:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <title>Counter</title>

    <link href="../css/bootstrap.min.css" rel="stylesheet" />
    <link href="../font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <!-- FooTable -->
    <link href="../css/plugins/footable/footable.core.css" rel="stylesheet" />

    <link href="../css/animate.css" rel="stylesheet" />
    <link href="../css/style.css" rel="stylesheet" />

    <!-- Sweet Alert -->
    <link href="../css/plugins/sweetalert/sweetalert.css" rel="stylesheet"/>

<%--    --%>
    <link href="../css/bootstrap.min.css" rel="stylesheet" />
    <link href="../font-awesome/css/font-awesome.css" rel="stylesheet" />
<%--    <link rel="stylesheet"--%>
<%--          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">--%>
    <!-- FooTable -->
    <link href="../css/plugins/footable/footable.core.css" rel="stylesheet" />

    <link href="../css/animate.css" rel="stylesheet" />
    <link href="../css/style.css" rel="stylesheet" />
</head>
<body>

<div id="wrapper">
    <jsp:include page="layoutCounter.jsp"></jsp:include>
    <div class="row wrapper border-bottom white-bg page-heading">
        <div class="col-lg-10">
            <h2>Money Transaction</h2>
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <c:url var="index" value="${requestScope.contextPath}/counter/index"></c:url>
                    <a href="${index}">Counter</a>
                </li>
                <li class="breadcrumb-item active">
                    <strong>Money Transaction</strong>
                </li>
            </ol>
        </div>
    </div>

    <div class="wrapper wrapper-content animated fadeInRight ecommerce">
        <div class="ibox-content m-b-sm border-bottom">


            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="ibox">
                        <div class="ibox-content">
                            <table class="footable table table-stripped toggle-arrow-tiny" data-page-size="15">
                                <thead>
                                <tr>
                                    <th data-toggle="true" data-sort-ignore="true">Customer</th>

                                    <th data-hide="phone" data-sort-ignore="true">Phone number</th>

                                    <th data-sort-ignore="true">Amounts</th>

                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>${requestScope.CUSTOMER.getName()}</td>
                                    <td>${requestScope.CUSTOMER.getPhone()}</td>
<%--                                    <fmt:setLocale value="vi_VN"/>--%>
                                    <td><fmt:formatNumber value="${requestScope.BALANCE}" pattern="###,###,### ₫"/></td>

                                </tr>
                                </tbody>

                            </table>


                        </div>


                        <form method="post" id="form_create_transaction" action="">
                            <div class="form-group row pt-5">
                                <!-- <label class="col-sm-6 col-form-label">Currency</label> -->
                                <div class="col-sm-12 ">
                                    <input type="text" name="amount"
                                           class="form-control text-center h-100 d-inline-block"
                                           data-mask="000000000" placeholder="Amount" style="font-size: 3em;" autocomplete="off"
                                           maxlength="16" required>
                                    <input type="hidden" name="walletID" value="${requestScope.WALLET}">
                                    <input type="hidden" name="customerPhone" value="${requestScope.CUSTOMER.getPhone()}">
                                    <span class="form-text text-center">VNĐ</span>
                                </div>

                                <div class="col-sm-6 pt-5">
                                    <a href="<c:url value="/counter/index"/>" class="btn btn-primary dim btn-large-dim w-100 p-3"> <i class="fa fa-long-arrow-left"></i></a>

<%--                                    <button id="buttonDepositMoney" class="btn btn-warning dim btn-large-dim w-100 p-3 " type="submit"><i--%>
<%--                                            class="fa-solid fa-hand-holding-dollar"></i></button>--%>
                                </div>
                                <div class="col-sm-6 pt-5">
                                    <button id="buttonAddMoney" class="btn btn-success dim btn-large-dim w-100 p-3 " type="submit"><i
                                            class="fa fa-money"></i></button>
                                </div>

                            </div>
                        </form>
<%--                        <form>--%>
<%--                            <div class="col-sm-12 d-flex align-items-center justify-content-center">--%>

<%--                                <button formmethod="post" formaction="<c:url value="/counter/index"/>" class="btn btn-primary dim btn-large-dim w-50 p-3" type="submit">--%>
<%--                                    <i class="fa fa-long-arrow-left"></i>--%>
<%--                                </button>--%>
<%--                            </div>--%>
<%--                        </form>--%>

                    </div>
                </div>
            </div>
        </div>
        <!-- TMP -->


    </div>
    <div class="footer">
        <div class="float-right">10GB of <strong>250GB</strong> Free.</div>
        <div><strong>Copyright</strong> Example Company &copy; 2014-2018</div>
    </div>
</div>

</div>

<!-- Script -->
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

<!-- FooTable -->
<script src="../js/plugins/footable/footable.all.min.js"></script>

<!-- Jquery Validate -->
<script src="../../js/plugins/jquery-ui/jquery-ui.min.js"></script>
<script src="../js/plugins/jquery-ui/jquery-ui.min.js"></script>
<script src="../../js/plugins/validate/jquery.validate.min.js"></script>
<script src="../js/plugins/validate/jquery.validate.min.js"></script>

<!-- Sweet alert -->
<script src="../js/plugins/sweetalert/sweetalert.min.js"></script>

<!-- Page-Level Scripts -->
<script>
    $(document).ready(function () {
        $(".footable").footable();

        $('#buttonAddMoney').click(function(){
            <c:url var="addMoneyLink" value="${requestScope.contextPath}/counter/addMoney"></c:url>
            $('#form_create_transaction').attr('action', '${addMoneyLink}');
        });
        $('#buttonDepositMoney').click(function(){
            <c:url var="withdrawLink" value="${requestScope.contextPath}/counter/withDraw"></c:url>
            $('#form_create_transaction').attr('action', '${withdrawLink}');
        });

        <%--$("#buttonAddMoney").click(function () {--%>
        <%--    swal({--%>
        <%--        title: "Are you sure to create?",--%>
        <%--        type: "info",--%>
        <%--        showCancelButton: true,--%>
        <%--        confirmButtonColor: "#DD6B55",--%>
        <%--        confirmButtonText: "Yes, create it!",--%>
        <%--        closeOnConfirm: false,--%>
        <%--    });--%>
        <%--});--%>
        <%--$(".confirm").click(function () {--%>
        <%--    &lt;%&ndash;window.location = "${createLink}";&ndash;%&gt;--%>
        <%--    $(".createForm").submit();--%>
        <%--});--%>
        <c:if test="${sessionScope.addStatus != null}">
        swal({
            title: "Add Money Success!",
            <fmt:setLocale value="vi_VN"/>
            text: "<fmt:formatNumber value="${sessionScope.AMOUNT}" type="currency"/> has just added success",
            type: "success"
        });
        <%
        session.removeAttribute("addStatus");
        session.removeAttribute("AMOUNT");
        %>
        </c:if>
<%--        <c:if test="${sessionScope.withdrawStatus != null}">--%>
<%--        swal({--%>
<%--            title: "Add Money Success!",--%>
<%--            text: "You clicked the button!",--%>
<%--            type: "success"--%>
<%--        });--%>
<%--        <%--%>
<%--        session.removeAttribute("withdrawStatus");--%>
<%--        %>--%>
<%--        </c:if>--%>

        $('#form_create_transaction').validate({
            rules: {
                amount: {
                    required: true,
                    min: 1000
                }
            },
            messages: {
                amount: {
                    required: 'Please enter amount',
                    min: 'Amount must be greater than 1000'
                }
            }
        })
    });
</script>
<script src="../../js/plugins/jqueryMask/jquery.mask.min.js"></script>
<script src="../js/plugins/jqueryMask/jquery.mask.min.js"></script>



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
