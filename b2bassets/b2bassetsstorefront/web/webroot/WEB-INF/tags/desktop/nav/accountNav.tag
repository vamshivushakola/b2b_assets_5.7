<%-- <%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="selected" required="false" type="java.lang.String" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<div class="accountNav">
	
		<div class="headline"><spring:theme code="text.account.yourAccount" text="My Account"/></div>

			<ul>
				<li class='${selected eq 'profile' ? 'active' : ''}'>
					<c:url value="/my-account/profile" var="encodedUrl" />
					<ycommerce:testId code="myAccount_profile_navLink">
						<a href="${encodedUrl}"><spring:theme code="text.account.profile" text="Profile"/></a>
					</ycommerce:testId>
				</li>
				<li class='${selected eq 'address-book' ? 'active' : ''}'>
					<c:url value="/my-account/address-book" var="encodedUrl" />
					<ycommerce:testId code="myAccount_addressBook_navLink">
						<a href="${encodedUrl}"><spring:theme code="text.account.addressBook" text="Address Book"/></a>
					</ycommerce:testId>
				</li>
				<li class='${selected eq 'payment-details' ? 'active' : ''}'>
					<c:url value="/my-account/payment-details" var="encodedUrl" />
					<ycommerce:testId code="myAccount_paymentDetails_navLink">
						<a href="${encodedUrl}"><spring:theme code="text.account.paymentDetails" text="Payment Details"/></a>
					</ycommerce:testId>
				</li>
				<li class='${selected eq 'orders' ? 'active' : ''}'>
					<c:url value="/my-account/orders" var="encodedUrl" />
					<ycommerce:testId code="myAccount_orders_navLink">
						<a href="${encodedUrl}"><spring:theme code="text.account.orderHistory" text="Order History"/></a>
					</ycommerce:testId>
				</li>
			</ul>

</div>
 --%>
 
<!-- Added for migration -->
<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="selected" required="false" type="java.lang.String" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="block-content">
	<dl>
		<dt>
			<spring:theme code="text.account.yourAccount" text="Your Account" />
		</dt>
		<dd class='${selected eq 'profile' ? 'nav_selected' : ''}'>
					<c:url value="/my-account/profile" var="encodedUrl" />
					<ycommerce:testId code="myAccount_profile_navLink">
						<a href="${encodedUrl}"><spring:theme code="text.account.profile" text="Profile"/></a>
					</ycommerce:testId>
		</dd>
				<sec:authorize ifAllGranted="ROLE_B2BCUSTOMERGROUP">
			<dd class='${selected eq 'address-book' ? 'nav_selected' : ''}'>
						<c:url value="/my-account/address-book" var="encodedUrl" />
						<ycommerce:testId code="myAccount_addressBook_navLink">
							<a href="${encodedUrl}"><spring:theme code="text.account.addressBook" text="Address Book"/></a>
						</ycommerce:testId>
			</dd>
			<dd class='${selected eq 'payment-details' ? 'nav_selected' : ''}'>
						<c:url value="/my-account/payment-details" var="encodedUrl" />
						<ycommerce:testId code="myAccount_paymentDetails_navLink">
							<a href="${encodedUrl}"><spring:theme code="text.account.paymentDetails" text="Payment Details"/></a>
						</ycommerce:testId>
			</dd>
				</sec:authorize>
				<sec:authorize ifAllGranted="ROLE_B2BCUSTOMERGROUP">
			<dd class='${selected eq 'my-quotes' ? 'nav_selected' : ''}'>
						<c:url value="/my-account/my-quotes" var="encodedUrl" />
						<ycommerce:testId code="myAccount_orderquotes_navLink">
							<a href="${encodedUrl}"><spring:theme code="text.account.myQuotes" text="My Quotes"/></a>
						</ycommerce:testId>
			</dd>
			<dd class='${selected eq 'orders' || selected eq 'order' ? 'nav_selected' : ''}'>
						<c:url value="/my-account/orders" var="encodedUrl" />
						<ycommerce:testId code="myAccount_orders_navLink">
							<a href="${encodedUrl}"><spring:theme code="text.account.orderHistory" text="Order History"/></a>
						</ycommerce:testId>
			</dd>
			<dd class='${selected eq 'my-replenishment-orders' ? 'nav_selected' : ''}'>
						<c:url value="/my-account/my-replenishment" var="encodedUrl" />
						<ycommerce:testId code="myAccount_replenishment_navLink">
							<a href="${encodedUrl}"><spring:theme code="text.account.myReplenishment" text="My Replenishment Orders"/></a>
						</ycommerce:testId>
			</dd>
			<c:if test="${ycommerce:isExtensionInstalled('multicarts')}"> 
			<dd class='${selected eq 'my-carts' ? 'nav_selected' : ''}'>
						<c:url value="/multicarts" var="encodedUrl" />
						<ycommerce:testId code="myAccount_multicarts_navLink">
							<a href="${encodedUrl}"><spring:theme code="text.account.myCarts" text="My Carts"/></a>
						</ycommerce:testId>
			</dd>
			</c:if>
			<c:if test="${ycommerce:isExtensionInstalled('ordersearch')}"> 
			<dd class='${selected eq 'ordersearch' ? 'nav_selected' : ''}'>
						<c:url value="/orderSearch" var="encodedUrl" />
						<ycommerce:testId code="myAccount_ordersearch_navLink">
							<a href="${encodedUrl}"><spring:theme code="text.account.ordersearch" text="Search Cart(s)/Order(s)"/></a>
						</ycommerce:testId>
			</dd>
			</c:if>
					<%-- <li class='${selected eq 'quick-orders' ? 'nav_selected' : ''}'>
						<c:url value="/my-account/quick-orders" var="encodedUrl" />
						<ycommerce:testId code="myAccount_quickorders_navLink">
							<a href="${encodedUrl}"><spring:theme code="text.account.quickorders" text="Quick Orders"/></a>
						</ycommerce:testId>
					</li> --%>
				</sec:authorize>
				<sec:authorize ifAllGranted="ROLE_B2BAPPROVERGROUP">
			<dd class='${selected eq 'approval-dashboard' ? 'nav_selected' : ''}'>
						<c:url value="/my-account/approval-dashboard" var="encodedUrl" />
						<ycommerce:testId code="myAccount_orderdashboard_navLink">
							<a href="${encodedUrl}"><spring:theme code="text.account.orderApprovalDashboard" text="Order Approval Dashboard"/></a>
						</ycommerce:testId>
			</dd>
				</sec:authorize>
				
	</dl>
	
</div>
