<%@ page import = "com.contactor.model.Contact,
				   com.contactor.services.ServiceContact,
				   java.util.List,
				   java.lang.String
					" %>
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
<link rel="stylesheet" href="resources/magnific-popup/magnific-popup.css"> 
<link rel="stylesheet" href="resources/foundation-essentials-5.4.6/foundation-icons/foundation-icons.css"> 


<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> 
<script src="resources/magnific-popup/jquery.magnific-popup.js"></script> 
<script src="resources/script.js"></script>

 <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>

</head>
<body>
	<div id="title">
		<h1 style="color:white">Contactor</h1>
	</div>
	
	 <div id="addContact-popup" class="mfp-hide white-popup" >
 		<form method="post" action="addContact" >

		Prenom : <input type="text" style="width: 185px;" maxlength="30" name="prenom" id="name" />
		Nom : <input type="text" style="width: 185px;" maxlength="30" name="nom" id="name" />
		Email : <input type="text" style="width: 185px;" maxlength="30" name="email" id="email" />
		Date de naissance : <input type="text" id="datepicker" style="width: 185px;" maxlength="30" name="date">
		
		<input type="submit" class="save button" title="Save" value="Save" />
	</form>

	</div>
	
		 
<table style="margin-left:5%;margin-top:5%">
  <thead>
    <tr>
      <th>Prenom</th>
      <th>Nom </th>  
      <th>Date de naissance</th>
      <th>Actif</th>
      <th>Email</th>
      <th ></th>
    </tr>
  </thead>
  <tbody>
  <%
 
		    List<Contact> contactsList = ServiceContact.getContactList();
		    for(Contact contact : contactsList){
 
		%>
  
    <tr>
      <td><%=contact.getPrenom()%></td>
      <td><%=contact.getNom() %></td>
      <td><%=contact.getDate_naissance().toString() %></td>
      <td><%=contact.isActif()?"actif":"inactif" %></td>
      <td><a href="#"><%=contact.getEmail() %></a></td>
       <td><a href="#"> <i class="fi-pencil"></i></a></td>
    </tr>
  
  	<%
			}
		%>
		<tbody>
	</table>
 
 <div style="margin-left:5%"> 
 <a class="addContact-popup-link button" href="#addContact-popup">Ajouter un nouveau contact</a> </div>


</body>
</html>