package hansung.ac.kr.j2ee.bbs;

import hansung.ac.kr.j2ee.bbs.db.BBS_Dto;
import hansung.ac.kr.j2ee.bbs.db.BBS_OracleDao;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateArticleImpl implements BBS_Service {
	BBS_OracleDao oracleDao;
	BBS_Dto article;

	@Override
	public String BBS_hansung(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {

		req.setCharacterEncoding("utf-8");
		
		oracleDao = BBS_OracleDao.getInstance();
		article = new BBS_Dto();
		
		article.setArticle_num(Integer.parseInt(req.getParameter("article_num")));
		article.setTitle(req.getParameter("title"));
		article.setContent(req.getParameter("content"));
		article.setFname(req.getParameter("fname"));
		
		oracleDao.updateArticle(article);
		
		req.setAttribute("pageNum", req.getParameter("pageNum"));
		
		return "/list.bbs";
	}

}
