package member.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import member.bean.ZipcodeDTO;
import member.dao.MemberDAO;

public class CheckPostService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 데이터
		String sido = request.getParameter("sido");
		String sigungu = request.getParameter("sigungu");
		String roadname = request.getParameter("roadname");
		
		System.out.println(sido + "," + sigungu+ "," + roadname);
		// 정보가 제대로 전달되는지 확인해보려고 console 창에 찍는 것.
		
		
		//DB
		List<ZipcodeDTO> list = null; // if 안에서 선언하면 밑에 request 함수가 list를 모르므로 여기서 선언해준다.
		if(sido != null && roadname != null) { //sido, roadname 둘다 null 이 아닐 때에 db로 가라.
			//sigungu 는 뺀 이유는? 세종시는 구가 없어서. 즉 sigungu가 null 일 때가 있어서.
			MemberDAO memberDAO = MemberDAO.getInstance();
			list = memberDAO.getZipcodeList(sido, sigungu, roadname); // 데이터가 여러줄이니까 list에 담아온다.
			//MemberDTO 는 zipcode 만 있어서 이 3개의 데이터를 한 줄로 담을 DTO가 필요해서 ZipcodeDTO 를 만든다.
		}
		
		//응답
		request.setAttribute("list", list);
		return "/member/checkPost.jsp";
	}

}
