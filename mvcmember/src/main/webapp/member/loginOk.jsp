<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String name = null;
String id = null;

/*
//쿠키
Cookie[] ar = request.getCookies(); //모든 쿠키들을 다 가져온다
if(ar!=null){
	for(int i=0; i<ar.length; i++){
		String cookieName = ar[i].getName(); //쿠키명
		String cookieValue = ar[i].getValue(); //값
		
		//System.out.println("쿠키명 = " + cookieName);
		//System.out.println("값 = " + cookieValue);

		if(cookieName.equals("memName")) name = cookieValue;
		if(cookieName.equals("memId")) id = cookieValue;
	} //for
}//if
*/

//
//name = (String)session.getAttribute("memName");
//id = (String)session.getAttribute("memId");
			
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
img {
	width: 50px;
	height: 50px;
	cursor: pointer;
}
</style>
</head>
<body>
<%-- ${requestScope.name }님 로그인 --%>
<%-- <%=name %>님 로그인 --%>

<img src="../image/1.gif" onclick="location.href='../index.jsp'">
${sessionScope.memName }님 로그인
<br><br>

<input type="button" value="로그아웃" onclick="location.href='logout.do'">
<input type="button" value="회원정보수정" onclick="location.href='modifyForm.do'">

</body>
</html>



