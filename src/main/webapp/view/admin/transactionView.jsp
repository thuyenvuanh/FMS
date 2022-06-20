<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/20/2022
  Time: 7:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Transaction View</title>

    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <link href="../../font-awesome/css/font-awesome.css" rel="stylesheet">

    <!-- FooTable -->
    <link href="../../css/plugins/footable/footable.core.css" rel="stylesheet">

    <!-- Date picker -->
    <link href="../../css/plugins/iCheck/custom.css" rel="stylesheet">

    <link href="../../css/plugins/bootstrap-tagsinput/bootstrap-tagsinput.css" rel="stylesheet">

    <link href="../../css/plugins/colorpicker/bootstrap-colorpicker.min.css" rel="stylesheet">

    <link href="../../css/plugins/cropper/cropper.min.css" rel="stylesheet">

    <link href="../../css/plugins/switchery/switchery.css" rel="stylesheet">

    <link href="../../css/plugins/nouslider/jquery.nouislider.css" rel="stylesheet">

    <link href="../../css/plugins/datapicker/datepicker3.css" rel="stylesheet">

    <link href="../../css/plugins/ionRangeSlider/ion.rangeSlider.css" rel="stylesheet">

    <link href="../../css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">

    <link href="../../css/plugins/clockpicker/clockpicker.css" rel="stylesheet">

    <link href="../../css/plugins/daterangepicker/daterangepicker-bs3.css" rel="stylesheet">

    <link href="../../css/plugins/select2/select2.min.css" rel="stylesheet">
    <link href="../../css/plugins/select2/select2-bootstrap4.min.css" rel="stylesheet">

    <link href="../../css/plugins/touchspin/jquery.bootstrap-touchspin.min.css" rel="stylesheet">

    <link href="../../css/plugins/dualListbox/bootstrap-duallistbox.min.css" rel="stylesheet">


    <link href="../../css/animate.css" rel="stylesheet">
    <link href="../../css/style.css" rel="stylesheet">

</head>
<body>
    <div id="wrapper">
        <jsp:include page="layoutAdmin.jsp"></jsp:include>
        <div class="col-lg-10">
            <h2>Product edit</h2>
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <a href="index.html">Home</a>
                </li>
                <li class="breadcrumb-item">
                    <a>E-commerce</a>
                </li>
                <li class="breadcrumb-item">
                    <a>Orders</a>
                </li>
                <li class="breadcrumb-item active">
                    <strong>Product view</strong>
                </li>
            </ol>
        </div>
            <div class="wrapper wrapper-content animated fadeInRight ecommerce">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="ibox">
                            <div class="ibox-content">
                                <table class="footable table table-stripped toggle-arrow-tiny" data-page-size="15">
                                    <thead>
                                    <tr>
                                        <th data-toggle="true" data-sort-ignore="true">Wallet ID</th>
                                        <th data-hide="phone" data-sort-ignore="true">Amount</th>
                                        <th data-hide="phone" data-sort-ignore="true">Previous Balance</th>
                                        <th data-hide="phone" data-sort-ignore="true">Created Date</th>
                                        <th data-hide="phone" data-sort-ignore="true">Payment</th>
                                        <th data-hide="phone" data-sort-ignore="true">Status</th>
                                        <th data-sort-ignore="true" class="text-right">Action</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <jsp:useBean id="transactionShared" class="com.fptuni.fms.model.TransactionShared" scope="request"/>
                                        <tr><td><jsp:getProperty name="transactionShared" property="walletID"/></td>
                                            <td><jsp:getProperty name="transactionShared" property="amount"/></td>
                                            <td><jsp:getProperty name="transactionShared" property="previousBalance"/></td>
                                            <td><jsp:getProperty name="transactionShared" property="createdDate"/></td>
                                            <td><jsp:getProperty name="transactionShared" property="paymentID"/></td>
                                            <td><span class="label label-primary"><jsp:getProperty name="transactionShared" property="status"/></span></td></tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
</body>
</html>
