<%-- <%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>


		<c:forEach items="${navigationNodes}" var="node">
				<c:if test="${node.visible}">
					<div class="links">
						<div class="title">${node.title}</div>
						<c:forEach items="${node.links}" step="${component.wrapAfter}" varStatus="i">
							<ul>
								<c:forEach items="${node.links}" var="childlink" begin="${i.index}" end="${i.index + component.wrapAfter - 1}">
									<cms:component component="${childlink}" evaluateRestriction="true" element="li" />
								</c:forEach>
							</ul>
						</c:forEach>
					</div>
				</c:if>
		</c:forEach>


<div class="copyright">${notice}</div>
 --%>
 
<!--  added by namrata -->

<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<div id="footer">
	<div class="footer-top">
		<div class="container">
			<div class="row">
				<c:forEach items="${navigationNodes}" var="node">
					<c:if test="${node.visible}">
						<div class="col-sm-4">
							<h3 class="title">${node.title}</h3>
							<c:forEach items="${node.links}" step="${component.wrapAfter}" varStatus="i">
								<ul class="links">
									<c:forEach items="${node.links}" var="childlink" begin="${i.index}" end="${i.index + component.wrapAfter - 1}">
										<cms:component component="${childlink}" evaluateRestriction="true" element="li" class="Fc ${i.count < 2 ? 'left_col' : 'right_col'}" />
									</c:forEach>
								</ul>
							</c:forEach>
						</div>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</div>
	<div class="footer-bottom">
		<div class="container">
			<div class="row">
				<div class="col-sm-7 social-links">
					<div class="social-links">
						<a title="Facebook" target="_blank" href="https://www.facebook.com"><span class="icon icon-facebook">&nbsp;</span></a> 
						<a title="Twitter" target="_blank" href="https://twitter.com"><span class="icon icon-twitter">&nbsp;</span></a> 
						<a title="RSS" target="_blank" href="www.rssfeeds.com/"><span class="icon icon-rss">&nbsp;</span></a> 
						<a title="Delicious" target="_blank" href="https://delicious.com/"><span class="icon icon-delicious">&nbsp;</span></a> 
						<a title="LinkedIn" target="_blank" href="https://in.linkedin.com/"><span class="icon icon-linkedin">&nbsp;</span></a> 
						<a title="Blog" target="_blank" href="http://blog.com"><span class="icon icon-blog">&nbsp;</span></a> 
						<a title="Skype" target="_blank" href="https://skype.com"><span class="icon icon-skype">&nbsp;</span></a> 
						<a title="Email" target="_blank" href="https://mail.google.com/"><span class="icon icon-email">&nbsp;</span></a>
					</div>
				</div>
				<div class="col-sm-5 copyright">
					<p>${notice}</p>
				</div>
			</div>
		</div>
	</div>
</div>