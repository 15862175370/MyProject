package com.atgem.googleplay;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView.ScaleType;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.roamer.ui.view.SmoothImageView;

public class SpaceImageDetailActivity extends Activity {

	private String mDatas;
	private int mPosition;
	private int mLocationX;
	private int mLocationY;
	private int mWidth;
	private int mHeight;
	SmoothImageView imageView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//mDatas =  (String) getIntent().getSerializableExtra("images");
		mDatas =getIntent().getStringExtra("images");
		mPosition = getIntent().getIntExtra("position", 0);
		mLocationX = getIntent().getIntExtra("locationX", 0);
		mLocationY = getIntent().getIntExtra("locationY", 0);
		mWidth = getIntent().getIntExtra("width", 0);
		mHeight = getIntent().getIntExtra("height", 0);
       
		imageView = new SmoothImageView(this);
		imageView.setOriginalInfo(mWidth, mHeight, mLocationX, mLocationY);
		imageView.transformIn();
		imageView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
		imageView.setScaleType(ScaleType.FIT_CENTER);
		
		setContentView(imageView);
		getActionBar().hide();
		ImageLoader.getInstance().displayImage(mDatas, imageView);
		//imageView.setImageResource(R.drawable.temp);
		 ScaleAnimation scaleAnimation = new ScaleAnimation(0.5f, 0.8f, 0.5f,
		 0.8f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
		 0.5f);
		 scaleAnimation.setDuration(300);
		 scaleAnimation.setInterpolator(new AccelerateInterpolator());
		 imageView.startAnimation(scaleAnimation);
           imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				/*Intent intent=new Intent(SpaceImageDetailActivity.this,WorksActivity.class);
				  startActivity(intent);
				overridePendingTransition(0,0);*/
				finish();
			}
		});
	}

	@Override
	public void onBackPressed() {
		imageView.setOnTransformListener(new SmoothImageView.TransformListener() {
			@Override
			public void onTransformComplete(int mode) {
				if (mode == 2) {
					finish();
				}
			}
		});
		imageView.transformOut();
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (isFinishing()) {
			overridePendingTransition(0, 0);
		}
	}

}
