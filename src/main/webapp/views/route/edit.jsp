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
<form:form action="route/user/edit.do" modelAttribute="route">
	<!-- Hidden Attributes -->
	<form:hidden path="creator" />
	<form:hidden path="date" />
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<!-- Editable Attributes -->
	
	<acme:textbox code="route.origin" path="origin"/>
	
	<acme:textbox code="route.destination" path="destination"/>
		
	<acme:textbox code="route.departureTime" path="departureTime"/>
	
	<acme:textbox code="route.arriveTime" path="arriveTime"/>
	
	<acme:textbox code="route.itemEnvelope" path="itemEnvelope"/>
	
	<acme:select items="${vehicles}" itemLabel="brand" code="route.vehicle" path="vehicle"/>
		
	<br/>

	<!-- Action buttons -->
	<acme:submit name="save" code="route.save"/>
	
	<jstl:if test="${route.id != 0}">
		<acme:submit_confirm name="delete" code="route.delete" codeConfirm="route.confirm.delete"/>
	</jstl:if>
	
	<acme:cancel code="route.cancel" url="route/user/list.do"/>
	
</form:form>