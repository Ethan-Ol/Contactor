<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New contact</title>

 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">

 <script>
$(function() {
$( "#datepicker" ).datepicker();
});
</script>

</head>
<body>

<form method="post" action="addContact" >
		<table>
			<tr>
				<td>
					Prenom :
				</td>
				<td>
					<input type="text" style="width: 185px;" 
                                              maxlength="30" name="prenom" id="name" />
				</td>
			</tr>
			<tr>
				<td>
					Nom :
				</td>
				<td>
					<input type="text" style="width: 185px;" 
                                              maxlength="30" name="nom" id="name" />
				</td>
			</tr>
			<tr>
				<td>
					Email :
				</td>
				<td>
					<input type="text" style="width: 185px;" 
                                            maxlength="30" name="email" id="email" />
				</td>
			</tr>
			<tr>
				<td>
					Date de naissance :
				</td>
				<td>
				<input type="text" id="datepicker" style="width: 185px;" 
                                              maxlength="30" name="date">
				</td>
			</tr>
		</table>
		<input type="submit" class="save" title="Save" value="Save" />
	</form>

</body>
</html>