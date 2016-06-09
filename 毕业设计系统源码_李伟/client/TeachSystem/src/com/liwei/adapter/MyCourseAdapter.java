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
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.liwei.activity.CourseTestActivity;
import com.liwei.activity.SubmitQuestion;
import com.liwei.model.bean.ChooseCourseEnty;
import com.liwei.teachsystem.R;
import com.liwei.utils.ToastUtils;
import com.liwei.utils.UrlUtils;

public class MyCourseAdapter  extends BaseAdapter{
	private String sno=null;
	private List<ChooseCourseEnty>list;
	private Context context;
public MyCourseAdapter(Context context,List<ChooseCourseEnty>list,String sno) {
	this.context=context;
	this.list=list;
	this.sno=sno;
		
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
		final ChooseCourseEnty chooseCourseEnty=list.get(position);
		final ViewHolder holder;
		if(convertView==null){
			holder=new ViewHolder();
			convertView=View.inflate(context, R.layout.mycourse_item, null);
			holder.cno=(TextView) convertView.findViewById(R.id.my_cno_tv);
			holder.cname=(TextView) convertView.findViewById(R.id.my_cname_tv);
			holder.cadress=(TextView) convertView.findViewById(R.id.my_cadress_tv);
			holder.classtime=(TextView) convertView.findViewById(R.id.my_ctime_tv);
			holder.content=(TextView) convertView.findViewById(R.id.my_content_tv);
			holder.teacher=(TextView) convertView.findViewById(R.id.my_teacher_tv);
			holder.test=(Button) convertView.findViewById(R.id.test);
			holder.submitQuestionButton=(Button) convertView.findViewById(R.id.askquestion);
			holder.sign=(Button) convertView.findViewById(R.id.sign);
			
			convertView.setTag(holder);
			
		}else{
		holder=	(ViewHolder) convertView.getTag();
		}
		holder.cno.setText(chooseCourseEnty.getCno()+"");
		holder.cname.setText(chooseCourseEnty.getCname());
		holder.cadress.setText(chooseCourseEnty.getCadress());
		holder.content.setText(chooseCourseEnty.getCourseContent());
		holder.classtime.setText(chooseCourseEnty.getClassTime());
		holder.teacher.setText(chooseCourseEnty.getTeacherName());
		holder.submitQuestionButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(context,SubmitQuestion.class);
				intent.putExtra("sno", sno);
				intent.putExtra("cno", chooseCourseEnty.getCno());
				context.startActivity(intent);
				
			}
		});
		holder.test.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				HttpUtils utils=new HttpUtils();
				RequestParams params=new RequestParams();
				params.addBodyParameter("sno",sno);
				params.addBodyParameter("cno",String.valueOf( chooseCourseEnty.getCno()));
				utils.send(HttpMethod.POST, UrlUtils.url+"/CheckIsTestServlet",params ,new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						String flag=responseInfo.result;
						if(flag.equals("false")){
							Intent intent=new Intent(context,CourseTestActivity.class);
							intent.putExtra("sno", sno);
							intent.putExtra("cno", chooseCourseEnty.getCno());
							context.startActivity(intent);
						}else{
							Toast.makeText(context, "您已考试，不能再考", Toast.LENGTH_LONG).show();
						}
						
					}
				});
			}
		});
		
		holder.sign.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				HttpUtils utils=new HttpUtils();
				RequestParams params=new RequestParams();
				params.addBodyParameter("sno",sno);
				params.addBodyParameter("cno",String.valueOf(chooseCourseEnty.getCno()));
				utils.send(HttpMethod.POST, UrlUtils.url+"/StudentSignServlet", params, new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String respnseinfo) {
						ToastUtils.getToast(context, "请求网络数据失败");
						
					}

					@Override
					public void onSuccess(ResponseInfo<String> respnseinfo) {
					String flag=respnseinfo.result;
				if(flag.equals("1")){
					ToastUtils.getToast(context, "不能重复签到");
				}else if(flag.equals("true")){
						ToastUtils.getToast(context, "签到成功");
					}else{
						ToastUtils.getToast(context, "签到失败");
					}
					
						
					}
				});
			}
		});
		return convertView;
	}
	class ViewHolder{
		public TextView cno;
		public TextView cname;
		public TextView classtime;
		public TextView cadress;
		public TextView content;
		public TextView teacher;
		public Button test;
		private Button submitQuestionButton;
		private Button sign;
		
	}

}
