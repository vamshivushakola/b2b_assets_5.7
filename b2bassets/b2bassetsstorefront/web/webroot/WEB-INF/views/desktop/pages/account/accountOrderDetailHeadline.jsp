<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/desktop/order" %>
<%@ taglib prefix="b2b-order" tagdir="/WEB-INF/tags/addons/b2bacceleratoraddon/desktop/order" %>

<%-- <div class="headline">
	<spring:theme code="text.account.order.title.details"/>
</div> --%>
		<div class="row">
			<div class="col-sm-6">
			<!-- Deepali: Commented class="orderBox payment and class="orderBox billing" 
			and added repositioned div to fix UI issues in case of order with payment type 'CREDIT CARD'-->
			
				<c:if test="${orderData.paymentType.code eq 'CARD'}">
<!-- 				<div class="orderBox payment"> -->
					<order:paymentDetailsItem order="${orderData}"/>
<!-- 				</div> -->
				</c:if>
				<c:if test="${orderData.paymentType.code eq 'ACCOUNT'}">
					<b2b-order:paymentDetailsAccountItem order="${orderData}"/>
				</c:if>
			</div>
			<div class="col-sm-6">
				<order:deliveryAddressItem order="${orderData}"/>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-6">
				<order:deliveryMethodItem order="${orderData}"/>
			</div>
			<c:if test="${orderData.paymentType.code eq 'CARD'}">
				<div class="col-sm-6">
<!-- 				<div class="orderBox billing"> -->
					<order:billingAddressItem order="${orderData}"/>
<!-- 				</div> -->
				</div>
			</c:if>
		</div>
