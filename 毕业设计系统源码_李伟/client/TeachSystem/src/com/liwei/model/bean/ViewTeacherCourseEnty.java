package com.liwei.model.bean;

public class ViewTeacherCourseEnty {
	public ViewTeacherCourseEnty(int cno, String cname, String classtime,
			String cadress, String content) {
		super();
		this.cno = cno;
		this.cname = cname;
		this.classtime = classtime;
		this.cadress = cadress;
		this.content = content;
	}
	private int cno;
	private String cname;
	private String classtime;
	private String cadress;
	private String content;
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getClasstime() {
		return classtime;
	}
	public void setClasstime(String classtime) {
		this.classtime = classtime;
	}
	public String getCadress() {
		return cadress;
	}
	public void setCadress(String cadress) {
		this.cadress = cadress;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	

}
