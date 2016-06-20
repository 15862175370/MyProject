package com.atgem.googleplay;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ChangeSucess extends ActionBarActivity implements OnClickListener {

	private TextView backhome;
	private TextView returnset;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_sucess);
		backhome = (TextView) findViewById(R.id.tv_backhome);
		returnset = (TextView) findViewById(R.id.tv_reutrnset);
		backhome.setOnClickListener(this);
		returnset.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_backhome:
			Intent intent1 = new Intent(ChangeSucess.this,
					MainActivity2.class);
			startActivity(intent1);
			break;
		case R.id.tv_reutrnset:
			Intent intent2 = new Intent(ChangeSucess.this,
					SettingActivity.class);
			startActivity(intent2);
			break;
		default:
			break;
		}
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

}
