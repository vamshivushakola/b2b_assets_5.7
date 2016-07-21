<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>

<template:page pageTitle="${pageTitle}">
<div id="breadcrumb" class="breadcrumb">
		<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" />
	</div>
	<div id="globalMessages">
		<common:globalMessages/>
	</div>
	<cms:pageSlot position="Section1" var="feature">
		<cms:component component="${feature}" element="div" class="span-24 section1 cms_disp-img_slot"/>
	</cms:pageSlot>
	
	
	<div class="main-content product-list">
		<div class="container">
			<div class="row">
				<div class="col-sm-3">
					<div class="sidebar">
						<!-- <div class="span-24"> 
						<div class="span-6 facetNavigation">-->
							<cms:pageSlot position="ProductLeftRefinements" var="feature">
								<cms:component component="${feature}"/>
							</cms:pageSlot>
						<!--</div> -->
					</div>
				</div>
		
				<div class="col-sm-9">
				<!--<div class="span-18 last"> -->
					<cms:pageSlot position="ProductListSlot" var="feature">
						<cms:component component="${feature}"/>
					</cms:pageSlot>
				</div>
				<!--</div> -->
			</div>
		</div>
	</div>
</template:page>