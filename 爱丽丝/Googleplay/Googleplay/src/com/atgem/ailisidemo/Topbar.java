package com.atgem.ailisidemo;

import com.atgem.googleplay.LoginActivity;
import com.atgem.googleplay.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Topbar extends RelativeLayout {

	private Button leftButton,rightButton;
	private TextView tvTitle;
	//������Button����
	private int leftTextColor;
	private Drawable leftBackground;
	private String leftText;
	//������Button����
	private int rightTextColor;
	private Drawable rightBackground;
	private String rightText;
	//�����������
	private float titleTextSize;
	private String title;
	private int titleTextColor;
	
	private LayoutParams leftParams,rightParams,titleParams;
	
	private OnTopbarClickListener listener;
	private final Context context;
	
	public void setOnTopbarClickListener(OnTopbarClickListener listener){
		this.listener=listener;
	}
	
	
	
	public Topbar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.context=context;
		TypedArray ta=context.obtainStyledAttributes(attrs, R.styleable.topbar);
		leftTextColor=ta.getColor(R.styleable.topbar_leftTextColor,0);
		leftText=ta.getString(R.styleable.topbar_leftText);
		leftBackground=ta.getDrawable(R.styleable.topbar_leftbackground);
		
		rightTextColor=ta.getColor(R.styleable.topbar_rightTextColor, 0);
		rightText=ta.getString(R.styleable.topbar_rightText);
		rightBackground=ta.getDrawable(R.styleable.topbar_rightbackground);
		
		title=ta.getString(R.styleable.topbar_titlecontent);
		titleTextSize=ta.getDimension(R.styleable.topbar_titleTextSize, 0);
		titleTextColor=ta.getColor(R.styleable.topbar_titleTextColor, 0);
		
		ta.recycle();
		//��ȡbutton���
		leftButton=new Button(context);
		rightButton=new Button(context);
		tvTitle=new TextView(context);
		//Ϊtopbar�е�ÿ�������ֵ����ʼ��s
		leftButton.setText(leftText);
		leftButton.setTextColor(leftTextColor);
		leftButton.setBackground(leftBackground);
		
		rightButton.setText(rightText);
		rightButton.setBackground(rightBackground);
		rightButton.setTextColor(rightTextColor);
		
		tvTitle.setText(title);
		tvTitle.setTextSize(titleTextSize);
		tvTitle.setTextColor(titleTextColor);
		tvTitle.setGravity(Gravity.CENTER);
		
		//setBackgroundColor(0xfff59563);
		setBackgroundColor(0Xffffffff);
		leftParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		addView(leftButton,leftParams);
		
		rightParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		addView(rightButton,rightParams);
		
		titleParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
		titleParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
		addView(tvTitle,titleParams);
		
		
		leftButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			Toast.makeText(getContext(),"Back....", 0).show();
		
			}
		});
		rightButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				listener.rightClick();
				
			}
		});
	}

}
