<?php
	require_once 'connection.php';
	extract($_GET);
    if($room=="new")
	{
		 $room="rno".time();
		 $numbers = range(1,16);
		shuffle($numbers);
		
		for($i=0;$i<16;$i++)
		{
			$numbers[$i]=($numbers[$i]%4)+1;
		}	
		
		$numbers=implode($numbers,",");
	
		 $query = "INSERT INTO currentgame VALUES('$room','$numbers','','','','')";
		 mysql_query($query);
	}
	else
	{
	    $query = "SELECT * FROM currentgame";
		$rows = mysql_query($query);
		while($row=mysql_fetch_assoc($rows))
		{   
			$lists=$row['RoomId'];
			if($lists==$room)
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