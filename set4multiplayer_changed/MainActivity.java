package com.example.set4multiplayer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (getIntent().getBooleanExtra("EXIT", false)) 
		{
		        finish();
		        System.exit(0);
		}
		Log.d("strt","newActivity");
		Intent intent = new Intent(this, Winner.class);
		intent.putExtra("playerNo", "2");
		intent.putExtra("card", "3");
		startActivity(intent);
	}
}
/*	public void getRooms(View v)
	{
		new Thread(){
			 public void run(){
		try {
			String send = URLEncoder.encode("GetRooms", "UTF-8");
			HttpClient myClient = new DefaultHttpClient();
			String url = "http://116.202.147.39/set4/roomlist.php?id="+send;
			HttpGet get = new HttpGet(url);
			HttpResponse response = myClient.execute(get);
			String responseStr = EntityUtils.toString(response.getEntity());
			Intent intent = new Intent(MainActivity.this, JoinRoom.class);
			intent.putExtra("RoomList", responseStr);
			startActivity(intent);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			 }
		}.start();
		
	}
	public void clickNew(View v)
	{
		finish();
		System.exit(0);
	}
	public void instructions(View view) {
		Intent intent = new Intent(this, Instructions.class);
		startActivity(intent);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
}
*/