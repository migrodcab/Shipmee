<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<link rel="stylesheet" href="styles/assets/css/datetimepicker.min.css" />
<script type="text/javascript" src="scripts/moment.js"></script>
<script type="text/javascript" src="scripts/datetimepicker.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/css/bootstrap-select.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script>

<!-- (Optional) Latest compiled and minified JavaScript translation files -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/i18n/defaults-*.min.js"></script>

<link rel="stylesheet" href="styles/assets/css/lateral-menu.css"
	type="text/css">
<link rel="stylesheet" href="styles/assets/css/style-list.css" type="text/css">


<style>
@font-face {
	font-family: 'icons';
	src: url('styles/assets/fonts/iconos/iconos.eot?58322891');
	src: url('styles/assets/fonts/iconos/iconos.eot?58322891#iefix')
		format('embedded-opentype'),
		url('styles/assets/fonts/iconos/iconos.woff?58322891') format('woff'),
		url('styles/assets/fonts/iconos/iconos.ttf?58322891')
		format('truetype'),
		url('styles/assets/fonts/iconos/iconos.svg?58322891#fontello')
		format('svg');
	font-weight: normal;
	font-style: normal;
}

.demo-icon {
	font-family: "icons";
	font-style: normal;
	font-weight: normal;
	font-variant: normal;
	text-transform: none;
	font-size: 150%;
}

.size-icon {
	font-family: "package-open";
	font-style: normal;
	font-weight: normal;
	font-variant: normal;
	text-transform: none;
	font-size: 150%;
}
</style>


<div class="blue-barra">
	    <div class="container">
			<div class="row">
				<h3><spring:message code="shipment.shipments" /></h3>
			</div><!-- /row -->
	    </div>
	</div>

<div class="container">

	<div class="row profile">
		<div class="col-md-3">
			<div class="profile-sidebar">

				<div class="profile-usermenu">
					<form method="get" action="shipment/search.do">

						<ul class="nav">

							<li class="active"><a> <i
									class="glyphicon glyphicon-map-marker img-origin"></i> <spring:message code="shipment.origin" />
							</a></li>
							<li class="li-input"><input type="text" name="origin"
								class="form-control input-text" value="${origin}" required></li>
							<li class="active"><a><i
									class="glyphicon glyphicon-map-marker img-destination"></i>
									<spring:message code="shipment.destination" />
							</a></li>
							<li class="li-input"><input name="destination" type="text"
								class="form-control input-text" value="${destination}" required></li>
							<li class="active"><a href="" target="_blank"> <i
									class="glyphicon glyphicon-plane"></i> <spring:message code="shipment.date" />
							</a></li>
							<li class="li-input">
								<div class='input-group fondoDesplegable input-text' id='datetimepicker1'>
									<input name="date" style="backgroud-color: white;" type='text'
										class="form-control" /> <span class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</li>
							<li class="active"><a href="" target="_blank"> <i
									class="glyphicon glyphicon-time"></i> <spring:message code="shipment.hour" />
							</a></li>
							<li style="text-align: center" class="li-input">
								<select class="selectpicker input-text fondoDesplegable" name="hour">
									<jstl:forEach begin="0" end="23" varStatus="i">
										<jstl:choose>	
											<jstl:when test="${i.index lt 10 }">
												<option>0${i.index}:00</option>
											</jstl:when>
											<jstl:otherwise>
												<option>${i.index}:00</option>
											</jstl:otherwise>
										</jstl:choose>
									</jstl:forEach>
								</select>
							</li>
							<li class="active"><a href="#"> <i
									class="glyphicon glyphicon-eye-open"></i><spring:message code="shipment.package" />
							</a></li>
							<li style="padding-bottom: 2%;">
								<div class="form-check form-check-inline input-text">
									<label class="form-check-label"> <input
										class="form-check-input" type="checkbox" id="inlineCheckbox1" name="envelope"
										value="open"> <i class="demo-icon icon-package-1">&#xe800;</i><spring:message code="shipment.open" />
									</label> <label class="form-check-label"> <input
										class="form-check-input" type="checkbox" id="inlineCheckbox2" name="envelope"
										value="close"> <i class="demo-icon icon-package-1">&#xe801;</i><spring:message code="shipment.closed" />
									</label>
								</div>

							</li>
							<li class="active"><a> <i
									class="glyphicon glyphicon-resize-full"></i> <spring:message code="shipment.itemSize" />
							</a></li>
							<li style="text-align: center" class="li-input"><select
								class="selectpicker input-text fondoDesplegable" name="itemSize">
									<option selected="selected" disabled value=''><spring:message code="shipment.select.sizes" /></option>
									<option value="xs">XS</option>
									<option value="s">S</option>
									<option value="m">M</option>
									<option value="l">L</option>
									<option value="xl">XL</option>
							</select></li>
							<li class="active"><button type="submit"
									class="btnSearch btn-lg btnSample btn-block btn-success">
									<spring:message code="welcome.search" /> <span class="glyphicon glyphicon-search"></span>
								</button></li>
						</ul>
					</form>

				</div>
				<!-- END MENU -->
			</div>
		</div>
		
		
		<div class="col-md-9">
			<div class="profile-content">
		
		<!-- 2016 Thanks to TavoQiqe https://www.facebook.com/tavo.qiqe.lucero -->						
									
						
<div class="container">
	<div class="row">
		<section class="content">
			<div class="col-md-8">
				<div class="panel panel-default">
					<div class="panel-body">
						
						<div class="table-container">
							<table class="table table-filter">
								<tbody>
								
								
								<jstl:choose>
					<jstl:when test="${not empty shipments}">
						<jstl:forEach items="${shipments}" var="shipment">
								
									<tr>
										
										<td>
											<div class="media">
												<div class="pull-left">
													<img src="${shipment.itemPicture}" class="media-photo-shipment">
												</div>
												<div class="media-body">
													<span class="media-meta pull-right">${shipment.date}</span>
													<h4 class="title">
														<a>${shipment.itemName}</a>
														<span class="pull-right cantidad"></span>
													</h4>
													
													<div class="row info-lugar">

											<div class="col-xs-12 col-sm-4 text-center">
												<a><i class="glyphicon glyphicon-map-marker img-origin"></i>${shipment.origin}
												</a>
											</div>

											<div class="col-sm-2 text-center">
												<i class="glyphicon glyphicon-sort"></i>
											</div>

											<div class="col-xs-12 col-sm-4 text-center">
												<a> <i
													class="glyphicon glyphicon-map-marker img-destination"></i>${shipment.destination}
												</a>
											</div>
										</div>
													
												</div>
											</div>
											
										<div class="row">
											<div class="info-salida col-sm-12 ">

												<div class="view" style="float: right;"><div class="price"><h4>${shipment.price} Euros</h4></div><h4><a href="shipment/display.do?shipmentId=${shipment.id}">
												
						
												
													<spring:message code="route.details" />
													<i class="glyphicon glyphicon-chevron-right"></i></a></h4>
													
													
													
												</div>

												<i class="glyphicon glyphicon-plane"></i> 
												<spring:message code="shipment.departureTime" />: ${shipment.date} / ${shipment.departureTime}
												<br/>
												<i class="glyphicon glyphicon-plane"></i> 
												<spring:message code="shipment.maximumArriveTime" />: ${shipment.maximumArriveTime}
												
													
											</div>
										</div>
											
										
											
										</td>
									</tr>
									</jstl:forEach>
					</jstl:when>
					<jstl:otherwise>
						<p><spring:message code="shipment.results" /></p>
					</jstl:otherwise>
				</jstl:choose>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				
			</div>
		</section>
		
	</div>
</div>
										
			</div>


		</div>

	</div>
</div>







<script type="text/javascript">
	$(function() {
		$('#datetimepicker1').datetimepicker({
			viewMode : 'days',
			format : 'DD/MM/YYYY'
		});
	});
</script>
