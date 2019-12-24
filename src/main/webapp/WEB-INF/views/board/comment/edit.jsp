<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<head>
    <link href="/resources/css/board/comment/edit.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet">
    <script src="/resources/js/board/comment/newAndEdit.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
</head>

<div id="c_commentEdit" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="c_commentEditModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-dialog-centered modal-md" role="document">
        <div class="modal-content">

            <div id="c_commentEditHeader" class="modal-header">
                <sec:authorize access="isAnonymous()">
                    <h4><small>- <i class="far fa-comment-dots"></i> <span style="color:#EB387C" rel="tooltip" data-html="true" data-original-title="계정을 등록하여<br/>닉네임으로 댓글을 작성하세요!">익명</span>으로 댓글을 작성할 수 있습니다 -</small></h4>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <h4><small>- <i class="far fa-comment-dots"></i> <span style="color:#EB387C">닉네임</span>으로 댓글을 작성할 수 있습니다 -</small></h4>
                </sec:authorize>
                <button type="button" id="c_commentEditClose" class="close" data-dismiss="modal" aria-label="Close" rel="tooltip" title="" data-placement="top" data-original-title="댓글 수정 취소" onclick="javascript:window.location.reload()">
                    <span class="modalClose" aria-hidden="true">&times;</span>
                </button>
            </div>

            <div id="c_commentEditBody" class="media media-post modal-body">
                <%-- 프로필 이미지 추가 시, 조건문 필요 --%>
                <a class="media-profile pull-left" href="#">
                    <div class="">
                        <img class="media-object img-raised" src="/resources/img/placeholder.jpg" alt="...">
                    </div>
                </a>

                <div id="c_commentEditContent" class="media-body">
                    <form method="post" id="c_commentEditForm" novalidate="true">
                        <div class="materialGroup">
                            <div class="form-group">
                                <textarea id="editContent" class="form-control" name="content" rows="4" maxlength="100" required="required" onkeyup="editContentCheck()" onblur="editContentError()"></textarea>
                                <span class="highlight"></span>
                                <span class="bar"></span>
                                <label for="editContent">* 댓글 <span id="editContentCounter">(0 / 100자)</span></label>

                                <span id="editContentValidation"></span>
                                <span id="editContentCheck"></span>
                            </div>
                        </div>

                        <div class="c_commentEditButtonGroup">
                            <input id="postId" type="hidden" name="postId" value="0"/>
                            <input id="commentId" type="hidden" name="id" value="0"/>
                            <button id="c_commentEditCompleteButton" type="button" class="btn btn-neutral btn-sm btn-font-sm" onclick="commentEdit()"><i class="fas fa-angle-right"></i> 수정</button>
                            <button id="c_commentEditClearButton" type="reset" class="btn btn-neutral btn-heart btn-sm btn-font-sm" onclick="editCommentClear()" data-toggle="tooltip" data-placement="bottom" title="작성한 댓글이 삭제됩니다."><i class="fas fa-angle-right"></i> 삭제</button>
                        </div>
                    </form>
                </div>
            </div>
            <div id="c_commentEditFooter" class="modal-footer">
            </div>
        </div>
    </div>
</div>



<%--<spring:url var="commentEditUri" value="/board/comment/edit" />--%>

<%--<div id="c_commentEdit" class="container c_container-sm">--%>
<%--    <div id="c_commentEditHeader" class="container">--%>
<%--        <sec:authorize access="isAnonymous()">--%>
<%--            <h4><small>- <i class="far fa-comment-dots"></i> <span style="color:#EB387C" rel="tooltip" data-html="true" data-original-title="계정을 등록하여<br/>닉네임으로 댓글을 작성하세요!">익명</span>으로 댓글을 작성할 수 있습니다 -</small></h4>--%>
<%--        </sec:authorize>--%>
<%--        <sec:authorize access="isAuthenticated()">--%>
<%--            <h4><small>- <i class="far fa-comment-dots"></i> <span style="color:#EB387C">닉네임</span>으로 댓글을 작성할 수 있습니다 -</small></h4>--%>
<%--        </sec:authorize>--%>
<%--    </div>--%>

<%--    <div id="c_commentEditBody" class="media media-post">--%>
<%--        &lt;%&ndash; 프로필 이미지 추가 시, 조건문 필요 &ndash;%&gt;--%>
<%--        <a class="pull-left" href="#">--%>
<%--            <div class="container">--%>
<%--                <img class="media-object img-raised" src="/resources/img/placeholder.jpg" alt="...">--%>
<%--            </div>--%>
<%--        </a>--%>

<%--        <div id="c_commentEditContent" class="media-body">--%>
<%--            <form:form method="post" modelAttribute="comment" action="${commentEditUri}">--%>
<%--                <div class="materialGroup">--%>
<%--                    <div class="form-group">--%>
<%--                        <form:textarea id="content" class="form-control" name="content" path="content" required="required" rows="4" maxlength="100" onkeyup="checkContent()" onblur="hideErrorContent()"/>--%>
<%--                        <span class="highlight"></span>--%>
<%--                        <span class="bar"></span>--%>
<%--                        <label for="content">* 댓글 <span id="contentCounter">(0 / 100자)</span></label>--%>

<%--                        <form:errors id="contentValidation" path="content"/>--%>
<%--                        <span id="contentCheck"></span>--%>
<%--                    </div>--%>
<%--                </div>--%>

<%--                <div class="commentButtonGroup">--%>
<%--                    <input type="hidden" name="postId" value="${post.id}"/>--%>
<%--                    <button id="c_commentEditCompleteButton" type="submit" class="btn btn-neutral btn-sm btn-font-sm"><i class="fas fa-angle-right"></i> 수정</button>--%>
<%--                    <button id="c_commentEditClearButton" type="reset" class="btn btn-neutral btn-heart btn-sm btn-font-sm" onclick="clearComment()" data-toggle="tooltip" data-placement="right" title="작성한 댓글이 삭제됩니다."><i class="fas fa-angle-right"></i> 삭제</button>--%>
<%--                </div>--%>
<%--            </form:form>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>