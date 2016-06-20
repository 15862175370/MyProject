package com.atgem.ailisidemo;


	import android.content.Context;
	import android.util.AttributeSet;
	/**
	* @author Administrator
	*
	*/
	import android.widget.ListView;

	/**
	* 
	* @author wjh
	* �Զ����ListView ����ScrollViewǶ��ListViewʱ ListViewչʾ��ȫ
	*/
	public class AllListView extends ListView
	{
          
	public AllListView(Context context, AttributeSet attrs)
	{
	super(context, attrs);
	}

	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
	int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
	MeasureSpec.AT_MOST);
	super.onMeasure(widthMeasureSpec, mExpandSpec);
	}

	
	
	}
	


