<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>

<head>
<title>Account aangemaakt</title>
<link rel="stylesheet" type="text/css" href="../css/bieb.css" />
</head>

<body>
	<h4>Annulering voltooid.</h4>

	<hr />

	
	<s:property value="User.username" />, Uw reservering van het boek "<s:property value="Book.titel"/>" is geannuleerd.

	<hr />

	<a href=" <s:url action="MemberMenu" namespace="/member" /> ">Het
		menu voor leden</a><br>
	<a href=" <s:url action="CancelReservationForm" namespace="/member" /> ">Nog
		een reservering annuleren</a>
</body>
</html>