$(document).ready(function() {


	//Pop-up ajout d'un contact
	$('.addContact-popup-link').magnificPopup({ 
		type: 'inline'
	});

	//Pop-up edition d'une adresse
	$('.editAddress-popup-link').magnificPopup({ 
		type: 'inline'
	});

	//Pop-up ajout d'une adresse
	$('.addAddress-popup-link').magnificPopup({ 
		type: 'inline'
	});

	//Pop-up details d'un contact
	$('.detailsContact-popup-link').magnificPopup({ 
		type: 'inline'
	});

	//Calendrier en français
	$( "#datepicker" ).datepicker({
		altField: "#datepicker",
		closeText: 'Fermer',
		prevText: 'Pr&eacute;c&eacute;dent  ',
		nextText: 'Suivant',
		currentText: 'Aujourd\'hui',
		monthNames: ['Janvier', 'F&eacute;vrier', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Ao&ucirc;t', 'Septembre', 'Octobre', 'Novembre', 'Décembre'],
		monthNamesShort: ['Janv.', 'Févr.', 'Mars', 'Avril', 'Mai', 'Juin', 'Juil.', 'Août', 'Sept.', 'Oct.', 'Nov.', 'Déc.'],
		dayNames: ['Dimanche', 'Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi'],
		dayNamesShort: ['Dim.', 'Lun.', 'Mar.', 'Mer.', 'Jeu.', 'Ven.', 'Sam.'],
		dayNamesMin: ['D', 'L', 'M', 'M', 'J', 'V', 'S'],
		weekHeader: 'Sem.',
		dateFormat: 'dd/mm/yy'
	});

	$('.errorMail').hide();
	$('.errorPrenom').hide();
	$('.errorNom').hide();
	$('.verify').hide();

	//Vérification format adresse mail
	$('form input[name="email"]').blur(function () {
		var email = $(this).val();
		var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/igm;
		if (re.test(email)) {
			$('.msg').hide();
			$('.save').show();
		} else {
			$('.msg').hide();
			$('.errorMail').show();
			$('.verify').show();
			$('.save').hide();
		}


	});

	//Vérification format adresse mail
	$('form input[name="prenom"]').blur(function () {
		var prenom = $(this).val();
		var re = /^[a-zA-Z]+$/;
			if (re.test(prenom)) {
			$('.msg').hide();
			$('.save').show();
		} else {
			$('.msg').hide();
			$('.errorPrenom').show();
			$('.verify').show();
			$('.save').hide();
		}


	});
	
	
	//Vérification format adresse mail
	$('form input[name="nom"]').blur(function () {
		var nom = $(this).val();
		var re = /^[a-zA-Z]+$/;
			if (re.test(nom)) {
			$('.msg').hide();
			$('.save').show();
		} else {
			$('.msg').hide();
			$('.errorNom').show();
			$('.verify').show();
			$('.save').hide();
		}


	});


});




