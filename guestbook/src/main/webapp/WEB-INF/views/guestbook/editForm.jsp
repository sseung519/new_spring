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
    <h1>방명록 수정</h1>

    <c:if test="${not empty error}">
        <p class="error">${error}</p>
    </c:if>

    <c:if test="${not empty guestbook}">
        <form action="<c:url value='/guestbook/${guestbook.id}/edit'/>" method="post">
            <div>
                <label for="writer">작성자:</label>
                <input type="text" id="writer" name="writer" value="${guestbook.writer}" required>
            </div>
            <div>
                <label for="password">비밀번호:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div>
                <label for="content">내용:</label>
                <textarea id="content" name="content" required>${guestbook.content}</textarea>
            </div>
            <button type="submit" class="btn">수정</button>
        </form>
    </c:if>

    <a href="<c:url value='/guestbook/${guestbook.id}'/>">상세보기로 돌아가기</a>

</body>
</html>