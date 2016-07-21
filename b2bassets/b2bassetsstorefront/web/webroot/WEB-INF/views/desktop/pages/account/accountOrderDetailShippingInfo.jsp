<%-- <%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/desktop/order" %>

<div class="orderBoxes clearfix">
	<order:deliveryAddressItem order="${orderData}"/>
	<order:deliveryMethodItem order="${orderData}"/>
	<div class="orderBox billing">
		<order:billingAddressItem order="${orderData}"/>
	</div>
	<c:if test="${not empty orderData.paymentInfo}">
		<div class="orderBox payment">
			<order:paymentDetailsItem order="${orderData}"/>
		</div>
	</c:if>
</div> --%>

<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/desktop/order" %>
<%@ taglib prefix="b2b-order" tagdir="/WEB-INF/tags/addons/b2bacceleratoraddon/desktop/order" %>

<!-- copied from b2bassetsstorefront\web\webroot\WEB-INF\views\addons\b2bacceleratoraddon\desktop\pages\account\accountOrderDetailShippingInfo.jsp  -->

 <div class="row" >
 	<div class="col-sm-6">
		<order:deliveryAddressItem order="${orderData}"/>
	</div>
	<div class="col-sm-6">
		<order:deliveryMethodItem order="${orderData}"/>
	</div>
	
	<c:if test="${orderData.paymentType.code eq 'CARD'}">
		<div class="orderBox billing">
			<order:billingAddressItem order="${orderData}"/>
		</div>
		<div class="orderBox payment">
			<order:paymentDetailsItem order="${orderData}"/>
		</div>
	</c:if>
	<c:if test="${orderData.paymentType.code eq 'ACCOUNT'}">
		<div class="col-sm-6" >
			<b2b-order:paymentDetailsAccountItem order="${orderData}"/>
		</div>
	</c:if>
	
</div>