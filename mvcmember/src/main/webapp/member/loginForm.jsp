<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>

</head>
<body>
	<form name="loginForm" method="post" action="http://localhost:8080/mvcmember/member/login.do">
		<div class="text">
			 <table border="1" cellspacing="0" cellpadding="5">
		      				      
					<tr>
					   <td width="100" align="center">아이디</td>
					   <td>
					      <input type="text" name="id" id="id" size="20">
					      <div id= "idDiv"></div>
					   </td>   
					</tr>
		      
					<tr>
					   <td width="100" align="center">비밀번호</td>
					   <td>
					      <input type="password" name="pwd" id="pwd" size="20">
					      <div id= "pwdDiv"></div>
					   </td>   
					</tr>
	        
					<tr>
						<td colspan="2" align="center">
							<input type="button" id="loginBtn" value="로그인">  <!-- onclick="checkLogin()" -->
							<input type="button" value="회원가입" onclick="location.href='/mvcmember/member/writeForm.do'">
						</td>
					</tr>		
		         		         
	        	 </table>
				</form>
			</div> <!--  text  -->
		</div> <!--  content  -->
	</div> <!--  container   -->
</div> <!--  wrap   -->
<hr>






</form>
<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="http://localhost:8080/mvcmember/js/member.js"></script>




</body>
</html>




