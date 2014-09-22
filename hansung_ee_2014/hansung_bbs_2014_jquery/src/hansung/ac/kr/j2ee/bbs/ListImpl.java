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
	 * [변?�명 : ?�명]
	 * 
	 *  count : �?글??개수
	 *  articleList : ?�당 ?�이지??글�?10�??�위�??�는 ArrayList
	 *  page : ?�이�?관�??�래??
	 *  pageNum : page�?관리하�??�한 변??
	 *  pageSize : 게시글 10�??�위 지?�을 ?�한 변??
	 *  pageBlock : page 링크�?10�??�위 지?�을 ?�한 변??
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
		
		//?�겨�??�자 지??
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("pageCode", page.getSb().toString());
		req.setAttribute("articleList", articleList);		
		req.setAttribute("count", count);
		
		return "/list.jsp";
	}

}
