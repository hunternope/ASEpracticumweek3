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

	Het boek met id ´<s:property value="bookId" />´
	is verwijderd.

	<hr />

	<a href=" <s:url action="ManagerMenu" namespace="/manager" /> ">Het
		menu voor managers</a>
	<br>
	<a href=" <s:url action="DeleteBookForm" namespace="/manager" /> ">Nog
		een boek verwijderen</a>
	<br>
</body>
</html>