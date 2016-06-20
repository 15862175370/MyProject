package com.atgem.googleplay;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import bean.UnuseInfo;
import bean.Url;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.atgem.ailisidemo.activity.MyApplication;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.image.SmartImageView;

public class UnuseOrderActivity extends Activity {
	private ListView unuselist;
	private List<UnuseInfo> list;
	private UnuseInfo unuseInfo;
	UnuseInfoAdapter unuseInfoAdapter;
	Type classOfT;
	int count=0;

	class UnuseInfoAdapter extends BaseAdapter{
           List<UnuseInfo> aInfos;
	  
		

		public UnuseInfoAdapter(List<UnuseInfo> aInfos) {
			
			this.aInfos = aInfos;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return aInfos.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View arg0, ViewGroup arg2) {
			// TODO Auto-generated method stub
			unuseInfo=aInfos.get(position);
			
			if(arg0==null){
				arg0=View.inflate(UnuseOrderActivity.this,R.layout.unuseorderitem,null);
			}
			
			SmartImageView siv=(SmartImageView)arg0.findViewById(R.id.iv_storeimage);
			TextView shopName=(TextView) arg0.findViewById(R.id.tv_storename);
			TextView price=(TextView) arg0.findViewById(R.id.tv_price);
			TextView quantity=(TextView) arg0.findViewById(R.id.tv_quantity);
			TextView textprice=(TextView) arg0.findViewById(R.id.tv_textprice);
			TextView buytime=(TextView) arg0.findViewById(R.id.tv_buytime);
			TextView use=(TextView) arg0.findViewById(R.id.tv_use);
			
			
			shopName.setText(unuseInfo.getShopName());
			price.setText(unuseInfo.getTotalPirce()+"");
			System.out.println(unuseInfo.getTotalPirce());
			
			/*SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日    HH:mm:ss",Locale.CHINA);       
			Date curDate=new Date(System.currentTimeMillis());//获取当前时间       
			String str= sdf.format(curDate); */ 
			

			buytime.setText(unuseInfo.getTime());
			quantity.setText(unuseInfo.getTotalCount()+"");
			System.out.println(unuseInfo.getTotalCount());
			
		buytime.setText(unuseInfo.getTime());
			use.setText(unuseInfo.getUseState());
			siv.setImageUrl(unuseInfo.getImageUrl());
			use.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
				/*	Intent intent=new Intent(UnuseOrderActivity.this,YuyueActivity.class);
				   
				startActivity(intent);*/
					SharedPreferences sharedPreferences = getSharedPreferences("yuyuema",MODE_PRIVATE);
					int res_num=sharedPreferences.getInt("res_num",0);
					Toast.makeText(UnuseOrderActivity.this,"亲，你的预约码是："+res_num,Toast.LENGTH_LONG).show();
					
				}
			});
			
			return arg0;
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_unuse_order);
		showAcitonBar();
		unuselist=(ListView) findViewById(R.id.lv_unuse);
		 SharedPreferences sp = getSharedPreferences("userInfo",MODE_PRIVATE);
		  int u_id=sp.getInt("u_id",0);
		volley_Get(Url.url+":8080/Alisi2/ShowUnuseInfoServlet?u_id="+u_id);
	}


	public void volley_Get(String url){
		//Url.url+"10.203.1.23:8080/Alisi2/ShowAllShopInfoServlet
		//String url="Url.url+".23:8080/Alisi2/ShowAllShopInfoServlet";
		StringRequest request=new StringRequest(Method.GET, url, new ResponseListener(),new ResponseErrorListener());
	        request.setTag("getABC"+count++);
	    MyApplication.getRequestQueues().add(request);
	}
	
	
	
	
	class ResponseListener implements Response.Listener<String>{

		@Override
		public void onResponse(String arg0) {
			// TODO Auto-generated method stub
			Log.i("MainActivity",arg0);
			Gson gson=new Gson();
			String json=arg0;
			System.out.println("未使用订单传回来的数据是："+json);
			
			classOfT=new TypeToken<List<UnuseInfo>>(){}.getType();
			list=gson.fromJson(json, classOfT);
			unuseInfoAdapter=new UnuseInfoAdapter(list);
			System.out.println(list.size());
			unuselist.setAdapter(unuseInfoAdapter);
//			
//			classOfT=new TypeToken<List<OrderItem>>(){}.getType();
//			 list=g.fromJson(json, classOfT);//ת����list����
//			  orderInfoAdapter=new OrderInfoAdapter();
//			orderList.setAdapter(orderInfoAdapter);
		}
		
	}
	
	
	
	
	class ResponseErrorListener implements Response.ErrorListener{

		@Override
		public void onErrorResponse(VolleyError arg0) {
			// TODO Auto-generated method stub
			   Log.i("UnuseOrderActivity","请求发生错误....");
		}
		
	}
	public void showAcitonBar(){
		 ActionBar actionBar = getActionBar();  
	       actionBar.setDisplayHomeAsUpEnabled(true);  
	         
	       ActionBar.LayoutParams lp =new ActionBar.LayoutParams(  
	                 ActionBar.LayoutParams.MATCH_PARENT,  
	                 ActionBar.LayoutParams.MATCH_PARENT,  
	                 Gravity.CENTER);  
	       LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
	       View titleView = inflater.inflate(R.layout.action_bar_title, null);  
	       actionBar.setCustomView(titleView, lp);  
	         
	       actionBar.setDisplayShowHomeEnabled(false);//去掉导航  
	       actionBar.setDisplayShowTitleEnabled(false);//去掉标题  
	       actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);  
	       actionBar.setDisplayShowCustomEnabled(true);  
	       TextView tv_title=(TextView) actionBar.getCustomView().findViewById(R.id.title);
	         tv_title.setText("订单");
	       ImageButton imageBtn = (ImageButton) actionBar.getCustomView().findViewById(R.id.image_btn);  
	         
	       imageBtn.setOnClickListener(new View.OnClickListener() {  
	             
	           @Override  
	           public void onClick(View v) {  
	              finish();
	           
	           }  
	       });  
	}
}
