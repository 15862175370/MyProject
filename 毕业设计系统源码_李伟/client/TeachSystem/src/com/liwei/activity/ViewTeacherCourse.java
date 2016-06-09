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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.liwei.adapter.ViewTeacherCourseAdapter;
import com.liwei.application.MyApplation;
import com.liwei.model.bean.ViewTeacherCourseEnty;
import com.liwei.teachsystem.R;
import com.liwei.utils.ToastUtils;
import com.liwei.utils.UrlUtils;

public class ViewTeacherCourse extends Activity {
	private ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.teachercourse);
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
		final String tno=getIntent().getStringExtra("tno");
		ToastUtils.getToast(ViewTeacherCourse.this, tno);
		lv=(ListView) findViewById(R.id.teachercourse);
		HttpUtils utils=new HttpUtils();
		RequestParams params=new RequestParams();
		params.addBodyParameter("tno",tno);
		utils.send(HttpMethod.POST, UrlUtils.url+"/ViewTeacherCourseServlet",params ,new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				ToastUtils.getToast(ViewTeacherCourse.this, "请求网络数据失败");
				
			}

			@Override
			public void onSuccess(ResponseInfo<String> rs) {
			String list=rs.result;
			Gson gson=new Gson();
			Type classOfT=new TypeToken<List<ViewTeacherCourseEnty>>(){}.getType();
			List<ViewTeacherCourseEnty> list1=gson.fromJson(list, classOfT);
			ViewTeacherCourseAdapter adapter=new ViewTeacherCourseAdapter(ViewTeacherCourse.this, tno, list1);
			lv.setAdapter(adapter);
			adapter.notifyDataSetChanged();
			}
		});
		
	}

}
