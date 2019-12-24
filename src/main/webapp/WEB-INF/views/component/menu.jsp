<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<head>
    <link href="/resources/css/component/menu.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet">
    <script src="/resources/js/component/menu.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
</head>

<spring:url var="postListUri" value="/board/post/list"/>
<spring:url var="roomListUri" value="/chat/room/list"/>
<spring:url var="errorUri" value="/error/404"/>
<spring:url var="adminUri" value="/admin/home"/>

<div id="m_menu" class="container">
    <ul class="nav justify-content-center">
        <li class="p-2">
            <button type="button" class="btn btn-neutral" data-toggle="tooltip" data-placement="bottom" title="커뮤니티"><a href="${postListUri}">COMMUNITY</a></button>
        </li>
        <li class="p-2">
            <button type="button" class="btn btn-neutral" data-toggle="tooltip" data-placement="bottom" title="채팅"><a href="${roomListUri}">CHATTING</a></button>
        </li>
        <li class="p-2">
            <button type="button" class="btn btn-neutral" data-toggle="tooltip" data-placement="bottom" title="에러"><a href="${errorUri}">ERROR</a></button>
        </li>
        <li class="p-2">
            <button type="button" class="btn btn-neutral" data-toggle="tooltip" data-placement="bottom" title="관리"><a href="${adminUri}">ADMIN</a></button>
        </li>
    </ul>
</div>

<%--<ul id="m_menu" class="nav justify-content-center">--%>
<%--    <li class="p-2">--%>
<%--        <button type="button" class="btn btn-neutral" data-toggle="tooltip" data-placement="bottom" title="이 달의 새 메뉴">NEW</button>--%>
<%--    </li>--%>
<%--    <li class="p-2">--%>
<%--        <button type="button" class="btn btn-neutral" data-toggle="tooltip" data-placement="bottom" title="커피 메뉴">COFFEE</button>--%>
<%--    </li>--%>
<%--    <li class="p-2">--%>
<%--        <button type="button" class="btn btn-neutral" data-toggle="tooltip" data-placement="bottom" title="음료 메뉴">BEVERAGE</button>--%>
<%--    </li>--%>
<%--    <li class="p-2">--%>
<%--        <button type="button" class="btn btn-neutral" data-toggle="tooltip" data-placement="bottom" title="디저트 메뉴">DESSERT</button>--%>
<%--    </li>--%>
<%--    <li class="p-2">--%>
<%--        <button type="button" class="btn btn-neutral" data-toggle="tooltip" data-placement="bottom" title="케이크 메뉴">CAKE</button>--%>
<%--    </li>--%>
<%--    <li class="p-2">--%>
<%--        <button type="button" class="btn btn-neutral" data-toggle="tooltip" data-placement="bottom" title="선물 메뉴">GIFT</button>--%>
<%--    </li>--%>
<%--</ul>--%>

<%-- 스크롤 시, 메뉴 상단 고정 --%>
<%--<nav id="m_menu" class="navbar justify-content-center navbar-transparent" color-on-scroll="100">--%>
<%--    <ul id="m_menuItem" class="nav">--%>
<%--        <li class="nav-item">--%>
<%--            <button type="button" class="btn btn-neutral" data-toggle="tooltip" data-placement="bottom" title="이 달의 새 메뉴">NEW</button>--%>
<%--        </li>--%>
<%--        <li class="nav-item">--%>
<%--            <button type="button" class="btn btn-neutral" data-toggle="tooltip" data-placement="bottom" title="커피 메뉴">COFFEE</button>--%>
<%--        </li>--%>
<%--        <li class="nav-item">--%>
<%--            <button type="button" class="btn btn-neutral" data-toggle="tooltip" data-placement="bottom" title="음료 메뉴">BEVERAGE</button>--%>
<%--        </li>--%>
<%--        <li class="nav-item">--%>
<%--            <button type="button" class="btn btn-neutral" data-toggle="tooltip" data-placement="bottom" title="디저트 메뉴">DESSERT</button>--%>
<%--        </li>--%>
<%--        <li class="nav-item">--%>
<%--            <button type="button" class="btn btn-neutral" data-toggle="tooltip" data-placement="bottom" title="케이크 메뉴">CAKE</button>--%>
<%--        </li>--%>
<%--        <li class="nav-item">--%>
<%--            <button type="button" class="btn btn-neutral" data-toggle="tooltip" data-placement="bottom" title="선물 메뉴">GIFT</button>--%>
<%--        </li>--%>
<%--    </ul>--%>
<%--</nav>--%>