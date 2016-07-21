<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="formUtil" tagdir="/WEB-INF/tags/desktop/form"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/form"%>


<%-- <c:url value="/productAdvancedSearch/search" var="searchUrl" /> --%>
<c:url value="/productAdvancedSearch" var="searchUrl" />

<template:page pageTitle="${pageTitle}">  
 <jsp:body>    
    <div id="breadcrumb" class="breadcrumb">
		<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>
	</div>
	<div id="globalMessages">
		<common:globalMessages />
	</div>	
	<div class="main-content search-list">
			<div class="container">
				<div class="row">
					<%-- <div class="col-sm-3">
						<div class="sidebar">
							<nav:facetNavAppliedFilters pageData="${searchPageData}" />
							<nav:facetNavRefinements pageData="${searchPageData}" />
						</div>
					</div> --%>
                    <div class="col-sm-12">
                    <h2><spring:theme code="advanced.product.search.title"/></h2>
                    <form:form action="${searchUrl}" method="get" commandName="fieldsValues">
						<ul class="form-list productsearchform">
							<c:forEach items="${fields}" var="field">
								<li>
									<label class="label-input store" for="id_${field}">
										<i class="fa fa-building"></i>
											<spring:theme code="advanced.product.search.attr.${field}" />
										<span class="skip"></span>
									</label>

									<div class="input-box">
										<input id="id_${field}" name="fields[${field}]" class="text" type="text" value="">
									</div>
								</li>
							</c:forEach>
							<li>
								<input class="btn btn-primary" type="submit" value="<spring:theme code="advanced.product.search.button" />"/>
							</li>
						</ul>
                    </form:form>
               
   <%--  <div class="span-4">
        <nav:facetNavAppliedFilters pageData="${searchPageData}"/>
        <nav:facetNavRefinements pageData="${searchPageData}"/>
    </div> --%>
  
			    <nav:pagination top="true" supportShowPaged="false"
			                        supportShowAll="false"
			                        searchPageData="${searchPageData}"
			                        searchUrl="${searchNext}&sort=${searchPageData.pagination.sort}"
			                        numberPagesShown="${numberPagesShown}"
			                        sortQueryParams="${sortParams}"			                       
			                        msgKey="product.search.page"
			                />
			                 		
            <div>
                <ol class="products-list" data-isOrderForm="false">
                    <c:forEach items="${searchPageData.results}" var="product">
                        <product:productListerItem product="${product}"/>
                    </c:forEach>
                    </ol>
                </div>
            <div>
			    <nav:pagination top="false" supportShowPaged="false"
			                        supportShowAll="false"
			                        searchPageData="${searchPageData}"
			                        searchUrl="${searchNext}&sort=${searchPageData.pagination.sort}"
			                        numberPagesShown="${numberPagesShown}"
			                        sortQueryParams="${sortParams}"
			                        msgKey="order.search.page"
			                />
			                </div> 
			                </div>
           
        </div>
    </div>
    </div>
    </jsp:body>
</template:page>