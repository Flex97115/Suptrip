<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/app.css"/>">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<title>Suptrip</title>
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
	
	<h1 id="suptrip"><span id="sup">SUP</span><span id="trip">TRIP</span></h1>
	<c:choose>
		<c:when test="${login == null}">
			<div id="gestionBlock">	
				<div class="alert alert-info alertRegister col-md-offset-5" role="alert">
					<p><strong>Users : </strong> <c:out value="${users}"></c:out> <i class="fa fa-users"></i></p>
				</div>
				<div class="alert alert-success alertDestination col-md-offset-8" role="alert">
					<p><strong>Destinations : </strong> <c:out value="${destinations}"></c:out> <i class="fa fa-map-marker"></i></p>
				</div>
				<div class="alert alert-danger alertTrips col-md-offset-3" role="alert">
					<p><strong>Trips : </strong>Over 1 000 000 <i class="fa fa-plane"></i></p>
				</div>
				<blockquote>
					<p>Suptrip is an application developed by Supinfo's students.<br/> This application allow you to found a direct flight between Supinfo's campuses. <br />The flights are retrieved by <a href="https://developers.google.com/qpx-express/" target="_blank">QPX express</a>, an API developed by Google. </p>
					<footer class="pull-right">Suptrip Application</footer>
				</blockquote>
			</div>
	 	</c:when>
	</c:choose>
	
</div>

</body>

<jsp:include page="/WEB-INF/headfoot/footer.jsp"/>
</html>