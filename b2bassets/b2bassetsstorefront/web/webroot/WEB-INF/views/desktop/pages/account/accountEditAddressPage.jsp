<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="address" tagdir="/WEB-INF/tags/desktop/address"%>

	<h2 class="subtitle">
		<spring:theme code="text.account.addressBook.addressDetails" text="Address Details" />
	</h2>
	<p>
		<spring:theme code="text.account.addressBook.addEditform" text="Please use this form to add/edit an address." />
	</p>
	<p class="required">
		<spring:theme code="form.required" text="Fields marked * are required" />
	</p>
		<div class="accountContentPane clearfix">			
			<address:addressFormSelector supportedCountries="${countries}" regions="${regions}" cancelUrl="/my-account/address-book"/>
			<address:suggestedAddresses selectedAddressUrl="/my-account/select-suggested-address"/>
		</div>

<%-- <%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="formUtil" tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>


<h2 class="subtitle">
						<spring:theme code="text.account.addressBook.addressDetails" text="Address Details" />
					</h2>

					<p>
						<spring:theme code="text.account.addressBook.addEditform" text="Please use this form to add/edit an address." />
					</p>
					<p class="required">
						<spring:theme code="form.required" text="Fields marked * are required" />
					</p>
					<form:form action="add-address" method="post" commandName="addressForm">
						<form:hidden path="addressId" />
						<ul class="form-list">
							<li><formUtil:formSelectBox icon="fa-user" selectCSSClass="chosen-select-responsive select" idKey="address.title" labelKey="address.title" path="titleCode" mandatory="true" skipBlank="false" skipBlankMessageKey="address.title.pleaseSelect" items="${titleData}" selectedValue="${addressForm.titleCode}" /></li>
							<li><formUtil:formInputBox icon="fa-user" idKey="address.firstName" labelKey="address.firstName" path="firstName" inputCSS="text" mandatory="true" /></li>
							<li><formUtil:formInputBox icon="fa-user" idKey="address.surname" labelKey="address.surname" path="lastName" inputCSS="text" mandatory="true" /></li>
							<li><formUtil:formInputBox icon="fa-building" idKey="address.line1" labelKey="address.line1" path="line1" inputCSS="text" mandatory="true" /></li>
							<li><formUtil:formInputBox icon="fa-building" idKey="address.line2" labelKey="address.line2" path="line2" inputCSS="text" mandatory="false" /></li>
							<li><formUtil:formInputBox icon="fa-building" idKey="address.townCity" labelKey="address.townCity" path="townCity" inputCSS="text" mandatory="true" /></li>
							<li><formUtil:formInputBox icon="fa-building" idKey="address.postcode" labelKey="address.postcode" path="postcode" inputCSS="text" mandatory="true" /></li>
							<li><formUtil:formSelectBox icon="fa-building" selectCSSClass="chosen-select-responsive select" idKey="address.country" labelKey="address.country" path="countryIso" mandatory="true" skipBlank="false" skipBlankMessageKey="address.selectCountry" items="${countryData}" itemValue="isocode" selectedValue="${addressForm.countryIso}" /></li>
							<c:if test="${not addressBookEmpty && not addressForm.defaultAddress}">
								@TODO À remplacer le idKey par ${fn:replace('address.default',' ', '')} plus propre mais à faire marcher
								<li><formUtil:formCheckbox idKey="address-label" labelKey="address.default" path="defaultAddress" inputCSS="add-address-left-input" labelCSS="add-address-left-label" mandatory="false" /></li>
							</c:if>
						</ul>

						<button class="form">
							<spring:theme code="text.account.addressBook.saveAddress" text="Save address" />
						</button>

					</form:form> --%>