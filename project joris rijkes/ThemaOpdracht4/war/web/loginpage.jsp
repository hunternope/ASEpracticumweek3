<!DOCTYPE HTML>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/ThemaOpdracht4/css/stylesheetloginregister.css">
</head>
<body>
<div id="logo">
		<img id="logo" src="http://i1165.photobucket.com/albums/q583/MrSvennie/atd-logo_zps6f58cc8f.jpg" alt="ATD-logo"/>
</div>
<div id= "midden" >
<h2>Login ATD</h2>


<div id="account">
<form action="/ThemaOpdracht4/LogServlet.do" method="post">


<% String username = (String)request.getAttribute("cUsername");
if (username == null) request.setAttribute("cUsername", "");%>

<div id="inputbox">
<%
Object msgs = request.getAttribute("msgs");
if (msgs != null) {
out.println(msgs);
}
%>
<%
				String s = "";
				if (request.getCookies() != null) {
				for (Cookie c : request.getCookies()) {
					if (c.getName().equals("cUsername")) {
						 s = c.getValue();
					}
				}
				}			
			%>
<input class="ltf" type="text" name="username" value="<%=s%>" /><br/>
<input class="ltf" type="password" name="password" /><br/>
<input type="submit"  name="button" value="inloggen" />
<a href="/ThemaOpdracht4/web/registerklant.jsp">Registreren</a>
</div>
</form>
</div>
</div>
</body>
</html>


