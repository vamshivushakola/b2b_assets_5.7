<%-- <%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<div class="span-20 last">
	<div class="accountContentPane clearfix">
		<div class="headline">
			<spring:theme code="text.account.addressBook" text="Address Book"/>
		</div>
		<div class="description">
			<spring:theme code="text.account.addressBook.manageYourAddresses" text="Manage your address book"/>
		</div>
		<c:choose>
			<c:when test="${not empty addressData}">
				<c:forEach items="${addressData}" var="address">
					<div class="addressItem">
						<ycommerce:testId code="addressBook_address_label">
							<ul>
								<li>${fn:escapeXml(address.title)}&nbsp;${fn:escapeXml(address.firstName)}&nbsp;${fn:escapeXml(address.lastName)}</li>
								<li>${fn:escapeXml(address.line1)}</li>
								<li>${fn:escapeXml(address.line2)}</li>
								<li>${fn:escapeXml(address.town)}</li>
								<li>${fn:escapeXml(address.region.name)}</li>
								<li>${fn:escapeXml(address.postalCode)}</li>
								<li>${fn:escapeXml(address.country.name)}</li>
                                <li>${fn:escapeXml(address.phone)}</li>
							</ul>
						</ycommerce:testId>
						<div class="buttons">
							<ycommerce:testId code="addressBook_addressOptions_label">
								<c:if test="${not address.defaultAddress}">
									<ycommerce:testId code="addressBook_isDefault_button"><a class="button" href="set-default-address/${address.id}">
										<spring:theme code="text.setDefault" text="Set as default"/>
									</a></ycommerce:testId>
								</c:if>
								<c:if test="${address.defaultAddress}">
									<ycommerce:testId code="addressBook_isDefault_label">
										<span class="is-default-address"><spring:theme code="text.default" text="Default"/></span>
									</ycommerce:testId>
								</c:if>
								<ycommerce:testId code="addressBook_editAddress_button">
									<a class="button" href="edit-address/${address.id}">
										<spring:theme code="text.edit" text="Edit"/>
									</a>
								</ycommerce:testId>
								<ycommerce:testId code="addressBook_removeAddress_button">
									<a class="button removeAddressButton" data-address-id="${address.id}"><spring:theme code="text.remove" text="Remove"/></a>
								</ycommerce:testId>
							</ycommerce:testId>
						</div>
					</div>
					<div style="display:none">
						<div id="popup_confirm_address_removal_${address.id}">
							<div class="addressItem">
								<ul>
									<li>${fn:escapeXml(address.title)}&nbsp;${fn:escapeXml(address.firstName)}&nbsp;${fn:escapeXml(address.lastName)}</li>
									<li>${fn:escapeXml(address.line1)}</li>
									<li>${fn:escapeXml(address.line2)}</li>
									<li>${fn:escapeXml(address.town)}</li>
									<li>${fn:escapeXml(address.region.name)}</li>
									<li>${fn:escapeXml(address.postalCode)}</li>
									<li>${fn:escapeXml(address.country.name)}</li>
                                    <li>${fn:escapeXml(address.phone)}</li>
								</ul>
								<spring:theme code="text.adress.remove.confirmation" text="Are you sure you would like to delete this address?"/>
								<div class="buttons">
									<a class="button removeAddressButton" data-address-id="${address.id}" href="remove-address/${address.id}">
										<spring:theme code="text.yes" text="Yes"/>
									</a>
									<a class="button closeColorBox" data-address-id="${address.id}">
										<spring:theme code="text.no" text="No"/>
									</a>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<p class="emptyMessage">
					<spring:theme code="text.account.addressBook.noSavedAddresses"/>
				</p>
			</c:otherwise>
		</c:choose>
		<ycommerce:testId code="addressBook_addNewAddress_button">
			<a href="add-address" class="button positive">
				<spring:theme code="text.account.addressBook.addAddress" text="Add new address"/>
			</a>
		</ycommerce:testId>
	</div>
</div> --%>


<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="formUtil" tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>

<h2 class="subtitle">
						<spring:theme code="text.account.addressBook" text="Address Book" />
					</h2>
					<a href="add-address">
						<button class="positive right" type="submit">
							<ycommerce:testId code="addressBook_addNewAddress_button">
								<spring:theme code="text.account.addressBook.addAddress" text="Add new address" />
							</ycommerce:testId>
						</button>
					</a>
					<p>
						<spring:theme code="text.account.addressBook.manageYourAddresses" text="Manage your address book" />
					</p>
					<c:choose>
						<c:when test="${not empty addressData}">
							<table id="address_book" class="data-table">
								<thead>
									<tr class="hidden-xs">
										<th><spring:theme code="text.address" text="Address" /></th>
										<th><spring:theme code="text.updates" text="Updates" /></th>
									</tr>
									<tr class="hidden-lg hidden-md hidden-sm">
										<th><spring:theme code="text.address" text="Address" /></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${addressData}" var="address" varStatus="loop">
										<tr class="${loop.last ? 'last' : ''}">
											<td><ycommerce:testId code="addressBook_address_label">
											<span class="hidden-lg hidden-md hidden-sm mobile-label"><spring:theme code="text.address" text="Address" /></span>
													<ul>
														<li>${fn:escapeXml(address.title)}&nbsp;${fn:escapeXml(address.firstName)}&nbsp;${fn:escapeXml(address.lastName)}</li>
														<li>${fn:escapeXml(address.line1)}</li>
														<li>${fn:escapeXml(address.line2)}</li>
														<li>${fn:escapeXml(address.town)}</li>
														<li>${fn:escapeXml(address.postalCode)}</li>
														<li>${fn:escapeXml(address.country.name)}</li>
													</ul>
												</ycommerce:testId></td>
											<td class="last"><ycommerce:testId code="addressBook_addressOptions_label">
													<ul class="updates clear_fix">
														<li><ycommerce:testId code="addressBook_editAddress_button">
																<a title="<spring:theme code="text.edit" text="Edit"/>" class="edit-address" href="edit-address/${address.id}"></a>
															</ycommerce:testId></li>
														<li><ycommerce:testId code="addressBook_removeAddress_button">
																<a title="<spring:theme code="text.remove" text="Remove"/>" class="remove-address" href="remove-address/${address.id}"></a>
															</ycommerce:testId></li>
														<c:if test="${not address.defaultAddress}">
															<li><ycommerce:testId code="addressBook_isDefault_button">
																	<a class="default-address" title="<spring:theme code="text.setDefault" text="Set as default" />" href="set-default-address/${address.id}"></a>
																</ycommerce:testId></li>
														</c:if>
														<c:if test="${address.defaultAddress}">
															<li><ycommerce:testId code="addressBook_isDefault_label">
																	<span class="default-address"></span>
																</ycommerce:testId></li>
														</c:if>
													</ul>
												</ycommerce:testId></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:when>
						<c:otherwise>
							<p class="emptyMessage">
								<spring:theme code="text.account.addressBook.noSavedAddresses" />
							</p>
						</c:otherwise>
					</c:choose>