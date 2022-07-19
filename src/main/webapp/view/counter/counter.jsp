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

<%--    --%>
    <link href="../css/bootstrap.min.css" rel="stylesheet" />
    <link href="../font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
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
                                    <fmt:setLocale value="vi_VN"/>
                                    <td><fmt:formatNumber value="${requestScope.BALANCE}" type="currency"/></td>

                                </tr>
                                </tbody>

                            </table>


                        </div>

                        <form id="form_create_transaction" action="">
                            <div class="form-group row pt-5">
                                <!-- <label class="col-sm-6 col-form-label">Currency</label> -->

                                <div class="col-sm-12 ">
                                    <input type="text" name="amount"
                                           class="form-control text-center h-100 d-inline-block"
                                           data-mask="000 000 000" placeholder="Amount" style="font-size: 3em;" autocomplete="off"
                                           maxlength="16" required>
                                    <input type="hidden" name="walletID" value="${requestScope.WALLET}">
                                    <input type="hidden" name="customerPhone" value="${requestScope.CUSTOMER.getPhone()}">
                                    <span class="form-text text-center">VNĐ</span>
                                </div>

                                <div class="col-sm-6 pt-5">
                                    <button id="buttonDepositMoney" class="btn btn-warning dim btn-large-dim w-100 p-3 " type="submit"><i
                                            class="fa-solid fa-hand-holding-dollar"></i></button>
                                </div>
                                <div class="col-sm-6 pt-5">

                                    <button id="buttonAddMoney" class="btn btn-success dim btn-large-dim w-100 p-3 " type="submit"><i
                                            class="fa fa-money"></i></button>
                                </div>
                            </div>
                        </form>


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

<!-- Page-Level Scripts -->
<script>
    $(document).ready(function () {
        $(".footable").footable();

        $('#buttonAddMoney').click(function(){
            <c:url var="addMoneyLink" value="${requestScope.contextPath}/counter/addMoney"></c:url>
            $('#form_create_transaction').attr('action', '${addMoneyLink}');
        });
        $('#buttonDepositMoney').click(function(){
            $('#form_create_transaction').attr('action', '${requestScope.contextPath}/counter/withDraw');
        });

        $('#form_create_transaction').validate({
            rules: {
                amount: {
                    required: true
                }
            },
            messages: {
                amount: {
                    required: 'Please enter amount'
                }
            }
        })
    });
</script>
<script src="../../js/plugins/jqueryMask/jquery.mask.min.js"></script>
<script src="../js/plugins/jqueryMask/jquery.mask.min.js"></script>


</body>
</html>
