<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
td {
	width : 100px;
	font-size: 8pt;
}
</style>

</head>
<body>
<!-- submit(검색 버튼) 을 한다는 것은 여기서 입력한 데이터를 다 action 주소(checkPost.do)로 전달한다는 뜻이다.
1. 데이터 3개 (시도, 시군구, 도로명)
2. DB에서 데이터를 꺼내온다.
3. 응답해야하는데 한두 건이 아니다. 스크롤바를 이용해서 아래에 띄운다. 

checkPost.do 는 member/writeForm.do 와 /member/checkPost.do 에서 각각 "우편번호 검색" / "검색" 으로 submit되는데
회원가입창에서는 전달하는 데이터가 없지만
checkPost 창에서는 위에 1번에서 말한 데이터 3개를 전달한다.

즉, 데이터를 전달하는지 아닌지 체크해야 한다.
-->
<form action="/mvcmember/member/checkPost.do">
	<table border="1" cellspacing="0" cellpadding="5">
		<tr>
			<td width="100" align="center">시도</td>
			<td>
				<select name="sido">
					<option value="시도선택" selected>시도선택</option>
					<option value="서울">서울</option>
					<option value="인천">인천</option>
					<option value="대전">대전</option>
					<option value="대구">대구</option>
					<option value="울산">울산</option>
					<option value="세종">세종</option>
					<option value="광주">광주</option>
					<option value="경기">경기</option>
					<option value="강원">강원</option>
					<option value="전남">전남</option>
					<option value="전북">전북</option>
					<option value="경남">경남</option>
					<option value="경북">경북</option>
					<option value="충남">충남</option>
					<option value="충북">충북</option>
					<option value="부산">부산</option>
					<option value="제주">제주</option>
				</select>
				</td>
			
			<td width="100" align="center">시.군.구</td>
			<td align="center"><input type="text" name="sigungu"></td>
		</tr>
		
		
		<tr>
			<td width="100" align="center">도로명</td>
			<td colspan="3">
				<input type="text" name="roadname" width="250px;"/>
				<input type="button" value="검색"/>
			</td>
		</tr>
		
		
		<tr> 
			<td width="100" align="center">우편번호</td>
			<td align="center" colspan="3">주소</td>
		</tr>
		
		<%-- 
		
		<% if(list != null){ %>
			<% for(ZipcodeDTO zipcodeDTO : list){ %>
			
			<%} %>
		<%} %> 
		
		를 아래와 같이 jstl 로 쓴다. 
		--%>


         <c:if test="${list != null }">
            <c:forEach var="zipcodeDTO" items="${requestScope.list }">
				<c:set var="address">
					${zipcodeDTO.sido 
					} ${zipcodeDTO.sigungu 
					} ${zipcodeDTO.yubmyundong
					} ${zipcodeDTO.ri 
					} ${zipcodeDTO.roadname 
					} ${zipcodeDTO.buildingname }
					<%-- 위에 ${ }들은 엔터키 치면 안 먹힘. 밑으로 내리면 안된다. 
					하지만 앞에 } 를 하고 엔터치면 인식한다. --%>
				</c:set>
				
				
				<!-- td의 부모는 tr, 형제는 tr안에 속해있는 다른 td들 
				 본인 이전 형제는 prev() 본인 다음 형제는 next()-->
				<tr>
					<td align="center">${zipcodeDTO.zipcode }</td>
					<td colspan="3">
	
				<%--<a href="#" id="addressA" 
						onclick="checkPostClose('${zipcodeDTO.zipcode }','${address }')">${address }</a>
 						--%>
 						
					<%--<a href="#" id="addressA">${address }</a>
						<input type="hidden" id="zipcode" value="${zipcodeDTO.zipcode }">
						<input type="hidden" id="address" value="${address }"> 
						이렇게 쓰면 for문이 끝나고 가져와서 첫번째 list밖에 못 가져온다.
						--%>
						
						<a href="#" class="addressA">${address }</a>
						<!-- list elements가 여러개니까 id보다 class 속성을 하는게 더 정확하다 -->
					</td>
				</tr>            
            </c:forEach>
         </c:if>
		
				
	</table>
</form>


<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="../js/member.js"></script>
<script type="text/javascript">
/* $('#addressA').click(function(){
	alert($('#zipcode').val() + ", " + $('#address'));
	
}); */

function checkPostClose(zipcode, address){
	opener.writeForm.zipcode.value = zipcode;
	opener.writeForm.addr1.value = address;
	/* 101 line에서 
	onclick="checkPostClose('${zipcodeDTO.zipcode }','${address }')" 
	이 넘어오는 것. */
	window.close();
	opener.writeForm.addr2.focus();
}
</script>

<script type="text/javascript" src="../js/member.js"></script>

</body>
</html>
