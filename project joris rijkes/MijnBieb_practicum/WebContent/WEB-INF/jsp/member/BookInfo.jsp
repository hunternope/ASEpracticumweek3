<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>

<head>
<title>Boekenlijst</title>
<link rel="stylesheet" type="text/css" href="../css/bieb.css" />
</head>

<body>
	<h4>Boekenlijst.</h4>

	<hr />

	<table>

		<tr class="tdcolor">
		<td>Id:</td>
			<td><s:property value="book.id" /></td>
		</tr>
		<tr class="tdcolor">
		<td>Titel:</td>
			<td><s:property value="book.titel" /></td>
		</tr>
		<tr class="tdcolor">
		<td>Auteur:</td>
			<td><s:property value="book.auteur" /></td>
		</tr>
		<tr class="tdcolor">
		<td>Isbn:</td>
			<td><s:property value="book.isbn" /></td>
		</tr>
		<tr class="tdcolor">
		<td>Pagina´s:</td>
			<td><s:property value="book.pages" /></td>
		</tr>
		<tr class="tdcolor">
		<td>Status:</td>
			<td><s:property value="book.status" /></td>
		</tr>
		<tr class="tdcolor">
		<td>Huidige lener:</td>
			<td><s:property value="book.user.name" /></td>
		</tr>
	</table>

	<hr />
	<a href="<s:url action='BookInfoForm' namespace="/member"/>">Terug naar de boekenlijst</a><br/>
	<a href=" <s:url value="MemberMenu.action"/> ">Terug naar het menu voor leden</a>
</body>
</html>
