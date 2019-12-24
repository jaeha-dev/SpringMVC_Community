<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<head>
    <link href="/resources/css/component/side.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet">
    <script src="/resources/js/component/side.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
</head>

<spring:url var="joinConsentUri" value="/user/join"/>
<spring:url var="loginUri" value="/user/login"/>
<spring:url var="editUri" value="/user/edit"/>
<spring:url var="userHomeUri" value="/user/home"/>
<spring:url var="logoutUri" value="/user/logout"/>

<div id="s_menu" class="control-center">
    <ul class="s_menuBottomSidebar">
        <sec:authorize access="isAnonymous()">
            <li class="btn-sm btn-round" data-toggle="tooltip" data-placement="left" title="계정 등록"><i class="fas fa-tag"></i></li>
            <li class="btn-sm btn-round" data-toggle="tooltip" data-placement="left" title="북마크 등록"><i class="fas fa-tag"></i></li>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <li class="btn-sm btn-round" data-toggle="tooltip" data-placement="left" title="계정 수정" onclick="window.location.href='${editUri}'"><i class="fas fa-tag"></i></li>
            <li class="btn-sm btn-round" data-toggle="tooltip" data-placement="left" title="계정 관리"><i class="fas fa-tag"></i></li>
        </sec:authorize>
    </ul>
    <div id="s_menuButton" class="btn btn-neutral btn-icon btn-round option-btn" data-toggle="tooltip" data-placement="left" title="퀵 메뉴" onclick="quickMenu()"></div>
    <ul class="s_menuTopSidebar">
        <sec:authorize access="isAnonymous()">
            <li class="btn-sm btn-round" data-toggle="tooltip" data-placement="left" title="로그인"><i class="fas fa-tag"></i></li>
            <li class="btn-sm btn-round" data-toggle="tooltip" data-placement="left" title="계정 관리"><i class="fas fa-tag"></i></li>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <li class="btn-sm btn-round" data-toggle="tooltip" data-placement="left" title="로그아웃"><i class="fas fa-tag"></i></li>
            <li class="btn-sm btn-round" data-toggle="tooltip" data-placement="left" title="계정 관리"><i class="fas fa-tag"></i></li>
        </sec:authorize>
    </ul>
</div>

<%-- 퀵 이동 버튼 --%>
<div id="s_menuQuick">
    <button id="s_menuTopButton" class="btn btn-neutral btn-icon btn-round" onclick="goTop()"><i class="fas fa-chevron-up"></i></button>
    <button id="s_menuBottomButton" class="btn btn-neutral btn-icon btn-round" onclick="goBottom()"><i class="fas fa-chevron-down"></i></button>
</div>