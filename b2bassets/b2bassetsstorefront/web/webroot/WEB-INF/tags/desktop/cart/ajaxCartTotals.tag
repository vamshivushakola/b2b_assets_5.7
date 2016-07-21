<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>

<script id="cartTotalsTemplate" type="text/x-jquery-tmpl">
		<table id="shopping-cart-totals-table" class="data-table">
		<tfoot>
			<tr class="grand-total last">
				<td class="a-right" colspan="1"><strong><spring:theme code="basket.page.totals.total"/></strong></td>
				<td class="a-right total-price last"><strong><span class="price"> 
				{{if net}}
					{{= totalPriceWithTax.formattedValue}}
				{{else}}
					{{= totalPrice.formattedValue}}
				{{/if}}
				</span></strong></td>
			</tr>
		</tfoot>
		<tbody>
			<tr>
				<td class="a-right" colspan="1"><spring:theme code="basket.page.totals.subtotal"/></td>
				<td class="a-right last"><span class="price">{{= subTotal.formattedValue}}</span></td>
			</tr>
			<tr>
				<td class="a-right" colspan="1"><spring:theme code="basket.page.totals.savings"/></td>
				<td class="a-right last"><span class="price">{{= totalDiscounts.formattedValue}}</span></td>
			</tr>
			{{if deliveryCost}}
				<tr>
					<td class="a-right" colspan="1"><spring:theme code="basket.page.totals.delivery"/></td>
					<td class="a-right last">
						<span class="price">
							{{if deliveryCost.value > 0}}
								{{= deliveryCost.formattedValue}}
							{{else}}
								<spring:theme code="basket.page.free"/>
							{{/if}}
						</span>
					</td>
				</tr>
			{{/if}}
			{{if net && totalTax.value > 0 }}
				<tr>
					<td class="a-right" colspan="1"><spring:theme code="basket.page.totals.netTax"/></td>
					<td class="a-right last"><span class="price">{{= totalTax.formattedValue}}</span></td>
				</tr>
			{{/if}}
		</tbody>
	</table>
	{{if !net}}
		<p><spring:theme code="basket.page.totals.grossTax" arguments="{{= totalTax.formattedValue}}" argumentSeparator="!!!!" /></p>
	{{/if}}
	{{if net && totalTax.value <= 0}}
		<p><spring:theme code="basket.page.totals.noNetTax" /></p>
	{{/if}}
	

</script>

<div class="totals" id="cart_totals_div">
	
	<c:if test="${cartData.net && cartData.totalTax.value <= 0}">
		<p>
			<ycommerce:testId code="checkout_tax_label">
				<spring:theme code="basket.page.totals.noNetTax" />
			</ycommerce:testId>
		</p>
	</c:if>
</div>
