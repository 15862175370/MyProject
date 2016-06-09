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

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class StudentSendMessageToTeacherActivity  extends Activity {
	private String sno;
	private int tno;
	private String sendcontent;
	private EditText et;
	private ImageView fb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.send_message);
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
		et=(EditText) findViewById(R.id.et_comment);
		fb=(ImageView) findViewById(R.id.comment_fb);
		sno=getIntent().getStringExtra("sno");
		tno=getIntent().getIntExtra("tno", 0);
		fb.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				if(et.getText().toString().equals("")){
					Toast.makeText(StudentSendMessageToTeacherActivity.this, "不能发送空内容", Toast.LENGTH_SHORT).show();
					
				}else{
					
					HttpUtils utils=new HttpUtils();
					RequestParams params=new RequestParams();
					Toast.makeText(StudentSendMessageToTeacherActivity.this, sno+"=="+tno, Toast.LENGTH_LONG).show();
					params.addBodyParameter("sno",String.valueOf(sno));
					params.addBodyParameter("tno",String.valueOf(tno));
					params.addBodyParameter("content",et.getText().toString());
					utils.send(HttpMethod.POST, UrlUtils.url+"/StudentSendMessageToTeacherServlet",params ,new RequestCallBack<String>() {

						@Override
						public void onFailure(HttpException arg0, String arg1) {
							Toast.makeText(StudentSendMessageToTeacherActivity.this, "请求网络数据失败", Toast.LENGTH_SHORT).show();
							
						}

						@Override
						public void onSuccess(ResponseInfo<String> responseInfo) {
					String flag=responseInfo.result;
					if(flag.equals("true")){
						Toast.makeText(StudentSendMessageToTeacherActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
						finish();
					}else{
						Toast.makeText(StudentSendMessageToTeacherActivity.this, "发送失败", Toast.LENGTH_SHORT).show();
					}
							
						}
					});
					
				}
				
				
			}
		});
		
		
	}

}
