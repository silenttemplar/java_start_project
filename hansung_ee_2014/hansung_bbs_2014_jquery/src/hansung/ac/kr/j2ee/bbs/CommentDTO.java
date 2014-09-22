package hansung.ac.kr.j2ee.bbs;

import java.sql.Timestamp;

public class CommentDTO {
	int comment_num;
	String id;
	Timestamp comment_write_date;
	String comment_content;
	int article_num;
	
	public int getComment_num() {
		return comment_num;
	}
	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Timestamp getComment_write_date() {
		return comment_write_date;
	}
	public void setComment_write_date(Timestamp comment_write_date) {
		this.comment_write_date = comment_write_date;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public int getArticle_num() {
		return article_num;
	}
	public void setArticle_num(int article_num) {
		this.article_num = article_num;
	}
}
