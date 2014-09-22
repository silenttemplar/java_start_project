package hansung.ac.kr.j2ee.bbs;

import hansung.ac.kr.j2ee.bbs.db.BBS_OracleDao;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginImpl implements BBS_Service {
	BBS_OracleDao oracleDao;
	int status;
	HttpSession hs;
	
	@Override
	public String BBS_hansung(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		String id = req.getParameter("id");
		String pass =req.getParameter("pass");
		String addr = null;
		oracleDao = BBS_OracleDao.getInstance();
		status = oracleDao.login_check(id, pass);
		
		if(status == 1){
			hs = req.getSession();
			hs.setAttribute("id", id);
			
			if(hs.getAttribute("writeForm") == null){
				addr =  "/list.bbs";
			}else{
				addr =  "/writeForm.bbs";
			}
		}else if(status ==2){
			System.out.println("Password 틀림");
		}else{
			System.out.println("해당 id 없음");
		}
		
		return addr;
	}

}
