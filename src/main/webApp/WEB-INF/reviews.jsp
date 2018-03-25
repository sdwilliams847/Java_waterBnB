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
	
	
	<p>${listing.address}</p>
	<p>${listing.description}</p>
	
	
	<p>Email: ${listing.user.email}</p>
	<p>Name: ${listing.user.firstName} ${listing.user.lastName}</p>
	<p>Size: ${listing.size}</p>
	
	<strong>Reviews: ( / 5)</strong><br><br>
	
	<c:if test="${!user.host}"><a href="/listings/${listing.id}/review">Leave a Review</a></c:if>
	
	<div class="reviews">
		<c:forEach items="${listing.reviews }" var="review">
			<p>${review.user.firstName }</p>
			<p>Review: ${review.rating}/5</p>
			<p>${review.description}</p>
		
		
		</c:forEach>
	</div>
	
</body>
</html>