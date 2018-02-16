<!DOCTYPE html >

<html>

<head>
<title>Barre Menu</title>
<head>
<body>
	<div style="margin-left: 15%; margin-right: 15%">
		<nav class="navbar navbar-inverse" style="background-color: #d6caa9;">
			<div class="container-fluid">
				<ul class="nav navbar-nav">

					<li><a
						href="${pageContext.request.contextPath}/utilisateur/accueil"
						style="color: Teal; font-style: bold"><img
							src="${pageContext.request.contextPath}/resources/images/agenceLACEME2.png"
							style="height: 50px" /></a></li>

					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#"
						style="color: Teal; font-style: blod">Continents <span
							class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a
								href="${pageContext.request.contextPath}/listeVoyageParCont?pContinent=All&pNum=2"
								style="color: Teal; font-style: bold">Tous les voyages</a></li>
							<li role="separator" class="divider" style="color: grey"></li>
							<li><a
								href="${pageContext.request.contextPath}/listeVoyageParCont?pContinent=Europe&pNum=2"
								style="color: Teal; font-style: blod">Europe</a></li>
							<li><a
								href="${pageContext.request.contextPath}/listeVoyageParCont?pContinent=Afrique&pNum=2"
								style="color: Teal; font-style: blod">Afrique</a></li>
							<li><a
								href="${pageContext.request.contextPath}/listeVoyageParCont?pContinent=Asie&pNum=2"
								style="color: Teal; font-style: blod">Asie</a></li>
							<li><a
								href="${pageContext.request.contextPath}/listeVoyageParCont?pContinent=Oceanie&pNum=2"
								style="color: Teal; font-style: blod">Oceanie</a></li>
							<li><a
								href="${pageContext.request.contextPath}/listeVoyageParCont?pContinent=Amerique&pNum=2"
								style="color: Teal; font-style: blod">Amerique</a></li>
							<li><a
								href=" ${pageContext.request.contextPath}/listeVoyageParCont?pContinent=Groenland&pNum=2"
								style="color: Teal; font-style: blod">Groenland</a></li>
						</ul></li>

					<li><a
						href="${pageContext.request.contextPath}/utilisateur/client/afficheAjout"
						style="color: Teal; font-style: blod">Inscription Client</a></li>
					<li><a
						href="${pageContext.request.contextPath}/client/accueilClient"
						style="color: Teal; font-style: blod">Connexion Client</a></li>
					<li><a
						href="${pageContext.request.contextPath}/agent/accueilAgent"
						style="color: Teal; font-style: blod">Connexion Agent</a></li>

					<li><a
						href="${pageContext.request.contextPath}/infosAgence?pNum=2"
						style="color: Teal; font-style: blod">Notre agence</a></li>

				</ul>
			</div>
		</nav>
	</div>
</body>

</html>