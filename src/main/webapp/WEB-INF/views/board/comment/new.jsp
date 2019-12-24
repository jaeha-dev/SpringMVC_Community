<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<head>
    <link href="/resources/css/board/comment/new.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet">
    <script src="/resources/js/board/comment/newAndEdit.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
</head>

<spring:url var="commentNewUri" value="/board/comment/new" />

<div id="c_commentNew" class="container c_container-sm">
    <div id="c_commentNewHeader" class="container">
        <sec:authorize access="isAnonymous()">
            <h4><small>- <i class="far fa-comment-dots"></i> <span style="color:#EB387C" rel="tooltip" data-html="true" data-original-title="계정을 등록하여<br/>닉네임으로 댓글을 작성하세요!">익명</span>으로 댓글을 작성할 수 있습니다 -</small></h4>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <h4><small>- <i class="far fa-comment-dots"></i> <span style="color:#EB387C">닉네임</span>으로 댓글을 작성할 수 있습니다 -</small></h4>
        </sec:authorize>
    </div>

    <div id="c_commentNewBody" class="media media-post">
        <%-- 프로필 이미지 추가 시, 조건문 필요 --%>
        <a class="pull-left" href="#">
            <div class="container">
                <img class="media-object img-raised" src="/resources/img/placeholder.jpg" alt="...">
            </div>
        </a>

        <div id="c_commentNewContent" class="media-body">
            <form:form method="post" modelAttribute="comment" action="${commentNewUri}" novalidate="true">
                <div class="materialGroup">
                    <div class="form-group">
                        <form:textarea id="newContent" class="form-control" name="content" path="content" rows="4" maxlength="100" required="required" onkeyup="newContentCheck()" onblur="newContentError()"/>
                        <span class="highlight"></span>
                        <span class="bar"></span>
                        <label for="newContent">* 댓글 <span id="newContentCounter">(0 / 100자)</span></label>

                        <form:errors id="newContentValidation" path="content"/>
                        <span id="newContentCheck"></span>
                    </div>
                </div>

                <div class="c_commentNewButtonGroup">
                    <input type="hidden" name="postId" value="${post.id}"/>
                    <button id="c_commentNewCompleteButton" type="submit" class="btn btn-neutral btn-sm btn-font-sm"><i class="fas fa-angle-right"></i> 등록</button>
                    <button id="c_commentNewClearButton" type="reset" class="btn btn-neutral btn-heart btn-sm btn-font-sm" onclick="newCommentClear()" data-toggle="tooltip" data-placement="right" title="작성한 댓글이 삭제됩니다."><i class="fas fa-angle-right"></i> 삭제</button>
                </div>
            </form:form>
        </div>
    </div>
</div>