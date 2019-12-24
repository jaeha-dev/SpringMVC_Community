$(document).ready(function () {

});

// 이메일 검사
function emailCheck() {
    var email   = $('#email').val(); // 사용자 입력값
    var regexp  = /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/; // 이메일 정규식
    var data    = {email: email};

    console.log(data);
    console.log(JSON.stringify(data));
    $.ajax({
        type: 'post',
        url: '/user/email/check',
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8;",
        dataType: "json",

        success: function (result) {
            if (regexp.test(email) && result === 0) {
                $('#emailCheck').html("사용 가능한 이메일입니다.").css('color', '#3A81E9');
            } else if (! regexp.test(email)) {
                $('#emailCheck').html("이메일 형식이 맞지 않습니다.").css('color', '#FF3636');
            } else {
                $('#emailCheck').html("사용할 수 없는 이메일입니다.").css('color', '#FF3636');
            }
            $('#emailValidation').hide();
        }
    })

    // // POST, MIME 타입 전송 시 (JSON 변환만 없다.)
    // var email   = $('#email').val(); // 사용자 입력값
    // var regexp  = /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/; // 이메일 정규식
    // var data    = {email: email};
    //
    // $.ajax({
    //     type: 'post',
    //     url: '/user/email/check',
    //
    //     success: function (result) {
    //         if (regexp.test(email) && result === 0) {
    //             $('#emailCheck').html("사용 가능한 이메일입니다.").css('color', '#3A81E9');
    //         } else if (! regexp.test(email)) {
    //             $('#emailCheck').html("이메일 형식이 맞지 않습니다.").css('color', '#FF3636');
    //         } else {
    //             $('#emailCheck').html("사용할 수 없는 이메일입니다.").css('color', '#FF3636');
    //         }
    //         $('#emailValidation').hide();
    //     }
    // })
}

// 비밀번호 검사 및 확인
function passwordCheck() {
    var password        = $('#password').val(); // 사용자 입력값
    var passwordConfirm = $('#passwordConfirm').val(); // 사용자 재입력값
    var regexp          = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,16}$/; // 비밀번호 정규식

    if (regexp.test(password) && password === passwordConfirm) {
        $('#passwordCheck').html("비밀번호가 일치합니다.").css('color', '#3A81E9');
    } else if (! regexp.test(password)) {
        $('#passwordCheck').html("8~15자의 영문 대 소문자, 숫자, 특수문자를 최소 1개씩 사용하세요.").css('color', '#FF3636');
    } else {
        $('#passwordCheck').html("비밀번호가 일치하지 않습니다.").css('color', '#FF3636');
    }
    $('#passwordValidation').hide();
}

// 이름 검사
function nameCheck() {
    var name    = $('#name').val(); // 사용자 입력값
    var regexp  = /^[가-힣]{2,5}$/; // 이름 정규식

    if (regexp.test(name)) {
        $('#nameCheck').html("사용 가능한 이름입니다.").css('color', '#3A81E9');
    } else {
        $('#nameCheck').html("2~5자의 한글만 사용 가능합니다.").css('color', '#FF3636');
    }
    $('#nameValidation').hide();
}

// 연락처 검사
function phoneCheck() {
    var phone   = $('#phone').val(); // 사용자 입력값
    var regexp  = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/; // 연락처 정규식
    var data    = {phone: phone};

    $.ajax({
        type: 'post',
        url: '/user/phone/check',
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8;",
        dataType: "json",

        success: function (result) {
            if (regexp.test(phone) && result === 0) {
                $('#phoneCheck').html("사용 가능한 핸드폰 번호입니다.").css('color', '#3A81E9');
            } else if (! regexp.test(phone)) {
                $('#phoneCheck').html("핸드폰 번호 형식이 맞지 않습니다.").css('color', '#FF3636');
            } else {
                $('#phoneCheck').html("사용할 수 없는 핸드폰 번호입니다.").css('color', '#FF3636');
            }
            $('#phoneValidation').hide();
        }
    })
}

// 닉네임 검사
function nicknameCheck() {
    var nickname  = $('#nickname').val(); // 사용자 입력값
    var regexp    = /^[가-힣a-zA-Z0-9]{2,10}$/; // 닉네임 정규식
    var data      = {nickname: nickname};

    $.ajax({
        type: 'post',
        url: '/user/nickname/check',
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8;",
        dataType: "json",

        success: function (result) {
            if (nickname !== '익명' && regexp.test(nickname) && result === 0) {
                $('#nicknameCheck').html("사용 가능한 닉네임입니다.").css('color', '#3A81E9');
            } else if (nickname === '익명') {
                $('#nicknameCheck').html(nickname + "은 사용할 수 없는 닉네임입니다.").css('color', '#FF3636');
            } else if (! regexp.test(nickname)) {
                $('#nicknameCheck').html("닉네임 형식이 맞지 않습니다.").css('color', '#FF3636');
            } else {
                $('#nicknameCheck').html("사용할 수 없는 닉네임입니다.").css('color', '#FF3636');
            }
            $('#nicknameValidation').hide();
        }
    })
}