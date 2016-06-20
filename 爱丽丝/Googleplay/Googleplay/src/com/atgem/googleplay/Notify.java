package com.atgem.googleplay;



import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class Notify extends Activity  {
List<String>list=new ArrayList<String>();
private ListView lv;

class MyAdapter extends BaseAdapter{
	private List<String> list1;
	public MyAdapter(List<String> list) {
		// TODO Auto-generated constructor stub
		list1=list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list1.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list1.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		String s=list1.get(position);
		if(convertView==null){
		convertView=View.inflate(Notify.this, R.layout.reservetime_items_activity,null);
		}
		TextView tView=(TextView) convertView.findViewById(R.id.tv_time);
		tView.setText(s);
		return convertView;
	}
	
}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notify);
		
		lv=(ListView)findViewById(R.id.listView1);
	String string=getIntent().getStringExtra("a");
	list.add(string);
	
	MyAdapter adapter=new MyAdapter(list);
	lv.setAdapter(adapter);
	
	
		
      
	}

	
		
	

}
