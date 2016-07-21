<%--<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

 <c:url value="${product.url}/reviewhtml/3" var="getPageOfReviewsUrl"/>
<c:url value="${product.url}/reviewhtml/all" var="getAllReviewsUrl"/>

<div id="reviews" class="reviews" data-reviews="${getPageOfReviewsUrl}"  data-allreviews="${getAllReviewsUrl}" ></div>
<div id="write_reviews" class="reviews" style="display:none">
	
	<div class="write_review clearfix">
		<div class="headline"><spring:theme code="review.write.title"/></div>
		<div class="required right"><spring:theme code="review.required"/></div>
		<div class="description"><spring:theme code="review.write.description"/></div>
		
		<c:url value="${product.url}/review" var="productReviewActionUrl"/>
		<form:form method="post" action="${productReviewActionUrl}" commandName="reviewForm">
			<div class="write_review_container">
				<formElement:formInputBox idKey="review.headline" labelKey="review.headline" path="headline" inputCSS="text" mandatory="true"/>
				<formElement:formTextArea idKey="review.comment" labelKey="review.comment" path="comment" areaCSS="textarea" mandatory="true"/>
				
				<spring:bind path="rating">
					<div class="control-group<c:if test="${not empty status.errorMessages}"> error</c:if>">
						<label class="control-label"><spring:theme code="review.rating"/>:</label>
						<div id="stars-wrapper" class="controls clearfix">
							<c:forEach begin="1" end="5" varStatus="status">
								<label><img class="no_star" src="${commonResourcePath}/images/jquery.ui.stars.custom.gif" alt="<spring:theme code="review.rating.alt"/>"/><form:radiobutton path="rating" value="${status.index}"/>${status.index}/${status.end}</label></br>
							</c:forEach>
						</div>
						<div class="help-inline"><form:errors path="rating" /></div>
					</div>
				</spring:bind>
				
				<formElement:formInputBox idKey="alias" labelKey="review.alias" path="alias" inputCSS="" mandatory="false"/>
			</div>
			
				<button class="positive" type="submit" value="<spring:theme code="review.submit"/>"><spring:theme code="review.submit"/></button>
			
		</form:form>
	</div>
	<div class="actionBar bottom clearfix">
			<a href="#" id="read_reviews_action"><spring:theme code="review.back"/></a>
	</div>
</div> --%>

<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formUtil" tagdir="/WEB-INF/tags/addons/b2bacceleratoraddon/desktop/formElement"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:url value="${product.url}/reviewhtml/3" var="getPageOfReviewsUrl" />
<c:url value="${product.url}/reviewhtml/all" var="getAllReviewsUrl" />
<script type="text/javascript">
	// set vars
	/*<![CDATA[*/
	var showReviewForm = <c:out value="${showReviewForm}" default="false"/>;
	var getPageOfReviewsUrl = '${getPageOfReviewsUrl}';
	var getAllReviewsUrl = '${getAllReviewsUrl}';
	/*]]>*/
</script>

<div id="reviews"></div>
<div id="write_reviews" class="clear_fix" style="display: none">
	<ul class="review_actions clear_fix">
		<li><a href="#" id="read_reviews_action"><spring:theme code="review.back" /></a></li>
	</ul>
	<h4 class="subtitle">
		<spring:theme code="review.write.title" />
	</h4>
	<p>
		<spring:theme code="review.write.description" />
	</p>
	<p>
		<spring:theme code="review.required" />
	</p>
	<div class="write_review">
		<c:url value="/${product.name}/p/${product.code}/review" var="productReviewActionUrl" />
		<form:form method="post" action="${productReviewActionUrl}" commandName="reviewForm">
			<ul class="form-list">
				<li><formUtil:formInputBox idKey="review.headline" labelKey="review.headline" path="headline" inputCSS="text" mandatory="true" /></li>
				<li><formUtil:formTextArea idKey="review.comment" labelKey="review.comment" path="comment" areaCSS="textarea" mandatory="true" /></li>
				<li><label for="alias" class="label-input"><spring:theme code="review.alias" /></label>
					<div class="input-box">
						<form:input path="alias" class="text" />
					</div></li>
			</ul>
			<spring:bind path="rating">
				<c:if test="${not empty status.errorMessages}">
					<span class="form_field_error">
				</c:if>
				<div class="form_field-label">
					<label><spring:theme code="review.rating" />:</label>
				</div>
				<div id="stars-wrapper" class="clear_fix">
					<c:forEach begin="1" end="5" varStatus="status">
						<label><img class="no_star" src="${commonResourcePath}/images/jquery.ui.stars.custom.gif" alt="<spring:theme code="review.rating.alt"/>" /> <form:radiobutton class="no-transform-radio" path="rating" value="${status.index}" />${status.index}/${status.end}</label>
					</c:forEach>
				</div>
				<p>
					<form:errors path="rating" />
				</p>
				<c:if test="${not empty status.errorMessages}">
					</span>
				</c:if>
			</spring:bind>
			<button class="form" type="submit" value="<spring:theme code="review.submit"/>">
				<spring:theme code="review.submit" />
			</button>
		</form:form>
	</div>
</div>

