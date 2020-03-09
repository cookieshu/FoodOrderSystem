<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>饭暖暖首页</title>
    <script src="https://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
    <link href="https://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="https://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>
</head>
<body>
<a href="/forelogin">登录</a>|<a href="/foreregister">注册</a>|<a href="/forecart">我的菜单</a>
<div align="center">
<form action="foresearch" method="post" >
    <div class="searchDiv">
        <input name="keyword" type="text" placeholder="糖醋里脊  小炒肉 ">
        <button  type="submit" class="searchButton">搜索</button>
    </div>
</form>
</div>
<br>
<div align="left">
    <%--每个食堂--%>
    <c:forEach items="${canteens}" var="c">
        <h4 align="center">${c.name}</h4><hr>
        <%--每一行店铺--%>
        <c:forEach items="${c.shopsByRow}" var="shopRow">
            <%--每一个店铺--%>
            <c:forEach items="${shopRow}" var="shop">
        <a href="/foreshop?sid=${shop.id}"><h5>${shop.name}</h5>
                <hr width="50%" align="left">
                <%--每行推荐的菜名--%>
                <c:forEach items="${shop.foodsByRow}" var="foodRow">
                    <%--每一个菜名--%>
                    <c:forEach items="${foodRow}" var="food">
                        <a href="forefood?fid=${food.id}">${food.name}</a>
                    </c:forEach>
                </c:forEach>
                    <hr>

            </c:forEach>
            <br>
        </c:forEach>
    </c:forEach>
</div>
</body>
</html>
