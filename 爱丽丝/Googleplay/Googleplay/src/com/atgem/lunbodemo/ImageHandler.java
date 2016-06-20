package com.atgem.lunbodemo;

import java.lang.ref.WeakReference;

import android.os.Handler;
import android.os.Message;

public class ImageHandler extends Handler {
	 
        
        /** 
         * ���������ʾ��View�� 
         */  
        protected static final int MSG_UPDATE_IMAGE  = 1;  
        /** 
         * ������ͣ�ֲ��� 
         */  
        protected static final int MSG_KEEP_SILENT   = 2;  
        /** 
         * ����ָ��ֲ��� 
         */  
        protected static final int MSG_BREAK_SILENT  = 3;  
        /** 
         * ��¼���µ�ҳ�ţ����û��ֶ�����ʱ��Ҫ��¼��ҳ�ţ������ʹ�ֲ���ҳ����� 
         * ���統ǰ����ڵ�һҳ������׼�����ŵ��ǵڶ�ҳ������ʱ���û���������ĩҳ�� 
         * ��Ӧ�ò��ŵ��ǵ�һҳ�������������ԭ���ĵڶ�ҳ���ţ����߼��������⡣ 
         */  
        protected static final int MSG_PAGE_CHANGED  = 4;  
           
        //�ֲ����ʱ��  
        protected static final long MSG_DELAY = 3000;  
           
        //ʹ�������ñ���Handlerй¶.����ķ��Ͳ������Բ���Activity��Ҳ������Fragment��  
        private WeakReference<HomeActivity> weakReference;  
        private int currentItem = 0;  
           
      
           
        public ImageHandler(WeakReference<HomeActivity> wk) {
			// TODO Auto-generated constructor stub
        	 weakReference = wk;
		}

		@Override  
        public void handleMessage(Message msg) {  
            super.handleMessage(msg);  
          //  Log.d(LOG_TAG, "receive message " + msg.what);  
            HomeActivity activity = weakReference.get();  //���HomeActivity
            if (activity==null){  
                //Activity�Ѿ����գ������ٴ���UI��  
                return ;  
            }  
            //�����Ϣ���в��Ƴ�δ���͵���Ϣ������Ҫ�Ǳ����ڸ��ӻ�������Ϣ�����ظ������⡣  
            if (activity.handler.hasMessages(MSG_UPDATE_IMAGE)){  //�����Ϣ�������Ƿ����what����ָ����ֵ
                activity.handler.removeMessages(MSG_UPDATE_IMAGE);  
            }  
            switch (msg.what) {  
            case MSG_UPDATE_IMAGE:  
                currentItem++;  
                activity.viewPager.setCurrentItem(currentItem);  
                //׼���´β���  
                activity.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);  
                break;  
            case MSG_KEEP_SILENT:  
                //ֻҪ��������Ϣ����ͣ��  
                break;  
            case MSG_BREAK_SILENT:  
                activity.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);  
                break;  
            case MSG_PAGE_CHANGED:  
                //��¼��ǰ��ҳ�ţ����ⲥ�ŵ�ʱ��ҳ����ʾ����ȷ��  
                currentItem = msg.arg1;  
                break;  
            default:  
                break;  
            }   
        }  
    }  

