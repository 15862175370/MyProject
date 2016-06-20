package com.atgem.adapter;

import com.atgem.googleplay.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class GridViewAdapter extends BaseAdapter {
       Context context;
       int[] images;
	public GridViewAdapter(Context context,int[] images) {
	       this.context=context;
	       this.images=images;
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return images.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		View view=View.inflate(context,R.layout.gv_item, null);
           if(arg1==null){
				
			
			//  View view=View.inflate(HomeActivity.this,R.layout.gv_item,null);
			  
			  arg1=view;
			}
			ImageView imageView=(ImageView) view.findViewById(R.id.iv_item);
			  imageView.setImageResource(images[arg0]);
			
			
			return view;
	
	}

}
