package hansung.ac.kr.j2ee;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllId implements TestInterface {
	DBConnection dc;
	private ArrayList<LoginVO> loginList;

	@Override
	public String hansung(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		dc = DBConnection.getInstance();
		
		loginList = dc.getAllId();
		req.setAttribute("loginList", loginList);
		
		return "/loginList.jsp";
	}

}
