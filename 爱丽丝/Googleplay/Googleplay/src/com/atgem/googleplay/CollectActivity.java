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
import android.widget.Toast;
import bean.Collect;
import bean.Url;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class CollectActivity extends Activity {
	  private ListView lv;
	   class MyAdapter extends BaseAdapter{
	 	  private List<Collect>list1;
	 public MyAdapter(List<Collect>list){
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
	 		Collect v=list1.get(position);
	 		if (view==null) {
	 		view=	LayoutInflater.from(CollectActivity.this).inflate(R.layout.collect_item, null);
	 		}
	 		TextView tv1=(TextView) view.findViewById(R.id.tv_shopname);
	 		tv1.setText(v.getS_name());
	 		TextView tv2=(TextView) view.findViewById(R.id.tv_barbername);
	 		tv2.setText(v.getB_name());
	 		TextView tv3=(TextView) view.findViewById(R.id.tv_bpos);
	 		tv3.setText(v.getB_position());
	 		BitmapUtils utils=new BitmapUtils(CollectActivity.this);
	 		ImageView iv=(ImageView) view.findViewById(R.id.image_barbericon);
	 	    utils.display(iv,v.getB_icon());
	 		return view;
	 	}
	 	
	 	
	 	  
	   }
	
	
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_collect);
 	lv=(ListView)findViewById(R.id.lv_act);
	 	
	 	
	 	HttpUtils httpUtils=new HttpUtils();

	 	httpUtils.send(HttpMethod.POST, Url.url+":8080/Alisi2/ShowCollectServlet", new RequestCallBack<String>(){

	 		@Override
	 		public void onFailure(HttpException arg0, String arg1) {
	 			// TODO Auto-generated method stub
	 			Toast.makeText(CollectActivity.this, "ddlnlsnlnl", Toast.LENGTH_LONG).show();
	 		}

	 		@Override
	 		public void onSuccess(ResponseInfo<String> a) {
	 			// TODO Auto-generated method stub
	 			
	 			
	 			 String b=a.result;
	 			 Gson gson=new  Gson();
	 			 Type classOfT=new TypeToken<List<Collect>>(){}.getType();
	 		
	 			 
	 			 List<Collect> list= gson.fromJson(b, classOfT);
	 			 MyAdapter adapter=new MyAdapter(list);
	 				lv.setAdapter(adapter);
	 				
	 			
	 		}});
		
		
		
		
		
		
	
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.collect, menu);
		return true;
	}

}
