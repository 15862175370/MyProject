package com.stm.bean;



public class ViewScoreEntry {
	public ViewScoreEntry(int sno, String sname, String cname, int score) {
		super();
		this.sno = sno;
		this.sname = sname;
		this.cname = cname;
		this.score = score;
	}
	private int sno;
	private String sname;
	private String cname;
	private int score;
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
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	

}
