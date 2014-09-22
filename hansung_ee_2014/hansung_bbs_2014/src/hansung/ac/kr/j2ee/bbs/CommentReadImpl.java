package hansung.ac.kr.j2ee.bbs;

import hansung.ac.kr.j2ee.bbs.db.BBS_OracleDao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

public class CommentReadImpl implements BBS_Service {
	BBS_OracleDao oracleDao;
	ArrayList<CommentDTO> commentList;
	
	@Override
	public String BBS_hansung(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		System.out.println("CommentReadImpl  실행");
		resp.setCharacterEncoding("utf-8");
		
		oracleDao = BBS_OracleDao.getInstance();
		commentList = new ArrayList<CommentDTO>();
		commentList = oracleDao.getComment(Integer.parseInt(req.getParameter("article_num")));
		
		JSONArray ja = new JSONArray(commentList);
		PrintWriter pw = resp.getWriter();
		pw.println(ja);
		
		if(pw != null){
			pw.close();
		}
		
		return null;
	}
}