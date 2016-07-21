<%-- <%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>

<spring:theme code="text.addToCart" var="addToCartText"/>
<spring:theme code="text.popupCartTitle" var="popupCartTitleText"/>
<c:url value="/cart" var="cartUrl"/>
<c:url value="/cart/checkout" var="checkoutUrl"/>
cartttttttttttttttttttttttttttttttttttttttttttttt
<div class="title">
	<a href="#" id="ajax_cart_close" title="<spring:theme code="popup.close"/>" alt="<spring:theme code="popup.close"/>"><i class="fa fa-close"></i></a>
</div>


<c:if test="${numberShowing > 0 }">
	<div class="cart_modal_popup empty-popup-cart legend">
		<spring:theme code="popup.cart.showing" arguments="${numberShowing},${numberItemsInCart}"/>
		<c:if test="${numberItemsInCart > numberShowing}">
			<a class="show-all" href="${cartUrl}">Show All</a>
		</c:if>
	</div>
</c:if>


<c:if test="${empty numberItemsInCart or numberItemsInCart eq 0}">
	<div class="cart_modal_popup empty-popup-cart">
		<spring:theme code="popup.cart.empty"/>
	</div>
</c:if>
<c:if test="${numberShowing > 0 }">
	<ul class="itemList">
	<c:forEach items="${entries}" var="entry" end="${numberShowing - 1}">
		<c:url value="${entry.product.url}" var="entryProductUrl"/>
		<li class="popupCartItem cart_modal_popup product-popup">
<!-- 			<div class="itemThumb"> -->
					<a href="${entryProductUrl}" class="product-image">
						<product:productPrimaryImage product="${entry.product}" format="cartIcon"/>
					</a>
<!-- 			</div> -->
<!-- 			<div class="itemDesc"> -->
				<div class="prod_info">
					<p class="prod_name">
						<a href="${entryProductUrl}">${entry.product.name}</a>
					</p>
					${entry.quantity} x <span class="product_price"><format:price priceData="${entry.basePrice}" /></span>
				<div class="itemQuantity"><span class="label">
				<spring:theme code="popup.cart.quantity"/></span>${entry.quantity}
				</div>
				
				<p class="prod_options">
					<c:forEach items="${entry.product.baseOptions}" var="baseOptions">
						<c:forEach items="${baseOptions.selected.variantOptionQualifiers}" var="baseOptionQualifier">
							<c:if test="${baseOptionQualifier.qualifier eq 'style' and not empty baseOptionQualifier.image.url}">
<!-- 								<div class="itemColor"> -->
									<span class="label"><spring:theme code="product.variants.colour"/></span>
									<span class="prod_color"> 
										<span class="product-variant-label">
											<spring:theme code="product.variants.colour" />
										</span> 
										<img src="${baseOptionQualifier.image.url}" alt="${baseOptionQualifier.value}" title="${baseOptionQualifier.value}" />
									</span>
									<img src="${baseOptionQualifier.image.url}" alt="${baseOptionQualifier.value}" title="${baseOptionQualifier.value}"/>
<!-- 								</div> -->
								</c:if>
								<c:if test="${baseOptionQualifier.qualifier eq 'size'}">
								<div class="itemSize">
									<span class="label"><spring:theme code="product.variants.size"/></span>
									${baseOptionQualifier.value}
								</div>
								
								<span class="prod_size">
									<span class="product-variant-label">
										<spring:theme code="product.variants.size" />
									</span>
									${baseOptionQualifier.value}
								</span>
							</c:if>
						</c:forEach>
					</c:forEach>
				</p>
				<c:if test="${not empty entry.deliveryPointOfService.name}">
					<div class="itemPickup"><span class="itemPickupLabel"><spring:theme code="popup.cart.pickup"/></span>${entry.deliveryPointOfService.name}</div>
				</c:if>
				
				<c:choose>
					<c:when test="${not entry.product.multidimensional or (entry.product.priceRange.minPrice.value eq entry.product.priceRange.maxPrice.value)}" >
						<div class="itemPrice"><format:price priceData="${entry.basePrice}"/></div>
					</c:when>
					<c:otherwise>
						<div class="itemPrice">
							<format:price priceData="${entry.product.priceRange.minPrice}"/>
							-
							<format:price priceData="${entry.product.priceRange.maxPrice}"/>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</li>
	</c:forEach>
	</ul>
</c:if>

<div  class="total">
	<spring:theme code="popup.cart.total"/>&nbsp;<span class="right"><format:price priceData="${cartData.totalPrice}"/></span>
</div>
<div  class="banner">
	<c:if test="${not empty lightboxBannerComponent && lightboxBannerComponent.visible}">
			<cms:component component="${lightboxBannerComponent}" evaluateRestriction="true"  />
	</c:if>
</div>

<div class="links">
	<a href="${cartUrl}" class="button positive"><spring:theme code="checkout.checkout" /></a>
</div>

 
 <div class="price-wrapper">
	<c:if test="${numberShowing > 0}">
		<div class="prod_cart-total">
			<span class="price-label"><spring:theme code="popup.cart.total" /> : </span> <span class="price"><format:price priceData="${cartData.totalPrice}" /></span>
		</div>
	</c:if>
	<c:if test="${numberShowing > 0}">
		<a href="${cartUrl}" class="positive large"> <spring:theme code="checkout.checkout" />
		</a>
	</c:if>
</div>

 --%>
 <%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>

<spring:theme code="text.addToCart" var="addToCartText" />
<spring:theme code="text.popupCartTitle" var="popupCartTitleText" />
<c:url value="/cart" var="cartUrl" />
<c:url value="/cart/checkout" var="checkoutUrl" />

<div class="title">
	<a href="#" id="ajax_cart_close" title="<spring:theme code="popup.close"/>" alt="<spring:theme code="popup.close"/>"><i class="fa fa-close"></i></a>
</div>

<c:if test="${numberShowing > 0 }">
	<div class="cart_modal_popup empty-popup-cart">
		<spring:theme code="popup.cart.showing" arguments="${numberShowing},${numberItemsInCart}" />
		<c:if test="${numberItemsInCart > numberShowing}">
			<a class="show-all" href="${cartUrl}">Show All</a>
		</c:if>
	</div>
</c:if>
<c:if test="${empty numberItemsInCart or numberItemsInCart eq 0}">
	<div class="cart_modal_popup empty-popup-cart">
		<spring:theme code="popup.cart.empty" />
	</div>
</c:if>
<c:if test="${numberShowing > 0 }">
	<ul>
		<c:forEach items="${entries}" var="entry" end="${numberShowing - 1}">
			<c:url value="${entry.product.url}" var="entryProductUrl" />
			<li class="cart_modal_popup product-popup"><a href="${entryProductUrl}" class="product-image"> <product:productPrimaryImage product="${entry.product}" format="cartIcon" /></a>
				<div class="prod_info">
					<p class="prod_name">
						<a href="${entryProductUrl}">${entry.product.name}</a>
					</p>
					${entry.quantity} x <span class="product_price"><format:price priceData="${entry.basePrice}" /></span>
					<p class="prod_options">
						<c:forEach items="${entry.product.baseOptions}" var="baseOptions">
							<c:forEach items="${baseOptions.selected.variantOptionQualifiers}" var="baseOptionQualifier">
								<c:if test="${baseOptionQualifier.qualifier eq 'style' and not empty baseOptionQualifier.image.url}">
									<span class="prod_color"> <span class="product-variant-label"><spring:theme code="product.variants.colour" /></span> <img src="${baseOptionQualifier.image.url}" alt="${baseOptionQualifier.value}" title="${baseOptionQualifier.value}" />
									</span>
								</c:if>
								<c:if test="${baseOptionQualifier.qualifier eq 'size'}">
									<span class="prod_size"><span class="product-variant-label"><spring:theme code="product.variants.size" /></span>${baseOptionQualifier.value}</span>
								</c:if>
							</c:forEach>
						</c:forEach>
					</p>
				</div></li>
		</c:forEach>
	</ul>
</c:if>
<div class="price-wrapper">
	<c:if test="${numberShowing > 0}">
		<div class="prod_cart-total">
			<span class="price-label"><spring:theme code="popup.cart.total" /> : </span> <span class="price"><format:price priceData="${cartData.totalPrice}" /></span>
		</div>
	</c:if>
	<c:if test="${numberShowing > 0}">
		<a href="${cartUrl}" class="positive large"> <spring:theme code="checkout.checkout" />
		</a>
	</c:if>
</div>
 