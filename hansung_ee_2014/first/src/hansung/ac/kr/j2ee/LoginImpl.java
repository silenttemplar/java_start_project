package hansung.ac.kr.j2ee;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginImpl implements TestInterface {

	@Override
	public String hansung(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("LoginImpl에서 출력");
		
		/*
		 *  request 받은 parameter value를 test.jsp에 넘겨주기 위해서
		 *  setAttribute method를 활용
		 */
		
		req.setAttribute("id", req.getParameter("id"));
		req.setAttribute("pass", req.getParameter("pass"));
			return "/test.jsp";
	}

}
