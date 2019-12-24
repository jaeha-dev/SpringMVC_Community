<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<head>
    <title>게시글</title>
    <link href="/resources/css/board/post.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet">
    <script src="/resources/js/board/post.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
</head>

<spring:url var="postListUri" value="/board/post/list"/>

<div id="c_post" class="container c_container-xl">

    <%-- 게시글 헤더 영역 --%>
    <div class="container c_headerLine">
    </div>
    <div class="container c_header">
        <a id="c_postType" href="/"><i class="fas fa-tag"></i> ${post.type}</a>
        <h4 id="c_postTitle">&ldquo; ${post.title} &rdquo;</h4>
        <p id="c_postInfo">${post.userNickname}<span class="spacingBar"></span><fmt:formatDate pattern="yyyy.MM.dd HH:mm" value="${post.createdAt}"/></p>
    </div>

    <div class="container d-flex">
        <div id="c_headerLeftButtonGroup" class="mr-auto">
            <button id="c_postCopyUri" type="button" class="btn btn-neutral btn-sm btn-font-sm" data-toggle="tooltip" data-placement="top" title="이 게시글의 URL 복사" onclick="copyUri()">&ratio; URL 복사</button>
        </div>
        <div id="c_headerRightButtonGroup" class="ml-auto">
            <button id="c_postListButton" type="button" class="btn btn-neutral btn-sm btn-font-sm" onclick="history.back()">
                <i class="fas fa-list"></i> 목록</button>
            <button id="c_postEditButton" type="button" class="btn btn-neutral btn-sm btn-font-sm" onclick="postEdit(${post.id})">
                <i class="fas fa-edit"></i> 수정</button>
            <button id="c_postDeleteButton" type="button" class="btn btn-neutral btn-heart btn-sm btn-font-sm" onclick="postDeleteCheck(${post.id})">
                <i class="fas fa-eraser"></i> 삭제</button>
        </div>
    </div>
    <hr/>

    <%-- 게시글 바디 영역 --%>
    <div id="c_postBody" class="container">
        <div class="container">
            ${post.content}
        </div>
    </div>
    <hr/>

    <%-- 게시글 푸터 영역 --%>
    <div id="c_postFooter" class="container d-flex">
        <sec:authorize access="isAuthenticated()">
            <c:if test="${isPostLike eq 0}">
                <div class="c_postLikeButton heart" onclick="postLike(${post.id})" data-toggle="tooltip" data-placement="bottom" title="좋아요"></div>
            </c:if>
            <c:if test="${isPostLike eq 1}">
                <div class="c_postLikeButton heart is-active" onclick="postLike(${post.id})" data-toggle="tooltip" data-placement="bottom" title="좋아요"></div>
            </c:if>
            <div class="c_postFavoriteButton star is-active" onclick="" data-toggle="tooltip" data-placement="bottom" title="즐겨찾기"></div>
        </sec:authorize>

        <div id="postFooterButtonGroup" class="ml-auto">
            <button id="c_postViewsButton" type="button" class="btn btn-neutral btn-sm btn-font-sm" data-toggle="tooltip" data-placement="bottom" title="조회수">
                <i class="fas fa-eye"></i> &nbsp;${post.views}</button>
            <button id="c_postCommentsButton" type="button" class="btn btn-neutral btn-sm btn-font-sm" data-toggle="tooltip" data-placement="bottom" title="댓글수">
                <i class="fas fa-comment-dots"></i> &nbsp;${post.comments}</button>
            <button id="c_postLikesButton" type="button" class="btn btn-heart btn-neutral btn-sm btn-font-sm" data-toggle="tooltip" data-placement="bottom" title="좋아요수">
                <i class="fas fa-heart"></i> &nbsp;<span class="postLikes">${post.likes}</span></button>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/board/comment/list.jsp"/>
<jsp:include page="/WEB-INF/views/board/comment/new.jsp"/>
<jsp:include page="/WEB-INF/views/component/alertMessage.jsp"/>