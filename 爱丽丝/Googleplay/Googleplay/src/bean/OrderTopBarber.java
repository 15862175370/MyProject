package bean;

import android.R.integer;

public class OrderTopBarber {
	public OrderTopBarber(int b_id, String b_name, 
			String s_name) {
		super();
		this.b_id = b_id;
		this.b_name = b_name;
	
		this.s_name = s_name;
	}
	private int b_id;
	private String b_name;
	
	private String s_name;
	public int getB_id() {
		return b_id;
	}
	public void setB_id(int b_id) {
		this.b_id = b_id;
	}
	public String getB_name() {
		return b_name;
	}
	public void setB_name(String b_name) {
		this.b_name = b_name;
	}
	
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

}
