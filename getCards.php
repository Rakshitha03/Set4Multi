<?php
	require_once 'connection.php';
	/*INPUT GAME ID AND PLAYER NUMBER */
	
	extract($_GET);
	
	$query = "SELECT $player FROM currentgame WHERE RoomId='$RoomId'";
	$rows = mysql_query($query);
	//print_r($rows);
	$row=mysql_fetch_assoc($rows);
	echo $row[$player];
?>