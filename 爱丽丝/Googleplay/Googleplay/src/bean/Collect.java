package bean;

public class Collect {
private int u_id;
private int b_id;
private String u_name;
private String b_name;
private String b_icon;
private String b_position;
private String s_name;
private int state;
public int getU_id() {
	return u_id;
}
public void setU_id(int u_id) {
	this.u_id = u_id;
}
public int getB_id() {
	return b_id;
}
public void setB_id(int b_id) {
	this.b_id = b_id;
}
public String getU_name() {
	return u_name;
}
public void setU_name(String u_name) {
	this.u_name = u_name;
}
public String getB_name() {
	return b_name;
}
public void setB_name(String b_name) {
	this.b_name = b_name;
}
public String getB_icon() {
	return b_icon;
}
public void setB_icon(String b_icon) {
	this.b_icon = b_icon;
}
public String getB_position() {
	return b_position;
}
public void setB_position(String b_position) {
	this.b_position = b_position;
}
public String getS_name() {
	return s_name;
}
public void setS_name(String s_name) {
	this.s_name = s_name;
}
public int getState() {
	return state;
}
public void setState(int state) {
	this.state = state;
}
public Collect(int u_id, int b_id, String u_name, String b_name, String b_icon, String b_position, String s_name,
		int state) {
	super();
	this.u_id = u_id;
	this.b_id = b_id;
	this.u_name = u_name;
	this.b_name = b_name;
	this.b_icon = b_icon;
	this.b_position = b_position;
	this.s_name = s_name;
	this.state = state;
}

public Collect(int b_id, String b_name, String b_icon, String b_position, int state) {
	super();
	this.b_id = b_id;
	this.b_name = b_name;
	this.b_icon = b_icon;
	this.b_position = b_position;
	this.state = state;
}



}
