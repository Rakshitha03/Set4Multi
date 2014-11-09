package com.example.set4multiplayer;
import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.text.InputFilter.LengthFilter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Wait extends Activity {
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.wait);
			try {
				status.run();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
		}
		 Handler mhandler = new Handler();
			
		Runnable status = new Runnable()
		{
			@Override
			public void run()
			{
				Log.d("threadstart", "Came ah?");
				try {
					sendHeader();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//poll();
				//winner();
				mhandler.postDelayed(status, 10000);
			}
		};
		 void stopRepeatingTask() {
			    mhandler.removeCallbacks(status);
			  }
		public void sendHeader() throws MalformedURLException
		{
			Log.d("testing", "Entered");
			//final EditText textbox = (EditText) findViewById(R.id.editText1);
			final String value = (String) getIntent().getExtras().get("gameId");			
			new Thread()
			{
			    public void run() 
			    {
			    	try
			    	{
			    	  String url = "http://116.202.147.39/set4/wait.php?id="+value;
			    	  HttpClient client = new DefaultHttpClient();
				      Log.d("testing", "Requesting");
		              // Create Request to server and get response
		              HttpGet request = new HttpGet(url);
		              String headerName = "Polling";
		              request.addHeader(headerName, "full");
		              Log.d("request",request.getFirstHeader(headerName).getValue());
		              HttpResponse response = client.execute(request);
		/*              String responseStr = EntityUtils.toString(response.getEntity());
		              Log.d("testing", responseStr);
		
		              Log.d("response", responseStr);
		              */Header [] val = response.getAllHeaders();
		              for (Header header : val) {
		            	//  Log.d("Key", header.getName());
		            	  //Log.d("Value",header.getValue());  
		            	  if(header.getName().equalsIgnoreCase(headerName))
		            	  {
		            		  Log.d("entered","true");
		            		  if(header.getValue().equalsIgnoreCase("true"))
				              {
		            			  Log.d("changing","activity");
		            			  stopRepeatingTask();
				            	  Intent intent = new Intent(Wait.this,Game.class);
				            	  intent.putExtra("gameId", value);
				            	  startActivity(intent);
				            	  
				              }
		            	  }
		          	}
		              /*Log.d("value",val);
		              Log.d("header", response.getFirstHeader("Polling").getValue());
		              Log.d("Value",val);
		              */
		
		              // Show response on activity 
			    	}
			    	catch(Exception ex)
			    	{
		    	   		ex.printStackTrace();
		    	   		Log.d("testing", "Fail");
			    	}
			    }
			}.start();
	
		}
}
