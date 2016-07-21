<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Oswald:400,300,700' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Gudea:400,700' rel='stylesheet' type='text/css'>

<!-- colorbox CSS -->
<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/jquery.colorbox-1.3.16.css"/>

<!-- BeautyTips CSS -->
<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/jquery.bt-0.9.5.css"/>

<!-- blueprintcss -->
<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/blueprint/screen.css"/>

<!-- jQuery UI CSS -->
<link rel="stylesheet" type="text/css" media="screen" href="${commonResourcePath}/css/jquery.ui.stars-3.0.1.custom.css"/>
<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/jquery.ui.autocomplete-1.8.18.css"/>

<!--  AddOn Common CSS files -->
<c:forEach items="${addOnCommonCssPaths}" var="addOnCommonCss">
	<link rel="stylesheet" type="text/css" media="all" href="${addOnCommonCss}"/>
</c:forEach>


<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/helper.css"/>
<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/main.css"/>
<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/buttons.css"/>
<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/forms.css"/>
<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/header.css"/>
<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/miniCart.css"/>
<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/siteSearch.css"/>
<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/navigation.css"/>
<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/breadcrumb.css"/>


<!--  Category page -->
<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/facetNav.css"/>

<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/paginationBar.css"/>


<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/productGrid.css"/>
<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/productList.css"/>
<!--  Product page -->
<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/productDetails.css"/>

<!--  Checkout pages -->
<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/checkoutContentPanel.css"/>
<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/checkoutOrderDetails.css"/>
<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/checkoutProgress.css"/>

<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/order.css"/>
<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/orderTotals.css"/>


<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/footer.css"/>


<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/colorBox.css"/>

<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/searchPOS.css"/>


<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/userRegister.css"/>
<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/userLogin.css"/>
<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/userGuest.css"/>


<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/account.css"/>


<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/cartItems.css"/>


<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/landingLayout2Page.css"/>
<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/storeDetail.css"/>
<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/storeFinder.css"/>


<!-- Theme CSS files -->
<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/css/changes.css"/>

<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/multi-d.css"/>
<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/futureStock.css"/>

<!--  AddOn Theme CSS files -->
<c:forEach items="${addOnThemeCssPaths}" var="addOnThemeCss">
	<link rel="stylesheet" type="text/css" media="all" href="${addOnThemeCss}"/>
</c:forEach>



<!-- added by namrata -->

<%-- treeview CSS --%>
<link rel="stylesheet" type="text/css" media="screen" href="${commonResourcePath}/css/jquery.treeview.css" />

<%-- scrollplus CSS --%>
<link rel="stylesheet" type="text/css" media="screen" href="${commonResourcePath}/css/jquery.scrollplus.css" />

<%-- bootstrap CSS --%>
<link rel="stylesheet" type="text/css" media="screen" href="${commonResourcePath}/css/bootstrap.min.css" />

<%-- bootstraptheme CSS --%>
<link rel="stylesheet" type="text/css" media="screen" href="${commonResourcePath}/css/bootstrap-theme.min.css" />

<%-- font-awesome CSS --%>
<link rel="stylesheet" type="text/css" media="screen" href="${commonResourcePath}/css/font-awesome.min.css" />

<%-- Chosen CSS --%>
<link rel="stylesheet" type="text/css" media="screen" href="${commonResourcePath}/css/chosen.min.css" />

<%-- Yamm CSS --%>
<link rel="stylesheet" type="text/css" media="screen" href="${commonResourcePath}/css/yamm.css" />

<%-- Owl carousel CSS --%>
<link rel="stylesheet" type="text/css" media="screen" href="${commonResourcePath}/css/owl.carousel.css" />

<%-- Owl carousel Theme CSS --%>
<link rel="stylesheet" type="text/css" media="screen" href="${commonResourcePath}/css/owl.theme.css" />

<%-- Scroll To Top Theme CSS --%>
<link rel="stylesheet" type="text/css" media="screen" href="${commonResourcePath}/css/scrollToTop.css" />

<%-- Pretty Checkable Theme CSS --%>
<link rel="stylesheet" type="text/css" media="screen" href="${commonResourcePath}/css/prettyCheckable.css" />

<%-- Elastislide Theme CSS --%>
<link rel="stylesheet" type="text/css" media="screen" href="${commonResourcePath}/css/elastislide.css" />

<%-- Responsive Tabs Theme CSS --%>
<link rel="stylesheet" type="text/css" media="screen" href="${commonResourcePath}/css/easy-responsive-tabs.css" />


<%-- theme specific css --%>
<link rel="stylesheet" type="text/css" media="screen" href="${themeResourcePath}/css/theme-green.css" />

<%-- B2B css --%>
<link rel="stylesheet" type="text/css" media="screen" href="${themeResourcePath}/css/theme-green-b2b.css" />

<%-- theme responsive css --%>
<link rel="stylesheet" type="text/css" media="screen" href="${themeResourcePath}/css/responsive.css" />

<%-- Tooltip css --%>
<link rel="stylesheet" type="text/css" media="screen" href="${themeResourcePath}/css/hint.css" />



