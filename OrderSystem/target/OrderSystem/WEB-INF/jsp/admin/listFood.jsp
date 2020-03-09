<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
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
    <li><a href="/shop_list?cid=${canteen.id}">${canteen.name}</a></li>
    <li class="active">${shop.name}</li>
    <li class="active">菜品管理</li>
</ol>
<div>
<table class="table table-hover" align="center">
    <thead>
    <th>ID</th>
    <th>菜品图片</th>
    <th>菜品名称</th>
    <th>菜品标签</th>
    <th>菜品价格</th>
    <th>上传日期</th>
    <th>图片管理</th>
    <th>编辑</th>
    <th>删除</th>
    </thead>
    <tbody>
    <c:forEach items="${foods}" var="food" >
        <tr>
            <td>${food.id}</td>
            <td>
                <c:if test="${!empty food.firstFoodImage}">
                <img width="40px" src="img/foodSingle/${food.firstFoodImage.id}.jpg">
                </c:if>
            </td>
            <td>${food.name}</td>
            <td>${food.subTitle}</td>
            <td>${food.price}</td>
            <td><fmt:formatDate value="${food.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td><a href="foodImage_list?fid=${food.id}">图片管理</a></td>
            <td><a href="food_edit?id=${food.id}">编辑</a></td>
            <td><a href="food_delete?id=${food.id}">删除</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
    <div class="pageDiv" align="center">
        <%@include file="../include/admin/adminPage.jsp" %>
    </div>
</div>
<div class="panel-body" align="center">
    <h2 >增加菜品</h2>
    <form method="post" id="editForm" action="food_add"  enctype="multipart/form-data">
        <table class="addTable">
            <tr>
                <td>菜品图片</td>
                <td>
                    <input id="foodPic" accept="img/food/*" type="file" name="image" />
                </td>
            </tr>
            <tr>
                <td>菜品名称</td>
                <td><input  id="name" name="name" placeholder="请输入菜品名称" type="text" class="form-control"></td>
            </tr>
            <tr>
                <td>菜品标签</td>
                <td><input  id="title" name="subTitle" placeholder="请输入菜品标签" type="text" class="form-control"></td>
            </tr>
            <tr>
                <td>菜品价格</td>
                <td><input  id="price" name="price" placeholder="请输入菜品价格" type="text" class="form-control"></td>
            </tr>
            <tr>
                <td>创建日期</td>
                <td><input  id="date" name="createDate" placeholder="请输入创建日期" type="text" class="form-control"></td>
            </tr>
            <tr class="submitTR">
                <td colspan="2" align="center">
                    <input type="hidden" name="sid" value="${shop.id}">
                    <button type="submit" class="btn btn-success">提 交</button>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
