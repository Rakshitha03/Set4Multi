package com.example.set4multiplayer;

import java.util.TimerTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class ScheduledTask extends TimerTask {
	
	String roomid;
	Data dobj;
	ScheduledTask(String rid, Data d)
	{
		roomid = rid;
		dobj = d;
	}
	 
 	public void run()
 	{
 		
	}
 	
}