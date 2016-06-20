package bean;

public class InfoMessagePraise {
	private int im_id;
	private int u_id;
	private String im_p_time;

	public InfoMessagePraise(int im_id, int u_id, String im_p_time) {
		super();
		this.im_id = im_id;
		this.u_id = u_id;
		this.im_p_time = im_p_time;
	}

	public int getIm_id() {
		return im_id;
	}

	public void setIm_id(int im_id) {
		this.im_id = im_id;
	}

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public String getIm_p_time() {
		return im_p_time;
	}

	public void setIm_p_time(String im_p_time) {
		this.im_p_time = im_p_time;
	}

	public InfoMessagePraise(int im_id, int u_id) {
		super();
		this.im_id = im_id;
		this.u_id = u_id;
	}

}
