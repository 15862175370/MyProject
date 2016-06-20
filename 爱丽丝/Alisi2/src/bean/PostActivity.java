package bean;

public class PostActivity {
   private String imageUrl;
   private String content;
public String getImageUrl() {
	return imageUrl;
}
public void setImageUrl(String imageUrl) {
	this.imageUrl = imageUrl;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public PostActivity() {
	
	// TODO Auto-generated constructor stub
}
public PostActivity(String imageUrl, String content) {
	
	this.imageUrl = imageUrl;
	this.content = content;
}
}
