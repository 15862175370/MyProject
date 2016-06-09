package com.liwei.activity;

import com.liwei.application.MyApplation;
import com.liwei.teachsystem.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class SelfTestActivity   extends Activity {
	private RadioGroup radiogroup1;
	private RadioGroup radiogroup2;
	private RadioGroup radiogroup3;
	private RadioGroup radiogroup4;
	private RadioGroup radiogroup5;
	private RadioButton radiobutton1;
	private RadioButton radiobutton2;
	private RadioButton radiobutton3;
	private RadioButton radiobutton4;
	private RadioButton radiobutton5;
	private RadioButton radiobutton6;
	private RadioButton radiobutton7;
	private RadioButton radiobutton8;
	private RadioButton radiobutton9;
	private RadioButton radiobutton10;
	private RadioButton radiobutton11;
	private RadioButton radiobutton12;
	private RadioButton radiobutton13;
	private RadioButton radiobutton14;
	private RadioButton radiobutton15;
	private RadioButton radiobutton16;
	private RadioButton radiobutton17;
	private RadioButton radiobutton18;
	private RadioButton radiobutton19;
	private RadioButton radiobutton20;
	
 @Override
protected void onCreate(Bundle savedInstanceState) {
	 
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.question_test);
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
	 radiogroup1=(RadioGroup) findViewById(R.id.radiogroup1);
	radiogroup2= (RadioGroup) findViewById(R.id.radiogroup2);
	 radiogroup3=(RadioGroup) findViewById(R.id.radiogroup3);
	 radiogroup4=(RadioGroup) findViewById(R.id.radiogroup4);
	 radiogroup5=(RadioGroup) findViewById(R.id.radiogroup5);
	 radiobutton1=(RadioButton) findViewById(R.id.radiobutton1);
	 radiobutton2=(RadioButton) findViewById(R.id.radiobutton2);
	 radiobutton3=(RadioButton) findViewById(R.id.radiobutton3);
	 radiobutton4=(RadioButton) findViewById(R.id.radiobutton4);
	 radiobutton5=(RadioButton) findViewById(R.id.radiobutton5);
	 radiobutton6=(RadioButton) findViewById(R.id.radiobutton6);
	 radiobutton7=(RadioButton) findViewById(R.id.radiobutton7);
	 radiobutton8=(RadioButton) findViewById(R.id.radiobutton8);
	 
	 radiobutton9=(RadioButton) findViewById(R.id.radiobutton9);
	 radiobutton10=(RadioButton) findViewById(R.id.radiobutton10);
	 radiobutton11=(RadioButton) findViewById(R.id.radiobutton11);
	 radiobutton12=(RadioButton) findViewById(R.id.radiobutton12);
	 radiobutton13=(RadioButton) findViewById(R.id.radiobutton13);
	 radiobutton14=(RadioButton) findViewById(R.id.radiobutton14);
	 
	 radiobutton15=(RadioButton) findViewById(R.id.radiobutton15);
	 radiobutton16=(RadioButton) findViewById(R.id.radiobutton16);
	 radiobutton17=(RadioButton) findViewById(R.id.radiobutton17);
	 radiobutton18=(RadioButton) findViewById(R.id.radiobutton18);
	 radiobutton19=(RadioButton) findViewById(R.id.radiobutton19);
	 
	 radiobutton20=(RadioButton) findViewById(R.id.radiobutton20);
	 
	 radiogroup1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			if(checkedId==radiobutton1.getId()){
				Toast.makeText(SelfTestActivity.this, "恭喜你答对了，掌握不错哦", Toast.LENGTH_LONG).show();
			}else{
				Toast.makeText(SelfTestActivity.this, "答错了，继续努力", Toast.LENGTH_LONG).show();	
			}
			
		}
	});
	 
	 radiogroup2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			if(checkedId==radiobutton7.getId()){
				Toast.makeText(SelfTestActivity.this, "恭喜你答对了，掌握不错哦", Toast.LENGTH_LONG).show();
			}else{
				Toast.makeText(SelfTestActivity.this, "答错了，继续努力", Toast.LENGTH_LONG).show();	
			}
			
		}
	});
	 
	 
	 radiogroup3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==radiobutton9.getId()){
					Toast.makeText(SelfTestActivity.this, "恭喜你答对了，掌握不错哦", Toast.LENGTH_LONG).show();
				}else{
					Toast.makeText(SelfTestActivity.this, "答错了，继续努力", Toast.LENGTH_LONG).show();	
				}
				
			}
		});
	 
	 radiogroup4.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==radiobutton16.getId()){
					Toast.makeText(SelfTestActivity.this, "恭喜你答对了，掌握不错哦", Toast.LENGTH_LONG).show();
				}else{
					Toast.makeText(SelfTestActivity.this, "答错了，继续努力", Toast.LENGTH_LONG).show();	
				}
				
			}
		});
	 
	 radiogroup5.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==radiobutton19.getId()){
					Toast.makeText(SelfTestActivity.this, "恭喜你答对了，掌握不错哦", Toast.LENGTH_LONG).show();
				}else{
					Toast.makeText(SelfTestActivity.this, "答错了，继续努力", Toast.LENGTH_LONG).show();	
				}
				
			}
		});
	 ImageView closeImageView=(ImageView) findViewById(R.id.bjmgf_sdk_closeAboutBtnId);
	 closeImageView.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
		finish();
			
		}
	});
	 
 }
}
