<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>Menu voor leden</title>
<link rel="stylesheet" type="text/css" href="../css/bieb.css" />
</head>

<body>
	<h4>
		Ingelogd: <s:property value="#session['user'].username" />.
	</h4>

	<hr />

	<ul>
		<li><a href="<s:url action='BookList' namespace="/member"/>">Alle
				boeken</a></li>
		<li><a href="<s:url action='BookInfoForm' namespace="/member"/>">Boekinformatie</a></li>
		<li><a href="<s:url action='BookReservationForm' namespace="/member"/>">Reserveer
				boek</a></li>
		<li><a href="<s:url action='CancelReservationForm' namespace="/member"/>">Annuleer
				reservering</a></li>
		<li><a href="<s:url action='MyBookList' namespace="/member"/>">Alle
				gereserveerde en geleende boeken</a></li>
		<li><a href="<s:url action='ChangePasswordForm' namespace="/member"/>">Wijzig
				wachtwoord</a></li>
		<li><a href="<s:url action='Logout' namespace="/member"/>">Log
				uit</a></li>
	</ul>

</body>
</html>
