<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	const a = (num) => {
		//비동기 요청 객체 생성
		let req = new XMLHttpRequest(); 
		
		//비동기 요청에 대한 응답이 오면 자동 호출될 핸들러 등록
		//받은 응답은 비동기 객체의 responseText 속성에 저장됨
		//{"num":1, "writer":"aaa"}
		req.onload = () => {
			let res = document.getElementById("res");
			let obj = JSON.parse(req.responseText);
			let txt = "글 상세내용<br/>";
			txt += "num:"+obj.num+"<br/>";
			txt += "writer:"+obj.writer+"<br/>";
			txt += "wdate:"+obj.wdate+"<br/>";
			txt += "title:"+obj.title+"<br/>";
			txt += "content:"+obj.content+"<br/>";
			res.innerHTML = txt;
		}
		//open()로 요청 설정
		req.open('get', '/board/getAjax?num='+num);
		
		//요청 전송
		req.send();
	}
	const b = () => {
		let res = document.getElementById("res");
		res.innerHTML = '';
	}

</script>
</head>
<body>
<h3>게시판</h3>
<span id="res" style="position:absolute;top:100px;left:400px 	"></span>
<a href="/board/todaylist">오늘 읽은 글목록</a><br/>
<a href="/board/add">글작성</a><br/>
<select name="gettype">
	<option>작성자</option>
	<option>제목</option>
</select>
<input type="text" name="search">
<input type="button" value="검색">
<table border="1">
	<tr><th>num</th><th>title</th><th>writer</th></tr>
	<c:forEach var="b" items="${list }">
		<tr>
			<td><a href="/board/detail?num=${b.num }">${b.num }</a></td>
			<td><span onmouseover="a(${b.num })" onmouseout="b()">${b.title }</span></td>
			<td>${b.writer }</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>