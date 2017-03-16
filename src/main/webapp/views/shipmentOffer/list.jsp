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

<jstl:forEach items="${shipmentOffers}" var="shipmentOfferRow">

		<h3 class="mb">
			<spring:message code="shipmentOffer.list.by" />
			<a href="user/user/view.do?userId=${shipmentOfferRow.user.id}">
				<jstl:out value="${shipmentOfferRow.user.name}" />
			</a>
			<spring:message code="shipmentOffer.list.for" />
			<a href="shipment/display.do?shipmentId=${shipmentOfferRow.shipment.id}">
				<jstl:out value="${shipmentOfferRow.shipment.itemName}" />
			</a>
		</h3>
				
			<jstl:if test="${shipmentOfferRow.acceptedBySender}">
					<p><b><spring:message code="shipmentOffer.accepted" /></b></p>
			</jstl:if>
			<jstl:if test="${shipmentOfferRow.rejectedBySender}">
					<p><b><spring:message code="shipmentOffer.rejected" /></b></p>
			</jstl:if>
			<jstl:if test="${!shipmentOfferRow.rejectedBySender && !shipmentOfferRow.acceptedBySender}">
					<p> <b><spring:message code="shipmentOffer.pending" /></b></p>
			</jstl:if>
			<jstl:if test="${!shipmentOfferRow.rejectedBySender && !shipmentOfferRow.acceptedBySender}">		
					<input type=submit class="btn-sm btn-llevar btn btn-success ok"
					value= "<spring:message code="shipmentOffer.accept" />" onclick="location.href = 'shipmentOffer/user/accept.do?shipmentOfferId=${shipmentOfferRow.id}';"></input>

					<input type=submit class="btn-sm btn-llevar btn btn-success ok"
					value= "<spring:message code="shipmentOffer.deny" />" onclick="location.href = 'shipmentOffer/user/deny.do?shipmentOfferId=${shipmentOfferRow.id}';"></input>
			</jstl:if>
			
			<p> <b><spring:message code="shipmentOffer.amount" />:</b> <jstl:out value="${shipmentOfferRow.amount}" /> </p>
			
			<p> <b><spring:message code="shipmentOffer.description" />:</b> <jstl:out value="${shipmentOfferRow.description}" /> </p>

<hr>

</jstl:forEach>


<jstl:if test="${fn:length(shipmentOffers) == 0}">
	<center>
		<h2>
			<spring:message code="shipmentOffer.anything" />
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