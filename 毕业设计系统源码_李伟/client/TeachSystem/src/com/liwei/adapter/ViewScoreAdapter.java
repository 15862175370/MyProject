package com.liwei.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.liwei.model.bean.ViewScoreEntry;
import com.liwei.teachsystem.R;

public class ViewScoreAdapter extends BaseAdapter {
	private List<ViewScoreEntry> list;
	private Context context;
	public ViewScoreAdapter(Context context, List<ViewScoreEntry> list) {
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
		ViewScoreEntry viewScoreEntry=list.get(position);
		ViewHolder holder;
		if(convertView==null){
			holder=new ViewHolder();
			convertView=View.inflate(context, R.layout.viewscore_item, null);
			holder.scoreTextView=(TextView) convertView.findViewById(R.id.score_tv);
			holder.snotView=(TextView) convertView.findViewById(R.id.sno_tv);
			holder.cnametTextView=(TextView) convertView.findViewById(R.id.cname_tv);
			holder.snameteTextView=(TextView) convertView.findViewById(R.id.sname_tv);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		
		holder.snotView.setText(viewScoreEntry.getSno()+"");
		holder.snameteTextView.setText(viewScoreEntry.getSname());
		holder.cnametTextView.setText(viewScoreEntry.getCname());
		holder.scoreTextView.setText(viewScoreEntry.getScore()+"");
		return convertView;
	}
	
	class ViewHolder{
		public TextView snotView;
		public TextView snameteTextView;
		public TextView cnametTextView;
		public TextView scoreTextView;
		
	}

}
