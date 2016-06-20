package bean;

import java.io.Serializable;



public class OrderShop implements Serializable{
	public OrderShop(int s_id, String s_name, String s_icon, double s_distance,
			int count_order, int count_comment, String s_score) {
		super();
		this.s_id = s_id;
		this.s_name = s_name;
		this.s_icon = s_icon;
		this.s_distance = s_distance;
		this.count_order = count_order;
		this.count_comment = count_comment;
		this.s_score = s_score;
	}
	private int s_id;
	private String s_name;
	private String s_icon;
	private double s_distance;
	private int count_order;
	private int count_comment;
	private String s_score;
	private String s_desc;
	public double getS_latitude() {
		return s_latitude;
	}
	public void setS_latitude(double s_latitude) {
		this.s_latitude = s_latitude;
	}
	public double getS_longitude() {
		return s_longitude;
	}
	public void setS_longitude(double s_longitude) {
		this.s_longitude = s_longitude;
	}
	private double s_latitude;
	private double s_longitude;
	public OrderShop(int s_id, String s_name, String s_icon,double s_distance,
			int count_order, int count_comment, String s_score, String s_desc,
			double s_latitude, double s_longitude) {
		super();
		this.s_id = s_id;
		this.s_name = s_name;
		this.s_icon = s_icon;
		this.s_distance = s_distance;
		this.count_order = count_order;
		this.count_comment = count_comment;
		this.s_score = s_score;
		this.s_desc = s_desc;
		this.s_latitude = s_latitude;
		this.s_longitude = s_longitude;
	}
	public String getS_desc() {
		return s_desc;
	}
	public OrderShop(int s_id, String s_name, String s_icon,double s_distance,
			int count_order, int count_comment, String s_score, String s_desc) {
		super();
		this.s_id = s_id;
		this.s_name = s_name;
		this.s_icon = s_icon;
		this.s_distance = s_distance;
		this.count_order = count_order;
		this.count_comment = count_comment;
		this.s_score = s_score;
		this.s_desc = s_desc;
	}
	public void setS_desc(String s_desc) {
		this.s_desc = s_desc;
	}
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public String getS_icon() {
		return s_icon;
	}
	public void setS_icon(String s_icon) {
		this.s_icon = s_icon;
	}
	public double getS_distance() {
		return s_distance;
	}
	public void setS_distance(double s_distance) {
		this.s_distance = s_distance;
	}
	public int getCount_order() {
		return count_order;
	}
	public void setCount_order(int count_order) {
		this.count_order = count_order;
	}
	public int getCount_comment() {
		return count_comment;
	}
	public void setCount_comment(int count_comment) {
		this.count_comment = count_comment;
	}
	public String getS_score() {
		return s_score;
	}
	public void setS_score(String s_score) {
		this.s_score = s_score;
	}
	
	

	

}
