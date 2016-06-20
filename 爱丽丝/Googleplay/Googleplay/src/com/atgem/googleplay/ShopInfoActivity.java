package com.atgem.googleplay;

import java.lang.reflect.Type;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import bean.FixBarber;
import bean.OrderShop;
import bean.Url;

import com.android.volley.RequestQueue;
import com.baidu.navisdk.util.common.SysOSAPI;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class ShopInfoActivity extends Activity implements OnItemClickListener{



	private OrderShop shop;
	private ImageView iv;
	private TextView tv2;
	private TextView tv3;
	private TextView tv4;
	private ListView lv1;
	private RequestQueue rq;
	private TextView tv5;
	private TextView s_desc;
	   private ClickItem item;
	
	
	
	class MyAdapter extends BaseAdapter{
		private List<FixBarber> list1;
		
public MyAdapter(List<FixBarber> list){
	this.list1=list;
	
}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list1.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list1.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			FixBarber barber=list1.get(position);
			
			BitmapUtils utils=new BitmapUtils(ShopInfoActivity.this);
			if(convertView==null){
		convertView=View.inflate(ShopInfoActivity.this, R.layout.barbershop_barinforitem_activity,null);
			}
			ImageView img=(ImageView)convertView.findViewById(R.id.imageView1);
			utils.display(img, barber.getB_icon());
		TextView tv1=	(TextView)convertView.findViewById(R.id.textView1);
		tv1.setText(barber.getB_name());
		TextView tv2=	(TextView)convertView.findViewById(R.id.textView2);
		tv2.setText(barber.getB_position());
		
		TextView tv3=	(TextView)convertView.findViewById(R.id.textView3);
		tv3.setText(barber.getB_desc());
			return convertView;
		}
		
	}
	
	
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.barbershop_activity);
	
	shop=(OrderShop)getIntent().getSerializableExtra("shop");
	showAcitonBar();
	s_desc=(TextView) findViewById(R.id.textView8);
	s_desc.setText(shop.getS_desc());
	String id=String.valueOf(shop.getS_id());
	final BitmapUtils bmUtils = new BitmapUtils(ShopInfoActivity.this);
iv=(ImageView)findViewById(R.id.imageView1);
bmUtils.display(iv,shop.getS_icon());
/*tv2=(TextView)findViewById(R.id.textView2);
tv2.setText("预约过"+String.valueOf(shop.getCount_order())+"人");*/
tv3=(TextView)findViewById(R.id.textView3);
tv3.setText(String.valueOf(shop.getCount_comment())+"人评价过");
/*tv4=(TextView)findViewById(R.id.textView4);
tv4.setText(shop.getS_name());*/

ImageView iv3=(ImageView) findViewById(R.id.imageView3);//地图图标
iv3.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		 Intent intent=new Intent(ShopInfoActivity.this,MapOverThing.class);
		 Bundle bundle=new Bundle();
		 bundle.putSerializable("shop",shop);
		 intent.putExtras(bundle);
    	startActivity(intent);
		
	}
});
	lv1=(ListView)findViewById(R.id.listView1);
	
	lv1.setOnItemClickListener(this);
	
	
	
	
	RequestParams params = new RequestParams();
	params.addHeader("name", "value");
	params.addQueryStringParameter("name", "value");

	// 只包含字符串参数时默认使用BodyParamsEntity，
	// 类似于UrlEncodedFormEntity（"application/x-www-form-urlencoded"）。
	params.addBodyParameter("id", id);

	// 加入文件参数后默认使用MultipartEntity（"multipart/form-data"），
	// 如需"multipart/related"，xUtils中提供的MultipartEntity支持设置subType为"related"。
	// 使用params.setBodyEntity(httpEntity)可设置更多类型的HttpEntity（如：
	// MultipartEntity,BodyParamsEntity,FileUploadEntity,InputStreamUploadEntity,StringEntity）。
	// 例如发送json参数：params.setBodyEntity(new StringEntity(jsonStr,charset));
	HttpUtils http = new HttpUtils();
	http.send(HttpMethod.POST,
			Url.url+":8080/Alisi2/FindBarberByShopIdServlet",
	    params,
	    new RequestCallBack<String>() {

	     

			@Override
	        public void onStart() {
	        	
	        }

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(ShopInfoActivity.this,"网络加载错误", 0).show();
			}

			@Override
			public void onSuccess(ResponseInfo<String>  responseInfo) {
				// TODO Auto-generated method stub
				
				String json=responseInfo.result;
				
				Gson g=new Gson();
				Type classOfT=new TypeToken<List<FixBarber>>(){}.getType();
				List<FixBarber> list12=g.fromJson(json, classOfT);
				 item=new ClickItem(list12);
				for(FixBarber b:list12){
					System.out.println(b.getS_addr()+""+b.getCountPerson()+b.getB_name());
				}
				FixBarber fixBarber=list12.get(0);
				TextView tv1=(TextView)findViewById(R.id.textView1);
				tv1.setText("员工"+String.valueOf(fixBarber.getCountPerson())+"人");
				TextView v2=(TextView)findViewById(R.id.textView2);
				v2.setText(fixBarber.getS_addr());
				MyAdapter adapter=new MyAdapter(list12);
				lv1.setAdapter(adapter);
				
			}

	});
	
	
	
	/*final Gson g=new Gson();
	

	
	
	
	JsonArrayRequest jArrayRequest=new JsonArrayRequest(Method.POST,"Url.url+"10.40.7.142:8080/WebProject/FindBarberByShopIdServlet",null, new Listener<JSONArray>() {

		@Override
		public void onResponse(JSONArray array) {
			// TODO Auto-generated method stub
			String json=array.toString();
			Type classOfT=new TypeToken<List<Barber>>(){}.getType();
			List<Barber> list12=g.fromJson(json, classOfT);
			for(Barber b:list12){
				System.out.println(b.getB_name());
			}
		
		
			


			
		}
	}, new ErrorListener() {

		@Override
		public void onErrorResponse(VolleyError error) {
			// TODO Auto-generated method stub
		
			
		}
	});
	
	
	
rq.add(jArrayRequest);*/





}


@Override
public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	// TODO Auto-generated method stub
	switch (parent.getId())
	{
	case R.id.listView1:
		
		item.expressItemClick(position);//position 代表你点的哪一个
		break;
}
	
	
}


public class ClickItem{
	private List<FixBarber> list2;
	public ClickItem( List<FixBarber> list2){
		this.list2=list2;
		
	}
public void expressItemClick(int position) {
	// TODO Auto-generated method stssub
      FixBarber	bar=list2.get(position);
	
  
   

	
	Intent intent=new Intent();
	Bundle bundle = new Bundle();  
    bundle.putSerializable("bar", bar); 
    bundle.putSerializable("shop", shop);
	intent.setClass(ShopInfoActivity.this,BarberActivity.class);
	

	 intent.putExtras(bundle);  
	 System.out.println(shop.getS_name());
       
	    startActivity(intent);
	
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
         tv_title.setText(shop.getS_name());
       ImageButton imageBtn = (ImageButton) actionBar.getCustomView().findViewById(R.id.image_btn);  
         
       imageBtn.setOnClickListener(new View.OnClickListener() {  
             
           @Override  
           public void onClick(View v) {  
              finish();
           
           }  
       });  
}
}
