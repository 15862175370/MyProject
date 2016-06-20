package com.atgem.ailisidemo.activity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import bean.FixBarber;
import bean.FreeTime;
import bean.OrderShop;
import bean.ServcePrice;
import bean.Url;

import com.atgem.googleplay.ConfirmOrderActivity;
import com.atgem.googleplay.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class OrderMenu extends Activity {
	private GridView gv;
	private TextView today;
	
	private TextView tomorrow;
	private TextView aftertomorrow;
	// private TextView tv;
	private boolean flag;
	int number=1;
	List<TextView> tvListl = new ArrayList<TextView>();
	private TextView submit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.reservetime_activity);
		  SharedPreferences is_first = getSharedPreferences("isFirstLogin",MODE_PRIVATE);
		     boolean isfirst=    is_first.getBoolean("haslogin",false);
		     System.out.println("是否登陆过:"+isfirst);
		  gv = (GridView) findViewById(R.id.gridView1);
		 today = (TextView) findViewById(R.id.tv_today);
		today.setBackgroundColor(getResources().getColor(R.color.select));
           submit=(TextView) findViewById(R.id.tv_tijiao);
         /*  submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				
				Toast.makeText(OrderMenu.this,"dianji",Toast.LENGTH_SHORT).show();
				// TODO Auto-generated method stub
				Intent intent=new Intent(OrderMenu.this,ConfirmOrderActivity.class);
        		FixBarber barber=(FixBarber)getIntent().getSerializableExtra("bar");
        		OrderShop   shop=	(OrderShop)  getIntent().getSerializableExtra("shop");
        		ServcePrice sPrice=(ServcePrice) getIntent().getSerializableExtra("price");
        		Bundle bundle=new Bundle();
				bundle.putSerializable("bar", barber);
				bundle.putSerializable("shop",shop);
			    bundle.putSerializable("price", sPrice);
				intent.putExtras(bundle);
				System.out.println(shop.getS_icon());
				startActivity(intent);
				
			}
		});*/
           
		tomorrow = (TextView) findViewById(R.id.tv_tomorrow);

		tomorrow.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(OrderMenu.this,
						TomorrowOrderMenu.class);
				startActivity(intent);

			}
		});

		aftertomorrow = (TextView) findViewById(R.id.tv_aftertomorrow);
		aftertomorrow.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(OrderMenu.this,
						AfterTomorrowMenu.class);
				startActivity(intent);

			}
		});

		final List<String> list = new ArrayList<String>();
		list.add("10:00");
		list.add("10:30");
		list.add("11:00");
		list.add("11:30");
		list.add("12:00");
		list.add("12:30");
		list.add("13:00");
		list.add("13:30");
		list.add("14:00");
		list.add("14:30");
		list.add("15:00");
		list.add("15:30");
		list.add("16:00");
		list.add("16:30");
		list.add("17:00");
		list.add("17:30");
		list.add("18:00");
		list.add("18:30");
		list.add("19:00");
		list.add("19:30");
		list.add("20:00");
		list.add("20:30");

		class MyAdapter extends BaseAdapter {

			private List<FreeTime> list1;
			

			public MyAdapter(List<FreeTime> list2) {
				this.list1 = list2;

				
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return list1.size();
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
			final	TextView tv;
				
				String string = list.get(position);
				 final FreeTime fTime;
				fTime = list1.get(position);

				if (convertView == null) {
					convertView = View.inflate(OrderMenu.this,
							R.layout.reservetime_items_activity, null);
				}

		   tv = (TextView) convertView.findViewById(R.id.tv_time);
				
				tv.setText(string);

			
				// 1表示没有空闲时间，0表示有空闲时间！！！！！
				if (fTime.getState().equals("1")) {
					tv.setBackgroundColor(getResources().getColor(R.color.grey));

				} else if (fTime.getState().equals("0")) {
				
					tv.setBackgroundColor(getResources()
							.getColor(R.color.white));
					  tvListl.add(tv);
							
							tv.setOnClickListener(new OnClickListener() {
								
								@Override
								public void onClick(View v) {
									
									tv.setBackgroundColor(getResources().getColor(R.color.select));
									for(TextView t:tvListl){
										if(t!=tv){
											t.setBackgroundColor(getResources()
													.getColor(R.color.white));
										}
									}
									
									submit.setOnClickListener(new OnClickListener() {
										
										@Override
										public void onClick(View v) {
											
									final String time=tv.getText().toString();//获得预约时间段
									String day=today.getText().toString();//哪一天
									//将订单信息存起来，留着支付成功后修改理发师空闲时间！！
									  SharedPreferences spMyInfo=getSharedPreferences("MyOrderInfo",MODE_PRIVATE);
									 SharedPreferences.Editor editormyorderinfo=spMyInfo.edit();
									editormyorderinfo.putString("time",time);
									editormyorderinfo.putString("day",day);
									editormyorderinfo.commit();
									FixBarber fixbarber=(FixBarber)getIntent().getSerializableExtra("bar");
									int b_id=fixbarber.getB_id();//哪一个理发师
									System.out.println("理发师的id是："+b_id);
									Intent intent=new Intent(OrderMenu.this,ConfirmOrderActivity.class);
									FixBarber barber=(FixBarber)getIntent().getSerializableExtra("bar");
					        		OrderShop   shop=	(OrderShop)  getIntent().getSerializableExtra("shop");
					        		ServcePrice sPrice=(ServcePrice) getIntent().getSerializableExtra("price");
					        		Bundle bundle=new Bundle();
									bundle.putSerializable("bar", barber);
									bundle.putSerializable("shop",shop);
								    bundle.putSerializable("price", sPrice);
								    bundle.putString("time",time);
									intent.putExtras(bundle);
									System.out.println(shop.getS_icon());
									startActivity(intent);
									finish();
									HttpUtils utils=new HttpUtils();
									/*RequestParams params=new RequestParams();
									params.addBodyParameter("time", time);
									params.addBodyParameter("day", day);
									params.addBodyParameter("b_id", String.valueOf(b_id));
									utils.send(HttpMethod.POST, "10.202.1.99:8080/Alisi2/OrderBarberServlet", params,new RequestCallBack<String>() {

										@Override
										public void onFailure(HttpException arg0,
												String arg1) {
											// TODO Auto-generated method stub
											
										}

										@Override
										public void onSuccess(ResponseInfo<String> responseInfo) {
										String flag=responseInfo.result;
										if(flag.equals("true")){
											Toast.makeText(OrderMenu.this, "提交成功", Toast.LENGTH_LONG).show();
											Intent intent=new Intent(OrderMenu.this,ConfirmOrderActivity.class);
											FixBarber barber=(FixBarber)getIntent().getSerializableExtra("bar");
							        		OrderShop   shop=	(OrderShop)  getIntent().getSerializableExtra("shop");
							        		ServcePrice sPrice=(ServcePrice) getIntent().getSerializableExtra("price");
							        		Bundle bundle=new Bundle();
											bundle.putSerializable("bar", barber);
											bundle.putSerializable("shop",shop);
										    bundle.putSerializable("price", sPrice);
										    bundle.putString("time",time);
											intent.putExtras(bundle);
											System.out.println(shop.getS_icon());
											startActivity(intent);
											finish();
											
										}else{
											Toast.makeText(OrderMenu.this, "提交失败", Toast.LENGTH_LONG).show();
										}
											
										}
									});*/
											
										}
									});
								}
							});
							
							
						}
					

				
					
					
				

				return convertView;

			}

		}

		HttpUtils utils = new HttpUtils();
		utils.send(HttpMethod.POST,
				Url.url+":8080/Alisi2/FreeTimeServlet", null,//://localhost:8080/Alisi2/FreeTimeServlet
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						String timeString = responseInfo.result;
						Gson gson = new Gson();
						Type classOfT = new TypeToken<List<FreeTime>>() {
						}.getType();
						List<FreeTime> list1 = gson.fromJson(timeString,
								classOfT);

						MyAdapter adapter = new MyAdapter(list1);

						gv.setAdapter(adapter); 
					}
				});

		// ΪGridView��Ŀ���ü����¼�
		/*gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View arg1,
					int position, long arg3) {
				TextView tv = tvListl.get(position + 3);
				switch (position) {
				case 0:
					for (TextView tv1 : tvListl) {
						if (tv1.equals("0")) {
							tv.setBackgroundColor(getResources().getColor(
									R.color.white));
						}
					}
					tv.setBackgroundColor(getResources().getColor(
							R.color.select));

					break;
				case 1:

					for (TextView tv1 : tvListl) {
						if (tv1.equals("0")) {
							tv.setBackgroundColor(getResources().getColor(
									R.color.white));
						}
					}

					tv.setBackgroundColor(getResources().getColor(
							R.color.select));

					break;
				case 2:

					for (TextView tv1 : tvListl) {
						if (tv1.equals("0")) {
							tv.setBackgroundColor(getResources().getColor(
									R.color.white));
						}
					}

					tv.setBackgroundColor(getResources().getColor(
							R.color.select));

					break;
				case 3:
					for (TextView tv1 : tvListl) {
						if (tv1.equals("0")) {
							tv.setBackgroundColor(getResources().getColor(
									R.color.white));
						}
					}

					tv.setBackgroundColor(getResources().getColor(
							R.color.select));

					break;
				case 4:
					for (TextView tv1 : tvListl) {
						if (tv1.equals("0")) {
							tv.setBackgroundColor(getResources().getColor(
									R.color.white));
						}
					}

					tv.setBackgroundColor(getResources().getColor(
							R.color.select));

					break;
				case 5:
					for (TextView tv1 : tvListl) {
						if (tv1.equals("0")) {
							tv.setBackgroundColor(getResources().getColor(
									R.color.white));
						}
					}

					tv.setBackgroundColor(getResources().getColor(
							R.color.select));

					break;
				case 6:
					tv.setBackgroundColor(getResources().getColor(
							R.color.select));

					break;
				case 7:
					tv.setBackgroundColor(getResources().getColor(
							R.color.select));

					break;
				case 8:
					tv.setBackgroundColor(getResources().getColor(
							R.color.select));

					break;
				case 9:
					tv.setBackgroundColor(getResources().getColor(
							R.color.select));

					break;
				case 10:

					tv.setBackgroundColor(getResources().getColor(
							R.color.select));

					break;
				case 11:

					tv.setBackgroundColor(getResources().getColor(
							R.color.select));

					break;
				case 12:

					tv.setBackgroundColor(getResources().getColor(
							R.color.select));

					break;
				case 13:
					tv.setBackgroundColor(getResources().getColor(
							R.color.select));

					break;
				case 14:

					tv.setBackgroundColor(getResources().getColor(
							R.color.select));

					break;
				case 15:

					tv.setBackgroundColor(getResources().getColor(
							R.color.select));

					break;
				case 16:

					tv.setBackgroundColor(getResources().getColor(
							R.color.select));

					break;
				case 17:*/
/*					tv.setBackgroundColor(getResources().getColor(
							R.color.select));

					break;
				case 18:
					tv.setBackgroundColor(getResources().getColor(
							R.color.select));

					break;
				case 19:
					for (TextView tv1 : tvListl) {
						if (tv1.equals("0")) {
							tv.setBackgroundColor(getResources().getColor(
									R.color.white));
						}
					}

					tv.setBackgroundColor(getResources().getColor(
							R.color.select));

					break;
				case 20:

					for (TextView tv1 : tvListl) {
						if (tv1.equals("0")) {
							tv.setBackgroundColor(getResources().getColor(
									R.color.white));
						}
					}

					tv.setBackgroundColor(getResources().getColor(
							R.color.select));

					break;
				case 21:

					for (TextView tv1 : tvListl) {
						if (tv1.equals("0")) {
							tv.setBackgroundColor(getResources().getColor(
									R.color.white));
						}
					}

					tv.setBackgroundColor(getResources().getColor(
							R.color.select));

					break;
				case 22:
					for (TextView tv1 : tvListl) {
						if (tv1.equals("0")) {
							tv.setBackgroundColor(getResources().getColor(
									R.color.white));
						}
					}

					tv.setBackgroundColor(getResources().getColor(
							R.color.select));

					break;
				default:
					break;
				}

			}
		});*/

	}

}

