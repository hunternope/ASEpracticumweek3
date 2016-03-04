<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>

<head>
<title>Een boek aanmaken</title>
<link rel="stylesheet" type="text/css" href="../css/bieb.css" />
</head>

<body>
	<h4>Een boek aanmaken.</h4>

	<hr />

	<s:form action="AddBook">
		<s:textfield name="id" label="Id" />
		<s:textfield name="titel" label="Titel" />
		<s:textfield name="auteur" label="Auteur" />
		<s:textfield name="isbn" label="ISBN" />
		<s:textfield name="pages" label="Pages" />

		<s:submit value="maak boek aan" />
	</s:form>

	<hr />

	<a href=" <s:url action="ManagerMenu" namespace="/manager" /> ">Het
		menu voor managers</a>
</body>
</html>
