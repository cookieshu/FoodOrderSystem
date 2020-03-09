<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>编辑菜品</title>
    <script src="https://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
    <link href="https://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="https://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>
</head>
<body>
<h2>编辑菜品</h2>
<div class="panel-body" align="center">
    <form method="post" id="editForm" action="food_update"  enctype="multipart/form-data">
        <table class="editTable">
            <tr>
                <td>菜品名称</td>
                <td><input  id="name" name="name" value="${food.name}" type="text" class="form-control"></td>
            </tr>
            <tr>
                <td>菜品标签</td>
                <td><input  id="title" name="subTitle" value="${food.subTitle}" type="text" class="form-control"></td>
            </tr>
            <tr>
                <td>菜品价格</td>
                <td><input  id="price" name="price" value="${food.price}" type="text" class="form-control"></td>
            </tr>
            <tr>
                <td>发布日期</td>
                <td><input  id="date" name="createDate" value="${food.createDate}" type="text" class="form-control"></td>
            </tr>
            <tr class="submitTR">
                <td colspan="2" align="center">
                    <input type="hidden" name="id" value="${food.id}">
                    <input type="hidden" name="sid" value="${food.sid}">
                    <button type="submit" class="btn btn-success">提 交</button>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
