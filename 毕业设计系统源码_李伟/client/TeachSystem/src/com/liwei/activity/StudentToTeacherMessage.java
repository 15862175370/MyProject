package com.liwei.activity;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.liwei.adapter.StudentMessageAdapter;
import com.liwei.application.MyApplation;
import com.liwei.model.bean.ChooseCourseEnty;
import com.liwei.model.bean.StudentMessageBean;
import com.liwei.teachsystem.R;
import com.liwei.utils.UrlUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;

public class StudentToTeacherMessage extends Activity {
	private ListView lv;
	private String tno;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.to_teacher_message);
		MyApplation.setActivity(this);
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
		lv=(ListView) findViewById(R.id.studentmessage);
		HttpUtils utils=new HttpUtils();
		RequestParams params=new RequestParams();
		params.addBodyParameter("tno",tno);
		utils.send(HttpMethod.POST, UrlUtils.url+"/TeacherViewMyMessageServlet", params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
			
				
			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String list=responseInfo.result;
				Gson gson=new Gson();
				Type classOfT=new TypeToken<List<StudentMessageBean>>(){}.getType();
				List<StudentMessageBean> list1=gson.fromJson(list, classOfT);
				StudentMessageAdapter adapter=new StudentMessageAdapter(StudentToTeacherMessage.this, list1);
				lv.setAdapter(adapter);
				
			}
		});
		
		
		
	}

}
