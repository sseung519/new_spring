<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>쿠키 테스트</h3>
${cookie.num.name } / ${cookie.num.value }<br/>
${cookie.name.name } / ${cookie.name.value }<br/>
${cookie.JSESSIONID.name } / ${cookie.JSESSIONID.value }<br/>
<a href="/cookie/add?num=1&name=aaa">쿠키추가</a><br/>
<a href="/cookie/list">쿠키목록</a><br/>
</body>
</html>