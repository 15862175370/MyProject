package bean;
 /**
  * 理发师帖子实体类
  * @author Administrator
  *
  */
import java.sql.*;
public class Barpost {
    private int m_barber_id;
    private int b_id;
    private String content;
    private String image;
    private Timestamp time;
	public int getM_barber_id() {
		return m_barber_id; 
	}
	public void setM_barber_id(int m_barber_id) {
		this.m_barber_id = m_barber_id;
	}
	public int getB_id() {
		return b_id;
	}
	public void setB_id(int b_id) {
		this.b_id = b_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public Barpost(int m_barber_id, int b_id, String content, String image, Timestamp time) {
		super();
		this.m_barber_id = m_barber_id;
		this.b_id = b_id;
		this.content = content;
		this.image = image;
		this.time = time;
	}
    
    
}
