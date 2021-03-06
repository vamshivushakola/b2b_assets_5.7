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
	value="/my-company/organization-management/manage-usergroups/permissions/"
	var="permissionsUrl">
	<spring:param name="usergroup" value="${usergroup.uid}"/>
</spring:url>
<spring:url
	value="/my-company/organization-management/manage-usergroups/members/"
	var="membersUrl">
	<spring:param name="usergroup" value="${usergroup.uid}"/>
</spring:url>

<spring:url
	value="/my-company/organization-management/manage-units/details/"
	var="unitDetailsUrl">
	<spring:param name="unit" value="${usergroup.unit.uid}"/>
</spring:url>

<c:if test="${empty editUrl}">
	<spring:url
		value="/my-company/organization-management/manage-usergroups/edit/"
		var="editUrl">
		<spring:param name="usergroup" value="${usergroup.uid}"/>
	</spring:url>
</c:if>
<c:if test="${empty disableUrl}">
	<spring:url
		value="/my-company/organization-management/manage-usergroups/disable/"
		var="disableUrl">
		<spring:param name="usergroup" value="${usergroup.uid}"/>
	</spring:url>
</c:if>
<c:if test="${empty removeUrl}">
	<spring:url
		value="/my-company/organization-management/manage-usergroups/remove/"
		var="removeUrl">
		<spring:param name="usergroup" value="${usergroup.uid}"/>
	</spring:url>
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
	<div class="headline"><spring:theme code="text.company.usergroups.viewDetails.label" text="View Usergroup Details"/></div>
		<div class="right">
				<ycommerce:testId code="Usergroup_Edit_button">
					<a href="${editUrl}" class="button edit"><spring:theme code="text.company.usergroup.edit.button" text="Edit"/></a>
				</ycommerce:testId>
			<c:if test="${fn:length(usergroup.members) > 0}">
					<ycommerce:testId code="Usergroup_Disable_button">
						<a href="${disableUrl}" class="button disable"><spring:theme code="text.company.usergroup.disable.button" text="Disable"/></a>
					</ycommerce:testId>
			</c:if>
				<ycommerce:testId code="Usergroup_Remove_button">
					<a href="${removeUrl}" class="button remove"><spring:theme code="text.company.usergroup.remove.button" text="Remove"/></a>
				</ycommerce:testId>
		</div>
		
			<table class="table_budget orderListTable">
				<tr>
					<td>
						<spring:theme code="text.company.usergroup.id.title" text="Usergroup ID"/>
						:
					</td>
					<td>${fn:escapeXml(usergroup.uid)}</td>
				</tr>
				<tr>
					<td>
						<spring:theme code="text.company.usergroup.name.title" text="Usergroup Name"/>
						:
					</td>
					<td>${fn:escapeXml(usergroup.name)}</td>
				</tr>
				<tr>
					<td>
						<spring:theme code="text.company.usergroup.unit.title" text="Parent unit"/>
						:
					</td>
					<td><a href="${unitDetailsUrl}">${fn:escapeXml(usergroup.unit.name)}</a></td>
				</tr>
			</table>


			<div class="headline"><spring:theme code="text.company.manageUser.permission.title" text="Permissions"/></div>
				<div class="right">
					<a href="${permissionsUrl}" class="button edit"><spring:theme code="text.edit" text="Edit"/></a>
				</div>
				<div class="description">
					<spring:theme code="text.company.permissions.subtitle" />
				</div>
				
								<table class="orderListTable">
									<thead>
									<tr>
										<th id="header1">
											<spring:theme code="text.company.permissions.name.title"
														  text="Permision Name"/>
										</th>
										<th id="header2">
											<spring:theme code="text.company.permissions.currency.title"
														  text="Currency"/>
										</th>
										<th id="header3">
											<spring:theme code="text.company.permissions.value.title" text="Value"/>
										</th>
										<th id="header4">
											<spring:theme code="text.company.permissions.timespan.title"
														  text="TimeSpan"/>
										</th>
										<th id="header5">
											<spring:theme code="text.company.permissions.unit.title"
														  text="Parent unit"/>
										</th>
									</tr>
									</thead>
									<tbody>
									<c:forEach items="${usergroup.permissions}" var="permission">
										<spring:url
											value="/my-company/organization-management/manage-units/details/"
											var="unitDetailUrl">
											<spring:param name="unit" value="${permission.unit.uid}"/>
										</spring:url>
										<spring:url
											value="/my-company/organization-management/manage-permissions/view/"
											var="permissionDetailsUrl">
											<spring:param name="permissionCode" value="${permission.code}"/>
										</spring:url>
										<tr>
											<td headers="header1">
												<ycommerce:testId code="permission_name_link">
													<p><a href="${permissionDetailsUrl}">${fn:escapeXml(permission.code)}</a></p>
												</ycommerce:testId>
											</td>
											<td headers="header2">
												<ycommerce:testId code="permission_currency_link">
													<p>${fn:escapeXml(permission.currency.name)}</p>
												</ycommerce:testId>
											</td>
											<td headers="header3">
												<ycommerce:testId code="permission_value_link">
													<p>${fn:escapeXml(permission.value)}</p>
												</ycommerce:testId>
											</td>
											<td headers="header4">
												<ycommerce:testId code="permission_timespan_link">
													<p>${fn:escapeXml(permission.timeSpan)}</p>
												</ycommerce:testId>
											</td>
											<td headers="header5">
												<ycommerce:testId code="permission_b2bunit_label">
													<p><a href="${unitDetailUrl}">${fn:escapeXml(permission.unit.name)}</a></p>
												</ycommerce:testId>
											</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
						

			<div class="headline"><spring:theme code="text.company.usergroup.members" text="Users"/></div>
				<div class="right">
					<a href="${membersUrl}" class="button create"><spring:theme code="text.company.usergroup.button.createnew" text="Edit"/></a>
				</div>
				<div class="description"><spring:theme code="text.company.usergroups.users.subtitle"/></div>
				
								<table class="orderListTable">
									<thead>
									<tr>
										<th id="membername">
											<spring:theme code="text.company.usergroup.name.title"
														  text="User Name"/>
										</th>
										<th id="header5">
											<spring:theme code="text.company.usergroup.unit.title"
														  text="Parent business unit"/>
										</th>
									</tr>
									</thead>
									<tbody>
									<c:forEach items="${usergroup.members}" var="user">
										<spring:url
											value="/my-company/organization-management/manage-units/details/"
											var="unitDetailUrl">
											<spring:param name="unit" value="${user.unit.uid}"/>
										</spring:url>
										<spring:url
											value="/my-company/organization-management//manage-users/details/"
											var="userDetailUrl">
											<spring:param name="user" value="${user.uid}"/>
										</spring:url>



										<tr>
											<td headers="header1">
												<ycommerce:testId code="member_name_link">
													<p><a href="${userDetailUrl}">${fn:escapeXml(user.name)}</a></p>
												</ycommerce:testId>
											</td>
											<td headers="header5">
												<ycommerce:testId code="member_b2bunit_label">
													<p><a href="${unitDetailUrl}">${fn:escapeXml(user.unit.name)}</a></p>
												</ycommerce:testId>
											</td>
										</tr>
									</c:forEach>
									</tbody>
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

<spring:url
	value="/my-company/organization-management/manage-usergroups/permissions/"
	var="permissionsUrl">
	<spring:param name="usergroup" value="${usergroup.uid}"/>
</spring:url>
<spring:url
	value="/my-company/organization-management/manage-usergroups/members/"
	var="membersUrl">
	<spring:param name="usergroup" value="${usergroup.uid}"/>
</spring:url>

<spring:url
	value="/my-company/organization-management/manage-units/details/"
	var="unitDetailsUrl">
	<spring:param name="unit" value="${usergroup.unit.uid}"/>
</spring:url>

<c:if test="${empty editUrl}">
	<spring:url
		value="/my-company/organization-management/manage-usergroups/edit/"
		var="editUrl">
		<spring:param name="usergroup" value="${usergroup.uid}"/>
	</spring:url>
</c:if>
<c:if test="${empty disableUrl}">
	<spring:url
		value="/my-company/organization-management/manage-usergroups/disable/"
		var="disableUrl">
		<spring:param name="usergroup" value="${usergroup.uid}"/>
	</spring:url>
</c:if>
<c:if test="${empty removeUrl}">
	<spring:url
		value="/my-company/organization-management/manage-usergroups/remove/"
		var="removeUrl">
		<spring:param name="usergroup" value="${usergroup.uid}"/>
	</spring:url>
</c:if>


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
						<%-- <nav:myCompanyNav selected="manageUsergroups" /> --%>
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
						<spring:theme code="text.company.usergroups.viewDetails.label" text="View Usergroup Details" />
					</h2>
					<div class="last margin-bottom-10">
						<ycommerce:testId code="Usergroup_Edit_button">
							<a href="${editUrl}" class="positive edit"><spring:theme code="text.company.usergroup.edit.button" text="Edit" /></a>
						</ycommerce:testId>
						<c:if test="${fn:length(usergroup.members) > 0}">
							<a href="${disableUrl}"> <ycommerce:testId code="Usergroup_Disable_button">
									<a href="${disableUrl}" class="positive disable"><spring:theme code="text.company.usergroup.disable.button" text="Disable" /></a>
								</ycommerce:testId>
							</a>
						</c:if>
						<ycommerce:testId code="Usergroup_Remove_button">
							<a href="${removeUrl}" class="positive remove"><spring:theme code="text.company.usergroup.remove.button" text="Remove" /></a>
						</ycommerce:testId>
					</div>
					<ul>
						<li><strong><spring:theme code="text.company.usergroup.id.title" text="Usergroup ID" /> : </strong>${usergroup.uid}</li>
						<li><strong><spring:theme code="text.company.usergroup.name.title" text="Usergroup Name" /> : </strong>${usergroup.name}</li>
						<li><strong><spring:theme code="text.company.usergroup.unit.title" text="Parent unit" /> : </strong><a href="${unitDetailsUrl}">${usergroup.unit.name}</a></li>
					</ul>
					<h2 class="subtitle">
						<spring:theme code="text.company.manageUser.permission.title" text="Permissions" />
					</h2>
					<div class="last margin-bottom-10">
						<a href="${permissionsUrl}" class="positive edit"><spring:theme code="text.edit" text="Edit" /></a>
					</div>
					<p>
						<spring:theme code="text.company.permissions.subtitle" />
					</p>
					<table class="data-table quote-status-details-table">
						<thead>
							<tr class="hidden-xs">
								<th><spring:theme code="text.company.permissions.name.title" text="Permision Name" /></th>
								<th><spring:theme code="text.company.permissions.currency.title" text="Currency" /></th>
								<th><spring:theme code="text.company.permissions.value.title" text="Value" /></th>
								<th><spring:theme code="text.company.permissions.timespan.title" text="TimeSpan" /></th>
								<th><spring:theme code="text.company.permissions.unit.title" text="Parent unit" /></th>
							</tr>
							<tr class="hidden-lg hidden-md hidden-sm">
								<th><spring:theme code="text.company.manageUser.permission.title" text="Permissions" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${usergroup.permissions}" var="permission">
								<spring:url value="/my-company/organization-management/manage-units/details/" var="unitDetailUrl">
									<spring:param name="unit" value="${permission.unit.uid}" />
								</spring:url>
								<spring:url value="/my-company/organization-management/manage-permissions/view/" var="permissionDetailsUrl">
									<spring:param name="permissionCode" value="${permission.code}" />
								</spring:url>
								<tr>
									<td><ycommerce:testId code="permission_name_link">
											<span class="hidden-lg hidden-md hidden-sm mobile-label"> <spring:theme code="text.company.permissions.name.title" text="Permision Name" /> :
											</span>
											<a href="${permissionDetailsUrl}">${permission.code}</a>
										</ycommerce:testId></td>
									<td><ycommerce:testId code="permission_currency_link">
											<span class="hidden-lg hidden-md hidden-sm mobile-label"> <spring:theme code="text.company.permissions.currency.title" text="Currency" /> :
											</span>
											<span>${permission.currency.name}</span>
										</ycommerce:testId></td>
									<td><ycommerce:testId code="permission_value_link">
											<span class="hidden-lg hidden-md hidden-sm mobile-label"> <spring:theme code="text.company.permissions.value.title" text="Value" /> :
											</span>
											<span>${permission.value}</span>
										</ycommerce:testId></td>
									<td><ycommerce:testId code="permission_timespan_link">
											<span class="hidden-lg hidden-md hidden-sm mobile-label"> <spring:theme code="text.company.permissions.timespan.title" text="TimeSpan" /> :
											</span>
											<span>${permission.timeSpan}</span>
										</ycommerce:testId></td>
									<td class="last"><ycommerce:testId code="permission_b2bunit_label">
											<span class="hidden-lg hidden-md hidden-sm mobile-label"> <spring:theme code="text.company.permissions.unit.title" text="Parent unit" /> :
											</span>
											<a href="${unitDetailUrl}">${permission.unit.name}</a>
										</ycommerce:testId></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<h2 class="subtitle">
						<spring:theme code="text.company.usergroup.members" text="Users" />
					</h2>
					<div class="last margin-bottom-10">
						<a href="${membersUrl}" class="positive create"><spring:theme code="text.company.usergroup.button.createnew" text="Edit" /></a>
					</div>
					<table class="data-table quote-status-details-table">
						<thead>
							<tr class="hidden-xs">
								<th><spring:theme code="text.company.usergroup.name.title" text="User Name" /></th>
								<th><spring:theme code="text.company.usergroup.unit.title" text="Parent business unit" /></th>
							</tr>
							<tr class="hidden-lg hidden-md hidden-sm">
								<th><spring:theme code="text.company.usergroup.members" text="Users" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${usergroup.members}" var="user">
								<spring:url value="/my-company/organization-management/manage-units/details/" var="unitDetailUrl">
									<spring:param name="unit" value="${user.unit.uid}" />
								</spring:url>
								<spring:url value="/my-company/organization-management//manage-users/details/" var="userDetailUrl">
									<spring:param name="user" value="${user.uid}" />
								</spring:url>
								<tr>
									<td><ycommerce:testId code="member_name_link">
											<span class="hidden-lg hidden-md hidden-sm mobile-label"> <spring:theme code="text.company.usergroup.name.title" text="User Name" /> : 
											</span>
											<a href="${userDetailUrl}">${user.name}</a>
										</ycommerce:testId></td>
									<td class="last"><ycommerce:testId code="member_b2bunit_label">
											<span class="hidden-lg hidden-md hidden-sm mobile-label"> <spring:theme code="text.company.usergroup.unit.title" text="Parent business unit" /> : 
											</span>
											<a href="${unitDetailUrl}">${user.unit.name}</a>

										</ycommerce:testId></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</template:page>
 