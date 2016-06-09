package com.liwei.activity;

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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class AdministratorLoginActivity  extends Activity{
	private Button login;
	private EditText username;
	private EditText password;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.administrator_login);
		MyApplation.setActivity(this);
		initView();
	}
	public void  initView(){
		ImageView close=(ImageView)findViewById(R.id.bjmgf_sdk_closeAboutBtnId);
		/**
		 * 返回按钮
		 */
		close.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			finish();
				
			}
		});
		login=(Button) findViewById(R.id.confremButton);
		username=(EditText) findViewById(R.id.firstEditText);
		password=(EditText) findViewById(R.id.secondEditText);
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(username.getText().toString().equals("")||password.getText().toString().equals("")){
					ToastUtils.getToast(AdministratorLoginActivity.this, "用户名或密码不能为空");
				}else{
					HttpUtils utils=new HttpUtils();
				RequestParams params=new RequestParams();
				params.addBodyParameter("username",username.getText().toString().trim());
				params.addBodyParameter("password",password.getText().toString().trim());
					utils.send(HttpMethod.POST, UrlUtils.url+"/AdministratorLoginServlet", params, new RequestCallBack<String>() {

						@Override
						public void onFailure(HttpException arg0, String arg1) {
							ToastUtils.getToast(AdministratorLoginActivity.this, "请求网络数据失败");
							
						}

						@Override
						public void onSuccess(ResponseInfo<String> responseInfo) {
							 String flag=responseInfo.result;
							 if(flag.equals("true")){
								 ToastUtils.getToast(AdministratorLoginActivity.this, "登录成功");
								 Intent intent=new Intent(AdministratorLoginActivity.this,Administrator.class);
								  startActivity(intent);
							 }else{
								 ToastUtils.getToast(AdministratorLoginActivity.this, "登录失败");
							 }
							
						}
					});
				}
				
			}
		});
		
	}

}
