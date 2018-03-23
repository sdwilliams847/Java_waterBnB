<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dashboard</title>
</head>
<body>
	<form action="users/logout" method="get">
		<p>
			<input type="submit" value="Logout">
		</p>	
	</form>
	
	<h1>Dashboard</h1>

	<hr>
	
	<c:forEach items="${users}" var="user">
		<h1>${user.id }</h1>
		<h1>${user.firstName }</h1>
		<h1>${user.lastName }</h1>
		<h1>${user.email }</h1>
		<h1>${user.password }</h1>
	</c:forEach>

</body>
</html>