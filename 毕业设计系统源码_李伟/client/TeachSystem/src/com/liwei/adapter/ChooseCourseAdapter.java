package com.liwei.adapter;

import java.util.List;
import android.content.Context;
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
import com.liwei.activity.ChooseCourse;
import com.liwei.model.bean.ChooseCourseEnty;
import com.liwei.teachsystem.R;
import com.liwei.utils.UrlUtils;
/**
 * 
 * @author liwei
 *
 */

public class ChooseCourseAdapter extends BaseAdapter {
	private ChooseCourse chooseCourse;
	private Context context;
	private List<ChooseCourseEnty> list=null;
	private String sno=null;
    public ChooseCourseAdapter(Context context, ChooseCourse chooseCourse,List<ChooseCourseEnty> list,String sno) {
	            this.context=context;
	            this.list=list;
	            this.sno=sno;
	            this.chooseCourse=chooseCourse;
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
			convertView=View.inflate(context, R.layout.choose_course_item, null);
			holder.cno=(TextView) convertView.findViewById(R.id.cno_tv);
			holder.cname=(TextView) convertView.findViewById(R.id.cname_tv);
			holder.cadress=(TextView) convertView.findViewById(R.id.cadress_tv);
			holder.classtime=(TextView) convertView.findViewById(R.id.ctime_tv);
			holder.content=(TextView) convertView.findViewById(R.id.content_tv);
			holder.teacher=(TextView) convertView.findViewById(R.id.teacher_tv);
			holder.choseButton=(Button) convertView.findViewById(R.id.studentChooseCourse);
			convertView.setTag(holder);
			
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		holder.cno.setText(chooseCourseEnty.getCno()+"");
		holder.cname.setText(chooseCourseEnty.getCname());
		holder.cadress.setText(chooseCourseEnty.getCadress());
		holder.content.setText(chooseCourseEnty.getCourseContent());
		holder.classtime.setText(chooseCourseEnty.getClassTime());
		holder.teacher.setText(chooseCourseEnty.getTeacherName());
		holder.choseButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				if(sno.equals("")&&String.valueOf(chooseCourseEnty.getCno()).equals("")){
					
				}else{
					if(holder.choseButton.getText().toString().equals("选课")){
				HttpUtils utils=new HttpUtils();
				RequestParams params=new RequestParams();
				params.addBodyParameter("sno",sno);
				params.addBodyParameter("cno",String.valueOf(chooseCourseEnty.getCno()));
				utils.send(HttpMethod.POST,UrlUtils.url+"/OkChooseCourseServlet" ,params ,new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						Toast.makeText(context, "请求网络数据失败", Toast.LENGTH_LONG).show();
						
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						String flag=responseInfo.result;
						if(flag.equals("true")){
							Toast.makeText(context, "选课成功", Toast.LENGTH_LONG).show();
							
						      holder.choseButton.setText("已选课");
						
							
						}else{
							Toast.makeText(context, flag, Toast.LENGTH_LONG).show();
						}
						
					}
				});
					}else{
						
					}
				
				}
				
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
		public Button choseButton;
		
	}

}
