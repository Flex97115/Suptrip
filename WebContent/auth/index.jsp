<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/app.css"/>">
	<script src="//cdn.jsdelivr.net/webshim/1.14.5/polyfiller.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	
	<script type="text/javascript">
		webshims.setOptions('forms-ext', {types: 'date'});
		webshims.polyfill('forms forms-ext');

		function tailleBackground(){
			var hContainer = $("#taille").height();
			var hauteur = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;
			
			if (hContainer < hauteur) {
				$(".container").css({"height":"100%"});
			}
			else if(hContainer == hauteur){
				$(".container").css({"height":"100%"});
			}
			else{
				$(".container").css({"height":"auto"});
			}
		}

		$(document).ready(function(){
			tailleBackground();
		});

		window.onresize = tailleBackground;

	</script>
	<title>SubTrip</title>
</head>

<script type="text/javascript">
var airportList = {};
var airportArray = [];
var  i = 0;
function setAirport(cityId, airportId, airportName){
	airportList = {
			'cityId': cityId,
			'airportId': airportId,
			'airportName': airportName
	};
	airportArray[i] = airportList;
	i++;
}

var campusList = {};
var campusArray = [];
var a = 0;
function setCampus(campusId , campusName){
	campusList = {
			'campusId':campusId,
			'campusName':campusName
	};
	campusArray[a] = campusList;
	a++;
}
</script>


<body>

<div class="container" id="taille">

	<jsp:include page="/WEB-INF/headfoot/header.jsp"/>

	<h1> Search your trip </h1>

	<form method="post" action="/suptrip/auth/index">
		<input type="hidden" name="type" value="search" />
		
		<div id="gestionBlock">
			<table class="table table-hover">
				<tr>
					<th class="btnAlign"> Trip </th>
					<th class="btnAlign"> City </th>
					<th class="btnAlign"> Airport </th>
					<th class="btnAlign"> Date </th>
				</tr>
				
				<tr>
					<th class="btnAlign"> Departure </th>
					<td> 
					<select class="form-control btnAlign" name="campusD" id="campusD" required>
						<option value="0" selected>-------</option>
						<c:forEach items="${campus}" var="c">
							<option value="${c.getId()}">${c.getName()}</option>
						</c:forEach>
					</select> 
					</td> 
					<td>
					<select class="form-control btnAlign" name="airportD" id="airportD" required disabled>
						<c:forEach items="${airports}" var="airport">
							<script type="text/javascript"> setAirport('${airport.campus.getId()}' , '${airport.getId()}' , '${airport.getName()}')</script>
						</c:forEach>
					</select> 
					</td>
					<td>
						<div class="btnAlign">
							<input type="date" name="date" id="date" class="form-control">
						</div>
					</td>
				</tr>
				
				<tr>
					<th class="btnAlign"> Destination </th>
						<td> 
							<select class="form-control btnAlign" name="campusA" id="campusA" required disabled>
								<c:forEach items="${campus}" var="c">
									<script type="text/javascript"> setCampus('${c.getId()}' , '${c.getName()}')</script>
								</c:forEach>
							</select> 
						</td> 
					<td>
					<select class="form-control btnAlign" name="airportA" id="airportA" required disabled>
					
					</select> 
					</td>
				</tr>
			
				<tr>
					<th> Passengers </th>
					<td>
						<select class="form-control btnAlign" name="passengers" id="passengers" required>
						<c:forEach var="i" begin="1" end="9">
								<option value="${i}">${i}</option>
						</c:forEach>
						</select>
					</td>
				</tr>
			</table>
			
			<div class="form-group">
				<div class="col-md-12 btnAlign espaceBtn">
					<button class="btn btn-primary" type="submit"><i class="fa fa-search"></i></button>
				</div>
			</div>
			
		</div>
	</form>
	
	<c:choose>
		<c:when test="${tripList != null }">
		
			<table class="table table-hover padTable2">
				<tr>
					<th class="btnAlign">Departure</th>
					<th class="btnAlign">Departure time (local time)</th>
					<th class="btnAlign">Arrival</th>
					<th class="btnAlign">Arrival time (local time)</th>
					<th class="btnAlign">Carrier</th>
					<th class="btnAlign">Passenger(s)</th>
					<th class="btnAlign">Total price </th>
					<th class="btnAlign">Bag</th>
				</tr>
				<c:forEach items="${tripList}" var="trip">
					<tr>
						<th class="btnAlign">
							<c:out value="${campusD.getName()}"></c:out> -  <c:out value="${airportD.getName()}"></c:out>
						</th>
						<th class="btnAlign">
							${trip.getDepartureTime()}
						</th>
						<th class="btnAlign">
							<c:out value="${campusA.getName()}"></c:out> -  <c:out value="${airportA.getName()}"></c:out>
						</th>
						<th class="btnAlign">
							${trip.getArrivalTime()}
						</th>
						<th class="btnAlign">
							${trip.getCarrier()}
						</th>
						<th class="btnAlign">
							<c:out value="${passengers}"></c:out>
						</th>
						<th class="btnAlign">
							${trip.getPrice()} €
						</th>
						<th>
						<form method="POST" action="/suptrip/auth/addtobag">
							<input type="hidden" name="type" value="AddBag" />
						    <input type="hidden" name="tripToAdd" value="${trip.getId()}">
						    <button type="submit" class="btn btn-primary"><i class="fa fa-plus-circle"></i></button>
						</form>
						</th>
					</tr>
				</c:forEach>
			</table>
			
		</c:when>
		
		<c:when test="${tripList == null }">
			<div class="alert alert-info alertRegister col-md-offset-5 col-md-2 padTable2" role="alert">
					<p class="btnAlign"><strong>NO TRIP FOUND</strong></p>
			</div>
		</c:when>
		
	</c:choose>
	<jsp:include page="/WEB-INF/headfoot/footer.jsp"/>
</div>
</body>


<script type="text/javascript">
var campusDValue;
$('#campusD').change(function(){
	$('#airportD').removeAttr('disabled');
	$("#campusD option[value='0']").remove();
	$('#airportD').find('option').remove();
	$('#campusA').find('option').remove();
	$('#airportD').append('<option value="0">-------</option>');
	campusDValue = this.value;
	airportArray.forEach(function(airport){	
		if(airport.cityId == campusDValue){
			$('#airportD').append('<option value="'+airport.airportId+'">'+airport.airportName+'</option>');
		}
	});
});
$('#airportD').change(function(){
	$("#airportD option[value='0']").remove();
	$('#campusA').find('option').remove();
	$('#campusA').append('<option value="0">-------</option>');
	$('#campusA').removeAttr('disabled');
	campusArray.forEach(function(campus){
		if(campus.campusId != campusDValue){
			$('#campusA').append('<option value="'+campus.campusId+'">'+campus.campusName+'</option>');
		}
	});
});
$('#campusA').change(function(){
	$('#airportA').removeAttr('disabled');
	$('#airportA').find('option').remove();
	$('#airportA').append('<option value="0">-------</option>');
	var campusAValue = this.value;
	airportArray.forEach(function(airport){	
		if(airport.cityId == campusAValue){
			$('#airportA').append('<option value="'+airport.airportId+'">'+airport.airportName+'</option>');
		}
	});
});
$('#airportA').change(function(){
	$("#airportA option[value='0']").remove();
});

$('#sandbox-container').datepicker({
	format: "dd/mm/yyyy",
	maxViewMode: 0,
	todayBtn: "linked",
	language: "fr",
	autoclose: true
});

</script>
</html>
