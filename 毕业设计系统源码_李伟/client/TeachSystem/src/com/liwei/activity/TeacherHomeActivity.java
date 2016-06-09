package com.liwei.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.liwei.application.MyApplation;
import com.liwei.calendar.MyCalendar;
import com.liwei.teachsystem.R;

public class TeacherHomeActivity extends Activity {
	private LinearLayout studentquestion;
	private LinearLayout  sendNotify;
	private LinearLayout  courseRegistration;
	private LinearLayout  calendar;
	private LinearLayout  myMessage;
	private LinearLayout  myStudent;
	private LinearLayout mycourse;
	public String tno;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.teacherhome);
		MyApplation.setActivity(this);
		final SharedPreferences sPreferences=getSharedPreferences("teacheruser",Context.MODE_PRIVATE);
		tno=sPreferences.getString("tusername","");
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
		myStudent=(LinearLayout ) findViewById(R.id.mystudent);
		myStudent.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(TeacherHomeActivity.this,MyStudentActivity.class);
				intent.putExtra("tno", tno);
				startActivity(intent);
				
			}
		});
		myMessage=(LinearLayout ) findViewById(R.id.mymessage);
myMessage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(TeacherHomeActivity.this,StudentToTeacherMessage.class);
				intent.putExtra("tno", tno);
				startActivity(intent);
				
				
			}
		});
		calendar=(LinearLayout ) findViewById(R.id.rili);
		calendar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(TeacherHomeActivity.this,MyCalendar.class);
				startActivity(intent);
				
				
			}
		});
		studentquestion=(LinearLayout ) findViewById(R.id.studentquestion);
		studentquestion.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(TeacherHomeActivity.this,StudentQuestionAsk.class);
				intent.putExtra("tno", tno);
				startActivity(intent);
				
			
				
			}
		});
		courseRegistration=(LinearLayout ) findViewById(R.id.courseregistration);
		courseRegistration.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(TeacherHomeActivity.this,CourseRegist.class);
				intent.putExtra("tno", tno);
				startActivity(intent);
				
				
			}
		});
		sendNotify=(LinearLayout ) findViewById(R.id.sendnotify);
		sendNotify.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			Intent intent=new Intent(TeacherHomeActivity.this,SendMessageActivity.class);
			startActivity(intent);
				
			}
		});
		mycourse=(LinearLayout) findViewById(R.id.teachercourse);
		mycourse.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(TeacherHomeActivity.this,ViewTeacherCourse.class);
				intent.putExtra("tno", tno);
				startActivity(intent);
				
			}
		});
		
	}

}
