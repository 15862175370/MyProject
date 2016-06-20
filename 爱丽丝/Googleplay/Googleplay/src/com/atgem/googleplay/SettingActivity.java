package com.atgem.googleplay;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;

import bean.Url;

import com.android.volley.toolbox.JsonArrayRequest;
import com.atgem.ailisidemo.utils.DataCleanManager;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.support.v7.app.ActionBarActivity;
import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class SettingActivity extends ActionBarActivity implements
		OnClickListener {

	private TextView tv1;
	private TextView tv2;
	private TextView tv3;
	private TextView tv4;
	private TextView tv0;
	private RoundedCornerImageView iv_sendpic;
	Dialog dialog;

	private File tempFile;
	private Bitmap bitmap;

	private String imageone;
	
	private static final int PHOTO_REQUEST_CAMERA = 1;// 拍照
	private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
	private static final int PHOTO_REQUEST_CUT = 3;// 结果
	private String PHOTO_FILE_NAME = "wxq_temp_photo.jpg";// 覆盖只有唯一一张
	private String uuidname;
	private TextView tv5;

	public void showAcitonBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
				ActionBar.LayoutParams.MATCH_PARENT,
				ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View titleView = inflater.inflate(R.layout.action_bar_title, null);
		TextView title = (TextView) titleView.findViewById(R.id.title);
		title.setText("设置");
		actionBar.setCustomView(titleView, lp);

		actionBar.setDisplayShowHomeEnabled(false);// 去掉导航
		actionBar.setDisplayShowTitleEnabled(false);// 去掉标题
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		actionBar.setDisplayShowCustomEnabled(true);

		ImageButton imageBtn = (ImageButton) actionBar.getCustomView()
				.findViewById(R.id.image_btn);

		imageBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(SettingActivity.this, "返回", Toast.LENGTH_SHORT)
						.show();

			}
		});
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		initView();
		// getActionBar().hide();
		showAcitonBar();
		tv0.setOnClickListener(this);
		tv1.setOnClickListener(this);
		tv2.setOnClickListener(this);
		tv3.setOnClickListener(this);
		tv4.setOnClickListener(this);
		tv5.setOnClickListener(this);

	}

	private void initView() {
		tv0 = (TextView) findViewById(R.id.tv_0);
		tv1 = (TextView) findViewById(R.id.tv_1);
		tv2 = (TextView) findViewById(R.id.tv_2);
		tv3 = (TextView) findViewById(R.id.tv_3);
		tv4 = (TextView) findViewById(R.id.tv_4);
		tv5 = (TextView) findViewById(R.id.tv_updata);
		iv_sendpic = (RoundedCornerImageView) findViewById(R.id.iv_sendpic);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_0:
			showDialog();

			break;

		case R.id.tv_1:
			Intent intent1 = new Intent(SettingActivity.this,
					ChangeUserInfo.class);
			startActivity(intent1);

			break;
		case R.id.tv_2:
			// DataCleanManager dataCleanManager = new DataCleanManager();
			 DataCleanManager.cleanSharedPreference(this);
			 Toast.makeText(this,"清除缓存成功....",Toast.LENGTH_SHORT).show();
			
			break;

		case R.id.tv_3:
			Intent intent3 = new Intent(SettingActivity.this,
					VersionActivity.class);
			startActivity(intent3);
			break;

		case R.id.tv_4:
			Intent intent4 = new Intent(SettingActivity.this,
					SuggestActivity.class);
			startActivity(intent4);
			break;
		case R.id.tv_updata:
			upload();
			break;
		default:
			break;
		}

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

				this.iv_sendpic.setImageBitmap(bitmap);
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
		System.out.println("Rong"+uuidname);
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
		String pictureid = Url.url+":8080/Alisi2/upload/"+uuidname+".jpg";
		System.out.println("wang   "+pictureid);
		if (imageone != null) {
			params.addBodyParameter("picture", pictureid);
			params.addBodyParameter("u_id", "1");
			Toast.makeText(getApplicationContext(), "有图片", 0).show();
		} else {
			Toast.makeText(getApplicationContext(), "没有图片", 0).show();
		}
		// Url.url+"localhost:8080/LoveDogs04/JsonPostsPictureServlet?name=jack
		httpUtils.send(HttpMethod.POST,
				Url.url+":8080/Alisi2/ChangeUserIconServlet",
				params, new RequestCallBack<String>() {
					
					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						Toast.makeText(getApplicationContext(),
								"yes", Toast.LENGTH_LONG).show();
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(),
								"no", Toast.LENGTH_LONG).show();
					}
				});

	}
}
