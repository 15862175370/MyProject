package bean;

import java.sql.Timestamp;

public class BarpostItem {
	 private String b_name;
	  private String b_position;
	  private String b_icon;
	  private String content;
	  private String image;
	  private Timestamp time;
	  private int commentCount;
	  private int zanCount;
	  private String belong;
	public BarpostItem(String b_name, String b_position, String b_icon, String content, String image, Timestamp time,
			int commentCount, int zanCount, String belong) {
		super();
		this.b_name = b_name;
		this.b_position = b_position;
		this.b_icon = b_icon;
		this.content = content;
		this.image = image;
		this.time = time;
		this.commentCount = commentCount;
		this.zanCount = zanCount;
		this.belong = belong;
	}
	public String getBelong() {
		return belong;
	}
	public void setBelong(String belong) {
		this.belong = belong;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public int getZanCount() {
		return zanCount;
	}
	public void setZanCount(int zanCount) {
		this.zanCount = zanCount;
	}
	public String getB_name() {
		return b_name;
	}
	public void setB_name(String b_name) {
		this.b_name = b_name;
	}
	public String getB_position() {
		return b_position;
	}
	public void setB_position(String b_position) {
		this.b_position = b_position;
	}
	public String getB_icon() {
		return b_icon;
	}
	public void setB_icon(String b_icon) {
		this.b_icon = b_icon;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
}
