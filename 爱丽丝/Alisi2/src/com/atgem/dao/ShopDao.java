package com.atgem.dao;

import java.util.List;

import bean.ActivityComment;
import bean.AllInfoMessage;
import bean.Barber;
import bean.BarpostItem;
import bean.ChangeUser;
import bean.Collect;
import bean.Entity;
import bean.FreeTime;
import bean.InfoMessage;
import bean.InfoMessageComment;
import bean.InfoMessagePraise;
import bean.NewActivityInfo;
import bean.OrderItem;
import bean.OrderShop;
import bean.OrderTopBarber;
import bean.PostActivity;
import bean.Shop;
import bean.Suggest;
import bean.UnuseInfo;
import bean.User;
import bean.UserPost;

public interface ShopDao {
   
	public List<Barber> getAllBarberInfo(int s_id);

	public int findShopByShopNameAndPassword(String name, String password);
    public boolean updateServType(int n,int b_id,String price);
	public boolean updatePosition(int b_id,String b_position);
	public boolean updateDesc(int b_id,String b_desc);
	public List<Entity> getAllOrderInfo(int s_id);
	public int checkCode(String code);
	public boolean deleteBarberById(int b_id);
	public boolean updateIcon(String url,int b_id);
	public List<BarpostItem> getAllBarpost();
	public List<PostActivity> getAllActivities();
	public List<OrderShop> getShopByDistance();
	public List<OrderItem> getAllOrdersById(int id);
	public Shop getShopInfoByBarberId(int id);

	public boolean addUserByPhone(String phone);
	public User getUserInfoById(String phone);

	public List<FreeTime> getState(int i);
	public boolean updateUserMoney(int money,int id);
	//			//INSERT into t_order(u_id,b_id,time1,servid,state,day_id)VALUES(2,3,1,1,1,1)
	public boolean addOrder(int u_id,int b_id,int time1,int servid,int state,int day_id);
     public int getMaxId();
     public boolean addReservation(int o_id,String res_num);

	public boolean updateState(int parseInt, String day, String time);

	public List<Collect> getAllCollectInfoById(int id);

	public List<UnuseInfo> getAllUnuseById(int id);

	public List<NewActivityInfo> getAllNewActivityById(int id);
	public boolean addZancountById(int u_id,int m_barber_id);
	public boolean addPostComment(int u_id,int m_barber_id,String content,String imageUrl);
	public InfoMessage getInfoMessageByIm_id(int im_id);
	public List<InfoMessageComment> getImCommentByIm_id(int im_id);
	public User getUserInfoByInfoMessage(int u_id);
	public List<AllInfoMessage> getAllInfoMessage();
	public boolean addInfoMessageComment(InfoMessageComment comment);

	public boolean collection(int u_id, int b_id);

	public boolean deleteCollection(int u_id, int b_id);
	public boolean updateUser(ChangeUser list);
	public boolean insertSuggest(Suggest suggest);
	public User getUserInfoById2(String phone);//这是王蓉的方法

	public boolean sendActivityComment(int parseInt, String content, int parseInt2);

	public List<OrderShop> getAllOrderShop();

	public List<ActivityComment> getListActivityComment(int parseInt);

	public List<OrderTopBarber> getTopBarbers();

	public boolean changeUserIconById(ChangeUser changeUser);
	public boolean addInfoMessagePraise(InfoMessagePraise infoMessagePraise);
	public int getInfoMessagePraiseNum(int im_id);
	public boolean cutInfoMessagePraise(InfoMessagePraise infoMessagePraise);

	public List<UserPost> getUserPost();

	public boolean addUserPostPraise();

	public boolean deleteUserPostPraise();
}
