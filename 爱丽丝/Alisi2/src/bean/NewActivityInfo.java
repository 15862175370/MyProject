package bean;

public class NewActivityInfo {
	private int id;
	private String imageUrl;
	private String content;
	private String time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public NewActivityInfo(int id, String imageUrl, String content, String time) {
		super();
		this.id = id;
		this.imageUrl = imageUrl;
		this.content = content;
		this.time = time;
	}

	
}
