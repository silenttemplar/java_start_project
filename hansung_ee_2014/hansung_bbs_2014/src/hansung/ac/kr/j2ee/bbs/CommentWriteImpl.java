package hansung.ac.kr.j2ee.bbs;

import hansung.ac.kr.j2ee.bbs.db.BBS_OracleDao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

public class CommentWriteImpl implements BBS_Service {
	
	BBS_OracleDao oracleDao;
	CommentDTO comment;
	HttpSession hs;
	ArrayList<CommentDTO> commentList;

	@Override
	public String BBS_hansung(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		
		resp.setCharacterEncoding("utf-8");
		
		System.out.println("CommentWriteImpl 실행");
		
		oracleDao = BBS_OracleDao.getInstance();
		comment = new CommentDTO();
		hs = req.getSession();
		
		
		comment.setArticle_num(Integer.parseInt(req.getParameter("article_num")));
		comment.setComment_content(req.getParameter("comment_content"));
		comment.setId(hs.getAttribute("id").toString());
		
		System.out.println(req.getParameter("article_num")+", "+req.getParameter("comment_content")
				+", "+hs.getAttribute("id").toString());
		
		
		commentList = oracleDao.insertComment(comment);
		
		PrintWriter pw = resp.getWriter();
		JSONArray ja = new JSONArray(commentList);
		pw.println(ja);
		
		if(pw != null){
			pw.close();
		}
		
		return null;
	}

}
