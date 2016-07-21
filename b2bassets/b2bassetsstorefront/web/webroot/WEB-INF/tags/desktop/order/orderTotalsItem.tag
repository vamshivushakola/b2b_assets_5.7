<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="order" required="true" type="de.hybris.platform.commercefacades.order.data.OrderData" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>

<%@ attribute name="containerCSS" required="false" type="java.lang.String" %>

<div class="totals">
	<table id="shopping-cart-totals-table" class="data-table">
	<%-- <tfoot>
		<tr>
			<td><spring:theme code="text.account.order.total" text="Total:"/></td>
			<c:choose>
				<c:when test="${order.net}">
					<td><format:price priceData="${order.totalPriceWithTax}"/></td>
				</c:when>
				<c:otherwise>
					<td><format:price priceData="${order.totalPrice}"/></td>
				</c:otherwise>
			</c:choose>
		</tr>
	</tfoot> --%>
		<tfoot>
			<c:choose>
				<c:when test="${order.net}">
					<tr class="grand-total last">
						<td class="a-right" colspan="1"><strong><spring:theme code="text.account.order.netTax" text="Tax:" /></strong></td>
						<td class="a-right total-price last"><strong><span class="price"><format:price priceData="${order.totalTax}" /></span></strong></td>
					</tr>
					<tr class="grand-total last">
						<td class="a-right" colspan="1"><strong><spring:theme code="text.account.order.total" text="Total:" /></strong></td>
						<td class="a-right total-price last"><strong><span class="price"><format:price priceData="${order.totalPriceWithTax}" /></span></strong></td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr class="grand-total last">
						<td class="a-right" colspan="1"><strong><spring:theme code="text.account.order.total" text="Total:" /></strong></td>
						<td class="a-right total-price last"><strong><span class="price"><format:price priceData="${order.totalPriceWithTax}" /></span></strong></td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tfoot>
	<%-- <tbody>
		<tr>
			<td><spring:theme code="text.account.order.subtotal" text="Subtotal:"/></td>
			<td><format:price priceData="${order.subTotal}"/></td>
		</tr>
		<c:if test="${order.totalDiscounts.value > 0}">
			<tr class="savings">
				<td><spring:theme code="text.account.order.savings" text="Savings:"/></td>
				<td><format:price priceData="${order.totalDiscounts}"/></td>
			</tr>
		</c:if>
		
		<tr>
			<td><spring:theme code="text.account.order.delivery" text="Delivery:"/></td>
			<td><format:price priceData="${order.deliveryCost}" displayFreeForZero="true"/></td>
		</tr>
		
		
		<c:if test="${order.net}" >
			<tr>
				<td><spring:theme code="text.account.order.netTax" text="Tax:"/></td>
				<td><format:price priceData="${order.totalTax}"/></td>
			</tr>
		</c:if>

	</tbody> --%>
	<tbody>
			<tr>
				<td class="a-right" colspan="1"><spring:theme code="text.account.order.subtotal" text="Subtotal:" /></td>
				<td class="a-right last"><span class="price"><format:price priceData="${order.subTotal}" /></span></td>
			</tr>
			<c:if test="${order.totalDiscounts.value > 0}">
				<tr>
					<td class="a-right" colspan="1"><spring:theme code="text.account.order.savings" text="Savings:" /></td>
					<td class="a-right last"><span class="price"><format:price priceData="${order.totalDiscounts}" /></span></td>
				</tr>
			</c:if>
			<tr>
				<td class="a-right" colspan="1"><spring:theme code="text.account.order.delivery" text="Delivery:" /></td>
				<td class="a-right last"><span class="price"><format:price priceData="${order.deliveryCost}" displayFreeForZero="true" /></span></td>
			</tr>
			<c:if test="${not empty cartData.deliveryCost}">
				<tr>
					<td class="a-right" colspan="1"><spring:theme code="basket.page.totals.delivery" /></td>
					<td class="a-right last"><span class="price"><format:price priceData="${cartData.deliveryCost}" displayFreeForZero="TRUE" /></span></td>
				</tr>
			</c:if>
			<c:if test="${cartData.net && cartData.totalTax.value > 0}">
				<tr>
					<td class="a-right" colspan="1"><spring:theme code="basket.page.totals.netTax" /></td>
					<td class="a-right last"><span class="price"><format:price priceData="${cartData.totalTax}" /></span></td>
				</tr>
			</c:if>
		</tbody>	
</table>
</div>
<c:if test="${not order.net}">
	<p>
		<spring:theme code="text.account.order.includesTax" text="Your order includes {0} tax" arguments="${order.totalTax.formattedValue}" argumentSeparator="###" />
	</p>
</c:if>
