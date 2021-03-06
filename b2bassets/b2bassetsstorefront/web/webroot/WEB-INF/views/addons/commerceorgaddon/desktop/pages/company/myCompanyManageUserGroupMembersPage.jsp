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
<spring:theme code="text.company.disable.action.label" text="Disable" var="disableAction"/>

<c:if test="${empty cancelUrl}">
	<c:url value="/my-company/organization-management/manage-usergroups" var="cancelUrl"/>
</c:if>

<template:page pageTitle="${pageTitle}">
	<div id="globalMessages">
		<common:globalMessages/>
	</div>
	<company:myCompanyNav selected="manageUsergroups"/>
	<div class="column companyContentPane clearfix orderList">
		<cms:pageSlot position="TopContent" var="feature" element="div" class="span-20 wide-content-slot cms_disp-img_slot">
			<cms:component component="${feature}"/>
		</cms:pageSlot>		
		<div class="headline"><spring:theme code="text.company.usergroups.${action}.title" text="${action}" arguments="${fn:escapeXml(param.usergroup)}"/></div>
			
				<nav:pagination top="true" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}"
								searchUrl="${baseUrl}/${action}?usergroup=${param.usergroup}&sort=${searchPageData.pagination.sort}"
								msgKey="text.company.${action}.page"
								sortQueryParams="user=${param.user}"
								numberPagesShown="${numberPagesShown}"/>
				<table id="order_history" class="orderListTable">
					<thead>
					<tr>
						<th id="header1">
							<spring:theme code="text.company.${action}.name.title" text="Name"/>
						</th>
						<th id="header2">
							<spring:theme code="text.company.${action}.unit.title" text="Parent unit"/>
						</th>
						<th id="header3">
							<spring:theme code="text.company.${action}.roles.title" text="Roles"/>
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
						<spring:url value="${baseUrl}/${action}/select/"
									var="selectUrl">
							<spring:param name="user" value="${user.uid}"/>
							<spring:param name="usergroup" value="${param.usergroup}"/>
						</spring:url>
						<spring:url value="${baseUrl}/${action}/deselect/"
									var="deselectUrl">
							<spring:param name="user" value="${user.uid}"/>
							<spring:param name="usergroup" value="${param.usergroup}"/>
						</spring:url>
						<spring:url
							value="/my-company/organization-management/manage-units/details/"
							var="unitDetailUrl">
							<spring:param name="unit" value="${user.unit.uid}"/>
						</spring:url>
						
						<tr class="${user.selected ? 'selected' : ''}" id="row-${user.normalizedUid}">
							<td headers="header1">
								<ycommerce:testId code="${action}_name_link">
									<a href="${viewUrl}">${user.name}</a>
								</ycommerce:testId>
							</td>
							<td headers="header2">
								<ycommerce:testId code="${action}_b2bunit_label">
									<p><a href="${unitDetailUrl}">${user.unit.name}</a></p>
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
									<span id="selection-${user.normalizedUid}">
										<c:choose>
											<c:when test="${user.selected}">
												${selectAction} |
												<a href="#"
												   url="${deselectUrl}"
												   class="deselectUser"
													>${deselectAction}</a>
											</c:when>
											<c:otherwise>
												<a href="#"
												   url="${selectUrl}"
												   class="selectUser"
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
								searchUrl="${baseUrl}/${action}?usergroup=${param.usergroup}&sort=${searchPageData.pagination.sort}"
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
							 url="${actionLink}/deselect/?user={{= uid}}&usergroup=${param.usergroup}"
							 class="deselectUser">${deselectAction}</a>
		{{else}}
		<a href="#"
		   url="${actionLink}/select/?user={{= uid}}&usergroup=${param.usergroup}"
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

<spring:theme code="text.company.select.action.label" text="Select" var="selectAction"/>
<spring:theme code="text.company.deselect.action.label" text="Deselect" var="deselectAction"/>
<spring:theme code="text.company.disable.action.label" text="Disable" var="disableAction"/>

<c:if test="${empty cancelUrl}">
	<c:url value="/my-company/organization-management/manage-usergroups" var="cancelUrl"/>
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
						<%-- <nav:myCompanyNav selected="manageUsergroups" /> --%>
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
						<spring:theme code="text.company.usergroups.${action}.title" text="${action}" arguments="${param.usergroup}" />
					</h2>
					<nav:pagination top="true" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="${baseUrl}/${action}?usergroup=${param.usergroup}&sort=${searchPageData.pagination.sort}" sortQueryParams="usergroup=${param.usergroup}" msgKey="text.company.${action}.page" numberPagesShown="${numberPagesShown}" />
					<table id="order_history" class="data-table quote-status-details-table">
						<thead>
							<tr class="hidden-xs">
								<th><spring:theme code="text.company.${action}.name.title" text="Name" /></th>
								<th><spring:theme code="text.company.${action}.unit.title" text="Parent unit" /></th>
								<th><spring:theme code="text.company.${action}.roles.title" text="Roles" /></th>
								<th><spring:theme code="text.company.${action}.actions.title" text="Actions" /></th>
							</tr>
							<tr class="hidden-lg hidden-md hidden-sm">
								<th><spring:theme code="text.company.${action}.name.title" text="Name" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${searchPageData.results}" var="user">
								<spring:url value="/my-company/organization-management/manage-users/details" var="viewUrl">
									<spring:param name="user" value="${user.uid}" />
								</spring:url>
								<spring:url value="${baseUrl}/${action}/select/" var="selectUrl">
									<spring:param name="user" value="${user.uid}" />
									<spring:param name="usergroup" value="${param.usergroup}" />
								</spring:url>
								<spring:url value="${baseUrl}/${action}/deselect/" var="deselectUrl">
									<spring:param name="user" value="${user.uid}" />
									<spring:param name="usergroup" value="${param.usergroup}" />
								</spring:url>
								<spring:url value="/my-company/organization-management/manage-units/details/" var="unitDetailUrl">
									<spring:param name="unit" value="${user.unit.uid}" />
								</spring:url>

								<tr class="${user.selected ? 'selected' : ''}" id="row-${user.normalizedUid}">
									<td><ycommerce:testId code="${action}_name_link">
											<span class="hidden-lg hidden-md hidden-sm mobile-label"> <spring:theme code="text.company.${action}.name.title" text="Name" /> :
											</span>
											<a href="${viewUrl}">${user.name}</a>
										</ycommerce:testId></td>
									<td><ycommerce:testId code="${action}_b2bunit_label">
											<span class="hidden-lg hidden-md hidden-sm mobile-label"> <spring:theme code="text.company.${action}.unit.title" text="Parent unit" /> :
											</span>
											<a href="${unitDetailUrl}">${user.unit.name}</a>

										</ycommerce:testId></td>
									<td><ycommerce:testId code="${action}_roles_label">
											<span class="hidden-lg hidden-md hidden-sm mobile-label"> <spring:theme code="text.company.${action}.roles.title" text="Roles" /> :
											</span>
											<div id="roles-${user.normalizedUid}">
												<c:forEach items="${user.roles}" var="role">
													<p>
														<spring:theme code="b2busergroup.${role}.name" />
													</p>
												</c:forEach>
											</div>
										</ycommerce:testId></td>
									<td><ycommerce:testId code="${action}_actions_label">
											<span class="hidden-lg hidden-md hidden-sm mobile-label"> <spring:theme code="text.company.${action}.actions.title" text="Actions" /> :
											</span>
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
										</ycommerce:testId></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<nav:pagination top="false" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="${baseUrl}/${action}?usergroup=${param.usergroup}&sort=${searchPageData.pagination.sort}" sortQueryParams="usergroup=${param.usergroup}" msgKey="text.company.${action}.page" numberPagesShown="${numberPagesShown}" />
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
			<li><a class="remove-address deselectUser" title="${deselectAction}" url="${actionLink}/deselect/?user={{= uid}}&usergroup=${param.usergroup}"></a></li>
		{{else}}
			<li><a class="default-address selectUser" title="${selectAction}" url="${actionLink}/select/?user={{= uid}}&usergroup=${param.usergroup}"></a></li>
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
 