<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<head>
    <link href="/resources/css/chat/room/page.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet">
<%--    <script src="/resources/js/chat/room/page.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>--%>
</head>

<spring:url var="roomNewUri" value="/chat/room/new"/>
<spring:url var="adminRoomUri" value="/admin/home"/>

<div id="c_roomListPage" class="container">
    <div class="d-flex bd-highlight mb-3">
        <button id="c_roomNewButton" type="button" class="btn btn-neutral btn-heart btn-sm ml-auto" onclick="location.href='${roomNewUri}'"><i class="fas fa-edit"></i> 새 채팅</button>
        <sec:authorize access="hasRole('ROLE_M')">
            <button id="c_adminRoomButton" type="button" class="btn btn-neutral btn-heart btn-sm" onclick="location.href='${adminRoomUri}'"><i class="fas fa-edit"></i> 관리</button>
        </sec:authorize>
    </div>

</div>
