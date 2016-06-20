package com.atgem.ailisidemo;

import java.io.File;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.Header;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import bean.BarpostItem;
import bean.Url;
import bean.UserPost;

import com.atgem.adapter.MyAdapter;
import com.atgem.googleplay.AllInfoMessageActivity;
import com.atgem.googleplay.OrderMainActivity;
import com.atgem.googleplay.R;
import com.atgem.lunbodemo.HomeActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.heima52.pullrefresh.view.RefreshListView;
import com.heima52.pullrefresh.view.RefreshListView.OnRefreshListener;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.image.SmartImageView;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.MemoryCacheAware;
import com.nostra13.universalimageloader.cache.memory.impl.LRULimitedMemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class WorksActivity extends Activity implements OnClickListener{
	//下面是整合的界面
	private ViewPager vp_attfans;
	private TextView tvattention;
	private TextView tvfans;
	private ListView listView;
	//////
	int n=0;
	private MyAdapter adapter;
	private MyPostAdapter postAdapter;
	private RefreshListView refreshListView;
	private RefreshListView refreshListView2;
	 GridView gv;
	 View view1;
	 View view2;
	List<BarpostItem> barpostItems;
     List<UserPost> userpostItems;
		//  int[] images=new int[]{R.drawable.home,R.drawable.tuijian1,R.drawable.zixun1,R.drawable.yuyue1};
	 int[] images=new int[]{R.drawable.home1,R.drawable.tuijian,R.drawable.zixun1,R.drawable.yuyue1};
	 private Handler handler = new Handler(){
			public void handleMessage(android.os.Message msg) {
				//更新UI
				if(msg.what==0)
				{	adapter.notifyDataSetChanged();
				refreshListView.completeRefresh();}
				else if(msg.what==1){
					System.out.println("个人秀新增数据。。。。。。。。。。。。。。。。。。。");
					postAdapter.notifyDataSetChanged();
					refreshListView2.completeRefresh();
				}
				
			};
		};
	 
		public static DisplayImageOptions mNormalImageOptions;
		public static final String SDCARD_PATH = Environment.getExternalStorageDirectory().toString();
		public static final String IMAGES_FOLDER = SDCARD_PATH + File.separator + "demo" + File.separator + "images" + File.separator;
	 
	 
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zuopin_main);
		getActionBar().hide();
		initImageLoader(this);
		final List<View> views=new ArrayList<View>();
		LayoutInflater inflater=getLayoutInflater();
		 view1=inflater.inflate(R.layout.activity_works, null);
		views.add(view1);
		 view2=inflater.inflate(R.layout.activity_works, null);
		views.add(view2);
		MyAdapterView adapterView=new MyAdapterView(views);

		vp_attfans=(ViewPager) findViewById(R.id.vp_attentoinfanspager);
		vp_attfans.setAdapter(adapterView);
		tvattention=(TextView) findViewById(R.id.tv_attention);
		tvfans=(TextView) findViewById(R.id.tv_fans);
		tvattention.setBackgroundResource(R.drawable.tip_leftoff);
		int color=getResources().getColor(R.color.select);
		tvattention.setTextColor(color);
		tvfans.setBackgroundResource(R.drawable.tip_righton);
		tvfans.setTextColor(getResources().getColor(R.color.unselect));
		View view=views.get(0);
	//	View view2=views.get(1);
		refreshListView = (RefreshListView)view.findViewById(R.id.list_works);
		refreshListView.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onPullRefresh() {
				//需要联网请求服务器的数据，然后更新UI
				requestDataFromServer(true);
			}

			@Override
			public void onLoadingMore() {
				
				requestDataFromServer(true);
			}
		});
		
		refreshListView2= (RefreshListView)view2.findViewById(R.id.list_works);
		refreshListView2.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onPullRefresh() {
				//需要联网请求服务器的数据，然后更新UI
				requestDataFromServer2(true);
			}

			@Override
			public void onLoadingMore() {
				
				requestDataFromServer2(true);
			}
		});
		gv=(GridView)view.findViewById(R.id.gv_news);
		 getBarberPostInfoFromNet();

			
			
			
			
			gv.setAdapter(new GvAdapter());
			 gv.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1, int position,
							long arg3) {
						// TODO Auto-generated method stub
						
						switch (position) {
						case 0:
							Intent intent_home=new Intent(WorksActivity.this,HomeActivity.class);
							startActivity(intent_home);
							
							
							
							break;
		                
		              
		               case 2:
		            	   Intent intent3=new Intent(WorksActivity.this,AllInfoMessageActivity.class);
							startActivity(intent3);
		            	 
			                 
			              break;
		               case 3:
		            	   Intent intent4=new Intent(WorksActivity.this,OrderMainActivity.class);
							startActivity(intent4);
		            	 
			                 
			              break;
						default:
							break;
						}
						
						
						
						
					}
				});
		
		vp_attfans.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				if (position==0) {
					//璁剧疆鍏虫敞琚�涓�
					tvattention.setBackgroundResource(R.drawable.tip_leftoff);
					int color=getResources().getColor(R.color.select);
					tvattention.setTextColor(color);
					tvfans.setBackgroundResource(R.drawable.tip_righton);
					tvfans.setTextColor(getResources().getColor(R.color.unselect));
					View view=views.get(0);
					refreshListView = (RefreshListView)view.findViewById(R.id.list_works);
					refreshListView.setOnRefreshListener(new OnRefreshListener() {
						@Override
						public void onPullRefresh() {
							//需要联网请求服务器的数据，然后更新UI
							requestDataFromServer(true);
						}

						@Override
						public void onLoadingMore() {
							
							requestDataFromServer(true);
						}
					});
					gv=(GridView)view.findViewById(R.id.gv_news);
					 getBarberPostInfoFromNet();

						
						
						
						
						gv.setAdapter(new GvAdapter());
						 gv.setOnItemClickListener(new OnItemClickListener() {

								@Override
								public void onItemClick(AdapterView<?> arg0, View arg1, int position,
										long arg3) {
									// TODO Auto-generated method stub
									
									switch (position) {
									case 0:
										Intent intent_home=new Intent(WorksActivity.this,HomeActivity.class);
										startActivity(intent_home);
										
										
										
										break;
					                
					              
					               case 2:
					            	   Intent intent3=new Intent(WorksActivity.this,AllInfoMessageActivity.class);
										startActivity(intent3);
					            	 
						                 
						              break;
					               case 3:
					            	   Intent intent4=new Intent(WorksActivity.this,OrderMainActivity.class);
										startActivity(intent4);
					            	 
						                 
						              break;
									default:
										break;
									}
									
									
									
									
								}
							});
					
					
					
					
					
				}else if (position==1) {
					//璁剧疆鍏虫敞琚�涓�
					tvfans.setBackgroundResource(R.drawable.tip_rightoff);
					int color=getResources().getColor(R.color.select);
					tvfans.setTextColor(color);
					tvattention.setBackgroundResource(R.drawable.tip_lefton);
					tvattention.setTextColor(getResources().getColor(R.color.unselect));
				
					View view=views.get(1);
					refreshListView2 = (RefreshListView)view.findViewById(R.id.list_works);
					refreshListView2.setOnRefreshListener(new OnRefreshListener() {
						@Override
						public void onPullRefresh() {
							//需要联网请求服务器的数据，然后更新UI
							requestDataFromServer2(true);
						}

						@Override
						public void onLoadingMore() {
							
							requestDataFromServer2(true);
						}
					});
					gv=(GridView)view.findViewById(R.id.gv_news);
					 getSelfPostFromNet();

						
						
						
						
						gv.setAdapter(new GvAdapter());
						 gv.setOnItemClickListener(new OnItemClickListener() {

								@Override
								public void onItemClick(AdapterView<?> arg0, View arg1, int position,
										long arg3) {
									// TODO Auto-generated method stub
									
									switch (position) {
									case 0:
										Intent intent_home=new Intent(WorksActivity.this,HomeActivity.class);
										startActivity(intent_home);
										
										
										
										break;
					                
					              
					               case 2:
					            	   Intent intent3=new Intent(WorksActivity.this,AllInfoMessageActivity.class);
										startActivity(intent3);
					            	 
						                 
						              break;
					               case 3:
					            	   Intent intent4=new Intent(WorksActivity.this,OrderMainActivity.class);
										startActivity(intent4);
					            	 
						                 
						              break;
									default:
										break;
									}
									
									
									
									
								}
							});
					
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				}
				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		


		tvattention.setOnClickListener(this);
		tvfans.setOnClickListener(this);

		
		
		//上面是新家的
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	//	listView=(ListView) findViewById(R.id.list_works);
	/*	refreshListView = (RefreshListView) findViewById(R.id.list_works);
		refreshListView.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onPullRefresh() {
				//需要联网请求服务器的数据，然后更新UI
				requestDataFromServer(true);
			}

			@Override
			public void onLoadingMore() {
				
				requestDataFromServer(true);
			}
		});
		gv=(GridView) findViewById(R.id.gv_news);
		showAcitonBar();*/
		/*String url=Url.url+"10.40.7.71:8080/Alisi2/ShowBarpostServlet";
		AsyncHttpClient client=new AsyncHttpClient();
		client.get(url, new AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				// TODO Auto-generated method stub
			//	Toast.makeText(WorksActivity.this, new String(responseBody),Toast.LENGTH_LONG).show();
			   
				String allInfo=new String(responseBody);
				Log.i("WorksActivity", allInfo);
				Gson gson=new Gson();
				Type typeOfT=new TypeToken<List<BarpostItem>>(){}.getType();
				barpostItems=gson.fromJson(allInfo,typeOfT);
				 adapter=new MyAdapter(barpostItems,WorksActivity.this);
			        
	        	 // adapter.setItems(barpostItems);
	        	//  adapter.notifyDataSetChanged();
	         
		    refreshListView.setAdapter(adapter);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				// TODO Auto-generated method stub
				Toast.makeText(WorksActivity.this, new String(responseBody),Toast.LENGTH_LONG).show();
			}
		});*/
	/*	 getBarberPostInfoFromNet();

		
		
		
	
		gv.setAdapter(new GvAdapter());
		 gv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int position,
						long arg3) {
					// TODO Auto-generated method stub
					
					switch (position) {
					case 0:
						Intent intent_home=new Intent(WorksActivity.this,HomeActivity.class);
						startActivity(intent_home);
						
						
						
						break;
	                
	              
	               case 2:
	            	   Intent intent3=new Intent(WorksActivity.this,AllInfoMessageActivity.class);
						startActivity(intent3);
	            	 
		                 
		              break;
	               case 3:
	            	   Intent intent4=new Intent(WorksActivity.this,OrderMainActivity.class);
						startActivity(intent4);
	            	 
		                 
		              break;
					default:
						break;
					}
					
					
					
					
				}
			});*/
		}

	//192.168.191.3
	 public void downLoad(View view){
		   ////localhost:8080/Alisi2/upload/ee78adcc-f739-40ef-b99e-d8c86847370f.png
		   String path=Url.url+":8080/Alisi2/upload/ee78adcc-f739-40ef-b99e-d8c86847370f.png";
		   SmartImageView siv=(SmartImageView) findViewById(R.id.imageView1);
	           siv.setImageUrl(path);
	          /* ImageView view1=(ImageView)siv;
	           Toast.makeText(this, "success", 0).show();*/
	   
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
				View view=View.inflate(WorksActivity.this,R.layout.gv_item,null);
				if(arg1==null){
					
				
				//  View view=View.inflate(HomeActivity.this,R.layout.gv_item,null);
				  
				  arg1=view;
				}
				ImageView imageView=(ImageView) view.findViewById(R.id.iv_item);
				  imageView.setImageResource(images[arg0]);
				
				
				return view;
			}
			 
		 }
		/**
		 * 模拟向服务器请求数据
		 */
		private void requestDataFromServer(final boolean isLoadingMore){
			new Thread(){
				public void run() {
					SystemClock.sleep(3000);//模拟请求服务器的一个时间长度
					Timestamp a = new Timestamp(new Date().getTime());
					if(isLoadingMore){
					barpostItems.add(new BarpostItem("刘远","小职员",Url.url+":8080/Alisi2/upload/ee78adcc-f739-40ef-b99e-d8c86847370f.png","完美发型！",Url.url+":8080/Alisi2/upload/ee78adcc-f739-40ef-b99e-d8c86847370f.png",a, 5,5,"3"));
				//	barpostItems.add(new BarpostItem(b_name, b_position, b_icon, content, image, time, commentCount, zanCount, belong))
					}else {
					//	barpostItems.add(new );
						barpostItems.add(new BarpostItem("刘远","小职员",Url.url+":8080/Alisi2/upload/ee78adcc-f739-40ef-b99e-d8c86847370f.png","完美发型！",Url.url+":8080/Alisi2/upload/ee78adcc-f739-40ef-b99e-d8c86847370f.png",a, 5,5,"3"));

					}
					
					//在UI线程更新UI
					handler.sendEmptyMessage(0);
				};
			}.start();
		}
		private void requestDataFromServer2(final boolean isLoadingMore){
			new Thread(){
				public void run() {
					SystemClock.sleep(3000);//模拟请求服务器的一个时间长度
					Timestamp a = new Timestamp(new Date().getTime());
					if(isLoadingMore){
					userpostItems.add(new  UserPost(Url.url+":8080/Alisi2/upload/ee78adcc-f739-40ef-b99e-d8c86847370f.png", 1, 1,"李伟",Url.url+":8080/Alisi2/upload/ee78adcc-f739-40ef-b99e-d8c86847370f.png","哈哈哈哈",a));
				//barpostItems.add(new BarpostItem(b_name, b_position, b_icon, content, image, time, commentCount, zanCount, belong))
					}else {
					//	barpostItems.add(new );
				//		barpostItems.add(new BarpostItem("刘远","小职员","://10.40.7.71:8080/Alisi2/upload/ee78adcc-f739-40ef-b99e-d8c86847370f.png","完美发型！","10.40.7.71:8080/Alisi2/upload/9304bb26-7266-4e8f-a8dc-adc9934fcee1.png",a, 5,5,"3"));
						userpostItems.add(new  UserPost(Url.url+":8080/Alisi2/upload/ee78adcc-f739-40ef-b99e-d8c86847370f.png", 1, 1,"李伟",Url.url+":8080/Alisi2/upload/ee78adcc-f739-40ef-b99e-d8c86847370f.png","哈哈哈哈",a));
					}
					
					//在UI线程更新UI
					handler.sendEmptyMessage(1);
				};
			}.start();
		}
		public void getBarberPostInfoFromNet(){
			String url=Url.url+":8080/Alisi2/ShowBarpostServlet";
			AsyncHttpClient client=new AsyncHttpClient();
			client.get(url, new AsyncHttpResponseHandler() {
				
				@Override
				public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
					// TODO Auto-generated method stub
				//	Toast.makeText(WorksActivity.this, new String(responseBody),Toast.LENGTH_LONG).show();
				   
					String allInfo=new String(responseBody);
					Log.i("WorksActivity", allInfo);
					Gson gson=new Gson();
					Type typeOfT=new TypeToken<List<BarpostItem>>(){}.getType();
					barpostItems=gson.fromJson(allInfo,typeOfT);
					 adapter=new MyAdapter(barpostItems,WorksActivity.this);
				        
		        	 // adapter.setItems(barpostItems);
		        	//  adapter.notifyDataSetChanged();
		         
			    refreshListView.setAdapter(adapter);
				}
				
				@Override
				public void onFailure(int statusCode, Header[] headers,
						byte[] responseBody, Throwable error) {
					// TODO Auto-generated method stub
					Toast.makeText(WorksActivity.this, new String(responseBody),Toast.LENGTH_LONG).show();
				}
			});
		}
		public void getSelfPostFromNet(){
			HttpUtils utils=new HttpUtils();
			utils.send(HttpMethod.POST, Url.url+":8080/Alisi2/A", null, new RequestCallBack<String>() {

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
					// TODO Auto-generated method stub
					String rl=responseInfo.result;
					
					Gson gson=new Gson();
					Type classOfT=new TypeToken<List<UserPost>>(){}.getType();
					userpostItems=gson.fromJson(rl, classOfT);
					for(UserPost u:userpostItems){
					System.out.println("用户名是："+u.getU_name());
					}
					 postAdapter=new MyPostAdapter(userpostItems);
					postAdapter.notifyDataSetChanged();
					refreshListView2.setAdapter(postAdapter);
					
				}
			});
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
		         tv_title.setText("作品集");
		       ImageButton imageBtn = (ImageButton) actionBar.getCustomView().findViewById(R.id.image_btn);  
		         
		       imageBtn.setOnClickListener(new View.OnClickListener() {  
		             
		           @Override  
		           public void onClick(View v) {  
		              finish();
		           
		           }  
		       });  
}
		
		class MyAdapterView extends PagerAdapter{

			private List<View> views;

			public MyAdapterView(List<View> views) {
				this.views=views;
			}


			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return views.size();
			}

			@Override
			public boolean isViewFromObject(View view, Object object) {
				// TODO Auto-generated method stub
				return view==object;
			}

			@Override
			public void destroyItem(View container, int position, Object object) {
				ViewPager vpPager=(ViewPager) container;
				View view=views.get(position);
				vpPager.removeView(view);
			}

			@Override
			public Object instantiateItem(View container, int position) {
				View view=views.get(position);
				//
				/*refreshListView = (RefreshListView)view.findViewById(R.id.list_works);
				refreshListView.setOnRefreshListener(new OnRefreshListener() {
					@Override
					public void onPullRefresh() {
						//需要联网请求服务器的数据，然后更新UI
						requestDataFromServer(true);
					}

					@Override
					public void onLoadingMore() {
						
						requestDataFromServer(true);
					}
				});
				gv=(GridView)view.findViewById(R.id.gv_news);
				 getBarberPostInfoFromNet();

					
					
					
					
					gv.setAdapter(new GvAdapter());
					 gv.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> arg0, View arg1, int position,
									long arg3) {
								// TODO Auto-generated method stub
								
								switch (position) {
								case 0:
									Intent intent_home=new Intent(WorksActivity.this,HomeActivity.class);
									startActivity(intent_home);
									
									
									
									break;
				                
				              
				               case 2:
				            	   Intent intent3=new Intent(WorksActivity.this,AllInfoMessageActivity.class);
									startActivity(intent3);
				            	 
					                 
					              break;
				               case 3:
				            	   Intent intent4=new Intent(WorksActivity.this,OrderMainActivity.class);
									startActivity(intent4);
				            	 
					                 
					              break;
								default:
									break;
								}
								
								
								
								
							}
						});*/
			//	showAcitonBar();
				ViewPager vpPager=(ViewPager) container;
				vpPager.addView(view);
				return view;

			}
		}
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id=v.getId();
			switch (id) {
			case R.id.tv_attention:
				vp_attfans.setCurrentItem(0);
				break;

			case R.id.tv_fans:
				vp_attfans.setCurrentItem(1);
				break;
		}
		}
		private void initImageLoader(Context context) {
			int memoryCacheSize = (int) (Runtime.getRuntime().maxMemory() / 5);
			MemoryCacheAware<String, Bitmap> memoryCache;
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
				memoryCache = new LruMemoryCache(memoryCacheSize);
			} else {
				memoryCache = new LRULimitedMemoryCache(memoryCacheSize);
			}

			mNormalImageOptions = new DisplayImageOptions.Builder().bitmapConfig(Config.RGB_565).cacheInMemory(true).cacheOnDisc(true)
					.resetViewBeforeLoading(true).build();

			// This
			// This configuration tuning is custom. You can tune every option, you
			// may tune some of them,
			// or you can create default configuration by
			// ImageLoaderConfiguration.createDefault(this);
			// method.
			ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).defaultDisplayImageOptions(mNormalImageOptions)
					.denyCacheImageMultipleSizesInMemory().discCache(new UnlimitedDiscCache(new File(IMAGES_FOLDER)))
					// .discCacheFileNameGenerator(new Md5FileNameGenerator())
					.memoryCache(memoryCache)
					// .memoryCacheSize(memoryCacheSize)
					.tasksProcessingOrder(QueueProcessingType.LIFO).threadPriority(Thread.NORM_PRIORITY - 2).threadPoolSize(3).build();

			// Initialize ImageLoader with configuration.
			ImageLoader.getInstance().init(config);
		}
		
		
		
		
		
		class MyPostAdapter extends BaseAdapter{
			private List<UserPost> list;
		 public MyPostAdapter(List<UserPost> list) {
				// TODO Auto-generated constructor stub
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
				BitmapUtils bp=new BitmapUtils(WorksActivity.this);
				UserPost up=list.get(position);
				if(convertView==null){
					           convertView= View.inflate(WorksActivity.this, R.layout.worksitem1,null);
				}
				
				ImageView u_icon=(ImageView)convertView.findViewById(R.id.imageView1);
				bp.display(u_icon, up.getU_icon());
				TextView u_name=(TextView)convertView.findViewById(R.id.b_name);
				u_name.setText(up.getU_name());
				TextView u_content=(TextView)convertView.findViewById(R.id.content);
				u_content.setText(up.getContent());
				ImageView image=(ImageView)convertView.findViewById(R.id.imageView2);
			bp.display(image,up.getImage());
				final TextView zan=(TextView)convertView.findViewById(R.id.pariseCount);
				zan.setText(String.valueOf(up.getCountgood()));
				zan.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						if(n==0){
						HttpUtils utils=new HttpUtils();
					 
						
						utils.send(HttpRequest.HttpMethod.POST, Url.url+":8080/Alisi2/CountZanServlet",null,new RequestCallBack<String>() {

							@Override
							public void onFailure(HttpException arg0, String arg1) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void onSuccess(ResponseInfo<String> responseInfo) {
								// TODO Auto-generated method stub
								String flag= responseInfo.result;
							
								if(flag.equals("true")){
									Toast.makeText(WorksActivity.this,"点赞成功",Toast.LENGTH_LONG).show();
									HttpUtils utils=new HttpUtils();
									utils.send(HttpMethod.POST, Url.url+":8080/Alisi2/A", null, new RequestCallBack<String>() {

										@Override
										public void onFailure(HttpException arg0, String arg1) {
											// TODO Auto-generated method stub
											
										}

										@Override
										public void onSuccess(ResponseInfo<String> responseInfo) {
											// TODO Auto-generated method stub
											String rl=responseInfo.result;
											
											Gson gson=new Gson();
											Type classOfT=new TypeToken<List<UserPost>>(){}.getType();
											List<UserPost>list=gson.fromJson(rl, classOfT);
											for(UserPost u:list){
											System.out.println(u.getU_name());
											}
											postAdapter=new MyPostAdapter(list);
										
											refreshListView2.setAdapter(postAdapter);
											postAdapter.notifyDataSetChanged();
										}
									});
										
									
									
									
									
									
									
								}else{
									Toast.makeText(WorksActivity.this,"点赞失败",Toast.LENGTH_LONG).show();
								}
								
							}
						});
						n=1;
						}
						else if(n==1){
							HttpUtils utils=new HttpUtils();
							utils.send(HttpMethod.POST,Url.url+":8080/Alisi2/CancelZan", null, new RequestCallBack<String>() {

								@Override
								public void onFailure(HttpException arg0,
										String arg1) {
									// TODO Auto-generated method stub
									
								}

								@Override
								public void onSuccess(ResponseInfo<String> responseInfo) {
									// TODO Auto-generated method stub
									String f=responseInfo.result;
									if(f.equals("true")){
									Toast.makeText(WorksActivity.this,"取消赞成功",Toast.LENGTH_LONG).show();
									HttpUtils utils=new HttpUtils();
									utils.send(HttpMethod.POST, Url.url+":8080/Alisi2/A", null, new RequestCallBack<String>() {

										@Override
										public void onFailure(HttpException arg0, String arg1) {
											// TODO Auto-generated method stub
											
										}

										@Override
										public void onSuccess(ResponseInfo<String> responseInfo) {
											// TODO Auto-generated method stub
											String rl=responseInfo.result;
											
											Gson gson=new Gson();
											Type classOfT=new TypeToken<List<UserPost>>(){}.getType();
											List<UserPost>list=gson.fromJson(rl, classOfT);
											for(UserPost u:list){
											System.out.println(u.getU_name());
											}
											 postAdapter=new MyPostAdapter(list);
										
											refreshListView2.setAdapter(postAdapter);
											postAdapter.notifyDataSetChanged();
										}
									});
										
									
									
									}else{
										Toast.makeText(WorksActivity.this,"点赞失败",Toast.LENGTH_LONG).show();
									}
								}
							});
							
							n=0;
						}
					}
				});
				TextView time=(TextView)convertView.findViewById(R.id.time);
				time.setText(String.valueOf(up.getTime()));
				
				
				
				
				
				return convertView;
			}
			
		}
		
}
