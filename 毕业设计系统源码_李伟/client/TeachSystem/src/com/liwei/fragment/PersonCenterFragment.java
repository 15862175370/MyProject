package com.liwei.fragment;

import java.lang.reflect.Type;

import java.util.List;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.liwei.activity.ChangePassWordActivity;
import com.liwei.model.bean.CircleImageView;
import com.liwei.model.bean.MyTeacher;
import com.liwei.model.bean.NiChengAndIcon;
import com.liwei.teachsystem.MainActivity;
import com.liwei.teachsystem.R;
import com.liwei.utils.ToastUtils;
import com.liwei.utils.UrlUtils;

public class PersonCenterFragment extends Fragment {
	
	private CircleImageView imageView;
	private TextView nicheng;
	private View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if(view==null){
		 view=inflater.inflate(R.layout.personcenter,container, false);
		}
		imageView=(CircleImageView) view.findViewById(R.id.icon);
		 SharedPreferences sharedPreferences=getActivity().getSharedPreferences("studentuser",Context.MODE_PRIVATE);
				//ToastUtils.getToast(getActivity(),sharedPreferences.getString("susername", ""));
		nicheng=(TextView) view.findViewById(R.id.nicheng);
		/*HttpUtils utils=new HttpUtils();
		RequestParams params=new RequestParams();
		System.out.println(sharedPreferences.getString("susername", ""));
		System.out.println(sharedPreferences.getString("tusername", ""));
		if(!sharedPreferences.getString("susername", "").equals("")){
			params.addBodyParameter("suserName",String.valueOf(sharedPreferences.getString("susername", "")));
		}else if(!sharedPreferences.getString("tusername", "").equals("")){
			params.addBodyParameter("tuserName",String.valueOf(sharedPreferences.getString("tusername", "")));
		}
		utils.send(HttpMethod.POST, UrlUtils.url+"/GetNiChengAndIconServlet", params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
			
				
			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String list=responseInfo.result;
				Gson gson=new Gson();
				NiChengAndIcon niChengAndIcon=gson.fromJson(list, NiChengAndIcon.class);
				if(niChengAndIcon!=null){
				set(niChengAndIcon);
				}
			}
		});
		*/
		
		/**
		 * 用户修改密码操作
		 */
	
		LinearLayout changepassLayout=(LinearLayout) view.findViewById(R.id.updatepassword);
		changepassLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(getActivity(),ChangePassWordActivity.class);
				startActivity(intent);
			
				
			}
		});
		/**
		 * 用户注销操作
		 */
		LinearLayout exit=(LinearLayout)view.findViewById(R.id.exitaccount);
		exit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new AlertDialog.Builder(getActivity())
				.setTitle("注销")
				.setMessage("你确定要注销账号吗")
				.setPositiveButton("确定",new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						/**
						 * 清除教师登录信息
						 */
						SharedPreferences sPreferences=getActivity().getSharedPreferences("teacheruser",Context.MODE_PRIVATE);
						Editor editor=sPreferences.edit();
						editor.clear();
						editor.commit();
						/**
						 * 清除学生登录信息
						 */
						
						SharedPreferences sharedPreferences=getActivity().getSharedPreferences("studentuser",Context.MODE_PRIVATE);
						Editor editor1=sharedPreferences.edit();
						editor1.clear();
						editor1.commit();
					Intent intent=new Intent(getActivity(),MainActivity.class);
					startActivity(intent);
						
					}
				}
					
				
				)
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						
					}
				}).create().show();
				
			}
		});
		return view;
	
		 
}
	/*public void set(NiChengAndIcon niChengAndIcon){
		nicheng.setText(niChengAndIcon.getUserName());
		
	}*/
}
