<%@page import="models.Contact"%>
<%@page import="java.util.ArrayList"%>

<html>
<head>
	<meta <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>>
	<meta charset="UTF-8">
	<title>Contacts</title>
	<link rel="stylesheet" href="css/site.css">
</head>
<body>
	<h1>all contacts</h1>
	<%
		ArrayList<Contact> allCons = (ArrayList<Contact>) request.getAttribute("allCons");
		for(Contact c : allCons) {
			out.write(""+c.getName() + " " + c.getEmail() + "<br>");
		}
	%> 

	<br>
	<a class="btn" href="./addnew">+ New Contact</a>

</body>
</html>