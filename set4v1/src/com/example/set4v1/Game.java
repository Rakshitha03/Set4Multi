package com.example.set4v1;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class Game extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_play);
        startGame();
	}
    public void startGame() 
    {
    	Intent i=getIntent();
    //	String gameId=i.getExtras("gameId");
    	final String gameId="rno1415424651";
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
			HttpGet get=new HttpGet("http://192.168.1.6/mse/createroom.php?RoomId="+data);
		
    	//get.setEntity()
			HttpResponse response;
    		
		
			Log.d("testing", "request");
			response = myClient.execute(get);
			res=EntityUtils.toString(response.getEntity());
			
			d.put(res);
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
    	//t.start();
    	
    	Toast.makeText(getBaseContext(),d.get(),Toast.LENGTH_SHORT).show();
    }
}
