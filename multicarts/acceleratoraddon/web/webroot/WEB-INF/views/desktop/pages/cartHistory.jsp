<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>

<%@ taglib prefix="multicarts" tagdir="/WEB-INF/tags/addons/multicarts/desktop" %>

<template:page pageTitle="MultiCarts" >
    <jsp:attribute name="pageScripts">
    </jsp:attribute>

    <jsp:body>
        <div id="breadcrumb" class="breadcrumb">
            <breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>
        </div>
        <div id="globalMessages">
            <common:globalMessages/>
        </div>
        <div class="main-content account-page">
        <div class="container">
			<div class="row">
        		<div class="col-sm-3">
					<div class="sidebar sidebar-left">
						<nav:accountNav />
					</div>
				</div>
               <div class="col-sm-9">
									<h2 class="subtitle">
                    
                        ${selectedCart.name}
                    </h2>
                <div class="item_container">
                    <c:choose>
                        <c:when test="${fn:length(logs) gt 0}">

                            <table>
                                <tr>
                                    <th><spring:theme code="text.multicarts.history.user" /></th>
                                    <th><spring:theme code="text.multicarts.history.message" /></th>
                                </tr>
                                <c:forEach items="${logs}" var="log">
                                    <multicarts:logDetails log="${log}" />
                                </c:forEach>
                            </table>
                        </c:when>
                        <c:otherwise>
                            <spring:theme code="text.multicarts.history.nologs" />
                        </c:otherwise>
                    </c:choose>

                </div>
            </div>
        </div>
        </div>
        </div>
    </jsp:body>
</template:page>



