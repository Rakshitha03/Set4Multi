<?php
	require_once 'connection.php';
	
	/*INPUT: RoomId,p1,1
	  OUTPUT:UPDATE in DB */
	  
	extract($_GET);
	$query = "SELECT * FROM currentgame";
	$rows = mysql_query($query);
	while($row=mysql_fetch_assoc($rows))
	{   
		
		if($room==$row['RoomId']) 
		{
			$room=$row['RoomId'];
	
			/*GETTING OLD VALUES OF PLAYER WHO PLAYED AND UPDATING HIS DB */
			$inprescards=$row[$player];
			$inprescards=preg_replace("/".$card."/","", $inprescards,1);
			$inprescards=str_replace(",,",",",$inprescards);
			$query1 = "UPDATE currentgame SET $player='$inprescards' WHERE RoomId='$room'";
			mysql_query($query1);
			
			/*UPDATING NEXT PLAYER IN DB AND ECHOING TO HIM*/
			$nextPlayer=substr($player,-1); 
			$nextPlayer="p".($nextPlayer+1);
			if($nextPlayer=="p5")
				$nextPlayer=="p1"
			$outprescards=$row[$nextPlayer];
			$outprescards=$outprescards.",".$card;
			
			$query1 = "UPDATE currentgame SET $nextPlayer='$outprescards' WHERE RoomId='$room'";
			mysql_query($query1);
			
			
		}
	}
	
?>