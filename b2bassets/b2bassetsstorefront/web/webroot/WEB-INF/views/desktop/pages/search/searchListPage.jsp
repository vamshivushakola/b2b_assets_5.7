<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="storepickup" tagdir="/WEB-INF/tags/desktop/storepickup" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>

<template:page pageTitle="${pageTitle}">
	<div id="breadcrumb" class="breadcrumb">
		<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" />
	</div>
	<div id="globalMessages">
		<common:globalMessages/>
	</div>
<!-- 	<div class="span-6 facetNavigation"> -->
	<div class="main-content search-list">
		<div class="container">
			<div class="row">
				<div class="col-sm-3">
					<div class="sidebar">
						<cms:pageSlot position="ProductLeftRefinements" var="feature">
							<cms:component component="${feature}"/>
						</cms:pageSlot>
					</div>
				</div>
				
				<div class="col-sm-9">
<!-- 				<div class="spasn-18 last"> -->
					<cms:pageSlot position="SearchResultsListSlot" var="feature">
						<cms:component component="${feature}"/>
					</cms:pageSlot>
				</div>
				<storepickup:pickupStorePopup />
	
			</div>
		</div>
	</div>
</template:page>