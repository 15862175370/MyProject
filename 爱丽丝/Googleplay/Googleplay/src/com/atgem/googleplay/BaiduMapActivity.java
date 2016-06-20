package com.atgem.googleplay;


import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.Toast;

public class BaiduMapActivity extends Activity {
	 MapView mMapView = null; 
	 private BaiduMap mBaiduMap;
	 //定位相关
	 private LocationClient mLocationClient;
	 private MyLocationListener mLocationListener;
      private boolean isFirstIn= true;
      Context context;
      private double mLatitude;
  	private double mLongtitude;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 //在使用SDK各组件之前初始化context信息，传入ApplicationContext  
        //注意该方法要再setContentView方法之前实现  
        SDKInitializer.initialize(getApplicationContext()); 
		setContentView(R.layout.activity_baidu_map);
		 //获取地图控件引用  
      //  mMapView = (MapView) findViewById(R.id.bmapView);  
		this.context=this;
      initView();
      initLocation();
	}
	private void initView()
	{
		mMapView = (MapView) findViewById(R.id.bmapView);
		mBaiduMap = mMapView.getMap();
		MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15.0f);
		mBaiduMap.setMapStatus(msu);
		
	}
	private void initLocation(){
		mLocationClient=new LocationClient(this);
		mLocationListener=new MyLocationListener();
		mLocationClient.registerLocationListener(mLocationListener);
		
		LocationClientOption option=new LocationClientOption();
		option.setCoorType("bd09ll");
		option.setIsNeedAddress(true);
		option.setOpenGps(true);
		option.setScanSpan(1000);
		mLocationClient.setLocOption(option);
	}

	   @Override
	    protected void onStart() {
	    	// TODO Auto-generated method stub
	    	super.onStart();
	    	mBaiduMap.setMyLocationEnabled(true);
	    	if(!mLocationClient.isStarted())
	    	  mLocationClient.start();
	    }
	  @Override  
	    protected void onDestroy() {  
	        super.onDestroy();  
	        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理  
	        mMapView.onDestroy();  
	    }  
	    @Override  
	    protected void onResume() {  
	        super.onResume();  
	        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理  
	        mMapView.onResume();  
	        }  
	    @Override  
	    protected void onPause() {  
	        super.onPause();  
	        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理  
	        mMapView.onPause();  
	        }  
	    @Override
	    protected void onStop() {
	    	// TODO Auto-generated method stub
	    	super.onStop();
	    	//停止定位
	    	mBaiduMap.setMyLocationEnabled(false);
	    	mLocationClient.stop();
	    }
	    class MyLocationListener implements BDLocationListener{

			@Override
			public void onReceiveLocation(BDLocation location) {
				// TODO Auto-generated method stub
				
				MyLocationData data=new MyLocationData.Builder()//
				.accuracy(location.getRadius())//
				.latitude(location.getLatitude() )//
				.longitude(location.getLongitude()).build();
				mBaiduMap.setMyLocationData(data);
				
				if(isFirstIn){
					LatLng latLng=new LatLng(location.getLatitude(),location.getLatitude());
					
					MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
				  mBaiduMap.animateMapStatus(msu);
				  isFirstIn=false;
				  
				Toast.makeText(context, location.getAddrStr(),Toast.LENGTH_LONG).show();
				
				}
				
			}

	    }
}
