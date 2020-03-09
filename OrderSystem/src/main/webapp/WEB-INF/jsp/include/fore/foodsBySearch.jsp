<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>

<div class="searchProducts">

    <c:forEach items="${foods}" var="p">
    <div class="productUnit" price="${p.price}">
        <a href="/forefood?fid=${p.id}">
            <img class="productImage" src="img/foodSingle/${p.firstFoodImage.id}.jpg">
        </a>
        <span class="productPrice">¥<fmt:formatNumber type="number" value="${p.price}" minFractionDigits="2"/></span>
        <a class="productLink" href="/forefood?fid=${p.id}">
                ${fn:substring(p.name, 0, 50)}
        </a>

        <a class="tmallLink" href="/forefood?fid=${p.id}">天猫专卖</a>

        <div class="productInfo">
            <span class="monthDeal ">月成交 <span class="productDealNumber">${p.saleCount}笔</span></span>
            <span class="productReview">评价<span class="productReviewNumber">${p.reviewCount}</span></span>
            <span class="wangwang"><img src="img/site/wangwang.png"></span>
        </div>

    </div>
    </c:forEach>
    <c:if test="${empty foods}">
    <div class="noMatch">没有满足条件的产品<div>
        </c:if>

        <div style="clear:both"></div>
    </div>