package com.atgem.googleplay;

import java.lang.reflect.Type;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import bean.OrderItem;
import bean.OrderShop;
import bean.Url;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.atgem.ailisidemo.activity.MyApplication;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.king.photo.activity.MainActivity;
import com.loopj.android.image.SmartImageView;

public class HistoryOrderActivity extends Activity {
	  Type classOfT;
	   List<OrderItem> list;
	   int count=0;
	   private ListView orderList;
	   private OrderItem orderItem;
	   Button pingjia;
	   TextView orderState;
	   OrderInfoAdapter orderInfoAdapter;
	 class OrderInfoAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			orderItem=list.get(position);
			System.out.println("用户当前消费额为"+orderItem.getTotalPirce());
			if(arg1==null){
				arg1=View.inflate(HistoryOrderActivity.this,R.layout.orderitem,null);
			}
			  SmartImageView siv=(SmartImageView)arg1.findViewById(R.id.image_shop);
			TextView shopName=(TextView)arg1.findViewById(R.id.tv_shop);
			TextView totalPrice=(TextView)arg1.findViewById(R.id.tv_totalnumber);
		   orderState=(TextView)arg1.findViewById(R.id.tv_pinglun);
			  pingjia=(Button)arg1.findViewById(R.id.btn_pingjia);
			   shopName.setText(orderItem.getShopName());
			   totalPrice.setText(orderItem.getTotalPirce());
			  orderState.setText(orderItem.getOrderState());
			  if(orderItem.getOrderState().equals("已评论")){
				  pingjia.setVisibility(View.INVISIBLE);
			  }else{
				  pingjia.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						Intent turntoPingjia=new Intent(HistoryOrderActivity.this,MainActivity.class);
						   turntoPingjia.putExtra("评价","评价");
						
						startActivityForResult(turntoPingjia, 1);
					}
				});
			  }
			
			siv.setImageUrl(orderItem.getImageUrl());
			
			return arg1;
		}
		 
	 }
	   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history_order);
		orderList=(ListView) findViewById(R.id.listView1);
		  SharedPreferences sp = getSharedPreferences("userInfo",MODE_PRIVATE);
		  int u_id=sp.getInt("u_id",0);
		volley_Get(Url.url+":8080/Alisi2/ShowOrderInfoServlet?u_id="+u_id);
	}
  
	public void volley_Get(String url){
		//Url.url+"localhost:8080/Alisi2/ShowAllShopInfoServlet
		//String url="Url.url+"10.40.7.71:8080/Alisi2/ShowAllShopInfoServlet";
		StringRequest request=new StringRequest(Method.GET, url, new ResponseListener(),new ResponseErrorListener());
	        request.setTag("getABC"+count++);
	    MyApplication.getRequestQueues().add(request);
	}
	//
	class ResponseListener implements Response.Listener<String>{

		@Override
		public void onResponse(String arg0) {
			// TODO Auto-generated method stub
			
			Log.i("MainActivity",arg0);
			Gson g=new Gson();
			String json=arg0;
		
	 
		classOfT=new TypeToken<List<OrderItem>>(){}.getType();
		 list=g.fromJson(json, classOfT);//ת����list����
		 if(list!=null)
		 { orderInfoAdapter=new OrderInfoAdapter();
		orderList.setAdapter(orderInfoAdapter);}
		 else{
				Toast.makeText(HistoryOrderActivity.this,"亲，你暂时还没有订单记录哦...",Toast.LENGTH_SHORT).show();

			}
			
		
			  
		}
		
	}
	//ʧ�ܺ���ô˷���
	class ResponseErrorListener implements Response.ErrorListener{

		@Override
		public void onErrorResponse(VolleyError arg0) {
			// TODO Auto-generated method stub
		          Log.i("ShopActicity","请求发生错误....");
			
		}
		
	}
	//@Override
/*	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		
		 int hasComment = data.getIntExtra("hasComment", -1);
		   if(hasComment==666){
			   pingjia.setVisibility(View.INVISIBLE);
			   //orderState.setText("已评论..");
			   orderItem.setOrderState("已评论");
			   orderInfoAdapter.notifyDataSetChanged();
		   }
		
		
	
	}*/
}
