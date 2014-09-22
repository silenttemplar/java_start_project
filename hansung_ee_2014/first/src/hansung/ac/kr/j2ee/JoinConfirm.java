package hansung.ac.kr.j2ee;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JoinConfirm implements TestInterface {
	private DBConnection dbcon;
	
	
	@Override
	public String hansung(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		dbcon = DBConnection.getInstance();
		dbcon.join_member(req.getParameter("id"), req.getParameter("pw"));
		
		req.setAttribute("id", req.getParameter("id"));
		req.setAttribute("pw", req.getParameter("pw"));
		
		resp.sendRedirect("/first/getAllid.hansung");
		return null;
//		return "/joinConfirm.jsp";
	}

}
