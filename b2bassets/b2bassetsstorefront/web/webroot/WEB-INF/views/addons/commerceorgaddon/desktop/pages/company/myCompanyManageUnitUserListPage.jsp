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

<spring:theme code="text.company.select.action.label" text="Select" var="selectAction" />
<spring:theme code="text.company.deselect.action.label" text="Deselect" var="deselectAction"/>
<spring:theme code="text.company.disable.action.label" text="Disable" var="disableAction"/>

<c:if test="${empty cancelUrl}">
	<c:url value="/my-company/organization-management/manage-units" var="cancelUrl"/>
</c:if>

<template:page pageTitle="${pageTitle}">

<div id="globalMessages">
	<common:globalMessages/>
</div>
<company:myCompanyNav selected="units"/>
<div class="column companyContentPane clearfix orderList">
	<cms:pageSlot position="TopContent" var="feature" element="div" class="span-20 wide-content-slot cms_disp-img_slot">
		<cms:component component="${feature}"/>
	</cms:pageSlot>								
	<div class="headline"><spring:theme code="text.company.manage.units.${action}.title" text="${action}"  arguments="${unit.name}"/></div>
			<spring:theme code="text.company.manage.units.${action}.subtitle"/>
			<nav:pagination top="true" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}"
							searchUrl="${baseUrl}/${action}?unit=${param.unit}&role=${param.role}&sort=${searchPageData.pagination.sort}"
							sortQueryParams="unit=${param.unit}&role=${param.role}"
							msgKey="text.company.${action}.page"
							numberPagesShown="${numberPagesShown}"/>
			<table id="order_history" class="orderListTable">
				<thead>
				<tr>
					<th id="header1">
						<spring:theme code="text.company.name.name" text="Name"/>
					</th>
					<th id="header2">
						<spring:theme code="text.company.parentUnit.name" text="Parent Unit"/>
					</th>
					<th id="header3">
						<spring:theme code="text.company.roles.name" text="Roles"/>
					</th>
					<th id="header4">
						<spring:theme code="text.company.${action}.actions.title" text="Actions"/>
					</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${searchPageData.results}" var="user">
					<spring:url value="/my-company/organization-management/manage-users/details"
								var="viewUrl">
						<spring:param name="user" value="${user.uid}"/>
					</spring:url>
					<spring:url value="${baseUrl}/members/select/"
								var="selectUrl">
						<spring:param name="user" value="${user.uid}"/>
						<spring:param name="role" value="${param.role}"/>
					</spring:url>
					<spring:url value="${baseUrl}/members/deselect/"
								var="deselectUrl">
						<spring:param name="user" value="${user.uid}"/>
						<spring:param name="role" value="${param.role}"/>
					</spring:url>
					<tr class="${user.selected ? 'selected' : ''}" id="row-${user.normalizedUid}">
						<td headers="header1">
							<ycommerce:testId code="${action}_name_link">
								<a href="${viewUrl}">${user.name}</a>
							</ycommerce:testId>
						</td>
						<td headers="header2">
							<ycommerce:testId code="${action}_b2bunit_label">
								<p>${user.unit.name}</p>
							</ycommerce:testId>
						</td>
						<td headers="header3">
							<ycommerce:testId code="${action}_roles_label">
								<div id="roles-${user.normalizedUid}">
									<c:forEach items="${user.roles}" var="role">
										<p>
											<spring:theme code="b2busergroup.${role}.name"/>
										</p>
									</c:forEach>
								</div>
							</ycommerce:testId>
						</td>
						<td headers="header4">
							<ycommerce:testId code="${action}_actions_label">
								<p>
									<c:choose>
										<c:when test="${user.active}">
									<span id="selection-${user.normalizedUid}">
										<c:choose>
											<c:when test="${user.selected}">
												${selectAction} |
												<a href="#"
													url="${deselectUrl}"
													class="deselectUser">
													<c:choose>
														<c:when test="${fn:length(user.roles) eq 1}">
															${disableAction}
														</c:when>
														<c:otherwise>
															${deselectAction}
														</c:otherwise>
													</c:choose>
												</a>
											</c:when>
											<c:otherwise>
												<a href="#"
													url="${selectUrl}"
													class="selectUser">${selectAction}</a> | ${deselectAction}
											</c:otherwise>
										</c:choose>
									</span>
										</c:when>
										<c:otherwise>
											<spring:theme code="text.user.disabled" text="Disabled User"/>
										</c:otherwise>
									</c:choose>
								</p>
							</ycommerce:testId>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>

			<nav:pagination top="false"  supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}"
							searchUrl="${baseUrl}/${action}?unit=${param.unit}&role=${param.role}&sort=${searchPageData.pagination.sort}"
							sortQueryParams="unit=${param.unit}&role=${param.role}"
							msgKey="text.company.${action}.page"
							numberPagesShown="${numberPagesShown}"/>
			<c:if test="${empty searchPageData.results}">
				<p>No entries.</p>
			</c:if>
			
	<org-common:back cancelUrl="${cancelUrl}"/>
</div>

<c:url value="${baseUrl}/members" var="membersActionLink" />
<script id="enableDisableLinksTemplate" type="text/x-jquery-tmpl">
	{{if selected}}
	${selectAction} | <a href="#"
				url="${membersActionLink}/deselect/?user={{= id}}&role=${param.role}"
				class="deselectUser">${deselectAction}</a>
	{{else}}
	<a href="#"
		url="${membersActionLink}/select/?user={{= id}}&role=${param.role}"
		class="selectUser">${selectAction}</a> | ${deselectAction}
	{{/if}}
</script>

<script id="userRolesTemplate" type="text/x-jquery-tmpl">
	{{each displayRoles}}
		<p>
			{{= $value}}
		</p>
	{{/each}}
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
<%@ taglib prefix="org-common" tagdir="/WEB-INF/tags/addons/commerceorgaddon/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="company" tagdir="/WEB-INF/tags/addons/commerceorgaddon/desktop/company"%>

<spring:theme code="text.company.select.action.label" text="Select" var="selectAction" />
<spring:theme code="text.company.deselect.action.label" text="Deselect" var="deselectAction" />
<spring:theme code="text.company.disable.action.label" text="Disable" var="disableAction" />

<c:if test="${empty cancelUrl}">
	<%-- <c:url value="/my-company/organization-management/manage-units" var="cancelUrl"/> --%>
</c:if>


<template:page pageTitle="${pageTitle}">
	<script type="text/javascript">
		// set vars
		/*<![CDATA[*/

		var selectDeselectUser = true;

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
						<spring:theme code="text.company.manage.units.${action}.title" text="${action}" arguments="${unit.name}" />
					</h2>
					<p>
						<spring:theme code="text.company.manage.units.${action}.subtitle" />
					</p>
					<nav:pagination top="true" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="${baseUrl}/${action}?unit=${param.unit}&role=${param.role}&sort=${searchPageData.pagination.sort}" sortQueryParams="unit=${param.unit}&role=${param.role}" msgKey="text.company.${action}.page" numberPagesShown="${numberPagesShown}" />
					<table id="order_history" class="data-table quote-status-details-table">
						<thead>
							<tr class="hidden-xs">
								<th><spring:theme code="text.company.name.name" text="Name" /></th>
								<th><spring:theme code="text.company.parentUnit.name" text="Parent Unit" /></th>
								<th><spring:theme code="text.company.roles.name" text="Roles" /></th>
								<th><spring:theme code="text.company.${action}.actions.title" text="Actions" /></th>
							</tr>
							<tr class="hidden-lg hidden-md hidden-sm">
								<th><spring:theme code="text.company.name.name" text="Name" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${searchPageData.results}" var="user">
								<spring:url value="/my-company/organization-management/manage-users/details" var="viewUrl">
									<spring:param name="user" value="${user.uid}" />
								</spring:url>
								<spring:url value="${baseUrl}/members/select/" var="selectUrl">
									<spring:param name="user" value="${user.uid}" />
									<spring:param name="role" value="${param.role}" />
								</spring:url>
								<spring:url value="${baseUrl}/members/deselect/" var="deselectUrl">
									<spring:param name="user" value="${user.uid}" />
									<spring:param name="role" value="${param.role}" />
								</spring:url>
								<tr class="${user.selected ? 'selected' : ''}" id="row-${user.normalizedUid}">
									<td><ycommerce:testId code="${action}_name_link">
											<span class="hidden-lg hidden-md hidden-sm mobile-label"> <spring:theme code="text.company.name.name" text="Name" /> :
											</span>
											<a href="${viewUrl}">${user.name}</a>
										</ycommerce:testId></td>
									<td><ycommerce:testId code="${action}_b2bunit_label">
											<span class="hidden-lg hidden-md hidden-sm mobile-label"> <spring:theme code="text.company.parentUnit.name" text="Parent Unit" /> :
											</span>
											<span>${user.unit.name}</span>
										</ycommerce:testId></td>
									<td><ycommerce:testId code="${action}_roles_label">
											<span class="hidden-lg hidden-md hidden-sm mobile-label"> <spring:theme code="text.company.roles.name" text="Roles" /> :
											</span>
											<div id="roles-${user.normalizedUid}">
												<c:forEach items="${user.roles}" var="role">
													<p>
														<spring:theme code="b2busergroup.${role}.name" />
													</p>
												</c:forEach>
											</div>
										</ycommerce:testId></td>
									<td class="last"><ycommerce:testId code="${action}_actions_label">
											<span class="hidden-lg hidden-md hidden-sm mobile-label"> <spring:theme code="text.company.${action}.actions.title" text="Actions" /> :
											</span>
											<c:choose>
												<c:when test="${user.active}">
													<ul class="updates clear_fix" id="selection-${user.normalizedUid}">
														<c:choose>
															<c:when test="${user.selected}">
																<li><span class="default-address"></span></li>
																<li><a class="remove-address deselectUser" title="${deselectAction}" url="${deselectUrl}"></a></li>
															</c:when>
															<c:otherwise>
																<li><a class="default-address selectUser" title="${selectAction}" url="${selectUrl}"></a></li>
																<li><span class="remove-address"></span></li>
															</c:otherwise>
														</c:choose>
													</ul>
												</c:when>
												<c:otherwise>
													<spring:theme code="text.user.disabled" text="Disabled User" />
												</c:otherwise>
											</c:choose>
										</ycommerce:testId></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<nav:pagination top="false" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="${baseUrl}/${action}?unit=${param.unit}&role=${param.role}&sort=${searchPageData.pagination.sort}" sortQueryParams="unit=${param.unit}&role=${param.role}" msgKey="text.company.${action}.page" numberPagesShown="${numberPagesShown}" />
					<c:if test="${empty searchPageData.results}">
						<p>No entries.</p>
					</c:if>
				</div>
			</div>
		</div>
	</div>

	<c:url value="${baseUrl}/members" var="membersActionLink" />
	<script id="enableDisableLinksTemplate" type="text/x-jquery-tmpl">
	{{if selected}}
		<li><span class="default-address"></span></li>
		<li><a class="remove-address deselectUser" title="${deselectAction}" url="${membersActionLink}/deselect/?user={{= id}}&role=${param.role}"></a></li>
	{{else}}
		<li><a class="default-address selectUser" title="${selectAction}" url="${membersActionLink}/select/?user={{= id}}&role=${param.role}"></a></li>
		<li><span class="remove-address"></span></li>
	{{/if}}
</script>

	<script id="userRolesTemplate" type="text/x-jquery-tmpl">
	{{each displayRoles}}
		<p>
			{{= $value}}
		</p>
	{{/each}}
</script>
</template:page>
 