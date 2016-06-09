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
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SubmitQuestion extends Activity{
	private String sno;
	private int cno;
	private Button send;
	private EditText sendcontent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.submit_question);
		MyApplation.setActivity(this);
		sno=getIntent().getStringExtra("sno");
		cno=getIntent().getIntExtra("cno", 0);
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
		sendcontent=(EditText) findViewById(R.id.submitquestion);
		send=(Button) findViewById(R.id.sendquestion);
		send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(sendcontent.getText().toString().equals("")){
					Toast.makeText(SubmitQuestion.this, "不能发送空内容", Toast.LENGTH_LONG).show();
					
				}else{
					HttpUtils utils=new HttpUtils();
					RequestParams params=new RequestParams();
					params.addBodyParameter("sno",sno);
					params.addBodyParameter("cno",String.valueOf(cno));
					params.addBodyParameter("question",sendcontent.getText().toString());
					
					utils.send(HttpMethod.POST, UrlUtils.url+"/SubmitQuestion", params, new RequestCallBack<String>() {

						@Override
						public void onFailure(HttpException arg0, String arg1) {
						
							Toast.makeText(SubmitQuestion.this, "请求网络数据失败", Toast.LENGTH_LONG).show();
							
						}

						@Override
						public void onSuccess(ResponseInfo<String> responseInfo) {
							String flag=responseInfo.result;
							if(flag.equals("true")){
								Toast.makeText(SubmitQuestion.this, "提交成功", Toast.LENGTH_LONG).show();
								finish();
							}else{
								Toast.makeText(SubmitQuestion.this, "提交失败", Toast.LENGTH_LONG).show();
							}
							
						}
					});
					
				}
				
			}
		});
	}

}
