package hansung.ac.kr.j2ee.bbs.db;

import hansung.ac.kr.j2ee.bbs.CommentDTO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BBS_OracleDao implements BBS_DaoService {
	private final String serverIP="127.0.0.1";
	private final String portNum="1521";
	private final String id="templar";
	private final String pw="1234";
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	ArrayList<BBS_Dto> articleList;
	ArrayList<CommentDTO> commentList;
	static BBS_OracleDao dbcon = new BBS_OracleDao();
	
	private BBS_OracleDao(){
		
	}
	
	public static BBS_OracleDao getInstance(){
		return dbcon;
	}
	
	public Connection getConnection(){

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url="jdbc:oracle:thin:@"+serverIP+":"+portNum+":XE";
			con = DriverManager.getConnection(url,id,pw);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return con;
	}

	@Override
	public void insertArticle(BBS_Dto article) throws SQLException, IOException {
		con = this.getConnection();
		
			/*
			 *  table bbs
			 *  article_num(1), id(2), title(3), content(4), depth(5)
			 *  , pos(6), group_id(7), hit(8), write_date(9), fname(10)
			 */
			String sql = "insert into bbs values("
					+ "bbs_seq.nextval,?,?,?,?,?"
					+ ",bbs_seq.currval,?,sysdate,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getId());
			pstmt.setString(2, article.getTitle());
			pstmt.setString(3, article.getContent());
			pstmt.setInt(4, 0);
			pstmt.setInt(5, 0);
			pstmt.setInt(6, 0);
			pstmt.setString(7, article.getFname());
			pstmt.executeUpdate();
			
			closeConnection();

	}//insertAriticle end
	
	public int getArticleNum() throws SQLException, IOException {
		int count=0;
		con = this.getConnection();
	
		pstmt = con.prepareStatement("select count(*) from bbs");
		rs = pstmt.executeQuery();
		
		if(rs.next()){
			count = rs.getInt(1);
		}
		closeConnection();
		
		return count;
	}
	
	public ArrayList<BBS_Dto> getArticles(int startRow, int endRow) throws SQLException, IOException {
		articleList = new ArrayList<BBS_Dto>();
		con = this.getConnection();
		BBS_Dto article;
		
		StringBuffer query = new StringBuffer();
		query.append("select *");
		query.append(" from (select rownum rm, human.* from"
								+ " (select * from bbs order by group_id desc, pos)"
								+ "	human)");
		query.append(" where rm between ? and ?");
		pstmt = con.prepareStatement(query.toString());
		pstmt.setInt(1, startRow);
		pstmt.setInt(2, endRow);
		rs = pstmt.executeQuery();
		
		while(rs.next()){
			article = new BBS_Dto();
			article.setArticle_num(rs.getInt("article_num"));
			article.setTitle(rs.getString("title"));
			article.setId(rs.getString("id"));
			article.setContent(rs.getString("content"));
			article.setHit(rs.getInt("hit"));
			article.setDepth(rs.getInt("depth"));
			article.setGroup_id(rs.getInt("group_id"));
			article.setWrite_date(rs.getTimestamp("write_date"));
			article.setPos(rs.getInt("pos"));
			article.setFname(rs.getString("fname"));
			
			articleList.add(article);
		}
		
		closeConnection();

		return articleList;
	}
	
	public BBS_Dto getContent(int article_num) throws SQLException, IOException{
		con = this.getConnection();
		BBS_Dto  content = new BBS_Dto();
		
		pstmt = con.prepareStatement("select * from bbs where article_num = ?");
		pstmt.setInt(1, article_num);
		rs = pstmt.executeQuery();
		
		if(rs.next()){
			content.setArticle_num(rs.getInt("article_num"));
			content.setId(rs.getString("id"));
			content.setTitle(rs.getString("title"));
			content.setWrite_date(rs.getTimestamp("write_date"));
			content.setContent(rs.getString("content"));
			content.setHit(rs.getInt("hit"));
			content.setFname(rs.getString("fname"));
			content.setDepth(rs.getInt("depth"));
			content.setPos(rs.getInt("pos"));
			content.setGroup_id(rs.getInt("group_id"));
		}
		
		closeConnection();
		
		return content;
	}
	
	public int login_check(String id, String pass) throws SQLException, IOException{
		int status = 0;
		con = this.getConnection();
		
		String sql = "select * from login where id =? and pw = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, pass);
		rs = pstmt.executeQuery();
		
		if(rs.next()){
			if(pass.equals(rs.getString("pw"))){
				status = 1;
			}else{
				status = 2;
			}
		}
		
		return status;
	}
	
	public void insert_Reply(BBS_Dto reply)  throws SQLException, IOException{
		con = this.getConnection();
		String sql = "update bbs set pos = pos+1 where group_id=? and pos>?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, reply.getGroup_id());
		pstmt.setInt(2, reply.getPos());
		pstmt.executeUpdate();
		
		/*
		 *  table bbs
		 *  article_num(1), id(2), title(3), content(4), depth(5)
		 *  , pos(6), group_id(7), hit(8), write_date(9), fname(10)
		 */
		String sql2 = "insert into bbs values("
				+ "bbs_seq.nextval,?,?,?,?,?"
				+ ",?,?,sysdate,?)";
		pstmt = con.prepareStatement(sql2);
		pstmt.setString(1, reply.getId());
		pstmt.setString(2, reply.getTitle());
		pstmt.setString(3, reply.getContent());
		pstmt.setInt(4, reply.getDepth());
		pstmt.setInt(5, reply.getPos()+1);
		pstmt.setInt(6, reply.getGroup_id());
		pstmt.setInt(7,0);
		pstmt.setString(8, reply.getFname());
		pstmt.executeUpdate();
		
		closeConnection();
	}

	public void deleteArticle(int article_num) throws SQLException, IOException{
		con = this.getConnection();
		StringBuffer sb = new StringBuffer();
		sb.append("delete from bbs where article_num = ?");
		pstmt = con.prepareStatement(sb.toString());
		pstmt.setInt(1, article_num);
		pstmt.executeUpdate();
		
		closeConnection();
	}
	
	public void updateArticle(BBS_Dto article) throws SQLException, IOException{
		con = this.getConnection();
		String sql = "update bbs set title=?, content=?, fname=? where article_num=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, article.getTitle());
		pstmt.setString(2, article.getContent());
		pstmt.setString(3, article.getFname());
		pstmt.setInt(4,article.getArticle_num());
		pstmt.executeUpdate();
		
		closeConnection();
	}

	@Override
	public ArrayList<CommentDTO> getComment(int article_num) throws SQLException, IOException {
		con = this.getConnection();
		commentList = new ArrayList<CommentDTO>();
			
		String sql = "select * from comment_table where article_num = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, article_num);
		
		rs = pstmt.executeQuery();
		
		while(rs.next()){
			CommentDTO obj  = new CommentDTO();
			obj.setArticle_num(rs.getInt("article_num"));
			obj.setComment_num(rs.getInt("comment_num"));
			obj.setComment_content(rs.getString("comment_content"));
			obj.setComment_write_date(rs.getTimestamp("comment_write_date"));
			obj.setId(rs.getString("id"));
			
			commentList.add(obj);
		}
		
		closeConnection();
		
		return commentList;
	}
	
	public ArrayList<CommentDTO> insertComment(CommentDTO comment) throws SQLException, IOException{
		con = this.getConnection();
		commentList = new ArrayList<CommentDTO>();
		/*
		 * 	comment_table
		 *  	comment_num, id, comment_content, comment_write_date, article_num
		 */
		String sql = "insert into comment_table values(comment_seq.nextval,?,?,sysdate,?)";
		pstmt= con.prepareStatement(sql);
		pstmt.setString(1, comment.getId());
		pstmt.setString(2, comment.getComment_content());
		pstmt.setInt(3, comment.getArticle_num());
		
		pstmt.executeUpdate();
		
		String sql2 = "select * from comment_table where article_num = ?";
		pstmt = con.prepareStatement(sql2);
		pstmt.setInt(1, comment.getArticle_num());
		
		rs = pstmt.executeQuery();
		
		while(rs.next()){
			CommentDTO obj  = new CommentDTO();
			obj.setArticle_num(rs.getInt("article_num"));
			obj.setComment_num(rs.getInt("comment_num"));
			obj.setComment_content(rs.getString("comment_content"));
			obj.setComment_write_date(rs.getTimestamp("comment_write_date"));
			obj.setId(rs.getString("id"));
			
			commentList.add(obj);
		}
		
		closeConnection();
		
		return commentList;
	}
	
	public void closeConnection(){
		try{
			if(rs != null){
				rs.close();
			}
			pstmt.close();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}//closeConnection() end


}
