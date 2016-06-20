package com.atgem.ailisidemo.utils;

import org.json.JSONException;


import org.json.JSONObject;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.StringRequest;
import com.atgem.ailisidemo.activity.MyApplication;



public class Volley_get {
	public static void volley_Get(String url){
		//String url="http://10.40.7.71:8080/Alisi2/LastedVersionInfoServlet";
		StringRequest request=new StringRequest(Method.GET, url, new ResponseListener(),new ResponseErrorListener());
	        request.setTag("getABC");
	    MyApplication.getRequestQueues().add(request);
	}
	//成功时调用
	static class ResponseListener implements Response.Listener<String>{

		@Override
		public void onResponse(String arg0) {
			// TODO Auto-generated method stub
		
		
			
			
		} 
			  
		}
		
	}
	//
	class ResponseErrorListener implements Response.ErrorListener{

		@Override
		public void onErrorResponse(VolleyError arg0) {
			// TODO Auto-generated method stub
		
		
		  
		}
		
	}

