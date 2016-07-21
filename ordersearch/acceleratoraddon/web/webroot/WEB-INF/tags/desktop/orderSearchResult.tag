<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="order" required="true" type="com.generic.ordersearch.OrderSearchResultData" %>
<%-- <%@ attribute name="varStatus" required="true" type="javax.servlet.jsp.jstl.core.LoopTagSupport.LoopTagStatus" %> --%>

<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:url value="/my-account/order/" var="orderDetails" />



<tr>
	<td>
		<span class="hidden-lg hidden-md hidden-sm mobile-label">
			<spring:theme code="order.search.page.result.title.code" text="Cart Number (Web Order)" /> : 
		</span>
		<c:choose>
            <c:when test="${order.type eq 'ORDER'}">
                <a href="${orderDetails}${order.code}" data-code="${order.code}">${order.code}</a>
            </c:when>
            <c:otherwise>
                <a href="#" class="readCart" data-code="${order.code}">${order.code}</a>
            </c:otherwise>
        </c:choose>
    </td>
    <%--Commented by venkatesh, as from v5 cart_name is at cart level 
    	<td>
    	<span class="hidden-lg hidden-md hidden-sm mobile-label">
			<spring:theme code="order.search.page.result.title.name" text="Name" /> : 
		</span>
    	${order.name}
    </td> --%>
   	<td>
  	 	<span class="hidden-lg hidden-md hidden-sm mobile-label">
			<spring:theme code="order.search.page.result.title.type" text="Type" /> : 
		</span>
   		${order.type}
   	</td>
   	<td>
   		<span class="hidden-lg hidden-md hidden-sm mobile-label">
			 <spring:theme code="order.search.page.result.title.user" text="User" /> :
		</span>
   		${order.user}
   	</td>
   	<td class="last">
   		<span class="hidden-lg hidden-md hidden-sm mobile-label">
			<spring:theme code="order.search.page.result.title.date" text="Creation Date" /> :
		</span>
   		${order.date}
   	</td>
</tr>