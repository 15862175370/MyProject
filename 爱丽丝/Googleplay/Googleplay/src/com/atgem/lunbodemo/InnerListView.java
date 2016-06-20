package com.atgem.lunbodemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;
import android.widget.ScrollView;

public class InnerListView extends ListView {

	public ScrollView parentScrollView;
	
	
	public InnerListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public InnerListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public InnerListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
   @Override
public boolean onInterceptTouchEvent(MotionEvent ev) {
	// TODO Auto-generated method stub
	   switch (ev.getAction()) {
	case MotionEvent.ACTION_DOWN:
		setParentScrollAble(false);
		break;
	  case MotionEvent.ACTION_MOVE:
          
		//  LogManager.d("onInterceptTouchEvent move");
		                  break;
		   
	  case MotionEvent.ACTION_UP:
		                  
		//  LogManager.d("onInterceptTouchEvent up");
		              case 
		  MotionEvent.ACTION_CANCEL:
		                  
		//  LogManager.d("onInterceptTouchEvent cancel");
		                 
		   setParentScrollAble(true);//当手指松开时，让父ScrollView重新拿到onTouch权限
		          break;
	default:
		break;
	}
	    
	   
	return super.onInterceptTouchEvent(ev);
}
   /**
    * 是否把滚动事件交给父scrollview
    * 
    * @param 
flag
    */
   private void setParentScrollAble(boolean flag) {
    
 parentScrollView.requestDisallowInterceptTouchEvent(!flag);//这里的parentScrollView就是listview外面的那个scrollview
 
 
}
	

}
