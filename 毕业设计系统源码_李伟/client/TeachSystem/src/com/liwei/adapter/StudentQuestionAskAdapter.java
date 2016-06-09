package com.liwei.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.liwei.activity.ReplyStudentQuestion;
import com.liwei.model.bean.StudentAskQuestion;
import com.liwei.teachsystem.R;

public class StudentQuestionAskAdapter extends BaseAdapter {
	private String tno;
	private Context context;
	private List<StudentAskQuestion> list;
	 public StudentQuestionAskAdapter(Context context,List<StudentAskQuestion> list,String tno) {
		 this.context=context;
		 this.list=list;
		this.tno=tno;
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
		final StudentAskQuestion studentAskQuestion=list.get(position);
		int xuhao=position+1;
		final ViewHolder holder;
		
		if(convertView==null){
			holder=new ViewHolder();
			convertView=View.inflate(context, R.layout.student_ask_question_item, null);
			holder.title=(TextView) convertView.findViewById(R.id.textView1);
			holder.contentTextView=(TextView) convertView.findViewById(R.id.textView2);
			holder.reply=(TextView) convertView.findViewById(R.id.textView3);
			convertView.setTag(holder);
			
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
	holder.title.setText(xuhao+"、"+studentAskQuestion.getSname()+"同学的问题：");
	holder.contentTextView.setText(studentAskQuestion.getQuestionContentString());
	if(studentAskQuestion.getState()==0){
		holder.reply.setText("回复");
	}else{
		holder.reply.setText("已回复");
	}
	holder.reply.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if(holder.reply.getText().toString().equals("回复")){
				Intent intent=new Intent(context,ReplyStudentQuestion.class);
				intent.putExtra("tno", tno);
				intent.putExtra("sno",studentAskQuestion.getSno() );
				intent.putExtra("sq_id", studentAskQuestion.getSq_id());
				context.startActivity(intent);
			}else{
			
			}

		}
	});
		return convertView;
	}
	class ViewHolder{
		public TextView title;
		public  TextView contentTextView;
		public TextView reply;
	
	}

}
