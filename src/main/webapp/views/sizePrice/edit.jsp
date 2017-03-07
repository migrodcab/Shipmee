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
<form:form action="sizePrice/user/edit.do" modelAttribute="sizePrice">
	<!-- Hidden Attributes -->
	<form:hidden path="route" />
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<!-- Editable Attributes -->
	
	<acme:textbox code="sizePrice.size" path="size"/>
	
	<acme:textbox code="sizePrice.price" path="price"/>
				
	<br/>

	<!-- Action buttons -->
	<acme:submit name="save" code="sizePrice.save"/>
	
	<jstl:if test="${sizePrice.id != 0}">
		<acme:submit_confirm name="delete" code="sizePrice.delete" codeConfirm="sizePrice.confirm.delete"/>
	</jstl:if>
	
	<acme:cancel code="sizePrice.cancel" url="route/user/list.do"/>
	
</form:form>