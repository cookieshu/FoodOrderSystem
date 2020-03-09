<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>编辑店铺</title>
    <script src="https://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
    <link href="https://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="https://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>
</head>
<body>
<h2>编辑店铺</h2>
<div class="panel-body" align="center">
    <form method="post" id="editForm" action="shop_update"  enctype="multipart/form-data">
        <table class="editTable">
            <tr>
                <td>店铺名称</td>
                <td><input  id="name" name="name" value="${shop.name}" type="text" class="form-control"></td>
            </tr>
            <tr>
                <td>店主id</td>
                <td><input  id="sid" name="sid" value="${shop.sid}" type="text" class="form-control"></td>
            </tr>
            <tr class="submitTR">
                <td colspan="2" align="center">
                    <input type="hidden" name="id" value="${shop.id}">
                    <input type="hidden" name="cid" value="${shop.cid}">
                    <button type="submit" class="btn btn-success">提 交</button>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
