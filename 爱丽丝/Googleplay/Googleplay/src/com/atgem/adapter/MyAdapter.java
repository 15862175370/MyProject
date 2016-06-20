package com.atgem.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import bean.BarpostItem;

import com.atgem.googleplay.BarPostItemComment;
import com.atgem.googleplay.LoginActivity;
import com.atgem.googleplay.R;
import com.atgem.googleplay.SpaceImageDetailActivity;
import com.king.photo.activity.MainActivity;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.loopj.android.image.SmartImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.roamer.ui.view.SquareCenterImageView;

public class MyAdapter extends BaseAdapter {
     private List<BarpostItem> items;
     private Context context;
    private boolean isFirstIn=true;
 
	public MyAdapter(List<BarpostItem> items, Context context) {
		super();
		this.items = items;
		this.context = context;
	}

	public List<BarpostItem> getItems() {
		return items;
	}

	public void setItems(List<BarpostItem> items) {
		this.items = items;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return items.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}
  /**
   *    String path="http://10.40.7.16:8080/Alisi2/upload/ee78adcc-f739-40ef-b99e-d8c86847370f.png";
		   SmartImageView siv=(SmartImageView) findViewById(R.id.imageView1);
	           siv.setImageUrl(path);
   */
	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		final BarpostItem item=items.get(position);
		final int theOne=position+1;
	    
		if(convertView==null){
			convertView=View.inflate(context,R.layout.worksitem1,null);
			
		}
		   SmartImageView siv=(SmartImageView)convertView.findViewById(R.id.imageView1);
		   final SquareCenterImageView image=( SquareCenterImageView)convertView.findViewById(R.id.imageView2);
		   image.setScaleType(ScaleType.CENTER_CROP);
		   TextView b_name=(TextView) convertView.findViewById(R.id.b_name);
		TextView b_position=(TextView) convertView.findViewById(R.id.position);
		TextView content=(TextView) convertView.findViewById(R.id.content);
		final TextView commentCount=(TextView) convertView.findViewById(R.id.commentCount);
		TextView s_name=(TextView)convertView.findViewById(R.id.belong);
		s_name.setText(item.getBelong());
		TextView pariseCount=(TextView) convertView.findViewById(R.id.pariseCount);
		ImageView iv_comment=(ImageView)convertView.findViewById(R.id.iv_comment);
	/*	iv_comment.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				 SharedPreferences sp = context.getSharedPreferences("userInfo",context.MODE_PRIVATE);
			       int u_id=sp.getInt("u_id",0);
			   if(u_id!=0)
			   {	Intent intent=new Intent(context,MainActivity.class);
		          SharedPreferences m_barber_id = context.getSharedPreferences("m_barber_id",context.MODE_PRIVATE);
		               SharedPreferences.Editor editor=m_barber_id.edit();
		          
		          editor.putInt("m_barber_id",theOne);
		          intent.putExtra("barberpost","barberpost");
		          editor.commit();
	         context.startActivity(intent);
			   }else{
				   Intent intent=new Intent(context,LoginActivity.class);
				   context.startActivity(intent);
			   }
			}
		});*/
		//ImageView comment=(ImageView)convertView.findViewById(R.id.);
		iv_comment.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
          
		    	Intent intent=new Intent();
				Bundle bundle = new Bundle();  
		        bundle.putSerializable("baritem",item);  
				intent.setClass(context,BarPostItemComment.class);
				intent.putExtra("m_barber_id",theOne);
			
				 intent.putExtras(bundle);  
		           
				    context.startActivity(intent);
				
				
			}
		});

		ImageView iv_parise=(ImageView)convertView.findViewById(R.id.iv_parise);
		
		iv_parise.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				 SharedPreferences sp = context.getSharedPreferences("userInfo",context.MODE_PRIVATE);
			       int u_id=sp.getInt("u_id",0);
			   if(u_id!=0)
			   {   int number=item.getZanCount();
				      item.setZanCount(number+1);
				   RequestParams params = new RequestParams();
				   params.addHeader("name", "value");
				   params.addQueryStringParameter("name", "value");
                   
                    
                    
                    // 只包含字符串参数时默认使用BodyParamsEntity，
				   // 类似于UrlEncodedFormEntity（"application/x-www-form-urlencoded"）。
				   params.addBodyParameter("u_id",String.valueOf(u_id));
				   params.addBodyParameter("m_barber_id",String.valueOf(theOne+1));
				   // 加入文件参数后默认使用MultipartEntity（"multipart/form-data"），
				   // 如需"multipart/related"，xUtils中提供的MultipartEntity支持设置subType为"related"。
				   // 使用params.setBodyEntity(httpEntity)可设置更多类型的HttpEntity（如：
				   // MultipartEntity,BodyParamsEntity,FileUploadEntity,InputStreamUploadEntity,StringEntity）。
				   // 例如发送json参数：params.setBodyEntity(new StringEntity(jsonStr,charset));
				 
				   
				   
				   HttpUtils http = new HttpUtils();
				   http.send(HttpRequest.HttpMethod.POST,
				       "http://10.202.1.99:8080/Alisi2/AddZanCountServlet",
				       params,
				       new RequestCallBack<String>() {

				        

				        

				           @Override
				           public void onSuccess(ResponseInfo<String> responseInfo) {
				              System.out.println("yesyesyes!!!");
				           }

				           @Override
				           public void onFailure(HttpException error, String msg) {
				            System.out.println("nonononono!!!");
				           }
				   });
			      
			      
			      
			      
				
				     
				//  Toast.makeText(context,number,0).show();
					notifyDataSetChanged();
			      
			      
			      
			   }else{
				   Intent intent=new Intent(context,LoginActivity.class);
				   context.startActivity(intent);
				   
				   
				   
			   }
				
			}
		});
		
		TextView time=(TextView)convertView.findViewById(R.id.time);
		
		
		pariseCount.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					 SharedPreferences sp = context.getSharedPreferences("userInfo",context.MODE_PRIVATE);
				       int u_id=sp.getInt("u_id",0);
				   if(u_id!=0)
				   {   int number=item.getZanCount();
					      item.setZanCount(number+1);
					   RequestParams params = new RequestParams();
					   params.addHeader("name", "value");
					   params.addQueryStringParameter("name", "value");
                         
                          
                          
                          // 只包含字符串参数时默认使用BodyParamsEntity，
					   // 类似于UrlEncodedFormEntity（"application/x-www-form-urlencoded"）。
					   params.addBodyParameter("u_id",String.valueOf(u_id));
					   params.addBodyParameter("m_barber_id",String.valueOf(theOne+1));
					   // 加入文件参数后默认使用MultipartEntity（"multipart/form-data"），
					   // 如需"multipart/related"，xUtils中提供的MultipartEntity支持设置subType为"related"。
					   // 使用params.setBodyEntity(httpEntity)可设置更多类型的HttpEntity（如：
					   // MultipartEntity,BodyParamsEntity,FileUploadEntity,InputStreamUploadEntity,StringEntity）。
					   // 例如发送json参数：params.setBodyEntity(new StringEntity(jsonStr,charset));
					 
					   
					   
					   HttpUtils http = new HttpUtils();
					   
					   http.send(HttpRequest.HttpMethod.POST,
					       "http://10.202.1.99:8080/Alisi2/AddZanCountServlet",
					       params,
					       new RequestCallBack<String>() {

					        

					        

					           @Override
					           public void onSuccess(ResponseInfo<String> responseInfo) {
					              System.out.println("yesyesyes!!!");
					           }

					           @Override
					           public void onFailure(HttpException error, String msg) {
					            System.out.println("nonononono!!!");
					           }
					   });
				      
				      
				      
				      
					
					     
					//  Toast.makeText(context,number,0).show();
						notifyDataSetChanged();
				      
				      
				      
				   }else{
					   Intent intent=new Intent(context,LoginActivity.class);
					   context.startActivity(intent);
					   
					   
					   
				   }
				      
				      
				      
				      
				   
				}}
		
			);
		 
		
	   // hasZan=true;//用来规定每个用户只能赞一次！！！！
		  commentCount.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(context,MainActivity.class);
				          SharedPreferences m_barber_id = context.getSharedPreferences("m_barber_id",context.MODE_PRIVATE);
				               SharedPreferences.Editor editor=m_barber_id.edit();
				          
				          editor.putInt("m_barber_id",theOne);
				          intent.putExtra("barberpost","barberpost");
				          editor.commit();
			         context.startActivity(intent);
			}
		});
		//Log.i("error",item.getB_name());
		b_name.setText(item.getB_name());
		//Log.i("error",item.getB_position());
		b_position.setText(item.getB_position());
		content.setText(item.getContent());
		//Log.i("error",item.getCommentCount()+"");
		
		commentCount.setText(String.valueOf(item.getCommentCount()));
		pariseCount.setText(String.valueOf(item.getZanCount()));
		Log.i("error",item.getB_icon());
		//http://10.40.7.16:8080/Alisi2/upload/ee78adcc-f739-40ef-b99e-d8c86847370f.png0/Alisi2/upload/ee78adcc-f739-40ef-b99e-d8c86847370f.png
		 siv.setImageUrl(item.getB_icon());
		 image.setScaleType(ScaleType.CENTER_CROP);
	    ImageLoader.getInstance().displayImage(item.getImage(), image);
		 //image.setImageUrl(item.getImage());
	//http://localhost:8080/Alisi2/upload/24e50006-a78d-4bb0-9807-acfe5121b998.png
		
		image.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context,SpaceImageDetailActivity.class);
				intent.putExtra("images",item.getImage());
				intent.putExtra("position", theOne-1);
				int[] location = new int[2];
				image.getLocationOnScreen(location);
				intent.putExtra("locationX", location[0]);
				intent.putExtra("locationY", location[1]);
                    
				intent.putExtra("width", image.getWidth());
				intent.putExtra("height", image.getHeight());
				  System.out.println("宽度是："+image.getWidth());
				context.startActivity(intent);
				((Activity) context).overridePendingTransition(0, 0);
			}
		});
		 
		 
		 return convertView;
	}



}
