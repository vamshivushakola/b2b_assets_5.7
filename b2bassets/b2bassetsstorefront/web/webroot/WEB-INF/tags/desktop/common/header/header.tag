<%-- <%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="hideHeaderLinks" required="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/desktop/common/header"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>

Test if the UiExperience is currently overriden and we should show the UiExperience prompt
<c:if test="${uiExperienceOverride and not sessionScope.hideUiExperienceLevelOverridePrompt}">
	<c:url value="/_s/ui-experience?level=" var="clearUiExperienceLevelOverrideUrl"/>
	<c:url value="/_s/ui-experience-level-prompt?hide=true" var="stayOnDesktopStoreUrl"/>
	<div class="backToMobileStore">
		<a href="${clearUiExperienceLevelOverrideUrl}"><span class="greyDot">&lt;</span><spring:theme code="text.swithToMobileStore" /></a>
		<span class="greyDot closeDot"><a href="${stayOnDesktopStoreUrl}">x</a></span>
	</div>
</c:if>



<div id="header" class="clearfix">
	<cms:pageSlot position="TopHeaderSlot" var="component">
		<cms:component component="${component}"/>
	</cms:pageSlot>
	
	<div class="headerContent ">
		<ul class="nav clearfix">
			<c:if test="${empty hideHeaderLinks}">
				<c:if test="${uiExperienceOverride}">
					<li class="backToMobileLink">
						<c:url value="/_s/ui-experience?level=" var="backToMobileStoreUrl"/>
						<a href="${backToMobileStoreUrl}"><spring:theme code="text.backToMobileStore"/></a>
					</li>
				</c:if>

				<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
					<c:set var="maxNumberChars" value="25"/>
					<c:if test="${fn:length(user.firstName) gt maxNumberChars}">
						<c:set target="${user}" property="firstName" value="${fn:substring(user.firstName, 0, maxNumberChars)}..."/>
					</c:if>
					<li class="logged_in"><ycommerce:testId code="header_LoggedUser"><spring:theme code="header.welcome" arguments="${user.firstName},${user.lastName}" htmlEscape="true"/></ycommerce:testId></li>
				</sec:authorize>
				<sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
					<li><ycommerce:testId code="header_Login_link"><a href="<c:url value="/login"/>"><spring:theme code="header.link.login"/></a></ycommerce:testId></li>
				</sec:authorize>
				<li><ycommerce:testId code="header_myAccount"><a href="<c:url value="/my-account"/>"><spring:theme code="header.link.account"/></a></ycommerce:testId></li>
			</c:if>

			<cms:pageSlot position="HeaderLinks" var="link">
				<cms:component component="${link}" element="li"/>
			</cms:pageSlot>
		
			<c:if test="${empty hideHeaderLinks}">
				<li><a href="<c:url value="/store-finder"/>"><spring:theme code="general.find.a.store" /></a></li>
				<sec:authorize ifNotGranted="ROLE_ANONYMOUS"><li><ycommerce:testId code="header_signOut"><a href="<c:url value='/logout'/>"><spring:theme code="header.link.logout"/></a></ycommerce:testId></li></sec:authorize>
			</c:if>

			<cms:pageSlot position="MiniCart" var="cart" limit="1">
				<cms:component component="${cart}" element="li" class="miniCart" />
			</cms:pageSlot>
		</ul>
	</div>

	<cms:pageSlot position="SearchBox" var="component" element="div" class="headerContent secondRow">
		<cms:component component="${component}" element="div" />
	</cms:pageSlot>


	<cms:pageSlot position="SiteLogo" var="logo" limit="1">
		<cms:component component="${logo}" class="siteLogo"  element="div"/>
	</cms:pageSlot>
</div>
 --%>
 
<!--added by namrata 5.5  -->
<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/desktop/common/header"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<c:url value="/cart" var="cartUrl" />
<c:url value="/cart/checkout" var="checkoutUrl" />
<c:url value="/store-finder" var="storeFinder" />

<div class="header-top">
	<div class="container">
		<div class="pull-left">
			<ul class="toplinks links">
				<li class="first">
					<ycommerce:testId code="header_myAccount">
						<a href="<c:url value='/my-account'/>"><i class="fa fa-user"></i><span class="hidden-xs"><spring:theme code="header.link.account" /></span></a>
					</ycommerce:testId></li>
				<sec:authorize ifAnyGranted="ROLE_B2BADMINGROUP">
					<li><ycommerce:testId code="header_myCompany">
							<a href="<c:url value='/my-company/organization-management'/>"><i class="fa fa-briefcase"></i><span class="hidden-xs"><spring:theme code="header.link.company" /></span></a>
						</ycommerce:testId></li>
				</sec:authorize>
				<%-- <ycommerce:testId code="header_myWishlists">
					<a href="<c:url value='/wishlist/list'/>"><i class="wishlist"></i><span class="hidden-xs"><spring:theme code="header.link.wishlist"/></span> </a>
				</ycommerce:testId> --%>
				<li><a href="${cartUrl}"><i class="fa fa-shopping-cart"></i><span class="hidden-xs"><spring:theme code="checkout.checkout" /></span></a></li>
				<li class="last"><a href="${storeFinder}"><i class="fa fa-map-marker"></i><span class="hidden-xs"><spring:theme code="general.find.a.store" /></span></a></li>
			</ul>
		</div>

		<div class="pull-right clear_fix">
			<div class="block block-language">
				<header:languageSelector languages="${languages}" currentLanguage="${currentLanguage}" />
			</div>

			<div class="block block-currency">
				<header:currencySelector currencies="${currencies}" currentCurrency="${currentCurrency}" />
			</div>

			<div class="pull-right">
				<span class="hidden-xs hidden-sm"> 
				<div class="right-components">
				<cms:pageSlot position="MiniCart" var="cart" limit="2">
						<cms:component component="${cart}" />
					</cms:pageSlot>
				</div>
				</span>
				
				<sec:authorize ifNotGranted="ROLE_CUSTOMERGROUP">
					<p class="login-link">
						<ycommerce:testId code="header_Login_link">
							<a href="<c:url value='/login'/>"><spring:theme code="header.link.login" /></a>
						</ycommerce:testId>
					</p>
				</sec:authorize>

				<sec:authorize ifAnyGranted="ROLE_CUSTOMERGROUP">
					<p class="welcome-msg logged_in hidden-xs hidden-sm">
						<ycommerce:testId code="header_LoggedUser">
							<spring:theme code="header.welcome" arguments="${user.firstName},${user.lastName}" htmlEscape="true" />
						</ycommerce:testId>
					</p>
				</sec:authorize>
				<sec:authorize ifAnyGranted="ROLE_CUSTOMERGROUP">
					<p class="login-link">
						<ycommerce:testId code="header_signOut">
							<a href="<c:url value='/logout'/>"><spring:theme code="header.link.logout" /></a>
						</ycommerce:testId>
					</p>
				</sec:authorize>
			</div>
		</div>
	</div>
</div>

<div class="header">
	<div class="container">
		<div class="clear_fix">
			<h1 class="logo">
				<cms:pageSlot position="SiteLogo" var="logo" limit="1">
					<cms:component component="${logo}" />
				</cms:pageSlot>
			</h1>
			
				<div class="pull-right advanced-search">
					<ycommerce:testId code="advanced_s">
						<a href="<c:url value='/advancedProductSearch'/>"><p class="advanced welcome-msg logged_in"><spring:theme code="search.advanced.homepage" /></p></a>
					</ycommerce:testId>
				</div>
			
			<div class="pull-right header-search">
				<cms:pageSlot position="SearchBox" var="component">
					<cms:component component="${component}" />
				</cms:pageSlot>
			</div>
		</div>
	</div>
</div>


