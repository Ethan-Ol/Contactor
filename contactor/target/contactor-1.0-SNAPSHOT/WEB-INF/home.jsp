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
	</tbody>
</table>

	
	<div>
 	 <a href="addContactPage" class="button">Ajouter un nouveau contact</a>
 	 </div>
</body>
</html>