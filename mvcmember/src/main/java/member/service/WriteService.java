package member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import member.dao.MemberDAO;
import member.bean.MemberDTO;

public class WriteService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 데이터
		// ControlServlet을 거쳐 command.properties 를 거칠 때 ControlServlet execute 함수에서 이미 한글 처리가 되어있다.
		// 따라서  request.setCharacterEncoding("UTF-8"); 은 생략될 수 있다.
				
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String gender = request.getParameter("gender");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
		String zipcode = request.getParameter("zipcode");
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		
		//DB
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setName(name);
		memberDTO.setId(id);
		memberDTO.setPwd(pwd);
		memberDTO.setGender(gender);
		memberDTO.setEmail1(email1);
		memberDTO.setEmail2(email2);
		memberDTO.setTel1(tel1);
		memberDTO.setTel2(tel2);
		memberDTO.setTel3(tel3);
		memberDTO.setZipcode(zipcode);
		memberDTO.setAddr1(addr1);
		memberDTO.setAddr2(addr2);
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.write(memberDTO);
		
		
		//응답
		return "/member/write.jsp";
		// 여기서 /member/ 는 폴더를 의미한다. url 일때만 name space.
	}

}
