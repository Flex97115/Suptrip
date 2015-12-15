<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/app.css"/>">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<title>Register</title>
</head>

<script type="text/javascript" id="taille">

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

	<h1>Register</h1>
	
	<div class="gestionBlock">
		
		<form method="post" action="/suptrip/register">
			
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
				<label for="firstName" class="col-md-12">First name</label>
				<div class="col-md-offset-4 col-md-4 espaceBtn">
					<input class="form-control btnAlign" id="firstName" name="firstName" type="text" required>
				</div>
			</div>
			
			<div class="form-group">
				<label for="lastName" class="col-md-12">Last name</label>
				<div class="col-md-offset-4 col-md-4 espaceBtn">
					<input class="form-control btnAlign" id="lastName" name="lastName" type="text" required>
				</div>
			</div>
			
			<div class="form-group">
				<label for="email" class="col-md-12">Email</label>
				<div class="col-md-offset-4 col-md-4 espaceBtn">
					<input class="form-control btnAlign" id="email" name="email" type="email" required>
				</div>
			</div>
			
			<div class="form-group">
				<label for="campus" class="col-md-12">Campus</label>
				<div class="col-md-offset-4 col-md-4 espaceBtn">
					<select class="form-control btnAlign" name="campus" id="campus" required>
						<c:forEach items="${campus}" var="c">
							<option class="btnAlign" value="${c.getId()}">${c.getName()}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-md-12 btnAlign espaceBtn">
					<button type="submit" class="btn btn-primary">Sign up <i class="fa fa-check-circle-o"></i></button>
				</div>
			</div>
			
		</form>
		<c:choose>
			<c:when test="${invalide == true}">
			<div class="row">
				<div class="col-md-offset-5 col-md-4 espaceBtn">
				<p class="alert alert-danger alertInvalide"> <strong> Invalide id booster</strong> </p>
				</div>
			</div>
			</c:when>
		</c:choose>
			
	</div>
	
</div>

<jsp:include page="/WEB-INF/headfoot/footer.jsp"/>

</body>
</html>