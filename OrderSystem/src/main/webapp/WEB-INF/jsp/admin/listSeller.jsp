<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>店主管理</title>
    <script src="https://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
    <link href="https://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="https://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>
</head>
<body>
<ol class="breadcrumb">
    <li><a href="canteen_list">所有食堂</a></li>
    <li><a href="/shop_list?cid=${canteen.id}">${canteen.name}</a></li>
    <li class="active">${shop.name}</li>
    <li class="active">店主管理</li>
</ol>
<table class="table table-hover" align="center">
    <thead>
    <th>ID</th>
    <th>用户名</th>
    <th>联系电话</th>
    <th>密码</th>
    <th>加密</th>
    <th>编辑</th>
    <th>删除</th>
    </thead>
    <tbody>
    <c:forEach items="${sellers}" var="s">
        <tr>
            <td>${s.id}</td>
            <td>${s.name}</td>
            <td>${s.mobile}</td>
            <td>${s.password}</td>
            <td>${s.salt}</td>
            <td><a href="seller_edit?id=${s.id}">编辑</a></td>
            <td><a href="seller_delete?id=${s.id}">删除</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pageDiv" align="center">
    <%@include file="../include/admin/adminPage.jsp" %>
</div>
<div class="panel-body" align="center">
    <h2 >增加店主</h2>
    <form method="post" id="editForm" action="seller_add"  enctype="multipart/form-data">
        <table class="addTable">
            <tr>
                <td>店主姓名</td>
                <td><input  id="name" name="name" placeholder="请输入店主姓名" type="text" class="form-control"></td>
            </tr>
            <tr>
                <td>联系电话</td>
                <td><input  id="tel" name="mobile" placeholder="请输入联系电话" type="text" class="form-control"></td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input  id="password" name="password" placeholder="请输入密码" type="text" class="form-control"></td>
            </tr>
            <tr>
                <td>加密</td>
                <td><input  id="salt" name="salt" placeholder="请输入加密" type="text" class="form-control"></td>
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
