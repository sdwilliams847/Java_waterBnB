<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Host</title>
</head>
<body>
	<h1>Current Listings</h1> <a href="/users/logout">Logout</a>
	
	<table>
		<tr>
			<td>Address</td>
			<td>Size</td>
			<td>Cost</td>
			<td>Details</td>
		</tr>
		<c:forEach items="${user.listings }" var="listing">
			<tr>
				<td>${listing.address}</td>
				<td>${listing.size}</td>
				<td>${listing.cost}</td>
				<td><a href="/listings/${listing.id}">See More</a></td>
			</tr>
		</c:forEach>
	</table><br>
	
	
	<form:errors path="listing.errors.*"></form:errors>
	<fieldset>
	<legend>New Listing</legend>
		<form:form method="POST" action="/listings" modelAttribute="listing">
			<form:label path="address">Address: 
				<form:input path="address"></form:input>
			</form:label><br><br>
			
			<form:label path="description">Description: 
				<form:input path="description"></form:input>
			</form:label><br><br>
			
			<form:label path="cost">Cost: 
				<form:input type="number" path="cost"></form:input>
			</form:label><br><br>
			
			<label>Size:</label>
			<form:select path="size">
				<form:option value="small">Small</form:option>
				<form:option value="medium">Medium</form:option>
				<form:option value="large">Large</form:option>
			</form:select><br><br>

		
			<input type="submit" value="Create Listing">
		</form:form>
	</fieldset>
	
	
	
	
</body>
</html>