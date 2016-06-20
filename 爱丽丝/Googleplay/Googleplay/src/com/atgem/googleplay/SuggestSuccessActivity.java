package com.atgem.googleplay;

import com.atgem.lunbodemo.HomeActivity;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class SuggestSuccessActivity extends ActionBarActivity implements
		OnClickListener {

	private TextView tv1;
	private TextView tv2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_suggest_success);
		tv1 = (TextView) findViewById(R.id.tv_returnhome);
		tv2 = (TextView) findViewById(R.id.tv_returnset);
		tv1.setOnClickListener(this);
		tv2.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.suggest_success, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_returnhome:
			Intent intent1 = new Intent(SuggestSuccessActivity.this,HomeActivity.class);//返回首页
			startActivity(intent1);
			break;
		case R.id.tv_returnset:
			Intent intent2 = new Intent(SuggestSuccessActivity.this,SettingActivity.class);//返回首页
			startActivity(intent2);
			break;
		default:
			break;
		}
	}
}
