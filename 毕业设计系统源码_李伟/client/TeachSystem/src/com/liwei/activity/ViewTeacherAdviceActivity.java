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
import com.liwei.adapter.ViewTeacherAdviceAdapter;
import com.liwei.application.MyApplation;
import com.liwei.model.bean.QuestionAndAnswer;
import com.liwei.model.bean.ViewTeacherAdviceBean;
import com.liwei.teachsystem.R;
import com.liwei.utils.UrlUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class ViewTeacherAdviceActivity  extends Activity{
	private String sno;
	private ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.teacher_advice_list);
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
		 lv=(ListView) findViewById(R.id.teacheradvice);
		 sno=getIntent().getStringExtra("sno");
		 HttpUtils utils=new HttpUtils();
		 RequestParams params=new RequestParams();
		 params.addBodyParameter("sno",sno);
		 utils.send(HttpMethod.POST, UrlUtils.url+"/ViewTeacherAdviceServlet", params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
			
				Toast.makeText(ViewTeacherAdviceActivity.this, "失败", Toast.LENGTH_LONG).show();
			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String list=responseInfo.result;
				Gson gson=new Gson();
				Type classOfT=new TypeToken<List<ViewTeacherAdviceBean>>(){}.getType();
				List<ViewTeacherAdviceBean> list1=gson.fromJson(list, classOfT);
				ViewTeacherAdviceAdapter adapter=new ViewTeacherAdviceAdapter(ViewTeacherAdviceActivity.this, list1);
				lv.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				Toast.makeText(ViewTeacherAdviceActivity.this, "成功", Toast.LENGTH_LONG).show();
			}
		});
		 
		 
	 }
}
