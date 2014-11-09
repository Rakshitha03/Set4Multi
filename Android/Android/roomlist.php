<?php
	require_once 'connection.php';
/* NO INPUT
	OUTPUT: LIST OF ROOMS THAT CAN ACCOMODATE AT LEAST 1 PLAYER */
	
	$query = "SELECT * FROM currentgame";
	$rows = mysql_query($query);
	$roomlist="";
	while($row=mysql_fetch_assoc($rows))
	{  
		if($row['p2']==""||$row['p3']==""||$row['p4']=="")
		{
			$rm=$row['RoomId'];
			$roomlist=$roomlist.";$rm";
		}
	}
	echo $roomlist;
?>