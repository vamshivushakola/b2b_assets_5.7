<%-- <%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="org-common" tagdir="/WEB-INF/tags/addons/commerceorgaddon/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="company" tagdir="/WEB-INF/tags/addons/commerceorgaddon/desktop/company"%>

<spring:url value="/my-company/organization-management/manage-budgets/view" var="cancelUrl">
	<spring:param name="budgetCode" value="${b2BBudgetForm.originalCode}"/>
</spring:url>
<spring:url value="/my-company/organization-management/manage-budgets/update" var="saveUrl">
	<spring:param name="budgetCode" value="${b2BBudgetForm.originalCode}"/>
</spring:url>

<template:page pageTitle="${pageTitle}">
	<spring:theme code="text.store.dateformat.datepicker.selection" text="mm/dd/yy" var="dateForForDatePicker"/>
	<spring:theme code="text.store.dateformat.datepicker.selection.hint" text="mm/dd/yyyy" var="dateFormatHint"/>

	<script type="text/javascript"> // set vars
		/*<![CDATA[*/		
		var budgetStartEnd = '${dateForForDatePicker}';
		var budgetStartEndHint = '${dateFormatHint}';
		
		/*]]>*/
	</script>
	<div id="globalMessages">
		<common:globalMessages/>
	</div>
	<company:myCompanyNav selected="budgets"/>
	<div class="column companyContentPane clearfix">
		<cms:pageSlot position="TopContent" var="feature" element="div" class="span-20 wide-content-slot cms_disp-img_slot">
			<cms:component component="${feature}"/>
		</cms:pageSlot>
		<div class="headline"><spring:theme code="text.company.budget.edit.title.label" text="Edit Budget: {0}" arguments="${b2BBudgetForm.name}"/></div>
		<company:b2bBudgetForm cancelUrl="${cancelUrl}" saveUrl="${saveUrl}" b2BBudgetForm="${b2BBudgetForm}"/>
			<org-common:back cancelUrl="${cancelUrl}"/>
	</div>
</template:page> --%>

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
<%@ taglib prefix="company" tagdir="/WEB-INF/tags/desktop/company"%>
<%@ taglib prefix="org-common" tagdir="/WEB-INF/tags/addons/commerceorgaddon/desktop/common" %>
<%@ taglib prefix="company" tagdir="/WEB-INF/tags/addons/commerceorgaddon/desktop/company"%>

<spring:url value="/my-company/organization-management/manage-budgets/view" var="cancelUrl">
	<spring:param name="budgetCode" value="${b2BBudgetForm.originalCode}" />
</spring:url>
<spring:url value="/my-company/organization-management/manage-budgets/update" var="saveUrl">
	<spring:param name="budgetCode" value="${b2BBudgetForm.originalCode}" />
</spring:url>

<template:page pageTitle="${pageTitle}">
	<spring:theme code="text.store.dateformat.datepicker.selection" text="mm/dd/yy" var="dateForForDatePicker" />
	<spring:theme code="text.store.dateformat.datepicker.selection.hint" text="mm/dd/yyyy" var="dateFormatHint" />

	<script type="text/javascript">
		// set vars
		/*<![CDATA[*/
		var budgetStartEnd = '${dateForForDatePicker}';
		var budgetStartEndHint = '${dateFormatHint}';

		/*]]>*/
	</script>
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
						<%-- <nav:myCompanyNav selected="budgets" /> --%>
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
						<spring:theme code="text.company.budget.edit.title.label" text="Edit Budget: {0}" arguments="${b2BBudgetForm.name}" />
					</h2>
					<company:b2bBudgetForm cancelUrl="${cancelUrl}" saveUrl="${saveUrl}" b2BBudgetForm="${b2BBudgetForm}" />
				</div>
			</div>
		</div>
	</div>
</template:page>

