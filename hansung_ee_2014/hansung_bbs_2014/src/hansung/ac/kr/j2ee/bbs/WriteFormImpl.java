package hansung.ac.kr.j2ee.bbs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WriteFormImpl implements BBS_Service {
	HttpSession hs;
	
	@Override
	public String BBS_hansung(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		hs = req.getSession();
		if(hs.getAttribute("id") != null){
			return "/writeForm.jsp";
		}else{
			hs.setAttribute("writeFrom", "ok");
			return "/login.jsp";
		}
	}

}
