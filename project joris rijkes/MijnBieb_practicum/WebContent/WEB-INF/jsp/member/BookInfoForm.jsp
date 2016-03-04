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
		<tr class="thcolor">
			<th>id</th>
			<th>titel</th>
			<th>status</th>
			<th>info</th>
		</tr>
		<s:iterator value="books">
			<tr class="tdcolor">
				<td><s:property value="id" /></td>
				<td><s:property value="titel" /></td>
				<td><s:property value="status" /></td>
				<td><s:url id="url" action="BookInfo"><s:param name="bookId"><s:property value="id" /></s:param></s:url>
				<s:a href="%{url}">Info</s:a></td>
			</tr>
		</s:iterator>
	</table>
	<hr />
	<s:form action="BookInfo">
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
