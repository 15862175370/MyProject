package com.liwei.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.liwei.model.bean.StudentMessageBean;
import com.liwei.teachsystem.R;

public class StudentMessageAdapter extends BaseAdapter{
	private Context context;
	private List<StudentMessageBean> list;
	public StudentMessageAdapter(Context context,List<StudentMessageBean> list){
		this.context=context;
		this.list=list;
		
	}

	@Override
	public int getCount() {
		
		return list.size();
	}

	@Override
	public Object getItem(int position) {
	
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
	
		return position;
	}

	@Override
	public View getView(int position, View containView, ViewGroup viewGroup) {
	
	StudentMessageBean	 messageBean=list.get(position);
		ViewHolder holder;
		if(containView==null){
			holder=new ViewHolder();
			containView=View.inflate(context, R.layout.to_teacher_message_item, null);
			holder.name=(TextView) containView.findViewById(R.id.textView1);
			holder.content=(TextView) containView.findViewById(R.id.textView2);
			holder.time=(TextView) containView.findViewById(R.id.textView3);
			containView.setTag(holder);

			
		}else{
			holder=(ViewHolder) containView.getTag();
		}
	holder.name.setText(messageBean.getName());
	holder.content.setText(messageBean.getMessage());
	holder.time.setText(String.valueOf(messageBean.getSystemtime()).substring(0,19));
		return containView;
	}

	class ViewHolder{
		public TextView name;
		public TextView content;
		public TextView time;
	}
}
