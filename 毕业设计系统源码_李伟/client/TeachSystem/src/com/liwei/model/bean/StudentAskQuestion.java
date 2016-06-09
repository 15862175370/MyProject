package com.liwei.model.bean;

import android.R.integer;

public class StudentAskQuestion {
	public StudentAskQuestion(int sq_id, int state, int sno, String sname,
			int tno, String tname, String questionContentString) {
		super();
		this.sq_id = sq_id;
		this.state = state;
		this.sno = sno;
		this.sname = sname;
		this.tno = tno;
		this.tname = tname;
		this.questionContentString = questionContentString;
	}
	public StudentAskQuestion(int sno, String sname, int tno, String tname,
			String questionContentString) {
		super();
		this.sno = sno;
		this.sname = sname;
		this.tno = tno;
		this.tname = tname;
		this.questionContentString = questionContentString;
	}
	private int sq_id;
	private int state;
	private int sno;
	private String sname;
	private int tno;
	private String tname;
	private String questionContentString;
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public int getTno() {
		return tno;
	}
	public void setTno(int tno) {
		this.tno = tno;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getQuestionContentString() {
		return questionContentString;
	}
	public void setQuestionContentString(String questionContentString) {
		this.questionContentString = questionContentString;
	}
	public int getSq_id() {
		return sq_id;
	}
	public void setSq_id(int sq_id) {
		this.sq_id = sq_id;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}

}
