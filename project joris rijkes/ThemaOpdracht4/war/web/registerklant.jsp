<!DOCTYPE HTML>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/ThemaOpdracht4/css/stylesheetloginregister.css">
</head>
<body>
<form action="/ThemaOpdracht4/RegistrerenServlet.do" method="post">
<div id="logo">
		<img id="logo" src="http://i1165.photobucket.com/albums/q583/MrSvennie/atd-logo_zps6f58cc8f.jpg" alt="ATD-logo"/>
</div>
<div id= "midden" >
<h3>Maak een Nieuw ATD-account:</h3>

<%
Object msgs = request.getAttribute("msgs");
if (msgs != null) {
out.println(msgs);
}
Object rerror = request.getAttribute("rerror");
Object rerror1 = request.getAttribute("rerror1");
Object rerror2 = request.getAttribute("rerror2");
Object rerror3 = request.getAttribute("rerror3");
Object rerror4 = request.getAttribute("rerror4");
Object rerror5 = request.getAttribute("rerror5");
Object rerror6 = request.getAttribute("rerror6");
Object rerror7 = request.getAttribute("rerror7");
Object rerror8 = request.getAttribute("rerror8");
Object rerror9 = request.getAttribute("rerror9");
%>
<div id="borderreg">
<h4>Klant Gegevens:</h4>

<table cellpadding="0" cellspacing="0" border="0">
	<tr>
<td>Gebruikersnaam:</td><td><input class="ltf" type="text" name="username" value="${param.username}" /></td><td>[lengte 5,14]</td><div id=meldingen><%if (rerror != null) {out.println(rerror+"</br>");}%></div>
	</tr>
<td>Wachtwoord:</td><td><input class="ltf" type="password" name="password1" /></td><td>[lengte 5,14]</td><div id=meldingen><%if (rerror1 != null) {out.println(rerror1+"</br>");}%></div>
	</tr>
	<tr>
<td>Nogmaals uw Wachtwoord:</td><td><input class="ltf" type="password" name="password2" /></td><td>[lengte 5,14]</td><div id=meldingen><%if (rerror2 != null) {out.println(rerror2+"</br>");}%></div>
	</tr>
	<tr>
<td>Achternaam:</td><td><input class="ltf" type="text" name="achternaam" value="${param.achternaam}" /></td><td>[alleen letters]</td><div id=meldingen><%if (rerror8 != null) {out.println(rerror8+"</br>");}%></div>
	</tr>	
	<tr>
<td>Adres:</td><td><input class="ltf" type="text" name="address" value="${param.address}" /></td><td>[letters en cijfers]</td><div id=meldingen><%if (rerror5 != null) {out.println(rerror5+"</br>");}%></div>
	</tr>
	<tr>
<td>Woonplaats:</td><td><input class="ltf" type="text" name="woonplaats" value="${param.woonplaats}" /></td><td>[alleen letters]</td><div id=meldingen><%if (rerror9 != null) {out.println(rerror9+"</br>");}%></div>
	</tr>
	<tr>
<td>Geboortedatum:</td><td><input class="ltf" type="text" name="geb_datum" value="${param.geb_datum}" /></td><td>[dd-mm-yyyy]</td><div id=meldingen><%if (rerror7 != null) {out.println(rerror7+"</br>");}%></div>
	</tr>
	<tr>
<td>Geslacht:</td><td>
<select name="geslacht" class="ltf">
<option value="man">man</option>
<option value="vrouw">vrouw</option>
</select>
</td><td>[man of vrouw]</td><div id=meldingen><%if (rerror6 != null) {out.println(rerror6+"</br>");}%></div>
	</tr>
	<tr>
<td>E-mail:</td><td><input class="ltf" type="text" name="email1" value="${param.email1}" /></td><td>[Voorbeeld@gmail.com]</td><div id=laatstemelding><%if (rerror3 != null) {out.println(rerror3+"</br>");}%></div>
	</tr>
	<tr>
<td>E-mail bevestigen:</td><td><input class="ltf" type="text" name="email2" value="${param.email2}" /></td><td>[Voorbeeld@gmail.com]</td><div id=laatstemelding><%if (rerror4 != null) {out.println(rerror4+"</br>");}%></div>
	</tr>
	</table>
	<br/>
	
	<%
Object msgs1 = request.getAttribute("msgs1");
if (msgs1 != null) {
out.println(msgs1);
}
Object aerror = request.getAttribute("aerror");
Object aerror1 = request.getAttribute("aerror1");
Object aerror2 = request.getAttribute("aerror2");
Object aerror3 = request.getAttribute("aerror3");

%>
	<h4>Auto Gegevens:</h4>

<table cellpadding="0" cellspacing="0" border="0">
	<tr>	<td>Naam:</td>	
	<td><div id="tableopmaak"><input class="ltf" type="text" name="autonaam" value="${param.autonaam}"/></div></td><td>[Lengte 3,14]</td><div id=laatstemelding><%if (aerror != null) {out.println(aerror+"</br>");}%></div>		</tr>
	<tr>	<td>Merk:</td>				
	<td><div id="tableopmaak"><input class="ltf" type="text" name="merk" value="${param.merk}"/></div></td><td>[Lengte 3,14]</td><div id=laatstemelding><%if (aerror1 != null) {out.println(aerror1+"</br>");}%></div>			</tr>
	<tr>	<td>Kenteken:</td>			
	<td><div id="tableopmaak"><input class="ltf" type="text" name="kenteken" value="${param.kenteken}"/></div></td><td>[A1-2B-C3]</td><div id=laatstemelding><%if (aerror2 != null) {out.println(aerror2+"</br>");}%></div>	</tr>
	<tr>	<td>Brandstoftype:</td>		
	<td><div id="tableopmaak"><select name="brandstoftype">
		<option value="Diesel">Diesel</option>
		<option value="LPG">LPG</option>
		<option value="Super98">Super 98</option>
		<option value="Euro95">Euro 95</option>
	</select></div></td><div id=laatstemelding><%if (aerror3 != null) {out.println(aerror3+"</br>");}%></div>	</tr>

</table>
	<br/>
<input type="hidden" name="usertype" value="klant"/>

<div id= knoppen>
<input type="submit" name="button" value="registreren">
<a  href="/ThemaOpdracht4/web/loginpage.jsp"> Annuleren </a>
</div>
</div>
</div>
</form>
</body>
</html>

