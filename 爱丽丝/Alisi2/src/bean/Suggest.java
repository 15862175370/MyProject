package bean;

public class Suggest {
	private int u_id;
	private String u_suggest;
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public String getU_suggest() {
		return u_suggest;
	}
	public void setU_suggest(String u_suggest) {
		this.u_suggest = u_suggest;
	}
	public Suggest(int u_id, String u_suggest) {
		super();
		this.u_id = u_id;
		this.u_suggest = u_suggest;
	}
	
}
