package com.atgem.googleplay;



import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.sax.StartElementListener;

public class MyOrientationListener implements SensorEventListener{
private SensorManager mSensorManager;
private Context context;
private Sensor mSrnsor;
private float lastX;
public  MyOrientationListener(Context context) {
	// TODO Auto-generated constructor stub
	this.context=context;
}
public void start(){//��ʼ����
	mSensorManager=(SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
	if(mSensorManager!=null){
		//��÷��򴫸���
		mSrnsor=mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
	}
	if(mSrnsor!=null){
		mSensorManager.registerListener(this, mSrnsor,mSensorManager.SENSOR_DELAY_UI);
		
		
	}
	
}
public void stop(){//�������
	mSensorManager.unregisterListener(this);
	
}
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		if(event.sensor.getType()==Sensor.TYPE_ORIENTATION){
			float x=event.values[SensorManager.DATA_X];
			if(Math.abs(x-lastX)>1.0){
				
			}
			if(mOnOrientationListener!=null){
				mOnOrientationListener.OnOrientationChanged(lastX);
			}
			lastX=x;
		}
		
		
	}
	
public interface OnOrientationListener{
	void OnOrientationChanged(float x);
	
}
private OnOrientationListener mOnOrientationListener;
public void setmOnOrientationListener(
		OnOrientationListener mOnOrientationListener) {
	this.mOnOrientationListener = mOnOrientationListener;
}

}
