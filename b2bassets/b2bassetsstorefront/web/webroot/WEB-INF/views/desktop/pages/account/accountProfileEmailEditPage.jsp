<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<c:url var="profileUrl" value="/my-account/profile" />

<h2 class="subtitle">
	<spring:theme code="text.account.change.email.address" text="Profile"/>
</h2>
<p>
	<spring:theme code="text.account.profile.updateEmailAddress" text="Enter your new email address and confirm with your password"/>
</p>
<p class="required">
	<spring:theme code="form.required" text="Fields marked * are required"/>
</p>
		
<form:form action="update-email" method="post" commandName="updateEmailForm">
	<ul class="form-list">
		<li><formElement:formInputBox idKey="profile.email" labelKey="profile.email" path="email" inputCSS="text" mandatory="true"/></li>
		<li><formElement:formInputBox idKey="profile.checkEmail"  labelKey="profile.checkEmail" path="chkEmail" inputCSS="text" mandatory="true"/></li>
		<li><formElement:formPasswordBox idKey="profile.pwd" labelKey="profile.pwd" path="password" inputCSS="text" mandatory="true"/></li>
	</ul>
	<input type="hidden" id="recaptchaChallangeAnswered" value="${requestScope.recaptchaChallangeAnswered}"/>
	
	
	<span style="display: block; clear: both;"> 
		<button class="form" type="button" class="negative" onclick="window.location='${profileUrl}'"><spring:theme code="text.account.profile.cancel" text="Cancel"/></button>
		<ycommerce:testId code="myAccount_profile_SaveUpdates_button">
			<button class="form">
				<spring:theme code="text.account.profile.saveUpdates" text="Save Updates" />
			</button>
		</ycommerce:testId>
	</span>
</form:form>
			

