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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import bean.FixBarber;
import bean.OrderShop;
import bean.ServcePrice;
import bean.Url;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

public class ConfirmOrderActivity extends Activity {
	private ImageView s_icon;
	private TextView s_name;
	private TextView s_adress;
	private TextView ocount;
	private TextView oproject;
	private TextView otime;
	private TextView phoneNumber;
	private TextView submit;
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirm_order);
		
	FixBarber barber=(FixBarber)getIntent().getSerializableExtra("bar");
	OrderShop   shop=	(OrderShop)  getIntent().getSerializableExtra("shop");
	ServcePrice sPrice=(ServcePrice) getIntent().getSerializableExtra("price");
	 //获得预约的时间段！！！！
	String time=getIntent().getStringExtra("time");
	 s_icon=(ImageView)findViewById(R.id.iv_barbershopphoto);
	  s_name=(TextView)findViewById(R.id.tv_barshopname);
	  ocount=(TextView)findViewById(R.id.TextView01);//订单合计
	  s_adress=(TextView)findViewById(R.id.tv_barshopaddress);
	  oproject=(TextView)findViewById(R.id.TextView03);//订单项目
	 otime=(TextView) findViewById(R.id.tv_time);//预约时间
	 otime.setText("11月2日"+time);
	 phoneNumber=(TextView) findViewById(R.id.tv_phonemun);
	 submit=(TextView) findViewById(R.id.tv_textsubmit);//提交
	 BitmapUtils bUtils=new BitmapUtils(ConfirmOrderActivity.this);
	 bUtils.display(s_icon, shop.getS_icon());
	 s_name.setText(shop.getS_name());
	 ocount.setText(String.valueOf(sPrice.getPrice()));
	  s_adress.setText(barber.getS_addr());
	 oproject.setText(sPrice.getType());
	 showAcitonBar();
	    SharedPreferences sp = getSharedPreferences("userInfo",MODE_PRIVATE);
	   
	    System.out.println("用户现在拥有的钱数是："+sp.getInt("money",20));
	 phoneNumber.setText(sp.getString("phone","110"));
	  submit.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Toast.makeText(ConfirmOrderActivity.this, "提交成功",Toast.LENGTH_LONG).show();
     	  Intent intent=new Intent(ConfirmOrderActivity.this,PayActivity.class);

    		FixBarber barber=(FixBarber)getIntent().getSerializableExtra("bar");
    		OrderShop   shop=	(OrderShop)  getIntent().getSerializableExtra("shop");
    		ServcePrice sPrice=(ServcePrice) getIntent().getSerializableExtra("price");
    		Bundle bundle=new Bundle();
			bundle.putSerializable("bar", barber);
			bundle.putSerializable("shop",shop);
		    bundle.putSerializable("price", sPrice);
			intent.putExtras(bundle);
			
			//INSERT into t_order(u_id,b_id,time1,servid,state,day_id)VALUES(2,3,1,1,1,1)
			RequestParams params = new RequestParams();
			params.addHeader("name", "value");
			params.addQueryStringParameter("name", "value");

			// 只包含字符串参数时默认使用BodyParamsEntity，
			// 类似于UrlEncodedFormEntity（"application/x-www-form-urlencoded"）。
			params.addBodyParameter("u_id",String.valueOf(getSharedPreferences("userInfo",MODE_PRIVATE).getInt("u_id",0)));
             params.addBodyParameter("b_id",String.valueOf(barber.getB_id()));
             params.addBodyParameter("time1","1");
             params.addBodyParameter("servid","1");
             params.addBodyParameter("state","0");
             params.addBodyParameter("day_id","1");
			// 加入文件参数后默认使用MultipartEntity（"multipart/form-data"），
			// 如需"multipart/related"，xUtils中提供的MultipartEntity支持设置subType为"related"。
			// 使用params.setBodyEntity(httpEntity)可设置更多类型的HttpEntity（如：
			// MultipartEntity,BodyParamsEntity,FileUploadEntity,InputStreamUploadEntity,StringEntity）。
			// 例如发送json参数：params.setBodyEntity(new StringEntity(jsonStr,charset));
		
			

			HttpUtils http = new HttpUtils();
			http.send(HttpRequest.HttpMethod.POST,
					Url.url+":8080/Alisi2/AddOrderServlet",
			    params,
			    new RequestCallBack<String>() {



			        @Override
			        public void onSuccess(ResponseInfo<String> responseInfo) {
			          System.out.println("订单的编号是："+responseInfo.result);
			          SharedPreferences spOrder = getSharedPreferences("orderId",MODE_PRIVATE);
			            SharedPreferences.Editor editor=spOrder.edit();
			            editor.putInt("o_id",Integer.parseInt(responseInfo.result));
			            editor.commit();
			          
			          
			        }

			        @Override
			        public void onFailure(HttpException error, String msg) {
			           System.out.println("订单获取失败。。。。");
			        }
			});
			
			
			
			
			
			startActivity(intent);
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
	         tv_title.setText("订单详情");
	       ImageButton imageBtn = (ImageButton) actionBar.getCustomView().findViewById(R.id.image_btn);  
	         
	       imageBtn.setOnClickListener(new View.OnClickListener() {  
	             
	           @Override  
	           public void onClick(View v) {  
	              finish();
	           
	           }  
	       });  
	}
}
