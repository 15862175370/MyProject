 package com.liwei.activity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.liwei.application.MyApplation;
import com.liwei.teachsystem.MainActivity;
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
import android.widget.Toast;

public class SendMessageActivity  extends Activity{
	private EditText sendContent;
	private Button send;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.sendnotifymessage);
		MyApplation.setActivity(this);
		initView();
	}
	
	public void initView(){
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
		sendContent=(EditText) findViewById(R.id.sendcontent);
		
		send=(Button) findViewById(R.id.send);
		send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(sendContent.getText().toString().equals("")){
					Toast.makeText(SendMessageActivity.this, "不能发送空信息", Toast.LENGTH_LONG).show();
				}else{
					HttpUtils utils=new HttpUtils();
					RequestParams params=new RequestParams();
				
						params.addBodyParameter("content",sendContent.getText().toString());
					
					utils.send(HttpMethod.POST, UrlUtils.url+"/SendMessage", params, new RequestCallBack<String>() {

						@Override
						public void onFailure(HttpException arg0, String arg1) {
						
							Toast.makeText(SendMessageActivity.this, "发送失败", Toast.LENGTH_LONG).show();
						}

						@Override
						public void onSuccess(ResponseInfo<String> responseInfo) {
							if(responseInfo.result.equals("true")){
								ToastUtils.getToast(SendMessageActivity.this, "发送成功");
						Intent intent=new Intent(SendMessageActivity.this,MainActivity.class);
						startActivity(intent);
							}else{
								ToastUtils.getToast(SendMessageActivity.this, "发送失败");
							}
							
						}
					});
				}
				
			}
		});
		
	}

}
