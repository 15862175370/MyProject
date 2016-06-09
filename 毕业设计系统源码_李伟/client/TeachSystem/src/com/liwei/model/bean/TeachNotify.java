package com.liwei.model.bean;

import java.sql.Timestamp;

public class TeachNotify {
	public TeachNotify(int notify_id, String notify_content, Timestamp notify_time) {
		super();
		this.notify_id = notify_id;
		this.notify_content = notify_content;
		this.notify_time = notify_time;
	}
	private int notify_id;
	private String notify_content;
	private Timestamp notify_time;
	public int getNotify_id() {
		return notify_id;
	}
	public void setNotify_id(int notify_id) {
		this.notify_id = notify_id;
	}
	public String getNotify_content() {
		return notify_content;
	}
	public void setNotify_content(String notify_content) {
		this.notify_content = notify_content;
	}
	public Timestamp getNotify_time() {
		return notify_time;
	}
	public void setNotify_time(Timestamp notify_time) {
		this.notify_time = notify_time;
	}

}
