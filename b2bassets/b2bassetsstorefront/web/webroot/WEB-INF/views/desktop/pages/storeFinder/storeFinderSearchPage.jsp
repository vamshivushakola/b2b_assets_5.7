<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<template:page pageTitle="${pageTitle}">

<jsp:attribute name="pageScripts">
		<c:if test="${!empty googleApiVersion}">
			<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?v=${googleApiVersion}&key=${googleApiKey}&sensor=false"></script>
		</c:if>
	</jsp:attribute>
	
	<jsp:body>
	<div id="breadcrumb" class="breadcrumb">
			<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" />
	</div>
	<div id="globalMessages">
		<common:globalMessages/>
	</div>
	
	<cms:pageSlot position="TopContent" var="feature">
		<cms:component component="${feature}"  element="div" class="top-content-slot cms_disp-img_slot"  />
	</cms:pageSlot>

	<cms:pageSlot position="MiddleContent" var="feature">
		<cms:component component="${feature}"  element="div"/>
	</cms:pageSlot>


</jsp:body>
</template:page>