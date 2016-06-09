package com.liwei.model.bean;

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
* 自定义的ListView 用于ScrollView嵌套ListView时 ListView展示不全
*/
public class AllList extends ListView
{

public AllList(Context context, AttributeSet attrs)
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



