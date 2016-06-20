package com.atgem.googleplay;











import java.util.ArrayList;
import java.util.List;



import cn.jpush.android.api.JPushInterface;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;




import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;


public class MyReceiver extends BroadcastReceiver {
public static final String TAG="JPush";



	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		List<String>list=new ArrayList<String>();
		  Bundle bundle = intent.getExtras();
	        Log.d(TAG, "onReceive - " + intent.getAction());
	        
	      

	        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
	        }else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
	            System.out.println("�յ����Զ�����Ϣ����Ϣ�����ǣ�" + bundle.getString(JPushInterface.EXTRA_MESSAGE));
	            // �Զ�����Ϣ����չʾ��֪ͨ������ȫҪ������д����ȥ����
	        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
	   
	            System.out.println("�յ���֪ͨ,֪ͨ��������"+ bundle.getString(JPushInterface.EXTRA_ALERT));
	          list.add(bundle.getString(JPushInterface.EXTRA_ALERT));
	            // �����������Щͳ�ƣ�������Щ������
	        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
	            System.out.println("�û��������֪ͨ");
	            // ����������Լ�д����ȥ�����û���������Ϊ
	            Intent i = new Intent(context, Notify.class);  //������͵Ľ�������ĸ�����
	            i.putExtra("a",bundle.getString(JPushInterface.EXTRA_ALERT));
	            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    	            context.startActivity(i);
	        } else {
	            Log.d(TAG, "Unhandled intent - " + intent.getAction());
	  }

	}
	

}
