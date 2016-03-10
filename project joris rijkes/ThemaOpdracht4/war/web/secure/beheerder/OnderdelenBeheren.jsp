<!DOCTYPE HTML>
<html>
<%@page import="java.util.*"%>
<head>

<%@page import="nl.themaopdracht4.werkplaatsdomein.*"%>
<meta charset="ISO-8859-1">
<title>Modelbouw</title>
<link rel="stylesheet" type="text/css" href="/ThemaOpdracht4/css/stylesheet.css">
</head>
<body>


<% Object user2 = request.getSession().getAttribute("userObject"); if(user2 instanceof Beheerder) { %> <jsp:include page= "/incl/headerbovenbeheerder.jsp"/> <jsp:include page= "/incl/headerzijkantbeheerder.jsp"/><% } %>				

	<div id ="standaardopmaak">
	<h2>Onderdeel Overzicht:</h2></br>
	<%
		Object melding = request.getAttribute("melding");
		Object melding1 = request.getAttribute("melding1");
		Object geenvoorraad = request.getAttribute("geenvoorraad");
	
%>

		<form action="/ThemaOpdracht4/OnderdeelServlet.do" method="post">
				<%
			Object error = request.getSession().getAttribute("userObject");
			if(error != null && error instanceof Monteur) {
			User u = (User)error;	
			out.println("Beste "+u.getNaam()+",");
			}
			else {
			out.println("Geen gebruiker");
			}
			%>
		
		<p>Hier kunt u onderdelen toevoegen, en een overzicht opvragen van de voorraad.</p>
		<table cellpadding="0" cellspacing="0" border="0">
		<tr>
		<td>Artikel Nummer:</td><td><input  class="ltf" type="text" name="artikelNummer"    value="${param.artikelNummer}"placeholder="ArtikelNummer"  /></td>
		</tr>
		<tr>
		<td>Naam:</td><td><input class="ltf" type="text" name="Naam"   value="${param.Naam}" placeholder="ArtikelNaam"  /></td>
		</tr>
		<tr>
		<td>Aantal:</td><td><input class="ltf" type="text" name="Aantal"  value="${param.Aantal}"  placeholder="Hoeveelheid"  /></td>
		</tr>
		</table>
				<div id=meldingen><%if (melding != null) {out.println(melding+"</br>");}%></div>
				<div id=meldingeng><%if (melding1 != null) {out.println(melding1+"</br>");}%></div>
				<div id=meldingen><%if (geenvoorraad!= null) {out.println(geenvoorraad+"</br>");}%></div>
			<input type="submit" value="Toevoegen" /> <br />
		</form>
		</br>
<div class="line-separator"></div>
		<h2>Onderdeel Overzicht:</h2></br>

	<h3>ID:  Naam:  Aantal</h3>
	
		<%Object output = request.getSession().getAttribute("Onderdeel");
				if(output == null) {	
					out.println("Geen onderdelen aanwezig."); 
					}
				// is nodig zodat hij de onderdelen ophaald bij het klikken op de link.
				ServletContext sct = request.getSession().getServletContext();
				Object o = sct.getAttribute("hetBedrijf");
				Bedrijf hetBedrijf = (Bedrijf)o;
				ArrayList<Onderdeel> deOnderdelen = hetBedrijf.getAlleOnderdelen();
				String onderdeel = "";
				if (deOnderdelen != null && !deOnderdelen.isEmpty()) {
					for (Onderdeel a : deOnderdelen) {
						onderdeel += a;
					}
				}
				System.out.println(onderdeel);
				request.getSession().setAttribute("Onderdeel", onderdeel);
		
			
				%>
				<div id ="tables">
				<%
				if(output != null) {	
				out.println(output); 
		
				}%>
				</div>
	</div>
</body>
</html>