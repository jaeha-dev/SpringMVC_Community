<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<head>
    <link href="/resources/css/chat/room/list.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet">
    <script src="/resources/js/chat/room/list.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
</head>

<div id="c_roomList" class="container c_container-xl">
    <div class="container c_headerLine">
    </div>
    <div class="container c_header">
        <h5>채팅방</h5>
        <p>채팅에 참여하거나 채팅방을 개설할 수 있습니다.</p>
    </div>
    <br/>

    <div id="c_roomListBody" class="container">
        <table class="table table-hover">
            <thead>
            <tr id="c_roomListItemHeader">
                <th id="c_roomListItemId" style="width: 10%">번호</th>
                <th id="c_roomListItemName" style="width: 40%">이름</th>
                <th id="c_roomListItemUserNickname" style="width: 10%">방장</th>
                <th id="c_roomListItemCreatedAt" style="width: 20%">개설일</th>
                <th id="c_roomListItemMaximum" style="width: 10%">정원</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="room" items="${roomList}">
                <tr class="c_roomListItem" onclick="roomOpen(${room.id})">
                    <td>${room.id}</td>
                    <td>${room.name}</td>
                    <td>${room.userNickname}</td>
                    <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${room.createdAt}"/></td>
                    <td>${room.maximum}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="/WEB-INF/views/chat/room.jsp"/>
<jsp:include page="/WEB-INF/views/chat/room/page.jsp"/>