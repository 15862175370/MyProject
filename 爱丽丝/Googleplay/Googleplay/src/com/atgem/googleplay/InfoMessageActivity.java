package com.atgem.googleplay;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import bean.InfoMessage;
import bean.InfoMessageComment;
import bean.InfoMessagePraise;
import bean.Url;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.loopj.android.image.SmartImageView;

public class InfoMessageActivity extends Activity implements OnClickListener {
	private TextView title;
	private SmartImageView im1;
	private TextView tv1;
	private SmartImageView im2;
	private TextView tv2;
	private SmartImageView im3;
	private TextView tv3;
	private SmartImageView im4;
	private TextView tv4;
	private TextView time;
	private RequestQueue rq;
	private EditText et;
	private TextView tvsend;
	private TextView pnum;

	private List<InfoMessageComment> list;

	Type classOfT;
	CommentListViewAdapter commentlistviewadapter;
	private ListView lv;

	private String content;
	InfoMessageComment comment;

	boolean isplaying = false;
	private SmartImageView photo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info_message);
		showAcitonBar();
		initView();

		String url = Url.url+":8080/Alisi2/InfoMessageServlet";
		// String url = "Url.url+"10.223.13.158:8080/Alisi2/InfoMessageServlet";
		volley_Get(url);

		lv = (ListView) findViewById(R.id.lv_im_user);
		String url1 = Url.url+":8080/Alisi2/InfoMessageCommentServlet";
		// "Url.url+"10.223.13.158:8080/Alisi2/InfoMessageCommentServlet";
		initData(url1);

		setPraise();

		tvsend.setOnClickListener(this);
		findViewById(R.id.ly_1).setOnClickListener(this);

		findViewById(R.id.iv_zan).setOnClickListener(iblis);

	}

	OnClickListener iblis = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (!isplaying) {
				photo = (SmartImageView) findViewById(R.id.iv_zan);
				photo.setImageResource(R.drawable.good_work_items_red);
				isplaying = true;
				praiseadd();
			} else {
				photo = (SmartImageView) findViewById(R.id.iv_zan);
				photo.setImageResource(R.drawable.good_work_items);
				isplaying = false;
				praisecut();
			}
		}

	};

	private void praisecut() {
		int im_id = 1;
		int u_id = 2;
		InfoMessagePraise infoMessagePraise = new InfoMessagePraise(im_id, u_id);
		Gson gson = new Gson();
		String infopraise = gson.toJson(infoMessagePraise);
		RequestParams params = new RequestParams();
		params.addBodyParameter("praiseInfo", infopraise);
		HttpUtils httpUtils = new HttpUtils();

		String url = Url.url+"10.202.1.99:8080/Alisi2/CutInfoMessagePraiseServlet";
		httpUtils.send(HttpMethod.POST, url, params,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException error, String msg) {

					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						System.out.println("zan 取消");
						
						pnum.setText(Integer.valueOf(pnum.getText().toString())
								- 1 + "");
					}
				});
	}

	private void praiseadd() {
		int im_id = 1;
		int u_id = 2; // 登录的用户id
		SimpleDateFormat sDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		String im_p_time = sDateFormat.format(new java.util.Date());
		InfoMessagePraise infoPraise = new InfoMessagePraise(im_id, u_id,
				im_p_time);
		Gson gson = new Gson();
		String infopraise = gson.toJson(infoPraise);
		RequestParams params = new RequestParams();
		params.addBodyParameter("praiseInfo", infopraise);
		HttpUtils httpUtils = new HttpUtils();
		String url = Url.url+":8080/Alisi2/AddInfoMessagePraiseServlet";
		httpUtils.send(HttpMethod.POST, url, params,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException error, String msg) {

					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						System.out.println("yes");
						if ("true".equals(responseInfo.result)) {
							pnum.setText(Integer.valueOf(pnum.getText()
									.toString()) + 1 + "");
						} else if ("faile".equals(responseInfo.result)) {
							System.out.println("no");
						}
					}

				});

	}

	private void initData(String url) {
		HttpUtils httputil = new HttpUtils();
		httputil.send(HttpMethod.GET, url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(ResponseInfo<String> infoMessageComment) {
				String json = infoMessageComment.result;
				Gson gson = new Gson();
				list = new ArrayList<InfoMessageComment>();
				Type type = new TypeToken<List<InfoMessageComment>>() {
				}.getType();

				list = gson.fromJson(json, type);
				for (InfoMessageComment o : list) {
					System.out.println(o.getIcon() + o.getIm_reply_time()
							+ o.getU_name());
				}
				CommentListViewAdapter adapter = new CommentListViewAdapter(
						list);
				lv.setAdapter(adapter);

			}
		});
	}

	public void volley_Get(String url) {
		StringRequest request = new StringRequest(Method.POST, url,
				new ResponseListener(), new ResponseErrorListener());
		rq = Volley.newRequestQueue(this);
		rq.add(request);
	}

	class ResponseListener implements Response.Listener<String> {

		@Override
		public void onResponse(String iminfo) {

			Gson gson = new Gson();
			String json = iminfo;
			InfoMessage infoMessage = gson.fromJson(json, InfoMessage.class);
			System.out.println(infoMessage);

			title.setText(infoMessage.getIm_name());
			im1.setImageUrl(infoMessage.getIm_image());
			tv1.setText(infoMessage.getIm_content());
			im2.setImageUrl(infoMessage.getIm_image1());
			tv2.setText(infoMessage.getIm_content1());
			im3.setImageUrl(infoMessage.getIm_image2());
			tv3.setText(infoMessage.getIm_content2());
			im4.setImageUrl(infoMessage.getIm_image3());
			tv4.setText(infoMessage.getIm_content3());
			time.setText(infoMessage.getIm_time());
		}

	}

	class ResponseErrorListener implements Response.ErrorListener {

		@Override
		public void onErrorResponse(VolleyError arg0) {
			Log.i("infoMessage", "请求发生错误....");

		}

	}

	public void initView() {
		title = (TextView) findViewById(R.id.tv_im_title);
		im1 = (SmartImageView) findViewById(R.id.iv_im_01);
		tv1 = (TextView) findViewById(R.id.tv_im_01);
		im2 = (SmartImageView) findViewById(R.id.iv_im_02);
		tv2 = (TextView) findViewById(R.id.tv_im_02);
		im3 = (SmartImageView) findViewById(R.id.iv_im_03);
		tv3 = (TextView) findViewById(R.id.tv_im_03);
		im4 = (SmartImageView) findViewById(R.id.iv_im_04);
		tv4 = (TextView) findViewById(R.id.tv_im_04);
		time = (TextView) findViewById(R.id.tv_im_time);
		tvsend = (TextView) findViewById(R.id.tv_send);
	}

	class CommentListViewAdapter extends BaseAdapter {
		List<InfoMessageComment> list1;
		private LayoutInflater inflater = LayoutInflater
				.from(InfoMessageActivity.this);

		public CommentListViewAdapter(List<InfoMessageComment> list) {
			list1 = list;
			System.out.println(list1.size());
		}

		@Override
		public int getCount() {
			return list1.size();
		}

		@Override
		public Object getItem(int position) {
			return list1.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			InfoMessageComment infomessagecomment = list1.get(position);
			if (convertView == null) {
				convertView = inflater.inflate(
						R.layout.activity_info_message_items, null);
			}
			ImageView uicon = (ImageView) convertView
					.findViewById(R.id.iv_im_userp);
			TextView tv_name = (TextView) convertView
					.findViewById(R.id.tv_im_item1);
			TextView tv_content = (TextView) convertView
					.findViewById(R.id.tv_im_item2);
			TextView tv_time = (TextView) convertView
					.findViewById(R.id.tv_im_item3);
			BitmapUtils utils = new BitmapUtils(InfoMessageActivity.this);
			// uicon.setImage(infomessagecomment.getIcon());
			utils.display(uicon, infomessagecomment.getIcon());

			tv_name.setText(infomessagecomment.getU_name());
			tv_content.setText(infomessagecomment.getIm_reply_content());
			tv_time.setText(infomessagecomment.getIm_reply_time());

			return convertView;
		}

	}

	public void setPraise() {
		HttpUtils httpUtils = new HttpUtils();

		String url =Url.url+":8080/Alisi2/GetInfoMessagePraiseNum";
		httpUtils.send(HttpMethod.POST, url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException error, String msg) {

			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String rl = responseInfo.result;
				String num = rl;
				pnum = (TextView) findViewById(R.id.tv_imp_num);

				pnum.setText(num);
			}
		});
	}

	public void showAcitonBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
				ActionBar.LayoutParams.MATCH_PARENT,
				ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View titleView = inflater.inflate(R.layout.action_bar_title, null);
		actionBar.setCustomView(titleView, lp);

		actionBar.setDisplayShowHomeEnabled(false);// 去掉导航
		actionBar.setDisplayShowTitleEnabled(false);// 去掉标题
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		actionBar.setDisplayShowCustomEnabled(true);

		ImageButton imageBtn = (ImageButton) actionBar.getCustomView()
				.findViewById(R.id.image_btn);
		TextView tv_title = (TextView) actionBar.getCustomView().findViewById(
				R.id.title);
		tv_title.setText("资讯");
		imageBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(InfoMessageActivity.this, "返回",
						Toast.LENGTH_SHORT).show();

			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		if (v.getId() == R.id.tv_send) {

			et = (EditText) findViewById(R.id.et_talk);
			content = et.getText().toString();

			int im_id = 1; // 虚拟用户id

			SimpleDateFormat sDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String time = sDateFormat.format(new java.util.Date());

			RequestParams params = new RequestParams();
			// params.addBodyParameter("im_id", String.valueOf(im_id));

			// SharedPreferences sp = getSharedPreferences("userInfo",
			// MODE_PRIVATE);
			// int u_id = sp.getInt("u_id", 0);
			int u_id = 1;
			InfoMessageComment u_content = new InfoMessageComment(im_id, u_id,
					content, time);
			Gson gson = new Gson();
			String usercontent = gson.toJson(u_content);
			params.addBodyParameter("comment", usercontent);
			String url = Url.url+":8080/Alisi2/AddInfoMessageCommentServlet";
			HttpUtils httpUtils = new HttpUtils();
			httpUtils.send(HttpMethod.POST, url, params,
					new RequestCallBack<String>() {

						@Override
						public void onFailure(HttpException arg0, String arg1) {

							System.out.println("whywhywhy!!!!!!!!!!");

						}

						@Override
						public void onSuccess(ResponseInfo<String> responseInfo) {
							if ("true".equals(responseInfo.result)) {
								System.out.println("yes!!!!!");
								et.setText("");

								HttpUtils httpUtils = new HttpUtils();
								// RequestParams params=new RequestParams();
								// params.addBodyParameter("activity_id","1");
								httpUtils
										.send(HttpMethod.POST,
												Url.url+":8080/Alisi2/InfoMessageCommentServlet",
												new RequestCallBack<String>() {

													@Override
													public void onFailure(
															HttpException arg0,
															String arg1) {

													}

													@Override
													public void onSuccess(
															ResponseInfo<String> infoMessageComment) {
														String json = infoMessageComment.result;
														Gson gson = new Gson();
														list = new ArrayList<InfoMessageComment>();
														Type type = new TypeToken<List<InfoMessageComment>>() {
														}.getType();

														list = gson.fromJson(
																json, type);
														for (InfoMessageComment o : list) {
															System.out.println(o
																	.getIcon()
																	+ o.getIm_reply_time()
																	+ o.getU_name());
														}
														CommentListViewAdapter adapter = new CommentListViewAdapter(
																list);
														lv.setAdapter(adapter);

														adapter.notifyDataSetChanged();
													}
												});
							} else {
								System.out.println("no");
							}
						}
					});

		}
	}

	

}
