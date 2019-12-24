<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<head>
    <title>계정 생성</title>
    <link href="/resources/css/user/join.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet" >
    <script src="/resources/js/user/joinAndEdit.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
</head>

<spring:url var="joinUri" value="/user/join"/>
<spring:url var="joinCancelUri" value="/user/consent"/>

<div id="c_join" class="container c_container-sm">
    <div class="container c_headerLine">
    </div>
    <div class="container c_header">
        <h5>계정 등록</h5>
        <p>가입 정보를 입력하여 쿠폰/마일리지 등 다양한 혜택을 받으세요.</p>
    </div>

    <div class="d-flex justify-content-center c_step">
        <ul>
            <li>
                <p><strong>STEP 1</strong></p>
                <p>이용 약관 동의</p>
            </li>
            <li class="active">
                <p><strong>STEP 2</strong></p>
                <p>계정 정보 입력</p>
            </li>
            <li>
                <p><strong>STEP 3</strong></p>
                <p>계정 등록 완료</p>
            </li>
        </ul>
    </div>

    <div id="c_joinForm" class="container">
        <form:form method="post" modelAttribute="user" action="${joinUri}" novalidate="true">
            <div class="materialGroup">
                <p id="c_joinFormLoginHeader">로그인 정보</p>
                <div class="form-group">
                    <form:input id="email" name="email" path="email" type="text" value="${email}" required="required" maxlength="40" onblur="emailCheck()"/>
                    <span class="highlight"></span>
                    <span class="bar"></span>
                    <label for="email">* 이메일 주소</label>

                    <form:errors id="emailValidation" path="email"/>
                    <span id="emailCheck"></span>
                </div>
                <div class="form-group">
                    <form:input id="password" name="password" path="password" type="password" required="required" maxlength="15" onblur="passwordCheck()"/>
                    <span class="highlight"></span>
                    <span class="bar"></span>
                    <label for="password">* 비밀번호</label>

                    <form:errors id="passwordValidation" path="password"/>
                </div>
                <div class="form-group">
                    <input id="passwordConfirm" type="password" required="required" maxlength="20" onblur="passwordCheck()"/>
                    <span class="highlight"></span>
                    <span class="bar"></span>
                    <label for="passwordConfirm">* 비밀번호 확인</label>

                    <span id="passwordCheck"></span>
                </div>

                <p id="c_joinFormPrivacyHeader">개인 정보</p>
                <div id="c_joinFormName" class="form-group">
                    <form:input id="name" name="name" path="name" type="text" value="${name}" required="required" maxlength="5" onblur="nameCheck()"/>
                    <span class="highlight"></span>
                    <span class="bar"></span>
                    <label for="name">* 이름</label>

                    <form:errors id="nameValidation" path="name"/>
                    <span id="nameCheck"></span>
                </div>
                <div class="form-group">
                    <form:input id="nickname" name="nickname" path="nickname" type="text" value="${nickname}" required="required" maxlength="10" onblur="nicknameCheck()"/>
                    <span class="highlight"></span>
                    <span class="bar"></span>
                    <label for="nickname">* 닉네임</label>

                    <form:errors id="nicknameValidation" path="nickname"/>
                    <span id="nicknameCheck"></span>
                </div>
                <div class="form-group">
                    <form:input id="phone" name="phone" path="phone" type="text" value="${phone}" required="required" maxlength="11" onblur="phoneCheck()"/>
                    <span class="highlight"></span>
                    <span class="bar"></span>
                    <label for="phone">* 연락처</label>

                    <form:errors id="phoneValidation" path="phone"/>
                    <span id="phoneCheck"></span>
                </div>
            </div>

            <jsp:include page="/WEB-INF/views/component/bootstrapAlertMessage.jsp">
                <jsp:param name="type" value="danger"/>
                <jsp:param name="title" value="등록"/>
            </jsp:include>

            <button type="submit" class="btn btn-neutral c_button"><i class="fas fa-angle-right"></i> 등록</button>
            <button type="button" class="btn btn-neutral btn-heart c_button" onclick="location.href='${joinCancelUri}'"><i class="fas fa-angle-right"></i> 취소</button>
        </form:form>
    </div>
</div>