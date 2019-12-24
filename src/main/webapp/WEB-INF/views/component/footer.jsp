<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<head>
    <link href="/resources/css/component/footer.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet">
</head>

<spring:url var="gitUri" value="https://github.com/jaeha-dev"/>

<footer id="f_footer" class="footer footer-big footer-white">
    <div class="container">
        Copyright &copy;
        <script>
            document.write(new Date().getFullYear())
        </script>
        <a href="${gitUri}" target="_blank">GIT(jaeha-dev)</a> All Rights Reserved.
    </div>
</footer>