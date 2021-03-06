<%-- <%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="company" tagdir="/WEB-INF/tags/addons/commerceorgaddon/desktop/company"%>
<spring:url value="/my-company/organization-management/manage-users/create"
			var="manageUsersUrl"/>
<template:page pageTitle="${pageTitle}">
	<div id="globalMessages">
		<common:globalMessages/>
	</div>
	<company:myCompanyNav selected="users"/>
	<div class="column companyContentPane clearfix orderList">

			<div class="headline"><spring:theme code="text.company.manageusers.label" text="All Users"/></div>
			<div class="right">
				<ycommerce:testId code="User_AddUser_button">
					<a href="${manageUsersUrl}" class="button add"><spring:theme code="text.company.manageUser.button.create" text="Create New User"/></a>
				</ycommerce:testId>
			</div>
			<div class="description"><spring:theme code="text.company.manageusers.subtitle" arguments="${b2bStore}"/></div>
			
			
				<c:if test="${not empty searchPageData.results}">
					<p>
						<spring:theme code="text.company.manageUser.viewUsers" text="View Users"/>
					</p>
					<nav:pagination top="true" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}"
									searchUrl="/my-company/organization-management/manage-users?sort=${searchPageData.pagination.sort}"
									sortQueryParams="user=${param.user}"
									msgKey="text.company.manageUser.pageAll" numberPagesShown="${numberPagesShown}"/>
					<form>
						<table id="manage_user"  class="orderListTable">
							<thead>
							<tr>
								<th id="header1">
									<spring:theme code="text.company.column.name.name" text="Name"/>
								</th>
								<th id="header2">
									<spring:theme code="text.company.column.roles.name" text="Roles"/>
								</th>
								<th id="header3">
									<spring:theme code="text.company.column.parentUnit.name" text="Parent Unit"/>
								</th>
								<th id="header4">
									<spring:theme code="text.company.manageUser.user.costCenter" text="Cost center"/>
								</th>
								<th id="header5">
									<spring:theme code="text.company.status.title" text="Status"/>
								</th>
							</tr>
							</thead>
							<tbody>
							<c:forEach items="${searchPageData.results}" var="user">
								<tr>
									<spring:url value="/my-company/organization-management/manage-users/details/"
												var="viewUserUrl">
										<spring:param name="user" value="${user.uid}"/>
									</spring:url>
									<spring:url value="/my-company/organization-management/manage-units/details/"
												var="viewUnitUrl">
										<spring:param name="unit" value="${user.unit.uid}"/>
									</spring:url>

									<td headers="header1">
										<ycommerce:testId code="my-company_username_label">
											<p><a href="${viewUserUrl}">${fn:escapeXml(user.firstName)}&nbsp;${fn:escapeXml(user.lastName)}</a></p>
										</ycommerce:testId>
									</td>
									<td headers="header2">
										<ycommerce:testId code="my-company_user_roles_label">
											<c:forEach items="${user.roles}" var="role">
												<p>
													<spring:theme code="b2busergroup.${role}.name"/>
												</p>
											</c:forEach>
										</ycommerce:testId>
									</td>
									<td headers="header3">
										<ycommerce:testId code="my-company_user_unit_label">
											<p><a href="${viewUnitUrl}">${user.unit.name}</a></p>
										</ycommerce:testId>
									</td>
									<td headers="header4">
										<ycommerce:testId code="my-company_user_costcenter_label">
											<c:forEach items="${user.unit.costCenters}" var="costCenter">
												<spring:url value="/my-company/organization-management/manage-costcenters/view/"
													var="viewCostCenterUrl">
													<spring:param name="costCenterCode" value="${costCenter.code}"/>
												</spring:url>
												<p><a href="${viewCostCenterUrl}">${costCenter.code}</a></p>
											</c:forEach>
										</ycommerce:testId>
									</td>
									<td headers="header5">
										<ycommerce:testId code="costCenter_status_label">
											<p>
												<spring:theme code="text.company.status.active.${user.active}" />
											</p>
										</ycommerce:testId>
									</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</form>
					<nav:pagination top="false" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}"
									searchPageData="${searchPageData}" searchUrl="/my-company/organization-management/manage-users?sort=${searchPageData.pagination.sort}"
									sortQueryParams="user=${param.user}"
									msgKey="text.company.manageUser.pageAll" numberPagesShown="${numberPagesShown}"/>
				</c:if>
				<c:if test="${empty searchPageData.results}">
					<p>
						<spring:theme code="text.company.manageUser.noUser" text="You have no users"/>
					</p>
				</c:if>
		
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
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>
<%@ taglib prefix="company" tagdir="/WEB-INF/tags/addons/commerceorgaddon/desktop/company"%>
<spring:url value="/my-company/organization-management/manage-users/create" var="manageUsersUrl" />


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
						<%-- <nav:myCompanyNav selected="users" /> --%>
						<cms:pageSlot position="SideContent" var="feature" element="div" class="accountSideContentSlot">
		                	<cms:component component="${feature}" element="div" class="clearfix" />
		                </cms:pageSlot>
					</div>
				</div>
				<div class="col-lg-9">
					<h2 class="subtitle">
						<spring:theme code="text.company.manageusers.label" text="All Users" />
					</h2>
					<div class="last margin-bottom-10">
						<ycommerce:testId code="User_AddUser_button">
							<a href="${manageUsersUrl}" class="positive create"><spring:theme code="text.company.manageUser.button.create" text="Create New User" /></a>
						</ycommerce:testId>
					</div>
					<p>
						<spring:theme code="text.company.manageusers.subtitle" arguments="${b2bStore}" />
					</p>
					<c:if test="${not empty searchPageData.results}">
						<p>
							<spring:theme code="text.company.manageUser.viewUsers" text="View Users" />
						</p>
						<nav:pagination top="true" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="/my-company/organization-management/manage-users?sort=${searchPageData.pagination.sort}" msgKey="text.company.manageUser.pageAll" numberPagesShown="${numberPagesShown}" />
						<form>
							<table id="manage_user" class="data-table quote-status-details-table">
								<thead>
									<tr class="hidden-xs">
										<th><spring:theme code="text.company.column.name.name" text="Name" /></th>
										<th><spring:theme code="text.company.column.roles.name" text="Roles" /></th>
										<th><spring:theme code="text.company.column.parentUnit.name" text="Parent Unit" /></th>
										<th><spring:theme code="text.company.manageUser.user.costCenter" text="Cost center" /></th>
										<th><spring:theme code="text.company.status.title" text="Status" /></th>
									</tr>
									<tr class="hidden-lg hidden-md hidden-sm">
										<th><spring:theme code="text.company.manageusers.label" text="All Users" /></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${searchPageData.results}" var="user">
										<tr>
											<spring:url value="/my-company/organization-management/manage-users/details/" var="viewUserUrl">
												<spring:param name="user" value="${user.uid}" />
											</spring:url>
											<spring:url value="/my-company/organization-management/manage-units/details/" var="viewUnitUrl">
												<spring:param name="unit" value="${user.unit.uid}" />
											</spring:url>

											<td><ycommerce:testId code="my-company_username_label">
													<span class="hidden-lg hidden-md hidden-sm mobile-label"> <spring:theme code="text.company.column.name.name" text="Name" /> : 
													</span>
													<span> <a href="${viewUserUrl}">${user.firstName}&nbsp;${user.lastName}</a>
													</span>
												</ycommerce:testId></td>
											<td><ycommerce:testId code="my-company_user_roles_label">
													<span class="hidden-lg hidden-md hidden-sm mobile-label"> <spring:theme code="text.company.column.roles.name" text="Roles" /> : 
													</span>
													<c:forEach items="${user.roles}" var="role">
														<p>
															<spring:theme code="b2busergroup.${role}.name" />
														</p>
													</c:forEach>
												</ycommerce:testId></td>
											<td><ycommerce:testId code="my-company_user_unit_label">
													<span class="hidden-lg hidden-md hidden-sm mobile-label"> <spring:theme code="text.company.column.parentUnit.name" text="Parent Unit" /> : 
													</span>
													<span> <a href="${viewUnitUrl}">${user.unit.name}</a>
													</span>
												</ycommerce:testId></td>
											<td><ycommerce:testId code="my-company_user_costcenter_label">
													<span class="hidden-lg hidden-md hidden-sm mobile-label"> <spring:theme code="text.company.manageUser.user.costCenter" text="Cost center" /> : 
													</span>
													<c:forEach items="${user.unit.costCenters}" var="costCenter">
														<spring:url value="/my-company/organization-management/manage-costcenters/view/" var="viewCostCenterUrl">
															<spring:param name="costCenterCode" value="${costCenter.code}" />
														</spring:url>
														<p>
															<a href="${viewCostCenterUrl}">${costCenter.code}</a>
														</p>
													</c:forEach>
												</ycommerce:testId></td>
											<td class="last"><ycommerce:testId code="costCenter_status_label">
													<span class="hidden-lg hidden-md hidden-sm mobile-label"> <spring:theme code="text.company.status.title" text="Status" /> : 
													</span>
													<span> <spring:theme code="text.company.status.active.${user.active}" />
													</span>
												</ycommerce:testId></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</form>
						<nav:pagination top="false" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="/my-company/organization-management/manage-users?sort=${searchPageData.pagination.sort}" msgKey="text.company.manageUser.pageAll" numberPagesShown="${numberPagesShown}" />
					</c:if>
					<c:if test="${empty searchPageData.results}">
						<p>
							<spring:theme code="text.company.manageUser.noUser" text="You have no users" />
						</p>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</template:page>
 