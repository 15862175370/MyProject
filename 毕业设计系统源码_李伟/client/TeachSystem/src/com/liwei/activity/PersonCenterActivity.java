package com.liwei.activity;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.liwei.application.MyApplation;
import com.liwei.model.bean.CircleImageView;
import com.liwei.model.bean.NiChengAndIcon;
import com.liwei.teachsystem.MainActivity;
import com.liwei.teachsystem.R;
import com.liwei.utils.UrlUtils;

public class PersonCenterActivity  extends Activity{
	private CircleImageView imageView;
	private TextView nicheng;
	private String sno="";
	private String tno="";
	private String age;
	 private  SharedPreferences sPreferences;
	    private   SharedPreferences sharedPreferences;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.personcenter);
		MyApplation.setActivity(this);
		 sPreferences=getSharedPreferences("teacheruser",Context.MODE_PRIVATE);
		 sharedPreferences=getSharedPreferences("studentuser",Context.MODE_PRIVATE);
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
		imageView=(CircleImageView) findViewById(R.id.icon);
		nicheng=(TextView) findViewById(R.id.nicheng);
		age=getIntent().getStringExtra("age");
		if(age.equals("2")){
		sno=getIntent().getStringExtra("sno");
		}
		if(age.equals("1")){
		tno=getIntent().getStringExtra("tno");
		}
		
		
		HttpUtils utils=new HttpUtils();
		RequestParams params=new RequestParams();
		if(!sno.equals("")){
			params.addBodyParameter("suserName",sno);
		}else if(!tno.equals("")){
			params.addBodyParameter("tuserName",tno);
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
		
		
		
		
		
		
		
		
		
		/**
		 * 用户修改密码操作
		 */
	
		LinearLayout changepassLayout=(LinearLayout) findViewById(R.id.updatepassword);
		changepassLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(PersonCenterActivity.this,ChangePassWordActivity.class);
				startActivity(intent);
			
				
			}
		});
		/**
		 * 用户退出系统操作
		 * 
		 */
		LinearLayout logout=(LinearLayout) findViewById(R.id.logoutaccount);
		logout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				/**
				 * 清除教师登录信息
				 */
				new AlertDialog.Builder(PersonCenterActivity.this)
				.setTitle("退出")
				.setMessage("您确定要退出吗？")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						System.out.println(sPreferences.getString("tusername", ""));
						Editor editor=sPreferences.edit();
						editor.clear();
						editor.commit();
						/**
						 * 清除学生登录信息
						 */
						
						
						Editor editor1=sharedPreferences.edit();
						editor1.clear();
						editor1.commit();
				List<Activity>list=MyApplation.getActivity();
				for(Activity activity:list){
					activity.finish();
				}
						
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						
					}
				})
			.create().show();
				
				
			}
		});
		
		/**
		 * 用户切换账号操作
		 */
		LinearLayout exit=(LinearLayout)findViewById(R.id.exitaccount);
		exit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new AlertDialog.Builder(PersonCenterActivity.this)
				.setTitle("注销账号")
				.setMessage("你确定要注销账号吗")
				.setPositiveButton("确定",new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						/**
						 * 清除教师登录信息
						 */
						SharedPreferences sPreferences=PersonCenterActivity.this.getSharedPreferences("teacheruser",Context.MODE_PRIVATE);
						Editor editor=sPreferences.edit();
						editor.clear();
						editor.commit();
						/**
						 * 清除学生登录信息
						 */
						
						SharedPreferences sharedPreferences=PersonCenterActivity.this.getSharedPreferences("studentuser",Context.MODE_PRIVATE);
						Editor editor1=sharedPreferences.edit();
						editor1.clear();
						editor1.commit();
					Intent intent=new Intent(PersonCenterActivity.this,MainActivity.class);
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
	}
	public void set(NiChengAndIcon niChengAndIcon){
		nicheng.setText(niChengAndIcon.getUserName());
		BitmapUtils bitmapUtils=new BitmapUtils(PersonCenterActivity.this);
		if(niChengAndIcon.getIcon()!=null&&!niChengAndIcon.getIcon().equals("")){
		bitmapUtils.display(imageView,niChengAndIcon.getIcon());
		}
	}
}
