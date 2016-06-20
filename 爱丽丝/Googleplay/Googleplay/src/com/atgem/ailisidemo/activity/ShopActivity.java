package com.atgem.ailisidemo.activity;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import bean.OrderShop;
import bean.Url;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.atgem.googleplay.R;
import com.atgem.googleplay.ShopInfoActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.BitmapUtils;

public class ShopActivity extends Activity implements OnClickListener,OnItemClickListener {
	private  ListView lv;
	private RequestQueue rq;
	 private	View root;
	   private PopupWindow popupWindow;
	   private ListView lvPop;
	   private ImageView tip;
	   private TextView tv_project;
	   private TextView tv_sort;
	   private int n=1;
	   private boolean hasSort;
	   private  SimpleAdapter popAdapter;
	   private ShopAdapter  shopAdapter;
	   Type classOfT;
	   List<OrderShop> list;
	   private int count=0;//请求队列的序号
	
	class ShopAdapter extends BaseAdapter{
		private List<OrderShop> list1;
		public ShopAdapter(List<OrderShop> list){
			list1=list;
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
			final BitmapUtils bmUtils = new BitmapUtils(ShopActivity.this);
			 OrderShop  shop=list1.get(position);
			if(convertView==null){
				convertView=View.inflate(ShopActivity.this, R.layout.shop,null);
			}
			ImageView iView=(ImageView) convertView.findViewById(R.id.imageView1);
			bmUtils.display(iView,shop.getS_icon());
			TextView tv=(TextView)convertView.findViewById(R.id.textView4);
			tv.setText(String.valueOf(shop.getS_distance())+"km");
			TextView tv1=(TextView)convertView.findViewById(R.id.textView2);
			tv1.setText(String.valueOf(shop.getCount_comment())+"条");//
			
			TextView tv2=(TextView)convertView.findViewById(R.id.textView3);
			
			tv2.setText(String.valueOf(shop.getCount_order())+"人预约过");
			TextView tv3=(TextView)convertView.findViewById(R.id.textView1);
			tv3.setText(shop.getS_name());
			
			return convertView;
		}
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		   showAcitonBar();
		
		
		lv=(ListView)findViewById(R.id.listView1);
		 root=View.inflate(this, R.layout.popup,null);
		 lvPop=(ListView)root.findViewById(R.id.lv_projects);
		
	    popupWindow = new PopupWindow(root, LayoutParams.MATCH_PARENT,  
	                LayoutParams.WRAP_CONTENT);  
	       popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg));  
	        popupWindow.setOutsideTouchable(true);  
	        popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);  
	        popupWindow.setBackgroundDrawable(new ColorDrawable());
	       
	        popupWindow.setTouchable(true);  
	      //  popupWindow.setFocusable(true); 
		
		  
		 String projectname=getIntent().getStringExtra("projects");
		tv_project=(TextView)findViewById(R.id.projects);
		tv_project.setText(projectname);
		tv_sort=(TextView) findViewById(R.id.sort);
		tv_project.setOnClickListener(this);
		tv_sort.setOnClickListener(this);
		
		rq = Volley.newRequestQueue(this);// ����http������� �Է��͵�http�����Ŷ�
	       volley_Get(Url.url+":8080/Alisi2/ShowAllShopInfoServlet");
     
	       
	     lv.setOnItemClickListener(this);  
	}
	public void volley_Get(String url){
		//localhost:8080/Alisi2/ShowAllShopInfoServlet
		//String url="10.40.7.71:8080/Alisi2/ShowAllShopInfoServlet";
		StringRequest request=new StringRequest(Method.GET, url, new ResponseListener(),new ResponseErrorListener());
	        request.setTag("getABC"+count++);
	    MyApplication.getRequestQueues().add(request);
	}
	//
	class ResponseListener implements Response.Listener<String>{

		@Override
		public void onResponse(String arg0) {
			// TODO Auto-generated method stub
			Log.i("MainActivity",arg0);
			Gson g=new Gson();
			String json=arg0;
		
			
			 classOfT=new TypeToken<List<OrderShop>>(){}.getType();
		 list=g.fromJson(json, classOfT);//ת����list����
		
		 shopAdapter=new ShopAdapter(list);
		
		lv.setAdapter(shopAdapter);
		shopAdapter.notifyDataSetChanged();
		
		
			  
		}
		
	}
	//ʧ�ܺ���ô˷���
	class ResponseErrorListener implements Response.ErrorListener{

		@Override
		public void onErrorResponse(VolleyError arg0) {
			// TODO Auto-generated method stub
		          Log.i("ShopActicity","请求发生错误....");
			
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
	          
	        ImageButton imageBtn = (ImageButton) actionBar.getCustomView().findViewById(R.id.image_btn);  
	          
	        imageBtn.setOnClickListener(new View.OnClickListener() {  
	              
	            @Override  
	            public void onClick(View v) {  
	               finish();
	            
	            }  
	        });  
	}
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		
		switch (view.getId()) {
		
		
		case R.id.projects:
			  if (n==1) { 
				tip=(ImageView) findViewById(R.id.tip3);
				tip.setImageResource(R.drawable.below);
				tv_sort.setTextColor(Color.BLACK);
				hasSort=false;
			
				 popAdapter = new SimpleAdapter(this, getData(),   
			                R.layout.popupwindowitem,  
			                new String[] { "text" },  
			                new int[] { R.id.item }); 
				
				// ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.item,arrays);
				  lvPop.setAdapter(popAdapter);
				  lvPop.setOnItemClickListener(new OnItemClickListener() {
		               /**
		                * 给popListView 增加点击事件！！！！！
		                */
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1, int position,
							long arg3) {
						// TODO Auto-generated method stub
						
					    TextView tvItem=(TextView) arg1.findViewById(R.id.item);
					   // Toast.makeText(ShopActivity.this,tvItem.getText(),0).show();
						volley_Get(Url.url+":8080/Alisi2/ShowShopInfoByDistanceServlet");
						//shopAdapter.notifyDataSetChanged();
						Toast.makeText(ShopActivity.this, "已经修改了..",0).show();
						popupWindow.dismiss();
					}
				});
				
				
               
			popupWindow.showAsDropDown(view,0, 0);//(view, Gravity.CENTER, 0, 0);
			//Toast.makeText(this,"111",Toast.LENGTH_LONG).show();
			tip=(ImageView) findViewById(R.id.tip2);
			tip.setImageResource(R.drawable.abc_ic_go_search_api_holo_light2);
			tv_project.setTextColor(Color.RED);
			n=(n+1)%2;
			
			
			  } else{
				   popupWindow.dismiss();
				  tip=(ImageView) findViewById(R.id.tip2);
					tip.setImageResource(R.drawable.below);
					tv_project.setTextColor(Color.BLACK);
					
					
					n=(n+1)%2;
			  }
			  break;
		case R.id.sort:
			if(!hasSort)
				
			{
				
				//popupWindow.dismiss();
				tip=(ImageView) findViewById(R.id.tip2);
			    tip.setImageResource(R.drawable.below);
			    tv_project.setTextColor(Color.BLACK);
			    n=1;
			    popAdapter = new SimpleAdapter(this, getDataSort(),   
		                R.layout.popupwindowitem,  
		                new String[] { "text" },  
		                new int[] { R.id.item }); 
	
			         System.out.println(getDataSort());
			     lvPop.setAdapter(popAdapter);
			     popupWindow.showAsDropDown(view,0, 0);//(view, Gravity.CENTER, 0, 0);
					//Toast.makeText(this,"111",Toast.LENGTH_LONG).show();
					tip=(ImageView) findViewById(R.id.tip3);
					tip.setImageResource(R.drawable.abc_ic_go_search_api_holo_light2);
					tv_sort.setTextColor(Color.RED);
					hasSort=true;
			}
		 else{
						 
			 popupWindow.dismiss();
			  tip=(ImageView) findViewById(R.id.tip3);
				tip.setImageResource(R.drawable.below);
				tv_sort.setTextColor(Color.BLACK);
			 
			 hasSort=false;
			 
					}
		
			break;

		default:
			break;
		}
		
		
		
	}
	 private List<Map<String, String>> getData() {  
	        List<Map<String, String>> list = new ArrayList<Map<String, String>>();  
	  
	        Map<String, String> map = new HashMap<String, String>();  
	        map.put("text", "所有项目");  
	        list.add(map);  
	  
	        map = new HashMap<String, String>();  
	        map.put("text", "洗剪吹");  
	        list.add(map);  
	  
	        map = new HashMap<String, String>();  
	        map.put("text", "烫发");  
	        list.add(map);  
	  
	        map = new HashMap<String, String>();  
	        map.put("text", "染发");  
	        list.add(map); 
	        map = new HashMap<String, String>();  
	        map.put("text", "造型");  
	        list.add(map); 
	        return list;  
	    }  
	 private List<Map<String, String>> getDataSort() {  
	        List<Map<String, String>> list = new ArrayList<Map<String, String>>();  
	  
	        Map<String, String> map = new HashMap<String, String>();  
	        map.put("text", "智能排序");  
	        list.add(map);  
	  
	        map = new HashMap<String, String>();  
	        map.put("text", "离我最近");  
	        list.add(map);  
	  
	        map = new HashMap<String, String>();  
	        map.put("text", "人气最高");  
	        list.add(map);  
	  
	       
	        return list;  
	    }
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		  OrderShop orderShop = list.get(position);
		  Intent intent=new Intent(this,ShopInfoActivity.class);
		 Bundle bundle=new Bundle();
		 bundle.putSerializable("shop",orderShop);
		
		 intent.putExtras(bundle);
		 System.out.println("理发店的简介是:"+orderShop.getS_desc());
		 startActivity(intent);
		  
		  
		  
		 
		  
	}  
}
