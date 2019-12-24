<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%String type = request.getParameter("type");%>
<%String title = request.getParameter("title");%>

<c:if test="${not empty serverMessage}">
    <div class="alert alert_<%=type%>">
        <div class="alert--icon"><i class="fas fa-bell"></i></div>
        <div class="alert--content">
            <strong><%=title%></strong> ${serverMessage}
        </div>
        <div class="alert--close"><i class="far fa-times-circle"></i></div>
    </div>
</c:if>

<c:if test="${not empty successMessage}">
    <div class="alert alert_info">
        <div class="alert--icon"><i class="fas fa-bell"></i></div>
        <div class="alert--content">
            <strong><%=title%> 성공!</strong> ${successMessage}
        </div>
        <div class="alert--close"><i class="far fa-times-circle"></i></div>
    </div>
</c:if>

<c:if test="${not empty failureMessage}">
    <div class="alert alert_danger">
        <div class="alert--icon"><i class="fas fa-bell"></i></div>
        <div class="alert--content">
            <strong><%=title%> 실패!</strong> ${failureMessage}
        </div>
        <div class="alert--close"><i class="far fa-times-circle"></i></div>
    </div>
</c:if>