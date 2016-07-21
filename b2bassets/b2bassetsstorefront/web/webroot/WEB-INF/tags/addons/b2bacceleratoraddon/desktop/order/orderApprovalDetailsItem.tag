<%-- <%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="order" required="true"
	type="de.hybris.platform.commercefacades.order.data.AbstractOrderData"%>
<%@ attribute name="isOrderDetailsPage" type="java.lang.Boolean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>


<div class="orderList">
	<div class="headline"><spring:theme code="text.account.order.approval.title" /></div>
	<table class="orderListTable">
		<thead>
			<tr>
				<th><spring:theme code="text.account.order.permission.title" /></th>
				<th><spring:theme code="text.account.order.approver.title" /></th>
				<th><spring:theme code="text.account.order.status.title" /></th>
				<th><spring:theme code="text.account.order.permission.notes"/></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${order.b2bPermissionResult}"
				var="b2bPermissionResult">
				<tr>
					<td>${b2bPermissionResult.permissionTypeData.name}</td>
					<td>${b2bPermissionResult.approver}</td>
					<td>${b2bPermissionResult.statusDisplay}</td>
					<td>
						<c:choose>
						   <c:when test="${not empty b2bPermissionResult.approverNotes}">
						   		${b2bPermissionResult.approverNotes}
							</c:when>
							<c:otherwise>
								<span><spring:theme code="text.notAvailable"/></span>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div> --%>

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

<h2 class="subtitle">
	<spring:theme code="text.account.order.approval.title" />
</h2>
<table id="approval-details-item" class="data-table">
	<thead>
		<tr class="hidden-xs">
			<th><span><spring:theme code="text.account.order.permission.title" /></span></th>
			<th><span><spring:theme code="text.account.order.approver.title" /></span></th>
			<th><span><spring:theme code="text.account.order.status.title" /></span></th>
			<th><span><spring:theme code="text.account.order.permission.notes" /></span></th>
		</tr>
		<tr class="hidden-lg hidden-md hidden-sm">
			<th><span><spring:theme code="text.account.order.permission.title" /></span></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${order.b2bPermissionResult}" var="b2bPermissionResult" varStatus="loop">
			<tr class="${loop.last ? 'last' : ''}">
				<td><span class="hidden-lg hidden-md hidden-sm mobile-label"><spring:theme code="text.account.order.permission.title" /> : </span>${b2bPermissionResult.permissionTypeData.name}</td>
				<td><span class="hidden-lg hidden-md hidden-sm mobile-label"><spring:theme code="text.account.order.approver.title" /> : </span>${b2bPermissionResult.approver}</td>
				<td><span class="hidden-lg hidden-md hidden-sm mobile-label"><spring:theme code="text.account.order.status.title" /> : </span>${b2bPermissionResult.statusDisplay}</td>
				<td class="last">
				<span class="hidden-lg hidden-md hidden-sm mobile-label"><spring:theme code="text.account.order.permission.notes" /> : </span>
				<c:choose>
						<c:when test="${not empty b2bPermissionResult.approverNotes}">
							   		${b2bPermissionResult.approverNotes}
								</c:when>
						<c:otherwise>
							<span><spring:theme code="text.notAvailable" /></span>
						</c:otherwise>
					</c:choose></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
