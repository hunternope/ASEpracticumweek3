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
<form action="/ThemaOpdracht4/KlusAfvinkenServlet.do" method="post">

<h1>Klus afvinken</h1>

<%
Object msgs = request.getAttribute("msgs");
if (msgs != null) {
out.println(msgs);
}
%>

<h4>Selecteer de Klus</h4>

<%

Object o = request.getSession().getAttribute("userObject");
Monteur deMonteur = (Monteur)o;
ArrayList<Tijdvak> deTijdvakken = deMonteur.getAlleTijdvakken();

if (deTijdvakken != null && !deTijdvakken.isEmpty()) {

	out.println("<select name =\"selectedTijdvak\">");
	for (Tijdvak t : deTijdvakken) {
		if (t.getKlus().getStatus().equals("inbehandeling")) {
	out.println("<option value =\""+ t.getKlus().getKlusCode() +"\">" + t.getDatumString() + " " + t.getBeginTijdString() + "-" + t.getEindTijdString() + " " + t.getKlus().getAuto().getKenteken() + "</option>");
	}
	}
	out.println("</select>");
	

} else { 
	out.println("<select>");
	out.println("<option value=\"\">Geen klussen</option>");
	out.println("</select>");
}

%>

<h5>Status klus</h5>
<select name="status">
<option value = "inbehandeling">In behandeling</option>
<option value = "klusvoltooid"> klusvoltooid</option>

</select>



<h5>Werkzaamheden</h5>
<textarea name ="werkzaamheden" rows="10" cols="50">
Vul hier de werkzaamheden in.
</textarea>


<h5> Vul hier de aantal manuren in  </h5>
<input type="number" name="manuren" min="1" max="24">

<h3>Onderdelen</h3>
<select name="onderdeel">
  <option value="Band">band</option>
  <option value="Velg">velg</option>
   <option value="Airbag">Airbag</option>
    <option value="Autogordel">Autogordel</option>
     <option value="Distributieriem">Distributieriem</option>
      <option value="Driewegkatalysator">Driewegkatalysator</option>
       <option value="Bumper">Bumper</option>
        <option value="Koppaking">Koppaking</option>
         <option value="Slotbout">Slotbout</option>
          <option value="Gloeibougie">Gloeibougie</option>
           <option value="Grille">Grille</option>
            <option value="Carrosserie">Carrosserie</option>
             <option value="Radiateur">Radiateur</option>
              <option value="Ruitenwisser">Ruitenwisser</option>
               <option value="Remkrachtverdeler">Remkrachtverdeler</option>
                <option value="Wieldop">Wieldop</option>
                 <option value="Imperiaal">Imperiaal</option>
                  <option value="Brandstofmeter">Brandstofmeter</option>
                   <option value="Gordelspanner">Gordelspanner</option>
                    <option value="Alcoholslot">Alcholslot</option>
                     <option value="Automatic Crash notification">Automatic Crash notification</option>
                      <option value="Autoradio">Autoradio</option>
                       <option value="Uitlaat">Uitlaat</option>
                        <option value="Uitlaat">Uitlaat</option>
                         <option value="Uitlaat">Uitlaat</option>
                          <option value="Uitlaat">Uitlaat</option>
                           <option value="Uitlaat">Uitlaat</option>
                           
          
</select>
<h5>Aantal</h5>
<input type="number" name="aantalOnderdelen" min="1" max="24">

<input type="submit"  name="button" value="doorvoeren gegevens" />
</form>
</div>
</body>
</html>










