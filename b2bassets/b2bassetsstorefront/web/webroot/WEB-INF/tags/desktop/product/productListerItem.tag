<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="storepickup" tagdir="/WEB-INF/tags/desktop/storepickup" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="action" tagdir="/WEB-INF/tags/desktop/action" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<spring:theme code="text.addToCart" var="addToCartText"/>
<c:url value="${product.url}" var="productUrl"/>

<c:set value="${not empty product.potentialPromotions}" var="hasPromotion"/>

<%-- <div class="productListItem${hasPromotion ? ' productListItemPromotion' : ''}"> --%>
<li class="item clear_fix">
	<ycommerce:testId code="test_searchPage_wholeProduct">
	<div class="product-image">
		<a href="${productUrl}" title="<c:out value='${product.name}' />" class="productMainLink">
			<product:productPrimaryImage product="${product}" format="product"/> 
		</a>
		
		<ycommerce:testId code="searchPage_price_label_${product.code}">
		<div class="price-box"><product:productListerItemPrice product="${product}" /></div>
		</ycommerce:testId>
	
		<c:if test="${not empty product.averageRating}">
			<product:productStars rating="${product.averageRating}"/>
		</c:if>
	</div>
	
	<div class="product-shop">
		<ycommerce:testId code="searchPage_productName_link_${product.code}">
			<h2 class="product-name">
				<a href="${productUrl}" title="<c:out value='${product.name}' />" class="productMainLink">
					<c:out value='${product.name}' />
				</a>
			</h2>
		</ycommerce:testId>
		
		<div class="desc std">
			<c:if test="${not empty product.summary && not skipSummary }">
				<p>${product.summary}</p>
				<p>
					<product:productListerClassifications product="${product}"/>
				</p>
			</c:if>
			
			<c:if test="${not empty product.potentialPromotions and not empty product.potentialPromotions[0].productBanner}">
					<img class="promo" src="${product.potentialPromotions[0].productBanner.url}" alt="${product.potentialPromotions[0].description}" title="${product.potentialPromotions[0].description}"/>
			</c:if>
		</div>
	</div>
	
	<div class="product-shop">
        <c:choose>
            <%-- Verify if products is a multidimensional product --%>
            <c:when test="${product.multidimensional}">
				<div class="viewDetailButton margin-bottom-10">
                  <c:url var="backToProductUrl" value="${productUrl}" />
                  <a href="${backToProductUrl}" class="positive" >
                  <spring:theme code="product.view.details" /></a>
               </div>
                 <sec:authorize ifAnyGranted="ROLE_CUSTOMERGROUP">
                 	<div>
                  	<c:url var="productOrderFormUrl" value="${product.url}/orderForm"/>
                      <a href="${productOrderFormUrl}"  class="positive">
                      	<spring:theme code="order.form" />
                      </a>
			</div>
                 </sec:authorize>
            </c:when>
            <c:otherwise>
                <ycommerce:testId code="searchPage_addToCart_button_${product.code}">
           		<c:set var="buttonType">submit</c:set>
                <c:if test="${product.stock.stockLevelStatus.code eq 'outOfStock' or empty product.price}">
                   <c:set var="buttonType">button</c:set>
                   <spring:theme code="text.addToCart.outOfStock" var="addToCartText"/>
               </c:if>
               <c:if test="${empty isOrderForm || not isOrderForm}">
	               <div class="product-stock">
						<form id="addToCartForm${product.code}" action="<c:url value="/cart/add"/>" method="post" class="add_to_cart_form">
							<input type="hidden" name="productCodePost" value="${product.code}" />
							<button type="${buttonType}" class="add_to_cart_button positive large <c:if test="${fn:contains(buttonType, 'button')}">out-of-stock</c:if>" <c:if test="${fn:contains(buttonType, 'button')}">disabled="true" aria-disabled="true"</c:if>>
								<theme:image code="img.addToCartIcon" alt="${addToCartText}" title="${addToCartText}" />
								${addToCartText}
							</button>
						</form>
					</div>
               </c:if>
               
               <c:if test="${not empty isOrderForm && isOrderForm}">
					<input type=hidden id="productPrice[${sessionScope.skuIndex}]" value="${product.price.value}" />
					<input type="hidden" class="${product.code} sku" name="productQuantities[${sessionScope.skuIndex}].sku" id="productQuantities[${sessionScope.skuIndex}].sku" value="${product.code}" />
					<div class="add-to-cart">
						<%-- <label for="qty"><spring:theme code="basket.page.quantity" /></label> --%>
						<input type="text" maxlength="3" size="1" id="productQuantities[${sessionScope.skuIndex}].quantity" name="productQuantities[${sessionScope.skuIndex}].quantity" class="sku-quantity" value="0">
						<button type="button" class="button-arrow button-up">Increase</button>
						<button type="button" class="button-arrow button-down">Decrease</button>
					</div>
					<c:if test="${isOrderForm and !product.multidimensional}">
						<span class="prod prod_results">
						 <product:productFutureAvailability product="${product}" futureStockEnabled="${futureStockEnabled}" />
						</span>
					</c:if>
					<c:set var="skuIndex" scope="session" value="${sessionScope.skuIndex + 1}" />
				</c:if>
       			</ycommerce:testId>
            </c:otherwise>
        </c:choose>
	</div>	
		
		<%-- <a href="${productUrl}" title="${product.name}" class="productMainLink">

			<c:set var="product" value="${product}" scope="request"/>
			<c:set var="addToCartText" value="${addToCartText}" scope="request"/>
			<c:set var="addToCartUrl" value="${addToCartUrl}" scope="request"/>
			
			<c:if test="${not product.multidimensional}">
				<div id="actions-container-for-${component.uid}" class="listAddPickupContainer clearfix">
					<action:actions element="div" parentComponent="${component}"/>
				</div>
			</c:if>
		
            </a> --%>
          
        </ycommerce:testId>
</li>
<!-- </div> -->

