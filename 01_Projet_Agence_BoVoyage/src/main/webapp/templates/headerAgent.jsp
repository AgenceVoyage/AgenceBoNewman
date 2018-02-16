<!DOCTYPE html >

<html>

<head>
<title>Barre Menu</title>
<head>
<body>
	<div style="margin-left: 10%; margin-right: 10%">

		<nav class="navbar navbar-inverse" style="background-color: #d6caa9">
			<div class="container-fluid">
				<ul class="nav navbar-nav">

					<li><a
						href="${pageContext.request.contextPath}/agent/accueilAgent"><img
							src="${pageContext.request.contextPath}/resources/images/agenceLACEME2.png"
							style="height: 50px" /></a></li>

					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#"
						style="color: Teal; font-style: blod">Voyage<span
							class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a
								href="${pageContext.request.contextPath}/agent/ajouter"
								style="color: Teal; font-style: blod">Ajouter</a></li>
							<li><a
								href="${pageContext.request.contextPath}/voyage/supprimer"
								style="color: Teal; font-style: blod">Supprimer</a></li>
						</ul></li>


					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#"
						style="color: Teal; font-style: blod">Assurances <span
							class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a
								href="${pageContext.request.contextPath}/assurances/liste"
								style="color: Teal; font-style: blod">Afficher toutes les
									Assurances</a></li>
							<li><a
								href="${pageContext.request.contextPath}/assurances/afficheAjoutAssurance"
								style="color: Teal; font-style: blod">Ajouter</a></li>
							<li><a
								href="${pageContext.request.contextPath}/assurances/afficheRecherche"
								style="color: Teal; font-style: blod">Rechercher</a></li>
						</ul></li>

					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#"
						style="color: Teal; font-style: blod">Voitures <span
							class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a
								href="${pageContext.request.contextPath}/voitures/liste"
								style="color: Teal; font-style: blod">Afficher toutes les
									Voitures</a></li>
							<li><a
								href="${pageContext.request.contextPath}/voitures/afficheAjoutVoiture"
								style="color: Teal; font-style: blod">Ajouter</a></li>
							<li><a
								href="${pageContext.request.contextPath}/voitures/afficheRecherche"
								style="color: Teal; font-style: blod">Rechercher</a></li>
						</ul></li>

					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#"
						style="color: Teal; font-style: blod">Hotels <span
							class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a
								href="${pageContext.request.contextPath}/hotels/liste"
								style="color: Teal; font-style: blod">Afficher tous les
									hotels</a></li>
							<li><a
								href="${pageContext.request.contextPath}/hotels/afficheAjoutHotel"
								style="color: Teal; font-style: blod">Ajouter</a></li>
							<li><a
								href="${pageContext.request.contextPath}/hotels/afficheRecherche"
								style="color: Teal; font-style: blod">Rechercher</a></li>
						</ul></li>

					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#"
						style="color: Teal; font-style: blod">Dossiers <span
							class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a
								href="${pageContext.request.contextPath}/agent/liste"
								style="color: Teal; font-style: blod">Afficher tous les
									dossiers</a></li>
							
						</ul></li>

					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#"
						style="color: Teal; font-style: blod">Clients <span
							class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a
								href="${pageContext.request.contextPath}/agent/clients/liste"
								style="color: Teal; font-style: blod">Afficher tous les
									clients</a></li>
							<li><a
								href="${pageContext.request.contextPath}/agent/clients/afficheRecherche"
								style="color: Teal; font-style: blod">Rechercher</a></li>
						</ul></li>

					<li><a
						href="${pageContext.request.contextPath}/infosAgence?pNum=0"
						style="color: Teal; font-style: blod">Notre agence</a></li>

					<li><a href="${pageContext.request.contextPath}/deconnexion"
						style="color: Teal; font-style: blod">Deconnexion</a></li>



				</ul>
			</div>
		</nav>


	</div>

</body>

</html>