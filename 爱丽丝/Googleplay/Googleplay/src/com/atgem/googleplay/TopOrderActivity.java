package com.atgem.googleplay;

import java.lang.reflect.Type;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import bean.ActivityComment;
import bean.OrderTopBarber;
import bean.Url;

import com.atgem.ailisidemo.activity.ShopActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class TopOrderActivity extends Activity {
	private ListView lv;
	private ImageView img1;
	private ListView lv1;
	private TextView btn;
	private EditText et;
	
	String content;
	int u_id;
	
	
	
	
	//评论的adapter
	
	class MyAdapter1 extends BaseAdapter{
		private List<ActivityComment> acList;
		 public MyAdapter1(List<ActivityComment> acList) {
			this.acList=acList;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return acList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return acList.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			BitmapUtils utils=new BitmapUtils(TopOrderActivity.this);
			ActivityComment ac=acList.get(position);
			if(convertView==null){
				convertView=View.inflate(TopOrderActivity.this,R.layout.activitycomment, null);
			}
			ImageView img=(ImageView)convertView.findViewById(R.id.imageView1);
			
			utils.display(img,ac.getS_icon());
			TextView tv=(TextView)convertView.findViewById(R.id.textView1);
			tv.setText(ac.getU_name());
			TextView tv1=(TextView)convertView.findViewById(R.id.textView2);
			tv.setText(ac.getContent());
			TextView tv2=(TextView)convertView.findViewById(R.id.textView3);
			tv2.setText(String.valueOf(ac.getTime()));
			
			return convertView;
		}
		
	}
	
	
	
	
	//人气最高前八位理发师排行榜listview设置监听事件
	class MyAdapter extends BaseAdapter{
		private List<OrderTopBarber> otbList;
	public MyAdapter(List<OrderTopBarber> otb) {
			// TODO Auto-generated constructor stub
		this.otbList=otb;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return otbList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return otbList.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			System.out.println(position);
			OrderTopBarber otb= otbList.get(position);
			if(convertView==null){
				convertView=View.inflate(TopOrderActivity.this,R.layout.topbarbersort, null);
			}
			//排名
			TextView tv1=(TextView) convertView.findViewById(R.id.textView1);
			tv1.setText(String.valueOf((position+1)));
			//理发师名
			TextView tv2=(TextView) convertView.findViewById(R.id.textView2);
			tv2.setText(otb.getB_name());
			//商店名
			TextView tv3=(TextView) convertView.findViewById(R.id.textView3);
			tv3.setText(otb.getS_name());
			
			return convertView;
		}
		
	}
	/*public void xUtilsPost(String url){
		 HttpUtils httpUtils=new HttpUtils();
		 RequestParams params=new RequestParams();
		 params.addBodyParameter("u_id",String.valueOf(u_id));
		 params.addBodyParameter("content",content);
		 params.addBodyParameter("activity_id",String.valueOf(activity_id));
		 httpUtils.send(HttpMethod.POST,"Url.url+"10.202.1.53:8080/WebProject/SendActivityContentServlet", params,new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String json=responseInfo.result;
				if(json.equals("true")){
					Toast.makeText(TopOrderActivity.this, "评论成功", Toast.LENGTH_LONG).show();
					et.setText("");
				}else{
					Toast.makeText(TopOrderActivity.this, "评论失败", Toast.LENGTH_LONG).show();
				}
				
				
			}
		});
		 
		
			
	
	}*/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_activity);
		showAcitonBar();
		 lv1=(ListView)findViewById(R.id.listView2);//评论的listview
			//发送按钮
			btn=(TextView)findViewById(R.id.tv_send);
			et=(EditText)findViewById(R.id.et_talk);
			 int activity_id=1;//此处为前一个activity传来的活动id
				
			 //传一个活动id到数据库得到此活动的评论
			 HttpUtils httpUtils=new HttpUtils();
			 RequestParams params=new RequestParams();
			 params.addBodyParameter("activity_id",String.valueOf(activity_id));
			 httpUtils.send(HttpMethod.POST,Url.url+":8080/Alisi2/ActivityServlet", params,new RequestCallBack<String>() {

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
					String json=responseInfo.result;
					Gson g=new Gson();
					//转换成自定义的OrderTopBarber
					Type classOfT=new TypeToken<List<ActivityComment>>(){}.getType();
					List<ActivityComment> list=g.fromJson(json, classOfT);
					for(ActivityComment ac:list){
						System.out.println(ac.getU_name());
					}
					MyAdapter1 adapter=new MyAdapter1(list);
				     adapter.notifyDataSetChanged();
					lv1.setAdapter(adapter);
					
				
					
				}
			});
			 
			btn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
				 content=et.getText().toString();//得到文本框内容
				/* SharedPreferences sp=getSharedPreferences("userInfo",);
				int u_id=sp.getInt("u_id",1);*/
				 SharedPreferences sp = getSharedPreferences("userInfo",MODE_PRIVATE);
			       int u_id=sp.getInt("u_id",0);
				//  u_id=1;//用户登陆后的id虚拟的
				 int activity_id=1;
				//评论内容和用户id传到服务器
				/* HttpUtils httpUtils=new HttpUtils();
				 RequestParams params=new RequestParams();
				 params.addBodyParameter("u_id",String.valueOf(u_id));
				 params.addBodyParameter("content",content);
				 params.addBodyParameter("activity_id",String.valueOf(activity_id));
				 httpUtils.send(HttpMethod.POST,Url.url+":8080/WebProject/SendActivityContentServlet", params,new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						String json=responseInfo.result;
						if(json.equals("true")){
							Toast.makeText(TopOrderActivity.this, "评论成功", Toast.LENGTH_LONG).show();
							et.setText("");
						}else{
							Toast.makeText(TopOrderActivity.this, "评论失败", Toast.LENGTH_LONG).show();
						}
						
						
					}
				});*/
				 xUtilsPost(Url.url+":8080/Alisi2/SendActivityContentServlet",u_id, activity_id,content);
				 
				 //传一个活动id到数据库得到此活动的评论
				/* HttpUtils httpUtils=new HttpUtils();
				 RequestParams params=new RequestParams();
				 params.addBodyParameter("activity_id",String.valueOf(activity_id));
				 httpUtils.send(HttpMethod.POST,Url.url+":8080/Alisi2/ActivityServlet", params,new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						String json=responseInfo.result;
						Gson g=new Gson();
						//转换成自定义的OrderTopBarber
						Type classOfT=new TypeToken<List<ActivityComment>>(){}.getType();
						List<ActivityComment> list=g.fromJson(json, classOfT);
						for(ActivityComment ac:list){
							System.out.println(ac.getU_name());
						}
						MyAdapter1 adapter=new MyAdapter1(list);
					     adapter.notifyDataSetChanged();
						lv1.setAdapter(adapter);
						
					
						
					}
				});*/
				 
				
					
				}
			});
			
			//返回按钮事件
		/*	img1=(ImageView)findViewById(R.id.imageView1);
			img1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Toast.makeText(TopOrderActivity.this, "返回主界面", Toast.LENGTH_LONG).show();
					
				}
			});
			*/
			
			 
	/*		 int activity_id=1;//此处为前一个activity传来的活动id
			
			 //传一个活动id到数据库得到此活动的评论
			 HttpUtils httpUtils=new HttpUtils();
			 RequestParams params=new RequestParams();
			 params.addBodyParameter("activity_id",String.valueOf(activity_id));
			 httpUtils.send(HttpMethod.POST,"Url.url+":8080/Alisi2/ActivityServlet", params,new RequestCallBack<String>() {

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
					String json=responseInfo.result;
					Gson g=new Gson();
					//转换成自定义的OrderTopBarber
					Type classOfT=new TypeToken<List<ActivityComment>>(){}.getType();
					List<ActivityComment> list=g.fromJson(json, classOfT);
					for(ActivityComment ac:list){
						System.out.println(ac.getU_name());
					}
					MyAdapter1 adapter=new MyAdapter1(list);
				     adapter.notifyDataSetChanged();
					lv1.setAdapter(adapter);
					
				
					
				}
			});*/
			 
			 
		
			 lv=(ListView)findViewById(R.id.listView1);	//人气最高前八位理发师排行榜listview
			
		
			 //请求服务器返回人气最高前八位理发师
		HttpUtils http=new HttpUtils();
		http.send(HttpMethod.POST, Url.url+":8080/Alisi2/TopBarberSortServlet", new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				String json=responseInfo.result;
				Gson g=new Gson();
				//转换成自定义的OrderTopBarber
				Type classOfT=new TypeToken<List<OrderTopBarber>>(){}.getType();
				List<OrderTopBarber> list12=g.fromJson(json, classOfT);
				MyAdapter adapter=new MyAdapter(list12);
				
				lv.setAdapter(adapter);
				
				
			}
			
		}

		);
	 
		
		 
		 
		}
		
		public void xUtilsPost(String url,int u_id,int activity_id,String content){
			 HttpUtils httpUtils=new HttpUtils();
			 RequestParams params=new RequestParams();
			 params.addBodyParameter("u_id",String.valueOf(u_id));
			 params.addBodyParameter("content",content);
			 params.addBodyParameter("activity_id",String.valueOf(activity_id));
			 httpUtils.send(HttpMethod.POST,url, params,new RequestCallBack<String>() {

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
					String json=responseInfo.result;
					if(json.equals("true")){
						Toast.makeText(TopOrderActivity.this, "评论成功", Toast.LENGTH_LONG).show();
						et.setText("");
						/*String json2=responseInfo.result;
						Gson g=new Gson();
						//转换成自定义的OrderTopBarber
						Type classOfT=new TypeToken<List<ActivityComment>>(){}.getType();
						List<ActivityComment> list=g.fromJson(json2, classOfT);
						for(ActivityComment ac:list){
							System.out.println(ac.getU_name());
						}
						MyAdapter1 adapter=new MyAdapter1(list);
					     adapter.notifyDataSetChanged();
						lv1.setAdapter(adapter);*/
						 HttpUtils httpUtils=new HttpUtils();
						 RequestParams params=new RequestParams();
						 params.addBodyParameter("activity_id","1");
						 httpUtils.send(HttpMethod.POST,Url.url+":8080/Alisi2/ActivityServlet", params,new RequestCallBack<String>() {

							@Override
							public void onFailure(HttpException arg0, String arg1) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void onSuccess(ResponseInfo<String> responseInfo) {
								String json=responseInfo.result;
								Gson g=new Gson();
								//转换成自定义的OrderTopBarber
								Type classOfT=new TypeToken<List<ActivityComment>>(){}.getType();
								List<ActivityComment> list=g.fromJson(json, classOfT);
								for(ActivityComment ac:list){
									System.out.println(ac.getU_name());
								}
								MyAdapter1 adapter=new MyAdapter1(list);
							     adapter.notifyDataSetChanged();
								lv1.setAdapter(adapter);
								
							      adapter.notifyDataSetChanged();
								
							}
						});
					
						
					}else{
						Toast.makeText(TopOrderActivity.this, "评论失败", Toast.LENGTH_LONG).show();
					}
					
					
				}
			});
			 
			
				
		
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
	          TextView tvtitle=(TextView) actionBar.getCustomView().findViewById(R.id.title);
	          tvtitle.setText("发型师排行榜");
	        ImageButton imageBtn = (ImageButton) actionBar.getCustomView().findViewById(R.id.image_btn);  
	          
	        imageBtn.setOnClickListener(new View.OnClickListener() {  
	              
	            @Override  
	            public void onClick(View v) {  
	            	
	               finish();  
	            
	            }  
	        });  
	}

}
