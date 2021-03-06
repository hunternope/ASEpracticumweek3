<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>

<head>
<title>Boekenlijst</title>
<link rel="stylesheet" type="text/css" href="../css/bieb.css" />
</head>

<body>
	<h4>Boeken innemen.</h4>

	<hr />


	<br />
	<table>
		<tr class="thcolor">
			<th>id</th>
			<th>titel</th>
			<th>status</th>
			<th>gebruiker</th>
		</tr>
		<s:iterator value="books">
			<tr class="tdcolor">
				<td><s:property value="id" /></td>
				<td><s:property value="titel" /></td>
				<td><s:property value="status" /></td>
				<td><s:property value="user.username" /></td>
			</tr>
		</s:iterator>
	</table>
	<hr />
	<s:form action="LendBook">
		<table>
			<tr>
				<s:textfield name="bookId" label="Boek Id" />
			</tr>
			<tr>
				<s:submit />
			</tr>
		</table>
	</s:form>
	<s:form action="TakeBackBook">
		<s:select name="bookId" list='books' listKey="id" listValue="id"
			label="Kies een boek" />
			<s:submit />
	</s:form>

	<a href=" <s:url action="CoworkerMenu" namespace="/coworker" /> ">Het
		menu voor coworkers</a>
	<br>
</body>
</html>
