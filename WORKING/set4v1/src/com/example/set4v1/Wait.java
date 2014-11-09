package com.example.set4v1;
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
				value = (String) getIntent().getExtras().get("gameId");	
				startGame(value);
				status.run();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		Handler mhandler = new Handler();
		String value;
		String ip = "http://192.168.1.9/Android/";
		String player;
		String cardvals;
			
		Runnable status = new Runnable()
		{
			@Override
			public void run()
			{
				Log.d("waiting", "Came ah?");
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
		private Object playerno;
		 void stopRepeatingTask() {
			    mhandler.removeCallbacks(status);
			  }
		public void sendHeader() throws MalformedURLException
		{
			Log.d("waiting", "Entered");
			//final EditText textbox = (EditText) findViewById(R.id.editText1);
			
			new Thread()
			{
			    public void run() 
			    {
			    	try
			    	{
			    	  String url = ip+"wait.php?id="+value;
			    	  HttpClient client = new DefaultHttpClient();
				      Log.d("testing", "Requesting");
		              // Create Request to server and get response
		              HttpGet request = new HttpGet(url);
		              String headerName = "Polling";
		              request.addHeader(headerName, "full");
		              Log.d("request",request.getFirstHeader(headerName).getValue());
		              HttpResponse response = client.execute(request);
		
		              Header [] val = response.getAllHeaders();
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
				            	  intent.putExtra("player", player);
				            	  intent.putExtra("cards", cardvals);
				            	  startActivity(intent);
				            	  
				              }
		            	  }
		          	}
		              
		
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
		
		public void startGame(String value) throws InterruptedException 
	    {
	    	Intent i=getIntent();
	    	final String gameId = value;
	    	//final String gameId=i.getStringExtra("gameId");
	    	//final String gameId="rno1415424651";
	    	int flag=0;
	    	Log.d("testing", "came to start");
	    	final Data d=new Data();
	    	Thread t=new Thread(){
	    	public void run(){	
	    	Log.d("testing", "Came to run");
	    	HttpClient myClient=new DefaultHttpClient();
	    	String res="rash";
	    	String test="fsf";
	    	
	    	try {
				String data=URLEncoder.encode(gameId,"UTF-8");
				HttpGet get=new HttpGet(ip+"createroom.php?RoomId="+data);
			
	    	//get.setEntity()
				HttpResponse response;
	    		
			
				Log.d("testing", "request");
				response = myClient.execute(get);
				res=EntityUtils.toString(response.getEntity());
				Log.d("testing", res);
				d.put(res);
				Log.d("testget1", d.get());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				test=e.getMessage();
				d.put("fail");
				//flag=1;
			//Toast.maeText(getBaseContext(),"exc",Toast.LENGTH_SHORT).show();
			}
	    	
	    	
	    	//Toast.makeText(getBaseContext(),res,Toast.LENGTH_SHORT).show();
	    	}};
	    	t.start();
	    	Thread.sleep(8000);
	    	//Toast.makeText(getBaseContext(),d.get(),Toast.LENGTH_SHORT).show();
	    	String cardValues = d.get();
	    	if(cardValues.equals("pls")||cardValues.equals("Room Full"))
	    	{
	    		Toast.makeText(getBaseContext(), "There was an error",Toast.LENGTH_LONG).show();
	    	}
	    	else
	    	{
	    		String cards1[] = cardValues.split(":");
		    	player = cards1[0];
		    	Log.d("playerno", player);
		    	cardvals = cards1[1];
	    	}
		   
	    	
	    	
	    }
	    
}
