<%-- <%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component" %>

<c:choose>
	<c:when test="${not empty productData}">
		<div class="scroller">
			<div class="title">${title}</div>

			<c:choose>
				<c:when test="${component.popup}">
					<ul class="carousel jcarousel-skin popup">
						<c:forEach items="${productData}" var="product">

							<c:url value="${product.url}/quickView" var="productQuickViewUrl"/>
							<li>
								<a href="${productQuickViewUrl}" class="popup scrollerProduct">
									<div class="thumb">
										<product:productPrimaryImage product="${product}" format="product"/>
									</div>
									
									<div class="priceContainer">
										<format:fromPrice priceData="${product.price}"/>
									</div>
									<div class="details">
											${product.name}
									</div>
								</a>
							</li>
						</c:forEach>
					</ul>
				</c:when>
				<c:otherwise>
					<ul class="carousel jcarousel-skin">
						<c:forEach items="${productData}" var="product">

							<c:url value="${product.url}" var="productUrl"/>
							<li>
								<a href="${productUrl}" class="scrollerProduct">
									<div class="thumb">
										<product:productPrimaryImage product="${product}" format="product"/>
									</div>
									<div class="priceContainer">
										<format:fromPrice priceData="${product.price}"/>
									</div>
									<div class="details">
											${product.name}
									</div>
									
								</a>
							</li>
						</c:forEach>
					</ul>
				</c:otherwise>
			</c:choose>
		</div>
	</c:when>

	<c:otherwise>
		<component:emptyComponent/>
	</c:otherwise>
</c:choose>
 --%>
 
<!-- added by namrata -->
 <%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<spring:theme code="text.addToCart" var="addToCartText" />
<c:set var="buttonType">submit</c:set>
<c:if test="${not empty productData}">
	<div class="featured-products">
		<h2 class="subtitle">
			<span class="inline-title">${title}</span>
			<div class="line"></div>
		</h2>
		<c:choose>
			<c:when test="${component.popup}">
				<ul class="carousel jcarousel-skin popup">
					<c:forEach items="${productData}" var="product">
						<c:url value="${product.url}/quickView" var="productQuickViewUrl" />
						<li><a href="${productQuickViewUrl}" class="popup"> <span> <product:productPrimaryImage product="${product}" format="thumbnail" />
							</span>
								<h3>${product.name}</h3>
								<p>
									<format:fromPrice priceData="${product.price}" />
								</p>
						</a></li>
					</c:forEach>
				</ul>
			</c:when>
			<c:otherwise>
				<div id="products-grid" class="products-grid owl-carousel">
					<c:forEach items="${productData}" var="product">
						<c:url value="${product.url}" var="productUrl" />

						<div>
							<a href="${productUrl}">
								<div class="productImage">
									<div class="price-box">
										<p class="price">
											<format:fromPrice priceData="${product.price}" />
										</p>
									</div>
									<product:productPrimaryImage product="${product}" format="thumbnail" />
								</div>
								<h3 class="product-name">${product.name}</h3> <c:if test="${empty isOrderForm || not isOrderForm}">
									<form id="addToCartForm${product.code}" action="<c:url value="/cart/add"/>" method="post" class="add_to_cart_form">
										<input type="hidden" name="productCodePost" value="${product.code}" />
										<button type="${buttonType}" disabled="true" class="add_to_cart_button positive large <c:if test="${fn:contains(buttonType, 'button')}">out-of-stock</c:if>" <c:if test="${fn:contains(buttonType, 'button')}">disabled="true" aria-disabled="true"</c:if>>${addToCartText}</button>
										<input type="hidden" name="qty" class="qty" value="1" />
									</form>
								</c:if>
							</a>
						</div>
					</c:forEach>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</c:if>
 