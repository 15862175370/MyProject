package bean;

import java.sql.Timestamp;




public class ActivityComment {
	public ActivityComment(String s_icon, int u_id, String u_name,
			int act_com_id, int m_act_id, String content, Timestamp time) {
		super();
		this.s_icon = s_icon;
		this.u_id = u_id;
		this.u_name = u_name;
		this.act_com_id = act_com_id;
		this.m_act_id = m_act_id;
		this.content = content;
		this.time = time;
	}
	private String s_icon;
	private int u_id;
	private String u_name;
	private int act_com_id;
	private int m_act_id;
	private String content;
	private Timestamp time;
	public String getS_icon() {
		return s_icon;
	}
	public void setS_icon(String s_icon) {
		this.s_icon = s_icon;
	}
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public int getAct_com_id() {
		return act_com_id;
	}
	public void setAct_com_id(int act_com_id) {
		this.act_com_id = act_com_id;
	}
	public int getM_act_id() {
		return m_act_id;
	}
	public void setM_act_id(int m_act_id) {
		this.m_act_id = m_act_id;
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
