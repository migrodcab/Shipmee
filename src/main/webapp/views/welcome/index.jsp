
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
					<h3><spring:message code="welcome.sentence" /></h3>
					<h1 class="titlePrincipal"><spring:message code="welcome.title" /></h1>
					<h5><spring:message code="welcome.sentence2" /></h5>
								
				</div>
				<div class="col-lg-8 col-lg-offset-2 himg">

				</div>
			</div>
	     
      	
	    </div>
	    
	    <div class="container2">
	    <div class="well-searchbox">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-md-4 control-label"><spring:message code="welcome.searcher.origin" /></label>
                        <div class="col-xs-4">
                            <input type="text" class="form-control" placeholder="City">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label"><spring:message code="welcome.searcher.destination" /></label>
                        <div class="col-xs-4">
                            <input type="text" class="form-control" placeholder="City">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label"><spring:message code="welcome.searcher.date" /></label>
                        <div class="col-xs-4">
                            <input type="text" class="form-control" placeholder="Date">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label"><spring:message code="welcome.searcher.time" /></label>
                        <div class="col-xs-4">
                            <input type="text" class="form-control" placeholder="Time">
                        </div>
                    </div>

                        <button type="submit" class="btnSample btn-success">Search</button>
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