<%-- <%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="headline">
	<spring:theme code="text.account.profile" text="Profile"/>
</div>
<table class="account-profile-data">
	<tr>
		<td><spring:theme code="profile.title" text="Title"/>: </td>
		<td>${fn:escapeXml(title.name)}</td>
	</tr>
	<tr>
		<td><spring:theme code="profile.firstName" text="First name"/>: </td>
		<td>${fn:escapeXml(customerData.firstName)}</td>
	</tr>
	<tr>
		<td><spring:theme code="profile.lastName" text="Last name"/>: </td>
		<td>${fn:escapeXml(customerData.lastName)}</td>
	</tr>
	<tr>
		<td><spring:theme code="profile.email" text="E-mail"/>: </td>
		<td>${fn:escapeXml(customerData.displayUid)}</td>
	</tr>
</table>
<a class="button" href="update-password"><spring:theme code="text.account.profile.changePassword" text="Change password"/></a>
<a class="button" href="update-profile"><spring:theme code="text.account.profile.updatePersonalDetails" text="Update personal details"/></a>
<a class="button" href="update-email"><spring:theme code="text.account.profile.updateEmail" text="Update email"/></a> --%>

<!-- commented for migration -->
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="col-sm-9">
	<h2 class="subtitle">
		<spring:theme code="text.account.profile" text="Profile" />
	</h2>
	<table class="data-table no-responsive">
		<tbody>
			<tr>
				<td><strong><spring:theme code="profile.title"
							text="Title" /></strong></td>
				<td class="last">${fn:escapeXml(title.name)}</td>
			</tr>
			<tr>
				<td><strong><spring:theme code="profile.firstName"
							text="First name" /></strong></td>
				<td class="last">${fn:escapeXml(customerData.firstName)}</td>
			</tr>
			<tr>
				<td><strong><spring:theme code="profile.lastName"
							text="Last name" /></strong></td>
				<td class="last">${fn:escapeXml(customerData.lastName)}</td>
			</tr>
			<tr class="last">
				<td class="last"><strong><spring:theme
							code="profile.email" text="E-mail" /></strong></td>
				<td class="last">${fn:escapeXml(customerData.displayUid)}</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td></td>
				<td class="last">
					<ul class="updates">
						<li><a href="update-password"><spring:theme
									code="text.account.profile.changePassword"
									text="Change password" /></a></li>
						<li><a href="update-profile"><spring:theme
									code="text.account.profile.updatePersonalDetails"
									text="Update personal details" /></a></li>
						<sec:authorize ifAllGranted="ROLE_B2BADMINGROUP">
							<li><a href="update-email"><spring:theme
										code="text.account.profile.updateEmail" text="Update email" /></a></li>
						</sec:authorize>
					</ul>
				</td>
			</tr>
		</tfoot>
	</table>
</div>