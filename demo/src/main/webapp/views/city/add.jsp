<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
	<head>
		<title>Title</title>
	</head>
	<body>
	<h3>도시 추가</h3>
		<form action="/city/add" method="post">
		Name: <input type="text" name="name" /> <br />
		CountryCode:
			<select name="countryCode">
			<c:forEach var="s" items="${list}">
				<option >${s}</option>
			</c:forEach>
			</select>
<%--			<label for="USA">USA</label>--%>
<%--			<input type="radio" name="countryCode" id="USA" value="USA" /> <br />--%>
<%--			<label for="KOR">KOR</label>--%>
<%--			<input type="radio" name="countryCode" id="KOR" value="KOR" /> <br />--%>
<%--			<label for="ARG">ARG</label>--%>
<%--			<input type="radio" name="countryCode" id="ARG" value="ARG" /> <br />--%>
			
			<br />
		District: <input type="text" name="district" /> <br />
		Population: <input type="number" name="population" /> <br />
			<button type="submit">추가</button>
		</form>
	</body>
</html>
