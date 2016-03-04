<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>

   <head>
      <title>Boekenlijst</title>
      <link rel="stylesheet" type="text/css" href="../css/bieb.css" />
   </head>

   <body>
	    <h4>Alle rereserveerde en geleende boeken.</h4>
	    
	    <hr />

	    <table>
		    <tr class="thcolor">
			    <th>id</th>
			    <th>titel</th>
			    <th>status</th>
		    </tr>
		    <s:iterator value="books">
			    <tr class="tdcolor">
				    <td><s:property value="id" />
					<td><s:property value="titel" />
		     		<td><s:property value="status" />
		        </tr>
 		    </s:iterator>
	    </table>
  		
  		
	    <hr /> 
	    
	    	<s:form action="CancelReservation">
		<table>
			<tr>
				<s:textfield name="bookId" label="Id" />
			</tr>
			<tr>
				<s:submit />
			</tr>	
		</table>
</s:form>
	    
        <a href=" <s:url action="MemberMenu" namespace="/member" /> ">Het menu voor leden</a>
	</body> 
</html> 