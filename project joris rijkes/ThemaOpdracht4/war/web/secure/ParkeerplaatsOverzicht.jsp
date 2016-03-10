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
<% Object user = request.getSession().getAttribute("userObject"); if(user instanceof Klant) { %> <jsp:include page= "/incl/headerboven.jsp"/> <jsp:include page= "/incl/headerzijkant.jsp"/><% } %>
<% Object user1 = request.getSession().getAttribute("userObject"); if(user1 instanceof Monteur) { %> <jsp:include page= "/incl/headerboven medewerker.jsp"/> <jsp:include page= "/incl/headerzijkant medewerker.jsp"/><% } %>
<% Object user2 = request.getSession().getAttribute("userObject"); if(user2 instanceof Beheerder) { %> <jsp:include page= "/incl/headerbovenbeheerder.jsp"/> <jsp:include page= "/incl/headerzijkantbeheerder.jsp"/><% } %>		
	<div id ="standaardopmaak">

<div id="logo2">
<img id="logo2" src="http://www.vlieland.net/wp-content/uploads/300px-nederlands_verkeersbord_e4.svg_2.png" alt="parkeerplaats-logo"/>
</div>
<h2>Reservering Overzicht:</h2>
	<%
		Object melding1 = request.getAttribute("melding1");
%>
<div id=meldingeng><%if (melding1 != null) {out.println(melding1+"</br>");}%></div>
	<div id ="borderoverzicht">
		
				<%
				Object u = request.getSession().getAttribute("userObject");
				if(u != null) {
					User deUser = (User)u;
					String usernaam = deUser.getUsername();
					
					ServletContext sct = request.getSession().getServletContext();
					Object o = sct.getAttribute("hetBedrijf");
					Bedrijf hetBedrijf = (Bedrijf)o;
					ArrayList<Parkeerplaats> alleParkeerplaatsen = hetBedrijf.getAlleParkeerplaatsen();
					
					if(deUser instanceof Klant) {
						for(Parkeerplaats p : alleParkeerplaatsen) {
							ArrayList<ParkeerplaatsReservering> alleReserveringen = p.getAlleParkeerplaatsReserveringen();
							if (alleReserveringen != null) {
								for (ParkeerplaatsReservering pr : alleReserveringen) {
									if(pr.getUsername().equals(usernaam)) {
										out.println("Parkeerplaats " + p.getNummer() + " is gereserveerd op " + pr.getDatum() + "<br>");
									}
				
								} 
							}	
						}
					}
					else {
						for(Parkeerplaats p : alleParkeerplaatsen) {
							ArrayList<ParkeerplaatsReservering> alleReserveringen = p.getAlleParkeerplaatsReserveringen();
							for (ParkeerplaatsReservering pr : alleReserveringen) {
								out.println("Parkeerplaats " + p.getNummer() + " is gereserveerd op " + pr.getDatum() + " door " + pr.getUsername());
							}
						}	
					}
					
					
					
					
				}
				else {
					out.println("Geen gebruiker is ingelogd");
				}
				
			%>
	</div>
	<h2>Reservering Verwijderen:</h2><br>
	Datum:<input class="ltf" type="text" name="Datum" />
	</div>
	
	
</body>
</html>