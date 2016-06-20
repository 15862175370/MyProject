package com.atgem.lunbodemo;

import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import bean.PostActivity;
import bean.Url;

import com.atgem.adapter.ActivityAdapter;
import com.atgem.adapter.GridViewAdapter;
import com.atgem.ailisidemo.WorksActivity;
import com.atgem.ailisidemo.activity.MyApplication;
import com.atgem.ailisidemo.activity.ShopActivity;
import com.atgem.googleplay.AllInfoMessageActivity;
import com.atgem.googleplay.CollectActivity;
import com.atgem.googleplay.DetailActivity;
import com.atgem.googleplay.OrderMainActivity;
import com.atgem.googleplay.R;
import com.atgem.googleplay.SecondActivity;
import com.atgem.googleplay.SettingActivity;
import com.atgem.googleplay.TopOrderActivity;
import com.atgem.googleplay.UserShowActivity;
import com.atgem.googleplay.VersionActivity;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.location.Poi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.heima52.slidemenu.view.SlideMenu;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.image.SmartImageView;

public class HomeActivity extends ActionBarActivity implements OnQueryTextListener{

	 private static final String LOG_TAG = "MainActivity";  
	    ImageHandler handler = new ImageHandler(new WeakReference<HomeActivity>(this));//将activity放入弱引用中  
	    ViewPager viewPager; 
	    GridView gv;
	    GridView gv_kinds;
	    ListView lv;
	    ActivityAdapter actAdapter;
	    private ImageView btn_back;
		private SlideMenu slideMenu;
		String result;
		TextView tv_name;
		TextView tv_dingwei;
		 private LocationClient locationClient;
	   int[] images=new int[]{R.drawable.home,R.drawable.tuijian1,R.drawable.zixun1,R.drawable.yuyue1};
	   int[] kinds=new int[]{R.drawable.wcc,R.drawable.tang,R.drawable.ran,R.drawable.zaoxing};
	   @Override  
	    protected void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);  
	        setContentView(R.layout.slidemain); 
	        showAcitonBar();
	        SharedPreferences sp=getSharedPreferences("userInfo",MODE_PRIVATE);
	        tv_name=(TextView) findViewById(R.id.tv_name);
	        tv_name.setText("用户编号:"+sp.getInt("u_id",001)+"");
	        locationClient=MyApplication.getLocationClient();
			locationClient.registerLocationListener(locListener);
	        //��ʼ��iewPager������  
	        getActionBar().setDisplayHomeAsUpEnabled(true);
	        viewPager = (ViewPager) findViewById(R.id.main_viewpager); 
	        gv=(GridView) findViewById(R.id.gv);
	        gv_kinds=(GridView) findViewById(R.id.gv_kinds);
	        lv=(ListView) findViewById(R.id.lv_activity);
	       // btn_back = (ImageView) findViewById(R.id.btn_back);
			slideMenu = (SlideMenu) findViewById(R.id.slideMenu);
			
			/*btn_back.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					slideMenu.switchMenu();
				}
			});*/
	       LayoutInflater inflater = LayoutInflater.from(this);  
	        View view =inflater.inflate(R.layout.item, null); 
	        SmartImageView image1=(SmartImageView) view.findViewById(R.id.image1);
	      
	        image1.setImageUrl(Url.url+":8080/Alisi2/images/lunbo1.png");
	       // ImageView view1=(ImageView) view.findViewById(R.id.image);
	        SmartImageView view2=(SmartImageView) view.findViewById(R.id.image2);
	        view2.setImageUrl(Url.url+":8080/Alisi2/images/lunbo2.png");
	        
	        SmartImageView view3=(SmartImageView) view.findViewById(R.id.image3);
	        view3.setImageUrl(Url.url+":8080/Alisi2/images/lunbo3.png");
	       // ImageView view2=new ImageView(this);
	        //ImageView view3=new ImageView(this);
	       // ImageView view2 = (ImageView) inflater.inflate(R.layout.item, null);  
	       // ImageView view3 = (ImageView) inflater.inflate(R.layout.item, null);  
	        //view1.setImageResource(R.drawable.image1);  
	       // view2.setImageResource(R.drawable.image2);  
	       // view3.setImageResource(R.drawable.image3);  
	        ArrayList<ImageView> views = new ArrayList<ImageView>();  
	        views.add(image1);  
	        views.add(view2);  
	        views.add(view3);  
	        viewPager.setAdapter(new ImageAdapter(views));  
	        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {  
	               
	            //���Adapter��currentItem�ֶν������á�  
	            @Override  
	            public void onPageSelected(int arg0) {//第几个界面被选中时  
	                handler.sendMessage(Message.obtain(handler, ImageHandler.MSG_PAGE_CHANGED, arg0, 0)); //handle what int arg0  int arg1
	               //此方法必掉
	                //msg.arg1 = 1;
	        		//msg.arg2 = 2;
	        		//msg.what = 3;
	        		//msg.obj = Object;
	            }  
	               
	            @Override  
	            public void onPageScrolled(int arg0, float arg1, int arg2) {  
	            }  
	               
	            //��д�÷���ʵ���ֲ�Ч�����ͣ�ͻָ�  
	            @Override  
	            public void onPageScrollStateChanged(int arg0) {  
	            	
	                switch (arg0) {  
	                case ViewPager.SCROLL_STATE_DRAGGING:  //手动滑动停止轮播
	                    handler.sendEmptyMessage(ImageHandler.MSG_KEEP_SILENT);  
	                    break;  
	                case ViewPager.SCROLL_STATE_IDLE:  //闲置轮播
	                    handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);  
	                    break;  
	                default:  
	                    break;  
	                }  
	            } 
	        });  
	        viewPager.setCurrentItem(Integer.MAX_VALUE/2);//默认在中间，使用户看不到边界

	       /* viewPager.setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View arg0, MotionEvent arg1) {
					// TODO Auto-generated method stub
					System.out.println("事件被拦截！！！！！");
					//
				
					
					
					
					
					
					
					
					
					
					
					
					
					return true;
				}
			});*/
	        
	        //��ʼ�ֲ�Ч��  
	        handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);  
	      gv.setAdapter(new GvAdapter());
	      gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				
				switch (position) {
			
                 case 1:
					
					Intent intent=new Intent(HomeActivity.this,WorksActivity.class);
					startActivity(intent);
					
					break;
               case 2:
            		Intent intent3=new Intent(HomeActivity.this,AllInfoMessageActivity.class);
					startActivity(intent3);
              
	                    
	              break;
               case 3:
            		
            	 Intent orderMain=new Intent(HomeActivity.this,OrderMainActivity.class);
            	 startActivity(orderMain);
	
	              break;
				default:
					break;
				}
				
				
				
				
			}
		});
	      gv_kinds.setAdapter(new GridViewAdapter(HomeActivity.this,kinds));
	      gv_kinds.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					Intent xijianchui=new Intent(HomeActivity.this,ShopActivity.class);
					   
					   xijianchui.putExtra("projects","洗剪吹");
					
					
					
					startActivity(xijianchui);
					
					
					break;
                 case 1:
					
					Intent intent=new Intent(HomeActivity.this,ShopActivity.class);
					  intent.putExtra("projects","烫");
					startActivity(intent);
					
					break;
               case 2:
            	   Intent intent2=new Intent(HomeActivity.this,ShopActivity.class);
            	   intent2.putExtra("projects","染");
					startActivity(intent2);
            	 
	
	              break;
               case 3:
            	   Intent intent3=new Intent(HomeActivity.this,ShopActivity.class);
            	   intent3.putExtra("projects","造型");
					startActivity(intent3);
            	 
	
	              break;
				default:
					break;
				
				
				
				}
		}});
	      getActivityDate();
	    /*  TextView tv_version=(TextView) findViewById(R.id.versioncode);
	      
	      tv_version.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent version=new Intent(HomeActivity.this,VersionActivity.class);
				
				startActivity(version);
				
			}
		});*/
	      TextView tv_selfInfo=(TextView) findViewById(R.id.selfInfo);
	      tv_selfInfo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent self=new Intent(HomeActivity.this,SettingActivity.class);
				startActivity(self);
			}
		});
	      TextView tv_collect=(TextView) findViewById(R.id.mycollect);
                       tv_collect.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
		
							Intent selfcollect=new Intent(HomeActivity.this,CollectActivity.class);
							startActivity(selfcollect);
							
						}
					});
           TextView tv_selfpost=(TextView) findViewById(R.id.selfpost);
           tv_selfpost.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent selfpost=new Intent(HomeActivity.this,UserShowActivity.class);
				startActivity(selfpost);
			}
		});
          
	      lv.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			// TODO Auto-generated method stub
			switch (position) {
			case 0:
				Intent intent1=new Intent(HomeActivity.this,TopOrderActivity.class);
				startActivity(intent1);
				break;
			case 1:
				Intent intent2=new Intent(HomeActivity.this,SecondActivity.class);
				startActivity(intent2);
				break;

			default:
				break;
			}
			
			
			
		}
	});
	      
	    }//end of onCreate  
	    
/*
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.main, menu);
			//getactionview
			SearchView searchView=(SearchView)menu.findItem(R.id.action_search).getActionView();
			searchView.setOnQueryTextListener(this);
		View view=menu.findItem(R.id.gps).getActionView();
		  tv_dingwei=(TextView) view.findViewById(R.id.dingwei);
			tv_dingwei.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					initLoctionOption();
					locationClient.start();//默认发起1次请求
					
				}
			});
			
			
			return true;
		}*/
	     
		
	  

		@Override
		public boolean onQueryTextChange(String arg0) {
			// TODO Auto-generated method stub
			Toast.makeText(this,arg0,Toast.LENGTH_SHORT).show();
			return true;
		}

		@Override
		public boolean onQueryTextSubmit(String arg0) {
			Toast.makeText(this,arg0,Toast.LENGTH_SHORT).show();
			// TODO Auto-generated method stub
			return true;
		}
		public void click(View view){
			Intent intent=new Intent(this,DetailActivity.class);
			startActivity(intent);
		}
	 class GvAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return images.length;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return images[arg0];
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			View view=View.inflate(HomeActivity.this,R.layout.gv_item,null);
			if(arg1==null){
				
			
			//  View view=View.inflate(HomeActivity.this,R.layout.gv_item,null);
			  
			  arg1=view;
			}
			ImageView imageView=(ImageView) view.findViewById(R.id.iv_item);
			  imageView.setImageResource(images[arg0]);
			
			
			return view;
		}
		 
	 }
	 public void getActivityDate(){
		 String url=Url.url+":8080/Alisi2/ShowPostActivityServlet";
			AsyncHttpClient client=new AsyncHttpClient();
			client.get(url, new AsyncHttpResponseHandler() {
				
				@Override
				public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
					// TODO Auto-generated method stub
				//	Toast.makeText(WorksActivity.this, new String(responseBody),Toast.LENGTH_LONG).show();
				   
					String allInfo=new String(responseBody);
					Log.i("WorksActivity", allInfo);
					Gson gson=new Gson();
					Type typeOfT=new TypeToken<List<PostActivity>>(){}.getType();
					List<PostActivity> ActivityItems=gson.fromJson(allInfo,typeOfT);
					 actAdapter=new ActivityAdapter(HomeActivity.this,ActivityItems);
				        
		        	 // adapter.setItems(barpostItems);
		        	//  adapter.notifyDataSetChanged();
		         
			    lv.setAdapter(actAdapter);
			    System.out.println("WHY!!!!!");
				}
				
				@Override
				public void onFailure(int statusCode, Header[] headers,
						byte[] responseBody, Throwable error) {
					// TODO Auto-generated method stub
					Toast.makeText(HomeActivity.this, new String(responseBody),Toast.LENGTH_LONG).show();
				}
			});

	 }
		private BDLocationListener locListener=new BDLocationListener()
		{
			StringBuilder sb=new StringBuilder();
			@Override
			public void onReceiveLocation(BDLocation location) {
	          /*  sb.append("当前时间 : ");
				sb.append(location.getTime());
				
				sb.append("\n定位类型 : ");
				sb.append(location.getLocType());
				
				sb.append("\n纬度 : ");
				sb.append(location.getLatitude());
				sb.append("\n精度 : ");
				sb.append(location.getLongitude());
				sb.append("\n定位半径 : ");
				sb.append(location.getRadius());*/
				
				if (location.getLocType() == BDLocation.TypeNetWorkLocation) 
				{
					// 网络定位结果
				//	sb.append("\n国 省 市 区.. : ");
					sb.append(location.getAddrStr());
					// 运营商信息
				//	sb.append("\n网络定位结果 :");
				//	sb.append("定位成功");
				} 
			/*	sb.append("\n简略地址信息 : ");
				sb.append(location.getLocationDescribe());
				
				List<Poi> list = location.getPoiList();// 附近信息
				if (list != null)
				{
					sb.append("\npoilist size = : ");
					sb.append(list.size());
					for (Poi p : list)
					{
						sb.append("\npoi= : ");
						sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
					}
				}*/
	           Log.i("result:",sb.toString());
	           
	          result=sb.toString().substring(5, 7);
	          tv_dingwei.setText(result);
			}
			
		};
	 

		private void initLoctionOption()
		{
			LocationClientOption locOption=new LocationClientOption();
			//net loc
			locOption.setLocationMode(LocationMode.Battery_Saving);
			locOption.setCoorType("gcj02");// 定位结果坐标系
			locOption.setScanSpan(0);//定位请求的时间间隔，定位一次
			locOption.setIsNeedAddress(true);//设置是否需要地址信息
			locOption.setIsNeedLocationDescribe(true);//简单位置描述
			locOption.setIsNeedLocationPoiList(true);
			locOption.setIgnoreKillProcess(true);
			locationClient.setLocOption(locOption);	
		}
		@Override
		protected void onStop() {
			super.onStop();
			locationClient.stop();
		}
		public void showAcitonBar(){
			 ActionBar actionBar = getActionBar();  
		        actionBar.setDisplayHomeAsUpEnabled(true);  
		          
		        ActionBar.LayoutParams lp =new ActionBar.LayoutParams(  
		                  ActionBar.LayoutParams.MATCH_PARENT,  
		                  ActionBar.LayoutParams.MATCH_PARENT,  
		                  Gravity.CENTER);  
		        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
		        View titleView = inflater.inflate(R.layout.home_actionbar, null);  
		        actionBar.setCustomView(titleView, lp);  
		          
		        actionBar.setDisplayShowHomeEnabled(false);//去掉导航  
		        actionBar.setDisplayShowTitleEnabled(false);//去掉标题  
		        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);  
		        actionBar.setDisplayShowCustomEnabled(true);  
		          
		        ImageView imageBtn = (ImageView) actionBar.getCustomView().findViewById(R.id.image_btn);  
		          
		        imageBtn.setOnClickListener(new View.OnClickListener() {  
		              
		            @Override  
		            public void onClick(View v) {  
		               
		            	 slideMenu.switchMenu();
		            }  
		        });  
				  tv_dingwei=(TextView)actionBar.getCustomView().findViewById(R.id.dingwei);

		        ImageView icon=(ImageView)actionBar.getCustomView().findViewById(R.id.dingweiicon);
				icon.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						initLoctionOption();
						locationClient.start();//默认发起1次请求
						tv_dingwei.setVisibility(View.VISIBLE);
					}
				});
		}
		
		
	 
	}//end of MainActivity 

