<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<head>
    <title>게시글 목록</title>
    <link href="/resources/css/board/post/list.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet">
    <script src="/resources/js/board/post/list.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
</head>

<div id="c_postList" class="container c_container-xl">
    <div class="container c_headerLine">
    </div>
    <div class="container c_header">
        <h5>자유 게시판</h5>
        <p>자유롭게 게시글을 작성할 수 있습니다.</p>
    </div>
    <br/>

    <jsp:include page="/WEB-INF/views/component/bootstrapAlertMessage.jsp">
        <jsp:param name="type" value="info"/>
        <jsp:param name="title" value="알림: "/>
    </jsp:include>

    <%-- 이미지/파일 첨부 아이콘 표시, 새 글 표시 필요 --%>
    <div id="c_postListBody" class="container">
        <table class="table table-hover">
            <thead>
            <tr id="c_postListItemHeader">
                <th id="c_postListItemId" style="width: 10%">번호</th>
                <th id="c_postListItemTitle" style="width: 44%">제목</th>
                <th id="c_postListItemUserNickname" style="width: 15%">작성자</th>
                <th id="c_postListItemCreatedAt" style="width: 15%">작성일</th>
                <th id="c_postListItemLikes" style="width: 8%">좋아요</th>
                <th id="c_postListItemViews" style="width: 8%">조회수</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="post" items="${postList}">
                <tr class="c_postListItem tableItem" onclick="location.href='/board/post/${post.id}'">
                    <td>${post.id}</td>
                    <td style="text-align: left">${post.title}
                        <c:if test="${post.comments ne 0}">
                        <span class="counter">${post.comments}</span></td>
                        </c:if>
                    <td>${post.userNickname}</td>
                    <td><fmt:formatDate pattern="yyyy.MM.dd HH:mm" value="${post.createdAt}"/></td>
                    <td>${post.likes}</td>
                    <td>${post.views}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="/WEB-INF/views/board/post/page.jsp"/>
<jsp:include page="/WEB-INF/views/board/post/search.jsp"/>