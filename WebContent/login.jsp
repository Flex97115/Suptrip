<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="css/app.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<title>Login</title>
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
	
	<h1>Login</h1>
	<div class="gestionBlock">
	<form method="post" action="/suptrip/login">
		
			<div class="form-group">
				<label for="idBooster" class="col-md-12">Id booster</label>
				<div class="col-md-offset-4 col-md-4 espaceBtn">	
					<input class="form-control btnAlign" id="idBooster" name="idBooster" type="text" required>
				</div>	
			</div>
		
			<div class="form-group">
				<label for="password" class="col-md-12">Password</label>
				<div class="col-md-offset-4 col-md-4 espaceBtn">
					<input class="form-control btnAlign" id="password" name="password" type="password" required>
				</div>
			</div>
		
			<div class="form-group">
				<div class="col-md-12 btnAlign espaceBtn">
					<button class="btn btn-primary" type="submit">Sign in <i class="fa fa-check-circle-o"></i></button>
				</div>
			</div>
		</form>
		<c:choose>
			<c:when test="${isLogged == false}">
			<div class="row">
				<div class="col-md-offset-4 col-md-4 btnAlign espaceBtn">
					<p class="alert alert-danger alertLogin"> <strong> Id booster or password is incorrect</strong> </p>
				</div>
			</div>
			</c:when>
		</c:choose>
	</div>
	
	<jsp:include page="/WEB-INF/headfoot/footer.jsp"/>
</div>
</body>

</html>