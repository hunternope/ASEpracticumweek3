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


	<jsp:include page="/incl/headerboven.jsp" />
	<jsp:include page="/incl/headerzijkant.jsp" />

	<div id="standaardopmaak">
		<form action="/ThemaOpdracht4/Tanken.do" method="post">

			<h1>Tanken</h1>
			
			<%
				Object msgs = request.getAttribute("msgs");
				if (msgs != null) {
				out.println(msgs + "<br>");
				}
			%>
			
			Pasnummer:   <input type="text" name="pasnummer" min="2" max="20" value="${userObject.klantenPas.pasNummer}"> 
			<br>
			Pascode:     <input type="password" name="pascode" min="1" max="24">
			<br>
			Benzinetype: <input type="text" name="benzinetype" min="1" max="24" value="${userObject.auto.brandstofType}">
			<br>
			Literaantal: <input type="text" min="1" max="24" value="${requestScope.liter}">
			<br>
			<input type="submit" name="button" value="Begin met tanken!">

		</form>
		
	</div>

</body>
</html>










