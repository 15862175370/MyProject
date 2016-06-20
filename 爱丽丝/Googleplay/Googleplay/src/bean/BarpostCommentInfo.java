package bean;

import java.sql.Timestamp;

public class BarpostCommentInfo {
    private String u_name;
    private String content;
    private Timestamp time;
	public BarpostCommentInfo(String u_name, String content, Timestamp time) {
		super();
		this.u_name = u_name;
		this.content = content;
		this.time = time;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getContent() {
		return content;
	}
	public BarpostCommentInfo() {
		super();
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
}
