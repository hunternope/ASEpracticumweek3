<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>

	<head>
        <title>Account aangemaakt</title>
        <link rel="stylesheet" type="text/css" href="../css/bieb.css" />
    </head>

	<body>
	    <h4>Boek gereserveerd.</h4>
	     	
	    <hr />
	    
		<s:property value="User.username"/>, u heeft het boek "<s:property value="Book.titel"/>" gereserveerd.
	    <hr />
	    
	<a href=" <s:url action="MemberMenu" namespace="/member" /> ">Het
		menu voor leden</a><br>
	<a href=" <s:url action="BookReservationForm" namespace="/member" /> ">Nog
		een boek reserveren</a>
	</body> 
</html>