
console.log($('#c_postLikesButton').attr('value'));

// URI 복사
function isIe() {
    if (navigator.userAgent.toLowerCase().indexOf("chrome") !== -1) return false;
    if (navigator.userAgent.toLowerCase().indexOf("msie") !== -1) return true;
    return navigator.userAgent.toLowerCase().indexOf("windows nt") !== -1;
}
function copyUri() {
    var uri = document.location.href;

    console.log(uri);
    if (isIe()) {
        window.clipboardData.setData("Text", uri);
        alert("URL이 복사되었습니다.");
        return;
    }
    prompt("Ctrl + C를 눌러 복사하세요.", uri);
}

// 게시글 수정 폼
function postEdit(id) {
    $.ajax({
        type: 'post',
        url: '/board/post/' + id + '/check', // 요청 서버 URL

        success: function (result) {
            if (result === 1) {
                window.location.href = '/board/post/' + id + '/edit';
            } else {
                alert('게시글 수정 권한이 없습니다.');
            }
        }
    });

    // AJAX 호출 (JSON 데이터 전송 시)
    // var data = {id: id};
    //
    // $.ajax({
    //     type: 'post',
    //     url: '/board/post/' + id + '/check', // 요청 서버 URL
    //     data: JSON.stringify(data),
    //     contentType: "application/json; charset=utf-8;",
    //     dataType: "json",
    //
    //     success: function (result) {
    //         if (result === 1) {
    //             window.location.href = '/board/post/' + id + '/edit';
    //         } else {
    //             alert('게시글 수정 권한이 없습니다.');
    //         }
    //     }
    // });

    // AJAX 호출 (MIME 데이터 전송 시)
    // var id      = id; // 게시글 번호
    // var url     = '/board/post/' + id + '/check'; // 요청 서버 URL
    // var type    = 'post'; // 요청 메소드
    // console.log(url);
    //
    // $.ajax({
    //     type: type,
    //     url: url,
    //     data: {id: id},
    //     success: function (result) {
    //         if (result === 1) {
    //             window.location.href = '/board/post/' + id + '/edit';
    //         } else {
    //             alert('게시글 수정 권한이 없습니다.');
    //         }
    //     }
    // })
}

// 게시글 삭제 검사
function postDeleteCheck(id) {
    $.ajax({
        type: 'post',
        url: '/board/post/' + id + '/check', // 요청 서버 URL

        success: function (result) {
            if (result === 1) {
                postDelete(id);
            } else {
                alert('게시글 삭제 권한이 없습니다.');
            }
        }
    });

    // AJAX 호출 (JSON 데이터 전송 시)
    // var data = {id: id};
    //
    // $.ajax({
    //     type: 'post',
    //     url: '/board/post/' + id + '/check', // 요청 서버 URL
    //     data: JSON.stringify(data),
    //     contentType: "application/json; charset=utf-8;",
    //     dataType: "json",
    //
    //     success: function (result) {
    //         if (result === 1) {
    //             window.location.href = '/board/post/' + id + '/delete';
    //         } else {
    //             alert('게시글 삭제 권한이 없습니다.');
    //         }
    //     }
    // });

    // AJAX 호출 (MIME 데이터 전송 시)
    // var id      = id; // 게시글 번호
    // var url     = '/board/post/' + id + '/check'; // 요청 서버 URL
    // var type    = 'post'; // 요청 메소드
    // console.log(url);
    //
    // $.ajax({
    //     type: type,
    //     url: url,
    //     data: {id: id},
    //     success: function (result) {
    //         if (result === 1) {
    //             window.location.href = '/board/post/' + id + '/delete';
    //         } else {
    //             alert('게시글 삭제 권한이 없습니다.');
    //         }
    //     }
    // });
}

// 게시글 삭제
function postDelete(id) {
    $.ajax({
        type: 'post',
        url: '/board/post/' + id + '/delete', // 요청 서버 URL

        success: function (result) {
            if (result === 1) {
                alert('게시글이 삭제되었습니다.');
                window.location.href = '/board/post/list';

            } else {
                alert('게시글 삭제 권한이 없습니다.');
            }
        }
    });
}

// 게시글 추천
function postLike(id) {
    var postLikes = $('.postLikes').text();

    $.ajax({
        type: 'post',
        url: '/board/post/' + id + '/like', // 요청 서버 URL

        success: function (result) {
            // if (result === 0 || result === 1) {
            //     $('.c_postLikeButton').toggleClass("is-active");
            // }
            if (result === 0) {
                // 게시글 추천
                $('.c_postLikeButton').toggleClass("is-active");
                $('.postLikes').text(parseInt(postLikes) + 1);
            } else if (result === 1) {
                // 게시글 추천 취소
                $('.c_postLikeButton').toggleClass("is-active");
                $('.postLikes').text(parseInt(postLikes) - 1);
            }
        }
    });

    // AJAX 호출 (JSON 데이터 전송 시)
    // var data = {id: id};
    //
    // $.ajax({
    //     type: 'post',
    //     url: '/board/post/' + id + '/like', // 요청 서버 URL
    //     data: JSON.stringify(data),
    //     contentType: "application/json; charset=utf-8;",
    //     dataType: "json",
    //
    //     success: function (result) {
    //         if (result === 0 || result === 1) {
    //             $('.c_postLikeButton').toggleClass("is-active");
    //         }
    //     }
    // });

    // AJAX 호출 (MIME 데이터 전송 시)
    // var id      = postId; // 게시글 번호
    // var url     = '/board/post/' + id + '/like'; // 요청 서버 URL
    // var type    = 'post'; // 요청 메소드
    // console.log(url);
    //
    // $.ajax({
    //     type: type,
    //     url: url,
    //     data: {id: id},
    //
    //     success: function (result) {
    //         if (result === 0 || result === 1) {
    //             $('.c_postLikeButton').toggleClass("is-active");
    //         }
    //     }
    // })
}