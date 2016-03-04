<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>

<head>
<title>Boek uitgeleend</title>
<link rel="stylesheet" type="text/css" href="../css/bieb.css" />
</head>

<body>
	<h4>Boek uitgeleend.</h4>

	<hr />

	Het boek ´
	<s:property value="book.titel" />
	´ is teruggenomen.

	<hr />

	<a href=" <s:url action="CoworkerMenu" namespace="/coworker" /> ">Het
		menu voor coworkers</a>
	<br>
	<a href=" <s:url action="TakeBackBookForm" namespace="/coworker" /> ">Nog
		een boek terugnemen</a>
	<br>
</body>
</html>