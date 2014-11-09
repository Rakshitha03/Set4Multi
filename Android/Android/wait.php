<?php
	require_once 'connection.php';
	$headers = apache_request_headers();
	
/* NO INPUT
	OUTPUT: LIST OF ROOMS THAT CAN ACCOMODATE AT LEAST 1 PLAYER */
	extract($_GET);
	$query = "SELECT * FROM currentgame WHERE RoomId='$id'";
	$rows = mysql_query($query);
	//$roomlist="";
	$row=mysql_fetch_assoc($rows);
	if($row['p2']==""||$row['p3']==""||$row['p4']=="")
	{
		header('Polling: false');	
	}
	else
	{
		header('Polling: true');
	}
?>