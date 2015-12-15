<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/app.css"/>">
	<title>My Profile</title>
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
	<div class="container">
		
		<jsp:include page="/WEB-INF/headfoot/header.jsp"/>
		
		<div class="gestionBlock">
			<form method="post" action="/suptrip/auth/profile">
				
				<div class="form-group">
					<label for="idBooster" class="col-md-12">Id booster</label>
					<div class="col-md-offset-4 col-md-4 espaceBtn">
						<input class="form-control btnAlign" id="password" name="idBooster" type="text" value="<c:out value="${idBooster}"></c:out>" disabled required>
					</div>
				</div>
				
				<div class="form-group">
					<label for="password" class="col-md-12">Password</label>
					<div class="col-md-offset-4 col-md-4 espaceBtn">
						<input class="form-control btnAlign" id="password" name="password" type="password" value="<c:out value="${password}"></c:out>" required>
					</div>
				</div>
				
				<div class="form-group">
					<label for="firstName" class="col-md-12">First name</label>
					<div class="col-md-offset-4 col-md-4 espaceBtn">
						<input class="form-control btnAlign" id="firstName" name="firstName" type="text" value="<c:out value="${firstName}"></c:out>" required>
					</div>
				</div>	
				
				<div class="form-group">
					<label for="lastName" class="col-md-12">Last name</label>
					<div class="col-md-offset-4 col-md-4 espaceBtn">
						<input class="form-control btnAlign" id="lastName" name="lastName" type="text" value="<c:out value="${lastName}"></c:out>" required>
					</div>
				</div>
				
				<div class="form-group">
					<label for="email" class="col-md-12">Email</label>
					<div class="col-md-offset-4 col-md-4 espaceBtn">
						<input class="form-control btnAlign" id="email" name="email" type="email" value="<c:out value="${email}"></c:out>" required>
					</div>
				</div>
				
				<div class="form-group">
					<label for="campus" class="col-md-12">Campus</label>
					<div class="col-md-offset-4 col-md-4 espaceBtn">
						<select class="form-control btnAlign" name="campus" id="campus" required>
							<c:forEach items="${campus}" var="c">
									<option value="${c.getId()}"  ${c.getId() eq campusId ? 'selected' : ''} >${c.getName()}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-md-12 btnAlign espaceBtn">
						<button type="submit" class="btn btn-primary">Update</button>
					</div>
				</div>
			
			</form>
		</div>
		
		<jsp:include page="/WEB-INF/headfoot/footer.jsp"/>
	
	</div>
</body>
</html>