<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>

<spring:theme text="Your Shopping Cart" var="title" code="cart.page.title"/>
<template:page pageTitle="${pageTitle}">
	<spring:theme code="basket.add.to.cart" var="basketAddToCart"/>
	<spring:theme code="cart.page.checkout" var="checkoutText"/>
	
	<div id="breadcrumb" class="breadcrumb">
		<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" />
	</div>
	<div id="globalMessages">
		<common:globalMessages />
		<cart:cartRestoration />
		<cart:cartValidation />
	</div>
	<div class="main-content cart-page">
		<div class="container">
			<div class="row">
				<div class="col-sm-10">
					<c:url value="/cart/checkout" var="checkoutUrl" scope="session"/>
					<c:url value="${continueUrl}" var="continueShoppingUrl" scope="session"/>	
					<cms:pageSlot position="TopContent" var="feature">
						<cms:component component="${feature}"/>
					</cms:pageSlot>
					<c:if test="${not empty cartData.entries}">
						<!-- <div class="clearfix">
							<%-- <div class="span-16">
								
							</div> --%>
							<div class="span-8 last"> -->
							<div class="row">
								<div class="col-sm-offset-7 col-sm-5">
									<cms:pageSlot position="CenterRightContentSlot" var="feature">
										<cms:component component="${feature}"/>
									</cms:pageSlot>
								</div>
							</div>
<!-- 						</div> -->
						<cms:pageSlot position="BottomContentSlot" var="feature">
							<cms:component component="${feature}"/>
						</cms:pageSlot>
					</c:if>
				</div>
				<%--commented by namrata
				 <div class="col-sm-2" style="margin-top: 51px;">
					<c:if test="${not empty cartData.entries}">
						<cart:cartPotentialPromotions cartData="${cartData}" />
					</c:if>
					<cms:pageSlot position="SideContent" var="feature" element="div">
						<cms:component component="${feature}" />
					</cms:pageSlot>
				</div> --%>
				<c:if test="${empty cartData.entries}">
					<div class="span-24">
						<div class="span-24 wide-content-slot cms_disp-img_slot">
							<cms:pageSlot position="EmptyCartMiddleContent" var="feature" element="div">
								<cms:component component="${feature}"/>
							</cms:pageSlot>
						</div>
					</div>
				</c:if>
			<div class="col-sm-2" style="margin-top: 51px;">
					<c:if test="${not empty cartData.entries}">
						<cms:pageSlot position="CenterLeftContentSlot" var="feature">
						<cms:component component="${feature}"/>
					</cms:pageSlot>
					</c:if>
					<cms:pageSlot position="SideContent" var="feature" element="div">
						<cms:component component="${feature}" />
					</cms:pageSlot>
				</div>
				
			</div>
		</div>
	</div>	
</template:page>
