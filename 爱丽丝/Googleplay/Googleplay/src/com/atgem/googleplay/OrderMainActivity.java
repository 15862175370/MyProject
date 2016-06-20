package com.atgem.googleplay;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;



import com.atgem.lunbodemo.HomeActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class OrderMainActivity extends Activity implements OnClickListener{
	private TextView tv3;
	private TextView tv1;
	private TextView tv2;
	 GridView gv;
	 int[] images=new int[]{R.drawable.home1,R.drawable.tuijian1,R.drawable.zixun1,R.drawable.yuyue};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_main);
		gv=(GridView) findViewById(R.id.gv_news);
		initView();
		showAcitonBar();
		tv1.setOnClickListener(this);
		tv2.setOnClickListener(this);
		tv3.setOnClickListener(this);
		TextView tv_time=(TextView) findViewById(R.id.tv_time);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日    HH:mm:ss",Locale.CHINA);       
		Date curDate=new Date(System.currentTimeMillis());//获取当前时间       
		String str= sdf.format(curDate);  
		tv_time.setText(str);

		gv.setAdapter(new GvAdapter());
		 gv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int position,
						long arg3) {
					// TODO Auto-generated method stub
					
					switch (position) {
					case 0:
						Intent intent_home=new Intent(OrderMainActivity.this,HomeActivity.class);
						startActivity(intent_home);
						
						
						
						break;
	                
	              
	               case 2:
	            	   Intent intent3=new Intent(OrderMainActivity.this,AllInfoMessageActivity.class);
						startActivity(intent3);
	            	 
		                 
		              break;
	               case 3:
	            	   Intent intent4=new Intent(OrderMainActivity.this,OrderMainActivity.class);
						startActivity(intent4);
	            	 
		                 
		              break;
					default:
						break;
					}
					
					
					
					
				}
			});
		
	}
	public void initView(){
		tv1=(TextView) findViewById(R.id.tv1);
		tv2=(TextView) findViewById(R.id.tv2);
		tv3=(TextView) findViewById(R.id.tv3);
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
	          TextView title=(TextView) titleView.findViewById(R.id.title);
	           title.setText("预约查询");
	        actionBar.setCustomView(titleView, lp);  
	          
	        actionBar.setDisplayShowHomeEnabled(false);//去掉导航  
	        actionBar.setDisplayShowTitleEnabled(false);//去掉标题  
	        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);  
	        actionBar.setDisplayShowCustomEnabled(true);  
	          
	        ImageButton imageBtn = (ImageButton) actionBar.getCustomView().findViewById(R.id.image_btn);  
	          
	        imageBtn.setOnClickListener(new View.OnClickListener() {  
	              
	            @Override  
	            public void onClick(View v) {  
	               finish();
	            
	            }  
	        });  
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.tv1:
			
			break;
      case R.id.tv2:
			Intent unuse=new Intent(this,UnuseOrderActivity.class);
			startActivity(unuse);
			break;
      case R.id.tv3:
	   
    	  Intent intent=new Intent(this,HistoryOrderActivity.class);
    	  startActivity(intent);
    	  
	   break;
		default:
			break;
		}
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
				View view=View.inflate(OrderMainActivity.this,R.layout.gv_item,null);
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
