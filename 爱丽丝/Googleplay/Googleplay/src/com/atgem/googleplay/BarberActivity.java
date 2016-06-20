package com.atgem.googleplay;

import java.lang.reflect.Type;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import bean.FixBarber;
import bean.Order;
import bean.OrderShop;
import bean.ServcePrice;
import bean.Url;

import com.atgem.ailisidemo.activity.OrderMenu;
import com.atgem.googleplay.ShopInfoActivity.ClickItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class BarberActivity extends Activity {

	private ListView lv;
	private FixBarber bar;
	private OrderShop shop;
	private ClickItem item;
	private ImageView guangzhu;
	private boolean flag = true;
	private int u_id;// 记录用户的id
	private GridView gv;
	private int currentPage = 2;
	private TextView tv_chakan;
	private TextView desc;
	 boolean hasLogin;
	class MyAdapter extends BaseAdapter {
		private List<ServcePrice> list;

		public MyAdapter(List<ServcePrice> list) {
			this.list = list;

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
			// ServcePrice price=list.get(position);
			final ServcePrice price = list.get(position);
			if (convertView == null) {
				convertView = View.inflate(BarberActivity.this, R.layout.item1,
						null);
			}
			TextView tView = (TextView) convertView
					.findViewById(R.id.tv_xiangmu);
			tView.setText(price.getType());
			TextView tView1 = (TextView) convertView
					.findViewById(R.id.tv_price);
			tView1.setText(String.valueOf(price.getPrice()));
			Button button = (Button) convertView.findViewById(R.id.bt_yuyue);
			button.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					   if(hasLogin==false){
					  Intent intent = new Intent(BarberActivity.this,
							LoginActivity.class);
					// Intent intent=new Intent(Home.this,Order.class);

					FixBarber barber = (FixBarber) getIntent()
							.getSerializableExtra("bar");
					OrderShop shop = (OrderShop) getIntent()
							.getSerializableExtra("shop");

					Bundle bundle = new Bundle();
					bundle.putSerializable("bar", barber);
					bundle.putSerializable("shop", shop);
					bundle.putSerializable("price", price);
					intent.putExtras(bundle);
					intent.putExtra("age", 1);

					System.out.println(shop.getS_name());
					startActivity(intent);

				}else{
					//必须这样，但是现在数据没有传过去！！！！！！！！！！！！！！
					Intent toOrderSort=new Intent(BarberActivity.this,OrderMenu.class);
					FixBarber barber = (FixBarber) getIntent()
							.getSerializableExtra("bar");
					OrderShop shop = (OrderShop) getIntent()
							.getSerializableExtra("shop");

					Bundle bundle = new Bundle();
					bundle.putSerializable("bar", barber);
					bundle.putSerializable("shop", shop);
					bundle.putSerializable("price", price);
					toOrderSort.putExtras(bundle);
					toOrderSort.putExtra("age", 1);

					System.out.println(shop.getS_name());
					startActivity(toOrderSort);
					
					}
				}
			});

			return convertView;
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_barber);
		gv = (GridView) findViewById(R.id.gv);
		tv_chakan = (TextView) findViewById(R.id.tv_chakan);
		BitmapUtils utils = new BitmapUtils(this);
		bar = (FixBarber) getIntent().getSerializableExtra("bar");
		int id = bar.getB_id();
		OrderShop shop = (OrderShop) getIntent().getSerializableExtra("shop");
		ImageView img = (ImageView) findViewById(R.id.image_icon);

		utils.display(img, bar.getB_icon());
		TextView tv = (TextView) findViewById(R.id.tv_barbername);
		tv.setText(bar.getB_name());
		Button btn = (Button) findViewById(R.id.bt_zhiwei);
		btn.setText(bar.getB_position());
		TextView tv1 = (TextView) findViewById(R.id.tv_zhishu1);
		tv1.setText(String.valueOf(bar.getPop_degree()));
		TextView tv2 = (TextView) findViewById(R.id.tv_zhishu2);
		tv2.setText(String.valueOf(bar.getSer_degree()));

		TextView tv3 = (TextView) findViewById(R.id.tv_zhishu3);
		tv3.setText(String.valueOf(bar.getPro_degree()));

		TextView tv4 = (TextView) findViewById(R.id.tv_dianming);
		tv4.setText(shop.getS_name());

		TextView tv5 = (TextView) findViewById(R.id.tv_pingjia);
		tv5.setText(String.valueOf(shop.getCount_comment()) + "位顾客评价");

		lv = (ListView) findViewById(R.id.listView1);
           showAcitonBar();
		// MyAdapter adapter=new MyAdapter();
		// lv.setAdapter(adapter);
		RequestParams params = new RequestParams();
		params.addHeader("name", "value");
		params.addQueryStringParameter("name", "value");

		// 只包含字符串参数时默认使用BodyParamsEntity，
		// 类似于UrlEncodedFormEntity（"application/x-www-form-urlencoded"）。
		params.addBodyParameter("bar_id", String.valueOf(id));

		// 加入文件参数后默认使用MultipartEntity（"multipart/form-data"），
		// 如需"multipart/related"，xUtils中提供的MultipartEntity支持设置subType为"related"。
		// 使用params.setBodyEntity(httpEntity)可设置更多类型的HttpEntity（如：
		// MultipartEntity,BodyParamsEntity,FileUploadEntity,InputStreamUploadEntity,StringEntity）。
		// 例如发送json参数：params.setBodyEntity(new StringEntity(jsonStr,charset));
		HttpUtils http = new HttpUtils();
		http.send(HttpMethod.POST,
				Url.url+":8080/Alisi2/FindServicePriceServlet",
				params, new RequestCallBack<String>() {

					@Override
					public void onStart() {

					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// TODO Auto-generated method stub

						String json = responseInfo.result;
						Gson g = new Gson();
						Type classOfT = new TypeToken<List<ServcePrice>>() {
						}.getType();
						List<ServcePrice> list12 = g.fromJson(json, classOfT);
						// System.out.println(list12.toString());

						MyAdapter adapter = new MyAdapter(list12);

						lv.setAdapter(adapter);
						setListViewHeight(lv);

					}

				});
		//作品集gv访问网络获取数据
		HttpUtils utils1=new HttpUtils();
		RequestParams params1=new RequestParams();
	
		params1.addBodyParameter("b_id",String.valueOf(bar.getB_id()));
		utils1.send(HttpMethod.POST, Url.url+":8080/Alisi2/ZuoPinJi", params1, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
			String string=responseInfo.result;
			Gson gson=new Gson();
			Type classOfT=new TypeToken<List<String>>(){}.getType();
			List<String> list=gson.fromJson(string, classOfT);
			MyAdapter1 adapter1=new MyAdapter1(list);
			gv.setAdapter(adapter1);
				
			}
		});
		
		
		
		
		
		
		

		// 关注图片的细节处理
		guangzhu = (ImageView) findViewById(R.id.image_guanzhu);
        SharedPreferences sp_login = getSharedPreferences("isFirstLogin",MODE_PRIVATE);
		 hasLogin = sp_login.getBoolean("haslogin",false);
		/* SharedPreferences is_first = getSharedPreferences("isFirstLogin",MODE_PRIVATE);
	     boolean isfirst=    is_first.getBoolean("haslogin",false);*/
        guangzhu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			
              if(hasLogin==false){
            	  
              
				if (flag) {
					Intent intent = new Intent(BarberActivity.this,
							LoginActivity.class);

					startActivityForResult(intent, 1);

					// guangzhu.setImageResource(R.drawable.yiguanzhu);
					flag = false;
					// HttpUtils utils=new HttpUtils();
					// RequestParams params=new RequestParams();
					// params.addBodyParameter("b_id",String.valueOf(bar.getB_id()));

				} else {

					RequestParams params = new RequestParams();
					params.addBodyParameter("b_id",
							String.valueOf(bar.getB_id()));
					params.addBodyParameter("u_id", String.valueOf(u_id));

					HttpUtils http = new HttpUtils();

					http.send(
							HttpMethod.POST,
							Url.url+":8080/Alisi2/deleteCollection",
							params, new RequestCallBack<String>() {

								@Override
								public void onStart() {

								}

								@Override
								public void onFailure(HttpException arg0,
										String arg1) {
									// TODO Auto-generated method stub

								}

								@Override
								public void onSuccess(
										ResponseInfo<String> responseInfo) {
									// TODO Auto-generated method stub

									String flag = responseInfo.result;
									if (flag.equals("true")) {
										Toast.makeText(BarberActivity.this,
												"取消关注成功", Toast.LENGTH_LONG)
												.show();

									} else {
										Toast.makeText(BarberActivity.this,
												"取消关注失败，请重新取消关注",
												Toast.LENGTH_LONG).show();
									}

								}

							});

					guangzhu.setImageResource(R.drawable.guanzhu);

					flag = true;
				}

			}
              else{
            	  Intent notfirstlogin=new Intent(BarberActivity.this,OrderMenu.class);
            	  startActivity(notfirstlogin);
              }
		} 
		}
        
        		
        		);
  
		tv_chakan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				HttpUtils utils1 = new HttpUtils();
				RequestParams params1 = new RequestParams();
				params1.addBodyParameter("currentPage",
						String.valueOf(currentPage));
				params1.addBodyParameter("b_id", String.valueOf(bar.getB_id()));
				utils1.send(HttpMethod.POST,
						Url.url+":8080/Alisi2/ZuoPinJi", params1,
						new RequestCallBack<String>() {

							@Override
							public void onFailure(HttpException arg0,
									String arg1) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onSuccess(
									ResponseInfo<String> responseInfo) {
								String string = responseInfo.result;
								Gson gson = new Gson();
								Type classOfT = new TypeToken<List<String>>() {
								}.getType();
								List<String> list = gson.fromJson(string,
										classOfT);
								MyAdapter1 adapter1 = new MyAdapter1(list);
								gv.setAdapter(adapter1);
                               
							}
						});

				currentPage++;

			}
		});
		desc=( TextView)findViewById(R.id.tv_neirong);
		desc.setText(bar.getB_desc());
	}

	/**
	 * 重新计算ListView的高度，解决ScrollView和ListView两个View都有滚动的效果，在嵌套使用时起冲突的问题
	 * 
	 * @param listView
	 */
	public void setListViewHeight(ListView listView) {

		// 获取ListView对应的Adapter

		ListAdapter listAdapter = listView.getAdapter();

		if (listAdapter == null) {
			return;
		}
		int totalHeight = 0;
		for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0); // 计算子项View 的宽高
			totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == 11) {
			u_id = data.getIntExtra("u_id", 0);
			if (requestCode == 1) {
				guangzhu.setImageResource(R.drawable.yiguanzhu);

				HttpUtils utils = new HttpUtils();
				RequestParams params = new RequestParams();
				params.addBodyParameter("b_id", String.valueOf(bar.getB_id()));
				params.addBodyParameter("u_id", String.valueOf(u_id));

				utils.send(HttpMethod.POST,
						Url.url+":8080/Alisi2/CollectionServlet",
						params, new RequestCallBack<String>() {

							@Override
							public void onFailure(HttpException arg0,
									String arg1) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onSuccess(
									ResponseInfo<String> responseInfo) {
								// TODO Auto-generated method stub
								String flag = responseInfo.result;
								if (flag.equals("true")) {
									Toast.makeText(BarberActivity.this, "关注成功",
											Toast.LENGTH_LONG).show();

								} else {
									Toast.makeText(BarberActivity.this,
											"关注失败，请重新关注", Toast.LENGTH_LONG)
											.show();
								}
							}
						});

			}

		}

	}

	class MyAdapter1 extends BaseAdapter {
		List<String> list;

		public MyAdapter1(List<String> list) {
			this.list = list;
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
			BitmapUtils utils = new BitmapUtils(BarberActivity.this);
			String zuopin = list.get(position);
			if (convertView == null) {
				convertView = LayoutInflater.from(BarberActivity.this).inflate(
						R.layout.zuopin_item, null);
			}
			ImageView img = (ImageView) convertView
					.findViewById(R.id.imageView1);
			utils.display(img, zuopin);
			return convertView;
		}

	}
	public void showAcitonBar(){
		 ActionBar actionBar = getActionBar();  
	       actionBar.setDisplayHomeAsUpEnabled(true);  
	         
	       ActionBar.LayoutParams lp =new ActionBar.LayoutParams(  
	                 ActionBar.LayoutParams.MATCH_PARENT,  
	                 ActionBar.LayoutParams.MATCH_PARENT,  
	                 Gravity.CENTER);  
	       LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
	       View titleView = inflater.inflate(R.layout.action_bar_title, null);  
	       actionBar.setCustomView(titleView, lp);  
	         
	       actionBar.setDisplayShowHomeEnabled(false);//去掉导航  
	       actionBar.setDisplayShowTitleEnabled(false);//去掉标题  
	       actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);  
	       actionBar.setDisplayShowCustomEnabled(true);  
	       TextView tv_title=(TextView) actionBar.getCustomView().findViewById(R.id.title);
	         tv_title.setText("理发师");
	       ImageButton imageBtn = (ImageButton) actionBar.getCustomView().findViewById(R.id.image_btn);  
	         
	       imageBtn.setOnClickListener(new View.OnClickListener() {  
	             
	           @Override  
	           public void onClick(View v) {  
	              finish();
	           
	           }  
	       });  
	}

}
