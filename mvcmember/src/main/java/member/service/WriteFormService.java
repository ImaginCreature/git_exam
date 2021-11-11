package member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

public class WriteFormService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		return "/member/writeForm.jsp"; // ControlServlet 에 상대번지를 요청하는 것이므로 절대번지를 쓰면 안된다. 
	}

}
