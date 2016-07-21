<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>

<div class="block-content">
	<dl>
		<dt>
			<c:if test="${title=='my-account'}">				
				<spring:theme code="text.account.yourAccount" text="Your Account" /> 
			</c:if>
			<c:if test="${title=='my-company'}">			
				<spring:theme code="text.company.myCompany" text="My Company" />
			</c:if>
 		</dt> 

<!-- commented/added by kinnari for side navigation component -->
		<%-- <dd>
            	<c:forEach items="${navigationNode.links}" var="link">
                	<c:set value="${requestScope['javax.servlet.forward.servlet_path'] == link.url ? 'active':'' }" var="selected"/>
                	<cms:component component="${link}" evaluateRestriction="true" element="li" class=" ${selected}"/>
            	</c:forEach>
        </dd>  --%>
        
            	<c:forEach items="${navigationNode.links}" var="link">
                	<c:set value="${requestScope['javax.servlet.forward.servlet_path'] == link.url ? 'active':'' }" var="selected"/>
                	<cms:component component="${link}" evaluateRestriction="true" element="dd" class=" ${selected}"/>
            	</c:forEach>
        
				
	</dl>
	
</div>