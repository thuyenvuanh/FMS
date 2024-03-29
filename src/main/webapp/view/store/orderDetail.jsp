<%--
  Created by IntelliJ IDEA.
  User: LEGION
  Date: 6/18/2022
  Time: 12:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>OrderDetails</title>

    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <link href="../../font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../font-awesome/css/font-awesome.css" rel="stylesheet">

    <!-- FooTable -->
    <link href="../../css/plugins/footable/footable.core.css" rel="stylesheet">

    <link href="../../css/animate.css" rel="stylesheet">
    <link href="../../css/style.css" rel="stylesheet">
    <link href="../css/plugins/footable/footable.core.css" rel="stylesheet">

    <link href="../css/animate.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet">
</head>
<body>
<div id="wrapper">
    <jsp:include page="layoutStore.jsp"></jsp:include>
    <%--  Content  --%>
    <div class="row wrapper border-bottom white-bg page-heading">
        <div class="col-lg-10">
            <h2>OrderDetail</h2>
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <c:url var="homeLink" value="${requestScope.contextPath}/dashboard/store"></c:url>
                    <a href="${homeLink}">Home</a>
                </li>
                <li class="breadcrumb-item">
                    <c:url var="orderListLink" value="${requestScope.contextPath}/order/list"></c:url>
                    <a href="${orderListLink}">Order list</a>
                </li>
                <li class="breadcrumb-item active">
                    <strong>OrderDetails</strong>
                </li>
            </ol>
        </div>
    </div>

    <div class="wrapper wrapper-content animated fadeInRight">

        <div class="row">
            <div class="col-lg-12">
                <div class="ibox-content p-xl">
                    <div class="row">
                        <div class="col-sm-6">
                            <h5>Store:</h5>
                            <address>
                                <strong>${sessionScope.storeSession.name}</strong><br>
                                106 Jorg Avenu, 600/10<br>
                                Chicago, VT 32456<br>
                                <abbr title="Phone">P:</abbr> (123) 601-4590
                            </address>
                        </div>

                        <div class="col-sm-6 text-right">
                            <c:set var="customerDetail" value="${requestScope.customer}"></c:set>
                            <h4>Invoice No.</h4>
                            <h4 class="text-navy">${requestScope.order.id}</h4>
                            <span><strong>Customer: </strong>${customerDetail.name}</span>
                            <address>
                                ${customerDetail.address}<br>
                                <abbr title="Phone">P:</abbr> ${customerDetail.phone}
                            </address>

                            <p>
                                <fmt:formatDate value="${requestScope.order.createdDateTime}" var="formattedDate"
                                                type="date" pattern="dd/MM/yyyy HH:mm:ss"></fmt:formatDate>
                                <span><strong>Invoice Date:</strong> ${formattedDate}</span><br>
                            </p>
                        </div>
                    </div>

                    <div class="table-responsive m-t">
                        <table class="table invoice-table" style="font-size: small">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>Products</th>
                                <th>Quantity</th>
                                <th>Unit Price</th>

                                <th>Total Price</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="orderDetail" items="${requestScope.orderDetail}">
                                <tr>
                                    <td>
                                        <strong>${orderDetail.product.id}</strong>
                                    </td>
                                    <td style="width: 25%">
                                        <div>
                                                ${orderDetail.product.name}
                                        </div>
                                    </td>
                                    <td>${orderDetail.quantity}</td>
                                    <td>
                                        <fmt:formatNumber value="${orderDetail.price}" var="price"
                                                           pattern="###,###,### ₫"></fmt:formatNumber>
                                                ${price}
                                    </td>
                                    <td>
                                        <fmt:formatNumber value="${orderDetail.amount}" var="amount"
                                                          pattern="###,###,### ₫"></fmt:formatNumber> VND
                                        ${amount}
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div><!-- /table-responsive -->

                    <table class="table invoice-total">
                        <tbody>
                        <tr>
                            <td><strong>TOTAL :</strong></td>
                            <td>
                                <fmt:formatNumber var="total" value="${requestScope.order.total}" pattern="###,###,### ₫"></fmt:formatNumber>
                                 ${total}
                            </td>
                        </tr>
                        </tbody>
                    </table>

                    <div class="well m-t"><strong>Comments</strong>
                        No tax included
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
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

<!-- Page-Level Scripts -->
<script>
    $(document).ready(function () {

        $('.footable').footable();

    });

</script>

</body>
</html>
