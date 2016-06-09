package com.liwei.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
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
import com.liwei.utils.UrlUtils;

public class ReplyStudentQuestion  extends Activity{
	private  String tno;
	private int sno;
	private int sq_id;
	private EditText content;
	private ImageView send;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.reply);
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
		tno=getIntent().getStringExtra("tno");
		sno=getIntent().getIntExtra("sno",0);
		sq_id=getIntent().getIntExtra("sq_id",0);
		content=(EditText) findViewById(R.id.et_comment);
		 send= (ImageView) findViewById(R.id.comment_fb);
		 send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(content.getText().toString().equals("")){
					Toast.makeText(ReplyStudentQuestion.this,"不能发送空内容", Toast.LENGTH_LONG).show();
				}else{
					HttpUtils utils=new HttpUtils();
		             RequestParams params=new RequestParams();
		             params.addBodyParameter("tno",tno);
		             params.addBodyParameter("sno",String.valueOf(sno));
		             params.addBodyParameter("sq_id",String.valueOf(sq_id));
		             params.addBodyParameter("content",content.getText().toString());
		            utils.send(HttpMethod.POST, UrlUtils.url+"/TeacherReplyStudentQuestionServlet", params, new RequestCallBack<String>() {

						@Override
						public void onFailure(HttpException arg0, String arg1) {
							
							Toast.makeText(ReplyStudentQuestion.this, "请求网络失败", Toast.LENGTH_LONG).show();
						}

						@Override
						public void onSuccess(ResponseInfo<String> responseInfo) {
							String flag=responseInfo.result;
							if(flag.equals("true")){
								Toast.makeText(ReplyStudentQuestion.this, "回复成功", Toast.LENGTH_LONG).show();
								Intent intent=new Intent(ReplyStudentQuestion.this,TeacherHomeActivity.class);
								startActivity(intent);
							}else{
								Toast.makeText(ReplyStudentQuestion.this, "回复失败", Toast.LENGTH_LONG).show();
							}
							
						}
					});
				}
				
			}
		});
		
	}

}
