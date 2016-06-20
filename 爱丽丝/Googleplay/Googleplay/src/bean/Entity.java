package bean;

import java.sql.Timestamp;

public class Entity {
	private int o_id;
	private Timestamp time2;
	private String phone;
	private String state;
	public int getO_id() {
		return o_id;
	}
	public void setO_id(int o_id) {
		this.o_id = o_id;
	}
	
	public Timestamp getTime2() {
		return time2;
	}
	public void setTime2(Timestamp time2) {
		this.time2 = time2;
	}

	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Entity(int o_id, Timestamp time2, String phone, String state) {
		super();
		this.o_id = o_id;
		this.time2 = time2;
		this.phone = phone;
		this.state = state;
	}
	

}
