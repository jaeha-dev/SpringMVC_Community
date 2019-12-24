<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<head>
    <title>계정 생성</title>
    <link href="/resources/css/user/consent.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet" >
    <script src="/resources/js/user/consent.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
</head>

<spring:url var="homeUri" value="/"/>

<div id="c_joinConsent" class="container c_container-sm">

    <%-- 계정 등록 이용약관 헤더 영역 --%>
    <div class="container c_headerLine">
    </div>
    <div class="container c_header">
        <h5>계정 등록</h5>
        <p>계정을 등록하시면 각종 혜택 및 정보를 제공 받으실 수 있습니다.</p>
    </div>

    <div class="d-flex justify-content-center c_step">
        <ul>
            <li class="active">
                <p><strong>STEP 1</strong></p>
                <p>이용 약관 동의</p>
            </li>
            <li>
                <p><strong>STEP 2</strong></p>
                <p>계정 정보 입력</p>
            </li>
            <li>
                <p><strong>STEP 3</strong></p>
                <p>계정 등록 완료</p>
            </li>
        </ul>
    </div>

    <%-- 계정 등록 이용약관 컨텐트 영역 --%>
    <div class="container">
        <ul>
            <li class="textarea">
                <div class="form-group">
                    <label for="c_joinConsentTermTextarea">계정 등록 이용약관</label>
                    <textarea id="c_joinConsentTermTextarea" class="form-control" readonly="readonly" rows="10">
계정 등록 이용 약관입니다.
                    </textarea>
                </div>
            </li>
            <li>
                <div class="form-check form-check-radio">
                    <label class="form-check-label">
                        <input id="c_joinConsentCheck1" class="form-check-input" type="radio" name="c_joinTermRadio" value="c_joinTermOk" >
                        <span class="form-check-sign"></span>
                        동의합니다.
                    </label>
                    <label class="form-check-label">
                        <input id="c_joinConsentCheck2" class="form-check-input" type="radio" name="c_joinTermRadio" value="c_joinTermNo" checked>
                        <span class="form-check-sign"></span>
                        동의하지 않습니다.
                    </label>
                </div>
            </li>
        </ul>
    </div>

    <div class="container">
        <ul>
            <li class="textarea">
                <div class="form-group">
                    <label for="c_joinConsentPrivacyTextarea">개인정보 수집 및 이용</label>
                    <textarea id="c_joinConsentPrivacyTextarea" class="form-control" readonly="readonly" rows="10">
개인정보 수집 및 이용입니다.
                    </textarea>
                </div>
            </li>
            <li>
                <div class="form-check form-check-radio">
                    <label class="form-check-label">
                        <input id="c_joinConsentCheck3" class="form-check-input" type="radio" name="c_joinPrivacyRadio" value="c_joinPrivacyOk" >
                        <span class="form-check-sign"></span>
                        동의합니다.
                    </label>
                    <label class="form-check-label">
                        <input id="c_joinConsentCheck4" class="form-check-input" type="radio" name="c_joinPrivacyRadio" value="c_joinPrivacyNo" checked>
                        <span class="form-check-sign"></span>
                        동의하지 않습니다.
                    </label>
                </div>
            </li>
        </ul>
    </div>

    <button type="button" class="btn btn-neutral c_button" onclick="joinContinue()"><i class="fas fa-angle-right"></i> 다음</button>
    <button type="button" class="btn btn-neutral btn-heart c_button" onclick="location.href='${homeUri}'"><i class="fas fa-angle-right"></i> 취소</button>
</div>