<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<head>
    <link href="/resources/css/user/home.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet">
</head>

<spring:url var="homeUri" value="/"/>
<spring:url var="editUri" value="/user/edit"/>
<spring:url var="deleteUri" value="/user/delete"/>

<div id="c_userHome" class="container c_container-xs">

    <%-- 계정 홈 헤더 영역 --%>
    <div class="container c_headerLine">
    </div>
    <div class="container c_header">
        <h5>계정 관리</h5>
        <p>등록된 계정 정보를 수정할 수 있습니다.</p>
    </div>

    <%-- 계정 홈 컨텐트 영역 --%>

    <%-- 계정 홈 푸터 영역 --%>
    <div id="c_loginFooter" class="container">
        <div id="c_loginFooterItem" class="d-flex bd-highlight mb-3">
            <div class="mr-auto p-1 bd-highlight"><a href="${editUri}">계정 수정</a></div>
            <div class="p-1 bd-highlight"><a href="${deleteUri}">계정 탈퇴</a></div>
        </div>
    </div>
</div>