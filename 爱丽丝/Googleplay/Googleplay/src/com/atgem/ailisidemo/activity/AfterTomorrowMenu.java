package com.atgem.ailisidemo.activity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import bean.FreeTime;

import com.atgem.googleplay.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class AfterTomorrowMenu extends Activity{


	private GridView gv;
	private TextView today;
	private TextView tomorrow;
	private TextView aftertomorrow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.reservetime_activity);
		aftertomorrow=(TextView) findViewById(R.id.tv_aftertomorrow);
		aftertomorrow.setBackgroundColor(getResources().getColor(R.color.select));
		 today=(TextView) findViewById(R.id.tv_today);
		today.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(AfterTomorrowMenu.this,OrderMenu.class);
				startActivity(intent);
				
				
			}
		});
		
		
		
	 tomorrow=(TextView) findViewById(R.id.tv_tomorrow);
	
	tomorrow.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			Intent intent=new Intent(AfterTomorrowMenu.this,TomorrowOrderMenu.class);
			startActivity(intent);
			
			
		}
	});
	
	
	
	
				
	
	
	
		class MyAdapter extends BaseAdapter{
			private List<String> list;
		private	List<FreeTime>list1;
		private FreeTime fTime;
		public MyAdapter( List<String> list,List<FreeTime>list2){
			this.list1=list2;
			this.list=list;
			System.out.println(list.size());
			//System.out.println(list1.size());
		}
	
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return 22;
			}

			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return position;
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				//String string=list.get(position);
				System.out.println(list.size());
			fTime=list1.get(position);
			//System.out.println(list1.size());
		
			
				if(convertView==null){
					convertView=View.inflate(AfterTomorrowMenu.this, R.layout.reservetime_items_activity, null);
				}
				
				TextView tv=(TextView) convertView.findViewById(R.id.tv_time);
				//tv.setText(string);
		
			//System.out.println(",,,,,,,,,,,,,"+fTime.getT_id()+",,,,,,,,,,"+fTime.getDay_id()+",,,,,,,,,,,,"+fTime.getState());
			
				if(fTime.getState().equals("1")){
					tv.setBackgroundColor(getResources().getColor(R.color.themecolor));
				}else{
					tv.setBackgroundColor(getResources().getColor(R.color.black));
					
				}
			
				return convertView;
			}
			
		}
	gv=(GridView) findViewById(R.id.gridView1);
		
		//ΪGridView��Ŀ���ü����¼�
		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				switch (position) {
				case 0:
					
					Toast.makeText(AfterTomorrowMenu.this,"��ҳ1",Toast.LENGTH_SHORT).show();
					
					break;
	case 1:
					
					Toast.makeText(AfterTomorrowMenu.this,"��ҳ2",Toast.LENGTH_SHORT).show();
					
					break;
	case 2:
		
		Toast.makeText(AfterTomorrowMenu.this,"��ҳ3",Toast.LENGTH_SHORT).show();
		
		break;
	case 3:
		
		Toast.makeText(AfterTomorrowMenu.this,"��ҳ4",Toast.LENGTH_SHORT).show();
		
		break;
	case 4:
		
		Toast.makeText(AfterTomorrowMenu.this,"��ҳ5",Toast.LENGTH_SHORT).show();
		
		break;
	case 5:
		
		Toast.makeText(AfterTomorrowMenu.this,"��ҳ6",Toast.LENGTH_SHORT).show();
		
		break;
	case 6:
		
		Toast.makeText(AfterTomorrowMenu.this,"��ҳ7",Toast.LENGTH_SHORT).show();
		
		break;
	case 7:
		
		Toast.makeText(AfterTomorrowMenu.this,"��ҳ8",Toast.LENGTH_SHORT).show();
		
		break;
	case 8:
		
		Toast.makeText(AfterTomorrowMenu.this,"��ҳ9",Toast.LENGTH_SHORT).show();
		
		break;
	case 9:
		
		Toast.makeText(AfterTomorrowMenu.this,"��ҳ10",Toast.LENGTH_SHORT).show();
		
		break;
	case 10:
		
		Toast.makeText(AfterTomorrowMenu.this,"��ҳ11",Toast.LENGTH_SHORT).show();
		
		break;
	case 11:
		
		Toast.makeText(AfterTomorrowMenu.this,"��ҳ12",Toast.LENGTH_SHORT).show();
		
		break;
	case 12:
		
		Toast.makeText(AfterTomorrowMenu.this,"��ҳ13",Toast.LENGTH_SHORT).show();
		
		break;
	case 13:
		
		Toast.makeText(AfterTomorrowMenu.this,"��ҳ14",Toast.LENGTH_SHORT).show();
		
		break;
	case 14:
		
		Toast.makeText(AfterTomorrowMenu.this,"��ҳ1",Toast.LENGTH_SHORT).show();
		
		break;
	case 15:
		
		Toast.makeText(AfterTomorrowMenu.this,"��ҳ15",Toast.LENGTH_SHORT).show();
		
		break;
	case 16:
		
		Toast.makeText(AfterTomorrowMenu.this,"��ҳ16",Toast.LENGTH_SHORT).show();
		
		break;
	case 17:
		
		Toast.makeText(AfterTomorrowMenu.this,"��ҳ17",Toast.LENGTH_SHORT).show();
		
		break;
	case 18:
		
		Toast.makeText(AfterTomorrowMenu.this,"��ҳ18",Toast.LENGTH_SHORT).show();
		
		break;
	case 19:
		
		Toast.makeText(AfterTomorrowMenu.this,"��ҳ19",Toast.LENGTH_SHORT).show();
		
		break;
	case 20:
		
		Toast.makeText(AfterTomorrowMenu.this,"��ҳ20",Toast.LENGTH_SHORT).show();
		
		break;
	case 21:
		
		Toast.makeText(AfterTomorrowMenu.this,"��ҳ21",Toast.LENGTH_SHORT).show();
		
		break;
	case 22:
		
		Toast.makeText(AfterTomorrowMenu.this,"��ҳ22",Toast.LENGTH_SHORT).show();
		
		break;
				default:
					break;
				}
				
			}
		});
		HttpUtils utils=new HttpUtils();
		utils.send(HttpMethod.POST, "http://10.202.1.53:8080/WebProject/FreeTimeServlet", null,new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String timeString=responseInfo.result;
				Gson gson=new Gson();
				Type classOfT=new TypeToken<List<FreeTime>>(){}.getType();
				List<FreeTime>list1=gson.fromJson(timeString, classOfT);
				List<String>list=new ArrayList<String>();
				list.add("10:00");
				list.add("10:30");
				list.add("11:00");
				list.add("11:30");
				list.add("12:00");
				list.add("12:30");
				list.add("13:00");
				list.add("13:30");
				list.add("14:00");
				list.add("14:30");
				list.add("15:00");
				list.add("15:30");
				list.add("16:00");
				list.add("16:30");
				list.add("17:00");
				list.add("17:30");
				list.add("18:00");
				list.add("18:30");
				list.add("19:00");
				list.add("19:30");
				list.add("20:00");
				list.add("20:30");
				
				MyAdapter adapter=new MyAdapter(list,list1);
				
				gv.setAdapter(adapter);
				
			}
		});
		
	}

	
	
	
}

