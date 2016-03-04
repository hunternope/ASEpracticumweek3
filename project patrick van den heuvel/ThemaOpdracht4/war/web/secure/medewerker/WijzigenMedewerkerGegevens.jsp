<!DOCTYPE HTML>
<html>
<%@page import="nl.themaopdracht4.werkplaatsdomein.*"%>
<%@page import="java.util.*"%>
<head>
<link rel="stylesheet" type="text/css" href="/ThemaOpdracht4/css/stylesheet.css">
<meta charset="ISO-8859-1">	
<title>Klus</title>

</head>
<body>

		
		<jsp:include page="/incl/headerboven medewerker.jsp" />
		<jsp:include page="/incl/headerzijkant medewerker.jsp" />
		
	
<div id ="standaardopmaak">
<form action="/ThemaOpdracht4/WijzigenKlantGegevensServlet.do" method="post">

<h1>Medewerker gegevens wijzigen</h1>

<h4>Ingelogde gebruiker : </h4>

<%
Object klant = request.getSession().getAttribute("userObject");
if(klant != null) {
	User u = (User)klant;
out.println("Gebruikersnaam : " + u.getUsername() + "(" + u.getNaam() + ")");
out.println("Geboortedatum : " + u.getGeboortedatum() + " Email-adres : " +  u.getEmail());
}
else {
out.println("Geen gebruiker");
}
%>


<h5>Wijzig voornaam</h5>
<input class="ltf" type="text" name="voornaam" value="" />
<h5> Wijzig woonplaats</h5>
<input class="ltf" type="text" name="woonplaats" value="${param.woonplaats}" />
<h5>Wijzig achternaam</h5>
<input class="ltf" type="text" name="achternaam" value="${param.achternaam}" />
<h5>Wijzig email-adres</h5>
<input class="ltf" type="text" name="email" value="${param.email}" />
<h3>Wachtwoord wijzigen ?</h3>
<h5>Nieuwe wachtwoord</h5>
<input class="ltf" type="password" name="password1" value="" />
<h5>Herhaal nieuwe wachtwoord</h5>
<input class="ltf" type="password" name="password2" value="" />
<div id= knoppen>
<input type="submit"  name="button" value="Wijzig gegevens" />
</div>
</form>
</div>

</body>
</html>





