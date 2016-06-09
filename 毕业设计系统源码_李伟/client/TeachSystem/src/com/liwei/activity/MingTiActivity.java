package com.liwei.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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


public class MingTiActivity extends Activity{
	private int cno;
	private String tno;
	private EditText question;
	private EditText question_a;
	private EditText question_b;
	private EditText question_zhengque;
	private Button ok;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mingti);
		MyApplation.setActivity(this);
		cno=getIntent().getIntExtra("cno", 0);
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
		question=(EditText) findViewById(R.id.question);
		question_a=(EditText) findViewById(R.id.question_a);
		question_b=(EditText) findViewById(R.id.question_b);
		question_zhengque=(EditText) findViewById(R.id.zhengque);
		ok=(Button) findViewById(R.id.ok);
		ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(question.getText().toString().equals("")||question_a.getText().toString().equals("")
						||question_b.getText().toString().equals("")||question_zhengque.getText().toString().equals("")){
					ToastUtils.getToast(MingTiActivity.this, "请确保所填内容都不为空");
					
				}else{
					HttpUtils utils=new HttpUtils();
					RequestParams params=new RequestParams();
					params.addBodyParameter("tno",tno);
					params.addBodyParameter("cno",String.valueOf(cno));
					params.addBodyParameter("question",question.getText().toString());
					params.addBodyParameter("question_a",question_a.getText().toString());
					params.addBodyParameter("question_b",question_b.getText().toString());
					params.addBodyParameter("question_zhengque",question_zhengque.getText().toString());
					utils.send(HttpMethod.POST, UrlUtils.url+"/MingTiServlet", params, new RequestCallBack<String>() {

						@Override
						public void onFailure(HttpException arg0, String arg1) {
						ToastUtils.getToast(MingTiActivity.this,"请求网络数据失败");
							
						}

						@Override
						public void onSuccess(ResponseInfo<String> responseInfo) {
							String flag=responseInfo.result;
							if(flag.equals("true")){
								ToastUtils.getToast(MingTiActivity.this,"命题成功");
								finish();
							}else{
								ToastUtils.getToast(MingTiActivity.this,"命题失败");
							}
							
						}
					});
					
				}
				
			}
		});
		
	}

}
