package com.stm.bean;

public class StudentMessageBean {
	public StudentMessageBean(String name, String message, String systemtime) {
		super();
		this.name = name;
		this.message = message;
		this.systemtime = systemtime;
	}
private String name;
private String message;
private String systemtime;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public String getSystemtime() {
	return systemtime;
}
public void setSystemtime(String systemtime) {
	this.systemtime = systemtime;
}
	
}
