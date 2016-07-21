<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<h2>
	<spring:theme code="csvorder.title" />
</h2>

<form action="${uploadUrl}" method="post" enctype="multipart/form-data" class="quickorder-csv-form">

	<input type="file" name="csvFile" class="filestyle test" data-input="false" />
	<button class="positive" type="submit">
		<spring:theme code="text.account.quickOrder.addtoCart"
					  text="Add to Cart" />
	</button>
	<a href="${CsvUrl}" class="template"> <spring:theme
			code="quickorder.page.file.template.load" />
	</a>
</form>