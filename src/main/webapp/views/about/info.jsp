<%--
 * index.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


	<div id="blue">
	    <div class="container">
			<div class="row">
				<h3><spring:message code="info.title" /></h3>
			</div><!-- /row -->
	    </div>
	</div>

	 <div class="container mtb">
	 	<div class="row">
	 		<div class="col-lg-6">
	 			<img class="foto-shipmee" src="images/cajalogoV2.png" alt="">
	 		</div>
	 		
	 		<div class="col-lg-6">
		 		<h4><spring:message code="info.shipmee" /></h4>
		 		<p><spring:message code="info.about.text1" /></p>
		 		<p><spring:message code="info.about.text2" /></p>
		 		
		 		<!--<p><br/><a href="contact.html" class="btn btn-theme">Contact Us</a></p>-->
	 		</div>
	 	</div>
	 </div>

	
	 <div id="twrap">
	 	<div class="container centered">
	 		<div class="row">
	 			<div class="col-lg-8 col-lg-offset-2">
	 			<i class="fa fa-comment-o"></i>
	 			<p>....</p>
	 			<h4><br/>....</h4>
	 			<p>...</p>
	 			</div>
	 		</div>
	 	</div>
	 </div>
