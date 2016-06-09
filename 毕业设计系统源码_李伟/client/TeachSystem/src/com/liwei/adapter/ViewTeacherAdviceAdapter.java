package com.liwei.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.liwei.model.bean.ViewTeacherAdviceBean;
import com.liwei.teachsystem.R;

public class ViewTeacherAdviceAdapter extends BaseAdapter {
	private Context context;
	private List<ViewTeacherAdviceBean> list;
	 public ViewTeacherAdviceAdapter(Context context,List<ViewTeacherAdviceBean> list) {
		 this.context=context;
		 this.list=list;
		
	}

	@Override
	public int getCount() {
	
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
	
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewTeacherAdviceBean viewTeacherAdviceBean=list.get(position);
		ViewHolder holder;
		if(convertView==null){
			holder=new ViewHolder();
			convertView=View.inflate(context, R.layout.teacher_advice_item, null);
			holder.content=(TextView) convertView.findViewById(R.id.textView2);
			holder.tname=(TextView) convertView.findViewById(R.id.textView1);
			holder.time=(TextView) convertView.findViewById(R.id.textView3);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		
		holder.tname.setText(viewTeacherAdviceBean.getTname());
		holder.content.setText(viewTeacherAdviceBean.getContent());
		holder.time.setText(String.valueOf(viewTeacherAdviceBean.getTimestamp()).substring(0,19));
		return convertView;
	}
	
	class ViewHolder{
		public TextView tname;
		public TextView content;
		public TextView time;
		
	}

}
