<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>

<template:page pageTitle="${pageTitle}">
	<cms:pageSlot position="Section1" var="feature">
		<cms:component component="${feature}" element="div"  class="header-banner" />
	</cms:pageSlot>

	<div id="globalMessages">
		<common:globalMessages />
	</div>

	<div class="container">
		<cms:pageSlot position="Section2A" var="feature" element="div" class="quick-order-component col-md-6 zone_a cms_disp-img_slot">
			<cms:component component="${feature}" />
		</cms:pageSlot>

		<cms:pageSlot position="Section2B" var="feature" element="div" class="col-md-3 col-sm-6 zone_b thumbnail_detail">
			<cms:component component="${feature}" />
		</cms:pageSlot>

		<cms:pageSlot position="Section2C" var="feature" element="div" class="col-md-3 col-sm-6 zone_c last thumbnail_detail">
			<cms:component component="${feature}" />
		</cms:pageSlot>
	</div>
	<div class="main-content">
		<div class="container">
			<cms:pageSlot position="Section3" var="feature" element="div">
				<cms:component component="${feature}" />
			</cms:pageSlot>
		</div>
		<div class="container">
			<div class="featured-products">
				<cms:pageSlot position="Section4" var="feature" element="div" id="brand-grid" class="products-grid owl-carousel">
					<cms:component component="${feature}" element="div" class="category" />
				</cms:pageSlot>
			</div>
		</div>
		<cms:pageSlot position="Section5" var="feature" element="div" class="span-24 section5 cms_disp-img_slot">
			<cms:component component="${feature}" />
		</cms:pageSlot>
	</div>
</template:page>