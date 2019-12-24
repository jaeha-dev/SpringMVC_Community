<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<head>
    <title>게시글 등록</title>
    <link href="/resources/css/board/post/newAndEdit.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet">
    <script src="/resources/js/board/post/newAndEdit.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
</head>

<spring:url var="postNewUri" value="/board/post/new" />
<spring:url var="postListUri" value="/board/post/list" />

<body>
<div id="c_postNewContainer" class="container c_container-xl">
    <div class="container c_headerLine">
    </div>
    <div class="container c_header">
        <h5>게시글 등록</h5>
        <p>제목과 내용을 작성해주세요.</p>
    </div>

    <div id="c_postNew" class="container">
        <form:form method="post" modelAttribute="post" class="post" action="${postNewUri}" novalidate="true">
            <div class="materialGroup">

                <div class="form-group">
                    <form:input id="title" name="title" path="title" type="text" value="${title}" required="required" maxlength="100" onKeyup="titleCheck()"/>
                    <span class="highlight"></span>
                    <span class="bar"></span>
                    <label for="title">* 게시글 제목 <span id="titleCounter"> (0 / 100자)</span></label>
                    <span id="titleCheck"></span>
                </div>

                <div class="form-group">
                    <span id="typeLabel">* 게시글 카테고리</span>
                    <form:select id="type" class="form-control" path="type">
                        <form:option value="공지" label="공지"/>
                        <form:option value="행사" label="행사"/>
                        <form:option value="자유" label="자유"/>
                        <form:option value="질문" label="질문"/>
                    </form:select>
                </div>

                <div class="form-group">
                    <span id="contentLabel">* 게시글 내용</span>
                    <form:textarea id="froala-editor" name="content" class="form-control" path="content" value="${content}"/>
                </div>
            </div>

            <jsp:include page="/WEB-INF/views/component/bootstrapAlertMessage.jsp">
                <jsp:param name="title" value="등록"/>
            </jsp:include>

            <button id="c_postNewCompleteButton" type="submit" class="btn btn-neutral"><i class="fas fa-angle-right"></i> 등록</button>
            <button id="c_postNewCancelButton" type="button" class="btn btn-neutral btn-heart" onclick="location.href='${postListUri}'"><i class="fas fa-angle-right"></i> 취소</button>
        </form:form>
    </div>

    <div id="c_postFormFooter" class="container">
        <%-- 태그 목록--%>
    </div>
</div>

</body>