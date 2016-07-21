<%-- <%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>


<cms:pageSlot position="Footer" var="feature" element="div" class="footer">
	<cms:component component="${feature}"/>
</cms:pageSlot> --%>


<!-- added by namrata 5.5 -->
<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<cms:pageSlot position="Footer" var="feature" element="div">
	<cms:component component="${feature}" />
</cms:pageSlot>
