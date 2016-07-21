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
<%@ taglib prefix="org-common" tagdir="/WEB-INF/tags/addons/commerceorgaddon/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="company" tagdir="/WEB-INF/tags/addons/commerceorgaddon/desktop/company" %>
<spring:url value="/my-company/organization-management/manage-units/edit"
			var="editUnitUrl">
	<spring:param name="unit" value="${b2BUnitForm.originalUid}"/>
</spring:url>
<spring:url value="/my-company/organization-management/manage-units/details"
			var="cancelUrl">
	<spring:param name="unit" value="${b2BUnitForm.originalUid}"/>
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
			<div class="headline"><spring:theme code="text.company.manage.units.unit.edit.title" text="Edit Unit: {0}" arguments="${empty b2BUnitForm.name ? b2BUnitForm.uid : b2BUnitForm.name}"/></div>
			<company:b2bUnitForm formUrl="${editUnitUrl}" b2BUnitForm="${b2BUnitForm}" cancelUrl="${cancelUrl}"/>
			<org-common:back cancelUrl="${cancelUrl}"/>
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

<%@ taglib prefix="org-common" tagdir="/WEB-INF/tags/addons/commerceorgaddon/desktop/common" %>
<%@ taglib prefix="company" tagdir="/WEB-INF/tags/addons/commerceorgaddon/desktop/company" %>


<spring:url value="/my-company/organization-management/manage-units/edit" var="editUnitUrl">
	<spring:param name="unit" value="${b2BUnitForm.originalUid}" />
</spring:url>
<spring:url value="/my-company/organization-management/manage-units/details" var="cancelUrl">
	<spring:param name="unit" value="${b2BUnitForm.originalUid}" />
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
					<org-common:back cancelUrl="${cancelUrl}" />
					<cms:pageSlot position="TopContent" var="feature" element="div" class="span-20 wide-content-slot cms_disp-img_slot">
						<cms:component component="${feature}" />
					</cms:pageSlot>
					<h2 class="subtitle">
						<spring:theme code="text.company.manage.units.unit.edit.title" text="Edit Unit: {0}" arguments="${empty b2BUnitForm.name ? b2BUnitForm.uid : b2BUnitForm.name}" />
					</h2>
					<company:b2bUnitForm formUrl="${editUnitUrl}" b2BUnitForm="${b2BUnitForm}" cancelUrl="${cancelUrl}" />
				</div>
			</div>
		</div>
	</div>
</template:page>
  