package com.liwei.adapter;




import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.liwei.activity.StudentSendMessageToTeacherActivity;
import com.liwei.model.bean.CircleImageView;
import com.liwei.model.bean.MyTeacher;
import com.liwei.teachsystem.R;

public class MyTeacherAdapter extends BaseAdapter {
	private Context context;
	private List<MyTeacher> list;
	private String sno;
	public MyTeacherAdapter(Context context,List<MyTeacher>list,String sno){
		this.context=context;
		this.list=list;
		this.sno=sno;
		
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int positon) {
		return list.get(positon);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final MyTeacher myTeacher=list.get(position);
	ViewHolder holder;
	BitmapUtils utils=new BitmapUtils(context);
		if(convertView==null){
			holder=new ViewHolder();
			convertView=View.inflate(context, R.layout.myteacher_item, null);
		    holder.tno=(TextView) convertView.findViewById(R.id.myteacher_tno);
			holder.cname=(TextView) convertView.findViewById(R.id.myteacher_course);
			holder.tname=(TextView) convertView.findViewById(R.id.myteacher_tname);
			holder.tsex=(TextView) convertView.findViewById(R.id.myteacher_sex);
			holder.prof=(TextView) convertView.findViewById(R.id.teacher_prof);
			holder.phone=(TextView) convertView.findViewById(R.id.myteacher_phone);
			holder.dname=(TextView) convertView.findViewById(R.id.myteacher_dept);
			holder.tage=(TextView) convertView.findViewById(R.id.myteacher_age);
			holder.ticon=(CircleImageView) convertView.findViewById(R.id.mytecher_icon);
			//留言
			holder.Message=(Button) convertView.findViewById(R.id.myteacher_message);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		holder.tno.setText(myTeacher.getTno()+"");
		holder.cname.setText(myTeacher.getCname());
		holder.tname.setText(myTeacher.getTname());
		holder.tsex.setText(myTeacher.getTsex());
		holder.phone.setText(myTeacher.getPhone());
		holder.prof.setText(myTeacher.getProf());
		holder.dname.setText(myTeacher.getDname());
		holder.tage.setText(myTeacher.getTage()+"");
		
		if(myTeacher.getTicon()!=null){
		utils.display(holder.ticon, myTeacher.getTicon());
		}
		holder.Message.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(context,StudentSendMessageToTeacherActivity.class);
				intent.putExtra("sno", sno);
				intent.putExtra("tno",myTeacher.getTno());
				context.startActivity(intent);
				
			}
		});
		
	
		return convertView;
	}
	class ViewHolder{
		public TextView cno;
		public  TextView cname;
		public TextView tno;
		public TextView tname;
	    public TextView tsex;
	    public TextView prof;
	    public CircleImageView ticon;
	    public TextView phone;
	    public TextView dname;
	    public TextView tage;
	    public Button Message;
	}

}
