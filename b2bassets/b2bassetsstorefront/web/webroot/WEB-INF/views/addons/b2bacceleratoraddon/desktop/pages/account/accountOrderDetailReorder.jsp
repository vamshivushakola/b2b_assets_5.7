<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/addons/b2bacceleratoraddon/desktop/order" %>
<%@ taglib prefix="orderTotal" tagdir="/WEB-INF/tags/desktop/order" %>

<div class="row">
	<div class="col-sm-offset-7 col-sm-5">
		<orderTotal:orderTotalsItem order="${orderData}"/>
		<order:reorderButton order="${orderData}"/>
	</div>
</div>