//아이디 중복 체크 ; 밑에 function 에 onload 밖에 있는 건데 버튼을 누르면 실행되는 거라 상관 없음.
$('# checkIdBtn').click(function(){
	//var id = document.getElementById("id").value;
	//위는 java script 방식이고 아래는 jQuery 방식이다.		
	var id = $('#id').val(); //id 'id'의 value를 가져와라
	if(id == "") 
		alert("먼저 아이디를 입력해주세요");
	
	else 
		window.open("/mvcmember/member/checkId.do?id="+id,"checkId","width=300 height=100 top=200 left=700");
		/*/member/는  name space고 checkId 는 명령어(클래스 아님) 그래서 checkId가 소문자로 시작한다. 클래스가 아니라 명령어라.*/
		/*checkId.do 는 command.properties 에서 /member/checkId.do=member.service.CheckIdService
		를 실행하도록 하는 명령어*/
});

/*$('#checkIdClose').click(function(){
	alert("${requestScope.id}"); // 이렇게 쓰면 alert 창에 ${requestScope.id} 라고 그대로 뜬다.
	// ${requestScope.id} 는 jsp 안에서만 먹히는 것이다.그래서 checkIdOk.jsp 로 코드를 넣어준다.
});*/



/*jsp의 onclick 대신 써준 함수*/
$('#checkIdClose').click(function(){ 
	//alert($('#id').val());
	
	/*opener.document.getElementById("id").value = $('#checkId').val();*/ // 여기서는 id가 id 속성 
	/*document란 현재 문서라는 뜻이다. 여기서 document는 writeForm 이 아니라 checkId 다. 그래서 document 앞에 opener 를 써준다. */
	/*위처럼 써도 되고 아래처럼 써도 된다.*/
	/*opener.writeForm.id.value = $('#checkId').val();	 //  여기서 좌변의 id 는 name 속성 (form 뒤에 나오면 name 속성이다. )
	window.close();
	opener.writeForm.pwd.focus(); */
	
	//jQuery 형식으로 쓰면 다음과 같다.
	$('#id', opener.document).val($('#checkId').val());
	$('#check', opener.document).val($('#checkId').val()); //중복체크 버튼을 눌렀는지 확인용
	window.close();
	$('#pwd', opener.document).focus();
});

$('#zipcodeBtn').click(function(){
	window.open("/mvcmember/member/checkPost.do","checkPost", "width=300 height=100 top=200 left=700");
});

$('.addressA').click(function(){
	//alert($(this).text()); //주소
	//alert($(this).parent().prev.text()); // - 우편번호. prop 은 properties 의 약자
	
	$('#zipcode', opener.document).val($(this).parent().prev().text());
	$('#addr1', opener.document).val($(this).text());
	window.close();
	$('#addr2', opener.document).focus();
}); 

//address 두 개를 끌고 와야 한다.맨 마지막 td인 ${}address }와 그 위에 있는 것.
$('#addressA').click(function(){
	alert($('#zipcode').val() + ", " + $('#address'));
	
});


//jQuery
$(function(){
   //회원가입
   $('#writeBtn').click(function(){
      $('#nameDiv').empty();
      $('#idDiv').empty();
         $('#pwdDiv').empty();
         $('#repwdDiv').empty(); //Div 초기화

      //id 속성
      /*if($('#name').val() == '') $('#nameDiv').html('이름 입력'); */
      
      //name 속성
      if($('input[name="name"]').val() == '') {
         $('#nameDiv').html('이름 입력');
         $('#name').focus(); // input name 이 비었으면 이름 입력을 띄우고 focus 해줘라.
      }else if($('input[name="id"]').val()=='')
         $('#idDiv').html('아이디 입력');
      else if($('input[name="pwd"]').val()=='')
         $('#pwdDiv').html('비밀번호 입력');
      else if($('input[name="pwd"]').val() != $('input[name="repwd"]').val())
         $('#repwdDiv').html('비밀번호 틀림');
      
      else if($('#id').val() != $('#check').val())
          $('#idDiv').html('중복체크 하세요');
      
      else 
         $('form[name="writeForm"]').submit(); // submit을 하면 action으로 넘어간다.
   });
   
   //로그인
   $('#loginBtn').click(function(){
      $('#idDiv').empty();
         $('#pwdDiv').empty();

      if($('input[name="id"]').val()=='')
         $('#idDiv').html('아이디 입력');
      else if($('input[name="pwd"]').val()=='')
         $('#pwdDiv').html('비밀번호 입력');
      else 
         $('form[name="loginForm"]').submit();
   });
});

