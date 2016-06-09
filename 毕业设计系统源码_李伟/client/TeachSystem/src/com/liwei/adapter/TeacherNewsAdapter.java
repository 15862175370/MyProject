package com.liwei.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.liwei.model.bean.TeachNotify;
import com.liwei.teachsystem.R;

public class TeacherNewsAdapter extends BaseAdapter{
	private Context context;
	private List<TeachNotify> list;
	public TeacherNewsAdapter(Context context,List<TeachNotify> list) {
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
	public View getView(int position, View convertView, ViewGroup parent) {
	TeachNotify teachNews=list.get(position);
		ViewHolder holder=null;
		if(convertView==null){
			holder=new ViewHolder();
			convertView=View.inflate(context, R.layout.teachnewsitem,null);
			holder.teachnewscontent=(TextView)convertView.findViewById(R.id.id_teachcontent);
			holder.timeTextView=(TextView)convertView.findViewById(R.id.time);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder)convertView.getTag();
		}
if(teachNews!=null){
	holder.teachnewscontent.setText(teachNews.getNotify_content());
	holder.timeTextView.setText(String.valueOf(teachNews.getNotify_time()).substring(0, 19));
}
		return convertView;
	}
class ViewHolder{
	public TextView teachnewscontent;
	public TextView timeTextView;
}
	
}
