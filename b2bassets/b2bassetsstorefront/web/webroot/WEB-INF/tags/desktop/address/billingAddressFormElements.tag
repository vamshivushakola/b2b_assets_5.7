<%@ attribute name="regions" required="true" type="java.util.List"%>
<%@ attribute name="country" required="false" type="java.lang.String"%>
<%@ attribute name="tabindex" required="false" type="java.lang.Integer"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<ul class="form-list" id="billingAddressForm"> 
	<c:choose>
		<c:when test="${country == 'US'}">
			<li><formElement:formInputBox idKey="address.firstName" icon="fa-user" labelKey="address.firstName" path="billTo_firstName" inputCSS="text" mandatory="true" tabindex="${tabindex + 1}"/></li>
			<li><formElement:formInputBox idKey="address.surname" icon="fa-user" labelKey="address.surname" path="billTo_lastName" inputCSS="text" mandatory="true" tabindex="${tabindex + 2}"/></li>
			<li><formElement:formInputBox idKey="address.line1" icon="fa-building" labelKey="address.line1" path="billTo_street1" inputCSS="text" mandatory="true" tabindex="${tabindex + 3}"/></li>
			<li><formElement:formInputBox idKey="address.line2" icon="fa-building" labelKey="address.line2" path="billTo_street2" inputCSS="text" mandatory="false" tabindex="${tabindex + 4}"/></li>
			<li><formElement:formInputBox idKey="address.townCity" icon="fa-building" labelKey="address.townCity" path="billTo_city" inputCSS="text" mandatory="true" tabindex="${tabindex + 5}"/></li>
			<li><formElement:formSelectBox idKey="address.region" icon="fa-building" labelKey="address.state" path="billTo_state" mandatory="true" skipBlank="false" skipBlankMessageKey="address.selectState" items="${regions}" itemValue="isocodeShort" tabindex="${tabindex + 6}" selectedValue="${billingAddressForm.regionIso}" selectCSSClass="chosen-select-responsive select"/></li>
			<br></br><br></br>
			<li><formElement:formInputBox idKey="address.postcode" icon="fa-building" labelKey="address.postcode" path="billTo_postalCode" inputCSS="text" mandatory="true" tabindex="${tabindex + 7}"/></li>
		</c:when>
		<c:when test="${country == 'CA'}">
			<li><formElement:formInputBox idKey="address.firstName" icon="fa-user" labelKey="address.firstName" path="billTo_firstName" inputCSS="text" mandatory="true" tabindex="${tabindex + 1}"/></li>
			<li><formElement:formInputBox idKey="address.surname" icon="fa-user" labelKey="address.surname" path="billTo_lastName" inputCSS="text" mandatory="true" tabindex="${tabindex + 2}"/></li>
			<li><formElement:formInputBox idKey="address.line1" icon="fa-building" labelKey="address.line1" path="billTo_street1" inputCSS="text" mandatory="true" tabindex="${tabindex + 3}"/></li>
			<li><formElement:formInputBox idKey="address.line2" icon="fa-building" labelKey="address.line2" path="billTo_street2" inputCSS="text" mandatory="false" tabindex="${tabindex + 4}"/></li>
			<li><formElement:formInputBox idKey="address.townCity" icon="fa-building" labelKey="address.townCity" path="billTo_city" inputCSS="text" mandatory="true" tabindex="${tabindex + 5}"/></li>
			<li><formElement:formSelectBox idKey="address.region" icon="fa-building" labelKey="address.state" path="billTo_state" mandatory="true" skipBlank="false" skipBlankMessageKey="address.selectState" items="${regions}" itemValue="isocodeShort" tabindex="${tabindex + 6}" selectedValue="${billingAddressForm.regionIso}" selectCSSClass="chosen-select-responsive select"/></li>
			<br></br><br></br>
			<li><formElement:formInputBox idKey="address.postcode" icon="fa-building" labelKey="address.postcode" path="billTo_postalCode" inputCSS="text" mandatory="true" tabindex="${tabindex + 7}"/></li>
		</c:when>
	<%-- 	<c:when test="${country == 'CN'}">
			<formElement:formInputBox idKey="address.postcode" labelKey="address.postcode" path="billTo_postalCode" inputCSS="text" mandatory="true" tabindex="${tabindex + 1}"/>
			<formElement:formSelectBox idKey="address.region" labelKey="address.province" path="billTo_state" mandatory="true" skipBlank="false" skipBlankMessageKey="address.selectProvince" items="${regions}" itemValue="isocodeShort" tabindex="${tabindex + 2}"/>
			<formElement:formInputBox idKey="address.townCity" labelKey="address.townCity" path="billTo_city" inputCSS="text" mandatory="true" tabindex="${tabindex + 3}"/>
			<formElement:formInputBox idKey="address.line2" labelKey="address.district_and_street" path="billTo_street2" inputCSS="text" mandatory="true" tabindex="${tabindex + 4}"/>
			<formElement:formInputBox idKey="address.line1" labelKey="address.building_and_room" path="billTo_street1" inputCSS="text" mandatory="false" tabindex="${tabindex + 5}"/>
			<formElement:formInputBox idKey="address.surname" labelKey="address.surname" path="billTo_lastName" inputCSS="text" mandatory="true" tabindex="${tabindex + 6}"/>
			<formElement:formInputBox idKey="address.firstName" labelKey="address.firstName" path="billTo_firstName" inputCSS="text" mandatory="true" tabindex="${tabindex + 7}"/>
		</c:when>
		<c:when test="${country == 'JP'}">
			<formElement:formInputBox idKey="address.surname" labelKey="address.surname" path="billTo_lastName" inputCSS="text" mandatory="true" tabindex="${tabindex + 1}"/>
			<formElement:formInputBox idKey="address.firstName" labelKey="address.firstName" path="billTo_firstName" inputCSS="text" mandatory="true" tabindex="${tabindex + 2}"/>
			<formElement:formInputBox idKey="address.postcode" labelKey="address.postcodeJP" path="billTo_postalCode" inputCSS="text" mandatory="true" tabindex="${tabindex + 3}"/>
			<formElement:formSelectBox idKey="address.region" labelKey="address.prefecture" path="billTo_state" mandatory="true" skipBlank="false" skipBlankMessageKey="address.selectPrefecture" items="${regions}" itemValue="isocodeShort" tabindex="${tabindex + 4}"/>
			<formElement:formInputBox idKey="address.townCity" labelKey="address.townJP" path="billTo_city" inputCSS="text" mandatory="true" tabindex="${tabindex + 5}"/>
			<formElement:formInputBox idKey="address.line2" labelKey="address.subarea" path="billTo_street2" inputCSS="text" mandatory="true" tabindex="${tabindex + 6}"/>
			<formElement:formInputBox idKey="address.line1" labelKey="address.furtherSubarea" path="billTo_street1" inputCSS="text" mandatory="true" tabindex="${tabindex + 7}"/>
		</c:when> --%>
		<c:otherwise>
			<li><formElement:formInputBox idKey="address.firstName" icon="fa-user" labelKey="address.firstName" path="billTo_firstName" inputCSS="text" mandatory="true" tabindex="${tabindex + 1}"/></li>
			<li><formElement:formInputBox idKey="address.surname" icon="fa-user" labelKey="address.surname" path="billTo_lastName" inputCSS="text" mandatory="true" tabindex="${tabindex + 2}"/></li>
			<li><formElement:formInputBox idKey="address.line1" icon="fa-building" labelKey="address.line1" path="billTo_street1" inputCSS="text" mandatory="true" tabindex="${tabindex + 3}"/></li>
			<li><formElement:formInputBox idKey="address.line2" icon="fa-building" labelKey="address.line2" path="billTo_street2" inputCSS="text" mandatory="false" tabindex="${tabindex + 4}"/></li>
			<li><formElement:formInputBox idKey="address.townCity" icon="fa-building" labelKey="address.townCity" path="billTo_city" inputCSS="text" mandatory="true" tabindex="${tabindex + 5}"/></li>
			<li><formElement:formInputBox idKey="address.postcode" icon="fa-building" labelKey="address.postcode" path="billTo_postalCode" inputCSS="text" mandatory="true" tabindex="${tabindex + 6}" /></li>
		</c:otherwise>
	</c:choose>

</ul>
