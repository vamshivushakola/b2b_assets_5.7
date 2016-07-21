<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="order" required="true" type="de.hybris.platform.commercefacades.order.data.AbstractOrderData"%>
<%@ attribute name="isOrderDetailsPage" type="java.lang.Boolean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%-- <%@ taglib prefix="order" tagdir="/WEB-INF/tags/desktop/order"%> --%>

<table id="your_cart" class="data-table">
	<tbody>
		<c:forEach items="${order.entries}" var="entry" varStatus="loop">
						
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
