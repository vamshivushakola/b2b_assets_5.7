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
<%@ taglib prefix="company" tagdir="/WEB-INF/tags/addons/commerceorgaddon/desktop/company" %>
<spring:url value="/my-company/organization-management/manage-usergroups/disable"
			var="disableUrl">
	<spring:param name="usergroup" value="${usergroup.uid}"/>
</spring:url>
<spring:url value="/my-company/organization-management/manage-usergroups/details"
			var="cancelUrl">
	<spring:param name="usergroup" value="${usergroup.uid}"/>
</spring:url>
<template:page pageTitle="${pageTitle}">
	<div id="globalMessages">
		<common:globalMessages/>
	</div>
	<company:myCompanyNav selected="units"/>
	<div class="column companyContentPane clearfix">
		<cms:pageSlot position="TopContent" var="feature" element="div" class="span-20 wide-content-slot cms_disp-img_slot">
			<cms:component component="${feature}"/>
		</cms:pageSlot>	
		<div class="headline"><spring:theme code="text.company.manageUsergroups.usergroup.disable" text="Confirm Disable"  arguments="${fn:escapeXml(usergroup.uid)}"/></div>
		<div class="description"><spring:theme code="text.company.manageUsergroups.disableUsergroup.confirmation" text="Doing this will prevent the users of this Usergrop from placing orders. Do you want to proceed?"/></div>

		<form:form action="${disableUrl}">
			<a href="${cancelUrl}" class="button no-confirm"><spring:theme code="usergroup.no.button" text="No"/></a>
			<button type="submit" class=" confirm"><spring:theme code="usergroup.yes.button" text="Yes"/></button>
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
<%@ taglib prefix="company" tagdir="/WEB-INF/tags/addons/commerceorgaddon/desktop/company" %>
<spring:url value="/my-company/organization-management/manage-usergroups/disable" var="disableUrl">
	<spring:param name="usergroup" value="${usergroup.uid}" />
</spring:url>
<spring:url value="/my-company/organization-management/manage-usergroups/details" var="cancelUrl">
	<spring:param name="usergroup" value="${usergroup.uid}" />
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
						<%-- <nav:myCompanyNav selected="units" /> --%>
						<cms:pageSlot position="SideContent" var="feature" element="div" class="accountSideContentSlot">
		                	<cms:component component="${feature}" element="div" class="clearfix" />
		                </cms:pageSlot>
					</div>
				</div>
				<div class="col-lg-9">
					<cms:pageSlot position="TopContent" var="feature" element="div" class="span-20 wide-content-slot cms_disp-img_slot">
						<cms:component component="${feature}" />
					</cms:pageSlot>
					<h2 class="subtitle">
						<spring:theme code="text.company.manageUsergroups.usergroup.disable" text="Confirm Disable" arguments="${usergroup.uid}" />
					</h2>
					<p>
						<spring:theme code="text.company.manageUsergroups.disableUsergroup.confirmation" text="Doing this will prevent the users of this Usergrop from placing orders.
									   Do you want to proceed?" />
					</p>

					<form:form action="${disableUrl}" id="confirmdisableUserGroup">
						<a href="${cancelUrl}" class="positive no-confirm"><spring:theme code="usergroup.no.button" text="No" /></a>
						<button type="submit" class="positive confirm">
							<spring:theme code="usergroup.yes.button" text="Yes" />
						</button>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</template:page>
 