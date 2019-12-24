<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<head>
    <title>댓글 목록</title>
    <link href="/resources/css/board/comment/list.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet">
    <script src="/resources/js/board/comment/list.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
</head>

<div id="c_commentList" class="container">
    <div class="container c_headerLine">
    </div>
    <div id="c_commentListHeader" class="container">
        <%-- 댓글 개수 --%>
        <h4><small>- <i class="far fa-comment-dots"></i> <span style="color:#EB387C">${fn:length(commentList)}개</span>의 댓글이 작성되었습니다 -</small></h4>
    </div>

    <div id="c_commentListBody" class="container">
        <%-- 댓글 아이템 --%>
        <c:forEach var="comment" items="${commentList}">
            <div id="c_commentListItem_${comment.id}" class="media-area">
                <div class="media">

                    <a class="pull-left" href="#">
                        <div class="container">
                            <img class="media-object img-raised" src="/resources/img/placeholder.jpg" alt="...">
                        </div>
                    </a>

                    <div class="media-body">
                        <h5 class="media-heading">${comment.userNickname}<small class="text-muted"> · <fmt:formatDate pattern="yyyy.MM.dd HH:mm" value="${comment.createdAt}"/></small></h5>
                        <%-- <span class="counter">등급</span>--%>
                        <div id="c_commentListButton" class="media-footer">

                            <%-- 댓글 좋아요 개수 --%>
                            <sec:authorize access="isAuthenticated()">
                                <button id="c_commentLikeButton" class="btn btn-heart btn-neutral btn-sm pull-right" rel="tooltip" data-original-title="댓글 추천" onclick="commentLike(${post.id}, ${comment.id})">
                                    <%-- 댓글 좋아요 여부 검사 --%>
                                    <c:set var="isCommentLike" value="0"/>
                                    <c:forEach var="commentLike" items="${isCommentLikeList}">
                                        <c:if test="${isCommentLike eq 0}">
                                            <c:if test="${comment.id eq commentLike.commentId}">
                                                <c:set var="isCommentLike" value="1"/>
                                            </c:if>
                                        </c:if>
                                    </c:forEach>

                                    <c:if test="${isCommentLike eq 0}">
                                        <i class="far fa-heart commentLikeButton"></i> <span class="commentLikes">${comment.likes}</span>
                                    </c:if>
                                    <c:if test="${isCommentLike eq 1}">
                                        <i class="fas fa-heart commentLikeButton"></i> <span class="commentLikes">${comment.likes}</span>
                                    </c:if>
                                </button>
                            </sec:authorize>
                            <sec:authorize access="isAnonymous()">
                                <button id="c_commentLikeButton" class="btn btn-heart btn-neutral btn-sm pull-right" rel="tooltip" data-html="true" data-original-title="로그인 시<br/>추천할 수 있습니다.">
                                    <i class="far fa-heart"></i> ${comment.likes}
                                </button>
                            </sec:authorize>

                            <%-- 댓글 삭제 --%>
                            <button id="c_commentDeleteButton" class="btn btn-neutral btn-sm pull-right" rel="tooltip" data-original-title="댓글 삭제" onclick="commentDeleteCheck(${post.id}, ${comment.id})">
                                <i class="fas fa-eraser"></i> 삭제
                            </button>

                            <%-- 댓글 수정 --%>
                            <button id="c_commentEditButton" class="btn btn-neutral btn-sm pull-right" rel="tooltip" data-original-title="댓글 수정" onclick="commentEditCheck(${post.id}, ${comment.id})">
                                <i class="fas fa-edit"></i> 수정
                            </button>
                        </div>
                        <p class="commentContent">${comment.content}</p>
                    </div>

<%--                    <div class="media-body">--%>
<%--                        <h5 class="media-heading">${comment.userNickname}<small class="text-muted"> · <fmt:formatDate pattern="yyyy.MM.dd HH:mm" value="${comment.createdAt}"/></small></h5>--%>
<%--                        <p>${comment.content}</p>--%>
<%--                        <div id="c_commentListButton" class="media-footer">--%>
<%--                            &lt;%&ndash; 댓글 좋아요 개수 &ndash;%&gt;--%>
<%--                            <button id="c_commentLikeButton" class="btn btn-heart btn-neutral btn-sm pull-right" rel="tooltip" title="" data-original-title="댓글 추천" onclick="commentLike(${post.id}, ${comment.id})">--%>
<%--                                <i class="far fa-heart"></i> ${comment.likes}--%>
<%--                            </button>--%>
<%--                            &lt;%&ndash; 댓글 삭제 &ndash;%&gt;--%>
<%--                            <button id="c_commentDeleteButton" class="btn btn-neutral btn-sm pull-right" rel="tooltip" title="" data-original-title="댓글 삭제" onclick="commentDelete(${post.id}, ${comment.id})">--%>
<%--                                <i class="fas fa-eraser"></i> 삭제--%>
<%--                            </button>--%>
<%--                            &lt;%&ndash; 댓글 수정 &ndash;%&gt;--%>
<%--                            <button id="c_commentEditButton" class="btn btn-neutral btn-sm pull-right" rel="tooltip" title="" data-original-title="댓글 수정" onclick="commentEdit(${post.id}, ${comment.id})">--%>
<%--                                <i class="fas fa-edit"></i> 수정--%>
<%--                            </button>--%>
<%--                        </div>--%>
<%--                    </div>--%>
                </div>
            </div>
        </c:forEach>

        <div id="c_commentListFooter" class="container">
            <%-- 댓글 페이징 --%>
            <%-- <jsp:include page="/WEB-INF/views/board/comment/page.jsp"/>--%>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/board/comment/edit.jsp"/>