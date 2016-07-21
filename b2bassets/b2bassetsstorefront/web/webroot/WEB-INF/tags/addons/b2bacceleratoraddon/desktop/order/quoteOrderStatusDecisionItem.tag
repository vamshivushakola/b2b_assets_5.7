<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="orderData" required="true" type="de.hybris.platform.commercefacades.order.data.OrderData"%>
<%@ attribute name="orderHistoryEntries" required="true" type="java.util.List"%>
<%@ attribute name="quoteOrderForm" required="true" type="de.hybris.platform.b2bacceleratoraddon.forms.QuoteOrderForm"%>
<%@ attribute name="isOrderDetailsPage" type="java.lang.Boolean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/desktop/order"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%--
	~ /*
	~  * [y] hybris Platform
	~  *
	~  * Copyright (c) 2000-2011 hybris AG
	~  * All rights reserved.
	~  *
	~  * This software is the confidential and proprietary information of hybris
	~  * ("Confidential Information"). You shall not disclose such Confidential
	~  * Information and shall use it only in accordance with the terms of the
	~  * license agreement you entered into with hybris.
	~  *
	~  */
--%>

<spring:url value="/my-account/quote/quoteOrderDecision"
	var="quoteOrderDecisionURL" />

<script type="text/javascript"> // set vars
	var quoteActive=true;
	var negotiateQuote = ${quoteOrderForm.negotiateQuote};
</script>

<form:form method="post" id="quoteOrderDecisionForm" commandName="quoteOrderDecisionForm" action="${quoteOrderDecisionURL}">
	
	<%-- <div class="headline">
		<spring:theme code="text.quotes.orderStatusDetails.label" />
	</div>  --%>

		
<form:input type="hidden" name="orderCode" path="orderCode" value="${orderData.code}" />
	<form:input type="hidden" name="selectedQuoteDecision" path="selectedQuoteDecision" id="selectedQuoteDecision" />

	<c:if test="${orderData.status=='APPROVED_QUOTE' || orderData.status=='REJECTED_QUOTE'}">
		<button class="positive" name="NEGOTIATEQUOTE" id="negotiateQuoteButton">
			<spring:theme code="text.quotes.negotiateQuoteButton.displayName" text="Re-Quote" />
		</button>

	</c:if>
	<c:if test="${orderData.status eq 'APPROVED_QUOTE'}">
		<button class="positive" name="ACCEPTQUOTE" id="acceptQuoteButton" onClick="ACC.quote.submitQuoteDecision('ACCEPTQUOTE');return false;">
			<spring:theme code="text.quotes.acceptQuoteButton.displayName" text="Accept Quote" />
		</button>

	</c:if>
	<c:if test="${orderData.status=='APPROVED_QUOTE' || orderData.status=='REJECTED_QUOTE' || orderData.status=='PENDING_QUOTE'}">
		<button class="positive" name="CANCELQUOTE" id="cancelQuoteButton">
			<spring:theme code="text.quotes.cancelQuoteButton.displayName" text="Cancel Quote" />
		</button>
	</c:if>

	<c:if test="${orderData.status=='PENDING_QUOTE'}">
		<button class="positive" name="ADDADDITIONALCOMMENT" id="addAdditionalComment">
			<spring:theme code="text.quotes.addAdditionalComment.displayName" text="Add Comment" />
		</button>
	</c:if>
	<div id="negotiate-quote-div" style="display: none;">
		<h2 class="subtitle">
			<div id="negotiate-quote-div-label-add-comment" style="display: none">
				<spring:theme code="checkout.summary.negotiateQuote.quoteReason" />
			</div>
			<div id="negotiate-quote-div-label-cancel" style="display: none">
				<spring:theme code="checkout.summary.negotiateQuote.quotecancelreason" />
			</div>
		</h2>
		<ul class="form-list">
			<li class="your_cart"><label class="label-input"> Comment <c:if test="${quoteOrderForm.negotiateQuote}">
						<span class="form_field_error">
					</c:if>
			</label>
				<div class="input-box">
					<form:input cssClass="text" id="comments" path="comments" />
				</div></li>
		</ul>
		<ul>
			<form:input type="hidden" name="negotiateQuote" class="negotiateQuoteClass" path="negotiateQuote" />
			<button class="positive negotiateQuote" id="proceedButton">
				<spring:theme code="checkout.summary.negotiateQuote.proceed" />
			</button>
			<button class="positive cancel" id="cancelComment">
				<spring:theme code="checkout.summary.negotiateQuote.cancel" />
			</button>
		</ul>
	</div>
	<%-- <h2 class="subtitle">
		<spring:theme code="text.quotes.orderStatusDetails.label" text="Order Status Details" />
	</h2> --%> <!-- commented by kinnari -->
	 <h2 class="subtitle">
		<spring:theme code="text.quotes.orderStatusDetails.label" /> <!-- added & commented by kinnari -->	
	</h2> 
	<ul>
		<li><spring:theme code="text.account.orderApprovalDetails.OrderNumber" text="Order number" /> : ${orderData.code}</li>
		<li><spring:theme code="text.account.orderApprovalDetails.orderPlacedBy" text="Order placed by" /> : ${orderData.b2bCustomerData.name}</li>
		<li><spring:theme code="text.account.orderApprovalDetails.paidOntoAccount" text="Paid onto account" /> : <c:if test="${orderData.paymentType.code eq 'CARD'}">
				${orderData.paymentInfo.cardNumber}
			</c:if> <c:if test="${orderData.paymentType.code eq 'ACCOUNT'}">
				${orderData.costCenter.code}
			</c:if></li>
		<li><spring:theme code="text.account.orderApprovalDetails.purchaseOrderNumber" text="P.O.No" /> : ${orderData.purchaseOrderNumber}</li>
		<li><spring:theme code="text.account.orderApprovalDetails.parentBusinessUnit" text="Parent Business Unit" /> : ${orderData.costCenter.unit.uid}</li>
		<c:if test="${orderData.paymentType.code eq 'ACCOUNT'}">
			<li><spring:theme code="text.account.orderApprovalDetails.costCenter" text="Cost Center" /> : ${orderData.costCenter.code}</li>
		</c:if>
		<li><spring:theme code="text.account.orderApproval.orderStatus" text="Order Status" /> : <spring:theme code="text.account.order.status.display.${orderData.statusDisplay}" /></li>
	</ul>
	
	<table class="data-table quote-status-details-table">
				<thead>
					<tr class="hidden-xs">
						<th id="header1"><spring:theme code="text.quote.orderHistoryEntry.date" /></th>
						<th id="header2"><spring:theme code="text.quote.orderHistoryEntry.status" /></th>
						<th id="header3"><spring:theme code="text.quote.orderHistoryEntry.user" /></th>
						<th id="header4"><spring:theme code="text.quote.orderHistoryEntry.price" /></th>
						<th id="header5"><spring:theme code="text.quote.orderHistoryEntry.comment" /></th>
						<th id="header6"><spring:theme code="text.quote.orderHistoryEntry.quoteExpirationDate" /></th>
					</tr>
					<tr class="hidden-lg hidden-md hidden-sm">
						<th><spring:theme code="text.quote.orderHistoryEntry.date" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${orderHistoryEntries}" var="orderHistoryEntryData" varStatus="loop">
						<tr class="${loop.last ? 'last' : ''}">
							<td headers="1">${orderHistoryEntryData.timeStamp}</td>
							<td headers="2"><spring:theme code="text.account.order.status.display.${orderHistoryEntryData.previousOrderVersionData.statusDisplay}"/></td>
							<td headers="3">${orderHistoryEntryData.ownerData.name}</td>
							<td headers="4">${orderHistoryEntryData.previousOrderVersionData.totalPrice.formattedValue}</td>
							<td headers="5">${orderHistoryEntryData.previousOrderVersionData.b2BComment.comment}</td>
							<td headers="6" class="last">${orderHistoryEntryData.previousOrderVersionData.quoteExpirationDate}</td>
						</tr>
					 </c:forEach> 
				</tbody>
	</table>
	<h2 class="subtitle">
		<spring:theme code="text.quotes.comments.label" text="Quotes Comments Details" />
	</h2>
		
	<table class="data-table quote-status-details-table">
			<thead>
				<tr class="hidden-xs">
					<th id="header1" width="1px"><spring:theme code="text.quote.orderHistoryEntry.date" /></th>
					<th id="header2" width="1px"><spring:theme code="text.quote.orderHistoryEntry.user" /></th>
					<th id="header3" width="5px"><spring:theme code="text.quote.orderHistoryEntry.comment" /></th>
				</tr>
				<tr class="hidden-lg hidden-md hidden-sm">
					<th><spring:theme code="text.quote.orderHistoryEntry.date" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${orderData.b2bCommentData}" var="b2bComments" varStatus="loop">
					<tr class="${loop.last ? 'last' : ''}">
						<td headers="1">${b2bComments.timeStamp}</td>
						<td headers="2">${b2bComments.ownerData.name}</td>
						<td headers="3" class="last">${b2bComments.comment}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
</form:form>