<%@ page trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="order" tagdir="/WEB-INF/tags/addons/b2bacceleratoraddon/desktop/order" %>

<!-- <div class="column accountContentPane clearfix orderList"> -->
<div class="col-sm-9">
	<order:quoteOrderStatusDecisionItem quoteOrderForm="${quoteOrderDecisionForm}" orderData ="${orderData}" orderHistoryEntries="${orderHistoryEntryData}"/>
</div>
<!-- </div> -->