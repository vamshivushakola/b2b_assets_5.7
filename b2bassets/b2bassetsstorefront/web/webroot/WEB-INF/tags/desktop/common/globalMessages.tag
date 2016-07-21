<%-- <%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags"%>

Information (confirmation) messages
<c:if test="${not empty accConfMsgs}">
		<c:forEach items="${accConfMsgs}" var="msg">
			<div class="alert positive">
				<spring:theme code="${msg.code}" arguments="${msg.attributes}"/>
			</div>
		</c:forEach>
</c:if>

Warning messages
<c:if test="${not empty accInfoMsgs}">
		<c:forEach items="${accInfoMsgs}" var="msg">
			<div class="alert neutral">
				<spring:theme code="${msg.code}" arguments="${msg.attributes}"/>
			</div>
		</c:forEach>
</c:if>

Error messages (includes spring validation messages)
<c:if test="${not empty accErrorMsgs}">
		<c:forEach items="${accErrorMsgs}" var="msg">
			<div class="alert negative">
				<spring:theme code="${msg.code}" arguments="${msg.attributes}"/>
			</div>
		</c:forEach>
</c:if> --%>

<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<%-- Information (confirmation) messages --%>
<c:if test="${not empty accConfMsgs}">
	<div class="container">
		<div class="info">
			<ul>
				<c:forEach items="${accConfMsgs}" var="msg">
					<li><spring:theme code="${msg.code}" arguments="${msg.attributes}" /></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</c:if>

<%-- Warning messages --%>
<c:if test="${not empty accInfoMsgs}">
	<div class="container">
		<div class="warning">
			<ul>
				<c:forEach items="${accInfoMsgs}" var="msg">
					<li><spring:theme code="${msg.code}" arguments="${msg.attributes}" /></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</c:if>

<%-- Error messages (includes spring validation messages)--%>
<c:if test="${not empty accErrorMsgs}">
	<div class="container">
		<div class="error">
			<ul>
				<c:forEach items="${accErrorMsgs}" var="msg">
					<li><spring:theme code="${msg.code}" arguments="${msg.attributes}" htmlEscape="true" /></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</c:if>