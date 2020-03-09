<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>菜品管理</title>
    <script src="https://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
    <link href="https://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="https://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>
</head>
<body>
<ol class="breadcrumb">
    <li><a href="canteen_list">所有食堂</a></li>
    <li class="active">食堂管理</li>
</ol>
<div>
    <table class="table table-hover" align="center">
        <thead>
        <th>ID</th>
        <th>食堂名称</th>
        <th>店铺管理</th>
        <th>编辑</th>
        <th>删除</th>
        </thead>
        <tbody>
        <c:forEach items="${canteens}" var="c">
            <tr>
                <td>${c.id}</td>
                <td>${c.name}</td>
                <td><a href="shop_list?cid=${c.id}">店铺管理</a></td>
                <td><a href="canteen_edit?id=${c.id}">编辑</a></td>
                <td><a href="canteen_delete?id=${c.id}">删除</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pageDiv" align="center">
        <%@include file="../include/admin/adminPage.jsp" %>
    </div>
</div>
<div class="panel-body" align="center">
    <h2 >增加食堂</h2>
    <form method="post" id="editForm" action="canteen_add"  enctype="multipart/form-data">
        <table class="addTable">
            <tr>
                <td>食堂名称</td>
                <td><input  id="name" name="name" placeholder="请输入食堂名称" type="text" class="form-control"></td>
            </tr>
            <tr class="submitTR">
                <td colspan="2" align="center">
                    <button type="submit" class="btn btn-success">提 交</button>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
