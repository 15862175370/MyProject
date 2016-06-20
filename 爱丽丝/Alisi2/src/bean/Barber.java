package bean;

public class Barber {
	private int b_id;
	  private String b_name;
	  private String b_position;
	  private String b_icon;
	  private String pro_degree;
	  private String ser_degree;
	  private String pop_degree;
	  private int belong;
	  private String b_pass;
	  private String b_desc;
	public int getB_id() {
		return b_id;
	}
	public String getB_desc() {
		return b_desc;
	}
	public void setB_desc(String b_desc) {
		this.b_desc = b_desc;
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
	public String getB_position() {
		return b_position;
	}
	public void setB_position(String b_position) {
		this.b_position = b_position;
	}
	public String getB_icon() {
		return b_icon;
	}
	public void setB_icon(String b_icon) {
		this.b_icon = b_icon;
	}
	public String getPro_degree() {
		return pro_degree;
	}
	public void setPro_degree(String pro_degree) {
		this.pro_degree = pro_degree;
	}
	public String getSer_degree() {
		return ser_degree;
	}
	public void setSer_degree(String ser_degree) {
		this.ser_degree = ser_degree;
	}
	public String getPop_degree() {
		return pop_degree;
	}
	public void setPop_degree(String pop_degree) {
		this.pop_degree = pop_degree;
	}
	public int getBelong() {
		return belong;
	}
	public void setBelong(int belong) {
		this.belong = belong;
	}
	public String getB_pass() {
		return b_pass;
	}
	public void setB_pass(String b_pass) {
		this.b_pass = b_pass;
	}
	
	public Barber(int b_id, String b_name, String b_position, String b_icon, String pro_degree, String ser_degree,
			String pop_degree, int belong, String b_pass,String b_desc) {
		super();
		this.b_id = b_id;
		this.b_name = b_name;
		this.b_position = b_position;
		this.b_icon = b_icon;
		this.pro_degree = pro_degree;
		this.ser_degree = ser_degree;
		this.pop_degree = pop_degree;
		this.belong = belong;
		this.b_pass = b_pass;
		this.b_desc=b_desc;
}
	public Barber(String b_name, String b_position, String b_icon,int belong) {
		super();
		this.b_name = b_name;
		this.b_position = b_position;
		this.b_icon = b_icon;
		this.belong=belong;
	}
	public Barber(String b_name, String b_position, String b_icon) {
		super();
		this.b_name = b_name;
		this.b_position = b_position;
		this.b_icon = b_icon;
	}
	
}
