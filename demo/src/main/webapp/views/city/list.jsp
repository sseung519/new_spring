<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<title>Title</title>
	</head>
	<body>
		<h3>도시 목록</h3>
		<table border="1">
			<tr> <th>id</th> <th>name</th> <th>countryCode</th> <th>district</th> <th>population</th> </tr>
		<c:forEach var="city" items="${cities}">
		<tr> <td>${city.id}</td> <td><a href="/city/detail?id=${city.id}"> ${city.name}</a> </td> <td>${city.countryCode}</td> <td>${city.district}</td> <td>${city.population}</td> </tr>
		</c:forEach>
		</table>
	</body>
</html>
