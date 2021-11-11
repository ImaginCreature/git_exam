<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>${requestScope.id } 이미 사용 중입니다. </h3>
<br>
<form action="/mvcmember/member/checkId.do">  <!-- submit 할 땐 form 이 있어야 한다. submit 하면 action으로 가기 때문이다.-->
	아이디 <input type="text" name="id">
	<input type="submit" value="중복체크">
	<!-- cf ) 캐쉬 지우기 ctrl + f5 -->
</form>

</body>
</html>