package hansung.ac.kr.j2ee.bbs.db;

import java.sql.Timestamp;

public class BBS_Dto {

	private String id;
	private String title;
	private String content;
	private int article_num;
	private int depth;
	private int pos;
	private int group_id;
	private int hit;
	private Timestamp write_date;
	private String fname;
	
	
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getArticle_num() {
		return article_num;
	}
	public void setArticle_num(int article_num) {
		this.article_num = article_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	public Timestamp getWrite_date() {
		return write_date;
	}
	public void setWrite_date(Timestamp write_date) {
		this.write_date = write_date;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	@Override
	public String toString() {
		return "BBS_Dto [id=" + id + ", title=" + title + ", content="
				+ content + ", depth=" + depth + ", pos=" + pos + ", group_id="
				+ group_id + ", write_date=" + write_date + ", fname=" + fname
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + article_num;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BBS_Dto other = (BBS_Dto) obj;
		if (article_num != other.article_num)
			return false;
		return true;
	}

}
