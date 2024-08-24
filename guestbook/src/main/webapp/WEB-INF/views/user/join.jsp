<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <link rel="stylesheet" href="<c:url value='/css/styles.css'/>">
</head>
<body>
    <h1>회원가입</h1>

    <c:if test="${not empty error}">
        <p class="error">${error}</p>
    </c:if>

    <form:form action="/user/join" method="post" modelAttribute="user">
        <div>
            <label for="id">아이디:</label>
            <form:input path="id" id="id" required="true"/>
        </div>
        <div>
            <label for="username">이름:</label>
            <form:input path="username" id="username" required="true"/>
        </div>
        <div>
            <label for="pwd">비밀번호:</label>
            <form:password path="pwd" id="pwd" required="true"/>
        </div>
        <button type="submit" class="btn">가입하기</button>
    </form:form>

    <a href="<c:url value='/guestbook'/>" style="margin-top: 10px">방명록으로 돌아가기</a>

</body>
</html>