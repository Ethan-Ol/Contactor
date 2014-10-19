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
</head>
<body>
	<h1>Page principale a modifier</h1>
 
	Function : <a href="addContactPage">Nouveau Contact</a>
<!-- 	<hr /> -->
 
	<h2>Contacts</h2>
	<table border="1">
		<thead>
			<tr>
				<td>Name</td>
				<td>Email</td>
				<td>Age</td>
				<td>Actif</td>
				<td>Editer</td>
			</tr>
		</thead>
		<%
 
		    List<Contact> contactsList = ServiceContact.getContactList();
		    for(Contact contact : contactsList){
 
		%>
			<tr>
			  <td><%=contact.getPrenom() + " " + contact.getNom() %></td>
			  <td><%=contact.getEmail() %></td>
			  <td><%=contact.getDate_naissance().toString() %></td>
			  <td><%=contact.isActif()?"actif":"inactif" %></td>
			  <td>
			  	<a href="update/<%=contact.getId() %>">Modifier</a>
			  </td>
			</tr>
		<%
			}
		%>
	</table>
 
</body>
</html>