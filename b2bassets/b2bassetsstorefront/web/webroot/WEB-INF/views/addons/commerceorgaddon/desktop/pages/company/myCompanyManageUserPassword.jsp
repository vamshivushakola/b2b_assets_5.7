<%-- <%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="company" tagdir="/WEB-INF/tags/addons/commerceorgaddon/desktop/company"%>

<spring:url
	value="/my-company/organization-management/manage-users/resetpassword"
	var="resetpasswordUrl">
	<spring:param name="user" value="${customerResetPasswordForm.uid}"/>
</spring:url>

<template:page pageTitle="${pageTitle}">
	<div id="globalMessages">
		<common:globalMessages/>
	</div>
	<company:myCompanyNav selected="users" />
	<div class="column companyContentPane clearfix">
		<div class="headline"><spring:theme code="text.manageuser.restpasswordform" text="Reset Password"/></div>
		<div class="required right"><spring:theme code="form.required" text="Fields marked * are required"/></div>
		<div class="description"><spring:theme code="text.manageuser.password" text="Please enter and confirm new password" arguments="${customerResetPasswordForm.uid}"/></div>
			
				<form:form action="${resetpasswordUrl}" method="post" commandName="customerResetPasswordForm" autocomplete="off">
					<form:input type="hidden" name="uid" path="uid" id="uid" />
					<formElement:formPasswordBox idKey="profile-newPassword" labelKey="profile.newPassword" path="newPassword" inputCSS="text password strength" mandatory="true"/>
					<formElement:formPasswordBox idKey="profile.checkNewPassword" labelKey="profile.checkNewPassword" path="checkNewPassword" inputCSS="text password" mandatory="true"/>
					<button class="confirm"><spring:theme code="text.account.profile.updatePasswordForm" text="Update Password"/></button>
				</form:form>

	</div>
</template:page>
 --%>
 
 <%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav"%>
<%@ taglib prefix="formUtil" tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>
<%@ taglib prefix="company" tagdir="/WEB-INF/tags/addons/commerceorgaddon/desktop/company"%>

<spring:url value="/my-company/organization-management/manage-users/resetpassword" var="resetpasswordUrl">
	<spring:param name="user" value="${customerResetPasswordForm.uid}" />
</spring:url>

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
				<div class="col-lg-3">
					<div class="sidebar sidebar-left">
						<%-- <nav:myCompanyNav selected="users" /> --%>
						<cms:pageSlot position="SideContent" var="feature" element="div" class="accountSideContentSlot">
		                	<cms:component component="${feature}" element="div" class="clearfix" />
		                </cms:pageSlot>
					</div>
				</div>
				<div class="col-lg-9">
					<h2 class="subtitle">
						<spring:theme code="text.manageuser.restpasswordform" text="Reset Password" />
					</h2>
					<p>
						<spring:theme code="text.manageuser.password" text="Please enter and confirm new password" arguments="${customerResetPasswordForm.uid}" />
					</p>
					<p class="required">
						<spring:theme code="form.required" text="Fields marked * are required" />
					</p>
					<form:form action="${resetpasswordUrl}" method="post" commandName="customerResetPasswordForm" autocomplete="off">

						<form:input type="hidden" name="uid" path="uid" id="uid" />
						<ul class="form-list">
							<li><formUtil:formPasswordBox idKey="profile-newPassword" labelKey="profile.newPassword" path="newPassword" inputCSS="text password strength" mandatory="true" /></li>
							<li><formUtil:formPasswordBox idKey="profile.checkNewPassword" labelKey="profile.checkNewPassword" path="checkNewPassword" inputCSS="text password" mandatory="true" /></li>
						</ul>

						<button class="positive form confirm">
							<spring:theme code="text.account.profile.updatePasswordForm" text="Update Password" />
						</button>

					</form:form>
				</div>
			</div>
		</div>
	</div>
</template:page>
 