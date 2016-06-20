package com.atgem.googleplay;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import bean.AllList;
import bean.BarpostCommentInfo;
import bean.BarpostItem;
import bean.Url;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

public class BarPostItemComment extends Activity {
	private RoundedCornerImageView b_icon;
	private TextView b_name;
	private TextView b_pos;
	private ImageView b_zuopin;
	private TextView s_name;
	private TextView time;
	private EditText et_talk;
	private TextView tv_send;
	private BarpostItem barpostItem;
	private AllList lv;
	private MyAdapter adapter;
	 List<BarpostCommentInfo> list=new ArrayList<BarpostCommentInfo>();
	int itemid;//哪个理发师帖子
	
class MyAdapter extends BaseAdapter{
	 List<BarpostCommentInfo> list;
public MyAdapter( List<BarpostCommentInfo> list) {
	// TODO Auto-generated constructor stub;
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
		// TODO Auto-generated method stub
		BarpostCommentInfo barpostcommentItem=list.get(position);
		if(convertView==null){
			convertView=View.inflate(BarPostItemComment.this, R.layout.activity_barber_comment_item, null);
		}
		TextView content=(TextView) convertView.findViewById(R.id.textView1);
		content.setText(barpostcommentItem.getContent());
		TextView u_name=(TextView) convertView.findViewById(R.id.tv_uname);
		u_name.setText(barpostcommentItem.getU_name());
		TextView time=(TextView) convertView.findViewById(R.id.tv_time);
		time.setText(barpostcommentItem.getTime()+"");
		return convertView;
	}
	
}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_barber_comment);
		showAcitonBar();
	                     itemid= getIntent().getIntExtra("m_barber_id",1);
	barpostItem=(BarpostItem) getIntent().getSerializableExtra("baritem");
		BitmapUtils utils=new BitmapUtils(this);
		b_icon=(RoundedCornerImageView)findViewById(R.id.iv_bphotp);
		utils.display(b_icon, barpostItem.getB_icon());
		b_name=(TextView)findViewById(R.id.tv_bname);
		b_name.setText(barpostItem.getB_name());
		b_pos=(TextView)findViewById(R.id.tv_bpos);
		b_pos.setText(barpostItem.getB_position());
		b_zuopin=(ImageView)findViewById(R.id.iv_zuopin);
		et_talk=(EditText) findViewById(R.id.et_talk);
		tv_send=(TextView) findViewById(R.id.tv_send);//单机发送按钮
		
		tv_send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				 RequestParams params = new RequestParams();
				 params.addHeader("name", "value");
				 params.addQueryStringParameter("name", "value");
                      SharedPreferences sp=getSharedPreferences("userInfo",MODE_PRIVATE);
				      int  u_id=sp.getInt("u_id",1);
                      params.addBodyParameter("u_id", String.valueOf(u_id));
                      params.addBodyParameter("m_barber_id", String.valueOf(itemid));
                      params.addBodyParameter("content",et_talk.getText().toString());

				 HttpUtils http = new HttpUtils();
				 http.send(HttpRequest.HttpMethod.POST,
						 Url.url+":8080/Alisi2/AddBarpostCommnetServlet",
				     params,
				     new RequestCallBack<String>() {

				      


				         

						@Override
				         public void onSuccess(ResponseInfo<String> responseInfo) {
				        	
							
							RequestParams params = new RequestParams();
							 params.addHeader("name", "value");
							 params.addQueryStringParameter("name", "value");

							 params.addBodyParameter("m_barber_id", String.valueOf(itemid));



							 HttpUtils http = new HttpUtils();
							 http.send(HttpRequest.HttpMethod.POST,
									 Url.url+":8080/Alisi2/ShowAllbarpostCommentById",
							     params,
							     new RequestCallBack<String>() {

							      


							         

									@Override
							         public void onSuccess(ResponseInfo<String> responseInfo) {
							        	 Gson gson=new Gson();
											Type typeOfT=new TypeToken<List<BarpostCommentInfo>>(){}.getType();
											list=gson.fromJson(responseInfo.result,typeOfT);
											 adapter=new MyAdapter(list);
											 lv.setAdapter(adapter);
											 adapter.notifyDataSetChanged();
							         }

							         @Override
							         public void onFailure(HttpException error, String msg) {
							                    System.out.println("BarPostItem出错。。。。。。。");
							         }
							 });
							 
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
				        	Toast.makeText(BarPostItemComment.this,"评论成功...",Toast.LENGTH_SHORT).show();
				        	adapter.notifyDataSetChanged();
				        	
				         }

				         @Override
				         public void onFailure(HttpException error, String msg) {
				                    System.out.println("点击评论失败......");
				         }
				 });
				 
				
				
				
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		utils.display(b_zuopin, barpostItem.getImage());
		s_name=(TextView)findViewById(R.id.tv_bstore);
	s_name.setText("阿海美发设计");
	time=(TextView)findViewById(R.id.textView1);
	time.setText("40分钟前");
 lv=(AllList)findViewById(R.id.lv_pinglun);
 RequestParams params = new RequestParams();
 params.addHeader("name", "value");
 params.addQueryStringParameter("name", "value");

 params.addBodyParameter("m_barber_id", String.valueOf(itemid));



 HttpUtils http = new HttpUtils();
 http.send(HttpRequest.HttpMethod.POST,
		 Url.url+":8080/Alisi2/ShowAllbarpostCommentById",
     params,
     new RequestCallBack<String>() {

      


         

		@Override
         public void onSuccess(ResponseInfo<String> responseInfo) {
        	 Gson gson=new Gson();
				Type typeOfT=new TypeToken<List<BarpostCommentInfo>>(){}.getType();
				list=gson.fromJson(responseInfo.result,typeOfT);
				 adapter=new MyAdapter(list);
				 lv.setAdapter(adapter);
         }

         @Override
         public void onFailure(HttpException error, String msg) {
                    System.out.println("BarPostItem出错。。。。。。。");
         }
 });
 
 
 
 
		
		
		
	}
	public void showAcitonBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
				ActionBar.LayoutParams.MATCH_PARENT,
				ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View titleView = inflater.inflate(R.layout.action_bar_title, null);
		actionBar.setCustomView(titleView, lp);

		actionBar.setDisplayShowHomeEnabled(false);// 去掉导航
		actionBar.setDisplayShowTitleEnabled(false);// 去掉标题
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		actionBar.setDisplayShowCustomEnabled(true);
		TextView tv_title = (TextView) actionBar.getCustomView().findViewById(
				R.id.title);
		tv_title.setText("作品评论");
		ImageButton imageBtn = (ImageButton) actionBar.getCustomView()
				.findViewById(R.id.image_btn);

		imageBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

			}
		});
	}

}
