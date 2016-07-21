<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/desktop/user" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>

<template:page pageTitle="${pageTitle}">
	<%-- <div id="globalMessages">
		<common:globalMessages/>
	</div>
	<div class="span-24">
		<sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
			<c:set var="spanStyling" value="span-8"/>
		</sec:authorize>
		<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
			<c:set var="spanStyling" value="span-12"/>
			<c:set var="spanStylingLast" value=" last"/>
		</sec:authorize>
		<cms:pageSlot position="LeftContentSlot" var="feature" element="div" class="${spanStyling}">
			<cms:component component="${feature}"/>
		</cms:pageSlot>
		<cms:pageSlot position="CenterContentSlot" var="feature" element="div" class="${spanStyling}${spanStylingLast}">
			<cms:component component="${feature}"/>
		</cms:pageSlot>
		<cms:pageSlot position="RightContentSlot" var="feature" element="div" class="${spanStyling} last">
			<cms:component component="${feature}"/>
		</cms:pageSlot>
	</div> --%>
	
	<div id="breadcrumb" class="breadcrumb">
		<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" />
	</div>

	<div id="globalMessages">
		<common:globalMessages />
	</div>


	<div class="main-content">
		<div class="container">
			<div class="account-login">
				<div class="row">
					<div class="col-sm-6">
						<c:url value="/checkout/j_spring_security_check" var="loginAndCheckoutAction" />
						<user:login actionNameKey="checkout.login.loginAndCheckout" action="${loginAndCheckoutAction}" />
						<cms:pageSlot position="MerchantContactContent" var="feature">
							<cms:component component="${feature}" element="div" />
						</cms:pageSlot>
					</div>

					<cms:pageSlot position="SideContent" var="feature" element="div" class="col-sm-6">
						<cms:component component="${feature}" />
					</cms:pageSlot>
				</div>
			</div>
		</div>
	</div>
</template:page>