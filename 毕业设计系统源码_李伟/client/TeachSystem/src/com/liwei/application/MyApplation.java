package com.liwei.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.rong.imkit.RongIM;


import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

public class MyApplation extends Application {

public static  List<Activity>list ;
public static String reply="回复";
	
	@Override
	public void onCreate() {
		System.out.println("Applaction");
		// TODO Auto-generated method stub
		super.onCreate();
		list=new ArrayList<Activity>();
	     /**
         * OnCreate 会被多个进程重入，这段保护代码，确保只有您需要使用 RongIM 的进程和 Push 进程执行了 init。
         * io.rong.push 为融云 push 进程名称，不可修改。
         */
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext())) ||
                "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {

            /**
             * IMKit SDK调用第一步 初始化
             */
            RongIM.init(this);
        }
    }

    /**
     * 获得当前进程的名字
     *
     * @param context
     * @return 进程号
     */
    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
    
    public static  void setActivity(Activity activity){
    	list.add(activity);
    	
    }
   public static  List<Activity> getActivity(){
	   return list;
   }
	

}
