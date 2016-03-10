<!DOCTYPE HTML>
<html>
<%@page import="java.util.*"%>
<head>
<meta charset="ISO-8859-1">
<title>Afspraak maken</title>
<script src="/ThemaOpdracht4/js/javascript.js"></script>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
<script>
	$(function() {
		$("#datepicker").datepicker();
	});
</script>
<link rel="stylesheet" type="text/css" href="/ThemaOpdracht4/css/stylesheet.css">
</head>
<body>
	<jsp:include page="/incl/headerboven.jsp" />
	<jsp:include page="/incl/headerzijkant.jsp" />
	<div id ="standaardopmaak">

	<%
		Object msgs = request.getAttribute("msgs");
		if (msgs != null) {
			out.println("<br>" + msgs);
		}
	%>

	<button id="gaterug" onClick="window.location='/ThemaOpdracht4/web/secure/index-dynamic.jsp'">Ga terug</button>
	<div id="menu">
		<form action="/ThemaOpdracht4/AfspraakMaken.do" method="post">
			<p>
				Datum: <input type="text" id="datepicker" name="datum" value="${param.datum}" />
			</p>
						
			Afspraaktype:
			<select name="selectTypeKlus">
			<option value ="onderhoudsbeurt">Onderhoudsbeurt (3uur)</option>
			<option value ="apk keuring">Apk keuring (1uur)</option>
			</select>
			
			<br>
			
			<%
				Object datearray = request.getAttribute("datearray");
				if (datearray != null) {
					
					ArrayList<Date> alleDates = (ArrayList<Date>) datearray;
					out.println("<br/>");
					out.println("<select name=\"selectBegin\">");

					for (Date d : alleDates) {
						int uur = d.getHours();
						int minuut = d.getMinutes();
						int secondes = d.getSeconds();

						out.println("<option value=\"" + uur + ":" + minuut + ":"
								+ secondes + "\">" + uur + ":" + minuut + ":"
								+ secondes + "</option>");
					}
					out.println("</select>");
					out.println("<br/>");
				}
			%>

			<input type="submit" value="Doorgaan" /> <br />

		</form>

	</div>
	</div>
</body>
</html>