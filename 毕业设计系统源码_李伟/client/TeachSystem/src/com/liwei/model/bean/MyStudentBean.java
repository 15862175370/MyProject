package com.liwei.model.bean;

import java.io.Serializable;

public class MyStudentBean  implements Serializable{
	
	public MyStudentBean(String sno, String sname, String ssex, String sicon,
			String sage, String dname, String phone) {
		super();
		this.sno = sno;
		this.sname = sname;
		this.ssex = ssex;
		this.sicon = sicon;
		this.sage = sage;
		this.dname = dname;
		this.phone = phone;
	}

	private String sno,sname,ssex,sicon,sage,dname,phone;

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSsex() {
		return ssex;
	}

	public void setSsex(String ssex) {
		this.ssex = ssex;
	}

	public String getSicon() {
		return sicon;
	}

	public void setSicon(String sicon) {
		this.sicon = sicon;
	}

	public String getSage() {
		return sage;
	}

	public void setSage(String sage) {
		this.sage = sage;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
