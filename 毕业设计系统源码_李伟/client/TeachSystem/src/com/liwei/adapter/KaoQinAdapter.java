package com.liwei.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.liwei.model.bean.KaoQin;
import com.liwei.teachsystem.R;

public class KaoQinAdapter extends BaseAdapter {
	private List<KaoQin> list;
   private Context context;
 
   public  KaoQinAdapter(Context context,List<KaoQin> list) {
	this.context=context;
	this.list=list;

}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		KaoQin kaoQin=list.get(position);
		ViewHolder holder = null;
		if(convertView==null){
			holder=new ViewHolder();
			convertView=View.inflate(context, R.layout.kaoqin_item, null);
			holder.sname=(TextView) convertView.findViewById(R.id.sname);
			holder.ip=(TextView) convertView.findViewById(R.id.ip);
			holder.state=(TextView) convertView.findViewById(R.id.state);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		holder.sname.setText((position+1)+"、"+kaoQin.getSname()+"");
		holder.ip.setText(kaoQin.getIp());
		if(kaoQin.getState().equals("1")){
			holder.state.setText("已到");
		}else{
			holder.state.setText("缺勤");
		}
		if(!kaoQin.getIp().substring(0, 5).equals("10.20")){
			holder.state.setText("签到异常");
		}
		return convertView;
	}
class ViewHolder{
	public TextView sname;
	public TextView ip;
	public TextView state;
}
}
