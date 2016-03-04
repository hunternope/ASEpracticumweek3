<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>

<head>
<title>Boekenlijst</title>
<link rel="stylesheet" type="text/css" href="../css/bieb.css" />
</head>

<body>
	<h4>Verwijder user.</h4>

	<hr />

	<table>
		<tr class="thcolor">
			<th>username</th>
			<th>password</th>
			<th>role</th>
		</tr>
		<s:iterator value="allUsers">
			<tr class="tdcolor">
				<td><s:property value="username" />
				<td><s:property value="password" />
				<td><s:property value="ur" />
			</tr>
		</s:iterator>
	</table>

	<hr />

	<s:form action="DeleteUser">
		<table>
			<tr>
				<s:textfield name="username" label="username" />
			</tr>
			<tr>
				<s:submit />
			</tr>
		</table>
	</s:form>

	<a href=" <s:url action="ManagerMenu" namespace="/manager" /> ">Het
		menu voor managers</a>
</body>
</html>
