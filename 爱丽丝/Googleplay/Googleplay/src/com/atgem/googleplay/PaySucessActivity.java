package com.atgem.googleplay;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import bean.FixBarber;
import bean.OrderShop;
import bean.ServcePrice;
import bean.Url;

import com.atgem.ailisidemo.WorksActivity;
import com.atgem.ailisidemo.activity.ShopActivity;
import com.atgem.lunbodemo.HomeActivity;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

public class PaySucessActivity extends Activity {
	TextView tvOrderName;
	TextView yuyuema;
	TextView return_home;
	TextView continueshop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.paysuccess);
		tvOrderName=(TextView) findViewById(R.id.tv_odername);
		yuyuema=(TextView) findViewById(R.id.yuyuema);
		FixBarber barber=(FixBarber)getIntent().getSerializableExtra("bar");
		OrderShop   shop=	(OrderShop)  getIntent().getSerializableExtra("shop");
		final ServcePrice sPrice=(ServcePrice) getIntent().getSerializableExtra("price");
		Bundle bundle=new Bundle();
		bundle.putSerializable("bar", barber);
		bundle.putSerializable("shop",shop);
	    bundle.putSerializable("price", sPrice);
		tvOrderName.setText(shop.getS_name()+"预约券");
		showAcitonBar();
		SharedPreferences sp = getSharedPreferences("orderId",MODE_PRIVATE);
	  SharedPreferences sharedPreferences = getSharedPreferences("yuyuema", MODE_PRIVATE);
		SharedPreferences.Editor editor=sharedPreferences.edit();
		
	
	
	int i=(int) (Math.random()*1000+10000);
	editor.putInt("res_num", i);
	editor.commit();
		yuyuema.setText(i+"");
		RequestParams params = new RequestParams();
		params.addHeader("name", "value");
		params.addQueryStringParameter("name", "value");

		// 只包含字符串参数时默认使用BodyParamsEntity，
		// 类似于UrlEncodedFormEntity（"application/x-www-form-urlencoded"）。
		params.addBodyParameter("o_id",String.valueOf(sp.getInt("o_id",0)));
		params.addBodyParameter("res_num",String.valueOf(i));

		// 加入文件参数后默认使用MultipartEntity（"multipart/form-data"），
		// 如需"multipart/related"，xUtils中提供的MultipartEntity支持设置subType为"related"。
		// 使用params.setBodyEntity(httpEntity)可设置更多类型的HttpEntity（如：
		// MultipartEntity,BodyParamsEntity,FileUploadEntity,InputStreamUploadEntity,StringEntity）。
		// 例如发送json参数：params.setBodyEntity(new StringEntity(jsonStr,charset));
	
		

		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST,
				Url.url+":8080/Alisi2/AddReservationServlet",
		    params,
		    new RequestCallBack<String>() {

		      

		        @Override
		        public void onSuccess(ResponseInfo<String> responseInfo) {
		           Toast.makeText(PaySucessActivity.this,"订阅成功...",Toast.LENGTH_SHORT).show();
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        	Toast.makeText(PaySucessActivity.this,"订阅失败...",Toast.LENGTH_SHORT).show();
		        }
		});
		return_home=(TextView) findViewById(R.id.tv_returnhome);
		return_home.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(PaySucessActivity.this,HomeActivity.class);
				startActivity(intent);
				
			}
		});
		continueshop=(TextView) findViewById(R.id.tv_continuebuy);
		continueshop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent2=new Intent(PaySucessActivity.this,ShopActivity.class);
				startActivity(intent2);
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
	       TextView tv_title=(TextView) actionBar.getCustomView().findViewById(R.id.title);
	         tv_title.setText("支付成功");
	       ImageButton imageBtn = (ImageButton) actionBar.getCustomView().findViewById(R.id.image_btn);  
	         
	       imageBtn.setOnClickListener(new View.OnClickListener() {  
	             
	           @Override  
	           public void onClick(View v) {  
	              finish();
	           
	           }  
	       });  
	}

}
