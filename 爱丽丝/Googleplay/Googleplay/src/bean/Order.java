package bean;

public class Order {
     int o_id;
     int u_id;
     int b_id;
     String times1;
     String time2;
     int servid;
     String state;
	public int getO_id() {
		return o_id;
	}
	public void setO_id(int o_id) {
		this.o_id = o_id;
	}
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
	public String getTimes1() {
		return times1;
	}
	public void setTimes1(String times1) {
		this.times1 = times1;
	}
	public String getTime2() {
		return time2;
	}
	public void setTime2(String time2) {
		this.time2 = time2;
	}
	public int getServid() {
		return servid;
	}
	public void setServid(int servid) {
		this.servid = servid;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Order(int o_id, int u_id, int b_id, String times1, String time2, int servid, String state) {
		super();
		this.o_id = o_id;
		this.u_id = u_id;
		this.b_id = b_id;
		this.times1 = times1;
		this.time2 = time2;
		this.servid = servid;
		this.state = state;
	}
     
}
