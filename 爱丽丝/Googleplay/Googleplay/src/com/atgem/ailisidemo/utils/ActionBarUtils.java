package com.atgem.ailisidemo.utils;

import com.atgem.googleplay.R;

import android.app.ActionBar;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;



public class ActionBarUtils {
    public View showCustomActionBar(Context context,ActionBar actionBar,int resId){
    	// ActionBar actionBar = getActionBar();  
         actionBar.setDisplayHomeAsUpEnabled(true);  
           
         ActionBar.LayoutParams lp =new ActionBar.LayoutParams(  
                   ActionBar.LayoutParams.MATCH_PARENT,  
                   ActionBar.LayoutParams.MATCH_PARENT,  
                   Gravity.CENTER);  
         LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
         View titleView = inflater.inflate(resId, null);  
         actionBar.setCustomView(titleView, lp);  
           
         actionBar.setDisplayShowHomeEnabled(false);//去掉导航  
         actionBar.setDisplayShowTitleEnabled(false);//去掉标题  
         actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);  
         actionBar.setDisplayShowCustomEnabled(true);  
           
/*         ImageButton imageBtn = (ImageButton) actionBar.getCustomView().findViewById(R.id.image_btn);  
           
         imageBtn.setOnClickListener(new View.OnClickListener() {  
               
             @Override  
             public void onClick(View v) {  
                 Toast.makeText(SecondActivity.this, "返回", Toast.LENGTH_SHORT).show();  
             }  
         });  */
         return null;
    }
}
