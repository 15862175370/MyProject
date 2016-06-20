package com.atgem.adapter;

import java.util.List;


import bean.BarpostItem;
import bean.PostActivity;

import com.atgem.googleplay.R;
import com.loopj.android.image.SmartImageView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ActivityAdapter extends BaseAdapter {
     Context context;
     private List<PostActivity> items;
    
	



	public ActivityAdapter(Context context, List<PostActivity> items) {
		super();
		this.context = context;
		this.items = items;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		System.out.println(items.size());
		return items.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return items.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub

		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		final PostActivity item=items.get(position);
		
       
        if(convertView==null){
			convertView=View.inflate(context,R.layout.activity_item, null);
			
		}
        SmartImageView siv=(SmartImageView)convertView.findViewById(R.id.iv_activity);
			 System.out.println(item.getImageUrl());
			siv.setImageUrl(item.getImageUrl());
			
			
			
			
			return convertView;
		
	}

}
