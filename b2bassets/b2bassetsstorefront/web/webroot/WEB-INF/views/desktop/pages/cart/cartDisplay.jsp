<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>


<c:if test="${not empty cartData.entries}">
	<%-- <button id="checkoutButtonTop" class="doCheckoutBut positive right continueCheckout" type="button" data-checkout-url="${checkoutUrl}">
		<spring:theme code="checkout.checkout" />
	</button> --%>
<%-- 	<a href="${checkoutUrl}" class="top-button-cart positive right checkout-cart"> <spring:theme code="checkout.checkout" /> </a>--%>
	
	<cart:cartItems cartData="${cartData}"/>
</c:if>
