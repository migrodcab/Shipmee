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

<style>
.input-text {
	width: 90%;
	margin: 0px auto;
}

.envio {
	margin-top: 5%;
	border: 1px solid #ccc;
	border-radius: 25px;
	background-color: #f7f7f7;
	-webkit-box-shadow: 2px 2px 5px #999;
	-moz-box-shadow: 2px 2px 5px #999;
	margin-bottom: 5%;
	width: 100%;
	padding-bottom: 2%;
}

.rfecha {
	border-top: 1px solid #ccc;
}

.precio {
	border: 1px solid #ccc;
	border-radius: 25px;
	background-color: #f7f7f7;
	margin: auto;
	padding-top: 1%;
	padding-bottom: 1%;
	width: 20%;
	height: 20%;
	background-color: darkred;
	color: white;
}

.titulo {
	padding: 0.5%
}

.row-origen {
	padding-top: 0.5%;
}

.information {
	font-size: 16px;
}

.imagen-envio {
	margin-bottom: 0.5%;
}

.btn {
	white-space: normal;
}

.row {
	margin-left: 0px !important;
}

.info-lugar {
	padding-top: 0.5%;
	font-size: 120%;
	color: black;
}

.img-origin {
	color: green;
}

.img-destination {
	color: red;
}

.info-salida {
	padding-top: 1%;
	pading-bottom: 0.5%;
}

.dropdown-menu {
	background-color: white;
}

label {
	color: #495766 !important;
	font-weight: normal !important;
	position: relative;
}

.bootstrap-select.btn-group .dropdown-toggle .filter-option {
	width: auto !important;
}

.li-input {
	padding: 3%;
}
</style>

<style>
.i-code {
	display: none;
}

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


<div class="container">

	<div class="row profile">
		<div class="col-md-3">
			<div class="profile-sidebar">

				<div class="profile-usermenu">
					<form method="get" action="shipment/search.do">

						<ul class="nav">

							<li class="active"><a href=""> <i
									class="glyphicon glyphicon-map-marker img-origin"></i> Origen
							</a></li>
							<li class="li-input"><input type="text" name="origin"
								class="form-control input-text"></li>
							<li class="active"><a href=""> <i
									class="glyphicon glyphicon-map-marker img-destination"></i>
									Destino
							</a></li>
							<li class="li-input"><input name="destination" type="text"
								class="form-control input-text"></li>
							<li class="active"><a href="" target="_blank"> <i
									class="glyphicon glyphicon-plane
"></i> Fecha
							</a></li>
							<li class="li-input">
								<div class='input-group date input-text' id='datetimepicker1'>
									<input name="fecha" style="backgroud-color: white;" type='text'
										class="form-control" /> <span class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</li>
							<li class="active"><a href="#"> <i
									class="glyphicon glyphicon-eye-open"></i> Tipo de paquete
							</a></li>
							<li style="padding-bottom: 2%;">
								<div class="form-check form-check-inline input-text">
									<label class="form-check-label"> <input
										class="form-check-input" type="checkbox" id="inlineCheckbox1"
										value="open"> <i class="demo-icon icon-package-1">&#xe800;</i>Abierto
									</label> <label class="form-check-label"> <input
										class="form-check-input" type="checkbox" id="inlineCheckbox2"
										value="close"> <i class="demo-icon icon-package-1">&#xe801;</i>Cerrado
									</label>
								</div>

							</li>
							<li class="active"><a href="#"> <i
									class="glyphicon glyphicon-resize-full"></i> Tamaño del paquete
							</a></li>
							<li style="text-align: center" class="li-input"><select
								class="selectpicker input-text" multiple name="tam">
									<option selected="selected" value="xs">XS</option>
									<option selected="selected" value="s">S</option>
									<option selected="selected" value="m">M</option>
									<option selected="selected" value="l">L</option>
									<option selected="selected" value="xl">XL</option>
							</select></li>
							<li class="active"><button type="submit"
									class="btnSearch btn-lg btnSample btn-block btn-success">
									Search <span class="glyphicon glyphicon-search"></span>
								</button></li>
						</ul>
					</form>

				</div>
				<!-- END MENU -->
			</div>
		</div>
		<div class="col-md-9">
			<div class="profile-content">
				<jstl:choose>
					<jstl:when test="${not empty shipments}">
						<jstl:forEach items="${shipments}" var="shipment">
							<div class="row envio">
								<div class="row rtitulo">
									<div class="rtitulo col-sm-12 text-center ">
										<h4 class="titulo">Bicicleta de coleccion</h4>
									</div>
								</div>
								<div class="row info-envio">
									<div class="rfecha col-xs-7 col-sm-9">

										<div class="row info-lugar">

											<div class="col-xs-12 col-sm-4 text-center">
												<a href="#"> <i
													class="glyphicon glyphicon-map-marker img-origin"></i>${shipment.origin}
												</a>
											</div>

											<div class="col-sm-2 text-center">
												<i class="glyphicon glyphicon-sort"></i>
											</div>

											<div class="col-xs-12 col-sm-4 text-center">
												<a href="#"> <i
													class="glyphicon glyphicon-map-marker img-destination"></i>${shipment.destination}
												</a>
											</div>
										</div>

										<div class="row">
											<div class="info-salida col-sm-12 ">

												<i class="glyphicon glyphicon-plane
"></i> Salida: Sabado 11
												de Marzo a las 11 a.m


											</div>
										</div>
										<div class="row info1">
											<div class="col-xs-6">
												<i class="demo-icon icon-package-1">&#xe800;</i>Paquete
												abierto


											</div>
											<div class="col-xs-6">
												<i class="demo-icon icon-package-1">&#xe802;</i>Tamaño del
												paquete


											</div>
										</div>


									</div>
									<div class="imagen col-xs-5 col-sm-3 center">
										<div class="row text-center">
											<div class="col-xs-12">
												<img class="img-responsive center-block imagen-envio"
													width="70" height="70" src="images/bycicle.svg">
											</div>
											
											<!-- Hecho por Bartolomé y Torres -->
<!-- 											<div class="col-xs-12 text-center"> -->
<!-- 												<button type="button" -->
<!-- 													class="btn-xs btn-llevar btn btn-success "> -->
<%-- 													Llevar por<b></b> <jstl:set var="price" value="${fn:replace(shipment.price,  --%>
<%-- 		                                 '.0', '')}" /> <jstl:set var="priceFormated" value="${fn:replace(price,		                                 '.', ',')}" />${priceFormated}</b> euros</button> --%>
<!-- 												</button> -->
												
											<!-- Hecho por Guillermo para poder meter el link
											Si es necesario cambiarlo, no hay problemas -->	
											
											<form action="shipment/user/select.do?shipmentId=${shipment.id}" method="get">
    											<input type=submit class="btn-xs btn-llevar btn btn-success "
    												value= "Llevar por <jstl:set var="price" value="${fn:replace(shipment.price, 
                                '.0', '')}" /> <jstl:set var="priceFormated" value="${fn:replace(price, 
                                '.', ',')}" />${priceFormated} euros"/></input>
											</form>
												
										</div>

									</div>
								</div>

							</div>
						</jstl:forEach>
					</jstl:when>
					<jstl:otherwise>
						<p>No se han encontrado resultados</p>
					</jstl:otherwise>
				</jstl:choose>
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
