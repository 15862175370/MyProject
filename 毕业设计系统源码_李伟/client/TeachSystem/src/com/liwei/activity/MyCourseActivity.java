package com.liwei.activity;

import java.lang.reflect.Type;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.liwei.adapter.MyCourseAdapter;
import com.liwei.application.MyApplation;
import com.liwei.model.bean.ChooseCourseEnty;
import com.liwei.teachsystem.R;
import com.liwei.utils.ToastUtils;
import com.liwei.utils.UrlUtils;

public class MyCourseActivity  extends Activity{
	private ListView lv;
	private String sno;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mycourse);
		MyApplation.setActivity(this);
		sno=getIntent().getStringExtra("sno");
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
		lv=(ListView) findViewById(R.id.mycourse);
		HttpUtils utils=new HttpUtils();
		RequestParams params=new RequestParams();
		params.addBodyParameter("sno",sno);
		utils.send(HttpMethod.POST, UrlUtils.url+"/GetChooseCourseServlet", params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				ToastUtils.getToast(MyCourseActivity.this, "请求网络数据失败");
				
			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String list=responseInfo.result;
				Gson gson=new Gson();
				Type classOfT=new TypeToken<List<ChooseCourseEnty>>(){}.getType();
				List<ChooseCourseEnty> list1=gson.fromJson(list, classOfT);
				MyCourseAdapter adapter=new MyCourseAdapter(MyCourseActivity.this, list1, sno);
				lv.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				
				
			}
		});
	}

}
