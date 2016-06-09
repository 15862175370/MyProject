package com.liwei.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
	public static void getToast(Context context,String message){
		Toast.makeText(context, message, Toast.LENGTH_LONG).show();
		
	}

}
