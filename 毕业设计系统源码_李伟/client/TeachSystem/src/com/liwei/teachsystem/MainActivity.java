package com.liwei.teachsystem;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.liwei.activity.PersonCenterActivity;
import com.liwei.application.MyApplation;
import com.liwei.fragment.BroadCastFragment;
import com.liwei.fragment.MainFragment;
import com.liwei.fragment.PersonCenterFragment;
import com.liwei.utils.ToastUtils;
import com.liwei.utils.UrlUtils;

public class MainActivity extends Activity implements OnClickListener{
	private MainFragment mainFragment=null;
	private PersonCenterFragment personCenterFragment=null;
	private BroadCastFragment broadCastFragment=null;
    private LinearLayout mainLinearLayout=null;
    private LinearLayout broadcastLinearLayout=null;
    private LinearLayout shareLayout=null;
    private LinearLayout personcenterLinearLayout=null;
    private  SharedPreferences sPreferences;
    private   SharedPreferences sharedPreferences;
    private ImageButton homeIcon;
    private ImageButton  shareIcon;
    private ImageButton personCenterIcon;
    private ImageButton BroadcstIcon;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		MyApplation.setActivity(this);
		
		initView();
		unSelect();
		homeIcon.setImageResource(R.drawable.home_select);
		getFragmentManager().beginTransaction().show(mainFragment).commit();
		
		
	}
	
	
	public void initView() {
		homeIcon=(ImageButton) findViewById(R.id.id_tab_weixin_img);
		shareIcon=(ImageButton) findViewById(R.id.id_tab_address_img);
		personCenterIcon=(ImageButton) findViewById(R.id.id_tab_settings_img);
		BroadcstIcon=(ImageButton) findViewById(R.id.id_tab_frd_img);
		
		
		 sPreferences=getSharedPreferences("teacheruser",Context.MODE_PRIVATE);
		 sharedPreferences=getSharedPreferences("studentuser",Context.MODE_PRIVATE);
		
		if(mainLinearLayout==null){
		mainLinearLayout=(LinearLayout)findViewById(R.id.id_tab_main);
		mainLinearLayout.setOnClickListener(this);
		}
		
		if(shareLayout==null){
			shareLayout=(LinearLayout)findViewById(R.id.id_tab_share);
			shareLayout.setOnClickListener(this);
		}
		if(personcenterLinearLayout==null){
			personcenterLinearLayout=(LinearLayout)findViewById(R.id.id_tab_personcenter);
			personcenterLinearLayout.setOnClickListener(this);
		}
		if(broadcastLinearLayout==null){
			broadcastLinearLayout=(LinearLayout)findViewById(R.id.id_tab_broadcast);
			broadcastLinearLayout.setOnClickListener(this);
		}
	if(mainFragment==null){
		mainFragment=new MainFragment();
		getFragmentManager().beginTransaction().add(R.id.id_viewpager, mainFragment).commit();
		getFragmentManager().beginTransaction().hide(mainFragment).commit();
		
	}

if(personCenterFragment==null){
	personCenterFragment=new PersonCenterFragment();
	getFragmentManager().beginTransaction().add(R.id.id_viewpager, personCenterFragment).commit();
	getFragmentManager().beginTransaction().hide(personCenterFragment).commit();
}

if(broadCastFragment==null){
	broadCastFragment=new BroadCastFragment();
	getFragmentManager().beginTransaction().add(R.id.id_viewpager, broadCastFragment).commit();
	getFragmentManager().beginTransaction().hide(broadCastFragment).commit();
}
	}
	@Override
	protected void onResume() {
//CheckNetUtils.isCheckNetWorkConnection(MainActivity.this);

		super.onResume();
	}

	@Override
	public void onClick(View v) {
		int id=v.getId();
		switch (id) {
		case R.id.id_tab_main:
			unSelect();
			homeIcon.setImageResource(R.drawable.home_select);
			showFragment(mainFragment);
			
			break;
			case R.id.id_tab_personcenter:
				unSelect();
				personCenterIcon.setImageResource(R.drawable.tabbar_profile_selected);
				String username=sPreferences.getString("tusername", "");
				String susername=sharedPreferences.getString("susername", "");
				if(username.equals("")&&susername.equals("")){
					Toast.makeText(MainActivity.this, "亲，请您先登录", Toast.LENGTH_LONG).show();
				}else if(!username.equals("")){
					Intent intent=new Intent(MainActivity.this,PersonCenterActivity.class);
					intent.putExtra("tno", username);
					intent.putExtra("age", "1");
					startActivity(intent);
				}else if(!susername.equals("")){
					Intent intent=new Intent(MainActivity.this,PersonCenterActivity.class);
					intent.putExtra("age", "2");
					intent.putExtra("sno", susername);
					startActivity(intent);
				//showFragment(personCenterFragment);
				}
				
				System.out.println("sucess");
				break;
			case R.id.id_tab_broadcast:
				unSelect();
				BroadcstIcon.setImageResource(R.drawable.tabbar_message_center_selected);
				String username1=sPreferences.getString("tusername", "");
				if(!username1.equals("")){
					HttpUtils utils=new HttpUtils();
				RequestParams params=new RequestParams();
				params.addBodyParameter("TeacherUser", username1);
				Toast.makeText(MainActivity.this,username1, Toast.LENGTH_LONG).show();
				utils.send(HttpMethod.POST, UrlUtils.url+"/GetRongToken", params, new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						
						
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						String token=responseInfo.result;
						connect(token);
						//Toast.makeText(MainActivity.this,token, Toast.LENGTH_LONG).show();
						RongIM.getInstance().startPrivateChat(MainActivity.this, "20160511", "");
						/*RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {

						    @Override
						    public UserInfo getUserInfo(String userId) {

						        return ;//根据 userId 去你的用户系统里查询对应的用户信息返回给融云 SDK。
						    }

						}, true);*/
						
					}
				});
					
				}
				
				String susername1=sharedPreferences.getString("susername", "");
				if(!susername1.equals("")){
					HttpUtils utils=new HttpUtils();
					RequestParams params=new RequestParams();
					params.addBodyParameter("studentUser", susername1);
					utils.send(HttpMethod.POST, UrlUtils.url+"/GetRongToken", params, new RequestCallBack<String>() {

						@Override
						public void onFailure(HttpException arg0, String arg1) {
							
							
						}

						@Override
						public void onSuccess(ResponseInfo<String> responseInfo) {
							String token=responseInfo.result;
							//Toast.makeText(MainActivity.this,token, Toast.LENGTH_LONG).show();
							connect(token);
							RongIM.getInstance().startPrivateChat(MainActivity.this, "20120510", "呜呜呜呜呜呜");
							
						}
					});
					
				}
				if(susername1.equals("")&&username1.equals("")){
			ToastUtils.getToast(MainActivity.this, "亲，您还没有登录呢");
				}
				System.out.println("sucess");
				break;
		default:
			break;
		}
		
	}
	/**
	 * 显示Fragment
	 * @param f 需要显示的Fragment
	 */
public void showFragment(Fragment f){
	if(mainFragment!=null){
		getFragmentManager().beginTransaction().hide(mainFragment).commit();
		
	}


if(personCenterFragment!=null){
	getFragmentManager().beginTransaction().hide(personCenterFragment).commit();
}

if(broadCastFragment!=null){
	getFragmentManager().beginTransaction().hide(broadCastFragment).commit();
}
	getFragmentManager().beginTransaction().show(f).commit();
	System.out.println("sucess");
}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	if(keyCode==KeyEvent.KEYCODE_BACK){
		new AlertDialog.Builder(MainActivity.this)
		.setTitle("退出")
		.setMessage("你确定要退出吗")
		.setPositiveButton("确定",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				/**
				 * 清除教师登录信息
				 */
				
				System.out.println(sPreferences.getString("tusername", ""));
				Editor editor=sPreferences.edit();
				editor.clear();
				editor.commit();
				/**
				 * 清除学生登录信息
				 */
				
				
				Editor editor1=sharedPreferences.edit();
				editor1.clear();
				editor1.commit();
		List<Activity>list=MyApplation.getActivity();
		for(Activity activity:list){
			activity.finish();
		}
				
			}
		}
			
		
		)
		.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				
			}
		}).create().show();
		return true;
	}
		return super.onKeyDown(keyCode, event);
	}
	
	
	/**
	 * 建立与融云服务器的连接
	 *
	 * @param token
	 */
	private void connect(String token) {
		if (getApplicationInfo().packageName.equals(MyApplation.getCurProcessName(getApplicationContext()))) {

	  
	        /**
	         * IMKit SDK调用第二步,建立与服务器的连接
	         */
	        RongIM.connect(token, new RongIMClient.ConnectCallback() {

	            /**
	             * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
	             */
	            @Override
	            public void onTokenIncorrect() {

	                Log.d("LoginActivity", "--onTokenIncorrect");
	            }

	            /**
	             * 连接融云成功
	             * @param userid 当前 token
	             */
	            @Override
	            public void onSuccess(String userid) {
	            	
	            
	            }

	            /**
	             * 连接融云失败
	             * @param errorCode 错误码，可到官网 查看错误码对应的注释
	             */
	            @Override
	            public void onError(RongIMClient.ErrorCode errorCode) {

	                Log.d("LoginActivity", "--onError" + errorCode);
	            }
	        });
		}
	    
	}
	
	/**
	 * 将图片设置为未选中状态
	 */
	
	public void unSelect(){
		homeIcon.setImageResource(R.drawable.home_unselect);
		shareIcon.setImageResource(R.drawable.tabbar_share);
	 personCenterIcon.setImageResource(R.drawable.tabbar_message_center);
	   BroadcstIcon.setImageResource(R.drawable.tabbar_message_center);
		
	}
}
