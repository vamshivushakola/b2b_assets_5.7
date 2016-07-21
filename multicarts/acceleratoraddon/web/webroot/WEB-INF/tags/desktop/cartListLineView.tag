<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="cart" required="true" type="com.generic.multicarts.data.MultiCartsData" %>
<%@ attribute name="selected" required="true" type="java.lang.String" %>
<%@ attribute name="history" required="false" type="java.lang.Boolean"  %>
<%@ attribute name="notification" required="false" type="java.lang.Boolean"  %>


<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="multicarts" tagdir="/WEB-INF/tags/addons/multicarts/desktop" %>

<c:url value="/multicarts/history/" var="cartHistory" />
<c:url value="/multicarts/follow/" var="followURL" />
<c:url value="/multicarts/unfollow/" var="unfollowURL" />

<tr>
    <td class="action">
        <c:choose>
            <c:when test="${cart.code eq currentCart.code}">
                <input class="multicarts-select-cart no-transform-radio" data-code="${cart.code}" type="radio" name="selectCart" checked="checked" />
            </c:when>
            <c:otherwise>
                <input class="multicarts-select-cart no-transform-radio" data-code="${cart.code}"type="radio" name="selectCart" />
            </c:otherwise>
        </c:choose>
    </td>
    <td class="action">
        <c:if test="${notification}">
            <c:choose>
                <c:when test="${cart.follower ne null}">
                    <a href="${unfollowURL}${cart.code}" class="unsubscribe"></a>
                </c:when>
                <c:otherwise>
                    <a href="${followURL}${cart.code}" class="subscribe"></a>
                </c:otherwise>
            </c:choose>
        </c:if>
    </td>
    <td class="addon-multicarts-cartname">
        <c:if test="${notification}">
            <c:if test="${cart.hasNotification}">
                <span class="notification" ></span>
            </c:if>
        </c:if>
        <p>${cart.name}</p>
        <p><spring:theme code="text.multicarts.list.code" />=${cart.code}</p>
    </td>
    <td class="select-action">
        <c:forEach items="${cart.shareList}" var="shareUnit">
            <span class="multicarts-tag">${shareUnit.b2bunitName}</span>
        </c:forEach>
    </td>
    <td class="text-action">
        <c:if test="${history}">
            <a href="${cartHistory}${cart.code}"><spring:theme code="text.multicarts.list.history" /></a>
        </c:if>
    </td>
    <td></td>
</tr>
