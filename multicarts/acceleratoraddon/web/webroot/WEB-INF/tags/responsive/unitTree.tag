<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="multicarts" tagdir="/WEB-INF/tags/addons/multicarts/responsive" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="node" required="true"
              type="de.hybris.platform.b2bacceleratorfacades.company.data.B2BUnitNodeData" %>
<%@ attribute name="cart" required="true" type="com.generic.multicarts.data.MultiCartsData" %>
<%@ attribute name="padding" required="false"
              type="java.lang.Integer"  %>

<c:if test="${padding == null}"><c:set var="padding" value="10" /></c:if>


<c:set var="selected" value="deselected" />
<c:forEach items="${cart.shareList}" var="shareUnit">
    <c:if test="${node.id eq shareUnit.b2bunit}">
        <c:set var="selected" value="selected" />
    </c:if>
</c:forEach>

<option style="padding-left: ${padding}px" value="${node.id}" ${selected}>${node.name}</option>

<c:if test="${fn:length(node.children) > 0}">
    <c:forEach var="node" items="${node.children}">
        <multicarts:unitTree node="${node}" padding="${padding + 10}" cart="${cart}"/>
    </c:forEach>
</c:if>




