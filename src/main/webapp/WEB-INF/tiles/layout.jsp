<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%-- Fonts and Icons --%>
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css?family=Lato:100,300,400,700,900&amp;subset=latin-ext" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/gh/theeluwin/NotoSansKR-Hestia@master/stylesheets/NotoSansKR-Hestia.css" integrity="sha384-zGzb8nSrN9lm5PPcfztVC5DjjTs5sVStV85IH8x51fM9yIwmI+Uvh3RZDJlrpPWQ" crossorigin="anonymous" rel="stylesheet">
    <link href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" rel="stylesheet">
    <%-- Core CSS Files --%>
    <link href="/resources/assets/now/css/bootstrap.min.css?v=<%=System.currentTimeMillis()%>" rel="stylesheet"/>
    <%-- Custom CSS Files --%>
    <link href="/resources/assets/now/css/custom-now-ui-kit.css?v=<%=System.currentTimeMillis()%>" rel="stylesheet">
    <link href="/resources/assets/alerts.css?v=<%=System.currentTimeMillis()%>" rel="stylesheet">
    <link href="/resources/assets/froala/css/froala_editor.pkgd.css?v=<%=System.currentTimeMillis()%>" rel="stylesheet"/>
    <link href="/resources/css/component/content.css?v=<%=System.currentTimeMillis()%>" rel="stylesheet">

    <%-- JS 로딩: async(JS 간 의존성이 없을 경우 사용) / defer(JS 간 의존성이 있을 경우 사용)--%>
    <%-- Core JS Files (jQuery 부터 로딩)--%>
    <script src="https://code.jquery.com/jquery-3.2.1.js?v=<%=System.currentTimeMillis()%>" integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE=" crossorigin="anonymous"></script>
    <script src="/resources/assets/now/js/core/popper.min.js?v=<%=System.currentTimeMillis()%>" type="text/javascript" defer></script>
    <script src="/resources/assets/now/js/core/bootstrap.min.js?v=<%=System.currentTimeMillis()%>" type="text/javascript" defer></script>
    <%-- Plugins --%>
    <script src="/resources/assets/now/js/plugins/bootstrap-switch.js?v=<%=System.currentTimeMillis()%>" defer></script>
    <script src="/resources/assets/now/js/plugins/nouislider.min.js?v=<%=System.currentTimeMillis()%>" defer></script>
    <script src="/resources/assets/now/js/plugins/bootstrap-datepicker.js?v=<%=System.currentTimeMillis()%>" defer></script>
    <script src="/resources/assets/now/js/now-ui-kit.js?v=<%=System.currentTimeMillis()%>" defer></script>
    <script src="/resources/assets/counter.js?v=<%=System.currentTimeMillis()%>" defer></script>
    <script src="/resources/assets/alerts.js?v=<%=System.currentTimeMillis()%>" defer></script>
    <script src="/resources/assets/froala/js/froala_editor.pkgd.min.js?v=<%=System.currentTimeMillis()%>" defer></script>
<%--    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js" defer></script>--%>
<%--    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js" defer></script>--%>
    <%-- 공통 JS Files --%>
    <script src="/resources/js/common.js?v=<%=System.currentTimeMillis()%>" type="text/javascript" defer></script>
    <title></title>
</head>

<body>
    <%-- Wrapper(가운데 정렬, 최대 너비 지정) --%>
    <%-- ignore 속성의 기본 값은 false 이므로 없을 경우, 에러 발생 (참고: https://stackunderflow.tistory.com/entry/CannotRenderException) --%>
    <div id="wrapper">
        <%-- Header --%>
        <div id="layout_header">
            <tiles:insertAttribute name="header" ignore="true"/>
        </div>

        <%-- Title --%>
        <div id="layout_title">
            <tiles:insertAttribute name="title" ignore="true"/>
        </div>

        <%-- Side Menu --%>
        <div id="layout_side">
            <tiles:insertAttribute name="side" ignore="true"/>
        </div>

        <%-- Menu --%>
        <div id="layout_menu">
            <tiles:insertAttribute name="menu" ignore="true"/>
        </div>

        <%-- Content --%>
        <div id="layout_content">
            <tiles:insertAttribute name="content" ignore="true"/>
        </div>

    </div>

    <%-- Footer --%>
    <div id="layout_footer">
        <tiles:insertAttribute name="footer" ignore="true"/>
    </div>

</body>

<script type="text/javascript">

</script>
</html>