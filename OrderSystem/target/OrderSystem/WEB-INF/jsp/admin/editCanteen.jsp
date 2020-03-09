<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>编辑菜品</title>
    <script src="https://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
    <link href="https://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="https://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>
</head>
<body>
<h2>编辑食堂</h2>
<div class="panel-body" align="center">
    <form method="post" id="editForm" action="canteen_update"  enctype="multipart/form-data">
        <table class="editTable">

            <tr>
                <td>食堂名称</td>
                <td><input  id="name" name="name" value="${canteen.name}" type="text" class="form-control"></td>
            </tr>

            <tr class="submitTR">
                <td colspan="2" align="center">
                    <input type="hidden" name="id" value="${canteen.id}">
                    <button type="submit" class="btn btn-success">提 交</button>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
