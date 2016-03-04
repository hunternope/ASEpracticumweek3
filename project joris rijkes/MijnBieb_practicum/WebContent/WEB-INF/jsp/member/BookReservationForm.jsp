<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>

<head>
<title>Boekenlijst</title>
<link rel="stylesheet" type="text/css" href="../css/bieb.css" />
</head>

<body>
	<h4>Boek reserveren.</h4>

	<hr />

<h4>Beschikbare boeken</h4>
	<table>
	
	
		<tr class="thcolor">
			<th>id</th>
			<th>titel</th>
		</tr>
		<s:iterator value="books">
			<tr class="tdcolor">
				<td><s:property value="id" /></td>
				<td><s:property value="titel" /></td>
			</tr>
		</s:iterator>
	</table>
	<hr />
	<s:form action="BookReservation">
		<table>
			<tr>
				<s:textfield name="bookId" label="Id" />
			</tr>
			<tr>
				<s:submit />
			</tr>	
		</table>
</s:form>

	<a href=" <s:url value="MemberMenu.action"/> ">Terug naar het menu voor leden</a>
</body>
</html>
