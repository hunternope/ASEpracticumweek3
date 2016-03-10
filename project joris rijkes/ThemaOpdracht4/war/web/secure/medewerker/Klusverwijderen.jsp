<!DOCTYPE HTML>
<html>
<%@page import="nl.themaopdracht4.werkplaatsdomein.*"%>
<%@page import="java.util.*"%>
<head>
<link rel="stylesheet" type="text/css" href="/ThemaOpdracht4/css/stylesheet.css">
<meta charset="ISO-8859-1">	
<title>Klant verwijderen</title>

</head>
<body>

		
		<jsp:include page="/incl/headerboven medewerker.jsp" />
		<jsp:include page="/incl/headerzijkant medewerker.jsp" />
		
	
<div id ="standaardopmaak">
<form action="/ThemaOpdracht4/KlusVerwijderenServlet.do" method="post">


<%
Object msgs = request.getAttribute("msgs");
if (msgs != null) {
out.println(msgs);
}
%>

<h1>Klus verwijderen</h1>

<h4>Selecteer de klus</h4>	
	
<%

Object o = request.getSession().getAttribute("userObject");
Monteur deMonteur = (Monteur)o;
ArrayList<Tijdvak> deTijdvakken = deMonteur.getAlleTijdvakken();

if (deTijdvakken != null && deTijdvakken.size() > 0) {

	out.println("<select name =\"selectedTijdvak\">");
	for (Tijdvak t : deTijdvakken) {
	if (t.getKlus() != null)  {
	out.println("<option value =\""+ t.getKlus().getKlusCode() +"\">" + t.getDatumString() + " " + t.getBeginTijdString() + "-" + t.getEindTijdString() + " " + t.getKlus().getAuto().getKenteken() + "</option>");
	out.println("</select>");
	} else {
		out.println("<select>");
		out.println("<option value=\"\">Geen klus beschikbaar</option>");
		out.println("</select>");
	}
	}
	
	
	

} else { 
	out.println("<select>");
	out.println("<option value=\"\">Geen klus beschikbaar</option>");
	out.println("</select>");
}

%>



<input type="submit"  name="button" value="Klus verwijderen" />
</form>
</div>

</body>
</html>




