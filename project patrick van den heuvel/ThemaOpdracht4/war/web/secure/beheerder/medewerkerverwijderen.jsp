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

		<jsp:include page= "/incl/headerzijkantbeheerder.jsp"/>
		<jsp:include page= "/incl/headerbovenbeheerder.jsp"/>
		
	
<div id ="standaardopmaak">
<form action="/ThemaOpdracht4/MedewerkerVerwijderenServlet.do" method="post">

<h1>Klant verwijderen</h1>
<%
Object msgs = request.getAttribute("msgs");
if (msgs != null) {
out.println(msgs);
}
%>
<h4>Selecteer de medewerker</h4>

<%

ServletContext sc = getServletContext();	
Bedrijf hetBedrijf = (Bedrijf) sc.getAttribute("hetBedrijf");
ArrayList<Monteur> alleMonteurs = hetBedrijf.getAlleMonteurs();

if (alleMonteurs != null && alleMonteurs.size() > 0) {

	out.println("<select name =\"selectedMonteur\">");
	for (Monteur m : alleMonteurs) {
	out.println("<option value =\""+ m.getGebruikersNaam() +"\">" + m.getNaam() + "   (" + m.getGebruikersNaam() + ")" + "</option>");
	}
	out.println("</select>");
	

} else { 
	out.println("<select>");
	out.println("<option value=\"\">Geen medewerkers</option>");
	out.println("</select>");
}

%>
<br/><br/>

<input type="submit"  name="button" value="Verwijderen" />
</form>

</div>

</body>
</html>









