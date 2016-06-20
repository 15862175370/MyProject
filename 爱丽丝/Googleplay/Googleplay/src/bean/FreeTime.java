package bean;

public class FreeTime {
	public FreeTime(String day_id, String t_id, String state) {
		super();
		this.day_id = day_id;
		this.t_id = t_id;
		this.state = state;
	}
	private String day_id;
	private String t_id;
	private String state;
	public String getDay_id() {
		return day_id;
	}
	public void setDay_id(String day_id) {
		this.day_id = day_id;
	}
	public String getT_id() {
		return t_id;
	}
	public void setT_id(String t_id) {
		this.t_id = t_id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
