<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<head>
    <title>Title</title>
    <link href="/resources/css/board/post/search.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet">
</head>

<div id="c_postSearch" class="container">
    <div id="c_postSearchContent" class="container">
        <form name="option" method="get" action="/board/post/list">
            <label>
                <select class="form-control" name="option">
                    <option value="title">제목</option>
                    <option value="content">내용</option>
                    <option value="user_nickname">작성자</option>
                </select>
            </label>
            <div class="form-group">
                <input id="c_postSearchInput" class="form-control" name="keyword" type="text" aria-label="search" value="${keyword}">
            </div>
            <div class="form-group">
                <button id="c_postSearchButton" class="btn btn-primary btn-sm" type="submit"><i class="fas fa-search"></i></button>
            </div>
        </form>
    </div>
</div>