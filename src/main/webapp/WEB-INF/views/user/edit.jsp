<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<head>
    <title>계정 수정</title>
    <link href="/resources/css/user/edit.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet" >
    <script src="/resources/js/user/joinAndEdit.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
</head>

<spring:url var="editEmailUri" value="/user/edit/email"/>
<spring:url var="editNameUri" value="/user/edit/name"/>
<spring:url var="editPhoneUri" value="/user/edit/phone"/>
<spring:url var="editNicknameUri" value="/user/edit/nickname"/>
<spring:url var="editPasswordUri" value="/user/edit/password"/>

<div id="c_userEdit" class="container c_container-xs">
    <div class="container c_headerLine">
    </div>
    <div class="container c_header">
        <h5>계정 수정</h5>
        <p>등록된 계정 정보를 수정할 수 있습니다.</p>
    </div>

    <div id="c_userEditForm" class="container">
        <%-- 이메일 수정 --%>
        <form:form method="post" modelAttribute="user" action="${editEmailUri}" novalidate="true">
            <div class="materialGroup">
                <div class="form-group">
                    <form:input id="email" name="email" path="email" type="text" value="${email}" required="required" maxlength="40" onblur="emailCheck()"/>
                    <span class="highlight"></span>
                    <span class="bar"></span>
                    <label for="email">* 이메일 주소</label>

                    <form:errors id="emailValidation" path="email"/>
                    <span id="emailCheck"></span>
                </div>
            </div>
            <button type="submit" id="userEmailEditButton" class="btn btn-neutral btn-heart c_button"><i class="fas fa-edit"></i></button>
        </form:form>

        <%-- 비밀번호 수정 --%>
        <form:form method="post" modelAttribute="user" action="${editPasswordUri}" novalidate="true">
            <div class="materialGroup">
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
            </div>
            <button type="submit" id="userPasswordEditButton" class="btn btn-neutral btn-heart c_button"><i class="fas fa-edit"></i></button>
        </form:form>

        <%-- 이름 수정 --%>
        <form:form method="post" modelAttribute="user" action="${editNameUri}" novalidate="true">
            <div class="materialGroup">
                <div id="c_joinFormName" class="form-group">
                    <form:input id="name" name="name" path="name" type="text" value="${name}" required="required" maxlength="5" onblur="nameCheck()"/>
                    <span class="highlight"></span>
                    <span class="bar"></span>
                    <label for="name">* 이름</label>

                    <form:errors id="nameValidation" path="name"/>
                    <span id="nameCheck"></span>
                </div>
            </div>
            <button type="submit" id="userNameEditButton" class="btn btn-neutral btn-heart c_button"><i class="fas fa-edit"></i></button>
        </form:form>

        <%-- 닉네임 수정 --%>
        <form:form method="post" modelAttribute="user" action="${editNicknameUri}" novalidate="true">
            <div class="materialGroup">
                <div class="form-group">
                    <form:input id="nickname" name="nickname" path="nickname" type="text" value="${nickname}" required="required" maxlength="10" onblur="nicknameCheck()"/>
                    <span class="highlight"></span>
                    <span class="bar"></span>
                    <label for="nickname">* 닉네임</label>

                    <form:errors id="nicknameValidation" path="nickname"/>
                    <span id="nicknameCheck"></span>
                </div>
            </div>
            <button type="submit" id="userNicknameEditButton" class="btn btn-neutral btn-heart c_button"><i class="fas fa-edit"></i></button>
        </form:form>

        <%-- 연락처 수정 --%>
        <form:form method="post" modelAttribute="user" action="${editPhoneUri}" novalidate="true">
            <div class="materialGroup">
                <div class="form-group">
                    <form:input id="phone" name="phone" path="phone" type="text" value="${phone}" required="required" maxlength="11" onblur="phoneCheck()"/>
                    <span class="highlight"></span>
                    <span class="bar"></span>
                    <label for="phone">* 연락처</label>

                    <form:errors id="phoneValidation" path="phone"/>
                    <span id="phoneCheck"></span>
                </div>
            </div>
            <button type="submit" id="userPhoneEditButton" class="btn btn-neutral btn-heart c_button"><i class="fas fa-edit"></i></button>
        </form:form>
    </div>

    <jsp:include page="/WEB-INF/views/component/bootstrapAlertMessage.jsp">
        <jsp:param name="title" value="수정"/>
    </jsp:include>
</div>