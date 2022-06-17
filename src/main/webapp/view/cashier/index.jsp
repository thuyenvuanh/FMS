<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: thuyn
  Date: 6/16/2022
  Time: 5:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">

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
                    <a class="nav-link active" aria-current="page" href="#">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Stock</a>
                </li>
            </ul>
            <form class="d-flex">
                <a href="#" class="navbar-brand">Tai Cashier</a>
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
        <div class="col-3 h-100 px-0">
            <div style="height: 65%; width: 100%;" class="row overflow-auto px-0 mx-0">
                <div class="px-0" style="width: 100%;">
                    <table class="table table-striped my-0" style="width: 100%;">
                        <colgroup>
                            <col span="1" style="width: 10%;">
                            <col span="1" style="width: 30%;">
                            <col span="1" style="width: 30%;">
                            <col span="1" style="width: 20%;">
                            <col span="1" style="width: 10%;">
                        </colgroup>

                        <thead>
                        <tr>
                            <th scope="col" class="text-end">Qty</th>
                            <th scope="col">Name</th>
                            <th scope="col" class="text-end">Note</th>
                            <th scope="col" class="text-center">Price</th>
                            <th scope="col" class="text-center">Remove</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%--                        <c:forEach var="orderDetail" items="${sessionScope.order.orderDetailList}" varStatus="loop">--%>
                        <%--                            <tr class="align-middle">--%>
                        <%--                                <th scope="row" class="text-end">${orderDetail.quantity}</th>--%>
                        <%--                                <td>${orderDetail.product.name}</td>--%>
                        <%--                                <td class="text-end"></td>--%>
                        <%--                                <td class="text-center">{orderDetail.Quantity * orderDetail.product.Price}</td>--%>
                        <%--                                <td class="text-center">--%>
                        <%--                                    <button type="submit" class="btn btn-outline-danger" formmethod="post" formaction="<c:url value="/order/add?id=1"/>">--%>
                        <%--                                        <i class="bi bi-x"></i>--%>
                        <%--                                    </button>--%>
                        <%--                                </td>--%>
                        <%--                            </tr>--%>
                        <%--                        </c:forEach>--%>
                        <c:forEach begin="1" end="5" varStatus="loop">
                            <tr class="align-middle">
                                <th scope="row" class="text-end">1</th>
                                <td>Milk Coffee</td>
                                <td class="text-end"></td>
                                <td class="text-center">20000</td>
                                <td class="text-center">
                                    <form>
                                        <button type="submit" class="btn btn-outline-danger" formmethod="get"
                                                formaction="<c:url value="/order/add"/>">
                                            <i class="bi bi-x"></i>
                                        </button>
                                    </form>
                                </td>
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
                        <td class="col pe-3 text-end fs-3">20k</td>
                    </tr>
                    <tr>
                        <th class="col ps-3">Tax</th>
                        <td class="col pe-3 text-end fs-3">0k</td>
                    </tr>
                    <tr>
                        <th class="col ps-3">Total</th>
                        <td class="col pe-3 text-end fs-3 fw-bold">20k</td>
                    </tr>
                </table>
                <div class="row pb-3 h-25 w-100">
                    <div class="row d-flex justify-content-between align-items-stretch"
                         style="height: 100%; margin-right: 0;margin-left: 0; padding: 0;">
                        <div class="col flex-grow-1 d-flex justify-content-start align-items-stretch"
                             style="padding-left: 0; padding-right: 12px;">
                            <!-- <input type="submit" class="btn btn-success" value="Take-Away" /> -->
                            <button style="width: 100% !important;"
                                    class="btn btn-outline-success">Take-Away
                            </button>
                        </div>
                        <div class="col flex-grow-1 d-flex justify-content-center align-items-stretch"
                             style="padding-left: 12px; padding-right: 0;">
                            <!-- <input type="submit" class="btn btn-outline-success" value="Take-Away" /> -->
                            <button style="width: 100% !important;" class="btn btn-success">Check out</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- product select btn, category btn -->
        <div class="col-9 h-100  m-0 p-0">
            <!-- product select field -->
            <div style="height: 65%; margin: 0 12px; padding: 0;"
                 class="row d-flex justify-content-center align-items-start overflow-auto">
                <h3 class="fs4 text-center m-0 text-center" style="height: fit-content;">Products</h3>
                <div class="row p-0 d-flex justify-content-center align-items-start"
                     style="height: 90%;width: 100%;">
                    <div class="row p-0">
                        <c:forEach var="product" items="${sessionScope.products}" varStatus="loop">
                            <a href="#" class="col-md-4 col-xxl-3 d-flex align-items-center h-25 py-2" style="
                            position: relative;">
                                <img style="object-fit: cover; height: 100%;width: 100%; max-height: 160px;"
                                     class="rounded-2"
                                     src="https://bonjourcoffee.vn/blog/wp-content/uploads/2020/01/capuchino.jpg "
                                     alt=" ">
                                <div class="bottomleft rounded-bottom px-1 mx-auto text-break"
                                     style="font-size: 20px ;position: absolute; color: aliceblue; bottom: 8px; left: 12px;right: 12px; height: fit-content; background-color: rgba(0, 0, 0, 0.7);">${product.Name}
                                </div>
                            </a>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <hr style="margin: -1px 12px;">
            <div class="row d-flex justify-content-center align-items-start overflow-auto"
                 style="height: 35%; width: 100%; margin: 0; padding: 0;">
                <h3 class="fs4 text-center m-0 text-centerp-0" style="height: fit-content;">Categories</h3>
                <div class="row mx-0 p-0 d-flex justify-content-start align-items-start"
                     style="height: 85%;width: 100%;">
                    <div class="row m-0 h-100">
                        <div class="col-3 d-flex align-items-center py-2" style="height: 50%; width: 25%">
                            <button type="submit" class="btn btn-outline-success"
                                    style="height: 100%; width: 100%;">Italian Coffee
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