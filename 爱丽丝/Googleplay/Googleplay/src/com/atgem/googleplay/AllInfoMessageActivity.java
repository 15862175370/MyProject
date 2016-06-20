package com.atgem.googleplay;

import java.lang.reflect.Type;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import bean.AllInfoMessage;
import bean.BarpostItem;
import bean.Url;

import com.atgem.ailisidemo.WorksActivity;
import com.atgem.lunbodemo.HomeActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.heima52.pullrefresh.view.RefreshListView;
import com.heima52.pullrefresh.view.RefreshListView.OnRefreshListener;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class AllInfoMessageActivity extends Activity {
	private List<AllInfoMessage> list;
	private RefreshListView lv;
	AllInfoMessAdapter adapter ;
	 GridView gv;
	 int[] images=new int[]{R.drawable.home1,R.drawable.tuijian1,R.drawable.zixun,R.drawable.yuyue1};
	 private Handler handler = new Handler(){
			public void handleMessage(android.os.Message msg) {
				//更新UI
				adapter.notifyDataSetChanged();
				lv.completeRefresh();
			};
		};
	 
	 
	 
	 
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_info_message);
		lv = (RefreshListView) findViewById(R.id.lv_allinfomess);
		gv=(GridView) findViewById(R.id.gv_news);
		lv.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onPullRefresh() {
				//需要联网请求服务器的数据，然后更新UI
				requestDataFromServer(true);
			}

			@Override
			public void onLoadingMore() {
				
				requestDataFromServer(true);
			}
		});
		showAcitonBar();
		String url =Url.url+":8080/Alisi2/AllInfoMessageServlet";
		initData(url);
	/*	lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(position==1){
					System.out.println("位置是："+position);
					Intent intent = new Intent(AllInfoMessageActivity.this,InfoMessageActivity.class);
					startActivity(intent);
				}
				
			}
		});
		*/
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				if(position==1)
				{
					
					
					
					Intent intent = new Intent(AllInfoMessageActivity.this,InfoMessageActivity.class);
					startActivity(intent);
				}
				
				
			}
		});

		gv.setAdapter(new GvAdapter());
		 gv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int position,
						long arg3) {
					// TODO Auto-generated method stub
					
					switch (position) {
					case 0:
						Intent intent_home=new Intent(AllInfoMessageActivity.this,HomeActivity.class);
						startActivity(intent_home);
						
						
						
						break;
					 case 1:
		            	   Intent intent3=new Intent(AllInfoMessageActivity.this,WorksActivity.class);
							startActivity(intent3);
		            	 
			                 
			              break;
	              
	              /* case 2:
	            	   Intent intent3=new Intent(AllInfoMessageActivity.this,AllInfoMessageActivity.class);
						startActivity(intent3);
	            	 
		                 
		              break;*/
	               case 3:
	            	   Intent intent4=new Intent(AllInfoMessageActivity.this,OrderMainActivity.class);
						startActivity(intent4);
	            	 
		                 
		              break;
					default:
						break;
					}
					
					
					
					
				}
			});
		
		
		
	}
	/**
	 * 模拟向服务器请求数据
	 */
	private void requestDataFromServer(final boolean isLoadingMore){
		new Thread(){
			public void run() {
				SystemClock.sleep(3000);//模拟请求服务器的一个时间长度
				Timestamp a = new Timestamp(new Date().getTime());
				if(isLoadingMore){
				list.add(new AllInfoMessage(3,Url.url+":8080/Alisi2/images/canvas5.png"));
			//	barpostItems.add(new BarpostItem(b_name, b_position, b_icon, content, image, time, commentCount, zanCount, belong))
				}else {
				//	barpostItems.add(new );
					//list.add(new BarpostItem("刘远","小职员","Url.url+"10.40.7.71:8080/Alisi2/upload/ee78adcc-f739-40ef-b99e-d8c86847370f.png","完美发型！","Url.url+"10.40.7.71:8080/Alisi2/upload/9304bb26-7266-4e8f-a8dc-adc9934fcee1.png",a, 5,5,"3"));
					list.add(new AllInfoMessage(3,Url.url+":8080/Alisi2/images/canvas5.png"));
				}
				
				//在UI线程更新UI
				handler.sendEmptyMessage(0);
			};
		}.start();
	}
	private void initData(String url) {
		HttpUtils httputil = new HttpUtils();
		httputil.send(HttpMethod.GET, url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(ResponseInfo<String> allinfomesslist) {
				String json = allinfomesslist.result;
				Gson gson = new Gson();
				list = new ArrayList<AllInfoMessage>();
				Type type = new TypeToken<List<AllInfoMessage>>() {
				}.getType();

				list = gson.fromJson(json, type);
				for(AllInfoMessage o:list){
					System.out.println(o.getIm_id()+o.getIm_logo());
				}
				adapter = new AllInfoMessAdapter(
						list);
				lv.setAdapter(adapter);

			}
		});
	}
	class AllInfoMessAdapter extends BaseAdapter{
		 List<AllInfoMessage> list1;
		 private LayoutInflater inflater = LayoutInflater.from(AllInfoMessageActivity.this);

		 public  AllInfoMessAdapter(List<AllInfoMessage> list) {
			 this.list1 = list;
			 System.out.println(list1.size());
		}
		@Override
		public int getCount() {
			return list1.size();
		}

		@Override
		public Object getItem(int position) {
			return list1.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			AllInfoMessage allInfoMessage = list1.get(position);
			if( convertView == null ){
				convertView = inflater.inflate(R.layout.activity_all_info_message_items, null);
			}
			ImageView logo = (ImageView) convertView.findViewById(R.id.iv_ifomesslogo);
			
			BitmapUtils utils = new BitmapUtils(AllInfoMessageActivity.this);
			utils.display(logo, allInfoMessage.getIm_logo());
			return convertView;
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
	         tv_title.setText("资讯");
	       ImageButton imageBtn = (ImageButton) actionBar.getCustomView().findViewById(R.id.image_btn);  
	         
	       imageBtn.setOnClickListener(new View.OnClickListener() {  
	             
	           @Override  
	           public void onClick(View v) {  
	              finish();
	           
	           }  
	       });  
	}
	 class GvAdapter extends BaseAdapter{
         
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return images.length;
			}

			@Override
			public Object getItem(int arg0) {
				// TODO Auto-generated method stub
				return images[arg0];
			}

			@Override
			public long getItemId(int arg0) {
				// TODO Auto-generated method stub
				return arg0;
			}

			@Override
			public View getView(int arg0, View arg1, ViewGroup arg2) {
				// TODO Auto-generated method stub
				View view=View.inflate(AllInfoMessageActivity.this,R.layout.gv_item,null);
				if(arg1==null){
					
				
				//  View view=View.inflate(HomeActivity.this,R.layout.gv_item,null);
				  
				  arg1=view;
				}
				ImageView imageView=(ImageView) view.findViewById(R.id.iv_item);
				  imageView.setImageResource(images[arg0]);
				
				
				return view;
			}
			 
		 }
}
