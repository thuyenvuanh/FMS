<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: LEGION
  Date: 06/07/2022
  Time: 12:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>INSPINIA | E-commerce</title>

    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../font-awesome/css/font-awesome.css" rel="stylesheet">

    <link href="../css/animate.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet">

    <!-- date -->
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
</head>
<body>

<div id="wrapper">
    <jsp:include page="layoutAdmin.jsp"></jsp:include>
    <div class="row wrapper border-bottom white-bg page-heading">
        <div class="col-lg-9">
            <h2>Income</h2>
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <c:url var="homelink" value="${requestScope.contextPath}/adminDashboard/index"></c:url>
                    <a href="${homelink}">Home</a>
                </li>
                <li class="breadcrumb-item active">
                    <strong>Income</strong>
                </li>
            </ol>
        </div>

        <div class="col-lg-3">
            <div class="form-group float-right" id="date_range_order">
                <label class="col-form-label">Range Date</label>
                <div class="input-daterange input-group">
<%--                    <div id="reportrange"--%>
<%--                         style="background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #ccc; width: 100%">--%>
<%--                        <i class="fa fa-calendar"></i>&nbsp;--%>
<%--                        <span></span> <i class="fa fa-caret-down"></i>--%>
<%--                    </div>--%>
                        <form id="form_date_range" action="${homelink}" method="GET">
                            <input type="text" class="form-control" name="daterangepicker" value="${requestScope.BEGIN_DATE} - ${requestScope.END_DATE}" readonly>
                            <input type="hidden" name="startDate" id="Start" value="" />
                            <input type="hidden" name="endDate" id="End" value="" />
                        </form>

                </div>
                <button id="search_date_range" class="btn btn-outline-success float-right">Search</button>
            </div>

<%--            <form id="form_date_range" action="${homelink}" method="GET">--%>
<%--                <input type="hidden" name="startDate" id="Start" value="" />--%>
<%--                <input type="hidden" name="endDate" id="End" value="" />--%>
<%--            </form>--%>


        </div>

    </div>

    <div class="wrapper wrapper-content animated fadeInRight">


        <div class="ibox">
            <div class="ibox-title bg-danger">
                <h5>Top 5 store</h5>

            </div>
            <div class="ibox-content">

                <table class="table">

                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Store</th>
                        <th>Order Quantity</th>
                        <th>Total Value</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="MAP" value="${requestScope.TOP_STORES}"/>
                    <c:forEach var="item" items="${requestScope.LIST_KEY_TOP_STORES}" varStatus="loop">
                        <tr>
                            <td>${loop.index + 1}</td>
                            <td>${item.name}</td>
                            <td>${MAP.get(item).getValue()}</td>
                            <td>${MAP.get(item).getKey()}</td>
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
                        <h5>TOTAL VALUE</h5>
                    </div>
                    <div class="ibox-content  text-success">
                        <h1 class="no-margins">${requestScope.TOTAL_VALUE_ALLSTORES == null ? 0 : requestScope.TOTAL_VALUE_ALLSTORES}</h1>

                        <div class="stat-percent font-bold">98% <i class="fa fa-level-up"></i></div>
                        <small>Total income</small>
                    </div>
                </div>

                <div class="col-lg-6">
                    <div class="ibox-title yellow-bg">
                        <h5 class="">TOTAL ORDER</h5>
                    </div>
                    <div class="ibox-content text-warning">
                        <h1 class="no-margins">${requestScope.TOTAL_ORDER_ALLSTORES == null ? 0 : requestScope.TOTAL_VALUE_ALLSTORES}</h1>
                        <div class="stat-percent font-bold">98% <i class="fa fa-level-up"></i></div>
                        <small>Total order</small>
                    </div>
                </div>


            </div>
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
                    <canvas id="lineChart" height="156" style="display: block; height: 104px; width: 447px;"
                            width="670" class="chartjs-render-monitor active"></canvas>
                </div>

                <div class="m-t-md">
                    <small class="float-right">
                        <i class="fa fa-clock-o"> </i>
                        Update on 16.07.2015
                    </small>
                    <small>
                        <strong>Analysis of sales:</strong> The value has been changed over time, and last
                        month reached a level over $50,000.
                    </small>
                </div>

            </div>
        </div>


        <div class="row">

            <div class="col-lg-12">
                <div class="ibox">
                    <div class="ibox-title">
                        <h5>Income</h5>
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
                            <canvas id="lineChartIncome" height="156"
                                    style="display: block; height: 104px; width: 447px;" width="670"
                                    class="chartjs-render-monitor active"></canvas>
                        </div>

                        <div class="m-t-md">
                            <small class="float-right">
                                <i class="fa fa-clock-o"> </i>
                                Update on 16.07.2015
                            </small>
                            <small>
                                <strong>Analysis of sales:</strong> The value has been changed over time, and
                                last
                                month reached a level over $50,000.
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

<!-- jQuery UI -->
<script src="../js/plugins/jquery-ui/jquery-ui.min.js"></script>

<!-- Jvectormap -->
<script src="../js/plugins/jvectormap/jquery-jvectormap-2.0.2.min.js"></script>
<script src="../js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>


<!-- ChartJS-->
<script src="../js/plugins/chartJs/Chart.min.js"></script>
<%--<script src="../js/plugins/chartJs/Util.js"></script>--%>


<!-- data picker -->

<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>

<script>
    $(function () {

        var startDate;
        var endDate;

        if('${requestScope.BEGIN_DATE}' == ""){
            startDate = moment().subtract('days', 29);
            endDate = moment();
        }
        else {
            startDate = new Date('${requestScope.BEGIN_DATE}');
            endDate = new Date('${requestScope.END_DATE}');
        }



        $('input[name="daterangepicker"]').daterangepicker(
            {
                startDate: startDate,
                endDate: endDate,
                dateLimit: { days: 60 },
                showDropdowns: false,
                showWeekNumbers: true,
                timePicker: false,
                timePickerIncrement: 1,
                timePicker12Hour: true,
                ranges: {
                    'Today': [moment(), moment()],
                    'Yesterday': [moment().subtract('days', 1), moment().subtract('days', 1)],
                    'Last 7 Days': [moment().subtract('days', 6), moment()],
                    'Last 30 Days': [moment().subtract('days', 29), moment()],
                    'This Month': [moment().startOf('month'), moment().endOf('month')],
                    'Last Month': [moment().subtract('month', 1).startOf('month'), moment().subtract('month', 1).endOf('month')]
                },
                opens: 'left',
                buttonClasses: ['btn btn-default'],
                applyClass: 'btn-small btn-primary',
                cancelClass: 'btn-small',
                format: 'YYYY-MM-DD',
                separator: ' to ',
                locale: {
                    applyLabel: 'Submit',
                    fromLabel: 'From',
                    toLabel: 'To',
                    customRangeLabel: 'Custom Range',
                    daysOfWeek: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa'],
                    monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
                    firstDay: 1
                }
            },
            function (start, end) {
                console.log("Callback has been called!");
                $('#reportrange span').html(start.format('YYYY-MM-DD') + ' - ' + end.format('YYYY-MM-DD'));
                startDate = start;
                endDate = end;
            }
        );
        //Set the initial state of the picker label

        <%--var s = "${requestScope.START}" == "" ? moment().subtract('days', 29).format('YYYY-MM-DD') : "${requestScope.START}";--%>
        <%--var e = "${requestScope.END}" == "" ? moment().format('YYYY-MM-DD') : "${requestScope.END}";--%>

        <%--$('#reportrange span').html(s + ' - ' + e);--%>


        // $('#reportrange span').html(moment().subtract('days', 29).format('D MMMM YYYY') + ' - ' + moment().format('D MMMM YYYY'));


        $('#search_date_range').click(function () {
            if (startDate == null && endDate == null) {
                console.log(moment().subtract('days', 29).format('YYYY-MM-DD') + ' - ' + moment().format('YYYY-MM-DD'));
                $("#Start").val(moment().subtract('days', 29).format('YYYY-MM-DD'));
                $("#End").val(moment().format('YYYY-MM-DD'));
            }
            else {
                console.log(startDate.format('YYYY-MM-DD') + ' - ' + endDate.format('YYYY-MM-DD'));
                $("#Start").val(startDate.format('YYYY-MM-DD'));
                $("#End").val(endDate.format('YYYY-MM-DD'));
                $("#form_date_range").submit();
            }

        });

    });
</script>

<script>

    $(document).ready(function () {

        const MONTHS = [
            <c:forEach var="item" items="${requestScope.LIST_KEY_TOTAL_ORDER_BY_TIME}">
            '${item}',
            </c:forEach>
        ];

        // Order

        var lineData = {
            labels: MONTHS,
            datasets: [
                {
                    label: "Order",
                    backgroundColor: "rgba(26,179,148,0.5)",
                    borderColor: "rgba(26,179,148,0.7)",
                    pointBackgroundColor: "rgba(26,179,148,1)",
                    pointBorderColor: "#fff",
                    data: [
                        <c:forEach var="item" items="${requestScope.TOTAL_ORDER_BY_TIME}">
                        '${item.value}',
                        </c:forEach>
                    ]

                },
            ]
        };

        var lineOptions = {
            responsive: true,
            scales: {
                yAxes: [{
                    display: true,
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        };




        var ctx = document.getElementById("lineChart").getContext("2d");
        new Chart(ctx, { type: 'bar', data: lineData, options: lineOptions });


        //Income

        var lineDataIncome = {
            labels: MONTHS,
            datasets: [
                {
                    label: "Income",
                    backgroundColor: "rgba(26,179,148,0.5)",
                    borderColor: "rgba(26,179,148,0.7)",
                    pointBackgroundColor: "rgba(26,179,148,1)",
                    pointBorderColor: "#fff",
                    data: [
                        <c:forEach var="item" items="${requestScope.TOTAL_VALUE_BY_TIME}">
                        '${item.value}',
                        </c:forEach>
                    ]
                },
            ]
        };

        var lineOptions = {
            responsive: true
        };




        var ctxs = document.getElementById("lineChartIncome").getContext("2d");
        new Chart(ctxs, {
            type: 'bar',
            data: lineDataIncome,
            options: {
                responsive: true,
                scales: {
                    yAxes: [{
                        display: true,
                        ticks: {
                            beginAtZero: true
                        }
                    }]
                }
            }
        });




        // Category
        const pieCategory = {
            labels: [
                'Red',
                'Blue',
                'Yellow'
            ],
            datasets: [{
                label: 'My First Dataset',
                data: [300, 50, 100],
                backgroundColor: [
                    'rgb(255, 99, 132)',
                    'rgb(54, 162, 235)',
                    'rgb(255, 205, 86)'
                ],
                hoverOffset: 4
            }]
        };

        var chartCategory = document.getElementById("chartCategory").getContext("2d");
        new Chart(chartCategory, { type: 'doughnut', data: pieCategory });

        //Product
        const pieProduct = {
            labels: [
                'Red',
                'Blue',
                'Yellow'
            ],
            datasets: [{
                label: 'My First Dataset',
                data: [300, 50, 100],
                backgroundColor: [
                    'rgb(255, 99, 132)',
                    'rgb(54, 162, 235)',
                    'rgb(255, 205, 86)'
                ],
                hoverOffset: 4
            }]
        };

        var chartCategory = document.getElementById("chartProduct").getContext("2d");
        new Chart(chartCategory, { type: 'doughnut', data: pieProduct });


    });
</script>
</body>
</html>
