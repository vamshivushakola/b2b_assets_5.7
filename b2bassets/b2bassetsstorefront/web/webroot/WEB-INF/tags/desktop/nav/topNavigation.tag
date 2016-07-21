<%-- <%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>


	<cms:pageSlot position="NavigationBar" var="component">
		<cms:component component="${component}"/>
	</cms:pageSlot> --%>

<!-- added by namrata -->
<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<div class="header-menu">
	<div class="container">
		<div class="nav-container">
			<div id="custommenu">
				<nav role="navigation" class="navbar navbar-default yamm">
					<div class="navbar-header">
						<span class="navbar-brand hidden-md hidden-lg">Menu</span>
						<button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
							<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
						</button>
					</div>
					<div id="navbarCollapse" class="collapse navbar-collapse">
						<div class="container-shadow"></div>
						<cms:pageSlot position="NavigationBar" var="component" element="ul" class="nav navbar-nav">
							<cms:component component="${component}" />
						</cms:pageSlot>
					</div>
				</nav>
			</div>
			<span class="hidden-lg hidden-md"> 
				<cms:pageSlot position="MiniCart" var="cart" limit="1">
					<cms:component component="${cart}" />
				</cms:pageSlot>
			</span>
		</div>
	</div>
	<div class="container-shadow"></div>
</div>

