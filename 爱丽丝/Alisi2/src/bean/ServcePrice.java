package bean;

public class ServcePrice {
	public ServcePrice(int b_id, String type,int price) {
		super();
		this.b_id = b_id;
		this.type = type;
		this.price = price;
	}
	private int b_id;
	private String type;
	private int price;
	public int getB_id() {
		return b_id;
	}
	public void setB_id(int b_id) {
		this.b_id = b_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

}
