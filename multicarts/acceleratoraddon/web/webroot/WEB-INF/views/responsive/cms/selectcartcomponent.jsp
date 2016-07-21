<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="multicarts" tagdir="/WEB-INF/tags/addons/multicarts/responsive" %>

<c:if test="${fn:length(carts) gt 1}">
    <multicarts:selectCart carts="${carts}" selected="${currentCart}"/>
</c:if>




