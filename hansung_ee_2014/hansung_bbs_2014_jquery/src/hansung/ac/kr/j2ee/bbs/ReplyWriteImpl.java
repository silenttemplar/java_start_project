package hansung.ac.kr.j2ee.bbs;

import hansung.ac.kr.j2ee.bbs.db.BBS_Dto;
import hansung.ac.kr.j2ee.bbs.db.BBS_OracleDao;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReplyWriteImpl implements BBS_Service {
	
	BBS_OracleDao oracleDao;
	BBS_Dto reply;

	@Override
	public String BBS_hansung(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		
		oracleDao = BBS_OracleDao.getInstance();
		reply = new BBS_Dto();

		int depth = Integer.parseInt(req.getParameter("depth")) + 1;
		
		reply.setContent(req.getParameter("content"));
		reply.setTitle(req.getParameter("title"));
		reply.setId(req.getSession().getAttribute("id").toString());
		reply.setGroup_id(Integer.parseInt(req.getParameter("group_id")));
		reply.setDepth(depth);
		reply.setPos(Integer.parseInt(req.getParameter("pos")));
		reply.setFname(null);

		oracleDao.insert_Reply(reply);

		return "/list.bbs?pageNum="+req.getParameter("pageNum");
	}

}
