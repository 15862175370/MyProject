package com.liwei.adapter;

import java.util.List;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.liwei.activity.KaoQinActivity;
import com.liwei.activity.MingTiActivity;
import com.liwei.model.bean.ViewTeacherCourseEnty;
import com.liwei.teachsystem.R;
import com.liwei.utils.ToastUtils;
import com.liwei.utils.UrlUtils;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ViewTeacherCourseAdapter extends BaseAdapter {
	private Context context;
	private String tno;
	private List<ViewTeacherCourseEnty> list;
	public ViewTeacherCourseAdapter(Context context,String tno,List<ViewTeacherCourseEnty> list) {
		this.list=list;
		this.context=context;
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
		final ViewTeacherCourseEnty viewTeacherCourseEnty=list.get(position);
		ViewHolder holder;
		if(convertView==null){
			holder=new ViewHolder();
			
			convertView=View.inflate(context, R.layout.teachercourse_item, null);
			holder.kaoqin=(Button) convertView.findViewById(R.id.attendance);
			holder.cancelSign=(Button) convertView.findViewById(R.id.attenda);
			holder.proposition=(Button) convertView.findViewById(R.id.proposition);
			holder.cno=(TextView) convertView.findViewById(R.id.my_cno_tv);
			holder.cname=(TextView) convertView.findViewById(R.id.my_cname_tv);
			holder.cadress=(TextView) convertView.findViewById(R.id.my_cadress_tv);
			holder.classtime=(TextView) convertView.findViewById(R.id.my_ctime_tv);
			holder.content=(TextView) convertView.findViewById(R.id.my_content_tv);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		holder.cno.setText(viewTeacherCourseEnty.getCno()+"");
		holder.cname.setText(viewTeacherCourseEnty.getCname());
		holder.classtime.setText(viewTeacherCourseEnty.getClasstime());
		holder.content.setText(viewTeacherCourseEnty.getContent());
		holder.cadress.setText(viewTeacherCourseEnty.getCadress());
		//清除签到
		holder.cancelSign.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				HttpUtils utils=new HttpUtils();
				RequestParams params=new RequestParams();
				params.addBodyParameter("cno",String.valueOf(viewTeacherCourseEnty.getCno()));
				utils.send(HttpMethod.POST, UrlUtils.url+"/CancelSignServlet", params, new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						ToastUtils.getToast(context, "请求网络数据失败");
						
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						String flag=responseInfo.result;
						if(flag.equals("true")){
							ToastUtils.getToast(context, "清除成功");
						}else{
							ToastUtils.getToast(context, "清除失败");
						}
						
					}
				});
				
			}
		});
		
		//考勤
		holder.kaoqin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(context,KaoQinActivity.class);
				intent.putExtra("cno",viewTeacherCourseEnty.getCno() );
				context.startActivity(intent);
				
			}
		});
		//命题
		holder.proposition.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(context,MingTiActivity.class);
				intent.putExtra("tno", tno);
				intent.putExtra("cno", viewTeacherCourseEnty.getCno());
				context.startActivity(intent);
				
			}
		});
		return convertView;
	}
	class ViewHolder{
		public TextView cno;
		public TextView cname;
		public TextView classtime;
		public TextView content;
		public TextView cadress;
		//命题按钮
		public Button proposition;
		//清除签到按钮
		public Button cancelSign;
	     //查看考勤
		public Button kaoqin;
		
	}

}
