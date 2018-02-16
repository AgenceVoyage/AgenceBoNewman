<!DOCTYPE html >

<html>

<head>
<title>Barre Menu</title>
<head>
<body>
	<div style="margin-left: 19%; margin-right: 19%">
		<nav class="navbar navbar-inverse" style="background-color: #d6caa9;">
			<div class="container-fluid">
				<ul class="nav navbar-nav">

					<li><a
						href="${pageContext.request.contextPath}/client/accueilClient"><img
							src="${pageContext.request.contextPath}/resources/images/agenceLACEME2.png"
							style="height: 50px" /></a></li>

					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#"
						style="color: Teal; font-style: blod">Continents <span
							class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a
								href="${pageContext.request.contextPath}/listeVoyageParCont?pContinent=All&pNum=1"
								style="color: Teal; font-style: bold">Tous les voyages</a></li>
							<li role="separator" class="divider" style="color: grey"></li>
							<li><a
								href="${pageContext.request.contextPath}/listeVoyageParCont?pContinent=Europe&pNum=1"
								style="color: Teal; font-style: blod">Europe</a></li>
							<li><a
								href="${pageContext.request.contextPath}/listeVoyageParCont?pContinent=Afrique&pNum=1"
								style="color: Teal; font-style: blod">Afrique</a></li>
							<li><a
								href="${pageContext.request.contextPath}/listeVoyageParCont?pContinent=Asie&pNum=1"
								style="color: Teal; font-style: blod">Asie</a></li>
							<li><a
								href="${pageContext.request.contextPath}/listeVoyageParCont?pContinent=Oceanie&pNum=1"
								style="color: Teal; font-style: blod">Oceanie</a></li>
							<li><a
								href="${pageContext.request.contextPath}/listeVoyageParCont?pContinent=Amerique&pNum=1"
								style="color: Teal; font-style: blod">Amerique</a></li>
							<li><a
								href=" ${pageContext.request.contextPath}/listeVoyageParCont?pContinent=Groenland&pNum=1"
								style="color: Teal; font-style: blod">Groenland</a></li>
						</ul></li>

					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#"
						style="color: Teal; font-style: blod">Mes reservations<span
							class="caret"></span></a> 
						<ul class="dropdown-menu">
							<li><a
								href="${pageContext.request.contextPath}/client/listeDossier"
								style="color: Teal; font-style: blod">Liste de mes voyages</a></li>
							<li><a
								href="${pageContext.request.contextPath}/client/listeVoyage"
								style="color: Teal; font-style: blod">Listes de mes dossier</a></li>
						</ul></li>

					<li><a
						href="${pageContext.request.contextPath}/client/afficheModifClient"
						style="color: Teal; font-style: blod">Mes infos</a></li>


					<li><a
						href="${pageContext.request.contextPath}/infosAgence?pNum=1"
						style="color: Teal; font-style: blod">Notre agence</a></li>

					<li><a href="${pageContext.request.contextPath}/deconnexion"
						style="color: Teal; font-style: blod">Deconnexion</a></li>


				</ul>
			</div>
		</nav>


	</div>

</body>

</html>