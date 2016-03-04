<!DOCTYPE HTML>
<%@page import="nl.themaopdracht4.werkplaatsdomein.*"%>
<html>

<head>
<script src="javascript.js"></script>
<meta charset="ISO-8859-1">
<title>Modelbouw</title>
<link rel="stylesheet" type="text/css" href="/ThemaOpdracht4/css/stylesheet.css">

</head>
<body>
<div id ="Kleur">
	<div id="logo">
		<img id="logo" src="http://i1165.photobucket.com/albums/q583/MrSvennie/atd-logo_zps6f58cc8f.jpg" alt="ATD-logo"/>
	</div>
		<div id="titel">
		Hoofdmenu Klant 
	</div>
	<div id ="Kleinboven">
	<div id="log">
		<span id="logged">Ingelogd als:</span>
		
		
		<%
			Object error = request.getSession().getAttribute("userObject");
			if(error != null) {
				User u = (User)error;
			out.println(u.getUsername() + "(" + u.getNaam() + ")");
			}
			else {
			out.println("Geen gebruiker");
			}
			%>
		<br/>
		<form action="/ThemaOpdracht4/LogoutServlet.do" method="post">
			<input id="logout" type="submit" name="button" value="uitloggen"/>
		</form>
	</div>
	</div>
</div>
<div class="footer">
Haydar Yilmaz || Sven van Walderveen || Joris Rijkes || Ingo van Leeuwen
</div>
</body>

</html>