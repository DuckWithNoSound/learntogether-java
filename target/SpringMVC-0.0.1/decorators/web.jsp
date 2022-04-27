<%@taglib prefix="dec" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <link rel="alternate" hreflang="vi" href="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value='/assets/CSS/resetCSS.css'/>">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Slab:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/assets/ASSET/fontawesome-5.15.2/css/all.min.css'/>">
    <!-- Google font -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:300,700" rel="stylesheet">
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <!-- Custom stlylesheet -->
    <link rel="stylesheet" href="<c:url value='/assets/CSS/stylesheet.css'/>" >
    <link rel="stylesheet" href="<c:url value='/assets/CSS/style.css'/>">
    <link rel="stylesheet" href="<c:url value='/assets/CSS/personal.css'/>">
    <!-- Jquery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="<c:url value='/assets/JS/jquery.twbsPagination.js'/>"></script>
    <!-- CKEDITOR -->
    <script src="<c:url value='/assets/ASSET/ckeditor/ckeditor.js'/>" charset="utf-8"></script>
    <!-- Multiseclect -->
    <link rel="stylesheet" href="<c:url value='/assets/ASSET/filter-multi-select/dist/filter_multi_select.css'/>" />
    <script src="<c:url value='/assets/ASSET/filter-multi-select/dist/filter-multi-select-bundle.js'/>"></script>
    <!--[if lte IE 9]>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <![endif]-->
    <title>Learn together | Khó đâu - Hỏi đấy - Cùng giải quyết vấn đề.</title>
    <link rel="icon" type="image/png" href="<c:url value='/assets/Images/square-logo.png'/>">
</head>
<body>
    <%@include file="/common/web/header.jsp"%>

    <dec:body></dec:body>

    <%@include file="/common/web/footer.jsp"%>

    <!-- common script -->
    <script src="<c:url value='/assets/JS/main.js'/>"></script>
    <script>
        <c:if test="${param.get('Success') != null}">
        (function(){
            <c:if test="${param.get('Success') == 'Registered'}">
            alert("Registered successfully !");
            </c:if>
        })();
        </c:if>
        <c:if test="${ error != null}">
        (function(){
            alert("${error}");
        })();
        </c:if>
    </script>
</body>
</html>
