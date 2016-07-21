<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="formUtil" tagdir="/WEB-INF/tags/desktop/form"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/form"%>

<%@ taglib prefix="order" tagdir="/WEB-INF/tags/addons/ordersearch/desktop" %>

<c:url value="/orderSearch" var="searchUrl" />
<c:url value="/multicarts/selectCart/" var="selectCart" />
<c:url value="/cart" var="redirectUrl"/>
<template:page pageTitle="${pageTitle}">
<jsp:attribute name="pageScripts">
        <script type="text/javascript">
			var selectCartUrl = '${selectCart}';
			
			$(document).ready(function() {
				$(".readCart").on('click', function() {
					$.ajax({
						url : selectCartUrl + $(this).attr("data-code")
					}).done(function() {
						location.href='${redirectUrl}';
					});
				});

				
			});
			
			//Javascript moved here for date picker
			$(function() {
			    $( "#id_creation_date" ).datepicker({ dateFormat: 'dd/mm/yy' }).val();
			  });
						
			</script>
			
    </jsp:attribute>
    <jsp:body>
    
	    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
	  			<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>	  			
	  			<!--below script commeted by venkatesh its not working in firefox
	  				it is moved above jsp:attribute tag 
	  				<script>
				  $(function() {
				    $( "#id_creation_date" ).datepicker({ dateFormat: 'dd/mm/yy' }).val();
				  });
	 			 </script> -->
	 			 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	    <div id="breadcrumb" class="breadcrumb">
	            <breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" />
	    </div>
	    <div class="main-content account-page">
	        <div class="container">
				<div class="row">
		            <div class="col-sm-3">
						<div class="sidebar sidebar-left">
							<nav:accountNav selected="ordersearch" />
						</div>
					</div>
					<div class="col-sm-9">
						<h2 class="subtitle"><spring:theme code="oas.search.title" /></h2>
						<form:form action="${searchUrl}" method="get" commandName="fieldsValues">
							<table>
								<ul class="form-list productsearchform">
									<c:forEach items="${fields}" var="field">
										<li>
											<label class="label-input store" for="id_${field}">
										<i class="fa fa-building"></i>
										<spring:theme code="oas.search.attr.${field}" />
											<span class="skip"></span>
										</label>
										
										<div class="input-box">
											<input id="id_${field}" name="fields[${field}]" class="text" type="text" value="">
											</div>
										</li>
									</c:forEach>
									<li>
										<button class="form btn btn-primary" >
											<spring:theme code="oas.search.button" />
										</button>
									</li>
								</ul>
							</table>
						</form:form>
						<div>
							<div>
								<span></span>
								<h3>
									<spring:theme code="search.product.results.title" text="Results"/><a href="#" class="edit"></a>
								</h3>
							</div>
						<!-- 	<div>
							<div> -->
							<nav:pagination top="true" supportShowPaged="false"
						                        supportShowAll="false"
						                        searchPageData="${searchPageData}"
						                        searchUrl="${searchNext}&sort=${searchPageData.pagination.sort}"
						                        numberPagesShown="${numberPagesShown}"
						                        sortQueryParams="${sortParams}"
					                        msgKey="order.search.page" />
					    </div>
					    <div>
					        <table id="order_history" class="data-table">
					        	<thead>
						            <tr class="hidden-xs">
						                <th class="width-100"><spring:theme code="order.search.page.result.title.code" text="Cart Number (Web Order)" /></th>
										<%-- Commented by venkatesh, as from v5 cart_name is at cart level 
											<th class="width-100"><spring:theme code="order.search.page.result.title.name" text="Name" /></th> --%>
<!-- 						                <th class="width-0"></th> -->
						                <th class="width-100"><spring:theme code="order.search.page.result.title.type" text="Type" /></th>
						                <th class="width-100"><spring:theme code="order.search.page.result.title.user" text="User" /></th>
						                <th class="width-100"><spring:theme code="order.search.page.result.title.date" text="Creation Date" /></th>
						            </tr>
						            <tr class="hidden-lg hidden-md hidden-sm">
										<th>
											<spring:theme code="order.search.page.result.title.code" text="Cart Number (Web Order)" />
										</th>
									</tr>
								</thead>
								<tbody>
						            <c:forEach items="${searchPageData.results}" var="order">
						                <order:orderSearchResult  order="${order}"/>
						            </c:forEach>
					            </tbody>
					        </table>
					    </div>
						<div>
							<nav:pagination top="false" supportShowPaged="false"
					                        supportShowAll="false"
					                        searchPageData="${searchPageData}"
					                        searchUrl="${searchNext}&sort=${searchPageData.pagination.sort}"
					                        numberPagesShown="${numberPagesShown}"
					                        sortQueryParams="${sortParams}"
					                        msgKey="order.search.page"  />
						</div>    
					</div>
				</div>
			</div>
		</div>
	</jsp:body>
</template:page>