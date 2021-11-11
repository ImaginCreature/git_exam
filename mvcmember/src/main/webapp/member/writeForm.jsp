<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style type="text/css">
div {
	color: red;
	font-size: 8pt;
	font-weight: bold;
}
</style>
</head>
<body>
<form name="writeForm" method="post" action="/mvcmember/writeForm.do"> 
<!-- action 주소에 http://localhost:8080 생략함. /member/ 는 폴더가 아니라 name space 어떤 category인지 구분하려고 쓰는 것이다. 회사명으로 쓰는 곳도 있다.
url에 써져있는 /name/ 은 모두 name space다. -->
	<table border="1" cellspacing="0" cellpadding="5">
		<tr>
			<td width="100" align="center">이름</td>
			<td>
				<input type="text" name="name" id="name" placeholder="이름 입력">
				<div id= "nameDiv"></div>
			</td>	
		</tr>
		
		<tr>
			<td width="100" align="center">아이디</td>
			<td>
				<input type="text" name="id" id="id" placeholder="아이디 입력">
				<input type="button" value="중복체크" id="checkIdBtn">
				<input type="text" id="check" value="false">
				<div id= "idDiv"></div>
			</td>	
		</tr>
		
		<tr>
			<td width="100" align="center">비밀번호</td>
			<td>
				<input type="password" name="pwd" id="pwd" size="30" placeholder="비밀번호 입력">
				<div id= "pwdDiv"></div>
			</td>	
		</tr>
		
		<tr>
			<td width="100" align="center">재확인</td>
			<td>
				<input type="password" name="repwd" id="repwd" size="30" placeholder="비밀번호 입력">
				<div id= "repwdDiv"></div>
			</td>	
		</tr>
		
		<tr>
			<td width="100" align="center">성별</td>
			<td>
				<input type="radio" name="gender" value="0" checked>남
				<input type="radio" name="gender" value="1">여
			</td>
		</tr>
		
		<tr>
			<td width="100" align="center">이메일</td>
			<td>
				<input type="text" name="email1">
				@
				<input type="text" name="email2" list="email2" placeholder="직접입력">
				<datalist id="email2">
					<option value="naver.com">naver.com
					<option value="daum.net">daum.net
					<option value="gmail.com">gmail.com
				</datalist>
			</td>
		</tr>
		
		<tr>
			<td width="100" align="center">핸드폰</td>
			<td>
				<select name="tel1" style="width: 70px;">
					<option value="010" selected>010</option>
					<option value="011" >011</option>
					<option value="019" >019</option>
				</select>
				-
				<input type="text" name="tel2" size="6" maxlength="4">
				-
				<input type="text" name="tel3" size="6" maxlength="4">
			</td>
		</tr>
		
		<tr>
			<td width="100" align="center">주소</td>
			<td>
				<input type="text" name="zipcode" id="zipcode" readonly>
				<input type="button" value="우편번호 검색" id="zipcodeBtn"><br>
				<input type="text" name="addr1" id="addr1" size="60" placeholder="주소" readonly><br>
				<input type="text" name="addr2" id="addr2" size="60" placeholder="상세주소">
			</td>
		</tr>
		
		<tr>
			<td colspan="2" align="center">
				<input type="button" id="writeBtn" value="회원가입" >
				<input type="reset" value="다시작성">
			</td>
		</tr>
	</table>
</form>
<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="../js/member.js"></script>
</body>
</html>
