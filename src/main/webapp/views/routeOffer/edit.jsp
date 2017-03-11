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
<form:form action="routeOffer/user/edit.do" modelAttribute="routeOffer">
	<!-- Hidden Attributes -->
	<form:hidden path="id" />
	<form:hidden path="route" />
	<form:hidden path="user" />
	<form:hidden path="acceptedByCarrier" />
	<form:hidden path="rejectedByCarrier" />
	
	<!-- Editable Attributes -->
	
	<acme:textbox code="routeOffer.amount" path="amount"/>
	
	<acme:textarea code="routeOffer.description" path="description"/>
		
	<br/>

	<!-- Action buttons -->
	<acme:submit name="save" code="routeOffer.save"/>
	
	<acme:cancel code="routeOffer.cancel" url="routeOffer/user/list.do"/>
	
</form:form>