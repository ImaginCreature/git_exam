<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3> ${requestScope.id } 은(는) 사용 가능한 아이디입니다. </h3>
<input type="hidden" id="checkId" value="${requestScope.id }"> <!-- html 안에서 변수 설정 -->
<br>
<input type="button" value="사용하기" id="checkIdClose"> <!-- jQuery 를 써서 onclick은 쓰지 못한다. 그래서 위에 input type="hidden"으로 쓴다. -->

<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="../js/member.js"></script>

<script type="text/javascript">
$('#checkIdClose').click(function(){
	alert("${requestScope.id}"); /* ${requestScope.id} 는 jsp 안에서만 먹히기 때문에 member.js 에 쓰지 않고 여기서 쓴다. */
});

</script>
</body>
</html>