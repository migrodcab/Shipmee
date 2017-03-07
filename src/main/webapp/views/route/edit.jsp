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
<form:form action="route/user/edit.do" modelAttribute="routeForm">
	<!-- Hidden Attributes -->
	<form:hidden path="routeId" />
	
	<!-- Editable Attributes -->
	
	<acme:textbox code="route.origin" path="origin"/>
	
	<acme:textbox code="route.destination" path="destination"/>
	
	<acme:datetime code="route.departureTime" path="departureTime"/>
	
	<acme:datetime code="route.arriveTime" path="arriveTime"/>
		
	<form:label path="itemEnvelope">
		<spring:message code="route.itemEnvelope" />
	</form:label>
	
	<spring:message code="route.open" var="open"/>
	<spring:message code="route.closed" var="closed"/>
	<spring:message code="route.both" var="both"/>
	
	<form:select id="route" path="ItemEnvelope">
		<form:option value="" label="----" />
		<form:option value="${open }" label="${open }" />
		<form:option value="${closed }" label="${closed }" />
		<form:option value="${both }" label="${both }" />
	</form:select>
	<form:errors path="itemEnvelope" cssClass="error" />
	
	<acme:select items="${vehicles}" itemLabel="brand" code="route.vehicle" path="vehicle"/>
		
	<br/>

	<!-- Action buttons -->
	<acme:submit name="save" code="route.save"/>
	
	<jstl:if test="${routeForm.routeId != 0}">
		<acme:submit_confirm name="delete" code="route.delete" codeConfirm="route.confirm.delete"/>
	</jstl:if>
	
	<acme:cancel code="route.cancel" url="route/user/list.do"/>
	
</form:form>