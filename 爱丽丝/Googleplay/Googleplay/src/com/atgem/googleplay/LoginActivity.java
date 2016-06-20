package com.atgem.googleplay;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import bean.FixBarber;
import bean.OrderShop;
import bean.ServcePrice;
import bean.Url;
import bean.User;

import com.atgem.ailisidemo.activity.OrderMenu;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

public class LoginActivity extends Activity implements OnClickListener {

     
	private ImageButton reqButton,loginButton;
	NotificationManager nm;
	SharedPreferences sp;
	SharedPreferences.Editor edit;
	int pass=-1;
	String phone;
	static int  i=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		getActionBar().hide();
		reqButton=(ImageButton) findViewById(R.id.request);
		loginButton=(ImageButton) findViewById(R.id.login);
		
		nm=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	   reqButton.setOnClickListener(this);
	   loginButton.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.request:
		{ int i=(int) (Math.random()*1000+10000);
				sp=getPreferences(MODE_PRIVATE);
				sp.edit().putInt("random", i).commit();
				
				pass=sp.getInt("random", 0);
				Toast.makeText(LoginActivity.this,"密码是:"+pass, 0).show(); 
				
				Notification notify=new Notification.Builder(LoginActivity.this)
				          .setAutoCancel(true)
				          .setTicker("有新消息")
				          .setSmallIcon(R.drawable.ic_launcher)
				          .setContentTitle("一条新通知")
				          .setContentText("你的密码是:"+pass)
				          .build();
				nm.notify(0, notify);
			
			break;
			}
		case R.id.login:
			if(pass==-1){
				Toast.makeText(this, "你还没有获取注册码哦，亲...", 0).show();
			}else{
				EditText et_pass=(EditText) findViewById(R.id.pass);
				EditText et_num=(EditText)findViewById(R.id.user);
				
				 phone=et_num.getText().toString();
				//isMobileNo(phone);
				
			    String password=et_pass.getText().toString();
			    if(!isMobileNo(phone))
				{
					Toast.makeText(this, "手机号码不正确",0).show();
					break;
				}else{
					if(TextUtils.isEmpty(phone)){
						Toast.makeText(this, "号码不能为空",0).show();
					}else{
			       if(pass==Integer.parseInt(password)){
			    	   HttpUtils http = new HttpUtils();
			    	   http.send(HttpRequest.HttpMethod.GET,
			    			   Url.url+":8080/Alisi2/AndroidLoginServlet?phone="+phone,
			    	       new RequestCallBack<String>(){
			    	         

			    	           @Override
			    	           public void onSuccess(ResponseInfo<String> responseInfo) {
			    	             //  textView.setText(responseInfo.result);
			    	        	   String userInfo=responseInfo.result;
			    	        	   Gson gson=new Gson();
			    	        	    User fromJson = gson.fromJson(userInfo, User.class);
			    	        	 
			    	        	   int u_id=fromJson.getU_id();
								System.out.println("用户的id是："+u_id);
								int sex=fromJson.getSex();
								int money=fromJson.getMoney();
								SharedPreferences spUser = getSharedPreferences("userInfo",MODE_PRIVATE);
								    edit= spUser.edit();
								    edit.putInt("sex",sex);
								     edit.putInt("money",money);
								     edit.putInt("u_id",u_id);
								     edit.putString("phone",phone);
								        edit.commit();
        	                   System.out.println("用户的电话号码是："+spUser.getString("phone", "110"));
			    	        	   
			    	        	   
			    	        	   
			    	        	/*   Intent intent=new Intent(LoginActivity.this,OrderMenu.class);
			    				  
			    	        		FixBarber barber=(FixBarber)getIntent().getSerializableExtra("bar");
			    	        		OrderShop   shop=	(OrderShop)  getIntent().getSerializableExtra("shop");
			    	        		ServcePrice sPrice=(ServcePrice) getIntent().getSerializableExtra("price");
			    	        		Bundle bundle=new Bundle();
			    					bundle.putSerializable("bar", barber);
			    					bundle.putSerializable("shop",shop);
			    				    bundle.putSerializable("price", sPrice);
			    					intent.putExtras(bundle);*/
			    	        	//   System.out.println(sPrice.getPrice());
			    	        	   //从收藏界面跳转问题
			    	        	   Intent intent2=getIntent();
		
			    	        	   i=intent2.getIntExtra("age", 0);
			    	        	   System.out.println(i);
			    	        	   if(i==0){
			    	        			Intent data=new Intent(LoginActivity.this,BarberActivity.class);//创建意图
			    	        			System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaa");
			    	        			System.out.println("用户的id是：11111:"+u_id);
			    	        			data.putExtra("u_id", u_id);//设置数据
			    	        			setResult(11,data);
			    	        			finish();
			    	        		}else if(i==1){
			    	    	        	Intent intent3=new Intent(LoginActivity.this,OrderMenu.class);
			    	    	        	FixBarber barber=(FixBarber)getIntent().getSerializableExtra("bar");
				    	        		OrderShop   shop=	(OrderShop)  getIntent().getSerializableExtra("shop");
				    	        		ServcePrice sPrice=(ServcePrice) getIntent().getSerializableExtra("price");
				    	        		Bundle bundle=new Bundle();
				    					bundle.putSerializable("bar", barber);
				    					bundle.putSerializable("shop",shop);
				    				    bundle.putSerializable("price", sPrice);
				    					intent3.putExtras(bundle);
				    					SharedPreferences isFirstLogin = getSharedPreferences("isFirstLogin", MODE_PRIVATE);
			    	    	        	     SharedPreferences.Editor editorLogin=isFirstLogin.edit();
			    	    	        	     editorLogin.putBoolean("haslogin",true);
			    	    	        	     editorLogin.commit();
				    					 
				    					 
				    					 startActivity(intent3);
			    	    	}
			    	        	   
			    	        	   
			    	        	   
			    	        	
			    	          
			    	           
			    	           }

			    	           @Override
			    	           public void onStart() {
			    	           }

			    	           @Override
			    	           public void onFailure(HttpException error, String msg) {
			    	           
			    	           Toast.makeText(LoginActivity.this, "登录失败...", 0).show();
			    	           
			    	           }
			    	   });
			    	   
			    	   
			    	   
			    	   
				 
			     }else{
				   
				   Toast.makeText(this, "亲,输入的验证码不正确...", 0).show();
			   }
					}
				}
			}
			
			
		default:
			break;
		}
		
	}
	
	
	public  boolean isMobileNo(String mobiles){
		
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");  

		Matcher m = p.matcher(mobiles); 
		
		
		return m.matches();
		
	}
	

}
