<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>hello spring</h3>
	<a href="/member/join">회원가입</a><br/>
	<a href="/member/login">로그인 </a><br/>
	${m1.id } / ${m1.pwd} / ${m1.name } / ${m1.email} <br />
	<a href="/city/info" >도시 검색</a> <br />
	<a href="/city/add">도시 추가</a><br />
	<a href="/city/list">도시 목록</a>
</body>
</html>