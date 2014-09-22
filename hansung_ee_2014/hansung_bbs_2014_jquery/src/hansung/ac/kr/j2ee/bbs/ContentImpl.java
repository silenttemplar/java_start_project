package hansung.ac.kr.j2ee.bbs;

import hansung.ac.kr.j2ee.bbs.db.BBS_Dto;
import hansung.ac.kr.j2ee.bbs.db.BBS_OracleDao;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContentImpl implements BBS_Service{
	
	BBS_OracleDao oracleDao;
	BBS_Dto content;

	@Override
	public String BBS_hansung(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		oracleDao = BBS_OracleDao.getInstance();
		content = oracleDao.getContent(Integer.parseInt(req.getParameter("article_num")));
		
		req.setAttribute("pageNum", req.getParameter("pageNum"));
		req.setAttribute("content", content);
		
		return "/content.jsp";
	}

}
