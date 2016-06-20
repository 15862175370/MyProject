package bean;

public class InfoMessageComment {
	private int im_id;
	private int u_id;
	private String im_reply_content;
	private String im_reply_time;
	private String u_name;
	private String icon;
	public int getIm_id() {
		return im_id;
	}
	public void setIm_id(int im_id) {
		this.im_id = im_id;
	}
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public String getIm_reply_content() {
		return im_reply_content;
	}
	public void setIm_reply_content(String im_reply_content) {
		this.im_reply_content = im_reply_content;
	}
	public String getIm_reply_time() {
		return im_reply_time;
	}
	public void setIm_reply_time(String im_reply_time) {
		this.im_reply_time = im_reply_time;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public InfoMessageComment(String im_reply_content, String im_reply_time,
			String u_name, String icon) {
		super();
		this.im_reply_content = im_reply_content;
		this.im_reply_time = im_reply_time;
		this.u_name = u_name;
		this.icon = icon;
	}
	public InfoMessageComment(int im_id, int u_id, String im_reply_content,
			String im_reply_time, String u_name, String icon) {
		super();
		this.im_id = im_id;
		this.u_id = u_id;
		this.im_reply_content = im_reply_content;
		this.im_reply_time = im_reply_time;
		this.u_name = u_name;
		this.icon = icon;
	}
	public InfoMessageComment(int im_id, int u_id, String im_reply_content,
			String im_reply_time) {
		super();
		this.im_id = im_id;
		this.u_id = u_id;
		this.im_reply_content = im_reply_content;
		this.im_reply_time = im_reply_time;
	}
	
	
}
