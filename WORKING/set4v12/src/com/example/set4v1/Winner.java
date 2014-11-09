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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Winner extends Activity {

	
	int imgSources[] = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4};
	ImageView card1;
	ImageView card2;
	ImageView card3;
	ImageView card4;
	TextView t;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.winner);
		Intent i = getIntent();
		String player = i.getExtras().getString("playerno");
		String cardVal = i.getExtras().getString("card");
		t = (TextView)findViewById(R.id.Text1);
		t.setText("Player "+Integer.parseInt(player)+ " Won!!!");
		card1 = (ImageView)findViewById(R.id.imageButton1);
		card2 = (ImageView)findViewById(R.id.imageButton2);
		card3 = (ImageView)findViewById(R.id.imageButton3);
		card4 = (ImageView)findViewById(R.id.imageButton4);
		card1.setImageResource(imgSources[Integer.parseInt(cardVal)-1]);
    	card2.setImageResource(imgSources[Integer.parseInt(cardVal)-1]);
    	card3.setImageResource(imgSources[Integer.parseInt(cardVal)-1]);
    	card4.setImageResource(imgSources[Integer.parseInt(cardVal)-1]);
		//setContentView(R.layout.winner);
	}
	public void clickNew(View v)
	{
		Intent intent = new Intent(this, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("EXIT", true);
		startActivity(intent);
	}
	public void clickStart (View v){
	    Intent intent = new Intent(this,MainActivity.class);
	    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    finish();
	    startActivity(intent);
	}

}
