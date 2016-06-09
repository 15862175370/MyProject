package com.liwei.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class DialogUtils {
	/**
     * 创建一个透明进度条dialog
     *
     * @param context
     * @param message
     * @return
     */

    public static final Dialog createTransparentProgressDialog(Context context, String message) {
        Dialog dialog = new Dialog(context);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        LinearLayout viewGroup = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                  ViewGroup.LayoutParams.WRAP_CONTENT);
        viewGroup.setBackgroundColor(0x10000000);
        viewGroup.setLayoutParams(params);
        viewGroup.setPadding(10, 10, 20, 10);
        viewGroup.setOrientation(LinearLayout.HORIZONTAL);
        ProgressBar bar = new ProgressBar(context);
        TextView msgText = new TextView(context);
        msgText.setTextColor(Color.WHITE);
        msgText.setText(message);
        msgText.setGravity(Gravity.CENTER_VERTICAL);
        viewGroup.addView(bar, params);
        params.height = ViewGroup.LayoutParams.FILL_PARENT;
        params.leftMargin = 10;
        viewGroup.addView(msgText, params);
        
        dialog.setContentView(viewGroup);
        dialog.setCancelable(false);
        return dialog;
    }

}
