<%@ page
	import="com.contactor.model.Contact,
				   com.contactor.services.ServiceContact,
				   java.util.List,
				   java.lang.String,
				   java.text.SimpleDateFormat,
				   java.util.Date
					"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>

<link href="resources/foundation-essentials-5.4.6/css/foundation.css" rel="stylesheet">
<link href="resources/foundation-essentials-5.4.6/css/docs.css" rel="stylesheet">
<link href="resources/main.css" rel="stylesheet">
<<<<<<< HEAD
<link rel="stylesheet" href="resources/magnific-popup/magnific-popup.css">
<link rel="stylesheet" href="resources/foundation-essentials-5.4.6/foundation-icons/foundation-icons.css">


<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="resources/magnific-popup/jquery.magnific-popup.js"></script>
<script src="resources/script.js"></script>

<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>

</head>
<body>
	<div class="titre">
		<h1 style="color: white">Contactor</h1>
	</div>

<!-- Pop-up ajouter un nouveau contact -->
	<div id="addContact-popup" class="mfp-hide white-popup">
		<form method="post" action="addContact">

			Prenom : <input type="text" style="width: 185px;" maxlength="30" name="prenom" id="name" /> 
			Nom : <input type="text" style="width: 185px;" maxlength="30" name="nom" id="name" /> 
			Email: <input type="email" style="width: 185px;" maxlength="30" name="email" id="email" /> 
			Date de naissance : <input type="date" id="datepicker" style="width: 185px;" maxlength="10" name="date">

			<input type="submit" class="save button" title="Save" value="Save" />
		</form>

	</div>
	
<!-- Pop-up modifier un contact -->	
	<div id="editContact-popup" class="mfp-hide white-popup">
		<form method="post" action="addContact">

			Prenom : <input type="text" style="width: 185px;" maxlength="30" name="prenom" id="name" /> 
			Nom : <input type="text" style="width: 185px;" maxlength="30" name="nom" id="name" /> 
			Email: <input type="email" style="width: 185px;" maxlength="30" name="email" id="email" /> 
			Date de naissance : <input type="date" id="datepicker" style="width: 185px;" maxlength="10" name="date">

			<input type="submit" class="save button" title="Save" value="Save" />
		</form>

	</div>
	
	<%
		List<Contact> contactsList = ServiceContact.getContactList();
		SimpleDateFormat frenchDate = null;
		frenchDate = new SimpleDateFormat("dd/MM/yyyy");
		
		if (!contactsList.isEmpty()) {
	%>
	<div style="margin-top: 120px">
	<form action="">

	</form>
	<table  align="center">
		<thead>
			<tr>
				<th>Prenom</th>
				<th>Nom</th>
				<th>Date de naissance</th>
				<th>Actif</th>
				<th>Email</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<%
				for (Contact contact : contactsList) {
			%>
			
			<tr>
				<td><%=contact.getPrenom()%></td>
				<td><%=contact.getNom()%></td>
				<td><%=frenchDate.format(contact.getDate_naissance())%></td>
				<td><%if(contact.isActif())  %> <i class="fi-check"></i><%else%> <i class="fi-x"></i></td>
				<td><a href="mailto:<%=contact.getEmail()%>"><%=contact.getEmail()%></a></td>
				<td><a class="editContact-popup-link" href="#editContact-popup"> <i class="fi-pencil"></i></a></td>
			</tr>

			<%
				}
				} 
		else {
			%>

			<h2 style="margin-top: 120px; text-align: center">Vous n'avez aucun contact</h2>

			<%
				}
			%>
		
		<tbody>
	</table>
</div>
	<div style="margin-top: 5%; text-align: center">
		<a class="addContact-popup-link button" href="#addContact-popup">Ajouter
			un nouveau contact</a>
	</div>


=======
</head>
<body>
	<div style="text-align:center">
		<h1>Carnet de contacts</h1>
	</div>
	

	<h2>Liste des contacts</h2>


<table>
  <thead>
    <tr>
      <th width="200">Prenom Nom</th>
      <th>Date de naissance</th>
      <th width="150">Actif</th>
      <th width="150">Email</th>
    </tr>
  </thead>
  <tbody>
  <%
 
		    List<Contact> contactsList = ServiceContact.getContactList();
		    for(Contact contact : contactsList){
 
		%>
  
    <tr>
      <td><%=contact.getPrenom() + " " + contact.getNom() %></td>
      <td><%=contact.getDate_naissance().toString() %></td>
      <td><%=contact.isActif()?"actif":"inactif" %></td>
      <td><a href="#"><%=contact.getEmail() %></a></td>
       <td><a class="button" href="#">modifier</a></td>
    </tr>
  
  	<%
			}
		%>
	</table>
	
	<div>
 	 <a href="addContactPage" class="button">Ajouter un nouveau contact</a>
 	 </div>
 
>>>>>>> FETCH_HEAD
</body>
</html>