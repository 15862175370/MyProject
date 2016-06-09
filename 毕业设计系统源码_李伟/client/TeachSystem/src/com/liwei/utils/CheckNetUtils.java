package com.liwei.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;


public class CheckNetUtils {
public static void isCheckNetWorkConnection(Context context){
	ConnectivityManager connectivityManager = (ConnectivityManager) context  
			.getSystemService(Context.CONNECTIVITY_SERVICE);//获取系统的连接服务  
			NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();//获取网络的连接情况  
			if(activeNetInfo.getType()==ConnectivityManager.TYPE_MOBILE){  
			//判断3G网 
				 State state = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();  
				  if(State.CONNECTED==state){  
					  ToastUtils.getToast(context, "移动网络已连接");
				  }  else{
					  ToastUtils.getToast(context, "移动网络没有连接");
				  }
			}else if(activeNetInfo.getType()==ConnectivityManager.TYPE_WIFI) {  
			//判断wifi网 
				 State	state = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();  
				  if(State.CONNECTED==state){  
				 ToastUtils.getToast(context, "wifi已连接");
				  } else{
					  ToastUtils.getToast(context, "wifi没有连接");
				  }
			}  
	
}
}
