package com.atgem.googleplay;

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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import bean.FixBarber;
import bean.OrderShop;
import bean.ServcePrice;
import bean.Url;

import com.atgem.ailisidemo.activity.OrderMenu;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class PayActivity extends Activity {
	private TextView tvTotal;
	private TextView tvPay;
	private Button submit;
  private FixBarber barber;
  SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pay);
		tvTotal = (TextView) findViewById(R.id.hasmoney);
		tvPay = (TextView) findViewById(R.id.needpay);
		submit = (Button) findViewById(R.id.submit);
		tvTotal.setText(getSharedPreferences("userInfo", MODE_PRIVATE).getInt(
				"money", 0)
				+ "");

	 barber = (FixBarber) getIntent().getSerializableExtra("bar");
		OrderShop shop = (OrderShop) getIntent().getSerializableExtra("shop");
		final ServcePrice sPrice = (ServcePrice) getIntent()
				.getSerializableExtra("price");
		Bundle bundle = new Bundle();
		bundle.putSerializable("bar", barber);
		bundle.putSerializable("shop", shop);
		bundle.putSerializable("price", sPrice);
		tvPay.setText(sPrice.getPrice() + "");
		showAcitonBar();
		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				int u_id = getSharedPreferences("userInfo", MODE_PRIVATE)
						.getInt("u_id", 0);
				System.out.println("用户的id是:" + u_id);
				/*
				 * HttpUtils http = new HttpUtils();
				 * http.send(HttpRequest.HttpMethod.GET,
				 * "Url.url+"10.202.1.52:8080/Alisi2/UpdateUserMoneyServlet?u_id="
				 * +u_id, new RequestCallBack<String>(){
				 * 
				 * 
				 * @Override public void onSuccess(ResponseInfo<String>
				 * responseInfo) {
				 * Toast.makeText(PayActivity.this,"支付成功",Toast.LENGTH_SHORT
				 * ).show();
				 * 
				 * 
				 * }
				 * 
				 * 
				 * @Override public void onFailure(HttpException error, String
				 * msg) {
				 * 
				 * 
				 * Toast.makeText(PayActivity.this,"支付失败",Toast.LENGTH_SHORT).show
				 * (); } });
				 */
				RequestParams params = new RequestParams();
				params.addHeader("name", "value");
				params.addQueryStringParameter("name", "value");

				// 只包含字符串参数时默认使用BodyParamsEntity，
				// 类似于UrlEncodedFormEntity（"application/x-www-form-urlencoded"）。
				params.addBodyParameter("u_id", String.valueOf(u_id));
				params.addBodyParameter("money",
						String.valueOf(sPrice.getPrice()));

				// 加入文件参数后默认使用MultipartEntity（"multipart/form-data"），
				// 如需"multipart/related"，xUtils中提供的MultipartEntity支持设置subType为"related"。
				// 使用params.setBodyEntity(httpEntity)可设置更多类型的HttpEntity（如：
				// MultipartEntity,BodyParamsEntity,FileUploadEntity,InputStreamUploadEntity,StringEntity）。
				// 例如发送json参数：params.setBodyEntity(new
				// StringEntity(jsonStr,charset));

				HttpUtils http = new HttpUtils();
				http.send(
						HttpRequest.HttpMethod.POST,
						Url.url+":8080/Alisi2/UpdateUserMoneyServlet",
						params, new RequestCallBack<String>() {

							@Override
							public void onSuccess(
									ResponseInfo<String> responseInfo) {
								//在此付款后更新理发师空闲时间
								 sp=getSharedPreferences("MyOrderInfo",MODE_PRIVATE);
								
								HttpUtils utils=new HttpUtils();
								RequestParams params=new RequestParams();
								params.addBodyParameter("time",sp.getString("time","0"));
								params.addBodyParameter("day", sp.getString("day","today"));
								params.addBodyParameter("b_id", String.valueOf(barber.getB_id()));
								utils.send(HttpMethod.POST, Url.url+":8080/Alisi2/OrderBarberServlet", params,new RequestCallBack<String>() {

									@Override
									public void onFailure(HttpException arg0,
											String arg1) {
										// TODO Auto-generated method stub
										
									}

									@Override
									public void onSuccess(ResponseInfo<String> responseInfo) {
									String flag=responseInfo.result;
									if(flag.equals("true")){
										Toast.makeText(PayActivity.this,"修改理发师空闲时间成功",Toast.LENGTH_SHORT).show();
										/*Intent intent=new Intent(PayActivity.this,ConfirmOrderActivity.class);
										FixBarber barber=(FixBarber)getIntent().getSerializableExtra("bar");
						        		OrderShop   shop=	(OrderShop)  getIntent().getSerializableExtra("shop");
						        		ServcePrice sPrice=(ServcePrice) getIntent().getSerializableExtra("price");
						        		Bundle bundle=new Bundle();
										bundle.putSerializable("bar", barber);
										bundle.putSerializable("shop",shop);
									    bundle.putSerializable("price", sPrice);
									    bundle.putString("time",sp.getString("time","0"));
										intent.putExtras(bundle);
										System.out.println(shop.getS_icon());
										startActivity(intent);
										finish();*/
										
									}else{
										Toast.makeText(PayActivity.this, "修改理发师空闲时间失败", Toast.LENGTH_LONG).show();
									}
										
									}
								});
								
								
								
								// 更新前台用户所拥有的钱数！！！

								Toast.makeText(PayActivity.this, "支付成功",
										Toast.LENGTH_SHORT).show();

								Intent intent = new Intent(PayActivity.this,
										PaySucessActivity.class);

								FixBarber barber = (FixBarber) getIntent()
										.getSerializableExtra("bar");
								OrderShop shop = (OrderShop) getIntent()
										.getSerializableExtra("shop");
								ServcePrice sPrice = (ServcePrice) getIntent()
										.getSerializableExtra("price");
								Bundle bundle = new Bundle();
								bundle.putSerializable("bar", barber);
								bundle.putSerializable("shop", shop);
								bundle.putSerializable("price", sPrice);
								System.out.println(shop.getS_name());
								intent.putExtras(bundle);
								SharedPreferences spUser = getSharedPreferences(
										"userInfo", MODE_PRIVATE);
								SharedPreferences.Editor edit = spUser.edit();

								edit.putInt("money", spUser.getInt("money", 0)
										- sPrice.getPrice());
								edit.commit();

								startActivity(intent);
								finish();

							}

							@Override
							public void onFailure(HttpException error,
									String msg) {
								Toast.makeText(PayActivity.this, "支付失败",
										Toast.LENGTH_SHORT).show();

							}
						});

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
		tv_title.setText("支付");
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
