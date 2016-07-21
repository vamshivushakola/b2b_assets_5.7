<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<template:page pageTitle="${pageTitle}">
	<div id="breadcrumb" class="breadcrumb">
		<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" />
	</div>
	<div id="globalMessages">
		<common:globalMessages />
	</div>
	<div class="main-content account-page">
		<div class="container">
			<div class="row">
				<div class="col-sm-3">
					<div class="sidebar sidebar-left">
						<nav:accountNav />
					</div>
				</div>
				<div class="col-sm-9">
					<div class="row">
						<div class="account-div col-sm-12">
							<c:url value="/my-account/profile" var="encodedUrl" />
							<span><spring:theme code="text.account.profile" var="textAccountProfile" /></span>
							<h2 class="subtitle">
								<spring:theme code="text.account.profile" text="Profile" />
							</h2>
							<ul>
								<ycommerce:testId code="myAccount_options_profile_groupbox">
									<li><a href="${encodedUrl}"><spring:theme code="text.account.profile.updatePersonalDetails" text="Update personal details" /></a></li>
									<c:url value="/my-account/update-password" var="encodedUrl" />
									<li><a href="${encodedUrl}"><spring:theme code="text.account.profile.changePassword" text="Change your password" /></a></li>
								</ycommerce:testId>
							</ul>
						</div>
					</div>
					<sec:authorize ifAllGranted="ROLE_B2BCUSTOMERGROUP">
						<div class="row">
							<div class="account-div col-sm-6">
								<c:url value="/my-account/address-book" var="encodedUrl" />
								<span> <spring:theme code="text.account.addressBook" var="textAccountAddressBook" /></span>
								<h2 class="subtitle">
									<spring:theme code="text.account.addressBook" text="Address Book" />
								</h2>
								<ul>
									<ycommerce:testId code="myAccount_options_addressBook_groupbox">
										<li><a href="${encodedUrl}"><spring:theme code="text.account.addressBook.manageDeliveryAddresses" text="Manage your delivery addresses" /></a></li>
									</ycommerce:testId>
								</ul>
							</div>
							<div class="account-div col-sm-6">
								<c:url value="/my-account/payment-details" var="encodedUrl" />
								<span> <spring:theme code="text.account.paymentDetails" var="textAccountPaymentDetails" /></span>
								<h2 class="subtitle">
									<spring:theme code="text.account.paymentDetails" text="Payment Details" />
								</h2>
								<ul>
									<ycommerce:testId code="myAccount_options_paymentDetails_groupbox">
										<li><a href="${encodedUrl}"><spring:theme code="text.account.paymentDetails.managePaymentDetails" text="Manage your payment details" /></a></li>
									</ycommerce:testId>
								</ul>
							</div>
						</div>
						<div class="row">
							<div class="account-div col-sm-6">
								<c:url value="/my-account/my-quotes" var="encodedUrl" />
								<h2 class="subtitle">
									<spring:theme code="text.account.quotes" text="Quotes" />
								</h2>
								<ul>
									<ycommerce:testId code="myAccount_options_quotes_groupbox">
										<li><a href="${encodedUrl}"><spring:theme code="text.account.viewQuotes" text="View my quotes" /></a></li>
									</ycommerce:testId>
								</ul>
							</div>
							<div class="account-div col-sm-6">
								<c:url value="/my-account/orders" var="encodedUrl" />
								<span> <spring:theme code="text.account.orderHistory" var="textAccountOrderHistory" /></span>
								<h2 class="subtitle">
									<spring:theme code="text.account.orderHistory" text="Order History" />
								</h2>
								<ul>
									<ycommerce:testId code="myAccount_options_orderHistory_groupbox">
										<li><a href="${encodedUrl}"><spring:theme code="text.account.viewOrderHistory" text="View order history" /></a></li>
										<c:if test="${ycommerce:isExtensionInstalled('ordersearch')}"> 
										<c:url value="/orderSearch" var="encodedUrl" />
										<li><a href="${encodedUrl}"><spring:theme code="text.account.searchOrderHistory" text="Search Cart(s)/Order(s)" /></a></li>
										</c:if>
										<c:url value="/my-account/my-replenishment" var="encodedUrl" />
										<li><a href="${encodedUrl}"><spring:theme code="text.account.myReplenishment" text="Change your password" /></a></li>
									</ycommerce:testId>
								</ul>
							</div>
						</div>
					</sec:authorize>
					<sec:authorize ifAllGranted="ROLE_B2BAPPROVERGROUP">
						<div class="row">
							<div class="account-div col-sm-6">
								<c:url value="/my-account/approval-dashboard" var="encodedUrl" />
								<span><spring:theme code="text.account.orderApproval" var="textAccountOrderApproval" /></span>
								<h2 class="subtitle">
									<spring:theme code="text.account.orderApproval" text="Order Approval" />
								</h2>
								<ul>
									<ycommerce:testId code="myAccount_options_orderApproval_groupbox">
										<li><a href="${encodedUrl}"><spring:theme code="text.account.viewOrderApproval" text="View orders that require approval" /></a></li>
									</ycommerce:testId>
								</ul>
							</div>
						</div>
					</sec:authorize>					
				</div>
			</div>
			<cms:pageSlot position="TopContent" var="feature" element="div" class="span-20 wide-content-slot cms_disp-img_slot">
				<cms:component component="${feature}" />
			</cms:pageSlot>
		</div>
	</div>
</template:page>