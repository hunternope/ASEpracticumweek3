<!DOCTYPE HTML>
<html>
<%@page import="java.util.*"%>
<head>
<link rel="stylesheet" href="/resources/demos/style.css" />
<%@page import="nl.themaopdracht4.werkplaatsdomein.*"%>
<meta charset="ISO-8859-1">
<title>Modelbouw</title>
<link rel="stylesheet" type="text/css" href="stylesheet.css">
</head>
<body>
	<jsp:include page="/incl/headerboven.jsp" />
	<jsp:include page="/incl/headerzijkant.jsp" />
	<div id ="standaardopmaak">

<div id="logo2">
<img id="logo2" src="http://www.vlieland.net/wp-content/uploads/300px-nederlands_verkeersbord_e4.svg_2.png" alt="parkeerplaats-logo"/>
</div>
<%
		Object erorr1 = request.getAttribute("erorr1");
	
%>
		<form action="/ThemaOpdracht4/ParkeerplaatsServlet.do" method="post">
				<%
			Object error = request.getSession().getAttribute("userObject");
			if(error != null) {
			User u = (User)error;	
			out.println("Beste "+u.getUsername()+",");
			}
			else {
			out.println("Geen gebruiker");
			}
			%>
		<p>U kunt een parkeerplaats reserveren voor een hele dag(8:00 t/m 17:00).</br>
		De reservering kunnen alleen plaats vinden in het jaar 2013 of 2014.<p>
		<input class="ltf" type="text" name="datum" placeholder="DD-MM-JJJJ" />
		<div id=meldingen><%if (erorr1 != null) {out.println(erorr1+"</br>");}%></div>
		</br>
			
			<input type="submit" value="Reserveren" /> <br />

			

		</form>







	</div>
	</div>
</body>
</html>