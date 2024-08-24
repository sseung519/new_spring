<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
    <link rel="stylesheet" href="<c:url value='/css/styles.css'/>">
</head>
<body>
    <h1>로그인</h1>

    <c:if test="${not empty error}">
        <p class="error">${error}</p>
    </c:if>

    <form:form action="/user/login" method="post" modelAttribute="user">
        <div>
            <label for="id">아이디:</label>
            <input type="text" id="id" name="id" value="${user.id}" required>
        </div>
        <div>
            <label for="pwd">비밀번호:</label>
            <input type="password" id="pwd" name="pwd" required>
        </div>
        <button type="submit" class="btn">로그인</button>
    </form:form>

    <a href="<c:url value='/guestbook'/>" style="margin-top: 10px">방명록으로 돌아가기</a>

</body>
</html>