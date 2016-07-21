<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="order" required="true" type="com.generic.ordersearch.OrderSearchResultData" %>

<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:url value="/my-account/order/" var="orderDetails" />



<tr>
		<td>
		<c:choose>
            <c:when test="${order.type eq 'ORDER'}">
                <a href="${orderDetails}${order.code}" data-code="${order.code}">${order.code}</a>
            </c:when>
            <c:otherwise>
                <a href="#" class="readCart" data-code="${order.code}">${order.code}</a>
            </c:otherwise>
        </c:choose>
    </td>
    <td>${order.name}</td>
   	<td>${order.type}</td>
   	<td>${order.user}</td>
   	<td>${order.date}</td>
</tr>