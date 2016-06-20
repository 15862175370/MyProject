package com.atgem.ailisidemo.activity;

import com.atgem.googleplay.R;
import com.atgem.googleplay.R.layout;
import com.atgem.googleplay.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class LActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_l);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.l, menu);
		return true;
	}

}
