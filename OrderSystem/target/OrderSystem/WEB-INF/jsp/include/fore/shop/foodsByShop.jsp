<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>

<c:if test="${empty param.categorycount}">
    <c:set var="categorycount" scope="page" value="100"/>
</c:if>

<c:if test="${!empty param.categorycount}">
    <c:set var="categorycount" scope="page" value="${param.categorycount}"/>
</c:if>

<div class="categoryProducts">
    <c:forEach items="${shop.foods}" var="p" varStatus="stc">
        <c:if test="${stc.count<=categorycount}">
            <div class="productUnit" price="${p.price}">
                <div class="productUnitFrame">
                    <a href="/forefood?fid=${p.id}">
                        <img class="productImage" src="img/foodSingle_middle/${p.firstFoodImage.id}.jpg">
                    </a>
                    <span class="productPrice">¥<fmt:formatNumber type="number" value="${p.price}" minFractionDigits="2"/></span>
                    <a class="productLink" href="forefood?fid=${p.id}">
                            ${fn:substring(p.name, 0, 50)}
                    </a>
                    <div class="show1 productInfo">
                        <span class="monthDeal ">月成交 <span class="productDealNumber">${p.saleCount}笔</span></span>
                        <span class="productReview">评价<span class="productReviewNumber">${p.reviewCount}</span></span>
                    </div>
                </div>
            </div>
        </c:if>
    </c:forEach>
    <div style="clear:both"></div>
</div>