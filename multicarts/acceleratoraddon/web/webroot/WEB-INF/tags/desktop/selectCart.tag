<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="carts" required="true" type="java.util.List" %>
<%@ attribute name="selected" required="true" type="com.generic.multicarts.data.MultiCartsData" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:url value="/multicarts/selectCart/" var="selectCartURL" />

<script type="text/javascript">
    ACC.multicartsSelectCartUrl = "${selectCartURL}";
</script>

<div class="multicarts-selectcartcomponent">
    <p><spring:theme code="text.multicarts.component.currentcart" /></p>
    <select>
        <c:forEach items="${carts}" var="cart">
            <c:choose>
                <c:when test="${cart.code eq selected.code}">
                    <option value="${cart.code}" selected>${cart.name}</option>
                </c:when>
                <c:otherwise>
                    <option value="${cart.code}">${cart.name}</option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select>
</div>