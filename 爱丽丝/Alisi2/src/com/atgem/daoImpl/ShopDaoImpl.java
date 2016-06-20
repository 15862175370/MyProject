package com.atgem.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.atgem.dao.ShopDao;
import com.atgem.utils.JDBCUtil;

import bean.ActivityComment;
import bean.AllInfoMessage;
import bean.Barber;
import bean.BarpostItem;
import bean.ChangeUser;
import bean.Collect;
import bean.Entity;
import bean.EntityPage;
import bean.FixBarber;
import bean.FreeTime;
import bean.InfoMessage;
import bean.InfoMessageComment;
import bean.InfoMessagePraise;
import bean.NewActivityInfo;
import bean.OrderItem;
import bean.OrderShop;
import bean.OrderTopBarber;
import bean.PostActivity;
import bean.ServcePrice;
import bean.Shop;
import bean.Suggest;
import bean.UnuseInfo;
import bean.User;
import bean.UserPost;



public class ShopDaoImpl implements ShopDao {

	@Override
	public List<Barber> getAllBarberInfo(int s_id) {
		// TODO Auto-generated method stub
		Connection conn=JDBCUtil.getConn();
		PreparedStatement psmt=null;
		ResultSet resultSet=null;
		List<Barber> barberList=new ArrayList<Barber>();
		Barber barber=null;
		try {
			psmt=conn.prepareStatement("select * from barber where belong=?");
			psmt.setInt(1, s_id);
			resultSet=psmt.executeQuery();
			while(resultSet.next()){
				int b_id=resultSet.getInt("b_id");
				String b_name=resultSet.getString("b_name");
				String b_position=resultSet.getString("b_position");
				String b_icon=resultSet.getString("b_icon");
				String pro_degree=resultSet.getString("pro_degree");
				String ser_degree=resultSet.getString("ser_degree");
				String pop_degree=resultSet.getString("pop_degree");
				String b_pass=resultSet.getString("b_pass");
				String b_desc=resultSet.getString("b_desc");
			
				barber=new Barber(b_id, b_name, b_position, b_icon, pro_degree, ser_degree, pop_degree,s_id, b_pass, b_desc);
				barberList.add(barber);
				
				
			}
			return barberList;
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return null;
	}

	@Override
	public int findShopByShopNameAndPassword(String name, String password) {
		PreparedStatement psmt;
		  ResultSet resultSet;
		 Connection conn=(Connection) JDBCUtil.getConn();
		 if(conn!=null){
			   try {
					PreparedStatement prepareStatement = (PreparedStatement) conn.prepareStatement("select * from shop where s_name=? and s_pass=?");
				          prepareStatement.setString(1,name);
				          prepareStatement.setString(2, password);
			         resultSet=prepareStatement.executeQuery();
			         System.out.println(name);
			         if(resultSet.next()){
			        	 return  resultSet.getInt("s_id");
			         }
			         else{
			        	 return 0;
			         }
			   
			   
			   } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 }
		 return 0;
	}

	@Override
	public boolean updateServType(int n,int b_id,String price) {
		// TODO Auto-generated method stub
		PreparedStatement psmt;
		  ResultSet resultSet;
		 Connection conn=(Connection) JDBCUtil.getConn();
		
		
		switch(n){
		case 1:
		{        
			try {
				psmt=conn.prepareStatement("update price set price=? where ser_id=1 and b_id="+b_id );
			   psmt.setString(1, price);
			int m=  psmt.executeUpdate();
			if(m>0){
				return true;
			}
			
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		case 2:
		{
			try {
				psmt=conn.prepareStatement("update price set price=? where ser_id=2 and b_id="+b_id );
			   psmt.setString(1, price);
			int m=  psmt.executeUpdate();
			if(m>0){
				return true;
			}
			
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			case 3:
			{

				try {
					psmt=conn.prepareStatement("update price set price=? where ser_id=3 and b_id="+b_id );
				   psmt.setString(1, price);
				int m=  psmt.executeUpdate();
				if(m>0){
					return true;
				}
				
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			case 4:
			{
				try {
					psmt=conn.prepareStatement("update price set price=? where ser_id=4 and b_id="+b_id );
				   psmt.setString(1, price);
				int m=  psmt.executeUpdate();
				if(m>0){
					return true;
				}
				
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		  }
		
		
		
		
		
		}
		
		
		
		
		
		
		
		
		
		return false;
	}

	@Override
	public boolean updatePosition(int b_id, String b_position) {
		// TODO Auto-generated method stub
		PreparedStatement psmt;
		  ResultSet resultSet;
		 Connection conn=(Connection) JDBCUtil.getConn();
		 
		   try {
			psmt=conn.prepareStatement("update barber set b_position=?where b_id="+b_id);
		    psmt.setString(1, b_position);
			int n=   psmt.executeUpdate();
		  if(n>0){
			  return true;
		  }
		  else{
			  return false;
		  }
		   
		   
		   } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return false;
	}

	@Override
	public boolean updateDesc(int b_id, String b_desc) {
		// TODO Auto-generated method stub
		PreparedStatement psmt;
		  ResultSet resultSet;
		 Connection conn=(Connection) JDBCUtil.getConn();
		 try {   //"update barber set b_position=?where b_id="+b_id
			psmt=conn.prepareStatement("update barber set b_desc=?where b_id=?");
			psmt.setString(1, b_desc);
			psmt.setInt(2, b_id);
		  
			int n=     psmt.executeUpdate();
		 if(n>0){
			 return true;
		 }
		 
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		 
		 
		 
		 
		 
		 
		return false;
	}

	@Override
	public List<Entity> getAllOrderInfo(int s_id) {
		// TODO Auto-generated method stub
		PreparedStatement psmt;
		  ResultSet resultSet;
		 Connection conn=(Connection) JDBCUtil.getConn();
		
		 List<Entity> entityList=new ArrayList<Entity>();
		 
		 
		 try {	 
	     psmt=conn.prepareStatement("select o_id,u_id,time2,state from order where b_id in (select b_id from barber where belong=?)");
		   psmt.setInt(1, s_id);
		  resultSet= psmt.executeQuery();
		  while(resultSet.next()){
			        int u_id=resultSet.getInt("u_id");
			        int o_id=resultSet.getInt("o_id");
			        String state=resultSet.getString("state");
			       
			        Timestamp time2= resultSet.getTimestamp("time2");
			           System.out.println(time2);
			         
			          
			        String phone= getPhone(u_id);
			  Entity entity=new Entity(o_id, time2, phone, state);
			  
			  entityList.add(entity);
	
		  }
		 
		 return entityList;
		 
		 
		 
		 
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		return null;
	}

	public String getPhone(int u_id){
		PreparedStatement psmt;
		  ResultSet resultSet;
		 Connection conn=(Connection) JDBCUtil.getConn();
		 
		 try {
			psmt=conn.prepareStatement("select phone from user where u_id=?");
			   psmt.setInt(1, u_id);
			 resultSet= psmt.executeQuery();
			 if(resultSet.next()){
				    String phone=resultSet.getString("phone");
				    return phone;
				 
				 
			 }
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public EntityPage getEntityByPage(int nowPage, int pageSize,int s_id) {
		// TODO Auto-generated method stub
		List<Entity> listEntity = new ArrayList<Entity>();
		int totalPage=getTotalPage(pageSize,s_id);
		int currentPage = nowPage;
		Connection conn=(Connection) JDBCUtil.getConn();
		  PreparedStatement psmt=null;
		   ResultSet result=null;
		   int start=(nowPage-1)*pageSize;
		   try {
			psmt=(PreparedStatement) conn.prepareStatement("SELECT * FROM `order` WHERE b_id IN (SELECT b_id FROM barber WHERE  belong =?) limit ?,?");
			psmt.setInt(1, s_id);
		    psmt.setInt(2, start);
		    psmt.setInt(3, pageSize);
		     result=psmt.executeQuery();
		     while(result.next()){
		    	  int u_id=result.getInt("u_id");
			        int o_id=result.getInt("o_id");
			        String state=result.getString("state");
			       
			        Timestamp time2= result.getTimestamp("time2");
			           System.out.println(time2);
			         
			          
			        String phone= getPhone(u_id);
			  Entity entity=new Entity(o_id, time2, phone, state);
			  
			  listEntity.add(entity);
		    	 
		     }
		     EntityPage userPage=new EntityPage(totalPage,currentPage,listEntity);
		     return userPage;
		   } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		return null;
	}
	public int getTotalPage(int pageSize,int s_id){
		   Connection conn=(Connection) JDBCUtil.getConn();
		   PreparedStatement psmt=null;
		   ResultSet result=null;
		   try {
			psmt=(PreparedStatement) conn.prepareStatement("SELECT COUNT(*) FROM `order` WHERE b_id IN (SELECT b_id FROM barber WHERE  belong =?)");
		    psmt.setInt(1, s_id);
			result= psmt.executeQuery();
		      if(result.next()){
		    	  int count=result.getInt(1);
		    	  
		    	  
		    	  int pageTotal=(count+pageSize-1)/pageSize;
		    	  return pageTotal;
		      }
		   
		   
		   } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   return 0;
		   
	}

	@Override
	public int checkCode(String code) {
		// TODO Auto-generated method stub
		PreparedStatement psmt;
		  ResultSet resultSet;
		 Connection conn=(Connection) JDBCUtil.getConn();
		 try {
			psmt=conn.prepareStatement("select state from reservation where res_num=?");
			psmt.setString(1, code);
			
		    resultSet= psmt.executeQuery();
		    if(resultSet.next()){
		    	
		    	int state=resultSet.getInt("state");
		    	 return state;
		    }
		   
		 
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		return 2;
	}

	@Override
	public boolean deleteBarberById(int b_id) {
		// TODO Auto-generated method stub
		Connection conn=(Connection) JDBCUtil.getConn();
		PreparedStatement psmt=null;
		  try {
			  System.out.println(111111);
			   psmt=(PreparedStatement) conn.prepareStatement("delete from barber where b_id=?");
					   psmt.setInt(1, b_id);
					   
		   int n=  psmt.executeUpdate();
			System.out.println(n);
			if(n>0){
				
				return true;
			}
			else{
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return false;
		
		
		
	}

	@Override
	public boolean updateIcon(String url,int b_id) {
		// TODO Auto-generated method stub
		PreparedStatement psmt;
		  ResultSet resultSet;
		 Connection conn=(Connection) JDBCUtil.getConn();
	        try {
	        	System.out.println(url);
				psmt=conn.prepareStatement("update barber set b_icon=? where b_id=?");
				psmt.setString(1, url);
				psmt.setInt(2, b_id);
			  int n=	psmt.executeUpdate();
				if(n>0){
					return true;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		return false;
	}

	@Override
	public List<BarpostItem> getAllBarpost() {
		// TODO Auto-generated method stub
		Connection conn=JDBCUtil.getConn();
		PreparedStatement psmt=null;
		ResultSet resultSet=null;
		List<BarpostItem> barpostItems=new ArrayList<BarpostItem>();
		   try {
			psmt=conn.prepareStatement("select * from bar_post");
			resultSet=psmt.executeQuery();
			while(resultSet.next()){
		     int m_barber_id=resultSet.getInt("m_barber_id");
		     int commentCount=getCommentCountByid(m_barber_id);
		     int praiseCount=getPraiseCount(m_barber_id);
			  int b_id=resultSet.getInt("b_id");
			  String content=resultSet.getString("content");
			  String image=resultSet.getString("image");
			   Timestamp time=resultSet.getTimestamp("time");
				Barber barber=getBarberInfoById(b_id);
				 String s_name=getShopNameById(barber.getBelong());
				 BarpostItem item=new BarpostItem(barber.getB_name(),barber.getB_position(),barber.getB_icon(),content,image,time,commentCount,praiseCount,s_name);
				barpostItems.add(item);
			}
			return barpostItems;
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(conn, psmt, resultSet);
		}
		return null;
	}
	/**
	 * 通过理发师的帖子中理发师id获取理发师的姓名，头像和职称
	 * @param b_id
	 * @return
	 */
	public Barber getBarberInfoById(int b_id){
		Connection conn=JDBCUtil.getConn();
		PreparedStatement psmt=null;
		ResultSet resultSet=null;
		try {
			psmt=conn.prepareStatement("select * from barber where b_id=?");
			psmt.setInt(1, b_id);
		      resultSet=psmt.executeQuery();
		      if(resultSet.next()){
		    	  String b_name=resultSet.getString("b_name");
		    	  String b_icon=resultSet.getString("b_icon");
		    	  String b_position=resultSet.getString("b_position");
		    	  int belong=resultSet.getInt("belong");
		    	  Barber barber=new Barber(b_name,b_position,b_icon,belong);
		    	  return barber;
		      }
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(conn, psmt, resultSet);
		}
		return null;
	}
	/**
	 * 通过理发店的id获取理发店的名字
	 * @param s_id
	 * @return
	 */
	public String getShopNameById(int s_id){
		Connection conn=JDBCUtil.getConn();
		PreparedStatement psmt=null;
		ResultSet resultSet=null;
		
			try {
				psmt=conn.prepareStatement("select s_name from shop where s_id=?");
				psmt.setInt(1, s_id);
				resultSet=psmt.executeQuery();
				if(resultSet.next()){
						String b_name=resultSet.getString("s_name");
						return b_name;
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	}
	/**
	 * 通过帖子的id在评论表中查询总共被评论的次数
	 * @param m_barber_id
	 * @return
	 */
	public int getCommentCountByid(int m_barber_id){
		Connection conn=JDBCUtil.getConn();
		PreparedStatement psmt=null;
		ResultSet resultSet=null;
		         try {
					psmt=conn.prepareStatement("SELECT COUNT(*) FROM `t_barcomment` WHERE m_barber_id=?");
					psmt.setInt(1, m_barber_id);
					resultSet=psmt.executeQuery();
					if(resultSet.next()){
						int count=resultSet.getInt(1);
						return count;
					}
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					close(conn, psmt, resultSet);
				}
		         return 0;
	}
	public int getPraiseCount(int m_barber_id){
		Connection conn=JDBCUtil.getConn();
		PreparedStatement psmt=null;
		ResultSet resultSet=null;
		try {
			psmt=conn.prepareStatement("SELECT COUNT(*) FROM `bar_praise` WHERE m_barber_id=?");
			psmt.setInt(1, m_barber_id);
			resultSet=psmt.executeQuery();
			if(resultSet.next()){
				int praiseCount=resultSet.getInt(1);
				return praiseCount;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(conn, psmt, resultSet);
		}
		return 0;
	}

	@Override
	public List<PostActivity> getAllActivities() {
		// TODO Auto-generated method stub
		Connection conn=JDBCUtil.getConn();
		PreparedStatement psmt=null;
		ResultSet resultSet=null;
		List<PostActivity> activities=new ArrayList<PostActivity>();
		   try {
			psmt=conn.prepareStatement("select * from activity");
			resultSet=psmt.executeQuery();
			while(resultSet.next()){
			
			
			     String content=resultSet.getString("content");
			      String imageUrl=resultSet.getString("image");
			  	PostActivity activityItem=new PostActivity(imageUrl, content);
			  activities.add(activityItem);
			}
		   } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
				  if(conn!=null){
					  try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				  }
			}
		return activities;
	
}
	//获得所有的商家信息
	public List<OrderShop> getAllOrderShop(){
		
		
		Connection conn=JDBCUtil.getConn();
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		
		
		List<OrderShop>listOrder=new ArrayList<OrderShop>();
		
		try{
			psmt=conn.prepareStatement("SELECT * FROM shop");
			
			rs=psmt.executeQuery();
			System.out.println(111111);
			while(rs.next()){
				int s_id=rs.getInt("s_id");
				String s_name=rs.getString("s_name");
				String s_phone=rs.getString("s_phone");
				String s_addr=rs.getString("s_addr");
				String s_descc=rs.getString("s_desc");
				String s_icon=rs.getString("s_icon");
				double s_distance=rs.getDouble("s_distance");
				String s_score=rs.getString("s_score");
				String s_pass=rs.getString("s_pass");
				//	private double s_latitude;
				//private double s_longitude;
			 double s_latitude=rs.getDouble("s_latitude");
		     double s_longitude=rs.getDouble("s_longitude");
				int count_order=orderCount(s_id);//获得预约数
				int count_comment=commentCount(s_id);
				OrderShop orderShop2=new OrderShop(s_id, s_name, s_icon, s_distance, count_order, count_comment, s_score,s_descc,s_latitude,s_longitude);;
				
			
				
				listOrder.add(orderShop2);
				
				
				
				
			}
			
		}catch(Exception e){
			System.out.println("error");
		}finally {
			close(conn, psmt, rs);
		}
		return listOrder;
		
	}
	public int orderCount(int s_id){
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		conn=JDBCUtil.getConn();
		try {
			pst=conn.prepareStatement("SELECT COUNT(*)FROM t_order WHERE b_id IN(SELECT b_id FROM barber WHERE belong=?)");
			pst.setInt(1, s_id);
			rs=pst.executeQuery();
			while(rs.next()){
				int count=rs.getInt(1);
				return count;
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(conn, pst, rs);
		}
		return 0;
	}
	public int commentCount(int s_id){
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		conn=JDBCUtil.getConn();
		try {
			pst=conn.prepareStatement("SELECT COUNT(*)FROM view_barberid WHERE b_id IN(SELECT b_id FROM barber WHERE belong=?)");
			pst.setInt(1, s_id);
			rs=pst.executeQuery();
			while(rs.next()){
				int count=rs.getInt(1);
				return count;
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(conn, pst, rs);
		}
		return 0;
	}
	public void getAllShopInfo(){
		Connection conn=JDBCUtil.getConn();
		PreparedStatement psmt=null;
		ResultSet resultSet=null;
		List<OrderShop>listOrder=new ArrayList<OrderShop>();
			try {
				psmt=conn.prepareStatement("select * from shop");
				resultSet=psmt.executeQuery();
				while(resultSet.next()){
					/*int s_id=resultSet.getInt("s_id");
					String s_name=resultSet.getString("s_name");
					System.out.println(s_id);
					System.out.println(s_name);
*/					int s_id=resultSet.getInt("s_id");
					String s_name=resultSet.getString("s_name");
					String s_phone=resultSet.getString("s_phone");
					String s_addr=resultSet.getString("s_addr");
					String s_descc=resultSet.getString("s_desc");
					String s_icon=resultSet.getString("s_icon");
					double s_distance=resultSet.getDouble("s_distance");
					String s_score=resultSet.getString("s_score");
					String s_pass=resultSet.getString("s_pass");
					int count_order=orderCount(s_id);//获得预约数
					int count_comment=commentCount(s_id);
					OrderShop orderShop2=new OrderShop(s_id, s_name, s_icon, s_distance, count_order, count_comment, s_score);
					System.out.println(s_id);
					
					listOrder.add(orderShop2);
					
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(conn, psmt, resultSet);
			}
			
		
		
		
		
	
	}

	@Override
	public List<OrderShop> getShopByDistance() {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		List<OrderShop>listOrder=new ArrayList<OrderShop>();
		conn=JDBCUtil.getConn();
			try {
				pst=conn.prepareStatement("select * from shop  ORDER BY s_distance ASC ");
				rs=pst.executeQuery();
				while(rs.next()){
					int s_id=rs.getInt("s_id");
					String s_name=rs.getString("s_name");
					String s_phone=rs.getString("s_phone");
					String s_addr=rs.getString("s_addr");
					String s_descc=rs.getString("s_desc");
					String s_icon=rs.getString("s_icon");
					double s_distance=rs.getDouble("s_distance");
					String s_score=rs.getString("s_score");
					String s_pass=rs.getString("s_pass");
					int count_order=orderCount(s_id);//获得预约数
					int count_comment=commentCount(s_id);
					OrderShop orderShop2=new OrderShop(s_id, s_name, s_icon, s_distance, count_order, count_comment, s_score);
					
					
					listOrder.add(orderShop2);
					
			
			
			
			} 
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(conn, pst, rs);
			}
			
		
				
				
				
				
			
			
		
		return listOrder;
		
		
		
		
		
	}

	@Override
	public List<OrderItem> getAllOrdersById(int id) {
		// TODO Auto-generated method stub
		
		Connection conn=JDBCUtil.getConn();
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		
		
		List<OrderItem>listOrder=new ArrayList<OrderItem>();
		 OrderItem orderItem;
		
			try {
				psmt=conn.prepareStatement("SELECT * from t_order where u_id=? and state='1'");
				psmt.setInt(1, id);
				
				rs=psmt.executeQuery();
				while(rs.next()){
					int servid=rs.getInt("servid");
					int barberid=rs.getInt("b_id");
					int price=getPrice(servid, barberid);
					int oder_id=rs.getInt("o_id");
					String orderState=getOderStateByOrderId(oder_id);
					Shop shopInfoByBarberId = getShopInfoByBarberId(barberid);
				   String imageUrl=shopInfoByBarberId.getS_icon();
				   String shopName=shopInfoByBarberId.getS_name();
			    orderItem=new OrderItem(imageUrl, shopName, price, 1, orderState);
			    System.out.println("订单id是:"+oder_id);
			    System.out.println("评论状态是:"+orderState);
				  listOrder.add(orderItem);
				
				}
			return listOrder;
			
			
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(conn, psmt, rs);
			}
			
		
		return null;
	}
	public int getPrice(int servid,int barberid){
		Connection conn=JDBCUtil.getConn();
		PreparedStatement psmt=null;
		ResultSet rs=null;
		try {
			psmt=conn.prepareStatement("SELECT price from price where ser_id=? and b_id=? ");
			psmt.setInt(1, servid);
			psmt.setInt(2, barberid);
			
			rs=psmt.executeQuery();
			if(rs.next()){
				int price=rs.getInt("price");
				return price;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(conn, psmt, rs);
		}
		return 0;
	}

	@Override
	public Shop getShopInfoByBarberId(int id) {
		// TODO Auto-generated method stub
		Connection conn=JDBCUtil.getConn();
		PreparedStatement psmt=null;
		ResultSet rs=null;
	  
			try {
				psmt=conn.prepareStatement("SELECT * from shop where s_id=(select belong from barber where b_id=?) ");
				psmt.setInt(1, id);
				
				
				rs=psmt.executeQuery();
			  if(rs.next()){
				    String imageUrl=rs.getString("s_icon");
				    String shopName=rs.getString("s_name");
				    Shop shop=new Shop(shopName,imageUrl);
				    return shop;
			  }
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(conn, psmt, rs);
			}
		
		
		
		return null;
	}
	public String getOderStateByOrderId(int id){
		
		Connection conn=JDBCUtil.getConn();
		PreparedStatement psmt=null;
		ResultSet rs=null;
	
		
				try {
					psmt=conn.prepareStatement("SELECT state from ordercomment where oder_id=? ");
					psmt.setInt(1, id);
				   rs=psmt.executeQuery();
				   if(rs.next()){
					   String state=rs.getString("state");
					   if(state.equals("1"))
					   {
						  return "已评论";
					   }
					   else{
						   return "未评论";
					   }
				   }
				
				
				
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					close(conn, psmt, rs);
				}
				
				
				return null;
			
		
	}
	public List<FixBarber> getAllBarberByShopId(int s_id){
		
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		
		List<FixBarber>list=new ArrayList<FixBarber>();
		conn=JDBCUtil.getConn();
		int countPerson=countPersonByShopId( s_id);
		String s_addr=findS_adress(s_id);
		
		
		
		
		try {
			pst=conn.prepareStatement("SELECT *FROM barber WHERE b_id IN(SELECT b_id FROM barber WHERE belong=?)");
			pst.setInt(1, s_id);
			
			rs=pst.executeQuery();
			while(rs.next()){
				int b_id=rs.getInt("b_id");
				String b_name=rs.getString("b_name");
				String b_position=rs.getString("b_position");
				String b_icon=rs.getString("b_icon");
				String pro_degree=rs.getString("pro_degree");
				String ser_degree=rs.getString("ser_degree");
				String pop_degree=rs.getString("pop_degree");
				int belong=rs.getInt("belong");
				String b_pass=rs.getString("b_pass");
				String b_desc=rs.getString("b_desc");
			
				
				FixBarber barber=new FixBarber(b_id, b_name, b_position, b_icon, pro_degree, ser_degree, pop_degree, belong, b_pass,b_desc,s_addr,countPerson);
				list.add(barber);
				
				
				
					
				
			}
			
		
	}catch(Exception e){
		e.printStackTrace();
	}finally {
		close(conn, pst, rs);
	}
		
		return list;
	}
	public String  findS_adress(int id){
		
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		conn=JDBCUtil.getConn();
		try{
			pst=conn.prepareStatement("select s_addr from shop where s_id=? ");
			pst.setInt(1, id);
			rs=pst.executeQuery();
			while(rs.next()){
				
				String s_addr=rs.getString("s_addr");
				return s_addr;
				
				
			}
			
		}catch(Exception e){
			
		}finally {
			close(conn, pst, rs);
		}
		return null;
		
	}






	public List<ServcePrice> getAllServcePriceByBar_id(int bar_id){
		
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		
		List<ServcePrice>list=new ArrayList<ServcePrice>();
		conn=JDBCUtil.getConn();

		
		
		
		
		try {
			pst=conn.prepareStatement("select type,price from bsp where b_id=?");
			pst.setInt(1, bar_id);
			
			rs=pst.executeQuery();
			while(rs.next()){
			String type=rs.getString("type");
			int price=rs.getInt("price");
				
				 ServcePrice sp=new ServcePrice(bar_id, type, price);
				list.add(sp);
				
				
				
					
				
			}
			
		
	}catch(Exception e){
		e.printStackTrace();
	}finally {
		close(conn, pst, rs);
	}
		
		return list;
	}
	public int countPersonByShopId(int s_id){
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		conn=JDBCUtil.getConn();
		try {
			pst=conn.prepareStatement("SELECT COUNT(*)FROM barber WHERE belong=?");
			pst.setInt(1, s_id);
			rs=pst.executeQuery();
			while(rs.next()){
				int count=rs.getInt(1);
				return count;
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(conn, pst, rs);
		}
		return 0;
	}

	@Override
	public boolean addUserByPhone(String phone) {
		// TODO Auto-generated method stub
	
			String sqlString = "insert into user(phone)values(?)";
			Connection conn = JDBCUtil.getConn();
			PreparedStatement psmt = null;
			ResultSet rs = null;
//			String name = String.valueOf(Math.random()*100);
			
				try {
					psmt = conn.prepareStatement(sqlString);
//					psmt.setString(1, name);
					psmt.setString(1, phone);
					
					psmt.executeUpdate();
					
					return true;
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					close(conn, psmt, rs);
				}
			return false;
		

		
		
		
	}
public User getUserInfoById(String phone){
	
	Connection conn = JDBCUtil.getConn();
	PreparedStatement psmt = null;
	ResultSet rs = null;
	String sqlString="select * from user where phone=?";
	try {
		
		psmt = conn.prepareStatement(sqlString);
//		psmt.setString(1, name);
		psmt.setString(1, phone);
		
		rs=psmt.executeQuery();
	
		if(rs.next()){
			String u_name=rs.getString("u_name");
			String icon=rs.getString("icon");
			String u_sign=rs.getString("u_sign");
			String city=rs.getString("city");
			int sex=rs.getInt("sex");
			String e_mail=rs.getString("e_mail");
			int money=rs.getInt("money");
		    int u_id=rs.getInt("u_id");
			User user=new User(u_id, u_name, icon, u_sign, city, sex, e_mail, money);
					
		return user;
			
			
			
		}
		
		
		
	
	
	
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}finally{
	if(conn!=null){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
	return null;
		
}

@Override
public List<FreeTime> getState(int b_id) {
	// TODO Auto-generated method stub
	
		List<FreeTime> list=new ArrayList<FreeTime>();
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		conn=JDBCUtil.getConn();
		try{
			pst=conn.prepareStatement("select day_id,t_id,state from freetime where b_id=? ");
			pst.setInt(1,b_id);
			rs=pst.executeQuery();
			while(rs.next()){
				String day_id=rs.getString("day_id");
				String t_id=rs.getString("t_id");
				String state=rs.getString("state");
				FreeTime ft=new FreeTime(day_id, t_id, state);
				list.add(ft);
			}
		
	}catch(Exception e){
		e.printStackTrace();
	}
		return list;
	
	


	
	
}

@Override
public boolean updateUserMoney(int money,int id) {
	// TODO Auto-generated method stub
	
	Connection conn=null;
	PreparedStatement pst=null;
	int state=0;
	conn=JDBCUtil.getConn();
	try {
		pst=conn.prepareStatement("UPDATE `user` SET money=money-? WHERE u_id=?");
		pst.setInt(1,money);
		pst.setInt(2, id);
		pst.executeUpdate();
		
		return true;
	
	
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
	
}

@Override
public boolean addOrder(int u_id, int b_id, int time1, int servid, int state, int day_id) {
	// TODO Auto-generated method stub
	
////INSERT into t_order(u_id,b_id,time1,servid,state,day_id)VALUES(2,3,1,1,1,1)
	String sqlString = "INSERT into t_order(u_id,b_id,time1,servid,state,day_id)VALUES(?,?,?,?,?,?)";
	Connection conn = JDBCUtil.getConn();
	PreparedStatement psmt = null;
	ResultSet rs = null;
//	String name = String.valueOf(Math.random()*100);
	
		
			try {
				psmt = conn.prepareStatement(sqlString);
				//psmt.setString(1, name);
				psmt.setInt(1,u_id);
				psmt.setInt(2, b_id);
				psmt.setInt(3, time1);
				psmt.setInt(4,servid);
				psmt.setInt(5, state);
				psmt.setInt(6, day_id);
				
				psmt.executeUpdate();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//		
	
		
	
	
	
	
	
	
	return false;
}

@Override
public int getMaxId() {
	// TODO Auto-generated method stub
	String sqlString = "SELECT MAX(o_id)FROM t_order";
	Connection conn = JDBCUtil.getConn();
	PreparedStatement psmt = null;
	ResultSet rs = null;
//	String name = String.valueOf(Math.random()*100);
	
		
			
				try {
					psmt = conn.prepareStatement(sqlString);
				
					rs=psmt.executeQuery();
					if(rs.next()){
						int maxId=rs.getInt(1);
						return maxId;
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//psmt.setString(1, name);
			
				
			
	
	return 0;
}

@Override
public boolean addReservation(int o_id, String res_num) {
	// TODO Auto-generated method stub
	String sqlString = "INSERT INTO reservation(o_id,res_num,state)VALUES(?,?,?)";
	Connection conn = JDBCUtil.getConn();
	PreparedStatement psmt = null;
	ResultSet rs = null;
//	String name = String.valueOf(Math.random()*100);
	
		
			
				try {
					psmt = conn.prepareStatement(sqlString);
					psmt.setInt(1,o_id);
					psmt.setString(2,res_num);
					psmt.setInt(3, 1);
					
					
					psmt.executeUpdate();
					return true;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//psmt.setString(1, name);
			
	
	
	return false;
}

@Override
public boolean updateState(int b_id, String day, String t_time) {
	// TODO Auto-generated method stub
	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	int day_id=getDayId(day);
	int t_id=getTimeId(t_time);
	conn=JDBCUtil.getConn();
	
		try {
			pst=conn.prepareStatement("update freetime set state='1' where  b_id=? and day_id=? and t_id=?");
			pst.setInt(1, b_id);
			pst.setInt(2,day_id);
			pst.setInt(3, t_id);
		
			int count=pst.executeUpdate();
			if(count>0){
				return true;
			}else{
				return false;
			}
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
		
	


	
	
	
	
	

	
	
	
	
	
	

}

public int getDayId(String day){
	
	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	
	conn=JDBCUtil.getConn();
	try {
		pst=conn.prepareStatement("select day_id from showday where day=? ");
		pst.setString(1, day);
		rs=pst.executeQuery();
		while(rs.next()){
			
			int day_id=rs.getInt("day_id");
		
			return day_id;
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return 0;
	
	
	
}
public int getTimeId(String t_time){
	
	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	
	conn=JDBCUtil.getConn();
	try {
		pst=conn.prepareStatement("select t_id from showtime where t_time=? ");
		pst.setString(1, t_time);
		rs=pst.executeQuery();
		while(rs.next()){
			
			int t_id=rs.getInt("t_id");
		
			return t_id;
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return 0;
	
	
	
}

@Override
public List<Collect> getAllCollectInfoById(int id) {
	// TODO Auto-generated method stub
	Connection conn=JDBCUtil.getConn();
	PreparedStatement psmt=null;
	ResultSet rs=null;
	
	List<Collect> listCollect=new  ArrayList<Collect>();
	Collect collect;
	
	try {
		psmt=conn.prepareStatement("SELECT * from bar_collect where u_id=?");

		psmt.setInt(1, id);
		 rs=psmt.executeQuery();
	 while(rs.next()){
		 int b_id=rs.getInt("b_id");
		 int state=rs.getInt("state");
		 String s_name=getshopNameById(b_id); 
		 
		 Barber barberInfoById=getAllBarberInfoById(b_id);
		 String b_name=barberInfoById.getB_name();
		 String b_icon=barberInfoById.getB_icon();
		 String b_position=barberInfoById.getB_position();
        
		 collect=new Collect(b_id, b_name, b_icon, b_position, state);
		 listCollect.add(collect);

	 }
	 return listCollect;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	

}
public  String getshopNameById(int b_id) {
	// TODO Auto-generated method stub
	
	Connection conn=JDBCUtil.getConn();
	PreparedStatement psmt=null;
	ResultSet rs=null;
	
	try {
		psmt=conn.prepareStatement("SELECT s_name from shop where s_id=(select belong from barber where b_id=?)");
		 psmt.setInt(1, b_id);
		 rs=psmt.executeQuery();
		 while(rs.next()){
			 String s_name=rs.getString("s_name");
			 return s_name;
		 }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return null;
}

public Barber getAllBarberInfoById(int b_id) {
	// TODO Auto-generated method stub
	Connection conn=JDBCUtil.getConn();
	PreparedStatement psmt=null;
	ResultSet rs=null;
	
	try {
		psmt=conn.prepareStatement("SELECT * from barber where b_id=?");
		 psmt.setInt(1, b_id);
		 rs=psmt.executeQuery();
		 while(rs.next()){
			 String b_name=rs.getString("b_name");
			 String b_icon=rs.getString("b_icon");
			 String b_position=rs.getString("b_position");
			 Barber barber=new Barber(b_name,b_position,b_icon);
			 return barber;
		 }
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return null;
}

@Override
public List<UnuseInfo> getAllUnuseById(int id) {
	// TODO Auto-generated method stub
	Connection conn=JDBCUtil.getConn();
	PreparedStatement psmt=null;
	ResultSet rs=null;
	
	
	List<UnuseInfo>listUnuse=new ArrayList<UnuseInfo>();
	UnuseInfo unuseInfo;
	try {
		psmt=conn.prepareStatement("SELECT * from t_order where u_id=? and state='0'");
		psmt.setInt(1, id);
		
		rs=psmt.executeQuery();
		while(rs.next()){
			int servid=rs.getInt("servid");
			int barberid=rs.getInt("b_id");
			int price=getPrice(servid, barberid);
			int use_id=rs.getInt("o_id");
			String time=rs.getString("time2");
			String useState=getUseStateByOrderId(use_id);
			Shop shopInfoByBarberId = getShopInfoByBarberId(barberid);
			   String imageUrl=shopInfoByBarberId.getS_icon();
			   String shopName=shopInfoByBarberId.getS_name();
			   unuseInfo=new UnuseInfo(imageUrl, shopName, price, 1, useState, time);
			    System.out.println(shopName);
			    listUnuse.add(unuseInfo);
			   
		}
		return listUnuse;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return null;
	
	
	
	
}
public String getUseStateByOrderId(int id){
	Connection conn=JDBCUtil.getConn();
	PreparedStatement psmt=null;
	ResultSet rs=null;
	
	try {
		psmt=conn.prepareStatement("SELECT state from t_order where o_id=? ");
		psmt.setInt(1, id);
		 rs=psmt.executeQuery();
		while(rs.next()){
			String state=rs.getString("state");
			if(state.equals("0")){
				return "未使用";
			}
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	return null;

}

@Override
public List<NewActivityInfo> getAllNewActivityById(int id) {
	// TODO Auto-generated method stub
	Connection conn=JDBCUtil.getConn();
	PreparedStatement psmt=null;
	ResultSet rs=null;
	
	List<NewActivityInfo>listNowday=new ArrayList<NewActivityInfo>();
	NewActivityInfo newActivityInfo;
	
	try {
		psmt=conn.prepareStatement("SELECT * from activity where act_id=?");
	    psmt.setInt(1, id);
	    rs=psmt.executeQuery();
	    
	    while(rs.next()){
	    	int m_act_id=rs.getInt("act_id");
	    	String content=rs.getString("content");
	    	String image=rs.getString("image");
	    	String time=rs.getString("time");
	    	newActivityInfo=new NewActivityInfo(m_act_id, image,content, time);
	    	listNowday.add(newActivityInfo);
	    	
	    }
	    return listNowday;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return null;
	
	
	
	
	
}

@Override
public boolean addZancountById(int u_id,int m_barber_id) {
	// TODO Auto-generated method stub
	Connection conn=JDBCUtil.getConn();
	PreparedStatement psmt=null;
	ResultSet rs=null;
	
	//List<NewActivityInfo>listNowday=new ArrayList<NewActivityInfo>();
	//NewActivityInfo newActivityInfo;
	
	
		try {
			psmt=conn.prepareStatement("INSERT INTO bar_praise(u_id,m_barber_id,state)VALUES(?,?,'1')");
			  psmt.setInt(1, u_id);
			  psmt.setInt(2, m_barber_id);
			  psmt.executeUpdate();
			  return true;

		
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	
	
	
	
	
	return false;
}

@Override
public boolean addPostComment(int u_id, int m_barber_id,String content, String imageUrl) {
	// TODO Auto-generated method stub
	Connection conn=JDBCUtil.getConn();
	PreparedStatement psmt=null;
	ResultSet rs=null;
	
	//List<NewActivityInfo>listNowday=new ArrayList<NewActivityInfo>();
	//NewActivityInfo newActivityInfo;
	
	
		
			try {
				psmt=conn.prepareStatement("INSERT INTO bar_postcomment(u_id,m_barber_id,content,image)VALUES(?,?,?,?)");
				  psmt.setInt(1, u_id);
				  psmt.setInt(2, m_barber_id);
				  psmt.setString(3, content);
				  psmt.setString(4, imageUrl);
				  psmt.executeUpdate();
				  return true;
			
			
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	
	
	
	
	
	
	return false;
}



@Override
public InfoMessage getInfoMessageByIm_id(int im_id) {
	Connection conn = JDBCUtil.getConn();
	PreparedStatement psmt = null;
	ResultSet rs = null;

	InfoMessage infoMessage = null;
	String sql = "SELECT * FROM infomessage WHERE im_id =?";
	try {
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, im_id);
		rs = psmt.executeQuery();
		while (rs.next()) {
			String im_name = rs.getString("im_name");
			String im_image = rs.getString("im_image");
			String im_content = rs.getString("im_cotent");
			String im_time = rs.getString("im_time");
			String im_image1 = rs.getString("im_image1");
			String im_image2 = rs.getString("im_image2");
			String im_image3 = rs.getString("im_image3");
			String im_content1 = rs.getString("im_content1");
			String im_content2 = rs.getString("im_content2");
			String im_content3 = rs.getString("im_content3");

			infoMessage = new InfoMessage(im_id, im_name, im_content, im_image, im_time, im_image1, im_image2, im_image3,im_content1, im_content2, im_content3);
				
		}
		return infoMessage;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	return null;
}
@Override
public List<InfoMessageComment> getImCommentByIm_id(int im_id) {
	Connection conn = JDBCUtil.getConn();
	PreparedStatement psmt = null;
	ResultSet rs = null;
	
	List<InfoMessageComment> conlist = new ArrayList<InfoMessageComment>();
	String sql = "SELECT * FROM im_reply WHERE im_id=? ORDER BY im_reply_id DESC";
	
	try {
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, im_id);
		rs = psmt.executeQuery();
		
		while (rs.next()) {
		int u_id = rs.getInt("u_id");
		User userinfo = getUserInfoByInfoMessage(u_id);
		String u_name = userinfo.getU_name();
		String icon = userinfo.getIcon();
		System.out.println(u_name);
		String im_reply_content = rs.getString("im_reply_content");
		String im_reply_time = rs.getString("im_reply_time");
		InfoMessageComment infomessagecomment = new InfoMessageComment(im_reply_content, im_reply_time, u_name, icon);
		conlist.add(infomessagecomment);
		}
		System.out.println(conlist);
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return conlist;
}
public User getUserInfoByInfoMessage(int u_id){
	Connection conn = JDBCUtil.getConn();
	PreparedStatement psmt = null;
	ResultSet rs = null;
		
	
	String sql = " SELECT icon ,u_name FROM user WHERE u_id =?";
	try {
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, u_id);
		rs = psmt.executeQuery();
		if (rs.next()) {
		String icon = rs.getString("icon");
		String u_name = rs.getString("u_name");
		User user = new User(u_name, icon);
		System.out.println(user);
		return user;
		}
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
return null;
}
@Override
public List<AllInfoMessage> getAllInfoMessage() {
	Connection conn = JDBCUtil.getConn();
	PreparedStatement psmt = null;
	ResultSet rs = null;
	
	List<AllInfoMessage> allinfomesslist = new ArrayList<AllInfoMessage>();
	String sql = "SELECT im_id,im_logo FROM infomessage";
	try {
		psmt = conn.prepareStatement(sql);
		rs = psmt.executeQuery();
		while(rs.next()){
			int im_id = rs.getInt("im_id");
			String im_logo = rs.getString("im_logo");
			AllInfoMessage allInfoMessage = new AllInfoMessage(im_id, im_logo);
			allinfomesslist.add(allInfoMessage);
		}
		System.out.println(allinfomesslist);

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return allinfomesslist;
}

@Override
public boolean addInfoMessageComment(InfoMessageComment comment) {
	Connection conn = JDBCUtil.getConn();
	PreparedStatement psmt = null;
	ResultSet rs = null;
	
	String sql = "INSERT INTO im_reply(im_id,u_id,im_reply_content,im_reply_time)VALUES(?,?,?,?)";
	int im_id = comment.getIm_id();
	int u_id = comment.getU_id();
	String im_reply_content = comment.getIm_reply_content();
	String im_reply_time =comment.getIm_reply_time();
	try {
		
		psmt =conn.prepareStatement(sql);
		psmt.setInt(1, im_id);
		psmt.setInt(2, u_id);
		psmt.setString(3, im_reply_content);
		psmt.setString(4, im_reply_time);
		psmt.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return true;
}

@Override
public boolean collection(int u_id, int b_id) {
	// TODO Auto-generated method stub
	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;

	conn=JDBCUtil.getConn();
	try {
		pst=conn.prepareStatement("insert into bar_collect(u_id,b_id,state)values(?,?,1)");
		pst.setInt(1, u_id);
		pst.setInt(2,b_id);
		
	
		int count=pst.executeUpdate();
		if(count>0){
			return true;
		}else{
			return false;
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
	

}

@Override
public boolean deleteCollection(int u_id, int b_id) {
	// TODO Auto-generated method stub
	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;

	conn=JDBCUtil.getConn();
	try {
		pst=conn.prepareStatement("delete from bar_collect where  u_id=? and b_id=?");
		pst.setInt(1, u_id);
		pst.setInt(2,b_id);
		
	
		int count=pst.executeUpdate();
		if(count>0){
			return true;
		}else{
			return false;
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return false;
}
public List<String> getIconByPage(int pageSize,int b_id,int currentPage){
	
	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	List<String> list=new ArrayList<String>();
	conn=JDBCUtil.getConn();
	int totalPage=getBarPostPage(pageSize,b_id);
	int start=(currentPage-1)*pageSize;
	try{
	pst=conn.prepareStatement("select image from bar_post  where b_id=? limit ?,?");
	pst.setInt(1,b_id );
	pst.setInt(2,start );
	pst.setInt(3,pageSize );
	rs=pst.executeQuery();
	while(rs.next()){
		
		String image=rs.getString("image");
		list.add(image);
	}


	}catch(Exception e){
		
	}
	return list;
	
	
	
	
	
}


public int getBarPostPage(int pageSize,int b_id){
	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	
	conn=JDBCUtil.getConn();
	try {
		
		pst=conn.prepareStatement("select count(*) from bar_post where b_id=? ");
		pst.setInt(1,b_id);
		
		rs=pst.executeQuery();
		while(rs.next()){
			int total=rs.getInt(1);
			return(total+pageSize-1)/pageSize;
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return 0;
	
}

@Override
public boolean updateUser(ChangeUser list) {
	Connection conn = JDBCUtil.getConn();
	PreparedStatement psmt = null;
	ResultSet rs = null;

	int id = list.getU_id();
	String name = list.getU_name();
	String sign = list.getU_sign();
	String city = list.getCity();
	int sex = list.getSex();
	String mail = list.getE_mail();
	String sqlString = "UPDATE `user` SET u_name =?,u_sign =?,city=?,sex=?,e_mail=? WHERE u_id=?";

	try {
		psmt = conn.prepareStatement(sqlString);
		psmt.setString(1, name);
		psmt.setString(2, sign);
		psmt.setString(3, city);
		psmt.setInt(4, sex);
		psmt.setString(5, mail);
		psmt.setInt(6, id);
		psmt.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	return true;

	
	
}

@Override
public boolean insertSuggest(Suggest suggest) {
	
	Connection conn = JDBCUtil.getConn();
	PreparedStatement psmt = null;
	ResultSet rs = null;

	int u_id = suggest.getU_id();
	String u_suggest = suggest.getU_suggest();
	String sqlString = "INSERT INTO suggest(u_id,suggest) VALUES(?,?)";

	try {
		psmt = conn.prepareStatement(sqlString);
		psmt.setInt(1, u_id);
		psmt.setString(2, u_suggest);
		psmt.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


	
	return true;
}

@Override
public User getUserInfoById2(String phone) {
	Connection conn = JDBCUtil.getConn();
	PreparedStatement psmt = null;
	ResultSet rs = null;
	String sqlString = "select * from user where phone=?";
	try {
		psmt = conn.prepareStatement(sqlString);
		// psmt.setString(1, name);
		psmt.setString(1, phone);

		rs = psmt.executeQuery();
		System.out.println("why");
		if (rs.next()) {
			String u_name = rs.getString("u_name");
			String icon = rs.getString("icon");
			String u_sign = rs.getString("u_sign");
			String city = rs.getString("city");
			int sex = rs.getInt("sex");
			String e_mail = rs.getString("e_mail");
			int money = rs.getInt("money");
			int u_id = rs.getInt("u_id");
			User user = new User(u_id, u_name, icon, u_sign, city, sex, e_mail, money);

			return user;

		}

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	return null;

	
	
	
	
}

@Override
public boolean sendActivityComment(int u_id, String content, int activity_id) {
	// TODO Auto-generated method stub

	Connection conn=null;
	PreparedStatement pst=null;

	conn=JDBCUtil.getConn();
	try{
		pst=conn.prepareStatement("insert into activitycoment(m_act_id,content,u_id)values(?,?,?)");
		pst.setInt(1,activity_id);
		pst.setString(2, content);
		pst.setInt(3,u_id);
		
		int count=pst.executeUpdate();
		if(count>0){
			return true;
		}else{
			return false;
		}
	
		
	}catch(Exception e){
		e.printStackTrace();
	}finally {
		close(conn, pst, null);
	}
	return false;
	
	
	
	
	
	

}

@Override
public List<ActivityComment> getListActivityComment(int act_id) {
	// TODO Auto-generated method stub
	List<ActivityComment> list=new ArrayList<ActivityComment>();
	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	
	conn=JDBCUtil.getConn();
	try {
		pst=conn.prepareStatement("select *from activitycoment where m_act_id=?");
		pst.setInt(1,act_id);
	//把sql语句传入数据库并把数据库处理结果返回
	rs=pst.executeQuery();//从pst中查询
	while (rs.next()) {
		
	 int u_id=rs.getInt("u_id");
		String u_name=getu_name(u_id);
		 int act_com_id=rs.getInt("act_com_id");
	 
		 String content=rs.getString("content");
		 Timestamp time=rs.getTimestamp("time");
		String s_icon=getu_icon(u_id);
		ActivityComment ac=new ActivityComment(s_icon, u_id, u_name, act_com_id, act_id, content, time);
		list.add(ac);
	}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		close(conn, pst, rs);
	}
	return list;

}
public String getu_name(int u_id){
	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	conn=JDBCUtil.getConn();
	try{
		pst=conn.prepareStatement("select u_name from user  where u_id=?");
		pst.setInt(1,u_id);
	//把sql语句传入数据库并把数据库处理结果返回
	rs=pst.executeQuery();//从pst中查询
	while (rs.next()) {
		String u_name=rs.getString("u_name");
		
		return u_name;
	}
		
	}catch(Exception e){
		e.printStackTrace();
	}finally {
		close(conn, pst, rs);
	}
	return null;
	
}
public String getu_icon(int u_id){
	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	conn=JDBCUtil.getConn();
	try{
		pst=conn.prepareStatement("select icon from user  where u_id=?");
		pst.setInt(1,u_id);
	//把sql语句传入数据库并把数据库处理结果返回
	rs=pst.executeQuery();//从pst中查询
	while (rs.next()) {
		String icon=rs.getString("icon");
		
		return icon;
	}
		
	}catch(Exception e){
		e.printStackTrace();
	}finally {
		close(conn, pst, rs);
	}
	return null;
	
}


@Override
public List<OrderTopBarber> getTopBarbers() {
	// TODO Auto-generated method stub
	
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		List<OrderTopBarber> list=new ArrayList<OrderTopBarber>();
		conn=JDBCUtil.getConn();
		try {
			pst=conn.prepareStatement("select *from orderbarber  ORDER BY pop_degree  desc LIMIT 0,8");
		//把sql语句传入数据库并把数据库处理结果返回
		rs=pst.executeQuery();//从pst中查询
		while (rs.next()) {
			int b_id=rs.getInt("b_id");
		 String b_name=rs.getString("b_name");
					 String s_name=rs.getString("s_name");
					 OrderTopBarber otb=new OrderTopBarber(b_id, b_name, s_name);
					 list.add(otb);
			
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pst!=null){
				try {
					pst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
		
		
		
	}

@Override
public boolean changeUserIconById(ChangeUser changeUser) {
	Connection conn = JDBCUtil.getConn();
	PreparedStatement psmt = null;
	ResultSet rs = null;
	
	String sql = "UPDATE user SET icon=? WHERE u_id=?";
	int u_id = changeUser.getU_id();
	String icon = changeUser.getIcon();
	
	try {
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, icon);
		psmt.setInt(2, u_id);
		psmt.executeUpdate();
		return true;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		close(conn, psmt, rs);
	}
	
	return false;
}
  public void close(Connection conn,PreparedStatement psmt,ResultSet rs)
  {if(conn!=null){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	if(rs!=null){
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	if(psmt!=null){
		try {
			psmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	  
  }
	public boolean addInfoMessagePraise(InfoMessagePraise infoMessagePraise) {
		Connection conn = JDBCUtil.getConn();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = "INSERT INTO im_praise(u_id,im_id,im_p_time)VALUES(?,?,?)";
		int u_id = infoMessagePraise.getU_id();
		int im_id = infoMessagePraise.getIm_id();
		String im_p_time = infoMessagePraise.getIm_p_time();
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, u_id);
			psmt.setInt(2, im_id);
			psmt.setString(3, im_p_time);
			psmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}

	public int getInfoMessagePraiseNum(int im_id) {
		Connection conn = JDBCUtil.getConn();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT COUNT(*) FROM im_praise WHERE im_id=?";
		int count = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, im_id);
		
			rs = psmt.executeQuery();
			while(rs.next()){
			count = rs.getInt(1);
			
			
			}
			System.out.println(count);
			return count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 0;
	}

	public boolean cutInfoMessagePraise(InfoMessagePraise infoMessagePraise){
		Connection conn = JDBCUtil.getConn();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = "DELETE FROM im_praise WHERE im_id=? AND u_id =?";
		int u_id = infoMessagePraise.getU_id();
		int im_id = infoMessagePraise.getIm_id();
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, im_id);
			psmt.setInt(2, u_id);
			psmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return false;
		
	}

	@Override
	public List<UserPost> getUserPost() {
		// TODO Auto-generated method stub
		Connection conn=null;
		List<UserPost>list=new ArrayList<UserPost>();
		PreparedStatement pst=null;
		ResultSet rs=null;
		conn=JDBCUtil.getConn();
		try {
			pst=conn.prepareStatement("select u_id,m_self_id,content,image,time from selfshow  ");
			rs=pst.executeQuery();
			while(rs.next()){
			int	m_self_id= rs.getInt("m_self_id");
			int u_id=rs.getInt("u_id");
			String content=rs.getString("content");
			String image=rs.getString("image");
			Timestamp time=rs.getTimestamp("time");
			int countgood=getCountGood(m_self_id);
			String u_name=getName(u_id);
			String u_icon=getIcon(u_id);
			UserPost up=new UserPost(u_icon, u_id, countgood, u_name, image, content, time);
			list.add(up);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		        close(conn, pst, rs);
		}
		return list;

		
		
		
			
	}
	

public String getName(int u_id){
	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	
	conn=JDBCUtil.getConn();
	try {
		
		pst=conn.prepareStatement("select u_name from user where u_id=? ");
		pst.setInt(1, u_id);
		rs=pst.executeQuery();
		while(rs.next()){
			String u_name=rs.getString("u_name");
			return u_name;
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		close(conn, pst, rs);
	}
	return null;
	
	
	
	
}

public String getIcon(int u_id){
	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	
	conn=JDBCUtil.getConn();
	try {
		
		pst=conn.prepareStatement("select icon from user where u_id=? ");
		pst.setInt(1, u_id);
		rs=pst.executeQuery();
		while(rs.next()){
			String icon=rs.getString("icon");
			return icon;
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		close(conn, pst, rs);
	}
	return null;
	
	
	
	
}

public int getCountGood(int m_self_id){
	
	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	
	conn=JDBCUtil.getConn();
	try {
		
		pst=conn.prepareStatement("select count(u_id) from user_praise where m_self_id=? ");
		pst.setInt(1, m_self_id);
		rs=pst.executeQuery();
		while(rs.next()){
			int count=rs.getInt(1);
			return count;
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		close(conn, pst, rs);
	}
	return 0;
	
	
	
	
	
}



public boolean addUserPostPraise(){
	
	Connection conn=null;
PreparedStatement pst=null;
	ResultSet rs=null;
	
	conn=JDBCUtil.getConn();
	try {
		
		pst=conn.prepareStatement("insert into user_praise(u_id, m_self_id)values(1,1)");
		int count=pst.executeUpdate();
		if(count>0){
			return true;
		}else{
			return false;
		}
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		 close(conn, pst, rs);
	}
	return false;
	
	
	
	
	
}

public boolean deleteUserPostPraise(){
	
	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	
	conn=JDBCUtil.getConn();
	try {
		
		pst=conn.prepareStatement("delete from user_praise where u_id=1 and m_self_id=1");
		int count=pst.executeUpdate();
		if(count>0){
			return true;
		}else{
			return false;
		}
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		close(conn, pst, rs);
	}
	return false;
	
	
	
	
	
}
	//使用预约码	
public boolean useCode( String res_num){
	Connection conn=null;
	PreparedStatement pst=null;
	conn=JDBCUtil.getConn();
	try {
		
		pst=conn.prepareStatement("update reservation set state=1 where res_num=?");
		pst.setString(1, res_num);
		int count=pst.executeUpdate();
		if(count>0){
			return true;
		}else{
			return false;
		}
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		
	}
	return false;
	
	
	
}
}



	