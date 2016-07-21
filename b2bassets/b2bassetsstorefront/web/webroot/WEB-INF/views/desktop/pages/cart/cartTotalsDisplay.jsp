<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>

<cart:ajaxCartTotals/>
<cart:cartTotals cartData="${cartData}" showTaxEstimate="${taxEstimationEnabled}"/>