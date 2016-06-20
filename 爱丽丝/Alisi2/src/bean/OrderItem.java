package bean;

public class OrderItem {
   private String imageUrl;
   private String shopName;
   private int totalPirce;
   private int totalCount;
   private String orderState;
public OrderItem(String imageUrl, String shopName,int totalPirce,
		int totalCount, String orderState) {
	super();
	this.imageUrl = imageUrl;
	this.shopName = shopName;
	this.totalPirce = totalPirce;
	this.totalCount = totalCount;
	this.orderState = orderState;
}
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
public String getOrderState() {
	return orderState;
}
public void setOrderState(String orderState) {
	this.orderState = orderState;
}
}
