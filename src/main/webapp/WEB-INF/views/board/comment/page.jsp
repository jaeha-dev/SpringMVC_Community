<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<head>
    <title>Title</title>
</head>

<div id="pageNavigation" class="container">
    <nav aria-label="Page navigation">
        <ul class="pagination justify-content-center">

            <spring:url var="previousPageUri" value="/board/commentList?page=${pageMaker.startPage - 1}"/>
            <c:if test="${pageMaker.previous}">
                <li class="page-item">
                    <a class="page-link" aria-label="Previous" href="${previousPageUri}">
                        <span aria-hidden="true"><i class="fa fa-angle-double-left" aria-hidden="true"></i></span>
                    </a>
                </li>
            </c:if>

            <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNumber">
                <spring:url var="pageUri" value="/board/commentList?page=${pageNumber}"/>
                <c:choose>
                    <c:when test="${currentPage eq pageNumber}">
                        <li class="page-item active">
                            <a class="page-link" href="${pageUri}">${pageNumber}</a>
                        </li>
                    </c:when>
                    <c:when test="${currentPage ne pageNumber}">
                        <li class="page-item">
                            <a class="page-link" href="${pageUri}">${pageNumber}</a>
                        </li>
                    </c:when>
                </c:choose>
            </c:forEach>

            <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
                <li class="page-item">
                    <spring:url var="nextPageUri" value="/board/commentList?page=${pageMaker.endPage + 1}"/>
                    <a class="page-link" aria-label="Next" href=${nextPageUri}>
                        <span aria-hidden="true"><i class="fa fa-angle-double-right" aria-hidden="true"></i></span>
                    </a>
                </li>
            </c:if>
        </ul>
    </nav>
</div>