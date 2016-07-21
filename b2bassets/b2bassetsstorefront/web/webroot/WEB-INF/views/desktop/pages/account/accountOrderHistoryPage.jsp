<%-- <%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="action" tagdir="/WEB-INF/tags/desktop/action" %>

<div class="headline">
	<spring:theme code="text.account.orderHistory" text="Order History"/>
</div>
<c:if test="${not empty searchPageData.results}">
	<div class="description">
		<spring:theme code="text.account.orderHistory.viewOrders" text="View your orders"/>
	</div>
	<nav:pagination top="true" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="/my-account/orders?sort=${searchPageData.pagination.sort}" msgKey="text.account.orderHistory.page" numberPagesShown="${numberPagesShown}"/>
	<div class="orderList">
		<table class="orderListTable">
			<thead>
			<tr>
				<th id="header1">
					<spring:theme code="text.account.orderHistory.orderNumber" text="Order Number"/>
				</th>
				<th id="header2">
					<spring:theme code="text.account.orderHistory.erpOrderNumber" text="ERP Number" />
				</th>
				<th id="header3">
					<spring:theme code="text.account.orderHistory.origin" text="Origin" />
				</th>
				<th id="header4">
					<spring:theme code="text.account.orderHistory.orderStatus" text="Order Status"/>
				</th>
				<th id="header5">
					<spring:theme code="text.account.orderHistory.purchaseOrderNumber" text="P.O.No" />
				</th>
				<th id="header6">
					<spring:theme code="text.account.orderHistory.datePlaced" text="Date Placed"/>
				</th>
				<th id="header7">
					<spring:theme code="text.account.orderHistory.author" text="Author" />
				</th>
				<th id="header8">
					<spring:theme code="text.account.orderHistory.actions" text="Actions"/>
				</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${searchPageData.results}" var="order">
				<c:url value="/my-account/order/${order.code}" var="myAccountOrderDetailsUrl" />
				<tr>
					<td headers="header1">
						<ycommerce:testId code="orderHistory_orderNumber_link">
							<a href="${myAccountOrderDetailsUrl}">${order.code}</a>
						</ycommerce:testId>
					</td>
					<td headers="header2">						
						<ycommerce:testId code="orderHistory_ERPOrderNumber_link">
							<span class="hidden-lg hidden-md hidden-sm mobile-label">
								<spring:theme code="text.account.orderHistory.erpOrderNumber" text="ERP Order Number" /> : </span>
						${order.ERPOrderNumber}
						</ycommerce:testId>
					</td>
					<td headers="header3">
						<ycommerce:testId code="orderHistory_origin_link">
							<span class="hidden-lg hidden-md hidden-sm mobile-label">
								<spring:theme code="text.account.orderHistory.origin" text="Origin" /> : </span>
						${order.origin}
						</ycommerce:testId>
					</td>	
					<td headers="header4">
						<ycommerce:testId code="orderHistory_orderStatus_label">
							<p>
								<spring:theme code="text.account.order.status.display.${order.statusDisplay}"/>
							</p>
						</ycommerce:testId>
					</td>
					<td headers="header5">
						<ycommerce:testId code="orderHistory_purchaseOrderNumber_label">
							<span class="hidden-lg hidden-md hidden-sm mobile-label">
								<spring:theme code="text.account.orderHistory.purchaseOrderNumber" text="P.O.No" /> : </span>
							<span>${order.purchaseOrderNumber}</span>
						</ycommerce:testId>
					</td>
					<td headers="header6">
						<ycommerce:testId code="orderHistory_orderDate_label">
							<p>
								<fmt:formatDate value="${order.placed}" dateStyle="long" timeStyle="short" type="both"/>
							</p>
						</ycommerce:testId>
					</td>
					<td headers="header7">
						<ycommerce:testId code="orderHistory_author_link">
							<span class="hidden-lg hidden-md hidden-sm mobile-label">
								<spring:theme code="text.account.orderHistory.author" text="Author" /> : </span>
								${order.author}
						</ycommerce:testId>
					</td>
					<td headers="header8">
						<c:set var="orderCode" value="${order.code}" scope="request"/>
						<action:actions element="div" parentComponent="${component}"/>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<nav:pagination top="false" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="/my-account/orders?sort=${searchPageData.pagination.sort}" msgKey="text.account.orderHistory.page" numberPagesShown="${numberPagesShown}"/>
</c:if>
<c:if test="${empty searchPageData.results}">
	<p><spring:theme code="text.account.orderHistory.noOrders" text="You have no orders"/></p>
</c:if>
 --%>

<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>

<h2 class="subtitle">
						<spring:theme code="text.account.orderHistory" text="Order History" />
					</h2>
					<c:if test="${not empty searchPageData.results}">
						<p>
							<spring:theme code="text.account.orderHistory.viewOrders" text="View your orders" />
						</p>

						<nav:pagination top="true" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="/my-account/orders?sort=${searchPageData.pagination.sort}" msgKey="text.account.orderHistory.page" numberPagesShown="${numberPagesShown}" />
						<table id="order_history" class="order-history data-table">
							<thead>
								<tr class="hidden-xs">
									<th>
										<spring:theme code="text.account.orderHistory.orderNumber" text="Order Number" />
									</th>
									<th>
										<spring:theme code="text.account.orderHistory.erpOrderNumber" text="ERP Number" />
									</th>
									<th>
										<spring:theme code="text.account.orderHistory.origin" text="Origin" />
									</th>
									<th>
										<spring:theme code="text.account.orderHistory.orderStatus" text="Order Status" />
									</th>
									<th>
										<spring:theme code="text.account.orderHistory.purchaseOrderNumber" text="P.O.No" />
									</th>
									<th>
										<spring:theme code="text.account.orderHistory.datePlaced" text="Date Placed" />
									</th>
									<th>
										<spring:theme code="text.account.orderHistory.author" text="Author" />
									</th>
									<th>
										<spring:theme code="text.account.orderHistory.actions" text="Actions" />
									</th>
								</tr>
								<tr class="hidden-lg hidden-md hidden-sm">
									<th>
										<spring:theme code="text.account.orderHistory.orderNumber" text="Order Number" />
									</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${searchPageData.results}" var="order" varStatus="loop">

									<c:url value="/my-account/order/${order.code}" var="myAccountOrderDetailsUrl" />
									<tr class="${loop.last ? 'last' : ''}">
										<td>
											<ycommerce:testId code="orderHistory_orderNumber_link">
												<span class="hidden-lg hidden-md hidden-sm mobile-label">
													<spring:theme code="text.account.orderHistory.orderNumber" text="Order Number" /> : </span>
												<a href="${myAccountOrderDetailsUrl}">${order.code}</a>
											</ycommerce:testId>
										</td>
										<td>
											<ycommerce:testId code="orderHistory_ERPOrderNumber_link">
												<span class="hidden-lg hidden-md hidden-sm mobile-label">
													<spring:theme code="text.account.orderHistory.erpOrderNumber" text="ERP Order Number" /> : </span>
											${order.ERPOrderNumber}
											</ycommerce:testId>
										</td>
										<td>
											<ycommerce:testId code="orderHistory_origin_link">
												<span class="hidden-lg hidden-md hidden-sm mobile-label">
													<spring:theme code="text.account.orderHistory.origin" text="Origin" /> : </span>
											${order.origin}
											</ycommerce:testId>
										</td>											
										<td>
											<ycommerce:testId code="orderHistory_orderStatus_label">
												<span class="hidden-lg hidden-md hidden-sm mobile-label">
													<spring:theme code="text.account.orderHistory.orderStatus" text="Order Status" /> : </span>
												<span>
													<spring:theme code="text.account.order.status.display.${order.statusDisplay}" />
												</span>
											</ycommerce:testId>
										</td>
										<td>
											<ycommerce:testId code="orderHistory_purchaseOrderNumber_label">
												<span class="hidden-lg hidden-md hidden-sm mobile-label">
													<spring:theme code="text.account.orderHistory.purchaseOrderNumber" text="P.O.No" /> : </span>
												<span>${order.purchaseOrderNumber}</span>
											</ycommerce:testId>
										</td>
										<td>
											<ycommerce:testId code="orderHistory_orderDate_label">
												<span class="hidden-lg hidden-md hidden-sm mobile-label">
													<spring:theme code="text.account.orderHistory.datePlaced" text="Date Placed" /> : </span>
												<span>
													<fmt:formatDate value="${order.placed}" dateStyle="long" timeStyle="short" type="both" />
												</span>
											</ycommerce:testId>
										</td>
										<td>
											<ycommerce:testId code="orderHistory_author_link">
												<span class="hidden-lg hidden-md hidden-sm mobile-label">
													<spring:theme code="text.account.orderHistory.author" text="Author" /> : </span>
											${order.author}
											</ycommerce:testId>
										</td>
										<td class="last">
											<ycommerce:testId code="orderHistory_Actions_links">
												<ul class="updates">
													<li>
														<a title="<spring:theme code="text.view" text="View" />" class="view-order" href="${myAccountOrderDetailsUrl}"></a>
														</li>
													</ul>
												</ycommerce:testId>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>

							<nav:pagination top="false" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="/my-account/orders?sort=${searchPageData.pagination.sort}" msgKey="text.account.orderHistory.page" numberPagesShown="${numberPagesShown}" />

						</c:if>
						<c:if test="${empty searchPageData.results}">
							<p>
								<spring:theme code="text.account.orderHistory.noOrders" text="You have no orders" />
							</p>
						</c:if>