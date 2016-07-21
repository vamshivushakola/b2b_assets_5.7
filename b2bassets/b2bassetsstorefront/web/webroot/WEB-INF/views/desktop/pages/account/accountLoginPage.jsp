 <%-- <%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav"%> 
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 

<template:page pageTitle="${pageTitle}">
	<div id="globalMessages">
		<common:globalMessages/>
	</div>
	<div class="span-24">
		<cms:pageSlot position="LeftContentSlot" var="feature" element="div" class="span-12">
			<cms:component component="${feature}"/>
		</cms:pageSlot>
		<cms:pageSlot position="RightContentSlot" var="feature" element="div" class="span-12 last">
			<cms:component component="${feature}"/>
		</cms:pageSlot>
	</div>
	
	<div class="main-content">
		<div class="container">
			<div class="account-login">
				<h2 class="subtitle">
					<spring:theme code="login.title" />
				</h2>

				<div class="row">
					<div class="span-24">
						<cms:pageSlot position="LeftContentSlot" var="feature" element="div" class="span-12">
							<cms:component component="${feature}" />
						</cms:pageSlot>
						<c:url value="/j_spring_security_check" var="loginActionUrl" />
						<user:login actionNameKey="login.login" action="${loginActionUrl}" />
						<cms:pageSlot position="RightContentSlot" var="feature" element="div" class="span-12 last">
							<cms:component component="${feature}" />
						</cms:pageSlot> 
						<cms:pageSlot position="MerchantContactContent" var="feature" element="div" class="contact-merchant">
							<cms:component component="${feature}" />
						</cms:pageSlot>
					</div>
					<cms:pageSlot position="RightContentSlot" var="feature" element="div" class="span-12 last">
						<cms:component component="${feature}" />
					</cms:pageSlot>


				</div>
			</div>
		</div>
	</div>
</template:page> 

 --%>
 
 <%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/desktop/user"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<template:page pageTitle="${pageTitle}">
	<div id="breadcrumb" class="breadcrumb">
		<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" />
	</div>
	<div id="globalMessages">
		<common:globalMessages />
	</div>
	<div class="main-content">
		<div class="container">
			<div class="account-login">
				<h2 class="subtitle">
					<spring:theme code="login.title" />
				</h2>

				<div class="row">
					<div class="col-sm-6">
						<cms:pageSlot position="TopContent" var="feature" element="div" class="span-20 top-content-slot cms_disp-img_slot last">
							<cms:component component="${feature}" />
						</cms:pageSlot>
						<c:url value="/j_spring_security_check" var="loginActionUrl" />
						<user:login actionNameKey="login.login" action="${loginActionUrl}" />
						<cms:pageSlot position="MerchantContactContent" var="feature" element="div" class="contact-merchant">
							<cms:component component="${feature}" />
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
 
 
 