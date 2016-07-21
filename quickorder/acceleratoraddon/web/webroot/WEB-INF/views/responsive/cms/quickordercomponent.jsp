<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2><spring:theme code="quickorder.title"/></h2>

<form class="js-quickorder-form" action="${formUrl}" method="post">
    <table class="table quickorder ${className}">
        <thead>
            <tr>
                <th class="quantity"><spring:theme code="quickorder.quantity"/></th>
                <th class="code js-append"><spring:theme code="quickorder.code"/></th>
        		<th class="name js-append"><spring:theme code="quickorder.name"/></th>
                <th class="remove"></th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td colspan="3" class="errorMessage" id="errorMessage"></td>
            </tr>   
            <c:forEach var="i" begin="1" end="${nbRows}">            
            <tr>
                <td class="quantity"><input type="number" name="quantity[]" min="1"/></td>
                <td class="code"><input type="text" name="value[]" class="js-quickorder-autocomplete" data-options='{"autocompleteUrl": "${autocompleteUrl}", "autocompleteSolrUrl":"${autocompleteSolrUrl}", "useSolr":"${useSolr}", "minChars" : "${nbChars}", "size" : "${nbResults}", "delay" : "${delaySearch}"}' autocomplete="off"/></td>
                <td class="name"></td>
                   <%--  <td class="name"><input type=hidden name="name" id="name" value="' + ${el.name} + '" /></td> --%>
    			<%-- <td class="name"><input type="text" id="name" value="${el.name}"/>${el.name}</td> --%>  
                <td class="delete">
                    <a href="javascript:;" class="glyphicon glyphicon-remove-circle js-quickorder-delete"></a>
                </td>
            </tr>
            </c:forEach>                    
        </tbody>
    </table>

    <c:if test="${!isCartEmpty}">
        <p class="alert-cart-empty"><spring:theme code="quickorder.message.cart.not.empty"/></p>
    </c:if>

    <button type="submit" disabled="true" class="add_to_cart_button positive large">
        <spring:theme code="quickorder.basket.add" />
    </button>
</form>
