<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/addons/b2bacceleratoraddon/desktop/order" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- <div> -->
<%-- 	<c:if test="${not empty orderData.b2bPermissionResult}"> --%>
	<c:if test="${isOrderDetailsPage eq true }">
		<c:if test="${not empty order.b2bPermissionResult}">
		<order:orderApprovalDetailsItem order="${orderData}"/>
		</c:if>
	</c:if>
<!-- </div> -->