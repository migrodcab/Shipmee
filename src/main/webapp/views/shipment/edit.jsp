<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<!-- Form -->
<form:form action="shipment/user/edit.do" modelAttribute="shipmentForm">
	<!-- Hidden Attributes -->
	<form:hidden path="shipmentId" />
	
	<!-- Editable Attributes -->
	
	<acme:textbox code="shipment.origin" path="origin"/>
	
	<acme:textbox code="shipment.destination" path="destination"/>
	
	<acme:datetime code="shipment.departureTime" path="departureTime"/>
	
	<acme:datetime code="shipment.maximumArriveTime" path="maximumArriveTime"/>
	
	<form:label path="itemSize">
		<spring:message code="shipment.itemSize" />
	</form:label>
	<spring:message code="shipment.sizeS" var="s"/>
	<spring:message code="shipment.sizeM" var="m"/>
	<spring:message code="shipment.sizeL" var="l"/>
	<spring:message code="shipment.sizeXL" var="xl"/>
	<form:select id="shipment" path="ItemSize">
		<form:option value="" label="----" />
		<form:option value="S" label="${s }" />
		<form:option value="M" label="${m }" />
		<form:option value="L" label="${l }" />
		<form:option value="XL" label="${xl }" />
	</form:select>
	<form:errors path="itemSize" cssClass="error" />
	
	<acme:textbox code="shipment.price" path="price"/>
	
	<acme:textbox code="shipment.itemName" path="itemName"/>
	
	<acme:textbox code="shipment.itemPicture" path="itemPicture"/>
		
	<form:label path="itemEnvelope">
		<spring:message code="shipment.itemEnvelope" />
	</form:label>
	<spring:message code="shipment.open" var="open"/>
	<spring:message code="shipment.closed" var="closed"/>
	<form:select id="shipment" path="ItemEnvelope">
		<form:option value="" label="----" />
		<form:option value="${open }" label="${open }" />
		<form:option value="${closed }" label="${closed }" />
	</form:select>
	<form:errors path="itemEnvelope" cssClass="error" />
	
		
	<br/>

	<!-- Action buttons -->
	<acme:submit name="save" code="shipment.save"/>
	
	<jstl:if test="${shipmentForm.shipmentId != 0}">
		<acme:submit_confirm name="delete" code="shipment.delete" codeConfirm="shipment.confirm.delete"/>
	</jstl:if>
	
	<acme:cancel code="shipment.cancel" url="shipment/user/list.do"/>
	
</form:form>