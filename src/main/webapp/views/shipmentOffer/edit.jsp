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
<form:form action="shipmentOffer/user/edit.do" modelAttribute="shipmentOffer">
	<!-- Hidden Attributes -->
	<form:hidden path="id" />
	<form:hidden path="shipment" />
	<form:hidden path="user" />
	<form:hidden path="acceptedBySender" />
	<form:hidden path="rejectedBySender" />
	
	<!-- Editable Attributes -->
	
	<acme:textbox code="shipmentOffer.amount" path="amount"/>
	
	<acme:textarea code="shipmentOffer.description" path="description"/>
		
	<br/>

	<!-- Action buttons -->
	<acme:submit name="save" code="shipmentOffer.save"/>
	
	<acme:cancel code="shipmentOffer.cancel" url="shipmentOffer/user/list.do"/>
	
</form:form>