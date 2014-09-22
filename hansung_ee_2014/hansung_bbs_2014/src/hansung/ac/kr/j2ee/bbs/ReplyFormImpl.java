package hansung.ac.kr.j2ee.bbs;

import hansung.ac.kr.j2ee.bbs.db.BBS_Dto;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReplyFormImpl implements BBS_Service {
	BBS_Dto reply;

	@Override
	public String BBS_hansung(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		reply = new BBS_Dto();
		
		reply.setDepth(Integer.parseInt(req.getParameter("depth")));
		reply.setGroup_id(Integer.parseInt(req.getParameter("group_id")));
		reply.setPos(Integer.parseInt(req.getParameter("pos")));
		
		req.setAttribute("pageNum", req.getParameter("pageNum"));
		req.setAttribute("reply", reply);
		req.setAttribute("id", req.getSession().getAttribute("id"));
		
		return "/replyForm.jsp";
	}

}
