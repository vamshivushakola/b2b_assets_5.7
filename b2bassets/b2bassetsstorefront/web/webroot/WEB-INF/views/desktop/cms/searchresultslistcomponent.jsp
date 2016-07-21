<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="storepickup" tagdir="/WEB-INF/tags/desktop/storepickup" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>

<div class="results">
	<h2 class="subtitle">
		<span class="inline-title"><spring:theme code="search.page.searchText" arguments="${searchPageData.freeTextSearch}" /></span>
	</h2>
<%-- 	<h1><spring:theme code="search.page.searchText" arguments="${searchPageData.freeTextSearch}"/></h1> --%>
</div>

<nav:searchSpellingSuggestion spellingSuggestion="${searchPageData.spellingSuggestion}" />

<nav:pagination top="true"  supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="${searchPageData.currentQuery.url}"  numberPagesShown="${numberPagesShown}"/>

<c:url value="/search" var="currentURL" />
<div id="currentPath" data-current-path="${currentURL}"></div>

<!-- <div class="productList"> -->
<ol class="products-list" data-isOrderForm="false">
	<c:forEach items="${searchPageData.results}" var="product">
		<product:productListerItem product="${product}"/>
	</c:forEach>
<!-- </div> -->
</ol>

<%-- <common:infiniteScroll /> --%>

<nav:pagination top="false"  supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="${searchPageData.currentQuery.url}"  numberPagesShown="${numberPagesShown}"/>
<storepickup:pickupStorePopup />