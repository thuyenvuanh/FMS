<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: LEGION
  Date: 6/20/2022
  Time: 5:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <title>Counter - Input Phone</title>

    <link href="../../css/bootstrap.min.css" rel="stylesheet" />
    <link href="../../font-awesome/css/font-awesome.css" rel="stylesheet" />

    <!-- FooTable -->
    <link href="../../css/plugins/footable/footable.core.css" rel="stylesheet" />

    <link href="../../css/animate.css" rel="stylesheet" />
    <link href="../../css/style.css" rel="stylesheet" />

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
            <h2>Counter</h2>
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <a href="<c:url value="/counter/index"/>">Counter</a>
                </li>

                <li class="breadcrumb-item active">
                    <strong>Input Phone Number</strong>
                </li>
            </ol>
        </div>
    </div>

    <div class="wrapper wrapper-content animated fadeInRight ecommerce">
        <div class="row">
            <div class="col-lg-12">
                <div class="tabs-container">
                    <ul class="nav nav-tabs">
                        <li>
                            <a class="nav-link active" data-toggle="tab" href="#tab-1">
                                Phone number</a>
                        </li>

                    </ul>
                    <div class="tab-content">
                        <div id="tab-1" class="tab-pane active">
                            <div class="panel-body">
                                <c:url var="checklink" value="${requestScope.contextPath}/counter/check">
                                </c:url>
                                <form id="form_phonenumber" action="${checklink}" method="POST">

                                    <fieldset>
                                        <div class="form-group row">
                                            <label class="col-sm-2 col-form-label">Phone</label>
                                            <div class="col-sm-10">
                                                <input type="text" name="phoneNumber"
                                                       class="form-control" data-mask="000 000 0000"
                                                       placeholder=""
                                                       autocomplete="off" maxlength="14" required />
                                                <span class="form-text">000 000 0000</span>
                                            </div>
                                        </div>
                                    </fieldset>
                                    <div class="form-layout-footer text-center">
                                        <button type="submit" class="btn btn-primary bd-0">
                                            Recharge
                                        </button>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>


<%--script--%>
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

        $("form_phonenumber").validate({
            rules: {
                phoneNumber: {
                    required: true
                }
            },
            messages: {
                phoneNumber: {
                    required: 'Please enter phone number'
                }
            }
        })
    });
</script>
<script src="../../js/plugins/jqueryMask/jquery.mask.min.js"></script>
<script src="../js/plugins/jqueryMask/jquery.mask.min.js"></script>
</body>
</html>
