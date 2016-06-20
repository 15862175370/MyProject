package bean;

public class UnuseInfo {
	private String imageUrl;
	   private String shopName;
	   private int totalPirce;
	   private int totalCount;
	   private String useState;
	   private String time;
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public int getTotalPirce() {
		return totalPirce;
	}
	public void setTotalPirce(int totalPirce) {
		this.totalPirce = totalPirce;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public String getUseState() {
		return useState;
	}
	public void setUseState(String useState) {
		this.useState = useState;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public UnuseInfo(String imageUrl, String shopName, int totalPirce, int totalCount, String useState,String time) {
		super();
		this.imageUrl = imageUrl;
		this.shopName = shopName;
		this.totalPirce = totalPirce;
		this.totalCount = totalCount;
		this.useState = useState;
		this.time = time;
	}
}
