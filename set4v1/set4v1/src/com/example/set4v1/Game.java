package com.example.set4v1;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Timer;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.Toast;

public class Game extends Activity {
	
	String ip = "http://192.168.1.9/Android/";
	ImageButton card1;
	ImageButton card2;
	ImageButton card3;
	ImageButton card4;
	ImageButton card5;
	TextView currplayer;
    String gameId; // to be changed everywhere
    int imgSources[] = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4};
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_play);
        playerno = getIntent().getStringExtra("player");
        cards = getIntent().getStringExtra("cards").split(",");
        gameId = getIntent().getStringExtra("gameId");
        Toast.makeText(this, "You are player: "+playerno, Toast.LENGTH_LONG).show();
        card1 = (ImageButton)findViewById(R.id.imageButton1);
    	card2 = (ImageButton)findViewById(R.id.imageButton2);
    	 card3 = (ImageButton)findViewById(R.id.imageButton3);
    	 card4 = (ImageButton)findViewById(R.id.imageButton4);
    	 card5 = (ImageButton)findViewById(R.id.imageButton5);
    	 currplayer = (TextView) findViewById(R.id.textView1);
    	 if(!playerno.equalsIgnoreCase("p1"))
    	 {
    		card1.setClickable(false);
         	card2.setClickable(false);
         	card3.setClickable(false);
         	card4.setClickable(false);
         	card5.setClickable(false);
    	 }
        dispcards(cards);
		status.run();
         
	}
    
    
    
    public void dispcards(String[] cards)
    {
    	this.cards = cards;
    	if(cards.length == 3)
    	{
    		card1.setImageResource(imgSources[Integer.parseInt(cards[0])-1]);
        	card2.setImageResource(imgSources[Integer.parseInt(cards[1])-1]);
        	card3.setImageResource(imgSources[Integer.parseInt(cards[2])-1]);
    	}
    	else if(cards.length == 4)
    	{
	    	card1.setImageResource(imgSources[Integer.parseInt(cards[0])-1]);
	    	card2.setImageResource(imgSources[Integer.parseInt(cards[1])-1]);
	    	card3.setImageResource(imgSources[Integer.parseInt(cards[2])-1]);
	    	card4.setImageResource(imgSources[Integer.parseInt(cards[3])-1]);
	    	card5.setVisibility(View.INVISIBLE);
    	}
    	else if(cards.length == 5)
    	{
    		card5.setVisibility(View.VISIBLE);
        	card5.setImageResource(imgSources[Integer.parseInt(cards[4])-1]);
        	if(cards.length == 5){
	    		incoming = cards[4];
	    	}
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
			mhandler.postDelayed(status, 3000);
		}
	};

	
    String playerno = "0";
    String incoming = "0";
    String buttonId = "";
    String cards[];
    
    @SuppressLint("NewApi")
	public void passValue(View v)
    {
    	int id = v.getId();
    	int incomingNotPassed = 0;
    	if(id == R.id.imageButton1)
    	{
    		buttonId = cards[0];
    		if(!incoming.equals("0"))
    		{
    			cards[0] = incoming;
    			incomingNotPassed = 1;
    		}
    		Log.d("Passed", "Passed");
    		card1.setImageResource(android.R.color.transparent);
    	}
    	else if(id == R.id.imageButton2)
    	{
    		buttonId = cards[1];
    		if(!incoming.equals("0"))
    		{
    			cards[1] = incoming;
    			incomingNotPassed = 1;
    		}
    		card2.setImageResource(android.R.color.transparent);
    	}
    	else if(id == R.id.imageButton3)
    	{
    		buttonId = cards[2];
    		if(!incoming.equals("0"))
    		{
    			cards[2] = incoming;
    			incomingNotPassed = 1;
    		}
    		card3.setImageResource(android.R.color.transparent);
    	}
    	else if(id == R.id.imageButton4)
    	{
    		buttonId = cards[3];
    		if(!incoming.equals("0"))
    		{
    			cards[3] = incoming;
    			incomingNotPassed = 1;
    		}
    		card4.setImageResource(android.R.color.transparent);
    	}
    	else if(id == R.id.imageButton5)
    	{

    		buttonId = cards[4];
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
    			HttpGet get=new HttpGet(ip+"playgame.php?RoomId="+game+"&player="+pl+"&card="+card);
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
        	if(incomingNotPassed == 1)
        	{
        		String newcards[];
        		if(incoming.equals("0"))
        		{
        			newcards = Arrays.copyOfRange(cards, 0, 3);
        		}
        		else
        		{
        			newcards = Arrays.copyOfRange(cards, 0, 4);
        		}
        		dispcards(newcards);
        	}
        	else
        	{
        		card5.setVisibility(View.INVISIBLE);
        	}
        	card1.setClickable(false);
        	card2.setClickable(false);
        	card3.setClickable(false);
        	card4.setClickable(false);
        	card5.setClickable(false);
    }
    
    @Override
    public void onBackPressed()
    {
    	
    }
    
	final Data d3 = new Data();

    public void poll()
    {
    	Thread t = new Thread(){
    		public void run(){
    	Log.d("threadstart", "Came here ah?"+playerno);
    	try
    	{
    	  String url = ip+"turn.php";
    	  HttpClient client = new DefaultHttpClient();
	      Log.d("testingfromtimer", "Requesting");
          HttpGet request = new HttpGet(url);
          request.addHeader("RoomId", gameId);
          HttpResponse response = client.execute(request);
          Log.d("exec", "exec");
          
          String player = EntityUtils.toString(response.getEntity());
	      Log.d("testingfromtimer", player);
	      if(player.equals(playerno))
          {
        	  client = new DefaultHttpClient();
        	  String data;
			try {
				Log.d("testingsomething", "came into try");
				data = URLEncoder.encode(gameId,"UTF-8");
			
        	  String pl=URLEncoder.encode(player, "UTF-8");
        	  url = ip+"getCards.php?RoomId="+data+"&player="+pl;
        	  HttpGet request1 = new HttpGet(url);
        	  HttpResponse response2 = client.execute(request1);
        	  String res=EntityUtils.toString(response2.getEntity());
        	  String card[];
        	  card=res.split(",");
        	  d3.setPlayer(card);
        	  mhandler1.post(mUpdateResults);

        	  Log.d("player", res);
        	  
			}
		
			
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
          }
	      else
		  {
		      d3.put(player);
		      mhandler1.post(updatetext);
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
    
    Handler mhandler1 = new Handler();
    
    final Runnable mUpdateResults = new Runnable() {
        public void run() {
        	card1.setClickable(true);
         	card2.setClickable(true);
         	card3.setClickable(true);
         	card4.setClickable(true);
         	card5.setClickable(true);
            dispcards(d3.getPlayer());
        }
    };
    
    final Runnable updatetext = new Runnable() {
        public void run() {
        	currplayer.setText(d3.get()+"'s\nturn");
        }
    };
    
    void stopRepeatingTask() {
	    mhandler.removeCallbacks(status);
	    mhandler1.removeCallbacks(mUpdateResults);
	  }
    
    public void winner()
    {
    	Log.d("winner", "anyone won?");
    	Thread t=new Thread()
    	{
        	public void run()
        	{	
        		HttpClient myClient=new DefaultHttpClient();
        		String test="fsf";
        	
        		try {
    			String game=URLEncoder.encode(gameId,"UTF-8");
    			HttpGet get=new HttpGet(ip+"winner.php?room="+game);
    			HttpResponse resp = myClient.execute(get);
    			String res=EntityUtils.toString(resp.getEntity());
    			Log.d("winner", res);
    			if(!res.equalsIgnoreCase("none"))
    			{
        			stopRepeatingTask();
    				Intent i = new Intent(getBaseContext(), Winner.class);
    				i.putExtra("playerno", ""+res.split(":")[0].charAt(1));
    				String wincards = res.split(":")[1];
    				String wincr[] = wincards.split(",");
    				for(int x=0; x<wincr.length-1; x++)
    				{
    					if(wincr[x].equals(wincr[x+1]))
    					{
    	    				i.putExtra("card", wincr[x]);
    	    				break;
    					}
    				}
    				startActivity(i);
    			}
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
    }
    
}
