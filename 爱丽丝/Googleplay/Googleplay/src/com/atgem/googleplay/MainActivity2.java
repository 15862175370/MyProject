package com.atgem.googleplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity2 extends ActionBarActivity implements OnQueryTextListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		//getactionview
		/*SearchView searchView=(SearchView)menu.findItem(R.id.action_search).getActionView();
		searchView.setOnQueryTextListener(this);*/
		return true;
	}
     
	
     @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	// TODO Auto-generated method stub
    	/* if(item.getItemId()==R.id.action_search){
    		 Toast.makeText(this,item.getTitle(), 0).show();
    	 }*/
    	 
    	return super.onOptionsItemSelected(item);
    }

	@Override
	public boolean onQueryTextChange(String arg0) {
		// TODO Auto-generated method stub
		Toast.makeText(this,arg0,Toast.LENGTH_SHORT).show();
		return true;
	}

	@Override
	public boolean onQueryTextSubmit(String arg0) {
		Toast.makeText(this,arg0,Toast.LENGTH_SHORT).show();
		// TODO Auto-generated method stub
		return true;
	}
	public void click(View view){
		Intent intent=new Intent(this,DetailActivity.class);
		startActivity(intent);
	}
}
