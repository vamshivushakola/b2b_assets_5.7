<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="storeSearchPageData" required="false" type="de.hybris.platform.commerceservices.storefinder.data.StoreFinderSearchPageData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<c:if test="${storeSearchPageData ne null and !empty storeSearchPageData.results}">
<div class="row">
	<div class="col-xs-12">
	<div class="storeMap">
			<div  id="map_canvas" 
				data-latitude = '${searchPageData.sourceLatitude}'
				data-longitude = '${searchPageData.sourceLongitude}'
				data-south-Latitude = '${searchPageData.boundSouthLatitude}'
				data-west-Longitude = '${searchPageData.boundWestLongitude}'
				data-North-Latitude = '${searchPageData.boundNorthLatitude}'
				data-east-Longitude = '${searchPageData.boundEastLongitude}'
		
				data-stores= '{
			<c:forEach items="${storeSearchPageData.results}" var="singlePos" varStatus="status">
				<c:if test="${(status.index != 0)}">,</c:if>"store${status.index}":{"id":"${status.index}","latitude":"${singlePos.geoPoint.latitude}","longitude":"${singlePos.geoPoint.longitude}","name":"${singlePos.name}"}
			</c:forEach>
				}'
				
			></div>
	</div>
	
		</div>
	</div>
</c:if>
