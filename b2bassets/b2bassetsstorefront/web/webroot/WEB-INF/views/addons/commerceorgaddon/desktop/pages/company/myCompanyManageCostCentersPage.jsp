<%-- <%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<spring:url value="/my-company/organization-management/manage-costcenters/add" var="addCostCenterDetailsUrl"/>
<%@ taglib prefix="company" tagdir="/WEB-INF/tags/addons/commerceorgaddon/desktop/company"%>

<template:page pageTitle="${pageTitle}">
	<div id="globalMessages">
		<common:globalMessages/>
	</div>
	<company:myCompanyNav selected="costCenters"/>

	
	
	<div class="column companyContentPane clearfix orderList">
		<cms:pageSlot position="TopContent" var="feature" element="div" class="span-20 wide-content-slot cms_disp-img_slot">
			<cms:component component="${feature}"/>
		</cms:pageSlot>		
			<div class="headline"><spring:theme code="text.company.manage.costcenters.title" text="All Cost Centers"/></div>
			<div class="right last">
						<ycommerce:testId code="CostCenter_AddCostCenter_button">
						<a href="${addCostCenterDetailsUrl}" class="button add" ><spring:theme code="text.company.costCenter.addButton.displayName" /></a>
						</ycommerce:testId>
			</div>

				<spring:theme code="text.company.manage.costcenters.subtitle" text="" arguments="${b2bStore}"/>
				<nav:pagination top="true" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="/my-company/organization-management/manage-costcenters?sort=${searchPageData.pagination.sort}" msgKey="text.company.costCenter.page" numberPagesShown="${numberPagesShown}"/>
					<table id="order_history" class="orderListTable">
						<thead>
							<tr>
								<th id="header1"><spring:theme code="text.company.column.id.name" text="ID"/></th>
								<th id="header2"><spring:theme code="text.company.column.name.name" text="Name"/></th>
								<th id="header3"><spring:theme code="text.company.column.parentUnit.name" text="Parent Unit"/></th>
								<th id="header4"><spring:theme code="text.company.costCenter.currency.title" text="Currency"/></th>
								<th id="header5"><spring:theme code="text.company.status.title" text="Status"/></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${searchPageData.results}" var="costCenter">
								<spring:url value="/my-company/organization-management/manage-costcenters/view/" var="viewCostCenterDetailsUrl">
									<spring:param name="costCenterCode" value="${costCenter.code}"/>
								</spring:url>
								<spring:url value="/my-company/organization-management/manage-units/details/" var="viewUnitDetailsUrl">
									<spring:param name="unit" value="${costCenter.unit.uid}"/>
								</spring:url>
								<tr>
									<td headers="header1">
										<ycommerce:testId code="costCenter_code_link">
											<a href="${viewCostCenterDetailsUrl}">${costCenter.code}</a>
										</ycommerce:testId>
									</td>
									<td headers="header2">
										<ycommerce:testId code="costCenter_name_label">
											<p>${costCenter.name}</p>
										</ycommerce:testId>
									</td>
									<td headers="header3">
										<ycommerce:testId code="costCenter_b2bunit_label">
											<p>
												<a href="${viewUnitDetailsUrl}">${costCenter.unit.name}</a>
											</p>
										</ycommerce:testId>
									</td>
									<td headers="header4">
										<ycommerce:testId code="costCenter_currency_label">
											<p>${costCenter.currency.isocode}</p>
										</ycommerce:testId>
									</td>
									<td headers="header4">
										<ycommerce:testId code="costCenter_status_label">
											<p>
												<spring:theme code="text.company.status.active.${costCenter.active}" />
											</p>
										</ycommerce:testId>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				<nav:pagination top="false"  supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="/my-company/organization-management/manage-costcenters?sort=${searchPageData.pagination.sort}" msgKey="text.company.costCenter.page" numberPagesShown="${numberPagesShown}"/>
				<c:if test="${empty searchPageData.results}">
					<p><spring:theme code="text.company.noentries" text="No entries" /></p>
				</c:if>
	
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
<%@ taglib prefix="company" tagdir="/WEB-INF/tags/addons/commerceorgaddon/desktop/company"%>


<spring:url value="/my-company/organization-management/manage-costcenters/add" var="addCostCenterDetailsUrl" />

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
						<%-- <nav:myCompanyNav selected="costCenters" /> --%>
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
						<spring:theme code="text.company.manage.costcenters.title" text="All Cost Centers" />
					</h2>
					<div class="last margin-bottom-10">
						<ycommerce:testId code="CostCenter_AddCostCenter_button">
							<a href="${addCostCenterDetailsUrl}" class="positive create"><spring:theme code="text.company.costCenter.addButton.displayName" /></a>
						</ycommerce:testId>
					</div>
					<p>
						<spring:theme code="text.company.manage.costcenters.subtitle" text="" arguments="${b2bStore}" />
					</p>
					<nav:pagination top="true" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="/my-company/organization-management/manage-costcenters?sort=${searchPageData.pagination.sort}" msgKey="text.company.costCenter.page" numberPagesShown="${numberPagesShown}" />
					<table id="order_history" class="data-table quote-status-details-table">
						<thead>
							<tr class="hidden-xs">
								<th><spring:theme code="text.company.column.id.name" text="ID" /></th>
								<th><spring:theme code="text.company.column.name.name" text="Name" /></th>
								<th><spring:theme code="text.company.column.parentUnit.name" text="Parent Unit" /></th>
								<th><spring:theme code="text.company.costCenter.currency.title" text="Currency" /></th>
								<th><spring:theme code="text.company.status.title" text="Status" /></th>
							</tr>
							<tr class="hidden-lg hidden-md hidden-sm">
								<th><spring:theme code="text.company.column.id.name" text="ID" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${searchPageData.results}" var="costCenter" varStatus="loop">
								<spring:url value="/my-company/organization-management/manage-costcenters/view/" var="viewCostCenterDetailsUrl">
									<spring:param name="costCenterCode" value="${costCenter.code}" />
								</spring:url>
								<spring:url value="/my-company/organization-management/manage-units/details/" var="viewUnitDetailsUrl">
									<spring:param name="unit" value="${costCenter.unit.uid}" />
								</spring:url>
								<tr class="${loop.last ? 'last' : ''}">
									<td><span class="hidden-lg hidden-md hidden-sm mobile-label"> <spring:theme code="text.company.column.id.name" text="ID" /> : 
									</span> <ycommerce:testId code="costCenter_code_link">
											<a href="${viewCostCenterDetailsUrl}">${costCenter.code}</a>
										</ycommerce:testId></td>
									<td><span class="hidden-lg hidden-md hidden-sm mobile-label"> <spring:theme code="text.company.column.name.name" text="Name" /> : 
									</span> <ycommerce:testId code="costCenter_name_label">
											<span>${costCenter.name}</span>
										</ycommerce:testId></td>
									<td><ycommerce:testId code="costCenter_b2bunit_label">
											<span class="hidden-lg hidden-md hidden-sm mobile-label"> 
												<spring:theme code="text.company.column.parentUnit.name" text="Parent Unit" /> : 
											</span>
											<span> <a href="${viewUnitDetailsUrl}">${costCenter.unit.name}</a>
											</span>
										</ycommerce:testId></td>
									<td><span class="hidden-lg hidden-md hidden-sm mobile-label"> 
										<spring:theme code="text.company.costCenter.currency.title" text="Currency" /> : 
									</span> <ycommerce:testId code="costCenter_currency_label">
											<span>${costCenter.currency.isocode}</span>
										</ycommerce:testId></td>
									<td class="last"><span class="hidden-lg hidden-md hidden-sm mobile-label"> 
									<spring:theme code="text.company.status.title" text="Status" /> : 
									</span> <ycommerce:testId code="costCenter_status_label">
											<span> <spring:theme code="text.company.status.active.${costCenter.active}" />
											</span>
										</ycommerce:testId></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<nav:pagination top="false" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="/my-company/organization-management/manage-costcenters?sort=${searchPageData.pagination.sort}" msgKey="text.company.costCenter.page" numberPagesShown="${numberPagesShown}" />
					<c:if test="${empty searchPageData.results}">
						<p>
							<spring:theme code="text.company.noentries" text="No entries" />
						</p>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</template:page>

