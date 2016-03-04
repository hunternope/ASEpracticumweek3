<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>

	<head>
        <title>Account aangemaakt</title>
        <link rel="stylesheet" type="text/css" href="../css/bieb.css" />
    </head>

	<body>
	    <h4>Wachtwoord gewijzigd.</h4>
	     	
	    <hr />
	    
		<s:property value="user.username"/>, je wachtwoord is gewijzigd.
		
	    <hr />
	    
	<a href=" <s:url action="MemberMenu" namespace="/member" /> ">Het
		menu voor leden</a>
	</body> 
</html>