package com.stm.bean;

public class Student {
	public Student(int sno, String sname, String ssex, String sicon, int sage, int dno, String suser,
			String spassWord) {
		super();
		this.sno = sno;
		Sname = sname;
		Ssex = ssex;
		Sicon = sicon;
		Sage = sage;
		Dno = dno;
		Suser = suser;
		SpassWord = spassWord;
	}
	private int sno;
	private String Sname;
	private String Ssex;
	private String Sicon;
	private int Sage;
	private  int Dno;
	private String Suser;
	private String SpassWord;
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getSname() {
		return Sname;
	}
	public void setSname(String sname) {
		Sname = sname;
	}
	public String getSsex() {
		return Ssex;
	}
	public void setSsex(String ssex) {
		Ssex = ssex;
	}
	public String getSicon() {
		return Sicon;
	}
	public void setSicon(String sicon) {
		Sicon = sicon;
	}
	public int getSage() {
		return Sage;
	}
	public void setSage(int sage) {
		Sage = sage;
	}
	public int getDno() {
		return Dno;
	}
	public void setDno(int dno) {
		Dno = dno;
	}
	public String getSuser() {
		return Suser;
	}
	public void setSuser(String suser) {
		Suser = suser;
	}
	public String getSpassWord() {
		return SpassWord;
	}
	public void setSpassWord(String spassWord) {
		SpassWord = spassWord;
	}
	

}
