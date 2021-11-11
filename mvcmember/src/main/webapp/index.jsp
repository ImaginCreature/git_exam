<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>*** 메인화면 ***</h3>

<c:if test="${sessionScope.memId == null }">
	<a href="http://localhost:8080/mvcmember/member/writeForm.do">회원가입</a><br>
	<!-- do 명령어 요청을 하면 무조건 Servlet 으로 들어가는 게 약속이다. (여기서는 ControlServlcet) 
	서블릿으로 들어가면 *.properties 에 도움을 받으러 간다.
	*.properties 는 한 번 밖에 읽을 수 없고 여기의 정보는 모두 map으로 떠넘긴다.
	Map<Key,Value> 는 Key를 주면 Value(정보)를 주겠다는 뜻이다. -->
	<a href="/mvcmember/member/loginForm.do">로그인</a><br>
</c:if>

<c:if test="${sessionScope.memId != null }">
	<a href="/mvcmember/member/logout.do">로그아웃</a><br>
	<a href="">회원정보수정</a><br>
	<a href="/mvcmember/board/boardWriteForm.do">글쓰기</a><br>
	<!-- url의 /member/ , /board/ 는 namespace 로 파일의 성격을 나타낼 수 있게 만드는 게 좋다 -->
</c:if>

<br>
<a href="/mvcmember/board/boardList.do?pg=1">목록</a><br>
<a href=""></a><br>

</body>
</html>