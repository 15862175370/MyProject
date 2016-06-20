package com.heima52.pullrefresh.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class HomeViewPager extends ViewPager {

	public HomeViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public HomeViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
/*
	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		// TODO Auto-generated method stub
		  System.out.println("12334");
		if(arg0.getAction()==MotionEvent.ACTION_MOVE){
			System.out.println("自身！！！！！");
			
		}
		return false;
	}
    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
    	// TODO Auto-generated method stub
    	System.out.println("HomeViewPager......");
    	return true;
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
    	// TODO Auto-generated method stub
    	return super.dispatchTouchEvent(ev);
    }*/
   
}
