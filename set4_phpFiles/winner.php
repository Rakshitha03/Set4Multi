<?php
	$headers = apache_request_headers();
	require_once 'connection.php';
	
	/* INPUT: GAME ID
	   OUTPUT: WINNER OF THE GAME else NONE
	*/
	$room=$headers['RoomId'];
	$query = "SELECT Turn FROM currentgame WHERE RoomId='$room'";
	$rows = mysql_query($query);
	$row=mysql_fetch_assoc($rows);
	$turn=$row['TURN'];
	$tosend=$turn;
	if($turn!="none")
	{
		$query = "SELECT * FROM currentgame WHERE RoomId='$room'";
		$rows = mysql_query($query);
		$row=mysql_fetch_assoc($rows);
		$p1card=$row['p1'];
		$p2card=$row['p2'];
		$p3card=$row['p3'];
		$p4card=$row['p4'];
		$tosend=$tosend.";p1:$p1card";
		$tosend=$tosend.";p2:$p2card";
		$tosend=$tosend.";p3:$p3card";
		$tosend=$tosend.";p4:$p4card";
	}
	echo $tosend;
?>