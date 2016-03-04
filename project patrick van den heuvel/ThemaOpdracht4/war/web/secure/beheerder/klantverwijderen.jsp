<!DOCTYPE HTML>
<html>
<%@page import="nl.themaopdracht4.werkplaatsdomein.*"%>
<%@page import="java.util.*"%>
<head>
<link rel="stylesheet" type="text/css" href="stylesheet.css">
<meta charset="ISO-8859-1">	
<title>Klant verwijderen</title>

</head>
<body>

		
		<jsp:include page= "/incl/headerzijkantbeheerder.jsp"/>
		<jsp:include page= "/incl/headerbovenbeheerder.jsp"/>
		
		
	
<div id ="standaardopmaak">
<form action="/ThemaOpdracht4/KlantVerwijderenServlet.do" method="post">

<h1>Klant verwijderen</h1>
<%
Object msgs = request.getAttribute("msgs");
if (msgs != null) {
out.println(msgs);
}
%>
<h4>Selecteer de klant</h4>

<%

ServletContext sc = getServletContext();	
Bedrijf hetBedrijf = (Bedrijf) sc.getAttribute("hetBedrijf");
ArrayList<Klant> alleKlanten = hetBedrijf.getAlleKlanten();

if (alleKlanten != null && alleKlanten.size() > 0) {

	out.println("<select name =\"selectedKlant\">");
	for (Klant k : alleKlanten) {
	out.println("<option value =\""+ k.getGebruikersNaam() +"\">" + k.getNaam() + "   (" + k.getGebruikersNaam() + ")" + "</option>");
	}
	out.println("</select>");
	

} else { 
	out.println("<select>");
	out.println("<option value=\"\">Geen klanten</option>");
	out.println("</select>");
}

%>
<br/><br/>

<input type="submit"  name="button" value="Verwijderen" />
</form>

</div>

</body>
</html>







</body>


