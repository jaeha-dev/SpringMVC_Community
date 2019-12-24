// 모달 닫기 후 새로고침
$(document).ready(function() {
    $('#c_commentEdit').on('hidden.bs.modal', function () {
        window.location.reload();
    })
});

// 댓글 등록 내용 검사
function newContentCheck() {
    var length = document.getElementById("newContent").value.length;

    $('#newContentCounter').html("(" + length + " / 100자)");

    if (length >= 100) {
        $('#newContentCheck').show().html("댓글은 한글/영문 100자로 제한됩니다.").css('color', '#FF3636');
    } else if (length <= 100) {
        $('#newContentCheck').hide();
    }
}

// 댓글 수정 내용 검사
function editContentCheck() {
    var length = document.getElementById("editContent").value.length;

    $('#editContentCounter').html("(" + length + " / 100자)");

    if (length >= 100) {
        $('#editContentCheck').show().html("댓글은 한글/영문 100자로 제한됩니다.").css('color', '#FF3636');
    } else if (length <= 100) {
        $('#editContentCheck').hide();
    }
}

// 내용 에러 메시지 감추기
function newContentError() {
    $('#newContentValidation').hide();
}
function editContentError() {
    $('#editContentValidation').hide();
}

// 댓글 비우기 후 글자수, 에러 초기화
function newCommentClear() {
    $('#newContentCounter').html("(0 / 100자)");
    $('#newContentCheck').hide();
    $('#newContentValidation').hide();
}
function editCommentClear() {
    $('#editContent').text('');
    $('#editContentCounter').html("(0 / 100자)");
    $('#editContentCheck').hide();
    $('#editContentValidation').hide();
}

// 댓글 수정
function commentEdit() {
    var id =  $('#c_commentEdit #commentId').val();
    var postId = $('#c_commentEdit #postId').val();
    var content = $('#c_commentEdit #editContent').val();
    var data = {postId: postId, id: id, content: content};

    $.ajax({
        type: 'post',
        url: '/board/comment/' + id + '/edit', // 요청 서버 URL
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8;",
        dataType: "json",

        success: function (result) {
            if (result.result === 0) {
                // 수정 실패
                $('#editContentValidation').show().text(result.error);

            } else if (result.result === 1) {
                $('#c_commentEdit').modal('hide');
            }
        }
    });
}