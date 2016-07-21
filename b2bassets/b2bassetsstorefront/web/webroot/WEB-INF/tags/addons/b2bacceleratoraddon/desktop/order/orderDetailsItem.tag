<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="order" required="true" type="de.hybris.platform.commercefacades.order.data.OrderData" %>
<%@ attribute name="isOrderDetailsPage" type="java.lang.Boolean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="grid" tagdir="/WEB-INF/tags/desktop/grid" %>


<%-- <div class="orderList">
	<div class="headline"><spring:theme code="basket.page.title.yourDeliveryItems"/></div> --%>

	<%-- <table id="your_cart" class="data-table">
		<thead>
			<tr class="hidden-xs hidden-sm">
				<th colspan="2"><spring:theme code="text.productDetails"/></th>
				<th><spring:theme code="text.itemPrice" /></th>
				<th><spring:theme code="text.quantity"/></th>		
				<th><spring:theme code="text.total"/></th>
				<th>&nbsp;</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${order.entries}" var="entry" varStatus="loop">
				<c:url value="${entry.product.url}" var="productUrl"/>
				<tr class="item">
					<td headers="header2" class="thumb">
						<a href="${productUrl}">
							<product:productPrimaryImage product="${entry.product}" format="thumbnail"/>
						</a>
					</td>
					
					<td headers="header2" class="details">
						<ycommerce:testId code="orderDetails_productName_link">
							<div class="itemName"><a href="${entry.product.purchasable ? productUrl : ''}">${entry.product.name}</a></div>
						</ycommerce:testId>

						<c:forEach items="${entry.product.baseOptions}" var="option">
							<c:if test="${not empty option.selected and option.selected.url eq entry.product.url}">
								<c:forEach items="${option.selected.variantOptionQualifiers}" var="selectedOption">
									<dl>
										<dt>${selectedOption.name}:</dt>
										<dd>${selectedOption.value}</dd>
									</dl>
								</c:forEach>
							</c:if>
						</c:forEach>
						
						<c:if test="${not empty order.appliedProductPromotions}">
							<ul>
								<c:forEach items="${order.appliedProductPromotions}" var="promotion">
									<c:set var="displayed" value="false"/>
									<c:forEach items="${promotion.consumedEntries}" var="consumedEntry">
										<c:if test="${not displayed && consumedEntry.orderEntryNumber == entry.entryNumber}">
											<c:set var="displayed" value="true"/>
											<li><span>${promotion.description}</span></li>
										</c:if>
									</c:forEach>
								</c:forEach>
							</ul>
						</c:if>
					</td>
					
					<td headers="header4" class="quantity">
                        <c:choose>
							<c:when test="${empty entry.entries}" >
								<ycommerce:testId code="orderDetails_productQuantity_label">${entry.quantity}</ycommerce:testId>
							</c:when>
							<c:otherwise>
								<span class="qty">
									<c:out value="${entry.quantity}" />
								</span>
                            </c:otherwise>
                        </c:choose>
					</td>
					
					<td headers="header5">
						<ycommerce:testId code="orderDetails_productItemPrice_label">
							<c:choose>
 								<c:when test="${not entry.product.multidimensional or (entry.product.priceRange.minPrice.value eq entry.product.priceRange.maxPrice.value)}">
									<format:price priceData="${entry.basePrice}" displayFreeForZero="true"/>
								</c:when>
								<c:otherwise>
									<format:price priceData="${entry.product.priceRange.minPrice}" displayFreeForZero="true"/>
									-
									<format:price priceData="${entry.product.priceRange.maxPrice}" displayFreeForZero="true"/>
								</c:otherwise>
							</c:choose>
						</ycommerce:testId>
					</td>
					
					<td headers="header6" class="total">
						<ycommerce:testId code="orderDetails_productTotalPrice_label">
							<format:price priceData="${entry.totalPrice}" displayFreeForZero="true"/></ycommerce:testId>
					</td>
					
					<td headers="header7" class="multidimensional">
						<c:choose>
							<c:when test="${empty entry.entries}" >
							</c:when>
							<c:otherwise>                    
								<ycommerce:testId code="cart_product_updateQuantity">
									<a href="#" id="QuantityProductToggle" data-index="${loop.index}" class="showQuantityProduct updateQuantityProduct-toggle">+</a>
								</ycommerce:testId>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				
				<tr><td colspan="6"><div id="ajaxGrid${loop.index}" style="display: none"></div></td></tr>
				
				<c:if test="${not empty entry.entries}" >
                    <tr><th colspan="6">
                        <c:forEach items="${entry.entries}" var="currentEntry" varStatus="stat">
                            <c:set var="subEntries" value="${stat.first ? '' : subEntries}${currentEntry.product.code}:${currentEntry.quantity},"/>
                        </c:forEach>

                        <div style="display:none" id="grid${loop.index}" data-sub-entries="${subEntries}"> </div>
                    </th></tr>
                </c:if>

			</c:forEach>
		</tbody>
	</table> --%>

<!-- </div> -->
<table id="your_cart" class="data-table">
	<thead>
		<tr class="hidden-xs hidden-sm">
			<th><spring:theme code="text.product" text="Product" /></th>
			<th><spring:theme code="text.itemPrice" text="Item Price" /></th>
			<th><spring:theme code="text.quantity" text="Quantity" /></th>
			<th><spring:theme code="text.total" text="Total" /></th>
		</tr>
		<tr class="hidden-lg hidden-md last">
			<th colspan="4"><spring:theme code="text.product" text="Product" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${order.entries}" var="entry" varStatus="loop">
			<c:url value="${entry.product.url}" var="productUrl" />
			<tr class="${loop.last ? 'last' : ''}">
				<td class="column-name">
					<div class="product_image">
						<c:if test="${entry.offlineProduct eq true}">
							<product:productPrimaryImage product="${entry.product}" format="thumbnail" />
						</c:if>
						<c:if test="${entry.offlineProduct ne true}">
							<a href="${productUrl}"> <product:productPrimaryImage product="${entry.product}" format="thumbnail" /></a>
						</c:if>
					</div>
					<div class="product-shop">
						<h2 class="product-name">
							<ycommerce:testId code="orderDetails_productName_link">
								<a href="${entry.product.purchasable ? productUrl : ''}">${entry.product.name}</a>
							</ycommerce:testId>
						</h2>
						<c:forEach items="${entry.product.baseOptions}" var="option">
							<c:if test="${not empty option.selected and option.selected.url eq entry.product.url}">
								<c:forEach items="${option.selected.variantOptionQualifiers}" var="selectedOption">
									<dl>
										<dt>${selectedOption.name}:</dt>
										<dd>${selectedOption.value}</dd>
									</dl>
								</c:forEach>
							</c:if>
						</c:forEach>
						<c:if test="${not empty order.appliedProductPromotions}">
							<ul class="cart-promotions">
								<c:forEach items="${order.appliedProductPromotions}" var="promotion">
									<c:set var="displayed" value="false" />
									<c:forEach items="${promotion.consumedEntries}" var="consumedEntry">
										<c:if test="${not displayed && consumedEntry.orderEntryNumber == entry.entryNumber}">
											<c:set var="displayed" value="true" />
											<li><span>${promotion.description}</span></li>
										</c:if>
									</c:forEach>
								</c:forEach>
							</ul>
						</c:if>
					</div>
				</td>
				<td class="itemPrice a-center"><ycommerce:testId code="orderDetails_productItemPrice_label">
						<span class="hidden-lg hidden-md hidden-sm mobile-label"><spring:theme code="text.itemPrice" text="Item Price" /> : </span>
						<span class="price"><format:price priceData="${entry.basePrice}" displayFreeForZero="true" /></span>
					</ycommerce:testId></td>
				<td class="quantity a-center"><span class="hidden-lg hidden-md hidden-sm mobile-label"><spring:theme code="text.quantity" text="Quantity" /> : </span> <ycommerce:testId code="orderDetails_productQuantity_label">
						<span class="quantity-order">${entry.quantity}</span>
					</ycommerce:testId></td>

				<td class="total last a-center"><ycommerce:testId code="orderDetails_productTotalPrice_label">
						<span class="hidden-lg hidden-md hidden-sm mobile-label"><spring:theme code="text.total" text="Total" /></span>
						<span class="price-total-order"><format:price priceData="${entry.totalPrice}" displayFreeForZero="true" /></span>
					</ycommerce:testId>
				</td>
					
					
			</tr>
			<c:if test="${not empty entry.orderTracking}">
			<tr>
				<td colspan="4">
				<spring:theme code="text.account.orderHistory.page.shipping.info" arguments="${entry.product.name}" text="Shipping Infomration for " />
				<table id="your_cart" class="data-table">
				<thead>
					<tr class="hidden-xs hidden-sm">
						<th><spring:theme code="text.account.orderHistory.page.scheduledDate" text="Scheduled Date" /></th>
						<th><spring:theme code="text.account.orderHistory.page.scheduledQuantity" text="Scheduled Quantity" /></th>
						<th><spring:theme code="text.account.orderHistory.page.deliveredQuantity" text="Delivery Quantity" /></th>
						<th><spring:theme code="text.account.orderHistory.page.deliveryDate" text="Delivery Date" /></th>
						<th><spring:theme code="text.account.orderHistory.page.trackingNumber" text="Tracking Number" /></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<c:forEach items="${entry.orderTracking}" var="tracking">
							<td>${tracking.scheduledDate}</td>
							<td>${tracking.scheduledQty}</td>
							<td>${tracking.deliveryQty}</td>
							<td>${tracking.deliveryDate}</td>
							<td><spring:theme code="text.account.orderHistory.page.trackinglink" arguments="${tracking.trackingNumber},${tracking.trackingNumber}" text="Tracking Link" /></td>
						</c:forEach>
					</tr>
				</tbody>
				</table>
				</td>
			</tr>
			</c:if>
		</c:forEach>
	</tbody>
</table>
