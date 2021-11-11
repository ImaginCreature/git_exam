package board.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import board.bean.BoardDTO;
import board.dao.BoardDAO;
import member.bean.MemberDTO;

public class BoardWriteService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		//데이터
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		HttpSession session = request.getSession();
		
		//1) 문자열로 받기
		String name = (String) session.getAttribute("memName");
		String id = (String) session.getAttribute("memId");
		String email = (String) session.getAttribute("memEmail");
				
		//2) 오브젝트로 받기
//		MemberDTO memberDTO = (MemberDTO)(session.getAttribute("membDTO"));
//		String name = memberDTO.getName();
//		String id = memberDTO.getId();
//		String email = memberDTO.getEmail1()+"@"+memberDTO.getEmail2();
		
		
//		BoardDTO boardDTO = new BoardDTO();
//		boardDTO.setId(id);
//		boardDTO.setName(name);
//		boardDTO.setEmail(email);
//		boardDTO.setSubject(subject);
//		boardDTO.setContent(content);
		
		//Map<Key, Value>
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("name", name);
		map.put("email", email);
		map.put("subject", subject);
		map.put("content", content);
		
		//DB
		BoardDAO boardDAO = BoardDAO.getInstance();
//		boardDAO.boardWrite(boardDTO);
		boardDAO.boardWrite(map);
		//DTO에 넣어가도 상관없으나 map을 많이 이용함. 필요한 개수만 넘길 수 있어서.
		
		//응답
		return "/board/boardWrite.jsp";

	}
}
