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
    <h1>방명록 목록</h1>

    <c:if test="${not empty message}">
        <p class="success">${message}</p>
    </c:if>

    <c:if test="${not empty sessionScope.user}">
        <p>안녕하세요, ${sessionScope.user.username}님</p>
    </c:if>

    <c:if test="${empty guestbooks}">
        <p>작성된 방명록이 없습니다.</p>
    </c:if>

    <a href="<c:url value='/guestbook/write'/>" class="btn" style="margin-bottom: 10px">방명록 작성</a>
    <c:choose>
        <c:when test="${empty sessionScope.user}">
            <a href="<c:url value='/user/join'/>" class="btn" style="margin-bottom: 10px">회원가입</a>
            <a href="<c:url value='/user/login'/>" class="btn" style="margin-bottom: 10px">로그인</a>
        </c:when>
        <c:otherwise>
            <a href="<c:url value='/user/logout'/>" class="btn" style="margin-bottom: 10px">로그아웃</a>
        </c:otherwise>
    </c:choose>

    <c:forEach var="guestbook" items="${guestbooks}">
        <div class="guestbook-entry">
            <h3>${guestbook.content}</h3>
            <p>작성자: ${guestbook.writer}</p>
            <p>작성일: ${guestbook.formattedCreatedAt}</p>
            <a href="<c:url value='/guestbook/${guestbook.id}'/>">상세보기</a>
        </div>
    </c:forEach>
</body>
</html>