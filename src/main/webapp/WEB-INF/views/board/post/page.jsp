<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<head>
    <title>Title</title>
</head>

<spring:url var="postNewUri" value="/board/post/new"/>
<spring:url var="adminPostUri" value="/admin/home"/>

<div id="c_postListPage" class="container">
    <div class="d-flex bd-highlight mb-3">
        <button id="c_postNewButton" type="button" class="btn btn-neutral btn-heart btn-sm ml-auto" onclick="location.href='${postNewUri}'"><i class="fas fa-pen"></i> 글쓰기</button>
        <sec:authorize access="hasRole('ROLE_M')">
            <button id="c_adminPostButton" type="button" class="btn btn-neutral btn-heart btn-sm" onclick="location.href='${adminPostUri}'"><i class="fas fa-cog"></i> 관리</button>
        </sec:authorize>
    </div>

    <nav aria-label="Page navigation">
        <ul class="pagination justify-content-center">

            <c:if test="${option eq null && keyword eq null}">
                <spring:url var="previousPageUri" value="${baseUri}?page=${pageMaker.startPage - 1}"/>
            </c:if>
            <c:if test="${option ne null && keyword ne null}">
                <spring:url var="previousPageUri" value="${baseUri}?page=${pageMaker.startPage - 1}&option=${option}&keyword=${keyword}"/>
            </c:if>
            <c:if test="${pageMaker.previous}">
                <li class="page-item">
                    <a class="page-link" aria-label="Previous" href="${previousPageUri}">
                        <span aria-hidden="true"><i class="fa fa-angle-double-left" aria-hidden="true"></i></span>
                    </a>
                </li>
            </c:if>

            <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNumber">
                <c:if test="${option eq null && keyword eq null}">
                    <spring:url var="pageUri" value="${baseUri}?page=${pageNumber}"/>
                </c:if>
                <c:if test="${option ne null && keyword ne null}">
                    <spring:url var="pageUri" value="${baseUri}?page=${pageNumber}&option=${option}&keyword=${keyword}"/>
                </c:if>
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
                    <c:if test="${option eq null && keyword eq null}">
                        <spring:url var="nextPageUri" value="${baseUri}?page=${pageMaker.endPage + 1}"/>
                    </c:if>
                    <c:if test="${option ne null && keyword ne null}">
                        <spring:url var="nextPageUri" value="${baseUri}?page=${pageMaker.endPage + 1}&option=${option}&keyword=${keyword}"/>
                    </c:if>
                    <a class="page-link" aria-label="Next" href=${nextPageUri}>
                        <span aria-hidden="true"><i class="fa fa-angle-double-right" aria-hidden="true"></i></span>
                    </a>
                </li>
            </c:if>
        </ul>
    </nav>
</div>