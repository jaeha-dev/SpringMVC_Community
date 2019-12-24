<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<head>
    <link href="/resources/css/chat/room/newAndEdit.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet">
    <script src="/resources/js/chat/room/newAndEdit.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
</head>

<spring:url var="roomNewUri" value="/chat/room/new"/>
<spring:url var="roomListUri" value="/chat/room/list"/>

<div id="c_roomNewContainer" class="container">
    <div id="c_roomNewHeaderLine" class="container">
    </div>
    <div id="c_roomNewHeader" class="container">
        <h5>채팅방 개설</h5>
        <p>채팅방을 개설할 수 있습니다.</p>
    </div>

    <div id="c_roomNew" class="container">
        <form:form id="c_roomNewForm" method="post" modelAttribute="chatRoom" action="${roomNewUri}" novalidate="true">
            <div class="materialGroup">
                <p id="c_roomNewFormHeader">채팅방 정보</p>
                <div class="form-group">
                    <form:input id="name" name="name" path="name" type="text" value="${name}" required="required" maxlength="50" onblur="roomNameCheck()"/>
                    <span class="highlight"></span>
                    <span class="bar"></span>
                    <label for="name">* 채팅방 이름</label>

                    <form:errors id="roomNameValidation" path="name"/>
                    <span id="roomNameCheck"></span>
                </div>

                <div class="form-group c_roomNewMaximum">
                    <form:input id="maximum" class="input-counter" name="maximum" path="maximum" type="text" data-min="2" data-max="10" data-default="2" required="required" onblur="maximumCheck()" />
                    <span class="highlight"></span>
                    <span class="bar"></span>
                    <label id="c_roomNewMaximum" for="maximum">* 채팅방 인원</label>
                    <button id="c_roomSub" class="btn btn-heart btn-neutral btn-sm btn-font-sm btn-sub" type="button" onclick="inputFocus()"><i class="fas fa-minus"></i></button>
                    <button id="c_roomAdd" class="btn btn-neutral btn-sm btn-font-sm btn-add" type="button" onclick="inputFocus()"><i class="fas fa-plus"></i></button>

                    <form:errors id="roomMaximumValidation" path="maximum"/>
                    <span id="roomMaximumCheck"></span>
                </div>
            </div>

            <jsp:include page="/WEB-INF/views/component/bootstrapAlertMessage.jsp">
                <jsp:param name="title" value="등록"/>
            </jsp:include>

            <button id="c_roomNewCompleteButton" type="submit" class="btn btn-neutral btn-sm btn-font-sm"><i class="fas fa-angle-right"></i> 개설</button>
            <button id="c_roomNewCancelButton" type="button" class="btn btn-neutral btn-heart btn-sm btn-font-sm" onclick="location.href='${roomListUri}'"><i class="fas fa-angle-right"></i> 취소</button>
        </form:form>
    </div>

    <div id="c_roomNewFormFooter" class="container">
        <%-- 태그 목록--%>
    </div>
</div>