package com.atgem.googleplay;

import java.lang.reflect.Type;
import java.util.List;

import bean.ChangeUser;
import bean.Url;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.StringRequest;
import com.atgem.ailisidemo.OrderActivity;
import com.atgem.ailisidemo.utils.Volley_get;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class ChangeUserInfo extends ActionBarActivity implements
		OnClickListener {
	private ChangeUser changeUser;
	private TextView tv;
	private EditText etname;
	private EditText etcity;
	private EditText etmail;
	private EditText etsign;
	private RadioButton cbman;
	private RadioButton cbwomen;
	private String name;
	private String city;
	private String mail;
	private String sign;
	private int sex;
	private int u_id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_user_info);
		tv = (TextView) findViewById(R.id.tv_updata);
		tv.setOnClickListener(this);
		SharedPreferences sp = getSharedPreferences("userInfo",MODE_PRIVATE);
              u_id=sp.getInt("u_id",0);
	}

	@Override
	public void onClick(View v) {

		if (v.getId() == R.id.tv_updata) {
			etname = (EditText) findViewById(R.id.et_u_name);
			etcity = (EditText) findViewById(R.id.et_city);
			etmail = (EditText) findViewById(R.id.et_mail);
			etsign = (EditText) findViewById(R.id.et_sign);
			cbman = (RadioButton) findViewById(R.id.edit_man);
			cbwomen = (RadioButton) findViewById(R.id.edit_women);

			if (cbman.isChecked()) {

				name = etname.getText().toString();
				city = etcity.getText().toString();
				mail = etmail.getText().toString();
				sign = etsign.getText().toString();
				changeUser = new ChangeUser(u_id, name, sign, city, 1, mail);

			} else if (cbwomen.isChecked()) {

				name = etname.getText().toString();
				city = etcity.getText().toString();
				mail = etmail.getText().toString();
				sign = etsign.getText().toString();
				//getSharedPreferences("userInfo","u_id");
				changeUser = new ChangeUser(u_id, name, sign, city, 2, mail);

			}
			Gson gson = new Gson();
			String stringlist = gson.toJson(changeUser);

			RequestParams params = new RequestParams();
			params.addBodyParameter("info", stringlist);

			HttpUtils httpUtils = new HttpUtils();
			String url =Url.url+":8080/Alisi2/UpdateInfo";
			httpUtils.send(HttpMethod.POST, url, params,
					new RequestCallBack<String>() {

						@Override
						public void onFailure(HttpException arg0, String arg1) {

						}

						@Override
						public void onSuccess(ResponseInfo<String> responseInfo) {
							System.out.println("yes");
							if ("true".equals(responseInfo.result)) {
								Intent intent = new Intent(ChangeUserInfo.this,
										ChangeSucess.class);
								startActivity(intent);
							} else if ("faile".equals(responseInfo.result)) {
								System.out.println("no");
							}

						}
					});

		}

	}

	// private void Volley_get(String url) {
	// StringRequest reqest = new StringRequest(Method.GET, url,
	// new ResponseListener(), new ResponseErrorListener());
	//
	// }
	//
	// class ResponseListener implements Response.Listener<String> {
	//
	// @Override
	// public void onResponse(String arg0) {
	// // TODO Auto-generated method stub
	// Log.i("MainActivity", arg0);
	// Gson g = new Gson();
	// String json = arg0;
	// System.out.println(json);
	//
	// classOfT = new TypeToken<List<ChangeUser>>() {
	// }.getType();
	// list = g.fromJson(json, classOfT);
	//
	// }
	//
	// }
	//
	// class ResponseErrorListener implements Response.ErrorListener {
	//
	// @Override
	// public void onErrorResponse(VolleyError arg0) {
	// // TODO Auto-generated method stub
	// Log.i("ShopActicity", "请求发生错误....");
	//
	// }
	//
	// }


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
