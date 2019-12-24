$(document).ready(function () {
    // 채팅방
    var chatRoomBody = document.querySelector('#c_roomBody');
    var chatRoomClose = document.querySelector("#c_roomClose");
    // 채팅 메시지
    var connectionElement = document.querySelector('#c_roomConnection');
    var messageArea = document.querySelector('#c_roomMessageArea');
    var messageForm = document.querySelector('#c_roomMessageForm');
    var messageInput = document.querySelector('#c_roomMessageInput');
    // 채팅 정보
    var roomId = null;
    var stompClient = null;
    var userNickname = null;

    // 입장 이벤트 바인딩
    $('#c_room').on('shown.bs.modal', function (event) {
        connect(event);
        return true;
    });
    // 채팅 메시지 이벤트 바인딩
    messageForm.addEventListener('submit', sendMessage, true);
    // 퇴장 이벤트 바인딩
    chatRoomClose.addEventListener('click', disconnect, true);

    // 연결: 웹 소켓을 통해 서버에 접속한다.
    function connect(event) {
        userNickname = $('#c_roomUserNickName').text();
        roomId = $('#c_roomId').text();

        if (userNickname) {
            var socket = new SockJS('/ws-stomp'); // 클라이언트 소켓 생성
            stompClient = Stomp.over(socket);
            stompClient.connect({}, onConnected, onError);
        }
        event.preventDefault(); // 이벤트의 고유 기본 기능 비활성화 후, 자바스크립트 코드 수행
    }

    // 연결: 생성된 클라이언트 소켓은 토픽을 구독한다.
    function onConnected() {
        stompClient.subscribe('/topic/chat/room/' + roomId, onMessageReceived);
        var chatMessage = {
            roomId: roomId,
            userNickname: userNickname,
            type: 'JOIN'
        };
        stompClient.send('/app/chat/room/' + roomId, {}, JSON.stringify(chatMessage)); // 입장 메시지 전송
        connectionElement.style.display='none'; // 연결 중 메시지 감춤
    }

    // 연결 해제
    function disconnect() {
        var chatMessage = {
            roomId: roomId,
            userNickname: userNickname,
            type: 'QUIT'
        };
        stompClient.send('/app/chat/room/' + roomId, {}, JSON.stringify(chatMessage)); // 퇴장 메시지 전송
        stompClient.disconnect();
    }

    // 연결 에러 (채팅 중, 서버가 종료되었을 경우 출력된다.)
    function onError(error) {
        connectionElement.textContent = '[알림] 웹 소켓 서버에 연결할 수 없습니다.';
        connectionElement.style.display = 'block';
    }

    // 메시지 전송
    function sendMessage(event) {
        var messageContent = messageInput.value.trim();

        if (messageContent && stompClient) {
            var chatMessage = {
                roomId: roomId,
                userNickname: userNickname,
                content: messageInput.value,
                type: 'TALK'
            };
            stompClient.send('/app/chat/room/' + roomId, {}, JSON.stringify(chatMessage)); // 채팅 메시지 전송
            messageInput.value = ''; // 입력 메시지 초기화
        }
        event.preventDefault();
    }

    // 메시지 수신
    function onMessageReceived(payload) {
        var message = JSON.parse(payload.body); // Payload(실제 데이터)
        var messageElement = document.createElement('li'); // 메시지를 위한 HTML 요소 생성

        if (message.type === 'JOIN') {
            messageElement.classList.add('joinMessage'); // HTML class 속성 추가
            message.content = message.userNickname + " " + message.content;
        } else if (message.type === 'QUIT') {
            messageElement.classList.add('quitMessage');
            message.content = message.userNickname + " " + message.content;
        } else if (message.type === 'TALK') {
            if (message.userNickname === userNickname) { // 메시지 발신자 판별
                messageElement.classList.add('myMessage');
            } else {
                messageElement.classList.add('otherMessage');
            }

            var userNicknameElement = document.createElement('span'); // 메시지 발신자를 위한 HTML 요소 생성
            var userNicknameText = document.createTextNode(message.userNickname); // 선택 요소에 텍스트 추가
            userNicknameElement.appendChild(userNicknameText); // 선택 요소 내부에 자식 요소 추가
            messageElement.appendChild(userNicknameElement);

            // <li class="myMessage">
            //     <span>발신자</span>
            // </li>
        }

        var textElement = document.createElement('p'); // 메시지 내용을 위한 HTML 요소 생성
        var messageText = document.createTextNode(message.content);
        textElement.classList.add('message');
        textElement.appendChild(messageText);
        messageElement.appendChild(textElement); // li 요소의 자식으로 추가
        messageArea.appendChild(messageElement);

        chatRoomBody.scrollTop = chatRoomBody.scrollHeight; // Auto scroll
        chatRoomBody.animate({scrollTop: chatRoomBody.scrollHeight}); // Auto scroll
        // <li class="myMessage">
        //     <span>발신자</span>
        //     <p class="message">메시지</p>
        // </li>
    }
});