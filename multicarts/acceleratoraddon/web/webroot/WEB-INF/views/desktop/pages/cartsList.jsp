<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="common"	tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"	prefix="fn"%>
<%@ taglib prefix="nav"	tagdir="/WEB-INF/tags/desktop/nav"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>
<%@ taglib prefix="multicarts" tagdir="/WEB-INF/tags/addons/multicarts/desktop"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url value="/multicarts/createCart/"	var="createCartURL" />
<c:url value="/multicarts/selectCart/"	var="selectCartURL" />
<c:url value="/multicarts/deleteCart/"	var="deleteCartURL" />
<c:url value="/multicarts/share/"	var="shareURL" />
<c:url value="/multicarts/unshare/"	var="unshareURL" />
<c:url value="/multicarts/setCartName/"	var="setCartNameURL" />

<template:page pageTitle="MultiCarts">
	<jsp:attribute name="pageCss">
        <style>
			.hide-editform {
				display: none;
			}

			#multicarts-editform {
				padding: 20px;
				width: 350px;
			}

			.createtocart-input {
				height: 35px;
				padding: 0 10px;
			}
		</style>
    </jsp:attribute>
	<jsp:attribute name="pageScripts">
        <script type="text/javascript">
			var selectCartUrl = '${selectCartURL}';
			var deleteCartURL = '${deleteCartURL}';
			var shareURL = '${shareURL}';
			var unshareURL = '${unshareURL}';
			var setCartName = '${setCartNameURL}';
			$(document).ready(function() {
				$(".multicarts-select-cart").click(function() {
					$.ajax({
						url : selectCartUrl + $(this).attr("data-code")
					}).done(function() {
						location.reload();
					});
				});

				$(".createtocart-input").keypress(function(){
					
		            if($(this).val != ''){
		               $(".form").prop('disabled', false);
		             }
		        });
				
								
				$(".addon-multicarts-delete a").click(function() {
					$.ajax({
						type : "POST",
						url : deleteCartURL + $(this).attr("data-code")
					}).done(function() {
						location.reload();
					});
				});

				$("#addon-multicarts-list .select-action select").chosen({
					search_contains : true
				});

				$("#addon-multicarts-list .select-action select").on('change', function(evt, params) {
					if (params.deselected != null) {
						$.ajax({
							type : "POST",
							url : unshareURL + $(this).attr("data-code"),
							data : {
								b2bunit : params.deselected
							}
						}).done(function() { });
					}
					if (params.selected != null) {
						$.ajax({
							type : "POST",
							url : shareURL + $(this).attr("data-code"),
							data : {
								b2bunit : params.selected
							}
						}).done(function() {});
					}
				});

				$(".multicarts-editcart-action").colorbox({
					inline : true,
					href : "#multicarts-editform",
					width : 450,
					onLoad : function() {
						$(".hide-editform").show();
						$("#multicarts-editform input[name='name']").val($(this).text());
						$("#multicarts-editform input[name='cartCode']").val($(this).attr('data-code'));},
					onComplete : function() {
						$.colorbox.resize();
					},
					onClosed : function() {
						$(".hide-editform").hide();
					}
				});

				$("#multicarts-editform input[type='button']").click(function() {
					$.ajax({
						type : "POST",
						url : setCartName + $("#multicarts-editform input[name='cartCode']").val(),
						data : {
							name : $("#multicarts-editform input[name='name']").val()
						}
					}).done(function() {
						location.reload();
					});
				});
				
			});
			
			</script>
    </jsp:attribute>
	<jsp:body>
        <div
			id="breadcrumb"
			class="breadcrumb">
            <breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" />
        </div>
        <div id="globalMessages">
            <common:globalMessages />
        </div>
        <div class="main-content account-page">
        <div class="container">
			<div class="row">
        		<div class="col-sm-3">
					<div class="sidebar sidebar-left">
						<nav:accountNav selected="my-carts" />
					</div>
				</div>
        
                        			<div class="col-sm-9">
									<h2 class="subtitle">
										<spring:theme code="text.multicarts.list.title" />
									</h2>
                    
                    <div
							class="item_container addon-multicarts-not-hidden">
                        <form>
                            <table id="addon-multicarts-list" class="orderListTable">
                                <tr>
                                    <th></th>
                                    <th></th>
                                    <th><spring:theme
												code="text.multicarts.list.cart.name" /></th>
                                    <th><spring:theme
												code="text.multicarts.list.share" /></th>
                                    <th></th>
                                    <th></th>
                                </tr>
                                <c:forEach
										items="${carts}"
										var="cart">
                                    <c:choose>
                                        <c:when test="${cart.isOwner}">
                                            <multicarts:cartListLineEdit
													cart="${cart}"
													selected="currentCart.code"
													share="${shareAction}"
													delete="${deleteAction}"
													notification="${notificationAction}"
													history="${historyAction}" />
                                        </c:when>
                                        <c:otherwise>
                                            <multicarts:cartListLineView
													cart="${cart}"
													selected="currentCart.code"
													history="${historyAction}"
													notification="${notificationAction}" />
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </table>
                        </form>
                        <div
								id="multicarts-editform"
								class="hide-editform">
                            <h3>
											<spring:theme code="text.multicarts.setname.title" />
										</h3>
                            <form>
                                <table>
                                    <tr>
                                        <th>Attribute name</th>
                                        <th>Value</th>
                                    </tr>
                                    <tr>
                                        <td><spring:theme
													code="text.multicarts.setname.name" /></td>
                                        <td><input
												type="text"
												name="name" /></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td><input
												type="button"
												value="<spring:theme code="text.multicarts.setname.submit" />" /></td>
                                    </tr>
                                </table>
                                <input
										type="hidden"
										name="cartCode" />

                            </form>
                        </div>
                    </div>
                </div>
            
                  
                        <div class="col-sm-9" id="create">
									<h2 class="subtitle">
										<spring:theme code="text.multicarts.list.addcart.title" />
									</h2>
                    	
                    	<form:form id="createCart" action="multicarts/createCart" method="post" commandName="createCart">
							<ul>
								<li>
									<input class="createtocart-input" type="text" id="cartName" name="cartName" placeholder="<spring:theme code="text.multicarts.list.addcart.placeholder" />" />
									<button class="form" disabled>
										<spring:theme code="text.multicarts.list.addcart" />
									</button>
								</li>
							</ul>
						</form:form>
                       
                        </div>
                       
                        
        </div>
         
        </div>
        </div>
        
    </jsp:body>
</template:page>
