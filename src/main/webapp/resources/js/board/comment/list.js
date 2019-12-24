// 댓글 수정 검사
function commentEditCheck(postId, id) {
    $.ajax({
        type: 'post',
        url: '/board/comment/' + id + '/check', // 요청 서버 URL

        success: function (result) {
            if (result === 1) {
                commentEditForm(postId, id)

            } else if (result === 0) {
                alert('댓글 수정 권한이 없습니다.');
            }
        }
    });
}

// 댓글 수정 폼
function commentEditForm(postId, id) {
    $.ajax({
        type: 'get',
        url: '/board/comment/' + id + '/edit', // 요청 서버 URL

        success: function (result) {
            $('#c_commentEdit #editContent').text(result.comment.content);
            $('#c_commentEdit #editContentCounter').text('(' + result.comment.content.length + '/ 100자)');
            $('#c_commentEdit #postId').val(result.comment.postId);
            $('#c_commentEdit #commentId').val(result.comment.id);
            $('#c_commentEdit').modal('show');
        }
    });
}

// 댓글 삭제 검사
function commentDeleteCheck(postId, id) {
    $.ajax({
        type: 'post',
        url: '/board/comment/' + id + '/check', // 요청 서버 URL

        success: function (result) {
            if (result === 1) {
                commentDelete(postId, id);

            } else {
                alert('댓글 삭제 권한이 없습니다.');
            }
        }
    });
}

// 댓글 삭제
function commentDelete(postId, id) {
    var data = {postId: postId};

    $.ajax({
        type: 'post',
        url: '/board/comment/' + id + '/delete', // 요청 서버 URL
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8;",
        dataType: "json",
        
        success: function (result) {
            if (result === 1) {
                alert('댓글이 삭제되었습니다.');
                window.location.href = '/board/post/' + postId;

            } else {
                alert('댓글 삭제 권한이 없습니다.');
            }
        }
    });
}

// 댓글 추천
function commentLike(postId, commentId) {
    var data = {postId: postId, commentId: commentId};
    var commentItemId = $('#c_commentListItem_' + commentId);
    var commentLikes = commentItemId.find('.commentLikes').text();

    $.ajax({
        type: 'post',
        url: '/board/comment/' + commentId + '/like', // 요청 서버 URL
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8;",
        dataType: "json",

        success: function (result) {
            if (result === 0) {
                // 댓글 추천
                commentItemId.find('.commentLikeButton').removeClass("far").addClass("fas");
                commentItemId.find('.commentLikes').text(parseInt(commentLikes) + 1);

            } else if (result === 1) {
                // 댓글 추천 취소
                commentItemId.find('.commentLikeButton').removeClass("fas").addClass("far");
                commentItemId.find('.commentLikes').text(parseInt(commentLikes) - 1);
            }
        }
    });
}