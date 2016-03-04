
<!DOCTYPE HTML>
<html>
<head>
<script src="/ThemaOpdracht4/js/javascript.js"></script>
<meta charset="ISO-8859-1">
<title>AutoTotaalDiensten</title>
<link rel="stylesheet" type="text/css" href="/ThemaOpdracht4/css/stylesheet.css">

</head>

<body>
<%@page import="nl.themaopdracht4.werkplaatsdomein.*"%>
<% Object user = request.getSession().getAttribute("userObject"); if(user instanceof Klant) { %> <jsp:include page= "/incl/headerboven.jsp"/> <jsp:include page= "/incl/headerzijkant.jsp"/><% } %>
<% Object user1 = request.getSession().getAttribute("userObject"); if(user1 instanceof Monteur) { %> <jsp:include page= "/incl/headerboven medewerker.jsp"/> <jsp:include page= "/incl/headerzijkant medewerker.jsp"/><% } %>
<% Object user2 = request.getSession().getAttribute("userObject"); if(user2 instanceof Beheerder) { %> <jsp:include page= "/incl/headerbovenbeheerder.jsp"/> <jsp:include page= "/incl/headerzijkantbeheerder.jsp"/><% } %>				
<div id="logovoorraad">

<SCRIPT>

//Ingo van Leeuwen Copyright
src = ["http://www.portmoodyradiators.com/resources/retail-tire-racking-storage.jpg", "http://4.bp.blogspot.com/-EJScFMuAc0Y/Tw7JLYH-XDI/AAAAAAAAATI/e05d1fJ8VXQ/s400/car-parts-shop.JPG", "http://www.stationgaragearnside.co.uk/cdata/1266/img/1266_605231.jpg", "http://belforauto1.co.nz/images/v8-cars.jpg"]

duration = 10;

ads=[]; ct=0;
function switchAd() {
var n=(ct+1)%src.length;
if (ads[n] && (ads[n].complete || ads[n].complete==null)) {
document["Ad_Image"].src = ads[ct=n].src;
}
ads[n=(ct+1)%src.length] = new Image;
ads[n].src = src[n];
setTimeout("switchAd()",duration*500);
}
onload = function(){
if (document.images)
switchAd();
}
//-->
</SCRIPT>

<IMG NAME="Ad_Image" SRC="image1.gif" BORDER=0>

</div>

<div id ="standaardopmaak">
<% Object user4 = request.getSession().getAttribute("userObject"); if(user4 instanceof Klant) { %>
<h2>Welkom  bij de website van ATD.</h2><br>
<p>In het menu weergegeven aan de zijkant van het schrem<br> kunt u uw de volgende handelingen uitvoeren:</p><br>
	<ul>
		<li><p>Een Onderhoudsafspraak maken.</p></li>
		<li><p>Uw overzicht met gemaakte afspraken weergeven.</p></li>
		<li><p>Een parkeerplaats reserveren.</p></li>
	</ul>

<% } %>
<% Object user5 = request.getSession().getAttribute("userObject"); if(user5 instanceof Monteur) { %> 
<h2>Beste Medewerker,</h2><br>
<p>In het menu weergegeven aan de zijkant van het scherm <br> kunt u uw de volgende handelingen uitvoeren:</p><br>
	<ul>
		<li><p>De actuele klussen Bekijken.</p></li>
		<li><p>Klus aanpassen.</p></li>
		<li><p>Klus Verwijderen.</p></li>
		<li><p>Voorraad beheren.</p></li>
		<li><p>Overzicht Parkeerplaatsen bekijken.</p></li>
		<li><p>Een kant verwijderen.</p></li>
	</ul>
<% } %>
<% Object user6 = request.getSession().getAttribute("userObject"); if(user6 instanceof Beheerder) { %>
 <h2>Beste Paladijn.</h2>
<p>In het menu weergegeven aan de zijkant van het scherm<br> kunt u uw de volgende handelingen uitvoeren:</p>
	<ul>
		<li><p>Een Factuur opstellen.</p></li>
		<li><p>Onderdelen beheren.</p></li>
		<li><p>Medewerker toevoegen.</p></li>
		<li><p>Medewerker verwijderen.</p></li>
		<li><p>Overzicht van BTW per onderdeel.</p></li>
		<li><p>Overzicht van BTW per transactie.</p></li>
		<li><p>Actuele klussen bekijken.</p></li>
		<li><p>Voorraad beheren.</p></li>
		<li><p>Nieuwe onderdelen bestellen.</p></li>
		<li><p>Parkeerplaatsoverzicht.</p></li>
	</ul><% } %>				

</div>
</body>
</html>