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
	 * [변수명 : 설명]
	 * 
	 *  count : 총 글의 개수
	 *  aricleList : 해당 페이지의 글를 10개 단위로 담는 ArrayList
	 *  page : 페이징 관리 클래스
	 *  pageNum : page를 관리하기 위한 변수
	 *  pageSize : 게시글 10개 단위 지정을 위한 변수
	 *  pageBlock : page 링크를 10개 단위 지정을 위한 변수
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
		
		//넘겨줄 인자 지정
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("pageCode", page.getSb().toString());
		req.setAttribute("articleList", articleList);		
		req.setAttribute("count", count);
		
		return "/list.jsp";
	}

}
