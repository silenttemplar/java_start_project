package hansung.ac.kr.j2ee.bbs;

import hansung.ac.kr.j2ee.bbs.db.BBS_Dto;
import hansung.ac.kr.j2ee.bbs.db.BBS_OracleDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListImpl implements BBS_Service {
	
	/*
	 * [ë³€?˜ëª… : ?¤ëª…]
	 * 
	 *  count : ì´?ê¸€??ê°œìˆ˜
	 *  articleList : ?´ë‹¹ ?˜ì´ì§€??ê¸€ë¥?10ê°??¨ìœ„ë¡??´ëŠ” ArrayList
	 *  page : ?˜ì´ì§?ê´€ë¦??´ë˜??
	 *  pageNum : pageë¥?ê´€ë¦¬í•˜ê¸??„í•œ ë³€??
	 *  pageSize : ê²Œì‹œê¸€ 10ê°??¨ìœ„ ì§€?•ì„ ?„í•œ ë³€??
	 *  pageBlock : page ë§í¬ë¥?10ê°??¨ìœ„ ì§€?•ì„ ?„í•œ ë³€??
	 */
	int count;
	ArrayList<BBS_Dto> articleList;
	Page page;
	BBS_OracleDao dao;
	String pageNum;
	final int pageSize = 10;
	final int pageBlock = 10;

	@Override
	public String BBS_hansung(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {

		pageNum = req.getParameter("pageNum");
		if(pageNum == null){
			pageNum = "1";
		}
		
		dao = BBS_OracleDao.getInstance();
		count = dao.getArticleNum();
		
		page = new Page(Integer.parseInt(pageNum), count, pageSize, pageBlock);
		
		if(count > 0) {
			articleList = dao.getArticles(page.getStartRow(), page.getEndRow());
		}else {
			articleList = null;
		}
		
		//?˜ê²¨ì¤??¸ì ì§€??
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("pageCode", page.getSb().toString());
		req.setAttribute("articleList", articleList);		
		req.setAttribute("count", count);
		
		return "/list.jsp";
	}

}
