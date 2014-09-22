package hansung.ac.kr.j2ee.bbs;

import hansung.ac.kr.j2ee.bbs.db.BBS_OracleDao;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteArticleImpl implements BBS_Service {
	BBS_OracleDao oracleDao;

	@Override
	public String BBS_hansung(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		oracleDao = BBS_OracleDao.getInstance();
		oracleDao.deleteArticle(Integer.parseInt(req.getParameter("article_num")));
			
		return "/list.bbs?pageNum="+req.getParameter("pageNum");
	}

}
