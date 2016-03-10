<!DOCTYPE HTML>
<html>
<%@page import="nl.themaopdracht4.werkplaatsdomein.*"%>
<%@page import="java.util.*"%>

<head>
<link rel="stylesheet" type="text/css" href="/ThemaOpdracht4/css/stylesheet.css">
<link rel="stylesheet" type="text/css" href="/ThemaOpdracht4/css/stylesheetklusoverzicht.css">
<meta charset="ISO-8859-1">
<title>Overzicht klussen</title>
</head>
<body>
<% Object user = request.getSession().getAttribute("userObject"); if(user instanceof Klant) { %> <jsp:include page= "/incl/headerboven.jsp"/> <jsp:include page= "/incl/headerzijkant.jsp"/><% } %>
<% Object user1 = request.getSession().getAttribute("userObject"); if(user1 instanceof Monteur) { %> <jsp:include page= "/incl/headerboven medewerker.jsp"/> <jsp:include page= "/incl/headerzijkant medewerker.jsp"/><% } %>
<% Object user2 = request.getSession().getAttribute("userObject"); if(user2 instanceof Beheerder) { %> <jsp:include page= "/incl/headerbovenbeheerder.jsp"/> <jsp:include page= "/incl/headerzijkantbeheerder.jsp"/><% } %>	
	<script src="/ThemaOpdracht4/js/javascript.js"></script>



	<div id="standaardopmaak">

		<h3>Klus overzicht</h3>

		<div id=midden>
			<h3>Overzicht klussen</h3>

			<table>

				<%
					Object o = request.getSession().getAttribute("userObject");
					Bedrijf hetBedrijf = (Bedrijf) request.getSession().getServletContext().getAttribute("hetBedrijf");

					if (o instanceof Monteur) {
						Monteur deMonteur = (Monteur) o;
						ArrayList<Tijdvak> deTijdvakken = deMonteur.getAlleTijdvakken();
						out.println("<tr>");
						out.println("<th>Klustype</th>");
						out.println("<th>Datum</th>");
						out.println("<th>Begintijd</th>");
						out.println("<th>Eindtijd</th>");
						out.println("<th>Status</th>");
						out.println("<th>Manuur</th>");
						out.println("<th>Kenteken</th>");
						out.println("<th>Werkzaamheden</th>");
						out.println("</tr>");
						if (deTijdvakken != null && !deTijdvakken.isEmpty()) {
							for (Tijdvak t : deTijdvakken) {
								Klus k = t.getKlus();
								out.println("<tr>");
								out.println("<td>" + k.getSoortKlus() + "</td>");
								out.println("<td>" + t.getDatumString() + "</td>");
								out.println("<td>" + t.getBeginTijdString() + "</td>");
								out.println("<td>" + t.getEindTijdString() + "</td>");
								out.println("<td>" + k.getStatus() + "</td>");
								out.println("<td>" + k.getManuur() + "</td>");
								out.println("<td>" + k.getAuto().getKenteken()
										+ "</td>");
								out.println("<td>" + k.getWerkzaamheden() + "</td>");
								out.println("</tr>");
							}

						}
					}

					if (o instanceof Klant) {
						Klant deKlant = (Klant) o;
						ArrayList<Tijdvak> alleKlantTijdvakken = hetBedrijf
								.getAlleKlantTijdvakken(deKlant.getAuto().getKenteken());
						
						out.println("<tr>");
						out.println("<th>Klustype</th>");
						out.println("<th>Datum</th>");
						out.println("<th>Begintijd</th>");
						out.println("<th>Eindtijd</th>");
						out.println("<th>Status</th>");
						out.println("</tr>");
						
						if (alleKlantTijdvakken != null
								&& !alleKlantTijdvakken.isEmpty()) {
												
								for (Tijdvak t : alleKlantTijdvakken) {
									Klus k = t.getKlus();
									out.println("<tr>");
									out.println("<td>" + k.getSoortKlus() + "</td>");
									out.println("<td>" + t.getDatumString() + "</td>");
									out.println("<td>" + t.getBeginTijdString() + "</td>");
									out.println("<td>" + t.getEindTijdString() + "</td>");
									out.println("<td>" + k.getStatus() + "</td>");
									out.println("</tr>");
								}

							}else {
								out.println("Nog geen afspraken aanwezig!");
							}
						} 
					
					if (o instanceof Beheerder) {
						Beheerder deBeheerder = (Beheerder) o;
						ArrayList<Tijdvak> alleKlantTijdvakken = hetBedrijf.getalleBeheerdersTijdvakken();
						
						out.println("<tr>");
						out.println("<th>Klustype</th>");
						out.println("<th>Datum</th>");
						out.println("<th>Begintijd</th>");
						out.println("<th>Eindtijd</th>");
						out.println("<th>Status</th>");
						out.println("<th>Manuur</th>");
						out.println("<th>Kenteken</th>");
						out.println("<th>Werkzaamheden</th>");
						out.println("</tr>");
						if (alleKlantTijdvakken != null && !alleKlantTijdvakken.isEmpty()) {
							for (Tijdvak t : alleKlantTijdvakken) {
								Klus k = t.getKlus();
								out.println("<tr>");
								out.println("<td>" + k.getSoortKlus() + "</td>");
								out.println("<td>" + t.getDatumString() + "</td>");
								out.println("<td>" + t.getBeginTijdString() + "</td>");
								out.println("<td>" + t.getEindTijdString() + "</td>");
								out.println("<td>" + k.getStatus() + "</td>");
								out.println("<td>" + k.getManuur() + "</td>");
								out.println("<td>" + k.getAuto().getKenteken()
										+ "</td>");
								out.println("<td>" + k.getWerkzaamheden() + "</td>");
								out.println("</tr>");
							}

							}else {
								out.println("Nog geen afspraken aanwezig!");
							}
						} 
				%>

			</table>
		</div>
		<div id=knoppen>
			<a href="/ThemaOpdracht4/web/secure/index-dynamic.jsp"> Annuleren </a>
		</div>
		</div>
</body>

</html>


