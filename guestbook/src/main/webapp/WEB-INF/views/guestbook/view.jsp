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
    <h1>방명록 상세보기</h1>

    <c:if test="${not empty error}">
        <p class="error">${error}</p>
    </c:if>

    <c:if test="${not empty message}">
        <p class="success">${message}</p>
    </c:if>

    <c:if test="${not empty guestbook}">
        <div class="guestbook-entry">
            <h3>${guestbook.content}</h3>
            <p>작성자: ${guestbook.writer}</p>
            <p>작성일: ${guestbook.formattedCreatedAt}</p>
            <c:if test="${not empty guestbook.updatedAt}">
                <p>수정일: ${guestbook.formattedUpdatedAt}</p>
            </c:if>
        </div>

        <a href="<c:url value='/guestbook/${guestbook.id}/edit'/>" class="btn">수정</a>

        <form action="<c:url value='/guestbook/${guestbook.id}/delete'/>" method="post"
              onsubmit="return confirm('정말 삭제하시겠습니까?');" style="margin-top: 10px; width: fit-content">
            <input type="password" name="password" placeholder="비밀번호" style="width: auto; margin-bottom: 0" required>
            <button type="submit" class="btn">삭제</button>
        </form>
    </c:if>
    <br>
    <a href="<c:url value='/guestbook'/>">목록으로 돌아가기</a>
</body>
</html>