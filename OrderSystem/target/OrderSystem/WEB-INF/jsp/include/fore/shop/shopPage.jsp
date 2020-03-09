<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<title>饭暖暖-${shop.name}</title>
<div id="category">
    <div class="categoryPageDiv">
        <h3>${shop.name}</h3>
        <%@include file="sortBar.jsp"%>
        <%@include file="foodsByShop.jsp"%>
    </div>

</div>