<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<head>
    <title>: 계정 로그인</title>
    <link href="/resources/css/user/login.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet">
</head>

<spring:url var="homeUri" value="/"/>
<spring:url var="consentUri" value="/user/consent"/>
<spring:url var="loginUri" value="/user/login"/>

<div id="c_login" class="container c_container-xs">

    <%-- 계정 로그인 폼 헤더 영역 --%>
    <div class="container c_headerLine">
    </div>
    <div class="container c_header">
        <h5>계정 로그인</h5>
        <p>이메일과 비밀번호를 입력하여 로그인하세요.</p>
    </div>

<%--    <div class="input-group">--%>
<%--        <div class="input-group-prepend">--%>
<%--            <div class="input-group-text"><i class="fa fa-user-circle"></i></div>--%>
<%--        </div>--%>
<%--        <input type="text" class="form-control" placeholder="Left Font Awesome Icon">--%>
<%--        <div class="input-group-append">--%>
<%--            <div class="input-group-text"><i class="fa fa-user-circle"></i></div>--%>
<%--        </div>--%>
<%--    </div>--%>

    <%-- 계정 로그인 폼 컨텐트 영역 --%>
    <form id="c_loginForm" method="post" action="${loginUri}" novalidate="true">
        <div class="form-group">
            <input id="c_loginEmail" class="form-control" name="email" type="text" value="admin@test.com" placeholder="계정 이메일">
        </div>
        <div class="form-group">
            <input id="c_loginPassword" class="form-control" name="password" type="password" value="test123!" placeholder="계정 비밀번호">
        </div>

        <div id="c_loginButton" class="form-group">
            <button class="btn btn-primary btn-block" type="submit"><strong>로그인</strong></button>
        </div>
    </form>

    <%-- 계정 로그인 폼 푸터 영역 --%>
    <div id="c_loginFooter" class="container">
        <div id="c_loginFooterItem" class="d-flex bd-highlight mb-3">
            <div class="mr-auto p-1 bd-highlight"><a href="${consentUri}">계정 등록</a></div>
            <div class="p-1 bd-highlight"><a href="${homeUri}">이메일/비밀번호 찾기</a></div>
        </div>
    </div>

    <jsp:include page="/WEB-INF/views/component/bootstrapAlertMessage.jsp">
        <jsp:param name="title" value="로그인"/>
    </jsp:include>
</div>