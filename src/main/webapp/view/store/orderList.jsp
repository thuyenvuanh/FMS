<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: LEGION
  Date: 6/18/2022
  Time: 12:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Order List</title>

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

    <%--    --------------------%>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../font-awesome/css/font-awesome.css" rel="stylesheet">

    <!-- FooTable -->
    <link href="../css/plugins/footable/footable.core.css" rel="stylesheet">

    <!-- Date picker -->
    <link href="../css/plugins/iCheck/custom.css" rel="stylesheet">

    <link href="../css/plugins/bootstrap-tagsinput/bootstrap-tagsinput.css" rel="stylesheet">

    <link href="../css/plugins/colorpicker/bootstrap-colorpicker.min.css" rel="stylesheet">

    <link href="../css/plugins/cropper/cropper.min.css" rel="stylesheet">

    <link href="../css/plugins/switchery/switchery.css" rel="stylesheet">

    <link href="../css/plugins/nouslider/jquery.nouislider.css" rel="stylesheet">

    <link href="../css/plugins/datapicker/datepicker3.css" rel="stylesheet">

    <link href="../css/plugins/ionRangeSlider/ion.rangeSlider.css" rel="stylesheet">

    <link href="../css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">

    <link href="../css/plugins/clockpicker/clockpicker.css" rel="stylesheet">

    <link href="../css/plugins/daterangepicker/daterangepicker-bs3.css" rel="stylesheet">

    <link href="../css/plugins/select2/select2.min.css" rel="stylesheet">
    <link href="../css/plugins/select2/select2-bootstrap4.min.css" rel="stylesheet">

    <link href="../css/plugins/touchspin/jquery.bootstrap-touchspin.min.css" rel="stylesheet">

    <link href="../css/plugins/dualListbox/bootstrap-duallistbox.min.css" rel="stylesheet">

    <link href="../css/animate.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet">
</head>
<body>
<div id="wrapper">
    <jsp:include page="layoutStore.jsp"></jsp:include>

    <%--  Content  --%>
    <div class="row wrapper border-bottom white-bg page-heading">
        <div class="col-lg-10">
            <h2>E-commerce orders</h2>
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <a href="index.html">Home</a>
                </li>
                <li class="breadcrumb-item">
                    <a>E-commerce</a>
                </li>
                <li class="breadcrumb-item active">
                    <strong>Orders</strong>
                </li>
            </ol>
        </div>
    </div>

    <div class="wrapper wrapper-content animated fadeInRight ecommerce">
        <div class="ibox-content m-b-sm border-bottom">
            <c:url var="searchFormLink" value="${requestScope.contextPath}/order/list"></c:url>
            <form action="${searchFormLink}" method="get" autocomplete="off">
                <div class="row">

                    <div class="col-lg-4">
                        <div class="form-group" id="date_range_order">
                            <label class="col-form-label">Range Date</label>
                            <div class="input-daterange input-group" id="datepicker">

                                <input type="text" class="form-control" name="startDate" id="datePickerStart"
                                       value="${requestScope.startDate}" data-mask="00/00/0000"
                                       placeholder="" autocomplete="on" maxlength="10">
                                <span class="input-group-addon">to</span>
                                <input type="text" class="form-control" name="endDate" id="datePickerEnd"
                                       value="${requestScope.endDate}" data-mask="00/00/0000"
                                       placeholder="" autocomplete="on" maxlength="10">
                            </div>
                            ${requestScope.dateError}
                        </div>
                    </div>

                    <div class="col-lg-2">
                        <div class="form-group">
                            <label class="col-form-label">Amount</label>
                            <input type="text" class="form-control" name="totalAmount"
                                   value="${requestScope.totalAmount}"
                                   autocomplete="off" maxlength="16">
                        </div>
                    </div>
                    <div class="container-fluid">
                        <button class="btn btn-outline-success  float-right"
                                type="submit">Search
                        </button>
                    </div>
                </div>
            </form>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="ibox">
                    <div class="ibox-content">

                        <table class="footable table table-stripped toggle-arrow-tiny" data-page-size="15">
                            <thead>
                            <tr>

                                <th>Order ID</th>
                                <th data-hide="phone">Amount</th>
                                <th data-hide="phone">Date added</th>
                                <th class="text-right" data-sort-ignore="true">Action</th>

                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="order" items="${requestScope.orders}">
                                <tr>
                                    <td>${order.id}</td>
                                    <td>${order.total}</td>
                                    <td>
                                        <fmt:formatDate value="${order.createdDate}" var="formattedDate" type="date"
                                                        pattern="dd/MM/yyyy"></fmt:formatDate>
                                            ${formattedDate}
                                    </td>
                                    <td class="text-right">
                                        <div class="btn-group">
                                            <c:url var="viewOrderDetail"
                                                   value="${requestScope.contextPath}/order/view"></c:url>
                                            <a href="${viewOrderDetail}">
                                                <button
                                                        class="btn-white btn btn-xs">View
                                                </button>
                                            </a>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="7">
                                    <nav aria-label="Page navigation example">
                                        <ul class="paginations">
                                            <li class="page-item">
                                                <a class="page-link" href="#" aria-label="Previous">
                                                    <span aria-hidden="true">&laquo;</span>
                                                    <span class="sr-only">Previous</span>
                                                </a>
                                            </li>
                                            <li class="page-item"><a class="page-link" href="#">1</a></li>

                                            <li class="page-item">
                                                <a class="page-link" href="#" aria-label="Next">
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
    <jsp:include page="footer.jsp"></jsp:include>
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

<!-- Data picker -->
<script src="../../js/plugins/datapicker/bootstrap-datepicker.js"></script>

<!-- Image cropper -->
<script src="../../js/plugins/cropper/cropper.min.js"></script>

<!-- Date range use moment.js same as full calendar plugin -->
<script src="../../js/plugins/fullcalendar/moment.min.js"></script>

<!-- Date range picker -->
<script src="../../js/plugins/daterangepicker/daterangepicker.js"></script>

<!-- Tags Input -->
<script src="../../js/plugins/bootstrap-tagsinput/bootstrap-tagsinput.js"></script>

<!-- Input Mask-->
<script src="../../js/plugins/jqueryMask/jquery.mask.min.js"></script>
<%-------------------------------------%>

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

<!-- Data picker -->
<script src="../js/plugins/datapicker/bootstrap-datepicker.js"></script>

<!-- Image cropper -->
<script src="../js/plugins/cropper/cropper.min.js"></script>

<!-- Date range use moment.js same as full calendar plugin -->
<script src="../js/plugins/fullcalendar/moment.min.js"></script>

<!-- Date range picker -->
<script src="../js/plugins/daterangepicker/daterangepicker.js"></script>

<!-- Tags Input -->
<script src="../js/plugins/bootstrap-tagsinput/bootstrap-tagsinput.js"></script>

<!-- Input Mask-->
<script src="../js/plugins/jqueryMask/jquery.mask.min.js"></script>

<!-- Page-Level Scripts -->
<script>
    $(document).ready(function () {

        $('.footable').footable();

    });

</script>

<script>
    $(document).ready(function () {

        $(document).ready(function () {
            $('#date_range_order .input-daterange').datepicker({
                keyboardNavigation: false,
                forceParse: false,
                autoclose: true,
                format: "dd/mm/yyyy"
            });
        });
    });
</script>
<script>
    var today = moment().format('DD/MM/YYYY');
    $('#datePickerStart').val(today);
    $('#datePickerEnd').val(today);
</script>
<!-- Date picker -->
<div class="datepicker datepicker-dropdown dropdown-menu datepicker-orient-left datepicker-orient-bottom"
     style="top: 3077.83px; left: 46px; z-index: 10; display: none;">
    <div class="datepicker-days" style="display: none;">
        <table class="table-condensed">
            <thead>
            <tr>
                <th colspan="8" class="datepicker-title" style="display: none;"></th>
            </tr>
            <tr>
                <th class="prev">«</th>
                <th colspan="6" class="datepicker-switch">July 2014</th>
                <th class="next">»</th>
            </tr>
            <tr>
                <th class="cw">&nbsp;</th>
                <th class="dow">Su</th>
                <th class="dow">Mo</th>
                <th class="dow">Tu</th>
                <th class="dow">We</th>
                <th class="dow">Th</th>
                <th class="dow">Fr</th>
                <th class="dow">Sa</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td class="cw">27</td>
                <td class="old day" data-date="1404000000000">29</td>
                <td class="old day" data-date="1404086400000">30</td>
                <td class="day" data-date="1404172800000">1</td>
                <td class="day" data-date="1404259200000">2</td>
                <td class="day" data-date="1404345600000">3</td>
                <td class="active day" data-date="1404432000000">4</td>
                <td class="day" data-date="1404518400000">5</td>
            </tr>
            <tr>
                <td class="cw">28</td>
                <td class="day" data-date="1404604800000">6</td>
                <td class="day" data-date="1404691200000">7</td>
                <td class="day" data-date="1404777600000">8</td>
                <td class="day" data-date="1404864000000">9</td>
                <td class="day" data-date="1404950400000">10</td>
                <td class="day" data-date="1405036800000">11</td>
                <td class="day" data-date="1405123200000">12</td>
            </tr>
            <tr>
                <td class="cw">29</td>
                <td class="day" data-date="1405209600000">13</td>
                <td class="day" data-date="1405296000000">14</td>
                <td class="day" data-date="1405382400000">15</td>
                <td class="day" data-date="1405468800000">16</td>
                <td class="day" data-date="1405555200000">17</td>
                <td class="day" data-date="1405641600000">18</td>
                <td class="day" data-date="1405728000000">19</td>
            </tr>
            <tr>
                <td class="cw">30</td>
                <td class="day" data-date="1405814400000">20</td>
                <td class="day" data-date="1405900800000">21</td>
                <td class="day" data-date="1405987200000">22</td>
                <td class="day" data-date="1406073600000">23</td>
                <td class="day" data-date="1406160000000">24</td>
                <td class="day" data-date="1406246400000">25</td>
                <td class="day" data-date="1406332800000">26</td>
            </tr>
            <tr>
                <td class="cw">31</td>
                <td class="day" data-date="1406419200000">27</td>
                <td class="day" data-date="1406505600000">28</td>
                <td class="day" data-date="1406592000000">29</td>
                <td class="day" data-date="1406678400000">30</td>
                <td class="day" data-date="1406764800000">31</td>
                <td class="new day" data-date="1406851200000">1</td>
                <td class="new day" data-date="1406937600000">2</td>
            </tr>
            <tr>
                <td class="cw">32</td>
                <td class="new day" data-date="1407024000000">3</td>
                <td class="new day" data-date="1407110400000">4</td>
                <td class="new day" data-date="1407196800000">5</td>
                <td class="new day" data-date="1407283200000">6</td>
                <td class="new day" data-date="1407369600000">7</td>
                <td class="new day" data-date="1407456000000">8</td>
                <td class="new day" data-date="1407542400000">9</td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <th colspan="8" class="today" style="display: table-cell;">Today</th>
            </tr>
            <tr>
                <th colspan="8" class="clear" style="display: none;">Clear</th>
            </tr>
            </tfoot>
        </table>
    </div>
    <div class="datepicker-months" style="display: none;">
        <table class="table-condensed">
            <thead>
            <tr>
                <th colspan="8" class="datepicker-title" style="display: none;"></th>
            </tr>
            <tr>
                <th class="prev">«</th>
                <th colspan="5" class="datepicker-switch">2014</th>
                <th class="next">»</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td colspan="7"><span class="month">Jan</span><span class="month">Feb</span><span
                        class="month">Mar</span><span class="month">Apr</span><span
                        class="month">May</span><span class="month">Jun</span><span
                        class="month focused active">Jul</span><span class="month">Aug</span><span
                        class="month">Sep</span><span class="month">Oct</span><span
                        class="month">Nov</span><span class="month">Dec</span></td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <th colspan="8" class="today" style="display: table-cell;">Today</th>
            </tr>
            <tr>
                <th colspan="8" class="clear" style="display: none;">Clear</th>
            </tr>
            </tfoot>
        </table>
    </div>
    <div class="datepicker-years" style="display: none;">
        <table class="table-condensed">
            <thead>
            <tr>
                <th colspan="8" class="datepicker-title" style="display: none;"></th>
            </tr>
            <tr>
                <th class="prev">«</th>
                <th colspan="5" class="datepicker-switch">2010-2019</th>
                <th class="next">»</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td colspan="7"><span class="year old">2009</span><span class="year">2010</span><span
                        class="year">2011</span><span class="year">2012</span><span
                        class="year">2013</span><span class="year active focused">2014</span><span
                        class="year">2015</span><span class="year">2016</span><span
                        class="year">2017</span><span class="year">2018</span><span
                        class="year">2019</span><span class="year new">2020</span></td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <th colspan="8" class="today" style="display: table-cell;">Today</th>
            </tr>
            <tr>
                <th colspan="8" class="clear" style="display: none;">Clear</th>
            </tr>
            </tfoot>
        </table>
    </div>
    <div class="datepicker-decades" style="display: none;">
        <table class="table-condensed">
            <thead>
            <tr>
                <th colspan="8" class="datepicker-title" style="display: none;"></th>
            </tr>
            <tr>
                <th class="prev">«</th>
                <th colspan="5" class="datepicker-switch">2000-2090</th>
                <th class="next">»</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td colspan="7"><span class="decade old">1990</span><span class="decade">2000</span><span
                        class="decade active focused">2010</span><span class="decade">2020</span><span
                        class="decade">2030</span><span class="decade">2040</span><span
                        class="decade">2050</span><span class="decade">2060</span><span
                        class="decade">2070</span><span class="decade">2080</span><span
                        class="decade">2090</span><span class="decade new">2100</span></td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <th colspan="8" class="today" style="display: table-cell;">Today</th>
            </tr>
            <tr>
                <th colspan="8" class="clear" style="display: none;">Clear</th>
            </tr>
            </tfoot>
        </table>
    </div>
    <div class="datepicker-centuries" style="display: none;">
        <table class="table-condensed">
            <thead>
            <tr>
                <th colspan="8" class="datepicker-title" style="display: none;"></th>
            </tr>
            <tr>
                <th class="prev">«</th>
                <th colspan="5" class="datepicker-switch">2000-2900</th>
                <th class="next">»</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td colspan="7"><span class="century old">1900</span><span
                        class="century active focused">2000</span><span class="century">2100</span><span
                        class="century">2200</span><span class="century">2300</span><span
                        class="century">2400</span><span class="century">2500</span><span
                        class="century">2600</span><span class="century">2700</span><span
                        class="century">2800</span><span class="century">2900</span><span
                        class="century new">3000</span></td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <th colspan="8" class="today" style="display: table-cell;">Today</th>
            </tr>
            <tr>
                <th colspan="8" class="clear" style="display: none;">Clear</th>
            </tr>
            </tfoot>
        </table>
    </div>
</div>
</body>
</html>
