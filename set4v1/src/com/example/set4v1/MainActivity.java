package com.example.set4v1;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
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
	
	public void showScreens(View view)
	{
	   Intent intent=new Intent(this,Game.class); 	
	   startActivity(intent);
	}

}