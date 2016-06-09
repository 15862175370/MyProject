package com.stm.bean;

public class KaoQin {
	public KaoQin(String sname, String ip, String state) {
		super();
		this.sname = sname;
		this.ip = ip;
		this.state = state;
	}
	private String sname;
	private String ip;
	private String state;
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

}
