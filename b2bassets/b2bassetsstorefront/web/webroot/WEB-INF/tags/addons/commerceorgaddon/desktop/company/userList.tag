<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="users" required="true" type="java.util.List" %>
<%@ attribute name="action" required="true" type="java.lang.String" %>
<%@ attribute name="createUrl" required="true" type="java.lang.String" %>
<%@ attribute name="editUrl" required="true" type="java.lang.String" %>
<%@ attribute name="role" required="true" type="java.lang.String" %>
<%@ attribute name="subtitleKey" required="true" type="java.lang.String" %>

<div class="headline"><spring:theme code="text.company.manage.units.header.${action}" text="${action}"/></div>
	<div class="right">
		<a href="${editUrl}"class="button edit"><spring:theme code="text.company.manage.units.users.button.edit" text="Edit"/></a>
		<a href="${createUrl}&role=${role}" class="button create"><spring:theme code="text.company.manage.units.users.button.createnew" text="Create New"/></a>
	</div>
	
		<spring:theme code="${subtitleKey}"/>
		
					<table class="orderListTable">
						<thead>
						<tr>
							<th id="header1">
								<spring:theme code="text.company.manage.units.user.name" text="Name"/>
							</th>
							<th id="header2">
								<spring:theme code="text.company.manage.units.user.email" text="Email"/>
							</th>
							<th id="header3">
								<spring:theme code="text.company.manage.units.user.roles" text="Roles"/>
							</th>
							<th id="header4">
								<spring:theme code="text.company.manage.units.user.actions" text="Actions"/>
							</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${users}" var="user">
							<spring:url value="/my-company/organization-management/manage-units/edituser/"
										var="editUserUrl">
								<spring:param name="unit" value="${unit.uid}"/>
								<spring:param name="user" value="${user.uid}"/>
							</spring:url>
							<spring:url value="/my-company/organization-management/manage-units/viewuser/"
										var="viewUserUrl">
								<spring:param name="unit" value="${unit.uid}"/>
								<spring:param name="user" value="${user.uid}"/>
							</spring:url>
							<spring:url value="/my-company/organization-management/manage-units/members/confirm/remove"
										var="removeUserUrl">
								<spring:param name="unit" value="${unit.uid}"/>
								<spring:param name="user" value="${user.uid}"/>
								<spring:param name="role" value="${role}"/>
							</spring:url>
							<tr>
								<td headers="1"><a href="${viewUserUrl}">${fn:escapeXml(user.firstName)}&nbsp;${fn:escapeXml(user.lastName)}</a></td>
								<td headers="2">${fn:escapeXml(user.uid)}</td>
								<td headers="3">
									<c:forEach items="${user.roles}" var="group">
										<p><spring:theme code="b2busergroup.${group}.name"/></p>
									</c:forEach>
								</td>
								<td headers="4">
									<a href="${editUserUrl}">
											<spring:theme code="text.company.manage.unit.user.edit"
													  text="Edit"/></a>
									|
									<a href="${removeUserUrl}">
										<spring:theme code="text.company.manage.unit.user.${(fn:length(user.roles) eq 1) ? 'disable' : 'remove'}"/></a>
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>

 --%>
 
 
 
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ attribute name="users" required="true" type="java.util.List"%>
<%@ attribute name="action" required="true" type="java.lang.String"%>
<%@ attribute name="createUrl" required="true" type="java.lang.String"%>
<%@ attribute name="editUrl" required="true" type="java.lang.String"%>
<%@ attribute name="role" required="true" type="java.lang.String"%>
<%@ attribute name="subtitleKey" required="true" type="java.lang.String"%>


<h2 class="subtitle">
	<spring:theme code="text.company.manage.units.header.${action}" text="${action}" />
</h2>
<div class="last margin-bottom-10">
	<a href="${editUrl}" class="positive edit"><spring:theme code="text.company.manage.units.users.button.edit" text="Edit" /></a> <a href="${createUrl}&role=${role}" class="positive create"><spring:theme code="text.company.manage.units.users.button.createnew" text="Create New" /></a>
</div>
<p>
	<spring:theme code="${subtitleKey}" />
</p>

<table class="data-table quote-status-details-table">
	<thead>
		<tr class="hidden-xs">
			<th><spring:theme code="text.company.manage.units.user.name" text="Name" /></th>
			<th><spring:theme code="text.company.manage.units.user.email" text="Email" /></th>
			<th><spring:theme code="text.company.manage.units.user.roles" text="Roles" /></th>
			<th><spring:theme code="text.company.manage.units.user.actions" text="Actions" /></th>
		</tr>
		<tr class="hidden-lg hidden-md hidden-sm">
			<th><spring:theme code="text.company.manage.units.header.${action}" text="${action}" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${users}" var="user">
			<spring:url value="/my-company/organization-management/manage-units/edituser/" var="editUserUrl">
				<spring:param name="unit" value="${unit.uid}" />
				<spring:param name="user" value="${user.uid}" />
			</spring:url>
			<spring:url value="/my-company/organization-management/manage-units/viewuser/" var="viewUserUrl">
				<spring:param name="unit" value="${unit.uid}" />
				<spring:param name="user" value="${user.uid}" />
			</spring:url>
			<spring:url value="/my-company/organization-management/manage-units/members/confirm/remove" var="removeUserUrl">
				<spring:param name="unit" value="${unit.uid}" />
				<spring:param name="user" value="${user.uid}" />
				<spring:param name="role" value="${role}" />
			</spring:url>
			<tr>
				<td><span class="hidden-lg hidden-md hidden-sm mobile-label"> <spring:theme code="text.company.manage.units.user.name" text="Name" /> : 
				</span> <a href="${viewUserUrl}">${fn:escapeXml(user.firstName)}&nbsp;${fn:escapeXml(user.lastName)}</a></td>
				<td><span class="hidden-lg hidden-md hidden-sm mobile-label"> <spring:theme code="text.company.manage.units.user.email" text="Email" /> : 
				</span> ${fn:escapeXml(user.uid)}</td>
				<td><span class="hidden-lg hidden-md hidden-sm mobile-label"> <spring:theme code="text.company.manage.units.user.roles" text="Roles" /> : 
				</span> <c:forEach items="${user.roles}" var="group">
						<p>
							<spring:theme code="b2busergroup.${group}.name" />
						</p>
					</c:forEach></td>
				<td class="last">
					<ul class="updates clear_fix">
						<li><a title="<spring:theme code="text.company.manage.unit.user.edit" text="Edit" />" class="edit-address" href="${editUserUrl}"></a></li>
						<li><a title="<spring:theme code="text.company.manage.unit.user.${(fn:length(user.roles) eq 1) ? 'disable' : 'remove'}" />" class="remove-address" href="${removeUserUrl}"></a></li>
					</ul>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
 