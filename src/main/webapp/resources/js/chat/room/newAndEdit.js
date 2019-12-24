// 채팅방 이름 중복 검사
function roomNameCheck() {
    var name    = $('#name').val(); // 사용자 입력값
    var regexp  = /^[\w\Wㄱ-ㅎㅏ-ㅣ가-힣]{2,100}$/; // 채팅방 이름 정규식
    var data    = {name: name};

    $.ajax({
        type: 'post',
        url: '/chat/room/name/check',
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8;",
        dataType: "json",

        success: function (result) {
            if (regexp.test(name) && result === 0) {
                $('#roomNameCheck').html("사용할 수 있는 채팅방 이름입니다.").css('color', '#3A81E9');
            } else if (! regexp.test(name)) {
                $('#roomNameCheck').html("채팅방 이름 형식이 맞지 않습니다.").css('color', '#FF3636');
            } else {
                $('#roomNameCheck').html("사용할 수 없는 채팅방 이름입니다.").css('color', '#FF3636');
            }
            $('#roomNameValidation').hide();
        }
    })
}

// 채팅방 정원 검사
function maximumCheck() {
    var maximum   = $('#maximum').val(); // 사용자 입력값
    var regexp    = /^([2-9]|10)$/; // 채팅방 제한 인원 정규식

    if (regexp.test(maximum)) {
        $('#roomMaximumCheck').html('채팅방 제한 인원을 만족합니다.').css('color', '#3A81E9');
    } else {
        $('#roomMaximumCheck').html("채팅방 인원은 2~10명까지 지정할 수 있습니다.").css('color', '#FF3636');
    }
    $('#roomMaximumValidation').hide();
}

// 채팅방 정원 입력창 포커스
function inputFocus() {
    $('#maximum').focus();
    // $('#c_roomNewMaximum').focus();
    maximumCheck();
}

$(document).ready(function () {
    $(".c_roomNewMaximum").inputCounter({
        selectors: {
            addButtonSelector: '.btn-add',
            subtractButtonSelector: '.btn-sub',
            inputSelector: '.input-counter',
        },
        settings: {
            checkValue: false,
            isReadOnly: false
        }
    });

    $(".c_roomEditMaximum").inputCounter({
        selectors: {
            addButtonSelector: '.btn-add',
            subtractButtonSelector: '.btn-sub',
            inputSelector: '.input-counter',
        },
        settings: {
            checkValue: false,
            isReadOnly: false
        }
    });
});