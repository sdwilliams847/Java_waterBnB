<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<c:if test="${user != null}"><a href="/listings">Home</a></c:if>
	<c:if test="${user != null}"><a href="/users/new">Logout</a></c:if>
	
	<p>Review of: ${listing.address}</p>
	
	<form:form action="/listings/${listing.id}/review" method="POST" modelAttribute="review">
		<form:label path="description">Description: 
			<form:textarea path="description"/>
			<form:errors path="description"></form:errors>		
		</form:label>
		<label>Rating:</label>
		<form:select path="rating">
				<form:option value="1">1</form:option>
				<form:option value="2">2</form:option>
				<form:option value="3">3</form:option>
				<form:option value="4">4</form:option>
				<form:option value="5">5</form:option>
		</form:select>
		
		<input type="submit" value="Create Review">
	</form:form>
	
</body>
</html>