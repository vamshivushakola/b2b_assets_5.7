<%-- <%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>
<%@ taglib prefix="company" tagdir="/WEB-INF/tags/addons/commerceorgaddon/desktop/company"%>

<spring:url
	value="/my-company/organization-management/manage-permissions/edit"
	var="editPermissionDetailsUrl">
	<spring:param name="permissionCode" value="${permissionData.code}" />
</spring:url>
<spring:url
	value="/my-company/organization-management/manage-permissions/enable"
	var="enableUrl">
	<spring:param name="permissionCode" value="${permissionData.code}" />
</spring:url>
<spring:url
	value="/my-company/organization-management/manage-permissions/disable"
	var="disableUrl">
	<spring:param name="permissionCode" value="${permissionData.code}" />
</spring:url>
<spring:url
	value="/my-company/organization-management/manage-units/details"
	var="unitDetailsUrl">
	<spring:param name="unit" value="${permissionData.unit.uid}" />
</spring:url>

<template:page pageTitle="${pageTitle}">
	<div id="globalMessages">
		<common:globalMessages />
	</div>
	<company:myCompanyNav selected="managePermissions" />
	<div class="column companyContentPane clearfix orderList">
		<cms:pageSlot position="TopContent" var="feature" element="div" class="span-20 wide-content-slot cms_disp-img_slot">
			<cms:component component="${feature}"/>
		</cms:pageSlot>				
		<div class="headline"><spring:theme	code="text.company.managePermissions.viewDetails.page.title" text="View Permission: {0}"  arguments="${fn:escapeXml(permissionData.code)}"/></div>
		<div class="right">
			<a href="${editPermissionDetailsUrl}" class="button edit"><spring:theme code="text.company.costCenter.button.displayName" /></a>
			<c:choose>
				<c:when test="${permissionData.active}">
					<a href="${disableUrl}" class="button disable"><spring:theme code="text.company.budget.disableButton.displayName" /></a>
				</c:when>
				<c:otherwise>
					<a href="${enableUrl}" class="button enable"><spring:theme code="text.company.budget.enableButton.displayName" /></a>
				</c:otherwise>
			</c:choose>
		</div>
			

				<table class="table_budget orderListTable">
					<tr>
						<td><spring:theme
								code="text.company.managePermissions.name.label"
								text="Permission Name" />:</td>
						<td>${fn:escapeXml(permissionData.code)}</td>
					</tr>
					<tr>
						<td><spring:theme
								code="text.company.managePermissions.unit.label"
								text="Parent Business Unit" />:</td>
						<td><a href="${unitDetailsUrl}">${permissionData.unit.name}</a></td>
					</tr>
					<tr>
						<td><spring:theme
								code="text.company.managePermissions.type.label"
								text="permission type" />:</td>
						<td>${permissionData.b2BPermissionTypeData.name}</td>
					</tr>
					<c:if
						test="${permissionData.b2BPermissionTypeData.code ne 'B2BBudgetExceededPermission' }">
						<c:if
							test="${permissionData.b2BPermissionTypeData.code eq 'B2BOrderThresholdTimespanPermission'}">
							<tr>
								<td><spring:theme
										code="text.company.managePermissions.timespan.label"
										text="Permission timespan" />:</td>
								<td>${permissionData.timeSpan}</td>
							</tr>
						</c:if>
						<tr>
							<td><spring:theme
									code="text.company.managePermissions.currency.label"
									text="Permission currency" />:</td>
							<td>${permissionData.currency.isocode}</td>
						</tr>
						<tr>
							<td><spring:theme
									code="text.company.managePermissions.value.label"
									text="Permission value" />:</td>
							<td>
								<fmt:formatNumber value="${permissionData.value}"/></td>

						</tr>
					</c:if>
					<tr>
						<td><spring:theme
								code="text.company.managePermissions.active.label"
								text="Permission enabled/disabled" />:</td>
						<td><c:choose>
								<c:when test="${permissionData.active}">
									<spring:theme code="text.company.on" text="ON" />
								</c:when>
								<c:otherwise>
									<spring:theme code="text.company.off" text="OFF" />
								</c:otherwise>
							</c:choose></td>
					</tr>
				</table>
		
	</div>
</template:page>
 --%>
 
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
<%@ taglib prefix="company" tagdir="/WEB-INF/tags/addons/commerceorgaddon/desktop/company"%>

<spring:url value="/my-company/organization-management/manage-permissions/edit" var="editPermissionDetailsUrl">
	<spring:param name="permissionCode" value="${permissionData.code}" />
</spring:url>
<spring:url value="/my-company/organization-management/manage-permissions/enable" var="enableUrl">
	<spring:param name="permissionCode" value="${permissionData.code}" />
</spring:url>
<spring:url value="/my-company/organization-management/manage-permissions/disable" var="disableUrl">
	<spring:param name="permissionCode" value="${permissionData.code}" />
</spring:url>
<spring:url value="/my-company/organization-management/manage-units/details" var="unitDetailsUrl">
	<spring:param name="unit" value="${permissionData.unit.uid}" />
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
						<%-- <nav:myCompanyNav selected="managePermissions" /> --%>
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
						<spring:theme code="text.company.managePermissions.viewDetails.page.title" text="View Permission: {0}" arguments="${permissionData.code }" />
					</h2>
					<div class="last margin-bottom-10">
						<a href="${editPermissionDetailsUrl}" class="positive edit"><spring:theme code="text.company.costCenter.button.displayName" /></a>
						<c:choose>
							<c:when test="${permissionData.active}">
								<a href="${disableUrl}" class="positive disable"><spring:theme code="text.company.budget.disableButton.displayName" /></a>
							</c:when>
							<c:otherwise>
								<a href="${enableUrl}" class="positive enable"><spring:theme code="text.company.budget.enableButton.displayName" /></a>
							</c:otherwise>
						</c:choose>
					</div>
					<ul>
						<li><strong><spring:theme code="text.company.managePermissions.name.label" text="Permission Name" /> : </strong>${permissionData.code}</li>
						<li><strong><spring:theme code="text.company.managePermissions.unit.label" text="Parent Business Unit" /> : </strong><a href="${unitDetailsUrl}">${permissionData.unit.name}</a></li>
						<li><strong><spring:theme code="text.company.managePermissions.type.label" text="permission type" /> : </strong>${permissionData.b2BPermissionTypeData.name}</li>
						<c:if test="${permissionData.b2BPermissionTypeData.code ne 'B2BBudgetExceededPermission' }">
							<c:if test="${permissionData.b2BPermissionTypeData.code eq 'B2BOrderThresholdTimespanPermission'}">
								<li><strong><spring:theme code="text.company.managePermissions.timespan.label" text="Permission timespan" /> : </strong>${permissionData.timeSpan}</li>
							</c:if>
							<li><strong><spring:theme code="text.company.managePermissions.currency.label" text="Permission currency" /> : </strong>${permissionData.currency.isocode}</li>
							<li><strong><spring:theme code="text.company.managePermissions.value.label" text="Permission value" /> : </strong>
							<fmt:formatNumber value="${permissionData.value}" /></li>
						</c:if>
						<li><strong><spring:theme code="text.company.managePermissions.active.label" text="Permission enabled/disabled" /> : </strong>
						<c:choose>
								<c:when test="${permissionData.active}">
									<spring:theme code="text.company.on" text="ON" />
								</c:when>
								<c:otherwise>
									<spring:theme code="text.company.off" text="OFF" />
								</c:otherwise>
							</c:choose></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</template:page>
 