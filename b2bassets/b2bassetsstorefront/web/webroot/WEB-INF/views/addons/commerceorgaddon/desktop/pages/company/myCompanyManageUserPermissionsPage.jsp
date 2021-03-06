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
<%@ taglib prefix="org-common" tagdir="/WEB-INF/tags/addons/commerceorgaddon/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="company" tagdir="/WEB-INF/tags/addons/commerceorgaddon/desktop/company"%>

<spring:theme code="text.company.select.action.label" text="Select" var="selectAction"/>
<spring:theme code="text.company.deselect.action.label" text="Deselect" var="deselectAction"/>

<c:if test="${empty cancelUrl}">
	<c:url value="/my-company/organization-management/manage-users" var="cancelUrl"/>
</c:if>

<template:page pageTitle="${pageTitle}">



<div id="globalMessages">
	<common:globalMessages/>
</div>
<company:myCompanyNav selected="users"/>
<div class="column companyContentPane clearfix orderList">
	<cms:pageSlot position="TopContent" var="feature" element="div" class="span-20 wide-content-slot cms_disp-img_slot">
		<cms:component component="${feature}"/>
	</cms:pageSlot>		
	<div class="headline"><spring:theme code="text.company.manageusers.${action}.title" text="${action}" arguments="${param.user}"/></div>
	
			<nav:pagination top="true" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}"
							searchUrl="${baseUrl}/${action}?user=${param.user}&sort=${searchPageData.pagination.sort}"
							sortQueryParams="user=${param.user}"
							msgKey="text.company.${action}.page"
							numberPagesShown="${numberPagesShown}"/>
			<table id="order_history" class="orderListTable">
				<thead>
				<tr>
					<th id="header1">
						<spring:theme code="text.company.${action}.name.title" text="Permision Name"/>
					</th>
					<th id="header2">
						<spring:theme code="text.company.${action}.currency.title" text="Currency"/>
					</th>
					<th id="header3">
						<spring:theme code="text.company.${action}.value.title" text="Value"/>
					</th>
					<th id="header4">
						<spring:theme code="text.company.${action}.timespan.title" text="TimeSpan"/>
					</th>
					<th id="header5">
						<spring:theme code="text.company.${action}.unit.title" text="Parent business unit"/>
					</th>
					<th id="header6">
						<spring:theme code="text.company.${action}.actions.title" text="Actions"/>
					</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${searchPageData.results}" var="result">

					<spring:url value="/my-company/organization-management/manage-permissions/view"
								var="viewUrl">
						<spring:param name="permissionCode" value="${result.code}"/>
					</spring:url>
					<tr class="${result.selected ? 'selected' : ''}" id="row-${ycommerce:normalizedCode(result.code)}">
						<td headers="header1">
							<ycommerce:testId code="${action}_name_link">
								<p><a href="${viewUrl}">${result.code}</a></p>
							</ycommerce:testId>
						</td>
						<td headers="header2">
							<ycommerce:testId code="${action}_currency_link">
								<p>${result.currency.name}</p>
							</ycommerce:testId>
						</td>
						<td headers="header1">
							<ycommerce:testId code="${action}_value_link">
								<p>${result.value}</p>
							</ycommerce:testId>
						</td>
						<td headers="header1">
							<ycommerce:testId code="${action}_timespan_link">
								<p>${result.timeSpan}</p>
							</ycommerce:testId>
						</td>
						<td headers="header2">
							<ycommerce:testId code="${action}_b2bunit_label">
								<p>${result.unit.name}</p>
							</ycommerce:testId>
						</td>

						<td headers="header4">
							<ycommerce:testId code="${action}_actions_label">
								<p>
									<span id="span-${ycommerce:normalizedCode(result.code)}">
											<spring:url value="${baseUrl}/${action}/select/"
														var="selectUrl">
												<spring:param name="user" value="${param.user}"/>
												<spring:param name="permission" value="${result.code}"/>
											</spring:url>
											<spring:url value="${baseUrl}/${action}/deselect/"
														var="deselectUrl">
												<spring:param name="user" value="${param.user}"/>
												<spring:param name="permission" value="${result.code}"/>
											</spring:url>

										<c:choose>
											<c:when test="${result.selected}">
												${selectAction} |
												<a href="#"
												   url="${deselectUrl}"
												   class="deselectionLink"
													>${deselectAction}</a>
											</c:when>
											<c:otherwise>
												<a href="#"
												   url="${selectUrl}"
												   class="selectionLink"
													>${selectAction}</a> | ${deselectAction}
											</c:otherwise>
										</c:choose>
									</span>
								</p>
							</ycommerce:testId>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>

			<nav:pagination top="false" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}"
							searchUrl="${baseUrl}/${action}?user=${param.user}&sort=${searchPageData.pagination.sort}"
							msgKey="text.company.${action}.page"
							sortQueryParams="user=${param.user}"
							numberPagesShown="${numberPagesShown}"/>
			<c:if test="${empty searchPageData.results}">
				<p>No entries.</p>
			</c:if>
			
		<org-common:back cancelUrl="${cancelUrl }"/>
</div>

<c:url value="${baseUrl}/${action}" var="actionLink" />
<script id="enableDisableLinksTemplate" type="text/x-jquery-tmpl">
	{{if selected}}
	${selectAction} | <a href="#"
				url="${actionLink}/deselect/?permission={{= id}}&user=${param.user}"
				class="deselectionLink">${deselectAction}</a>
	{{else}}
	<a href="#"
	   url="${actionLink}/select/?permission={{= id}}&user=${param.user}"
	   class="selectionLink">${selectAction}</a> | ${deselectAction}
	{{/if}}
</script>

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
<%@ taglib prefix="org-common" tagdir="/WEB-INF/tags/addons/commerceorgaddon/desktop/common" %>
<%@ taglib prefix="company" tagdir="/WEB-INF/tags/addons/commerceorgaddon/desktop/company"%>


<spring:theme code="text.company.select.action.label" text="Select" var="selectAction"/>
<spring:theme code="text.company.deselect.action.label" text="Deselect" var="deselectAction"/>

<c:if test="${empty cancelUrl}">
	<c:url value="/my-company/organization-management/manage-users" var="cancelUrl"/>
</c:if>

<template:page pageTitle="${pageTitle}">

	<script type="text/javascript">
		// set vars
		/*<![CDATA[*/

		var selectDeselectLink = true;

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
						<%-- <nav:myCompanyNav selected="users" /> --%>
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
						<spring:theme code="text.company.manageusers.${action}.title" text="${action}" arguments="${param.user}" />
					</h2>
					<nav:pagination top="true" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="${baseUrl}/${action}?user=${param.user}&sort=${searchPageData.pagination.sort}" sortQueryParams="user=${param.user}" msgKey="text.company.${action}.page" numberPagesShown="${numberPagesShown}" />
					<table id="order_history" class="data-table quote-status-details-table">
						<thead>
							<tr class="hidden-xs">
								<th><spring:theme code="text.company.${action}.name.title" text="Permision Name" /></th>
								<th><spring:theme code="text.company.${action}.currency.title" text="Currency" /></th>
								<th><spring:theme code="text.company.${action}.value.title" text="Value" /></th>
								<th><spring:theme code="text.company.${action}.timespan.title" text="TimeSpan" /></th>
								<th><spring:theme code="text.company.${action}.unit.title" text="Parent business unit" /></th>
								<th><spring:theme code="text.company.${action}.actions.title" text="Actions" /></th>
							</tr>
							<tr class="hidden-lg hidden-md hidden-sm">
								<th><spring:theme code="text.company.${action}.name.title" text="Permision Name" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${searchPageData.results}" var="result">
								<spring:url value="/my-company/organization-management/manage-permissions/view" var="viewUrl">
									<spring:param name="permissionCode" value="${result.code}" />
								</spring:url>
								<tr class="${result.selected ? 'selected' : ''}" id="row-${ycommerce:normalizedCode(result.code)}">
									<td><ycommerce:testId code="${action}_name_link">
											<span class="hidden-lg hidden-md hidden-sm mobile-label"> <spring:theme code="text.company.${action}.name.title" text="Permision Name" /> :
											</span>
											<span> <a href="${viewUrl}">${result.code}</a>
											</span>
										</ycommerce:testId></td>
									<td><ycommerce:testId code="${action}_currency_link">
											<span class="hidden-lg hidden-md hidden-sm mobile-label"> <spring:theme code="text.company.${action}.currency.title" text="Currency" /> :
											</span>
											<span>${result.currency.name}</span>
										</ycommerce:testId></td>
									<td><ycommerce:testId code="${action}_value_link">
											<span class="hidden-lg hidden-md hidden-sm mobile-label"> <spring:theme code="text.company.${action}.value.title" text="Value" /> :
											</span>
											<span>${result.value}</span>
										</ycommerce:testId></td>
									<td><ycommerce:testId code="${action}_timespan_link">
											<span class="hidden-lg hidden-md hidden-sm mobile-label"> <spring:theme code="text.company.${action}.timespan.title" text="TimeSpan" /> :
											</span>
											<span>${result.timeSpan}</span>
										</ycommerce:testId></td>
									<td><ycommerce:testId code="${action}_b2bunit_label">
											<span class="hidden-lg hidden-md hidden-sm mobile-label"> <spring:theme code="text.company.${action}.unit.title" text="Parent business unit" /> :
											</span>
											<span>${result.unit.name}</span>
										</ycommerce:testId></td>
									<td class="last"><ycommerce:testId code="${action}_actions_label">
											<span class="hidden-lg hidden-md hidden-sm mobile-label"> <spring:theme code="text.company.${action}.actions.title" text="Actions" /> :
											</span>
											<ul class="updates clear_fix" id="${ycommerce:normalizedCode(result.code)}">
												<spring:url value="${baseUrl}/${action}/select/" var="selectUrl">
													<spring:param name="user" value="${param.user}" />
													<spring:param name="permission" value="${result.code}" />
												</spring:url>
												<spring:url value="${baseUrl}/${action}/deselect/" var="deselectUrl">
													<spring:param name="user" value="${param.user}" />
													<spring:param name="permission" value="${result.code}" />
												</spring:url>
												<c:choose>
													<c:when test="${result.selected}">
														<li><span class="default-address"></span></li>
														<li><a class="remove-address deselectionLink" title="${deselectAction}" url="${deselectUrl}"></a></li>
													</c:when>
													<c:otherwise>
														<li><a class="default-address selectionLink" title="${selectAction}" url="${selectUrl}"></a></li>
														<li><span class="remove-address"></span></li>
													</c:otherwise>
												</c:choose>
											</ul>
										</ycommerce:testId></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<nav:pagination top="false" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="${baseUrl}/${action}?user=${param.user}&sort=${searchPageData.pagination.sort}" sortQueryParams="user=${param.user}" msgKey="text.company.${action}.page" numberPagesShown="${numberPagesShown}" />
					<c:if test="${empty searchPageData.results}">
						<p>No entries.</p>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<c:url value="${baseUrl}/${action}" var="actionLink" />
	<script id="enableDisableLinksTemplate" type="text/x-jquery-tmpl">
	{{if selected}}
		<li><span class="default-address"></span></li>
		<li><a class="remove-address deselectionLink" title="${deselectAction}" url="${actionLink}/deselect/?permission={{= id}}&user=${param.user}"></a></li>
	{{else}}
		<li><a class="default-address selectionLink" title="${selectAction}" url="${actionLink}/select/?permission={{= id}}&user=${param.user}"></a></li>
		<li><span class="remove-address"></span></li>
	{{/if}}
</script>

</template:page>
 