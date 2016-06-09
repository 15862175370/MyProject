package com.liwei.fragment;



import java.lang.reflect.Type;
import java.util.List;





















import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.liwei.activity.AdministratorLoginActivity;
import com.liwei.activity.StudentHomeActivity;
import com.liwei.activity.StudentLogin;
import com.liwei.activity.TeacherHomeActivity;
import com.liwei.activity.TeacherLoginActivity;
import com.liwei.activity.WebViewActivity;
import com.liwei.adapter.TeacherNewsAdapter;
import com.liwei.model.bean.TeachNews;
import com.liwei.model.bean.TeachNotify;
import com.liwei.teachsystem.R;












import com.liwei.utils.UrlUtils;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class MainFragment extends Fragment{
	private View view;
	private List<TeachNews> list;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if(view==null){
	 view=inflater.inflate(R.layout.mainfragment,container, false);
		}
	 initView();
	
		return view;
	}
	public void initView(){
		
		final SharedPreferences tPreferences=getActivity().getSharedPreferences("teacheruser",Context.MODE_PRIVATE);
		
		final SharedPreferences sPreferences=getActivity().getSharedPreferences("studentuser",Context.MODE_PRIVATE);
		LinearLayout administration=(LinearLayout) view.findViewById(R.id.administration);
		administration.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(getActivity(),AdministratorLoginActivity.class);
				startActivity(intent);
				
			}
		});
		
		LinearLayout studentlogin=(LinearLayout)view.findViewById(R.id.studentlogin);
		studentlogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String susername=sPreferences.getString("susername","");
				if(!susername.equals("")){
					Intent intent = new Intent(getActivity(),StudentHomeActivity.class);
					startActivity(intent);
					
				}else{
				Intent intent = new Intent(getActivity(),StudentLogin.class);
				startActivity(intent);
				}
				
			}
		});
		LinearLayout teacherlogin=(LinearLayout)view.findViewById(R.id.teacherlogin);
		teacherlogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String tusername=tPreferences.getString("tusername","");
				if(!tusername.equals("")){
					Intent intent = new Intent(getActivity(),TeacherHomeActivity.class);
					startActivity(intent);
				}else{
				Intent intent = new Intent(getActivity(),TeacherLoginActivity.class);
				startActivity(intent);
				}
				
			}
		});
		LinearLayout baishitong=(LinearLayout)view.findViewById(R.id.baishitong);
		final ListView lView=(ListView)view.findViewById(R.id.lv);
		HttpUtils utils=new HttpUtils();
		
		utils.send(HttpMethod.POST, UrlUtils.url+"/GetTeachNotify", null, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				Toast.makeText(getActivity(), "请求网络数据失败", Toast.LENGTH_LONG).show();
				
			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String json=responseInfo.result;
				Gson g=new Gson();
				Type classOfT=new TypeToken<List<TeachNotify>>(){}.getType();
				List<TeachNotify> list1=g.fromJson(json, classOfT);
				//此处写逻辑将服务器传过来的教务通知放入List列表中
				TeacherNewsAdapter adapter=new TeacherNewsAdapter(getActivity(), list1);
				lView.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				
				
			}
			
		});
		
		/**
		 * 点击进入Web浏览界面
		 */
		baishitong.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			Intent intent=new Intent(getActivity(),WebViewActivity.class);
		startActivity(intent);
				
			}
		});
	}

}
