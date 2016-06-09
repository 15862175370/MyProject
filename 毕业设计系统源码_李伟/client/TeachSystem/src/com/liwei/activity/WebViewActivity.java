package com.liwei.activity;

import java.lang.reflect.Field;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.liwei.application.MyApplation;
import com.liwei.teachsystem.R;

public class WebViewActivity extends Activity {
	private String webUrl=null;
    private boolean loaded = false;
	private WebView webView;
	private Handler mHandler = new Handler(){
	    @Override
	    public void handleMessage(Message msg) {
	        if (!Thread.currentThread().isInterrupted()) {
	            switch (msg.what) {
	                case 0:
	                   
	                    
	                    break;
	                case 1:
	                    // 闅愯棌杩涘害瀵硅瘽妗嗭紝涓嶅彲浣跨敤dismiss()銆乧ancel(),鍚﹀垯鍐嶆璋冪敤show()鏃讹紝
	                    // 鏄剧ず鐨勫璇濇灏忓渾鍦堜笉浼氬姩銆?
	                    // mProgressDialog.dismiss();
	                   
	                    
	                    break;
	                default:
	                    break;
	        }
	        }
	        super.handleMessage(msg);
	        
	    }
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		

        requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
		MyApplation.setActivity(this);
		String url="http://www.baidu.com";
		webUrl=url;
		initView();
		
	}
	
	public void initView(){
		ImageView close=(ImageView)findViewById(R.id.bjmgf_sdk_closeAboutBtnId);
		/**
		 * 返回按钮
		 */
		close.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			finish();
				
			}
		});
		webView=(WebView)findViewById(R.id.webview);

       webView.getSettings().setJavaScriptEnabled(true);

       webView.getSettings().setSupportZoom(true);
  
       webView.getSettings().setUseWideViewPort(true);
 
      webView.getSettings().setBuiltInZoomControls(true);
  
    webView.getSettings().setLoadWithOverviewMode(true);
  
      webView.getSettings().setUseWideViewPort(true);
     webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view,
                                                    String url) {
            	loadUrl(view, url);
                return true;
            }


        });
     webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
             webView.requestFocus();
                if (newProgress == 100) {

                    loaded = true;

                }
                super.onProgressChanged(view, newProgress);
            }

        });
   webView.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean hasFocus) {

                if (hasFocus) {
                    try {
                      Field defaultScale = WebView.class
                                .getDeclaredField("mDefaultScale");
                        defaultScale.setAccessible(true);
                        defaultScale.setFloat(webView, 0.5f);
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

		
		
	}
	@Override
	protected void onResume() {
		loadUrl(webView, webUrl);
		super.onResume();
	}
    public void loadUrl(final WebView view, final String url){
        webUrl=url;
        loaded=false;
        view.loadUrl(url);
        //mHandler.sendEmptyMessage(0);
    }


}
