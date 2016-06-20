package bean;

import java.sql.Timestamp;




public class UserPost {
	public UserPost(String u_icon, int u_id, int countgood, String u_name,
			String image, String content, Timestamp time) {
		super();
		this.u_icon = u_icon;
		this.u_id = u_id;
		this.countgood = countgood;
		this.u_name = u_name;
		this.image = image;
		this.content = content;
		this.time = time;
	}
	private String u_icon;
	private int u_id;
	private int countgood;
	private String u_name;
	private String image;
	private String content;
	private Timestamp time;
	public String getU_icon() {
		return u_icon;
	}
	public void setU_icon(String u_icon) {
		this.u_icon = u_icon;
	}
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public int getCountgood() {
		return countgood;
	}
	public void setCountgood(int countgood) {
		this.countgood = countgood;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getContent() {
		return content;
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
