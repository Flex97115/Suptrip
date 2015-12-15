<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Bootstrap -->
<link href="<c:url value="/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<c:url value="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"/>">
<script type="text/javascript" src="<c:url value="/bootstrap/js/bootstrap.min.js"/>"></script>

<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" role="navigation">
			<c:choose>
				<c:when test="${login != null}">
					<ul class="nav navbar-nav">
						<li><a href="/suptrip/auth/index" class="titreHeader navbar-link"><span id="sup">SUP</span><span id="trip">TRIP</span></a></li>
						<li><a href="/suptrip/auth/index">Search a trip</a></li>
						<li><a href="/suptrip/logout">Logout</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li><p class="navbar-text"><a href="/suptrip/auth/bag" class="navbar-link">Panier <span class="badge"><c:out value="${loggedUser.getBags().size()}"></c:out></span></a></p></li>
						<li><a href="/suptrip/auth/profile" class="navbar-link titreHeader"><i class="fa fa-toggle-on"></i> <c:out value="${loggedUser.getFirstName()} ${loggedUser.getLastName()}"></c:out> </a></li>
					</ul>
				</c:when>
				<c:otherwise>
					<ul class="nav navbar-nav">
						<li><a href="/suptrip/index" class="titreHeader navbar-link"><span id="sup">SUP</span><span id="trip">TRIP</span></a></li>
						<li><a href="/suptrip/login">Login</a></li>
						<li><a href="/suptrip/register">Register</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li><p class="navbar-text titreHeader2"><i class="fa fa-toggle-off"></i> Not connected</p></li>		
					</ul>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</nav>
