<%@page import="com.contactor.model.Contact"%>
<%@page import="com.contactor.model.Adresse"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<%
	Adresse adresse = (Adresse)request.getAttribute("adresse");
	Contact contact = (Contact)request.getAttribute("contact");
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edition Adresse</title>

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
	<a class=button href="/editContactForm?id=<%=contact.getId()%>"> <i class="fi-arrow-left"></i> Retour</a>
	</div>
	
	<div style="margin-left:auto;margin-right:auto;width:20%">

	<form method="post" action="editAdresse">

			<input type="hidden" name="id" value="<%=""+adresse.getId()%>">
			<input type="hidden" name="cid" value="<%=""+contact.getId()%>">
			Numero : <input type="text" maxlength="30" name="num" id="num" value="<%=adresse.getNumero()%>" /> 
			Rue : <input type="text" maxlength="30" name="rue" id="rue" value="<%=adresse.getRue()%>" /> 
			Ville : <input type="text" maxlength="30" name="ville" id="ville" value="<%=adresse.getVille()%>" /> 
			Code Postal : <input type="text" maxlength="30" name="cp" id="cp" value="<%=adresse.getCodePostal()%>" />
			Adresse de livraison : <input id="exampleCheckboxSwitch" type="checkbox" name="livraison" <%=((contact.getAdresseLivraison()==adresse.getId())?"checked":"")%>>
					<label for="exampleCheckboxSwitch"></label>
			
						 
			<input type="submit" class="button expand" title="Edit" value="Sauvegarder" />
		</form>
		 
	</div>

</body>
</html>