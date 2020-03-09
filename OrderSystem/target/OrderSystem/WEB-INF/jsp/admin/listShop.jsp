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
<ol class="breadcrumb">
    <li><a href="canteen_list">所有食堂</a></li>
    <li class="active">${canteen.name}</li>
    <li class="active">店铺管理</li>
</ol>
<table class="table table-hover" align="center">
    <thead>
    <th>ID</th>
    <th>店铺名</th>
    <th>卖家ID</th>
    <th>菜品管理</th>
    <th>店主管理</th>
    <th>编辑</th>
    <th>删除</th>
    </thead>
    <tbody>
    <c:forEach items="${shops}" var="s">
        <tr>
            <td>${s.id}</td>
            <td>${s.name}</td>
            <td>${s.sid}</td>
            <td><a href="food_list?sid=${s.id}">菜品管理</a></td>
            <td><a href="seller_list?sid=${s.id}">店主管理</a></td>
            <td><a href="shop_edit?id=${s.id}">编辑</a></td>
            <td><a href="shop_delete?id=${s.id}">删除</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pageDiv" align="center">
    <%@include file="../include/admin/adminPage.jsp" %>
</div>
<div class="panel-body" align="center">
    <h2 >增加店铺</h2>
    <form method="post" id="editForm" action="shop_add"  enctype="multipart/form-data">
        <table class="addTable">
            <tr>
                <td>店铺图片</td>
                <td>
                    <input id="foodPic" accept="img/shop/*" type="file" name="image" />
                </td>
            </tr>
            <tr>
                <td>店铺名称</td>
                <td><input  id="name" name="name" placeholder="请输入店铺名称" type="text" class="form-control"></td>
            </tr>
            <tr>
                <td>店主id</td>
                <td><input  id="sid" name="sid" placeholder="请输入店主id" type="text" class="form-control"></td>
            </tr>
            <tr class="submitTR">
                <td colspan="2" align="center">
                    <input type="hidden" name="cid" value="${canteen.id}">
                    <button type="submit" class="btn btn-success">提 交</button>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
