package com.liwei.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.liwei.model.bean.QuestionAndAnswer;
import com.liwei.teachsystem.R;

public class QuestionBackAdapter extends BaseAdapter {
	private Context context;
	private List<QuestionAndAnswer>list;
public QuestionBackAdapter(Context context,List<QuestionAndAnswer>list) {
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
		int p=position+1;
		QuestionAndAnswer questionAndAnswer=list.get(position);
		if(convertView==null){
			convertView=View.inflate(context, R.layout.questionback_item, null);
		}
     TextView question=(TextView) convertView.findViewById(R.id.question);
     TextView answer=(TextView) convertView.findViewById(R.id.answer);
     question.setText(p+"、"+questionAndAnswer.getQuestion());
     answer.setText("答:"+questionAndAnswer.getAnswer());
		return convertView;
	}

}
