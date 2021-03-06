<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="errorNoResults" required="true" type="java.lang.String"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement"%>


<%-- <c:url value="/store-finder" var="storeFinderFormAction" />
<c:url value="/store-finder/position" var="nearMeStorefinderFormAction"/>

<div class="searchPane">
	<div class="headline"><spring:theme code="storeFinder.find.a.store" /></div>
	<div class="description"><spring:theme code="storeFinder.use.this.form" /></div>
	
	<form:form action="${storeFinderFormAction}" method="get" commandName="storeFinderForm">
		<ycommerce:testId code="storeFinder_search_box">
			<formElement:formInputBox idKey="storelocator-query" labelKey="storelocator.query" path="q" inputCSS="text" mandatory="true" />
			<button class="positive" type="submit"><spring:theme code="storeFinder.search" /></button>
		</ycommerce:testId>
	</form:form>
	
	<hr>
	
	<form:form id="nearMeStorefinderForm" name="near_me_storefinder_form" method="POST" action="${nearMeStorefinderFormAction}">
		<input type="hidden" id="latitude" name="latitude"/>
		<input type="hidden" id="longitude" name="longitude"/>
		<button href="#" id="findStoresNearMe" class="positive input-block-level findStoresNearMe" type="submit"><spring:theme code="storeFinder.findStoresNearMe"/></button>
	</form:form>
	
</div> --%>

<h2 class="subtitle">
					<span class="inline-title"><spring:theme code="storeFinder.find.a.store" /></span>
				</h2>
				<p>
					<spring:theme code="storeFinder.use.this.form" />
				</p>
				<c:url value="/store-finder" var="storeFinderFormAction" />
				<form:form action="${storeFinderFormAction}" method="get" commandName="storeFinderForm">
					<ycommerce:testId code="storeFinder_search_box">
					<input type="hidden" id="latitude1" name="latitude" />
					<input type="hidden" id="longitude1" name="longitude" />
					<ul class="form-list">
							<li><formElement:formInputBox icon="fa-building" idKey="storeFinder.postcode.town" labelKey="storeFinder.postcode.town" path="q" labelCSS="store" inputCSS="text" mandatory="true" /></li>
						</ul>
						<button class="form search">
							<span class="search-icon"> <spring:theme code="storeFinder.search" />
							</span>
						</button>
					</ycommerce:testId>
				</form:form>
				<c:url value="/store-finder/position" var="nearMeStorefinderFormAction" />
				<form:form id="nearMeStorefinderForm" name="near_me_storefinder_form" method="POST" action="${nearMeStorefinderFormAction}">
					<input type="hidden" id="latitude" name="latitude" />
					<input type="hidden" id="longitude" name="longitude" />
					<span class="find-stores-near-me"> <a href="#" id="findStoresNearMe"> <spring:theme code="storeFinder.findStoresNearMe" />
					</a>
					</span>
				</form:form>
