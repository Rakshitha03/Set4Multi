<?php
	error_reporting(E_ERROR);
	require_once 'connection.php';
	
	/*INPUT: RoomId,p1,1
	  OUTPUT:UPDATE in DB */
	  
	extract($_GET);
	$query = "SELECT * FROM currentgame";
	$rows = mysql_query($query);
	while($row=mysql_fetch_assoc($rows))
	{   
		
		if($RoomId==$row['RoomId']) 
		{
			echo "this";
			$room=$row['RoomId'];
	
			/*GETTING OLD VALUES OF PLAYER WHO PLAYED AND UPDATING HIS DB */
			$inprescards=$row[$player];
			$inprescards=preg_replace("/".$card."/","", $inprescards,1);
			$inprescards=str_replace(",,",",",$inprescards);
			$inprescards=trim($inprescards,",");
				
				
				
			$query1 = "UPDATE currentgame SET $player='$inprescards' WHERE RoomId='$room'";
			mysql_query($query1);
			
			
			
			/*UPDATING NEXT PLAYER IN DB AND ECHOING TO HIM*/
			$nextPlayer=substr($player,-1); 
			$nextPlayer="p".($nextPlayer+1);
			if($nextPlayer=="p5")
				$nextPlayer="p1";
			$outprescards=$row[$nextPlayer];
			if($outprescards=="")
					$outprescards=$card;
			else
				$outprescards=$outprescards.",".$card;
			
			$query1 = "UPDATE currentgame SET $nextPlayer='$outprescards' WHERE RoomId='$room'";
			mysql_query($query1);
			echo "$card";
			
			$query1 = "UPDATE currentgame SET Turn='$nextPlayer' WHERE RoomId='$room'";
			mysql_query($query1);
			echo"<br>";
			echo "player is $nextPlayer his cards are $outprescards";
			//echo substr_count($outprescards,$outprescards[0]);
			if(substr_count($outprescards,$outprescards[0])==4||substr_count($outprescards,$outprescards[1])==4)
			{
				$query1 = "UPDATE currentgame SET Winner='$nextPlayer' WHERE RoomId='$room'";
				mysql_query($query1);
			}
		}
		
	}
	
?>