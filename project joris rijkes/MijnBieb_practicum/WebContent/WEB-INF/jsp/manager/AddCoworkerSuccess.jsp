<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>

<head>
<title>Account aangemaakt</title>
<link rel="stylesheet" type="text/css" href="../css/bieb.css" />
</head>

<body>
	<h4>Account aangemaakt.</h4>

	<hr />

	Het account voor medewerker
	<s:property value="username" />
	is aangemaakt.

	<hr />

	<a href=" <s:url action="ManagerMenu" namespace="/manager" /> ">Het
		menu voor managers</a>
	<br>
	<a href=" <s:url action="AddCoworkerForm" namespace="/manager" /> ">Nog
		een medewerker toevoegen</a>
	<br>
</body>
</html>