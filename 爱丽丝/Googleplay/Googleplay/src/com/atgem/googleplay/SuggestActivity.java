package com.atgem.googleplay;

import bean.Suggest;
import bean.Url;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class SuggestActivity extends ActionBarActivity implements
		OnClickListener {

	private EditText suggest;
	private TextView suggestbut;
	private String b_suggest;
	Suggest suggestinfo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_suggest);

		suggestbut = (TextView) findViewById(R.id.tv_suggestbotton);
		suggestbut.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.suggest, menu);
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
		if (v.getId() == R.id.tv_suggestbotton) {
			suggest = (EditText) findViewById(R.id.et_suggest);
			b_suggest = suggest.getText().toString();
			suggestinfo = new Suggest(2, b_suggest);
		}
		Gson gson = new Gson();
		String stringjson = gson.toJson(suggestinfo);
		
		RequestParams params = new RequestParams();
		params.addBodyParameter("suggest",stringjson);
		
		HttpUtils httpUtils = new HttpUtils();
		String url = Url.url+":8080/Alisi2/UpdataSuggest";
		httpUtils.send(HttpMethod.POST, url, params,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {

					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						System.out.println("yes");
						if ("true".equals(responseInfo.result)) {
							Intent intent = new Intent(SuggestActivity.this,
									SuggestSuccessActivity.class);
							startActivity(intent);
						} else if ("faile".equals(responseInfo.result)) {
							System.out.println("no");
						}

					}
				});

		
	}
}
