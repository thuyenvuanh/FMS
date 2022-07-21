<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<!-- Mirrored from webapplayers.com/inspinia_admin-v2.9.4/ecommerce_product_list.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 06 Jun 2022 04:37:12 GMT -->

<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <title>Dash Board</title>

    <link href="../css/bootstrap.min.css" rel="stylesheet"/>
    <link href="../font-awesome/css/font-awesome.css" rel="stylesheet"/>

    <!-- FooTable -->
    <link href="../css/plugins/footable/footable.core.css" rel="stylesheet"/>

    <link href="../css/animate.css" rel="stylesheet"/>
    <link href="../css/style.css" rel="stylesheet"/>

    <!-- date -->
    <link
            rel="stylesheet"
            type="text/css"
            href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css"
    />
</head>

<body>
<div id="wrapper">
    <jsp:include page="layoutStore.jsp"></jsp:include>
    <!-- Body -->

    <!-- TMP -->
    <div class="row wrapper border-bottom white-bg page-heading">
        <div class="col-lg-9">
            <h2>Income</h2>
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <c:url var="storeDashBoardLink" value="${requestScope.contextPath}/dashboard/store"></c:url>
                    <a href="${storeDashBoardLink}">Home</a>
                </li>
                <li class="breadcrumb-item active">
                    <strong>Income</strong>
                </li>
            </ol>
        </div>

        <div class="col-lg-3">
            <div class="form-group" id="date_range_order">
                <label class="col-form-label">Range Date</label>
                <div class="input-daterange input-group">
                    <div
                            id="reportrange"
                            style="
                                background: #fff;
                                cursor: pointer;
                                padding: 5px 10px;
                                border: 1px solid #ccc;
                                width: 100%;
                              "
                    >
                        <i class="fa fa-calendar"></i>&nbsp; <span></span>
                        <i class="fa fa-caret-down"></i>
                    </div>
                </div>
            </div>

            <c:url var="dashBoardLink" value="${requestScope.contextPath}/dashboard/store"></c:url>
            <form id="form_date_range" action="${dashBoardLink}" method="post" style="display: none">
                <input type="text" name="startDate" id="startDate" value="${requestScope.startDate}"/>
                <input type="text" name="endDate" id="endDate" value="${requestScope.endDate}"/>
            </form>
            <button
                    id="search_date_range"
                    class="btn btn-outline-success float-right"
            >
                Search
            </button>
        </div>
    </div>

    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="ibox">
            <div class="ibox-title bg-danger">
                <h5>Top 5 Products</h5>
            </div>
            <div class="ibox-content">
                <table class="table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>ID</th>
                        <th>Product</th>
                        <th>Category</th>
                        <th>Quantity sale</th>
                        <th>Income</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="mapProduct" items="${requestScope.top5Products}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${mapProduct.key.key.id}</td>
                            <td>${mapProduct.key.key.name}</td>
                            <td>${mapProduct.key.value.name}</td>
                            <td>${mapProduct.value.quantity}</td>
                            <td>
                                <fmt:formatNumber type="number" pattern="###,###,### ₫"
                                                  value="${mapProduct.value.amount}"></fmt:formatNumber>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="ibox">
            <div class="row">
                <div class="col-lg-6">
                    <div class="ibox-title bg-success">
                        <h5>Income</h5>
                    </div>
                    <div class="ibox-content text-success">

                        <h1 class="no-margins">
                            <fmt:formatNumber type="number" pattern="###,###,### ₫"
                                              value="${requestScope.totalAmount}"></fmt:formatNumber>
                        </h1>


                        <small>Total income</small>
                    </div>
                </div>

                <div class="col-lg-6">
                    <div class="ibox-title yellow-bg">
                        <h5 class="">Order</h5>
                    </div>
                    <div class="ibox-content text-warning">
                        <h1 class="no-margins">${requestScope.numberOfOrders}</h1>

                        <small>Total order</small>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-2"></div>
            <div class="col-lg-8">
                <div class="ibox">
                    <div class="ibox-title">
                        <h5>Category (%)</h5>
                    </div>
                    <div class="ibox-content">
                        <div>
                            <div class="chartjs-size-monitor">
                                <div class="chartjs-size-monitor-expand">
                                    <div class=""></div>
                                </div>
                                <div class="chartjs-size-monitor-shrink">
                                    <div class=""></div>
                                </div>
                            </div>
                            <canvas
                                    id="chartCategory"
                                    height="219"
                                    width="471"
                                    style="display: block; height: 146px; width: 314px"
                                    class="chartjs-render-monitor"
                            ></canvas>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-2"></div>
        </div>
        <div class="ibox">
            <div class="ibox-title">
                <h5>Orders</h5>
            </div>
            <div class="ibox-content">
                <div>
                    <div class="chartjs-size-monitor">
                        <div class="chartjs-size-monitor-expand">
                            <div class=""></div>
                        </div>
                        <div class="chartjs-size-monitor-shrink">
                            <div class=""></div>
                        </div>
                    </div>
                    <canvas
                            id="lineChart"
                            height="156"
                            style="display: block; height: 104px; width: 447px"
                            width="670"
                            class="chartjs-render-monitor active"
                    ></canvas>
                </div>

                <div class="m-t-md">
                    <small class="float-right">
                        <i class="fa fa-clock-o"> </i>
                        <jsp:useBean id="now" class="java.util.Date" />
                        <fmt:formatDate var="currentDate" value="${now}" pattern="dd/MM/yyyy" />
                        Update on ${currentDate}
                    </small>
                    <small>
                        <strong>Analysis of sales:</strong> The value has been changed
                        over time.
                    </small>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="ibox">
                    <div class="ibox-title">
                        <h5>Income (₫)</h5>
                    </div>
                    <div class="ibox-content">
                        <div>
                            <div class="chartjs-size-monitor">
                                <div class="chartjs-size-monitor-expand">
                                    <div class=""></div>
                                </div>
                                <div class="chartjs-size-monitor-shrink">
                                    <div class=""></div>
                                </div>
                            </div>
                            <canvas
                                    id="lineChartIncome"
                                    height="156"
                                    style="display: block; height: 104px; width: 447px"
                                    width="670"
                                    class="chartjs-render-monitor active"
                            ></canvas>
                        </div>

                        <div class="m-t-md">
                            <small class="float-right">
                                <i class="fa fa-clock-o"> </i>
                                <fmt:formatDate var="currentDate" value="${now}" pattern="dd/MM/yyyy" />
                                Update on ${currentDate}
                            </small>
                            <small>
                                <strong>Analysis of sales:</strong> The value has been
                                changed over time
                            </small>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp"></jsp:include>
</div>
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


<!-- jQuery UI -->
<script src="../js/plugins/jquery-ui/jquery-ui.min.js"></script>

<!-- Jvectormap -->
<script src="../js/plugins/jvectormap/jquery-jvectormap-2.0.2.min.js"></script>
<script src="../js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>

<!-- Sparkline -->
<script src="../js/plugins/sparkline/jquery.sparkline.min.js"></script>

<!-- Sparkline demo data  -->
<script src="../js/demo/sparkline-demo.js"></script>


<!-- ChartJS-->
<script src="../js/plugins/chartJs/Chart.min.js"></script>

<!-- <script src="js/plugins/chartJs/Util.js"></script> -->
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

<!-- jQuery UI -->
<script src="../../js/plugins/jquery-ui/jquery-ui.min.js"></script>

<!-- Jvectormap -->
<script src="../../js/plugins/jvectormap/jquery-jvectormap-2.0.2.min.js"></script>
<script src="../../js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>

<!-- Sparkline -->
<script src="../../js/plugins/sparkline/jquery.sparkline.min.js"></script>

<!-- Sparkline demo data  -->
<script src="../../js/demo/sparkline-demo.js"></script>

<!-- ChartJS-->
<script src="../../js/plugins/chartJs/Chart.min.js"></script>


<!-- Page-Level Scripts -->
<script>
    $(document).ready(function () {
        $(".footable").footable();
    });
</script>

<!-- data picker -->

<script
        type="text/javascript"
        src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"
></script>
<script
        type="text/javascript"
        src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"
></script>
<script
        type="text/javascript"
        src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"
></script>

<script>
    $(function () {

        var startDate;
        var endDate;
        if ("${requestScope.startDateFmt}" == "") {
            startDate = moment().subtract("days", 29);
            endDate = moment();
        } else {
            startDate = new Date('${requestScope.startDateFmt}');
            endDate = new Date('${requestScope.endDateFmt}');
        }

        $("#reportrange").daterangepicker(
            {
                startDate: startDate,
                endDate: endDate,
                dateLimit: {days: 60},
                showDropdowns: false,
                showWeekNumbers: true,
                timePicker: false,
                timePickerIncrement: 1,
                timePicker12Hour: true,
                ranges: {
                    Today: [moment(), moment()],
                    Yesterday: [
                        moment().subtract("days", 1),
                        moment().subtract("days", 1),
                    ],
                    "Last 7 Days": [moment().subtract("days", 6), moment()],
                    "Last 30 Days": [moment().subtract("days", 29), moment()],
                    "This Month": [
                        moment().startOf("month"),
                        moment().endOf("month"),
                    ],
                    "Last Month": [
                        moment().subtract("month", 1).startOf("month"),
                        moment().subtract("month", 1).endOf("month"),
                    ],
                },
                opens: "left",
                buttonClasses: ["btn btn-default"],
                applyClass: "btn-small btn-primary",
                cancelClass: "btn-small",
                format: "DD/MM/YYYY",
                separator: " to ",
                locale: {
                    applyLabel: "Submit",
                    fromLabel: "From",
                    toLabel: "To",
                    customRangeLabel: "Custom Range",
                    daysOfWeek: ["Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"],
                    monthNames: [
                        "January",
                        "February",
                        "March",
                        "April",
                        "May",
                        "June",
                        "July",
                        "August",
                        "September",
                        "October",
                        "November",
                        "December",
                    ],
                    firstDay: 1,
                },
            },
            function (start, end) {
                console.log("Callback has been called!");
                $("#reportrange span").html(
                    start.format("DD/MM/YYYY") + " - " + end.format("DD/MM/YYYY")
                );
                startDate = start;
                endDate = end;
            }
        );
        var startDate = new Date('${requestScope.startDateFmt}');
        var endDate = new Date('${requestScope.endDateFmt}');
        let sd = startDate.getDate();
        let sm = startDate.getMonth() + 1;
        let sy = startDate.getFullYear();
        let ed = endDate.getDate();
        let em = endDate.getMonth() + 1;
        let ey = endDate.getFullYear();
        if (sd < 10) sd = '0' + sd;
        if (sm < 10) sm = '0' + sm;
        if (ed < 10) ed = '0' + ed;
        if (em < 10) em = '0' + em;
        //Set the initial state of the picker label
        $("#reportrange span").html(
            <c:if test="${requestScope.startDateFmt==null}">
            moment().subtract("days", 29).format("DD/MM/YYYY") +
            " - " +
            moment().format("DD/MM/YYYY")
        </c:if>
        <c:if test="${requestScope.startDateFmt!=null}">
        sd + "/" + sm + "/" + sy +
        " - " +
        ed + "/" + em + "/" + ey
        </c:if>
    )
        ;

        $("#search_date_range").click(function () {
            if (startDate == null && endDate == null) {
                console.log(
                    moment().subtract("days", 29).format("DD/MM/YYYY") +
                    " - " +
                    moment().format("DD/MM/YYYY")
                );
            } else {
                $("#startDate").val(startDate.format("DD/MM/YYYY"));
                $("#endDate").val(endDate.format("DD/MM/YYYY"))
                console.log($("#startDate").val());
                console.log($("#endDate").val());
                console.log($('#reportrange').val());
                $("#form_date_range").submit();
            }
        });

    });
</script>

<script>
    $(document).ready(function () {
        const DATES = [
            <c:if test="${fn:length(requestScope.dateRange) != 1}">
            <c:forEach var="date" items="${requestScope.dateRange}">
            <fmt:formatDate value="${date}" var="dateFmt" pattern="dd/MM"></fmt:formatDate>
            '${dateFmt}',
            </c:forEach>
            </c:if>
            <c:if test="${fn:length(requestScope.dateRange) == 1}">
            <c:forEach var="time" items="${requestScope.timeRange}">
            <fmt:formatDate value="${time}" var="timeFmt" pattern="HH:mm"></fmt:formatDate>
            '${timeFmt}',
            </c:forEach>
            </c:if>
        ];
        // Order
        var lineData = {
            labels: DATES,
            datasets: [
                {
                    label: "Order",
                    backgroundColor: "rgba(26,179,148,0.5)",
                    borderColor: "rgba(26,179,148,0.7)",
                    pointBackgroundColor: "rgba(26,179,148,1)",
                    pointBorderColor: "#fff",
                    data: [
                        <c:if test="${fn:length(requestScope.dateRange) != 1}">
                        <c:forEach var="numberOfOrderEachDate" items="${requestScope.numberOfOrderEachDate}">
                        '${numberOfOrderEachDate}',
                        </c:forEach>
                        </c:if>
                        <c:if test="${fn:length(requestScope.dateRange) == 1}">
                        <c:forEach var="numberOfOrderByHours" items="${requestScope.numberOfOrderByHours}">
                        '${numberOfOrderByHours}',
                        </c:forEach>
                        </c:if>
                    ],
                },
            ],
        };

        var lineOptions = {
            responsive: true,
            scales: {
                yAxes: [
                    {
                        display: true,
                        ticks: {
                            beginAtZero: true,
                        },
                    },
                ],
            },
        };

        var ctx = document.getElementById("lineChart").getContext("2d");
        new Chart(ctx, {type: "bar", data: lineData, options: lineOptions});

        //Income

        var lineDataIncome = {
            labels: DATES,
            datasets: [
                {
                    label: "Income",
                    backgroundColor: "rgba(26,179,148,0.5)",
                    borderColor: "rgba(26,179,148,0.7)",
                    pointBackgroundColor: "rgba(26,179,148,1)",
                    pointBorderColor: "#fff",
                    data: [
                        <c:if test="${fn:length(requestScope.dateRange) != 1}">
                        <c:forEach var="totalAmountOneDate" items="${requestScope.totalAmountEachDate}">
                        '${totalAmountOneDate}',
                        </c:forEach>
                        </c:if>
                        <c:if test="${fn:length(requestScope.dateRange) == 1}">
                        <c:forEach var="totalAmountOneHours" items="${requestScope.totalAmountEachHours}">
                        '${totalAmountOneHours}',
                        </c:forEach>
                        </c:if>

                    ],
                },
            ],
        };

        var lineOptions = {
            responsive: true,
        };

        var ctxs = document.getElementById("lineChartIncome").getContext("2d");
        new Chart(ctxs, {
            type: "bar",
            data: lineDataIncome,
            options: {
                responsive: true,
                scales: {
                    yAxes: [
                        {
                            display: true,
                            ticks: {
                                beginAtZero: true,
                            },
                        },
                    ],
                },
            },
        });

        // Category
        const pieCategory = {
            // labels: ["Red", "Blue", "Yellow", "Black"],
            labels: [
                <c:forEach items="${requestScope.categories}" var="category">
                "${category.name}",
                </c:forEach>
            ],
            datasets: [
                {
                    label: "My First Dataset",
                    // data: [300, 50, 100, 99],
                    data: [
                        <c:forEach var="percentage" items="${requestScope.percentageOfProductInCategory}">
                        ${percentage},
                        </c:forEach>
                    ],
                    backgroundColor: [
                        "rgb(255, 99, 132)",
                        "rgb(54, 162, 235)",
                        "rgb(255, 205, 86)",
                        "rgb(0, 0, 0)",
                    ],
                    hoverOffset: 4,
                },
            ],
        };

        var chartCategory = document
            .getElementById("chartCategory")
            .getContext("2d");
        new Chart(chartCategory, {type: "doughnut", data: pieCategory});

        //Product
        const pieProduct = {
            labels: ["Red", "Blue", "Yellow"],
            <%--labels: [<c:forEach items="${requestScope.numberOfProductsEachID}" var="n">--%>
            <%--    "${n}",--%>
            <%--    </c:forEach>],--%>
            datasets: [
                {
                    label: "My First Dataset",
                    data: [300, 50, 100],
                    <%--data: [--%>
                    <%--    <c:forEach items="${requestScope.numberOfProductsEachID}" var="n">--%>
                    <%--    ${n},--%>
                    <%--    </c:forEach>--%>
                    <%--],--%>
                    backgroundColor: [
                        "rgb(255, 99, 132)",
                        "rgb(54, 162, 235)",
                        "rgb(255, 205, 86)",
                        "rgb(100, 205, 86)",
                        "rgb(255, 50, 86)",
                    ],
                    hoverOffset: 5,
                },
            ],
        };

        var chartCategory = document
            .getElementById("chartProduct")
            .getContext("2d");
        new Chart(chartCategory, {type: "doughnut", data: pieProduct});
    });
</script>

<!-- Data range -->
</body>
</html>

