package com.liwei.model.bean;

import java.sql.Timestamp;

import android.R.integer;

public class TeachNews {
	public TeachNews() {
		super();
	}
	public TeachNews(String content, Timestamp timestamp) {
		super();
		this.content = content;
		this.timestamp = timestamp;
	}
	public TeachNews(int id, String content, Timestamp timestamp) {
		super();
		this.id = id;
		this.content = content;
		this.timestamp = timestamp;
	}
	private int id;
	private String content;
	private Timestamp timestamp;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
