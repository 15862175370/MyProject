package com.liwei.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.liwei.application.MyApplation;
import com.liwei.teachsystem.R;
import com.liwei.utils.ToastUtils;
import com.liwei.utils.UrlUtils;

public class ChangePassWordActivity extends Activity{
	  private SharedPreferences sPreferences;
	    private SharedPreferences sharedPreferences;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.chagepassword);
		ImageView close=(ImageView)findViewById(R.id.bjmgf_sdk_closeAboutBtnId);
		/**
		 * 返回按钮
		 */
	MyApplation.setActivity(this);
		close.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			ChangePassWordActivity.this.finish();
				
			}
		});
		final EditText usernameEditText=(EditText) findViewById(R.id.firstEditText);
		final EditText pass=(EditText) findViewById(R.id.secondEditText);
		             Button chagepassButton= (Button) findViewById(R.id.confremButton);

		
		 sPreferences=getSharedPreferences("teacheruser",Context.MODE_PRIVATE);
		 sharedPreferences=getSharedPreferences("studentuser",Context.MODE_PRIVATE);
		final  String username=sPreferences.getString("tusername", "");
			final String susername=sharedPreferences.getString("susername", "");
			if(!username.equals("")){
				usernameEditText.setText(username);
				
				
				
			}else if(!susername.equals("")){
				usernameEditText.setText(susername);
			}
			
			
			chagepassButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(pass.getText().toString().equals("")){
						ToastUtils.getToast(ChangePassWordActivity.this,"请输入新密码");
						return;
					}
					//如果学生用户名不为空请求服务器修改密码
					if(!susername.equals("")){
						HttpUtils utils=new HttpUtils();
						RequestParams params=new RequestParams();
						params.addBodyParameter("studentuserName",susername);
						params.addBodyParameter("passWord",pass.getText().toString());
						utils.send(HttpMethod.POST, UrlUtils.url+"/ChangePassWordServlet",params, new RequestCallBack<String>() {

							@Override
							public void onFailure(HttpException arg0,
									String arg1) {
								
								
							}

							@Override
							public void onSuccess(ResponseInfo<String> responseInfo) {
								String flag=responseInfo.result;
								if(flag.equals("true")){
									ToastUtils.getToast(ChangePassWordActivity.this,"修改密码成功");
									finish();
								}else{
									ToastUtils.getToast(ChangePassWordActivity.this,"修改密码失败");
								}
								
								
							}
						});
					}
						
						//如果教师用户名不为空请求服务器修改密码
					if(!username.equals("")){
						HttpUtils utils=new HttpUtils();
						RequestParams params=new RequestParams();
						params.addBodyParameter("userName",username);
						params.addBodyParameter("passWord",pass.getText().toString());
						utils.send(HttpMethod.POST, UrlUtils.url+"/ChangePassWordServlet",params, new RequestCallBack<String>() {

							@Override
							public void onFailure(HttpException arg0,
									String arg1) {
								
								
							}

							@Override
							public void onSuccess(ResponseInfo<String> responseInfo) {
								String flag=responseInfo.result;
								if(flag.equals("true")){
									ToastUtils.getToast(ChangePassWordActivity.this,"修改密码成功");
									finish();
								}else{
									ToastUtils.getToast(ChangePassWordActivity.this,"修改密码失败");
								}
								
								
							}
						});
						
					}
					
					
					
				}
			});
			
			
	}

}
