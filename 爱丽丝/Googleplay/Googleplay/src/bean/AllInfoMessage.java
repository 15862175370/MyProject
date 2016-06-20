package bean;

public class AllInfoMessage {
	private int im_id;
	private String im_logo;
	public int getIm_id() {
		return im_id;
	}
	public void setIm_id(int im_id) {
		this.im_id = im_id;
	}
	public String getIm_logo() {
		return im_logo;
	}
	public void setIm_logo(String im_logo) {
		this.im_logo = im_logo;
	}
	public AllInfoMessage(int im_id, String im_logo) {
		super();
		this.im_id = im_id;
		this.im_logo = im_logo;
	}
	
	
}
