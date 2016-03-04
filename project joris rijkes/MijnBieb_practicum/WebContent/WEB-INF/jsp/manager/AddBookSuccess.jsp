<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>

<head>
<title>Boek aangemaakt</title>
<link rel="stylesheet" type="text/css" href="../css/bieb.css" />
</head>

<body>
	<h4>Boek aangemaakt.</h4>

	<hr />

	Het boek ´<s:property value="titel" />´
	is aangemaakt.

	<hr />

	<a href=" <s:url action="ManagerMenu" namespace="/manager" /> ">Het
		menu voor managers</a>
	<br>
	<a href=" <s:url action="AddBookForm" namespace="/manager" /> ">Nog
		een boek toevoegen</a>
	<br>
</body>
</html>