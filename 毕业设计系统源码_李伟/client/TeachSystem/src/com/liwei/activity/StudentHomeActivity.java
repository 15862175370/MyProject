package com.liwei.activity;

import com.liwei.application.MyApplation;
import com.liwei.teachsystem.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class StudentHomeActivity extends Activity{
	private LinearLayout selfTest;
	private  LinearLayout chooseCourse;
	private String username;
	private  LinearLayout seecourse;
	private  LinearLayout viewScore;
	private  LinearLayout questionBack;
	private  LinearLayout myteacher;
	private  LinearLayout calendar;
	private  LinearLayout advise;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.studenthome);
		MyApplation.setActivity(this);
		 SharedPreferences sPreferences=getSharedPreferences("studentuser",Context.MODE_PRIVATE);
		 username=sPreferences.getString("susername","");
		initView();
		
		
	}
	/**
	 * 选择课程
	 */
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
		advise=( LinearLayout) findViewById(R.id.advise);
		advise.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(StudentHomeActivity.this,ViewTeacherAdviceActivity.class);
				intent.putExtra("sno", username);
				startActivity(intent);
				
			}
		});
		calendar=( LinearLayout) findViewById(R.id.calendar);
		calendar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(StudentHomeActivity.this,CalendarActivity.class);
				startActivity(intent);
				
			}
		});
		myteacher=( LinearLayout) findViewById(R.id.myteacher);
		myteacher.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(StudentHomeActivity.this,MyTeacherActivity.class);
				intent.putExtra("sno", username);
				startActivity(intent);
				
			}
		});
		questionBack=( LinearLayout) findViewById(R.id.chooseCourse1);
		questionBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(StudentHomeActivity.this,QuestionBackActivity.class);
				intent.putExtra("sno", username);
				startActivity(intent);
				
			}
		});
		viewScore =( LinearLayout) findViewById(R.id.my_score);
		viewScore.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
		Intent intent=new Intent(StudentHomeActivity.this,ViewScore.class);
		intent.putExtra("sno", username);
		startActivity(intent);
				
			}
		});
		seecourse=( LinearLayout) findViewById(R.id.seecourse);
		seecourse.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(StudentHomeActivity.this,MyCourseActivity.class);
				intent.putExtra("sno", username);
				startActivity(intent);
				
			}
		});
		chooseCourse=( LinearLayout) findViewById(R.id.chooseCourse);
		chooseCourse.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(StudentHomeActivity.this,ChooseCourse.class);
				intent.putExtra("sno", username);
				startActivity(intent);
			
				
			}
		});
		 ImageView closeImageView=(ImageView) findViewById(R.id.bjmgf_sdk_closeAboutBtnId);
		 closeImageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			finish();
				
			}
		});
		 /**
		  * 自测题
		  */
		selfTest=( LinearLayout) findViewById(R.id.selftest);
		selfTest.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(StudentHomeActivity.this,SelfTestActivity.class);
				startActivity(intent);
				
			}
		});
	}

}
