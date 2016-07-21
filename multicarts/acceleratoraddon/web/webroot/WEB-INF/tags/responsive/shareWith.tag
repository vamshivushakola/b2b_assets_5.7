<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="cart" required="true" type="com.generic.multicarts.data.MultiCartsData" %>
<%@ attribute name="selected" required="true" type="java.lang.String" %>


<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="multicarts" tagdir="/WEB-INF/tags/addons/multicarts/responsive" %>

<select data-code="${cart.code}" data-placeholder="<spring:theme code="text.multicarts.list.B2BUnit" />" multiple="" tabindex="1">
    <multicarts:unitTree node="${B2BUnits}" cart="${cart}"/>
</select>