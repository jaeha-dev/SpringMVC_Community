<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<head>
    <title>타이틀</title>
    <link href="/resources/css/*.css?v=<%=System.currentTimeMillis() %>"" rel="stylesheet">
    <script src="/resources/js/*.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
</head>

<%-- 전체 영역 --%>
<div id="c_xxx" class="container c_container-sm">

    <%-- 헤더 영역 --%>
    <div class="container c_headerLine">
    </div>
    <div class="container c_header">
        <h5>페이지 제목</h5>
        <p>페이지 설명</p>
    </div>

    <%-- 컨텐트 영역 --%>
    <div id="c_xxxContent" class="container c_content">
        <p>페이지 Content</p>
    </div>

    <%-- 푸터 영역 --%>
    <div id="c_xxxFooter" class="container c_footer">
        <p>페이지 Footer</p>
    </div>

    <%-- 부트스트랩 알림 메시지 영역 --%>
    <c:if test="${not empty serverMessage}">
        <div class="alert alert_danger">
            <div class="alert--icon"><i class="fas fa-bell"></i></div>
            <div class="alert--content">
                <strong>메시지!</strong> ${serverMessage}
            </div>
            <div class="alert--close"><i class="far fa-times-circle"></i></div>
        </div>
    </c:if>
</div>