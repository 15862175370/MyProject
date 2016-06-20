package bean;

public class User {
     private int u_id;
      private String u_name;
      private String phone;
      private String icon;
      private String u_sign;
      private String city;
      private  int sex;
      private String e_mail;
      private int money;
	public User(int u_id, String u_name, String phone, String icon, String u_sign, String city, int sex, String e_mail,
			int money) {
		super();
		this.u_id = u_id;
		this.u_name = u_name;
		this.phone = phone;
		this.icon = icon;
		this.u_sign = u_sign;
		this.city = city;
		this.sex = sex;
		this.e_mail = e_mail;
		this.money = money;
	}
	public User(String u_name, String phone, String icon, String u_sign, String city, int sex, String e_mail,
			int money) {
		super();
		this.u_name = u_name;
		this.phone = phone;
		this.icon = icon;
		this.u_sign = u_sign;
		this.city = city;
		this.sex = sex;
		this.e_mail = e_mail;
		this.money = money;
	}
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getU_sign() {
		return u_sign;
	}
	public void setU_sign(String u_sign) {
		this.u_sign = u_sign;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getE_mail() {
		return e_mail;
	}
	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	
	
}
