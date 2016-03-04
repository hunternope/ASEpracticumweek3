<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>

<head>
<title>Account aangemaakt</title>
<link rel="stylesheet" type="text/css" href="../css/bieb.css" />
</head>

<body>
	<h4>Account verwijderd.</h4>

	<hr />

	Het account '
	<s:property value="username" />
	' is verwijderd.

	<hr />

	<a href=" <s:url action="ManagerMenu" namespace="/manager" /> ">Het
		menu voor managers</a>
	<br>
	<a href=" <s:url action="DeleteUserForm" namespace="/manager" /> ">Nog een gebruiker verwijderen</a>
	<br>
</body>
</html>