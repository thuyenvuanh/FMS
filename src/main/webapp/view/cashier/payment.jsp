<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: thuyn
  Date: 6/16/2022
  Time: 5:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<fmt:setLocale value="vi_VN"/>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css">
    <script>
        function dis(digit) {
            let val = document.getElementById("result").value;
            if (digit !== '.')
                document.getElementById("result").value = val + digit;
        }

        //function that evaluates the digit and return result
        //  function solve()
        //  {
        //      let x = document.getElementById("result").value
        //      let y = eval(x)
        //      document.getElementById("result").value = y
        //  }

        //function that clear the display
        function clr() {
            document.getElementById("result").value = ""
        }
    </script>
    <style>
        @media screen and (min-width: 1200px) and (min-height: 600px) {
            .show-block {
                display: none;
            }
        }
    </style>
    <title>Cashier Desktop</title>
</head>

<body class="container-fluid" style="padding: 0; margin: 0;">
<div class="fixed-top bg-light show-block" style="height: 100vh; width: 100vw; z-index: 10000;">
    <div class="d-flex align-items-center justify-content-center h-100">
        <p class="fs-1 fw-bold">Only available in larger screen</p>
    </div>
</div>
<!-- Navigation bar -->
<nav class="navbar navbar-expand-lg fixed-top bg-light">
    <div class="container-fluid">
        <a class="navbar-brand mx-auto ps-3" href="#">
            <i class="bi bi-qr-code"></i>
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="<c:url value="/order/index"/>">Home</a>
                </li>
            </ul>
            <form class="d-flex">
                <a href="#" class="navbar-brand">${sessionScope.account.username}</a>
                <button class="btn btn-outline-success" type="submit" formaction="<c:url value="/account/logout"/>"
                        name="Signout">
                    <i class="fa-solid fa-arrow-right-from-bracket"></i>
                </button>
            </form>
        </div>
    </div>
</nav>
<div class="vh-100" style="min-height: 600px; min-width: 1200px;padding-top: 56px;">
    <div class="row h-100" style="margin: 0;">
        <!-- Order list, take-away-btn, checkout-btn -->
        <div class="col-4 h-100 px-0">
            <div style="height: 65%; width: 100%;" class="row overflow-auto px-0 mx-0">
                <div class="px-0" style="width: 100%;">
                    <table class="table table-striped my-0" style="width: 100%;">
                        <colgroup>
                            <col span="1" style="width: 10%;">
                            <col span="1" style="width: 50%;">
                            <col span="1" style="width: 40%;">
                        </colgroup>

                        <thead>
                        <tr>
                            <th scope="col" class="text-end">Qty</th>
                            <th scope="col">Name</th>
                            <th scope="col" class="text-end">Price</th>
                        </tr>
                        </thead>
                        <tbody>
                        <jsp:useBean id="order" scope="session" type="com.fptuni.fms.model.Orders"/>
                        <c:forEach var="orderDetail" items="${order.orderDetailList}" varStatus="loop">
                            <tr class="align-middle">
                                <th scope="row" class="text-end">${orderDetail.quantity}</th>
                                <td>${orderDetail.product.name}</td>
                                <td class="text-end"><fmt:formatNumber value="${orderDetail.amount}"
                                                                       type="currency"/></td>
                            </tr>

                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <hr style="margin: -1px 12px;">
            <div style="height: 35%; margin-right: 12px ;margin-left: 12px;"
                 class="row d-flex align-items-end justify-content-center">
                <table class="h-75" style="margin: 0 16px">
                    <tr>
                        <th class="col ps-3">SubTotal</th>
                        <td class="col pe-3 text-end fs-3"><fmt:formatNumber minIntegerDigits="1" value="${order.total}"
                                                                             type="currency"/></td>
                    </tr>
                    <tr>
                        <th class="col ps-3">Tax</th>
                        <td class="col pe-3 text-end fs-3"><fmt:formatNumber value="0" type="currency"/></td>
                    </tr>
                    <tr>
                        <th class="col ps-3">Total</th>
                        <td class="col pe-3 text-end fs-3 fw-bold"><fmt:formatNumber minIntegerDigits="1"
                                                                                     value="${order.total}"
                                                                                     type="currency"/></td>
                    </tr>
                </table>
            </div>
        </div>
        <!-- product select btn, category btn -->
        <div class="col-8 d-flex justify-content-center align-items-center">
            <div id="back-button" style="position: relative; top: -28%; right: 150px;">
                <a href="<c:url value="/order/index"/> " class="btn btn-link text-success" style="text-decoration: none;font-size: 30px;">
                    <c:if test="${sessionScope.message ne 'Payment approved'}">
                        <i class="bi bi-arrow-left"></i>Back
                    </c:if>
                    <c:if test="${sessionScope.message eq 'Payment approved'}">
                        <i class="bi bi-arrow-left"></i>Done
                    </c:if>
                </a>
            </div>
            <c:set scope="session" var="message" value="${message}" target="java.lang.String"/>
            <c:if test="${message != null}">
                <div class="<c:if test="${message ne 'Payment approved'}">text-danger </c:if>
                            <c:if test="${message eq 'Payment approved'}">text-success</c:if>"
                     style="position: absolute; left: auto; right: auto;padding-right:117px; top: 80px; font-size: 1.8rem;">${message}
                </div>
            </c:if>
            <% session.removeAttribute("message"); %>
            <!-- product select field -->
            <div class="h-75 rounded-3 shadow-lg" style="width: 40%; position: relative;  right: 117px;">
                <form method="post" class="row gx-2 px-2 pt-2" style="height: 20%;">
                    <div class="col-8 h-100" style="height: 66.6666%;">
                        <input name="walletID" autofocus
                               class="form-control form-control-lg h-100 fs-1 fw-bold text-success"
                               style="direction: rtl;" type="text" id="result" placeholder="Enter ID">
                    </div>
                    <div class="col-4 h-100" style="height: 33.3333%;">
                        <button type="submit" formaction="<c:url value="/payment/create"/>"
                                class="btn btn-success h-100 w-100 fs-1 fw-bold">PAY
                        </button>
                    </div>
                </form>
                <div class="mx-2 gx-2" style="height: 80%;">
                    <div class="row h-25 px-2">
                        <div class="col-4 px-1 pt-2">
                            <button type="submit" onclick="dis('7')" class="btn btn-success h-100 w-100 fs-1 fw-bold">
                                7
                            </button>
                        </div>
                        <div class="col-4 px-1 pt-2">
                            <button type="submit" onclick="dis('8')" class="btn btn-success h-100 w-100 fs-1 fw-bold">
                                8
                            </button>
                        </div>
                        <div class="col-4 px-1 pt-2">
                            <button type="submit" onclick="dis('9')" class="btn btn-success h-100 w-100 fs-1 fw-bold">
                                9
                            </button>
                        </div>
                    </div>
                    <div class="row h-25 px-2">
                        <div class="col-4 px-1 pt-2">
                            <button type="submit" onclick="dis('4')" accesskey="4"
                                    class="btn btn-success h-100 w-100 fs-1 fw-bold">4
                            </button>
                        </div>
                        <div class="col-4 px-1 pt-2">
                            <button type="submit" onclick="dis('5')" class="btn btn-success h-100 w-100 fs-1 fw-bold">
                                5
                            </button>
                        </div>
                        <div class="col-4 px-1 pt-2">
                            <button type="submit" onclick="dis('6')" class="btn btn-success h-100 w-100 fs-1 fw-bold">
                                6
                            </button>
                        </div>
                    </div>
                    <div class="row h-25 px-2">
                        <div class="col-4 px-1 pt-2">
                            <button type="submit" onclick="dis('1')" class="btn btn-success h-100 w-100 fs-1 fw-bold">
                                1
                            </button>
                        </div>
                        <div class="col-4 px-1 pt-2">
                            <button type="submit" onclick="dis('2')" class="btn btn-success h-100 w-100 fs-1 fw-bold">
                                2
                            </button>
                        </div>
                        <div class="col-4 px-1 pt-2">
                            <button type="submit" onclick="dis('3')" class="btn btn-success h-100 w-100 fs-1 fw-bold">
                                3
                            </button>
                        </div>
                    </div>
                    <div class="row h-25 px-2">
                        <div class="col-4 px-1 py-2">
                            <button type="submit" onclick="clr()" class="btn btn-success h-100 w-100 fs-1 fw-bold">CLR
                            </button>
                        </div>
                        <div class="col-4 px-1 py-2">
                            <button type="submit" onclick="dis('0')" class="btn btn-success h-100 w-100 fs-1 fw-bold">
                                0
                            </button>
                        </div>
                        <div class="col-4 px-1 py-2">
                            <button type="submit" onclick="dis('.')" disabled
                                    class="btn btn-success h-100 w-100 fs-1 fw-bold">.
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>