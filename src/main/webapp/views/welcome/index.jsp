
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


  <body>

	<div id="headerwrap">
	    <div class="container">
			<div class="row">
				<div class="col-lg-8 col-lg-offset-2">
				<div class="titlePrincipal">
					<h3><spring:message code="welcome.sentence" /></h3>
					<h1><spring:message code="welcome.title" /></h1>
					</div>
					<h4><spring:message code="welcome.sentence2" /></h4>
								
				</div>
				<div class="col-lg-8 col-lg-offset-2 himg">

				</div>
			</div>


    <div id="panel" class="col-lg-offset-4 col-lg-4 col-md-offset-3 col-md-6 col-sm-offset-3 col-sm-6 col-xs-offset-1 col-xs-10" >
        <h3 class="titleSearch"><spring:message code="welcome.searcher.title" /></h3>

        <form>
            <div class="group">
                <input class="camp" type="text">
                <span class="highlight"></span>
                <span class="bar"></span>
                <label><span class="glyphicon glyphicon-pushpin">&nbsp;</span> <spring:message code="welcome.searcher.origin" /> </label>
            </div>

            <div class="group">
                <input class="camp" type="text">
                <span class="highlight"></span>
                <span class="bar"></span>
                <label><span class="glyphicon glyphicon-pushpin">&nbsp;</span><spring:message code="welcome.searcher.destination" /></label>
            </div>

            <div class="group">
                <input class="camp" type="text">
                <span class="highlight"></span>
                <span class="bar"></span>
                <label><span class="glyphicon glyphicon-calendar">&nbsp;</span><spring:message code="welcome.searcher.date" /></label>
            </div>

            <div class="group">
                <input class="camp" type="text">
                <span class="highlight"></span>
                <span class="bar"></span>
                <label><span class="glyphicon glyphicon-time">&nbsp;</span><spring:message code="welcome.searcher.time" /></label>
            </div>
            <div class="group">
               <button type="submit" class="btnSearch btn-lg btnSample btn-block btn-success"><spring:message code="welcome.search" /> <span class="glyphicon glyphicon-search"></span></button>
       		</div>
        </form>

    </div>


		</div>
	    	    
	    
	</div>
	
	
	 <div id="service">
	 	<div class="container">
 			<div class="row centered">
 				<div class="col-md-4">
 					<i class="fa fa-heart-o"></i>
 					<h4><spring:message code="welcome.foryou" /></h4>
 					<p><spring:message code="welcome.foryou.text" /></p>
 				</div>
 				<div class="col-md-4">
 					<i class="fa fa-flask"></i>
 					<h4><spring:message code="welcome.experiment" /></h4>
 					<p><spring:message code="welcome.experiment.text" /></p>
 				</div>
 				<div class="col-md-4">
 					<i class="fa fa-trophy"></i>
 					<h4><spring:message code="welcome.quality" /></h4>
 					<p><spring:message code="welcome.quality.text" /></p>
 				</div>		 				
	 		</div>
	 	</div>
	 </div>
	
	
	</body>