<%@page import="com.google.appengine.api.mail.MailService.Message"%>
<%@ page
	import="com.contactor.model.Contact,
			com.contactor.model.Adresse,
			com.contactor.services.ServiceAdresse,
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
<title>Contactor</title>

<link href="resources/foundation-essentials-5.4.6/css/foundation.css" rel="stylesheet">
<link href="resources/foundation-essentials-5.4.6/css/docs.css" rel="stylesheet">
<link href="resources/main.css" rel="stylesheet">
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
	<h3>Nouveau contact</h3>
		<form method="post" action="addContact">

			Prenom : <input type="text" style="width: 185px;" maxlength="30" name="prenom" id="name" /> 
			<span class="msg error errorPrenom">prenom non valide</span>
			Nom : <input type="text" style="width: 185px;" maxlength="30" name="nom" id="name" /> 
			<span class="msg error errorNom">nom non valide</span>
			Email: <input type="text" style="width: 185px;" maxlength="30" name="email" id="email" /> 
			<span class="msg error errorMail">Adresse mail non valide</span>
			Date de naissance : <input type="text" id="datepicker" style="width: 185px;"maxlength="10" name="date">
			<span style="color: red" class="msg verify">Veuillez v&eacute;rifier tous vos champs</span>
			<input type="submit" class="save button" title="Save" value="Ajouter" />
		</form>

	</div>
		
	<div style="margin-top: 120px">
	
			<div class="div-title" style="width: 100%;">
			<form action="/search" method="post">
			<div style="margin: 5dp;">
					<table align="center" style="border:none;border-collapse:collapse">
						<tr>
							<td style="padding:0;">
							<input style="width:250px" type="text" name="svalue" id="search-bar" placeholder="pr&eacute;nom, nom, mail"/></td>
							<td style="padding:0;">
							<input type="submit" class="save button" title="Rechercher" value="Search"/></td>
						</tr>
					</table>
				</div>
			</form>
		</div>
			
	<%
		List<Contact> contactsList = (List<Contact>) request.getAttribute("contactList");
		SimpleDateFormat frenchDate = null;
		frenchDate = new SimpleDateFormat("dd/MM/yyyy");
		
		if (!contactsList.isEmpty()) {
	
	%>
  
	<table  align="center">
		<thead>
			<tr>
				<th></th>
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
					String date = "";
					if(contact.getDate_naissance()!=null){
						try{
						date = frenchDate.format(contact.getDate_naissance());
						}
						catch (Exception e){
							date = "inconnue";
						}
					}
					else{
						date = "inconnue";
					}
			%>
			
			<tr>
				<td><a class="detailsContact-popup-link" href="#detailsContact-popup"> <i class="fi-magnifying-glass"></i></a></td>
				<td><%=contact.getPrenom()%></td>
				<td><%=contact.getNom()%></td>
				<td><%=date%></td>
				<td><%if(contact.isActif())  %> <i class="fi-check"></i><%else%> <i class="fi-x"></i></td>
				<td><a href="mailto:<%=contact.getEmail()%>"><%=contact.getEmail()%></a></td>
				<td><a href="/editContactForm?id=<%=contact.getId()%>"> <i class="fi-pencil"></i></a></td>
				
			</tr>
			
			
		<div id="detailsContact-popup" class="mfp-hide white-popup">
		
		<h3><%=contact.getPrenom()%> <%=contact.getNom()%></h3>
		
			Email: <%=contact.getEmail()%> <br/>
			Date de naissance : <%=frenchDate.format(contact.getDate_naissance())%> <br/>
			Actif: <%if(contact.isActif())  %> <i class="fi-check"></i><%else%> <i class="fi-x"></i> <br/>
			Adresses :  
				<%
					List<Adresse> adrList = ServiceAdresse.getAdresses(contact);
					
					if (!adrList.isEmpty()) {
						for (Adresse adr : adrList){
				
				%>
				<p><%=adr.toString() %></p>
				<% }} %>
				
		</div>

			<%
				}
			
		%>
		</tbody>
			</table>
		<% 
		}
		
		else {
			String msg = (String)request.getAttribute("msgEmptyList");
			if(msg == null)
				
					msg = "";
			%>

			<h2 style="margin-top: 20dp; text-align: center"><%=msg%></h2>

			<%
			}
			%>
	</div>
		
	<div style="margin-top: 5%; text-align: center">
		<a class="addContact-popup-link button" href="#addContact-popup">Ajouter
			un nouveau contact</a>
	</div>
</body>
</html>