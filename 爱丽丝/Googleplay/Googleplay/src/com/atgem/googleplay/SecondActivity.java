package com.atgem.googleplay;

import java.lang.reflect.Type;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import bean.NewActivityInfo;
import bean.Url;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class SecondActivity extends Activity {
	private ListView lv;
	Type classOfT;
	private ImageView image;
	private TextView tv;
	class MyAdapter  extends BaseAdapter{
	       private List<NewActivityInfo>list1;
	      public MyAdapter (List<NewActivityInfo>list){
	    	  this.list1=list;
	      }
	      
	      
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return list1.size();
			}

			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return list1.get(position);
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}

			@Override
			public View getView(int position, View view, ViewGroup arg2) {
				// TODO Auto-generated method stub
				NewActivityInfo n=list1.get(position);
				
				if(view==null){
					view=LayoutInflater.from(SecondActivity.this).inflate(R.layout.secondactivityitem, null);
				    
				}
				
				TextView tv=(TextView) view.findViewById(R.id.tv_activity);
				tv.setText(n.getContent());
				
				BitmapUtils utils=new BitmapUtils(SecondActivity.this); 
				ImageView iv=(ImageView) view.findViewById(R.id.image_activity);
				utils.display(iv, n.getImageUrl());
				return view;
			}
			
		}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
	
	lv=(ListView) findViewById(R.id.lv_common);
		image=(ImageView)findViewById(R.id.image_activity);
		tv=(TextView)findViewById(R.id.tv_activity);
		HttpUtils httpUtils=new HttpUtils();
		RequestParams params=new RequestParams();
		httpUtils.send(HttpMethod.POST,Url.url+":8080/Alisi2/ShowNewActivityInfoServlet", new RequestCallBack<String>(){

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(ResponseInfo<String> a) {
				// TODO Auto-generated method stub
				
				
				 String b=a.result;
				 Gson gson=new  Gson();
				 Type classOfT=new TypeToken<List<NewActivityInfo>>(){}.getType();
			System.out.println(b);
				 
				 List<NewActivityInfo> list= gson.fromJson(b, classOfT);
				 MyAdapter adapter=new MyAdapter(list);
					lv.setAdapter(adapter);
			}});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}
	

}
