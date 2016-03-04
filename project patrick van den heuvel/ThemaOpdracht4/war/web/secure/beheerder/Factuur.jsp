<!DOCTYPE HTML>
<html>
<%@page import="nl.themaopdracht4.werkplaatsdomein.*"%>
<%@page import="java.util.*"%>
<head>
<link rel="stylesheet" type="text/css" href="/ThemaOpdracht4/css/stylesheet.css">
<meta charset="ISO-8859-1">	
<title>Factuur</title>

</head>
<body>

		
		<jsp:include page= "/incl/headerzijkantbeheerder.jsp"/>
		<jsp:include page= "/incl/headerbovenbeheerder.jsp"/>
		
	
<div id ="standaardopmaak">
<form action="/ThemaOpdracht4/FactuurServlet.do" method="post">


<%
Object msgs = request.getAttribute("msgs");
if (msgs != null) {
out.println(msgs);
}
%>

<h1>Factuur generator</h1>
Selecteer eerst de kluscode 
Klik daarna op genereer factuur 
Er wordt een email-bericht gestuurd naar de klant met daarin de factuur 

<h4>Selecteer de kluscode</h4>	
	

<%

ServletContext sct = request.getSession().getServletContext();
Bedrijf hetBedrijf = (Bedrijf)sct.getAttribute("hetBedrijf");
Object o = request.getSession().getAttribute("userObject");
Beheerder deBeheerder = (Beheerder)o;

ArrayList<Tijdvak> deTijdvakken = hetBedrijf.getalleBeheerdersTijdvakken();

if (deTijdvakken != null && deTijdvakken.size() > 0) {

	out.println("<select name =\"selectedKlus\">");
	for (Tijdvak t : deTijdvakken) {
	if (t.getKlus() != null) 
	out.println("<option value =\""+ t.getKlus().getKlusCode() +"\">" + t.getDatumString() + " " + t.getBeginTijdString() + "-" + t.getEindTijdString() + " " + t.getKlus().getAuto().getKenteken() + "</option>");
	}
	out.println("</select>");

} else { 
	out.println("<select>");
	out.println("<option value=\"\">Geen klus beschikbaar</option>");
	out.println("</select>");
}



%>




<input type="submit"  name="button" value="Genereer factuur" />
</form>
</div>

</body>
</html>



