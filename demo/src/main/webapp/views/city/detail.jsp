<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<title>Title</title>
	</head>
	<body>
		<h3>상세 페이지</h3>
		<form action="/city/edit" method="post">
			id: <input type="text" name="id" value="${city.id }" readonly><br/>
			name: ${city.name }<br/>
			countryCode: ${city.countryCode }<br/>
			district: ${city.district }<br/>
			population: <input type="text" name="population" value="${city.population }"><br/>
			<input type="submit" value="수정">
			<input type="button" value="삭제" onclick="a()">
		</form>
		
		<script type="text/javascript">
            const a = () => {
                location.href = "/city/del?id=${city.id}"
            }
		</script>
	</body>
</html>
