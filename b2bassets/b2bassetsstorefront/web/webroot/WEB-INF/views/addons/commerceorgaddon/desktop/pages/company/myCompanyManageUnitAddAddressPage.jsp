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
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="company" tagdir="/WEB-INF/tags/addons/commerceorgaddon/desktop/company"%>

<spring:url value="/my-company/organization-management/manage-units/details/"
			var="cancelUrl">
	<spring:param name="unit" value="${uid}"/>
</spring:url>
<c:choose>
	<c:when test="${not empty addressData.id}">
		<spring:url value="/my-company/organization-management/manage-units/edit-address/"
					var="actionUrl">
			<spring:param name="unit" value="${uid}"/>
			<spring:param name="addressId" value="${addressData.id}"/>
		</spring:url>
	</c:when>
	<c:otherwise>
		<spring:url value="/my-company/organization-management/manage-units/add-address/"
					var="actionUrl">
			<spring:param name="unit" value="${uid}"/>
		</spring:url>
	</c:otherwise>
</c:choose>

<template:page pageTitle="${pageTitle}">
	<div id="globalMessages">
		<common:globalMessages/>
	</div>
	<company:myCompanyNav selected="units"/>
	<div class="column companyContentPane clearfix">
		<div class="headline"><spring:theme code="text.company.manage.units.addressForm.${empty addressData.id?'create':'edit'}.header" arguments="${unitName}"/></div>
		<div class="required right"><spring:theme code="form.required" text="Fields marked * are required"/></div>
		<div class="description"><spring:theme code="text.account.addressBook.addEditform"	  text="Please use this form to add/edit an address."/></div>
	
				<form:form action="${actionUrl}" method="post" commandName="addressForm">
				
						<formElement:formSelectBox idKey="address.title" labelKey="address.title" path="titleCode"
												mandatory="true" skipBlank="false"
												skipBlankMessageKey="address.title.pleaseSelect" items="${titleData}"
												selectedValue="${addressForm.titleCode}"/>
						<formElement:formInputBox idKey="address.firstName" labelKey="address.firstName" path="firstName"
											   inputCSS="text" mandatory="true"/>
						<formElement:formInputBox idKey="address.surname" labelKey="address.surname" path="lastName"
											   inputCSS="text" mandatory="true"/>
						<formElement:formInputBox idKey="address.line1" labelKey="address.line1" path="line1"
											   inputCSS="text" mandatory="true"/>
						<formElement:formInputBox idKey="address.line2" labelKey="address.line2" path="line2"
											   inputCSS="text" mandatory="false"/>
						<formElement:formInputBox idKey="address.townCity" labelKey="address.townCity" path="townCity"
											   inputCSS="text" mandatory="true"/>
						<formElement:formInputBox idKey="address.postcode" labelKey="address.postcode" path="postcode"
											   inputCSS="text" mandatory="true"/>
						<formElement:formSelectBox idKey="address.country" labelKey="address.country" path="countryIso"
												mandatory="true" skipBlank="false"
												skipBlankMessageKey="address.selectCountry" items="${countryData}"
												itemValue="isocode" selectedValue="${addressForm.countryIso}"/>
				

					<ycommerce:testId code="UnitAddress_Cancel_button">
						<a href="${cancelUrl}" class="button cancel"><spring:theme code="text.company.manage.unit.address.cancelButton" text="Cancel"/></a>
					</ycommerce:testId>
					<ycommerce:testId code="UnitAddress_SaveAddress_button">
						<button type="submit" class="save"><spring:theme code="text.company.manage.unit.saveAddress" text="Save"/></button>
					</ycommerce:testId>

				</form:form>
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
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>

<%@ taglib prefix="org-common" tagdir="/WEB-INF/tags/addons/commerceorgaddon/desktop/common" %>
<%@ taglib prefix="company" tagdir="/WEB-INF/tags/addons/commerceorgaddon/desktop/company"%>



<spring:url value="/my-company/organization-management/manage-units/details/" var="cancelUrl">
	<spring:param name="unit" value="${uid}" />
</spring:url>
<c:choose>
	<c:when test="${not empty addressData.id}">
		<spring:url value="/my-company/organization-management/manage-units/edit-address/" var="actionUrl">
			<spring:param name="unit" value="${uid}" />
			<spring:param name="addressId" value="${addressData.id}" />
		</spring:url>
	</c:when>
	<c:otherwise>
		<spring:url value="/my-company/organization-management/manage-units/add-address/" var="actionUrl">
			<spring:param name="unit" value="${uid}" />
		</spring:url>
	</c:otherwise>
</c:choose>

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
					<h2 class="subtitle">
						<spring:theme code="text.company.manage.units.addressForm.${empty addressData.id?'create':'edit'}.header" arguments="${unitName}" />
					</h2>
					<p>
						<spring:theme code="text.account.addressBook.addEditform" text="Please use this form to add/edit an address." />
					</p>
					<p class="required">
						<spring:theme code="form.required" text="Fields marked * are required" />
					</p>
					<form:form action="${actionUrl}" method="post" commandName="addressForm" id="unitAddAddress">
						<ul class="form-list">
							<li><formUtil:formSelectBox selectCSSClass="chosen-select-responsive select" idKey="address.title" labelKey="address.title" path="titleCode" mandatory="true" skipBlank="false" skipBlankMessageKey="address.title.pleaseSelect" items="${titleData}" selectedValue="${addressForm.titleCode}" /></li>
							<li><formUtil:formInputBox idKey="address.firstName" labelKey="address.firstName" path="firstName" inputCSS="text" mandatory="true" /></li>
							<li><formUtil:formInputBox idKey="address.surname" labelKey="address.surname" path="lastName" inputCSS="text" mandatory="true" /></li>
							<li><formUtil:formInputBox idKey="address.line1" labelKey="address.line1" path="line1" inputCSS="text" mandatory="true" /></li>
							<li><formUtil:formInputBox idKey="address.line2" labelKey="address.line2" path="line2" inputCSS="text" mandatory="false" /></li>
							<li><formUtil:formInputBox idKey="address.townCity" labelKey="address.townCity" path="townCity" inputCSS="text" mandatory="true" /></li>
							<li><formUtil:formInputBox idKey="address.postcode" labelKey="address.postcode" path="postcode" inputCSS="text" mandatory="true" /></li>
							<li><formUtil:formSelectBox selectCSSClass="chosen-select-responsive select" idKey="address.country" labelKey="address.country" path="countryIso" mandatory="true" skipBlank="false" skipBlankMessageKey="address.selectCountry" items="${countryData}" itemValue="isocode" selectedValue="${addressForm.countryIso}" /></li>
						</ul>

						<ycommerce:testId code="UnitAddress_Cancel_button">
							<a href="${cancelUrl}" class="positive form cancel"><spring:theme code="text.company.manage.unit.address.cancelButton" text="Cancel" /></a>
						</ycommerce:testId>
						<ycommerce:testId code="UnitAddress_SaveAddress_button">
							<button type="submit" class="positive form save">
								<spring:theme code="text.company.manage.unit.saveAddress" text="Save" />
							</button>
						</ycommerce:testId>

					</form:form>
				</div>
			</div>
		</div>
	</div>
</template:page>
 