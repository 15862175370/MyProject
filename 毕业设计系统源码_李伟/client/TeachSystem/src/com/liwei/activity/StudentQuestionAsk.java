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
import com.liwei.adapter.StudentQuestionAskAdapter;
import com.liwei.application.MyApplation;
import com.liwei.model.bean.ChooseCourseEnty;
import com.liwei.model.bean.StudentAskQuestion;
import com.liwei.teachsystem.R;
import com.liwei.utils.UrlUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;

public class StudentQuestionAsk  extends Activity{
	private ListView lView;
	private String tno;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		MyApplation.setActivity(this);
		setContentView(R.layout.student_ask_question);
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
		lView=(ListView) findViewById(R.id.studentquestion);
		tno=getIntent().getStringExtra("tno");
		HttpUtils utils=new HttpUtils();
		RequestParams params=new RequestParams();
		params.addBodyParameter("tno",tno);
		System.out.println(tno);
		utils.send(HttpMethod.POST, UrlUtils.url+"/StudentAskQuestionServlet", params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
		
				
			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String list=responseInfo.result;
				Gson gson=new Gson();
				Type classOfT=new TypeToken<List<StudentAskQuestion>>(){}.getType();
				List<StudentAskQuestion> list1=gson.fromJson(list, classOfT);
				StudentQuestionAskAdapter adapter=new StudentQuestionAskAdapter(StudentQuestionAsk.this, list1,tno);
				lView.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				
				
			}
		});
		
	}

}
