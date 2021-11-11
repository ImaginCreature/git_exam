package member.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

public class LoginService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//데이터
		request.setCharacterEncoding("UTF-8"); // 한글처리하는 거라서 id pwd 할 땐 꼭 안 써도 된다. 
		
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		
		//DB
		MemberDAO memberDAO = MemberDAO.getInstance(); // 싱글톤 - 1번 생성해서 계속 사용한다.
		MemberDTO memberDTO = memberDAO.login(id,pwd); // 호출
		//login 드래그하고 F3키 누르면 그 함수로 이동한다.
		/*자바는 리턴값이 하나만 가능하므로
		배열
		MemerDDTO
		Map
		중 하나로 받아온다*/
		
		
		//응답
		if(memberDTO==null) {
			return "/member/loginFail.jsp";
		}else {
			//쿠키
			/*
			Cookie cookie = new Cookie("memName", name); // 쿠키 생성
			cookie.setMaxAge(1800); // 초 단위. 3초 동안 쿠키가 살아있는다. setPath() 안해도 시간을 늘리니까 되네?
			//cookie.setPath("/"); - /member/ member 폴더로 쿠키를 전송해라.
			response.addCookie(cookie); // 클라이언트로 보내기
			
			Cookie cookie2 = new Cookie("memId", id); 
			cookie2.setMaxAge(1800);
			//cookie2.setPath("/");
			response.addCookie(cookie2);
			
			
			//response.sendRedirect("/mvcmember/member/loginOk2.jsp");
			request.setAttribute("name", name);
			*/
			
			//세션 ; 이 안에다가 설정해서 경로 지정 안해도 된다.
			HttpSession session = request.getSession(); //세션 생성 ; interface라 new가 안된다.
			session.setAttribute("memName",memberDTO.getName());
			session.setAttribute("memId", id);
			session.setAttribute("memEmail", memberDTO.getEmail1()+"@"+memberDTO.getEmail2());
			
			session.setAttribute("memDTO", memberDTO);
			
			return "/member/loginOk.jsp";
		}
	}

}
