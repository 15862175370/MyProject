package com.stm.bean;

public class Teacher {
	public Teacher(int tno, String tname, String tsex, String tage, String ticon, String dno, String tprof,
			String tuser, String tpass) {
		super();
		Tno = tno;
		Tname = tname;
		Tsex = tsex;
		Tage = tage;
		Ticon = ticon;
		Dno = dno;
		Tprof = tprof;
		Tuser = tuser;
		Tpass = tpass;
	}
	private int Tno;
	private String Tname;
	private String Tsex;
	private String Tage;
	private String Ticon;
	private String Dno;
	private String Tprof;
	private String Tuser;
	private String Tpass;
	public int getTno() {
		return Tno;
	}
	public void setTno(int tno) {
		Tno = tno;
	}
	public String getTname() {
		return Tname;
	}
	public void setTname(String tname) {
		Tname = tname;
	}
	public String getTsex() {
		return Tsex;
	}
	public void setTsex(String tsex) {
		Tsex = tsex;
	}
	public String getTage() {
		return Tage;
	}
	public void setTage(String tage) {
		Tage = tage;
	}
	public String getTicon() {
		return Ticon;
	}
	public void setTicon(String ticon) {
		Ticon = ticon;
	}
	public String getDno() {
		return Dno;
	}
	public void setDno(String dno) {
		Dno = dno;
	}
	public String getTprof() {
		return Tprof;
	}
	public void setTprof(String tprof) {
		Tprof = tprof;
	}
	public String getTuser() {
		return Tuser;
	}
	public void setTuser(String tuser) {
		Tuser = tuser;
	}
	public String getTpass() {
		return Tpass;
	}
	public void setTpass(String tpass) {
		Tpass = tpass;
	}

}
