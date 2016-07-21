<%-- <%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<c:url value="/search/" var="searchUrl"/>
<c:url value="/search/autocomplete/${component.uid}" var="autocompleteUrl"/>


<div class="siteSearch">
	<form name="search_form_${component.uid}" method="get" action="${searchUrl}">
		<div class="control-group">
			<spring:theme code="text.search" var="searchText"/>
			<label class="control-label skip" for="input_${component.uid}">${searchText}</label>
			<div class="controls">
				<spring:theme code="search.placeholder" var="searchPlaceholder"/>
				<ycommerce:testId code="header_search_input">
					<input 
						id="input_${component.uid}" 
						class="siteSearchInput left" 
						type="text" 
						name="text"
						value="" 
						maxlength="100" 
						placeholder="${searchPlaceholder}" 
						data-options='{"autocompleteUrl" : "${autocompleteUrl}","minCharactersBeforeRequest" : "${component.minCharactersBeforeRequest}","waitTimeBeforeRequest" : "${component.waitTimeBeforeRequest}","displayProductImages" : ${component.displayProductImages}}'/>
				</ycommerce:testId>
				<ycommerce:testId code="header_search_button">
					<button class="siteSearchSubmit" type="submit"/></button>
				</ycommerce:testId>
			</div>
		</div>
	</form>
</div> --%>

<!-- added by namrata -->
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>

<c:url value="/search/" var="searchUrl" />
<c:url value="/search/autocomplete/${component.uid}" var="autocompleteUrl" />


<div class="siteSearch search">
	<form name="search_form" method="get" action="<c:url value="/search"/>">
		<spring:theme code="text.search" var="searchText" />
		<label class="skip" for="search">${searchText}</label>
		<spring:theme code="search.placeholder" var="searchPlaceholder" />
		<ycommerce:testId code="header_search_input">
			<input id="search" class="text" type="text" name="text" value="" maxlength="100" placeholder="${searchPlaceholder}" data-options='{"autocompleteUrl" : "${autocompleteUrl}","minCharactersBeforeRequest" : "${component.minCharactersBeforeRequest}","waitTimeBeforeRequest" : "${component.waitTimeBeforeRequest}","displayProductImages" : ${component.displayProductImages}}' />
		</ycommerce:testId>
		<ycommerce:testId code="header_search_button">
			<spring:theme code="img.searchButton" text="/" var="searchButtonPath" />
			<c:choose>
				<c:when test="${originalContextPath ne null}">
					<c:url value="${searchButtonPath}" context="${originalContextPath}" var="searchImageUrl" />
				</c:when>
				<c:otherwise>
					<c:url value="${searchButtonPath}" var="searchImageUrl" />
				</c:otherwise>
			</c:choose>
			<input class="button" type="image" src="${searchImageUrl}" alt="${searchText}" />
		</ycommerce:testId>
	</form>
</div>
