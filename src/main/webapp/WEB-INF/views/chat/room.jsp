<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<head>
    <link href="/resources/css/chat/room/room.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet">
    <script src="/resources/js/chat/room/room.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
</head>

<div id="c_room" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="c_postModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
        <div class="modal-content">
            <div id="c_roomHeader" class="modal-header">
                <button type="button" id="c_roomClose" class="close" data-dismiss="modal" aria-label="Close" rel="tooltip" title="" data-placement="top" data-original-title="채팅방 나가기" onclick="javascript:window.location.reload()">
                    <span class="modalClose" aria-hidden="true">&times;</span>
                </button>

                <%-- 스택 아이콘 (https://fontawesome.com/how-to-use/on-the-web/styling/stacking-icons) --%>
                <span id="c_roomIcon" class="fa-stack fa-1x" data-toggle="tooltip" data-placement="top" title="0">
                    <i class="fas fa-circle fa-stack-2x"></i>
                    <i class="fas fa-comment-dots fa-stack-1x fa-inverse"></i>
                </span>

                <h5 id="c_roomName">0</h5>
                <p id="c_roomId" hidden="hidden">0</p>
                <p id="c_roomUserNickName" hidden="hidden">0</p>
                <div id="c_roomButtonGroup">
                    <button id="c_roomEditButton" type="button" class="btn btn-neutral btn-sm btn-font-sm pull-center" onclick="roomEdit()">
                        <i class="fas fa-angle-right"></i> 수정</button>
                    <button id="c_roomDeleteButton" type="button" class="btn btn-heart btn-neutral btn-sm btn-font-sm pull-center" onclick="roomDeleteCheck()">
                        <i class="fas fa-angle-right"></i> 삭제</button>
                </div>
            </div>

            <div id="c_roomBody" class="modal-body">
                <div class="chat-container">
                    <ul id="c_roomMessageArea"></ul>
                    <div id="c_roomConnection">연결 중...</div>
                </div>
            </div>

            <div id="c_roomFooter" class="modal-footer">
                <form id="c_roomMessageForm" name="messageForm" nameForm="messageForm">
                    <div class="form-group">
                        <input id="c_roomMessageInput" class="form-control" type="text" aria-label="message" placeholder="메시지를 입력하세요." autocomplete="off"/>
                    </div>
                    <div class="form-group">
                        <button id="c_roomMessageButton" type="submit" class="btn btn-primary"><i class="fas fa-paper-plane"></i></button>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>

<script src="/resources/js/chat/room/chat.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>