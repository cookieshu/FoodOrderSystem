<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>

<title>饭暖暖 ${food.name}</title>

<div class="productPageDiv">

    <%--单个图片和基本信息--%>
    <%@include file="imgAndInfo.jsp" %>
    <%--评价信息--%>
    <%@include file="foodReview.jsp" %>
    <%--详情图片--%>
    <%@include file="foodDetail.jsp" %>
</div>