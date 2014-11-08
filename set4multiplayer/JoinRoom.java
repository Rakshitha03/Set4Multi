package com.example.set4multiplayer;

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

		    // Create and populate a List of planet names.
		    /*String[] instructions = new String[] { "1. The game consists of 16 cards and 4 players, where you are the user.","2. On each card is printed a number: either 1,2,3 or 4. These are NOT cards from the standard deck of 52 cards.",
		    		"3. Four cards will be randomly distributed to each player at the beginning of a game.","4. To win, you must make a set, i.e., you must hold four cards having the same number(such as four 1s or four 2s etc.)",
		    		"5. Click on a card you do not want to make a set of. This card will be passed to the player on your left.","6. The next player will pass it to the player on his left and so on until you receive a card from the player on your right.",
		    		"7. You may pass on the received card if you do not want it or choose to pass a different card. Continue passing and receiving until there is a winner.","8. Winner bags 10 points, losers lose 2 ",
		    		"9. By clicking on the 'Score' button, the scores of your previous game will be displayed. ","10.By clicking on the 'Play again' option, a new game begins, and the scores of the previous game are saved "
		    		}; */ 
		    ArrayList<String> roomList = new ArrayList<String>();
		    for(int i=1;i<roomIds.length;i++)
		    {
		    	roomList.add(roomIds[i]);
		    }
		    roomList.add("Join New Room");
		    // Create ArrayAdapter using the planet list.
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
	             			String url = "http://116.202.100.185/set4/createroom.php?RoomId="+send;
	             			HttpGet get = new HttpGet(url);
	             			HttpResponse response = myClient.execute(get);
	             			String responseStr = EntityUtils.toString(response.getEntity());
	             			Intent intent = new Intent(JoinRoom.this, Game.class);
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
	             			Intent intent = new Intent(JoinRoom.this, Game.class);
	             			intent.putExtra("gameId", itemValue);
	             			startActivity(intent);
	                  }
				}
  
           }); 
		  }
		}
