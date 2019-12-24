$(document).ready(function() {
    $('#c_room').on('hidden.bs.modal', function () {
        window.location.reload();
    })
});

// 채팅방 상세 조회
function roomOpen(id) {
    $.ajax({
        type: 'get',
        url: '/chat/room/' + id,
        data: {id: id},
        success: function (result) {
            $('#c_roomId').text(result.room.id);
            $('#c_roomName').text('“ ' + result.room.name + ' ”');
            $('#c_roomIcon').tooltip().attr('data-original-title', '채팅방 이름: ' + result.room.name);
            // 보안상 서버에서 닉네임만 전송한다.
            // $('#c_roomUserNickName').text(result.user.nickname);
            $('#c_roomUserNickName').text(result.userNickname);
            $('#c_room').modal('show');
        }
    })
}

// 채팅방 수정 폼
function roomEdit() {
    var id = $('#c_roomId').text(); // 채팅방 번호

    $.ajax({
        type: 'post',
        url: '/chat/room/' + id + '/check', // 요청 서버 URL
        data: {id: id},
        success: function (result) {
            if (result === 1) {
                window.location.href = '/chat/room/' + id + '/edit';

            } else {
                alert('채팅방 수정 권한이 없습니다.');
            }
        }
    });

    // $.ajax({
    //     type: 'get',
    //     url: '/chat/room/' + roomId + '/edit',
    //     data: JSON.stringify(data),
    //     contentType: "application/json; charset=utf-8;",
    //     dataType: "json",
    //
    //     success: function (result) {
    //         if (result === 1) {
    //
    //         } else if (result === 0) {
    //             alert('채팅방 수정 권한이 없습니다.');
    //         }
    //     }
    // })
}

// 채팅방 삭제 검사
function roomDeleteCheck() {
    var id = $('#c_roomId').text(); // 채팅방 번호

    $.ajax({
        type: 'post',
        url: '/chat/room/' + id + '/check', // 요청 서버 URL
        data: {id: id},
        success: function (result) {
            if (result === 1) {
                roomDelete(id);

            } else {
                alert('채팅방 삭제 권한이 없습니다.');
            }
        }
    });

    // $.ajax({
    //     type: 'post',
    //     url: '/chat/room/' + roomId + '/delete',
    //     data: JSON.stringify(data),
    //     contentType: "application/json; charset=utf-8;",
    //     dataType: "json",
    //
    //     success: function (result) {
    //         if (result === 1) {
    //             $('#c_room').modal("hide");
    //             alert('채팅방이 삭제되었습니다.');
    //
    //         } else if (result === 0) {
    //             alert('채팅방 개설자만 삭제할 수 있습니다.');
    //         }
    //     }
    // });
}

function roomDelete(id) {
    $.ajax({
        type: 'post',
        url: '/chat/room/' + id + '/delete', // 요청 서버 URL

        success: function (result) {
            if (result === 1) {
                alert('채팅방이 삭제되었습니다.');
                window.location.href = '/chat/room/list';

            } else {
                alert('채팅방 삭제 권한이 없습니다.');
            }
        }
    });
}