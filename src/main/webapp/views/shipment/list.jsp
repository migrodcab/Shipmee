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

<style>
.envio {
	margin-top: 10%;
	border: 1px solid #ccc;
	border-radius: 25px;
	background-color: #f7f7f7;
	-webkit-box-shadow: 2px 2px 5px #999;
	-moz-box-shadow: 2px 2px 5px #999;
	margin-bottom: 5%;
	width:90%;
}

.rfecha {
	border-bottom: 1px solid #ccc;
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

.btn-llevar {
	margin-top: 0.5%;
}

.imagen-envio {
	margin-top: 0.5%;
}

.info-destino {
	padding-top: 0.5%;
}
</style>

<div class="container">
	<div class="row envio">
		<div class="row rtitulo">
			<div class="rtitulo col-sm-12 text-center ">
				<h4 class="titulo">Bicicleta de coleccion</h4>
			</div>
		</div>
		<div class="row info-envio">
			<div class="rfecha col-xs-7 col-sm-9">

				<div class="row info-destino">
					<div class="col-xs-2 col-sm-1 text-center">
						<img class="img-responsive" src="images/origin.svg" width="40"
							alt="Origen">
					</div>
					<div class="col-xs-10 col-sm-4 text-center">
						<h4>Sevilla Santa Justa</h4>
					</div>
					<div class="col-sm-2 text-center">
						<img class="center-block img-responsive" src="images/exchange.svg"
							width="25" alt="Origen">
					</div>
					<div class="col-xs-10 col-sm-4 text-center">
						<h4>Almeria</h4>
					</div>
					<div class="col-xs-2 col-sm-1 text-center">
						<img class="img-responsive" src="images/destination.svg"
							width="40" alt="Origen">
					</div>
				</div>

				<div class="row">
					<div class="titulo col-sm-12 text-center">
						<div class="row row-origen">

							<div class="col-xs-2">
								<img class="pull-right img-responsive imagen-envio"
									src="images/departure.svg" width="25" alt="Llegada">
							</div>
							<div class="col-xs-10 text-left information">Hora de
								salida: Lunes 13 de Marzo 13:00</div>
						</div>
					</div>
				</div>
				<div class="row info1">
					<div class="col-xs-6">
						<div class="row">
							<div class="col-sm-9">
								<p class="information text-center">Forma de envio:</p>
							</div>
							<div class="col-sm-3">
								<img class=" pull-left img-responsive center-block" width="40"
									height="40" src="images/package_open.svg">
							</div>
						</div>

					</div>
					<div class="col-xs-6">
						<div class="row">
							<div class="col-sm-9 text-center">
								<p class="information">Tamanyo del paquete</p>
							</div>
							<div class="col-sm-3">
								<img class="img-responsive pull-left" width="40" height="40"
									src="images/tag-l.png">
							</div>
						</div>

					</div>
				</div>


			</div>
			<div class="imagen col-xs-5 col-sm-3 center">
				<div class="row text-center">
					<div class="col-xs-12">
						<img class="img-responsive center-block imagen-envio" width="90"
							height="90" src="images/bycicle.svg">
					</div>
					<div class="col-xs-12 text-center">
						<button type="button" class="btn-llevar btn btn-success">Llevar
							por 20 euros</button>
					</div>
				</div>

			</div>
		</div>

	</div>
</div>
