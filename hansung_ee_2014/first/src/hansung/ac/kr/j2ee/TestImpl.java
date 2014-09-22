package hansung.ac.kr.j2ee;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestImpl implements TestInterface {

	@Override
	public String hansung(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("TestImpl에서 출력");
			return "/login.jsp";
	}

}
