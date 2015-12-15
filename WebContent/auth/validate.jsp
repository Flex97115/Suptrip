<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/app.css"/>">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<title>Validation</title>
</head>

<script type="text/javascript">

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

<body>

<div class="container" id="taille">
	<jsp:include page="/WEB-INF/headfoot/header.jsp"/>
	<h1> Confirm the order </h1>
	<table class="table table-hover padTable2">
		<tr>
			<td class="btnAlign">Departure</td>
			<td class="btnAlign">Departure time (local time)</td>
			<td class="btnAlign">Arrival</td>
			<td class="btnAlign">Arrival time (local time)</td>
			<td class="btnAlign">Carrier</td>
			<td class="btnAlign">Aircraft</td>
			<td class="btnAlign">Passenger(s)</td>
			<td class="btnAlign">Price </td>
		</tr>
		<c:forEach items="${bags}" var="b">
			<tr>
				<th class="btnAlign">
					${b.getTrip().getOrigin().getName()} -  ${b.getTrip().getAirportOri().getName()}
				</th>
				<th class="btnAlign">
					${b.getTrip().getDepartureTime()}
				</th>
				<th class="btnAlign">
					${b.getTrip().getDestination().getName()} -  ${b.getTrip().getAirportDes().getName()}
				</th>
				<th class="btnAlign">
					${b.getTrip().getArrivalTime()}
				</th>
				<th class="btnAlign">
					${b.getTrip().getCarrier()}
				</th>
				<th class="btnAlign">
					${b.getTrip().getAircraft()}
				</th>
				<th class="btnAlign">
					${b.getTrip().getPassengers()}
				</th>
				<th class="btnAlign">
					${b.getTrip().getPrice()} €
				</th>
			</tr>
		</c:forEach>
	</table>
	
	<form method="POST" action="/suptrip/auth/validateorder">
		<div class="col-md-12 btnAlign espaceBtn">
			<p><strong>Total price : </strong> ${totalPrice} €</p>
			<button type="submit" class="btn btn-primary">Confirm </button>
		</div>
	</form>
</div>

</body>
</html>