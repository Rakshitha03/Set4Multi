package com.example.set4multiplayer;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Timer;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Game extends Activity {

	ImageButton card1;
	ImageButton card2;
	ImageButton card3;
	ImageButton card4;
	ImageButton card5;
    String gameId = "rno1415424651"; // to be changed everywhere
    int imgSources[] = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4};
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_play);
        card1 = (ImageButton)findViewById(R.id.imageButton1);
    	card2 = (ImageButton)findViewById(R.id.imageButton2);
    	 card3 = (ImageButton)findViewById(R.id.imageButton3);
    	 card4 = (ImageButton)findViewById(R.id.imageButton4);
    	 card5 = (ImageButton)findViewById(R.id.imageButton5);
        try {
			startGame();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
	}
    public void startGame() throws InterruptedException 
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
			HttpGet get=new HttpGet("http://116.202.147.39/set4/createroom.php?RoomId="+data);
		
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
	    	playerno = cards1[0];
	    	cards=cards1[1].split(",");
    		dispcards(cards);
    	}
	   
	    status.run();
    	
    	
    }
    public void dispcards(String[] cards)
    {
    	card1.setImageResource(imgSources[Integer.parseInt(cards[0])-1]);
    	card2.setImageResource(imgSources[Integer.parseInt(cards[1])-1]);
    	card3.setImageResource(imgSources[Integer.parseInt(cards[2])-1]);
    	card4.setImageResource(imgSources[Integer.parseInt(cards[3])-1]);
    	if(cards.length == 5)
    	{
    		card5.setVisibility(View.VISIBLE);
        	card5.setImageResource(imgSources[Integer.parseInt(cards[4])-1]);
    	}
    	else
    	{
    		card5.setVisibility(View.INVISIBLE);
    	}
    }	
    
    Handler mhandler = new Handler();
	
	Runnable status = new Runnable()
	{
		@Override
		public void run()
		{
			Log.d("threadstart", "Came ah?");
			poll();
			winner();
			mhandler.postDelayed(status, 2000);
		}
	};

	
    String playerno = "0";
    String incoming = "0";
    String buttonId = "";
    String cards[];
    
    public void passValue(View v)
    {
    	int id = v.getId();
    	if(id == R.id.imageButton1)
    	{
    		buttonId = cards[0];
    		cards[0] = incoming;
    		card1.setImageResource(android.R.color.transparent);
    	}
    	else if(id == R.id.imageButton2)
    	{
    		buttonId = cards[1];
    		cards[1] = incoming;
    		card2.setImageResource(android.R.color.transparent);
    	}
    	else if(id == R.id.imageButton3)
    	{
    		buttonId = cards[2];
    		cards[2] = incoming;
    		card3.setImageResource(android.R.color.transparent);
    	}
    	else if(id == R.id.imageButton4)
    	{
    		buttonId = cards[3];
    		cards[0] = incoming;
    		card4.setImageResource(android.R.color.transparent);
    	}
    	else if(id == R.id.imageButton5)
    	{
    		buttonId = incoming;
    	}
    	
    	Thread t=new Thread()
    	{
        	public void run()
        	{	
        		Log.d("testing", "Came to run");
        		HttpClient myClient=new DefaultHttpClient();
        		final String gameId="rno1415424651";
        		final String player = playerno;
        		final String cardval = buttonId;
        		String test="fsf";
        	
        		try {
    			String game=URLEncoder.encode(gameId,"UTF-8");
    			String pl=URLEncoder.encode(player,"UTF-8");
    			String card = URLEncoder.encode(cardval, "UTF-8");
    			HttpGet get=new HttpGet("http://116.202.147.39/set4/playgame.php?RoomId="+game+"&player="+pl+"&card="+card);
    			HttpResponse resp = myClient.execute(get);
    			String res=EntityUtils.toString(resp.getEntity());
    			Log.d("testing", res);
    			
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			test=e.getMessage();
    			//flag=1;
    		//Toast.maeText(getBaseContext(),"exc",Toast.LENGTH_SHORT).show();
    		}
        	
        	
        	//Toast.makeText(getBaseContext(),res,Toast.LENGTH_SHORT).show();
        	}};
        	t.start();
        	card1.setClickable(false);
        	card2.setClickable(false);
        	card3.setClickable(false);
        	card4.setClickable(false);
        	card5.setClickable(false);
    }
    
    
    public void poll()
    {
    	Thread t = new Thread(){
    		public void run(){
    	Log.d("threadstart", "Came here ah?");
    	try
    	{
    	  String url = "http://116.202.147.39/set4/turn.php";
    	  HttpClient client = new DefaultHttpClient();
	      Log.d("testingfromtimer", "Requesting");
          HttpGet request = new HttpGet(url);
          request.addHeader("RoomId", gameId);
          HttpResponse response = client.execute(request);
          Log.d("exec", "exec");
          
          String player = EntityUtils.toString(response.getEntity());
	      Log.d("testingfromtimer", player);
          if(player == playerno)
          {
        	  client = new DefaultHttpClient();
        	  String data;
			try {
				Log.d("testingsomething", "came into try");
				data = URLEncoder.encode(gameId,"UTF-8");
			
        	  String pl=URLEncoder.encode(playerno, "UTF-8");
        	  url = "http://116.202.147.39/set4/getCards.php?gameId="+data+"&playerno="+pl;
        	  HttpGet request1 = new HttpGet(url);
        	  HttpResponse response2 = client.execute(request1);
        	  String res=EntityUtils.toString(response2.getEntity());
        	  String card[]=new String[4];
        	  card=res.split(",");
        	  Log.d("player", res);
        	  dispcards(card);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
          }

    	}
    	catch(Exception ex)
    	{
	   		ex.printStackTrace();
	   		Log.d("testing", "Fail");
    	}
    }};
    t.start();
    }
    
    public void winner()
    {
    	
    }
    
}
