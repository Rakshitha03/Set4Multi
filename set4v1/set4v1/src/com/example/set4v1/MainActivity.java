package com.example.set4v1;


import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

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
	}
    
    String ip = "http://192.168.1.9/Android/";
    
    public void clickNew(View v)
	{
		finish();
		System.exit(0);
	}
	public void instructions(View view) {
		Intent intent = new Intent(this, Instructions.class);
		startActivity(intent);
	}
	/*
	public void showScreens(View view)
	{
	   Intent intent=new Intent(this,Game.class); 	
	   startActivity(intent);
	}*/
	
	public void getRooms(View v)
	{
		new Thread(){
			 public void run(){
		try {
			String send = URLEncoder.encode("GetRooms", "UTF-8");
			HttpClient myClient = new DefaultHttpClient();
			String url = ip+"roomlist.php?id="+send;
			HttpGet get = new HttpGet(url);
			HttpResponse response = myClient.execute(get);
			String responseStr = EntityUtils.toString(response.getEntity());
			Intent intent = new Intent(MainActivity.this, JoinRoom.class);
			intent.putExtra("RoomList", responseStr);
			startActivity(intent);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
			 }
		}.start();
		
	}

}