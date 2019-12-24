<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<head>
    <link href="/resources/css/error/error.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet">
</head>

<body>
<div id="c_error" class="container">
    <br/><br/>
    <c:if test="${error.STATUS_CODE eq 400}">
        <img src="/resources/img/error/400.png" alt="error400">
    </c:if>
    <c:if test="${error.STATUS_CODE eq 403}">
        <img src="/resources/img/error/403.png" alt="error403">
    </c:if>
    <c:if test="${error.STATUS_CODE eq 404}">
        <img src="/resources/img/error/404.png" alt="error404">
    </c:if>
    <c:if test="${error.STATUS_CODE eq 405}">
        <img src="/resources/img/error/405.png" alt="error405">
    </c:if>
    <c:if test="${error.STATUS_CODE eq 500}">
        <img src="/resources/img/error/500.png" alt="error500">
    </c:if>
    <c:if test="${error.STATUS_CODE eq 503}">
        <img src="/resources/img/error/503.png" alt="error503">
    </c:if>

    <br/><br/>
    <h3 id="c_errorText">OOOPS!</h3>

    <h5>${error.RESULT_MESSAGE}</h5>
    <p>입력하신 페이지 주소가 정확한지 다시 한번 확인해보시기 바랍니다.</p>
    <p>관련 문의사항이 있으시면 고객센터로 문의해 주시기 바랍니다.</p>

    <br/><br/>

    <button id="c_errorHomeButton" class="btn btn-neutral btn-heart btn-sm btn-font-sm" type="button" onclick="location.href='/'"><i class="fas fa-home"></i> Return to Homepage</button>
</div>

<div id="c_errorFooter" class="container">
<%--    <p>${error.STATUS_CODE}</p>--%>
<%--    <p>${error.MESSAGE}</p>--%>
<%--    <p>${error.EXCEPTION_TYPE}</p>--%>
<%--    <p>${error.EXCEPTION}</p>--%>
<%--    <p>${error.REQUEST_URI}</p>--%>
<%--    <p>${error.RESULT_MESSAGE}</p>--%>
</div>

</body>