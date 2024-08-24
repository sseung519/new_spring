<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Today List</title>
</head>
<body>
<h3>오늘 읽은 글목록</h3>
<table border="1">
    <tr>
        <th>num</th>
        <th>title</th>
        <th>writer</th>
        <th>Wdate</th>
    </tr>
    <c:forEach var="board" items="${boardList}">
        <tr>
            <td>${board.num}</td>
            <td><a href="/board/detail?num=${board.num}">${board.title}</a></td>
            <td>${board.writer}</td>
            <td>${board.wdate}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>