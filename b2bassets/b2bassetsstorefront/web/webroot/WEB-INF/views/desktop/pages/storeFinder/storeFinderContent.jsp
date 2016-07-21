<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="store" tagdir="/WEB-INF/tags/desktop/store" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>

<div class="main-content store-find">
	<div class="container">
			<store:storeSearch errorNoResults="${errorNoResults}"/>
			<store:storesMap storeSearchPageData="${searchPageData}"/>
			<store:storeListForm searchPageData="${searchPageData}" locationQuery="${locationQuery}" numberPagesShown="${numberPagesShown}" geoPoint="${geoPoint}"/>
	</div>
</div>