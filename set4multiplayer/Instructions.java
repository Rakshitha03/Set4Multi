package com.example.set4multiplayer;

import android.app.Activity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Arrays;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/*public class Instructions extends Activity{
	s[0]="1. The game consists of 16 cards and 4 players, where you are the user.";
    s[1] ="2. On each card is printed a number: either 1,2,3 or 4. \n    These are NOT cards from the standard deck of 52 cards.";
    s[2]="3. Four cards will be randomly distributed to each player at the beginning of\n     a game.";
    s[3]="4. To win, you must make a set, i.e., you must hold four cards having the same \n     number(such as four 1s or four 2s etc.)";
    s[4]="5. Click on a card you do not want to make a set of. \n     This card will be passed to the player on your left.";
    s[5]="6. The next player will pass it to the player on his left and so on until you \n     receive a card from the player on your right.";
    s[6]="7. You may pass on the received card if you do not want it or choose to pass a \n    different card. Continue passing and receiving until there is a winner.";
    s[7]="8. Winner bags 10 points, losers lose 2 ";
    s[8]="9. By clicking on the 'Score' button, the scores of your previous game will be \n     displayed. ";
    s[9]="10.By clicking on the 'Play again' option, a new game begins, and the scores of \n      the previous game are saved ";

	protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructions);
	}

}*/

public class Instructions extends Activity {
  
  private ListView mainListView ;
  private ArrayAdapter<String> listAdapter ;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.instructions);
    
    // Find the ListView resource. 
    mainListView = (ListView) findViewById( R.id.mainListView );

    // Create and populate a List of planet names.
    String[] instructions = new String[] { "1. The game consists of 16 cards and 4 players, where you are the user.","2. On each card is printed a number: either 1,2,3 or 4. These are NOT cards from the standard deck of 52 cards.",
    		"3. Four cards will be randomly distributed to each player at the beginning of a game.","4. To win, you must make a set, i.e., you must hold four cards having the same number(such as four 1s or four 2s etc.)",
    		"5. Click on a card you do not want to make a set of. This card will be passed to the player on your left.","6. The next player will pass it to the player on his left and so on until you receive a card from the player on your right.",
    		"7. You may pass on the received card if you do not want it or choose to pass a different card. Continue passing and receiving until there is a winner.","8. Winner bags 10 points, losers lose 2 ",
    		"9. By clicking on the 'Score' button, the scores of your previous game will be displayed. ","10.By clicking on the 'Play again' option, a new game begins, and the scores of the previous game are saved "
    		};  
    ArrayList<String> instructionList = new ArrayList<String>();
    instructionList.addAll( Arrays.asList(instructions) );
    
    // Create ArrayAdapter using the planet list.
    listAdapter = new ArrayAdapter<String>(this, R.layout.simple_rows, instructionList);
    
    // Add more planets. If you passed a String[] instead of a List<String> 
    // into the ArrayAdapter constructor, you must not add more items. 
    // Otherwise an exception will occur.
    /*listAdapter.add( "Ceres" );
    listAdapter.add( "Pluto" );
    listAdapter.add( "Haumea" );
    listAdapter.add( "Makemake" );
    listAdapter.add( "Eris" );
    */
    // Set the ArrayAdapter as the ListView's adapter.
    mainListView.setAdapter( listAdapter );      
  }
}