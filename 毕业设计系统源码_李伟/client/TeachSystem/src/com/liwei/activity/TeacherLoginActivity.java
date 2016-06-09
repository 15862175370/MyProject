package com.liwei.activity;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.liwei.application.MyApplation;
import com.liwei.teachsystem.R;
import com.liwei.utils.UrlUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class TeacherLoginActivity extends Activity {
	public Button loginButton;
	public String islogin;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.teacherlogin);
		MyApplation.setActivity(this);
		 initView();
		
	}
	public void  initView(){
		final SharedPreferences sPreferences=getSharedPreferences("teacheruser",Context.MODE_PRIVATE);
		ImageView close=(ImageView)findViewById(R.id.bjmgf_sdk_closeAboutBtnId);
		final EditText usernameEdit=(EditText)findViewById(R.id.firstEditText);
		final EditText password=(EditText)findViewById(R.id.secondEditText);
		 loginButton=(Button)findViewById(R.id.confremButton);
		/**
		 * 返回按钮
		 */
		close.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			TeacherLoginActivity.this.finish();
				
			}
		});
		
		loginButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//获得Preferences保存的对象
				String username=sPreferences.getString("tusername","");
				if(loginButton.getText().toString().equals("登录")){
					
					
					if(!usernameEdit.getText().toString().equals("")&&!password.getText().toString().equals("")){//当前用户不等于空才可以登录
						if(!username.equals(usernameEdit.getText().toString())){//如果当前用户名已存在不能再登录
						HttpUtils utils=new HttpUtils();
						RequestParams params=new RequestParams();
						params.addBodyParameter("username",usernameEdit.getText().toString());
						params.addBodyParameter("password", password.getText().toString());
						utils.send(HttpMethod.POST, UrlUtils.url+"/CheckAccount", params, new RequestCallBack<String>() {

							@Override
							public void onFailure(HttpException arg0,
									String arg1) {
								Toast.makeText(TeacherLoginActivity.this, "请求网络数据失败", Toast.LENGTH_LONG).show();
								
							}

							@Override
							public void onSuccess(ResponseInfo<String> responseInfo) {
								String flag=responseInfo.result;
								if(flag.equals("true")){
									
									Editor editor=sPreferences.edit();
									editor.putString("tusername",usernameEdit.getText().toString());
									editor.commit();
									Toast.makeText(TeacherLoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();
									Intent intent=new Intent(TeacherLoginActivity.this,TeacherHomeActivity.class);
									startActivity(intent);
									finish();
								}else{
									Toast.makeText(TeacherLoginActivity.this, "亲，请检查用户名或密码是否正确哦", Toast.LENGTH_LONG).show();
								}
								
							}
						});
						loginButton.setText("取消");
					}else{
						Toast.makeText(TeacherLoginActivity.this, "您已登录，不能重复登录", Toast.LENGTH_LONG).show();
				
					}
					}else{
						Toast.makeText(TeacherLoginActivity.this, "用户名或密码不能为空", Toast.LENGTH_LONG).show();
					}
				}else if(loginButton.getText().toString().equals("取消")){
					loginButton.setText("登录");
					usernameEdit.setText("");
					password.setText("");
					
				}
				
			}
		});
		
		
	}

}
