<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>방명록</title>
    <link rel="stylesheet" href="<c:url value='/css/styles.css'/>">
</head>
<body>
    <h1>방명록 작성</h1>

    <c:if test="${not empty error}">
        <p class="error">${error}</p>
    </c:if>

    <form action="/guestbook/write" method="post">
        <div>
            <label for="writer">작성자:</label>
            <input type="text" id="writer" name="writer" required>
        </div>
        <div>
            <label for="password">비밀번호:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div>
            <label for="content">내용:</label>
            <textarea id="content" name="content" required></textarea>
        </div>
        <button type="submit" class="btn">작성</button>
    </form>

    <a href="<c:url value='/guestbook'/>" style="margin-top: 10px">목록으로 돌아가기</a>

</body>
</html>