package com.liwei.model.bean;

import java.sql.Timestamp;

public class ViewTeacherAdviceBean {
	public ViewTeacherAdviceBean(String tname, String content,
			Timestamp timestamp) {
		super();
		this.tname = tname;
		this.content = content;
		this.timestamp = timestamp;
	}
	private String tname,content;
	private Timestamp timestamp;
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

}
