<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="order" required="true" type="de.hybris.platform.commercefacades.order.data.OrderData" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- <div class="span-19 delivery_stages"> -->
	<h2 class="subtitle">
		<spring:theme code="text.account.orderHistory.replenishment"/>
	</h2>
	<div class="description">
		<spring:theme code="text.account.orderHistory.replenishment.startFrom" arguments="${order.triggerData.activationTime}"/>
	</div>
	<div class="description">
		<spring:theme code="text.account.orderHistory.replenishment.schedule" arguments="${order.triggerData.displayTimeTable}"/>
	</div>
	<div class="description">
		<c:url value="/my-account/my-replenishment/${order.jobCode}" var="scheduleUrl"/>
		<a href="${scheduleUrl}"><spring:theme code="text.account.orderHistory.replenishment.link"/></a>
	</div>
<!-- </div> -->
