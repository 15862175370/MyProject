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
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;

public class TeacherProvideAdviceActivity extends Activity {
	private String sno;
	private String tno;
	private EditText content;
	private ImageView send;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.teacherleader);
		MyApplation.setActivity(this);
		sno=getIntent().getStringExtra("sno");
		tno=getIntent().getStringExtra("tno");
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
		content=(EditText) findViewById(R.id.et_comment);
		send=(ImageView) findViewById(R.id.comment_fb);
		send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(content.getText().toString().equals("")){
					ToastUtils.getToast(TeacherProvideAdviceActivity.this, "不能发送空内容");
				}else{
					HttpUtils utils=new HttpUtils();
					RequestParams params=new RequestParams();
					params.addBodyParameter("tno",tno);
					params.addBodyParameter("sno",sno);
					params.addBodyParameter("content",content.getText().toString());
					utils.send(HttpMethod.POST, UrlUtils.url+"/TeacherSendAdviceToStudentServlet", params, new RequestCallBack<String>() {

						@Override
						public void onFailure(HttpException arg0, String arg1) {
						
							ToastUtils.getToast(TeacherProvideAdviceActivity.this, "请求网络数据失败");
						}

						@Override
						public void onSuccess(ResponseInfo<String> responseInfo) {
							String flag=responseInfo.result;
							if(flag.equals("true")){
								ToastUtils.getToast(TeacherProvideAdviceActivity.this, "请求网络数据成功");
								finish();
							}else{
								ToastUtils.getToast(TeacherProvideAdviceActivity.this, "请求网络数据失败");
							}
					
							
						}
					});
					
				}
				
			}
		});
		
	}
	

}
