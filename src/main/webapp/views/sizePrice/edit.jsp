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
<form:form action="sizePrice/user/edit.do" modelAttribute="sizePriceForm">
	<!-- Hidden Attributes -->
	<form:hidden path="routeId" />
	<form:hidden path="sizePriceFormId" />
	
	<!-- Editable Attributes -->
			
	<acme:number code="sizePrice.priceS" path="priceS" min="0" step="0.10"/>
	
	<acme:number code="sizePrice.priceM" path="priceM" min="0" step="0.10"/>
	
	<acme:number code="sizePrice.priceL" path="priceL" min="0" step="0.10"/>
	
	<acme:number code="sizePrice.priceXL" path="priceXL" min="0" step="0.10"/>
			
	<br/>

	<!-- Action buttons -->
	<acme:submit name="save" code="sizePrice.save"/>
	
	<jstl:if test="${sizePriceForm.sizePriceFormId != 0}">
		<acme:submit_confirm name="delete" code="sizePrice.delete" codeConfirm="sizePrice.confirm.delete"/>
	</jstl:if>
	
	<acme:cancel code="sizePrice.cancel" url="route/user/list.do"/>
	
</form:form>