<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<head>
    <link href="/resources/css/component/header.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet">
    <script src="/resources/js/component/header.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
</head>

<spring:url var="homeUri" value="/"/>
<spring:url var="joinConsentUri" value="/user/consent"/>
<spring:url var="loginUri" value="/user/login"/>
<spring:url var="logoutUri" value="/user/logout"/>
<spring:url var="adminUri" value="/admin/home"/>
<spring:url var="myPageUri" value="/user/home"/>

<div id="h_headerProgress">
    <div id="h_headerProgressBar"></div>
</div>

<div class="d-flex bd-highlight mb-3">
    <a class="mr-auto p-3 bd-highlight" href="#" onclick="addFavorite()" data-toggle="tooltip" data-placement="bottom" title="북마크 등록">BOOKMARK +</a>

    <a class="p-3 bd-highlight" href="${homeUri}">HOME</a>

    <sec:authorize access="isAnonymous()">
        <%-- 비로그인 --%>
        <a class="p-3 bd-highlight" href="${loginUri}">LOGIN</a>
        <a class="p-3 bd-highlight" href="${joinConsentUri}">JOIN US</a>
    </sec:authorize>
    <c:if test="${isLoginFailure eq true}">
        <%-- 로그인 실패 --%>
        <a class="p-3 bd-highlight" href="${loginUri}">LOGIN</a>
        <a class="p-3 bd-highlight" href="${joinConsentUri}">JOIN US</a>
    </c:if>

    <sec:authorize access="isAuthenticated()">
        <%-- 로그인 --%>
        <a class="p-3 bd-highlight" href="${logoutUri}">LOGOUT</a>
        <a class="p-3 bd-highlight" href="${myPageUri}">MY PAGE</a>
        <sec:authorize access="hasRole('ROLE_M')">
            <%-- 관리자 --%>
            <a class="p-3 bd-highlight" href="${adminUri}">ADMIN PAGE</a>
        </sec:authorize>
    </sec:authorize>
</div>