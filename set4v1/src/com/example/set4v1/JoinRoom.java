package com.example.set4v1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
public class JoinRoom extends Activity{
	
			String ip = "http://192.168.1.9/Android/";

		  
		  private ListView mainListView ;
		  private ArrayAdapter<String> listAdapter ;
		  
		  /** Called when the activity is first created. */
		  @Override
		  public void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    setContentView(R.layout.instructions);
		    String newString;
			if (savedInstanceState == null) {
			    Bundle extras = getIntent().getExtras();
			    if(extras == null) {
			        newString= null;
			    } else {
			        newString= extras.getString("RoomList");
			    }
			} else {
			    newString= (String) savedInstanceState.getSerializable("RoomList");
			}
			String [] roomIds = newString.split(";");
			//Log.d("testing", roomIds[1]);
			//Toast.makeText(this, newString, Toast.LENGTH_SHORT);
			//Log.d("testing",newString);
		    
		    // Find the ListView resource. 
		    mainListView = (ListView) findViewById( R.id.mainListView );

		    ArrayList<String> roomList = new ArrayList<String>();
		    for(int i=1;i<roomIds.length;i++)
		    {
		    	roomList.add(roomIds[i]);
		    }
		    roomList.add("Join New Room");
		    listAdapter = new ArrayAdapter<String>(this, R.layout.simple_rows, roomList);
		    

		    mainListView.setAdapter( listAdapter );     
		    mainListView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					 // ListView Clicked item index
	                 int itemPosition     = position;
	                 
	                 // ListView Clicked item value
	                 String  itemValue    = (String) mainListView.getItemAtPosition(position);
	                    
	                  // Show Alert 
	                  if(itemValue.equals("Join New Room"))
	                  {
	                	  new Thread(){
	             			 public void run(){
	             		try {
	             			String send = URLEncoder.encode("new", "UTF-8");
	             			HttpClient myClient = new DefaultHttpClient();
	             			String url = ip+"createroom.php?RoomId="+send;
	             			HttpGet get = new HttpGet(url);
	             			HttpResponse response = myClient.execute(get);
	             			String responseStr = EntityUtils.toString(response.getEntity());
	             			Intent intent = new Intent(JoinRoom.this, Wait.class);
	             			intent.putExtra("gameId", responseStr);
	             			startActivity(intent);
	             			
	             		} catch (UnsupportedEncodingException e) {
	             			// TODO Auto-generated catch block
	             			e.printStackTrace();
	             		} catch (ClientProtocolException e) {
	             			// TODO Auto-generated catch block
	             			e.printStackTrace();
	             		} catch (IOException e) {
	             			// TODO Auto-generated catch block
	             			e.printStackTrace();
	             		}
	             			 }
	             		}.start();
	             		
	                  }
	                  else
	                  {
	             			Intent intent = new Intent(JoinRoom.this, Wait.class);
	             			intent.putExtra("gameId", itemValue);
	             			startActivity(intent);
	                  }
				}
  
           }); 
		  }
		}
