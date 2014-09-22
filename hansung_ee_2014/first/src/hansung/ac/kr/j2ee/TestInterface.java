package hansung.ac.kr.j2ee;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface TestInterface {
	public String hansung(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException;

}
