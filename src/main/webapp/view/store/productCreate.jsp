<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Product Create</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet"/>
    <link href="../font-awesome/css/font-awesome.css" rel="stylesheet"/>

    <!-- FooTable -->
    <link href="../css/plugins/footable/footable.core.css" rel="stylesheet"/>

    <link href="../css/animate.css" rel="stylesheet"/>
    <link href="../css/style.css" rel="stylesheet"/>
    <!-- Sweet Alert -->
    <link href="../css/plugins/sweetalert/sweetalert.css" rel="stylesheet"/>
    <link href="../../css/plugins/sweetalert/sweetalert.css" rel="stylesheet"/>

    <link href="../../css/bootstrap.min.css" rel="stylesheet"/>
    <link href="../../font-awesome/css/font-awesome.css" rel="stylesheet"/>

    <!-- FooTable -->
    <link href="../../css/plugins/footable/footable.core.css" rel="stylesheet"/>

    <link href="../../css/animate.css" rel="stylesheet"/>
    <link href="../../css/style.css" rel="stylesheet"/>

</head>
<body>
<div id="wrapper">
    <jsp:include page="layoutStore.jsp"></jsp:include>
    <div class="row wrapper border-bottom white-bg page-heading">
        <div class="col-lg-10">
            <h2>Product Create</h2>
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <a href="index.html">Home</a>
                </li>
                <li class="breadcrumb-item">
                    <a>E-commerce</a>
                </li>
                <li class="breadcrumb-item">
                    <a>Products list</a>
                </li>
                <li class="breadcrumb-item active">
                    <strong>Product create</strong>
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
                                Product info</a
                            >
                        </li>
                    </ul>
                    <div class="tab-content">
                        <div id="tab-1" class="tab-pane active">
                            <div class="panel-body">

                                <c:url var="createLink" value="${requestScope.contextPath}/product/create">
                                </c:url>
                                <form class="createForm" action="${createLink}" autocomplete="off" enctype="multipart/form-data" method="post">
                                    <fieldset>
                                        <div class="form-group row">
                                            <label class="col-sm-1 col-form-label">Name:</label>
                                            <div class="col-sm-5">
                                                <input
                                                        type="text"
                                                        class="form-control"
                                                        placeholder="Product name"
                                                        name="name"
                                                        autocomplete="off"/>

                                            </div>

                                            <label class="col-sm-1 col-form-label">Price:</label>
                                            <div class="col-sm-3">
                                                <input
                                                        type="text" class="form-control" data-mask="0000000000000" placeholder="₫"
                                                        autocomplete="off" maxlength="17" id="price" name="price"
                                                        value="1"
                                                />
                                            </div>
                                            VND
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-sm-1 col-form-label"
                                            >Image:</label
                                            >

                                            <div class="input-group col-sm-3">
                                                <div class="mb-3">
                                                    <input class="form-control" type="file" id="formFileMultiple"
                                                           name="imagePath" multiple>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-sm-1 col-form-label" for="category">Category</label>
                                            <div class="col-sm-4">
                                                <select name="categoryID" id="category" class="select_category form-control">
                                                    <c:forEach var="category" items="${requestScope.categories}">
                                                        <option value="${category.id}" ${category.id==1?"selected":""} >
                                                                ${category.name} (${category.shortName})
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <label class="col-sm-1 col-form-label"
                                            >Quantity:</label
                                            >
                                            <div class="col-sm-3">
                                                <input
                                                        type="number" class="form-control" data-mask="0000000000000" placeholder="Quantity"
                                                        autocomplete="off" maxlength="17" id="quantity" name="quantity"
                                                        value="1"
                                                />
                                            </div>
                                        </div>
                                    </fieldset>

                                    <div class="form-layout-footer text-center">
                                        <button type="button"
                                                class="btn btn-primary bd-0"
                                                id="create_product_form">
                                            Submit Form
                                        </button>
                                        <%--                                        <a href="productList.jsp">--%>
                                        <%--                                            <button class="btn btn-secondary bd-0">--%>
                                        <%--                                                Cancel--%>
                                        <%--                                            </button>--%>
                                        <%--                                        </a--%>
                                        <button onclick="history.back()" type="button" class="btn btn-dark">Cancel
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
    <jsp:include page="footer.jsp"></jsp:include>
</div>

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
<!-- Mainly scripts -->
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
<%--SweetAlert--%>
<script src="../../js/plugins/sweetalert/sweetalert.min.js"></script>
<!-- CodeMirror -->
<script src="../../js/plugins/codemirror/codemirror.js"></script>
<script src="../../js/plugins/codemirror/mode/xml/xml.js"></script>
<!-- Mainly scripts -->
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

<!-- Page-Level Scripts -->
<!-- Sweet alert -->
<script src="../js/plugins/sweetalert/sweetalert.min.js"></script>

<script>
    $(document).ready(function () {
        $("#create_product_form").click(function () {
            swal({
                title: "Are you sure to create?",
                type: "info",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "Yes, create it!",
                closeOnConfirm: false,
            });
        });
        $(".confirm").click(function () {
            <%--window.location = "${createLink}";--%>
            $(".createForm").submit();
        });
    });
</script>
<script>
    $(document).ready(function () {
        $(".footable").footable();
    });
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>
