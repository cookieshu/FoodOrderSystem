<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>

<div class="productDetailDiv" >
    <div class="productDetailTopPart">
        <a href="#nowhere" class="productDetailTopPartSelectedLink selected">菜品详情</a>
        <a href="#nowhere" class="productDetailTopReviewLink">累计评价 <span class="productDetailTopReviewLinkNumber">${food.reviewCount}</span> </a>
    </div>

    <div class="productDetailImagesPart">
        <c:forEach items="${food.foodDetailImages}" var="pi">
            <img src="img/foodDetail/${pi.id}.jpg">
        </c:forEach>
    </div>
</div>