package com.stm.bean;

public class NiChengAndIcon {
	public NiChengAndIcon(String userName, String icon) {
		super();
		this.userName = userName;
		this.icon = icon;
	}
	private String userName;
	private String icon;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}

}
