window.onload = function() {
	initMap();
};

function initMap() {
	// variable de localisation
	var adaming = {
		lat : 47.2134338,
		lng : -1.5616846
	};
	var map = new google.maps.Map(document.getElementById('map'), {
		zoom : 6,
		center : adaming
	});
	// text infos bulle
	var contentString = '<div id="content">'
			+ '<div id="siteNotice">'
			+ '</div>'
			+ '<h1 id="firstHeading" class="firstHeading">Agence LACEME</h1>'
			+ '<div id="bodyContent">'
			+ '<img src="resources/images/agenceLACEME.png" alt="" style="height:120px"/>'
			+ '<br/>' + '<br/>' + '<p>24 rue Crebillon</p>'
			+ '<p>44000 Nantes</p>' + '<p>France</p>' + '</div>' + '</div>';
	// affichage de l'info
	var infowindow = new google.maps.InfoWindow({
		content : contentString
	});
	// ajout du marqueur
	var marker = new google.maps.Marker({
		position : adaming,
		map : map,
		title : 'Agence'
	});
	// clic de l'info bulle
	marker.addListener('click', function() {
		infowindow.open(map, marker);
	});
}