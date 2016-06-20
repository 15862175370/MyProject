package com.atgem.googleplay;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.UUID;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import bean.Url;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class UserShowActivity extends ActionBarActivity implements
		OnClickListener {
	Dialog dialog;
	private File tempFile;
	private Bitmap bitmap;
	private String imageone;
	private static final int PHOTO_REQUEST_CAMERA = 1;// 拍照
	private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
	private static final int PHOTO_REQUEST_CUT = 3;// 结果
	private String PHOTO_FILE_NAME = "wxq_temp_photo.jpg";// 覆盖只有唯一一张
	private String uuidname;
	private ImageView iv_showphoto;
	private ImageView iv_showtime;
	private TextView time;
	private ImageView timered;
	private ImageView timeg;
	private TextView content;
//	private TextView send;
	private TextView post;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_show);
		showAcitonBar();
		iv_showphoto = (ImageView) findViewById(R.id.iv_showphoto);
		iv_showtime = (ImageView) findViewById(R.id.iv_showtime);
//		send = (TextView) findViewById(R.id.tv_send);
		iv_showphoto.setOnClickListener(this);
		iv_showtime.setOnClickListener(this);
//		send.setOnClickListener(this);
	}

	public void showAcitonBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
				ActionBar.LayoutParams.MATCH_PARENT,
				ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
		
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View titleView = inflater.inflate(R.layout.action_bar_title, null);
		
		post = (TextView) titleView.findViewById(R.id.tv_post);
		post.setVisibility(View.VISIBLE);
		TextView title = (TextView) titleView.findViewById(R.id.title);
		title.setText("发帖");
		actionBar.setCustomView(titleView, lp);
		
		actionBar.setDisplayShowHomeEnabled(false);// 去掉导航
		actionBar.setDisplayShowTitleEnabled(false);// 去掉标题
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		actionBar.setDisplayShowCustomEnabled(true);

		ImageButton imageBtn = (ImageButton) actionBar.getCustomView()
				.findViewById(R.id.image_btn);
		 post= (TextView) actionBar.getCustomView()
				.findViewById(R.id.tv_post);
		 post.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				upload();
				
			}
		});
		imageBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
					Toast.makeText(UserShowActivity.this, "返回", Toast.LENGTH_SHORT)
					.show();
				}
				
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_showphoto:
			showDialog();
			break;
		case R.id.iv_showtime:
			showTime();
			timeg.setVisibility(View.INVISIBLE);
			timered.setVisibility(View.VISIBLE);
			time.setVisibility(View.VISIBLE);
			break;
//		case R.id.tv_send:
//			upload();
//
//			// case :
//			break;
		default:
			break;
		}
	}

	private String showTime() {
		time = (TextView) findViewById(R.id.tv_time);
		timered = (ImageView) findViewById(R.id.iv_showtimered);
		timeg = (ImageView) findViewById(R.id.iv_showtime);
		SimpleDateFormat sDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String Ctime = sDateFormat.format(new java.util.Date());
		time.setText(Ctime);

		return Ctime;
	}

	public void on_click(View v) {
		switch (v.getId()) {
		case R.id.openCamera:
			openCamera();
			break;
		case R.id.openPhones:
			openPhones();
			break;
		case R.id.cancel:
			dialog.cancel();
			break;
		default:
			break;
		}
	}

	private void showDialog() {
		// 选择手机图片或者拍照
		View view = getLayoutInflater().inflate(R.layout.photo_choose_dialog,
				null);

		dialog = new Dialog(this, R.style.transparentFrameWindowStyle);
		dialog.setContentView(view, new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT));
		Window window = dialog.getWindow();

		// 设置显示动画
		window.setWindowAnimations(R.style.main_menu_animstyle);
		WindowManager.LayoutParams wl = window.getAttributes();
		wl.x = 0;
		wl.y = getWindowManager().getDefaultDisplay().getHeight();
		// 以下这两句是为了保证按钮可以水平满屏
		wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
		wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
		// 设置显示位置
		dialog.onWindowAttributesChanged(wl);
		// 设置点击外围解散
		dialog.setCanceledOnTouchOutside(true);
		dialog.show();

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == PHOTO_REQUEST_GALLERY) {
			if (data != null) {
				// 得到图片的全路径
				System.out.println("yes");
				Uri uri = data.getData();
				crop(uri);
			}

		} else if (requestCode == PHOTO_REQUEST_CAMERA) {
			if (hasSdcard()) {
				tempFile = new File(Environment.getExternalStorageDirectory(),
						PHOTO_FILE_NAME);
				crop(Uri.fromFile(tempFile));
			} else {
				Toast.makeText(getApplicationContext(), "未找到存储卡，无法存储照片！", 0)
						.show();
			}

		} else if (requestCode == PHOTO_REQUEST_CUT) {
			try {
				bitmap = data.getParcelableExtra("data");
				System.out.println(bitmap);
				System.out.println("hahahahaha");

				this.iv_showphoto.setImageBitmap(bitmap);
				System.out.println("yes2");
				imageone = saveMyBitmap(bitmap, "王晓清");

			} catch (Exception e) {
				e.printStackTrace();
			}
			dialog.dismiss();

		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	private String saveMyBitmap(Bitmap mBitmap, String bitName) {
		uuidname = UUID.randomUUID().toString();
		System.out.println("Rong" + uuidname);
		File f = new File(Environment.getExternalStorageDirectory(), uuidname
				+ ".jpg");// 保存到了sd卡中

		// File f = new File( "/sdcard/Note/"+bitName + ".jpg");
		FileOutputStream fOut = null;
		try {
			fOut = new FileOutputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
		try {
			fOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return uuidname + ".jpg";
	}

	private void openPhones() {// 打开相册
		Intent intent = new Intent(Intent.ACTION_PICK);
		intent.setType("image/*");
		startActivityForResult(intent, PHOTO_REQUEST_GALLERY);

	}

	private void openCamera() {// 打开照相机
		Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		// 判断存储卡是否可以用，可用进行存储
		if (hasSdcard()) {
			intent.putExtra(MediaStore.EXTRA_OUTPUT,
					Uri.fromFile(new File(Environment
							.getExternalStorageDirectory(), PHOTO_FILE_NAME)));
		}
		startActivityForResult(intent, PHOTO_REQUEST_CAMERA);

	}

	private void crop(Uri uri) {
		// 裁剪图片意图
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		// 裁剪框的比例，1：1
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// 裁剪后输出图片的尺寸大小
		intent.putExtra("outputX", 400);
		intent.putExtra("outputY", 400);
		// 图片格式
		intent.putExtra("outputFormat", "JPEG");
		intent.putExtra("noFaceDetection", true);// 取消人脸识别
		intent.putExtra("return-data", true);// true:不返回uri，false：返回uri
		startActivityForResult(intent, PHOTO_REQUEST_CUT);
	}

	private boolean hasSdcard() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}

	private void upload() {
		HttpUtils httpUtils = new HttpUtils();

		RequestParams params = new RequestParams();

		params.addBodyParameter("name", "jack");

		File file = new File(Environment.getExternalStorageDirectory(),
				imageone);
		System.out.println("fghjkljhgghjkljhggh");

		params.addBodyParameter("file", file);
		System.out.println("aaaaaaa" + file);
		String pictureid = Url.url+":8080/Alisi2/upload/" + uuidname
				+ ".jpg";// 图片地址
		System.out.println("wang   " + pictureid);

		content = (TextView) findViewById(R.id.et_us1);
		String ucontent = content.getText().toString();
		String time = showTime();
		// int u_id = 1;

		if (imageone != null) {
			// AddUserPost addUserPost = new AddUserPost(u_id, ucontent,
			// pictureid,time);
			// System.out.println(addUserPost);
			// String userpost = gson.toJson(addUserPost);
			// params.addBodyParameter("picture", pictureid);

			params.addBodyParameter("u_id", "1");
			params.addBodyParameter("content", ucontent);
			params.addBodyParameter("picture", pictureid);
			params.addBodyParameter("time", time);
			System.out.println("wangrong !!!" + "1" + " " + ucontent + " "
					+ time + " ");
			Toast.makeText(getApplicationContext(), "有图片" + pictureid, 0)
					.show();
		} else {
			Toast.makeText(getApplicationContext(), "没有图片", 0).show();
		}
		// Url.url+"localhost:8080/LoveDogs04/JsonPostsPictureServlet?name=jack
		httpUtils.send(HttpMethod.POST,
				Url.url+":8080/Alisi2/AddUserPostServlet", params,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						Toast.makeText(getApplicationContext(), "yes",
								Toast.LENGTH_LONG).show();
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						Toast.makeText(getApplicationContext(), "no",
								Toast.LENGTH_LONG).show();
					}
				});
		//
	}

}
