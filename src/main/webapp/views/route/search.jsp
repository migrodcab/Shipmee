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

<style>
.route {
	margin-top: 10%;
	border: 1px solid #ccc;
	border-radius: 25px;
	background-color: #f7f7f7;
	-webkit-box-shadow: 2px 2px 5px #999;
	-moz-box-shadow: 2px 2px 5px #999;
	margin-bottom: 5%
}

</style> 
<div class="container route">
	<jstl:choose>
		<jstl:when test="${not empty routes}">
			<ul class="list-group">
			<jstl:forEach items="${routes}" var="route">
			 	<li class="list-group-item">
			 		${route.creator.name}
					${route.origin}
					${route.destination}
					${route.date}
					${route.departureTime}
				</li>
			</jstl:forEach>
		</ul>
		</jstl:when>
		<jstl:otherwise>
			<p>No se han encontrado resultados</p>
		</jstl:otherwise>
	</jstl:choose>
</div>