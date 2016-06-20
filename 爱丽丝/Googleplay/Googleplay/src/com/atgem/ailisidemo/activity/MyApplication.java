package com.atgem.ailisidemo.activity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.baidu.location.LocationClient;

import android.app.Application;

public class MyApplication extends Application {
	private static RequestQueue requestQueues;
	public static LocationClient locationClient;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		//ע��˴���applicationContext
		requestQueues=Volley.newRequestQueue(getApplicationContext());
		locationClient=new LocationClient(this);
	}

	public static RequestQueue getRequestQueues() {
		return requestQueues;
	}
         public static LocationClient getLocationClient(){
        	 return locationClient;
         }
	
     
}
