package com.example.set4v1;

import android.util.Log;

public class Data 
{
	String t;
	String p;
	Data()
	{
		t="pls";
	}
	public void put(String val)
	{
		t=val;
		Log.d("test", t);
	}
	public String get()
	{
		Log.d("testget", t);
		return t;
	}
	
	public void setPlayer(String pl)
	{
		p = pl;
	}
	
	public String getPlayer()
	{
		return p;
	}
}
