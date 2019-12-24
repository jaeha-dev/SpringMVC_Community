// 초기 글자수 지정
$(document).ready(function() {
    var length = document.getElementById("title").value.length;
    $('#titleCounter').html(" (" + length + " / 100자)");
});

// 제목 검사
function titleCheck() {
    var length = document.getElementById("title").value.length;

    $('#titleCounter').html(" (" + length + " / 100자)");

    if (length >= 100) {
        $('#titleCheck').show().html("제목은 한글/영문 100자로 제한됩니다.").css('color', '#FF3636');
    } else if (length < 100) {
        $('#titleCheck').hide();
    }
}

// 제목 에러 메시지 감추기
// function titleError() {
//     $('#titleValidation').hide();
// }

// 내용 에러 메시지 감추기
// function contentError() {
//     $('#contentValidation').hide();
// }