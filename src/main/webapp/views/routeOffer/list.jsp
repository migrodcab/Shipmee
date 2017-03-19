<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<jstl:forEach items="${routeOffers}" var="routeOfferRow">

		<h3 class="mb">
			<spring:message code="routeOffer.list.by" />
			<a href="user/user/view.do?userId=${routeOfferRow.user.id}">
				<jstl:out value="${routeOfferRow.user.name}" />
			</a>
			<spring:message code="routeOffer.list.for" />
			<a href="route/display.do?routeId=${routeOfferRow.route.id}">
				<jstl:out value="${routeOfferRow.route.origin}" />
				->
				<jstl:out value="${routeOfferRow.route.destination}" />
			</a>
		</h3>
				
			<jstl:if test="${routeOfferRow.acceptedByCarrier}">
					<p><b><spring:message code="routeOffer.accepted" /></b></p>
			</jstl:if>
			<jstl:if test="${routeOfferRow.rejectedByCarrier}">
					<p><b><spring:message code="routeOffer.rejected" /></b></p>
			</jstl:if>
			<jstl:if test="${!routeOfferRow.rejectedByCarrier && !routeOfferRow.acceptedByCarrier}">
					<p> <b><spring:message code="routeOffer.pending" /></b></p>
			</jstl:if>
			
			<p> <b><spring:message code="routeOffer.amount" />:</b> <jstl:out value="${routeOfferRow.amount}" /> </p>
			
			<p> <b><spring:message code="routeOffer.description" />:</b> <jstl:out value="${routeOfferRow.description}" /> </p>

			<div class="botones col-xs-10 col-sm-2 col-lg-3">
				<div class="col-xs-12"
					style="text-align: center; padding-top: 2%;">
					<jstl:if test="${!routeOfferRow.rejectedByCarrier && !routeOfferRow.acceptedByCarrier && currentUser.id != routeOfferRow.user.id}">
						<div class="col-xs-6 col-sm-12">
							<button type="button"
								class="btn btn-primary btn-shipmentOffer-actions"
								onclick="location.href = 'routeOffer/user/accept.do?routeOfferId=${routeOfferRow.id}';">
								<spring:message code="routeOffer.accept" />
							</button>
						</div>
						<div class="col-xs-6 col-sm-12" style="text-align: center;">
							<button type="button"
								class="btn btn-danger btn-shipmentOffer-actions"
								onclick="location.href = 'routeOffer/user/deny.do?routeOfferId=${routeOfferRow.id}';">
								<spring:message code="routeOffer.deny" />
							</button>
						</div>
					</jstl:if>

				</div>
			</div>
<hr>

</jstl:forEach>


<jstl:if test="${fn:length(routeOffers) == 0}">
	<center>
		<h2>
			<spring:message code="routeOffer.anything" />
		</h2>
	</center>
</jstl:if>

<div id="pagination" class="copyright">

	<script>
		$('#pagination').bootpag({
			total : <jstl:out value="${total_pages}"></jstl:out>,
			page : <jstl:out value="${p}"></jstl:out>,
			maxVisible : 5,
			leaps : true,
			firstLastUse : true,
			first : '<',
            last: '>',
			wrapClass : 'pagination',
			activeClass : 'active',
			disabledClass : 'disabled',
			nextClass : 'next',
			prevClass : 'prev',
			lastClass : 'last',
			firstClass : 'first'
		}).on('page', function(event, num) {
			window.location.href = "${urlPage}" + num + "";
			page = 1
		});
	</script>

</div>