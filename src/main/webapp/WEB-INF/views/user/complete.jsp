<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<head>
    <title>계정 생성</title>
    <link href="/resources/css/user/complete.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet">
</head>

<spring:url var="joinCompleteUri" value="/"/>

<div id="c_joinComplete" class="container c_container-sm">
    <div class="container c_headerLine">
    </div>
    <div class="container c_header">
        <h5>등록 완료</h5>
        <p>계정 등록이 완료되었습니다.</p>
    </div>

    <div class="d-flex justify-content-center c_step">
        <ul>
            <li>
                <p><strong>STEP 1</strong></p>
                <p>이용 약관 동의</p>
            </li>
            <li>
                <p><strong>STEP 2</strong></p>
                <p>계정 정보 입력</p>
            </li>
            <li class="active">
                <p><strong>STEP 3</strong></p>
                <p>계정 등록 완료</p>
            </li>
        </ul>
    </div>

    <div class="container">
        <div class="form-group">
            <label for="c_joinCompleteTextarea">계정 등록 완료</label>
            <textarea id="c_joinCompleteTextarea" class="form-control" readonly="readonly" rows="10">
계정 등록이 완료되었습니다.

로그인하여 서비스를 이용해주세요.
            </textarea>
        </div>
    </div>

    <button type="button" class="btn btn-neutral c_button" onclick="location.href='${joinCompleteUri}'"><i class="fas fa-angle-right"></i> 완료</button>
</div>