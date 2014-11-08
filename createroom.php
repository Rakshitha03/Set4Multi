<?php
	require_once 'connection.php';
	/*
		INPUT: RoomId or new
		OUTPUT: Room Created, list created in db, player1's cards echoed */
		
	extract($_GET);
    if($RoomId=="new")
	{
		 $room="rno".time();
		 $numbers = range(1,16);
		shuffle($numbers);
		
		for($i=0;$i<16;$i++)
		{
			$numbers[$i]=($numbers[$i]%4)+1;
		}	
		
		$numbers=implode($numbers,",");
	
		 $query = "INSERT INTO currentgame VALUES('$room','$numbers','','','','','p1','none')";
		 mysql_query($query);
		 
		 echo $room;
	}
	else
	{
	    $query = "SELECT * FROM currentgame";
		$rows = mysql_query($query);
		while($row=mysql_fetch_assoc($rows))
		{   
			$lists=$row['RoomId'];
			if($lists==$RoomId)
			{
				$li=$row['Cards'];
			
				$numbers=explode(",",$li);
			
				if($row['p1']== "")
				{
					echo "herhe".$row['p1'];
					$player="p1";
					$playerValues=array_slice($numbers,0,4);				
				}
				else if($row['p2']== "")
				{
					echo $row['p2'];
					$player="p2";
					$playerValues=array_slice($numbers,5,9);				
				}
				else if($row['p3']== "")
				{
					$player="p3";
					$playerValues=array_slice($numbers,9,12);				
				}
				else if($row['p4']== "")
				{
					$player="p4";
					$playerValues=array_slice($numbers,12,16);				
				}
				else
				{
					echo "Room Full";
				}
				
				$playerValues=implode($playerValues,",");
				$query1 = "UPDATE currentgame SET $player='$playerValues' WHERE RoomId='$lists'";
				echo $query1;
				mysql_query($query1);
				echo "$player : $playerValues;
				
			}
			
		}
	}
?>