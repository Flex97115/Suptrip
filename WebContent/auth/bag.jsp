<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/app.css"/>">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<title>Bag</title>
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
	
	<h1> Bag </h1>
	<c:choose>
		<c:when test="${bags != null }">
			<table class="table table-hover padTable2">
				<tr>
					<th class="btnAlign">Departure</th>
					<th class="btnAlign">Departure time (local time)</th>
					<th class="btnAlign">Arrival</th>
					<th class="btnAlign">Arrival time (local time)</th>
					<th class="btnAlign">Carrier</th>
					<th class="btnAlign">Passenger(s)</th>
					<th class="btnAlign">Price </th>
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
							${b.getTrip().getPassengers()}
						</th>
						<th class="btnAlign">
							${b.getTrip().getPrice()} €
						</th>
					</tr>
				</c:forEach>

			</table>
			
			<form method="POST" action="/suptrip/auth/bag">
				<div class="col-md-12 btnAlign espaceBtn">
					<p><strong> Total price : </strong> ${totalPrice} €</p>
				    <button type="submit" class="btn btn-primary">Process to order <i class="fa fa-arrow-circle-o-right"></i></button>
				</div>
			</form>
		</c:when>
		
		<c:when test="${bags == null }">
			<div class="alert alert-info alertRegister col-md-offset-5 col-md-2 padTable2" role="alert">
					<p class="btnAlign"><strong>THERE IS NOTHING IN THIS BAG</strong></p>
			</div>
		</c:when>
	</c:choose>
	<jsp:include page="/WEB-INF/headfoot/footer.jsp"/>
</div>
</body>
</html>