package com.liwei.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.liwei.model.bean.TestCourse;
import com.liwei.teachsystem.R;

public class CourseTestAdapter extends BaseAdapter {
	private String sno=null;
	private int cno;
	private Context context;
	private List<TestCourse> list;
	public int score;
 public CourseTestAdapter(Context context,String sno,int cno,List<TestCourse> list,int score) {
            this.context=context;
            this.list=list;
            this.sno=sno;
            this.cno=cno;
            this.score=score;
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
		TestCourse tCourse=list.get(position);
	final	ViewHolder holder;
		if(convertView==null){
			holder=new ViewHolder();
			convertView=View.inflate(context, R.layout.test_course_item, null);
			holder.question=(TextView) convertView.findViewById(R.id.question_test);
			holder.rGroup=(RadioGroup) convertView.findViewById(R.id.radiogroup_test);
			holder.radioButtonA=(RadioButton) convertView.findViewById(R.id.radiobutton1_test);
			holder.radioButtonB=(RadioButton) convertView.findViewById(R.id.radiobutton2_test);
			holder.radioButtonZ=(RadioButton) convertView.findViewById(R.id.radiobutton3_test);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		holder.question.setText(tCourse.getQuestion_content());
		holder.radioButtonA.setText(tCourse.getAnswer_content_a());
		holder.radioButtonB.setText(tCourse.getAnswer_content_b());
		holder.radioButtonZ.setText(tCourse.getAnswer_correct());
		holder.rGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==holder.radioButtonZ.getId()){
					score=score+4;
				}else{
					
				}
				
			}
		});
	
		return convertView;
	}
	
	class ViewHolder{
		public TextView question;
		public RadioGroup rGroup;
		public RadioButton radioButtonA;
		public RadioButton radioButtonB;
		public RadioButton radioButtonZ;
	}

}
