function searchField() {
	var field = document.getElementById('search').value;
	var elements = document.getElementsByClassName('fiche');
	var element2 = document.getElementsByClassName('pays');
	for (var i = 0, length = elements.length; i < length; i++) {
		if (field != '') {
			if (!element2[i].innerHTML.startsWith(field)) {
				elements[i].style.display = 'none';
			}
		}
		if (field == '') {
			elements[i].style.display = '';
		}
	}

}