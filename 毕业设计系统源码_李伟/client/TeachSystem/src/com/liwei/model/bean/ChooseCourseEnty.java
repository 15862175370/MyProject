package com.liwei.model.bean;

import android.R.integer;


public class ChooseCourseEnty {
	
	public ChooseCourseEnty(int cno, String cname, String classTime,
			String cadress, String courseContent, String teacherName, int tno) {
		super();
		this.cno = cno;
		this.cname = cname;
		this.classTime = classTime;
		this.cadress = cadress;
		this.courseContent = courseContent;
		this.teacherName = teacherName;
		Tno = tno;
	}
	private int cno;
	private String cname;
	private String classTime;
	private String cadress;
	private String courseContent;
	private String teacherName;
	private int Tno;
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
	public String getClassTime() {
		return classTime;
	}
	public void setClassTime(String classTime) {
		this.classTime = classTime;
	}
	public String getCadress() {
		return cadress;
	}
	public void setCadress(String cadress) {
		this.cadress = cadress;
	}
	public String getCourseContent() {
		return courseContent;
	}
	public void setCourseContent(String courseContent) {
		this.courseContent = courseContent;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public int getTno() {
		return Tno;
	}
	public void setTno(int tno) {
		Tno = tno;
	}
	
}
