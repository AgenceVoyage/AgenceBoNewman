console.log("dossier js")

function affiche() {
	var prest = document.getElementById('idPrest').value;

	$("#avion").addClass("cacher");
	$("#hotel").addClass("cacher");
	$("#voiture").addClass("cacher");
	$("#voiture1").addClass("cacher");
	$("#hotel1").addClass("cacher2");
	$("#tout1").addClass("cacher2");

	if (prest == "avion") {
		$("#avion").removeClass("cacher");
		$("#hotel").addClass("cacher");
		$("#voiture").addClass("cacher");
		$("#voiture1").addClass("cacher");
		$("#hotel1").addClass("cacher2");
		$("#tout1").addClass("cacher2");
	}

	if (prest == "avion+hotel") {
		$("#hotel").removeClass("cacher");
		$("#hotel1").removeClass("cacher2");
		$("#avion").addClass("cacher");
		$("#voiture").addClass("cacher");
		$("#voiture1").addClass("cacher");
		$("#tout1").addClass("cacher2");
	}

	if (prest == "avion+voiture") {
		$("#voiture").removeClass("cacher");
		$("#voiture1").removeClass("cacher");

		$("#hotel").addClass("cacher");
		$("#avion").addClass("cacher");
		$("#hotel1").addClass("cacher2");
		$("#tout1").addClass("cacher2");
	}

	if (prest == "avion+hotel+voiture") {
		$("#hotel").removeClass("cacher");
		$("#voiture").removeClass("cacher");
		$("#tout1").removeClass("cacher2");

		$("#avion").addClass("cacher");
		$("#hotel1").addClass("cacher2");
		$("#voiture1").addClass("cacher");

	}

}

function affiche2() {
	var prest = document.getElementById('idPrest').value;
	var formule = document.getElementById('idFormule').value;

	if (prest == "avion+hotel") {
		if (formule == "hebergement seul") {
			$("#hotel1").removeClass("cacher2");
			$("#hotel2").addClass("cacher2");
			$("#hotel3").addClass("cacher2");
			$("#hotel4").addClass("cacher2");
		}

		if (formule == "petit dejeuner") {
			$("#hotel2").removeClass("cacher2");
			$("#hotel1").addClass("cacher2");
			$("#hotel3").addClass("cacher2");
			$("#hotel4").addClass("cacher2");
		}

		if (formule == "demi pension") {
			$("#hotel3").removeClass("cacher2");
			$("#hotel2").addClass("cacher2");
			$("#hotel1").addClass("cacher2");
			$("#hotel4").addClass("cacher2");
		}

		if (formule == "pension complete") {
			$("#hotel4").removeClass("cacher2");
			$("#hotel2").addClass("cacher2");
			$("#hotel1").addClass("cacher2");
			$("#hotel3").addClass("cacher2");
		}
	}
	
	if(prest=="avion+hotel+voiture") {
		if (formule == "hebergement seul") {
			$("#tout1").removeClass("cacher2");
			$("#tout2").addClass("cacher2");
			$("#tout3").addClass("cacher2");
			$("#tout4").addClass("cacher2");
		}

		if (formule == "petit dejeuner") {
			$("#tout2").removeClass("cacher2");
			$("#tout1").addClass("cacher2");
			$("#tout3").addClass("cacher2");
			$("#tout4").addClass("cacher2");
		}

		if (formule == "demi pension") {
			$("#tout3").removeClass("cacher2");
			$("#tout2").addClass("cacher2");
			$("#tout1").addClass("cacher2");
			$("#tout4").addClass("cacher2");
		}

		if (formule == "pension complete") {
			$("#tout4").removeClass("cacher2");
			$("#tout2").addClass("cacher2");
			$("#tout1").addClass("cacher2");
			$("#tout3").addClass("cacher2");
		}
	}

}