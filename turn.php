<?php
	$headers = apache_request_headers();
	require_once 'connection.php';
	
	/*INPUT: ROOM ID
	  OUTPUT: player whose turn it is */
	  
	$room=$headers['RoomId'];
	$query = "SELECT Turn FROM currentgame WHERE RoomId='$room'";
	$rows = mysql_query($query);
	$row=mysql_fetch_assoc($rows);
	$turn=$row['TURN'];
	header("TURN:$turn");

?>