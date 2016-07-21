<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="log" required="true" type="com.generic.multicarts.data.CartLogData" %>

<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mltlogs" uri="http://multicarts.generic.com/jsp/lib/functions"%>

<spring:theme code="${log.message}" var="message" />

<tr>
    <td>${log.userName}</td>
    <td>${mltlogs:formatLog(log, message)}</td>
</tr>