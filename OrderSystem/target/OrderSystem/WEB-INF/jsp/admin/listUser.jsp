<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>用户管理</title>
    <script src="https://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
    <link href="https://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="https://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>
</head>
<body>
<table class="table table-hover" align="center">
    <thead>
    <th>ID</th>
    <th>用户名</th>
    <th>密码</th>
    <th>电话</th>
    <th>加密</th>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="u">
    <tr>
        <td>${u.id}</td>
        <td>${u.name}</td>
        <td>${u.password}</td>
        <td>${u.mobile}</td>
        <td>${u.salt}</td>
    </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pageDiv" align="center">
    <%@include file="../include/admin/adminPage.jsp" %>
</div>
</body>
</html>
