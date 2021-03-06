<%-- <%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>


<c:url value="/cart/miniCart/${totalDisplay}" var="refreshMiniCartUrl"/>
<c:url value="/cart/rollover/${component.uid}" var="rolloverPopupUrl"/>
<c:url value="/cart" var="cartUrl"/>

<a href="${cartUrl}" class="minicart">
	${component.title}
	<ycommerce:testId code="miniCart_items_label">
		<span class="count">${totalItems}</span>	
		<span class="price">
			<c:if test="${totalDisplay == 'TOTAL'}">
				<format:price priceData="${totalPrice}"/>
			</c:if>
			<c:if test="${totalDisplay == 'SUBTOTAL'}">
				<format:price priceData="${subTotal}"/>
			</c:if>
			<c:if test="${totalDisplay == 'TOTAL_WITHOUT_DELIVERY'}">
				<format:price priceData="${totalNoDelivery}"/>
			</c:if>
		</span>
	</ycommerce:testId>
</a>
<div id="miniCartLayer" class="miniCartPopup" data-refreshMiniCartUrl="${refreshMiniCartUrl}/?"  data-rolloverPopupUrl="${rolloverPopupUrl}" ></div>

 --%>
 
 
<!--  added by namrata -->
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>

<c:url value="/cart/miniCart/${totalDisplay}" var="refreshMiniCartUrl" />
<c:url value="/cart/rollover/${component.uid}" var="rolloverPopupUrl" />

<script id="miniCartTemplate" type="text/x-jquery-tmpl">
/*<![CDATA[*/
	<dt><ycommerce:testId code="miniCart_items_label"><spring:theme text="items" code="cart.items" arguments="{{= totalItems}}"/></ycommerce:testId> - </dt>
	<dd><ycommerce:testId code="miniCart_total_label">
			<c:if test="${totalDisplay == 'TOTAL'}">{{= totalPrice.formattedValue}}</c:if>
			<c:if test="${totalDisplay == 'SUBTOTAL'}">{{= subTotal.formattedValue}}</c:if>
			<c:if test="${totalDisplay == 'TOTAL_WITHOUT_DELIVERY'}">{{= totalNoDelivery.formattedValue}}</c:if>
	</ycommerce:testId></dd>
/*]]>*/
</script>

<script type="text/javascript"> // set vars
var rolloverPopupUrl = '${rolloverPopupUrl}';
var refreshMiniCartUrl = '${refreshMiniCartUrl}/?';
</script>


<div id="cart_header" class="dropdown">
	<div data-toggle="dropdown" class="dropdown-toggle cart_content">
		<c:url value="/cart" var="cartUrl" />
		<a href="javascript:void(0)"> <i class="fa fa-shopping-cart"></i>
		</a>
		<div id="minicart_data" class="minicart_data">

			<ycommerce:testId code="miniCart_items_label">
				<spring:theme text="items" code="cart.items" arguments="${totalItems}" />
			</ycommerce:testId>
			- <span> <ycommerce:testId code="miniCart_total_label">
					<c:if test="${totalDisplay == 'TOTAL'}">
						<format:price priceData="${totalPrice}" />
					</c:if>
					<c:if test="${totalDisplay == 'SUBTOTAL'}">
						<format:price priceData="${subTotal}" />
					</c:if>
					<c:if test="${totalDisplay == 'TOTAL_WITHOUT_DELIVERY'}">
						<format:price priceData="${totalNoDelivery}" />
					</c:if>
				</ycommerce:testId>
			</span>
		</div>
	</div>
	<cart:rolloverCartPopup />
</div>