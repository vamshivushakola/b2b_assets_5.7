<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="galleryImages" required="true" type="java.util.List" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<spring:theme code="text.addToCart" var="addToCartText"/>

<%-- <div class="productDetailsPanel">

	<product:productImagePanel product="${product}" galleryImages="${galleryImages}"/>

	<div class="span-10 productDescription last">
		<ycommerce:testId code="productDetails_productNamePrice_label_${product.code}">
			<product:productPricePanel product="${product}"/>
		</ycommerce:testId>
		<ycommerce:testId code="productDetails_productNamePrice_label_${product.code}">
			<h1>
				${product.name}
			</h1>
		</ycommerce:testId>

		<product:productReviewSummary product="${product}"/>


		<div class="summary">
			${product.summary}
		</div>

		<product:productPromotionSection product="${product}"/>

		<cms:pageSlot position="VariantSelector" var="component" element="div">
			<cms:component component="${component}"/>
		</cms:pageSlot>

		<cms:pageSlot position="AddToCart" var="component" element="div" class="span-10 last add-to-cart">
			<cms:component component="${component}"/>
		</cms:pageSlot>
	</div>

	<cms:pageSlot position="Section2" var="feature" element="div" class="span-8 section2 cms_disp-img_slot last">
		<cms:component component="${feature}"/>
	</cms:pageSlot>
</div> --%>

<div class="row">
	<div class="col-md-7 product-img-box clear_fix">
		<%-- <div class="more-images">
			<product:productImageCarousel galleryImages="${galleryImages}" />
		</div> --%>
<!-- 		<div class="product-image"> -->
			<product:productImagePanel product="${product}" galleryImages="${galleryImages}"/>		
<%-- 			<product:productPricePanel product="${product}"/> --%>
<!-- 		</div> -->
	</div>
	<div class="col-md-5 product-shop">
		<ycommerce:testId code="productDetails_productNamePrice_label_${product.code}">
			<div class="product-name">
				<h1>${product.name}</h1>
			</div>
			 
			 <%--  <c:if test="${ycommerce:isExtensionInstalled('exportPDF')}">
                <linkExport:exportProductTag product="${product}" />
            </c:if> --%>
		</ycommerce:testId>
		<product:productFutureAvailability product="${product}" futureStockEnabled="${futureStockEnabled}" />		
		<p class="prod_summary">${product.summary}</p>
		<c:choose>
			<c:when test="${!empty product.volumePrices}">
				<table class="volume-prices data-table">
					<thead>
						<th class="volume-prices-quantity"><spring:theme code="product.volumePrices.column.qa" /></th>
						<th class="volume-price-amount"><spring:theme code="product.volumePrices.column.price" /></th>
					</thead>
					<tbody>
						<c:forEach var="volPrice" items="${product.volumePrices}">
							<tr>
								<td class="volume-price-quantity"><c:choose>
										<c:when test="${empty volPrice.maxQuantity}">
								${volPrice.minQuantity}+
							</c:when>
										<c:otherwise>
								${volPrice.minQuantity}-${volPrice.maxQuantity}
							</c:otherwise>
									</c:choose></td>
								<td class="volume-price-amount">${volPrice.formattedValue}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
		</c:choose>
		<sec:authorize ifNotGranted="ROLE_CUSTOMERGROUP">
			<c:url value='/login' var="loginUrl" />
			<a class="positive" href="${loginUrl}"><spring:theme code="product.volumePrices.log.for.price" /></a>
		</sec:authorize>
		<product:productPromotionSection product="${product}" />
		<cms:pageSlot position="VariantSelector" var="component" element="div" class="span-8">
			<cms:component component="${component}" />
		</cms:pageSlot>
		<cms:pageSlot position="AddToCart" var="component" element="div">
			<cms:component component="${component}" />
		</cms:pageSlot>
		<product:productShareTag />
	</div>
</div>
