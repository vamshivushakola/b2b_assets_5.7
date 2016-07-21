<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/desktop/order" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- 
<h2 class="subtitle"><spring:theme code="text.yourOrder" text="Your Order" /></h2> --%>
<div class="accountOrderDetailOrderTotals clearfix">
	<%-- <div class="span-7">
		<ul>
		<li><spring:theme code="text.account.order.orderNumber" arguments="${orderData.code}"/></li>
		<li><spring:theme code="text.account.order.orderPlaced" arguments="${orderData.created}"/></li>
		<c:if test="${not empty orderData.statusDisplay}">
			<li><spring:theme code="text.account.order.status.display.${orderData.statusDisplay}" var="orderStatus"/></li>
			<li><spring:theme code="text.account.order.orderStatus" arguments="${orderStatus}"/></li>
		</c:if>
		</ul>
	</div> --%>
	<div class="span-7">
		<order:receivedPromotions order="${orderData}"/><p/>
	</div>
	<%-- <div class="row">
		<div class="col-sm-offset-7 col-sm-5">
			<order:orderTotalsItem order="${orderData}"/>
		</div>
	</div> --%>
</div>