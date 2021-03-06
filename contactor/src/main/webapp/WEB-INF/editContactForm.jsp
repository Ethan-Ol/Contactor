<%@ page
	import="com.contactor.model.Contact,
			com.contactor.model.Adresse,
				   com.contactor.services.ServiceContact,
				   com.contactor.services.ServiceAdresse,
				   java.lang.String,
				   java.text.SimpleDateFormat,
				   java.util.Date,
				   java.util.List
					"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	SimpleDateFormat frenchDate = null;
	frenchDate = new SimpleDateFormat("dd/MM/yyyy");
	
	Integer contact_id = (Integer)request.getAttribute("id");
	Contact contact = ServiceContact.getContact(contact_id);
	
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


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edition</title>

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
	
	<div style="margin-top:120px;margin-right:auto;width:25%;margin-left:30%">
	<a class=button href="/home"> <i class="fi-arrow-left"></i> Retour</a>
	</div>
		
		<!-- Pop-up ajouter une adresse -->	
		<div id="addAddress-popup" class="mfp-hide white-popup">
		
		<h3>Nouvelle adresse</h3>
		<form method="post" action="addAddress">
		
			<input type="hidden" name="id" value="<%=contact.getId()%>">
			Num&eacute;ro: <input type="text" style="width: 185px;" maxlength="30" name="num"/> 
			Rue : <input type="text" style="width: 185px;" maxlength="30" name="rue"/> 
			Ville: <input type="text" style="width: 185px;" maxlength="30" name="ville"/> 
			Code postal : <input type="text"  style="width: 185px;" maxlength="10" name="cp" >
			
			<input type="submit" class="button" title="Edit" value="Sauvegarder" />
		</form>
		
		</div> 
	
		<div style="margin-left:auto;margin-right:auto;width:20%">

	
	<form method="post" action="editContact">

			<input type="hidden" name="id" value="<%=contact.getId()%>">
			Prenom : <input type="text" maxlength="30" name="prenom" id="name" value="<%=contact.getPrenom()%>" /> 
			<span class="msg error errorPrenom">prenom non valide</span>
			Nom : <input type="text" maxlength="30" name="nom" id="name" value="<%=contact.getNom()%>" /> 
			<span class="msg error errorNom">nom non valide</span>
			Email: <input type="email" maxlength="30" name="email" id="email" value="<%=contact.getEmail()%>" /> 
			<span class="msg error errorMail">Adresse mail non valide</span>
			Date de naissance : <input type="text" id="datepicker" maxlength="10" name="date" value="<%=date%>">
			<div class="switch tiny">
			Actif : <input id="exampleCheckboxSwitch" type="checkbox" name="actif" <%=(contact.isActif()?"checked":"")%>>
					<label for="exampleCheckboxSwitch"></label>
			</div>
			<span style="color: red" class="msg verify">Veuillez v&eacute;rifier tous vos champs</span>
			
			Adresses :  <a class="addAddress-popup-link" href="#addAddress-popup"> <i class="fi-plus"></i></a>
			<table style="width:100%">
				<tbody>
				
				<%
					List<Adresse> adrList = ServiceAdresse.getAdresses(contact);
					
					if (!adrList.isEmpty()) {
						for (Adresse adr : adrList){
				
				%>
				<tr>
				<td><%=adr.toString() %></td>
				<td><%if(contact.getAdresseLivraison()==adr.getId())  %> Livraison</td>
				<td><a href="/editAdresseForm?id=<%=""+adr.getId()+"&cid="+contact.getId()%>"> <i class="fi-pencil"></i></a></td>
				<td><a href="/deleteAdresse?id=<%=""+adr.getId()+"&cid="+contact.getId()%>"> <i class="fi-x"></i></a></td>
				</tr>
				
				<% }} %>
				
				</tbody>
			</table>
			<input type="submit" class="save button expand" title="Edit" value="Sauvegarder" />
		</form>
		
		<form method="get" action="deleteContact">
		<input type="hidden" name="id" value="<%=contact.getId()%>">
		<input type="submit" class="button alert expand" title="Delete" value="Supprimer le contact" />
		</form> 
	</div>

</body>
</html>